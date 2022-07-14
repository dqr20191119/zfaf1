package com.cesgroup.prison.route.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.route.entity.CdsRoamPointRltn;

public interface CdsRoamPointRltnMapper extends BaseDao<CdsRoamPointRltn,String>{
    int deleteByPrimaryKey(String id);

    int insertPoint(CdsRoamPointRltn record);

    int insertSelectivePoint(CdsRoamPointRltn record);

    CdsRoamPointRltn selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CdsRoamPointRltn record);

    int updateByPrimaryKey(CdsRoamPointRltn record);
    
    Page<CdsRoamPointRltn> findByPage(Map<String, String> map,Pageable page);
    
     List<CdsRoamPointRltn>  selectByRouteId(CdsRoamPointRltn record);
}