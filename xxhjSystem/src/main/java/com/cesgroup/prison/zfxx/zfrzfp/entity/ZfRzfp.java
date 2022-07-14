package com.cesgroup.prison.zfxx.zfrzfp.entity;

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
 *         2019年3月3日
 */

@Entity
@Table(name = "ZFINFO.T_ZF_RZFP")
public class ZfRzfp extends StringIDEntity {
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
	 * 登记日期
	 */
	private String dDjrq;
	/**
	 * 认罪服判类别
	 */
	private String cRzfplb;
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

	public String getdDjrq() {
		return dDjrq;
	}

	public void setdDjrq(String dDjrq) {
		this.dDjrq = dDjrq;
	}

	public String getcRzfplb() {
		return cRzfplb;
	}

	public void setcRzfplb(String cRzfplb) {
		this.cRzfplb = cRzfplb;
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
		return "ZfRzfp [cId=" + cId + ", cZfbh=" + cZfbh + ", dDjrq=" + dDjrq + ", cRzfplb=" + cRzfplb + ", dUrlTime="
				+ dUrlTime + ", dCjrq=" + dCjrq + ", cCjpc=" + cCjpc + "]";
	}
	
}
