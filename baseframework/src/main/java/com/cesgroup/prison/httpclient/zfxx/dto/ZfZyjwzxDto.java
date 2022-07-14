package com.cesgroup.prison.httpclient.zfxx.dto;


public class ZfZyjwzxDto {
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
	 * 保外应收监日期
	 */
	private String DSjrq;
	/**
	 * 终止日期
	 */
	private String DZzrq;
	/**
	 * 保人姓名
	 */
	private String CBrxm;
	/**
	 * 保人单位
	 */
	private String CDw;
	/**
	 * 保人电话
	 */
	private String CBrdh;
	
	/**
	 * 保人证件类型
	 */
	private String CZjlx;
	/**
	 * 保人证件号码
	 */
	private String CZjhm;
	/**
	 * 派出所（行政区划）
	 */
	private String CPcsQh;
	
	/**
	 * 派出所名称
	 */
	private String  CPcs;
	/**
	 * 司法所（行政区划）
	 */
	private String CSfsQh;
	/**
	 * 司法所名称
	 */
	private String CSfs;
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
	public String getDSjrq() {
		return DSjrq;
	}
	public void setDSjrq(String dSjrq) {
		DSjrq = dSjrq;
	}
	public String getDZzrq() {
		return DZzrq;
	}
	public void setDZzrq(String dZzrq) {
		DZzrq = dZzrq;
	}
	public String getCBrxm() {
		return CBrxm;
	}
	public void setCBrxm(String cBrxm) {
		CBrxm = cBrxm;
	}
	public String getCDw() {
		return CDw;
	}
	public void setCDw(String cDw) {
		CDw = cDw;
	}
	public String getCBrdh() {
		return CBrdh;
	}
	public void setCBrdh(String cBrdh) {
		CBrdh = cBrdh;
	}
	public String getCZjlx() {
		return CZjlx;
	}
	public void setCZjlx(String cZjlx) {
		CZjlx = cZjlx;
	}
	public String getCZjhm() {
		return CZjhm;
	}
	public void setCZjhm(String cZjhm) {
		CZjhm = cZjhm;
	}
	public String getCPcsQh() {
		return CPcsQh;
	}
	public void setCPcsQh(String cPcsQh) {
		CPcsQh = cPcsQh;
	}
	public String getCPcs() {
		return CPcs;
	}
	public void setCPcs(String cPcs) {
		CPcs = cPcs;
	}
	public String getCSfsQh() {
		return CSfsQh;
	}
	public void setCSfsQh(String cSfsQh) {
		CSfsQh = cSfsQh;
	}
	public String getCSfs() {
		return CSfs;
	}
	public void setCSfs(String cSfs) {
		CSfs = cSfs;
	}
	public String getDUrlTime() {
		return DUrlTime;
	}
	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}
	
	
}
