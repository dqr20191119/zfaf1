package com.cesgroup.prison.httpclient.zfxx.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ZfLjtqDto {
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
	 * 准假类别
	 */
	private String CZjlb;
	/**
	 * 假期起日
	 */
	private String DJqqr;
	/**
	 * 归监日期
	 */
	private String DGjrq;
	
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

	public String getCZjlb() {
		return CZjlb;
	}

	public void setCZjlb(String cZjlb) {
		CZjlb = cZjlb;
	}

	public String getDJqqr() {
		return DJqqr;
	}

	public void setDJqqr(String dJqqr) {
		DJqqr = dJqqr;
	}

	public String getDGjrq() {
		return DGjrq;
	}

	public void setDGjrq(String dGjrq) {
		DGjrq = dGjrq;
	}

	public String getDUrlTime() {
		return DUrlTime;
	}

	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}
	
	
}
