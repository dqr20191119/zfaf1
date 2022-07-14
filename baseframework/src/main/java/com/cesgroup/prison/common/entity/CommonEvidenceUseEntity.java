package com.cesgroup.prison.common.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="cds_evidence_use")
public class CommonEvidenceUseEntity extends StringIDEntity {

	private String ceuCusNumber;			// 监狱id
	private String ceuEvidenceId;			// 证据id
	private String ceuYwType;				// 业务类型1-监督检查(默认1)；2-报警处置；3-应急处突
	private String ceuYwId;					// 业务id
	private Date ceuCrteTime;				// 创建时间
	private String ceuCrteUserId;			// 创建人id
	
	@Transient
	private List<CommonEvidenceUseEntity> commonEvidenceUseEntityList = new ArrayList<CommonEvidenceUseEntity>();
	@Transient
	private String einTitle;						// 标题
	@Transient
	private String einFileTypeIndc;					// 文件类型1-截图；2-录像
	@Transient
	private String einCameraName;					// 摄像机名称
	@Transient
	private String einAddrs;						// 发生地点
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Transient
	private Date einCrteTime;						// 创建时间
	@Transient
	private String einFileName;						// 文件名称
	@Transient
	private String einFtpPath;						// ftp文件地址
	@Transient
	private String einFilePath;						// 文件路径
	
	
	public String getCeuCusNumber() {
		return ceuCusNumber;
	}
	public void setCeuCusNumber(String ceuCusNumber) {
		this.ceuCusNumber = ceuCusNumber;
	}
	public String getCeuEvidenceId() {
		return ceuEvidenceId;
	}
	public void setCeuEvidenceId(String ceuEvidenceId) {
		this.ceuEvidenceId = ceuEvidenceId;
	}
	public String getCeuYwType() {
		return ceuYwType;
	}
	public void setCeuYwType(String ceuYwType) {
		this.ceuYwType = ceuYwType;
	}
	public String getCeuYwId() {
		return ceuYwId;
	}
	public void setCeuYwId(String ceuYwId) {
		this.ceuYwId = ceuYwId;
	}
	public Date getCeuCrteTime() {
		return ceuCrteTime;
	}
	public void setCeuCrteTime(Date ceuCrteTime) {
		this.ceuCrteTime = ceuCrteTime;
	}
	public String getCeuCrteUserId() {
		return ceuCrteUserId;
	}
	public void setCeuCrteUserId(String ceuCrteUserId) {
		this.ceuCrteUserId = ceuCrteUserId;
	}
	public List<CommonEvidenceUseEntity> getCommonEvidenceUseEntityList() {
		return commonEvidenceUseEntityList;
	}
	public void setCommonEvidenceUseEntityList(List<CommonEvidenceUseEntity> commonEvidenceUseEntityList) {
		this.commonEvidenceUseEntityList = commonEvidenceUseEntityList;
	}
	public String getEinTitle() {
		return einTitle;
	}
	public void setEinTitle(String einTitle) {
		this.einTitle = einTitle;
	}
	public String getEinFileTypeIndc() {
		return einFileTypeIndc;
	}
	public void setEinFileTypeIndc(String einFileTypeIndc) {
		this.einFileTypeIndc = einFileTypeIndc;
	}
	public String getEinCameraName() {
		return einCameraName;
	}
	public void setEinCameraName(String einCameraName) {
		this.einCameraName = einCameraName;
	}
	public String getEinAddrs() {
		return einAddrs;
	}
	public void setEinAddrs(String einAddrs) {
		this.einAddrs = einAddrs;
	}
	public Date getEinCrteTime() {
		return einCrteTime;
	}
	public void setEinCrteTime(Date einCrteTime) {
		this.einCrteTime = einCrteTime;
	}
	public String getEinFileName() {
		return einFileName;
	}
	public void setEinFileName(String einFileName) {
		this.einFileName = einFileName;
	}
	public String getEinFtpPath() {
		return einFtpPath;
	}
	public void setEinFtpPath(String einFtpPath) {
		this.einFtpPath = einFtpPath;
	}
	public String getEinFilePath() {
		return einFilePath;
	}
	public void setEinFilePath(String einFilePath) {
		this.einFilePath = einFilePath;
	}
}
