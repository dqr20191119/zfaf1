package com.cesgroup.prison.ysjg.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.ysjg.entity.YsjgEntity;

public interface YsjgService extends IBaseCRUDService<YsjgEntity,String>{

	public void getYsjg(String type,String secCode,String period,String jyId);
}
