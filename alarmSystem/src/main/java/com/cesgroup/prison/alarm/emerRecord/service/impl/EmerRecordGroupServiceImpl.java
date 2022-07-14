package com.cesgroup.prison.alarm.emerRecord.service.impl;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.alarm.emerRecord.dao.EmerRecordGroupDao;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecordGroup;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecordMember;
import com.cesgroup.prison.alarm.emerRecord.service.EmerRecordGroupService;
import com.cesgroup.prison.alarm.emerRecord.service.EmerRecordMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: 01 超图版
 * @description:
 * @author: Mr.li
 * @create: 2019-11-27 10:18
 */
@Service
@Transactional
public class EmerRecordGroupServiceImpl extends BaseDaoService<EmerRecordGroup, String, EmerRecordGroupDao> implements EmerRecordGroupService {
    /**
     * 应急处置记录的工作组成员Service
     */
    @Autowired
    private EmerRecordMemberService emerRecordMemberService;
    @Override
    public List<EmerRecordGroup> queryDetailListByRecordIdAndRecordStepId(String recordId, String recordStepId) throws ServiceException {
        if(recordId == null || "".equals(recordId) || recordStepId == null || "".equals(recordStepId)) {
            return null;
        }
        //1 应急工作组查询
        List<EmerRecordGroup> handleGroupList = null;
        try {
            handleGroupList = this.getDao().findByRecordIdAndRecordStepIdAndStatus(recordId, recordStepId, "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录ID[" + recordId + "]、关联的处置步骤ID[" + recordStepId + "]查询关联的应急工作组发生异常");
        }
        if(handleGroupList == null) {
            return null;
        }

        //2 遍历应急处置步骤，查询其关联的工作组信息
        for(EmerRecordGroup handleGroup : handleGroupList) {
            List<EmerRecordMember> handleMemberList = this.emerRecordMemberService.queryListByRecordIdAndRecordGroupId(recordId, handleGroup.getId());
            if(handleMemberList != null && handleMemberList.size() > 0) {
                handleGroup.setHandleMemberList(handleMemberList);
            }
        }
        return handleGroupList;
    }
}