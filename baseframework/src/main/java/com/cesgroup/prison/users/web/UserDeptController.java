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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.prison.users.entity.UserDept;
import com.cesgroup.prison.users.service.UserDeptService;
import com.cesgroup.prison.utils.DataUtils;
/**
 * 用户部门关联Controller
 * 
 * @author xiexiaqin
 * @date 2016-09-07
 *
 */
@Controller
@RequestMapping("/userDept")
public class UserDeptController extends BaseController {
	@Autowired
	private UserDeptService service;
	
	/**
	 * 进入列表页面
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public ModelAndView showList() {
		ModelAndView mv = new ModelAndView("users/list2");
		return mv;
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
	public Map<String, Object> searchData1(String userName,String deptName,Pageable page) throws Exception {
		Page<UserDept> pageInfo =service.findUserDept(userName, deptName,page);
		return DataUtils.pageToMap(pageInfo);
	}
}
