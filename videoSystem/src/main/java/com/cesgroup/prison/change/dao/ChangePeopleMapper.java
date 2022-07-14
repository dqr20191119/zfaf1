package com.cesgroup.prison.change.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.change.entity.ChangePeople;

public interface ChangePeopleMapper extends BaseDao<ChangePeople, String> {

    int updateChangePeople(ChangePeople record);
    
    public List<ChangePeople> searchChangePeople(ChangePeople record);
}