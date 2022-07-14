package com.cesgroup.prison.alarm.plan.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.alarm.plan.dao.AlarmPlanMasterMapper;
import com.cesgroup.prison.alarm.plan.entity.AlarmEmerPlanRltn;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanBroadcastPlan;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanItemDtlsEntity;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanMasterEntity;
import com.cesgroup.prison.alarm.plan.entity.AlertPlanRltnEntity;
import com.cesgroup.prison.alarm.plan.entity.PlanDeviceRltnEntity;
import com.cesgroup.prison.alarm.plan.service.AlarmPlanService;
import com.cesgroup.prison.code.utils.CodeFacade;
import com.cesgroup.prison.utils.DataUtils;

/**
 * @projectName：prison
 * @ClassName：AlarmPlanController
 * @Description： 报警预案
 * @author：Tao.xu
 * @date：2017年12月25日 下午6:58:58
 */
@Controller
@RequestMapping(value = "/plan")
public class AlarmPlanController extends BaseEntityDaoServiceCRUDController<AlarmPlanMasterEntity, String, AlarmPlanMasterMapper, AlarmPlanService> {
    /**
     * @return ModelAndView
     * @throws
     * @methodName: openDialog
     * @Description: 打开窗口
     */
    @RequestMapping(value = "/openDialog")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("alarm/plan/planManage");
        return mv;
    }

    /**
     * @return ModelAndView
     * @throws
     * @methodName: openAddDialog
     * @Description: 打开增加预案窗口
     */
    @RequestMapping(value = "/openDialog/add")
    public ModelAndView openAddDialog() {
        ModelAndView mv = new ModelAndView("alarm/plan/addPlan");
        return mv;
    }

    /**
     * @return ModelAndView
     * @throws
     * @methodName: openUpdateDialog
     * @Description: 打开修改窗口
     */
    @RequestMapping(value = "/openDialog/update")
    public ModelAndView openUpdateDialog(String id) {
        ModelAndView mv = new ModelAndView("alarm/plan/updatePlan");
        mv.addObject("ID", id);
        return mv;
    }

    /**
     * @param id
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     * @throws
     * @methodName: openDeviceDialog
     * @Description: 打开关联设备窗口
     */
    @RequestMapping(value = "/openDialog/device")
    public ModelAndView openDeviceDialog(String id) {
        ModelAndView mv = new ModelAndView("alarm/plan/planDevice");
        mv.addObject("planId", id);
        return mv;
    }

    /**
     * @param id
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     * @methodName: openAlertorDialog
     * @Description: 打开报警器关联窗口
     */
    @RequestMapping(value = "/openDialog/alertor")
    public ModelAndView openAlertorDialog(String id, String planName) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView("alarm/plan/planAlertor");
        mv.addObject("planId", id);
        mv.addObject("planName", URLDecoder.decode(planName, "utf8"));
        return mv;
    }

    /**
     * @param id
     * @return ModelAndView
     * @throws UnsupportedEncodingException
     * @methodName: openAlertorDialog
     * @Description: 打开广播预案关联窗口
     */
    @RequestMapping(value = "/openDialog/broadcastPlan")
    public ModelAndView openBroadcatPlanDialog(String id, String planName) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView("alarm/plan/planBroadcastPlan");
        mv.addObject("planId", id);
        mv.addObject("planName", URLDecoder.decode(planName, "utf8"));
        return mv;
    }

    /**
     * 打开报警预案与应急预案关联关系配置窗口
     * @param request
     * @param response
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "/openDialog/emerPlan")
    public ModelAndView openEmerPlanDialog(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        String alarmPlanId = request.getParameter("alarmPlanId");

        ModelAndView mv = new ModelAndView("alarm/plan/planEmerPlan");

        mv.addObject("alarmPlanId", alarmPlanId);
        return mv;
    }

    @RequestMapping(value = "/listAll/plan")
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询报警预案信息")
    public Map<String, Object> listAll(AlarmPlanMasterEntity entity) throws Exception {
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = (Page<Map<String, Object>>) this.getService().listAllForMaster(entity, pageRequest);
        return DataUtils.pageToMap(page);
    }

    /**
     * @param cusNumber 监狱号
     * @param itemId    关联项 1 "门禁"; 2 "摄像机"; 3 "广播"; 4 "大屏"; 5 "对讲";
     * @param areaId    区域编号
     * @param planId    预案编号
     * @return
     * @throws Exception Map<String,Object>
     * @throws
     * @methodName: listAll
     * @Description: 查询需要关联的设备信息
     */
    @RequestMapping(value = "/listAll/device")
    @ResponseBody
    @Logger(action = "查询", logger = "列表查询待关联设备信息")
    public Map<String, Object> listAll(String cusNumber, String itemId, String areaId, String planId) throws Exception {
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.getService().listAllForDevice(cusNumber, areaId, itemId,
                planId);
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    @RequestMapping(value = "/listAll/alertor")
    @ResponseBody
    @Logger(action = "查询", logger = "列表查询待关联报警器信息")
    public Map<String, Object> listAll(String cusNumber, String areaId) throws Exception {
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.getService().listAllForAlertor(cusNumber, areaId);
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    @RequestMapping(value = "/listAllForDeviceByItem")
    @ResponseBody
    @Logger(action = "查询", logger = "列表查询关联设备信息")
    public Map<String, Object> listAllForDeviceByItem(PlanDeviceRltnEntity entity) throws Exception {
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.getService().listAllForDeviceByItem(entity);
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    @RequestMapping(value = "/listAllForAlertorByPlan")
    @ResponseBody
    @Logger(action = "查询", logger = "列表查询关联报警器信息")
    public Map<String, Object> listAllForAlertorByPlan(AlertPlanRltnEntity entity) throws Exception {
        List<Map<String, Object>> list = (List<Map<String, Object>>) this.getService().listAllForAlertorByPlan(entity);
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    /**
     * @param id
     * @return List<Object>
     * @throws
     * @methodName: findPlanById
     * @Description: 根据id查询预案信息和对应的关联项信息
     */
    @RequestMapping(value = "/findPlanById")
    @ResponseBody
    @Logger(action = "查询", logger = "根据ID查询报警预案信息")
    public AjaxMessage findPlanById(String id) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        Object obj = null;
        try {
            AlarmPlanMasterEntity entity = this.getService().findMasterById(id);
            flag = true;
            obj = entity;
        } catch (Exception e) {
            flag = false;
            obj = "查询异常： " + e.getMessage();
        }
        if (flag) {
            ajaxMessage.setObj(obj);
        } else {
            ajaxMessage.setMsg((String) obj);
        }
        ajaxMessage.setSuccess(flag);
        return ajaxMessage;
    }

    @RequestMapping(value = "/update/master")
    @ResponseBody
    @Logger(action = "修改", logger = "修改报警预案信息")
    public AjaxMessage update(@RequestBody AlarmPlanMasterEntity entity) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        Object obj = null;
        try {
            this.getService().updateMasterInfo(entity);
            flag = true;
            obj = "修改预案成功";
        } catch (Exception e) {
            flag = false;
            obj = "修改预案发生异常";
        }
        ajaxMessage.setMsg((String) obj);
        ajaxMessage.setSuccess(flag);
        return ajaxMessage;
    }

    /**
     * @param entity
     * @return Map<String, Object>
     * @throws
     * @methodName: delete
     * @Description: 删除预案
     * @author：Tao.xu
     * @date：2017年12月26日 下午12:02:35
     */
    @RequestMapping(value = "/delete/master", method = RequestMethod.POST)
    @ResponseBody
    public AjaxMessage delete(AlarmPlanMasterEntity entity) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        Object obj = null;
        try {
            if (entity != null) {
                this.getService().delAlarmPlanMaster(entity);
                flag = true;
            } else {
                flag = false;
                obj = "删除预案失败";
            }
        } catch (Exception e) {
            flag = false;
            obj = "删除预案发生异常";
        }
        if (!flag) {
            ajaxMessage.setMsg((String) obj);
        }
        ajaxMessage.setSuccess(flag);
        return ajaxMessage;
    }

    /**
     * @param entity
     * @return Map<String, Object>
     * @throws
     * @methodName: delete
     * @Description: 删除设备
     */
    @RequestMapping(value = "/delete/device", method = RequestMethod.POST)
    @ResponseBody
    public AjaxMessage delete(PlanDeviceRltnEntity entity) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        Object object = null;
        try {
            if (entity != null) {
                this.getService().delPlanDeviceRltn(entity);
                flag = true;
            } else {
                flag = false;
                object = "删除关联设备失败";
            }
        } catch (Exception e) {
            flag = false;
            object = "删除关联设备异常";
        }
        if (!flag) {
            ajaxMessage.setMsg((String) object);
        }
        ajaxMessage.setSuccess(flag);
        return ajaxMessage;
    }

    /**
     * @param entity
     * @return Map<String, Object>
     * @throws
     * @methodName: delete
     * @Description: 删除报警器
     */
    @RequestMapping(value = "/delete/alert", method = RequestMethod.POST)
    @ResponseBody
    public AjaxMessage delete(AlertPlanRltnEntity entity) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        Object object = null;
        try {
            if (entity != null) {
                this.getService().delAlertPlanRltn(entity);
                flag = true;
            } else {
                flag = false;
                object = "删除关联设备失败";
            }
        } catch (Exception e) {
            flag = false;
            object = "删除关联设备异常";
        }
        if (!flag) {
            ajaxMessage.setMsg((String) object);
        }
        ajaxMessage.setSuccess(flag);
        return ajaxMessage;
    }

    /**
     * @param entity
     * @return Map<String, Object>
     * @throws
     * @methodName: inster
     * @Description: 添加预案信息
     */
    @ResponseBody
    @RequestMapping(value = "/inster/master")
    public AjaxMessage inster(@RequestBody AlarmPlanMasterEntity entity) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        Object obj = null;
        try {
            if (entity != null) {
                this.getService().addMasterInfo(entity);
                flag = true;
                obj = "添加预案成功";
            } else {
                flag = false;
                obj = "添加预案失败";
            }
        } catch (Exception e) {
            flag = false;
            obj = "添加预案发生异常";
        }
        ajaxMessage.setMsg((String) obj);
        ajaxMessage.setSuccess(flag);
        return ajaxMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/inster/device")
    public AjaxMessage insterDevice(@RequestBody List<Map<String, Object>> devices) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        Object obj = null;
        try {
            flag = true;
            this.getService().addDeviceInfo(devices);
            obj = "保存成功";
        } catch (Exception e) {
            flag = false;
            obj = "预案关联设备异常";
        }
        if (flag) {
            ajaxMessage.setObj(obj);
        } else {
            ajaxMessage.setMsg((String) obj);
        }
        ajaxMessage.setSuccess(flag);
        return ajaxMessage;
    }

    @ResponseBody
    @RequestMapping(value = "/inster/alertor")
    public AjaxMessage insterAlertor(@RequestBody List<Map<String, Object>> alertors) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        Object obj = null;
        try {
            flag = true;
            this.getService().addAlertInfo(alertors);
            obj = "保存成功";
        } catch (Exception e) {
            flag = false;
            obj = "预案关联报警器异常";
        }
        if (flag) {
            ajaxMessage.setObj(obj);
        } else {
            ajaxMessage.setMsg((String) obj);
        }
        ajaxMessage.setSuccess(flag);
        return ajaxMessage;
    }

    /**
     * @param id 预案id
     * @return AjaxMessage
     * @throws
     * @methodName: findPlanAndItemsById
     * @Description: 根据ID查询预案名称信息和item控件下拉数据（关联项设置）
     */
    @RequestMapping(value = "/findPlanAndItemsById")
    @ResponseBody
    public AjaxMessage findPlanAndItemsById(String id) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        Object obj = null;
        try {
            AlarmPlanMasterEntity entity = this.getService().findMasterById(id);
            Map<String, Object> map = new HashMap<String, Object>();
            String items = "";
            if (!entity.getItems().isEmpty()) {
                for (AlarmPlanItemDtlsEntity item : entity.getItems()) {
                    items = items + item.getPidItemId() + ",";
                }
            }
            if (StringUtils.isNotBlank(items)) {
                String itemDate = CodeFacade.loadCode4ComboJson("4.20.51", 4, items.substring(0, items.length() - 1));
                map.put("items", JSON.parse(itemDate));
                map.put("planName", entity.getPmaPlanName());
                flag = true;
                obj = map;
            } else {
                flag = false;
                obj = "查询失败： 未查询到预案关联项信息";
            }

        } catch (Exception e) {
            flag = false;
            obj = "查询异常： " + e.getMessage();
        }
        if (flag) {
            ajaxMessage.setObj(obj);
        } else {
            ajaxMessage.setMsg((String) obj);
        }
        ajaxMessage.setSuccess(flag);
        return ajaxMessage;
    }

    @RequestMapping(value = "/saveBroadcastPlan")
    @ResponseBody
    public AjaxMessage saveBroadcastPlan(AlarmPlanBroadcastPlan entity) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        try {
            this.getService().saveBroadcastPlan(entity);
            ajaxMessage.setSuccess(true);
            ajaxMessage.setMsg("保存成功");
        } catch (Exception e) {
            ajaxMessage.setSuccess(false);
            ajaxMessage.setMsg("保存失败");
            e.printStackTrace();
        }
        return ajaxMessage;
    }

    @RequestMapping(value = "/findByPlanId")
    @ResponseBody
    public AjaxMessage findByPlanId(AlarmPlanBroadcastPlan entity) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        try {
            AlarmPlanBroadcastPlan alarmPlanBroadcastPlan = this.getService().findByPlanId(entity.getBprPlanId());
            ajaxMessage.setSuccess(true);
            ajaxMessage.setObj(alarmPlanBroadcastPlan);
            ajaxMessage.setMsg("查询成功");
        } catch (Exception e) {
            ajaxMessage.setSuccess(false);
            ajaxMessage.setMsg("查询失败");
            e.printStackTrace();
        }
        return ajaxMessage;
    }

    @RequestMapping(value = "/queryAlarmEmerPlanRltnByAlarmPlanId")
    @ResponseBody
    public AjaxMessage queryAlarmEmerPlanRltnByAlarmPlanId(HttpServletRequest request, HttpServletResponse response) {
        String alarmPlanId = request.getParameter("alarmPlanId");
        try {
            AlarmEmerPlanRltn alarmEmerPlanRltn = this.getService().queryAlarmEmerPlanRltnByAlarmPlanId(alarmPlanId);

            return new AjaxMessage(true, alarmEmerPlanRltn);
        } catch (ServiceException e) {
            return new AjaxMessage(false, null, e.getMessage());
        } catch (Exception e) {
            return new AjaxMessage(false, null, e.getMessage());
        }
    }

    @RequestMapping(value = "/saveOrUpdateAlarmEmerPlanRltn")
    @ResponseBody
    public AjaxMessage saveOrUpdateAlarmEmerPlanRltn(AlarmEmerPlanRltn entity) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        try {
            this.getService().saveOrUpdateAlarmEmerPlanRltn(entity);
            ajaxMessage.setSuccess(true);
            ajaxMessage.setMsg("保存成功");
        } catch (Exception e) {
            ajaxMessage.setSuccess(false);
            ajaxMessage.setMsg("保存失败");
            e.printStackTrace();
        }
        return ajaxMessage;
    }
}
