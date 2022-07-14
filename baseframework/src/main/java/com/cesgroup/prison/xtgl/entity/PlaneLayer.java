package com.cesgroup.prison.xtgl.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;

@Entity
@Table(name="CDS_PLANE_LAYER_INFO")
public class PlaneLayer extends StringIDEntity{

    private String pliCusNumber;

    private String pliPlaneName;

    private String pliAreaId;

    private String pliWidth;

    private String pliHeight;

    public String getPliCusNumber() {
        return pliCusNumber;
    }

    public void setPliCusNumber(String pliCusNumber) {
        this.pliCusNumber = pliCusNumber == null ? null : pliCusNumber.trim();
    }

    public String getPliPlaneName() {
        return pliPlaneName;
    }

    public void setPliPlaneName(String pliPlaneName) {
        this.pliPlaneName = pliPlaneName == null ? null : pliPlaneName.trim();
    }

    public String getPliAreaId() {
        return pliAreaId;
    }

    public void setPliAreaId(String pliAreaId) {
        this.pliAreaId = pliAreaId == null ? null : pliAreaId.trim();
    }

    public String getPliWidth() {
        return pliWidth;
    }

    public void setPliWidth(String pliWidth) {
        this.pliWidth = pliWidth == null ? null : pliWidth.trim();
    }

    public String getPliHeight() {
        return pliHeight;
    }

    public void setPliHeight(String pliHeight) {
        this.pliHeight = pliHeight == null ? null : pliHeight.trim();
    }

}