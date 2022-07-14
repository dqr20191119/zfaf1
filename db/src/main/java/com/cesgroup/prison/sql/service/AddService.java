package com.cesgroup.prison.sql.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.scrap.db.InsertProcess;
import com.cesgroup.scrap.db.JdbcDAO;
import com.cesgroup.scrap.db.SqlProcess;



@Service
@Transactional
public class AddService implements IAddService {
	
	private static Logger log = LoggerFactory.getLogger(AddService.class);
	
	@Resource
	private JdbcDAO jdbcDAO;
	
	@Resource
	private InsertProcess insertProcess;
	
	@Resource 
	private SqlProcess sqlProcess;
	public int insert(String sqlid,List<Object> parasList) {
		int rtn=0;
		String sql = sqlProcess.getInsertSql(sqlid);
		sql=sqlProcess.convertSql(sql);
		if(!sql.isEmpty()){
			rtn=jdbcDAO.update(sql,parasList.toArray());
		}else{
			new Exception("Not Found sql !");
		}
		return rtn;
	}
	
	@Override
	public int insertBlob(String sqlId, List<Object> params) {
		try {
			return insertProcess.insertBlob(sqlId, params);
		} catch (Exception e) {
			log.error("insertBlob.插入数据失败：", e);
			return 0;
		}
	}

	@Override
	public int insertClob(String sqlId, List<Object> params) {
		try {
			return insertProcess.insertClob(sqlId, params);
		} catch (Exception e) {
			log.error("insertClob.插入数据失败：", e);
			return 0;
		}
	}
}
