package com.cesgroup.prison.aqfxyp.fxcj.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 风险采集实体类
 * @author lincoln.cheng
 *
 * 2019年1月15日
 */
@Entity
@Table(name = "T_AQFX_FXCJ")
public class Fxcj extends StringIDEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String jyId;
	private String jyName;
	private String wgOne;
	private String wgTwo;
	private String wgThree;
	
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date fxDate;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date jhJjsj;
	
	private String wwjgId;
	private String wwjgName;
	private String sjfwId;
	private String sjfwName;
	private String fxdId;
	private String fxdName;
	private String pgtjId;
	private String pgtjName;
	private String dxkf;
	private String kcsl;
	private String kczf;
	private String status;
	@Column(updatable = false)
	private String cCjr ;//创建人
	@Column(updatable = false)
	private String cCjrId;//创建人Id
	@Column(updatable = false)
	private Date cCjRq ;//创建日期
	private String cGxrId;		// 更新人ID
	private String cGxr;			// 更新人
	private Date cGxRq;			// 更新日期
	private String scflg;
	private String bz;
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
	public String getWgOne() {
		return wgOne;
	}
	public void setWgOne(String wgOne) {
		this.wgOne = wgOne;
	}
	public String getWgTwo() {
		return wgTwo;
	}
	public void setWgTwo(String wgTwo) {
		this.wgTwo = wgTwo;
	}
	public Date getFxDate() {
		return fxDate;
	}
	public void setFxDate(Date fxDate) {
		this.fxDate = fxDate;
	}
	public String getFxdId() {
		return fxdId;
	}
	public void setFxdId(String fxdId) {
		this.fxdId = fxdId;
	}
	public String getFxdName() {
		return fxdName;
	}
	public void setFxdName(String fxdName) {
		this.fxdName = fxdName;
	}
	public String getPgtjId() {
		return pgtjId;
	}
	public void setPgtjId(String pgtjId) {
		this.pgtjId = pgtjId;
	}
	public String getPgtjName() {
		return pgtjName;
	}
	public void setPgtjName(String pgtjName) {
		this.pgtjName = pgtjName;
	}
	public String getDxkf() {
		return dxkf;
	}
	public void setDxkf(String dxkf) {
		this.dxkf = dxkf;
	}
	public String getKcsl() {
		return kcsl;
	}
	public void setKcsl(String kcsl) {
		this.kcsl = kcsl;
	}
	public String getKczf() {
		return kczf;
	}
	public void setKczf(String kczf) {
		this.kczf = kczf;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	public String getWwjgId() {
		return wwjgId;
	}
	public void setWwjgId(String wwjgId) {
		this.wwjgId = wwjgId;
	}
	public String getWwjgName() {
		return wwjgName;
	}
	public void setWwjgName(String wwjgName) {
		this.wwjgName = wwjgName;
	}
	public String getSjfwId() {
		return sjfwId;
	}
	public void setSjfwId(String sjfwId) {
		this.sjfwId = sjfwId;
	}
	public String getSjfwName() {
		return sjfwName;
	}
	public void setSjfwName(String sjfwName) {
		this.sjfwName = sjfwName;
	}
	public String getWgThree() {
		return wgThree;
	}
	public void setWgThree(String wgThree) {
		this.wgThree = wgThree;
	}
	public Date getJhJjsj() {
		return jhJjsj;
	}
	public void setJhJjsj(Date jhJjsj) {
		this.jhJjsj = jhJjsj;
	}
	
	
	
}
