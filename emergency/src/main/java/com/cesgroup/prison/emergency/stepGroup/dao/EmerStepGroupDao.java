package com.cesgroup.prison.emergency.stepGroup.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.emergency.stepGroup.entity.EmerStepGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 处理步骤与工作组梯队关联关系数据访问对象
 */
public interface EmerStepGroupDao extends BaseDao<EmerStepGroup, String> {
    /**
     * 根据主键ID查询数据
     *
     * @param id
     * @return
     */
    EmerStepGroup findById(@Param("id") String id);

    /**
     * 查询最大排序号
     *
     * @param cusNumber 监狱/单位编号
     * @return
     */
    Integer findMaxShowOrderByCusNumberAndStepId(@Param("cusNumber") String cusNumber, @Param("stepId") String stepId);

    /**
     * 分页查询指令数据
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> findWithPage(Map<String, Object> paramMap, Pageable pageable);

    /**
     *
     * @param cusNumber
     * @param stepId
     * @param status
     * @return
     */
    List<EmerStepGroup> findByCusNumberAndStepIdAndStatus(@Param("cusNumber") String cusNumber, @Param("stepId") String stepId, @Param("status") String status);

    /**
     *
     * @param cusNumber
     * @param groupId
     * @param status
     * @return
     */
    List<EmerStepGroup> findByCusNumberAndGroupIdAndStatus(@Param("cusNumber") String cusNumber, @Param("groupId") String groupId, @Param("status") String status);

    /**
     *
     * @param cusNumber
     * @param stepId
     * @param groupId
     * @param status
     * @return
     */
    List<EmerStepGroup> findByCusNumberAndStepIdAndGroupIdAndStatus(@Param("cusNumber") String cusNumber, @Param("stepId") String stepId, @Param("groupId") String groupId, @Param("status") String status);

    /**
     * 根据工作组ID数组、数据状态，查询处置步骤与工作组关系列表
     * @param groupIdArray
     * @param status
     * @return
     */
    List<EmerStepGroup> findByGroupIdArrayAndStatus(@Param("groupIdArray") String[] groupIdArray, @Param("status") String status);

    /**
     * 根据处理步骤ID数组、数据状态，查询处置步骤与工作组关系列表
     * @param stepIdArray
     * @param status
     * @return
     */
    List<EmerStepGroup> findByStepIdArrayAndStatus(@Param("stepIdArray") String[] stepIdArray, @Param("status") String status);

    /**
     * 根据应急预案ID数组、数据状态，查询工作组与成员关系列表
     * @param preplanIdArray
     * @param status
     * @return
     */
    List<EmerStepGroup> findExistsEmerStepByPreplanIdArrayAndStatus(@Param("preplanIdArray") String[] preplanIdArray, @Param("status") String status);
}