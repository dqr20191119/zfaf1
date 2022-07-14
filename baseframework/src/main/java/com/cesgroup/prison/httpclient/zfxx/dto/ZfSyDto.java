package com.cesgroup.prison.httpclient.zfxx.dto;

import java.io.Serializable;

/**
 * Description: 罪犯收押信息DTO
 * @author lincoln.cheng
 *
 * 2019年1月17日
 */
public class ZfSyDto implements Serializable {
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
	 * 收押类别
	 */
	private String CSylb;
	/**
	 * 收押日期
	 */
	private String DSyrq;
	/**
	 * 送押机关（即，何处调来）
	 */
	private String CSyjg;
	/**
	 * 送押机关区划
	 */
	private String CSyjgQh;
	/**
	 * 送押机关明细
	 */
	private String CSyjgMx;
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
	public String getCSylb() {
		return CSylb;
	}
	public void setCSylb(String cSylb) {
		CSylb = cSylb;
	}
	public String getDSyrq() {
		return DSyrq;
	}
	public void setDSyrq(String dSyrq) {
		DSyrq = dSyrq;
	}
	public String getCSyjg() {
		return CSyjg;
	}
	public void setCSyjg(String cSyjg) {
		CSyjg = cSyjg;
	}
	public String getCSyjgQh() {
		return CSyjgQh;
	}
	public void setCSyjgQh(String cSyjgQh) {
		CSyjgQh = cSyjgQh;
	}
	public String getCSyjgMx() {
		return CSyjgMx;
	}
	public void setCSyjgMx(String cSyjgMx) {
		CSyjgMx = cSyjgMx;
	}
	public String getDUrlTime() {
		return DUrlTime;
	}
	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}
}
