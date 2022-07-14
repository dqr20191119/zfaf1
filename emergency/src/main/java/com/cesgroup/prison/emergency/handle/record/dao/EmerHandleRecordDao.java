package com.cesgroup.prison.emergency.handle.record.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.emergency.handle.record.entity.EmerHandleRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * 应急处置记录数据访问对象
 */
public interface EmerHandleRecordDao extends BaseDao<EmerHandleRecord, String> {
    /**
     * 分页查询应急处置记录
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> findWithPage(Map<String, Object> paramMap, Pageable pageable);

    /**
     * 根据处置记录编号，查询处置记录
     * @param id
     * @return
     */
    EmerHandleRecord findById(@Param("id") String id);

    /**
     * 查询最大排序号
     *
     * @param cusNumber 监狱/单位编号
     * @return
     */
    Integer findMaxShowOrderByCusNumber(@Param("cusNumber") String cusNumber);


    AlarmRecordEntity queryAREByid(@Param("id") String id);



}