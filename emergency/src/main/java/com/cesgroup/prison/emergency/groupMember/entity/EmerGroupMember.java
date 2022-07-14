package com.cesgroup.prison.emergency.groupMember.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 应急工作组与工作组成员关联管理表实体类
 */
@Entity
@Table(name = "T_EMER_GROUP_MEMBER")
public class EmerGroupMember extends StringIDEntity {
    /**
     * 监狱/单位编号
     */
    private String cusNumber;
    /**
     * 应急工作组编号
     */
    private String groupId;
    /**
     * 应急工作组成员编号
     */
    private String memberId;
    /**
     * 数据来源（0:用户自定义; 1:系统接口同步）
     */
    private String source;
    /**
     * 工作组成员角色(1:组长: 2:副组长: 3:成员.)
     */
    private Integer memberRole;
    /**
     * 工作组成员角色任务
     */
    private String memberRoleTask;
    /**
     * 数据状态
     */
    private String status;
    /**
     * 排序
     */
    private Long showOrder;
    /**
     * 更新人ID
     */
    private String updateUserId;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Integer getMemberRole() {
        return memberRole;
    }

    public void setMemberRole(Integer memberRole) {
        this.memberRole = memberRole;
    }

    public String getMemberRoleTask() {
        return memberRoleTask;
    }

    public void setMemberRoleTask(String memberRoleTask) {
        this.memberRoleTask = memberRoleTask;
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
}