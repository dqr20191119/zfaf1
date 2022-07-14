package com.cesgroup.prison.emergency.step.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.emergency.step.dto.EmerStepDto;
import com.cesgroup.prison.emergency.step.entity.EmerStep;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 应急预案处理步骤数据访问对象
 */
public interface EmerStepDao extends BaseDao<EmerStep, String> {
    /**
     * 根据主键ID查询数据
     *
     * @param id
     * @return
     */
    EmerStep findById(@Param("id") String id);

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
     * 分页查询指令数据
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<EmerStepDto> findWithPage(Map<String, Object> paramMap, Pageable pageable);

    /**
     * 根据单位编号、应急预案编号、处置步骤名称、数据状态，查询处置步骤数据
     *
     * @param cusNumber
     * @param preplanId
     * @param name
     * @param status
     * @return
     */
    List<EmerStep> findByCusNumberAndPreplanIdAndNameAndStatus(@Param("cusNumber") String cusNumber, @Param("preplanId") String preplanId, @Param("name") String name, @Param("status") String status);

    /**
     *
     * @param idArray
     * @param status
     * @return
     */
    List<EmerStep> findByIdArrayAndStatus(@Param("idArray") String[] idArray, @Param("status") String status);

    /**
     *
     * @param preplanIdArray
     * @param status
     * @return
     */
    List<EmerStep> findByPreplanIdArrayAndStatus(@Param("preplanIdArray") String[] preplanIdArray, @Param("status") String status);
}