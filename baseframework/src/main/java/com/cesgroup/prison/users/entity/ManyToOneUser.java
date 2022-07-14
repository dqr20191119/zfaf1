/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.framework.mybatis.associate.entity</p>
 * <p>文件名:ManyToOneUser.java</p>
 * <p>类更新历史信息</p>
 * @author huz 
 * @date 2016-07-08 16:38
 * @todo 
 */
package com.cesgroup.prison.users.entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.cesgroup.prison.department.entity.Department;

/**
 * 多对一关联的用户
 * 
 * @author xiexiaqin
 * @date 2016-08-25
 *
 */
public class ManyToOneUser extends Users {
	
	private static final long serialVersionUID = 6946104691119346937L;
	
	@ManyToOne
    @JoinColumn(name="department_id")  
	private Department department;
	
	/**
	 * 
	 * @return
	 * @see com.cesgroup.system.user.entity.Users#getDepartmentId()
	 */
	@Override
	@Transient
	public String getDepartmentId() {
		return super.getDepartmentId();
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	
	
	
}
