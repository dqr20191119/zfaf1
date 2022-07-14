package com.cesgroup.prison.users.dao;

import java.util.Map;
/**
 * 用户部门Proverder
 * 
 * @author xiexiaqin
 * @date 2016-09-07
 *
 */
public class UserDeptProvider {
	
	
	public String findUserDeptByProvider(Map<String, Object> params){
		String sql="select u.*,d.department_name dept_name from t_fw_user u,t_fw_department d"
				+ " where u.department_Id=d.id";
		if(params.get("0")!=null){
			sql+=" and u.user_name like #{0, typeHandler=com.cesgroup.framework.mybatis.mapper.handler.LikeTypeHandler}";
		}
		if(params.get("1")!=null){
			sql+= " and d.department_name like #{1, typeHandler=com.cesgroup.framework.mybatis.mapper.handler.LikeTypeHandler}";
		}
		return sql;
	}
}
