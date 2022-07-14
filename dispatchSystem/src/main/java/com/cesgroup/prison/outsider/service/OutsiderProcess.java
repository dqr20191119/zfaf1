package com.cesgroup.prison.outsider.service;

import java.util.List;
import java.util.UUID;

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
import com.cesgroup.prison.outsider.bean.OutsiderBean;
import com.cesgroup.prison.outsider.dao.OutsiderMapper;
import com.cesgroup.prison.outsider.entity.Outsider;
import com.google.gson.JsonObject;

/**
 * 外来人员信息处理
 * @author monkey
 *
 */
@Service
@Transactional
public class OutsiderProcess implements IMessageProcess {
	/**
	 * 日志处理类
	 */
    private final Logger logger = LoggerFactory.getLogger(OutsiderProcess.class);
    
    /**
     * 外来人员登记信息Dao
     */
    @Resource
    private OutsiderMapper outsiderDao;
    
    /**
     * 外来人员登记
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
        
        if(MsgTypeConst.OUT_SIDER.equals(msgType)) {
            logger.info("收到外来人员信息：" + jsonObject.toJSONString());
            try {
            	// 从gsonObject中获取body
            	JsonObject body = gsonObject.getAsJsonObject("body");
            	if(body == null) {
            		return;
            	}
        		
                // 将body中的消息转化为SecurityCheckBean对象
            	OutsiderBean outsiderBean = Util.fromJson(body, OutsiderBean.class);
        		if(outsiderBean == null) {
        			return;
        		}
        		
        		// 格式转化
        		Outsider outsider = this.convertOutsiderBeanToOursider(outsiderBean);
        		
        		// 外来人员信息
        		if(outsider != null) {
        			//查询外来人员历史数据，避免插入重复数据 add by zk
        			List<Outsider> outsiderList = outsiderDao.findHisRecord(cusNumber, outsider.getcSfzbh(), outsider.getdDjrq());
        			if(outsiderList != null && outsiderList.size() > 0) {
        				return;
        			}
        			
        			outsider.setId(UUID.randomUUID().toString().replaceAll("-", ""));
        			outsider.setCusNumber(cusNumber);
            		this.saveOutsider(outsider);
        		}
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 外来人员
     * 
     * @param policeLocationList
     */
    @Transactional
    private void saveOutsider(Outsider outsider) {
    	if(outsider == null) {
			return;
		}
    	
    	// 保存外来人员数据
		this.outsiderDao.insert(outsider);
    }
    
    /**
     * 外来人员实体类转换
     * 
     * @param securityCheckBean
     * @return
     */
    private Outsider convertOutsiderBeanToOursider(OutsiderBean outsiderBean) {
    	if(outsiderBean == null) {
    		return null;
    	}
    	
    	Outsider outsider = new Outsider();
    	outsider.setcName(outsiderBean.getName());
    	outsider.setcSex(outsiderBean.getSex());
    	outsider.setcSfzbh(outsiderBean.getID());
    	outsider.setdCsrq(outsiderBean.getBirthday());
    	outsider.setdDjrq(outsiderBean.getRecordDate());
    	outsider.setcSy(outsiderBean.getCause());
    	outsider.setcCph(outsiderBean.getPlateNumber());
    	outsider.setcCxys(outsiderBean.getCxys());
    	
    	outsider.setcZp(outsiderBean.getPhoto());
    	outsider.setcSpld(outsiderBean.getLeader());
    	outsider.setcCzry(outsiderBean.getPoliceName());
    	outsider.setcCzryjh(outsiderBean.getPoliceNo());
    	outsider.setcBm(outsiderBean.getDepartment());
    	outsider.setcQy(outsiderBean.getLocation());
    	//outsider.setCheckTime(Util.toDate(outsiderBean.getTime(), Util.DF_TIME));
    	//outsider.setDataSource(CommonConstant.PoliceLocationDataSource.DAQI);
    	return outsider;
    }
    
}
