package com.cesgroup.prison.xxhj.zfsjsb.sjsb.web;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xxhj.zfsjsb.sjsb.dao.SjsbMapper;
import com.cesgroup.prison.xxhj.zfsjsb.sjsb.entity.SjsbEntity;
import com.cesgroup.prison.xxhj.zfsjsb.sjsb.service.SjsbService;
import com.cesgroup.prison.xxhj.zfsjsb.sjsb.service.SjsbServiceImpl;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/xxhj/zfsjsb/sjsb")
public class SjsbController extends BaseEntityDaoServiceCRUDController<SjsbEntity, String, SjsbMapper, SjsbServiceImpl> {

    @Resource
    private SjsbService sjsbService;

    @RequestMapping(value = "/toIndex")
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/xxhj/zfsjsb/sjsb/index");
        return mv;
    }

    @RequestMapping(value = "/toSjhz")
    public ModelAndView toSjhz(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/xxhj/zfsjsb/sjsb/sjhz");
        return mv;
    }

    /**
     *
     * @param entity
     * @return 分页查询罪犯上报数据信息
     * @throws Exception
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询罪犯上报数据信息")
    public Map<String, Object> listAll(SjsbEntity entity) throws Exception{
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = sjsbService.listAll(entity, pageRequest);
        return DataUtils.pageToMap(page);
    }

    @RequestMapping("/save")
    public ModelAndView save(String flag, String cprCusNumber, String cprTime, String cprDprtmntId, String cprRemark) throws UnsupportedEncodingException {
        ModelAndView mv = new ModelAndView("/xxhj/zfsjsb/sjsb/edit");
        if(StringUtils.isNotEmpty(flag)) {
            mv.addObject("flag", flag);
        }
        if(StringUtils.isNotEmpty(cprTime)) {
            mv.addObject("cprTime", cprTime);
        }
        if(StringUtils.isNotEmpty(cprDprtmntId)) {
            mv.addObject("cprDprtmntId", cprDprtmntId);
        }
        if(StringUtils.isNotEmpty(cprRemark)) {
            cprRemark=new String(cprRemark.getBytes("iso8859-1"),"UTF-8");
            mv.addObject("cprRemark", cprRemark);
        }
        return mv;
    }


    /**
     * 根据汇报期数和部门统计数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/listDetail")
    @ResponseBody
    public Map<String, Object> listDetail(HttpServletRequest request){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cprTime", request.getParameter("cprTime"));
        paramMap.put("cprDprtmntId", request.getParameter("cprDprtmntId"));
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = sjsbService.findByTimeAndDprtmntId(paramMap);
        return DataUtils.pageToMap(page);
    }


    /**
     * 根据汇报期数统计数据
     * @param request
     * @return
     */
    @RequestMapping(value = "/getSjhz")
    @ResponseBody
    public Map<String, Object> getSjhz(HttpServletRequest request){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cprTime", request.getParameter("cprTime"));
        paramMap.put("cprCusNumber", request.getParameter("cprCusNumber"));
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = sjsbService.getCntByTime(paramMap);
        return DataUtils.pageToMap(page);
    }


    @RequestMapping(value = "/removeInfo")
    @ResponseBody
    public AjaxMessage removeInfo(HttpServletRequest request){
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        String msg = "";
        try{
            flag = true;
            List<SjsbEntity> infos=(List<SjsbEntity>) JSONArray.toList(JSONArray.fromObject(request.getParameter("infos")), SjsbEntity.class);
            sjsbService.deleteInfo(infos);
            msg = "删除成功";
        } catch (Exception e) {
            flag = false;
            msg = "删除失败";
        }
        ajaxMessage.setSuccess(flag);
        ajaxMessage.setMsg(msg);

        return ajaxMessage;
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    @Logger(action = "登记", logger = "罪犯数据上报")
    public AjaxMessage add(@RequestBody Map<String, Object> map, HttpServletRequest request) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        String msg = null;
        try {
            service.addInfo(map,request);
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

    @RequestMapping(value = "/getReportDprt")
    @ResponseBody
    @Logger(action = "登记", logger = "上报部门统计")
    public AjaxMessage getReportDprt(HttpServletRequest request) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("cprTime", request.getParameter("cprTime"));
        paramMap.put("cprCusNumber", request.getParameter("cprCusNumber"));
        boolean flag = false;
        String msg = null;
        try {
            ajaxMessage.setObj(service.getReportDprt(paramMap, request));
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


}
