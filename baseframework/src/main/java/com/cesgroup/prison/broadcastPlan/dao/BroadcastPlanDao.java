package com.cesgroup.prison.broadcastPlan.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.broadcastPlan.entity.BroadcastPlan;

/**
 * @author lincoln.cheng
 *
 */
public interface BroadcastPlanDao extends BaseDao<BroadcastPlan, String> {
	/**
	 * 分页查询全部的预案信息
	 * 
	 * @param map
	 * @param pageable
	 * @return
	 */
	Page<BroadcastPlan> listAll(BroadcastPlan broadcastPlan, Pageable pageable);

	void deleteByIds(List<String> id);

	void deletByBroadcastPlanId(List<String> id);

	
}
