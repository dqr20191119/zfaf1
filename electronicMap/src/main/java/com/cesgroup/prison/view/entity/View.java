package com.cesgroup.prison.view.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;
/**
 * 视角信息
 * 
 * @author linhe
 * @date 2017-12-11
 *
 */
@Entity
@Table(name = "cds_view_func")
public class View extends StringIDEntity{
	private static final long serialVersionUID = 1L;

	private String vfuCusNumber;

    private String vfuViewName;

    private int vfuViewType;

    private String vfuAreaId;

    private String vfuXCrdnt;

    private String vfuYCrdnt;

    private String vfuZCrdnt;

    private String vfuHeadingCrdnt;

    private String vfuTiltCrdnt;
    
    private int vfuStts;

    private String vfuRemark;

    private String vfuCrteUserId;

    private Date vfuCrteTime;

    private String vfuUpdtUserId;

    private Date vfuUpdtTime;

    private int vfuDefIndc;
    
    private int vfuOrder;
    
    private String vfuConfine;

	public String getVfuCusNumber() {
		return vfuCusNumber;
	}

	public void setVfuCusNumber(String vfuCusNumber) {
		this.vfuCusNumber = vfuCusNumber;
	}

	public String getVfuViewName() {
		return vfuViewName;
	}

	public void setVfuViewName(String vfuViewName) {
		this.vfuViewName = vfuViewName;
	}

	public int getVfuViewType() {
		return vfuViewType;
	}

	public void setVfuViewType(int vfuViewType) {
		this.vfuViewType = vfuViewType;
	}

	public String getVfuAreaId() {
		return vfuAreaId;
	}

	public void setVfuAreaId(String vfuAreaId) {
		this.vfuAreaId = vfuAreaId;
	}

	public String getVfuXCrdnt() {
		return vfuXCrdnt;
	}

	public void setVfuXCrdnt(String vfuXCrdnt) {
		this.vfuXCrdnt = vfuXCrdnt;
	}

	public String getVfuYCrdnt() {
		return vfuYCrdnt;
	}

	public void setVfuYCrdnt(String vfuYCrdnt) {
		this.vfuYCrdnt = vfuYCrdnt;
	}

	public String getVfuZCrdnt() {
		return vfuZCrdnt;
	}

	public void setVfuZCrdnt(String vfuZCrdnt) {
		this.vfuZCrdnt = vfuZCrdnt;
	}

	public String getVfuHeadingCrdnt() {
		return vfuHeadingCrdnt;
	}

	public void setVfuHeadingCrdnt(String vfuHeadingCrdnt) {
		this.vfuHeadingCrdnt = vfuHeadingCrdnt;
	}

	public String getVfuTiltCrdnt() {
		return vfuTiltCrdnt;
	}

	public void setVfuTiltCrdnt(String vfuTiltCrdnt) {
		this.vfuTiltCrdnt = vfuTiltCrdnt;
	}

	public int getVfuStts() {
		return vfuStts;
	}

	public void setVfuStts(int vfuStts) {
		this.vfuStts = vfuStts;
	}

	public String getVfuRemark() {
		return vfuRemark;
	}

	public void setVfuRemark(String vfuRemark) {
		this.vfuRemark = vfuRemark;
	}

	public String getVfuCrteUserId() {
		return vfuCrteUserId;
	}

	public void setVfuCrteUserId(String vfuCrteUserId) {
		this.vfuCrteUserId = vfuCrteUserId;
	}

	public Date getVfuCrteTime() {
		return vfuCrteTime;
	}

	public void setVfuCrteTime(Date vfuCrteTime) {
		this.vfuCrteTime = vfuCrteTime;
	}

	public String getVfuUpdtUserId() {
		return vfuUpdtUserId;
	}

	public void setVfuUpdtUserId(String vfuUpdtUserId) {
		this.vfuUpdtUserId = vfuUpdtUserId;
	}

	public Date getVfuUpdtTime() {
		return vfuUpdtTime;
	}

	public void setVfuUpdtTime(Date vfuUpdtdTime) {
		this.vfuUpdtTime = vfuUpdtdTime;
	}

	public int getVfuDefIndc() {
		return vfuDefIndc;
	}

	public void setVfuDefIndc(int vfuDefIndc) {
		this.vfuDefIndc = vfuDefIndc;
	}

	public int getVfuOrder() {
		return vfuOrder;
	}

	public void setVfuOrder(int vfuOrder) {
		this.vfuOrder = vfuOrder;
	}

	public String getVfuConfine() {
		return vfuConfine;
	}

	public void setVfuConfine(String vfuConfine) {
		this.vfuConfine = vfuConfine;
	}

    
}