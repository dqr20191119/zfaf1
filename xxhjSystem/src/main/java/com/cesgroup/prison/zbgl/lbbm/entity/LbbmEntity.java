package com.cesgroup.prison.zbgl.lbbm.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "cds_duty_category_department")
public class LbbmEntity extends StringIDEntity {
	
	private String dcdCusNumber;					// 监狱id
	private String dcdCategoryId;                   // 类别ID
	private String dcdDprtmntId;					// 部门id
	//private String dcdStatus;                       // 状态
	private Date   dcdUpdtTime;						// 更新时间	
	private String dcdUpdtUserId;					// 更新id
	
	@Column(updatable = false)
	private Date dcdCrteTime;						// 创建时间
	@Column(updatable = false)						
	private String dcdCrteUserId;					// 创建人
	
	@Transient
	private List<LbbmEntity> lbbmEntityList = new ArrayList<LbbmEntity>();
	
	@Transient
	private String param;                            //1 是查询下拉框数据 
	
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	public String getDcdCategoryId() {
		return dcdCategoryId;
	}
	public void setDcdCategoryId(String dcdCategoryId) {
		this.dcdCategoryId = dcdCategoryId;
	}
	public List<LbbmEntity> getLbbmEntityList() {
		return lbbmEntityList;
	}
	public void setLbbmEntityList(List<LbbmEntity> lbbmEntityList) {
		this.lbbmEntityList = lbbmEntityList;
	}
	
	public String getDcdCusNumber() {
		return dcdCusNumber;
	}
	public void setDcdCusNumber(String dcdCusNumber) {
		this.dcdCusNumber = dcdCusNumber;
	}
	public String getDcdDprtmntId() {
		return dcdDprtmntId;
	}
	public void setDcdDprtmntId(String dcdDprtmntId) {
		this.dcdDprtmntId = dcdDprtmntId;
	}
	/*public String getDcdStatus() {
		return dcdStatus;
	}
	public void setDcdStatus(String dcdStatus) {
		this.dcdStatus = dcdStatus;
	}*/
	public Date getDcdUpdtTime() {
		return dcdUpdtTime;
	}
	public void setDcdUpdtTime(Date dcdUpdtTime) {
		this.dcdUpdtTime = dcdUpdtTime;
	}
	public String getDcdUpdtUserId() {
		return dcdUpdtUserId;
	}
	public void setDcdUpdtUserId(String dcdUpdtUserId) {
		this.dcdUpdtUserId = dcdUpdtUserId;
	}
	public Date getDcdCrteTime() {
		return dcdCrteTime;
	}
	public void setDcdCrteTime(Date dcdCrteTime) {
		this.dcdCrteTime = dcdCrteTime;
	}
	public String getDcdCrteUserId() {
		return dcdCrteUserId;
	}
	public void setDcdCrteUserId(String dcdCrteUserId) {
		this.dcdCrteUserId = dcdCrteUserId;
	}
	
	
	

	
	


}
