package com.cesgroup.prison.emergency.step.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.TreeDto;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.group.dao.EmerGroupDao;
import com.cesgroup.prison.emergency.group.entity.EmerGroup;
import com.cesgroup.prison.emergency.group.service.EmerGroupService;
import com.cesgroup.prison.emergency.preplan.dao.EmerPreplanDao;
import com.cesgroup.prison.emergency.preplan.entity.EmerPreplan;
import com.cesgroup.prison.emergency.step.dao.EmerStepDao;
import com.cesgroup.prison.emergency.step.dto.EmerStepDto;
import com.cesgroup.prison.emergency.step.entity.EmerStep;
import com.cesgroup.prison.emergency.step.service.EmerStepService;
import com.cesgroup.prison.emergency.stepGroup.dao.EmerStepGroupDao;
import com.cesgroup.prison.emergency.stepGroup.entity.EmerStepGroup;
import com.cesgroup.prison.utils.CommonUtil;

@Service
@Transactional
public class EmerStepServiceImpl extends BaseDaoService<EmerStep, String, EmerStepDao> implements EmerStepService {
    /**
     * 应急预案DAO
     */
    @Autowired
    private EmerPreplanDao emerPreplanDao;
    /**
     * 应急工作组DAO
     */
    @Autowired
    private EmerGroupDao emerGroupDao;
    /**
     * 处置步骤与工作组关联关系DAO
     */
    @Autowired
    private EmerStepGroupDao emerStepGroupDao;
    /**
     * 应急工作组Service
     */
    @Autowired
    private EmerGroupService emerGroupService;

    @Override
    public List<TreeDto> initTree(String cusNumber) throws ServiceException {
        try {
            // 查询有效的应急预案
            List<EmerPreplan> emerPreplanList = this.emerPreplanDao.findByCusNumberAndStatus(cusNumber, "0");

            return packageTreeList(emerPreplanList);
        } catch (Exception e) {
            throw new ServiceException("初始化应急预案树形数据发生异常，原因->" + e);
        }
    }

    @Override
    public Page<EmerStepDto> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException {
        try {
            Page<EmerStepDto> page = this.getDao().findWithPage(paramMap, pageable);
            if(page != null && page.getContent() != null) {
                // 遍历结果集，查询处置步骤关联的工作组名称
                for(EmerStepDto emerStepDto : page.getContent()) {
                    String groupName = "";
                    List<EmerGroup> emerGroupList = this.queryEmerGroupByPreplanIdAndStepId(emerStepDto.getPreplanId(), emerStepDto.getId());

                    if(emerGroupList != null && emerGroupList.size() > 0) {
                        for(EmerGroup emerGroup : emerGroupList) {
                            groupName += emerGroup.getName() + "，";
                        }
                        if(groupName != null && !"".equals(groupName)) {
                            groupName = groupName.substring(0, groupName.lastIndexOf("，"));
                        }
                    }
                    emerStepDto.setGroupName(groupName);
                }
            }
            return page;
        } catch (Exception e) {
            throw new ServiceException("分页查询应急预案处置步骤发生异常，原因：" + e);
        }
    }

    @Override
    public EmerStep queryById(String id) throws ServiceException {
        try {
            return this.getDao().findById(id);
        } catch (Exception e) {
            throw new ServiceException("根据主键ID，查询应急预案处置步骤详细数据发生错误，原因->" + e);
        }
    }

