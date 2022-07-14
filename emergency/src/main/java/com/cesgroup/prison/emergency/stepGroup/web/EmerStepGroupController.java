package com.cesgroup.prison.emergency.stepGroup.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.emergency.stepGroup.dao.EmerStepGroupDao;
import com.cesgroup.prison.emergency.stepGroup.entity.EmerStepGroup;
import com.cesgroup.prison.emergency.stepGroup.service.EmerStepGroupService;

@Controller
@RequestMapping(value = "/emergency/stepGroupManage")
public class EmerStepGroupController extends BaseEntityDaoServiceCRUDController<EmerStepGroup, String, EmerStepGroupDao, EmerStepGroupService> {

    /**
     * 应急处置步骤的关联梯队配置页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/toEdit")
    public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
        String preplanId = request.getParameter("preplanId");
        String stepId = request.getParameter("stepId");
        ModelAndView mv = new ModelAndView("emergency/stepGroup/edit");
        mv.addObject("preplanId", preplanId);
        mv.addObject("stepId", stepId);
        return mv;
    }

    /**
     * 新增或更新处置步骤的关联梯队
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    @Logger(action = "新增或更新", logger = "新增或更新处置步骤的关联梯队", model = "处置步骤的关联梯队管理")
    public AjaxMessage saveOrUpdate(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            String stepId = request.getParameter("stepId");
            String groupIds = request.getParameter("groupIds");
            this.getService().saveOrUpdate(stepId, groupIds);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "处置步骤的关联梯队操作失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "处置步骤的关联梯队操作失败：" + e.getMessage();
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
     * 根据预案编号、处置步骤编号，查询已关联的工作组
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryCheckedGroup")
    @ResponseBody
    @Logger(action = "查询", logger = "查询已关联的工作组", model = "处置步骤关联工作组")
    public AjaxMessage queryCheckedGroup(HttpServletRequest request, HttpServletResponse response) {
        String preplanId = request.getParameter("preplanId");
        String stepId = request.getParameter("stepId");
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            result = this.getService().queryCheckedGroupByPreplanIdAndStepId(preplanId, stepId);
            ajaxMsg.setObj(result);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "查询处置步骤已关联的梯队失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "查询处置步骤已关联的梯队失败：" + e.getMessage();
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
     * 根据预案编号、处置步骤编号，查询待关联的工作组
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryUncheckedGroup")
    @ResponseBody
    @Logger(action = "查询", logger = "查询待关联的工作组", model = "处置步骤关联工作组")
    public AjaxMessage queryUncheckedGroup(HttpServletRequest request, HttpServletResponse response) {
        String preplanId = request.getParameter("preplanId");
        String stepId = request.getParameter("stepId");
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            result = this.getService().queryUncheckedGroupByPreplanIdAndStepId(preplanId, stepId);
            ajaxMsg.setObj(result);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "查询处置步骤待关联的梯队失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "查询处置步骤待关联的梯队失败：" + e.getMessage();
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
