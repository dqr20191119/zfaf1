package com.cesgroup.prison.department.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.mybatis.entity.LongIDEntity;

/**
 * 部门信息表
 * 
 * @author xiexiaqin
 * @date 2016-04-22
 *
 */
@Entity
@Table(name = "T_FW_DEPARTMENT")
public class Department extends LongIDEntity {

	private static final long serialVersionUID = 9170900593523920698L;
	
	/**
	 * 部门名称
	 */
	private String departmentName;
	
	/**
	 * 部门描述
	 */
	private String departmentDescription;
	
	/**
	 * 部门领导
	 */
	private String departmentLeader;
	
	/**
	 * 父节点
	 */
	private String  parentId;
	
	/**
	 * 是否为叶子节点
	 */
	private String isLeaf;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getDepartmentDescription() {
		return departmentDescription;
	}

	public void setDepartmentDescription(String departmentDescription) {
		this.departmentDescription = departmentDescription;
	}

	public String getDepartmentLeader() {
		return departmentLeader;
	}

	public void setDepartmentLeader(String departmentLeader) {
		this.departmentLeader = departmentLeader;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
}
