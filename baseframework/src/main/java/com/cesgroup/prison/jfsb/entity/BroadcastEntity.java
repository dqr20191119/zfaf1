
package com.cesgroup.prison.jfsb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：prison   
* @ClassName：BroadcastEntity   
* @Description：   广播
* @author：Tao.xu   
* @date：2017年12月20日 下午3:28:52   
* @version        
*/
@Entity
@Table(name = "DVC_BROADCAST_BASE_DTLS")
public class BroadcastEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = -8126579597285425189L;

	private String bbdCusNumber;

	private String bbdIdnty;

	private String bbdName;

	private String bbdBrand;

	private String bbdModel;

	private String bbdIpAddrs;

	private String bbdPort;

	private String bbdAddrs;

	private String bbdDprtmntId;

	private String bbdDprtmnt;

	private String bbdAreaId;

	private String bbdArea;

	private String bbdSttsIndc;

	private String bbdRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date bbdCrteTime;

	private String bbdCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date bbdUpdtTime;

	private String bbdUpdtUserId;
	/**
	 * 关联的摄像头编号(多个摄像机之间以英文逗号分隔)
	 */
	private String bbdCameraId;
	/**
	 * 关联的摄像头名称(多个摄像机之间以英文逗号分隔)
	 */
	private String bbdCameraName;
	/**
	 * 广播设备最近的一次播放记录ID
	 */
	private String bbdLatestRecordId;

	public String getBbdCusNumber() {
		return bbdCusNumber;
	}

	public void setBbdCusNumber(String bbdCusNumber) {
		this.bbdCusNumber = bbdCusNumber;
	}

	public String getBbdIdnty() {
		return bbdIdnty;
	}

	public void setBbdIdnty(String bbdIdnty) {
		this.bbdIdnty = bbdIdnty;
	}

	public String getBbdName() {
		return bbdName;
	}

	public void setBbdName(String bbdName) {
		this.bbdName = bbdName;
	}

	public String getBbdBrand() {
		return bbdBrand;
	}

	public void setBbdBrand(String bbdBrand) {
		this.bbdBrand = bbdBrand;
	}

	public String getBbdModel() {
		return bbdModel;
	}

	public void setBbdModel(String bbdModel) {
		this.bbdModel = bbdModel;
	}

	public String getBbdIpAddrs() {
		return bbdIpAddrs;
	}

	public void setBbdIpAddrs(String bbdIpAddrs) {
		this.bbdIpAddrs = bbdIpAddrs;
	}

	public String getBbdPort() {
		return bbdPort;
	}

	public void setBbdPort(String bbdPort) {
		this.bbdPort = bbdPort;
	}

	public String getBbdAddrs() {
		return bbdAddrs;
	}

	public void setBbdAddrs(String bbdAddrs) {
		this.bbdAddrs = bbdAddrs;
	}

	public String getBbdDprtmntId() {
		return bbdDprtmntId;
	}

	public void setBbdDprtmntId(String bbdDprtmntId) {
		this.bbdDprtmntId = bbdDprtmntId;
	}

	public String getBbdDprtmnt() {
		return bbdDprtmnt;
	}

	public void setBbdDprtmnt(String bbdDprtmnt) {
		this.bbdDprtmnt = bbdDprtmnt;
	}

	public String getBbdAreaId() {
		return bbdAreaId;
	}

	public void setBbdAreaId(String bbdAreaId) {
		this.bbdAreaId = bbdAreaId;
	}

	public String getBbdArea() {
		return bbdArea;
	}

	public void setBbdArea(String bbdArea) {
		this.bbdArea = bbdArea;
	}

	public String getBbdSttsIndc() {
		return bbdSttsIndc;
	}

	public void setBbdSttsIndc(String bbdSttsIndc) {
		this.bbdSttsIndc = bbdSttsIndc;
	}

	public String getBbdRemark() {
		return bbdRemark;
	}

	public void setBbdRemark(String bbdRemark) {
		this.bbdRemark = bbdRemark;
	}

	public Date getBbdCrteTime() {
		return bbdCrteTime;
	}

	public void setBbdCrteTime(Date bbdCrteTime) {
		this.bbdCrteTime = bbdCrteTime;
	}

	public String getBbdCrteUserId() {
		return bbdCrteUserId;
	}

	public void setBbdCrteUserId(String bbdCrteUserId) {
		this.bbdCrteUserId = bbdCrteUserId;
	}

	public Date getBbdUpdtTime() {
		return bbdUpdtTime;
	}

	public void setBbdUpdtTime(Date bbdUpdtTime) {
		this.bbdUpdtTime = bbdUpdtTime;
	}

	public String getBbdUpdtUserId() {
		return bbdUpdtUserId;
	}

	public void setBbdUpdtUserId(String bbdUpdtUserId) {
		this.bbdUpdtUserId = bbdUpdtUserId;
	}

	public String getBbdCameraId() {
		return bbdCameraId;
	}

	public void setBbdCameraId(String bbdCameraId) {
		this.bbdCameraId = bbdCameraId;
	}

	public String getBbdCameraName() {
		return bbdCameraName;
	}

	public void setBbdCameraName(String bbdCameraName) {
		this.bbdCameraName = bbdCameraName;
	}

	public String getBbdLatestRecordId() {
		return bbdLatestRecordId;
	}

	public void setBbdLatestRecordId(String bbdLatestRecordId) {
		this.bbdLatestRecordId = bbdLatestRecordId;
	}
}
