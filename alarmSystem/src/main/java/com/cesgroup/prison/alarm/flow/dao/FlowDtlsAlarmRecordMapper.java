package com.cesgroup.prison.alarm.flow.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.flow.entity.FlowDtlsAlarmRecordEntity;

/**
 * @ClassName FlowDtlsAlarmRecordMapper
 * @Description TODO
 * @Author lh
 * @Date 2020/6/17 16:59
 **/
public interface FlowDtlsAlarmRecordMapper extends BaseDao<FlowDtlsAlarmRecordEntity, String> {
    void updateById(FlowDtlsAlarmRecordEntity flowDtlsAlarmRecordEntity);
}
