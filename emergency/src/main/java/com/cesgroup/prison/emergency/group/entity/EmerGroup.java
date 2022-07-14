package com.cesgroup.prison.emergency.group.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.prison.emergency.member.entity.EmerMember;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 应急工作组（梯队）表实体类
 */
@Entity
@Table(name = "T_EMER_GROUP")
public class EmerGroup extends StringIDEntity {
    /**
     * 监狱编号
     */
    private String cusNumber;
    /**
     * 应急预案编号
     */
    private String preplanId;
    /**
     * 工作组名称
     */
    private String name;
    /**
     * 通知内容
     */
    private String notice;
    /**
     * 获取成员方式(0:预定义; 1:即时获取; 2:预定义+即时获取;)
     */
    private String getMemberWay;
    /**
     * 数据来源（0:用户自定义; 1:系统接口同步）
     */
    private String source;
    /**
     * 数据状态（0:有效; 1:删除;）
     */
    private String status;
    /**c
     * 显示排序
     */
    private Long showOrder;
    /**
     * 创建人
     */
    private String createUserId;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 修改人
     */
    private String updateUserId;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;
    /**
     * 应急工作组关联的成员信息
     */
    @Transient
    private List<EmerMember> emerMemberList;

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

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }

    public String getGetMemberWay() {
        return getMemberWay;
    }

    public void setGetMemberWay(String getMemberWay) {
        this.getMemberWay = getMemberWay;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
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

    public List<EmerMember> getEmerMemberList() {
        return emerMemberList;
    }

    public void setEmerMemberList(List<EmerMember> emerMemberList) {
        this.emerMemberList = emerMemberList;
    }
}