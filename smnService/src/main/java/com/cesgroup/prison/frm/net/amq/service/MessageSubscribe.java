package com.cesgroup.prison.frm.net.amq.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.cache.IRefreshCache;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.prison.sql.service.QueryService;

/**
 * 消息订阅初始化
 * 
 */
@Service
public class MessageSubscribe implements IRefreshCache {
	
	private final static Logger logger = LoggerFactory.getLogger(MessageSubscribe.class);
	@Resource
	private QueryService queryService;
	
	/**
	 * 消息订阅用户Map key：msgIdnty  用户的List<Map<String,String>>>  key:MSD_USER_ID、MSD_CUS_NUMBER
	 */
	private final static String msgIdntyUserMap="msg_id_user";
	
    @PostConstruct
	public void init(){
		RedisCache.deleteHash(msgIdntyUserMap);
		logger.debug("开始初始化消息订阅");
		List<Object> parasList=new ArrayList<Object>();
		List<Map<String,Object>> rtnList=queryService.query("cds_query_message_subscribe", parasList);
		String msgIdntyLast="";
		JSONArray jsonArray=new JSONArray();
		int num=0;
		if(rtnList!=null&&rtnList.size()>0){
			for(Map<String,Object> msgMap:rtnList){
				num++;
				JSONObject user=new JSONObject();
				String msgIdnty=msgMap.get("MSD_MSG_IDNTY").toString();
				String cusNumber=msgMap.get("MSD_CUS_NUMBER").toString();
				String userId=msgMap.get("MSD_USER_ID").toString();
				user.put("cusNumber", cusNumber);
				user.put("userId", userId);
				user.put("msgIdnty", msgIdnty);
				if(!isNull(msgIdntyLast)&&(!isNull(msgIdnty)&&!msgIdnty.equals(msgIdntyLast))){
					RedisCache.putHash(msgIdntyUserMap, msgIdntyLast, jsonArray.toJSONString());
					jsonArray=new JSONArray();
				}
				jsonArray.add(user);
				msgIdntyLast=msgIdnty;
				if(num==rtnList.size()){
					RedisCache.putHash(msgIdntyUserMap, msgIdntyLast, jsonArray.toJSONString());
				}
			}
		}
	}
	
	public JSONArray getUserId(String msgIdnty){
		String jsonStr=(String)RedisCache.getObject(msgIdntyUserMap, msgIdnty);
		JSONArray jsonArray=JSONArray.parseArray(jsonStr);
		return jsonArray;
	}

	@Override
	public Boolean refresh() throws Exception {
		init();
		return true;
	}

	@Override
	public Boolean refresh(String fileName) throws Exception {
		return refresh();
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
}
