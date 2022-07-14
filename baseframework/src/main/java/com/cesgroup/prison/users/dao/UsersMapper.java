/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.demo.dao.code</p>
 * <p>文件名:CodeKeyMapper.java</p>
 * <p>类更新历史信息</p>
 * @author xiexiaqin 
 * @date 2016-04-12 10:21
 * @todo 
 */
package com.cesgroup.prison.users.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.department.entity.Department;
import com.cesgroup.prison.users.entity.UserDept;
import com.cesgroup.prison.users.entity.Users;

/**
 * 
 * @author xiexiaqin
 * @date 2016-04-22
 *
 */
public interface UsersMapper extends BaseDao<Users,Long> {

	/**
	 * 查询用户信息及所在部门
	 * 
	 * @param user
	 * @return
	 */
	public Page<Map<String, String>> listAll(Map<String,Object> map, Pageable pageable);
	
	/**
	 * 查询子部门信息
	 *sql方式
	 * @param parentId
	 * @return
	 */
	@Select("select id,is_leaf from t_fw_department where parent_Id=#{parentId}")
	public List<Department> findDepartmentByParentId(String parentId);
	
	/**
	 * 获取部门下的用户信息
	 * 默认实现方式
	 * @param departmentId
	 * @return
	 */
	public List<Users>findByDepartmentId(String departmentId);
	
	/**
	 * 删除部门下用户
	 * sql方式
	 * @param departmentId
	 */
	@Delete("delete from t_fw_user where department_id=#{departmentId}")
	public void deleteUserByDepartmentId(String departmentId);	
}
