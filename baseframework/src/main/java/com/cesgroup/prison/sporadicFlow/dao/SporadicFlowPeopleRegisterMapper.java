package com.cesgroup.prison.sporadicFlow.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.sporadicFlow.entity.SporadicFlowPeopleRegisterEntity;

import java.util.List;
import java.util.Map;

public interface SporadicFlowPeopleRegisterMapper extends BaseDao<SporadicFlowPeopleRegisterEntity,String> {


    void deleteByIds(List<String> ids);

    void insertPeople(SporadicFlowPeopleRegisterEntity record);

    List<Map<String, Object>> listOffender(Map<String, Object> map);

    List<Map<String, Object>> listPolice(Map<String, Object> map);

}