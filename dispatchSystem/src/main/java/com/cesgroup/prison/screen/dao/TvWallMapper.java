package com.cesgroup.prison.screen.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.screen.bean.TvWallEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by DELL on 2019/5/22.
 */
public interface TvWallMapper extends BaseDao<TvWallEntity,String> {

    List<TvWallEntity> findByCusNumberAndCode(@Param("cusNumber") String cusNumber, @Param("code") String code);
}
