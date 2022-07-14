package com.cesgroup.prison.emergency.handle.recordStatistic.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.handle.recordStatistic.entity.EmerHandleRecordStatistic;

/**
 * 应急处置记录业务访问接口
 */
public interface EmerHandleRecordStatisticService extends IBaseCRUDService<EmerHandleRecordStatistic, String> {
    /**
     * 分页查询应急统计
     * @param paramMap
     * @param pageable
     * @return
     * @throws ServiceException
     */
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;

    /**
     * 分页查询应急处置记录信息
     * @param paramMap
     * @param pageable
     * @return
     * @throws ServiceException
     */
    public Page<Map<String, Object>> queryRecordWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;
}
