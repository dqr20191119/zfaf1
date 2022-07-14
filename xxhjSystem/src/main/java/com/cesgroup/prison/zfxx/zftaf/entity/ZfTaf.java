package com.cesgroup.prison.zfxx.zftaf.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 同案犯实体类
 * 
 * @author lincoln.cheng
 *
 *         2019年1月17日
 */
@Entity
@Table(name = "ZFINFO.T_ZF_TAF")
public class ZfTaf extends StringIDEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Desc: 罪犯标识
	 */
	private String cId;
	/**
	 * Desc: 同案犯标识
	 */
	private String cZfId;
	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dUrlTime;

	/**
	 * 罪犯编号
	 */
	private String cZfbh;

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

	public String getcZfId() {
		return cZfId;
	}

	public void setcZfId(String cZfId) {
		this.cZfId = cZfId;
	}

	public Date getdUrlTime() {
		return dUrlTime;
	}

	public void setdUrlTime(Date dUrlTime) {
		this.dUrlTime = dUrlTime;
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
		return "ZfTaf [cId=" + cId + ", cZfId=" + cZfId + ", dUrlTime=" + dUrlTime + ", cZfbh=" + cZfbh + ", dCjrq="
				+ dCjrq + ", cCjpc=" + cCjpc + "]";
	}

}