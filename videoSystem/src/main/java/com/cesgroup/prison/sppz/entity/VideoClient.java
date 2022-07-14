package com.cesgroup.prison.sppz.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="DVC_VIDEO_CLIENT_CONFIG")
public class VideoClient extends StringIDEntity{
    private String vccCusNumber;

    private String vccAppIp;

    private String vccClientIp;

    private String vccWidth;

    private String vccHeight;

    private String vccXCrdnt;

    private String vccYCrdnt;

    private String vccImgPath;

    private String vccVideoPath;

    private String vccRemark;
    @NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date vccCrteTime;

    private String vccCrteUserId;
    @NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date vccUpdtTime;

    private String vccUpdtUserId;

    public String getVccCusNumber() {
        return vccCusNumber;
    }

    public void setVccCusNumber(String vccCusNumber) {
        this.vccCusNumber = vccCusNumber;
    }

    public String getVccAppIp() {
        return vccAppIp;
    }

    public void setVccAppIp(String vccAppIp) {
        this.vccAppIp = vccAppIp == null ? null : vccAppIp.trim();
    }

    public String getVccClientIp() {
        return vccClientIp;
    }

    public void setVccClientIp(String vccClientIp) {
        this.vccClientIp = vccClientIp == null ? null : vccClientIp.trim();
    }

    public String getVccWidth() {
        return vccWidth;
    }

    public void setVccWidth(String vccWidth) {
        this.vccWidth = vccWidth;
    }

    public String getVccHeight() {
        return vccHeight;
    }

    public void setVccHeight(String vccHeight) {
        this.vccHeight = vccHeight;
    }

    public String getVccXCrdnt() {
        return vccXCrdnt;
    }

    public void setVccXCrdnt(String vccXCrdnt) {
        this.vccXCrdnt = vccXCrdnt;
    }

    public String getVccYCrdnt() {
        return vccYCrdnt;
    }

    public void setVccYCrdnt(String vccYCrdnt) {
        this.vccYCrdnt = vccYCrdnt;
    }

    public String getVccImgPath() {
        return vccImgPath;
    }

    public void setVccImgPath(String vccImgPath) {
        this.vccImgPath = vccImgPath == null ? null : vccImgPath.trim();
    }

    public String getVccVideoPath() {
        return vccVideoPath;
    }

    public void setVccVideoPath(String vccVideoPath) {
        this.vccVideoPath = vccVideoPath == null ? null : vccVideoPath.trim();
    }

    public String getVccRemark() {
        return vccRemark;
    }

    public void setVccRemark(String vccRemark) {
        this.vccRemark = vccRemark == null ? null : vccRemark.trim();
    }

    public Date getVccCrteTime() {
        return vccCrteTime;
    }

    public void setVccCrteTime(Date vccCrteTime) {
        this.vccCrteTime = vccCrteTime;
    }

    public String getVccCrteUserId() {
        return vccCrteUserId;
    }

    public void setVccCrteUserId(String vccCrteUserId) {
        this.vccCrteUserId = vccCrteUserId == null ? null : vccCrteUserId.trim();
    }

    public Date getVccUpdtTime() {
        return vccUpdtTime;
    }

    public void setVccUpdtTime(Date vccUpdtTime) {
        this.vccUpdtTime = vccUpdtTime;
    }

    public String getVccUpdtUserId() {
        return vccUpdtUserId;
    }

    public void setVccUpdtUserId(String vccUpdtUserId) {
        this.vccUpdtUserId = vccUpdtUserId == null ? null : vccUpdtUserId.trim();
    }

}