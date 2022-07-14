package com.cesgroup.prison.users.dao;

import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.dao.IBaseDao;
import com.cesgroup.prison.users.entity.UserDept;
/**
 * 用户部门关联Dao
 * 
 * @author xiexiaqin
 * @date 2016-09-07
 *
 */
public interface UserDeptDao extends IBaseDao<UserDept>{
	/**
	 * 根据用户和部门信息查询
	 * 基于xml
	 */
	Page<UserDept> findUserDept(String userName, String deptName,Pageable page);
	
	

	/**
	 * 根据用户和部门信息查询
	 * 基于provider
	 */
	@SelectProvider(type = UserDeptProvider.class, method = "findUserDeptByProvider")
	Page<UserDept> findUserDeptByProvider(String userName, String deptName,Pageable page);
}
