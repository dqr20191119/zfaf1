package com.cesgroup.prison.xtgl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name="CDS_PLANE_LAYER_POINTS")
public class PlaneLayerPoint extends StringIDEntity{
    private String plpCusNumber;

    private String plpPointName;

    private String plpDeviceType;
    
    private String plpDeviceModel;

    private String plpDeviceIdnty;

    private String plpPointX;

    private String plpPointY;

    private String plpLayerIdnty;
    
    private Integer plpPointAngle;

    public String getPlpCusNumber() {
        return plpCusNumber;
    }

    public void setPlpCusNumber(String plpCusNumber) {
        this.plpCusNumber = plpCusNumber == null ? null : plpCusNumber.trim();
    }

    public String getPlpPointName() {
        return plpPointName;
    }

    public void setPlpPointName(String plpPointName) {
        this.plpPointName = plpPointName == null ? null : plpPointName.trim();
    }

    public String getPlpDeviceType() {
        return plpDeviceType;
    }

    public void setPlpDeviceType(String plpDeviceType) {
        this.plpDeviceType = plpDeviceType == null ? null : plpDeviceType.trim();
    }

    public String getPlpDeviceModel() {
		return plpDeviceModel;
	}

	public void setPlpDeviceModel(String plpDeviceModel) {
		this.plpDeviceModel = plpDeviceModel;
	}

	public String getPlpDeviceIdnty() {
        return plpDeviceIdnty;
    }

    public void setPlpDeviceIdnty(String plpDeviceIdnty) {
        this.plpDeviceIdnty = plpDeviceIdnty == null ? null : plpDeviceIdnty.trim();
    }

    public String getPlpPointX() {
        return plpPointX;
    }

    public void setPlpPointX(String plpPointX) {
        this.plpPointX = plpPointX == null ? null : plpPointX.trim();
    }

    public String getPlpPointY() {
        return plpPointY;
    }

    public void setPlpPointY(String plpPointY) {
        this.plpPointY = plpPointY == null ? null : plpPointY.trim();
    }

    public String getPlpLayerIdnty() {
        return plpLayerIdnty;
    }

    public void setPlpLayerIdnty(String plpLayerIdnty) {
        this.plpLayerIdnty = plpLayerIdnty == null ? null : plpLayerIdnty.trim();
    }

	public Integer getPlpPointAngle() {
		return plpPointAngle;
	}

	public void setPlpPointAngle(Integer plpPointAngle) {
		this.plpPointAngle = plpPointAngle;
	}
}