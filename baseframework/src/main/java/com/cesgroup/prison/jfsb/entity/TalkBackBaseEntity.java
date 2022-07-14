package com.cesgroup.prison.jfsb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**      
* @projectName：prison   
* @ClassName：TalkBackBaseEntitiy   
* @Description：   对讲信息
* @author：Tao.xu   
* @date：2017年12月21日 上午10:17:38   
* @version        
*/
@Entity
@Table(name = "DVC_TALKBACK_BASE_DTLS")
public class TalkBackBaseEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = 297548944103171139L;

	private String tbdCusNumber;

	private String tbdMainIdnty;

	private String tbdSeqNum;

	private String tbdIdnty;

	private String tbdBrand;

	private String tbdName;

	private String tbdPreName;

	private String tbdAddrs;

	private String tbdSttsIndc;

	private String tbdIpAddrs;

	private String tbdChnnlAddrs;

	private String tbdTypeIndc;

	private String tbdAreaId;

	private String tbdArea;

	private String tbdDprtmntId;

	private String tbdDprtmnt;

	private String tbdActionIndc;

	private String tbdRemark;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date tbdCrteTime;

	private String tbdCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date tbdUpdtTime;

	private String tbdUpdtUserId;

	public String getTbdCusNumber() {
		return tbdCusNumber;
	}

	public void setTbdCusNumber(String tbdCusNumber) {
		this.tbdCusNumber = tbdCusNumber;
	}

	public String getTbdMainIdnty() {
		return tbdMainIdnty;
	}

	public void setTbdMainIdnty(String tbdMainIdnty) {
		this.tbdMainIdnty = tbdMainIdnty;
	}

	public String getTbdSeqNum() {
		return tbdSeqNum;
	}

	public void setTbdSeqNum(String tbdSeqNum) {
		this.tbdSeqNum = tbdSeqNum;
	}

	public String getTbdIdnty() {
		return tbdIdnty;
	}

	public void setTbdIdnty(String tbdIdnty) {
		this.tbdIdnty = tbdIdnty;
	}

	public String getTbdBrand() {
		return tbdBrand;
	}

	public void setTbdBrand(String tbdBrand) {
		this.tbdBrand = tbdBrand;
	}

	public String getTbdName() {
		return tbdName;
	}

	public void setTbdName(String tbdName) {
		this.tbdName = tbdName;
	}

	public String getTbdPreName() {
		return tbdPreName;
	}

	public void setTbdPreName(String tbdPreName) {
		this.tbdPreName = tbdPreName;
	}

	public String getTbdAddrs() {
		return tbdAddrs;
	}

	public void setTbdAddrs(String tbdAddrs) {
		this.tbdAddrs = tbdAddrs;
	}

	public String getTbdSttsIndc() {
		return tbdSttsIndc;
	}

	public void setTbdSttsIndc(String tbdSttsIndc) {
		this.tbdSttsIndc = tbdSttsIndc;
	}

	public String getTbdIpAddrs() {
		return tbdIpAddrs;
	}

	public void setTbdIpAddrs(String tbdIpAddrs) {
		this.tbdIpAddrs = tbdIpAddrs;
	}

	public String getTbdChnnlAddrs() {
		return tbdChnnlAddrs;
	}

	public void setTbdChnnlAddrs(String tbdChnnlAddrs) {
		this.tbdChnnlAddrs = tbdChnnlAddrs;
	}

	public String getTbdTypeIndc() {
		return tbdTypeIndc;
	}

	public void setTbdTypeIndc(String tbdTypeIndc) {
		this.tbdTypeIndc = tbdTypeIndc;
	}

	public String getTbdAreaId() {
		return tbdAreaId;
	}

	public void setTbdAreaId(String tbdAreaId) {
		this.tbdAreaId = tbdAreaId;
	}

	public String getTbdArea() {
		return tbdArea;
	}

	public void setTbdArea(String tbdArea) {
		this.tbdArea = tbdArea;
	}

	public String getTbdDprtmntId() {
		return tbdDprtmntId;
	}

	public void setTbdDprtmntId(String tbdDprtmntId) {
		this.tbdDprtmntId = tbdDprtmntId;
	}

	public String getTbdDprtmnt() {
		return tbdDprtmnt;
	}

	public void setTbdDprtmnt(String tbdDprtmnt) {
		this.tbdDprtmnt = tbdDprtmnt;
	}

	public String getTbdActionIndc() {
		return tbdActionIndc;
	}

	public void setTbdActionIndc(String tbdActionIndc) {
		this.tbdActionIndc = tbdActionIndc;
	}

	public String getTbdRemark() {
		return tbdRemark;
	}

	public void setTbdRemark(String tbdRemark) {
		this.tbdRemark = tbdRemark;
	}

	public Date getTbdCrteTime() {
		return tbdCrteTime;
	}

	public void setTbdCrteTime(Date tbdCrteTime) {
		this.tbdCrteTime = tbdCrteTime;
	}

	public String getTbdCrteUserId() {
		return tbdCrteUserId;
	}

	public void setTbdCrteUserId(String tbdCrteUserId) {
		this.tbdCrteUserId = tbdCrteUserId;
	}

	public Date getTbdUpdtTime() {
		return tbdUpdtTime;
	}

	public void setTbdUpdtTime(Date tbdUpdtTime) {
		this.tbdUpdtTime = tbdUpdtTime;
	}

	public String getTbdUpdtUserId() {
		return tbdUpdtUserId;
	}

	public void setTbdUpdtUserId(String tbdUpdtUserId) {
		this.tbdUpdtUserId = tbdUpdtUserId;
	}

}
