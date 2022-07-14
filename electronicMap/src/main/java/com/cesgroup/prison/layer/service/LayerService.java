package com.cesgroup.prison.layer.service;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.layer.entity.Layer;

/**
 * @author Jack
 *
 */
@Service
public interface LayerService extends IBaseCRUDService<Layer,String>{

	/**
	 * 保存图层
	 * @param layer
	 * @return
	 * @throws Exception 
	 */
	Layer saveLayer (Layer layer) throws Exception;

	/**
	 * 根据linDeviceType,linDeviceId,linCusNumber查找Layer
	 * @param layer
	 * @return
	 */
	Layer findByLayer(Layer layer);

}
