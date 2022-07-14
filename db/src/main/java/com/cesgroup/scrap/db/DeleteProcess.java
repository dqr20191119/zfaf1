package com.cesgroup.scrap.db;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Repository;

@Repository
public class DeleteProcess {
	@Resource
	private JdbcDAO jdbcDAO;
	@Resource
	private JdbcProductionDAO jdbcProductionDAO;
	@Resource 
	private SqlProcess sqlProcess;
	
	public boolean deleteById(String sqlId, Object id){
		return deleteByIdList(sqlId, new Integer[]{Integer.valueOf(id.toString())});
	}
	
	public boolean deleteByIdList(String sqlId,Integer[] objs){
		String sql = sqlProcess.getDeleteSql(sqlId);
		sql=sqlProcess.convertSql(sql);
		return jdbcDAO.batchUpdate(sql, objs);
	}
	
	public int delete(String sqlId,List<Object> fields){
		String sql = sqlProcess.getDeleteSql(sqlId);
		sql=sqlProcess.convertSql(sql);
		return jdbcDAO.update(sql,fields.toArray());
	}
	
	public int deleteForFe(String sqlId,List<Object> fields){
		String sql = sqlProcess.getDeleteSql(sqlId);
		sql=sqlProcess.convertSql(sql);
		return jdbcProductionDAO.update(sql,fields.toArray());
	}
	
}
