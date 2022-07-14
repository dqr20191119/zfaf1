package com.cesgroup.prison.layer.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;


@Table(name = "CDS_ALARM_LAYER_INFO")
public class Layer extends StringIDEntity{

    @Column(name = "LIN_CUS_NUMBER")
    private String linCusNumber;

    @Column(name = "LIN_LAYER_NAME")
    private String linLayerName;

    @Column(name = "LIN_DEVICE_ID")
    private String linDeviceId;

    @Column(name = "LIN_DEVICE_TYPE")
    private String linDeviceType;

    @Column(name = "LIN_REMARK")
    private String linRemark;

    @Column(name = "LIN_CRTE_USER_ID")
    private String linCrteUserId;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "LIN_CRTE_TIME")
    private Date linCrteTime;

    @Column(name = "LIN_UPDT_USER_ID")
    private String linUpdtUserId;

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @Column(name = "LIN_UPDT_TIME")
    private Date linUpdtTime;
    
    @Transient
    private String linPoints;

    @Transient
    private List<LayerPoints> layerPoints;

    public String getLinCusNumber() {
        return linCusNumber;
    }

    public void setLinCusNumber(String linCusNumber) {
        this.linCusNumber = linCusNumber == null ? null : linCusNumber.trim();
    }

    public String getLinLayerName() {
        return linLayerName;
    }

    public void setLinLayerName(String linLayerName) {
        this.linLayerName = linLayerName == null ? null : linLayerName.trim();
    }

    public String getLinDeviceId() {
        return linDeviceId;
    }

    public void setLinDeviceId(String linDeviceId) {
        this.linDeviceId = linDeviceId == null ? null : linDeviceId.trim();
    }

    public String getLinDeviceType() {
        return linDeviceType;
    }

    public void setLinDeviceType(String linDeviceType) {
        this.linDeviceType = linDeviceType == null ? null : linDeviceType.trim();
    }

    public String getLinRemark() {
        return linRemark;
    }

    public void setLinRemark(String linRemark) {
        this.linRemark = linRemark == null ? null : linRemark.trim();
    }

    public String getLinCrteUserId() {
        return linCrteUserId;
    }

    public void setLinCrteUserId(String linCrteUserId) {
        this.linCrteUserId = linCrteUserId == null ? null : linCrteUserId.trim();
    }

    public Date getLinCrteTime() {
        return linCrteTime;
    }

    public void setLinCrteTime(Date linCrteTime) {
        this.linCrteTime = linCrteTime;
    }

    public String getLinUpdtUserId() {
        return linUpdtUserId;
    }

    public void setLinUpdtUserId(String linUpdtUserId) {
        this.linUpdtUserId = linUpdtUserId == null ? null : linUpdtUserId.trim();
    }

    public Date getLinUpdtTime() {
        return linUpdtTime;
    }

    public void setLinUpdtTime(Date linUpdtTime) {
        this.linUpdtTime = linUpdtTime;
    }

	public String getLinPoints() {
		return linPoints;
	}

	public void setLinPoints(String linPoints) {
		this.linPoints = linPoints;
	}

	public List<LayerPoints> getLayerPoints() {
		return layerPoints;
	}

	public void setLayerPoints(List<LayerPoints> layerPoints) {
		this.layerPoints = layerPoints;
	}
    
}