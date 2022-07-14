package com.cesgroup.prison.linkage.dto;

import java.io.Serializable;

/**
 * 消息参数DTO
 * @author lincoln
 *
 */
public class DoorMessageDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 监狱编号
	 */
	private String cusNumber;
	/**
	 * 门禁编号
	 */
	private String doorID;
	/**
	 * 操作指令
	 */
	private String action;
	/**
	 * 门禁厂商
	 */
	private String brand;
	/**
	 * 门禁名称
	 */
	private String doorName;
	/**
	 * 门禁类型
	 */
	private String doorType;
	/**
	 * 部门ID
	 */
	private String deptId;
	/**
	 * 部门名称
	 */
	private String deptName;
	/**
	 * 房间ID
	 */
	private String roomId;
	/**
	 * 房间名称
	 */
	private String roomName;
	
	public String getCusNumber() {
		return cusNumber;
	}
	public void setCusNumber(String cusNumber) {
		this.cusNumber = cusNumber;
	}
	public String getDoorID() {
		return doorID;
	}
	public void setDoorID(String doorID) {
		this.doorID = doorID;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getDoorName() {
		return doorName;
	}
	public void setDoorName(String doorName) {
		this.doorName = doorName;
	}
	public String getDoorType() {
		return doorType;
	}
	public void setDoorType(String doorType) {
		this.doorType = doorType;
	}
	public String getDeptId() {
		return deptId;
	}
	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getRoomId() {
		return roomId;
	}
	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
}
