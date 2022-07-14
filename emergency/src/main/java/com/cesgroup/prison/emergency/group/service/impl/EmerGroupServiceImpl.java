package com.cesgroup.prison.emergency.group.service.impl;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.TreeDto;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.group.dao.EmerGroupDao;
import com.cesgroup.prison.emergency.group.entity.EmerGroup;
import com.cesgroup.prison.emergency.group.service.EmerGroupService;
import com.cesgroup.prison.emergency.groupMember.dao.EmerGroupMemberDao;
import com.cesgroup.prison.emergency.groupMember.entity.EmerGroupMember;
import com.cesgroup.prison.emergency.member.entity.EmerMember;
import com.cesgroup.prison.emergency.member.service.EmerMemberService;
import com.cesgroup.prison.emergency.preplan.dao.EmerPreplanDao;
import com.cesgroup.prison.emergency.preplan.entity.EmerPreplan;
import com.cesgroup.prison.emergency.stepGroup.dao.EmerStepGroupDao;
import com.cesgroup.prison.emergency.stepGroup.entity.EmerStepGroup;
import com.cesgroup.prison.utils.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmerGroupServiceImpl extends BaseDaoService<EmerGroup, String, EmerGroupDao> implements EmerGroupService {
    /**
     * 应急预案DAO
     */
    @Autowired
    private EmerPreplanDao emerPreplanDao;
    /**
     * 处置步骤与工作组关联关系DAO
     */
    @Autowired
    private EmerStepGroupDao emerStepGroupDao;
    /**
     * 工作组与成员关联关系DAO
     */
    @Autowired
    private EmerGroupMemberDao emerGroupMemberDao;
    /**
     * 应急人员Service
     */
    @Autowired
    private EmerMemberService emerMemberService;

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
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException {
        try {
            Page<Map<String, Object>> page = this.getDao().findWithPage(paramMap, pageable);
            return page;
        } catch (Exception e) {
            throw new ServiceException("分页查询应急预案工作组发生异常，原因：" + e);
        }
    }

    @Override
    public EmerGroup queryById(String id) throws ServiceException {
        try {
            return this.getDao().findById(id);
        } catch (Exception e) {
            throw new ServiceException("根据主键ID，查询应急预案工作组详细数据发生错误，原因->" + e);
        }
    }

    @Override
    public void saveOrUpdate(EmerGroup emerGroup) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }
        if (emerGroup == null) {
            throw new ServiceException("工作组实体类为空");
        }
        emerGroup.setCusNumber(user.getCusNumber());

        if (emerGroup.getName() == null || "".equals(emerGroup.getName())) {
            throw new ServiceException("工作组名称为空");
        }

        if (emerGroup.getSource() == null || "".equals(emerGroup.getSource())) {
            throw new ServiceException("数据来源为空");
        }
        // 判断工作组名称是否重复
        try {
            if (this.isNameExists(emerGroup)) {
                throw new BusinessLayerException("工作组名称[" + emerGroup.getName() + "]在应急预案中已存在");
            }
        } catch (BusinessLayerException e) {
            throw new ServiceException(e.getMessage());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

        // 声明应急预案工作组
        EmerGroup emerGroupOperate = null;
        if (emerGroup.getId() != null && !"".equals(emerGroup.getId())) {
            try {
                emerGroupOperate = this.getDao().findById(emerGroup.getId());
            } catch (Exception e) {
                throw new ServiceException("根据应急工作组ID查询应急工作组数据失败");
            }
        }

        if (emerGroupOperate == null) {
            emerGroupOperate = new EmerGroup();
            try {
                emerGroupOperate.setId(CommonUtil.createUUID());
                emerGroupOperate.setCusNumber(emerGroup.getCusNumber());
                emerGroupOperate.setName(emerGroup.getName());
                emerGroupOperate.setPreplanId(emerGroup.getPreplanId());
                emerGroupOperate.setNotice(emerGroup.getNotice());
                emerGroupOperate.setGetMemberWay("2");
                emerGroupOperate.setSource(emerGroup.getSource());
                emerGroupOperate.setStatus("0");
                Integer showOrder = this.getDao().findMaxShowOrderByCusNumberAndPreplanId(emerGroup.getCusNumber(), emerGroup.getPreplanId());
                emerGroupOperate.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
                emerGroupOperate.setCreateUserId(user.getUserId());
                emerGroupOperate.setCreateTime(new Date());
                emerGroupOperate.setUpdateUserId(user.getUserId());
                emerGroupOperate.setUpdateTime(new Date());
                this.getDao().insert(emerGroupOperate);
            } catch (Exception e) {
                throw new ServiceException("新增应急工作组失败，原因：" + e);
            }
        } else {
            try {
                emerGroupOperate.setName(emerGroup.getName());
                emerGroupOperate.setNotice(emerGroup.getNotice());
                emerGroupOperate.setGetMemberWay("2");
                emerGroupOperate.setSource(emerGroup.getSource());
                emerGroupOperate.setStatus("0");
                emerGroupOperate.setUpdateUserId(user.getUserId());
                emerGroupOperate.setUpdateTime(new Date());
                this.getDao().update(emerGroupOperate);
            } catch (Exception e) {
                throw new ServiceException("更新应急工作组失败，原因：" + e);
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

        //1 统计工作组ID数组中，不可被删除的工作组
        List<EmerGroup> canNotDelGroupList = this.queryExistsEmerStepGroupByIdArray(idArr);
        if (canNotDelGroupList != null && canNotDelGroupList.size() > 0) {
            String canNotDelGroupNames = "";
            for (EmerGroup canNotDelGroup : canNotDelGroupList) {
                canNotDelGroupNames += canNotDelGroup.getName() + ",";
            }
            if (canNotDelGroupNames != null && !"".equals(canNotDelGroupNames)) {
                canNotDelGroupNames = canNotDelGroupNames.substring(0, canNotDelGroupNames.lastIndexOf(","));
                throw new ServiceException("工作组[" + canNotDelGroupNames + "]已关联了处置步骤，不可删除！");
            }
        }

        //2 删除工作组、工作组与用户关联关系、处置步骤与工作组关联关系
        List<EmerGroup> emerGroupList = this.queryByIdArray(idArr);
        List<EmerGroupMember> emerGroupMemberList = this.queryEmerGroupMemberByGroupIdArray(idArr);
        List<EmerStepGroup> emerStepGroupList = this.queryEmerStepGroupByGroupIdArray(idArr);

        if(emerGroupList != null && emerGroupList.size() > 0) {
            for(EmerGroup emerGroup : emerGroupList) {
                try {
                    this.getDao().deleteByEntity(emerGroup);
                } catch (Exception e) {
                    throw new ServiceException("删除工作组失败，原因：" + e);
                }
            }
        }
        if(emerGroupMemberList != null && emerGroupMemberList.size() > 0) {
            for(EmerGroupMember emerGroupMember : emerGroupMemberList) {
                try {
                    this.emerGroupMemberDao.deleteByEntity(emerGroupMember);
                } catch (Exception e) {
                    throw new ServiceException("删除工作组与成员关联关系数据失败，原因：" + e);
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
    public List<EmerGroup> queryDetailListByPreplanIdAndStepId(String preplanId, String stepId) throws ServiceException {
        if(preplanId == null || "".equals(preplanId) || stepId == null || "".equals(stepId)) {
            return null;
        }
        //1 应急工作组查询
        List<EmerGroup> emerGroupList = null;
        try {
            emerGroupList = this.getDao().findExistsEmerStepGroupByPreplanIdAndStepId(preplanId, stepId);
        } catch (Exception e) {
            throw new ServiceException("根据应急预案ID[" + preplanId + "]、处置步骤ID[" + stepId + "]查询预案关联的应急工作组发生异常");
        }
        if(emerGroupList == null) {
            return null;
        }
        //2 遍历应急处置步骤，查询其关联的工作组信息
        for(EmerGroup emerGroup : emerGroupList) {
            List<EmerMember> emerMemberList = this.emerMemberService.queryListByGroupId(emerGroup.getId());
            if(emerMemberList != null && emerMemberList.size() > 0) {
                emerGroup.setEmerMemberList(emerMemberList);
            }
        }
        return emerGroupList;
    }

    /**
     * 封装应急预案树形结构数据
     *
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
     * 判断工作组名称是否重复
     *
     * @param emerGroup
     * @return
     * @throws BusinessLayerException
     */
    private boolean isNameExists(EmerGroup emerGroup) throws ServiceException {
        if (emerGroup == null) {
            throw new ServiceException("判断工作组名称是否重复失败，工作组实体类为空");
        }
        if (emerGroup.getName() == null || "".equals(emerGroup.getName())) {
            throw new ServiceException("判断工作组名称是否重复失败，工作组名称为空");
        }
        try {
            List<EmerGroup> emerGroupList = this.queryByCusNumberAndPreplanIdAndName(emerGroup.getCusNumber(), emerGroup.getPreplanId(), emerGroup.getName());
            if (emerGroupList != null && emerGroupList.size() > 0) {
                if (emerGroup.getId() == null || "".equals(emerGroup.getId())) {
                    return true;
                } else {
                    for (EmerGroup tempZhddCommand : emerGroupList) {
                        if (!tempZhddCommand.getId().equals(emerGroup.getId())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (ServiceException e) {
            throw new ServiceException("判断应急工作组名称是否重复失败，" + e.getMessage());
        } catch (Exception e) {
            throw new ServiceException("判断应急工作组名称是否重复失败，" + e.getMessage());
        }
    }

    /**
     * 根据单位编号、工作组名称、预案编号，查询有效应急工作组
     *
     * @param cusNumber
     * @param preplanId
     * @param name
     * @return
     * @throws BusinessLayerException
     */
    private List<EmerGroup> queryByCusNumberAndPreplanIdAndName(String cusNumber, String preplanId, String name) throws ServiceException {
        try {
            List<EmerGroup> zhddCommandList = this.getDao().findByCusNumberAndPreplanIdAndNameAndStatus(cusNumber, preplanId, name, "0");
            return zhddCommandList;
        } catch (Exception e) {
            throw new ServiceException("根据单位编号、工作组名称、预案编号，查询有效应急工作组失败->" + e);
        }
    }

    /**
     * 根据工作组ID数组，查询其中有关联处置方法的工作组列表
     *
     * @param groupIdArray
     * @return
     * @throws ServiceException
     */
    private List<EmerGroup> queryExistsEmerStepGroupByIdArray(String[] groupIdArray) throws ServiceException {
        if (groupIdArray == null || groupIdArray.length <= 0) {
            return null;
        }
        try {
            return this.getDao().findExistsEmerStepGroupByIdArrayAndStatus(groupIdArray, "0");
        } catch (Exception e) {
            throw new ServiceException("根据工作组ID数组，查询其中有关联处置方法的工作组列表发生异常->" + e);
        }
    }

    /**
     * 根据工作组ID数组，查询工作组列表
     *
     * @param groupIdArray
     * @return
     * @throws ServiceException
     */
    private List<EmerGroup> queryByIdArray(String[] groupIdArray) throws ServiceException {
        if (groupIdArray == null || groupIdArray.length <= 0) {
            return null;
        }
        try {
            return this.getDao().findByIdArrayAndStatus(groupIdArray, "0");
        } catch (Exception e) {
            throw new ServiceException("根据工作组ID数组，查询其中有关联处置方法的工作组列表发生异常->" + e);
        }
    }

    /**
     * 根据工作组ID数组，查询处置步骤与工作组关联关系列表
     *
     * @param groupIdArray
     * @return
     * @throws ServiceException
     */
    private List<EmerStepGroup> queryEmerStepGroupByGroupIdArray(String[] groupIdArray) throws ServiceException {
        if (groupIdArray == null || groupIdArray.length <= 0) {
            return null;
        }
        try {
            return this.emerStepGroupDao.findByGroupIdArrayAndStatus(groupIdArray, "0");
        } catch (Exception e) {
            throw new ServiceException("根据工作组ID数组，查询处置步骤与工作组关联关系列表发生异常->" + e);
        }
    }

    /**
     * 根据工作组ID数组，查询工作组与成员关联关系列表
     *
     * @param groupIdArray
     * @return
     * @throws ServiceException
     */
    private List<EmerGroupMember> queryEmerGroupMemberByGroupIdArray(String[] groupIdArray) throws ServiceException {
        if (groupIdArray == null || groupIdArray.length <= 0) {
            return null;
        }
        try {
            return this.emerGroupMemberDao.findByGroupIdArrayAndStatus(groupIdArray, "0");
        } catch (Exception e) {
            throw new ServiceException("根据工作组ID数组，查询工作组与成员关联关系列表发生异常->" + e);
        }
    }
}