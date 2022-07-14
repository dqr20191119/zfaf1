package com.cesgroup.prison.emergency.handle.record.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.handle.record.dao.EmerHandleRecordDao;
import com.cesgroup.prison.emergency.handle.record.entity.EmerHandleRecord;
import com.cesgroup.prison.emergency.handle.record.service.EmerHandleRecordService;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.yjct.util.ExprotSTUtil;

@Controller
@RequestMapping(value = "/emergency/handle/recordManage")
public class EmerHandleRecordController extends BaseEntityDaoServiceCRUDController<EmerHandleRecord, String, EmerHandleRecordDao, EmerHandleRecordService> {

    @Resource
    EmerHandleRecordService service;

    /**
     * 窗口
     *
     * @return
     */
    @RequestMapping("/openDialog")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("emergency/handle/record/list");
        return mv;
    }

    @RequestMapping(value = "/queryWithPage")
    @ResponseBody
    public Map<String, Object> queryWithPage(HttpServletRequest request, HttpServletResponse response) throws WebUIException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new WebUIException("用户未登录");
        }
        String preplanName = request.getParameter("preplanName");//预案名称
        String recordStatus = request.getParameter("recordStatus");//应急处置状态
        String cusNumber = request.getParameter("cusNumber");//单位/监狱编号
        String alarmPlanName = request.getParameter("alarmPlanName");//报警预案名称
        String startTimeStart = request.getParameter("startTimeStart");//处置开始时间-开始
        String startTimeEnd = request.getParameter("startTimeEnd");//处置开始时间-结束

        // 如果页面传来的监狱/单位编号为空，则取当前登录用户的单位编号
        if(cusNumber == null || "".equals(cusNumber)) {
            cusNumber = user.getCusNumber();
        }

        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        if (preplanName != null && !"".equals(preplanName)) {
            paramMap.put("preplanName", preplanName);
        }
        if (recordStatus != null && !"".equals(recordStatus)) {
            paramMap.put("recordStatus", recordStatus);
        }
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
        if (alarmPlanName != null && !"".equals(alarmPlanName)) {
            paramMap.put("alarmPlanName", alarmPlanName);
        }
        if (startTimeStart != null && !"".equals(startTimeStart)) {
            paramMap.put("startTimeStart", startTimeStart);
        }
        if (startTimeEnd != null && !"".equals(startTimeEnd)) {
            paramMap.put("startTimeEnd", startTimeEnd);
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
     * 根据主键ID，查询应急处置记录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryById")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 ID 查询应急处置记录", model = "应急处置记录管理")
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

    /**
     * 根据主键ID，查询应急预案信息
     *
     * @param emerHandleRecord
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    @Logger(action = "新增或更新", logger = "新增或更新应急处置记录", model = "应急处置记录管理")
    public AjaxMessage saveOrUpdate(EmerHandleRecord emerHandleRecord) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            result = this.getService().saveOrUpdate(emerHandleRecord);
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
     * 跳转右侧应急处置面板
     *
     * @return
     */
    @RequestMapping("/handlePanel")
    public ModelAndView handlePanel(HttpServletRequest request, HttpServletResponse response) {
        String emerHandleRecordId = request.getParameter("emerHandleRecordId");//应急处置记录编号
        String handleType = request.getParameter("handleType");// handleType：1-事件处置，2-事件演练，3-报警处置，4-处置记录处置(现在使用中的只有3和4，1和2类型已停用)

        ModelAndView mv = new ModelAndView("emergency/handle/record/handle_panel");

        EmerHandleRecord emerHandleRecord = null;
        try {
            emerHandleRecord = this.getService().queryById(emerHandleRecordId);
        } catch (ServiceException e) {
            throw new WebUIException("查询应急处置记录发生异常，跳转失败");
        } catch (Exception e) {
            throw new WebUIException("查询应急处置记录发生异常，跳转失败");
        }
        mv.addObject("emerHandleRecord", emerHandleRecord);
        mv.addObject("handleType", handleType);
        return mv;
    }

    /**
     * 跳转到应急处置流程页面
     *
     * @return
     */
    @RequestMapping("/handleStep")
    public ModelAndView handleStep(HttpServletRequest request, HttpServletResponse response) {
        String emerHandleRecordId = request.getParameter("emerHandleRecordId");//应急处置记录编号
        String alarmRecordId = request.getParameter("alarmRecordId");//报警记录编号
        String preplanId = request.getParameter("preplanId");//应急预案编号
        ModelAndView mv = new ModelAndView("emergency/handle/record/handle_step");
        mv.addObject("alarmRecordId", alarmRecordId);
        mv.addObject("preplanId", preplanId);
        mv.addObject("emerHandleRecordId", emerHandleRecordId);
        return mv;
    }

    /**
     * 根据主键ID，查询应急预案信息
     *
     * @param emerHandleRecord
     * @return
     */
    @RequestMapping(value = "/updateRecordStatus")
    @ResponseBody
    @Logger(action = "更新", logger = "更新应急处置记录的处置状态", model = "应急处置记录管理")
    public AjaxMessage updateRecordStatus(EmerHandleRecord emerHandleRecord) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            this.getService().updateRecordStatus(emerHandleRecord);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "更新应急处置记录处置状态失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "更新应急处置记录处置状态失败：" + e.getMessage();
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
     * 跳转到应急处置流程查看页面
     *
     * @return
     */
    @RequestMapping("/handleStepReview")
    public ModelAndView handleStepReview(HttpServletRequest request, HttpServletResponse response) {
        String emerHandleRecordId = request.getParameter("emerHandleRecordId");//应急处置记录编号
        String alarmRecordId = request.getParameter("alarmRecordId");//报警记录编号
        String preplanId = request.getParameter("preplanId");//应急预案编号
        ModelAndView mv = new ModelAndView("emergency/handle/record/handle_step_review");
        mv.addObject("alarmRecordId", alarmRecordId);
        mv.addObject("preplanId", preplanId);
        mv.addObject("emerHandleRecordId", emerHandleRecordId);
        return mv;
    }

    /**
     * 事件报告
     *
     * @return
     */
    @RequestMapping("/eventReport")
    public ModelAndView eventReport(HttpServletRequest request, HttpServletResponse response) {
        String emerHandleRecordId = request.getParameter("emerHandleRecordId");//应急处置记录编号
        ModelAndView mv = new ModelAndView("emergency/handle/record/event_report");
        EmerHandleRecord emerHandleRecord = this.getService().queryById(emerHandleRecordId);
        mv.addObject("emerHandleRecordId", emerHandleRecordId);
        mv.addObject("experience", emerHandleRecord.getExperience());
        return mv;
    }

    /**
     * 根据主键ID，查询应急处置记录
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryDetailById")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 ID 查询应急处置记录详情", model = "应急处置记录管理")
    public AjaxMessage queryDetailById(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();

        try {
            String id = request.getParameter("id");
            if (id != null && !"".equals(id)) {
                result = this.getService().queryDetailById(id);
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

    /**
     * 根据主键ID，查询应急预案信息
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateEventReport")
    @ResponseBody
    @Logger(action = "新增或更新", logger = "新增或更新应急处置记录的事件报告", model = "应急处置记录管理")
    public AjaxMessage saveOrUpdateEventReport(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            String emerHandleRecordId = request.getParameter("emerHandleRecordId");//应急处置记录编号
            String experience = request.getParameter("experience");//应急处置记录经验总结
            String rydjbAffixIds = request.getParameter("rydjbAffixIds");//人员登记表附件编号
            EmerHandleRecord emerHandleRecord = this.getService().queryById(emerHandleRecordId);
            emerHandleRecord.setExperience(experience);
            this.getService().updateExperience(emerHandleRecord);
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
     * 查看预案报告
     *
     * @return
     */
    @RequestMapping("/reviewEmerReport")
    public ModelAndView reviewEmerReport(HttpServletRequest request, HttpServletResponse response) {
        String emerHandleRecordId = request.getParameter("emerHandleRecordId");//应急处置记录编号
        String alarmRecordId = request.getParameter("alarmRecordId");//报警记录编号
        EmerHandleRecord emerHandleRecord = null;
        try {
            emerHandleRecord = this.getService().queryDetailById(emerHandleRecordId);
        } catch (ServiceException e) {
            throw new WebUIException(e.getMessage());
        }
        ModelAndView mv = new ModelAndView("emergency/handle/record/review_emer_report");
        mv.addObject("emerHandleRecord", emerHandleRecord);
        mv.addObject("alarmRecordId", alarmRecordId);
        return mv;
    }


    /**
     * 预案报告导出word文档
     * @param recordId
     * @param request
     * @param response
     * @return
     * @throws IOException
     * @throws Exception
     */
    @RequestMapping(value = "exprotWord")
    @ResponseBody
    public void exprotWord(String recordId ,String alarmRecordId,HttpServletRequest request,HttpServletResponse response)
            throws IOException , Exception {

        Map<String, Object> resultMap=new HashMap<>();
        try {
            System.out.println("recordId:"+recordId);

            Map<String, Object> map = this.getService().queryYjyaReport(recordId);


            EmerHandleRecord emerHandleRecord = (EmerHandleRecord) map.get("YjyabgEntity");
            AlarmRecordEntity alarmRecordEntity = service.queryAREByid(alarmRecordId);
            //设置相应到客户端的文件名编码
            response.setCharacterEncoding("UTF-8");
            //定义下载的文件名字
            String fileName = "应急处置报告.docx";
            response.setContentType("application/msword;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            //获取响应报文输出流对象
            ServletOutputStream out = response.getOutputStream();
            ExprotSTUtil exprotWord = new ExprotSTUtil();
           /* XWPFDocument document = exprotWord.exportSTToWord(emerHandleRecord);*/

            XWPFDocument document = exprotWord.exportSTToWord1(emerHandleRecord,alarmRecordEntity);



            //String s = UUID.randomUUID().toString().replace("-","");
            //Write the Document in file system
            //out = new FileOutputStream(new File("D:\\预案报告文档"+s+".docx"));

            document.write(out);
            document.close();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
