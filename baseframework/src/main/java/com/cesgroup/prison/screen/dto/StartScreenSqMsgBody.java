package com.cesgroup.prison.screen.dto;

import com.cesgroup.frm.net.netty.bean.MsgBody;

import java.util.List;

/**
 * @ClassName StartScreenSqMsgBody
 * @Description 上墙的返回数据
 * @Author lh
 * @Date 2020/7/7 10:56
 **/
public class StartScreenSqMsgBody extends MsgBody {
    private String nTvWallId;//电视墙id
    private  List<WindowMsg> windowMsgs;//窗口消息

    public String getnTvWallId() {
        return nTvWallId;
    }

    public void setnTvWallId(String nTvWallId) {
        this.nTvWallId = nTvWallId;
    }

    public List<WindowMsg> getWindowMsgs() {
        return windowMsgs;
    }

    public void setWindowMsgs(List<WindowMsg> windowMsgs) {
        this.windowMsgs = windowMsgs;
    }
}
