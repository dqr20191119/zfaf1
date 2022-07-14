package com.cesgroup.prison.common.service.impl;
 
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.dao.MsgsubscribeMapper;
import com.cesgroup.prison.common.entity.MsgsubscribeEntity;
import com.cesgroup.prison.common.service.MsgsubscribeService;
import com.cesgroup.prison.db.service.RedisCache;

@Service("msgsubscribeService")
public class MsgsubscribeServiceImpl extends BaseDaoService<MsgsubscribeEntity, String, MsgsubscribeMapper> implements MsgsubscribeService {
	
	// 消息订阅用户Map key：msgIdnty  用户的List<Map<String,String>>>  key:MSD_USER_ID、MSD_CUS_NUMBER
	private final String msgIdntyUserMap = "msg_id_user";
	
	@Resource
	private MsgsubscribeMapper msgsubscribeMapper;
  
	@Resource(name = "dataSource")
	private DataSource dataSource;
	
	@Override
	public List<MsgsubscribeEntity> findAllList(MsgsubscribeEntity msgsubscribeEntity) {
		
		return msgsubscribeMapper.findAllList(msgsubscribeEntity);
	}

	@Override
	public void saveOrUpdate(MsgsubscribeEntity msgsubscribeEntity) throws Exception {

		Connection conn = null;
		Statement stat = null;
		
		try {
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
			stat = conn.createStatement();
			stat.addBatch("DELETE FROM COM_MSG_SUBSCRIBE A WHERE A.MSD_CUS_NUMBER = " + msgsubscribeEntity.getMsdCusNumber());
					
			List<MsgsubscribeEntity> msgsubscribeList = msgsubscribeEntity.getMsgsubscribeList();
			for(MsgsubscribeEntity msgsubscribe : msgsubscribeList) {
				stat.addBatch("INSERT INTO COM_MSG_SUBSCRIBE(ID, MSD_CUS_NUMBER, MSD_USER_ID, MSD_MSG_IDNTY, MSD_CRTE_TIME, MSD_CRTE_USER_ID, MSD_UPDT_TIME, MSD_UPDT_USER_ID) "
						+ "VALUES('" + UUID.randomUUID().toString().replace("-", "") + "', "
								+ "" + msgsubscribeEntity.getMsdCusNumber() + ", "
								+ "'" + msgsubscribe.getMsdUserId() + "', "
								+ "'" + msgsubscribe.getMsdMsgIdnty() + "', "
								+ "SYSDATE, "
								+ "'" + msgsubscribeEntity.getMsdCrteUserId() + "', "
								+ "SYSDATE, "
								+ "'" + msgsubscribeEntity.getMsdUpdtUserId() + "')");
			}
			stat.executeBatch();			
			conn.commit(); 
			
			// 刷新缓存redis
			refresshCache();
		} finally {
			
			if(stat != null) {
				stat.close();
			}
			
			if(conn != null) {
				conn.close();
			}
		}		
	}	
	
	private void refresshCache() {
		
		RedisCache.deleteHash(msgIdntyUserMap);
		
		MsgsubscribeEntity msgsubscribeEntity = new MsgsubscribeEntity();
		List<MsgsubscribeEntity> rtnList = msgsubscribeMapper.findAllData(msgsubscribeEntity);
		
		String msgIdntyLast = "";
		JSONArray jsonArray = new JSONArray();
		int num = 0;
		if(rtnList != null && rtnList.size() > 0) {
			for(MsgsubscribeEntity msgMap : rtnList) {
				
				num++;
				JSONObject user = new JSONObject();
				String msgIdnty = msgMap.getMsdMsgIdnty();
				String cusNumber = msgMap.getMsdCusNumber();
				String userId = msgMap.getMsdUserId();
				user.put("cusNumber", cusNumber);
				user.put("userId", userId);
				user.put("msgIdnty", msgIdnty);
				
				if(!isNull(msgIdntyLast) && (!isNull(msgIdnty) && !msgIdnty.equals(msgIdntyLast))){
					RedisCache.putHash(msgIdntyUserMap, msgIdntyLast, jsonArray.toJSONString());
					jsonArray = new JSONArray();
				}
				
				jsonArray.add(user);
				msgIdntyLast = msgIdnty;
				if(num == rtnList.size()) {
					RedisCache.putHash(msgIdntyUserMap, msgIdntyLast, jsonArray.toJSONString());
				}
			}
		}
	}
	
	private boolean isNull(String param) {
    	if(null == param) {
            return true;
        } else if(0 == param.trim().length()) {
            return true;
        }
    	
        return false;
   }  	
}