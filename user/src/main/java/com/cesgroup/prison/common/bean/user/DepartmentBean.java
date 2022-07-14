package com.cesgroup.prison.common.bean.user;

/**
 * cesgroup
 * 部门信息实体类(department)
 * @author lihh
 *
 */
public class DepartmentBean {
	/*
	 *  部门基础信息
	 */
	private String id = null;		// 部门ID
	private String code = null;		// 部门编码
	private String name = null;		// 部门名称
	private String weight = null;	// 权重


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
}
