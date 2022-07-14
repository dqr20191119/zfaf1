package com.cesgroup.prison.zfxx.zftg.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 罪犯 特管等级 实体类
 * 
 * @author monkey
 *
 *         2019年3月3日
 */

@Entity
@Table(name = "ZFINFO.T_ZF_TG")
public class ZfTg extends StringIDEntity {

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
	 * 分管等级
	 */
	private String cFgdj;
	/**
	 * 改造岗位
	 */
	private String cQzfg;
	/**
	 * 是否顽危重控
	 */
	private String cSfww;
	/**
	 * 同案犯标识
	 */

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

	public String getcFgdj() {
		return cFgdj;
	}

	public void setcFgdj(String cFgdj) {
		this.cFgdj = cFgdj;
	}

	public String getcQzfg() {
		return cQzfg;
	}

	public void setcQzfg(String cQzfg) {
		this.cQzfg = cQzfg;
	}

	public String getcSfww() {
		return cSfww;
	}

	public void setcSfww(String cSfww) {
		this.cSfww = cSfww;
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
		return "ZfTg [cId=" + cId + ", cZfbh=" + cZfbh + ", cFgdj=" + cFgdj + ", cQzfg=" + cQzfg + ", cSfww=" + cSfww
				+ ", dUrlTime=" + dUrlTime + ", dCjrq=" + dCjrq + ", cCjpc=" + cCjpc + "]";
	}

}
