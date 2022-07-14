/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.demo.system.dao</p>
 * <p>文件名:ManyToOneUserMapper.java</p>
 * <p>类更新历史信息</p>
 * @author huz 
 * @date 2016-07-09 11:53
 * @todo 
 */
package com.cesgroup.prison.users.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.users.entity.ManyToOneUser;
/**
 *多对一例子
 * 
 * @author xiexiaqin
 * @date 2016-08-25
 *
 */
public interface ManyToOneUserMapper extends BaseDao<ManyToOneUser, Long> {
	
	/**
	 * 多对一动态分页查询，可以用$标示关联对象属性
	 * 按用户名和部门名查询
	 */
	public Page<ManyToOneUser> findByUserNameLikeAndDepartment$DepartmentNameLike(String userName, String departmentName, Pageable pageable);
	
}
