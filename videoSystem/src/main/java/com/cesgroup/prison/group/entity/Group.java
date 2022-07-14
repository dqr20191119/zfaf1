package com.cesgroup.prison.group.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**   
*    
* @projectName：prison   
* @ClassName：Group   
* @Description：群组主表实体   
* @author：zhengke   
* @date：2017-12-01 14:58   
* @version        
*/
@Entity
@Table(name="CDS_GRP_MASTER")
public class Group extends StringIDEntity{
	/**
	* @Fields gmaCusNumber : 监所名称
	*/
	private String gmaCusNumber;

	/**
	* @Fields gmaGrpName : 群组名称
	*/
	private String gmaGrpName;

	/**
	* @Fields gmaTypeIndc : 群组类别1、摄像机，2、门禁，3、广播，4、大屏
	*/
	private String gmaTypeIndc;


	/**
	* @Fields gmaSubTypeIndc : 群组类型
	*/
	private String gmaSubTypeIndc;

	/**
	* @Fields gmaStartTime : 开始时间
	*/
	@JsonFormat(pattern="HH:mm:ss", locale = "zh" , timezone="GMT+8")
	@DateTimeFormat(pattern="HH:mm:ss")
	private Date gmaStartTime;

	/**
	* @Fields gmaEndTime : 结束时间
	*/
	@JsonFormat(pattern="HH:mm:ss", locale = "zh" , timezone="GMT+8")
	@DateTimeFormat(pattern="HH:mm:ss")
	private Date gmaEndTime;

	/**
	* @Fields gmaRemark : 备注
	*/
	private String gmaRemark;

	/**
	* @Fields gmaCrteTime : 创建时间
	*/
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date gmaCrteTime;

	/**
	* @Fields gmaCrteUserId : 创建人
	*/
	private String gmaCrteUserId;

	/**
	* @Fields gmaUpdtTime : 更新时间
	*/
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date gmaUpdtTime;

	/**
	* @Fields gmaUpdtUserId : 更新人
	*/
	private String gmaUpdtUserId;

	/**
	* @Fields gmaShowSeq : 显示序列
	*/
	private Integer gmaShowSeq;

	/**
	* @Fields gmaUseRange : 使用范围 0、公共，1、自定义
	*/
	private String gmaUseRange;

	/**
	* @Fields gmaShortName : 群组简称
	*/
	private String gmaShortName;

	/**
	* @Fields gmaParentId : 父ID
	*/
	private String gmaParentId;

	/**
	* @Fields gmaPurpose : 群组用途
	*/
	private String gmaPurpose;
	
	/**
	* @Fields gmaIsLeaf : 是否是叶子节点0、不是，1、是
	*/
	private String gmaIsLeaf;
	/**
	* @Fields gmaIsLeaf : 预案所属部门ID
	*/
	private String gmaCrteDepartment;
	
	public String getGmaCrteDepartment() {
		return gmaCrteDepartment;
	}

	public void setGmaCrteDepartment(String gmaCrteDepartment) {
		this.gmaCrteDepartment = gmaCrteDepartment;
	}

	public String getGmaIsLeaf() {
		return gmaIsLeaf;
	}

	public void setGmaIsLeaf(String gmaIsLeaf) {
		this.gmaIsLeaf = gmaIsLeaf;
	}

	
	public String getGmaCusNumber() {
		return gmaCusNumber;
	}

	public void setGmaCusNumber(String gmaCusNumber) {
		this.gmaCusNumber = gmaCusNumber;
	}

	public String getGmaGrpName() {
		return gmaGrpName;
	}

	public void setGmaGrpName(String gmaGrpName) {
		this.gmaGrpName = gmaGrpName;
	}

	public String getGmaTypeIndc() {
		return gmaTypeIndc;
	}

	public void setGmaTypeIndc(String gmaTypeIndc) {
		this.gmaTypeIndc = gmaTypeIndc;
	}

	public String getGmaSubTypeIndc() {
		return gmaSubTypeIndc;
	}

	public void setGmaSubTypeIndc(String gmaSubTypeIndc) {
		this.gmaSubTypeIndc = gmaSubTypeIndc;
	}

	public Date getGmaStartTime() {
		return gmaStartTime;
	}

	public void setGmaStartTime(Date gmaStartTime) {
		this.gmaStartTime = gmaStartTime;
	}

	public Date getGmaEndTime() {
		return gmaEndTime;
	}

	public void setGmaEndTime(Date gmaEndTime) {
		this.gmaEndTime = gmaEndTime;
	}

	public String getGmaRemark() {
		return gmaRemark;
	}

	public void setGmaRemark(String gmaRemark) {
		this.gmaRemark = gmaRemark;
	}

	public Date getGmaCrteTime() {
		return gmaCrteTime;
	}

	public void setGmaCrteTime(Date gmaCrteTime) {
		this.gmaCrteTime = gmaCrteTime;
	}

	public String getGmaCrteUserId() {
		return gmaCrteUserId;
	}

	public void setGmaCrteUserId(String gmaCrteUserId) {
		this.gmaCrteUserId = gmaCrteUserId;
	}

	public Date getGmaUpdtTime() {
		return gmaUpdtTime;
	}

	public void setGmaUpdtTime(Date gmaUpdtTime) {
		this.gmaUpdtTime = gmaUpdtTime;
	}

	public String getGmaUpdtUserId() {
		return gmaUpdtUserId;
	}

	public void setGmaUpdtUserId(String gmaUpdtUserId) {
		this.gmaUpdtUserId = gmaUpdtUserId;
	}

	public Integer getGmaShowSeq() {
		return gmaShowSeq;
	}

	public void setGmaShowSeq(Integer gmaShowSeq) {
		this.gmaShowSeq = gmaShowSeq;
	}

	public String getGmaUseRange() {
		return gmaUseRange;
	}

	public void setGmaUseRange(String gmaUseRange) {
		this.gmaUseRange = gmaUseRange;
	}

	public String getGmaShortName() {
		return gmaShortName;
	}

	public void setGmaShortName(String gmaShortName) {
		this.gmaShortName = gmaShortName;
	}

	public String getGmaParentId() {
		return gmaParentId;
	}

	public void setGmaParentId(String gmaParentId) {
		this.gmaParentId = gmaParentId;
	}

	public String getGmaPurpose() {
		return gmaPurpose;
	}

	public void setGmaPurpose(String gmaPurpose) {
		this.gmaPurpose = gmaPurpose;
	}


}
