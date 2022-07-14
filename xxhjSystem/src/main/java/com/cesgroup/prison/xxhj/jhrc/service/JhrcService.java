package com.cesgroup.prison.xxhj.jhrc.service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.jhrc.entity.JhrcEntity;


public interface JhrcService extends IBaseCRUDService<JhrcEntity, String> {
	
	public JhrcEntity getById(String id);

	public Page<JhrcEntity> findList(JhrcEntity jhrcEntity, PageRequest pageRequest);

	public void saveOrUpdate(JhrcEntity jhrcEntity) throws Exception;

	public void deleteByIds(String ids);
	
	//查询某一天的日程
	public List<Map<String, Object>> searchRcByDay(String cpsCusNumber,String cpsDrpmntId,String day);
	
	public void inserYrzq(String zbrVal, String zbrText,String sxsj,String departId);
	
	public Map<String, Object> getZbr(String cpsCusNumber, String cpsDrpmntId);
	
	public void insertDsq(String date) throws ParseException;
	
	public void insertDsqGb(String date) throws ParseException;
	
}