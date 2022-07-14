package com.cesgroup.prison.screen.dto;

import java.util.List;

/**
 * @ClassName WindowMsg
 * @Description TODO
 * @Author lh
 * @Date 2020/7/7 11:00
 **/
public class WindowMsg {
    private String nWndId;//窗口id
    private List<String> cameraList;//摄像头id

    public String getnWndId() {
        return nWndId;
    }

    public void setnWndId(String nWndId) {
        this.nWndId = nWndId;
    }

    public List<String> getCameraList() {
        return cameraList;
    }

    public void setCameraList(List<String> cameraList) {
        this.cameraList = cameraList;
    }
}
