package com.cesgroup.prison.door.bean;

/**
 * 开关门指令返回
 * @author zxh
 *
 */
public class DoorReturnStatusBean {
	
	private String doorID;
	private String doorName;
	private String userID;
	private String action;
	private String returnValue;
	
	
	public String getDoorID() {
		return doorID;
	}
	public void setDoorID(String doorID) {
		this.doorID = doorID;
	}
	public String getDoorName() {
		return doorName;
	}
	public void setDoorName(String doorName) {
		this.doorName = doorName;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getReturnValue() {
		return returnValue;
	}
	public void setReturnValue(String returnValue) {
		this.returnValue = returnValue;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
}
