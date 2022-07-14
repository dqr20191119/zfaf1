package com.cesgroup.prison.zbgl.lbgl.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.prison.zbgl.lbbm.entity.LbbmEntity;

@Table(name = "cds_duty_category")
public class LbglEntity extends StringIDEntity {
	
	private String dcaCusNumber;					// 监狱id
	private String dcaCategoryName;					// 岗位名称
	private String dcaRemark;						// 备注	
	private String dcaUpdtUserId;					// 更新id
	private Date dcaUpdtTime;					   //更新时间 
	private String dcaStatus;	
	@Column(updatable = false)
	private Date dcaCrteTime;						// 创建时间
	@Column(updatable = false)						
	private String dcaCrteUserId;                   // 创建人
	
	@Transient
	private String dcdCategoryId;                  //类别部门表中类别主键Id
	
	@Transient
	private String dcaDprtmntId;                  //部门
	
	@Transient
	private List<LbbmEntity> lbbmEntityList = new ArrayList<LbbmEntity>();

	public String getDcaDprtmntId() {
		return dcaDprtmntId;
	}

	public void setDcaDprtmntId(String dcaDprtmntId) {
		this.dcaDprtmntId = dcaDprtmntId;
	}

	public List<LbbmEntity> getLbbmEntityList() {
		return lbbmEntityList;
	}

	public void setLbbmEntityList(List<LbbmEntity> lbbmEntityList) {
		this.lbbmEntityList = lbbmEntityList;
	}

	public String getDcdCategoryId() {
		return dcdCategoryId;
	}

	public void setDcdCategoryId(String dcdCategoryId) {
		this.dcdCategoryId = dcdCategoryId;
	}

	public String getDcaCusNumber() {
		return dcaCusNumber;
	}

	public void setDcaCusNumber(String dcaCusNumber) {
		this.dcaCusNumber = dcaCusNumber;
	}
	
	public String getDcaCategoryName() {
		return dcaCategoryName;
	}

	public void setDcaCategoryName(String dcaCategoryName) {
		this.dcaCategoryName = dcaCategoryName;
	}

	public String getDcaRemark() {
		return dcaRemark;
	}

	public void setDcaRemark(String dcaRemark) {
		this.dcaRemark = dcaRemark;
	}

	public String getDcaUpdtUserId() {
		return dcaUpdtUserId;
	}

	public void setDcaUpdtUserId(String dcaUpdtUserId) {
		this.dcaUpdtUserId = dcaUpdtUserId;
	}

	public Date getDcaCrteTime() {
		return dcaCrteTime;
	}

	public void setDcaCrteTime(Date dcaCrteTime) {
		this.dcaCrteTime = dcaCrteTime;
	}

	public Date getDcaUpdtTime() {
		return dcaUpdtTime;
	}

	public void setDcaUpdtTime(Date dcaUpdtTime) {
		this.dcaUpdtTime = dcaUpdtTime;
	}

	public String getDcaCrteUserId() {
		return dcaCrteUserId;
	}

	public void setDcaCrteUserId(String dcaCrteUserId) {
		this.dcaCrteUserId = dcaCrteUserId;
	}
	public String getDcaStatus() {
		return dcaStatus;
	}

	public void setDcaStatus(String dcaStatus) {
		this.dcaStatus = dcaStatus;
	}

	
	
	
}
