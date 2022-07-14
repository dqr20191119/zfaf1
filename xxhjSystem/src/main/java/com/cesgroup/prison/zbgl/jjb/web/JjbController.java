package com.cesgroup.prison.zbgl.jjb.web;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.zbgl.jjb.dao.JjbMapper;
import com.cesgroup.prison.zbgl.jjb.entity.JjbEntity;
import com.cesgroup.prison.zbgl.jjb.entity.JjbRzglEntity;
import com.cesgroup.prison.zbgl.jjb.service.JjbRzglService;
import com.cesgroup.prison.zbgl.jjb.service.JjbService;

/**
* @author lihong
* @date 创建时间：2020年5月15日 上午9:59:12
* 类说明:
*/
@Controller
@RequestMapping(value = "/zbgl/jjb")
public class JjbController extends BaseEntityDaoServiceCRUDController<JjbEntity, String, JjbMapper, JjbService> {
	@Resource
	JjbRzglService jjbRzglService;
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("zbgl/jjb/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("zbgl/jjb/edit");
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		mv.addObject("cjrName", user.getRealName());
		mv.addObject("cjrq", DateUtils.getCurrentDate(false));
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	/**
	 * 进入交接班页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toJjbView")
	public ModelAndView toJjbView(@RequestParam("parma") String parma,@RequestParam("id") String id) {
		ModelAndView mv = new ModelAndView("zbgl/jjb/jjb_view");
		mv.addObject("parma", parma);
		mv.addObject("id", id);
		return mv;
	}
	
	/**
	 * 查看页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toView")
	public ModelAndView toView(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("zbgl/jjb/view");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}
	
	
	@RequestMapping(value = "/searchRzData")
	@ResponseBody
	public Map<String,Object> searchRzData(JjbEntity jjbEntity,HttpServletRequest request, HttpServletResponse response)throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<JjbRzglEntity> pageInfo = (Page<JjbRzglEntity>) this.service.findJjbRzList(jjbEntity.getId(), pageRequest);
		return DataUtils.pageToMap(pageInfo);
	}
	
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String,Object> seachData(JjbEntity jjbEntity,HttpServletRequest request, HttpServletResponse response)throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<JjbEntity> pageInfo = (Page<JjbEntity>) this.service.findList(jjbEntity, pageRequest);
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
	
	@RequestMapping(value = "/jjbTj")
	@ResponseBody
	public AjaxResult jjbTj(JjbEntity jjbEntity,HttpServletRequest request, HttpServletResponse response)throws Exception {
		AjaxResult ajaxResult = null;
		try {
			ajaxResult = this.service.jjbTj(jjbEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("更新发生异常");
		}
		return ajaxResult;
	}
	
	
	@RequestMapping(value = "/updateById")
	@ResponseBody
	public AjaxResult updateById(JjbEntity jjbEntity,HttpServletRequest request, HttpServletResponse response)throws Exception {
		try {
			this.service.updateById(jjbEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("更新发生异常");
		}
		return AjaxResult.success();
	}
	
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public AjaxResult getById(JjbEntity jjbEntity,HttpServletRequest request, HttpServletResponse response)throws Exception {
		try {
			JjbEntity jjb = this.service.selectOne(jjbEntity.getId());
			return AjaxResult.success(jjb);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("发生异常");
		}
	}
	
	
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public AjaxResult saveOrUpdate(JjbRzglEntity jjbRzglEntity,HttpServletRequest request, HttpServletResponse response)throws Exception {
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			jjbRzglEntity.setCjrId(user.getUserId());
			jjbRzglEntity.setCusNumber(user.getCusNumber());
			jjbRzglEntity.setDeptNumber(user.getDprtmntCode());
			jjbRzglService.insert(jjbRzglEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("保存发生异常");
		}
		return AjaxResult.success("保存成功");
	}
	
	/**
	 * 检查用户是否是当前班次的值班人员
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/checkIsZbry")
	@ResponseBody
	public AjaxResult checkIsZbry(@RequestParam("id") String id ,HttpServletRequest request, HttpServletResponse response)throws Exception {
		AjaxResult ajaxResult = null;
		try {
			ajaxResult  = this.service.checkIsZbry(id);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("查询发生异常");
		}
		return ajaxResult;
	}
	
	
	@RequestMapping(value = "/checkIsZbz")
	@ResponseBody
	public AjaxResult checkIsZbz(JjbEntity jjbEntity ,HttpServletRequest request, HttpServletResponse response)throws Exception {
		AjaxResult ajaxResult = null;
		try {
			ajaxResult  = this.service.checkIsZbz(jjbEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("查询发生异常");
		}
		return ajaxResult;
	}
	
	
}
