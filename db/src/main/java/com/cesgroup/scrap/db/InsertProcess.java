package com.cesgroup.scrap.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.AbstractLobCreatingPreparedStatementCallback;
import org.springframework.jdbc.support.lob.LobCreator;
import org.springframework.jdbc.support.lob.LobHandler;
import org.springframework.stereotype.Repository;
@Repository
public class InsertProcess {
	
//	private static final Logger log = LoggerFactory.getLogger(InsertProcess.class);
	
	@Resource
	private JdbcDAO jdbcDAO;
	@Resource
	private JdbcProductionDAO jdbcProductionDAO;
	@Resource 
	private SqlProcess sqlProcess;

	@Autowired
	@Qualifier("defaultLobHandler")
	private LobHandler lobHandler;
	
	public int[] insert(String sqlId, ArrayList<Map<String, Object>> list) {
		String tableName = sqlProcess.getInsertSql(sqlId);
		int[] rst = jdbcDAO.executeBatchInsert(tableName, list);
		return rst;
	}

	public int insert(String sqlId, Map<String, Object> list) {
		String tableName = sqlProcess.getInsertSql(sqlId);
		int rst = jdbcDAO.executeInsert(tableName, list);
		return rst;
	}

	public int insert(String sqlId,List<Object> fields){
		String sql = sqlProcess.getInsertSql(sqlId);
		sql=sqlProcess.convertSql(sql);
		return jdbcDAO.update(sql, fields.toArray());
	}
	
	public int insertForFe(String sqlId,List<Object> fields){
		String sql = sqlProcess.getInsertSql(sqlId);
		sql=sqlProcess.convertSql(sql);
		return jdbcProductionDAO.update(sql, fields.toArray());
	}

	public int insert(String sqlId, Object... args){
		String sql = sqlProcess.getInsertSql(sqlId);
		sql=sqlProcess.convertSql(sql);
		return jdbcDAO.update(sql, args);
	}

	public int insertBlob(String sqlId, final List<Object> params) throws Exception{
		String sql = sqlProcess.getInsertSql(sqlId);
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

/*	public int insertBlob(String sqlId, List<Object> params) throws Exception{
		String sql = sqlProcess.getInsertSql(sqlId);
		Connection conn = null;
		PreparedStatement pstmt = null;
		Blob blob = null;
		BufferedOutputStream bufOut = null;
		try {
			conn = jdbcDAO.getDataSource().getConnection();
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			for (Object obj : params){
				if( obj instanceof byte[] || obj instanceof Byte[] ){
					blob = conn.createBlob();
					bufOut = new BufferedOutputStream( blob.setBinaryStream(1) );
					bufOut.write( (byte[])obj );
					bufOut.close();
					pstmt.setBlob(index, blob);
				} else {
					pstmt.setObject(index, obj);
				}
				index++;
			}
			return pstmt.executeUpdate();
		} catch(Exception ex) {
			log.error("insertBlob插入数据异常：", ex);
		} finally {
			try {
				if( pstmt != null ){
					pstmt.close();
					pstmt = null;
				}
				if( conn != null ){
					conn.close();
					conn = null;
				}
			} catch (Exception e) {
				log.error("insertBlob释放数据库操作对象异常：", e);
			}
		}
		return 0;
	}*/

//	public int insertClob(String sqlId, final List<Object> params) throws Exception{
//		String sql = sqlProcess.getInsertSql(sqlId);
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		Clob clob = null;
//		BufferedOutputStream bufOut = null;
//		try {
//			conn = jdbcDAO.getDataSource().getConnection();
//			pstmt = conn.prepareStatement(sql);
//			int index = 1;
//			for (Object obj : params){
//				if( obj.toString().startsWith("CLOB") ){
//					String clobStr = obj.toString().substring(4);
//					clob = conn.createClob();
//					bufOut = new BufferedOutputStream( clob.setAsciiStream(1) );
//					bufOut.write( clobStr.getBytes( ConfigUtil.get(ConfigUtil.CLOB_CHARSET) ) );
//					bufOut.close();
//					pstmt.setClob(index, clob);
//				} else {
//					pstmt.setObject(index, obj);
//				}
//				index++;
//			}
//			return pstmt.executeUpdate();
//		} catch(Exception ex) {
//			log.error("insertClob插入数据异常：", ex);
//		} finally {
//			try {
//				if( pstmt != null ){
//					pstmt.close();
//					pstmt = null;
//				}
//				if( conn != null ){
//					conn.close();
//					conn = null;
//				}
//			} catch (Exception e) {
//				log.error("insertClob释放数据库操作对象异常：", e);
//			}
//		}
//		return 0;
//	}

	/**
	 * 包含CLOB数据类型数据插入
	 * @param sqlId
	 * @param params  CLOB数据需以  字符串"CLOB"开头   str = "CLOB" + obj
	 * @return
	 * @throws Exception
	 */
	public int insertClob(String sqlId, final List<Object> params) throws Exception{
		String sql = sqlProcess.getInsertSql(sqlId);
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

	public int[] insertBatch(String sqlId,List<Map<String,Object>> pms) throws Exception{
		
		return jdbcDAO.executeBatchInsert(sqlId, pms);
	}
}
