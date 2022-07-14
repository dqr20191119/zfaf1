package com.cesgroup.prison.screen.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.screen.entity.ScreenPlanWindowCameraEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ScreenPlanWindowCameraMapper
 * @Description TODO
 * @Author lh
 * @Date 2020/7/2 10:53
 **/
public interface ScreenPlanWindowCameraMapper extends BaseDao<ScreenPlanWindowCameraEntity, String> {
    List<ScreenPlanWindowCameraEntity> selectWindowByScreenPlanId(@Param("screenPlanId") String screenPlanId,@Param("cusNumber") String cusNumber);

    List<Map<String, Object>> listAllForSxNew(@Param("cusNumber") String cusNumber, @Param("areaId")String areaId,@Param("screenPlanId")String screenPlanId , @Param("windowId")String windowId);


    void deleteByscreenPlanIdAndWindowId(@Param("tvwallId") String tvwallId,@Param("screenPlanId") String screenPlanId, @Param("windowId")String windowId);
}
