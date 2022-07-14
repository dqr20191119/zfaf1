package com.cesgroup.prison.zfxx.jhzs.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 罪犯 解回再审 实体类
 * 
 * @author monkey
 *
 *         2019年3月5日
 */

@Entity
@Table(name = "ZFINFO.T_ZF_JHZS")
public class ZfJhzs extends StringIDEntity {
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
	 * 公函字号
	 */
	private String cGhzh;
	/**
	 * 提回单位
	 */
	private String cThdw;
	/**
	 * 提回期限
	 */
	private String cThqx;
	/**
	 * 提回日期
	 */
	private String dThrq;
	/**
	 * 终止日期
	 */
	private String dZzrq;
	/**
	 * 经办干警
	 */
	private String cJbgjMc;
	/**
	 * 联系电话
	 */
	private String cLxdh;
	/**
	 * 提回原因
	 */
	private String cThyy;
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

	public String getcGhzh() {
		return cGhzh;
	}

	public void setcGhzh(String cGhzh) {
		this.cGhzh = cGhzh;
	}

	public String getcThdw() {
		return cThdw;
	}

	public void setcThdw(String cThdw) {
		this.cThdw = cThdw;
	}

	public String getcThqx() {
		return cThqx;
	}

	public void setcThqx(String cThqx) {
		this.cThqx = cThqx;
	}

	public String getdThrq() {
		return dThrq;
	}

	public void setdThrq(String dThrq) {
		this.dThrq = dThrq;
	}

	public String getdZzrq() {
		return dZzrq;
	}

	public void setdZzrq(String dZzrq) {
		this.dZzrq = dZzrq;
	}

	public String getcJbgjMc() {
		return cJbgjMc;
	}

	public void setcJbgjMc(String cJbgjMc) {
		this.cJbgjMc = cJbgjMc;
	}

	public String getcLxdh() {
		return cLxdh;
	}

	public void setcLxdh(String cLxdh) {
		this.cLxdh = cLxdh;
	}

	public String getcThyy() {
		return cThyy;
	}

	public void setcThyy(String cThyy) {
		this.cThyy = cThyy;
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
		return "ZfJhzs [cId=" + cId + ", cZfbh=" + cZfbh + ", cGhzh=" + cGhzh + ", cThdw=" + cThdw + ", cThqx=" + cThqx
				+ ", dThrq=" + dThrq + ", dZzrq=" + dZzrq + ", cJbgjMc=" + cJbgjMc + ", cLxdh=" + cLxdh + ", cThyy="
				+ cThyy + ", dUrlTime=" + dUrlTime + ", dCjrq=" + dCjrq + ", cCjpc=" + cCjpc + "]";
	}

}
