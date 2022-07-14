package com.cesgroup.prison.emergency.handle.recordGroup.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.emergency.handle.recordGroup.entity.EmerHandleRecordGroup;
import com.cesgroup.prison.emergency.handle.recordStep.entity.EmerHandleRecordStep;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 应急处置记录数据访问对象
 */
public interface EmerHandleRecordGroupDao extends BaseDao<EmerHandleRecordGroup, String> {
    /**
     * 根据主键ID，查询应急处置记录的工作组
     * @param id
     * @return
     */
    EmerHandleRecordGroup findById(@Param("id") String id);

    /**
     * 查询最大排序号
     *
     * @param cusNumber 监狱/单位编号
     * @param recordId 处置记录编号
     * @param recordStepId 处置记录的处置步骤编号
     * @return
     */
    Integer findMaxShowOrderByCusNumberAndRecordIdAndRecordStepId(@Param("cusNumber") String cusNumber, @Param("recordId") String recordId, @Param("recordStepId") String recordStepId);

    /**
     * 根据应急处置记录的处置步骤ID、数据状态，查询应急处置记录的工作组列表
     * @param recordStepId
     * @param status
     * @return
     */
    List<EmerHandleRecordGroup> findByRecordStepIdAndStatus(@Param("recordStepId") String recordStepId, @Param("status") String status);

    /**
     * 根据应急处置记录的处置记录ID、处置步骤ID、数据状态，查询应急处置记录的工作组列表
     * @param recordId
     * @param recordStepId
     * @param status
     * @return
     */
    List<EmerHandleRecordGroup> findByRecordIdAndRecordStepIdAndStatus(@Param("recordId") String recordId, @Param("recordStepId") String recordStepId, @Param("status") String status);
}