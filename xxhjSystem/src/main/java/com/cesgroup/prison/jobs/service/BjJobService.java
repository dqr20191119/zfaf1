package com.cesgroup.prison.jobs.service;

import java.util.List;
import java.util.Map;
import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.framework.base.service.IBaseCRUDService;

public interface BjJobService extends IBaseCRUDService<StringIDEntity, String> {
	
	public void findById();
	
	public void getHY();
	
	/**
	 * 获取罪犯业务数据
	 */
	public void getHyZfyw();
	
	/**
	 * 获取计分考核数据
	 */
	public void getHyJfkh();
	
	/**
	 * 全量同步罪犯照片
	 */
	public void getHyZfzp();
	
	public void getKH();
	 
	public void tsSzbb();
	 
	List<Map<String, Object>> getOrgList(boolean isJq);
}
