package com.cesgroup.prison.zfxx.zfLjtq.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 罪犯 离监探亲 实体类
 * 
 * @author monkey
 *
 *         2019年3月4日
 */

@Entity
@Table(name = "ZFINFO.T_ZF_LJTQ_SJ")
public class ZfLjtq extends StringIDEntity {
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
	 * 准假类别
	 */
	private String cZjlb;
	/**
	 * 假期起日
	 */
	private String dJqqr;
	/**
	 * 归监日期
	 */
	private String dGjrq;

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

	public String getcZjlb() {
		return cZjlb;
	}

	public void setcZjlb(String cZjlb) {
		this.cZjlb = cZjlb;
	}

	public String getdJqqr() {
		return dJqqr;
	}

	public void setdJqqr(String dJqqr) {
		this.dJqqr = dJqqr;
	}

	public String getdGjrq() {
		return dGjrq;
	}

	public void setdGjrq(String dGjrq) {
		this.dGjrq = dGjrq;
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
		return "ZfLjtq [cId=" + cId + ", cZfbh=" + cZfbh + ", cZjlb=" + cZjlb + ", dJqqr=" + dJqqr + ", dGjrq=" + dGjrq
				+ ", dUrlTime=" + dUrlTime + ", dCjrq=" + dCjrq + ", cCjpc=" + cCjpc + "]";
	}

}
