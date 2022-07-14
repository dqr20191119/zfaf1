package com.cesgroup.prison.behaviorn.service;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.alarm.messager.bean.AlarmMessageBean;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.commons.SpringBeanUtils;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.constant.AuthSystemConst;
import com.cesgroup.prison.common.constants.socket.IMsgTypeConst;
import com.cesgroup.prison.common.facade.MessageSendFacade;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.fm.util.MsgTypeConst;
import com.cesgroup.prison.jfsb.dao.CameraMapper;
import com.cesgroup.prison.jfsb.entity.Camera;
import com.cesgroup.prison.jfsb.service.ICameraService;
import com.cesgroup.prison.linkage.service.AlarmProcessService;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xwzc.dao.XwzcMapper;
import com.cesgroup.prison.xwzc.dao.XwzcNowMapper;
import com.cesgroup.prison.xwzc.entity.XwzcEntity;
import com.cesgroup.prison.xwzc.entity.XwzcNowEntity;
import com.google.gson.JsonObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 行为侦测信息处理
 * @author monkey
 *
 */
@Service
@Transactional
public class BehaviornProcess implements IMessageProcess {
	/**
	 * 日志处理类
	 */
    private final Logger logger = LoggerFactory.getLogger(BehaviornProcess.class);

	@Resource
	private XwzcMapper xwzcMapper;
	
	@Resource
	private CameraMapper CameraMapper;
	
	@Resource
	private AlarmProcessService alarmProcessService;
	
	private static Map<String,String> eventTypeMap = new HashMap<String,String>();
	@Resource
	private ICameraService iCameraService;
	@Resource
	private XwzcNowMapper xwzcNowMapper;
	static {
		//区域入侵
		eventTypeMap.put("131588", "区域入侵");
		//区域入侵检测
		eventTypeMap.put("1315880", "区域入侵检测");
		//穿越警戒线
		eventTypeMap.put("1315850", "穿越警戒线");
		//剧烈运动
		eventTypeMap.put("1315960", "剧烈运动");
		//攀高监测
		eventTypeMap.put("131598", "攀高监测");
		//IO报警
		eventTypeMap.put("589825", "IO报警");
		//声强突变检测
		eventTypeMap.put("131606", "声强突变检测");

	}
	
    /**
     * 电网DAO
     */
    // @Resource
    // private PowerNetworkMapper powerNetworkMapper;
    
