package com.cesgroup.prison.viewPeople.web;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.viewPeople.dao.ViewPeopleMapper;
import com.cesgroup.prison.viewPeople.entity.ViewPeople;
import com.cesgroup.prison.viewPeople.service.IViewPeopleService;
import com.cesgroup.prison.viewPeople.service.impl.ViewPeopleServiceImpl;
import com.cesgroup.prison.viewPeople.vo.ViewPeopleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/viewPeople")
public class ViewPeopleController extends BaseEntityDaoServiceCRUDController<ViewPeople, String, ViewPeopleMapper,ViewPeopleServiceImpl> {
    @Autowired
    IViewPeopleService iViewPeopleService;

    /**
     * 登录页
     * @return
     */
    @RequestMapping(value = "/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("/viewPeople/index");
        return mv;
    }

    /**
     * 根据区域获取部门
     * @return
     */
    @RequestMapping(value = "/getPeopleInfo")
    @ResponseBody
    @Logger(action = "查询区域", logger = "根据区域编号查询部门信息")
    public List<ViewPeopleVo> getDepartInfo(String cusNumber, String areaId) throws WebUIException {
        return iViewPeopleService.findByCusNumberAndAreaId(cusNumber, areaId);
    }

    /**
     * 保存信息
     * @return
     * @throws Exception
     * @throws ParseException
     */
    @RequestMapping(value = "/save")
    @ResponseBody
    @Logger(action = "添加人员视角信息", logger = "添加人员视角信息")
    public int save(String objs) throws ParseException, Exception{
        return this.iViewPeopleService.insertByBatch(objs);
    }

    @RequestMapping(value="/findHavedAreatree")
    @ResponseBody
    public List<Map<String, Object>> findHavedAreatree(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cusNumber", request.getParameter("cusNumber"));

        return iViewPeopleService.findHavedAreatree(paramMap);
    }

}
