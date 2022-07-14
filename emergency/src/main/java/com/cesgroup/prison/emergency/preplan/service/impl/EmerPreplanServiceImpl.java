package com.cesgroup.prison.emergency.preplan.service.impl;

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
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.group.dao.EmerGroupDao;
import com.cesgroup.prison.emergency.group.entity.EmerGroup;
import com.cesgroup.prison.emergency.groupMember.dao.EmerGroupMemberDao;
import com.cesgroup.prison.emergency.groupMember.entity.EmerGroupMember;
import com.cesgroup.prison.emergency.preplan.dao.EmerPreplanDao;
import com.cesgroup.prison.emergency.preplan.entity.EmerPreplan;
import com.cesgroup.prison.emergency.preplan.service.EmerPreplanService;
import com.cesgroup.prison.emergency.step.dao.EmerStepDao;
import com.cesgroup.prison.emergency.step.entity.EmerStep;
import com.cesgroup.prison.emergency.step.service.EmerStepService;
import com.cesgroup.prison.emergency.stepGroup.dao.EmerStepGroupDao;
import com.cesgroup.prison.emergency.stepGroup.entity.EmerStepGroup;
import com.cesgroup.prison.utils.CommonUtil;

/**
 * 应急预案业务操作类
 */
@Service
@Transactional
public class EmerPreplanServiceImpl extends BaseDaoService<EmerPreplan, String, EmerPreplanDao> implements EmerPreplanService {
    /**
     * 处置步骤DAO
     */
    @Autowired
    private EmerStepDao emerStepDao;
    /**
     * 处置步骤与工作组DAO
     */
    @Autowired
    private EmerStepGroupDao emerStepGroupDao;
    /**
     * 工作组DAO
     */
    @Autowired
    private EmerGroupDao emerGroupDao;
    /**
     * 工作组与成员DAO
     */
    @Autowired
    private EmerGroupMemberDao emerGroupMemberDao;
    /**
     * 应急处置步骤Service
     */
    @Autowired
    private EmerStepService emerStepService;

    @Override
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException {
        try {
            Page<Map<String, Object>> page = this.getDao().findWithPage(paramMap, pageable);
            return page;
        } catch (Exception e) {
            throw new ServiceException("分页查询应急预案信息发生异常，原因：" + e);
        }
    }

    @Override
    public EmerPreplan queryById(String id) throws ServiceException {
        try {
            return this.getDao().findById(id);
        } catch (Exception e) {
            throw new ServiceException("根据主键ID，查询应急预案详细数据，原因：" + e);
        }
    }

