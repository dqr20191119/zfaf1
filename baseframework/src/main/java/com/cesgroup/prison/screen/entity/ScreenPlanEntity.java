package com.cesgroup.prison.screen.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：prison   
* @ClassName：ScreenPlanEntity   
* @Description：   大屏预案
* @author：Tao.xu   
* @date：2018年1月3日 下午6:41:58   
* @version        
*/
@Entity
@Table(name = "CDS_SCREEN_PLAN")
public class ScreenPlanEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = 1L;
    /**
     *监狱编号
     */
	private String splCusNumber;
    /**
     * 预案名称
     */
	private String splPlanName;
    /**
     * 使用状态
     */
    private String splStatusIndc;

	private String splRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date splCrteTime;

	/**
	* @Fields splIsDynamic : 是否动态信号源 0 否 1是
	*/
	private String splIsDynamic;

	private String splCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date splUpdtTime;

	private String splUpdtUserId;

	/**
	* @Fields splPlanIndc : 预案编号
	*/
	private String splPlanIndc;

	/**
	* @Fields splManufacturersId : 大屏厂家id
	*/
	private String splManufacturersId;

	public String getSplCusNumber() {
		return splCusNumber;
	}

	public String getSplIsDynamic() {
		return splIsDynamic;
	}

	public void setSplIsDynamic(String splIsDynamic) {
		this.splIsDynamic = splIsDynamic;
	}

	public void setSplCusNumber(String splCusNumber) {
		this.splCusNumber = splCusNumber;
	}

	public String getSplPlanName() {
		return splPlanName;
	}

	public void setSplPlanName(String splPlanName) {
		this.splPlanName = splPlanName;
	}

	public String getSplStatusIndc() {
		return splStatusIndc;
	}

	public void setSplStatusIndc(String splStatusIndc) {
		this.splStatusIndc = splStatusIndc;
	}

	public String getSplRemark() {
		return splRemark;
	}

	public void setSplRemark(String splRemark) {
		this.splRemark = splRemark;
	}

	public Date getSplCrteTime() {
		return splCrteTime;
	}

	public void setSplCrteTime(Date splCrteTime) {
		this.splCrteTime = splCrteTime;
	}

	public String getSplCrteUserId() {
		return splCrteUserId;
	}

	public void setSplCrteUserId(String splCrteUserId) {
		this.splCrteUserId = splCrteUserId;
	}

	public Date getSplUpdtTime() {
		return splUpdtTime;
	}

	public void setSplUpdtTime(Date splUpdtTime) {
		this.splUpdtTime = splUpdtTime;
	}

	public String getSplUpdtUserId() {
		return splUpdtUserId;
	}

	public void setSplUpdtUserId(String splUpdtUserId) {
		this.splUpdtUserId = splUpdtUserId;
	}

	public String getSplPlanIndc() {
		return splPlanIndc;
	}

	public void setSplPlanIndc(String splPlanIddc) {
		this.splPlanIndc = splPlanIddc;
	}

	public String getSplManufacturersId() {
		return splManufacturersId;
	}

	public void setSplManufacturersId(String splManufacturersId) {
		this.splManufacturersId = splManufacturersId;
	}

}
