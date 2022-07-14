package com.cesgroup.prison.screen.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.screen.entity.ScreenPlanWindowNewEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName ScreenPlanWindowNewMapper
 * @Description TODO
 * @Author lh
 * @Date 2020/7/3 16:43
 **/
public interface ScreenPlanWindowNewMapper extends BaseDao<ScreenPlanWindowNewEntity, String> {
    void deleteByCusNumberAndScreenPlan(@Param("cusNumber") String cusNumber, @Param("screenPlanId")String screenPlanId);

    List<ScreenPlanWindowNewEntity> selectByScreenPlanWindowNewEntity(ScreenPlanWindowNewEntity screenPlanWindowNewEntity);
}
