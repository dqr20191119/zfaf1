package com.cesgroup.prison.screen.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：baseframework   
* @ClassName：ScreenPlanAreaRltnEntity   
* @Description：   大屏区域
* @author：Tao   
* @date：2018年5月16日 上午10:52:37   
* @version        
*/
@Entity
@Table(name = "CDS_SCREEN_PLAN_AREA_RLTN")
public class ScreenPlanAreaRltnEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = 4073909406616163158L;

	private String sprCusNumber;

	private String sprScreenPlanId;

	private String sprScreenAreaName;

	private String sprIsRound;

	private String sprTimeLag;

	private String sprCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sprCrteTime;

	private String sprUpdtUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date sprUpdtTime;

	/**
	* @Fields windows :  区域关联的窗口
	*/
	@Transient
	private List<ScreenAreaWindowEntity> windows;

	public String getSprCusNumber() {
		return sprCusNumber;
	}

	public void setSprCusNumber(String sprCusNumber) {
		this.sprCusNumber = sprCusNumber;
	}

	public String getSprScreenPlanId() {
		return sprScreenPlanId;
	}

	public void setSprScreenPlanId(String sprScreenPlanId) {
		this.sprScreenPlanId = sprScreenPlanId;
	}

	public String getSprScreenAreaName() {
		return sprScreenAreaName;
	}

	public void setSprScreenAreaName(String sprScreenAreaName) {
		this.sprScreenAreaName = sprScreenAreaName;
	}

	public String getSprIsRound() {
		return sprIsRound;
	}

	public void setSprIsRound(String sprIsRound) {
		this.sprIsRound = sprIsRound;
	}

	public String getSprTimeLag() {
		return sprTimeLag;
	}

	public void setSprTimeLag(String sprTimeLag) {
		this.sprTimeLag = sprTimeLag;
	}

	public String getSprCrteUserId() {
		return sprCrteUserId;
	}

	public void setSprCrteUserId(String sprCrteUserId) {
		this.sprCrteUserId = sprCrteUserId;
	}

	public Date getSprCrteTime() {
		return sprCrteTime;
	}

	public void setSprCrteTime(Date sprCrteTime) {
		this.sprCrteTime = sprCrteTime;
	}

	public String getSprUpdtUserId() {
		return sprUpdtUserId;
	}

	public void setSprUpdtUserId(String sprUpdtUserId) {
		this.sprUpdtUserId = sprUpdtUserId;
	}

	public Date getSprUpdtTime() {
		return sprUpdtTime;
	}

	public void setSprUpdtTime(Date sprUpdtTime) {
		this.sprUpdtTime = sprUpdtTime;
	}

	public List<ScreenAreaWindowEntity> getWindows() {
		return windows;
	}

	public void setWindows(List<ScreenAreaWindowEntity> windows) {
		this.windows = windows;
	}

}
