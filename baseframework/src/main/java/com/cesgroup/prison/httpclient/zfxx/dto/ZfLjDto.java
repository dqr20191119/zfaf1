package com.cesgroup.prison.httpclient.zfxx.dto;

import java.io.Serializable;

/**
 * Description: 罪犯离监信息DTO
 * @author lincoln.cheng
 *
 * 2019年1月17日
 */
public class ZfLjDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Desc: 罪犯编号
	 */
    private String CZfbh;
	/**
	 * Desc: 罪犯标识
	 */
    private String CId;
	/**
	 * 离监日期
	 */
	private String DLjrq;
	/**
	 * 离监类别
	 */
	private String CLjlb;
	/**
	 * 离监去向
	 */
	private String CQx;
	/**
	 * 离监去向区划
	 */
	private String CQxMx;
	/**
	 * 离监去向明细
	 */
	private String CQxQh;
	/**
	 * 请求Url中的time参数，用于记录当前的数据是请求的哪天的数据
	 */
	private String DUrlTime;
	
	public String getCZfbh() {
		return CZfbh;
	}
	public void setCZfbh(String cZfbh) {
		CZfbh = cZfbh;
	}
	public String getCId() {
		return CId;
	}
	public void setCId(String cId) {
		CId = cId;
	}
	public String getDLjrq() {
		return DLjrq;
	}
	public void setDLjrq(String dLjrq) {
		DLjrq = dLjrq;
	}
	public String getCLjlb() {
		return CLjlb;
	}
	public void setCLjlb(String cLjlb) {
		CLjlb = cLjlb;
	}
	public String getCQx() {
		return CQx;
	}
	public void setCQx(String cQx) {
		CQx = cQx;
	}
	public String getCQxMx() {
		return CQxMx;
	}
	public void setCQxMx(String cQxMx) {
		CQxMx = cQxMx;
	}
	public String getCQxQh() {
		return CQxQh;
	}
	public void setCQxQh(String cQxQh) {
		CQxQh = cQxQh;
	}
	public String getDUrlTime() {
		return DUrlTime;
	}
	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}
}
