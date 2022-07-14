package com.cesgroup.prison.httpclient.zfxx.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ZfShgxDto {
	private static final long serialVersionUID = 1L;
	/**
	 * 罪犯标识
	 */
	private String CId;
	/**
	 * 罪犯编号
	 */
	private String CZfbh;
	
	/**
	 * 关系人姓名
	 */
	private String CXm;
	/**
	 * 性别
	 */
	private String CXb;
	/**
	 * 出生日期
	 */
	private String DCsrq;
	/**
	 * 证件类别
	 */
	private String CZjlb;
	
	/**
	 * 证件号码
	 */
	private String CZjhm;
	/**
	 * 职业
	 */
	private String CZy;
	/**
	 * 政治面貌
	 */
	private String CZzmm;
	/**
	 * 电话
	 */
	private String CLxdh;
	/**
	 * 主联系人
	 */
	private String NZlxr;
	
	/**
	 * 家庭住址
	 */
	private String CJtzz;

	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
	private String DUrlTime;

	public String getCId() {
		return CId;
	}

	public void setCId(String cId) {
		CId = cId;
	}

	public String getCZfbh() {
		return CZfbh;
	}

	public void setCZfbh(String cZfbh) {
		CZfbh = cZfbh;
	}

	public String getCXm() {
		return CXm;
	}

	public void setCXm(String cXm) {
		CXm = cXm;
	}

	public String getCXb() {
		return CXb;
	}

	public void setCXb(String cXb) {
		CXb = cXb;
	}

	public String getDCsrq() {
		return DCsrq;
	}

	public void setDCsrq(String dCsrq) {
		DCsrq = dCsrq;
	}

	public String getCZjlb() {
		return CZjlb;
	}

	public void setCZjlb(String cZjlb) {
		CZjlb = cZjlb;
	}

	public String getCZjhm() {
		return CZjhm;
	}

	public void setCZjhm(String cZjhm) {
		CZjhm = cZjhm;
	}

	public String getCZy() {
		return CZy;
	}

	public void setCZy(String cZy) {
		CZy = cZy;
	}

	public String getCLxdh() {
		return CLxdh;
	}

	public void setCLxdh(String cLxdh) {
		CLxdh = cLxdh;
	}

	public String getNZlxr() {
		return NZlxr;
	}

	public void setNZlxr(String nZlxr) {
		NZlxr = nZlxr;
	}

	public String getCJtzz() {
		return CJtzz;
	}

	public void setCJtzz(String cJtzz) {
		CJtzz = cJtzz;
	}

	public String getDUrlTime() {
		return DUrlTime;
	}

	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}

	public String getCZzmm() {
		return CZzmm;
	}

	public void setCZzmm(String cZzmm) {
		CZzmm = cZzmm;
	}
	
	
}
