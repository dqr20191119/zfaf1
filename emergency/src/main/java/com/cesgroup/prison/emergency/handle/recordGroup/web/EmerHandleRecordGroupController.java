package com.cesgroup.prison.emergency.handle.recordGroup.web;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.emergency.handle.recordGroup.dao.EmerHandleRecordGroupDao;
import com.cesgroup.prison.emergency.handle.recordGroup.entity.EmerHandleRecordGroup;
import com.cesgroup.prison.emergency.handle.recordGroup.service.EmerHandleRecordGroupService;
import com.cesgroup.prison.emergency.preplan.entity.EmerPreplan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/emergency/handle/recordGroupManage")
public class EmerHandleRecordGroupController extends BaseEntityDaoServiceCRUDController<EmerHandleRecordGroup, String, EmerHandleRecordGroupDao, EmerHandleRecordGroupService> {

    /**
     * 根据主键ID，查询应急预案信息
     *
     * @param emerPreplan
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    @Logger(action = "新增或更新", logger = "新增或更新应急处置记录", model = "应急处置记录管理")
    public AjaxMessage saveOrUpdate(EmerPreplan emerPreplan) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            // this.getService().saveOrUpdate(emerPreplan);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "应急处置记录操作失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "应急处置记录操作失败：" + e.getMessage();
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
    @RequestMapping(value = "/queryByRecordStepId")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 应急处置记录的处置记录的步骤ID 查询应急工作组信息", model = "应急处置记录的工作组管理")
    public AjaxMessage queryByRecordStepId(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();

        try {
            String recordStepId = request.getParameter("recordStepId");
            if (recordStepId != null && !"".equals(recordStepId)) {
                result = this.getService().queryByRecordStepId(recordStepId);
                flag = true;
            } else {
                flag = false;
                result = "查询失败：应急处置记录的步骤ID为空，请确认后重试。";
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
     * 根据主键ID，查询应急处置记录的工作组
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryById")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 ID 查询应急处置记录的工作组", model = "应急处置记录的工作组管理")
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
