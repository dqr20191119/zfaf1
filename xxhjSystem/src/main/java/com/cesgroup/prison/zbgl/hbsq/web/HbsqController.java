package com.cesgroup.prison.zbgl.hbsq.web;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.zbgl.rygl.entity.RyglEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.util.StringUtil;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xtgl.service.PlaneLayerService;
import com.cesgroup.prison.zbgl.hbsq.dao.HbsqMapper;
import com.cesgroup.prison.zbgl.hbsq.dto.ZbrxxDto;
import com.cesgroup.prison.zbgl.hbsq.entity.HbsqEntity;
import com.cesgroup.prison.zbgl.hbsq.service.HbsqService;
import com.cesgroup.prison.zbgl.rygl.service.RyglService;
import com.cesgroup.prison.zbgl.todayDuty.dao.TodayDutyDao;
import com.cesgroup.prison.zbgl.todayDuty.dto.TodayDutyDto;

import cn.hutool.core.util.StrUtil;

/**
 * @author lihong
 * @date 创建时间：2020年5月15日 上午11:34:16
 * 类说明:
 */
@Controller
@RequestMapping("/zbgl/hbsq")
public class HbsqController extends BaseEntityDaoServiceCRUDController<HbsqEntity, String, HbsqMapper, HbsqService>  {
	private final Logger logger = LoggerFactory.getLogger(HbsqController.class);

	@Resource
	private PlaneLayerService planeLayerService;

	@Resource
	private RyglService ryglService;

	@Resource
	private TodayDutyDao todayDutyDao;

	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("zbgl/hbsq/index");
		return mv;
	}

	@RequestMapping(value = "/toHbspIndex")
	public ModelAndView toHbspIndex(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView mv = new ModelAndView("zbgl/hbsq/hbsp_index");
		return mv;
	}

	@RequestMapping(value = "/toHbspEdit")
	public ModelAndView toHbspEdit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("zbgl/hbsq/hbsp_edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}

	@RequestMapping(value = "/view")
	public ModelAndView toview(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("zbgl/hbsq/view");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}

	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		ModelAndView mv = new ModelAndView("zbgl/hbsq/edit");
		mv.addObject("id", request.getParameter("id"));
		mv.addObject("sqrName",user.getUserName());
		mv.addObject("sqDate",DateUtils.getCurrentDate(false));
		return mv;
	}


	@RequestMapping(value = "/getById")
	@ResponseBody
	public HbsqEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {

		return this.service.selectOne(id);
	}

	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(HbsqEntity hbsqEntity,@RequestParam(value = "type", required = false,defaultValue = "") String type,   HttpServletRequest request, HttpServletResponse response) throws Exception  {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		// todo 此处零时设置为能看到此模块就可以审批
//			if("1".equals(type)) {//审批人查询审批列表
//				hbsqEntity.setSprName(user.getRealName());
//			}
		//	bcglEntity.setDorCusNumber(user.getOrgCode());
		Page<HbsqEntity> pageInfo = (Page<HbsqEntity>) this.service.findList(hbsqEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);
	}


	@RequestMapping(value = "/deleteById")
	@ResponseBody
	public AjaxResult deleteById(HbsqEntity hbsqEntity, HttpServletRequest request, HttpServletResponse response) throws Exception  {
		try {
			this.service.delete(hbsqEntity.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("删除失败");
		}
		return AjaxResult.success("删除成功");
	}


	/**
	 * 申请保存
	 */
	@RequestMapping(value = "/saveData")
	@ResponseBody
	public AjaxResult saveData(HbsqEntity hbsqEntity,HttpServletRequest request, HttpServletResponse response) {
		try {
			List<TodayDutyDto> todayDuty = todayDutyDao.getTodayDuty(hbsqEntity.getCusNumber(), "指挥长", hbsqEntity.getDutyDate());//查询当天值班的指挥长
			if(todayDuty.size()>0) {
				// todo 因为表和系统用户表无对应关系，此处设 user_name
				hbsqEntity.setSpr(todayDuty.get(0).getDbdStaffName());
				hbsqEntity.setSprName(todayDuty.get(0).getDbdStaffName());

				UserBean user = AuthSystemFacade.getLoginUserInfo();
				hbsqEntity.setSqr(user.getUserId());
				if(hbsqEntity.getId()!=null && hbsqEntity.getId()!="") {
					this.service.updateById(hbsqEntity);
				}else {
					this.service.insert(hbsqEntity);
				}
				if(!StrUtil.hasEmpty(hbsqEntity.getFiles())) {
					planeLayerService.saveFile(hbsqEntity.getId(), hbsqEntity.getFiles());//保存文件
				}
				return AjaxResult.success("插入成功");
			}
			return AjaxResult.error("无法查询当天值班信息，请确认值班已经编排并发布。");
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("插入失败");
		}
	}


	/**
	 *
	 * 检查当前用户值班岗位是否为指挥长
	 * @param
	 * @param
	 * @return
	 *
	 */
	@RequestMapping(value = "/checkIsZhz")
	@ResponseBody
	public AjaxResult checkIsZhz(@RequestParam("dutyDate") String dutyDate)  {
		//List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// UserBean user = AuthSystemFacade.getLoginUserInfo();
		try {
			AjaxResult ajaxResult = this.service.checkIsZhz(dutyDate);
			return ajaxResult;
		}catch (Exception e){
			e.printStackTrace();
			return  AjaxResult.error();
		}
	}


	/**
	 * 更新状态  审批状态
	 */
	@RequestMapping(value = "/updateZtOrSpzt")
	@ResponseBody
	public AjaxResult updateZtOrSpzt(HbsqEntity hbsqEntity,HttpServletRequest request, HttpServletResponse response) {
		try {
			if(hbsqEntity.getSpyj()!=null && hbsqEntity.getSpyj()!="") {
				//进入审批流程
				if("1".equals(hbsqEntity.getSpyj())) {//同意,进行换班
					this.service.tyHbsq(hbsqEntity.getId());
				}
				hbsqEntity.setSpDate(DateUtils.getCurrentDate(true));
			}

			this.service.updateById(hbsqEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("更新失败");
		}

		return AjaxResult.success("更新成功");
	}
	/**
	 * 检查值班人在指定的班次时间是否有值班信息
	 * @param zbrxxDto
	 * @return
	 */
	@RequestMapping("/checkZbrIsZbbp")
	@ResponseBody
	public AjaxResult checkZbrIsZbbp(ZbrxxDto zbrxxDto ) {
		String msg = "";
		try {
			Integer count = this.service.checkZbrIsZbbp(zbrxxDto);
			if(count>0) {
				msg = "true";
			}else {
				msg = "false";
			}
		} catch (Exception e) {
			e.printStackTrace();
			AjaxResult.error("发生异常");
		}

		return AjaxResult.success(msg);
	}

}
