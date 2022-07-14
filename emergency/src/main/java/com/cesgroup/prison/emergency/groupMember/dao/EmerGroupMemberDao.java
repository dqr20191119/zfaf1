package com.cesgroup.prison.emergency.groupMember.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.emergency.groupMember.dto.EmerGroupMemberDto;
import com.cesgroup.prison.emergency.groupMember.entity.EmerGroupMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 应急预案工作组与工作组成员关联关系数据访问对象
 */
public interface EmerGroupMemberDao extends BaseDao<EmerGroupMember, String> {
    /**
     * 根据主键ID查询数据
     *
     * @param id
     * @return
     */
    EmerGroupMember findById(@Param("id") String id);

    /**
     * 查询最大排序号
     *
     * @param cusNumber 监狱/单位编号
     * @param groupId 工作组编号
     * @return
     */
    Integer findMaxShowOrderByCusNumberAndGroupId(@Param("cusNumber") String cusNumber, @Param("groupId") String groupId);

    /**
     * 分页查询指令数据
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> findWithPage(Map<String, Object> paramMap, Pageable pageable);

    /**
     * 根据单位编号、工作组编号、应急人员编号、数据状态，查询应急工作组与人员关联数据
     * @param cusNumber
     * @param groupId
     * @param memberId
     * @param status
     * @return
     */
    List<EmerGroupMember> findByCusNumberAndGroupIdAndMemberIdAndStatus(@Param("cusNumber") String cusNumber, @Param("groupId") String groupId, @Param("memberId") String memberId, @Param("status") String status);

    /**
     * 根据应急人员编号、数据状态，查询应急工作组与成员关系数据
     * @param memberId 应急人员编号
     * @param status 数据状态
     * @return
     */
    List<EmerGroupMember> findByMemberIdAndStatus(@Param("memberId") String memberId, @Param("status") String status);

    /**
     * 根据工作组ID，查询工作组的成员列表
     * @param groupId
     * @return
     */
    List<EmerGroupMemberDto> findEmerGroupMemberDtoByGroupId(@Param("groupId") String groupId);

    /**
     * 根据工作组ID数组、数据状态，查询工作组与成员关系列表
     * @param groupIdArray
     * @param status
     * @return
     */
    List<EmerGroupMember> findByGroupIdArrayAndStatus(@Param("groupIdArray") String[] groupIdArray, @Param("status") String status);

    /**
     * 根据应急预案ID数组、数据状态，查询工作组与成员关系列表
     * @param preplanIdArray
     * @param status
     * @return
     */
    List<EmerGroupMember> findExistsEmerGroupByPreplanIdArrayAndStatus(@Param("preplanIdArray") String[] preplanIdArray, @Param("status") String status);
}