package com.cesgroup.prison.users.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.prison.users.dao.UserDeptDao;
import com.cesgroup.prison.users.entity.UserDept;
/**
 * 用户部门Service
 * 
 * @author xiexiaqin
 * @date 2016-09-07
 *
 */
@Service
@Transactional(readOnly = true)
public class UserDeptService {
	
	@Autowired
	private UserDeptDao dao;
	
	/**
	 * 根据用户和部门信息查询
	 */
	public Page<UserDept>findUserDept(String userName, String deptName,Pageable page){
		return dao.findUserDept(userName, deptName,page);
	}
	
}
