package com.cesgroup.prison.emergency.handle.recordStep.web;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.emergency.handle.recordStep.dao.EmerHandleRecordStepDao;
import com.cesgroup.prison.emergency.handle.recordStep.entity.EmerHandleRecordStep;
import com.cesgroup.prison.emergency.handle.recordStep.service.EmerHandleRecordStepService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/emergency/handle/recordStepManage")
public class EmerHandleRecordStepController extends BaseEntityDaoServiceCRUDController<EmerHandleRecordStep, String, EmerHandleRecordStepDao, EmerHandleRecordStepService> {

    /**
     * 保存或更新应急处置记录的处置步骤信息
     *
     * @param emerHandleRecordStep
     * @return
     */
    @RequestMapping(value = "/updateHandleContent")
    @ResponseBody
    @Logger(action = "更新", logger = "更新处置内容", model = "应急处置记录的处置步骤管理")
    public AjaxMessage updateHandleContent(EmerHandleRecordStep emerHandleRecordStep) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            this.getService().updateHandleContent(emerHandleRecordStep);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "更新应急处置记录的处置步骤内容操作失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "更新应急处置记录的处置步骤内容操作失败：" + e.getMessage();
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
     * 根据主键ID，查询应急处置步骤信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryByRecordId")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 应急处置记录ID 查询应急处置步骤信息", model = "应急处置记录的处置流程步骤管理")
    public AjaxMessage queryByRecordId(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();

        try {
            String recordId = request.getParameter("recordId");
            if (recordId != null && !"".equals(recordId)) {
                result = this.getService().queryByRecordId(recordId);
                flag = true;
            } else {
                flag = false;
                result = "查询失败：应急预案编号为空，请确认后重试。";
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

    /**
     * 根据主键ID，查询应急处置步骤信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryById")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 应急处置记录的处置步骤的ID 查询应急处置步骤信息", model = "应急处置记录的处置流程步骤管理")
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
                result = "查询失败：应急处置记录的处置步骤编号为空，请确认后重试。";
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

    /**
     * 根据主键ID，查询应急处置步骤信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryUnhandledPrevStepById")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 应急处置记录的处置步骤的ID 查询之前的处置步骤中是否有未处置或者过程记录为空的情况", model = "应急处置记录的处置流程步骤管理")
    public AjaxMessage queryUnhandledPrevStepById(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();

        try {
            String id = request.getParameter("id");
            if (id != null && !"".equals(id)) {
                result = this.getService().queryUnhandledPrevStepById(id);
                flag = true;
            } else {
                flag = false;
                result = "查询失败：应急处置记录的处置步骤编号为空，请确认后重试。";
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
