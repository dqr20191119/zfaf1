package com.cesgroup.prison.emergency.handle.recordMember.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.handle.record.service.EmerHandleRecordService;
import com.cesgroup.prison.emergency.handle.recordGroup.entity.EmerHandleRecordGroup;
import com.cesgroup.prison.emergency.handle.recordMember.dto.NearbyPoliceDto;
import com.cesgroup.prison.emergency.handle.recordMember.entity.EmerHandleRecordMember;
import com.cesgroup.prison.emergency.member.entity.EmerMember;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 应急处置记录业务访问接口
 */
public interface EmerHandleRecordMemberService extends IBaseCRUDService<EmerHandleRecordMember, String> {
    /**
     * 分页查询应急处置记录的工作组成员
     * @param paramMap
     * @param pageable
     * @return
     * @throws ServiceException
     */
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;

    /**
     * 根据应急处置记录的工作组ID，查询应急处置记录的工作组成员列表
     * @param recordGroupId
     * @return
     * @throws ServiceException
     */
    public List<EmerHandleRecordMember> queryByRecordGroupId(String recordGroupId) throws ServiceException;

    /**
     * 分页查询附近的民警
     * @param paramMap
     * @param pageable
     * @return
     * @throws ServiceException
     */
    public Page<NearbyPoliceDto> queryNearbyWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;

    /**
     * 分页查询本单位全部的民警
     * @param paramMap
     * @param pageable
     * @return
     * @throws ServiceException
     */
    public Page<NearbyPoliceDto> queryAllPoliceWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;

    /**
     * 新增或更新应急处置记录的工作组成员
     * @param recordGroupId
     * @param memberDataJson
     * @throws ServiceException
     */
    public void saveOrUpdate(String recordGroupId, String memberDataJson) throws ServiceException;

    /**
     * 根据应急处置记录的工作组ID，查询工作组成员并呼叫
     *
     * @param  clientIp 客户端IP地址，用于判定是否绑定对讲主机
     * @param recordGroupId 应急处置记录的工作组ID
     * @param noticeContent 通知内容
     * @param restartCall 是否重新呼叫标识
     * @throws ServiceException
     */
    public void startCallByRecordGroupId(String clientIp, String recordGroupId, String noticeContent, boolean restartCall) throws ServiceException;

    /**
     * 根据应急处置记录ID、应急处置记录关联的工作组ID，查询关联的工作组成员列表
     * @param recordId
     * @param recordGroupId
     * @return
     * @throws ServiceException
     */
    public List<EmerHandleRecordMember> queryListByRecordIdAndRecordGroupId(String recordId, String recordGroupId) throws ServiceException;
}
