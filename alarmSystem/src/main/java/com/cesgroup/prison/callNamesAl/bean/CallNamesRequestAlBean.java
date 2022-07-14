package com.cesgroup.prison.callNamesAl.bean;

public class CallNamesRequestAlBean {

	private String appKey;
	private String groupName;
	private String requestId;
	private int startTimestamp;
	private String userId;

	public CallNamesRequestAlBean() {
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public int getStartTimestamp() {
		return startTimestamp;
	}

	public void setStartTimestamp(int startTimestamp) {
		this.startTimestamp = startTimestamp;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
