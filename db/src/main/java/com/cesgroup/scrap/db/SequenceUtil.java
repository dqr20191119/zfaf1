package com.cesgroup.scrap.db;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

@Repository
public class SequenceUtil {
	
	@Resource
	private JdbcDAO jdbcDAO;
	public String querySequence(String seqName){
		String sql = "SELECT " + seqName + ".NEXTVAL SEQ FROM DUAL";
		List<Map<String, Object>> rtnList = jdbcDAO.query(1, sql);
		if (rtnList != null && rtnList.size() > 0) {
			return rtnList.get(0).get("SEQ").toString();
		}
		return null;
	}

	/**
	 * 根据表名和字段名获取seq
	 * @param tableName
	 * @param colomnName
	 * @return
	 */
	public String getSequence(String tableName,String colomnName){
		BigDecimal seq = null;
		List<String> paraList=new ArrayList<String>();
		paraList.add(tableName);
		paraList.add(colomnName);
		seq = jdbcDAO.executeFunction(BigDecimal.class, "FMS_F_SEQ_GET", paraList.toArray());
		return seq.toString();
	}
}
