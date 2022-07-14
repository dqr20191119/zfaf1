package com.cesgroup.prison.emergency.handle.recordStatistic.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.emergency.handle.recordStatistic.entity.EmerHandleRecordStatistic;

/**
 * 应急统计访问对象
 */
public interface EmerHandleRecordStatisticDao extends BaseDao<EmerHandleRecordStatistic, String> {
    /**
     * 分页查询应急统计数据
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> findWithPage(Map<String, Object> paramMap, Pageable pageable);

    /**
     * 分页查询应急处置记录
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> findRecordWithPage(Map<String, Object> paramMap, Pageable pageable);
}