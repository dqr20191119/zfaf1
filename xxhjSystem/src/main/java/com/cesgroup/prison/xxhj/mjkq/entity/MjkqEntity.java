package com.cesgroup.prison.xxhj.mjkq.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
 * 民警考勤查询
 * 
 * @author monkey 2019-3-14
 */
@Entity
@Table(name = "PRISON.T_LZJY_KQJL")
public class MjkqEntity extends StringIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 部门名称
	 */
	private String bm;
	/**
	 * 民警姓名
	 */
	private String xm;
	/**
	 * 设备工号
	 */
	private String sbgh;
	/**
	 * 员工工号
	 */
	private String yggh;
	/**
	 * 考勤日期
	 */
	private String kqrq;
	/**
	 * 考勤时间
	 */
	private String kssj;

	private String jyId;

	private String jqId;

	private Date cjRq;

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getKqrq() {
		return kqrq;
	}

	public void setKqrq(String kqrq) {
		this.kqrq = kqrq;
	}

	public String getKssj() {
		return kssj;
	}

	public void setKssj(String kssj) {
		this.kssj = kssj;
	}

	public String getJyId() {
		return jyId;
	}

	public void setJyId(String jyId) {
		this.jyId = jyId;
	}

	public String getJqId() {
		return jqId;
	}

	public void setJqId(String jqId) {
		this.jqId = jqId;
	}

	public Date getCjRq() {
		return cjRq;
	}

	public void setCjRq(Date cjRq) {
		this.cjRq = cjRq;
	}

	public String getSbgh() {
		return sbgh;
	}

	public void setSbgh(String sbgh) {
		this.sbgh = sbgh;
	}

	public String getYggh() {
		return yggh;
	}

	public void setYggh(String yggh) {
		this.yggh = yggh;
	}

	@Override
	public String toString() {
		return "MjkqEntity [bm=" + bm + ", xm=" + xm + ", sbgh=" + sbgh + ", yggh=" + yggh + ", kqrq=" + kqrq
				+ ", kssj=" + kssj + ", jyId=" + jyId + ", jqId=" + jqId + ", cjRq=" + cjRq + "]";
	}

}