    @Override
    public void saveOrUpdate(EmerStep emerStep) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }
        if(emerStep == null) {
            throw new ServiceException("处置步骤实体类为空");
        }
        emerStep.setCusNumber(user.getCusNumber());

        if(emerStep.getName() == null || "".equals(emerStep.getName())) {
            throw new ServiceException("处置步骤名称为空");
        }
        // 判断处置步骤名称是否重复
        try {
            if (this.isNameExists(emerStep)) {
                throw new BusinessLayerException("处置步骤名称[" + emerStep.getName() + "]在应急预案中已存在");
            }
        } catch (BusinessLayerException e) {
            throw new ServiceException(e.getMessage());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

        // 声明应急预案处置步骤
        EmerStep emerGroupOperate = null;
        if(emerStep.getId() != null && !"".equals(emerStep.getId())) {
            try {
                emerGroupOperate = this.getDao().findById(emerStep.getId());
            } catch (Exception e) {
                throw new ServiceException("根据应急处置步骤ID查询应急处置步骤数据失败");
            }
        }

        if(emerGroupOperate == null) {
            emerGroupOperate = new EmerStep();
            try {
                emerGroupOperate.setId(CommonUtil.createUUID());
                emerGroupOperate.setCusNumber(emerStep.getCusNumber());
                emerGroupOperate.setName(emerStep.getName());
                emerGroupOperate.setPreplanId(emerStep.getPreplanId());
                emerGroupOperate.setRemarks(emerStep.getRemarks());
                emerGroupOperate.setStatus("0");
                Integer showOrder = this.getDao().findMaxShowOrderByCusNumberAndPreplanId(emerStep.getCusNumber(), emerStep.getPreplanId());
                emerGroupOperate.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
                emerGroupOperate.setCreateUserId(user.getUserId());
                emerGroupOperate.setCreateTime(new Date());
                emerGroupOperate.setUpdateUserId(user.getUserId());
                emerGroupOperate.setUpdateTime(new Date());
                this.getDao().insert(emerGroupOperate);
            } catch (Exception e) {
                throw new ServiceException("新增应急处置步骤失败，原因：" + e);
            }
        } else {
            try {
                emerGroupOperate.setName(emerStep.getName());
                emerGroupOperate.setRemarks(emerStep.getRemarks());
                emerGroupOperate.setStatus("0");
                emerGroupOperate.setUpdateUserId(user.getUserId());
                emerGroupOperate.setUpdateTime(new Date());
                this.getDao().update(emerGroupOperate);
            } catch (Exception e) {
                throw new ServiceException("更新应急处置步骤失败，原因：" + e);
            }
        }
    }

    @Override
    public void deleteByIds(String ids) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }

        String[] idArr = ids != null && !"".equals(ids) ? ids.split(",") : null;
        if (idArr == null || idArr.length <= 0) {
            throw new ServiceException("请选择待删除的工作组");
        }

        // 删除工作组、工作组与用户关联关系、处置步骤与工作组关联关系
        List<EmerStep> emerStepList = this.queryByIdArray(idArr);
        List<EmerStepGroup> emerStepGroupList = this.queryEmerStepGroupByStepIdArray(idArr);

        if(emerStepList != null && emerStepList.size() > 0) {
            for(EmerStep emerStep : emerStepList) {
                try {
                    this.getDao().deleteByEntity(emerStep);
                } catch (Exception e) {
                    throw new ServiceException("删除处置步骤失败，原因：" + e);
                }
            }
        }
        if(emerStepGroupList != null && emerStepGroupList.size() > 0) {
            for(EmerStepGroup emerStepGroup : emerStepGroupList) {
                try {
                    this.emerStepGroupDao.deleteByEntity(emerStepGroup);
                } catch (Exception e) {
                    throw new ServiceException("删除处置步骤与工作组关联关系数据失败，原因：" + e);
                }
            }
        }
    }

    @Override
    public List<EmerStep> queryByPreplanId(String preplanId) throws ServiceException {
        try {
            return this.getDao().findByPreplanIdArrayAndStatus(new String[] {preplanId}, "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急预案ID，查询该预案关联的应急处置步骤发生异常，原因：" + e);
        }
    }

    @Override
    public List<EmerStep> queryDetailListByPreplanId(String preplanId) throws ServiceException {
        if(preplanId == null || "".equals(preplanId)) {
            return null;
        }
        //1 应急处置步骤查询
        List<EmerStep> emerStepList = null;
        try {
            emerStepList = this.getDao().findByPreplanIdArrayAndStatus(new String[] {preplanId}, "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急预案ID[" + preplanId + "]查询预案关联的处置步骤发生异常");
        }
        if(emerStepList == null) {
            return null;
        }
        //2 遍历应急处置步骤，查询其关联的工作组信息
        for(EmerStep emerStep : emerStepList) {
            List<EmerGroup> emerGroupList = this.emerGroupService.queryDetailListByPreplanIdAndStepId(emerStep.getPreplanId(), emerStep.getId());
            if(emerGroupList != null && emerGroupList.size() > 0) {
                emerStep.setEmerGroupList(emerGroupList);
            }
        }
        return emerStepList;
    }

    /**
     * 封装应急预案树形结构数据
     * @return
     */
    private List<TreeDto> packageTreeList(List<EmerPreplan> emerPreplanList) {
        List<TreeDto> treeList = new ArrayList<TreeDto>();
        // 根节点设置
        TreeDto root = new TreeDto();
        root.setId(TreeDto.ROOT_ID);
        root.setCode(TreeDto.ROOT_CODE);
        root.setName("应急预案");
        root.setOpen(true);
        treeList.add(root);

        if (emerPreplanList != null && emerPreplanList.size() > 0) {
            for (EmerPreplan emerPreplan : emerPreplanList) {
                TreeDto node = new TreeDto();
                node.setId(emerPreplan.getId());
                node.setName(emerPreplan.getName());
                node.setpId(root.getId());
                node.setOpen(false);
                treeList.add(node);
            }
        }
        return treeList;
    }

    /**
     * 判断处置步骤名称是否重复
     * @param emerStep
     * @return
     * @throws BusinessLayerException
     */
    private boolean isNameExists(EmerStep emerStep) throws ServiceException {
        if(emerStep == null) {
            throw new ServiceException("判断处置步骤名称是否重复失败，处置步骤实体类为空");
        }
        if(emerStep.getName() == null || "".equals(emerStep.getName())) {
            throw new ServiceException("判断处置步骤名称是否重复失败，处置步骤名称为空");
        }
        try {
            List<EmerStep> emerGroupList = this.queryByCusNumberAndPreplanIdAndName(emerStep.getCusNumber(), emerStep.getPreplanId(), emerStep.getName());
            if(emerGroupList != null && emerGroupList.size() > 0) {
                if(emerStep.getId() == null || "".equals(emerStep.getId())) {
                    return true;
                } else {
                    for(EmerStep tempZhddCommand : emerGroupList) {
                        if(!tempZhddCommand.getId().equals(emerStep.getId())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (ServiceException e) {
            throw new ServiceException("判断应急处置步骤名称是否重复失败，" + e.getMessage());
        } catch (Exception e) {
            throw new ServiceException("判断应急处置步骤名称是否重复失败，" + e.getMessage());
        }
    }

    /**
     * 根据单位编号、处置步骤名称、预案编号，查询有效应急处置步骤
     * @param cusNumber
     * @param preplanId
     * @param name
     * @return
     * @throws BusinessLayerException
     */
    private List<EmerStep> queryByCusNumberAndPreplanIdAndName(String cusNumber, String preplanId, String name) throws ServiceException {
        try {
            List<EmerStep> emerStepList = this.getDao().findByCusNumberAndPreplanIdAndNameAndStatus(cusNumber, preplanId, name, "0");
            return emerStepList;
        } catch (Exception e) {
            throw new ServiceException("根据单位编号、处置步骤名称、预案编号，查询有效应急处置步骤失败->" + e);
        }
    }

    /**
     * 根据预案ID、处置步骤ID，查询处置步骤关联的工作组列表
     * @param preplanId
     * @param stepId
     * @return
     * @throws ServiceException
     */
    private List<EmerGroup> queryEmerGroupByPreplanIdAndStepId(String preplanId, String stepId) throws ServiceException {
        if(preplanId == null || "".equals(preplanId)) {
            return null;
        }
        if(stepId == null || "".equals(stepId)) {
            return null;
        }
        try {
            return this.emerGroupDao.findExistsEmerStepGroupByPreplanIdAndStepId(preplanId, stepId);
        } catch (Exception e) {
            throw new ServiceException("根据预案ID、处置步骤ID，查询处置步骤关联的工作组列表发生异常，原因：" + e);
        }
    }

    /**
     * 根据处置步骤ID数组，查询处置步骤列表
     *
     * @param stepIdArray
     * @return
     * @throws ServiceException
     */
    private List<EmerStep> queryByIdArray(String[] stepIdArray) throws ServiceException {
        if (stepIdArray == null || stepIdArray.length <= 0) {
            return null;
        }
        try {
            return this.getDao().findByIdArrayAndStatus(stepIdArray, "0");
        } catch (Exception e) {
            throw new ServiceException("根据处置步骤ID数组，查询处置步骤列表发生异常->" + e);
        }
    }

    /**
     * 根据工作组ID数组，查询处置步骤与工作组关联关系列表
     *
     * @param stepIdArray
     * @return
     * @throws ServiceException
     */
    private List<EmerStepGroup> queryEmerStepGroupByStepIdArray(String[] stepIdArray) throws ServiceException {
        if (stepIdArray == null || stepIdArray.length <= 0) {
            return null;
        }
        try {
            return this.emerStepGroupDao.findByStepIdArrayAndStatus(stepIdArray, "0");
        } catch (Exception e) {
            throw new ServiceException("根据处置步骤ID数组，查询处置步骤与工作组关联关系列表发生异常->" + e);
        }
    }

}
