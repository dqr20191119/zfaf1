package com.cesgroup.prison.emergency.member.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.emergency.member.entity.EmerMember;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 应急预案工作组成员数据访问对象
 */
public interface EmerMemberDao extends BaseDao<EmerMember, String> {
    /**
     * 根据主键ID查询数据
     *
     * @param id
     * @return
     */
    EmerMember findById(@Param("id") String id);

    /**
     * 查询最大排序号
     *
     * @param cusNumber 监狱/单位编号
     * @return
     */
    Integer findMaxShowOrderByCusNumber(@Param("cusNumber") String cusNumber);

    /**
     * 根据单位编号、数据状态，查询指令列表
     * @param cusNumber 监狱/单位编号
     * @param status 数据状态
     * @return
     */
    List<EmerMember> findByCusNumberAndStatus(@Param("cusNumber") String cusNumber, @Param("status") String status);

    /**
     * 分页查询指令数据
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> findWithPage(Map<String, Object> paramMap, Pageable pageable);

    /**
     * 根据单位编码、人员名称、呼叫号码，查询应急人员数据
     * @param cusNumber
     * @param name
     * @param callNo
     * @param status
     * @return
     */
    List<EmerMember> findByCusNumberAndNameAndCallNoAndStatus(@Param("cusNumber") String cusNumber, @Param("name") String name, @Param("callNo") String callNo, @Param("status") String status);

    /**
     * 根据有工作组ID、数据状态，查询工作组关联的成员列表
     * @param groupId
     * @param status
     * @return
     */
    List<EmerMember> findExistsEmerGroupMemberByGroupIdAndStatus(@Param("groupId") String groupId, @Param("status") String status);
}