package com.cesgroup.prison.xtcs.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.xtcs.entity.XtcsEntity;

/**
 * 系统参数业务处理接口
 * XtcsService
 *
 * @author cheng.jie
 * @created Create Time: 2020/4/28
 */
public interface XtcsService extends IBaseCRUDService<XtcsEntity, String> {
    /**
     * 分页查询
     * @param paramMap
     * @param pageable
     * @return
     * @throws ServiceException
     */
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;

    /**
     * 根据主键ID查询
     * @param id
     * @return
     * @throws ServiceException
     */
    public XtcsEntity queryById(String id) throws ServiceException;

    /**
     * 根据参数编码获取系统参数
     * @param csbm
     * @return
     * @throws ServiceException
     */
    public XtcsEntity queryByCsbm(String csbm) throws ServiceException;

    /**
     * 保存或更新
     *
     * @param xtcsEntity
     * @throws ServiceException
     */
    public void saveOrUpdate(XtcsEntity xtcsEntity) throws ServiceException;

    /**
     * 删除
     *
     * @param ids
     * @throws ServiceException
     */
    public void deleteByIds(String ids) throws ServiceException;

    /**
     * 根据参数编码获取系统参数值
     * @param csbm
     * @return
     * @throws ServiceException
     */
    public String queryCzsByCsbm(String csbm) throws ServiceException;
}
