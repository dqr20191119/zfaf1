package com.cesgroup.prison.zbgl.rygl.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.rygl.entity.RyztHistoryEntity;

import java.util.List;

/**
 *@ClassName RyztHistoryMapper
 *@Description TODO
 *@Author lh
 *@Date 2020/9/21 14:16
 *
 **/
public interface RyztHistoryMapper extends BaseDao<RyztHistoryEntity, String> {

   List<RyztHistoryEntity> selectByZbryId(RyztHistoryEntity ryztHistoryEntity);


}
