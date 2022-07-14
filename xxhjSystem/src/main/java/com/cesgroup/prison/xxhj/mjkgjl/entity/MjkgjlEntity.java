package com.cesgroup.prison.xxhj.mjkgjl.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**      
*门禁开关管理
*/
@Entity
@Table(name = "V_POLICE_INOUT_RECORD")
public class MjkgjlEntity extends  StringIDEntity{
    private String pirCusNumber;

    private Date pirBrushDate;

    private String pirPoliceIdnty;

    private String pirPoliceName;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pirEnterTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date pirLeaveTime;

    private String pirDprtmntId;

    private String pirDprtmntName;

    private String pirDoorCardIdnty;

    private String pirPeopleTypeIndc;

    private String pirRemark;

    private Date pirCrteTime;

    private String pirCrteUserId;

    private Date pirUpdtTime;

    private String pirUpdtUserId;
    
    private String pirDoorIdnty;
    
    private String pirDoorName;

    public String getPirDoorName() {
		return pirDoorName;
	}

	public void setPirDoorName(String pirDoorName) {
		this.pirDoorName = pirDoorName;
	}

	public String getPirDoorIdnty() {
		return pirDoorIdnty;
	}

	public void setPirDoorIdnty(String pirDoorIdnty) {
		this.pirDoorIdnty = pirDoorIdnty;
	}

	public String getPirCusNumber() {
        return pirCusNumber;
    }

    public void setPirCusNumber(String pirCusNumber) {
        this.pirCusNumber = pirCusNumber == null ? null : pirCusNumber.trim();
    }

    public Date getPirBrushDate() {
        return pirBrushDate;
    }

    public void setPirBrushDate(Date pirBrushDate) {
        this.pirBrushDate = pirBrushDate;
    }

    public String getPirPoliceIdnty() {
        return pirPoliceIdnty;
    }

    public void setPirPoliceIdnty(String pirPoliceIdnty) {
        this.pirPoliceIdnty = pirPoliceIdnty == null ? null : pirPoliceIdnty.trim();
    }

    public String getPirPoliceName() {
        return pirPoliceName;
    }

    public void setPirPoliceName(String pirPoliceName) {
        this.pirPoliceName = pirPoliceName == null ? null : pirPoliceName.trim();
    }

    public Date getPirEnterTime() {
        return pirEnterTime;
    }

    public void setPirEnterTime(Date pirEnterTime) {
        this.pirEnterTime = pirEnterTime;
    }

    public Date getPirLeaveTime() {
        return pirLeaveTime;
    }

    public void setPirLeaveTime(Date pirLeaveTime) {
        this.pirLeaveTime = pirLeaveTime;
    }

    public String getPirDprtmntId() {
        return pirDprtmntId;
    }

    public void setPirDprtmntId(String pirDprtmntId) {
        this.pirDprtmntId = pirDprtmntId == null ? null : pirDprtmntId.trim();
    }

    public String getPirDprtmntName() {
        return pirDprtmntName;
    }

    public void setPirDprtmntName(String pirDprtmntName) {
        this.pirDprtmntName = pirDprtmntName == null ? null : pirDprtmntName.trim();
    }

    public String getPirDoorCardIdnty() {
        return pirDoorCardIdnty;
    }

    public void setPirDoorCardIdnty(String pirDoorCardIdnty) {
        this.pirDoorCardIdnty = pirDoorCardIdnty == null ? null : pirDoorCardIdnty.trim();
    }

    public String getPirPeopleTypeIndc() {
        return pirPeopleTypeIndc;
    }

    public void setPirPeopleTypeIndc(String pirPeopleTypeIndc) {
        this.pirPeopleTypeIndc = pirPeopleTypeIndc == null ? null : pirPeopleTypeIndc.trim();
    }

    public String getPirRemark() {
        return pirRemark;
    }

    public void setPirRemark(String pirRemark) {
        this.pirRemark = pirRemark == null ? null : pirRemark.trim();
    }

    public Date getPirCrteTime() {
        return pirCrteTime;
    }

    public void setPirCrteTime(Date pirCrteTime) {
        this.pirCrteTime = pirCrteTime;
    }

    public String getPirCrteUserId() {
        return pirCrteUserId;
    }

    public void setPirCrteUserId(String pirCrteUserId) {
        this.pirCrteUserId = pirCrteUserId == null ? null : pirCrteUserId.trim();
    }

    public Date getPirUpdtTime() {
        return pirUpdtTime;
    }

    public void setPirUpdtTime(Date pirUpdtTime) {
        this.pirUpdtTime = pirUpdtTime;
    }

    public String getPirUpdtUserId() {
        return pirUpdtUserId;
    }

    public void setPirUpdtUserId(String pirUpdtUserId) {
        this.pirUpdtUserId = pirUpdtUserId == null ? null : pirUpdtUserId.trim();
    }
}