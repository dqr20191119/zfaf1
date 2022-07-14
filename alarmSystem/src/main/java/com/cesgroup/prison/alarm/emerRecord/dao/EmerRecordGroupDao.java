package com.cesgroup.prison.alarm.emerRecord.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecordGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: 01 超图版
 * @description:
 * @author: Mr.li
 * @create: 2019-11-27 10:19
 */
public interface EmerRecordGroupDao extends BaseDao<EmerRecordGroup, String> {
    /**
     * 根据应急处置记录的处置记录ID、处置步骤ID、数据状态，查询应急处置记录的工作组列表
     * @param recordId
     * @param recordStepId
     * @param status
     * @return
     */
    List<EmerRecordGroup> findByRecordIdAndRecordStepIdAndStatus(@Param("recordId") String recordId, @Param("recordStepId") String recordStepId, @Param("status") String status);

}