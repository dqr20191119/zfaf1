package com.cesgroup.prison.location.service;

import java.util.ArrayList;
import java.util.Date;
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
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.fm.util.MsgTypeConst;
import com.cesgroup.prison.location.bean.PoliceLocationBean;
import com.cesgroup.prison.location.dao.PoliceLocationDao;
import com.cesgroup.prison.location.entity.PoliceLocation;
import com.cesgroup.prison.orgMapping.dao.CescodeContrastDaqiDao;
import com.cesgroup.prison.orgMapping.entity.CescodeContrastDaqi;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import ces.sdk.system.bean.OrgInfo;

/**
 * 外来车辆消息处理
 * @author lincoln.cheng
 *
 */
@Service
@Transactional
public class PoliceLocationProcess implements IMessageProcess {
	/**
	 * 日志处理类
	 */
    private final Logger logger = LoggerFactory.getLogger(PoliceLocationProcess.class);
    
    /**
     * 外来车辆登记信息Dao
     */
    @Resource
    private PoliceLocationDao policeLocationDao;
    /**
     * 中信与大旗机构编码映射Dao
     */
    @Resource
    private CescodeContrastDaqiDao cescodeContrastDaqiDao;
    
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
        
        if(MsgTypeConst.POLICE_LOCATION.equals(msgType)) {
            logger.info("收到民警最新所在位置信息：" + jsonObject.toJSONString());
            try {
            	// 从gsonObject中获取body
            	JsonObject body = gsonObject.getAsJsonObject("body");
            	if(body == null) {
            		return;
            	}
        		
            	// 从body中获取key为list的json对象数组
            	JsonArray list = body.get("list") != null ? body.getAsJsonArray("list") : null;
            	if(list == null || list.size() <= 0) {
            		return;
            	}
            	
                // 将JsonArray格式的list消息转化为PoliceLocationBean格式的list
        		List<PoliceLocationBean> policeLocationList = this.jsonArrayToPoliceLocationBeanList(list);
        		if(policeLocationList == null || policeLocationList.size() <= 0) {
        			return;
        		}
        		
        		// 更新民警最新位置信息
        		this.updatePoliceLocation(policeLocationList);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * json数组转化为PoliceLocationBean类型的对象list
     * 
     * @param sourceJsonArray
     * @return
     */
    private List<PoliceLocationBean> jsonArrayToPoliceLocationBeanList(JsonArray sourceJsonArray) {
    	List<PoliceLocationBean> dataList = new ArrayList<PoliceLocationBean>();
    	
    	if(sourceJsonArray != null && sourceJsonArray.size() > 0) {
    		for(JsonElement jsonObject : sourceJsonArray) {
    			PoliceLocationBean policeLocation = Util.fromJson(jsonObject, PoliceLocationBean.class);
    			if(policeLocation != null) {
        			dataList.add(policeLocation);
    			}
    		}
    	}
    	return dataList;
    }
    
    /**
     * 更新民警所在位置
     * 
     * @param policeLocationList
     */
    @Transactional
    private void updatePoliceLocation(List<PoliceLocationBean> policeLocationList) {
    	if(policeLocationList == null || policeLocationList.size() <= 0) {
			return;
		}
    	// 先将数据库中数据源为daqi的数据清空
    	this.deletePoliceLocationByDataSource(CommonConstant.PoliceLocationDataSource.DAQI);
    	
    	// 保存最新民警位置数据
    	Date syncTime = new Date();
    	for(PoliceLocationBean policeLocationBean : policeLocationList) {
    		PoliceLocation policeLocation = this.convertPoliceLocationBeanToPoliceLocation(policeLocationBean);
    		if(policeLocation != null ) {
    			policeLocation.setId(UUID.randomUUID().toString().replaceAll("-", ""));
    			policeLocation.setMqSyncTime(syncTime);
    			this.policeLocationDao.insert(policeLocation);
    		}
    	}
    }
    
    /**
     * 民警所在位置实体类转换
     * 
     * @param policeLocationBean
     * @return
     */
    private PoliceLocation convertPoliceLocationBeanToPoliceLocation(PoliceLocationBean policeLocationBean) {
    	if(policeLocationBean == null) {
    		return null;
    	}
    	// 监狱名称
    	String prisonName = policeLocationBean.getJYName();
    	// 部门编号
    	String deptNo = policeLocationBean.getDeptno();
    	
    	PoliceLocation policeLocation = new PoliceLocation();
    	policeLocation.setPoliceNo(policeLocationBean.getNo());
    	policeLocation.setPoliceName(policeLocationBean.getName());
    	
    	// 根据部门编号，到中信与大旗映射表中查找安防平台对应的机构信息
    	if(Util.notNull(deptNo)) {
    		CescodeContrastDaqi cescodeContrastDaqi = this.cescodeContrastDaqiDao.findByDaqiKey(policeLocationBean.getDeptno());
    		if(cescodeContrastDaqi != null) {
    			policeLocation.setLocationNo(cescodeContrastDaqi.getCesKey());
    			policeLocation.setLocationName(cescodeContrastDaqi.getCesName());
    	    	policeLocation.setLocationPx(Util.string2Int(policeLocationBean.getPx()));
    		}
    	}
    	
    	// 根据监狱名称，到系统管理平台中去查找监狱信息
    	if(Util.notNull(prisonName)) {
    		OrgInfo orgInfo = AuthSystemFacade.getOrgByOrgName(prisonName);
    		if(orgInfo != null) {
    			policeLocation.setPrisonNo(orgInfo.getOrganizeCode());
            	policeLocation.setPrisonName(orgInfo.getOrganizeName());
    		}
    	}
    	policeLocation.setDataSource(CommonConstant.PoliceLocationDataSource.DAQI);
    	return policeLocation;
    }
    
    /**
     * 根据数据源查询民警位置列表
     * 
     * @param dataSource
     * @return
     */
    @Transactional(readOnly = true)
    private List<PoliceLocation> queryPoliceLocationListByDataSource(String dataSource) {
    	try {
    		return this.policeLocationDao.findByDataSource(dataSource);
    	} catch (Exception e) {
    		return null;
    	}
    }
    
    /**
     * 根据数据源查询民警位置列表
     * 
     * @param dataSource
     * @return
     */
    @Transactional
    private void deletePoliceLocationByDataSource(String dataSource) {
    	try {
    		this.policeLocationDao.deleteByDataSource(dataSource);
    	} catch (Exception e) {
    		return;
    	}
    }
}
