package com.cesgroup.prison.emergency.groupMember.service.impl;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.group.dao.EmerGroupDao;
import com.cesgroup.prison.emergency.group.entity.EmerGroup;
import com.cesgroup.prison.emergency.groupMember.dao.EmerGroupMemberDao;
import com.cesgroup.prison.emergency.groupMember.dto.EmerGroupMemberDto;
import com.cesgroup.prison.emergency.groupMember.entity.EmerGroupMember;
import com.cesgroup.prison.emergency.groupMember.service.EmerGroupMemberService;
import com.cesgroup.prison.emergency.member.dao.EmerMemberDao;
import com.cesgroup.prison.emergency.member.entity.EmerMember;
import com.cesgroup.prison.emergency.member.service.EmerMemberService;
import com.cesgroup.prison.utils.CommonUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmerGroupMemberServiceImpl extends BaseDaoService<EmerGroupMember, String, EmerGroupMemberDao> implements EmerGroupMemberService {
    /**
     * 应急预案工作组DAO
     */
    @Autowired
    private EmerGroupDao emerGroupDao;
    /**
     * 应急预案工作组成员DAO
     */
    @Autowired
    private EmerMemberDao emerMemberDao;
    /**
     * 应急人员业务操作类
     */
    @Autowired
    private EmerMemberService emerMemberService;

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
    public void saveOrUpdate(EmerGroupMemberDto emerGroupMemberDto) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }
        emerGroupMemberDto.setCusNumber(user.getCusNumber());
        if(emerGroupMemberDto == null) {
            throw new ServiceException("工作组与人员数据传输对象为空");
        }
        if(emerGroupMemberDto.getGroupId() == null || "".equals(emerGroupMemberDto.getGroupId())) {
            throw new ServiceException("工作组ID为空");
        }
        if(emerGroupMemberDto.getMemberName() == null || "".equals(emerGroupMemberDto.getMemberName())) {
            throw new ServiceException("工作组成员名称为空");
        }
        if(emerGroupMemberDto.getCallNo() == null || "".equals(emerGroupMemberDto.getCallNo())) {
            throw new ServiceException("工作组成员呼叫号码为空");
        }
        if(emerGroupMemberDto.getSource() == null || "".equals(emerGroupMemberDto.getSource())) {
            throw new ServiceException("工作组成员数据来源为空");
        }

        //根据工作组id获取工作组数据
        EmerGroup emerGroupOperate = null;
        try {
            emerGroupOperate = this.emerGroupDao.findById(emerGroupMemberDto.getGroupId());
        } catch (Exception e) {
            throw new ServiceException("根据工作组ID，查询工作组失败");
        }
        if(emerGroupOperate == null || !"0".equals(emerGroupOperate.getStatus())) {
            throw new ServiceException("工作组已被删除，请刷新后重试");
        }
        emerGroupMemberDto.setGroupName(emerGroupOperate.getName());

        //新增或更新应急人员
        EmerMember emerMemberOperate = null;
        try {
            emerMemberOperate = this.saveOrUpdateEmerMember(emerGroupMemberDto);
        } catch (ServiceException e) {
            throw new ServiceException("新增或更新应急人员发生异常->" + e.getMessage());
        }

        if(emerMemberOperate == null || emerMemberOperate.getId() == null || "".equals(emerMemberOperate.getId())) {
            throw new ServiceException("应急人员数据异常");
        }

        //新增或更新工作组与应急人员关系
        emerGroupMemberDto.setMemberId(emerMemberOperate.getId());

        //判断工作组中是否已有待添加的应急人员
        EmerGroupMember emerGroupMemberOperate = null;
        if(emerGroupMemberDto.getId() != null && !"".equals(emerGroupMemberDto.getId())) {
            emerGroupMemberOperate = this.getDao().findById(emerGroupMemberDto.getId());
        }

        if(emerGroupMemberOperate == null) {
            List<EmerGroupMember> emerGroupMemberList = null;
            try {
                emerGroupMemberList = this.getDao().findByCusNumberAndGroupIdAndMemberIdAndStatus(user.getCusNumber(), emerGroupMemberDto.getGroupId(), emerGroupMemberDto.getMemberId(), "0");
            } catch (Exception e) {
                throw new ServiceException("根据单位编码、人员名称、呼叫号码，查询应急人员数据失败");
            }
            if(emerGroupMemberList != null && emerGroupMemberList.size() > 0) {
                emerGroupMemberOperate = emerGroupMemberList.get(0);
            }
        }

        if(emerGroupMemberOperate == null) {
            emerGroupMemberOperate = new EmerGroupMember();
            try {
                emerGroupMemberOperate.setId(CommonUtil.createUUID());
                emerGroupMemberOperate.setCusNumber(emerGroupMemberDto.getCusNumber());
                emerGroupMemberOperate.setGroupId(emerGroupMemberDto.getGroupId());
                emerGroupMemberOperate.setMemberId(emerGroupMemberDto.getMemberId());
                emerGroupMemberOperate.setSource(emerGroupMemberDto.getSource());
                emerGroupMemberOperate.setMemberRole(emerGroupMemberDto.getMemberRole());
                emerGroupMemberOperate.setMemberRoleTask(emerGroupMemberDto.getMemberRoleTask());
                emerGroupMemberOperate.setStatus("0");
                Integer showOrder = this.getDao().findMaxShowOrderByCusNumberAndGroupId(emerGroupMemberDto.getCusNumber(), emerGroupMemberDto.getGroupId());
                emerGroupMemberOperate.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
                emerGroupMemberOperate.setUpdateUserId(user.getUserId());
                emerGroupMemberOperate.setUpdateTime(new Date());
                this.getDao().insertSelective(emerGroupMemberOperate);
            } catch (Exception e) {
                throw new ServiceException("新增应急人员失败，原因：" + e);
            }
        } else {
            try {
                emerGroupMemberOperate.setSource(emerGroupMemberDto.getSource());
                emerGroupMemberOperate.setMemberRole(emerGroupMemberDto.getMemberRole());
                emerGroupMemberOperate.setMemberRoleTask(emerGroupMemberDto.getMemberRoleTask());
                emerGroupMemberOperate.setStatus("0");
                emerGroupMemberOperate.setUpdateUserId(user.getUserId());
                emerGroupMemberOperate.setUpdateTime(new Date());
                this.getDao().updateSelective(emerGroupMemberOperate);
            } catch (Exception e) {
                throw new ServiceException("更新应急人员失败，原因：" + e);
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
        if(idArray == null || idArray.length <= 0) {
            throw new ServiceException("请选择待删除的工作组成员");
        }

        for(String id : idArray) {
            EmerGroupMember emerGroupMemberOperate = this.getDao().findById(id);
            if(emerGroupMemberOperate == null) {
                continue;
            }
            // 判断工作组成员是否存在于别的工作组
            if(!this.isEmerMemberExistsInOtherGroup(emerGroupMemberOperate)) {
                EmerMember emerMember = null;
                try {
                    emerMember = this.emerMemberDao.findById(emerGroupMemberOperate.getMemberId());
                } catch (Exception e) {
                    throw new ServiceException("根据应急成员ID，查询应急成员发生异常");
                }
                if(emerMember != null) {
                    try {
                        this.emerMemberDao.deleteByEntity(emerMember);
                    } catch (Exception e) {
                        logger.error("根据应急成员ID，删除工作组成员发生异常", e);
                        throw new ServiceException("根据应急成员ID，删除工作组成员发生异常->" + e);
                    }
                }
            }
            try {
                this.getDao().deleteByEntity(emerGroupMemberOperate);
            } catch (Exception e) {
                logger.error("根据ID，删除工作组成员关联关系发生异常", e);
                throw new ServiceException("根据ID，删除工作组成员关联关系发生异常->" + e);
            }
        }
    }

    @Override
    public List<EmerGroupMemberDto> queryGroupMemberListByGroupId(String groupId) throws ServiceException {
        try {
            return this.getDao().findEmerGroupMemberDtoByGroupId(groupId);
        } catch (Exception e) {
            throw new ServiceException("根据工作组ID，查询工作组成员发生异常");
        }
    }

    /**
     * 新增或更新应急人员
     * @param emerGroupMemberDto
     * @return
     * @throws ServiceException
     */
    private EmerMember saveOrUpdateEmerMember(EmerGroupMemberDto emerGroupMemberDto) throws ServiceException {
        if(emerGroupMemberDto == null) {
            throw new ServiceException("工作组与人员数据传输对象为空");
        }
        if(emerGroupMemberDto.getMemberName() == null || "".equals(emerGroupMemberDto.getMemberName())) {
            throw new ServiceException("工作组成员名称为空");
        }
        if(emerGroupMemberDto.getCallNo() == null || "".equals(emerGroupMemberDto.getCallNo())) {
            throw new ServiceException("工作组成员呼叫号码为空");
        }
        if(emerGroupMemberDto.getSource() == null || "".equals(emerGroupMemberDto.getSource())) {
            throw new ServiceException("工作组成员数据来源为空");
        }
        EmerMember emerMember = new EmerMember();
        emerMember.setId(emerGroupMemberDto.getMemberId());
        emerMember.setCusNumber(emerGroupMemberDto.getUnitCode());
        emerMember.setJobNo(emerGroupMemberDto.getMemberJobNo());
        emerMember.setName(emerGroupMemberDto.getMemberName());
        emerMember.setDeptName(emerGroupMemberDto.getDeptName());
        emerMember.setUnitName(emerGroupMemberDto.getUnitName());
        emerMember.setCallNo(emerGroupMemberDto.getCallNo());
        emerMember.setSource(emerGroupMemberDto.getSource());

        try {
            return this.emerMemberService.saveOrUpdate(emerMember);
        } catch (Exception e) {
            throw new ServiceException("新增或更新应急人员发生异常->" + e);
        }
    }

    /**
     * 判断应急人员是否在其它工作组中存在
     *
     * @param emerGroupMember
     * @return
     * @throws ServiceException
     */
    private boolean isEmerMemberExistsInOtherGroup(EmerGroupMember emerGroupMember) throws ServiceException {
        if(emerGroupMember == null) {
            throw new ServiceException("应急预案与成员关联关系数据为null");
        }
        List<EmerGroupMember> emerGroupMemberList = this.queryByMemberId(emerGroupMember.getMemberId());
        if(emerGroupMemberList == null || emerGroupMemberList.size() <= 0) {
            return false;
        }
        for(EmerGroupMember tempEmerGroupMember : emerGroupMemberList) {
            if(tempEmerGroupMember.getGroupId() != null && !tempEmerGroupMember.getGroupId().equals(emerGroupMember.getGroupId())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 根据成员编号，查询工作组与成员关联关系数据
     * @param memberId
     * @return
     * @throws ServiceException
     */
    private List<EmerGroupMember> queryByMemberId(String memberId) throws ServiceException {
        List<EmerGroupMember> emerGroupMemberList = null;
        try {
            emerGroupMemberList = this.getDao().findByMemberIdAndStatus(memberId, "0");
        } catch (Exception e) {
            throw new ServiceException("根据应急人员ID，查询工作组与成员关联关系数据发生异常->" + e);
        }
        return emerGroupMemberList;
    }
}
