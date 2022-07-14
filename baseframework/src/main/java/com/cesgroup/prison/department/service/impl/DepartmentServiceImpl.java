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
package com.cesgroup.prison.department.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.department.dao.DepartmentMapper;
import com.cesgroup.prison.department.entity.Department;
import com.cesgroup.prison.department.service.IDepartmentService;

/**
 * 
 * @author xiexiaqin
 * @date 2016-04-22
 *
 */
@Service
public class DepartmentServiceImpl extends BaseService<Department, Long> implements IDepartmentService {
	@Autowired
	private DepartmentMapper mapper;

	@Override
	public List<Map<String, Object>> getTree(String id) {
		// 动态查询部门信息
				List<Department> departmentList = this.mapper.findSonBySql(id);
				List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
				for (Department department : departmentList) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", department.getDepartmentName());
					map.put("id", department.getId().toString());
					map.put("open", true);
					if("1".equals(department.getIsLeaf())){//叶子节点
						map.put("isParent", false);
						map.put("click", false);
					}else{
						map.put("isParent", true);
						map.put("click", true);
					}
					
					maps.add(map);
				}
				return maps;
	}

	@Override
	public int getUserCountByDepartmentId(String departmentId) {
		return mapper.getUserCountByDepartmentId(departmentId);
	}

	@Override
	public int findDepartmentCountByParentId(String departmentId) {
		return mapper.findDepartmentCountByParentId(departmentId);
	}



	

}
