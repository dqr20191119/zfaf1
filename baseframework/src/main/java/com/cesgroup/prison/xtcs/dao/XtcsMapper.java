package com.cesgroup.prison.xtcs.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.exception.PersistenceException;
import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xtcs.entity.XtcsEntity;

public interface XtcsMapper extends BaseDao<XtcsEntity,String> {
    /**
     * 分页查询
     * @param paramMap
     * @param pageable
     * @return
     */
    Page<Map<String, Object>> findWithPage(Map<String, Object> paramMap, Pageable pageable) throws PersistenceException;

    /**
     * 根据主键ID查询
     * @param id
     * @return
     * @throws PersistenceException
     */
    XtcsEntity findById(@Param("id") String id) throws PersistenceException;

    /**
     * 根据主键ID数组查询
     * @param idArray
     * @return
     * @throws PersistenceException
     */
    List<XtcsEntity> findByIdArray(@Param("idArray") String[] idArray) throws PersistenceException;

    /**
     * 查询最大排序号
     *
     * @return
     * @throws PersistenceException
     */
    Integer findMaxSjpx() throws PersistenceException;

    /**
     * 根据参数编码查询
     * @param csbm
     * @return
     * @throws PersistenceException
     */
    List<XtcsEntity> findByCsbm(@Param("csbm") String csbm) throws PersistenceException;
}
