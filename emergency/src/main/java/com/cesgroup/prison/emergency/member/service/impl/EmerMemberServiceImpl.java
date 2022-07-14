package com.cesgroup.prison.emergency.member.service.impl;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.member.dao.EmerMemberDao;
import com.cesgroup.prison.emergency.member.entity.EmerMember;
import com.cesgroup.prison.emergency.member.service.EmerMemberService;
import com.cesgroup.prison.emergency.preplan.dao.EmerPreplanDao;
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
public class EmerMemberServiceImpl extends BaseDaoService<EmerMember, String, EmerMemberDao> implements EmerMemberService {
    /**
     * 应急预案DAO
     */
    @Autowired
    private EmerPreplanDao emerPreplanDao;

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
    public EmerMember queryById(String id) throws ServiceException {
        try {
            return this.getDao().findById(id);
        } catch (Exception e) {
            throw new ServiceException("根据主键ID，查询应急预案工作组详细数据发生错误，原因->" + e);
        }
    }

    @Override
    public EmerMember saveOrUpdate(EmerMember emerMember) throws ServiceException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new ServiceException("用户未登录");
        }
        if(emerMember == null) {
            throw new ServiceException("应急人员实体类为空");
        }
        if(emerMember.getCusNumber() == null || "".equals(emerMember.getCusNumber())) {
            emerMember.setCusNumber(user.getCusNumber());
        }

        if(emerMember.getName() == null || "".equals(emerMember.getName())) {
            throw new ServiceException("应急人员名称为空");
        }

        if(emerMember.getCallNo() == null || "".equals(emerMember.getCallNo())) {
            throw new ServiceException("应急人员呼叫号码为空");
        }

        // 声明应急人员
        EmerMember emerMemberOperate = null;
        if(emerMember.getId() != null && !"".equals(emerMember.getId())) {
            try {
                emerMemberOperate = this.getDao().findById(emerMember.getId());
            } catch (Exception e) {
                throw new ServiceException("根据应急人员ID查询应急人员数据失败");
            }
        }
        // 根据单位编码、人员名称、呼叫号码，查询应急人员数据是否在数据库中已存在
        if(emerMemberOperate == null) {
            List<EmerMember> emerMemberList = null;
            try {
                emerMemberList = this.getDao().findByCusNumberAndNameAndCallNoAndStatus(emerMember.getCusNumber(), emerMember.getName(),emerMember.getCallNo(), "0");
            } catch (Exception e) {
                throw new ServiceException("根据单位编码、人员名称、呼叫号码，查询应急人员数据失败");
            }
            if(emerMemberList != null && emerMemberList.size() > 0) {
                emerMemberOperate = emerMemberList.get(0);
            }
        }

        if(emerMemberOperate == null) {
            emerMemberOperate = new EmerMember();
            try {
                emerMemberOperate.setId(CommonUtil.createUUID());
                emerMemberOperate.setCusNumber(emerMember.getCusNumber());
                emerMemberOperate.setName(emerMember.getName());
                emerMemberOperate.setDeptName(emerMember.getDeptName());
                emerMemberOperate.setUnitName(emerMember.getUnitName());
                emerMemberOperate.setCallNo(emerMember.getCallNo());
                emerMemberOperate.setSource(emerMember.getSource());
                emerMemberOperate.setStatus("0");
                Integer showOrder = this.getDao().findMaxShowOrderByCusNumber(emerMember.getCusNumber());
                emerMemberOperate.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
                emerMemberOperate.setCreateUserId(user.getUserId());
                emerMemberOperate.setCreateTime(new Date());
                emerMemberOperate.setUpdateUserId(user.getUserId());
                emerMemberOperate.setUpdateTime(new Date());
                this.getDao().insertSelective(emerMemberOperate);
            } catch (Exception e) {
                throw new ServiceException("新增应急人员失败，原因：" + e);
            }
        } else {
            try {
                emerMemberOperate.setName(emerMember.getName());
                emerMemberOperate.setDeptName(emerMember.getDeptName());
                emerMemberOperate.setUnitName(emerMember.getUnitName());
                emerMemberOperate.setCallNo(emerMember.getCallNo());
                emerMemberOperate.setSource(emerMember.getSource());
                emerMemberOperate.setStatus("0");
                emerMemberOperate.setUpdateUserId(user.getUserId());
                emerMemberOperate.setUpdateTime(new Date());
                this.getDao().updateSelective(emerMemberOperate);
            } catch (Exception e) {
                throw new ServiceException("更新应急人员失败，原因：" + e);
            }
        }
        return emerMemberOperate;
    }

    @Override
    public void deleteByIds(String ids) throws ServiceException {

    }

    @Override
    public List<EmerMember> queryListByGroupId(String groupId) throws ServiceException {
        if(groupId == null || "".equals(groupId)) {
            return null;
        }
        List<EmerMember> emerMemberList = null;
        try {
            emerMemberList = this.getDao().findExistsEmerGroupMemberByGroupIdAndStatus(groupId, "0");
        } catch (Exception e) {
            throw new ServiceException("根据工作组ID[" + groupId + "]查询关联成员发生异常");
        }
        return emerMemberList;
    }
}
