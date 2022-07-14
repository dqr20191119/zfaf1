package com.cesgroup.prison.emergency.handle.recordGroup.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.group.entity.EmerGroup;
import com.cesgroup.prison.emergency.handle.recordGroup.entity.EmerHandleRecordGroup;
import com.cesgroup.prison.emergency.handle.recordStep.entity.EmerHandleRecordStep;

import java.util.List;

/**
 * 应急处置记录业务访问接口
 */
public interface EmerHandleRecordGroupService extends IBaseCRUDService<EmerHandleRecordGroup, String> {
    /**
     * 根据应急处置记录的处置步骤ID，查询应急处置记录的工作组列表
     * @param recordStepId
     * @return
     * @throws ServiceException
     */
    public List<EmerHandleRecordGroup> queryByRecordStepId(String recordStepId) throws ServiceException;

    /**
     * 根据主键ID，查询应急处置记录的工作组
     * @param id
     * @return
     * @throws ServiceException
     */
    public EmerHandleRecordGroup queryById(String id) throws ServiceException;

    /**
     * 根据应急处置记录的处置步骤ID，查询关联的工作组详情列表
     * @param recordId
     * @param recordStepId
     * @return
     * @throws ServiceException
     */
    public List<EmerHandleRecordGroup> queryDetailListByRecordIdAndRecordStepId(String recordId, String recordStepId) throws ServiceException;
}
