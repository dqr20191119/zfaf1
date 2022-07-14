package com.cesgroup.prison.door.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 民警当前所在监区记录表
 * 
 * @author lincoln.cheng
 *
 */
@Entity
@Table(name = "cds_cur_inprsn_police_record")
public class PoliceInprsnEntity extends StringIDEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 监狱编号
	 */
	private String cipCusNumber;
	/**
	 * 民警警号
	 */
	private String cipPoliceIdnty;
	/**
	 * 民警姓名
	 */
	private String cipPoliceName;
	/**
	 * 门禁卡ID
	 */
	private String cipDoorCardIdnty;
	/**
	 * 门禁ID
	 */
	private String cipDoorIdnty;
	/**
	 * 民警进入监区时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date cipEnterTime;
	/**
	 * 数据创建时间
	 */
	@Column(updatable = false)
	private Date cipCrteTime;
	/**
	 * 数据创建人
	 */
	private String cipCrteUserId;
	/**
	 * 数据更新时间
	 */
	@Column(updatable = false)
	private Date cipUpdtTime;
	/**
	 * 数据更信任
	 */
	private String cipUpdtUserId;
	/**
	 * 数据来源
	 */
	private String cipDataSource;
	/**
	 * 人员类型
	 */
	private String cipPeopleTypeIndc;	
	
	public String getCipCusNumber() {
		return cipCusNumber;
	}
	public void setCipCusNumber(String cipCusNumber) {
		this.cipCusNumber = cipCusNumber;
	}
	public String getCipPoliceIdnty() {
		return cipPoliceIdnty;
	}
	public void setCipPoliceIdnty(String cipPoliceIdnty) {
		this.cipPoliceIdnty = cipPoliceIdnty;
	}
	public String getCipPoliceName() {
		return cipPoliceName;
	}
	public void setCipPoliceName(String cipPoliceName) {
		this.cipPoliceName = cipPoliceName;
	}
	public String getCipDoorCardIdnty() {
		return cipDoorCardIdnty;
	}
	public void setCipDoorCardIdnty(String cipDoorCardIdnty) {
		this.cipDoorCardIdnty = cipDoorCardIdnty;
	}
	public Date getCipEnterTime() {
		return cipEnterTime;
	}
	public void setCipEnterTime(Date cipEnterTime) {
		this.cipEnterTime = cipEnterTime;
	}
	public Date getCipCrteTime() {
		return cipCrteTime;
	}
	public void setCipCrteTime(Date cipCrteTime) {
		this.cipCrteTime = cipCrteTime;
	}
	public String getCipCrteUserId() {
		return cipCrteUserId;
	}
	public void setCipCrteUserId(String cipCrteUserId) {
		this.cipCrteUserId = cipCrteUserId;
	}
	public Date getCipUpdtTime() {
		return cipUpdtTime;
	}
	public void setCipUpdtTime(Date cipUpdtTime) {
		this.cipUpdtTime = cipUpdtTime;
	}
	public String getCipUpdtUserId() {
		return cipUpdtUserId;
	}
	public void setCipUpdtUserId(String cipUpdtUserId) {
		this.cipUpdtUserId = cipUpdtUserId;
	}
	public String getCipDataSource() {
		return cipDataSource;
	}
	public void setCipDataSource(String cipDataSource) {
		this.cipDataSource = cipDataSource;
	}
	public String getCipDoorIdnty() {
		return cipDoorIdnty;
	}
	public void setCipDoorIdnty(String cipDoorIdnty) {
		this.cipDoorIdnty = cipDoorIdnty;
	}
	public String getCipPeopleTypeIndc() {
		return cipPeopleTypeIndc;
	}
	public void setCipPeopleTypeIndc(String cipPeopleTypeIndc) {
		this.cipPeopleTypeIndc = cipPeopleTypeIndc;
	}
}