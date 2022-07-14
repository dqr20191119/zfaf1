package com.cesgroup.prison.emergency.step.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 应急处置步骤DTO
 */
public class EmerStepDto implements Serializable {
    /**
     * 编号
     */
    private String id;
    /**
     * 监狱编号
     */
    private String cusNumber;
    /**
     * 应急预案编号
     */
    private String preplanId;
    /**
     * 应急预案名称
     */
    private String preplanName;
    /**
     * 处置步骤名称
     */
    private String name;
    /**
     * 关联工作组名称
     */
    private String groupName;
    /**
     * 描述
     */
    private String remarks;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPreplanName() {
        return preplanName;
    }

    public void setPreplanName(String preplanName) {
        this.preplanName = preplanName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}