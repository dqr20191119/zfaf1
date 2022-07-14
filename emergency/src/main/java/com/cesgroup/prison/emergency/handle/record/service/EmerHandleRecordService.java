package com.cesgroup.prison.emergency.handle.record.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.emergency.handle.record.entity.EmerHandleRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * 应急处置记录业务访问接口
 */
public interface EmerHandleRecordService extends IBaseCRUDService<EmerHandleRecord, String> {
    /**
     * 分页查询应急预案信息
     * @param paramMap
     * @param pageable
     * @return
     * @throws ServiceException
     */
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;

    /**
     * 保存或更新应急处置记录
     *
     * @param emerHandleRecord
     * @throws ServiceException
     */
    public EmerHandleRecord saveOrUpdate(EmerHandleRecord emerHandleRecord) throws ServiceException;

    /**
     * 根据主键ID，查询应急处置记录
     * @param id
     * @return
     * @throws ServiceException
     */
    public EmerHandleRecord queryById(String id) throws ServiceException;

    /**
     * 更新应急处置记录状态
     * @param emerHandleRecord
     * @throws ServiceException
     */
    public void updateRecordStatus(EmerHandleRecord emerHandleRecord) throws ServiceException;

    /**
     * 根据主键ID，查询应急处置记录详情
     * @param id
     * @return
     * @throws ServiceException
     */
    public EmerHandleRecord queryDetailById(String id) throws ServiceException;

    /**
     * 查询预案报告
     * @return
     */
    public Map<String,Object> queryYjyaReport(String id);

    public AlarmRecordEntity queryAREByid(String id);

    /**
     * 更新经验总结
     * */
    public EmerHandleRecord updateExperience(EmerHandleRecord experience);
}
