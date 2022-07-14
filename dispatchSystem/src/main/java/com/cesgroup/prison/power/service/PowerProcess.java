package com.cesgroup.prison.power.service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.fm.util.MsgTypeConst;
import com.cesgroup.prison.jfsb.dao.PowerNetworkMapper;
import com.cesgroup.prison.jfsb.entity.PowerNetwork;
import com.cesgroup.prison.power.bean.PowerBean;
import com.google.gson.JsonObject;

/**
 * 电网信息处理
 * @author monkey
 *
 */
@Service
@Transactional
public class PowerProcess implements IMessageProcess {
	/**
	 * 日志处理类
	 */
    private final Logger logger = LoggerFactory.getLogger(PowerProcess.class);
    
    /**
     * 电网DAO
     */
    @Resource
    private PowerNetworkMapper powerNetworkMapper;
    
    /**
     * 电网登记
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
        
        if(MsgTypeConst.POWER_DW.equals(msgType)) {
            logger.info("收到电网信息：" + jsonObject.toJSONString());
            try {
            	// 从gsonObject中获取body
            	JsonObject body = gsonObject.getAsJsonObject("body");
            	if(body == null) {
            		return;
            	}
        		
                // 将body中的消息转化为SecurityCheckBean对象
            	PowerBean powerBean = Util.fromJson(body, PowerBean.class);
        		if(powerBean == null) {
        			return;
        		}
        		
        		// 格式转化
        		PowerNetwork powerNetwork = this.convertPowerBeanToPowerNetwork(powerBean);

        		if(powerNetwork != null) {
	        		// 根据监狱编号、电力网络编号查询电网信息
	        		PowerNetwork powerNetworkOp = this.queryPowerNetworkByPnbCusNumberAndPnbIdnty(cusNumber, powerNetwork.getPnbIdnty());
	        		
	        		if(powerNetworkOp != null) {
	        			// 判断新数据与旧数据之间的时间差，10分钟以内的不更新
	        			long seconds = Util.secondsBetween(powerNetwork.getPnbUpdtTime(), powerNetworkOp.getPnbUpdtTime());
	        			if(seconds < 60 * 10) {
	        				logger.info("CusNumber = " + cusNumber + "，pnbIdnty = " + powerNetwork.getPnbIdnty() + "距离的上次更新时间为" + seconds + "秒，在10分钟以内，不作更新");
	        				return;
	        			}
	        			
	        			// 更新电网信息
	        			BeanUtils.copyProperties(powerNetwork, powerNetworkOp, "id");
	        			
	        			powerNetworkOp.setPnbCusNumber(cusNumber);
	        			this.powerNetworkMapper.update(powerNetworkOp);
	        		} else {
		        		// 将最新的电网信息插入
	        			powerNetworkOp = new PowerNetwork();
	        			
	        			BeanUtils.copyProperties(powerNetwork, powerNetworkOp, "id");
	        			
	        			powerNetworkOp.setId(UUID.randomUUID().toString().replaceAll("-", ""));
	        			powerNetworkOp.setPnbCusNumber(cusNumber);
	            		this.powerNetworkMapper.insert(powerNetworkOp);
	        		}
        		}
            } catch (Exception e) {
            	logger.error("更新或插入电网数据发生异常，异常原因：" + e);
            }
        }
    }
    
    /**
     * 描述：根据监狱编号、电力网络编号，查询电网数据列表
     * 
     * @param cusNumber
     * @param cDlwlbh
     * @return
     */
    @Transactional(readOnly = true)
    private PowerNetwork queryPowerNetworkByPnbCusNumberAndPnbIdnty(String pnbCusNumber, String pnbIdnty) {
    	if(Util.isNull(pnbCusNumber) || Util.isNull(pnbIdnty)) {
    		return null;
    	}
    	
    	// 电网消息数据
    	List<PowerNetwork> powerNetworkList = this.powerNetworkMapper.findByPnbCusNumberAndPnbIdnty(pnbCusNumber, pnbIdnty);
    	if(powerNetworkList != null && powerNetworkList.size() > 0) {
    		return powerNetworkList.get(0);
    	}
    	return null;
    }
    
    /**
     * 电网消息 实体类转换
     * 
     * @param 
     * @return
     */
    private PowerNetwork convertPowerBeanToPowerNetwork(PowerBean powerBean) {
    	if(powerBean == null) {
    		return null;
    	}
    	
    	PowerNetwork powerNetwork = new PowerNetwork();
    	// powerNetwork.setPnbAreaName(powerBean.getPrisonNumber());
    	powerNetwork.setPnbIdnty(powerBean.getPowerNetworkIdnty());
    	powerNetwork.setPnbName(powerBean.getPowerNetworkName());
    	powerNetwork.setPnbIp(powerBean.getIp());
    	powerNetwork.setPnbABoxVoltage(Util.string2Double(powerBean.getaBoxVoltage()));
    	powerNetwork.setPnbBBoxVoltage(Util.string2Double(powerBean.getbBoxVoltage()));
    	powerNetwork.setPnbPowerSourceVoltage(Util.string2Double(powerBean.getPowerSourceVoltage()));
    	powerNetwork.setPnbABoxPowerFlow(Util.string2Double(powerBean.getaBoxPowerFlow()));
    	powerNetwork.setPnbBBoxPowerFlow(Util.string2Double(powerBean.getbBoxPowerFlow()));
    	powerNetwork.setPnbPowerSourceFlow(Util.string2Double(powerBean.getPowerSourcePowerFlow()));
    	powerNetwork.setPnbSttsIndc(powerBean.getStatus());
    	//电网数据更新时间
    	// Date updateTime = Util.toDate(powerBean.getUpdateTime(), Util.DF_TIME);
    	powerNetwork.setPnbTime(new Date());
    	powerNetwork.setPnbCrteTime(new Date());
    	powerNetwork.setPnbCrteUserId("电网前置机");
    	powerNetwork.setPnbUpdtTime(new Date());
    	powerNetwork.setPnbUpdtUserId("电网前置机");
    	
    	return powerNetwork;
    }
}
