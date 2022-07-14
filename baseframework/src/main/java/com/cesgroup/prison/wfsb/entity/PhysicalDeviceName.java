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
* @ClassName：PhysicalDeviceName   
* @Description：   物防设备名称
* @author：zhengke   
* @date：2017-12-13 19:57   
* @version        
*/
@Entity
@Table(name="CDS_PHYSICAL_DEVICE_NAME")
public class PhysicalDeviceName extends StringIDEntity{
    private String pdnCusNumber;

    private String pdnDeviceIdnty;

    private String pdnDeviceName;

    private String pdnRemark;

    private String pdnCrteUserId;
    @NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date pdnCrteTime;

    private String pdnUpdtUserId;
    @NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date pdnUpdtTime;

    public String getPdnCusNumber() {
        return pdnCusNumber;
    }

    public void setPdnCusNumber(String pdnCusNumber) {
        this.pdnCusNumber = pdnCusNumber == null ? null : pdnCusNumber.trim();
    }

    public String getPdnDeviceIdnty() {
        return pdnDeviceIdnty;
    }

    public void setPdnDeviceIdnty(String pdnDeviceIdnty) {
        this.pdnDeviceIdnty = pdnDeviceIdnty == null ? null : pdnDeviceIdnty.trim();
    }

    public String getPdnDeviceName() {
        return pdnDeviceName;
    }

    public void setPdnDeviceName(String pdnDeviceName) {
        this.pdnDeviceName = pdnDeviceName == null ? null : pdnDeviceName.trim();
    }

    public String getPdnRemark() {
        return pdnRemark;
    }

    public void setPdnRemark(String pdnRemark) {
        this.pdnRemark = pdnRemark == null ? null : pdnRemark.trim();
    }

	public String getPdnCrteUserId() {
		return pdnCrteUserId;
	}

	public void setPdnCrteUserId(String pdnCrteUserId) {
		this.pdnCrteUserId = pdnCrteUserId;
	}

	public String getPdnUpdtUserId() {
		return pdnUpdtUserId;
	}

	public void setPdnUpdtUserId(String pdnUpdtUserId) {
		this.pdnUpdtUserId = pdnUpdtUserId;
	}

	public Date getPdnCrteTime() {
		return pdnCrteTime;
	}

	public void setPdnCrteTime(Date pdnCrteTime) {
		this.pdnCrteTime = pdnCrteTime;
	}

	public Date getPdnUpdtTime() {
		return pdnUpdtTime;
	}

	public void setPdnUpdtTime(Date pdnUpdtTime) {
		this.pdnUpdtTime = pdnUpdtTime;
	}
	
}