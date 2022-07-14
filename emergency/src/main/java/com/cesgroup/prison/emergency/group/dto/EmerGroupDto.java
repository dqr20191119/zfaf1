package com.cesgroup.prison.emergency.group.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 应急工作组（梯队）DTO
 */
public class EmerGroupDto implements Serializable {
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
     * 工作组名称
     */
    private String name;
    /**
     * 通知内容
     */
    private String notice;
    /**
     * 数据来源（0:用户自定义; 1:系统接口同步）
     */
    private String source;
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

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}