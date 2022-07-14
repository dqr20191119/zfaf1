package com.cesgroup.prison.layer.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Table(name = "CDS_ALARM_LAYER_POINTS")
public class LayerPoints extends StringIDEntity{

    @Column(name = "LPO_LAYER_ID")
    private String lpoLayerId;

    @Column(name = "LPO_POINT_X")
    private String lpoPointX;

    @Column(name = "LPO_POINT_Y")
    private String lpoPointY;

    @Column(name = "LPO_POINT_Z")
    private String lpoPointZ;

    @Column(name = "LPO_ORDER")
    private Integer lpoOrder;

    @Column(name = "LPO_CRTE_USER_ID")
    private String lpoCrteUserId;

    @Column(name = "LPO_CRTE_TIME")
    private Date lpoCrteTime;

    @Column(name = "LPO_UPDT_USER_ID")
    private String lpoUpdtUserId;

    @Column(name = "LPO_UPDT_TIME")
    private Date lpoUpdtTime;

    public String getLpoLayerId() {
		return lpoLayerId;
	}

	public void setLpoLayerId(String lpoLayerId) {
		this.lpoLayerId = lpoLayerId;
	}

	public String getLpoPointX() {
        return lpoPointX;
    }

    public void setLpoPointX(String lpoPointX) {
        this.lpoPointX = lpoPointX == null ? null : lpoPointX.trim();
    }

    public String getLpoPointY() {
        return lpoPointY;
    }

    public void setLpoPointY(String lpoPointY) {
        this.lpoPointY = lpoPointY == null ? null : lpoPointY.trim();
    }

    public String getLpoPointZ() {
        return lpoPointZ;
    }

    public void setLpoPointZ(String lpoPointZ) {
        this.lpoPointZ = lpoPointZ == null ? null : lpoPointZ.trim();
    }
    
    public Integer getLpoOrder() {
		return lpoOrder;
	}

	public void setLpoOrder(Integer lpoOrder) {
		this.lpoOrder = lpoOrder;
	}

	public String getLpoCrteUserId() {
        return lpoCrteUserId;
    }

    public void setLpoCrteUserId(String lpoCrteUserId) {
        this.lpoCrteUserId = lpoCrteUserId == null ? null : lpoCrteUserId.trim();
    }

    public Date getLpoCrteTime() {
        return lpoCrteTime;
    }

    public void setLpoCrteTime(Date lpoCrteTime) {
        this.lpoCrteTime = lpoCrteTime;
    }

    public String getLpoUpdtUserId() {
        return lpoUpdtUserId;
    }

    public void setLpoUpdtUserId(String lpoUpdtUserId) {
        this.lpoUpdtUserId = lpoUpdtUserId == null ? null : lpoUpdtUserId.trim();
    }

    public Date getLpoUpdtTime() {
        return lpoUpdtTime;
    }

    public void setLpoUpdtTime(Date lpoUpdtTime) {
        this.lpoUpdtTime = lpoUpdtTime;
    }
}