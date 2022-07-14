package com.cesgroup.prison.aqfxyp.fxcj.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.dto.TreeDto;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Base64Util;
import com.cesgroup.prison.aqfxyp.fxcj.common.FxcjConstant.FxysTypePrefix;
import com.cesgroup.prison.aqfxyp.fxcj.dao.FxcjMapper;
import com.cesgroup.prison.aqfxyp.fxcj.entity.Fxcj;
import com.cesgroup.prison.aqfxyp.fxcj.service.FxcjService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.wwjg.sjfwgl.entity.SjfwglEntity;
import com.cesgroup.prison.wwjg.wwjgwh.entity.WwjgwhEntity;

/**
 * Description: 风险采集控制器
 * @author lincoln.cheng
 *
 * 2019年1月15日
 */
@Controller
@RequestMapping("/aqfxyp/fxcj")
public class FxcjController extends BaseEntityDaoServiceCRUDController<Fxcj, String, FxcjMapper, FxcjService> {
	private final Logger logger = LoggerFactory.getLogger(FxcjController.class);  

	/**
	 * Description: 风险采集首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("aqfxyp/fxcj/index");
		mv.addObject("type", request.getParameter("type"));
		return mv;
	}

	/**
	 * Description: 进入风险要素树页面
	 * @return
	 */
	@RequestMapping("/fxysTreePage")
	public ModelAndView fxysTreePage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("aqfxyp/fxcj/tree");
		mv.addObject("type", request.getParameter("type"));
		return mv;
	}

	/**
	 * Description: 初始化风险因素树形结构
	 * @return
	 */
	@RequestMapping("/fxysTree")
	@ResponseBody
	public List<TreeDto> initFxysTree() {
		List<TreeDto> treeList = new ArrayList<TreeDto>();
		try {
			return this.getService().initFxysTree();
		} catch (Exception e) {
			TreeDto root = new TreeDto();
	        root.setId(TreeDto.ROOT_ID);
	        root.setCode(TreeDto.ROOT_CODE);
	        root.setName(TreeDto.ROOT_NAME);
	        root.setOpen(true);
	        treeList.add(root);
		}
		return treeList;
	}
	@RequestMapping(value = "/getById")
	@ResponseBody
	public Fxcj getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return this.getService().getById(id); 
	}
	
	/**
	 * Description: 分页查询风险采集信息
	 * @param fxcj
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(Fxcj fxcj, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		String lx = request.getParameter("lx");
		String cid = request.getParameter("cid");
		if(lx.equals(FxysTypePrefix.WWJG)){
			fxcj.setWwjgId(cid);
		}
		if(lx.equals(FxysTypePrefix.SJFW)){
			fxcj.setSjfwId(cid);
		}
		
		Page<Fxcj> pageInfo = (Page<Fxcj>) this.getService().findList(fxcj, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	
	/**
	 * Description: 分页查询风险采集信息
	 * @param jQName
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/searchDataNew")
	@ResponseBody
	public Map<String, Object> searchDataNew (HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="jQName", defaultValue="", required=false) String jQName,
			@RequestParam(value="wwName", defaultValue="", required=false) String wwName) throws Exception {
		String bz = request.getParameter("bz");
		if(jQName != null && !jQName.isEmpty()) {
			jQName = Base64Util.decodeString(jQName, 2);
		};
		if(wwName != null && !wwName.isEmpty()) {
			wwName = Base64Util.decodeString(wwName, 2);
		}
		String date = request.getParameter("date");
		PageRequest pageRequest = buildPageRequest();
		Page<Fxcj> pageInfo = (Page<Fxcj>) this.getService().findList(jQName,wwName,bz, date,pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	/**
	 * Description: 跳转至新增编辑页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		String lx = request.getParameter("lx");
		String cid = request.getParameter("cid");
		String wwjgId = "";
		String wwjgName = "";
		String sjfwId="";
		String sjfwName="";
		if(FxysTypePrefix.WWJG.equals(lx)){
			WwjgwhEntity wwjg= this.getService().getwwjg(cid);
			wwjgId = wwjg.getId();
			wwjgName = wwjg.getName();
		}
		if(FxysTypePrefix.SJFW.equals(lx)){
			SjfwglEntity sjfw = this.getService().getsjfw(cid);
			wwjgId = sjfw.getWwjgId();
			wwjgName = sjfw.getWwjgName();
			sjfwId = sjfw.getId();
			sjfwName = sjfw.getName();
		}
		
		
		ModelAndView mv = new ModelAndView("aqfxyp/fxcj/edit");
		mv.addObject("id", request.getParameter("id"));
		mv.addObject("wwjgId", wwjgId);
		mv.addObject("wwjgName", wwjgName);
		mv.addObject("sjfwId", sjfwId);
		mv.addObject("sjfwName", sjfwName);
		return mv;
	}

	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(Fxcj fxcj, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = fxcj.getId();
			
			if(id != null && !"".equals(id)) {
				fxcj.setcGxr(user.getUserName());
				fxcj.setcGxr(user.getUserId());
				fxcj.setcGxRq(new Date());
			} else {
				fxcj.setStatus(CommonConstant.STATUS_VALID_CODE);
				fxcj.setcCjr(user.getUserName());
				fxcj.setcCjrId(user.getUserId());
				fxcj.setcCjRq(new Date());
			}
			
			String fxcjId = this.getService().saveOrUpdate(fxcj);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
			resultMap.put("fxcjId", fxcjId);
		} catch(BusinessLayerException e) {
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		} catch (Exception e) {
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	
	
	@RequestMapping(value = "/insertDb")
	@ResponseBody
	public Map<String, Object> insertDb(Fxcj fxcj, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			String id  = request.getParameter("fxcjId");
			fxcj.setId(id);
			 this.getService().insterDb(fxcj);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		}  catch (Exception e) {
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}
	

	@RequestMapping(value = "/deleteByIds")
	@ResponseBody
	public Map<String, Object> deleteByIds(String ids, HttpServletRequest request, HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		String[] id = ids.split(",");
		
		if (id != null && id.length > 0) {
			try {
				this.getService().deleteByIds(ids);
				resultMap.put("code", CommonConstant.SUCCESS_CODE);
			} catch(BusinessLayerException e) {
				logger.error(e.toString(), e.fillInStackTrace());
				resultMap.put("code", CommonConstant.FAILURE_CODE);
			} catch(Exception e) {
				logger.error(e.toString(), e.fillInStackTrace());
				resultMap.put("code", CommonConstant.FAILURE_CODE);
			}
		}
		return resultMap;
	}
	
	
	/**
	 * Description: 右侧详细页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {
		String sjfw = request.getParameter("sjfw");
		String lx = sjfw.substring(0,5);
		String id = sjfw.substring(5,sjfw.length());
		request.setAttribute("lx", lx);
		request.setAttribute("id", id);
		request.setAttribute("swfw", sjfw);
		ModelAndView mv = new ModelAndView("aqfxyp/fxcj/list");
		return mv;
	}
	
	
	/**
	 * Description: 图形点开后的二级界面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listNe")
	public ModelAndView listNew(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value="jQName", defaultValue="", required=false) String jQName,
			@RequestParam(value="wwName", defaultValue="", required=false) String wwName) {
		ModelAndView mv = new ModelAndView("aqfxyp/fxcj/listNew");
		mv.addObject("date",request.getParameter("date"));
		mv.addObject("jQName",jQName);
		mv.addObject("wwName",wwName);
		return mv;
	}
	@RequestMapping(value = "/searchWG")
	@ResponseBody
	public List<Map<String, Object>> searchWG(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String leve = request.getParameter("leve");
		String parent  = request.getParameter("parent");
		return this.getService().getWg(leve,parent);
	}
}
