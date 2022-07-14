package com.cesgroup.prison.xxhj.jryf.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.jryf.entity.Jryf;

public interface JryfMapper extends BaseDao<Jryf, String> {

	public List<Map<String, Object>> getAllJy();
	
	public List<Map<String, Object>> queryInfoPrisonerarchives(Map<String,Object> map);
	
	public Page<Map<String, Object>> listPoliticsReward(Map<String,Object> map, Pageable page);

	public Page<Map<String, Object>> listPoliticsPunish(Map<String,Object> map, Pageable page);
	
	public Page<Map<String, Object>> listJudicialReward(Map<String,Object> map, Pageable page);
	
	public Page<Map<String, Object>> listPrisonerAccount(Map<String,Object> map, Pageable page);
	
	public Page<Map<String, Object>> listPrisonerIncome(Map<String,Object> map, Pageable page);
	
	public Page<Map<String, Object>> listPrisonerConsume(Map<String,Object> map, Pageable page);
	
	public Page<Map<String, Object>> listPrisonerPay(Map<String,Object> map, Pageable page);
	
	public Page<Map<String, String>> listPrisonerHealthy(Map<String,Object> map, Pageable page);
	
	public Page<Map<String, String>> listPrisonerPhone(Map<String,Object> map, Pageable page);
	
	public Page<Map<String, String>> listPrisonerMeeting(Map<String,Object> map, Pageable page);
	
	public List<Map<String,String>> countPrisoner(Map<String,Object> map);
	
	public List<Map<String, Object>> hospitalCount(Map<String,Object> map);
	
	public List<Map<String, Object>> insideByPrisonCount(Map<String,Object> map);
	
   	public List<Map<String, Object>> freePrisonerCount(Map<String,Object> map);
   	
   	public List<Map<String, Object>> todayinPrisonerCount(Map<String,Object> map);
   	
   	public List<Map<String, Object>> repeatCheckPrisonerCount(Map<String,Object> map);
   	
   	public List<Map<String, Object>> leavePrisonerCount(Map<String,Object> map);
   	
   	public List<Map<String, Object>> outHospitalPrisonerCount(Map<String,Object> map);
   	
   	public List<Map<String, Object>> insidePrisonerDprtCount(Map<String,Object> map);
   	
   	public List<Map<String, Object>> freePrisonerDprtCount(Map<String,Object> map);
   	
   	public List<Map<String, Object>> todayInPrisonerDprtCount(Map<String,Object> map);
   	
   	public List<Map<String, Object>> repeatcheckInPrisonerDprtCount(Map<String,Object> map);
   	
   	public List<Map<String, Object>> leavePrisonerDprtCount(Map<String,Object> map);
   	
   	public List<Map<String, Object>> outHospitalPrisonerDprtCount(Map<String,Object> map);
   	
   	public Page<Map<String, Object>> queryPrisonerBriefInfo (@Param("para") String para,@Param("type") String type,
			@Param("cusNumber") String cusNumber, @Param("prsnrIdnty") String prsnrIdnty,
			@Param("prsnrName") String prsnrName, @Param("prsnAreaIdnty") String prsnAreaIdnty,
			@Param("prsnrSttsIndc") String prsnrSttsIndc,
			@Param("dprmtList") List<String> dprmtList,Pageable pageRequest);
   	
	public Page<Map<String, Object>> queryHospitalPrisonerInfo(Map<String,Object> map,Pageable pageRequest);
	
	public Page<Map<String, Object>> queryPrisonerXJF(Map<String,Object> map,Pageable page);
	
	public Page<Map<String, Object>> queryPrisonerWZF(Map<String,Object> map,Pageable page);
	
	public List<Map<String, Object>> queryXJPrisonerCount(Map<String,Object> map );
	
	public List<Map<String, Object>> queryXJPrsnrCountPrisonList(Map<String,Object> map);
	
	public List<Map<String, Object>> queryXJPrsnrCountDrptmntList(Map<String,Object> map);
	
	public Page<Map<String, Object>> queryPrisonerInfo(Map<String,Object> map,Pageable page);
}