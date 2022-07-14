package com.cesgroup.prison.callName.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.fm.util.MsgTypeConst;
import com.cesgroup.prison.zfxx.zfdm.dao.ZfdmMapper;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfdmEntity;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
/**
 * 湖南点名
 * **/
@Service
public class CallRollProcess implements IMessageProcess {

	private final Logger logger = LoggerFactory.getLogger(CallRollProcess.class);

	@Resource
	private ZfdmMapper zfdmMapper;

	@Override
	@Transactional
	public void processMessage(String cusNumber, JSONObject jsonObject) {
		//点名信息不做消息推送了，由前置机插数据库 20190616
		if(true) {
			return;
		}
		
		// 将普通的JSONObject对象转化为谷歌JsonObject
    	JsonObject gsonObject = Util.fromJson(JSONObject.toJSONString(jsonObject), JsonObject.class);
    	
    	// 从gsonObject中取出header，并转化为MsgHeader实现
        MsgHeader msgHead = gsonObject != null ? Util.fromJson(gsonObject.get("header"), MsgHeader.class) : null;
        
        // 从msgHead中获取msgType消息类型
        String msgType = msgHead != null ? msgHead.getMsgType() : "";
        
        if(MsgTypeConst.CALL_ROLL_IN.equals(msgType)) {
            logger.info("点名收到消息：" + jsonObject.toJSONString());
            try {
            	
            	//从gsonObject中获取callRollBody
                JsonArray body = gsonObject.getAsJsonArray("callRollBody");
                if (body == null) {
                    return;
                }
                
                for (int i = 0; i < body.size(); i++) {
                    JsonObject dmJson = body.get(i).getAsJsonObject();
                    if (dmJson == null) {
                        continue;
                    }
                  
    				Map<String,String> dmMap=this.convertJsonToMap(dmJson);
    				if(dmMap!=null){
    					ZfdmEntity zfdmEntity=this.convertMapToZfdm(dmMap,msgHead.getCusNumber());
    					this.zfdmMapper.insert(zfdmEntity);
    				 	
    				}
  
                }

            	

            } catch (Exception e) {
            	logger.error("更新或插入点名数据发生异常，异常原因：" + e);
            }
        }
	}

	private Map<String,String> convertJsonToMap(JsonObject body) {
    	if(body==null){
    		return null;
		}
    	//人员编号
		Map<String,String> dmMap=new HashMap<String,String>();
		if(body.get("prisonerBH")!=null){
			dmMap.put("prisonerBH",body.get("prisonerBH").getAsString());
		}
		//人员名称
		if(body.get("prisonerName")!=null){
			dmMap.put("prisonerName",body.get("prisonerName").getAsString());
		}
		//房间编号
		if(body.get("cellNo")!=null){
			dmMap.put("cellNo",body.get("cellNo").getAsString());
		}
		//房间名称
		if(body.get("cellName")!=null){
			dmMap.put("cellName",body.get("cellName").getAsString());
		}
		//点名状态(0 为未点名或点名失败, 1 为点名成功)
		if(body.get("rollcallResult")!=null){
			dmMap.put("rollcallResult",body.get("rollcallResult").getAsString());
		}
		//所在分组
		if(body.get("adminGroup")!=null){
			dmMap.put("adminGroup",body.get("adminGroup").getAsString());
		}
		//监区 Id
		if(body.get("deptId")!=null){
			dmMap.put("deptId",body.get("deptId").getAsString());
		}
		//监区名称
		if(body.get("deptName")!=null){
			dmMap.put("deptName",body.get("deptName").getAsString());
		}
		if (dmMap.size()==0){
			return  null;
		}
		return dmMap;
    }

	private ZfdmEntity convertMapToZfdm(Map<String, String> dmMap, String cusNumber) {
		ZfdmEntity zfdm=new ZfdmEntity();
		
		zfdm.setCusNumber(cusNumber);
		zfdm.setCrteTime(new Date());
		zfdm.setPrisonerBH(dmMap.get("prisonerBH"));
		zfdm.setPrisonerName(dmMap.get("prisonerName"));
		zfdm.setCellNo(dmMap.get("cellNo"));
		zfdm.setCellName(dmMap.get("cellName"));
		zfdm.setRollcallResult(dmMap.get("rollcallResult"));
		zfdm.setAdminGroup(dmMap.get("adminGroup"));
		zfdm.setDeptId(dmMap.get("deptId"));
		zfdm.setDeptName(dmMap.get("deptName"));

		return  zfdm;
	}

}
