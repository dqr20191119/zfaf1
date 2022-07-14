package com.cesgroup.prison.emergency.step.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.prison.emergency.group.entity.EmerGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 应急预案处理步骤表实体类
 */
@Entity
@Table(name = "T_EMER_STEP")
public class EmerStep extends StringIDEntity {
    /**
     * 监狱/单位编号
     */
    private String cusNumber;
    /**
     * 应急预案编号
     */
    private String preplanId;
    /**
     * 处置步骤名称
     */
    private String name;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 数据状态
     */
    private String status;
    /**
     * 排序
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
     * 更新人
     */
    private String updateUserId;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 应急处置步骤关联的工作组
     */
    @Transient
    private List<EmerGroup> emerGroupList;

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

    public String getPreplanId() {
        return preplanId;
    }

    public void setPreplanId(String preplanId) {
        this.preplanId = preplanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
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

    public List<EmerGroup> getEmerGroupList() {
        return emerGroupList;
    }

    public void setEmerGroupList(List<EmerGroup> emerGroupList) {
        this.emerGroupList = emerGroupList;
    }
}