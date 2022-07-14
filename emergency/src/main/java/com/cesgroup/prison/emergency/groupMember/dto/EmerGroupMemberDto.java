package com.cesgroup.prison.emergency.groupMember.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 应急工作组与工作组成员关联管理表实体类
 */
public class EmerGroupMemberDto implements Serializable {
    /**
     * 编号
     */
    private String id;
    /**
     * 监狱/单位编号
     */
    private String cusNumber;
    /**
     * 应急工作组编号
     */
    private String groupId;
    /**
     * 应急工作组名称
     */
    private String groupName;
    /**
     * 应急工作组成员编号
     */
    private String memberId;
    /**
     * 应急工作组成员警号
     */
    private String memberJobNo;
    /**
     * 应急工作组成员名称
     */
    private String memberName;
    /**
     * 呼叫号码
     */
    private String callNo;
    /**
     * 单位编码
     */
    private String unitCode;
    /**
     * 单位名称
     */
    private String unitName;
    /**
     * 部门编码
     */
    private String deptCode;
    /**
     * 部门名称
     */
    private String deptName;
    /**
     * 数据来源
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
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

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

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberJobNo() {
        return memberJobNo;
    }

    public void setMemberJobNo(String memberJobNo) {
        this.memberJobNo = memberJobNo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getCallNo() {
        return callNo;
    }

    public void setCallNo(String callNo) {
        this.callNo = callNo;
    }

    public String getUnitCode() {
        return unitCode;
    }

    public void setUnitCode(String unitCode) {
        this.unitCode = unitCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}