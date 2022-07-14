package com.cesgroup.prison.door.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 民警进出历史记录表
 * 
 * @author lincoln.cheng
 *
 */
@Entity
@Table(name = "cds_police_inout_record_h")
public class PoliceInoutHisEntity extends StringIDEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 监狱编号
	 */
	private String pirCusNumber;
	/**
	 * 刷卡时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date pirBrushDate;
	/**
	 * 警号
	 */
	private String pirPoliceIdnty;
	/**
	 * 民警姓名
	 */
	private String pirPoliceName;
	/**
	 * 进入时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date pirEnterTime;
	/**
	 * 离开时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date pirLeaveTime;
	/**
	 * 部门编码
	 */
	private String pirDprtmntId;
	/**
	 * 部门名称
	 */
	private String pirDprtmntName;
	/**
	 * 门禁卡编号
	 */
	private String pirDoorCardIdnty;
	/**
	 * 门禁卡类型()
	 */
	private String pirDoorCardTypeIdnty;
	/**
	 * 人员类型
	 */
 	private String pirPeopleTypeIndc;
 	/**
 	 * 备注
 	 */
	private String pirRemark;
	/**
	 * 数据创建时间
	 */
	@Column(updatable = false)
	private Date pirCrteTime;
	/**
	 * 数据创建人
	 */
	private String pirCrteUserId;
	/**
	 * 数据修改时间
	 */
	@Column(updatable = false)
	private Date pirUpdtTime;
	/**
	 * 数据修改人
	 */
	private String pirUpdtUserId;
	/**
	 * 门禁ID
	 */
 	private String pirDoorIdnty;
 	/**
 	 * 门禁名称
 	 */
 	private String pirDoorName;
	/**
	 * 上级部门编码
	 */
	private String pirParentDprtmntId;
	/**
	 * 上级部门名称
	 */
	private String pirParentDprtmntName;
	/**
	 * 是否进出AB门(即，是否进出监狱的大门，1:是; 空:否)
	 */
	private String pirAbFlag;
	/**
	 * 民警进出门禁标识(1:进; 2:出)
	 */
	private String pirInOutIndc;
	@Transient
	private String notNullEnterTime;
	@Transient
	private String notNullLeaveTime;
	
	public String getPirCusNumber() {
		return pirCusNumber;
	}
	public void setPirCusNumber(String pirCusNumber) {
		this.pirCusNumber = pirCusNumber;
	}
	public Date getPirBrushDate() {
		return pirBrushDate;
	}
	public void setPirBrushDate(Date pirBrushDate) {
		this.pirBrushDate = pirBrushDate;
	}
	public String getPirPoliceIdnty() {
		return pirPoliceIdnty;
	}
	public void setPirPoliceIdnty(String pirPoliceIdnty) {
		this.pirPoliceIdnty = pirPoliceIdnty;
	}
	public String getPirPoliceName() {
		return pirPoliceName;
	}
	public void setPirPoliceName(String pirPoliceName) {
		this.pirPoliceName = pirPoliceName;
	}
	public Date getPirEnterTime() {
		return pirEnterTime;
	}
	public void setPirEnterTime(Date pirEnterTime) {
		this.pirEnterTime = pirEnterTime;
	}
	public Date getPirLeaveTime() {
		return pirLeaveTime;
	}
	public void setPirLeaveTime(Date pirLeaveTime) {
		this.pirLeaveTime = pirLeaveTime;
	}
	public String getPirDprtmntId() {
		return pirDprtmntId;
	}
	public void setPirDprtmntId(String pirDprtmntId) {
		this.pirDprtmntId = pirDprtmntId;
	}
	public String getPirDprtmntName() {
		return pirDprtmntName;
	}
	public void setPirDprtmntName(String pirDprtmntName) {
		this.pirDprtmntName = pirDprtmntName;
	}
	public String getPirDoorCardIdnty() {
		return pirDoorCardIdnty;
	}
	public void setPirDoorCardIdnty(String pirDoorCardIdnty) {
		this.pirDoorCardIdnty = pirDoorCardIdnty;
	}
	public String getPirDoorCardTypeIdnty() {
		return pirDoorCardTypeIdnty;
	}
	public void setPirDoorCardTypeIdnty(String pirDoorCardTypeIdnty) {
		this.pirDoorCardTypeIdnty = pirDoorCardTypeIdnty;
	}
	public String getPirPeopleTypeIndc() {
		return pirPeopleTypeIndc;
	}
	public void setPirPeopleTypeIndc(String pirPeopleTypeIndc) {
		this.pirPeopleTypeIndc = pirPeopleTypeIndc;
	}
	public String getPirRemark() {
		return pirRemark;
	}
	public void setPirRemark(String pirRemark) {
		this.pirRemark = pirRemark;
	}
	public Date getPirCrteTime() {
		return pirCrteTime;
	}
	public void setPirCrteTime(Date pirCrteTime) {
		this.pirCrteTime = pirCrteTime;
	}
	public String getPirCrteUserId() {
		return pirCrteUserId;
	}
	public void setPirCrteUserId(String pirCrteUserId) {
		this.pirCrteUserId = pirCrteUserId;
	}
	public Date getPirUpdtTime() {
		return pirUpdtTime;
	}
	public void setPirUpdtTime(Date pirUpdtTime) {
		this.pirUpdtTime = pirUpdtTime;
	}
	public String getPirUpdtUserId() {
		return pirUpdtUserId;
	}
	public void setPirUpdtUserId(String pirUpdtUserId) {
		this.pirUpdtUserId = pirUpdtUserId;
	}
	public String getPirDoorIdnty() {
		return pirDoorIdnty;
	}
	public void setPirDoorIdnty(String pirDoorIdnty) {
		this.pirDoorIdnty = pirDoorIdnty;
	}
	public String getPirDoorName() {
		return pirDoorName;
	}
	public void setPirDoorName(String pirDoorName) {
		this.pirDoorName = pirDoorName;
	}
	public String getPirParentDprtmntId() {
		return pirParentDprtmntId;
	}
	public void setPirParentDprtmntId(String pirParentDprtmntId) {
		this.pirParentDprtmntId = pirParentDprtmntId;
	}
	public String getPirParentDprtmntName() {
		return pirParentDprtmntName;
	}
	public void setPirParentDprtmntName(String pirParentDprtmntName) {
		this.pirParentDprtmntName = pirParentDprtmntName;
	}
	public String getPirAbFlag() {
		return pirAbFlag;
	}
	public void setPirAbFlag(String pirAbFlag) {
		this.pirAbFlag = pirAbFlag;
	}
	public String getPirInOutIndc() {
		return pirInOutIndc;
	}
	public void setPirInOutIndc(String pirInOutIndc) {
		this.pirInOutIndc = pirInOutIndc;
	}
	public String getNotNullEnterTime() {
		return notNullEnterTime;
	}
	public void setNotNullEnterTime(String notNullEnterTime) {
		this.notNullEnterTime = notNullEnterTime;
	}
	public String getNotNullLeaveTime() {
		return notNullLeaveTime;
	}
	public void setNotNullLeaveTime(String notNullLeaveTime) {
		this.notNullLeaveTime = notNullLeaveTime;
	}
}