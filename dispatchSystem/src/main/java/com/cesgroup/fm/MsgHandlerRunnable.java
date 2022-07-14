package com.cesgroup.fm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.commons.SpringContextUtils;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.process.service.InitServiceMaping;

/**
 * cesgroup
 * 消息统一处理类
 * @author ziyang
 *
 */
public class MsgHandlerRunnable implements Runnable {
	private final Logger logger = LoggerFactory.getLogger(MsgHandlerRunnable.class);
	private String msg = null;
	private String cusNumber=null;
	
	public MsgHandlerRunnable(String cusNumber,String msg) {
		this.cusNumber=cusNumber;
		this.msg = msg;
	}
	
	/**
    * 判断是否为空字符串或者为空。
    * @param param：需要判断的字符串。
    * @return false：非空返回  true:空字符串或者null时返回。
    */
   public static boolean isNull(String param) {
   	if (null == param) {
           return true;
       } else if (0 == param.trim().length()) {
           return true;
       }
       return false;
   }  	
	
	@Override
	public void run() {
		try {
			JSONObject jSONObject = JSONObject.parseObject(msg);
			MsgHeader msgHeader = JSON.toJavaObject(JSONObject.parseObject(jSONObject.getString("header")), MsgHeader.class);
			String msgType = msgHeader.getMsgType();
			
			//变为懒加载后，手动初始化MsgServiceMap
			InitServiceMaping.init();
			
			logger.info("MsgHandlerRunnable run() msgType = " + msgType);
			String serviceName = MsgServiceMap.getInstance().getServiceName(msgType);
			logger.info("MsgHandlerRunnable run() serviceName = " + serviceName);
		
			
			if(!isNull(serviceName)){
				IMessageProcess messageProcess = (IMessageProcess) SpringContextUtils.getBean(serviceName);
				logger.info("MsgHandlerRunnable run() messageProcess = " + messageProcess);
				messageProcess.processMessage(cusNumber,jSONObject);
			}
		} catch (JSONException ex) {
			logger.error("JSON格式转换错误:" + ex.getMessage()+msg);
		} catch (NullPointerException ex) {
			logger.error("空对象异常：" + ex.getMessage());
		}catch(Exception e){
			logger.error("",e);
		}
	}
	
	

}
