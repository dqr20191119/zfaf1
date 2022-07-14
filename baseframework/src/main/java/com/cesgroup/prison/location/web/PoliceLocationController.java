package com.cesgroup.prison.location.web;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Base64Util;
import com.cesgroup.prison.location.dao.PoliceLocationDao;
import com.cesgroup.prison.location.entity.PoliceLocation;
import com.cesgroup.prison.location.service.PoliceLocationService;
import com.cesgroup.prison.utils.DataUtils;

/**
 * Description: 民警所在位置控制器
 * 
 * @author lincoln.cheng
 * 
 * 2019年02月22日
 */
@Controller
@RequestMapping("/policeLocation")
public class PoliceLocationController extends BaseEntityDaoServiceCRUDController<PoliceLocation, String, PoliceLocationDao, PoliceLocationService> {

    @RequestMapping(value = "/openDialog")
    public ModelAndView openDialog(@RequestParam(value="deptName", defaultValue="", required=false) String deptName) {
        ModelAndView mv = new ModelAndView("/location/police_in_prison");
		mv.addObject("deptName", Base64Util.decodeString(deptName, 2));
        return mv;
    }

    @RequestMapping(value = "/openSwsbCountDialog")
    public ModelAndView openPrisonAreaDialog() {
        ModelAndView mv = new ModelAndView("/location/swsb_count_in_prison");
        return mv;
    }

    /**
     * 分页查询车辆信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询当前在监民警信息")
    public Map<String, Object> search (
    		@RequestParam(value="policeNo", defaultValue="", required=false) String policeNo, 
    		@RequestParam(value="policeName", defaultValue="",required=false) String policeName,
    		@RequestParam(value="deptName", defaultValue="", required=false) String deptName) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("policeNo", policeNo);
        param.put("policeName", policeName);
        param.put("deptName", deptName);

        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = this.getService().queryWithPage(param, pageRequest);
        return DataUtils.pageToMap(page);
    }

    /**
     * 分页查询车辆信息
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/swsbSearch", method = RequestMethod.POST)
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询各监区罪犯与民警的生物识别数据")
    public Map<String, Object> swsbSearch (@RequestParam(value="deptName", defaultValue="", required=false) String deptName) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("deptName", deptName);

        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = this.getService().querySwsbWithPage(param, pageRequest);
        return DataUtils.pageToMap(page);
    }
}
