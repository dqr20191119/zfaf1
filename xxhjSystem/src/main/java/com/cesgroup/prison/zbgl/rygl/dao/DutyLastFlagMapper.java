package com.cesgroup.prison.zbgl.rygl.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.rygl.entity.DutyLastFlagEntity;

import java.util.List;

/**
 *@ClassName DutyLastFlagMapper
 *@Description TODO
 *@Author lh
 *@Date 2020/11/26 16:32
 *
 **/
public interface DutyLastFlagMapper extends BaseDao<DutyLastFlagEntity, String> {
    List<DutyLastFlagEntity> selectListByOrderByGxDate(DutyLastFlagEntity dutyLastFlagEntity);


}
