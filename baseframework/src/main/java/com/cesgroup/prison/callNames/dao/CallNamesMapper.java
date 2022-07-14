package com.cesgroup.prison.callNames.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.callNames.entity.CallNamesRecordEntity;

public interface CallNamesMapper extends BaseDao<CallNamesRecordEntity, String> {

	public List<Map<String, Object>> findPrisonerNumForCallNamesByCusNumber(Map<String, Object> map);

	public List<Map<String, Object>> findPrisonerNumForCallNamesByDempt(Map<String, Object> map);

	public List<Map<String, Object>> findPrisonerNumForCallNamesByArea(Map<String, Object> map);

	public List<Map<String, Object>> findAreaDepartment(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> getJSPrisonerInfo(Map<String, Object> paramMap);

	public Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	public void updateInfo(Map<String, Object> map);
}
