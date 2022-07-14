package com.cesgroup.prison.httpclient.zfxx.dto;


import com.cesgroup.framework.base.entity.StringIDEntity;
/**
 * Description: 罪犯认罪服判类别 实体类
 * @author monkey
 *
 * 2019年3月3日
 */
public class ZfRzfpDto  extends StringIDEntity{
	
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
	 * 登记日期
	 */
	private String DDjrq;
	/**
	 * 认罪服判类别
	 */
	private String CRzfplb;
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
	public String getDDjrq() {
		return DDjrq;
	}
	public void setDDjrq(String dDjrq) {
		DDjrq = dDjrq;
	}
	public String getCRzfplb() {
		return CRzfplb;
	}
	public void setCRzfplb(String cRzfplb) {
		CRzfplb = cRzfplb;
	}
	public String getDUrlTime() {
		return DUrlTime;
	}
	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}

}
