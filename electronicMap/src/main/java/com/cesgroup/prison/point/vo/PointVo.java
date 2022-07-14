package com.cesgroup.prison.point.vo;

import com.cesgroup.prison.point.entity.Point;

import java.util.List;

public class PointVo extends Point {
    //区域id
    private String alpGrandId;
    //区域id
    private String alpGrandName;
    //设备名称
    private String deviceName;
    //设备类型
    private String deviceType;
    //设备类型Name
    private String deviceTypeName;
    //设备id
    private String deviceId;
    //模糊查询
    private String Q;
    //临时容器
    private List<String> strList;

    private List<String> alpDeviceStr;

    private int ifShowChild;

    private String areaName;

    public String getAlpGrandId() {
        return alpGrandId;
    }

    public void setAlpGrandId(String alpGrandId) {
        this.alpGrandId = alpGrandId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceTypeName() {
        return deviceTypeName;
    }

    public void setDeviceTypeName(String deviceTypeName) {
        this.deviceTypeName = deviceTypeName;
    }

    public String getQ() {
        return Q;
    }

    public void setQ(String q) {
        Q = q;
    }

    public List<String> getStrList() {
        return strList;
    }

    public void setStrList(List<String> strList) {
        this.strList = strList;
    }

    public int getIfShowChild() {
        return ifShowChild;
    }

    public void setIfShowChild(int ifShowChild) {
        this.ifShowChild = ifShowChild;
    }

    public List<String> getAlpDeviceStr() {
        return alpDeviceStr;
    }

    public void setAlpDeviceStr(List<String> alpDeviceStr) {
        this.alpDeviceStr = alpDeviceStr;
    }

    public String getAlpGrandName() {
        return alpGrandName;
    }

    public void setAlpGrandName(String alpGrandName) {
        this.alpGrandName = alpGrandName;
    }
}
