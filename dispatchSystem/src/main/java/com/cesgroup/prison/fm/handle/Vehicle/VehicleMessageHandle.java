package com.cesgroup.prison.fm.handle.Vehicle;

import com.cesgroup.fm.MsgDispatcher;
import org.springframework.stereotype.Service;

/**
 * 接收前置机车辆信息消息处理类
 * Created by dingdian on 2018/8/28
 */
@Service
public class VehicleMessageHandle {

    private MsgDispatcher msgDispatcher;
    public VehicleMessageHandle(){
        msgDispatcher=new MsgDispatcher();
    }

    public void handle(String message,String cusNumber) {
        msgDispatcher.dispatch(cusNumber,message);
    }
}
