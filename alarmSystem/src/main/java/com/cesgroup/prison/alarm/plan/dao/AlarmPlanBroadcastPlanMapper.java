package com.cesgroup.prison.alarm.plan.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanBroadcastPlan;

public interface AlarmPlanBroadcastPlanMapper extends BaseDao<AlarmPlanBroadcastPlan, String> {

	void updateByPlanId(AlarmPlanBroadcastPlan entity);
}
