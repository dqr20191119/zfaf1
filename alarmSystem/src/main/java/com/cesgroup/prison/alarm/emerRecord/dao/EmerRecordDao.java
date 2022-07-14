package com.cesgroup.prison.alarm.emerRecord.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecord;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * @program: 01程序
 * @description:
 * @author: Mr.li
 * @create: 2019-11-18 14:45
 */
public interface EmerRecordDao extends BaseDao<EmerRecord, String> {
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
    EmerRecord findById(@Param("id") String id);

    /**
     * 查询最大排序号
     *
     * @param cusNumber 监狱/单位编号
     * @return
     */
    Integer findMaxShowOrderByCusNumber(@Param("cusNumber") String cusNumber);


    AlarmRecordEntity queryAREByid(@Param("id") String id);



}