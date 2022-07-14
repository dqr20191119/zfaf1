package com.cesgroup.prison.zfxx.zflbc.entity;

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
 *         2019年2月28日
 */

@Entity
@Table(name = "ZFINFO.T_ZF_LBC")
public class ZfLbc extends StringIDEntity {
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
	 * 鉴定日期
	 */
	private String dJdrq;
	/**
	 * 老病残类型
	 */
	private String cLb;
	/**
	 * 老病残情况
	 */
	private String cQk;
	/**
	 * 病残类别
	 */
	private String cBclb;
	/**
	 * 登记日期
	 */
	private String dDjrq;
	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dUrlTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date dCjrq;

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

	public String getdJdrq() {
		return dJdrq;
	}

	public void setdJdrq(String dJdrq) {
		this.dJdrq = dJdrq;
	}

	public String getcLb() {
		return cLb;
	}

	public void setcLb(String cLb) {
		this.cLb = cLb;
	}

	public String getcQk() {
		return cQk;
	}

	public void setcQk(String cQk) {
		this.cQk = cQk;
	}

	public String getcBclb() {
		return cBclb;
	}

	public void setcBclb(String cBclb) {
		this.cBclb = cBclb;
	}

	public String getdDjrq() {
		return dDjrq;
	}

	public void setdDjrq(String dDjrq) {
		this.dDjrq = dDjrq;
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
		return "ZfLbc [cId=" + cId + ", cZfbh=" + cZfbh + ", dJdrq=" + dJdrq + ", cLb=" + cLb + ", cQk=" + cQk
				+ ", cBclb=" + cBclb + ", dDjrq=" + dDjrq + ", dUrlTime=" + dUrlTime + ", dCjrq=" + dCjrq + ", cCjpc="
				+ cCjpc + "]";
	}
	
}
