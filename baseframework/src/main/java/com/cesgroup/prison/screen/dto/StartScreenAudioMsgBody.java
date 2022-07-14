package com.cesgroup.prison.screen.dto;

import com.cesgroup.frm.net.netty.bean.MsgBody;

/**
 * @ClassName StartScreenAudioMsgBody
 * @Description 电视墙预案切换返回消息
 * @Author lh
 * @Date 2020/7/7 9:35
 **/
public class StartScreenAudioMsgBody extends MsgBody {
    private String  TVWallId;//电视墙id
    private String TaskId;
    private String ScreenIndc;//电视墙预案id

    public String getTVWallId() {
        return TVWallId;
    }

    public void setTVWallId(String TVWallId) {
        this.TVWallId = TVWallId;
    }

    public String getTaskId() {
        return TaskId;
    }

    public void setTaskId(String taskId) {
        TaskId = taskId;
    }

    public String getScreenIndc() {
        return ScreenIndc;
    }

    public void setScreenIndc(String screenIndc) {
        ScreenIndc = screenIndc;
    }
}
