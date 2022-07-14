package com.cesgroup.prison.location.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 民警所在位置实体类
 * 
 * @author lincoln.cheng
 *
 */
@Entity
@Table(name = "CDS_POLICE_LOCATION")
public class PoliceLocation extends StringIDEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 警号
	 */
	private String policeNo;
	/**
	 * 民警姓名
	 */
    private String policeName;
    /**
     * 所在位置编号
     */
    private String locationNo;
    /**
     * 所在位置名称
     */
    private String locationName;
    /**
     * 所在位置排序
     */
    private Integer locationPx;
    /**
     * 监狱编号
     */
    private String prisonNo;
    /**
     * 监狱名称
     */
    private String prisonName;
    /**
     * 数据来源
     */
    private String dataSource;
    /**
     * 消息同步时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date mqSyncTime;
    
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
	public String getLocationNo() {
		return locationNo;
	}
	public void setLocationNo(String locationNo) {
		this.locationNo = locationNo;
	}
	public String getLocationName() {
		return locationName;
	}
	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}
	public Integer getLocationPx() {
		return locationPx;
	}
	public void setLocationPx(Integer locationPx) {
		this.locationPx = locationPx;
	}
	public String getPrisonNo() {
		return prisonNo;
	}
	public void setPrisonNo(String prisonNo) {
		this.prisonNo = prisonNo;
	}
	public String getPrisonName() {
		return prisonName;
	}
	public void setPrisonName(String prisonName) {
		this.prisonName = prisonName;
	}
	public String getDataSource() {
		return dataSource;
	}
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	public Date getMqSyncTime() {
		return mqSyncTime;
	}
	public void setMqSyncTime(Date mqSyncTime) {
		this.mqSyncTime = mqSyncTime;
	}
}