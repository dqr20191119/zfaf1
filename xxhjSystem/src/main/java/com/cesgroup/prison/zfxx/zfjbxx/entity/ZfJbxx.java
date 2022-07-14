package com.cesgroup.prison.zfxx.zfjbxx.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 罪犯基本信息表实体类
 * 
 * @author lincoln.cheng
 *
 *         2019年1月17日
 */
@Entity
@Table(name = "ZFINFO.T_ZF_JBXX")
public class ZfJbxx extends StringIDEntity {
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
	 * Desc: 罪犯姓名
	 */
	private String cXm;
	/**
	 * Desc: 罪犯真实用名
	 */
	private String cZsym;
	/**
	 * Desc: 性别
	 */
	private String cXb;
	/**
	 * Desc: 出生日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dCsrq;
	/**
	 * Desc: 民族
	 */
	private String cMz;
	/**
	 * Desc: 捕前政治面貌
	 */
	private String cBqzzmm;
	/**
	 * Desc: 捕前文化程度
	 */
	private String cBqwhcd;
	/**
	 * Desc: 捕前婚姻状况
	 */
	private String cBqhyzk;
	/**
	 * Desc: 捕前所学专业
	 */
	private String cSxzy;
	/**
	 * Desc: 捕前职业
	 */
	private String cBqzy;
	/**
	 * Desc: 捕前职业分类
	 */
	private String cBqzylb;
	/**
	 * Desc: 捕前职级
	 */
	private String cBqzj;
	/**
	 * 入监备注
	 */
	private String cRjbz;

	/**
	 * 没收财产履行情况
	 */
	private String cMsccLxqk;
	/**
	 * 罚金累计缴纳金额
	 */
	private String nFjjnZe;
	/**
	 * 民事赔偿累计缴纳金额
	 */
	private String nMspcjnZe;
	/**
	 * 追缴累计缴纳金额
	 */
	private String nZjjnZe;
	/**
	 * 责令退赔累计缴纳金额
	 */
	private String nZltpjnZe;
	/**
	 * 档案号
	 */
	private String cZydah;
	/**
	 * 副档号
	 */
	private String cFdah;
	/**
	 * 所在监狱
	 */
	private String cSzjy;
	/**
	 * 所在监区
	 */
	private String cSzjq;
	/**
	 * 分押类别
	 */
	private String cFylb;
	/**
	 * 工种
	 */
	private String cGz;
	/**
	 * 目前文化程度
	 */
	private String cMqwhcd;
	/**
	 * 互监组号
	 */
	private String cHjzh;
	/**
	 * 监舍号
	 */
	private String cJsh;
	/**
	 * 床位号
	 */
	private String cCwh;
	/**
	 * 监管干警名称
	 */
	private String cJggjMc;
	/**
	 * 分管等级
	 */
	private String cFgdj;
	/**
	 * 送押机关
	 */
	private String cSyjg;
	/**
	 * 入监日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dRjrq;
	/**
	 * 正面照缩略图
	 */
	private String cZmzSlt;

	/**
	 * 籍贯
	 */
	private String cJg;
	/**
	 * 家庭住址
	 */
	private String cJtzz;
	/**
	 * 户籍地址
	 */
	private String cHjdz;
	/**
	 * 原判刑种
	 */
	private String cYpXz;
	/**
	 * 原判刑期
	 */
	private String cYpXq;

	/**
	 * 原判刑期起日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dYpXqqr;
	/**
	 * 原判刑期止日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dYpXqzr;
	/**
	 * 原剥政类别
	 */
	private String cYpBzlb;
	/**
	 * 现刑种
	 */
	private String cXxqZxxz;
	/**
	 * 现刑期
	 */

	private String cXxq;

	/**
	 * 现刑期起日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dXxqQr;
	/**
	 * 现刑期止日
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dXxqZr;
	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dUrlTime;

	/**
	 * 监狱编码
	 */
	private String cJyId;

	/**
	 * 监区编码
	 */
	private String cJqId;

	/**
	 * 创建日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date dCjrq;

	/**
	 * 创建批次
	 */
	private String cCjpc;

	/**
	 * 证件类别
	 */
	private String cZjlb;
	
	/**
	 * 证件号码
	 */
	private String cZjhm;
	
	/**
	 * 所在班
	 */
	private String cSzb;
	
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

	public String getcXm() {
		return cXm;
	}

	public void setcXm(String cXm) {
		this.cXm = cXm;
	}

	public String getcZsym() {
		return cZsym;
	}

	public void setcZsym(String cZsym) {
		this.cZsym = cZsym;
	}

	public String getcXb() {
		return cXb;
	}

	public void setcXb(String cXb) {
		this.cXb = cXb;
	}

	public Date getdCsrq() {
		return dCsrq;
	}

	public void setdCsrq(Date dCsrq) {
		this.dCsrq = dCsrq;
	}

	public String getcMz() {
		return cMz;
	}

	public void setcMz(String cMz) {
		this.cMz = cMz;
	}

	public String getcBqzzmm() {
		return cBqzzmm;
	}

	public void setcBqzzmm(String cBqzzmm) {
		this.cBqzzmm = cBqzzmm;
	}

	public String getcBqwhcd() {
		return cBqwhcd;
	}

