package com.cesgroup.prison.door.bean;

/**
 * 门禁刷卡
 * @author zxh
 *
 */
public class DoorCardBean {
	/**
	 * 人员ID
	 */
	private String peopleID;
	/**
	 * 人员姓名
	 */
	private String peopleName;
	/**
	 * 人员类型（2：罪犯）
	 */
	private String peopleType;
	/**
	 * 门禁卡ID
	 */
	private String cardID;
	/**
	 * 刷卡时间
	 */
	private String brushCardTime;
	/**
	 * 进出标识（0：出；1：进；2：未知）
	 */
	private String inOutFlag; 
	/**
	 * 罪犯所属监区
	 */
	private String secName;
	/**
	 * 门ID
	 */
	private String doorID;
	/**
	 * 门名称
	 */
	private String doorName;
	/**
	 * 门所属监舍号
	 */
	private String jsNo;
	/**
	 * 门所属部门编号
	 */
	private String deptID;
	/**
	 * 门所属部门名称
	 */
	private String deptName;
	/**
	 * 状态（未使用）
	 */
	private String status;
	/**
	 * 备注（未使用）
	 */
	private String remark;
	
	public String getPeopleID() {
		return peopleID;
	}
	public void setPeopleID(String peopleID) {
		this.peopleID = peopleID;
	}
	public String getPeopleName() {
		return peopleName;
	}
	public void setPeopleName(String peopleName) {
		this.peopleName = peopleName;
	}
	public String getPeopleType() {
		return peopleType;
	}
	public void setPeopleType(String peopleType) {
		this.peopleType = peopleType;
	}
	public String getCardID() {
		return cardID;
	}
	public void setCardID(String cardID) {
		this.cardID = cardID;
	}
	public String getBrushCardTime() {
		return brushCardTime;
	}
	public void setBrushCardTime(String brushCardTime) {
		this.brushCardTime = brushCardTime;
	}
	public String getInOutFlag() {
		return inOutFlag;
	}
	public void setInOutFlag(String inOutFlag) {
		this.inOutFlag = inOutFlag;
	}
	public String getSecName() {
		return secName;
	}
	public void setSecName(String secName) {
		this.secName = secName;
	}
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
	public String getJsNo() {
		return jsNo;
	}
	public void setJsNo(String jsNo) {
		this.jsNo = jsNo;
	}
	public String getDeptID() {
		return deptID;
	}
	public void setDeptID(String deptID) {
		this.deptID = deptID;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}