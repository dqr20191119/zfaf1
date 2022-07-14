package com.cesgroup.prison.route.entity;

import java.util.Date;

import com.cesgroup.framework.base.entity.StringIDEntity;

public class CdsRoamPointRltn extends StringIDEntity{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;

    private String rprRoamIdnty;

    private String rprPointName;

    private Date rprCrteTime;

    private String rprCrteUserId;

    private Date rprUpdtTime;

    private String rprUpdtUserId;

    private String rprCusNumber;

    private String rprEquipmentId;

    private Double rprRouteSpeed;

    private Double rprPositionX;

    private Double rprPositionY;

    private Double rprPositionZ;

    private Double rprAngleHead;

    private Double rprAngleTilt;

    private Integer rprOrder;
   //设备名称
    private String rprEquipmentName;
    //路线名称
    private String rprRoamName;
    
    
    public String getRprEquipmentName() {
		return rprEquipmentName;
	}

	public void setRprEquipmentName(String rprEquipmentName) {
		this.rprEquipmentName = rprEquipmentName;
	}

	public String getRprRoamName() {
		return rprRoamName;
	}

	public void setRprRoamName(String rprRoamName) {
		this.rprRoamName = rprRoamName;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRprRoamIdnty() {
        return rprRoamIdnty;
    }

    public void setRprRoamIdnty(String rprRoamIdnty) {
        this.rprRoamIdnty = rprRoamIdnty == null ? null : rprRoamIdnty.trim();
    }

    public String getRprPointName() {
        return rprPointName;
    }

    public void setRprPointName(String rprPointName) {
        this.rprPointName = rprPointName == null ? null : rprPointName.trim();
    }

    public Date getRprCrteTime() {
        return rprCrteTime;
    }

    public void setRprCrteTime(Date rprCrteTime) {
        this.rprCrteTime = rprCrteTime;
    }

    public String getRprCrteUserId() {
        return rprCrteUserId;
    }

    public void setRprCrteUserId(String rprCrteUserId) {
        this.rprCrteUserId = rprCrteUserId == null ? null : rprCrteUserId.trim();
    }

    public Date getRprUpdtTime() {
        return rprUpdtTime;
    }

    public void setRprUpdtTime(Date rprUpdtTime) {
        this.rprUpdtTime = rprUpdtTime;
    }

    public String getRprUpdtUserId() {
        return rprUpdtUserId;
    }

    public void setRprUpdtUserId(String rprUpdtUserId) {
        this.rprUpdtUserId = rprUpdtUserId == null ? null : rprUpdtUserId.trim();
    }

    public String getRprCusNumber() {
		return rprCusNumber;
	}

	public void setRprCusNumber(String rprCusNumber) {
		this.rprCusNumber = rprCusNumber;
	}

	public String getRprEquipmentId() {
        return rprEquipmentId;
    }

    public void setRprEquipmentId(String rprEquipmentId) {
        this.rprEquipmentId = rprEquipmentId == null ? null : rprEquipmentId.trim();
    }

    public Double getRprRouteSpeed() {
        return rprRouteSpeed;
    }

    public void setRprRouteSpeed(Double rprRouteSpeed) {
        this.rprRouteSpeed = rprRouteSpeed;
    }

    public Double getRprPositionX() {
        return rprPositionX;
    }

    public void setRprPositionX(Double rprPositionX) {
        this.rprPositionX = rprPositionX;
    }

    public Double getRprPositionY() {
        return rprPositionY;
    }

    public void setRprPositionY(Double rprPositionY) {
        this.rprPositionY = rprPositionY;
    }

    public Double getRprPositionZ() {
        return rprPositionZ;
    }

    public void setRprPositionZ(Double rprPositionZ) {
        this.rprPositionZ = rprPositionZ;
    }

    public Double getRprAngleHead() {
        return rprAngleHead;
    }

    public void setRprAngleHead(Double rprAngleHead) {
        this.rprAngleHead = rprAngleHead;
    }

    public Double getRprAngleTilt() {
        return rprAngleTilt;
    }

    public void setRprAngleTilt(Double rprAngleTilt) {
        this.rprAngleTilt = rprAngleTilt;
    }

    public Integer getRprOrder() {
        return rprOrder;
    }

    public void setRprOrder(Integer rprOrder) {
        this.rprOrder = rprOrder;
    }
}