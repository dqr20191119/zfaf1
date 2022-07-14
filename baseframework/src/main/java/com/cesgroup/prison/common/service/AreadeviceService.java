package com.cesgroup.prison.common.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.common.entity.AreadeviceEntity;

public interface AreadeviceService extends IBaseCRUDService<AreadeviceEntity, String> {

	public List<Map<String, Object>> findForCombotree(Map<String, Object> paramMap);

	public List<Map<String, Object>> findDeviceList(Map<String, Object> paramMap);

	public List<Map<String, Object>> findForDepCombotree(Map<String, Object> paramMap);

	/**
	 * 根据条件，查询区域广播树
	 * @param paramMap
	 * @return
	 * @throws BusinessLayerException
	 */
	public List<Map<String, Object>> queryAreaBoradcastTree(Map<String, Object> paramMap) throws BusinessLayerException;
}
