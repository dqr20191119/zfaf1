package com.cesgroup.prison.httpclient.zfxx.dto;



public class ZfZdzfDto {
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
	 * 批准日期
	 */
	private String DPzrq;
	/**
	 * 重控级别
	 */
	private String CZkjb;
	/**
	 * 呈报日期
	 */
	private String DCbrq;
	/**
	 * 原因/表现
	 */
	private String CZkyy;
	/**
	 * 撤销批准日期
	 */
	private String DCxpzrq;
	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
 
	private String DUrlTime ;
	public String getDPzrq() {
		return DPzrq;
	}
	public void setDPzrq(String dPzrq) {
		DPzrq = dPzrq;
	}
	public String getCZkjb() {
		return CZkjb;
	}
	public void setCZkjb(String cZkjb) {
		CZkjb = cZkjb;
	}
	public String getDCbrq() {
		return DCbrq;
	}
	public void setDCbrq(String dCbrq) {
		DCbrq = dCbrq;
	}
	public String getCZkyy() {
		return CZkyy;
	}
	public void setCZkyy(String cZkyy) {
		CZkyy = cZkyy;
	}
	public String getDCxpzrq() {
		return DCxpzrq;
	}
	public void setDCxpzrq(String dCxpzrq) {
		DCxpzrq = dCxpzrq;
	}
	public String getDUrlTime() {
		return DUrlTime;
	}
	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}
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
	
	
}
