package com.cesgroup.prison.outsider.bean;

import java.io.Serializable;

/**
 * 外来人员Bean
 * 
 * @author monkey
 *
 */
public class OutsiderBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 监狱编号
	 */
	private String CusNumber;
	/**
	 * 姓名
	 */
	private String Name;
	/**
	 * 性别
	 */
	private String Sex;
	/**
	 * 身份证号
	 */
	private String ID;
	/**
	 * 案件结果
	 */
	private String Birthday;
	/**
	 * 登记日期
	 */
	private String RecordDate;
	/**
	 * 事由
	 */
	private String Cause;
	/**
	 * 车牌号
	 */
	private String PlateNumber;
	/**
	 * 
	 */
	private String Cxys;
	/**
	 * 照片
	 */
	private String Photo;
	/**
	 * 审批领导
	 */
	private String Leader;
	/**
	 * 操作员姓名
	 */
	private String PoliceName;
	/**
	 * 操作员警号
	 */
	private String PoliceNo;
	/**
	 * 部门
	 */
	private String Department;
	/**
	 * 安检地址
	 */
	private String Location;
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getBirthday() {
		return Birthday;
	}
	public void setBirthday(String birthday) {
		Birthday = birthday;
	}
	public String getRecordDate() {
		return RecordDate;
	}
	public void setRecordDate(String recordDate) {
		RecordDate = recordDate;
	}
	public String getCause() {
		return Cause;
	}
	public void setCause(String cause) {
		Cause = cause;
	}
	public String getPlateNumber() {
		return PlateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		PlateNumber = plateNumber;
	}
	public String getPhoto() {
		return Photo;
	}
	public void setPhoto(String photo) {
		Photo = photo;
	}
	public String getLeader() {
		return Leader;
	}
	public void setLeader(String leader) {
		Leader = leader;
	}
	public String getPoliceName() {
		return PoliceName;
	}
	public void setPoliceName(String policeName) {
		PoliceName = policeName;
	}
	public String getPoliceNo() {
		return PoliceNo;
	}
	public void setPoliceNo(String policeNo) {
		PoliceNo = policeNo;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getCusNumber() {
		return CusNumber;
	}
	public void setCusNumber(String cusNumber) {
		CusNumber = cusNumber;
	}
	public String getCxys() {
		return Cxys;
	}
	public void setCxys(String cxys) {
		Cxys = cxys;
	}
	
	
	
}