/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.demo.service.code</p>
 * <p>文件名:CodeKeyService.java</p>
 * <p>类更新历史信息</p>
 * @author xiexiaqin 
 * @date 2016-04-22 10:22
 * @todo 
 */
package com.cesgroup.prison.department.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.department.dao.DepartmentMapper;
import com.cesgroup.prison.department.entity.Department;
import com.cesgroup.prison.users.dao.UsersMapper;
import com.cesgroup.prison.users.entity.Users;

/**
 * 
 * @author xiexiaqin
 * @date 2016-04-22
 *
 */
@Service
public class DepartmentService extends BaseService<Department, Long> {

	@Autowired
	private DepartmentMapper departmentMapper;
	@Autowired
	private UsersMapper userMapper;

	/**
	 * 构建树信息
	 * @param id
	 * @return
	 */
	private List<Map<String, Object>> getTreeData(String id){
		//sql方式
		//List<Department> departmentList1 = this.departmentMapper.findSonBySql(id);
	
		//xml方式
		//List<Department> departmentList1 = this.departmentMapper.findSonByXml(id);
		
		//默认方式
		List<Department> departmentList1 = this.departmentMapper.findByParentId(id);
		
		//provider方式
		//List<Department> departmentList1 = this.departmentMapper.findSonByProvider(id);
		
		List<Map<String, Object>> mapChild =  new ArrayList<Map<String, Object>>();
		for (Department department : departmentList1) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("name", department.getDepartmentName());
			map1.put("id", department.getId().toString());
			map1.put("open", true);
			if ("1".equals(department.getIsLeaf())) {// 叶子节点
				map1.put("isParent", false);
				//map1.put("click", false);
			} else {
				map1.put("isParent", true);
				//map1.put("click", true);
			}
			mapChild.add(map1);
			
		}
		return mapChild;
	}
	public List<Map<String, Object>> getTree(String id) {
		// 动态查询部门信息
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		if("".equals(id)|| null==id){
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", com.cesgroup.prison.constants.Department.TREE_ROOT_NAME);
			map.put("id",com.cesgroup.prison.constants.Department.TREE_ROOT_VALUE);
			map.put("open", true);
			List<Map<String, Object>> mapChild=getTreeData(com.cesgroup.prison.constants.Department.TREE_ROOT_VALUE);
			if(mapChild!=null&&mapChild.size()>0){
				map.put("children", mapChild);
			}
			
			maps.add(map);
		}else{
			maps=getTreeData(id);
			
		}
		return maps;
	}
	
	/**
	 * 子部门集合
	 * @param parentId
	 * @return
	 */
	public List<Map<String, Object>> getDepartName(String parentId,String departmentName) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<Department> departmentList = this.departmentMapper.findByParentIdAndDepartmentNameLike(parentId,departmentName);
		for (Department department : departmentList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", department.getDepartmentName());
			map.put("text", department.getDepartmentName());
			maps.add(map);
		}
		return maps;
	}
	/**
	 * 根据部门查找用户数量
	 * 
	 * @param departmentId
	 * @return
	 */
	public int getUserCountByDepartmentId(String departmentId) {
		return departmentMapper.getUserCountByDepartmentId(departmentId);
	}

	/**
	 * 查看部门下是否有子部门
	 * 
	 * @param departmentId
	 * @return
	 */
	public int findDepartmentCountByParentId(String departmentId) {
		return departmentMapper.findDepartmentCountByParentId(departmentId);
	}
	/**
	 * 删除部门
	 * @param id
	 */
	@Transactional
	public void deleteDepartment(Long id) {
		List<Users>list=userMapper.findByDepartmentId(String.valueOf(id));
		if(list.size()>0){
			userMapper.deleteUserByDepartmentId(String.valueOf(id));
		}
		getDao().delete(id);
	}
	
	public Page<Map<String, String>> listAll(Department department, PageRequest pageRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("department", department);
		return departmentMapper.listAll(map,pageRequest);
	}
}
