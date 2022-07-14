package com.cesgroup.prison.emergency.handle.recordStatistic.web;

import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Base64Util;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.handle.recordStatistic.dao.EmerHandleRecordStatisticDao;
import com.cesgroup.prison.emergency.handle.recordStatistic.entity.EmerHandleRecordStatistic;
import com.cesgroup.prison.emergency.handle.recordStatistic.service.EmerHandleRecordStatisticService;
import com.cesgroup.prison.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/emergency/handle/recordStatisticManage")
public class EmerHandleRecordStatisticController extends BaseEntityDaoServiceCRUDController<EmerHandleRecordStatistic, String, EmerHandleRecordStatisticDao, EmerHandleRecordStatisticService> {

    /**
     * 窗口
     *
     * @return
     */
    @RequestMapping("/openDialog")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("emergency/handle/recordStatistic/list");
        return mv;
    }

    @RequestMapping(value = "/queryWithPage")
    @ResponseBody
    public Map<String, Object> queryWithPage(HttpServletRequest request, HttpServletResponse response) throws WebUIException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new WebUIException("用户未登录");
        }
        String cusNumber = request.getParameter("cusNumber");//单位/监狱编号
        String preplanName = request.getParameter("preplanName");//预案名称

        // 如果页面传来的监狱/单位编号为空，则取当前登录用户的单位编号
        if(cusNumber == null || "".equals(cusNumber)) {
            cusNumber = user.getCusNumber();
        }

        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        if (cusNumber != null && !"".equals(cusNumber)) {
            // 根据单位编号，查询该单位下属的市局、分局、监狱的机构列表
            List<String> cusNumberList = null;
            try {
                cusNumberList = AuthSystemFacade.getSjFjJyOrgKeyListByCusNumber(cusNumber);
            } catch (Exception e) {
                throw new WebUIException("根据单位编号，获取下属的局、分局、监狱编码发生异常");
            }
            paramMap.put("cusNumberList", cusNumberList);
        }
        if (preplanName != null && !"".equals(preplanName)) {
            paramMap.put("preplanName", preplanName);
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
     * 窗口
     *
     * @return
     */
    @RequestMapping("/openRecordDialog")
    public ModelAndView openRecordDialog(HttpServletRequest request, HttpServletResponse response) {
        String cusNumber = request.getParameter("cusNumber");//单位/监狱编号
        String preplanName = request.getParameter("preplanName");//应急预案名称

        ModelAndView mv = new ModelAndView("emergency/handle/recordStatistic/list_record");
        mv.addObject("cusNumber", cusNumber);
        mv.addObject("preplanName", Base64Util.decodeString(preplanName, 2));
        return mv;
    }

    @RequestMapping(value = "/queryRecordWithPage")
    @ResponseBody
    public Map<String, Object> queryRecordWithPage(HttpServletRequest request, HttpServletResponse response) throws WebUIException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new WebUIException("用户未登录");
        }
        String cusNumber = request.getParameter("cusNumber");//单位/监狱编号
        String preplanName = request.getParameter("preplanName");//预案名称
        String recordStatus = request.getParameter("recordStatus");//应急处置状态
        String startTimeStart = request.getParameter("startTimeStart");//处置开始时间-开始
        String startTimeEnd = request.getParameter("startTimeEnd");//处置开始时间-结束

        // 如果页面传来的监狱/单位编号为空，则取当前登录用户的单位编号
        if(cusNumber == null || "".equals(cusNumber)) {
            cusNumber = user.getCusNumber();
        }

        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        if (cusNumber != null && !"".equals(cusNumber)) {
            paramMap.put("cusNumber", cusNumber);
        }
        if (preplanName != null && !"".equals(preplanName)) {
            paramMap.put("preplanName", preplanName);
        }
        if (recordStatus != null && !"".equals(recordStatus)) {
            paramMap.put("recordStatus", recordStatus);
        }
        if (startTimeStart != null && !"".equals(startTimeStart)) {
            paramMap.put("startTimeStart", startTimeStart);
        }
        if (startTimeEnd != null && !"".equals(startTimeEnd)) {
            paramMap.put("startTimeEnd", startTimeEnd);
        }
        try {
            Page<Map<String, Object>> pageInfo = (Page<Map<String, Object>>) this.getService().queryRecordWithPage(paramMap, buildPageRequest());
            return DataUtils.pageToMap(pageInfo);
        } catch (ServiceException e) {
            throw new WebUIException(e.getMessage());
        } catch (Exception e) {
            throw new WebUIException(e.getMessage());
        }
    }
}
