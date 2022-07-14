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
* @ClassName：PoliceDevice   
* @Description：警用器材   
* @author：zhengke   
* @date：2017-12-13 19:57   
* @version        
*/
@Entity
@Table(name="CDS_POLICE_DEVICE")
public class PoliceDevice extends StringIDEntity {
    private String poeCusNumber;

    private String poeDeviceName;

    private String poeTypeIndc;

    private String poeDprtmntId;

    private String poeDprtmntName;

    private String poeModel;

    private String poeUnit;

    private Integer poeCount;

    private String poeAdministrator;

    private String poeFunctionDesc;

    private String poeSttsIndc;

    private String poeSttsDesc;

    private String poeSaveMehtod;

    private String poeCrteUserId;
    @NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date poeCrteTime;

    private String poeUpdtUserId;
    @NotNull
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date poeUpdtTime;

    public String getPoeDeviceName() {
        return poeDeviceName;
    }

    public void setPoeDeviceName(String poeDeviceName) {
        this.poeDeviceName = poeDeviceName == null ? null : poeDeviceName.trim();
    }

    public String getPoeDprtmntName() {
        return poeDprtmntName;
    }

    public void setPoeDprtmntName(String poeDprtmntName) {
        this.poeDprtmntName = poeDprtmntName == null ? null : poeDprtmntName.trim();
    }

    public String getPoeModel() {
        return poeModel;
    }

    public void setPoeModel(String poeModel) {
        this.poeModel = poeModel == null ? null : poeModel.trim();
    }

    public String getPoeUnit() {
        return poeUnit;
    }

    public void setPoeUnit(String poeUnit) {
        this.poeUnit = poeUnit == null ? null : poeUnit.trim();
    }

    public String getPoeAdministrator() {
        return poeAdministrator;
    }

    public void setPoeAdministrator(String poeAdministrator) {
        this.poeAdministrator = poeAdministrator == null ? null : poeAdministrator.trim();
    }

    public String getPoeFunctionDesc() {
        return poeFunctionDesc;
    }

    public void setPoeFunctionDesc(String poeFunctionDesc) {
        this.poeFunctionDesc = poeFunctionDesc == null ? null : poeFunctionDesc.trim();
    }

    public String getPoeTypeIndc() {
		return poeTypeIndc;
	}

	public void setPoeTypeIndc(String poeTypeIndc) {
		this.poeTypeIndc = poeTypeIndc;
	}

	public String getPoeDprtmntId() {
		return poeDprtmntId;
	}

	public void setPoeDprtmntId(String poeDprtmntId) {
		this.poeDprtmntId = poeDprtmntId;
	}

	public void setPoeCount(int poeCount) {
		this.poeCount = poeCount;
	}

	public String getPoeSttsIndc() {
		return poeSttsIndc;
	}

	public void setPoeSttsIndc(String poeSttsIndc) {
		this.poeSttsIndc = poeSttsIndc;
	}

	public String getPoeSttsDesc() {
        return poeSttsDesc;
    }

    public void setPoeSttsDesc(String poeSttsDesc) {
        this.poeSttsDesc = poeSttsDesc == null ? null : poeSttsDesc.trim();
    }

    public String getPoeSaveMehtod() {
        return poeSaveMehtod;
    }

    public void setPoeSaveMehtod(String poeSaveMehtod) {
        this.poeSaveMehtod = poeSaveMehtod == null ? null : poeSaveMehtod.trim();
    }

	public String getPoeCrteUserId() {
		return poeCrteUserId;
	}

	public void setPoeCrteUserId(String poeCrteUserId) {
		this.poeCrteUserId = poeCrteUserId;
	}

	public String getPoeUpdtUserId() {
		return poeUpdtUserId;
	}

	public void setPoeUpdtUserId(String poeUpdtUserId) {
		this.poeUpdtUserId = poeUpdtUserId;
	}

	public Date getPoeCrteTime() {
		return poeCrteTime;
	}

	public void setPoeCrteTime(Date poeCrteTime) {
		this.poeCrteTime = poeCrteTime;
	}

	public Date getPoeUpdtTime() {
		return poeUpdtTime;
	}

	public void setPoeUpdtTime(Date poeUpdtTime) {
		this.poeUpdtTime = poeUpdtTime;
	}

	public String getPoeCusNumber() {
		return poeCusNumber;
	}

	public void setPoeCusNumber(String poeCusNumber) {
		this.poeCusNumber = poeCusNumber;
	}

	public Integer getPoeCount() {
		return poeCount;
	}

	public void setPoeCount(Integer poeCount) {
		this.poeCount = poeCount;
	}
	
}