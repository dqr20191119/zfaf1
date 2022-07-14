package com.cesgroup.prison.change.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.prison.change.entity.ChangeApproval;
import com.cesgroup.prison.change.entity.ChangeDepartment;
import com.cesgroup.prison.change.entity.ChangePeople;

public interface ChangeService {

	Page<Map<String,String>> launchListPage(Map<String, Object> paramMap, PageRequest pageRequest);
	
	Page<Map<String,String>> changeListPage(Map<String, Object> paramMap, PageRequest pageRequest);
	
	Page<Map<String,String>> checkListPage(Map<String, Object> paramMap, PageRequest pageRequest);
	
	Page<Map<String,String>> recordListPage(Map<String, Object> paramMap, PageRequest pageRequest);
	
	//批量新增整改部门信息
	public void batchInsertChaDep(List<Map<String,Object>> list) throws Exception;
	//局部修改整改部门信息
	public void updateChangeDepartment(ChangeDepartment record) throws Exception;
	//添加整改人信息
	public void addChangePeople(ChangePeople model) throws Exception;
	//整改页面的提交业务
	public void changeSubmit(HttpServletRequest request) throws Exception;
	//审批页面的提交业务
	public void checkSubmit(HttpServletRequest request) throws Exception;
	
	public List<ChangeApproval> searchChangeApproval(ChangeApproval record);
	public List<ChangeDepartment> searchChangeDepartment(ChangeDepartment record);
	public List<ChangePeople> searchChangePeople(ChangePeople record);
}
