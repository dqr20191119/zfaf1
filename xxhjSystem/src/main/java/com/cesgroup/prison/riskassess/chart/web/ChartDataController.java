package com.cesgroup.prison.riskassess.chart.web;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.riskassess.chart.dao.ChartDataDao;
import com.cesgroup.prison.riskassess.chart.dto.MapDto;
import com.cesgroup.prison.riskassess.chart.entity.ChartData;
import com.cesgroup.prison.riskassess.chart.service.ChartDataService;

/**
 * Description: 图表展示控制器
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
@Controller
@RequestMapping("/riskAssess/chart")
public class ChartDataController extends BaseEntityDaoServiceCRUDController<ChartData, String, ChartDataDao, ChartDataService> {
	/**
	 * Description: 风险分布图数据
	 * @return
	 */
    @RequestMapping(value = "fxfbData")
    @ResponseBody
	public AjaxResult fxfbData() {
    	try {
    		List<MapDto> fxfbList = this.getService().queryFxfbByUserRole("role1");
    		return AjaxResult.success(fxfbList);
    	} catch (Exception e) {
    		return AjaxResult.error("初始化风险分布图发生异常");
    	}
	}
    
    /**
	 * Description: 风险趋势图数据
	 * @return
	 */
    @RequestMapping(value = "fxqsData")
    @ResponseBody
	public AjaxResult fxqsData() {
    	try {
    		List<MapDto> fxqsList = this.getService().queryFxqsByUserRole("role1");
    		return AjaxResult.success(fxqsList);
    	} catch (Exception e) {
    		return AjaxResult.error("初始化风险趋势图发生异常");
    	}
	}
    
    /**
	 * Description: 风险评估图数据
	 * @return
	 */
    @RequestMapping(value = "fxpgData")
    @ResponseBody
	public AjaxResult fxpgData(@RequestParam(value="category", required=false) String category) {
    	try {
    		Map<String, Object> fxpgMap = this.getService().queryFxpgByCategoryAndUserRole(category, "role1");
    		return AjaxResult.success(fxpgMap);
    	} catch (Exception e) {
    		return AjaxResult.error("初始化风险评估图发生异常");
    	}
	}
    
    /**
	 * Description: 风险指向图数据
	 * @return
	 */
    @RequestMapping(value = "fxzxData")
    @ResponseBody
	public AjaxResult fxzxData(@RequestParam(value="category", required=false) String category) {
    	try {
    		List<MapDto> fxpgList = this.getService().queryFxzxByCategoryAndUserRole(category, "role1");
    		return AjaxResult.success(fxpgList);
    	} catch (Exception e) {
    		return AjaxResult.error("初始化风险指向图发生异常");
    	}
	}

    /**
	 * Description: 风险概况图数据
	 * @return
	 */
    @RequestMapping(value = "fxgkData")
    @ResponseBody
	public AjaxResult fxgkData() {
    	try {
    		List<MapDto> fxgkList = this.getService().queryFxgkByUserRole("role1");
    		return AjaxResult.success(fxgkList);
    	} catch (Exception e) {
    		return AjaxResult.error("初始化风险概况图发生异常");
    	}
	}
}
