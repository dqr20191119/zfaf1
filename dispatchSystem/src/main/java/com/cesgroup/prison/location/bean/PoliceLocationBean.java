package com.cesgroup.prison.location.bean;

import java.io.Serializable;

/**
 * 民警所在位置Bean
 * 
 * @author lincoln.cheng
 *
 */
public class PoliceLocationBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 警号
	 */
	private String No;
	/**
	 * 民警姓名
	 */
	private String Name;
	/**
	 * 所在位置编号
	 */
	private String deptno;
	/**
	 * 所在位置名称
	 */
	private String deptname;
	/**
	 * 所在位置排序
	 */
	private String px;
	/**
	 * 所属监狱
	 */
	private String JYName;
	
	public String getNo() {
		return No;
	}
	public void setNo(String no) {
		No = no;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getDeptno() {
		return deptno;
	}
	public void setDeptno(String deptno) {
		this.deptno = deptno;
	}
	public String getDeptname() {
		return deptname;
	}
	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}
	public String getPx() {
		return px;
	}
	public void setPx(String px) {
		this.px = px;
	}
	public String getJYName() {
		return JYName;
	}
	public void setJYName(String jYName) {
		JYName = jYName;
	}
}