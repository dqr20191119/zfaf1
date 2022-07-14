package com.cesgroup.prison.zbgl.mbbm.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/*模板部门关联表
 * */
@Table(name = "cds_duty_mode_department")
public class MbbmEntity extends StringIDEntity {
	
	private String dmdCusNumber;					// 监狱id
	private String dmdModeId;                       //模板ID
	private String dmdDprtmntId;                    //部门ID
//	private String dmdStatus;
	private String dmdCategoryId;                   //类别ID
	private String dmdZt;//发布状态  0.未发布 1.已发布
	private String dmdName;//排班表名称
	@DateTimeFormat(pattern = "yyyy-MM-dd")             
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dmdStartDate;                    //编排开始时间
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dmdEndDate;                     //编排结束时间
	
	private String dmdUpdtUserId;					// 更新人id
	private Date dmdUpdtTime;					   //更新时间 
	
	@Column(updatable = false)
	private Date dmdCrteTime;						// 创建时间
	@Column(updatable = false)						
	private String dmdCrteUserId;                   // 创建人id
	
	@Transient
	private String cdmCategoryId;                   //类别ID
	
	@Transient
	private String allPoliceList ;              //值班人员信息
	
	@Transient
	private String param;  
	
	@Transient
	private String cdmPeriod;
	
	@Transient
	private String cdmModeName;                //模板名称      
	
	
	
	public String getDmdZt() {
		return dmdZt;
	}
	public void setDmdZt(String dmdZt) {
		this.dmdZt = dmdZt;
	}
	public String getDmdName() {
		return dmdName;
	}
	public void setDmdName(String dmdName) {
		this.dmdName = dmdName;
	}
	public String getCdmModeName() {
		return cdmModeName;
	}
	public void setCdmModeName(String cdmModeName) {
		this.cdmModeName = cdmModeName;
	}
	public String getCdmPeriod() {
		return cdmPeriod;
	}
	public void setCdmPeriod(String cdmPeriod) {
		this.cdmPeriod = cdmPeriod;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getDmdCusNumber() {
		return dmdCusNumber;
	}
	public void setDmdCusNumber(String dmdCusNumber) {
		this.dmdCusNumber = dmdCusNumber;
	}
	public String getDmdModeId() {
		return dmdModeId;
	}
	public void setDmdModeId(String dmdModeId) {
		this.dmdModeId = dmdModeId;
	}
	public String getDmdDprtmntId() {
		return dmdDprtmntId;
	}
	public void setDmdDprtmntId(String dmdDprtmntId) {
		this.dmdDprtmntId = dmdDprtmntId;
	}
	public String getDmdUpdtUserId() {
		return dmdUpdtUserId;
	}
	public void setDmdUpdtUserId(String dmdUpdtUserId) {
		this.dmdUpdtUserId = dmdUpdtUserId;
	}
	public Date getDmdUpdtTime() {
		return dmdUpdtTime;
	}
	public void setDmdUpdtTime(Date dmdUpdtTime) {
		this.dmdUpdtTime = dmdUpdtTime;
	}
	public Date getDmdCrteTime() {
		return dmdCrteTime;
	}
	public void setDmdCrteTime(Date dmdCrteTime) {
		this.dmdCrteTime = dmdCrteTime;
	}
	public String getDmdCrteUserId() {
		return dmdCrteUserId;
	}
	public void setDmdCrteUserId(String dmdCrteUserId) {
		this.dmdCrteUserId = dmdCrteUserId;
	}
	public Date getDmdStartDate() {
		return dmdStartDate;
	}
	public void setDmdStartDate(Date dmdStartDate) {
		this.dmdStartDate = dmdStartDate;
	}
	public Date getDmdEndDate() {
		return dmdEndDate;
	}
	public void setDmdEndDate(Date dmdEndDate) {
		this.dmdEndDate = dmdEndDate;
	}
	public String getCdmCategoryId() {
		return cdmCategoryId;
	}
	public void setCdmCategoryId(String cdmCategoryId) {
		this.cdmCategoryId = cdmCategoryId;
	}
	public String getAllPoliceList() {
		return allPoliceList;
	}
	public void setAllPoliceList(String allPoliceList) {
		this.allPoliceList = allPoliceList;
	}
	/*public String getDmdStatus() {
		return dmdStatus;
	}
	public void setDmdStatus(String dmdStatus) {
		this.dmdStatus = dmdStatus;
	}*/
	public String getDmdCategoryId() {
		return dmdCategoryId;
	}
	public void setDmdCategoryId(String dmdCategoryId) {
		this.dmdCategoryId = dmdCategoryId;
	}
}
