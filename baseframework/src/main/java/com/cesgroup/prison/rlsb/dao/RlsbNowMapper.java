package com.cesgroup.prison.rlsb.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.rlsb.entity.RlsbEntity;
import com.cesgroup.prison.rlsb.entity.RlsbNowEntity;

/**
 *@ClassName RlsbNowMapper
 *@Description TODO
 *@Author lh
 *@Date 2020/8/12 15:18
 *
 **/
public interface RlsbNowMapper extends BaseDao<RlsbNowEntity, String> {

    void deleteBeforNowTimeOfDay(RlsbNowEntity rlsbNowEntity);
}
