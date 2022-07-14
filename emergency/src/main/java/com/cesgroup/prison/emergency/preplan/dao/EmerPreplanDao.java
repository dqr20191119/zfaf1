package com.cesgroup.prison.emergency.preplan.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.emergency.preplan.entity.EmerPreplan;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 应急预案数据访问对象
 */
public interface EmerPreplanDao extends BaseDao<EmerPreplan, String> {
    /**
     * 根据主键ID查询数据
     *
     * @param id
     * @return
     */
    EmerPreplan findById(@Param("id") String id);

    /**
     * 查询最大排序号
     *
     * @param cusNumber 监狱/单位编号
     * @return
     */
    Integer findMaxShowOrderByCusNumber(@Param("cusNumber") String cusNumber);

    /**
     * 分页查询应急预案数据
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> findWithPage(Map<String, Object> paramMap, Pageable pageable);

    /**
     * 根据监狱编号、应急预案名称、状态，查询应急预案数据
     * @param cusNumber
     * @param name
     * @param status
     * @return
     */
    List<EmerPreplan> findByCusNumberAndNameAndStatus(@Param("cusNumber") String cusNumber, @Param("name") String name, @Param("status") String status);

    /**
     * 根据监狱编号、状态，查询应急预案数据
     * @param cusNumber
     * @param status
     * @return
     */
    List<EmerPreplan> findByCusNumberAndStatus(@Param("cusNumber") String cusNumber, @Param("status") String status);

    /**
     * 根据应急预案ID数组、数据状态，查询应急预案列表
     *
     * @param idArray
     * @param status
     * @return
     */
    List<EmerPreplan> findByIdArrayAndStatus(@Param("idArray") String[] idArray, @Param("status") String status);

    /**
     * 根据监狱编号、应急预案来源、状态，查询应急预案数据
     * @param cusNumber
     * @param source
     * @param status
     * @return
     */
    List<EmerPreplan> findByCusNumberAndSourceAndStatus(@Param("cusNumber") String cusNumber, @Param("source") String source, @Param("status") String status);

    /**
     * 根据监狱编号、应急预案来源、状态，查询应急预案ID列表
     * @param cusNumber
     * @param source
     * @param status
     * @return
     */
    List<String> findIdListByCusNumberAndSourceAndStatus(@Param("cusNumber") String cusNumber, @Param("source") String source, @Param("status") String status);
}