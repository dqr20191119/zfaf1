package com.cesgroup.prison.common.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.common.entity.CommonEntity;

public interface CommonService extends IBaseCRUDService<CommonEntity, String> {

	public List<Map<String, Object>> findEvidenceForCombobox(Map<String, Object> paramMap);

	public List<Map<String, Object>> findAreaViewFuncForCombotree(Map<String, Object> paramMap);

	public Map<String, Object> findAreaEqualLevelModel(Map<String, Object> paramMap);

	public List<Map<String, Object>> findMasterPlanForCombobox(Map<String, Object> paramMap) throws Exception;

	public List<Map<String, Object>> findSyncAreaForCombotree(Map<String, Object> paramMap);

	public List<Map<String, Object>> findAllJsxz(Map<String, Object> paramMap);

	public List<Map<String, Object>> findLcjsh(Map<String, Object> paramMap);

	public List<AffixEntity> findZfPicInfo(Map<String, Object> paramMap);

	public List<Map<String, Object>> findShowLabelData(Map<String, Object> paramMap);

	//实时民警分布(根据区域或部门)
	public List<Map<String, Object>> mjfbList(Map<String, Object> paramMap);

	//实时民警数量(根据区域或部门)
	public Map<String, Object> mjfbCount(Map<String, Object> paramMap);

	public List<Map<String, Object>> findDepartmentByAreaId(Map<String, Object> paramMap);
	
	// 统计各区域下民警、罪犯、各设备数量
	public void countAreaInfoHz() throws Exception;

	public Map<String, Object> findAreaInfoHzCount(Map<String, Object> paramMap);

	//统计进入人员及车辆信息
	public Map<String, Object> getPeopleAndCarCount(Map<String, Object> paramMap);

	// 统计监狱信息
	public void countPrisonInfoHz();
	
	/**
	 * 查询在监民警数量
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> getPoliceCountInPrison(Map<String, Object> paramMap);
	
	/**
	 * 查询在监民警数量
	 * @param paramMap
	 * @return
	 */
	public Map<String, Object> getSecurityCheckCount(Map<String, Object> paramMap);


	List<Map<String,Object>> findjqByjy(Map<String, Object> paramMap);
	
	/**
	 * 查询在则民警数量
	 */
	 public Map<String, Object> getPoliceCountZaiCe(Map<String, Object> paramMap); 
		/**
		 * 查询备情民警数量
		 */
		 public Map<String, Object> getPoliceCountBeiQin(Map<String, Object> paramMap);
/**
 * 省局查询在监民警
 * @param paramMap
 * @return
 */
		public Map<String, Object> getPoliceCountInPrisonsj(Map<String, Object> paramMap);

    Map<String, Object> getPoliceCountZaiCeByCusNumberOrDeptCode(Map<String, Object> paramMap);
}
