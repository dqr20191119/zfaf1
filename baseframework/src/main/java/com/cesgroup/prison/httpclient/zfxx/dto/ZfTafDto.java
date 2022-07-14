package com.cesgroup.prison.httpclient.zfxx.dto;

import java.io.Serializable;

/**
 * Description: 同案犯DTO
 * @author lincoln.cheng
 *
 * 2019年1月17日
 */
public class ZfTafDto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Desc: 罪犯标识
	 */
    private String CId;
	/**
	 * Desc: 同案犯标识
	 */
    private String CZfId;
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
	public String getCZfId() {
		return CZfId;
	}
	public void setCZfId(String cZfId) {
		CZfId = cZfId;
	}
	public String getDUrlTime() {
		return DUrlTime;
	}
	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}
}