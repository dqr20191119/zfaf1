package com.cesgroup.prison.zfxx.zfzyjwzx.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 罪犯 暂予监外执行 实体类
 * 
 * @author monkey
 *
 *         2019年3月5日
 */
@Entity
@Table(name = "ZFINFO.T_ZF_ZYJWZX")
public class ZfZyjwzx extends StringIDEntity {

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
	 * 保外应收监日期
	 */
	private String dSjrq;
	/**
	 * 终止日期
	 */
	private String dZzrq;
	/**
	 * 保人姓名
	 */
	private String cBrxm;
	/**
	 * 保人单位
	 */
	private String cDw;
	/**
	 * 保人电话
	 */
	private String cBrdh;

	/**
	 * 保人证件类型
	 */
	private String cZjlx;
	/**
	 * 保人证件号码
	 */
	private String cZjhm;
	/**
	 * 派出所（行政区划）
	 */
	private String cPcsQh;

	/**
	 * 派出所名称
	 */
	private String cPcs;
	/**
	 * 司法所（行政区划）
	 */
	private String cSfsQh;
	/**
	 * 司法所名称
	 */
	private String cSfs;
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

	public String getdSjrq() {
		return dSjrq;
	}

	public void setdSjrq(String dSjrq) {
		this.dSjrq = dSjrq;
	}

	public String getdZzrq() {
		return dZzrq;
	}

	public void setdZzrq(String dZzrq) {
		this.dZzrq = dZzrq;
	}

	public String getcBrxm() {
		return cBrxm;
	}

	public void setcBrxm(String cBrxm) {
		this.cBrxm = cBrxm;
	}

	public String getcDw() {
		return cDw;
	}

	public void setcDw(String cDw) {
		this.cDw = cDw;
	}

	public String getcBrdh() {
		return cBrdh;
	}

	public void setcBrdh(String cBrdh) {
		this.cBrdh = cBrdh;
	}

	public String getcZjlx() {
		return cZjlx;
	}

	public void setcZjlx(String cZjlx) {
		this.cZjlx = cZjlx;
	}

	public String getcZjhm() {
		return cZjhm;
	}

	public void setcZjhm(String cZjhm) {
		this.cZjhm = cZjhm;
	}

	public String getcPcsQh() {
		return cPcsQh;
	}

	public void setcPcsQh(String cPcsQh) {
		this.cPcsQh = cPcsQh;
	}

	public String getcPcs() {
		return cPcs;
	}

	public void setcPcs(String cPcs) {
		this.cPcs = cPcs;
	}

	public String getcSfsQh() {
		return cSfsQh;
	}

	public void setcSfsQh(String cSfsQh) {
		this.cSfsQh = cSfsQh;
	}

	public String getcSfs() {
		return cSfs;
	}

	public void setcSfs(String cSfs) {
		this.cSfs = cSfs;
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
		return "ZfZyjwzx [cId=" + cId + ", cZfbh=" + cZfbh + ", dSjrq=" + dSjrq + ", dZzrq=" + dZzrq + ", cBrxm="
				+ cBrxm + ", cDw=" + cDw + ", cBrdh=" + cBrdh + ", cZjlx=" + cZjlx + ", cZjhm=" + cZjhm + ", cPcsQh="
				+ cPcsQh + ", cPcs=" + cPcs + ", cSfsQh=" + cSfsQh + ", cSfs=" + cSfs + ", dUrlTime=" + dUrlTime
				+ ", dCjrq=" + dCjrq + ", cCjpc=" + cCjpc + "]";
	}

}
