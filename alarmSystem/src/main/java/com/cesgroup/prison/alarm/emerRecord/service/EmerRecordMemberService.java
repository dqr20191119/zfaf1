package com.cesgroup.prison.alarm.emerRecord.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecordMember;

import java.util.List;

/**
 * @program: 01 超图版
 * @description:
 * @author: Mr.li
 * @create: 2019-11-27 10:26
 */
public interface EmerRecordMemberService extends IBaseCRUDService<EmerRecordMember, String> {
    /**
     * 根据应急处置记录ID、应急处置记录关联的工作组ID，查询关联的工作组成员列表
     * @param recordId
     * @param recordGroupId
     * @return
     * @throws ServiceException
     */
    public List<EmerRecordMember> queryListByRecordIdAndRecordGroupId(String recordId, String recordGroupId) throws ServiceException;

}