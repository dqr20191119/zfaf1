package com.cesgroup.prison.foreign.web;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.foreign.dao.ForeignRegisterMapper;
import com.cesgroup.prison.foreign.entity.ForeignRegister;
import com.cesgroup.prison.foreign.service.ForeignRegisterService;
import com.cesgroup.prison.foreign.vo.ForeignRegisterVo;
import com.cesgroup.prison.utils.DataUtils;

/**
 * Created by dingdian on 2018/6/13.
 */
@Controller
@RequestMapping("/foreign")
public class ForeignRegisterController extends BaseEntityDaoServiceCRUDController<ForeignRegister, String, ForeignRegisterMapper, ForeignRegisterService> {

    /**
     * @methodName: openCarInfo
     * @Description: 打开车辆信息窗口
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/openCarInfo")
    public ModelAndView openCarInfo(
    		@RequestParam(value="frType", defaultValue="", required=false) String frType,
    		@RequestParam(value="date", defaultValue="", required=false) String date) {
        ModelAndView mv = new ModelAndView("/foreign/foreignCarInfo");
        mv.addObject("frType", frType);
        mv.addObject("date", date);
        return mv;
    }
    @RequestMapping(value = "/openImage")
    public ModelAndView openImage(
    		@RequestParam(value="id", defaultValue="", required=false) String id) {
        ModelAndView mv = new ModelAndView("/foreign/openImage");
        mv.addObject("id", id);
        return mv;
    }
    
    @RequestMapping(value = "/dkImage")
    public Map<String, Object>dkImage(
    		@RequestParam(value="id", defaultValue="", required=false) String id) {
    	
        return this.getService().dkImage(id);
    }
    
    
    /**
     * @methodName: openPeopleInfo
     * @Description: 打开人员信息窗口
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/openPeopleInfo")
    public ModelAndView openPeopleInfo(String flag){
        ModelAndView mv = new ModelAndView("/foreign/foreignPeopleInfo");
        mv.addObject("flag", flag);
        return mv;
    }

    /**
     * @methodName: openDialog
     * @Description: 打开窗口
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/openDialog")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("foreign/index");
        return mv;
    }

    /**
     * @methodName: openDialog
     * @Description: 打开登记窗口
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/openDialog/register")
    public ModelAndView openRegisterDialog() {
        ModelAndView mv = new ModelAndView("foreign/register");
        return mv;
    }
    /**
     * @methodName: openDialog
     * @Description: 打开编辑窗口
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/openDialog/edit")
    public ModelAndView openEditDialog(String id) {
        ModelAndView mv = new ModelAndView("foreign/register");
        mv.addObject("ID", id);
        return mv;
    }
    /**
     * @methodName: openDialog
     * @Description: 打开审核窗口
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/openDialog/check")
    public ModelAndView openCheckDialog(String id) {
        ModelAndView mv = new ModelAndView("foreign/check");
        mv.addObject("ID", id);
        return mv;
    }

    /**
     * @methodName: openDialog
     * @Description: 双击行打开查看窗口
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/openDialog/show")
    public ModelAndView openShowDialog(String id) {
        ModelAndView mv = new ModelAndView("foreign/foreignShow");
        mv.addObject("id", id);
        return mv;
    }


    @RequestMapping(value = "/findById")
    @ResponseBody
    @Logger(action = "查询", logger = "根据ID查询外来人车信息")
    public AjaxMessage findById(String id) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        Object obj = null;

        return ajaxMessage;
    }

    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("foreign/foreignList");
        return mv;
    }


    @RequestMapping("/save")
    public ModelAndView save(String id,String flag){
        ModelAndView mv = new ModelAndView("foreign/foreignInfo");
        if(StringUtils.isNotEmpty(id))
            mv.addObject("id",id);
        if(StringUtils.isNotEmpty(flag))
            mv.addObject("flag",flag);
        return mv;
    }

    /**
     * 根据登记ID查询外来人员明细信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/listPeopleByRegisterId")
    @ResponseBody
    public Map<String, Object> listPeopleByRegisterId(HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", request.getParameter("id"));

        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = this.getService().listPeopleByRegisterId(paramMap, pageRequest);
        System.out.println(page.toString());
        return DataUtils.pageToMap(page);
    }

    /**
     * 根据登记ID查询外来车辆明细信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/listCarByRegisterId")
    @ResponseBody
    public Map<String, Object> listCarByRegisterId(HttpServletRequest request) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", request.getParameter("id"));

        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = this.getService().listCarByRegisterId(paramMap, pageRequest);
        System.out.println(page.toString());
        return DataUtils.pageToMap(page);
    }

    /**
     * 根据Id查询单条进出入登记信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/registerInfoByRegisterId")
    @ResponseBody
    public ForeignRegister registerInfoByRegisterId(HttpServletRequest request){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", request.getParameter("id"));
        return this.getService().findById(paramMap);
    }

    /**
     *
     * @param entity
     * @return 分页查询外来人车信息
     * @throws Exception
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询外来人车信息")
    public Map<String, Object> listAll(ForeignRegisterVo entity) throws Exception{
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = this.getService().listAll(entity, pageRequest);
        return DataUtils.pageToMap(page);
    }
    /**
     * 保存登记信息
     * @param foreignRegisterVo
     * @return
     */
    @RequestMapping(value = "/register")
    @ResponseBody
    @Logger(action = "登记", logger = "车辆人员进出信息")
    public AjaxMessage add(@RequestBody ForeignRegisterVo foreignRegisterVo,HttpServletRequest request) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        String msg = null;
        try {
            this.getService().addEntryAndExitInfo(foreignRegisterVo,request);
            flag = true;
            msg = "操作成功";
        } catch (Exception e) {
            flag = false;
            msg = "操作异常： " + e.getMessage();
        }
        ajaxMessage.setMsg(msg);
        ajaxMessage.setSuccess(flag);
        return ajaxMessage;
    }

    @RequestMapping(value = "/checkInfo")
    @ResponseBody
    public AjaxMessage checkInfo(@RequestBody ForeignRegister foreignRegister){
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        String msg = null;
        try {
            this.getService().updateRegisterCheckInfo(foreignRegister);
            flag = true;
            msg = "操作成功";
        } catch (Exception e) {
            flag = false;
            msg = "操作异常： " + e.getMessage();
        }
        ajaxMessage.setMsg(msg);
        ajaxMessage.setSuccess(flag);
        return ajaxMessage;
    }

    @RequestMapping(value = "/outInfo")
    @ResponseBody
    public AjaxMessage outInfo(String id){
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        String msg = null;
        try {
            this.getService().updateRegisterOutInfo(id);
            flag = true;
            msg = "操作成功";
        } catch (Exception e) {
            flag = false;
            msg = "操作异常： " + e.getMessage();
        }
        ajaxMessage.setMsg(msg);
        ajaxMessage.setSuccess(flag);
        return ajaxMessage;
    }

    @RequestMapping(value = "/removeInfo")
    @ResponseBody
    public AjaxMessage removeInfo(HttpServletRequest request){
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        String msg = "";
        try{
            flag = true;
            List<String> ids = (List<String>) JSON.parse(request.getParameter("ids"));
            this.getService().deleteByIds(ids);
            msg = "删除成功";
        } catch (Exception e) {
            flag = false;
            msg = "删除失败";
        }
        ajaxMessage.setSuccess(flag);
        ajaxMessage.setMsg(msg);

        return ajaxMessage;

    }

    @RequestMapping(value = "/findPeopleByCardCode")
    @ResponseBody
    public AjaxMessage findPeopleByCardCode(String cardNumber){
        Map<String, Object> param = new HashedMap();
        param.put("fpdIdCardCode", cardNumber);
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        Object obj = null;
        try {
            obj = this.getService().findPeopleByCardCode(param);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        ajaxMessage.setSuccess(flag);
        ajaxMessage.setObj(obj);
        return ajaxMessage;
    }

    @RequestMapping(value = "/findCarByCarIdnty")
    @ResponseBody
    @Logger(action = "zc", logger ="" )
    public AjaxMessage findCarByCarIdnty(String fcdCarLcnsIdnty){
        Map<String, Object> param = new HashedMap();
        param.put("fcdCarLcnsIdnty", fcdCarLcnsIdnty);
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        Object obj = null;
        try {
            obj = this.getService().findCarByCarIdnty(param);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        ajaxMessage.setSuccess(flag);
        ajaxMessage.setObj(obj);
        return ajaxMessage;
    }

    /**
     * 分页查询车辆信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCarList", method = RequestMethod.POST)
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询外来车辆信息")
    public Map<String, Object> getCarList(
    		@RequestParam(value="frType", defaultValue="", required=false) String frType, 
    		@RequestParam(value="date", defaultValue="", required=false) String date, 
    		@RequestParam(value="fcdCarLcnsIdnty", defaultValue="",required=false) String fcdCarLcnsIdnty) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("frType", frType);
        param.put("fcdCarLcnsIdnty", fcdCarLcnsIdnty);
        if(date.equals("1")){
            param.put("frTime", Util.toStr(Util.DF_DATE));
        }

        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = this.getService().getCarList(param, pageRequest);
        return DataUtils.pageToMap(page);
    }

    /**
     * 分页查询人员信息
     * @param request
     * @return
     */
    @RequestMapping(value = "/getPeopleList")
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询外来人员信息")
    public Map<String, Object> getPeopleList(HttpServletRequest request){
        Map<String, Object> param = new HashMap<>();
        PageRequest pageRequest = buildPageRequest();

        String flag = request.getParameter("flag");
        if (!org.springframework.util.StringUtils.isEmpty(flag)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd 00:00:00");
            String format = simpleDateFormat.format(new Date());
            param.put("beginTime", Timestamp.valueOf(format));
        }

        Page<Map<String, Object>> page = this.getService().getPeopleList(param, pageRequest);
        return DataUtils.pageToMap(page);
    }

    @RequestMapping(value = "/findLastRow")
    @ResponseBody
    public AjaxMessage findLastRow(HttpServletRequest request){
        AjaxMessage ajaxMessage = new AjaxMessage();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("frCusNumber", request.getParameter("frCusNumber"));
        boolean flag = false;
        Object obj = null;
        try {
            obj = this.getService().findLastRow(paramMap);
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        ajaxMessage.setSuccess(flag);
        ajaxMessage.setObj(obj);
        return ajaxMessage;
    }

}
