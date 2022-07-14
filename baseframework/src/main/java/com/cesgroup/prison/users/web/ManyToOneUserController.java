/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.demo.web.code</p>
 * <p>文件名:CodeKeyController.java</p>
 * <p>类更新历史信息</p>
 * @author xiexiaqin 
 * @date 2016-04-22 10:17
 * @todo 
 */
package com.cesgroup.prison.users.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.base.utils.Searchable;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.users.dao.ManyToOneUserMapper;
import com.cesgroup.prison.users.entity.ManyToOneUser;
import com.cesgroup.prison.users.service.ManyToOneUserService;
import com.cesgroup.prison.utils.DataUtils;

/**
 * 用戶信息Controller
 * 
 * @author xiexiaqin
 * @date 2016-04-22
 *
 */
@Controller
@RequestMapping(value = "/manyToOne")
public class ManyToOneUserController  extends BaseEntityDaoServiceCRUDController<ManyToOneUser, Long, ManyToOneUserMapper, ManyToOneUserService> {
	@Autowired
	private ManyToOneUserService sevice;
	
	/**
	 * 进入列表页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/list")
	public ModelAndView showList(@PathVariable("id") String id) {
		ModelAndView mv = new ModelAndView("users/list1");
		mv.addObject("departmentId", id);
		return mv;
	}

	/**
	 * 列表显示
	 * 
	 * @param entp
	 * @return
	 */
	@RequestMapping(value = "/searchdata2")
	@ResponseBody
	@Logger(action = "查询方法", logger = "用户信息及对应的部门")
	public Map<String, Object> searchData(@RequestParam("userName") String userName,String departmentName,
			@RequestParam("P_pagesize") Integer pageSize, @RequestParam("P_pageNumber") Integer pageNumber,
			@RequestParam("sord") String sort) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		
		Page<ManyToOneUser> pageInfo=sevice.findAll(userName,departmentName, pageRequest);
		return DataUtils.pageToMap(pageInfo);
		//return null;
	}
	/**
	 * 列表显示
	 * 
	 * @param entp
	 * @return
	 */
	@RequestMapping(value = "/searchdata1")
	@ResponseBody
	@Logger(action = "查询方法", logger = "用户信息及对应的部门")
	public Map<String, Object> searchData1(Searchable searchable,Pageable page) throws Exception {
		Page<ManyToOneUser> pageInfo = processSearch(searchable, page);
		return DataUtils.pageToMap(pageInfo);
	}
	/**
	 * 列表显示
	 * 
	 * @param entp
	 * @return
	 */
	@RequestMapping(value = "/searchdata")
	@ResponseBody
	@Logger(action = "查询方法", logger = "用户信息及对应的部门")
	public Map<String, Object> searchData(ManyToOneUser manyToOneUser) throws Exception {
		Page<ManyToOneUser> pageInfo = processSearch();
		return DataUtils.pageToMap(pageInfo);
		
	}
}
