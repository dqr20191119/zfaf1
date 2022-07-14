package com.cesgroup.prison.securityCheck.service;

import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.fm.util.MsgTypeConst;
import com.cesgroup.prison.securityCheck.bean.SecurityCheckBean;
import com.cesgroup.prison.securityCheck.dao.SecurityCheckDao;
import com.cesgroup.prison.securityCheck.entity.SecurityCheck;
import com.google.gson.JsonObject;

/**
 * 安检消息处理
 * @author lincoln.cheng
 *
 */
@Service
@Transactional
public class SecurityCheckProcess implements IMessageProcess {
	/**
	 * 日志处理类
	 */
    private final Logger logger = LoggerFactory.getLogger(SecurityCheckProcess.class);
    
    /**
     * 外来车辆登记信息Dao
     */
    @Resource
    private SecurityCheckDao securityCheckDao;
    
    /**
     * 外来车辆登记
     */
    @Transactional
    @Override
    public void processMessage(String cusNumber, JSONObject jsonObject) {
    	// 将普通的JSONObject对象转化为JsonObject
    	JsonObject gsonObject = Util.fromJson(JSONObject.toJSONString(jsonObject), JsonObject.class);
    	
    	// 从gsonObject中取出header，并转化为MsgHeader兑现
        MsgHeader msgHead = gsonObject != null ? Util.fromJson(gsonObject.get("header"), MsgHeader.class) : null;
        
        // 从msgHead中获取msgType消息类型
        String msgType = msgHead != null ? msgHead.getMsgType() : "";
        
        if(MsgTypeConst.SECURITY_CHECK.equals(msgType)) {
            logger.info("收到安检信息：" + jsonObject.toJSONString());
            try {
            	// 从gsonObject中获取body
            	JsonObject body = gsonObject.getAsJsonObject("body");
            	if(body == null) {
            		return;
            	}
        		
                // 将body中的消息转化为SecurityCheckBean对象
            	SecurityCheckBean securityCheckBean = Util.fromJson(body, SecurityCheckBean.class);
        		if(securityCheckBean == null) {
        			return;
        		}
        		
        		// 格式转化
        		SecurityCheck securityCheck = this.convertSecurityCheckBeanToSecurityCheck(securityCheckBean);
        		
        		// 保存安检信息
        		if(securityCheck != null) {
        	    	securityCheck.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        			securityCheck.setCusNumber(cusNumber);
            		this.saveSecurityCheck(securityCheck);
        		}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 更新民警所在位置
     * 
     * @param policeLocationList
     */
    @Transactional
    private void saveSecurityCheck(SecurityCheck securityCheck) {
    	if(securityCheck == null) {
			return;
		}
    	
    	// 保存安检数据
		this.securityCheckDao.insert(securityCheck);
    }
    
    /**
     * 民警所在位置实体类转换
     * 
     * @param securityCheckBean
     * @return
     */
    private SecurityCheck convertSecurityCheckBeanToSecurityCheck(SecurityCheckBean securityCheckBean) {
    	if(securityCheckBean == null) {
    		return null;
    	}
    	
    	SecurityCheck securityCheck = new SecurityCheck();
    	securityCheck.setDeviceId("");
    	securityCheck.setDeviceName(securityCheckBean.getDeviceName());
    	securityCheck.setDeviceAddress(securityCheckBean.getLocation());
    	securityCheck.setCheckResult(securityCheckBean.getResult());
    	securityCheck.setCheckTime(Util.toDate(securityCheckBean.getTime(), Util.DF_TIME));
    	securityCheck.setDataSource(CommonConstant.PoliceLocationDataSource.DAQI);
    	return securityCheck;
    }
    
}
