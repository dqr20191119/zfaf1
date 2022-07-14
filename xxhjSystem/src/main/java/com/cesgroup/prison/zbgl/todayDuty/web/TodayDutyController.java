package com.cesgroup.prison.zbgl.todayDuty.web;

import java.util.HashMap;
import java.util.List;
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
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Base64Util;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.zbgl.todayDuty.dao.TodayDutyDao;
import com.cesgroup.prison.zbgl.todayDuty.dto.TodayDutyDto;
import com.cesgroup.prison.zbgl.todayDuty.entity.TodayDuty;
import com.cesgroup.prison.zbgl.todayDuty.service.TodayDutyService;

/**
 * 值班管理-今日值班控制类
 * 
 * @author lincoln.cheng
 *
 */
@Controller
@RequestMapping(value="/zbgl/todayDuty")
public class TodayDutyController extends BaseEntityDaoServiceCRUDController<TodayDuty, String, TodayDutyDao, TodayDutyService> {

    @RequestMapping(value = "/toIndex")
    public ModelAndView toIndex(
			@RequestParam(value="categoryName", defaultValue="", required=false) String categoryName,
			@RequestParam(value="modeName", defaultValue="", required=false) String modeName, 
			@RequestParam(value="orderName", defaultValue="", required=false) String orderName, 
			@RequestParam(value="deptCode", defaultValue="", required=false) String deptCode, 
			@RequestParam(value="dutyJobId", defaultValue="", required=false) String dutyJobId
			) {
        ModelAndView mv = new ModelAndView("/zbgl/todayDuty/index");
		/* mv.addObject("categoryName", Base64Util.decodeString(categoryName, 2)); */
		/* mv.addObject("modeName", Base64Util.decodeString(modeName, 2)); */
        mv.addObject("orderName", Base64Util.decodeString(orderName, 2));
		/* mv.addObject("deptCode", deptCode); */
		/* mv.addObject("dutyJobId", dutyJobId); */
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
			@RequestParam(value="categoryName", defaultValue="", required=false) String categoryName,
			@RequestParam(value="modeName", defaultValue="", required=false) String modeName, 
			@RequestParam(value="orderName", defaultValue="", required=false) String orderName, 
			@RequestParam(value="deptCode", defaultValue="", required=false) String deptCode, 
			@RequestParam(value="dutyJobId", defaultValue="", required=false) String dutyJobId, 
			@RequestParam(value="dbdStaffName", defaultValue="", required=false) String dbdStaffName) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("categoryName", categoryName);//值班名称类别
        param.put("modeName", modeName);//值班模板名称
        param.put("orderName", orderName);//班次名称
        param.put("deptCode", deptCode);//部门编号
        param.put("dutyJobId", dutyJobId);//岗位名称
        param.put("dbdStaffName", dbdStaffName);//值班人编号
		param.put("dutyDate", Util.toStr(Util.DF_DATE));

        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = this.getService().queryWithPage(param, pageRequest);
        return DataUtils.pageToMap(page);
    }

	/**
	 * 获取监狱领导今日值班列表
	 * 
	 * @param categoryName
	 * @param modelName
	 * @param orderName
	 * @return
	 */
	@RequestMapping(value="getJyldTodayDuty")
	@ResponseBody
	public AjaxResult getJyldTodayDuty(
			@RequestParam(value="categoryName", defaultValue="", required=false) String categoryName,
			@RequestParam(value="modeName", defaultValue="", required=false) String modeName, 
			@RequestParam(value="orderName", defaultValue="", required=false) String orderName) {
		try {
			Map<String, Object> paramMap = new HashMap<>();
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			if(user != null) {
				// 监狱编号
				paramMap.put("cusNumber", user.getCusNumber());
			}
			paramMap.put("categoryName", categoryName);
			paramMap.put("modeName", modeName);
			paramMap.put("orderName", orderName);
			paramMap.put("dutyDate", Util.toStr(Util.DF_DATE));
			
			// 值班人员列表
			List<TodayDutyDto> dutyList = this.getService().queryByCategoryNameAndModelNameAndOrderNameLike(paramMap);
			
			return AjaxResult.success(dutyList);
		} catch (Exception e) {
			return AjaxResult.error("查询监狱领导值班信息发生异常");
		}
	}
	/**
	 * 获取监狱领导今日值班列表
	 * 
	 * @param categoryName
	 * @param modelName
	 * @param orderName
	 * @return
	 */
	@RequestMapping(value="getZhzxTodayDuty")
	@ResponseBody
	public AjaxResult getZhzxTodayDuty(
			@RequestParam(value="categoryName", defaultValue="", required=false) String categoryName,
			@RequestParam(value="modeName", defaultValue="", required=false) String modeName, 
			@RequestParam(value="orderName", defaultValue="", required=false) String orderName) {
		try {
			Map<String, Object> paramMap = new HashMap<>();
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			if(user != null) {
				// 监狱编号
				paramMap.put("cusNumber", user.getCusNumber());
			}
			paramMap.put("categoryName", categoryName);
			paramMap.put("modeName", modeName);
			paramMap.put("orderName", orderName);
			paramMap.put("dutyDate", Util.toStr(Util.DF_DATE));
			
			// 值班人员列表
			List<TodayDutyDto> dutyList = this.getService().queryByCategoryNameAndModelNameAndOrderNameLike(paramMap);
			
			// 根据当天时间是否周末来过滤值班人员
			List<TodayDutyDto> newDutyList = this.getService().filterByIsWeekend(dutyList);
			
			return AjaxResult.success(newDutyList);
		} catch (Exception e) {
			return AjaxResult.error("查询指挥中心值班信息发生异常");
		}
	}
	/**
	 * 获取各监区今日值班列表
	 * 
	 * @param categoryName
	 * @param modeName
	 * @param parentDeptName
	 * @return
	 */
	@RequestMapping(value="getJqTodayDuty")
	@ResponseBody
	public AjaxResult getJqTodayDuty(
			@RequestParam(value="categoryName", defaultValue="", required=false) String categoryName,
			@RequestParam(value="modeName", defaultValue="", required=false) String modeName, 
			@RequestParam(value="parentDeptName", defaultValue="", required=false) String parentDeptName) {
		try {
			
			// 各监区值班人员列表
			List<Map<String, Object>> list = this.getService().queryByCategoryNameLikeAndModeNameLikeAndParentDeptName(categoryName, modeName, parentDeptName);
			
			return AjaxResult.success(list);
		} catch (Exception e) {
			return AjaxResult.error("查询各监区值班信息发生异常");
		}
	}

	/**
	 * 查询当前用户所在监区各个岗位的今日值班列表
	 * 
	 * @param deptCode
	 * @return
	 */
	@RequestMapping(value="getCurrentDeptTodayDuty")
	@ResponseBody
	public AjaxResult getCurrentDeptTodayDuty() {
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			if(user != null) {
				// 监区各值班岗位的今日值班人员列表
				List<Map<String, Object>> list = this.getService().queryByDeptCodeGroupByDutyJob(user.getDprtmntCode());
				
				return AjaxResult.success(list);
			} else {
				return AjaxResult.error("未查询当前用户所在监区");
			}
		} catch (Exception e) {
			return AjaxResult.error("查询监区各个岗位的今日值班信息发生异常");
		}
	}
	
	/**
	 * 获取今日值班的数据  现在统一修改为这个方法
	 * @return
	 */
	@RequestMapping("/getTodayDuty")
	@ResponseBody
	public AjaxResult getTodayDuty(@RequestParam(value="cusNumber", defaultValue="", required=false) String cusNumber,
								   @RequestParam(value="orderName", defaultValue="", required=false) String orderName) {
		try {
		List<TodayDutyDto> todayList = this.service.getTodayDuty(cusNumber,orderName);
		return AjaxResult.success(todayList);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("查询今日值班数据发生异常");
		}
		
	}
	
	/**
	 * 获取今日值班的数据  现在统一修改为这个方法
	 * @return
	 */
	@RequestMapping("/getTodayDutyList")
	@ResponseBody
	public Map<String, Object> getTodayDutyList(@RequestParam(value="cusNumber", defaultValue="", required=false) String cusNumber,
								   @RequestParam(value="orderName", defaultValue="", required=false) String orderName,
								   @RequestParam(value="dbdStaffName", defaultValue="", required=false) String dbdStaffName) throws Exception {
			PageRequest pageRequest = buildPageRequest();
			 Map<String, Object> param = new HashMap<String, Object>();
			 param.put("cusNumber", cusNumber);
			 param.put("orderName", orderName);
			 param.put("dbdStaffName", dbdStaffName);
			 param.put("dutyDate", DateUtils.getCurrentDate(false));
	        Page<Map<String, Object>> page = this.getService().getTodayDutyListWithPage(param, pageRequest);
	        return DataUtils.pageToMap(page);
		
	}
	
}
