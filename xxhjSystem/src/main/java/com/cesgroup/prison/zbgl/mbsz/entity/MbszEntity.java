package com.cesgroup.prison.zbgl.mbsz.entity;


import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.cesgroup.prison.zbgl.mbsz.dto.DutyPoliceDto;
import org.springframework.format.annotation.DateTimeFormat;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.prison.zbgl.mbxq.entity.MbxqEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

/*值班模板表*/
@Table(name = "cds_duty_mode")
public class MbszEntity extends StringIDEntity {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String cdmCusNumber;                     //监狱编号
	
	private String cdmCategoryId;                  //类别Id	
	
	private String cdmModeName;                    //模板名称
	
	private String cdmOrderCount;                  //班次数量
	
	private String cdmPeriod;                     //周期
	
	private Date cdmUpdtTime;                     //更新时间
	
	private String cdmUpdtUserId;                //更新者ID
	
	private String cdmSfqy;//0.停用 1.启用
	
	@Column(updatable = false)
	private Date cdmCrteTime;						// 创建时间
	
	@Column(updatable = false)						
	private String cdmCrteUserId;                   // 创建人
		
	@Transient
	private String cdmOrderData;                   //班次岗位信息
	
	@Transient
	private String dorDutyOrderName;                //班次名称
	
	@Transient
	private String dorStartTime;                   //班次开始时间
	
	@Transient
	private String dorEndTime;                      //班次结束时间
	
	@Transient
	private String dcaCategoryName;                //类别名称
	
	@Transient
	private String cdjJobName;                    //岗位名称
	
	@Transient
	private String allPoliceList ;              //值班人员信息
	
	@Transient
	private String param ;
	@Transient
	private String continueDutyMessage ;                      //

	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd")             
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dmdStartDate;                    //编排开始时间
	
	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date dmdEndDate;                     //编排结束时间
	@Transient
	private List<MbxqEntity> mbxqList;//模板详情
	@Transient
	private List<Map<String,Object>> orderPolices;
	@Transient
	private List<DutyPoliceDto>  dutyPoliceDtos;

	@Transient
      private List<Map<String,Object>> noZhzMessage;


    @Transient
    private String noDuty ;


    public List<Map<String, Object>> getNoZhzMessage() {
        return noZhzMessage;
    }

    public void setNoZhzMessage(List<Map<String, Object>> noZhzMessage) {
        this.noZhzMessage = noZhzMessage;
    }

    public String getNoDuty() {
        return noDuty;
    }

    public void setNoDuty(String noDuty) {
        this.noDuty = noDuty;
    }

    public String getContinueDutyMessage() {
        return continueDutyMessage;
    }

    public void setContinueDutyMessage(String continueDutyMessage) {
        this.continueDutyMessage = continueDutyMessage;
    }

    public List<DutyPoliceDto> getDutyPoliceDtos() {
        return dutyPoliceDtos;
    }

    public void setDutyPoliceDtos(List<DutyPoliceDto> dutyPoliceDtos) {
        this.dutyPoliceDtos = dutyPoliceDtos;
    }

    public List<Map<String, Object>> getOrderPolices() {
		return orderPolices;
	}

	public void setOrderPolices(List<Map<String, Object>> orderPolices) {
		this.orderPolices = orderPolices;
	}

	public List<MbxqEntity> getMbxqList() {
		return mbxqList;
	}

	public void setMbxqList(List<MbxqEntity> mbxqList) {
		this.mbxqList = mbxqList;
	}

	public String getCdmCusNumber() {
		return cdmCusNumber;
	}

	public void setCdmCusNumber(String cdmCusNumber) {
		this.cdmCusNumber = cdmCusNumber;
	}

	public String getCdmCategoryId() {
		return cdmCategoryId;
	}

	public void setCdmCategoryId(String cdmCategoryId) {
		this.cdmCategoryId = cdmCategoryId;
	}

	public String getCdmModeName() {
		return cdmModeName;
	}

	public void setCdmModeName(String cdmModeName) {
		this.cdmModeName = cdmModeName;
	}

	public String getCdmOrderCount() {
		return cdmOrderCount;
	}

	public void setCdmOrderCount(String cdmOrderCount) {
		this.cdmOrderCount = cdmOrderCount;
	}

	public String getCdmPeriod() {
		return cdmPeriod;
	}

	public void setCdmPeriod(String cdmPeriod) {
		this.cdmPeriod = cdmPeriod;
	}

	public Date getCdmUpdtTime() {
		return cdmUpdtTime;
	}

	public void setCdmUpdtTime(Date cdmUpdtTime) {
		this.cdmUpdtTime = cdmUpdtTime;
	}

	public String getCdmUpdtUserId() {
		return cdmUpdtUserId;
	}

	public void setCdmUpdtUserId(String cdmUpdtUserId) {
		this.cdmUpdtUserId = cdmUpdtUserId;
	}

	public Date getCdmCrteTime() {
		return cdmCrteTime;
	}

	public void setCdmCrteTime(Date cdmCrteTime) {
		this.cdmCrteTime = cdmCrteTime;
	}

	public String getCdmCrteUserId() {
		return cdmCrteUserId;
	}

	public void setCdmCrteUserId(String cdmCrteUserId) {
		this.cdmCrteUserId = cdmCrteUserId;
	}

	public String getCdmOrderData() {
		return cdmOrderData;
	}

	public void setCdmOrderData(String cdmOrderData) {
		this.cdmOrderData = cdmOrderData;
	}

	public String getDorDutyOrderName() {
		return dorDutyOrderName;
	}

	public void setDorDutyOrderName(String dorDutyOrderName) {
		this.dorDutyOrderName = dorDutyOrderName;
	}

	public String getDorStartTime() {
		return dorStartTime;
	}

	public void setDorStartTime(String dorStartTime) {
		this.dorStartTime = dorStartTime;
	}

	public String getDorEndTime() {
		return dorEndTime;
	}

	public void setDorEndTime(String dorEndTime) {
		this.dorEndTime = dorEndTime;
	}

	public String getDcaCategoryName() {
		return dcaCategoryName;
	}

	public void setDcaCategoryName(String dcaCategoryName) {
		this.dcaCategoryName = dcaCategoryName;
	}

	public String getCdjJobName() {
		return cdjJobName;
	}

	public void setCdjJobName(String cdjJobName) {
		this.cdjJobName = cdjJobName;
	}

	public String getAllPoliceList() {
		return allPoliceList;
	}

	public void setAllPoliceList(String allPoliceList) {
		this.allPoliceList = allPoliceList;
	}
	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}
	public Date getDmdStartDate() {
		return dmdStartDate;
	}

	public void setDmdStartDate(Date dmdStartDate) {
		this.dmdStartDate = dmdStartDate;
	}

	public Date getDmdEndDate() {
		return dmdEndDate;
	}

	public void setDmdEndDate(Date dmdEndDate) {
		this.dmdEndDate = dmdEndDate;
	}

	public String getCdmSfqy() {
		return cdmSfqy;
	}

	public void setCdmSfqy(String cdmSfqy) {
		this.cdmSfqy = cdmSfqy;
	}
	
}
