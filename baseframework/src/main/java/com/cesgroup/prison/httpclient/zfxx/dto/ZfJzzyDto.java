package com.cesgroup.prison.httpclient.zfxx.dto;


public class ZfJzzyDto {
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
	 * 就医医院
	 */
	private String CYymc;
	/**
	 * 就诊/住院类别
	 */
	private String CJzlb;
	/**
	 * 就诊/住院时间
	 */
	private String CJzzysj;
	/**
	 * 结束日期
	 */
	private String DJsrq;
	/**
	 * 责任干警
	 */
	private String CZrgjXm;
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
	public String getCYymc() {
		return CYymc;
	}
	public void setCYymc(String cYymc) {
		CYymc = cYymc;
	}
	public String getCJzlb() {
		return CJzlb;
	}
	public void setCJzlb(String cJzlb) {
		CJzlb = cJzlb;
	}
	public String getCJzzysj() {
		return CJzzysj;
	}
	public void setCJzzysj(String cJzzysj) {
		CJzzysj = cJzzysj;
	}
	public String getDJsrq() {
		return DJsrq;
	}
	public void setDJsrq(String dJsrq) {
		DJsrq = dJsrq;
	}
	public String getCZrgjXm() {
		return CZrgjXm;
	}
	public void setCZrgjXm(String cZrgjXm) {
		CZrgjXm = cZrgjXm;
	}
	public String getDUrlTime() {
		return DUrlTime;
	}
	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}

	
	
}
