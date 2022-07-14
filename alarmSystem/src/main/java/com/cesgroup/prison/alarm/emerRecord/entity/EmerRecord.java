package com.cesgroup.prison.alarm.emerRecord.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @program: 01程序
 * @description:
 * @author: Mr.li
 * @create: 2019-11-18 14:29
 */
@Entity
@Table(name = "CDS_EMER_HANDLE_RECORD")
public class EmerRecord extends StringIDEntity {

    /**
     * 单位编号
     */
    private String cusNumber;
    /**
     * 报警记录ID
     */
    private String alarmRecordId;
    /**
     * 应急预案ID
     */
    private String preplanId;
    /**
     * 应急预案名称
     */
    private String preplanName;
    /**
     * 应急预案来源
     */
    private String preplanSource;
    /**
     * 应急预案备注
     */
    private String preplanRemarks;
    /**
     * 报警预案ID
     */
    private String alarmPlanId;
    /**
     * 报警预案名称
     */
    private String alarmPlanName;
    /**
     * 报警区域ID
     */
    private String alarmAreaId;
    /**
     * 报警区域名称
     */
    private String alarmAreaName;
    /**
     * 应急处置开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;
    /**
     * 应急处置结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;
    /**
     * 处置记录归档时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fileTime;
    /**
     * 经验总结
     */
    private String experience;
    /**
     * 应急记录状态(1:处置中; 2:处置完成; 3:已归档)
     */
    private String recordStatus;
    /**
     * 数据状态(0:激活; 1:删除;)
     */
    private String status;
    /**
     * 显示排序
     */
    private Long showOrder;
    /**
     * 创建人ID
     */
    private String createUserId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 最近更新人
     */
    private String updateUserId;
    /**
     * 最近更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 应急处置记录的处置步骤列表
     */
    @Transient
    private List<EmerRecordStep> handleStepList;
    /**
     * 人员登记表附件列表
     */
    @Transient
    private List<AffixEntity> rydjbAffixList;

    @Override
    @Id
    @Column(length = 32, nullable = false)
    public String getId() {
        return id;
    }

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getAlarmRecordId() {
        return alarmRecordId;
    }

    public void setAlarmRecordId(String alarmRecordId) {
        this.alarmRecordId = alarmRecordId;
    }

    public String getPreplanId() {
        return preplanId;
    }

    public void setPreplanId(String preplanId) {
        this.preplanId = preplanId;
    }

    public String getPreplanName() {
        return preplanName;
    }

    public void setPreplanName(String preplanName) {
        this.preplanName = preplanName;
    }

    public String getPreplanSource() {
        return preplanSource;
    }

    public void setPreplanSource(String preplanSource) {
        this.preplanSource = preplanSource;
    }

    public String getPreplanRemarks() {
        return preplanRemarks;
    }

    public void setPreplanRemarks(String preplanRemarks) {
        this.preplanRemarks = preplanRemarks;
    }

    public String getAlarmPlanId() {
        return alarmPlanId;
    }

    public void setAlarmPlanId(String alarmPlanId) {
        this.alarmPlanId = alarmPlanId;
    }

    public String getAlarmPlanName() {
        return alarmPlanName;
    }

    public void setAlarmPlanName(String alarmPlanName) {
        this.alarmPlanName = alarmPlanName;
    }

    public String getAlarmAreaId() {
        return alarmAreaId;
    }

    public void setAlarmAreaId(String alarmAreaId) {
        this.alarmAreaId = alarmAreaId;
    }

    public String getAlarmAreaName() {
        return alarmAreaName;
    }

    public void setAlarmAreaName(String alarmAreaName) {
        this.alarmAreaName = alarmAreaName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getFileTime() {
        return fileTime;
    }

    public void setFileTime(Date fileTime) {
        this.fileTime = fileTime;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getRecordStatus() {
        return recordStatus;
    }

    public void setRecordStatus(String recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getShowOrder() {
        return showOrder;
    }

    public void setShowOrder(Long showOrder) {
        this.showOrder = showOrder;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<EmerRecordStep> getHandleStepList() {
        return handleStepList;
    }

    public void setHandleStepList(List<EmerRecordStep> handleStepList) {
        this.handleStepList = handleStepList;
    }

    public List<AffixEntity> getRydjbAffixList() {
        return rydjbAffixList;
    }

    public void setRydjbAffixList(List<AffixEntity> rydjbAffixList) {
        this.rydjbAffixList = rydjbAffixList;
    }

}