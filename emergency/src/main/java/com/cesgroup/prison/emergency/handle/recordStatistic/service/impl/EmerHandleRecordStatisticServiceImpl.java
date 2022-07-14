package com.cesgroup.prison.emergency.handle.recordStatistic.service.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.handle.recordStatistic.dao.EmerHandleRecordStatisticDao;
import com.cesgroup.prison.emergency.handle.recordStatistic.entity.EmerHandleRecordStatistic;
import com.cesgroup.prison.emergency.handle.recordStatistic.service.EmerHandleRecordStatisticService;

/**
 * 应急处置记录统计操作类
 */
@Service
@Transactional
public class EmerHandleRecordStatisticServiceImpl extends BaseDaoService<EmerHandleRecordStatistic, String, EmerHandleRecordStatisticDao> implements EmerHandleRecordStatisticService {

    @Override
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException {
        try {
            Page<Map<String, Object>> page = this.getDao().findWithPage(paramMap, pageable);
            return page;
        } catch (Exception e) {
            throw new ServiceException("分页查询应急统计发生异常，原因：" + e);
        }
    }

    @Override
    public Page<Map<String, Object>> queryRecordWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException {try {
        Page<Map<String, Object>> page = this.getDao().findRecordWithPage(paramMap, pageable);
        return page;
    } catch (Exception e) {
        throw new ServiceException("分页查询应急处置记录发生异常，原因：" + e);
    }
    }
}
