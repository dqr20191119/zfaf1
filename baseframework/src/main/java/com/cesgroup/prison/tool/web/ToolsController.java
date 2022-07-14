package com.cesgroup.prison.tool.web;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.tool.dao.TKhLdgjMapper;
import com.cesgroup.prison.tool.entity.TKhLdgj;
import com.cesgroup.prison.tool.service.ToolService;
import com.cesgroup.prison.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ Author     ：zhouzc.
 * @ Date       ：Created in 18:04 2019/5/26
 * @ Description：劳具发放
 */
@Controller
@RequestMapping(value = "/tool/all")
public class ToolsController extends BaseEntityDaoServiceCRUDController<TKhLdgj, String, TKhLdgjMapper, ToolService> {

    @Resource
    private ToolService toolService;


    /**
     * 查询劳具数量
     */
    @RequestMapping(value = "getToolNum")
    @ResponseBody
    public Map<String, Object> getToolNum(String cusNumber) {
        Map<String, Object> map = new HashMap<>();
        Integer toolNum = toolService.getToolOpenNum(cusNumber);
        map.put("toolNum", toolNum);
        return map;
    }
    /**
     * 打开窗口 跳转到list页面
     * @return
     */
    @RequestMapping(value = "/openDialog")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("tool/list");
        return mv;
    }

    /**
     * 劳具列表页
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询劳作信息")
    public Map<String, Object> listAll(
            @RequestParam(value="name", defaultValue="", required=false) String name
            ,@RequestParam(value="prisonName", required = false, defaultValue = "") String prisonName
            ,@RequestParam(value="status", required = false, defaultValue = "808") String status
    ) throws Exception {
    	
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("prisonName", prisonName);
        paramMap.put("name", name);
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = (Page<Map<String, Object>>) this.getService().listAll(paramMap, pageRequest);

        return DataUtils.pageToMap(page);
    }
}
