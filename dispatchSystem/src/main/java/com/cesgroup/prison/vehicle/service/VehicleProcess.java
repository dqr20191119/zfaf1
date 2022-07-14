package com.cesgroup.prison.vehicle.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.constants.socket.IMsgTypeConst;
import com.cesgroup.prison.common.facade.MessageSendFacade;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.fm.util.MsgTypeConst;
import com.cesgroup.prison.foreign.dao.ForeignCarDtlsMapper;
import com.cesgroup.prison.foreign.dao.ForeignRegisterMapper;
import com.cesgroup.prison.foreign.entity.ForeignCarDtls;
import com.cesgroup.prison.foreign.entity.ForeignRegister;
import com.cesgroup.prison.vehicle.bean.VehicleBean;

/**
 * 外来车辆消息处理
 * @author lincoln.cheng
 *
 */
@Service
public class VehicleProcess implements IMessageProcess {
	/**
	 * 日志处理类
	 */
    private final Logger logger = LoggerFactory.getLogger(VehicleProcess.class);
    
    /**
     * 外来车辆登记信息Dao
     */
    @Resource
    private ForeignRegisterMapper foreignRegisterMapper;
    
    /**
     * 外来车辆信息Dao
     */
    @Resource
    private ForeignCarDtlsMapper foreignCarDtlsMapper;
    
    /**
     * 外来车辆登记
     */
    @Transactional
    @Override
    public void processMessage(String cusNumber, JSONObject jsonObject) {
        MsgHeader msgHead = JSON.toJavaObject(jsonObject.getJSONObject("header"), MsgHeader.class);
        String msgType = msgHead.getMsgType();
        
        if(MsgTypeConst.FOREIGN_CAR_INOUT.equals(msgType)) {
            logger.info("收到车辆进出返回信息：" + jsonObject.toJSONString());
            try {
                // 将body消息转化为车辆bean
                VehicleBean vehicleBean = Util.fromJson(JSONObject.toJSONString(jsonObject.getJSONObject("body")), VehicleBean.class);
                
                //车牌号为空的不做处理 add by zk
                if(StringUtils.isBlank(vehicleBean.getCarNo())) {
                	return;
                }
                
                //查询外来车辆历史数据，避免插入重复数据 add by zk
                List<ForeignRegister> ForeignRegisterList = foreignRegisterMapper.findHisRecord(cusNumber, vehicleBean.getCarNo(), vehicleBean.getTime());
                if(ForeignRegisterList != null && ForeignRegisterList.size() > 0) {
    				return;
    			}
                
                
                // 保存或更新车辆进出记录
                this.saveVehicleInOutRecord(cusNumber, vehicleBean);
                
                // 向前台发送消息
                Map<String, String> msgMap = new HashMap<String, String>();
                msgMap.put("msgType", IMsgTypeConst.FOREIGN_CAR_INFO);
                msgMap.put("sendType", "1");
                msgMap.put("sendTo", msgHead.getCusNumber());
                msgMap.put("content", JSONObject.toJSONString(jsonObject.getJSONObject("body")));
                msgMap.put("isSendToGzt", "0");
                msgMap.put("url", null);
                msgMap.put("ywId", null);
                msgMap.put("cusNumber", cusNumber);
                msgMap.put("noticeContent", "收到车辆进出返回信息");
                MessageSendFacade.send(msgMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 保存车辆进出记录
     * 
     * @param vehicleBean
     * @throws Exception 
     */
    @Transactional
    private void saveVehicleInOutRecord(String cusNumber, VehicleBean vehicleBean) throws Exception {
    	if(vehicleBean != null) {
	    	// 车辆进出状态
	    	String status = vehicleBean != null && vehicleBean.getStatus() != null ? vehicleBean.getStatus() : "";
	    	
    		// 新增车辆登记信息数据
    		ForeignRegister fr = new ForeignRegister();
    		String registerId = UUID.randomUUID().toString().replaceAll("-", "");
    		fr.setId(registerId);
    		fr.setFrCusNumber(cusNumber);
    		fr.setFrCarsInfo(vehicleBean.getCarNo());
    		//fr.setFrType(Util.notNull(status) ? CommonConstant.CarInOutCNValue.get(status) : "99");// 车辆进出状态(99:未知)
    		//0 待审核 1 进 2 出
    		fr.setFrType(Util.notNull(status) ? status : "99");// 车辆进出状态(99:未知)
    		fr.setFrPhoto1(vehicleBean.getPhoto1());
    		fr.setFrPhoto2(vehicleBean.getPhoto2());
    		fr.setFrTime(Util.toDate(vehicleBean.getTime(), Util.DF_TIME));
    		fr.setFrCheckPeople(vehicleBean.getWatch());
    		fr.setFrCheckTime(Util.toDate(vehicleBean.getTime(), Util.DF_TIME));
    		fr.setFrCheckSttsIndc(vehicleBean.getResult());
    		fr.setFrLocation(vehicleBean.getLocation());
    		// fr.setFrCrteUserId(user != null ? user.getUserId() : null);
    		fr.setFrCrteTime(new Date());
    		// fr.setFrUpdtUserId(user != null ? user.getUserId() : null);
    		fr.setFrUpdtTime(new Date());
    		this.foreignRegisterMapper.insert(fr);
    		
    		// 新增车辆详细信息数据
    		ForeignCarDtls fcd = new ForeignCarDtls();
    		fcd.setId(UUID.randomUUID().toString().replaceAll("-", ""));
    		fcd.setFcdCarLcnsIdnty(vehicleBean.getCarNo());
    		// fcd.setFcdCrteUserId(user != null ? user.getUserId() : null);
    		fcd.setFcdCrteTime(new Date());
    		// fcd.setFcdUpdtUserId(user != null ? user.getUserId() : null);
    		fcd.setFcdUpdtTime(new Date());
    		fcd.setFcdRegisterId(registerId);
    		this.foreignCarDtlsMapper.insert(fcd);
    	}
    }
}
