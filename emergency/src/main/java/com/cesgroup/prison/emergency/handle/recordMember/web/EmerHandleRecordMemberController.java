package com.cesgroup.prison.emergency.handle.recordMember.web;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.util.IpUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.emergency.handle.record.entity.EmerHandleRecord;
import com.cesgroup.prison.emergency.handle.record.service.EmerHandleRecordService;
import com.cesgroup.prison.emergency.handle.recordMember.dao.EmerHandleRecordMemberDao;
import com.cesgroup.prison.emergency.handle.recordMember.dto.NearbyPoliceDto;
import com.cesgroup.prison.emergency.handle.recordMember.entity.EmerHandleRecordMember;
import com.cesgroup.prison.emergency.handle.recordMember.service.EmerHandleRecordMemberService;
import com.cesgroup.prison.utils.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/emergency/handle/recordMemberManage")
public class EmerHandleRecordMemberController extends BaseEntityDaoServiceCRUDController<EmerHandleRecordMember, String, EmerHandleRecordMemberDao, EmerHandleRecordMemberService> {
    /**
     * 应急处置记录Service
     */
    @Autowired
    private EmerHandleRecordService emerHandleRecordService;

    /**
     * 分页查询应急处置记录的工作组成员
     * @param request
     * @param response
     * @return
     * @throws WebUIException
     */
    @RequestMapping(value = "/queryWithPage")
    @ResponseBody
    public Map<String, Object> queryWithPage(HttpServletRequest request, HttpServletResponse response) throws WebUIException {
        String recordGroupId = request.getParameter("recordGroupId");// 应急处置步骤的工作组编号

        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        if (recordGroupId != null && !"".equals(recordGroupId)) {
            paramMap.put("recordGroupId", recordGroupId);
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
     * 根据主键ID，查询应急预案信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    @Logger(action = "新增或更新", logger = "新增或更新应急处置记录的工作组成员", model = "应急处置记录的工作组成员管理")
    public AjaxMessage saveOrUpdate(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            String recordGroupId = request.getParameter("recordGroupId");// 应急处置步骤的工作组编号
            String memberDataJson = request.getParameter("memberDataJson");// 应急处置步骤的工作组成员列表JSON

            this.getService().saveOrUpdate(recordGroupId, memberDataJson);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "应急处置记录的工作组成员操作失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "应急处置记录的工作组成员操作失败：" + e.getMessage();
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
     * 根据应急处置记录的工作组ID，查询应急处置步骤信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryByRecordGroupId")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 应急处置记录的工作组ID 查询应急工作组成员列表", model = "应急处置记录的工作组成员管理")
    public AjaxMessage queryByRecordGroupId(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();

        try {
            String recordGroupId = request.getParameter("recordGroupId");
            if (recordGroupId != null && !"".equals(recordGroupId)) {
                result = this.getService().queryByRecordGroupId(recordGroupId);
                flag = true;
            } else {
                flag = false;
                result = "查询失败：应急处置记录的工作组ID为空，请确认后重试。";
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
     * 附近的民警显示窗口
     *
     * @return
     */
    @RequestMapping("/openNearbyDialog")
    public ModelAndView openNearbyDialog(HttpServletRequest request, HttpServletResponse response) {
        String recordId = request.getParameter("recordId");// 应急处置记录编号
        String recordGroupId = request.getParameter("recordGroupId");// 应急处置记录的工作组编号

        // 根据应急处置记录编号，查询应急处置记录信息
        EmerHandleRecord emerHandleRecord = null;
        try {
            emerHandleRecord = this.emerHandleRecordService.queryById(recordId);
        } catch (ServiceException e) {
            throw new WebUIException("打开附近民警窗口失败：查询应急处置记录发生异常");
        }
        if(emerHandleRecord == null) {
            throw new WebUIException("打开附近民警窗口失败：应急处置记录数据缺失");
        }

        ModelAndView mv = new ModelAndView("emergency/handle/recordMember/list_nearby");
        mv.addObject("recordGroupId", recordGroupId);
        mv.addObject("emerHandleRecord", emerHandleRecord);

        return mv;
    }

    /**
     * 分页查询附近的民警信息
     * @param request
     * @param response
     * @return
     * @throws WebUIException
     */
    @RequestMapping(value = "/queryNearbyWithPage")
    @ResponseBody
    public Map<String, Object> queryNearbyWithPage(HttpServletRequest request, HttpServletResponse response) throws WebUIException {
        String cusNumber = request.getParameter("cusNumber");// 应急处置步骤所属的监狱编号
        String areaName = request.getParameter("areaName");// 报警所属区域名称
        String memberName = request.getParameter("memberName");// 姓名
        String callNo = request.getParameter("callNo");// 号码

        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        if (cusNumber != null && !"".equals(cusNumber)) {
            paramMap.put("cusNumber", cusNumber);
        }
        if (areaName != null && !"".equals(areaName)) {
            paramMap.put("areaName", areaName);
        }
        if (memberName != null && !"".equals(memberName)) {
            paramMap.put("memberName", memberName);
        }
        if (callNo != null && !"".equals(callNo)) {
            paramMap.put("callNo", callNo);
        }
        // 获取当前时间30分钟之前的时间
        String timeBeforeThirtyMins = Util.getAddMinDate(new Date(), Util.DF_DTIME_STR, -30);
        if (timeBeforeThirtyMins != null && !"".equals(timeBeforeThirtyMins)) {
            paramMap.put("timeBeforeThirtyMins", timeBeforeThirtyMins);
        }
        try {
            Page<NearbyPoliceDto> pageInfo = this.getService().queryNearbyWithPage(paramMap, buildPageRequest());
            return DataUtils.pageToMap(pageInfo);
        } catch (ServiceException e) {
            throw new WebUIException(e.getMessage());
        } catch (Exception e) {
            throw new WebUIException(e.getMessage());
        }
    }

    /**
     * 分页查询本单位全部民警信息
     * @param request
     * @param response
     * @return
     * @throws WebUIException
     */
    @RequestMapping(value = "/queryAllPoliceWithPage")
    @ResponseBody
    public Map<String, Object> queryAllPoliceWithPage(HttpServletRequest request, HttpServletResponse response) throws WebUIException {
        String cusNumber = request.getParameter("cusNumber");// 应急处置步骤所属的监狱编号
        String memberName = request.getParameter("memberName");// 姓名
        String callNo = request.getParameter("callNo");// 号码

        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        if (cusNumber != null && !"".equals(cusNumber)) {
            paramMap.put("cusNumber", cusNumber);
        }
        if (memberName != null && !"".equals(memberName)) {
            paramMap.put("memberName", memberName);
        }
        if (callNo != null && !"".equals(callNo)) {
            paramMap.put("callNo", callNo);
        }
        try {
            Page<NearbyPoliceDto> pageInfo = this.getService().queryAllPoliceWithPage(paramMap, buildPageRequest());
            return DataUtils.pageToMap(pageInfo);
        } catch (ServiceException e) {
            throw new WebUIException(e.getMessage());
        } catch (Exception e) {
            throw new WebUIException(e.getMessage());
        }
    }

    /**
     * 根据应急处置记录的工作组ID，查询应急处置步骤信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/startCallByRecordGroupId")
    @ResponseBody
    @Logger(action = "呼叫", logger = "根据 应急处置记录的工作组ID 呼叫应急工作组成员列表", model = "应急处置记录的工作组成员管理")
    public AjaxMessage startCallByRecordGroupId(HttpServletRequest request, HttpServletResponse response) {
        String recordGroupId = request.getParameter("recordGroupId");
        String noticeContent = request.getParameter("noticeContent");
        boolean restartCall = request.getParameter("restartCall") != null ? Boolean.parseBoolean(request.getParameter("restartCall")) : false;
        String clientIp = IpUtil.currentRemoteIp(request);// 当前用户的IP

        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            this.getService().startCallByRecordGroupId(clientIp, recordGroupId, noticeContent, restartCall);
            flag = true;
            result = "呼叫成功";
        } catch (ServiceException e) {
            flag = false;
            result = "呼叫失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "呼叫失败：" + e.getMessage();
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
