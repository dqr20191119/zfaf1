package com.cesgroup.prison.xxhj.zfsjsb.sblxsz.web;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.dao.SblxMapper;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity.SblxEntity;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity.SblxVo;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.service.SblxService;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.service.SblxServiceImpl;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/xxhj/zfsjsb/sblx")
public class SblxController extends BaseEntityDaoServiceCRUDController<SblxEntity, String, SblxMapper, SblxServiceImpl> {

    @Resource
    private SblxService sblxService;

    /**
     *
     * @param entity
     * @return 分页查询罪犯上报数据类型信息
     * @throws Exception
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询罪犯上报数据类型信息")
    public Map<String, Object> listAll(SblxEntity entity) throws Exception{
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = service.listAll(entity, pageRequest);
        return DataUtils.pageToMap(page);
    }

    @RequestMapping("/save")
    public ModelAndView save(String id){
        ModelAndView mv = new ModelAndView("/xxhj/zfsjsb/sblxsz/edit");
        if(StringUtils.isNotEmpty(id)) {
            mv.addObject("id", id);
        }
        return mv;
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    @Logger(action = "登记", logger = "罪犯数据上报类型登记及部门设置")
    public AjaxMessage add(@RequestBody SblxVo sblxVo, HttpServletRequest request) {
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        String msg = null;
        try {
            service.addInfo(sblxVo,request);
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

    /**
     * 根据Id查询
     * @param request
     * @return
     */
    @RequestMapping(value = "/SblxInfoById")
    @ResponseBody
    public SblxEntity findSblxInfoById(HttpServletRequest request){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", request.getParameter("id"));
        return service.findById(paramMap);
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
            service.deleteByIds(ids);
            msg = "删除成功";
        } catch (Exception e) {
            flag = false;
            msg = "删除失败";
        }
        ajaxMessage.setSuccess(flag);
        ajaxMessage.setMsg(msg);

        return ajaxMessage;

    }

}
