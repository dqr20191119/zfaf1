package com.cesgroup.prison.zfjcj.web;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.zfjcj.dao.ZfjcjMapper;
import com.cesgroup.prison.zfjcj.entity.ZfjcjEntity;
import com.cesgroup.prison.zfjcj.service.IZfjcjService;
import com.cesgroup.prison.zfjcj.vo.ZfjcjVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
@RequestMapping(value = "/zfjcj")
public class ZfjcjController extends BaseEntityDaoServiceCRUDController<ZfjcjEntity, String, ZfjcjMapper, IZfjcjService> {
    @Autowired
    IZfjcjService service;

    @RequestMapping(value = "/list")
    public ModelAndView getList(HttpServletRequest request, HttpServletResponse response){
        return new ModelAndView("zfjcj/list");
    }

    /**
     *
     * @param entity
     * @return 分页查询离监信息
     * @throws Exception
     *
     **/


    @RequestMapping(value = "/listAll")
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询离监信息")
    public Map<String, Object> listAll(ZfjcjVo entity) throws Exception{
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = service.listAll(entity, pageRequest);
        return DataUtils.pageToMap(page);
    }

    @RequestMapping(value = "/checkInfo")
    @ResponseBody
    public void checkInfo(@RequestBody ZfjcjVo zfjcjVo){
        service.checkInfo(zfjcjVo);
    }

}
