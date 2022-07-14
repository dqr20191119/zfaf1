package com.cesgroup.prison.djwg.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
 * 党建网格人员维护
 * @author S
 *
 */
@Entity
@Table(name = "T_DJWG_RYWH")
public class DjwgrywhEntity  extends StringIDEntity {

	//监狱id
	private String jyId;
	
	//监狱
	private String jyName;
	//人员姓名
	private String userName;
	//人员id
	private String userId;
	//职务
	private String zw;
	//排序
	private String px;
	 
	private String zzName;
	
	private String zzCode;
	
	private String code;
	
	private String parentCode;
	
	@Transient
	private String deptName;
	
	@Column(updatable = false)
	private String cCjr ;//创建人
	@Column(updatable = false)
	private String cCjrId;//创建人Id
	@Column(updatable = false)
	private Date cCjRq ;//创建日期
	private String cGxrId;		// 更新人ID
	private String cGxr;			// 更新人
	private Date cGxRq;			// 更新日期
	private String scflg;  //删除标示   1正常  0 删除
	private String bz;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getZw() {
		return zw;
	}
	public void setZw(String zw) {
		this.zw = zw;
	}
	public String getPx() {
		return px;
	}
	public void setPx(String px) {
		this.px = px;
	}
 
	public String getcCjr() {
		return cCjr;
	}
	public void setcCjr(String cCjr) {
		this.cCjr = cCjr;
	}
	public String getcCjrId() {
		return cCjrId;
	}
	public void setcCjrId(String cCjrId) {
		this.cCjrId = cCjrId;
	}
	public Date getcCjRq() {
		return cCjRq;
	}
	public void setcCjRq(Date cCjRq) {
		this.cCjRq = cCjRq;
	}
	public String getcGxrId() {
		return cGxrId;
	}
	public void setcGxrId(String cGxrId) {
		this.cGxrId = cGxrId;
	}
	public String getcGxr() {
		return cGxr;
	}
	public void setcGxr(String cGxr) {
		this.cGxr = cGxr;
	}
	public Date getcGxRq() {
		return cGxRq;
	}
	public void setcGxRq(Date cGxRq) {
		this.cGxRq = cGxRq;
	}
	public String getScflg() {
		return scflg;
	}
	public void setScflg(String scflg) {
		this.scflg = scflg;
	}
	public String getBz() {
		return bz;
	}
	public void setBz(String bz) {
		this.bz = bz;
	}
	public String getZzName() {
		return zzName;
	}
	public void setZzName(String zzName) {
		this.zzName = zzName;
	}
	public String getZzCode() {
		return zzCode;
	}
	public void setZzCode(String zzCode) {
		this.zzCode = zzCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	public String getJyId() {
		return jyId;
	}
	public void setJyId(String jyId) {
		this.jyId = jyId;
	}
	public String getJyName() {
		return jyName;
	}
	public void setJyName(String jyName) {
		this.jyName = jyName;
	}
	
	
	
	
}
