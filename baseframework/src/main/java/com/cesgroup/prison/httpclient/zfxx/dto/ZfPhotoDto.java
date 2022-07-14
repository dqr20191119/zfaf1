package com.cesgroup.prison.httpclient.zfxx.dto;

import java.io.Serializable;

/**
 * Description: 罪犯照片表实体类
 * @author lincoln.cheng
 *
 * 2019年1月17日
 */
public class ZfPhotoDto implements Serializable {
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
	 * 罪犯姓名
	 */
	private String CXm;
	/**
	 * Desc: 照片类别
	 */
    private String CZplb;
	/**
	 * Desc: 照片存储地址对应ID
	 */
    private String CStorageid;
	/**
	 * Desc: 采集时间
	 */
    private String DCjsj;
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
	public String getCXm() {
		return CXm;
	}
	public void setCXm(String cXm) {
		CXm = cXm;
	}
	public String getCZplb() {
		return CZplb;
	}
	public void setCZplb(String cZplb) {
		CZplb = cZplb;
	}
	public String getCStorageid() {
		return CStorageid;
	}
	public void setCStorageid(String cStorageid) {
		CStorageid = cStorageid;
	}
	public String getDCjsj() {
		return DCjsj;
	}
	public void setDCjsj(String dCjsj) {
		DCjsj = dCjsj;
	}
	public String getDUrlTime() {
		return DUrlTime;
	}
	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}
}