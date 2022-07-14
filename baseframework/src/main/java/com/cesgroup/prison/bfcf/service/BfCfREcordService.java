package com.cesgroup.prison.bfcf.service;

import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.bfcf.entity.BfCfREcordEntity;
import com.cesgroup.prison.xtgl.entity.PlaneLayerPoint;

public interface BfCfREcordService extends IBaseCRUDService<BfCfREcordEntity, String> {

	void startBfCf(Map<String,Object> map) throws BusinessLayerException;


}
