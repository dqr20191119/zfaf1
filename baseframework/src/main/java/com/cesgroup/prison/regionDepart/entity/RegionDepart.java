package com.cesgroup.prison.regionDepart.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
/**
 * 
 * @author linhe 2018-3-9
 * 区域部门关联实体类
 */
@Entity
@Table(name = "CDS_AREA_DEPARTMENT_RELATION")
public class RegionDepart extends StringIDEntity {
    
	private static final long serialVersionUID = 6985204464210459859L;

	private String adrCusNumber;

    private String adrAreaId;

    private String adrAreaName;

    private BigDecimal adrDprtmntId;

    private String adrDprtmntName;

    private Date adrCrteTime;

    private String adrCrteUserId;

    private Date adrUpdtTime;

    private String adrUpdtUserId;

    public String getAdrCusNumber() {
        return adrCusNumber;
    }

    public void setAdrCusNumber(String adrCusNumber) {
        this.adrCusNumber = adrCusNumber;
    }

    public String getAdrAreaId() {
        return adrAreaId;
    }

    public void setAdrAreaId(String adrAreaId) {
        this.adrAreaId = adrAreaId;
    }

    public String getAdrAreaName() {
        return adrAreaName;
    }

    public void setAdrAreaName(String adrAreaName) {
        this.adrAreaName = adrAreaName == null ? null : adrAreaName.trim();
    }

    public BigDecimal getAdrDprtmntId() {
        return adrDprtmntId;
    }

    public void setAdrDprtmntId(BigDecimal adrDprtmntId) {
        this.adrDprtmntId = adrDprtmntId;
    }

    public String getAdrDprtmntName() {
        return adrDprtmntName;
    }

    public void setAdrDprtmntName(String adrDprtmntName) {
        this.adrDprtmntName = adrDprtmntName == null ? null : adrDprtmntName.trim();
    }

    public Date getAdrCrteTime() {
        return adrCrteTime;
    }

    public void setAdrCrteTime(Date adrCrteTime) {
        this.adrCrteTime = adrCrteTime;
    }

    public String getAdrCrteUserId() {
        return adrCrteUserId;
    }

    public void setAdrCrteUserId(String adrCrteUserId) {
        this.adrCrteUserId = adrCrteUserId == null ? null : adrCrteUserId.trim();
    }

    public Date getAdrUpdtTime() {
        return adrUpdtTime;
    }

    public void setAdrUpdtTime(Date adrUpdtTime) {
        this.adrUpdtTime = adrUpdtTime;
    }

    public String getAdrUpdtUserId() {
        return adrUpdtUserId;
    }

    public void setAdrUpdtUserId(String adrUpdtUserId) {
        this.adrUpdtUserId = adrUpdtUserId == null ? null : adrUpdtUserId.trim();
    }
}