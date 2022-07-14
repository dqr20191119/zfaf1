package com.cesgroup.prison.emergency.group.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.emergency.group.entity.EmerGroup;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 指挥调度指令管理-数据访问接口
 */
public interface EmerGroupDao extends BaseDao<EmerGroup, String> {
    /**
     * 根据主键ID查询数据
     *
     * @param id
     * @return
     */
    EmerGroup findById(@Param("id") String id);

    /**
     * 查询最大排序号
     *
     * @param cusNumber 监狱/单位编号
     * @return
     */
    Integer findMaxShowOrderByCusNumber(@Param("cusNumber") String cusNumber);

    /**
     * 查询最大排序号
     *
     * @param cusNumber 监狱/单位编号
     * @param preplanId 应急预案编号
     * @return
     */
    Integer findMaxShowOrderByCusNumberAndPreplanId(@Param("cusNumber") String cusNumber, @Param("preplanId") String preplanId);

    /**
     * 根据单位编号、数据状态，查询指令列表
     * @param cusNumber 监狱/单位编号
     * @param status 数据状态
     * @return
     */
    List<EmerGroup> findByCusNumberAndStatus(@Param("cusNumber") String cusNumber, @Param("status") String status);

    /**
     * 分页查询指令数据
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> findWithPage(Map<String, Object> paramMap, Pageable pageable);

    /**
     * 根据单位编号、应急预案编号、工作组名称、数据状态，查询工作组数据
     *
     * @param cusNumber
     * @param preplanId
     * @param name
     * @param status
     * @return
     */
    List<EmerGroup> findByCusNumberAndPreplanIdAndNameAndStatus(@Param("cusNumber") String cusNumber, @Param("preplanId") String preplanId, @Param("name") String name, @Param("status") String status);

    /**
     * 根据预案编号、处置步骤编号，查询已关联的工作组
     *
     * @param preplanId
     * @param stepId
     * @return
     */
    List<EmerGroup> findExistsEmerStepGroupByPreplanIdAndStepId(@Param("preplanId") String preplanId, @Param("stepId") String stepId);

    /**
     * 根据预案编号、处置步骤编号，查询待关联的工作组
     *
     * @param preplanId
     * @param stepId
     * @return
     */
    List<EmerGroup> findNotExistsEmerStepGroupByPreplanIdAndStepId(@Param("preplanId") String preplanId, @Param("stepId") String stepId);

    /**
     *
     * @param idArray
     * @param status
     * @return
     */
    List<EmerGroup> findExistsEmerStepGroupByIdArrayAndStatus(@Param("idArray") String[] idArray, @Param("status") String status);

    /**
     *
     * @param idArray
     * @param status
     * @return
     */
    List<EmerGroup> findByIdArrayAndStatus(@Param("idArray") String[] idArray, @Param("status") String status);

    /**
     * 根据应急预案ID数组、数据状态，查询工作组列表
     * @param preplanIdArray
     * @param status
     * @return
     */
    List<EmerGroup> findByPreplanIdArrayAndStatus(@Param("preplanIdArray") String[] preplanIdArray, @Param("status") String status);
}