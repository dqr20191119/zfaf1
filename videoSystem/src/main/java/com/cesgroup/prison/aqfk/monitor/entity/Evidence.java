package com.cesgroup.prison.aqfk.monitor.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.format.annotation.DateTimeFormat;
import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;


/**   
*    
* 项目名称：prison   
* 类名称：Evidence   
* 类描述：   
* 创建人：zhengke   
* 创建时间：2017-11-24 09:11   
* @version        
*/
@Entity
@Table(name="CDS_EVIDENCE_INFO")
public class Evidence extends StringIDEntity{

	private static final long serialVersionUID = 1L;

	private String einCusNumber;

	private String einTitle;
	//证据保存的事件类型：1.监督检查(默认1)、2.报警处置
	private String einContentTypeIndc;

	private String einContentDesc;

	private String einFileTypeIndc;

	private String einCameraId;

	private String einCameraName;

	private String einAddrs;

	private String einFileName;

	private String einFilePath;

	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date einCrteTime;

	private String einCrteUserId;

	private String einSttsIndc;

	private String einProblem;

	private String einProblemType;

	private String einProblemSubType;

	private String einFtpPath;
	
	private String einFtpPrefix;
	public String getEinFtpPath() {
		return einFtpPath;
	}

	public void setEinFtpPath(String einFtpPath) {
		this.einFtpPath = einFtpPath;
	}

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

	public String getEinFtpPrefix() {
		return einFtpPrefix;
	}

	public void setEinFtpPrefix(String einFtpPrefix) {
		this.einFtpPrefix = einFtpPrefix;
	}
	
}
