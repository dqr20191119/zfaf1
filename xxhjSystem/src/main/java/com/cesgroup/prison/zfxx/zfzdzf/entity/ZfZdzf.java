package com.cesgroup.prison.zfxx.zfzdzf.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 罪犯老病残 实体类
 * 
 * @author monkey
 *
 *         2019年3月4日
 */

@Entity
@Table(name = "ZFINFO.T_ZF_ZDZF")
public class ZfZdzf extends StringIDEntity {

	private static final long serialVersionUID = 1L;
	/**
	 * 罪犯标识
	 */
	private String cId;
	/**
	 * 罪犯编号
	 */
	private String cZfbh;
	/**
	 * 批准日期
	 */
	private String dPzrq;
	/**
	 * 重控级别
	 */
	private String cZkjb;
	/**
	 * 呈报日期
	 */
	private String dCbrq;
	/**
	 * 原因/表现
	 */
	private String cZkyy;
	/**
	 * 撤销批准日期
	 */
	private String dCxpzrq;
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

	public String getdPzrq() {
		return dPzrq;
	}

	public void setdPzrq(String dPzrq) {
		this.dPzrq = dPzrq;
	}

	public String getcZkjb() {
		return cZkjb;
	}

	public void setcZkjb(String cZkjb) {
		this.cZkjb = cZkjb;
	}

	public String getdCbrq() {
		return dCbrq;
	}

	public void setdCbrq(String dCbrq) {
		this.dCbrq = dCbrq;
	}

	public String getcZkyy() {
		return cZkyy;
	}

	public void setcZkyy(String cZkyy) {
		this.cZkyy = cZkyy;
	}

	public String getdCxpzrq() {
		return dCxpzrq;
	}

	public void setdCxpzrq(String dCxpzrq) {
		this.dCxpzrq = dCxpzrq;
	}

	public Date getdUrlTime() {
		return dUrlTime;
	}

	public void setdUrlTime(Date dUrlTime) {
		this.dUrlTime = dUrlTime;
	}

	public String getcId() {
		return cId;
	}

	public void setcId(String cId) {
		this.cId = cId;
	}

	public String getcZfbh() {
		return cZfbh;
	}

	public void setcZfbh(String cZfbh) {
		this.cZfbh = cZfbh;
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
		return "ZfZdzf [cId=" + cId + ", cZfbh=" + cZfbh + ", dPzrq=" + dPzrq + ", cZkjb=" + cZkjb + ", dCbrq=" + dCbrq
				+ ", cZkyy=" + cZkyy + ", dCxpzrq=" + dCxpzrq + ", dUrlTime=" + dUrlTime + ", dCjrq=" + dCjrq
				+ ", cCjpc=" + cCjpc + "]";
	}

}
