package com.cesgroup.prison.xxhj.zfjbxx.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.zfjbxx.entity.Zfjbxx;

public interface ZfjbxxService extends IBaseCRUDService<Zfjbxx, String> {
		
	public List<Map<String, String>> countPrisoner(String cusNumber);
	
	public List<Map<String, Object>> hospitalCount(String para);
	
	public List<Map<String, Object>> queryPrisonCount(HttpServletRequest request) throws Exception;
	
	public List<Map<String, Object>> queryDprtCount(HttpServletRequest request) throws Exception;
	
	public List<Map<String, Object>> queryInfoPrisonerArchives(HttpServletRequest request) throws Exception;
	
	public Page<Map<String, Object>> listPrisonerFinanical(HttpServletRequest request,Pageable page) throws Exception;
	
	public Page<Map<String, Object>> listPrisonerRewardPunish(HttpServletRequest request,Pageable page) throws Exception;
	
	public Page<Map<String,String>>listPrisonerHealthy(String pheCusNumber,String phePrsnrIdnty,Pageable page);
	
	public Page<Map<String,String>>listPrisonerPhone(String pphCusNumber,String pphPrsnrIdnty,Pageable page);
	
	public Page<Map<String,String>>listPrisonerMeeting(String pmeCusNumber,String pmePrsnrIdnty,Pageable page);
	
	public Page<Map<String, Object>> queryPrisonerBriefInfo(HttpServletRequest request,PageRequest pageRequest) throws Exception;
	
	public List<Map<String, Object>> queryXJPrisonerCount(HttpServletRequest request) throws Exception;
	
	public List<Map<String, Object>> queryXJPrsnrCountPrisonList(HttpServletRequest request) throws Exception;
	
	public List<Map<String, Object>> queryXJPrsnrCountDrptmntList(HttpServletRequest request) throws Exception;
	
	public Page<Map<String, Object>> queryPrisonerInfo(HttpServletRequest request,PageRequest pageRequest) throws Exception;
	
	public Page<Map<String, Object>> queryPrisonerBedInfo(HttpServletRequest request,Pageable page) throws Exception;

	public Page<Map<String, Object>> queryJSPrisonerInfo(HttpServletRequest request,Pageable page) throws Exception;
	
	public Page<Map<String, Object>> findList(Map<String, Object> map, Pageable pageable);
	
	public List<Map<String, Object>> queryCaramer(HttpServletRequest request) throws Exception;
	
	public List<Map<String, Object>> queryRylb(HttpServletRequest request) throws Exception;
	
	public Map<String, String> jrdt(HttpServletRequest request) throws Exception;

}	
