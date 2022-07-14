package com.cesgroup.prison.emergency.handle.recordStep.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.handle.recordStep.entity.EmerHandleRecordStep;
import com.cesgroup.prison.emergency.step.entity.EmerStep;

import java.util.List;

/**
 * 应急处置记录业务访问接口
 */
public interface EmerHandleRecordStepService extends IBaseCRUDService<EmerHandleRecordStep, String> {
    /**
     * 根据应急处置记录ID，查询应急处置记录的处置步骤
     * @param recordId
     * @return
     * @throws ServiceException
     */
    public List<EmerHandleRecordStep> queryByRecordId(String recordId) throws ServiceException;

    /**
     * 更新处置内容
     * @param emerHandleRecordStep
     * @throws ServiceException
     */
    public void updateHandleContent(EmerHandleRecordStep emerHandleRecordStep) throws ServiceException;

    /**
     * 根据应急处置记录的处置步骤ID，查询应急处置记录的处置步骤信息
     * @param id
     * @return
     * @throws ServiceException
     */
    public EmerHandleRecordStep queryById(String id) throws ServiceException;

    /**
     * 根据应急处置记录的处置步骤ID，查询当前处置步骤之前的未被处置的处置步骤列表
     * @param id
     * @return
     * @throws ServiceException
     */
    public List<EmerHandleRecordStep> queryUnhandledPrevStepById(String id) throws ServiceException;

    /**
     * 根据应急处置记录ID，查询处置记录关联的处置步骤详情列表(包括工作组、工作组成员)
     * @param recordId
     * @return
     * @throws ServiceException
     */
    public List<EmerHandleRecordStep> queryDetailListByRecordId(String recordId) throws ServiceException;
}
