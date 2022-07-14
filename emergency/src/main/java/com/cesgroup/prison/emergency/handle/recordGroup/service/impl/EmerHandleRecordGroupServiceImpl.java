package com.cesgroup.prison.emergency.handle.recordGroup.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.handle.recordGroup.dao.EmerHandleRecordGroupDao;
import com.cesgroup.prison.emergency.handle.recordGroup.entity.EmerHandleRecordGroup;
import com.cesgroup.prison.emergency.handle.recordGroup.service.EmerHandleRecordGroupService;
import com.cesgroup.prison.emergency.handle.recordMember.entity.EmerHandleRecordMember;
import com.cesgroup.prison.emergency.handle.recordMember.service.EmerHandleRecordMemberService;

/**
 * 应急预案业务操作类
 */
@Service
@Transactional
public class EmerHandleRecordGroupServiceImpl extends BaseDaoService<EmerHandleRecordGroup, String, EmerHandleRecordGroupDao> implements EmerHandleRecordGroupService {
    /**
     * 应急处置记录的工作组成员Service
     */
    @Autowired
    private EmerHandleRecordMemberService emerHandleRecordMemberService;

    @Override
    public List<EmerHandleRecordGroup> queryByRecordStepId(String recordStepId) throws ServiceException {
        try {
            return this.getDao().findByRecordStepIdAndStatus(recordStepId, "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录的处置步骤ID、数据状态，查询应急处置记录的工作组列表发生异常");
        }
    }

    @Override
    public EmerHandleRecordGroup queryById(String id) throws ServiceException {
        try {
            return this.getDao().findById(id);
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录的工作组ID，查询应急处置记录的工作组发生异常");
        }
    }

    @Override
    public List<EmerHandleRecordGroup> queryDetailListByRecordIdAndRecordStepId(String recordId, String recordStepId) throws ServiceException {
        if(recordId == null || "".equals(recordId) || recordStepId == null || "".equals(recordStepId)) {
            return null;
        }
        //1 应急工作组查询
        List<EmerHandleRecordGroup> handleGroupList = null;
        try {
            handleGroupList = this.getDao().findByRecordIdAndRecordStepIdAndStatus(recordId, recordStepId, "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录ID[" + recordId + "]、关联的处置步骤ID[" + recordStepId + "]查询关联的应急工作组发生异常");
        }
        if(handleGroupList == null) {
            return null;
        }

        //2 遍历应急处置步骤，查询其关联的工作组信息
        for(EmerHandleRecordGroup handleGroup : handleGroupList) {
            List<EmerHandleRecordMember> handleMemberList = this.emerHandleRecordMemberService.queryListByRecordIdAndRecordGroupId(recordId, handleGroup.getId());
            if(handleMemberList != null && handleMemberList.size() > 0) {
                handleGroup.setHandleMemberList(handleMemberList);
            }
        }
        return handleGroupList;
    }
}
