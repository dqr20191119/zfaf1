package com.cesgroup.prison.common.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.common.entity.CommonEntity;

public interface CommonMapper extends BaseDao<CommonEntity, String> {

	public List<Map<String, Object>> findEvidenceForCombobox(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> findAreaViewFuncForCombotree(Map<String, Object> paramMap);

	public List<Map<String, Object>> findAreaEqualLevelModel(Map<String, Object> paramMap);

	public List<Map<String, Object>> findArea(Map<String, Object> paramMap);

	public List<Map<String, Object>> findMasterPlanForCombobox(Map<String, Object> paramMap);

	public List<Map<String, Object>> findSyncAreaForCombotree(Map<String, Object> paramMap);

	public List<Map<String, Object>> findAllJsxz(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> findLcjsh(Map<String, Object> paramMap);
	
	public List<AffixEntity> findZfPicInfo(Map<String, Object> paramMap);

	public List<Map<String, Object>> findAreaOfJs(Map<String, Object> paramMap);

	public List<Map<String, Object>> countZfsl(Map<String, Object> paramMap);

	public Map<String, Object> countMjslByAreaId(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> countMjsl(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> countCamera(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> countAlertor(Map<String, Object> paramMap);

	public List<Map<String, Object>> countTalk(Map<String, Object> paramMap);

	public List<Map<String, Object>> countTalkServer(Map<String, Object> paramMap);

	public List<Map<String, Object>> countDoor(Map<String, Object> paramMap);

	public List<Map<String, Object>> countBroadcast(Map<String, Object> paramMap);

	public List<Map<String, Object>> findAreaInfoHzCount(Map<String, Object> paramMap);
	
	//实时民警分布(根据区域或部门)
	public List<Map<String, Object>> mjfbList(Map<String, Object> paramMap);
	//实时民警数量(根据区域或部门)
	public Map<String, Object> mjfbCount(Map<String, Object> paramMap);

	public List<Map<String, Object>> findDepartmentByAreaId(Map<String, Object> paramMap);
	/**
	 * 查询外来车辆数量
	 * @param paramMap
	 * @return
	 */
	public Integer getCarCount(Map<String, Object> paramMap);
	/**
	 * 查询外来人员数量
	 * @param paramMap
	 * @return
	 */
	public Integer getPeopleCount(Map<String, Object> paramMap);

	public List<Map<String, Object>> countPrisonInfoHz();
	
	/**
	 * 查询在监民警数量
	 * @param paramMap
	 * @return
	 */
	public Integer getPoliceCountInPrison(Map<String, Object> paramMap);
	
	/**
	 * 查询在监民警数量
	 * @param paramMap
	 * @return
	 */
	public Integer getSecurityCheckCount(Map<String, Object> paramMap);

    List<Map<String,Object>> findjqByjy(Map<String, Object> paramMap);
    
    List<Map<String,Object>> getPoliceNo(Map<String, Object> paramMap);
    
    /**
     * 查询在册民警
     * 
     */
    public Integer getPoliceCountZaiCePrison(Map<String, Object> paramMap); 
    public Integer getPoliceCountBeiQinPrison(Map<String, Object> paramMap);
/**
 * 省局查询在在册民警
 * @param paramMap
 */
	public Integer getPoliceCountInPrisonsj(Map<String, Object> paramMap);

    Integer getPoliceCountZaiCeByCusNumberOrDeptCode(Map<String, Object> paramMap);
}
