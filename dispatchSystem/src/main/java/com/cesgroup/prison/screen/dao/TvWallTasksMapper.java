package com.cesgroup.prison.screen.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.screen.bean.TvWallTasksEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by DELL on 2019/5/22.
 */
public interface TvWallTasksMapper extends BaseDao<TvWallTasksEntity, String> {

    List<TvWallTasksEntity> findByCusNumberAndCodeAndTvWallId(@Param("cusNumber")String cusNumber, @Param("code")String code, @Param("tvWallId")String tvWallId);
}
