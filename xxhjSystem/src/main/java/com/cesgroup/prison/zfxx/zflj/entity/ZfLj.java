package com.cesgroup.prison.zfxx.zflj.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 离监表实体类
 * 
 * @author lincoln.cheng
 *
 *         2019年1月17日
 */
@Entity
@Table(name = "ZFINFO.T_ZF_LJ")
public class ZfLj extends StringIDEntity {
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
	 * Desc: 离监日期
	 */
	private Date dLjrq;
	/**
	 * Desc: 离监类别
	 */
	private String cLjlb;
	/**
	 * Desc: 离监去向
	 */
	private String cQx;
	/**
	 * Desc: 离监去向区划
	 */
	private String cQxQh;
	/**
	 * Desc: 离监去向明细
	 */
	private String cQxMx;
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

	public Date getdLjrq() {
		return dLjrq;
	}

	public void setdLjrq(Date dLjrq) {
		this.dLjrq = dLjrq;
	}

	public String getcLjlb() {
		return cLjlb;
	}

	public void setcLjlb(String cLjlb) {
		this.cLjlb = cLjlb;
	}

	public String getcQx() {
		return cQx;
	}

	public void setcQx(String cQx) {
		this.cQx = cQx;
	}

	public String getcQxQh() {
		return cQxQh;
	}

	public void setcQxQh(String cQxQh) {
		this.cQxQh = cQxQh;
	}

	public String getcQxMx() {
		return cQxMx;
	}

	public void setcQxMx(String cQxMx) {
		this.cQxMx = cQxMx;
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
		return "ZfLj [cZfbh=" + cZfbh + ", cId=" + cId + ", dLjrq=" + dLjrq + ", cLjlb=" + cLjlb + ", cQx=" + cQx
				+ ", cQxQh=" + cQxQh + ", cQxMx=" + cQxMx + ", dUrlTime=" + dUrlTime + ", dCjrq=" + dCjrq + ", cCjpc="
				+ cCjpc + "]";
	}

}