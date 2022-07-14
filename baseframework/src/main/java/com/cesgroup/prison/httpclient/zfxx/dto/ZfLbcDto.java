package com.cesgroup.prison.httpclient.zfxx.dto;

import java.io.Serializable;
/**
 * Description: 罪犯老病残 信息DTO
 * @author monkey
 *
 * 2019年2月28日
 */
public class ZfLbcDto  implements Serializable{
	
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
	 * 鉴定日期
	 */
	private String DJdrq;
	/**
	 * 老病残类型
	 */
	private String CLb;
	/**
	 * 老病残情况
	 */
	private String CQk;
	/**
	 * 病残类别
	 */
	private String CBclb;
	/**
	 * 登记日期
	 */
	private String DDjrq;
	/**
	 * 接口请求日期
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

	public String getDJdrq() {
		return DJdrq;
	}

	public void setDJdrq(String dJdrq) {
		DJdrq = dJdrq;
	}

	public String getCLb() {
		return CLb;
	}

	public void setCLb(String cLb) {
		CLb = cLb;
	}

	public String getCQk() {
		return CQk;
	}

	public void setCQk(String cQk) {
		CQk = cQk;
	}

	public String getCBclb() {
		return CBclb;
	}

	public void setCBclb(String cBclb) {
		CBclb = cBclb;
	}

	public String getDDjrq() {
		return DDjrq;
	}

	public void setDDjrq(String dDjrq) {
		DDjrq = dDjrq;
	}

	public String getDUrlTime() {
		return DUrlTime;
	}

	public void setDUrlTime(String dUrlTime) {
		DUrlTime = dUrlTime;
	}
}
