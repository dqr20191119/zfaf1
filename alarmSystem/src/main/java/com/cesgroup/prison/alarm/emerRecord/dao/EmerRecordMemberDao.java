package com.cesgroup.prison.alarm.emerRecord.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecordMember;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: 01 超图版
 * @description:
 * @author: Mr.li
 * @create: 2019-11-27 10:31
 */
public interface EmerRecordMemberDao extends BaseDao<EmerRecordMember, String> {
    /**
     * 根据应急处置记录的处置记录ID、工作组ID、数据状态，查询应急处置记录的工作组成员列表
     * @param recordId
     * @param recordGroupId
     * @param status
     * @return
     */
    List<EmerRecordMember> findByRecordIdAndRecordGroupIdAndStatus(@Param("recordId") String recordId, @Param("recordGroupId") String recordGroupId, @Param("status") String status);

}