/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.demo.system.entity</p>
 * <p>文件名:UserDept.java</p>
 * <p>类更新历史信息</p>
 * @author xiexiaqin 
 * @date 2016-09-07 15:41
 * @todo 
 */
package com.cesgroup.prison.users.entity;

/**
 * 
 * 用户部门关联实体类
 * @author xiexiaqin
 * @date 2016-09-07
 *
 */
public class UserDept extends Users {
	private static final long serialVersionUID = -1424155757706153880L;
	
	/** Dept.name */
	private String deptName;
	
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
	
}
