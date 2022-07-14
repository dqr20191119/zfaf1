package com.cesgroup.prison.aqfh.zfcjfh.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.aqfh.zfcjfh.dao.ZfcjfhMapper;
import com.cesgroup.prison.aqfh.zfcjfh.entity.ZfcjfhEntity;
import com.cesgroup.prison.aqfh.zfcjfh.service.ZfcjfhService;
import com.cesgroup.prison.utils.DataUtils;

/**
* @author lihong
* @date 创建时间：2020年6月4日 上午10:51:08
* 类说明:
*/
@Controller
@RequestMapping(value = "/aqfh/zfcjfh")
public class ZfcjfhController  extends BaseEntityDaoServiceCRUDController<ZfcjfhEntity, String, ZfcjfhMapper, ZfcjfhService> {

	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("aqfh/zfcjfh/index");
		return mv;
	}
	
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String,Object> seachData(ZfcjfhEntity zfcjfhEntity,HttpServletRequest request, HttpServletResponse response)throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<ZfcjfhEntity> pageInfo = (Page<ZfcjfhEntity>) this.service.findList(zfcjfhEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);
	}
	
	
	@RequestMapping(value = "/inItDutyData")
	@ResponseBody
	public AjaxResult inItDutyData(HttpServletRequest request, HttpServletResponse response)throws Exception {
		AjaxResult  ajaxResult = null;
		try {
			ajaxResult = this.service.inItDutyData();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("发生异常");
		}
		return ajaxResult;
	}
	
	@RequestMapping(value = "/updateById")
	@ResponseBody
	public AjaxResult updateById(ZfcjfhEntity zfcjfhEntity,HttpServletRequest request, HttpServletResponse response)throws Exception {
		try {
			this.service.updateById(zfcjfhEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("更新发生异常");
		}
		return AjaxResult.success();
	}
}
