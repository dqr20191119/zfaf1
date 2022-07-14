package com.cesgroup.prison.jfsb.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**      
* @projectName：prison   
* @ClassName：AlertorEntity   
* @Description：   报警器
* @author：Tao.xu   
* @date：2017年12月12日 下午3:30:18   
* @version        
*/
@Entity
@Table(name = "DVC_ALERTOR_BASE_DTLS")
public class AlertorEntity extends StringIDEntity {
	/**
	* @Fields serialVersionUID :  
	*/
	private static final long serialVersionUID = 1L;

	private String abdCusNumber;

	private String abdIdnty;

	private String abdName;

	private String abdPreName;

	private String abdTypeIndc;

	private String abdAreaId;

	private String abdArea;

	private String abdAddrs;

	private String abdIp;

	private String abdPort;

	private String abdHostIdnty;

	private String abdSttsIndc;

	private String abdActionIndc;

	private String abdRemark;
	private String  abdType;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date abdCrteTime;

	private String abdCrteUserId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date abdUpdtTime;

	private String abdUpdtUserId;

	private String abdBrandIndc;


	private String abdAlertorValid;//报警是否生效

	private String abdAlertorStarttime;//报警开始时间

	private String abdAlertorEndtime;//报警结束时间



	public String getAbdType() {
		return abdType;
	}

	public void setAbdType(String abdType) {
		this.abdType = abdType;
	}

	public String getAbdCusNumber() {
		return abdCusNumber;
	}

	public void setAbdCusNumber(String abdCusNumber) {
		this.abdCusNumber = abdCusNumber;
	}

	public String getAbdIdnty() {
		return abdIdnty;
	}

	public void setAbdIdnty(String abdIdnty) {
		this.abdIdnty = abdIdnty;
	}

	public String getAbdName() {
		return abdName;
	}

	public void setAbdName(String abdName) {
		this.abdName = abdName;
	}

	public String getAbdPreName() {
		return abdPreName;
	}

	public void setAbdPreName(String abdPreName) {
		this.abdPreName = abdPreName;
	}

	public String getAbdTypeIndc() {
		return abdTypeIndc;
	}

	public void setAbdTypeIndc(String abdTypeIndc) {
		this.abdTypeIndc = abdTypeIndc;
	}

	public String getAbdAreaId() {
		return abdAreaId;
	}

	public void setAbdAreaId(String abdAreaId) {
		this.abdAreaId = abdAreaId;
	}

	public String getAbdArea() {
		return abdArea;
	}

	public void setAbdArea(String abdArea) {
		this.abdArea = abdArea;
	}

	public String getAbdAddrs() {
		return abdAddrs;
	}

	public void setAbdAddrs(String abdAddrs) {
		this.abdAddrs = abdAddrs;
	}

	public String getAbdIp() {
		return abdIp;
	}

	public void setAbdIp(String abdIp) {
		this.abdIp = abdIp;
	}

	public String getAbdPort() {
		return abdPort;
	}

	public void setAbdPort(String abdPort) {
		this.abdPort = abdPort;
	}

	public String getAbdHostIdnty() {
		return abdHostIdnty;
	}

	public void setAbdHostIdnty(String abdHostIdnty) {
		this.abdHostIdnty = abdHostIdnty;
	}

	public String getAbdSttsIndc() {
		return abdSttsIndc;
	}

	public void setAbdSttsIndc(String abdSttsIndc) {
		this.abdSttsIndc = abdSttsIndc;
	}

	public String getAbdActionIndc() {
		return abdActionIndc;
	}

	public void setAbdActionIndc(String abdActionIndc) {
		this.abdActionIndc = abdActionIndc;
	}

	public String getAbdRemark() {
		return abdRemark;
	}

	public void setAbdRemark(String abdRemark) {
		this.abdRemark = abdRemark;
	}

	public Date getAbdCrteTime() {
		return abdCrteTime;
	}

	public void setAbdCrteTime(Date abdCrteTime) {
		this.abdCrteTime = abdCrteTime;
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

	public String getAbdBrandIndc() {
		return abdBrandIndc;
	}

	public void setAbdBrandIndc(String abdBrandIndc) {
		this.abdBrandIndc = abdBrandIndc;
	}

    public String getAbdAlertorValid() {
        return abdAlertorValid;
    }

    public void setAbdAlertorValid(String abdAlertorValid) {
        this.abdAlertorValid = abdAlertorValid;
    }

    public String getAbdAlertorStarttime() {
        return abdAlertorStarttime;
    }

    public void setAbdAlertorStarttime(String abdAlertorStarttime) {
        this.abdAlertorStarttime = abdAlertorStarttime;
    }

    public String getAbdAlertorEndtime() {
        return abdAlertorEndtime;
    }

    public void setAbdAlertorEndtime(String abdAlertorEndtime) {
        this.abdAlertorEndtime = abdAlertorEndtime;
    }
}
