package com.cesgroup.prison.bfcf.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.bfcf.dao.BfCfREcordMapper;
import com.cesgroup.prison.bfcf.entity.BfCfREcordEntity;
import com.cesgroup.prison.bfcf.service.BfCfREcordService;
@Controller
@RequestMapping(value = "/bfcf")
public class BfCfREcordController extends BaseEntityDaoServiceCRUDController<BfCfREcordEntity, String, BfCfREcordMapper, BfCfREcordService> {
	
	/**
	 * 布防撤防     type  1 布防  2.撤防   3.全部布防  4.全部撤防
	 * 			bjqId 
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "/startBfCf")
	@ResponseBody
	public AjaxResult startBfCf(@RequestParam Map<String,Object> map) {
		try {
			this.getService().startBfCf(map);
			if("1".equals(map.get("type").toString())) 
				return AjaxResult.success("布防指令发送成功");
			if("2".equals(map.get("type").toString()))
				return AjaxResult.success("撤防指令发送成功");
			if("3".equals(map.get("type").toString()))
				return AjaxResult.success("一键布防指令发送成功");
			if("4".equals(map.get("type").toString()))
				return AjaxResult.success("一键撤防指令发送成功");
		} catch (BusinessLayerException e) {
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("布防或撤防令发送失败");
		}
		return AjaxResult.success();
	}
	
	/**
	 * 布防控制窗口打开
	 * @return
	 */
	@RequestMapping(value = "/controlMultiDialog")
	public ModelAndView controlMultiDialog(@RequestParam(value = "bjqId", defaultValue = "", required = false) String bjqId) {
		ModelAndView mv = new ModelAndView("bfcfCtrl/bfcf_control_dialog");
		mv.addObject("bjqId", bjqId);
		return mv;
	}
	
	
	
}
