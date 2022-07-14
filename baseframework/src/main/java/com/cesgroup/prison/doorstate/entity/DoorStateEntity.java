package com.cesgroup.prison.doorstate.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 * @ Author     ：zhouzc.
 * @ Date       ：Created in 15:32 2019/5/22
 * @ Description：门禁状态
 */
@Entity
@Table(name = "CDS_DOOR_STATE_DTLS")
public class DoorStateEntity extends StringIDEntity {

    private static final long serialVersionUID = 1L;

    //监狱id
    private String dsdCusNumber;
    //门禁编号
    private String dsdDoorId;
    //门禁名称
    private String dsdDoorName;
    //门禁所属房间编号
    private String dsdRoomId;
    //门禁所属房间名称
    private String dsdRoomName;
    //门禁所属部门编号
    private String dsdDeptId;
    //门禁所属部门名称
    private String dsdDeptName;
    //门禁状态:0开1关2超时3戒严4离线5其它
    private String dsdDoorState;
    //更新人id
    private String dsdUpdtUserId;
    //更新时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date dsdUpdtTime;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDsdCusNumber() {
        return dsdCusNumber;
    }

    public void setDsdCusNumber(String dsdCusNumber) {
        this.dsdCusNumber = dsdCusNumber;
    }

    public String getDsdDoorId() {
        return dsdDoorId;
    }

    public void setDsdDoorId(String dsdDoorId) {
        this.dsdDoorId = dsdDoorId;
    }

    public String getDsdDoorName() {
        return dsdDoorName;
    }

    public void setDsdDoorName(String dsdDoorName) {
        this.dsdDoorName = dsdDoorName;
    }

    public String getDsdRoomId() {
        return dsdRoomId;
    }

    public void setDsdRoomId(String dsdRoomId) {
        this.dsdRoomId = dsdRoomId;
    }

    public String getDsdDoomName() {
        return dsdRoomName;
    }

    public void setDsdDoomName(String dsdDoomName) {
        this.dsdRoomName = dsdDoomName;
    }

    public String getDsdDeptId() {
        return dsdDeptId;
    }

    public void setDsdDeptId(String dsdDeptId) {
        this.dsdDeptId = dsdDeptId;
    }

    public String getDsdDeptName() {
        return dsdDeptName;
    }

    public void setDsdDeptName(String dsdDeptName) {
        this.dsdDeptName = dsdDeptName;
    }

    public String getDsdDoorState() {
        return dsdDoorState;
    }

    public void setDsdDoorState(String dsdDoorState) {
        this.dsdDoorState = dsdDoorState;
    }

    public String getDsdUpdtUserId() {
        return dsdUpdtUserId;
    }

    public void setDsdUpdtUserId(String dsdUpdtUserId) {
        this.dsdUpdtUserId = dsdUpdtUserId;
    }

    public Date getDsdUpdtTime() {
        return dsdUpdtTime;
    }

    public void setDsdUpdtTime(Date dsdUpdtTime) {
        this.dsdUpdtTime = dsdUpdtTime;
    }
}
