package com.cesgroup.prison.alarm.emerRecord.service.impl;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.alarm.emerRecord.dao.EmerRecordStepDao;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecordGroup;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecordStep;
import com.cesgroup.prison.alarm.emerRecord.service.EmerRecordGroupService;
import com.cesgroup.prison.alarm.emerRecord.service.EmerRecordStepService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: 01 超图版
 * @description:
 * @author: Mr.li
 * @create: 2019-11-27 10:04
 */
@Service
@Transactional
public class EmerRecordStepServiceImpl extends BaseDaoService<EmerRecordStep, String, EmerRecordStepDao> implements EmerRecordStepService{
    /**
     * 应急处置记录的工作组Service
     */
    @Autowired
    private EmerRecordGroupService emerRecordGroupService;
    @Override
    public List<EmerRecordStep> queryDetailListByRecordId(String recordId) throws ServiceException {
        if(recordId == null || "".equals(recordId)) {
            return null;
        }
        //1 应急处置步骤查询
        List<EmerRecordStep> handleStepList = null;
        try {
            handleStepList = this.getDao().findByRecordIdAndStatus(recordId, "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录ID[" + recordId + "]查询关联的处置步骤发生异常");
        }
        if(handleStepList == null) {
            return null;
        }
        //2 遍历应急处置步骤，查询其关联的工作组信息
        for(EmerRecordStep handleStep : handleStepList) {
            List<EmerRecordGroup> handleGroupList = this.emerRecordGroupService.queryDetailListByRecordIdAndRecordStepId(recordId, handleStep.getId());
            if(handleGroupList != null && handleGroupList.size() > 0) {
                handleStep.setHandleGroupList(handleGroupList);
            }
        }
        return handleStepList;
    }
}