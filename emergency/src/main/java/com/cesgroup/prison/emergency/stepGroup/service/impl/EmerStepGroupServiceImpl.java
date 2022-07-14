package com.cesgroup.prison.emergency.stepGroup.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.group.dao.EmerGroupDao;
import com.cesgroup.prison.emergency.group.entity.EmerGroup;
import com.cesgroup.prison.emergency.step.dao.EmerStepDao;
import com.cesgroup.prison.emergency.step.entity.EmerStep;
import com.cesgroup.prison.emergency.stepGroup.dao.EmerStepGroupDao;
import com.cesgroup.prison.emergency.stepGroup.entity.EmerStepGroup;
import com.cesgroup.prison.emergency.stepGroup.service.EmerStepGroupService;
import com.cesgroup.prison.utils.CommonUtil;

@Service
@Transactional
public class EmerStepGroupServiceImpl extends BaseDaoService<EmerStepGroup, String, EmerStepGroupDao> implements EmerStepGroupService {
    /**
     * 应急预案工作组DAO
     */
    @Autowired
    private EmerGroupDao emerGroupDao;
    /**
     * 应急预案处置步骤DAO
     */
    @Autowired
    private EmerStepDao emerStepDao;

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
    public void saveOrUpdate(String stepId, String groupIds) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }
        if(stepId == null || "".equals(stepId)) {
            throw new ServiceException("处置步骤ID为空");
        }
        //1 根据处置步骤ID，查询处置步骤
        EmerStep emerStep = null;
        try {
            emerStep = this.emerStepDao.findById(stepId);
        } catch (Exception e) {
            throw new ServiceException("根据处置步骤ID查询处置步骤详情发生异常，程序中断");
        }
        if(emerStep == null) {
            throw new ServiceException("处置步骤为空");
        }

        //2 根据处置步骤ID，查询关联的工组列表
        List<EmerStepGroup> stepGroupList = null;
        try {
            stepGroupList = this.getDao().findByCusNumberAndStepIdAndStatus(emerStep.getCusNumber(), emerStep.getId(), null);
        } catch (Exception e) {
            throw new ServiceException("根据处置步骤ID查询处置步骤关联的工作组发生异常，程序中断");
        }

        //3 遍历stepGroupList，与页面传过来的工作组ids比对，分别列出待添加的、待更新的、待删除的
        List<String> groupIdToAdd = null, groupIdToUpd = null, groupIdToDel = null;
        if(stepGroupList == null || stepGroupList.size() <= 0) {
            groupIdToAdd = (groupIds == null || "".equals(groupIds)) ? null : Arrays.asList(groupIds.split(","));
        } else {
            List<String> groupIdList = (groupIds == null || "".equals(groupIds)) ? new ArrayList<String>() : Arrays.asList(groupIds.split(","));
            for(EmerStepGroup stepGroup : stepGroupList) {
                if(groupIdList.contains(stepGroup.getGroupId())) {
                    if(groupIdToUpd == null) {
                        groupIdToUpd = new ArrayList<String>();
                    }
                    groupIdToUpd.add(stepGroup.getGroupId());
                } else {
                    if(stepGroup.getStatus() != null && "0".equals(stepGroup.getStatus())) {
                        if (groupIdToDel == null) {
                            groupIdToDel = new ArrayList<String>();
                        }
                        groupIdToDel.add(stepGroup.getGroupId());
                    }
                }
            }
            groupIdToAdd = (groupIdList == null || groupIdList.size() <= 0) ? null : Util.listRemove(groupIdList, groupIdToUpd);
        }

        //4 遍历待添加的、待更新的、待删除的groupList，分别处理
        if(groupIdToAdd != null && groupIdToAdd.size() > 0) {
            for(String groupId : groupIdToAdd) {
                EmerStepGroup emerStepGroup = new EmerStepGroup();
                emerStepGroup.setId(CommonUtil.createUUID());
                emerStepGroup.setCusNumber(emerStep.getCusNumber());
                emerStepGroup.setStepId(emerStep.getId());
                emerStepGroup.setGroupId(groupId);
                emerStepGroup.setStatus("0");
                Integer showOrder = this.getDao().findMaxShowOrderByCusNumberAndStepId(emerStep.getCusNumber(), emerStep.getId());
                emerStepGroup.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
                emerStepGroup.setUpdateTime(new Date());
                emerStepGroup.setUpdateUserId(user.getUserId());
                try {
                    this.getDao().insert(emerStepGroup);
                } catch (Exception e) {
                    throw new ServiceException("新增处置步骤与工作组关联关系发生异常" + e);
                }
            }
        }
        if(groupIdToUpd != null && groupIdToUpd.size() > 0) {
            for(String groupId : groupIdToUpd) {
                List<EmerStepGroup> emerStepGroupList = null;
                try {
                    emerStepGroupList = this.getDao().findByCusNumberAndStepIdAndGroupIdAndStatus(emerStep.getCusNumber(), emerStep.getId(), groupId, null);
                } catch (Exception e) {
                    throw new ServiceException("根据单位编码、处置步骤编号、工作组编号、数据状态查询处置步骤与工作组管理数据发生异常->" + e);
                }
                if(emerStepGroupList == null || emerStepGroupList.size() <= 0) {
                    EmerStepGroup emerStepGroup = new EmerStepGroup();
                    emerStepGroup.setId(CommonUtil.createUUID());
                    emerStepGroup.setCusNumber(emerStep.getCusNumber());
                    emerStepGroup.setStepId(emerStep.getId());
                    emerStepGroup.setGroupId(groupId);
                    emerStepGroup.setStatus("0");
                    Integer showOrder = this.getDao().findMaxShowOrderByCusNumberAndStepId(emerStep.getCusNumber(), emerStep.getId());
                    emerStepGroup.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
                    emerStepGroup.setUpdateTime(new Date());
                    emerStepGroup.setUpdateUserId(user.getUserId());
                    try {
                        this.getDao().insert(emerStepGroup);
                    } catch (Exception e) {
                        throw new ServiceException("新增处置步骤与工作组关联关系发生异常" + e);
                    }
                } else {
                    for(int i=0; i<emerStepGroupList.size(); i++) {
                        EmerStepGroup emerStepGroup = emerStepGroupList.get(i);
                        if(i == 0) {
                            if(emerStepGroup.getStatus() == null || !"0".equals(emerStepGroup.getStatus())) {
                                Integer showOrder = this.getDao().findMaxShowOrderByCusNumberAndStepId(emerStep.getCusNumber(), emerStep.getId());
                                emerStepGroup.setStatus("0");
                                emerStepGroup.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
                            }
                            emerStepGroup.setUpdateTime(new Date());
                            emerStepGroup.setUpdateUserId(user.getUserId());
                            try {
                                this.getDao().update(emerStepGroup);
                            } catch (Exception e) {
                                throw new ServiceException("更新处置步骤与工作组关联关系状态为有效发生异常" + e);
                            }
                        } else {
                            try {
                                this.getDao().deleteByEntity(emerStepGroup);
                            } catch (Exception e) {
                                throw new ServiceException("删除处置步骤与工作组关联关系发生异常" + e);
                            }
                        }
                    }
                }
            }
        }
        if(groupIdToDel != null && groupIdToDel.size() > 0) {
            for(String groupId : groupIdToDel) {
                List<EmerStepGroup> emerStepGroupList = null;
                try {
                    emerStepGroupList = this.getDao().findByCusNumberAndStepIdAndGroupIdAndStatus(emerStep.getCusNumber(), emerStep.getId(), groupId, null);
                } catch (Exception e) {
                    throw new ServiceException("根据单位编码、处置步骤编号、工作组编号、数据状态查询处置步骤与工作组管理数据发生异常->" + e);
                }
                if(emerStepGroupList == null || emerStepGroupList.size() <= 0) {
                    continue;
                }
                for(int i=0; i<emerStepGroupList.size(); i++) {
                    EmerStepGroup emerStepGroup = emerStepGroupList.get(i);
                    if(i == 0) {
                        emerStepGroup.setStatus("1");
                        emerStepGroup.setUpdateTime(new Date());
                        emerStepGroup.setUpdateUserId(user.getUserId());
                        try {
                            this.getDao().update(emerStepGroup);
                        } catch (Exception e) {
                            throw new ServiceException("更新处置步骤与工作组关联关系状态为删除发生异常" + e);
                        }
                    } else {
                        try {
                            this.getDao().deleteByEntity(emerStepGroup);
                        } catch (Exception e) {
                            throw new ServiceException("删除处置步骤与工作组关联关系发生异常" + e);
                        }
                    }
                }
            }
        }
    }

    @Override
    public List<EmerGroup> queryCheckedGroupByPreplanIdAndStepId(String preplanId, String stepId) throws ServiceException {
        if(stepId == null || "".equals(stepId)) {
            return null;
        }
        try {
            return this.emerGroupDao.findExistsEmerStepGroupByPreplanIdAndStepId(preplanId, stepId);
        } catch (Exception e) {
            throw new ServiceException("根据预案ID、处置步骤ID，查询已关联的工作组发生异常-》" + e);
        }
    }

    @Override
    public List<EmerGroup> queryUncheckedGroupByPreplanIdAndStepId(String preplanId, String stepId) throws ServiceException {
        if(stepId == null || "".equals(stepId)) {
            return null;
        }
        try {
            return this.emerGroupDao.findNotExistsEmerStepGroupByPreplanIdAndStepId(preplanId, stepId);
        } catch (Exception e) {
            throw new ServiceException("根据预案ID、处置步骤ID，查询待关联的工作组发生异常-》" + e);
        }
    }
}
