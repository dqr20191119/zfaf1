package com.cesgroup.prison.emergency.groupMember.web;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.groupMember.dao.EmerGroupMemberDao;
import com.cesgroup.prison.emergency.groupMember.dto.EmerGroupMemberDto;
import com.cesgroup.prison.emergency.groupMember.entity.EmerGroupMember;
import com.cesgroup.prison.emergency.groupMember.service.EmerGroupMemberService;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "/emergency/groupMemberManage")
public class EmerGroupMemberController extends BaseEntityDaoServiceCRUDController<EmerGroupMember, String, EmerGroupMemberDao, EmerGroupMemberService> {

    /**
     * 应急工作组成员列表窗口
     *
     * @return
     */
    @RequestMapping("/openDialog")
    public ModelAndView openDialog(@RequestParam(value = "groupId", defaultValue = "", required = false) String groupId) {
        ModelAndView mv = new ModelAndView("emergency/groupMember/list");
        mv.addObject("groupId", groupId);
        return mv;
    }

    @RequestMapping(value = "/queryWithPage")
    @ResponseBody
    public Map<String, Object> queryWithPage(HttpServletRequest request, HttpServletResponse response) throws WebUIException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new WebUIException("用户未登录");
        }
        String groupId = request.getParameter("groupId");//应急工作组编号
        String callNo = request.getParameter("callNo");//呼叫号码
        String memberName = request.getParameter("memberName");//呼叫人员名称

        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("cusNumber", user.getCusNumber());
        if (groupId != null && !"".equals(groupId)) {
            paramMap.put("groupId", groupId);
        }
        if (memberName != null && !"".equals(memberName)) {
            paramMap.put("memberName", memberName);
        }
        if (callNo != null && !"".equals(callNo)) {
            paramMap.put("callNo", callNo);
        }
        try {
            Page<Map<String, Object>> pageInfo = (Page<Map<String, Object>>) this.getService().queryWithPage(paramMap, buildPageRequest());
            return DataUtils.pageToMap(pageInfo);
        } catch (ServiceException e) {
            throw new WebUIException(e.getMessage());
        } catch (Exception e) {
            throw new WebUIException(e.getMessage());
        }
    }

    /**
     * Description: 打开更新页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/toEdit")
    public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        String groupId = request.getParameter("groupId");
        String memberId = request.getParameter("memberId");

        ModelAndView mv = new ModelAndView("emergency/groupMember/edit");
        mv.addObject("id", id);
        mv.addObject("groupId", groupId);
        mv.addObject("memberId", memberId);
        return mv;
    }

    /**
     * 新增或更新工作组成员
     *
     * @param emerGroupMemberDto
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    @Logger(action = "新增或更新", logger = "新增或更新工作组成员", model = "工作组成员管理")
    public AjaxMessage saveOrUpdate(EmerGroupMemberDto emerGroupMemberDto) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            this.getService().saveOrUpdate(emerGroupMemberDto);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "工作组成员操作失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "工作组成员操作失败：" + e.getMessage();
        }
        if (flag) {
            ajaxMsg.setObj(result);
        } else {
            ajaxMsg.setMsg((String) result);
        }
        ajaxMsg.setSuccess(flag);

        return ajaxMsg;
    }

    /**
     * 删除指令信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteByIds")
    @ResponseBody
    @Logger(action = "删除", logger = "删除工作组成员", model = "工作组成员管理")
    public AjaxMessage deleteByIds(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            String ids = request.getParameter("ids");
            this.getService().deleteByIds(ids);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "删除工作组成员失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "删除工作组成员失败：" + e.getMessage();
        }
        if (flag) {
            ajaxMsg.setObj(result);
        } else {
            ajaxMsg.setMsg((String) result);
        }
        ajaxMsg.setSuccess(flag);

        return ajaxMsg;
    }

    /**
     * 根据工作组ID，查询工作组成员
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryGroupMemberByGroupId")
    @ResponseBody
    @Logger(action = "新增或更新", logger = "新增或更新工作组成员", model = "工作组成员管理")
    public AjaxMessage queryGroupMemberByGroupId(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            String groupId = request.getParameter("groupId");
            result = this.getService().queryGroupMemberListByGroupId(groupId);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "工作组成员操作失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "工作组成员操作失败：" + e.getMessage();
        }
        if (flag) {
            ajaxMsg.setObj(result);
        } else {
            ajaxMsg.setMsg((String) result);
        }
        ajaxMsg.setSuccess(flag);

        return ajaxMsg;
    }

}
