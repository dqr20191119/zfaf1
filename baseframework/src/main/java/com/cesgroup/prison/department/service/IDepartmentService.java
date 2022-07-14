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

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 
 * @author xiexiaqin
 * @date 2016-04-22
 *
 */
@Service
public interface IDepartmentService  {


	/**
	 * 构建部门信息树
	 * 
	 * @param id
	 * @return
	 */
	public List<Map<String, Object>> getTree(String id);
	/**
	 * 根据部门查找用户数量
	 * @param departmentId
	 * @return
	 */
	public int getUserCountByDepartmentId(String departmentId);
	
	/**
	 * 查看部门下是否有子部门
	 * @param departmentId
	 * @return
	 */
	public int findDepartmentCountByParentId(String departmentId);

}
