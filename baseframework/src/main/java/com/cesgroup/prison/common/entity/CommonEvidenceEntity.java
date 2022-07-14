package com.cesgroup.prison.common.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="cds_evidence_info")
public class CommonEvidenceEntity extends StringIDEntity {

	private String einCusNumber;					// 监狱id
	private String einTitle;						// 标题
	private String einContentTypeIndc;				// 证据保存的事件类型：1-监督检查(默认1)；2-报警处置；3-应急处突
	private String einContentDesc;					// 内容说明
	private String einFileTypeIndc;					// 文件类型1-截图；2-录像
	private String einCameraId;						// 摄像机id
	private String einCameraName;					// 摄像机名称
	private String einAddrs;						// 发生地点
	private String einFileName;						// 文件名称
	private String einFilePath;						// 文件路径
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date einCrteTime;						// 创建时间
	private String einCrteUserId;					// 创建人id
	private String einSttsIndc;						// 状态0-否(未使用)，1-是(已使用)
	private String einProblem;						// (暂未使用)
	private String einProblemType;					// (暂未使用)
	private String einProblemSubType;				// (暂未使用)
	private String einFtpPath;						// ftp文件地址
	private String einFtpPrefix;					// ftp前缀
	
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Transient
	private Date einCrteStartTime;					// 创建开始时间
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Transient
	private Date einCrteEndTime;					// 创建结束时间
	
	
	public String getEinCusNumber() {
		return einCusNumber;
	}
	public void setEinCusNumber(String einCusNumber) {
		this.einCusNumber = einCusNumber;
	}
	public String getEinTitle() {
		return einTitle;
	}
	public void setEinTitle(String einTitle) {
		this.einTitle = einTitle;
	}
	public String getEinContentTypeIndc() {
		return einContentTypeIndc;
	}
	public void setEinContentTypeIndc(String einContentTypeIndc) {
		this.einContentTypeIndc = einContentTypeIndc;
	}
	public String getEinContentDesc() {
		return einContentDesc;
	}
	public void setEinContentDesc(String einContentDesc) {
		this.einContentDesc = einContentDesc;
	}
	public String getEinFileTypeIndc() {
		return einFileTypeIndc;
	}
	public void setEinFileTypeIndc(String einFileTypeIndc) {
		this.einFileTypeIndc = einFileTypeIndc;
	}
	public String getEinCameraId() {
		return einCameraId;
	}
	public void setEinCameraId(String einCameraId) {
		this.einCameraId = einCameraId;
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
	public String getEinFileName() {
		return einFileName;
	}
	public void setEinFileName(String einFileName) {
		this.einFileName = einFileName;
	}
	public String getEinFilePath() {
		return einFilePath;
	}
	public void setEinFilePath(String einFilePath) {
		this.einFilePath = einFilePath;
	}
	public Date getEinCrteTime() {
		return einCrteTime;
	}
	public void setEinCrteTime(Date einCrteTime) {
		this.einCrteTime = einCrteTime;
	}
	public String getEinCrteUserId() {
		return einCrteUserId;
	}
	public void setEinCrteUserId(String einCrteUserId) {
		this.einCrteUserId = einCrteUserId;
	}
	public String getEinSttsIndc() {
		return einSttsIndc;
	}
	public void setEinSttsIndc(String einSttsIndc) {
		this.einSttsIndc = einSttsIndc;
	}
	public String getEinProblem() {
		return einProblem;
	}
	public void setEinProblem(String einProblem) {
		this.einProblem = einProblem;
	}
	public String getEinProblemType() {
		return einProblemType;
	}
	public void setEinProblemType(String einProblemType) {
		this.einProblemType = einProblemType;
	}
	public String getEinProblemSubType() {
		return einProblemSubType;
	}
	public void setEinProblemSubType(String einProblemSubType) {
		this.einProblemSubType = einProblemSubType;
	}
	public String getEinFtpPath() {
		return einFtpPath;
	}
	public void setEinFtpPath(String einFtpPath) {
		this.einFtpPath = einFtpPath;
	}
	public String getEinFtpPrefix() {
		return einFtpPrefix;
	}
	public void setEinFtpPrefix(String einFtpPrefix) {
		this.einFtpPrefix = einFtpPrefix;
	}
	public Date getEinCrteStartTime() {
		return einCrteStartTime;
	}
	public void setEinCrteStartTime(Date einCrteStartTime) {
		this.einCrteStartTime = einCrteStartTime;
	}
	public Date getEinCrteEndTime() {
		return einCrteEndTime;
	}
	public void setEinCrteEndTime(Date einCrteEndTime) {
		this.einCrteEndTime = einCrteEndTime;
	}
}
