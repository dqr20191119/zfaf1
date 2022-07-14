package com.cesgroup.prison.foreign.web;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.foreign.dao.ForeignCarDtlsMapper;
import com.cesgroup.prison.foreign.dto.ForeignCarDtlsDto;
import com.cesgroup.prison.foreign.entity.ForeignCarDtls;
import com.cesgroup.prison.foreign.entity.ForeignRegister;
import com.cesgroup.prison.foreign.service.ForeignCarDtlsService;
import com.cesgroup.prison.utils.DataUtils;


@Controller
@RequestMapping("/foreignCar")
public class ForeignCarDtlsController extends BaseEntityDaoServiceCRUDController<ForeignCarDtls, String, ForeignCarDtlsMapper, ForeignCarDtlsService> {
	/**
	 * 外来车辆信息绑定
	 * @param webDataBinder
	 */
	@InitBinder("foreignCarDtls")
    public void foreignCarDtlsBinder(WebDataBinder webDataBinder){
        webDataBinder.setFieldDefaultPrefix("foreignCarDtls.");
    }
	
	/**
	 * 外来车辆登记信息绑定
	 * @param webDataBinder
	 */
	@InitBinder("foreignRegister")
    public void foreignRegisterBinder(WebDataBinder webDataBinder){
        webDataBinder.setFieldDefaultPrefix("foreignRegister.");
    }
	
	@RequestMapping("/save")
	public ModelAndView save(String id,String flag){
		ModelAndView mv = new ModelAndView("foreign/foreignCarSave");
		if(StringUtils.isNotEmpty(id)) {
			mv.addObject("id",id);
		}
		if(StringUtils.isNotEmpty(flag)) {
			mv.addObject("flag",flag);
		}
		return mv;
	}
	
	@RequestMapping("/detail")
	public ModelAndView detail(String id){
		ModelAndView mv = new ModelAndView("foreign/foreignCarDetail");
		if(StringUtils.isNotEmpty(id))
			mv.addObject("id",id);
		return mv;
	}
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("foreign/foreignCarIndex");
		return mv;
	}
	
	@RequestMapping("/list")
	public ModelAndView list(){
		ModelAndView mv = new ModelAndView("foreign/foreignCarList");
		return mv;
	}

	/**
	 * 保存、更新车辆出入
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveForeignCarDtls", method = RequestMethod.POST)
	@ResponseBody
	@Logger(action = "保存、更新车辆出入", logger = "保存、更新车辆出入信息")
	public AjaxMessage saveForeignCarDtls(ForeignCarDtls foreignCarDtls, ForeignRegister foreignRegister) throws Exception {
		try {
			ForeignCarDtls entity = this.getService().saveForeignCarDtls(foreignCarDtls, foreignRegister);
			return new AjaxMessage(true,entity,"操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxMessage(false,null,"系统出错");
		}
	}

	/**
	 * 删除车辆出入
	 * 
	 * @return
	 */
	@RequestMapping(value = "/deleteByIds")
	@ResponseBody
	@Logger(action = "删除车辆出入", logger = "删除车辆出入")
	public AjaxMessage deleteByIds(String[] ids) {
		try {
			int num = this.getService().deleteByIds(Arrays.asList(ids));
			if(num > 0){
				return new AjaxMessage(true,null,"删除成功");
			}else{
				return new AjaxMessage(false,null,"删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxMessage(false,null,"系统出错");
		}
	}

	@RequestMapping(value = "/findByPage")
	@ResponseBody
	@Logger(action = "车辆出入分页查询", logger = "车辆出入分页查询")
	public Map<String, Object> findByPage(String obj) {
		try {
			JSONObject jsonObject = JSONObject.parseObject(obj);
			Map<String, Object> map = jsonObject;
			if(map == null) {
				map = new HashMap<String,Object>();
			}
			PageRequest pageRequest = buildPageRequest();
			Page<ForeignCarDtlsDto> page = this.getService().queryWithPageByCondition(map, pageRequest);
			return DataUtils.pageToMap(page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 查找车辆出入
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findById")
	@ResponseBody
	@Logger(action = "查找车辆出入", logger = "查找车辆出入")
	public AjaxMessage findById(String id) {
		try {
			Map<String,Object> map= this.getService().findById(id);
			return new AjaxMessage(true,map,"success");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxMessage(false,null,"系统出错");
		}
	}
	

	/**
	 * 根据车牌号查找车辆出入
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findByCarLcnsIdnty")
	@ResponseBody
	@Logger(action = "根据车牌号查找车辆出入", logger = "根据车牌号查找车辆出入")
	public AjaxMessage findByCarLcnsIdnty(String carLcnsIdnty) {
		try {
			Map<String,Object> map= this.getService().findByCarLcnsIdnty(carLcnsIdnty);
			return new AjaxMessage(true,map,"success");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxMessage(false,null,"系统出错");
		}
	}

}
