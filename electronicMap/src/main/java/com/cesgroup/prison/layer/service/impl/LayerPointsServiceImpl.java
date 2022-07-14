package com.cesgroup.prison.layer.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.layer.dao.LayerPointsMapper;
import com.cesgroup.prison.layer.entity.LayerPoints;
import com.cesgroup.prison.layer.service.LayerPointsService;

@Service
public class LayerPointsServiceImpl extends BaseService<LayerPoints, String> implements LayerPointsService{

    @Autowired
    LayerPointsMapper layerPointsMapper;
    
    @Override
    public LayerPointsMapper getDao(){
        return layerPointsMapper;
    }
}