    /**
     * 行为侦测
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
       
        if(StringUtils.isBlank(cusNumber)) {
        	cusNumber = msgHead != null ? msgHead.getCusNumber() : "";
        }
        
        if(MsgTypeConst.BEHAVIORN_IN.equals(msgType)) {
            logger.info("行为侦测收到消息：" + jsonObject.toJSONString());
            try {
            	// 从gsonObject中获取body
            	JsonObject body = gsonObject.getAsJsonObject("body");

				 Map<String,String> behaviorn=this.convertJsonToMap(body);
				 if(behaviorn!=null){
					 
					 //报警联动
					 if(eventTypeMap.containsKey(behaviorn.get("EventType"))) {
						 alarmLinkage(cusNumber,behaviorn); 
						 
						 
						 XwzcEntity xwzc=this.convertMapToXwzc(behaviorn,msgHead.getCusNumber());
					 	 //插入行为侦测表 历史表
						 this.xwzcMapper.insert(xwzc);
                                     processXwzcNowData(xwzc,cusNumber);
                               } else if("qushi".equals(behaviorn.get("DataSource"))) {
						 
						 alarmLinkage(cusNumber,behaviorn); 
						 
						 XwzcEntity xwzc=this.convertMapToXwzc(behaviorn,msgHead.getCusNumber());
					 	 //插入行为侦测表
						 this.xwzcMapper.insert(xwzc);
                                     processXwzcNowData(xwzc,cusNumber);
					 }
                             XwzcEntity xwzc=this.convertMapToXwzc(behaviorn,msgHead.getCusNumber());
                             this.sendZnjklxMessageByBehaviorn(xwzc);

				 }

            } catch (Exception e) {
            	e.printStackTrace();
            	logger.error("更新或插入行为侦测数据发生异常，异常原因：" + e);
            }
        }
    }


   @Transactional
   public void  processXwzcNowData(XwzcEntity xwzc,String cusNumber) throws Exception{
       //1.删除当天一周之前的数据,2.插入的数据
       Date startTime = Util.getBeforeOrAfterDay(-7);
       XwzcNowEntity xwzcNowEntity = new XwzcNowEntity();
       xwzcNowEntity.setStartTime(startTime);
       xwzcNowEntity.setCusNumber(cusNumber);
       //删除一周前的数据
       xwzcNowMapper.deleteBeforeStartTime(xwzcNowEntity);
        //删除完后就插入数据
       XwzcNowEntity xwzcNow = new XwzcNowEntity();
       BeanUtils.copyProperties(xwzc,xwzcNow);
       xwzcNowMapper.insert(xwzcNow);
   }





    //测试报警联动
    private void alarmLinkage(String cusNumber,Map<String,String> map) {
    	String plateformId = "";
    	String eventType = map.get("EventType");
    	/**
    	 * 品牌	类别	事件名称	事件编码
			海康	区域入侵	(区域入侵	131588 ,区域入侵检测	1315880 ,穿越警戒线	1315850)
				
			行为分析	(剧烈运动	1315960,攀高监测	131598)
				
			IO报警	(IO报警	589825)
			尖叫报警	(声强突变检测	 131606)
			
			趋石	行为分析	所有都归为行为分析	

    	 * 
    	 * **/
    	String alarmDeviceType = "";
    	
    	//数据源： qs 趋石， hik 海康
    	String dataSource = map.get("DataSource");
    	if("qushi".equals(dataSource)){
    		//行为分析
    		alarmDeviceType = "12";
    		//趋石反馈的不是云平台中的id，而是自己维护的id，需要特殊处理下
    		//通过IP，去安防平台查询，摄像头在海康云平台中的plateformId
    		Map<String, Object> queryMap = new HashMap<String, Object>();
    		queryMap.put("cbdCusNumber", cusNumber);
    		queryMap.put("cbdIpAddrs", subIp(map.get("CameraId")));
    		List<Camera> cameralist = CameraMapper.findByQueryMap(queryMap);
    		if(cameralist != null && cameralist.size() > 0) {
    			plateformId = cameralist.get(0).getCbdPlatformIdnty();
			}else {
				//趋势的IP没有维护进来的话，则展示IP
				plateformId = map.get("CameraId");
			}
    		
    		//设置事件类型名称，趋石的全作为 行为分析 处理
	    	map.put("EventTypeName", map.get("AlarmType"));
    		
		}else if("hik".equals(dataSource)){
			//海康反馈的直接就是云平台中id
			plateformId = map.get("CameraId");
			//区域入侵 || 穿越警戒线
			if("131588".equals(eventType) || "1315880".equals(eventType) || "1315850".equals(eventType)){
				//区域入侵
	    		alarmDeviceType = "11";
			}
			//剧烈运动 || 攀高监测
			else if("1315960".equals(eventType) || "131598".equals(eventType)){
				//行为分析
				alarmDeviceType = "12";
			}
			//IO报警
			else if("589825".equals(eventType)){
				//IO报警
				alarmDeviceType = "13";
			}
			//尖叫报警
			else if("131606".equals(eventType)){
				//尖叫报警
				alarmDeviceType = "14";
			}
			
			//设置事件类型名称
	    	map.put("EventTypeName", eventTypeMap.get(eventType));
			
		}
    	
    	//设置摄像头在海康云平台的ID，趋石的如果没有关联到，则展示IP
    	map.put("CameraId", plateformId);
    	
