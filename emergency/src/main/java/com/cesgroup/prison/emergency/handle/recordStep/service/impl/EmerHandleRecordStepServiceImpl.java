package com.cesgroup.prison.emergency.handle.recordStep.service.impl;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.group.entity.EmerGroup;
import com.cesgroup.prison.emergency.handle.recordGroup.entity.EmerHandleRecordGroup;
import com.cesgroup.prison.emergency.handle.recordGroup.service.EmerHandleRecordGroupService;
import com.cesgroup.prison.emergency.handle.recordStep.dao.EmerHandleRecordStepDao;
import com.cesgroup.prison.emergency.handle.recordStep.entity.EmerHandleRecordStep;
import com.cesgroup.prison.emergency.handle.recordStep.service.EmerHandleRecordStepService;
import com.cesgroup.prison.emergency.step.entity.EmerStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 应急预案业务操作类
 */
@Service
@Transactional
public class EmerHandleRecordStepServiceImpl extends BaseDaoService<EmerHandleRecordStep, String, EmerHandleRecordStepDao> implements EmerHandleRecordStepService {
    /**
     * 应急处置记录的工作组Service
     */
    @Autowired
    private EmerHandleRecordGroupService emerHandleRecordGroupService;

    @Override
    public List<EmerHandleRecordStep> queryByRecordId(String recordId) throws ServiceException {
        try {
            return this.getDao().findByRecordIdAndStatus(recordId, "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录ID、数据状态，查询应急处置记录的处置步骤发生异常");
        }
    }

    @Override
    public void updateHandleContent(EmerHandleRecordStep emerHandleRecordStep) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }
        if(emerHandleRecordStep == null || emerHandleRecordStep.getId() == null || "".equals(emerHandleRecordStep.getId())) {
            throw new ServiceException("应急处置步骤为空");
        }
        //1 查询应急处置记录的处置步骤
        EmerHandleRecordStep emerHandleRecordStepOperate = null;
        try {
            emerHandleRecordStepOperate = this.getDao().findById(emerHandleRecordStep.getId());
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录的处置步骤ID，查询应急处置记录的处置步骤信息发生异常，原因：" + e);
        }
        if(emerHandleRecordStepOperate == null) {
            throw new ServiceException("根据应急处置记录的处置步骤ID，查询到的应急处置记录的处置步骤信息为空");
        }

        //2 更新应急处置记录的处置步骤
        emerHandleRecordStepOperate.setHandleStatus("1");
        emerHandleRecordStepOperate.setHandleContent(emerHandleRecordStep.getHandleContent());
        emerHandleRecordStepOperate.setHandleTime(new Date());
        emerHandleRecordStepOperate.setUpdateUserId(user.getUserId());
        emerHandleRecordStepOperate.setUpdateTime(new Date());

        try {
            this.getDao().update(emerHandleRecordStepOperate);
        } catch (Exception e) {
            throw new ServiceException("更新应急处置记录的处置步骤发生异常");
        }
    }

    @Override
    public EmerHandleRecordStep queryById(String id) throws ServiceException {
        try {
            return this.getDao().findById(id);
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录的处置步骤ID，查询应急处置记录的处置步骤发生异常，原因：" + e);
        }
    }

    @Override
    public List<EmerHandleRecordStep> queryUnhandledPrevStepById(String id) throws ServiceException {
        try {
            return this.getDao().findUnhandledPrevStepById(id);
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录的处置步骤ID，查询当前处置步骤之前的未被处置的处置步骤列表发生异常，原因：" + e);
        }
    }

    @Override
    public List<EmerHandleRecordStep> queryDetailListByRecordId(String recordId) throws ServiceException {
        if(recordId == null || "".equals(recordId)) {
            return null;
        }
        //1 应急处置步骤查询
        List<EmerHandleRecordStep> handleStepList = null;
        try {
            handleStepList = this.getDao().findByRecordIdAndStatus(recordId, "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录ID[" + recordId + "]查询关联的处置步骤发生异常");
        }
        if(handleStepList == null) {
            return null;
        }
        //2 遍历应急处置步骤，查询其关联的工作组信息
        for(EmerHandleRecordStep handleStep : handleStepList) {
            List<EmerHandleRecordGroup> handleGroupList = this.emerHandleRecordGroupService.queryDetailListByRecordIdAndRecordStepId(recordId, handleStep.getId());
            if(handleGroupList != null && handleGroupList.size() > 0) {
                handleStep.setHandleGroupList(handleGroupList);
            }
        }
        return handleStepList;
    }
}
