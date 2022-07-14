package com.cesgroup.prison.zfxx.zfjyzf.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 罪犯 狱外寄押 实体类
 * 
 * @author monkey
 *
 *         2019年3月5日
 */

@Entity
@Table(name = "ZFINFO.T_ZF_JYZF")
public class ZfJyzf extends StringIDEntity {
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
	 * 寄出单位
	 */
	private String cJcdw;
	/**
	 * 寄出部门
	 */
	private String cJcbm;

	/**
	 * 寄出日期
	 */
	private String dJyqr;
	/**
	 * 实际结束日期
	 */
	private String dZzrq;

	/**
	 * 寄入单位
	 */
	private String cJrdw;
	/**
	 * 寄入部门
	 */
	private String cJrbm;
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

	public String getcJcdw() {
		return cJcdw;
	}

	public void setcJcdw(String cJcdw) {
		this.cJcdw = cJcdw;
	}

	public String getcJcbm() {
		return cJcbm;
	}

	public void setcJcbm(String cJcbm) {
		this.cJcbm = cJcbm;
	}

	public String getdJyqr() {
		return dJyqr;
	}

	public void setdJyqr(String dJyqr) {
		this.dJyqr = dJyqr;
	}

	public String getdZzrq() {
		return dZzrq;
	}

	public void setdZzrq(String dZzrq) {
		this.dZzrq = dZzrq;
	}

	public String getcJrdw() {
		return cJrdw;
	}

	public void setcJrdw(String cJrdw) {
		this.cJrdw = cJrdw;
	}

	public String getcJrbm() {
		return cJrbm;
	}

	public void setcJrbm(String cJrbm) {
		this.cJrbm = cJrbm;
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
		return "ZfJyzf [cId=" + cId + ", cZfbh=" + cZfbh + ", cJcdw=" + cJcdw + ", cJcbm=" + cJcbm + ", dJyqr=" + dJyqr
				+ ", dZzrq=" + dZzrq + ", cJrdw=" + cJrdw + ", cJrbm=" + cJrbm + ", dUrlTime=" + dUrlTime + ", dCjrq="
				+ dCjrq + ", cCjpc=" + cCjpc + "]";
	}

}
