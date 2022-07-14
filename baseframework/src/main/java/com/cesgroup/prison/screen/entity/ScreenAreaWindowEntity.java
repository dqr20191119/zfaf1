package com.cesgroup.prison.screen.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：baseframework   
* @ClassName：ScreenAreaWindowEntity   
* @Description：  大屏窗口 
* @author：Tao   
* @date：2018年5月16日 上午10:54:49   
* @version        
*/
@Entity
@Table(name = "CDS_SCREEN_AREA_WINDOW_RLTN")
public class ScreenAreaWindowEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = -2589559341829910565L;

	private String swrCusNumber;

	private String swrScreenPlanId;

	private String swrScreenAreaId;

	private String swrScreenWindowNum;

	private String swrCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date swrCrteTime;

	private String swrUpdtUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date swrUpdtTime;

	private String swrSeqNum;

	public String getSwrCusNumber() {
		return swrCusNumber;
	}

	public void setSwrCusNumber(String swrCusNumber) {
		this.swrCusNumber = swrCusNumber;
	}

	public String getSwrSeqNum() {
		return swrSeqNum;
	}

	public void setSwrSeqNum(String swrSeqNum) {
		this.swrSeqNum = swrSeqNum;
	}

	public String getSwrScreenPlanId() {
		return swrScreenPlanId;
	}

	public void setSwrScreenPlanId(String swrScreenPlanId) {
		this.swrScreenPlanId = swrScreenPlanId;
	}

	public String getSwrScreenAreaId() {
		return swrScreenAreaId;
	}

	public void setSwrScreenAreaId(String swrScreenAreaId) {
		this.swrScreenAreaId = swrScreenAreaId;
	}

	public String getSwrScreenWindowNum() {
		return swrScreenWindowNum;
	}

	public void setSwrScreenWindowNum(String swrScreenWindowNum) {
		this.swrScreenWindowNum = swrScreenWindowNum;
	}

	public String getSwrCrteUserId() {
		return swrCrteUserId;
	}

	public void setSwrCrteUserId(String swrCrteUserId) {
		this.swrCrteUserId = swrCrteUserId;
	}

	public Date getSwrCrteTime() {
		return swrCrteTime;
	}

	public void setSwrCrteTime(Date swrCrteTime) {
		this.swrCrteTime = swrCrteTime;
	}

	public String getSwrUpdtUserId() {
		return swrUpdtUserId;
	}

	public void setSwrUpdtUserId(String swrUpdtUserId) {
		this.swrUpdtUserId = swrUpdtUserId;
	}

	public Date getSwrUpdtTime() {
		return swrUpdtTime;
	}

	public void setSwrUpdtTime(Date swrUpdtTime) {
		this.swrUpdtTime = swrUpdtTime;
	}

}
