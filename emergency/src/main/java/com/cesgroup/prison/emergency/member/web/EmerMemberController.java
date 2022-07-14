package com.cesgroup.prison.emergency.member.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.emergency.member.dao.EmerMemberDao;
import com.cesgroup.prison.emergency.member.entity.EmerMember;
import com.cesgroup.prison.emergency.member.service.EmerMemberService;

@Controller
@RequestMapping(value = "/emergency/memberManage")
public class EmerMemberController extends BaseEntityDaoServiceCRUDController<EmerMember, String, EmerMemberDao, EmerMemberService> {
    /**
     * 根据主键ID，查询应急成员信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryById")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 ID 查询应急成员信息", model = "应急成员")
    public AjaxMessage queryById(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();

        try {
            String id = request.getParameter("id");
            if (id != null && !"".equals(id)) {
                result = this.getService().queryById(id);
                flag = true;
            } else {
                flag = false;
                result = "查询失败 ";
            }
        } catch (ServiceException e) {
            flag = false;
            result = "查询失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "查询失败：" + e.getMessage();
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
