package com.cesgroup.prison.emergency.handle.recordStep.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.emergency.handle.recordStep.entity.EmerHandleRecordStep;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 应急处置记录数据访问对象
 */
public interface EmerHandleRecordStepDao extends BaseDao<EmerHandleRecordStep, String> {

    /**
     * 查询最大排序号
     *
     * @param cusNumber 监狱/单位编号
     * @param recordId 处置记录编号
     * @return
     */
    Integer findMaxShowOrderByCusNumberAndRecordId(@Param("cusNumber") String cusNumber, @Param("recordId") String recordId);

    /**
     * 根据应急处置记录ID、数据状态，查询应急处置记录的处置步骤
     *
     * @param recordId
     * @param status
     * @return
     */
    List<EmerHandleRecordStep> findByRecordIdAndStatus(@Param("recordId") String recordId, @Param("status") String status);

    /**
     * 根据主键ID，查询应急处置记录的处置步骤信息
     *
     * @param id
     * @return
     */
    EmerHandleRecordStep findById(@Param("id") String id);

    /**
     * 根据应急处置记录的处置步骤ID，查询当前处置步骤之前的未被处置的处置步骤列表
     *
     * @param id
     * @return
     */
    List<EmerHandleRecordStep> findUnhandledPrevStepById(@Param("id") String id);
}