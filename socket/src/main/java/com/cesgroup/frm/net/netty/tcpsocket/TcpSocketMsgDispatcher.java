package com.cesgroup.frm.net.netty.tcpsocket;

import java.lang.reflect.Constructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.frm.net.netty.bean.MsgHeader;
import com.cesgroup.frm.net.netty.bean.NettyConfig;
import com.cesgroup.frm.net.netty.service.IMsgChannel;
import com.cesgroup.frm.net.netty.service.IService;
import com.cesgroup.frm.net.netty.service.impl.MsgChannelManager;
import com.cesgroup.frm.net.netty.service.impl.MsgComsumerGroup;
import com.cesgroup.frm.net.netty.service.impl.MsgDispatcher;
import com.cesgroup.frm.net.service.LoginService;
/**
 * cesgroup
 * Socket 消息分发器
 * @author lihh
 */
public class TcpSocketMsgDispatcher extends MsgDispatcher {
	//线程组
	private MsgComsumerGroup msgComsumerGroup = null;
	private NettyConfig nettyConfig = null;
	/** 连接来源 */
	private String connSource;
	private final Logger logger = LoggerFactory.getLogger(TcpSocketMsgDispatcher.class);
	@Override
	public void init(NettyConfig nettyConfig, MsgChannelManager msgChannelManager, MsgComsumerGroup msgComsumerGroup) {
		if (nettyConfig.isMsgComsumerPool()) {
			this.msgComsumerGroup = msgComsumerGroup;
		} else {
			this.msgComsumerGroup = new MsgComsumerGroup(nettyConfig.getMsgComsumerNum());
		}
		this.nettyConfig = nettyConfig;
	}

	@Override
	public void dispatch(IMsgChannel msgChannel, Object msg) {
		try {
			Runnable runnable = createMsgRunnable(msgChannel, msg);
			if (null != runnable) {
				msgComsumerGroup.execute(runnable);
			}
        } catch (Throwable ex) {
            logger.error("TcpSocketMsgDispatcher.dispatch 消息分派发生异常 msg="+msg,ex);
            try {
                msgChannel.write(getErrMsg(msg, "系统分派请求时出现异常"));
            }catch (Throwable t){
                logger.error("写消息发生异常",t);
            }
        }
	}
	/**
	 * 创建消息处理Runnabl
	 * @param msgChannel
	 * @param msg
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Runnable createMsgRunnable(IMsgChannel msgChannel,Object msg) {
		try {
			TcpSocketMsgHandlerRunnable msgHandlerRunnable = null;
			//判断是否登录消息，是调用loginService，否则调用businessSevice
			JSONObject jsonMsg = JSONObject.parseObject(msg.toString());
			MsgHeader msgHeader = (MsgHeader)JSONObject.parseObject(jsonMsg.get("header").toString(), MsgHeader.class);
			String msgType=msgHeader.getMsgType();
			if(msgType!=null){
				if (msgType.equalsIgnoreCase("Login01")) {
					LoginService loginService=new LoginService();
					loginService.login(msgChannel, msg);
				} else {
					//构建执行者
		            Class clazz = Class.forName(nettyConfig.getMsgServicePackage());
		            Constructor cons = clazz.getConstructor();
		            IService service = (IService)cons.newInstance();
					msgHandlerRunnable = new TcpSocketMsgHandlerRunnable(msgChannel, service, jsonMsg);
				}
			}else{
				logger.error("msgType is null msg="+msg);
			}
			return msgHandlerRunnable;
		} catch (Throwable ex){
            throw new RuntimeException("createMsgRunnable异常",ex);
        }
	}

	@Override
	public String getErrMsg(Object reqMsg, String msg) {
		return null;
	}
	

	@Override
	public void close() {
		if (!nettyConfig.isMsgComsumerPool()) {
			msgComsumerGroup.shutdown();
		}
		MsgChannelManager.getMsgChannelManager().removeMsgChannel(connSource);
		//删除连接
		//ServerFepMsgThread.removeChannel(connSource);
	}


}
