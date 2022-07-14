package com.cesgroup.prison.region.entity;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cesgroup.framework.base.entity.StringIDEntity;


/**
 * 区域信息表
 * 
 * @author linhe
 * @date 2017-11-27
 *
 */
@Entity
@Table(name = "cds_area_base_dtls")
public class Region extends StringIDEntity {

	private static final long serialVersionUID = 9170900593523920698L;
	private String abdCusNumber;
	private String abdAreaId;    
	private String abdAreaName;
	private int abdTypeIndc;
    private int abdLevelIndc; 
    private String abdParentAreaId;
    //private int abdSrno;
    private String abdRemark;
    private Date abdCrteTime;
    private String abdCrteUserId;
    private Date abdUpdtTime;
    private String abdUpdtUserId;
    private String abdShortName;
    private int abdOrder;
    private String abdIsLeaf;
    private String abdJqId;
    private String abdJqName;
    
	public String getAbdCusNumber() {
		return abdCusNumber;
	}
	public void setAbdCusNumber(String abdCusNumber) {
		this.abdCusNumber = abdCusNumber;
	}
	public String getAbdAreaId() {
		return abdAreaId;
	}
	public void setAbdAreaId(String abdAreaId) {
		this.abdAreaId = abdAreaId;
	}
	public String getAbdAreaName() {
		return abdAreaName;
	}
	public void setAbdAreaName(String abdAreaName) {
		this.abdAreaName = abdAreaName;
	}
	public int getAbdTypeIndc() {
		return abdTypeIndc;
	}
	public void setAbdTypeIndc(int abdTypeIndc) {
		this.abdTypeIndc = abdTypeIndc;
	}
	public int getAbdLevelIndc() {
		return abdLevelIndc;
	}
	public void setAbdLevelIndc(int abdLevelIndc) {
		this.abdLevelIndc = abdLevelIndc;
	}
	public String getAbdParentAreaId() {
		return abdParentAreaId;
	}
	public void setAbdParentAreaId(String abdParentAreaId) {
		this.abdParentAreaId = abdParentAreaId;
	}
	/*public int getAbdSrno() {
		return abdSrno;
	}
	public void setAbdSrno(int abdSrno) {
		this.abdSrno = abdSrno;
	}*/
	public String getAbdRemark() {
		return abdRemark;
	}
	public void setAbdRemark(String abdRemark) {
		this.abdRemark = abdRemark;
	}
	public Date getAbdCrteTime() {
		return abdCrteTime;
	}
	public void setAbdCrteTime(Date date) {
		this.abdCrteTime = date;
	}
	public String getAbdCrteUserId() {
		return abdCrteUserId;
	}
	public void setAbdCrteUserId(String abdCrteUserId) {
		this.abdCrteUserId = abdCrteUserId;
	}
	public Date getAbdUpdtTime() {
		return abdUpdtTime;
	}
	public void setAbdUpdtTime(Date abdUpdtTime) {
		this.abdUpdtTime = abdUpdtTime;
	}
	public String getAbdUpdtUserId() {
		return abdUpdtUserId;
	}
	public void setAbdUpdtUserId(String abdUpdtUserId) {
		this.abdUpdtUserId = abdUpdtUserId;
	}
	public String getAbdShortName() {
		return abdShortName;
	}
	public void setAbdShortName(String abdShortName) {
		this.abdShortName = abdShortName;
	}
	public int getAbdOrder() {
		return abdOrder;
	}
	public void setAbdOrder(int abdOrder) {
		this.abdOrder = abdOrder;
	}
	public String getAbdIsLeaf() {
		return abdIsLeaf;
	}
	public void setAbdIsLeaf(String abdIsLeaf) {
		this.abdIsLeaf = abdIsLeaf;
	}
	public String getAbdJqId() {
		return abdJqId;
	}
	public void setAbdJqId(String abdJqId) {
		this.abdJqId = abdJqId;
	}
	public String getAbdJqName() {
		return abdJqName;
	}
	public void setAbdJqName(String abdJqName) {
		this.abdJqName = abdJqName;
	}
	
    
}
