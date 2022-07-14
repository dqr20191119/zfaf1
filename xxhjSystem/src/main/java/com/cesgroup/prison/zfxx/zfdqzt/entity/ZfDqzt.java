package com.cesgroup.prison.zfxx.zfdqzt.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 罪犯当前状态表实体类
 * @author lincoln.cheng
 *
 * 2019年1月17日
 */
@Entity
@Table(name = "ZFINFO.T_ZF_DQZT")
public class ZfDqzt extends StringIDEntity {
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
	 * Desc: 档案号
	 */
    private String cZydah;
	/**
	 * Desc: 副档号
	 */
    private String cFdah;
	/**
	 * Desc: 所在监狱
	 */
    private String cSzjy;
	/**
	 * Desc: 所在监区
	 */
    private String cSzjq;
	/**
	 * Desc: 分押类别
	 */
    private String cFylb;
	/**
	 * Desc: 目前文化程度
	 */
    private String cMqwhcd;
	/**
	 * Desc: 工种
	 */
    private String cGz;
	/**
	 * Desc: 没收财产履行情况
	 */
    private String cMsccLxqk;
	/**
	 * Desc: 罚金累计缴纳金额
	 */
    private String nFjjnZe;
	/**
	 * Desc: 民事赔偿累计缴纳金额
	 */
    private String nMspcjnZe;
	/**
	 * Desc: 追缴累计缴纳金额
	 */
    private String nZjjnZe;
	/**
	 * Desc: 责令退赔累计缴纳金额
	 */
    private String nZltpjnZe;
	/**
	 * Desc: 监舍号
	 */
    private String cJsh;
	/**
	 * Desc: 床位号
	 */
    private String cCwh;
	/**
	 * Desc: 互监组号
	 */
    private String cHjzh;
	/**
	 * Desc: 监管干警警号
	 */
    private String cJggjJh;
	/**
	 * Desc: 监管干警名称
	 */
    private String cJggjMc;
	/**
	 * Desc: 分管等级
	 */
    private String cFgdj;
	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dUrlTime;
    
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
	public String getcZydah() {
		return cZydah;
	}
	public void setcZydah(String cZydah) {
		this.cZydah = cZydah;
	}
	public String getcFdah() {
		return cFdah;
	}
	public void setcFdah(String cFdah) {
		this.cFdah = cFdah;
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
	public String getcFylb() {
		return cFylb;
	}
	public void setcFylb(String cFylb) {
		this.cFylb = cFylb;
	}
	public String getcMqwhcd() {
		return cMqwhcd;
	}
	public void setcMqwhcd(String cMqwhcd) {
		this.cMqwhcd = cMqwhcd;
	}
	public String getcGz() {
		return cGz;
	}
	public void setcGz(String cGz) {
		this.cGz = cGz;
	}
	public String getcMsccLxqk() {
		return cMsccLxqk;
	}
	public void setcMsccLxqk(String cMsccLxqk) {
		this.cMsccLxqk = cMsccLxqk;
	}
	public String getnFjjnZe() {
		return nFjjnZe;
	}
	public void setnFjjnZe(String nFjjnZe) {
		this.nFjjnZe = nFjjnZe;
	}
	public String getnMspcjnZe() {
		return nMspcjnZe;
	}
	public void setnMspcjnZe(String nMspcjnZe) {
		this.nMspcjnZe = nMspcjnZe;
	}
	public String getnZjjnZe() {
		return nZjjnZe;
	}
	public void setnZjjnZe(String nZjjnZe) {
		this.nZjjnZe = nZjjnZe;
	}
	public String getnZltpjnZe() {
		return nZltpjnZe;
	}
	public void setnZltpjnZe(String nZltpjnZe) {
		this.nZltpjnZe = nZltpjnZe;
	}
	public String getcJsh() {
		return cJsh;
	}
	public void setcJsh(String cJsh) {
		this.cJsh = cJsh;
	}
	public String getcCwh() {
		return cCwh;
	}
	public void setcCwh(String cCwh) {
		this.cCwh = cCwh;
	}
	public String getcHjzh() {
		return cHjzh;
	}
	public void setcHjzh(String cHjzh) {
		this.cHjzh = cHjzh;
	}
	public String getcJggjJh() {
		return cJggjJh;
	}
	public void setcJggjJh(String cJggjJh) {
		this.cJggjJh = cJggjJh;
	}
	public String getcJggjMc() {
		return cJggjMc;
	}
	public void setcJggjMc(String cJggjMc) {
		this.cJggjMc = cJggjMc;
	}
	public String getcFgdj() {
		return cFgdj;
	}
	public void setcFgdj(String cFgdj) {
		this.cFgdj = cFgdj;
	}
	public Date getdUrlTime() {
		return dUrlTime;
	}
	public void setdUrlTime(Date dUrlTime) {
		this.dUrlTime = dUrlTime;
	}
}