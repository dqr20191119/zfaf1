package com.cesgroup.prison.realTimeTalk.service;

import java.util.List;

import com.cesgroup.framework.bean.AjaxMessage;

public interface RealTimeTalkService {

	/**
	* @methodName: startTalk
	* @Description: 对讲呼叫
	* @param pcIp: 本机ip
	* @param cusNumber 监狱号
	* @param idnty 呼叫分机编号 
	* @throws  
	*/
	AjaxMessage startTalk(String pcIp, String cusNumber, List<String> idnty);
}
