package com.cesgroup.prison.alarm.emerRecord.service.impl;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.alarm.emerRecord.dao.EmerRecordMemberDao;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecordMember;
import com.cesgroup.prison.alarm.emerRecord.service.EmerRecordMemberService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @program: 01 超图版
 * @description:
 * @author: Mr.li
 * @create: 2019-11-27 10:29
 */
@Service
@Transactional
public class EmerRecordMemberServiceImpl extends BaseDaoService<EmerRecordMember, String, EmerRecordMemberDao> implements EmerRecordMemberService {
    @Override
    public List<EmerRecordMember> queryListByRecordIdAndRecordGroupId(String recordId, String recordGroupId) throws ServiceException {
        if(recordId == null || "".equals(recordId) || recordGroupId == null || "".equals(recordGroupId)) {
            return null;
        }
        //1 应急工作组查询
        List<EmerRecordMember> handleMemberList = null;
        try {
            handleMemberList = this.getDao().findByRecordIdAndRecordGroupIdAndStatus(recordId, recordGroupId, "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急处置记录ID[" + recordId + "]、关联的工作组ID[" + recordGroupId + "]查询关联的应急工作组成员发生异常");
        }
        return handleMemberList;
    }
}