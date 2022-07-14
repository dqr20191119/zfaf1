package com.cesgroup.prison.jctj.service;

import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.jctj.entity.JcjlEntity;
import com.cesgroup.prison.utils.PagerModel;

public interface JcjlService extends IBaseCRUDService<JcjlEntity, String> {
 
	/**
	 * 进出统计
	 * @return
	 */
	public PagerModel getJctj(Map<String, Object> param, int page, int rows);
	
	/**
	 * 罪犯所在区域统计
	 * @return
	 */
	public Map<String, Object> getZfszqytj();
	
}
