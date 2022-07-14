package com.cesgroup.prison.door.bean;

public class PoliceInoutBean {
	/**
	 * 民警ID
	 */
	private String peopleID;
	/**
	 * 民警姓名
	 */
	private String peopleName;
	/**
	 * 民警类型
	 */
	private String peopleType;
	/**
	 * 刷卡ID
	 */
	private String cardID;
	/**
	 * 卡类型
	 */
	private String CardType;
	/**
	 * 
	 */
	private String compareTime;
	/**
	 * 进出标识 1,进监 2、离监
	 */
	private String inOutFlag;
	/**
	 * 门禁ID  
	 * 湖南 这边没有ab门标识，所以把换证记录推送过来，当作进出ab门标识，换证推过来的数据门禁ID为 ABDoor, ABFlag为 1
	 */
	private String doorID;
	/**
	 * 门禁名称
	 */
	private String DoorName;
	/**
	 * 部门编号
	 */
	private String bmbh;
	/**
	 * 部门名称
	 */
	private String bmmc;
	/**
	 * 上级部门编号
	 */
	private String sjbmbh;
	/**
	 * 上级部门名称
	 */
	private String sjbmmc;
	/**
	 * 事件结果编码
	 */
	private Integer EventRet;
	/**
	 * 事件结果值
	 */
	private String EventRetValue;
	/**
	 * 事件类型
	 */
	private Integer EventType;
	/**
	 * 时间时间
	 */
	private String EventTime;
	/**
	 * 是否AB门(即，是否进出监狱的大门) (0: 否 ，1：是)
	 */
	private String ABFlag;
	
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
	public String getCardType() {
		return CardType;
	}
	public void setCardType(String cardType) {
		CardType = cardType;
	}
	public String getCompareTime() {
		return compareTime;
	}
	public void setCompareTime(String compareTime) {
		this.compareTime = compareTime;
	}
	public String getInOutFlag() {
		return inOutFlag;
	}
	public void setInOutFlag(String inOutFlag) {
		this.inOutFlag = inOutFlag;
	}
	public String getDoorID() {
		return doorID;
	}
	public void setDoorID(String doorID) {
		this.doorID = doorID;
	}
	public String getDoorName() {
		return DoorName;
	}
	public void setDoorName(String doorName) {
		DoorName = doorName;
	}
	public String getBmbh() {
		return bmbh;
	}
	public void setBmbh(String bmbh) {
		this.bmbh = bmbh;
	}
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public String getSjbmbh() {
		return sjbmbh;
	}
	public void setSjbmbh(String sjbmbh) {
		this.sjbmbh = sjbmbh;
	}
	public String getSjbmmc() {
		return sjbmmc;
	}
	public void setSjbmmc(String sjbmmc) {
		this.sjbmmc = sjbmmc;
	}
	public Integer getEventRet() {
		return EventRet;
	}
	public void setEventRet(Integer eventRet) {
		EventRet = eventRet;
	}
	public String getEventRetValue() {
		return EventRetValue;
	}
	public void setEventRetValue(String eventRetValue) {
		EventRetValue = eventRetValue;
	}
	public Integer getEventType() {
		return EventType;
	}
	public void setEventType(Integer eventType) {
		EventType = eventType;
	}
	public String getEventTime() {
		return EventTime;
	}
	public void setEventTime(String eventTime) {
		EventTime = eventTime;
	}
	public String getABFlag() {
		return ABFlag;
	}
	public void setABFlag(String aBFlag) {
		ABFlag = aBFlag;
	}
}