package com.cesgroup.prison.zbgl.hbsq.dto;
/**
* @author lihong
* @date 创建时间：2020年5月20日 下午7:57:12
* 类说明:
*/
public class ZbrxxDto {
	private String zbrId ;
	private String zbrName;
	private String dutyDate;
	private String orderId ;
	private String cusNumber;
	private Integer count;
	
	
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public String getZbrId() {
		return zbrId;
	}
	public void setZbrId(String zbrId) {
		this.zbrId = zbrId;
	}
	public String getZbrName() {
		return zbrName;
	}
	public void setZbrName(String zbrName) {
		this.zbrName = zbrName;
	}
	public String getDutyDate() {
		return dutyDate;
	}
	public void setDutyDate(String dutyDate) {
		this.dutyDate = dutyDate;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
}
