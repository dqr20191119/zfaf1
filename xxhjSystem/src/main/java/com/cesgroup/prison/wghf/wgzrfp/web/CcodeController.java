package com.cesgroup.prison.wghf.wgzrfp.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.dto.TreeDto;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.wghf.wgzrfp.dao.CcodeDao;
import com.cesgroup.prison.wghf.wgzrfp.entiy.Ccode;
import com.cesgroup.prison.wghf.wgzrfp.service.CcodeService;
import com.cesgroup.prison.wghf.wgzrfp.service.WgglService;




/**
 * Description: 网格划分管理控制器
 * @author Monkey
 *
 * 2019年3月15日
 */
@Controller
@RequestMapping("/wghf/wgzrfp")
public class CcodeController extends BaseEntityDaoServiceCRUDController<Ccode, String, CcodeDao, CcodeService> {
	private final Logger logger = LoggerFactory.getLogger(CcodeController.class);
	@Resource
	private CcodeService ccodeService;
	@Resource
	private WgglService wgglService;
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("wghf/wgzr/index");
		return mv;
	}
	
	/**
	 * Description: 进入北京市监狱局树页面
	 * @return
	 */
	@RequestMapping("/ccodeTreePage")
	public ModelAndView ccodeTreePage() {
		ModelAndView mv = new ModelAndView("wghf/wgzr/tree");
		return mv;
	}
	
	@RequestMapping("/ccodeTree")
	@ResponseBody
	public List<TreeDto> initCcodelTree() {
		List<TreeDto> treeList = new ArrayList<TreeDto>();
		try {
			return this.getService().initCcodeTree();
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
	
	@RequestMapping("/list")
	@ResponseBody
	public ModelAndView createWggl(HttpServletRequest request, HttpServletResponse response) {
		String wgid = request.getParameter("wgid");
		request.setAttribute("wgid", wgid);
		ModelAndView mv = new ModelAndView("wghf/wgzr/edit2");
		return mv;
		
	}

}
