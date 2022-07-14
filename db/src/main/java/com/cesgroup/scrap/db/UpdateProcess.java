package com.cesgroup.scrap.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.cesgroup.scrap.db.bean.SqlParamsBean;

@Repository
@Transactional
public class UpdateProcess {
	@Resource
	private JdbcDAO jdbcDAO;
	@Resource
	private JdbcProductionDAO jdbcProductionDAO;
	@Resource 
	private SqlProcess sqlProcess;
	
	@Autowired
	@Qualifier("defaultLobHandler")
	private LobHandler lobHandler;

	public int process(String sqlId, List<Object> args) {
		int rtn=0;
		String sql = sqlProcess.getUpdateSql(sqlId);
		sql=sqlProcess.convertSql(sql);
		if(!sql.isEmpty()){
			rtn=jdbcDAO.update(sql,args.toArray());
		}else{
			new Exception("Not Found sql !");
		}
		return rtn;
	}
	
	@Transactional(rollbackFor = Exception.class)
	public void process(List<SqlParamsBean> sqlParams) {
		if(sqlParams!=null){
			for(SqlParamsBean sqlParamsBean:sqlParams){
				process(sqlParamsBean.getSqlId(),sqlParamsBean.getParasList());
			}
		};
	}
	
	public int updateForFe(String sqlId, Object...args){
		String sql = sqlProcess.getUpdateSql(sqlId);
		sql=sqlProcess.convertSql(sql);
		if (!StringUtils.isEmpty(sql))
			return jdbcProductionDAO.update(sql, args);
		else
			throw new RuntimeException("未配置删除语句!");
	}
	
	public int update(String sqlId, Object...args){
		String sql = sqlProcess.getUpdateSql(sqlId);
		sql=sqlProcess.convertSql(sql);
		if (!StringUtils.isEmpty(sql))
			return jdbcDAO.update(sql, args);
		else
			throw new RuntimeException("未配置删除语句!");
	}

	public int updateBlob(String sqlId, final List<Object> params) throws Exception{
		String sql = sqlProcess.getUpdateSql(sqlId);
		JdbcTemplate jdbcTemplate = jdbcDAO.getJdbcTemplate();
		return jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler){
			@Override
			protected void setValues(PreparedStatement pstmt, LobCreator lobCreator) throws SQLException, DataAccessException {
				int index = 1;
				for (Object obj : params){
					if (obj instanceof byte[] || obj instanceof Byte[]){
						lobCreator.setBlobAsBytes(pstmt, index, (byte[])obj);
					} else {
						pstmt.setObject(index, obj);
					}
					index++;
				}
			}
		});
	}
	
	
	public int updateClob(String sqlId, final List<Object> params) throws Exception{
		String sql = sqlProcess.getUpdateSql(sqlId);
		JdbcTemplate jdbcTemplate = jdbcDAO.getJdbcTemplate();
		return jdbcTemplate.execute(sql, new AbstractLobCreatingPreparedStatementCallback(lobHandler){
			@Override
			protected void setValues(PreparedStatement pstmt, LobCreator lobCreator) throws SQLException, DataAccessException {
				int index = 1;
				for (Object obj : params){
					if (obj.toString().startsWith("CLOB")){
						lobCreator.setClobAsString(pstmt, index, obj.toString().substring(4));
					} else {
						pstmt.setObject(index, obj);
					}
					index++;
				}
			}
		});
	}
}
