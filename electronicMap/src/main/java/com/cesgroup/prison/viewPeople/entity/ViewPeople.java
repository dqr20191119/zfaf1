package com.cesgroup.prison.viewPeople.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;


@Entity
@Table(name = "CDS_VIEW_PEOPLE_RELATION")
public class ViewPeople extends StringIDEntity {

    private String id;
    private String vprCusNumber;
    private String vprAreaId;
    private String vprAreaName;
    private String vprPoliceId;
    private String vprPoliceName;
    private Date vprCrteTime;
    private String vprCrteUserId;
    private Date vprUpdtTime;
    private String vprUpdtUserId;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public String getVprCusNumber() {
        return vprCusNumber;
    }

    public void setVprCusNumber(String vprCusNumber) {
        this.vprCusNumber = vprCusNumber;
    }

    public String getVprAreaId() {
        return vprAreaId;
    }

    public void setVprAreaId(String vprAreaId) {
        this.vprAreaId = vprAreaId;
    }

    public String getVprAreaName() {
        return vprAreaName;
    }

    public void setVprAreaName(String vprAreaName) {
        this.vprAreaName = vprAreaName;
    }

    public String getVprPoliceId() {
        return vprPoliceId;
    }

    public void setVprPoliceId(String vprPoliceId) {
        this.vprPoliceId = vprPoliceId;
    }

    public String getVprPoliceName() {
        return vprPoliceName;
    }

    public void setVprPoliceName(String vprPoliceName) {
        this.vprPoliceName = vprPoliceName;
    }

    public Date getVprCrteTime() {
        return vprCrteTime;
    }

    public void setVprCrteTime(Date vprCrteTime) {
        this.vprCrteTime = vprCrteTime;
    }

    public String getVprCrteUserId() {
        return vprCrteUserId;
    }

    public void setVprCrteUserId(String vprCrteUserId) {
        this.vprCrteUserId = vprCrteUserId;
    }

    public Date getVprUpdtTime() {
        return vprUpdtTime;
    }

    public void setVprUpdtTime(Date vprUpdtTime) {
        this.vprUpdtTime = vprUpdtTime;
    }

    public String getVprUpdtUserId() {
        return vprUpdtUserId;
    }

    public void setVprUpdtUserId(String vprUpdtUserId) {
        this.vprUpdtUserId = vprUpdtUserId;
    }
}