	public void setcBqwhcd(String cBqwhcd) {
		this.cBqwhcd = cBqwhcd;
	}

	public String getcBqhyzk() {
		return cBqhyzk;
	}

	public void setcBqhyzk(String cBqhyzk) {
		this.cBqhyzk = cBqhyzk;
	}

	public String getcSxzy() {
		return cSxzy;
	}

	public void setcSxzy(String cSxzy) {
		this.cSxzy = cSxzy;
	}

	public String getcBqzy() {
		return cBqzy;
	}

	public void setcBqzy(String cBqzy) {
		this.cBqzy = cBqzy;
	}

	public String getcBqzylb() {
		return cBqzylb;
	}

	public void setcBqzylb(String cBqzylb) {
		this.cBqzylb = cBqzylb;
	}

	public String getcBqzj() {
		return cBqzj;
	}

	public void setcBqzj(String cBqzj) {
		this.cBqzj = cBqzj;
	}

	public String getcRjbz() {
		return cRjbz;
	}

	public void setcRjbz(String cRjbz) {
		this.cRjbz = cRjbz;
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

	public String getcGz() {
		return cGz;
	}

	public void setcGz(String cGz) {
		this.cGz = cGz;
	}

	public String getcMqwhcd() {
		return cMqwhcd;
	}

	public void setcMqwhcd(String cMqwhcd) {
		this.cMqwhcd = cMqwhcd;
	}

	public String getcHjzh() {
		return cHjzh;
	}

	public void setcHjzh(String cHjzh) {
		this.cHjzh = cHjzh;
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

	public String getcSyjg() {
		return cSyjg;
	}

	public void setcSyjg(String cSyjg) {
		this.cSyjg = cSyjg;
	}

	public Date getdRjrq() {
		return dRjrq;
	}

	public void setdRjrq(Date dRjrq) {
		this.dRjrq = dRjrq;
	}

	public String getcZmzSlt() {
		return cZmzSlt;
	}

	public void setcZmzSlt(String cZmzSlt) {
		this.cZmzSlt = cZmzSlt;
	}

	public String getcJg() {
		return cJg;
	}

	public void setcJg(String cJg) {
		this.cJg = cJg;
	}

	public String getcJtzz() {
		return cJtzz;
	}

	public void setcJtzz(String cJtzz) {
		this.cJtzz = cJtzz;
	}

	public String getcHjdz() {
		return cHjdz;
	}

	public void setcHjdz(String cHjdz) {
		this.cHjdz = cHjdz;
	}

	public String getcYpXz() {
		return cYpXz;
	}

	public void setcYpXz(String cYpXz) {
		this.cYpXz = cYpXz;
	}

	public String getcYpXq() {
		return cYpXq;
	}

	public void setcYpXq(String cYpXq) {
		this.cYpXq = cYpXq;
	}

	public String getcYpBzlb() {
		return cYpBzlb;
	}

	public void setcYpBzlb(String cYpBzlb) {
		this.cYpBzlb = cYpBzlb;
	}

	public String getcXxqZxxz() {
		return cXxqZxxz;
	}

	public void setcXxqZxxz(String cXxqZxxz) {
		this.cXxqZxxz = cXxqZxxz;
	}

	public Date getdYpXqqr() {
		return dYpXqqr;
	}

	public void setdYpXqqr(Date dYpXqqr) {
		this.dYpXqqr = dYpXqqr;
	}

	public Date getdYpXqzr() {
		return dYpXqzr;
	}

	public void setdYpXqzr(Date dYpXqzr) {
		this.dYpXqzr = dYpXqzr;
	}

	public String getcXxq() {
		return cXxq;
	}

	public void setcXxq(String cXxq) {
		this.cXxq = cXxq;
	}

	public Date getdXxqZr() {
		return dXxqZr;
	}

	public void setdXxqZr(Date dXxqZr) {
		this.dXxqZr = dXxqZr;
	}

	public Date getdXxqQr() {
		return dXxqQr;
	}

	public void setdXxqQr(Date dXxqQr) {
		this.dXxqQr = dXxqQr;
	}

	public Date getdUrlTime() {
		return dUrlTime;
	}

	public void setdUrlTime(Date dUrlTime) {
		this.dUrlTime = dUrlTime;
	}

	public String getcJyId() {
		return cJyId;
	}

	public void setcJyId(String cJyId) {
		this.cJyId = cJyId;
	}

	public String getcJqId() {
		return cJqId;
	}

	public void setcJqId(String cJqId) {
		this.cJqId = cJqId;
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
		return "ZfJbxx [cZfbh=" + cZfbh + ", cId=" + cId + ", cXm=" + cXm + ", cSzjy=" + cSzjy + ", cSzjq=" + cSzjq
				+ ", cJyId=" + cJyId + ", cJqId=" + cJqId + "]";
	}

	public String getcZjlb() {
		return cZjlb;
	}

	public void setcZjlb(String cZjlb) {
		this.cZjlb = cZjlb;
	}

	public String getcZjhm() {
		return cZjhm;
	}

	public void setcZjhm(String cZjhm) {
		this.cZjhm = cZjhm;
	}

	public String getcSzb() {
		return cSzb;
	}

	public void setcSzb(String cSzb) {
		this.cSzb = cSzb;
	}

}