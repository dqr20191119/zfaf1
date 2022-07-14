package com.cesgroup.prison.common.bean.user;

/**
 * cesgroup
 * 用户角色实体类
 * @author lihh
 */
public class RoleBean {
	
	private String id = "";		// 角色ID
	private String code = "";	// 角色编码
	private String name = "";	// 角色名称
	private String type = "";	// 角色类型

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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
