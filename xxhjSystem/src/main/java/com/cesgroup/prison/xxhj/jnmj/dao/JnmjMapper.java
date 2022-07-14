package com.cesgroup.prison.xxhj.jnmj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.jnmj.entity.Jnmj;

public interface JnmjMapper extends BaseDao<Jnmj, String> {

	public List<Map<String, String>> queryInsidePoliceCount(Map<String, Object> map);

	public List<Map<String, String>> queryInsidePoliceCountByPrison();

	public Page<Map<String, String>> list(Map<String, Object> map, Pageable pageable);

	public Page<Map<String, String>> listPoliceInfo(Map<String, Object> map, Pageable pageable);

	public Page<Map<String, Object>> listPoliceInoutRecordInfo(Map<String, Object> map, Pageable pageable);

	public List<Map<String, String>> queryPrisonDepartment(Map<String, Object> map);

	public List<Map<String, String>> queryPrisonDrptmntPoliceCount(Map<String, Object> map);

	public List<Map<String, Object>> queryPrisonDrptmntPoliceInfo(@Param("para") String para,
			@Param("obdOrgaIdnty") String obdOrgaIdnty, @Param("pbdDrptmntId") String pbdDrptmntId,
			@Param("policeIdntyOrName") String policeIdntyOrName,
			@Param("dprmtList") List<String> dprmtList,
			@Param("policeNoListss") List<String> policeNoListss,
			@Param("pliceLi") String pliceLi);
	
	/**
	* @methodName: queryPoliceByDid
	* @Description: 根据部门查询警察信息列表 update by tao
	* @param map
	* @param pageable
	* @return Page<Map<String,Object>>
	* @throws  
	*/
	public Page<Map<String, Object>> queryPoliceByDid(Map<String, Object> map, Pageable pageable);

	/**
	* @methodName: findPoliceByUserId
	* @Description: 根据 userId 查询警察信息 add by tao
	* @param map
	* @return Map<String,Object>
	* @throws  
	*/
	public Map<String, Object> findPoliceByUserId(Map<String, Object> map);

	public List<Map<String, String>> queryInsidePoliceCountByOrg(Map<String, Object> map);

	public List<Map<String, String>> queryPrisonDrptmntPoliceCountByDuty(Map<String, Object> map);

	public List<Map<String, Object>> queryPrisonDrptmntPoliceInfoByOrg(@Param("para") String para,
			@Param("obdOrgaIdnty") String obdOrgaIdnty, @Param("pbdDrptmntId") String pbdDrptmntId,
			@Param("policeIdntyOrName") String policeIdntyOrName,
			@Param("dprmtList") List<String> dprmtList,
			@Param("policeNoListss") List<String> policeNoListss,
			@Param("pliceLi") String pliceLi);
	
	public List<Map<String, Object>> queryPrisonDrptmntPoliceInfoByDuty(@Param("para") String para,
			@Param("obdOrgaIdnty") String obdOrgaIdnty, @Param("pbdDrptmntId") String pbdDrptmntId,
			@Param("policeIdntyOrName") String policeIdntyOrName,
			@Param("dprmtList") List<String> dprmtList,
			@Param("policeNoListss") List<String> policeNoListss,
			@Param("pliceLi") String pliceLi);

	public Map<String, Object> queryDutyConfig(@Param("cusNumber")String cusNumber);

	public Map<String, Object> querySYInsidePoliceCountByOrg(Map<String, Object> map);

	public Map<String, Object> querySYInsidePoliceCountByRecord(Map<String, Object> map);

	public Map<String, Object> querySYInsidePoliceCountByDuty(Map<String, Object> map);
	
	public List<Map<String, Object>> querySzqy(Map<String,Object> map );
	
	public List<Map<String, Object>> queryRygl(Map<String,Object> map );
	
	public List<Map<String, Object>> querySzjq(Map<String,Object> map );
	
	public List<Map<String, Object>> openCamare(Map<String,Object> map );
	
	public List<Map<String, Object>> queryXl(Map<String,Object> map );
}