    	AlarmMessageBean alarmBean = new AlarmMessageBean();
    	// 消息动作
    	/* 报警设备布、撤防消息字段 */
    	alarmBean.setAction("");
    	// 报警动作-设置为1才进行消息推送
    	alarmBean.setAlarmAction("1");
    	// 报警设备类型
    	/**
    	* NAME	CODE_KEY
		* 人工报警	0	
		* 网络报警器	1	
		* 高压电网	2	
		* 周界红外	3	
		* 门灯	    4	
		* 蛇腹网	    5	
		* 摄像机	    6	
		* 消防设备	7	
		* 周界光纤	8	
		* 对讲设备	9	
		* 电子围栏	10	
		* -------  --------
		* 区域入侵	11	
		* 行为分析	12	
		* IO报警	    13	
		* 尖叫报警	14	
    	* 
    	* **/
    	alarmBean.setAlarmDeviceType(alarmDeviceType);
   
    	/**
    	 * 报警器id
    	 * 目前，我们安防平台中报警器的编号，就是推送过来的cameraId即 海康云平台中摄像头的ID。
    	 * **/
    	alarmBean.setAlarmID(plateformId);
    	// 报警时间
    	alarmBean.setAlarmTime(map.get("Time"));
    	// 报警类型
    	alarmBean.setAlarmType(map.get("AlarmType"));
    	// 备用字段1：IP
    	alarmBean.setItem1("");
    	// 备用字段2
    	alarmBean.setItem2("");
    	// 备注
    	alarmBean.setRemark("");
    	// 状态
    	alarmBean.setStatus("");
    	alarmProcessService.processAlarm(cusNumber, alarmBean);
    }

	private XwzcEntity convertMapToXwzc(Map<String, String> behaviorn, String cusNumber) {
    	XwzcEntity xwzc=new XwzcEntity();
    	xwzc.setEventState(1);
    	xwzc.setAlarmType(behaviorn.get("AlarmType"));
    	xwzc.setLogId(behaviorn.get("AlarmId"));
    	xwzc.setSourceIdx(behaviorn.get("CameraId"));
    	xwzc.setCoding(behaviorn.get("Coding"));
		if("dahua".equals(behaviorn.get("DataSource"))){
			xwzc.setDataSource("大华");
		}else if("hik".equals(behaviorn.get("DataSource"))){
			xwzc.setDataSource("海康");
		}
		else if("qushi".equals(behaviorn.get("DataSource"))){
			xwzc.setDataSource("趋石");
		}
		xwzc.setSourceName(behaviorn.get("DeviceName"));
		xwzc.setCusNumber(cusNumber);
		xwzc.setEventType(Integer.valueOf(behaviorn.get("EventType")));
		//
		xwzc.setEventTypeName(behaviorn.get("EventTypeName"));
		xwzc.setEventLevel(Integer.valueOf(behaviorn.get("Level")));
		xwzc.setLogTxt(behaviorn.get("Message"));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startTime = null;
		Date stopTime = null;
		try {
			if(behaviorn.get("Time") != null &&  !"".equals(behaviorn.get("Time"))) {
				startTime = sdf.parse(behaviorn.get("Time"));
			}
			if(behaviorn.get("StopTime") != null &&  !"".equals(behaviorn.get("StopTime"))) {
				stopTime = sdf.parse(behaviorn.get("StopTime"));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		xwzc.setStartTime(startTime);
		xwzc.setStopTime(stopTime);
		return  xwzc;
	}

    /**
     * 获取ip摄像头的ip地址
     * @param str  格式:rtsp://admin:hhjy888888@34.69.51.8:554/h264/ch1/sub
     * @return
     */
    private String subIp(String str){
          String res = str.split("@")[1].split(":")[0];
          return  res;
      }



	private Map<String,String> convertJsonToMap(JsonObject body) {
    	if(body==null){
    		return null;
		}
		Map<String,String> behaviorn=new HashMap<String,String>();
		if(body.get("AlarmId")!=null){
			behaviorn.put("AlarmId",body.get("AlarmId").getAsString());
		}
		if(body.get("AlarmType")!=null){
			behaviorn.put("AlarmType",body.get("AlarmType").getAsString());
		}
		if(body.get("CameraId")!=null){
			behaviorn.put("CameraId",body.get("CameraId").getAsString());
		}
		if(body.get("Coding")!=null){
			behaviorn.put("Coding",body.get("Coding").getAsString());
		}
		if(body.get("DataSource")!=null){
			behaviorn.put("DataSource",body.get("DataSource").getAsString());
		}
		if(body.get("DeviceName")!=null){
			behaviorn.put("DeviceName",body.get("DeviceName").getAsString());
		}
		if(body.get("Message")!=null){
			behaviorn.put("Message",body.get("Message").getAsString());
		}
		if(body.get("EventState")!=null){
			behaviorn.put("EventState",body.get("EventState").getAsString());
		}
		if(body.get("EventType")!=null){
			behaviorn.put("EventType",body.get("EventType").getAsString());
		}
		if(body.get("EventTypeName")!=null){
			behaviorn.put("EventTypeName",body.get("EventTypeName").getAsString());
		}
		if(body.get("Level")!=null){
			behaviorn.put("Level",body.get("Level").getAsString());
		}
		if(body.get("Time")!=null){
			behaviorn.put("Time",body.get("Time").getAsString());
		}
		if(body.get("StopTime")!=null){
			behaviorn.put("StopTime",body.get("StopTime").getAsString());
		}
		if (behaviorn.size()==0){
			return  null;
		}
		return behaviorn;
    }

    /**
     * 根据行为侦测的消息 关联摄像头id 发送消息给前台
     * @param xwzc
     */
    private void sendZnjklxMessageByBehaviorn( XwzcEntity xwzc){
        //这里只发送 22点 到 8点的数据
        if(xwzc.getSourceIdx()!=null && !"".equals(xwzc.getSourceIdx())){
           //获取当前时间
            Date time = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
              Date nextDay =  CommonUtil.addDate(sdf.parse(DateUtils.getCurrentDate(false)),1);
                String formTime = DateUtils.getCurrentDate(false)+" 17:00:00";
                String toTime = sdf.format(nextDay) +" 06:00:00";
               Date form = simpleDateFormat.parse(formTime);
               Date to = simpleDateFormat.parse(toTime);
               boolean flag = this.belongCalendar(time,form,to);
               if(flag){
                   //发送消息  先查询摄像头id
                  List<Camera>  cameras= iCameraService.queryByCbdCusNumberAndCbdPlatformIdnty(xwzc.getCusNumber(),xwzc.getSourceIdx());
                  if(cameras.size() > 0 ){
                      // 向前台发送消息
                      Map<String, String> msgMap = new HashMap<String, String>();
                      msgMap.put("msgType", IMsgTypeConst.CURRENT_XWZC);
                      msgMap.put("sendType", "1");
                      msgMap.put("sendTo", xwzc.getCusNumber() + ","+ AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG);
                      msgMap.put("content", JSONObject.toJSONString(cameras.get(0)));
                      msgMap.put("isSendToGzt", "0");
                      msgMap.put("url", null);
                      msgMap.put("ywId", null);
                      msgMap.put("cusNumber", xwzc.getCusNumber());
                      MessageSendFacade.send(msgMap);
                  }else{
                       logger.info("行为侦测:没有查到摄像头信息............");
                   }
               }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 判断时间是否在某个时间段内
     * @param time 需要比较的时间
     * @param from 起始时间
     * @param to 结束时间
     * @return
     */
    private boolean belongCalendar(Date time ,Date from,Date to){
        Calendar date = Calendar.getInstance();
         date.setTime(time);
        Calendar after = Calendar.getInstance();
        after.setTime(from);
        Calendar before = Calendar.getInstance();
        before.setTime(to);
        return date.after(after) && date.before(before);
    }
}
