package com.cesgroup.prison.zfxx.zfsy.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 收押表实体类
 * 
 * @author lincoln.cheng
 *
 *         2019年1月17日
 */
@Entity
@Table(name = "ZFINFO.T_ZF_SY")
public class ZfSy extends StringIDEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Desc: 罪犯编号
	 */
	private String cZfbh;
	/**
	 * Desc: 罪犯标识
	 */
	private String cId;
	/**
	 * Desc: 收押类别
	 */
	private String cSylb;
	/**
	 * Desc: 收押日期
	 */
	private Date dSyrq;
	/**
	 * Desc: 送押机关（即，何处调来）
	 */
	private String cSyjg;
	/**
	 * Desc: 送押机关区划
	 */
	private String cSyjgQh;
	/**
	 * Desc: 送押机关明细
	 */
	private String cSyjgMx;
	/**
	 * Desc: 收押备注
	 */
	private String cSybz;
	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dUrlTime;

	/**
	 * 创建日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date dCjrq;

	/**
	 * 创建批次
	 */
	private String cCjpc;

	public String getcZfbh() {
		return cZfbh;
	}

	public void setcZfbh(String cZfbh) {
		this.cZfbh = cZfbh;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getcSylb() {
		return cSylb;
	}

	public void setcSylb(String cSylb) {
		this.cSylb = cSylb;
	}

	public Date getdSyrq() {
		return dSyrq;
	}

	public void setdSyrq(Date dSyrq) {
		this.dSyrq = dSyrq;
	}

	public String getcSyjg() {
		return cSyjg;
	}

	public void setcSyjg(String cSyjg) {
		this.cSyjg = cSyjg;
	}

	public String getcSyjgQh() {
		return cSyjgQh;
	}

	public void setcSyjgQh(String cSyjgQh) {
		this.cSyjgQh = cSyjgQh;
	}

	public String getcSyjgMx() {
		return cSyjgMx;
	}

	public void setcSyjgMx(String cSyjgMx) {
		this.cSyjgMx = cSyjgMx;
	}

	public String getcSybz() {
		return cSybz;
	}

	public void setcSybz(String cSybz) {
		this.cSybz = cSybz;
	}

	public Date getdUrlTime() {
		return dUrlTime;
	}

	public void setdUrlTime(Date dUrlTime) {
		this.dUrlTime = dUrlTime;
	}

	public Date getdCjrq() {
		return dCjrq;
	}

	public void setdCjrq(Date dCjrq) {
		this.dCjrq = dCjrq;
	}

	public String getcCjpc() {
		return cCjpc;
	}

	public void setcCjpc(String cCjpc) {
		this.cCjpc = cCjpc;
	}

	@Override
	public String toString() {
		return "ZfSy [cZfbh=" + cZfbh + ", cId=" + cId + ", cSylb=" + cSylb + ", dSyrq=" + dSyrq + ", cSyjg=" + cSyjg
				+ ", cSyjgQh=" + cSyjgQh + ", cSyjgMx=" + cSyjgMx + ", cSybz=" + cSybz + ", dUrlTime=" + dUrlTime
				+ ", dCjrq=" + dCjrq + ", cCjpc=" + cCjpc + "]";
	}

}