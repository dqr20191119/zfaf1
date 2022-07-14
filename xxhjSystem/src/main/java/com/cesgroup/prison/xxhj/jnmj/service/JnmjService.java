package com.cesgroup.prison.xxhj.jnmj.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.jnmj.entity.Jnmj;

public interface JnmjService extends IBaseCRUDService<Jnmj, String> {

	public Page<Map<String, Object>> listPoliceInoutRecordInfo(HttpServletRequest request, PageRequest pageRequest);

	/*public List<Map<String, String>> queryInsidePoliceCount(String obdOrgaIdnty, String drpmntId);*/

	public List<Map<String, String>> queryInsidePoliceCountByPrison() throws Exception;

	public List<OrgEntity> queryPrisonDepartment(String dbdCusNumber) throws Exception;

	public List<OrgEntity> queryPrison() throws Exception;

	public List<Map<String, String>> queryPrisonDrptmntPoliceCount(String cusNumber, String drpmntId, String config);

	public List<Map<String, Object>> queryPrisonDrptmntPoliceInfo(HttpServletRequest request) throws Exception;

	public Page<Map<String, Object>> queryPoliceByDid(HttpServletRequest request, PageRequest pageRequest);
	
	public Map<String, Object> findPoliceByUserId(HttpServletRequest request);

	public Map<String, Object> queryDutyConfig(String cusNumber);

	public Map<String, Object> querySYInsidePoliceCount(Map<String, Object> map);
	
	public List<Map<String, Object>> queryRylb(HttpServletRequest request) throws Exception;
	
	public List<Map<String, Object>> queryCaramer(HttpServletRequest request) throws Exception;
}
