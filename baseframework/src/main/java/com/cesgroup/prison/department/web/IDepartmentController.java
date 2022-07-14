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
package com.cesgroup.prison.department.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.department.dao.DepartmentMapper;
import com.cesgroup.prison.department.entity.Department;
import com.cesgroup.prison.department.service.IDepartmentService;
import com.cesgroup.prison.department.service.impl.DepartmentServiceImpl;
import com.cesgroup.prison.utils.DataUtils;

/**
 * 部门信息Controllerz
 * @author xiexiaqin
 * @date 2016-04-22
 *
 */
@Controller
@RequestMapping(value = "/department2")
public class IDepartmentController
		extends BaseEntityDaoServiceCRUDController<Department, Long, DepartmentMapper, DepartmentServiceImpl> {
	
	@Autowired
	private IDepartmentService service;
	
	/**
	 * 部门信息树加载
	 * @return
	 */
	@RequestMapping("/getTree")
	@ResponseBody
	public List<Map<String, Object>> getTree(String id) {
		return service.getTree(id);

	}
	/**
	 * 进入列表页面
	 * @param id
	 * @return
	 */
	@RequestMapping("/{id}/{isParent}/list")
	public ModelAndView showList(@PathVariable("id") String id,@PathVariable("isParent") String isParent){
		ModelAndView mv=new ModelAndView("department/list");
		mv.addObject("isParent",isParent);
		mv.addObject("parentId",id);
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
	@Logger(action = "查询方法", logger = "查询列表")
	public Map<String, Object> searchData(Department department) {
		Page<Department> page = processSearch("EQ_parentId", department.getParentId());
		return DataUtils.pageToMap(page);
	}

	/**
	 * 进入新增页面
	 * 
	 * @return
	 */
	@RequestMapping("/{parentId}/new")
	public ModelAndView editNew(@PathVariable("parentId") String parentId) {
		ModelAndView mv = new ModelAndView("department/new");
		mv.addObject("parentId", parentId);
		return mv;
	}

	
	/**
	 * 进入查看页面
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/show")
	@Logger(action = "查看", logger = "${id}")
	public ModelAndView show(@PathVariable("id") Long id) {
		Department department = getService().selectOne(id);
		ModelAndView mav = new ModelAndView("department/show");
		mav.addObject("model", department);
		return mav;

	}
	
	/**
	 * 校验部门下是否有用户
	 * @param department
	 * @return
	 */
	@RequestMapping(value="/checkData")
	@ResponseBody
	public  Map<String, Object> checkData(Department department){
		String message="";
		if("0".equals(department.getIsLeaf())){//非叶子节点
			int userNum=service.getUserCountByDepartmentId(String.valueOf(department.getId()));
			if(userNum>0){
				message="该部门下已存在用户不能修改成非叶子节点";
			}
		}
		if("1".equals(department.getIsLeaf())){//叶子节点
			int departmentCount = service.findDepartmentCountByParentId(String.valueOf(department.getId()));
			if(departmentCount>0){
				message="该部门下已存在子部门,不能修改成叶子节点";
			}
		}	
		return DataUtils.success(message);
	}
}
