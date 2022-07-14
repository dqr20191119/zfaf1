package com.cesgroup.prison.users.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.mybatis.entity.LongIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 用户信息表
 * 
 * @author xiexiaqin
 * @date 2016-04-22
 *
 */
@Entity
@Table(name = "T_FW_USER")
public class Users extends LongIDEntity {

	private static final long serialVersionUID = 9170900593523920698L;

	/**
	 * 部门ID
	 */
	@NotNull
	private String departmentId;

	/**
	 * 用户名
	 */
	@NotNull(message="用户名不能为空")
	private String userName;

	/**
	 * 密码
	 */
	private String userPass;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 年龄
	 */
	private int age;

	/**
	 * 住址
	 */
	private String address;

	/**
	 * 手机号码
	 */
	private String phone;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 个人简介
	 */
	private String introduction;
	
	/**
	 * 用户头像
	 */
	//private byte[]avatar;
	
	private Date entryDate;
	
	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPass() {
		return userPass;
	}

	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	// 设定JSON序列化时的日期格式
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyyMMdd")
	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	/*public byte[] getAvatar() {
		return avatar;
	}

	public void setAvatar(byte[] avatar) {
		this.avatar = avatar;
	}*/
	
	

}
