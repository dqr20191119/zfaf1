package com.cesgroup.prison.emergency.stepGroup.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.emergency.group.entity.EmerGroup;
import com.cesgroup.prison.emergency.stepGroup.entity.EmerStepGroup;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * 应急预案处置步骤关联梯队业务访问接口
 */
public interface EmerStepGroupService extends IBaseCRUDService<EmerStepGroup, String> {
    /**
     * 分页查询应急预案处置步骤关联梯队信息
     * @param paramMap
     * @param pageable
     * @return
     * @throws ServiceException
     */
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;

    /**
     * 保存或更新应急预案处置步骤关联梯队
     *
     * @param stepId
     * @param groupIds
     * @throws ServiceException
     */
    public void saveOrUpdate(String stepId, String groupIds) throws ServiceException;

    /**
     * 根据预案编号、处置步骤编号，查询已关联的工作组
     * @param preplanId
     * @param stepId
     * @return
     * @throws ServiceException
     */
    public List<EmerGroup> queryCheckedGroupByPreplanIdAndStepId(String preplanId, String stepId) throws ServiceException;

    /**
     * 根据预案编号、处置步骤编号，查询待关联的工作组
     * @param preplanId
     * @param stepId
     * @return
     * @throws ServiceException
     */
    public List<EmerGroup> queryUncheckedGroupByPreplanIdAndStepId(String preplanId, String stepId) throws ServiceException;
}
