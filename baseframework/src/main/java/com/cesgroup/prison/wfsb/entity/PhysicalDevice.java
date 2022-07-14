package com.cesgroup.prison.wfsb.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
/**   
*    
* @projectName：prison   
* @ClassName：PhysicalDevice   
* @Description：物防设备   
* @author：zhengke   
* @date：2017-12-13 19:56   
* @version        
*/
@Entity
@Table(name="CDS_PHYSICAL_DEVICE")
public class PhysicalDevice extends StringIDEntity{
    private String pdeCusNumber;

    private String pdeDeviceIdnty;

    private String pdeDeviceName;

    private String pdeTypeIndc;

    private String pdeLocation;

    private Integer pdeNormalCount;

    private Integer pdeAbnormalCount;

    private String pdeRemark;

    private String pdeCrteUserId;
    @NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date pdeCrteTime;

    private String pdeUpdtUserId;
    @NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date pdeUpdtTime;

    private String pdeUnit;
    public String getPdeCusNumber() {
        return pdeCusNumber;
    }

    public void setPdeCusNumber(String pdeCusNumber) {
        this.pdeCusNumber = pdeCusNumber == null ? null : pdeCusNumber.trim();
    }

    public String getPdeDeviceIdnty() {
        return pdeDeviceIdnty;
    }

    public void setPdeDeviceIdnty(String pdeDeviceIdnty) {
        this.pdeDeviceIdnty = pdeDeviceIdnty == null ? null : pdeDeviceIdnty.trim();
    }

    public String getPdeDeviceName() {
        return pdeDeviceName;
    }

    public void setPdeDeviceName(String pdeDeviceName) {
        this.pdeDeviceName = pdeDeviceName == null ? null : pdeDeviceName.trim();
    }

    public String getPdeTypeIndc() {
        return pdeTypeIndc;
    }

    public void setPdeTypeIndc(String pdeTypeIndc) {
        this.pdeTypeIndc = pdeTypeIndc == null ? null : pdeTypeIndc.trim();
    }

    public String getPdeLocation() {
        return pdeLocation;
    }

    public void setPdeLocation(String pdeLocation) {
        this.pdeLocation = pdeLocation == null ? null : pdeLocation.trim();
    }

    public Integer getPdeNormalCount() {
        return pdeNormalCount;
    }

    public void setPdeNormalCount(Integer pdeNormalCount) {
        this.pdeNormalCount = pdeNormalCount;
    }

    public Integer getPdeAbnormalCount() {
        return pdeAbnormalCount;
    }

    public void setPdeAbnormalCount(Integer pdeAbnormalCount) {
        this.pdeAbnormalCount = pdeAbnormalCount;
    }

    public String getPdeRemark() {
        return pdeRemark;
    }

    public void setPdeRemark(String pdeRemark) {
        this.pdeRemark = pdeRemark == null ? null : pdeRemark.trim();
    }


    public String getPdeCrteUserId() {
		return pdeCrteUserId;
	}

	public void setPdeCrteUserId(String pdeCrteUserId) {
		this.pdeCrteUserId = pdeCrteUserId;
	}

	public String getPdeUpdtUserId() {
		return pdeUpdtUserId;
	}

	public void setPdeUpdtUserId(String pdeUpdtUserId) {
		this.pdeUpdtUserId = pdeUpdtUserId;
	}

	public String getPdeUnit() {
        return pdeUnit;
    }

    public void setPdeUnit(String pdeUnit) {
        this.pdeUnit = pdeUnit == null ? null : pdeUnit.trim();
    }

	public Date getPdeCrteTime() {
		return pdeCrteTime;
	}

	public void setPdeCrteTime(Date pdeCrteTime) {
		this.pdeCrteTime = pdeCrteTime;
	}

	public Date getPdeUpdtTime() {
		return pdeUpdtTime;
	}

	public void setPdeUpdtTime(Date pdeUpdtTime) {
		this.pdeUpdtTime = pdeUpdtTime;
	}
    
}