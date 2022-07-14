package com.cesgroup.prison.doorstate.bean;

import java.io.Serializable;

/**
 * @ Author     ：zhouzc.
 * @ Date       ：Created in 14:22 2019/5/22
 * @ Description：门禁状态Bean
 */
public class DoorStateBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //门禁编号
    private String doorID;

    //门禁名称
    private String doorName;

    //门禁所属房间编号
    private String roomId;

    //门禁所属部门编号
    private String dept;

    //门禁状态:0开1关2超时3戒严4离线5其它
    private String state;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDoorID() {
        return doorID;
    }

    public void setDoorID(String doorID) {
        this.doorID = doorID;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
