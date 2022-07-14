package com.cesgroup.prison.yjct.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "cds_emergency_handle_record")
public class YjjlEntity extends StringIDEntity {
	
	private String ehrCusNumber;						// 监狱id
	private String ehrType;								// 类型	1-应急处置，2-应急演练
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date ehrTime;								// 时间
	private String ehrAddress;							// 地点
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date ehrSaveTime;							// 存档时间
	private String ehrExperience;						// 经验总结
 	private String ehrStatus;							// 状态	1-处置中，2-处置完成，3-已存档
	private String ehrEmPlanFid;						// 预案id
	private String ehrAlarmPlanFid;						// 报警联动预案id
	private String ehrAddressFid;						// 区域id
	private String ehrAlarmRecordId;					// 报警记录id
	
	@Column(updatable = false)
	private Date ehrCrteTime;							// 创建时间
	@Column(updatable = false)
	private String ehrCrteUserId;						// 创建人id
	private Date ehrUpdtTime;							// 更新时间
	private String ehrUpdtUserId;						// 更新人id
	
	@Transient
	private String epiPlanName;	
	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ehrTimeStart;
	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ehrTimeEnd;
	@Transient
	private String epiPlanType;
	@Transient
	private String timeType;
	@Transient
	private String cs;
	@Transient
	private String tjEhrTime;
	@Transient
	private int czlcNum;
	@Transient
	private String jlbstAffixIds;
	@Transient
	private String zzjtAffixIds;
	@Transient
	private String rydjbAffixIds;
	@Transient
	private String vedioPic;
	@Transient
	private String vedio;
	@Transient
	private String abdAreaName;
	@Transient
	private List<AffixEntity> jlbstAffixIdList = new ArrayList<AffixEntity>();
	@Transient
	private List<AffixEntity> zzjtAffixIdList = new ArrayList<AffixEntity>();
	@Transient
	private List<AffixEntity> rydjbAffixIdList = new ArrayList<AffixEntity>();
	
	
	public List<AffixEntity> getJlbstAffixIdList() {
		return jlbstAffixIdList;
	}
	public void setJlbstAffixIdList(List<AffixEntity> jlbstAffixIdList) {
		this.jlbstAffixIdList = jlbstAffixIdList;
	}
	public List<AffixEntity> getZzjtAffixIdList() {
		return zzjtAffixIdList;
	}
	public void setZzjtAffixIdList(List<AffixEntity> zzjtAffixIdList) {
		this.zzjtAffixIdList = zzjtAffixIdList;
	}
	public List<AffixEntity> getRydjbAffixIdList() {
		return rydjbAffixIdList;
	}
	public void setRydjbAffixIdList(List<AffixEntity> rydjbAffixIdList) {
		this.rydjbAffixIdList = rydjbAffixIdList;
	}
	public String getEhrCusNumber() {
		return ehrCusNumber;
	}
	public void setEhrCusNumber(String ehrCusNumber) {
		this.ehrCusNumber = ehrCusNumber;
	}
	public String getEhrType() {
		return ehrType;
	}
	public void setEhrType(String ehrType) {
		this.ehrType = ehrType;
	}
	public Date getEhrTime() {
		return ehrTime;
	}
	public void setEhrTime(Date ehrTime) {
		this.ehrTime = ehrTime;
	}
	public String getEhrAddress() {
		return ehrAddress;
	}
	public void setEhrAddress(String ehrAddress) {
		this.ehrAddress = ehrAddress;
	}
	public String getEhrStatus() {
		return ehrStatus;
	}
	public void setEhrStatus(String ehrStatus) {
		this.ehrStatus = ehrStatus;
	}
	public Date getEhrCrteTime() {
		return ehrCrteTime;
	}
	public void setEhrCrteTime(Date ehrCrteTime) {
		this.ehrCrteTime = ehrCrteTime;
	}
	public String getEhrCrteUserId() {
		return ehrCrteUserId;
	}
	public void setEhrCrteUserId(String ehrCrteUserId) {
		this.ehrCrteUserId = ehrCrteUserId;
	}
	public Date getEhrUpdtTime() {
		return ehrUpdtTime;
	}
	public void setEhrUpdtTime(Date ehrUpdtTime) {
		this.ehrUpdtTime = ehrUpdtTime;
	}
	public String getEhrUpdtUserId() {
		return ehrUpdtUserId;
	}
	public void setEhrUpdtUserId(String ehrUpdtUserId) {
		this.ehrUpdtUserId = ehrUpdtUserId;
	}
	public Date getEhrSaveTime() {
		return ehrSaveTime;
	}
	public void setEhrSaveTime(Date ehrSaveTime) {
		this.ehrSaveTime = ehrSaveTime;
	}
	public String getEhrExperience() {
		return ehrExperience;
	}
	public void setEhrExperience(String ehrExperience) {
		this.ehrExperience = ehrExperience;
	}
	public String getEhrEmPlanFid() {
		return ehrEmPlanFid;
	}
	public void setEhrEmPlanFid(String ehrEmPlanFid) {
		this.ehrEmPlanFid = ehrEmPlanFid;
	}
	public String getEhrAlarmPlanFid() {
		return ehrAlarmPlanFid;
	}
	public void setEhrAlarmPlanFid(String ehrAlarmPlanFid) {
		this.ehrAlarmPlanFid = ehrAlarmPlanFid;
	}
	public String getEpiPlanName() {
		return epiPlanName;
	}
	public void setEpiPlanName(String epiPlanName) {
		this.epiPlanName = epiPlanName;
	}
	public Date getEhrTimeStart() {
		return ehrTimeStart;
	}
	public void setEhrTimeStart(Date ehrTimeStart) {
		this.ehrTimeStart = ehrTimeStart;
	}
	public Date getEhrTimeEnd() {
		return ehrTimeEnd;
	}
	public void setEhrTimeEnd(Date ehrTimeEnd) {
		this.ehrTimeEnd = ehrTimeEnd;
	}
	public String getEpiPlanType() {
		return epiPlanType;
	}
	public void setEpiPlanType(String epiPlanType) {
		this.epiPlanType = epiPlanType;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getCs() {
		return cs;
	}
	public void setCs(String cs) {
		this.cs = cs;
	}
	public String getTjEhrTime() {
		return tjEhrTime;
	}
	public void setTjEhrTime(String tjEhrTime) {
		this.tjEhrTime = tjEhrTime;
	}
	public String getEhrAddressFid() {
		return ehrAddressFid;
	}
	public void setEhrAddressFid(String ehrAddressFid) {
		this.ehrAddressFid = ehrAddressFid;
	}
	public int getCzlcNum() {
		return czlcNum;
	}
	public void setCzlcNum(int czlcNum) {
		this.czlcNum = czlcNum;
	}
	public String getJlbstAffixIds() {
		return jlbstAffixIds;
	}
	public void setJlbstAffixIds(String jlbstAffixIds) {
		this.jlbstAffixIds = jlbstAffixIds;
	}
	public String getZzjtAffixIds() {
		return zzjtAffixIds;
	}
	public void setZzjtAffixIds(String zzjtAffixIds) {
		this.zzjtAffixIds = zzjtAffixIds;
	}
	public String getRydjbAffixIds() {
		return rydjbAffixIds;
	}
	public void setRydjbAffixIds(String rydjbAffixIds) {
		this.rydjbAffixIds = rydjbAffixIds;
	}
	public String getVedioPic() {
		return vedioPic;
	}
	public void setVedioPic(String vedioPic) {
		this.vedioPic = vedioPic;
	}
	public String getVedio() {
		return vedio;
	}
	public void setVedio(String vedio) {
		this.vedio = vedio;
	}
	public String getAbdAreaName() {
		return abdAreaName;
	}
	public void setAbdAreaName(String abdAreaName) {
		this.abdAreaName = abdAreaName;
	}
	public String getEhrAlarmRecordId() {
		return ehrAlarmRecordId;
	}
	public void setEhrAlarmRecordId(String ehrAlarmRecordId) {
		this.ehrAlarmRecordId = ehrAlarmRecordId;
	}
}
