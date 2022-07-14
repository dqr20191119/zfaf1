package com.cesgroup.prison.patrolrecord.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

/**
 * 巡更记录
 * @author admin
 *
 */
@Entity
@Table(name = "T_B_XGJL")
public class PatrolrecordEntity  extends StringIDEntity {

	private static final long serialVersionUID = 1L;
	
	private String deptNo;
	private String devId;
	private String devName;
	private String time;
	private String policeNo;
	private String name;
	private String deptName;
	
	public String getDeptNo() {
		return deptNo;
	}
	public void setDeptNo(String deptNo) {
		this.deptNo = deptNo;
	}
	public String getDevId() {
		return devId;
	}
	public void setDevId(String devId) {
		this.devId = devId;
	}
	public String getDevName() {
		return devName;
	}
	public void setDevName(String devName) {
		this.devName = devName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getPoliceNo() {
		return policeNo;
	}
	public void setPoliceNo(String policeNo) {
		this.policeNo = policeNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	

}
