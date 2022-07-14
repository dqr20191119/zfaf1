package com.cesgroup.prison.alarm.emerRecord.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecordStep;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: 01 超图版
 * @description:
 * @author: Mr.li
 * @create: 2019-11-27 10:06
 */
public interface EmerRecordStepDao extends BaseDao<EmerRecordStep, String> {
    /**
     * 根据应急处置记录ID、数据状态，查询应急处置记录的处置步骤
     *
     * @param recordId
     * @param status
     * @return
     */
    List<EmerRecordStep> findByRecordIdAndStatus(@Param("recordId") String recordId, @Param("status") String status);
}