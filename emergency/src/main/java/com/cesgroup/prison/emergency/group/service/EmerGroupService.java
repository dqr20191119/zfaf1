package com.cesgroup.prison.emergency.group.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.TreeDto;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.group.entity.EmerGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 应急预案工作组业务访问接口
 */
public interface EmerGroupService extends IBaseCRUDService<EmerGroup, String> {
    /**
     * 初始化应急预案树形结构数据
     * @param cusNumber
     * @return
     * @throws ServiceException
     */
    public List<TreeDto> initTree(String cusNumber) throws ServiceException;

    /**
     * 分页查询应急预案工作组信息
     * @param paramMap
     * @param pageable
     * @return
     * @throws ServiceException
     */
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;

    /**
     * 根据主键ID，查询应急预案工作组数据
     * @param id
     * @return
     * @throws ServiceException
     */
    public EmerGroup queryById(String id) throws ServiceException;

    /**
     * 保存或更新应急预案工作组
     *
     * @param emerGroup
     * @throws ServiceException
     */
    public void saveOrUpdate(EmerGroup emerGroup) throws ServiceException;

    /**
     * 删除应急预案工作组
     *
     * @param ids
     * @throws ServiceException
     */
    public void deleteByIds(String ids) throws ServiceException;

    /**
     * 根据处置步骤ID，查询工作组详情列表
     * @param preplanId
     * @param stepId
     * @return
     * @throws ServiceException
     */
    public List<EmerGroup> queryDetailListByPreplanIdAndStepId(String preplanId, String stepId) throws ServiceException;
}
