package com.cesgroup.prison.callNamesAl.bean;

import java.util.List;
import java.util.Map;

public class CallNameJsonRootBean {

	private List<CallNamesJsDataBean> data; // 需点名监舍
	private String department; // 点名发起部门
	private String duration; // 点名时长
	private String userID;// 点名发起人
	private String action;// 1 发起点名 2、结束点名

	private String masterId;// 点名主表id
	private Map<String, String> jsStts;// 监舍点名状态 key 监舍号 value 点名状态 0 未开始 1进行中 2点名结束 -1点名发生异常
	private String jsTotal; // 参与点名监舍总数
	private String doneTotal; // 点名完成监舍数

	public CallNameJsonRootBean() {

	}

	public String getJsTotal() {
		return jsTotal;
	}

	public void setJsTotal(String jsTotal) {
		this.jsTotal = jsTotal;
	}

	public String getDoneTotal() {
		return doneTotal;
	}

	public void setDoneTotal(String doneTotal) {
		this.doneTotal = doneTotal;
	}

	public Map<String, String> getJsStts() {
		return jsStts;
	}

	public void setJsStts(Map<String, String> jsStts) {
		this.jsStts = jsStts;
	}

	public String getMasterId() {
		return masterId;
	}

	public void setMasterId(String masterId) {
		this.masterId = masterId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public List<CallNamesJsDataBean> getData() {
		return data;
	}

	public void setData(List<CallNamesJsDataBean> data) {
		this.data = data;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getDepartment() {
		return department;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDuration() {
		return duration;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserID() {
		return userID;
	}

}
