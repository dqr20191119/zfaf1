package com.cesgroup.prison.yjct.entity;

import java.io.Serializable;
import java.util.List;


public class YjyabgEntity implements Serializable{

	private static final long serialVersionUID = -8641306206131441154L;
	private String alertArea;						// 报警地点
	private String alertTime;						// 报警时间
	private  String planName;						//预案名称
	private  String createTime;						//启动时间
	private  String	 receiveAlarmPolice;		   //接警人RECEIVE_ALARM_POLICE
	private String receiveTime;					   //接警时间
	private String processRecord;				  //过程记录PROCESS
	private String planFid;                      //预案id
	private List<List<GzzcyEntity>> workgroup;   //工作组
	private List<GzzcyEntity> list;              //工作组成员

	public List<List<GzzcyEntity>> getWorkgroup() {
		return workgroup;
	}

	public void setWorkgroup(List<List<GzzcyEntity>> workgroup) {
		this.workgroup = workgroup;
	}

	public List<GzzcyEntity> getList() {
		return list;
	}

	public void setList(List<GzzcyEntity> list) {
		this.list = list;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getAlertArea() {
		return alertArea;
	}

	public void setAlertArea(String alertArea) {
		this.alertArea = alertArea;
	}

	public String getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(String alertTime) {
		this.alertTime = alertTime;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getReceiveAlarmPolice() {
		return receiveAlarmPolice;
	}

	public void setReceiveAlarmPolice(String receiveAlarmPolice) {
		this.receiveAlarmPolice = receiveAlarmPolice;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getProcessRecord() {
		return processRecord;
	}

	public void setProcessRecord(String processRecord) {
		this.processRecord = processRecord;
	}

	public String getPlanFid() {
		return planFid;
	}

	public void setPlanFid(String planFid) {
		this.planFid = planFid;
	}

	@Override
	public String toString() {
		return "YjyabgEntity{" +
				"alertArea='" + alertArea + '\'' +
				", alertTime='" + alertTime + '\'' +
				", planName='" + planName + '\'' +
				", createTime='" + createTime + '\'' +
				", receiveAlarmPolice='" + receiveAlarmPolice + '\'' +
				", receiveTime='" + receiveTime + '\'' +
				", processRecord='" + processRecord + '\'' +
				", planFid='" + planFid + '\'' +
				", list=" + list +
				'}';
	}
}
