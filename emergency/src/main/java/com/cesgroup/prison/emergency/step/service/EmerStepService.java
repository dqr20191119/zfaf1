package com.cesgroup.prison.emergency.step.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.TreeDto;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.step.dto.EmerStepDto;
import com.cesgroup.prison.emergency.step.entity.EmerStep;

/**
 * 应急预案处置步骤业务访问接口
 */
public interface EmerStepService extends IBaseCRUDService<EmerStep, String> {
    /**
     * 初始化应急预案树形结构数据
     * @param cusNumber
     * @return
     * @throws ServiceException
     */
    public List<TreeDto> initTree(String cusNumber) throws ServiceException;

    /**
     * 分页查询应急预案处置步骤信息
     * @param paramMap
     * @param pageable
     * @return
     * @throws ServiceException
     */
    public Page<EmerStepDto> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;

    /**
     * 根据主键ID，查询应急预案处置步骤数据
     * @param id
     * @return
     * @throws ServiceException
     */
    public EmerStep queryById(String id) throws ServiceException;

    /**
     * 保存或更新应急预案处置步骤
     *
     * @param emerStep
     * @throws ServiceException
     */
    public void saveOrUpdate(EmerStep emerStep) throws ServiceException;

    /**
     * 删除应急预案处置步骤
     *
     * @param ids
     * @throws ServiceException
     */
    public void deleteByIds(String ids) throws ServiceException;

    /**
     * 根据应急预案ID，查询该预案的处置步骤
     * @param preplanId
     * @return
     * @throws ServiceException
     */
    public List<EmerStep> queryByPreplanId(String preplanId) throws ServiceException;

    /**
     * 根据应急预案ID，查询该预案的处置步骤详情列表(包括工作组、工作组成员)
     * @param preplanId
     * @return
     * @throws ServiceException
     */
    public List<EmerStep> queryDetailListByPreplanId(String preplanId) throws ServiceException;
}
