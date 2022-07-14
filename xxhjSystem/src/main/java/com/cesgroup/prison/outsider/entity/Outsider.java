package com.cesgroup.prison.outsider.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name = "PRISON.T_OUTSIDER_WLRY")
public class Outsider  extends StringIDEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 监狱编号
	 */
	private String cusNumber;
	/**
	 * 外来人员姓名
	 */
	private String cName;
	/**
	 * 性别
	 */
	private String cSex;
	/**
	 * 身份证编号
	 */
	private String cSfzbh;
	/**
	 * 出生日期
	 */
	private String dCsrq;
	/**
	 * 登记日期
	 */
	private String dDjrq;
	/**
	 * 事由
	 */
	private String cSy;
	/**
	 * 车牌号
	 */
	private String cCph;
	/**
	 * 
	 */
	private String cCxys;
	/**
	 * 照片
	 */
	private String cZp;
	/**
	 * 审批领导
	 */
	private String cSpld;
	/**
	 * 操作人员姓名
	 */
	private String cCzry;
	/**
	 * 操作人员警号
	 */
	private String cCzryjh;
	/**
	 * 部门
	 */
	private String cBm;
	/**
	 * 区域
	 */
	private String cQy;
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public String getcName() {
		return cName;
	}
	public void setcName(String cName) {
		this.cName = cName;
	}
	public String getcSex() {
		return cSex;
	}
	public void setcSex(String cSex) {
		this.cSex = cSex;
	}
	public String getcSfzbh() {
		return cSfzbh;
	}
	public void setcSfzbh(String cSfzbh) {
		this.cSfzbh = cSfzbh;
	}
	public String getdCsrq() {
		return dCsrq;
	}
	public void setdCsrq(String dCsrq) {
		this.dCsrq = dCsrq;
	}
	public String getdDjrq() {
		return dDjrq;
	}
	public void setdDjrq(String dDjrq) {
		this.dDjrq = dDjrq;
	}
	public String getcSy() {
		return cSy;
	}
	public void setcSy(String cSy) {
		this.cSy = cSy;
	}
	public String getcCph() {
		return cCph;
	}
	public void setcCph(String cCph) {
		this.cCph = cCph;
	}
	public String getcCxys() {
		return cCxys;
	}
	public void setcCxys(String cCxys) {
		this.cCxys = cCxys;
	}
	public String getcZp() {
		return cZp;
	}
	public void setcZp(String cZp) {
		this.cZp = cZp;
	}
	public String getcSpld() {
		return cSpld;
	}
	public void setcSpld(String cSpld) {
		this.cSpld = cSpld;
	}
	public String getcCzry() {
		return cCzry;
	}
	public void setcCzry(String cCzry) {
		this.cCzry = cCzry;
	}
	public String getcCzryjh() {
		return cCzryjh;
	}
	public void setcCzryjh(String cCzryjh) {
		this.cCzryjh = cCzryjh;
	}
	public String getcBm() {
		return cBm;
	}
	public void setcBm(String cBm) {
		this.cBm = cBm;
	}
	public String getcQy() {
		return cQy;
	}
	public void setcQy(String cQy) {
		this.cQy = cQy;
	}
	
	
}
