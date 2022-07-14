package com.cesgroup.prison.aqfxyp.txzs.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.aqfxyp.txzs.dao.TxzsMapper;
import com.cesgroup.prison.aqfxyp.txzs.entity.Txzs;
import com.cesgroup.prison.aqfxyp.txzs.service.TxzsService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;

 
@Controller
@RequestMapping("/aqfxyp/txzs")
public class TxzsController extends  BaseEntityDaoServiceCRUDController<Txzs, String, TxzsMapper, TxzsService> {
	
	public String getJyId() {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String cusNumber = userBean.getCusNumber();
		return cusNumber;
	}
	
	@RequestMapping(value = "/searchFxgk")
	@ResponseBody
	public AjaxResult searchFxgk(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String jyId = getJyId();
			String wgOne = "";
			String wgTwo ="";
			String wgThree = "";
			List list = this.getService().getfxgk(jyId, wgOne, wgTwo, wgThree);
			return AjaxResult.success(list);
		}catch(Exception e){
			return AjaxResult.error("初始化风险概况图发生异常");
		}
		
	}
	
	
	@RequestMapping(value = "/getPies")
	@ResponseBody
	public AjaxResult getPies(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String jyId = getJyId();
			String wgOne = "";
			String wgTwo ="";
			String wgThree = "";
			List list = this.getService().getPies(jyId, wgOne, wgTwo, wgThree);
			return AjaxResult.success(list);
		}catch(Exception e){
			return AjaxResult.error("风险评估图发生异常");
		}
		
	}
	
	@RequestMapping(value = "/getBars")
	@ResponseBody
	public AjaxResult getBars(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String jyId = getJyId();
			String wgOne = "";
			String wgTwo ="";
			String wgThree = "";
			List list = this.getService().getBars(jyId, wgOne, wgTwo, wgThree);
			return AjaxResult.success(list);
		}catch(Exception e){
			return AjaxResult.error("风险指向图发生异常");
		}
		
	}
	
	@RequestMapping(value = "/fxfbData")
	@ResponseBody
	public AjaxResult fxfbData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String jyId = getJyId();
			String wgOne = "";
			String wgTwo ="";
			String wgThree = "";
			List list = this.getService().fxfbData(jyId, wgOne, wgTwo, wgThree);
			return AjaxResult.success(list);
		}catch(Exception e){
			e.printStackTrace();
			return AjaxResult.error("风险分布图发生异常");
		}
		
	}

	@RequestMapping(value = "/fxqsData")
	@ResponseBody
	public AjaxResult fxqsData(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String jyId = getJyId();
			String wgOne = "";
			String wgTwo ="";
			String wgThree = "";
			String year="2019";
			List list = this.getService().getfxqs(year,jyId, wgOne, wgTwo, wgThree);
			return AjaxResult.success(list);
		}catch(Exception e){
			e.printStackTrace();
			return AjaxResult.error("风险趋势图发生异常");
		}
		
	}
	
	@RequestMapping(value = "/wgfb")
	@ResponseBody
	public AjaxResult wgfb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String jyId = getJyId();
			String wgOne = "";
			String wgTwo ="";
			String wgThree = "";
			String type = request.getParameter("type");
			List list = this.getService().wgfb(type,jyId, wgOne, wgTwo, wgThree);
			return AjaxResult.success(list);
		}catch(Exception e){
			e.printStackTrace();
			return AjaxResult.error("风险分布图发生异常");
		}
		
	}
	
	
	/**
	 * 获取警囚比图形数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/jqb")
	@ResponseBody
	public AjaxResult jqb(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			List list = this.getService().getJqb();
			return AjaxResult.success(list);
		}catch(Exception e){
			e.printStackTrace();
			return AjaxResult.error("加载警囚比图发生异常");
		}
		
	}
	
	/**
	 * 获取实时警力图形数据
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/ssjl")
	@ResponseBody
	public AjaxResult ssjl(HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			Map<String, Object> map = this.getService().getSssjl();
			return AjaxResult.success(map);
		}catch(Exception e){
			e.printStackTrace();
			return AjaxResult.error("加载实时警力图形发生异常");
		}
		
	}

}
