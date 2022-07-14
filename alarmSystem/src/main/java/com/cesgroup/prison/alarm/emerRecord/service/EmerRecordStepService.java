package com.cesgroup.prison.alarm.emerRecord.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecordStep;

import java.util.List;

/**
 * @program: 01 超图版
 * @description:
 * @author: Mr.li
 * @create: 2019-11-27 09:56
 */
public interface EmerRecordStepService extends IBaseCRUDService<EmerRecordStep, String> {


    /**
     * 根据应急处置记录ID，查询处置记录关联的处置步骤详情列表(包括工作组、工作组成员)
     * @param recordId
     * @return
     * @throws ServiceException
     */
    public List<EmerRecordStep> queryDetailListByRecordId(String recordId) throws ServiceException;

}