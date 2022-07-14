package com.cesgroup.prison.screen.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.screen.entity.ScreenPlanNewEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @ClassName ScreenPlanNewMapper
 * @Description TODO
 * @Author lh
 * @Date 2020/7/2 10:53
 **/
public interface ScreenPlanNewMapper extends BaseDao<ScreenPlanNewEntity, String> {
    Page<ScreenPlanNewEntity> pageSelectAll(ScreenPlanNewEntity screenPlanNewEntity, Pageable pageable);

    void deleteAll(@Param("cusNumber") String cusNumber);

    /**
     * 更新本监狱不为id 的状态
     * @param screenPlanNewEntity
     */
    void updateInfo(ScreenPlanNewEntity screenPlanNewEntity);
    void updateById(ScreenPlanNewEntity screenPlanNewEntity);
}
