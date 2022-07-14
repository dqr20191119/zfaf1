package com.cesgroup.prison.viewPeople.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.regionDepart.entity.RegionDepart;
import com.cesgroup.prison.viewPeople.entity.ViewPeople;
import com.cesgroup.prison.viewPeople.vo.ViewPeopleVo;

import java.util.List;
import java.util.Map;

public interface ViewPeopleMapper extends BaseDao<ViewPeople, String> {

    public List<ViewPeopleVo> findByCusNumberAndAreaId(String cusNumber, String areaId);

    public int insertByBatch(List<ViewPeople> list);

    public int deleteByCusNumberAndAreaId(String cusNumber, String areaId);

    public int findDepAndAreaRelation(String policeId, String areaId);

    public List<Map<String, Object>> findParenAreaIdAndName(Map<String, Object> paramMap);

    public List<Map<String, Object>> findChildAreaIdAndName(Map<String, Object> paramMap);

    public List<Map<String, Object>> findAreaIdByPoliceID(String cusNumber, String userId);

}