    @Override
    public void saveOrUpdate(EmerPreplan emerPreplan) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }
        if(emerPreplan == null) {
            throw new ServiceException("应急预案实体类为空");
        }
        emerPreplan.setCusNumber(user.getCusNumber());

        if(emerPreplan.getName() == null || "".equals(emerPreplan.getName())) {
            throw new ServiceException("应急预案名称为空");
        }
        // 判断指令名称是否重复
        try {
            if (this.isNameExists(emerPreplan)) {
                throw new ServiceException("指令名称在数据库表中已存在");
            }
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        } catch (Exception e) {
            throw new ServiceException(e.getMessage());
        }

        // 声明指令操作实类体
        EmerPreplan emerPreplanOperate = null;
        if(emerPreplan.getId() != null && !"".equals(emerPreplan.getId())) {
            try {
                emerPreplanOperate = this.getDao().findById(emerPreplan.getId());
            } catch (Exception e) {
                throw new ServiceException("根据应急预案ID查询应急预案失败");
            }
        }

        if(emerPreplanOperate == null) {
            emerPreplanOperate = new EmerPreplan();
            try {
                emerPreplanOperate.setId(CommonUtil.createUUID());
                emerPreplanOperate.setCusNumber(emerPreplan.getCusNumber());
                emerPreplanOperate.setName(emerPreplan.getName());
                emerPreplanOperate.setSource(emerPreplan.getSource());
                emerPreplanOperate.setRemarks(emerPreplan.getRemarks());
                emerPreplanOperate.setStatus("0");
                Integer showOrder = this.getDao().findMaxShowOrderByCusNumber(emerPreplan.getCusNumber());
                emerPreplanOperate.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
                emerPreplanOperate.setCreateUserId(user.getUserId());
                emerPreplanOperate.setCreateTime(new Date());
                emerPreplanOperate.setUpdateUserId(user.getUserId());
                emerPreplanOperate.setUpdateTime(new Date());
                this.getDao().insert(emerPreplanOperate);
            } catch (Exception e) {
                throw new ServiceException("新增指令失败，原因：" + e);
            }
        } else {
            try {
                emerPreplanOperate.setName(emerPreplan.getName());
                emerPreplanOperate.setSource(emerPreplan.getSource());
                emerPreplanOperate.setRemarks(emerPreplan.getRemarks());
                emerPreplanOperate.setStatus("0");
                emerPreplanOperate.setUpdateUserId(user.getUserId());
                emerPreplanOperate.setUpdateTime(new Date());
                this.getDao().update(emerPreplanOperate);
            } catch (Exception e) {
                throw new ServiceException("更新应急预案失败，原因：" + e);
            }
        }
    }

    @Override
    public void deleteByIds(String ids) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }

        String[] idArray = ids != null && !"".equals(ids) ? ids.split(",") : null;
        if(idArray == null) {
            return;
        }

        // 删除预案、预案处置步骤、预案工作组，以及关联关系数据
        List<EmerPreplan> emerPreplanList = this.queryByIdArray(idArray);
        List<EmerStep> emerStepList = this.queryEmerStepByPreplanIdArray(idArray);
        List<EmerStepGroup> emerStepGroupList = this.queryEmerStepGroupByParplanIdArray(idArray);
        List<EmerGroup> emerGroupList = this.queryEmerGroupByParplanIdArray(idArray);
        List<EmerGroupMember> emerGroupMemberList = this.queryEmerGroupMemberByParplanIdArray(idArray);

        if(emerPreplanList != null && emerPreplanList.size() > 0) {
            for(EmerPreplan emerPreplan : emerPreplanList) {
                try {
                    this.getDao().deleteByEntity(emerPreplan);
                } catch (Exception e) {
                    throw new ServiceException("删除应急预案失败，原因：" + e);
                }
            }
        }
        if(emerStepList != null && emerStepList.size() > 0) {
            for(EmerStep emerStep : emerStepList) {
                try {
                    this.emerStepDao.deleteByEntity(emerStep);
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
        if(emerGroupList != null && emerGroupList.size() > 0) {
            for(EmerGroup emerGroup : emerGroupList) {
                try {
                    this.emerGroupDao.deleteByEntity(emerGroup);
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
                    throw new ServiceException("删除工作组与成员关联关系失败，原因：" + e);
                }
            }
        }
    }

    @Override
    public EmerPreplan queryDetailById(String id) throws ServiceException {
        if(id == null || "".equals(id)) {
            return null;
        }
        //1 应急预案
        EmerPreplan emerPreplan = null;
        try {
            emerPreplan = this.getDao().findById(id);
        } catch (Exception e) {
            throw new ServiceException("根据应急预案ID[" + id + "]查询应急预案发生异常");
        }
        if(emerPreplan == null) {
            throw new ServiceException("根据应急预案ID[" + id + "]查询应急预案为空");
        }

        //2 处置步骤
        List<EmerStep> emerStepList = this.emerStepService.queryDetailListByPreplanId(id);
        if(emerStepList != null && emerStepList.size() > 0) {
            emerPreplan.setEmerStepList(emerStepList);
        }
        return emerPreplan;
    }

    @Override
    public List<EmerPreplan> queryByCusNumberAndStatus(String cusNumber, String status) throws ServiceException {
        try {
            return this.getDao().findByCusNumberAndStatus(cusNumber, status);
        } catch (Exception e) {
            throw new ServiceException("根据单位编号、数据状态，查询应急预案发生异常，原因：" + e);
        }
    }

    /**
     * 判断应急预案名称是否重复
     * @param emerPreplan
     * @return
     * @throws ServiceException
     */
    private boolean isNameExists(EmerPreplan emerPreplan) throws ServiceException {
        if(emerPreplan == null) {
            throw new ServiceException("判断应急预案名称是否重复失败，应急预案实体类为空");
        }
        if(emerPreplan.getName() == null || "".equals(emerPreplan.getName())) {
            throw new ServiceException("判断预案名称是否重复失败，预案名称为空");
        }
        try {
            List<EmerPreplan> emerPreplanList = this.queryByCusNumberAndName(emerPreplan.getCusNumber(), emerPreplan.getName());
            if(emerPreplanList != null && emerPreplanList.size() > 0) {
                if(emerPreplan.getId() == null || "".equals(emerPreplan.getId())) {
                    return true;
                } else {
                    for(EmerPreplan tempZhddCommand : emerPreplanList) {
                        if(!tempZhddCommand.getId().equals(emerPreplan.getId())) {
                            return true;
                        }
                    }
                }
            }
            return false;
        } catch (ServiceException e) {
            throw new ServiceException("判断应急预案名称是否重复失败，" + e.getMessage());
        } catch (Exception e) {
            throw new ServiceException("判断应急预案名称是否重复失败，" + e.getMessage());
        }
    }

    /**
     * 根据单位编号、预案名称，查询有效应急预案
     * @param cusNumber
     * @param name
     * @return
     * @throws ServiceException
     */
    private List<EmerPreplan> queryByCusNumberAndName(String cusNumber, String name) throws ServiceException {
        try {
            return this.getDao().findByCusNumberAndNameAndStatus(cusNumber, name, "0");
        } catch (Exception e) {
            throw new ServiceException("根据单位编号、预案名称，查询有效应急预案失败->" + e);
        }
    }

    /**
     * 根据预案ID数组，查询应急预案列表
     * @param idArray
     * @return
     * @throws ServiceException
     */
    private List<EmerPreplan> queryByIdArray(String[] idArray) throws ServiceException {
        try {
            return this.getDao().findByIdArrayAndStatus(idArray, "0");
        } catch (Exception e) {
            throw new ServiceException("根据预案ID数组，查询应急预案列表失败->" + e);
        }
    }

    /**
     * 根据预案ID数组，查询应急处置步骤列表
     * @param preplanIdArray
     * @return
     * @throws ServiceException
     */
    private List<EmerStep> queryEmerStepByPreplanIdArray(String[] preplanIdArray) throws ServiceException {
        try {
            return this.emerStepDao.findByPreplanIdArrayAndStatus(preplanIdArray, "0");
        } catch (Exception e) {
            throw new ServiceException("根据预案ID数组，查询应急处置步骤列表失败->" + e);
        }
    }

    /**
     * 根据预案ID数组，查询处置步骤与工作关联关系列表
     * @param preplanIdArray
     * @return
     * @throws ServiceException
     */
    private List<EmerStepGroup> queryEmerStepGroupByParplanIdArray(String[] preplanIdArray) throws ServiceException {
        try {
            return this.emerStepGroupDao.findExistsEmerStepByPreplanIdArrayAndStatus(preplanIdArray, "0");
        } catch (Exception e) {
            throw new ServiceException("根据预案ID数组，查询处置步骤与工作关联关系列表失败->" + e);
        }
    }

    /**
     * 根据预案ID数组，查询应急工作组列表
     * @param preplanIdArray
     * @return
     * @throws ServiceException
     */
    private List<EmerGroup> queryEmerGroupByParplanIdArray(String[] preplanIdArray) throws ServiceException {
        try {
            return this.emerGroupDao.findByPreplanIdArrayAndStatus(preplanIdArray, "0");
        } catch (Exception e) {
            throw new ServiceException("根据预案ID数组，查询应急工作组列表失败->" + e);
        }
    }

    /**
     * 根据预案ID数组，查询工作组与成员关联关系列表
     * @param preplanIdArray
     * @return
     * @throws ServiceException
     */
    private List<EmerGroupMember> queryEmerGroupMemberByParplanIdArray(String[] preplanIdArray) throws ServiceException {
        try {
            return this.emerGroupMemberDao.findExistsEmerGroupByPreplanIdArrayAndStatus(preplanIdArray, "0");
        } catch (Exception e) {
            throw new ServiceException("根据预案ID数组，查询工作组与成员关联关系列表失败->" + e);
        }
    }
}
