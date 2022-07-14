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
* @ClassName：GroupMember   
* @Description： 群组成员  
* @author：zhengke   
* @date：2017-12-10 21:06   
* @version        
*/
@Entity
@Table(name="CDS_GRP_RLTN_DTLS")
public class GroupMember  extends StringIDEntity{
	/**
	* @Fields serialVersionUID : TODO
	*/
	private static final long serialVersionUID = 1L;

	/**
	* @Fields grdCusNumber : 监所编号
	*/
	private String grdCusNumber;

	/**
	* @Fields grdGrpId : 群组ID
	*/
	private String grdGrpId;

	/**
	* @Fields grdGrpName : 群组名称
	*/
	private String grdGrpName;

	/**
	* @Fields grdTypeIndc : 群组类型
	*/
	private String grdTypeIndc;

	/**
	* @Fields grdMmbrName : 成员名称
	*/
	private String grdMmbrName;

	/**
	* @Fields grdMmbrIdnty : 成员ID
	*/
	private String grdMmbrIdnty;

	/**
	* @Fields grdRemark : 备注
	*/
	private String grdRemark;

	/**
	* @Fields grdCrteTime : 创建时间
	*/
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date grdCrteTime;

	/**
	* @Fields grdCrteUserId : 创建人
	*/
	private String grdCrteUserId;

	/**
	* @Fields grdUpdtTime : 更新时间
	*/
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date grdUpdtTime;

	/**
	* @Fields grdUpdtUserId : 更新人
	*/
	private String grdUpdtUserId;

	/**
	* @Fields grdShowSeq : 显示序列
	*/
	private Integer grdShowSeq;

	public String getGrdCusNumber() {
		return grdCusNumber;
	}

	public void setGrdCusNumber(String grdCusNumber) {
		this.grdCusNumber = grdCusNumber;
	}

	public String getGrdGrpId() {
		return grdGrpId;
	}

	public void setGrdGrpId(String grdGrpId) {
		this.grdGrpId = grdGrpId;
	}

	public String getGrdGrpName() {
		return grdGrpName;
	}

	public void setGrdGrpName(String grdGrpName) {
		this.grdGrpName = grdGrpName;
	}

	public String getGrdTypeIndc() {
		return grdTypeIndc;
	}

	public void setGrdTypeIndc(String grdTypeIndc) {
		this.grdTypeIndc = grdTypeIndc;
	}

	public String getGrdMmbrName() {
		return grdMmbrName;
	}

	public void setGrdMmbrName(String grdMmbrName) {
		this.grdMmbrName = grdMmbrName;
	}

	public String getGrdMmbrIdnty() {
		return grdMmbrIdnty;
	}

	public void setGrdMmbrIdnty(String grdMmbrIdnty) {
		this.grdMmbrIdnty = grdMmbrIdnty;
	}

	public String getGrdRemark() {
		return grdRemark;
	}

	public void setGrdRemark(String grdRemark) {
		this.grdRemark = grdRemark;
	}

	public Date getGrdCrteTime() {
		return grdCrteTime;
	}

	public void setGrdCrteTime(Date grdCrteTime) {
		this.grdCrteTime = grdCrteTime;
	}

	public String getGrdCrteUserId() {
		return grdCrteUserId;
	}

	public void setGrdCrteUserId(String grdCrteUserId) {
		this.grdCrteUserId = grdCrteUserId;
	}

	public Date getGrdUpdtTime() {
		return grdUpdtTime;
	}

	public void setGrdUpdtTime(Date grdUpdtTime) {
		this.grdUpdtTime = grdUpdtTime;
	}

	public String getGrdUpdtUserId() {
		return grdUpdtUserId;
	}

	public void setGrdUpdtUserId(String grdUpdtUserId) {
		this.grdUpdtUserId = grdUpdtUserId;
	}

	public Integer getGrdShowSeq() {
		return grdShowSeq;
	}

	public void setGrdShowSeq(Integer grdShowSeq) {
		this.grdShowSeq = grdShowSeq;
	}
	
	
}
