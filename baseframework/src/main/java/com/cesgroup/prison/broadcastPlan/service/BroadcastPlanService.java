package com.cesgroup.prison.broadcastPlan.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.broadcastPlan.entity.BroadcastPlan;

public interface BroadcastPlanService extends IBaseCRUDService<BroadcastPlan, String> {
	/**
	 * 分页查询全部的预案 信息曲目信息
	 * 
	 * @param map
	 * @param pageable
	 * @return
	 */
	Page<BroadcastPlan> listAll(BroadcastPlan broadcastPlan, Pageable pageable)throws BusinessLayerException;

	AjaxMessage deleteByIds(List<String> id) throws BusinessLayerException;

	void save(BroadcastPlan entity);

	void updateById(BroadcastPlan enyity);

	List<BroadcastPlan> queryByBfdCusNumber(String cusNumber);

	BroadcastPlan selectById(String id);


}
