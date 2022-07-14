package com.cesgroup.prison.foreign.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Description: 外来人车登记表实体类
 * @author lincoln
 *
 */
@Entity
@Table(name = "CDS_FOREIGN_REGISTER")
public class ForeignRegister extends StringIDEntity {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 监狱编号
	 */
	private String frCusNumber;
	/**
	 * 进出事由
	 */
    private String frReason;
    /**
     * 进出类型：0:待审核;1:进 2 出 99 未知
     */
    private String frType;
    /**
     * 所到地点
     */
    private String frDirection;
    /**
     * 进入时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date frTime;
    /**
     * 汇报人{"id":value,"name":text}
     */
    private String frReportPeople;
    /**
     * 带队民警{"id":value,"name":text}
     */
    private String frOprtnPolice;
    /**
     * 记录人{"id":value,"name":text}
     */
    private String frRecordPeople;
    /**
     * 备注
     */
    private String frRemark;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date frCrteTime;
    /**
     * 创建人
     */
    private String frCrteUserId;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date frUpdtTime;
    /**
     * 修改人
     */
    private String frUpdtUserId;
    /**
     * 审批人id
     */
    private String frCheckPeopleId;
    /**
     * 审批人name
     */
    private String frCheckPeople;
    /**
     * 审批状态 0 未通过 1 通过
     */
    private String frCheckSttsIndc;
    /**
     * 审批说明
     */
    private String frCheckRemark;
    /**
     * 审批时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date frCheckTime;
    /**
     * 车辆信息
     */
    private String frCarsInfo;
    /**
     * 人员信息
     */
    private String frPeoplesInfo;
    /**
     * 离开时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date frOutTime;
    /**
     * 离开确认人NAME
     */
    private String frOutPeople;
    /**
     * 离开确认人id
     */
    private String frOutPeopleId;
    /**
     * 路线ID
     */
    private String frPathId;
    /**
     * 出入门禁位置
     */
    private String frLocation;
    /**
     * 车辆车牌照片
     */
    private String frPhoto1;
    /**
     * 车辆地盘照片
     */
    private String frPhoto2;    
    
	public String getFrCusNumber() {
		return frCusNumber;
	}
	public void setFrCusNumber(String frCusNumber) {
		this.frCusNumber = frCusNumber;
	}
	public String getFrReason() {
		return frReason;
	}
	public void setFrReason(String frReason) {
		this.frReason = frReason;
	}
	public String getFrType() {
		return frType;
	}
	public void setFrType(String frType) {
		this.frType = frType;
	}
	public String getFrDirection() {
		return frDirection;
	}
	public void setFrDirection(String frDirection) {
		this.frDirection = frDirection;
	}
	public Date getFrTime() {
		return frTime;
	}
	public void setFrTime(Date frTime) {
		this.frTime = frTime;
	}
	public String getFrReportPeople() {
		return frReportPeople;
	}
	public void setFrReportPeople(String frReportPeople) {
		this.frReportPeople = frReportPeople;
	}
	public String getFrOprtnPolice() {
		return frOprtnPolice;
	}
	public void setFrOprtnPolice(String frOprtnPolice) {
		this.frOprtnPolice = frOprtnPolice;
	}
	public String getFrRecordPeople() {
		return frRecordPeople;
	}
	public void setFrRecordPeople(String frRecordPeople) {
		this.frRecordPeople = frRecordPeople;
	}
	public String getFrRemark() {
		return frRemark;
	}
	public void setFrRemark(String frRemark) {
		this.frRemark = frRemark;
	}
	public Date getFrCrteTime() {
		return frCrteTime;
	}
	public void setFrCrteTime(Date frCrteTime) {
		this.frCrteTime = frCrteTime;
	}
	public String getFrCrteUserId() {
		return frCrteUserId;
	}
	public void setFrCrteUserId(String frCrteUserId) {
		this.frCrteUserId = frCrteUserId;
	}
	public Date getFrUpdtTime() {
		return frUpdtTime;
	}
	public void setFrUpdtTime(Date frUpdtTime) {
		this.frUpdtTime = frUpdtTime;
	}
	public String getFrUpdtUserId() {
		return frUpdtUserId;
	}
	public void setFrUpdtUserId(String frUpdtUserId) {
		this.frUpdtUserId = frUpdtUserId;
	}
	public String getFrCheckPeopleId() {
		return frCheckPeopleId;
	}
	public void setFrCheckPeopleId(String frCheckPeopleId) {
		this.frCheckPeopleId = frCheckPeopleId;
	}
	public String getFrCheckPeople() {
		return frCheckPeople;
	}
	public void setFrCheckPeople(String frCheckPeople) {
		this.frCheckPeople = frCheckPeople;
	}
	public String getFrCheckSttsIndc() {
		return frCheckSttsIndc;
	}
	public void setFrCheckSttsIndc(String frCheckSttsIndc) {
		this.frCheckSttsIndc = frCheckSttsIndc;
	}
	public String getFrCheckRemark() {
		return frCheckRemark;
	}
	public void setFrCheckRemark(String frCheckRemark) {
		this.frCheckRemark = frCheckRemark;
	}
	public Date getFrCheckTime() {
		return frCheckTime;
	}
	public void setFrCheckTime(Date frCheckTime) {
		this.frCheckTime = frCheckTime;
	}
	public String getFrCarsInfo() {
		return frCarsInfo;
	}
	public void setFrCarsInfo(String frCarsInfo) {
		this.frCarsInfo = frCarsInfo;
	}
	public String getFrPeoplesInfo() {
		return frPeoplesInfo;
	}
	public void setFrPeoplesInfo(String frPeoplesInfo) {
		this.frPeoplesInfo = frPeoplesInfo;
	}
	public Date getFrOutTime() {
		return frOutTime;
	}
	public void setFrOutTime(Date frOutTime) {
		this.frOutTime = frOutTime;
	}
	public String getFrOutPeople() {
		return frOutPeople;
	}
	public void setFrOutPeople(String frOutPeople) {
		this.frOutPeople = frOutPeople;
	}
	public String getFrOutPeopleId() {
		return frOutPeopleId;
	}
	public void setFrOutPeopleId(String frOutPeopleId) {
		this.frOutPeopleId = frOutPeopleId;
	}
	public String getFrPathId() {
		return frPathId;
	}
	public void setFrPathId(String frPathId) {
		this.frPathId = frPathId;
	}
	public String getFrLocation() {
		return frLocation;
	}
	public void setFrLocation(String frLocation) {
		this.frLocation = frLocation;
	}
	public String getFrPhoto1() {
		return frPhoto1;
	}
	public void setFrPhoto1(String frPhoto1) {
		this.frPhoto1 = frPhoto1;
	}
	public String getFrPhoto2() {
		return frPhoto2;
	}
	public void setFrPhoto2(String frPhoto2) {
		this.frPhoto2 = frPhoto2;
	}
}