package com.cesgroup.prison.alarm.emerRecord.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecordGroup;

import java.util.List;

/**
 * @program: 01 超图版
 * @description:
 * @author: Mr.li
 * @create: 2019-11-27 10:16
 */
public interface EmerRecordGroupService extends IBaseCRUDService<EmerRecordGroup, String> {
    /**
     * 根据应急处置记录的处置步骤ID，查询关联的工作组详情列表
     * @param recordId
     * @param recordStepId
     * @return
     * @throws ServiceException
     */
    public List<EmerRecordGroup> queryDetailListByRecordIdAndRecordStepId(String recordId, String recordStepId) throws ServiceException;

}