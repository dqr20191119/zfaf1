package com.cesgroup.prison.xxhj.zfxxcx.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
/**
 * 罪犯基本信息查询
 * @author monkey
 *	2019-3-19
 */
@Entity
@Table(name = "ZFINFO.T_ZF_ZFJBXX")
public class ZfxxcxEntity  extends StringIDEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 罪犯编号
	 */
	private String cZfbh;
	/**
	 * 罪犯姓名
	 */
	private String cXm;
	/**
	 * 所在监狱
	 */
	private String cSzjy;
	/**
	 * 所在监区
	 */
	private String cSzjq;
	
	/**
	 * 出生日期
	 */
	private String dCsrq;
	/**
	 * 民族
	 */
	private String cMz;
	/**
	 * 入监日期
	 */
	private String dRjrq;
	/**
	 * 籍贯
	 */
	private String cJg;
	/**
	 * 户籍地址
	 */
	private String cHjdz;
	public String getcZfbh() {
		return cZfbh;
	}
	public void setcZfbh(String cZfbh) {
		this.cZfbh = cZfbh;
	}
	public String getcXm() {
		return cXm;
	}
	public void setcXm(String cXm) {
		this.cXm = cXm;
	}
	public String getcSzjy() {
		return cSzjy;
	}
	public void setcSzjy(String cSzjy) {
		this.cSzjy = cSzjy;
	}
	public String getcSzjq() {
		return cSzjq;
	}
	public void setcSzjq(String cSzjq) {
		this.cSzjq = cSzjq;
	}
	public String getdCsrq() {
		return dCsrq;
	}
	public void setdCsrq(String dCsrq) {
		this.dCsrq = dCsrq;
	}
	public String getcMz() {
		return cMz;
	}
	public void setcMz(String cMz) {
		this.cMz = cMz;
	}
	public String getdRjrq() {
		return dRjrq;
	}
	public void setdRjrq(String dRjrq) {
		this.dRjrq = dRjrq;
	}
	public String getcJg() {
		return cJg;
	}
	public void setcJg(String cJg) {
		this.cJg = cJg;
	}
	public String getcHjdz() {
		return cHjdz;
	}
	public void setcHjdz(String cHjdz) {
		this.cHjdz = cHjdz;
	}
	
	
	
}
