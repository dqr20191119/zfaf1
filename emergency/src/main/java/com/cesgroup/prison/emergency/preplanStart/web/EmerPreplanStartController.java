package com.cesgroup.prison.emergency.preplanStart.web;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.preplanStart.dao.EmerPreplanStartDao;
import com.cesgroup.prison.emergency.preplanStart.entity.EmerPreplanStart;
import com.cesgroup.prison.emergency.preplanStart.service.EmerPreplanStartService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/emergency/preplanStart")
public class EmerPreplanStartController extends BaseEntityDaoServiceCRUDController<EmerPreplanStart, String, EmerPreplanStartDao, EmerPreplanStartService> {

    /**
     * 窗口
     *
     * @return
     */
    @RequestMapping("/openDialog")
    public ModelAndView openDialog(HttpServletRequest request, HttpServletResponse response) {
        String alarmPlanId = request.getParameter("alarmPlanId");//报警预案编号
        String alarmRecordId = request.getParameter("alarmRecordId");//报警记录编号
        ModelAndView mv = new ModelAndView("emergency/preplanStart/index");
        mv.addObject("alarmPlanId", alarmPlanId);
        mv.addObject("alarmRecordId", alarmRecordId);
        return mv;
    }

    @RequestMapping(value = "/queryByCondition")
    @ResponseBody
    public AjaxMessage queryByCondition(HttpServletRequest request, HttpServletResponse response) throws WebUIException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new WebUIException("用户未登录");
        }
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();

        try {
            String alarmRecordId = request.getParameter("alarmRecordId");//报警记录编号
            String preplanName = request.getParameter("preplanName");//预案名称

            Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
            if (alarmRecordId != null && !"".equals(alarmRecordId)) {
                paramMap.put("alarmRecordId", alarmRecordId);
            }
            if (preplanName != null && !"".equals(preplanName)) {
                paramMap.put("preplanName", preplanName);
            }
            result = this.getService().queryByCondition(paramMap);
            flag = true;
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
     * 跳转到应急预案预览页面
     *
     * @return
     */
    @RequestMapping("/toView")
    public ModelAndView toView(HttpServletRequest request, HttpServletResponse response) {
        String alarmRecordId = request.getParameter("alarmRecordId");//报警记录编号
        String preplanId = request.getParameter("preplanId");//应急预案编号
        ModelAndView mv = new ModelAndView("emergency/preplanStart/preview");
        mv.addObject("alarmRecordId", alarmRecordId);
        mv.addObject("preplanId", preplanId);
        return mv;
    }
}
