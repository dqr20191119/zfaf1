package com.cesgroup.prison.alarm.flow.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.alarm.flow.entity.FlowDtlsAlarmRecordEntity;

/**
 * @ClassName FlowDtlsAlarmRecordService
 * @Description TODO
 * @Author lh
 * @Date 2020/6/17 17:00
 **/
public interface FlowDtlsAlarmRecordService extends IBaseCRUDService<FlowDtlsAlarmRecordEntity, String> {
    AjaxResult saveOrUpdate(FlowDtlsAlarmRecordEntity flowDtlsAlarmRecordEntity);

    FlowDtlsAlarmRecordEntity ListBylowDtlsAlarmRecord(FlowDtlsAlarmRecordEntity flowDtlsAlarmRecordEntity);

    AjaxResult ListByAlarmRecordId(String recordId);
}
