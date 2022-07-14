package com.cesgroup.prison.emergency.handle.recordMember.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.emergency.handle.recordGroup.entity.EmerHandleRecordGroup;
import com.cesgroup.prison.emergency.handle.recordMember.dto.NearbyLocationDto;
import com.cesgroup.prison.emergency.handle.recordMember.dto.NearbyPoliceDto;
import com.cesgroup.prison.emergency.handle.recordMember.entity.EmerHandleRecordMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 应急处置记录的工作组成员数据访问对象
 */
public interface EmerHandleRecordMemberDao extends BaseDao<EmerHandleRecordMember, String> {
    /**
     * 根据主键ID，查询应急处置记录的工作组成员记录
     * @param id
     * @return
     */
    EmerHandleRecordMember findById(@Param("id") String id);

    /**
     * 分页查询应急处置记录的工作组成员
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> findWithPage(Map<String, Object> paramMap, Pageable pageable);

    /**
     * 查询最大排序号
     *
     * @param cusNumber 监狱/单位编号
     * @param recordId 处置记录编号
     * @param recordGroupId 处置记录的工作组编号
     * @return
     */
    Integer findMaxShowOrderByCusNumberAndRecordIdAndRecordGroupId(@Param("cusNumber") String cusNumber, @Param("recordId") String recordId, @Param("recordGroupId") String recordGroupId);

    /**
     * 根据应急处置记录的工作组ID、数据状态，查询应急处置记录的工作组成员列表
     * @param recordGroupId
     * @param status
     * @return
     */
    List<EmerHandleRecordMember> findByRecordGroupIdAndStatus(@Param("recordGroupId") String recordGroupId, @Param("status") String status);

    /**
     * 分页查询附近的民警信息
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<NearbyPoliceDto> findNearbyWithPage(Map<String, Object> paramMap, Pageable pageable);

    /**
     * 分页查询本单位全部的民警信息
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<NearbyPoliceDto> findAllPoliceWithPage(Map<String, Object> paramMap, Pageable pageable);

    /**
     * 根据条件，查询附近民警所在位置详情
     * @param paramMap
     * @return
     */
    List<NearbyLocationDto> findNearbyLocationByContition(Map<String, Object> paramMap);

    /**
     * 根据应急处置记录的工作组ID、成员姓名、呼叫号码、数据状态，查询应急处置记录的工作组成员
     * @param recordGroupId
     * @param memberName
     * @param callNo
     * @param status
     * @return
     */
    List<EmerHandleRecordMember> findByRecordGroupIdAndMemberNameAndCallNoAndStatus(@Param("recordGroupId") String recordGroupId, @Param("memberName") String memberName, @Param("callNo") String callNo, @Param("status") String status);

    /**
     * 根据应急处置记录的工作组ID、呼叫号码、数据状态，查询应急处置记录的工作组成员
     * @param recordGroupId
     * @param callNo
     * @param status
     * @return
     */
    List<EmerHandleRecordMember> findByRecordGroupIdAndCallNoAndStatus(@Param("recordGroupId") String recordGroupId, @Param("callNo") String callNo, @Param("status") String status);

    /**
     * 根据应急处置记录的处置记录ID、工作组ID、数据状态，查询应急处置记录的工作组成员列表
     * @param recordId
     * @param recordGroupId
     * @param status
     * @return
     */
    List<EmerHandleRecordMember> findByRecordIdAndRecordGroupIdAndStatus(@Param("recordId") String recordId, @Param("recordGroupId") String recordGroupId, @Param("status") String status);
}