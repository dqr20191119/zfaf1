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
package com.cesgroup.prison.department.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.department.entity.Department;

/**
 * 部门mapper
 * @author xiexiaqin
 * @date 2016-04-22
 *
 */
public interface DepartmentMapper extends BaseDao<Department,Long> {

	/**
	 * 动态查询部门信息
	 * @param parentId
	 * @return
	 *//*
	@Select("select * from t_fw_department where parent_Id=#{parentId}")
	public List<Department> findDepartmentByParentId(String parentId);*/
	
	/**
	 * 获取部门信息
	 * 基于默认模糊查询方式
	 * @param parentId
	 * @param departmentName
	 * @return
	 */
	public List<Department>findByParentIdAndDepartmentNameLike(String parentId,String departmentName);
	
	/**
	 * 根据部门查找用户数量
	 * @param departmentId
	 * @return
	 */
	@Select("select count(id) from t_fw_user where department_id =#{departmentId}")
	public int getUserCountByDepartmentId(String departmentId);
	
	/**
	 * 查看部门下是否有子部门
	 * 基于sql方式
	 * @param departmentId
	 * @return
	 */
	@Select("select count(id) from t_fw_department where parent_Id =#{departmentId}")
	public int findDepartmentCountByParentId(String departmentId);
	/**
	 * 查询子部门信息
	 * 基于默认查询方式
	 * @param parentId
	 * @return
	 */
	public List<Department> findByParentId(String parentId);
	
	
	/**
	 * 查询子部门信息
	 * 基于xml方式
	 * @param parentId
	 * @return
	 */
	public List<Department>findSonByXml(String parentId);
	
	
	/**
	 * 查询子部门信息
	 * 基于sql方式
	 * @param parentId
	 * @return
	 */
	@Select("select * from t_fw_department where parent_Id =#{parentId}")
	public List<Department>findSonBySql(String parentId);
	
	/**
	 * 查询子部门信息
	 * 基于provider
	 * @param paremntId
	 * @return
	 */
	
	@SelectProvider(type = DepartmentProvider.class, method = "findSonByProvider")
	public List<Department>findSonByProvider(@Param("parentId")String parentId);
	
	public Page<Map<String, String>> listAll(Map<String,Object> map, Pageable pageable);
}
