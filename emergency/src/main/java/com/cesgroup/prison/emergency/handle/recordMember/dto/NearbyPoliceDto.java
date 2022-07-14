package com.cesgroup.prison.emergency.handle.recordMember.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 附近民警DTO
 */
public class NearbyPoliceDto implements Serializable {
    /**
     * 民警警号
     */
    private String policeNo;
    /**
     * 民警姓名
     */
    private String policeName;
    /**
     * 警务通号码
     */
    private String policeAffair;
    /**
     * 民警所属部门编号
     */
    private String deptId;
    /**
     * 民警所属部门名称
     */
    private String deptName;

    /**
     * 监狱/单位编号
     */
    private String unitId;

    /**
     * 监狱/单位名称
     */
    private String unitName;
    /**
     * 数据捕获时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date captureTime;
    /**
     * 所在位置
     */
    private String locationName;
    /**
     * 数据来源
     */
    private String dataSource;
    /**
     * 数据类型
     */
    private String dataCategory;

    public String getPoliceNo() {
        return policeNo;
    }

    public void setPoliceNo(String policeNo) {
        this.policeNo = policeNo;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getPoliceAffair() {
        return policeAffair;
    }

    public void setPoliceAffair(String policeAffair) {
        this.policeAffair = policeAffair;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public Date getCaptureTime() {
        return captureTime;
    }

    public void setCaptureTime(Date captureTime) {
        this.captureTime = captureTime;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getDataCategory() {
        return dataCategory;
    }

    public void setDataCategory(String dataCategory) {
        this.dataCategory = dataCategory;
    }
}
