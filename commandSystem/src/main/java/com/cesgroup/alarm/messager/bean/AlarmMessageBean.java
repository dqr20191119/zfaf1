package com.cesgroup.alarm.messager.bean;

/**
 * 报警消息实体类
 */
public class AlarmMessageBean {
	/* 报警设备消息公共字段 */
	private String alarmID;				// 报警编号
	private String alarmDeviceType;		// 报警设备类型

	/* 报警设备报警消息字段 */
	private String alarmType;			// 报警类型
    private String alarmTime;			// 报警时间
    private String alarmAction;			// 报警动作
    private String remark;				// 备注

    /* 报警设备布、撤防消息字段 */
    private String action;				// 消息动作

    /* 报警设备状态检测消息字段 */
    private String status;				// 状态

    /* 备用扩展字段 */
    private String item1;  				// 备用字段1：IP
    private String item2;				// 备用字段2


	public String getAlarmID() {
		return alarmID;
	}
	public void setAlarmID(String alarmID) {
		this.alarmID = alarmID;
	}
	public String getAlarmDeviceType() {
		return alarmDeviceType;
	}
	public void setAlarmDeviceType(String alarmDeviceType) {
		this.alarmDeviceType = alarmDeviceType;
	}
	public String getAlarmType() {
		return alarmType;
	}
	public void setAlarmType(String alarmType) {
		this.alarmType = alarmType;
	}
	public String getAlarmTime() {
		return alarmTime;
	}
	public void setAlarmTime(String alarmTime) {
		this.alarmTime = alarmTime;
	}
	public String getAlarmAction() {
		return alarmAction;
	}
	public void setAlarmAction(String alarmAction) {
		this.alarmAction = alarmAction;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getItem1() {
		return item1;
	}
	public void setItem1(String item1) {
		this.item1 = item1;
	}
	public String getItem2() {
		return item2;
	}
	public void setItem2(String item2) {
		this.item2 = item2;
	}

    @Override
    public String toString() {
        return "AlarmMessageBean{" +
                "alarmID='" + alarmID + '\'' +
                ", alarmDeviceType='" + alarmDeviceType + '\'' +
                ", alarmType='" + alarmType + '\'' +
                ", alarmTime='" + alarmTime + '\'' +
                ", alarmAction='" + alarmAction + '\'' +
                ", remark='" + remark + '\'' +
                ", action='" + action + '\'' +
                ", status='" + status + '\'' +
                ", item1='" + item1 + '\'' +
                ", item2='" + item2 + '\'' +
                '}';
    }
}
