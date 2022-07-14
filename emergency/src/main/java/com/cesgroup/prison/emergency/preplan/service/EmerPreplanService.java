package com.cesgroup.prison.emergency.preplan.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.preplan.entity.EmerPreplan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 应急预案业务访问接口
 */
public interface EmerPreplanService extends IBaseCRUDService<EmerPreplan, String> {
    /**
     * 分页查询应急预案信息
     * @param paramMap
     * @param pageable
     * @return
     * @throws ServiceException
     */
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;

    /**
     * 根据主键ID，查询应急预案数据
     * @param id
     * @return
     * @throws ServiceException
     */
    public EmerPreplan queryById(String id) throws ServiceException;

    /**
     * 保存或更新应急预案
     *
     * @param emerPreplan
     * @throws ServiceException
     */
    public void saveOrUpdate(EmerPreplan emerPreplan) throws ServiceException;

    /**
     * 删除应急预案
     *
     * @param ids
     * @throws ServiceException
     */
    public void deleteByIds(String ids) throws ServiceException;

    /**
     * 根据应急预案ID，查询应急预案详情(包括关联的处置流程、应急工作组、应急工作组成员信息)
     * @param id
     * @return
     * @throws ServiceException
     */
    public EmerPreplan queryDetailById(String id) throws ServiceException;

    /**
     * 根据单位编号、数据状态，查询应急预案
     * @param cusNumber
     * @param status
     * @return
     * @throws ServiceException
     */
    public List<EmerPreplan> queryByCusNumberAndStatus(String cusNumber, String status) throws ServiceException;
}
