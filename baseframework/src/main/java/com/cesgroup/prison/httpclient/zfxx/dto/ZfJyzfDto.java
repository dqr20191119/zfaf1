package com.cesgroup.prison.httpclient.zfxx.dto;


public class ZfJyzfDto {
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
	 * 寄出单位
	 */
	private String CJcdw;
	/**
	 * 寄出部门
	 */
	private String CJcbm;
	
	
	/**
	 * 寄出日期
	 */
	private String DJyqr;
	/**
	 * 实际结束日期
	 */
	private String DZzrq;
	
	/**
	 * 寄入单位
	 */
	private String CJrdw;
	/**
	 * 寄入部门
	 */
	private String CJrbm;
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
	public String getCJcdw() {
		return CJcdw;
	}
	public void setCJcdw(String cJcdw) {
		CJcdw = cJcdw;
	}
	public String getCJcbm() {
		return CJcbm;
	}
	public void setCJcbm(String cJcbm) {
		CJcbm = cJcbm;
	}
	public String getDJyqr() {
		return DJyqr;
	}
	public void setDJyqr(String dJyqr) {
		DJyqr = dJyqr;
	}
	public String getDZzrq() {
		return DZzrq;
	}
	public void setDZzrq(String dZzrq) {
		DZzrq = dZzrq;
	}
	public String getCJrdw() {
		return CJrdw;
	}
	public void setCJrdw(String cJrdw) {
		CJrdw = cJrdw;
	}
	public String getCJrbm() {
		return CJrbm;
	}
	public void setCJrbm(String cJrbm) {
		CJrbm = cJrbm;
	}
	public String getDUrlTime() {
		return DUrlTime;
	}
	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}
	
	
}
