package com.cesgroup.prison.xwzc.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

/**
 *@ClassName XwzcNowEntity
 *@Description 行为侦测表 存储一周的数据
 *@Author lh
 *@Date 2020/8/12 10:10
 *
 **/
@Entity
@Table(name = "CDS_BEHAVIOR_DETECTION_NOW")
public class XwzcNowEntity extends StringIDEntity {
    private static final long serialVersionUID = 1L;

    private String logId; //日志Id
    private Integer eventState; //事件状态
    private Integer eventLevel; //事件等级
    private String unitIdx; //控制中心编号
    private Integer eventType; //事件类型
    private String eventTypeName; //事件类型名称
    private Integer subSysType; //事件所属子系统类型
    private String eventName; //事件名称

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date startTime; //事件开始时间

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date stopTime; //事件结束时间
    private String sourceIdx; //事件源编号
    private Integer sourceType; //事件源类型
    private String sourceName; //事件源名称
    private String logTxt; //事件描述信息
    private String regionIdx; //事件源区域编号
    private String extInfo; //事件扩展信息
    private String userId; //事件接收的User Id数组
    private Integer rsltMsgTriggertype;

    public Integer getRsltMsgTriggertype() {
        return rsltMsgTriggertype;
    }

    public void setRsltMsgTriggertype(Integer rsltMsgTriggertype) {
        this.rsltMsgTriggertype = rsltMsgTriggertype;
    }

    private String triggerrets;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date triggertime;
    private String rsltMsgTriggerinfo;
    private String errmsg;


    private Integer trigInfoTriggertype;
    private String trigInfoTriggerinfo;
    private String cusNumber;
    private String dataSource;
    private String channelName;
    private String alarmType;
    private String coding;
    public String getLogId() {
        return logId;
    }
    public void setLogId(String logId) {
        this.logId = logId;
    }
    public Integer getEventState() {
        return eventState;
    }
    public void setEventState(Integer eventState) {
        this.eventState = eventState;
    }
    public Integer getEventLevel() {
        return eventLevel;
    }
    public void setEventLevel(Integer eventLevel) {
        this.eventLevel = eventLevel;
    }
    public String getUnitIdx() {
        return unitIdx;
    }
    public void setUnitIdx(String unitIdx) {
        this.unitIdx = unitIdx;
    }
    public Integer getEventType() {
        return eventType;
    }
    public void setEventType(Integer eventType) {
        this.eventType = eventType;
    }
    public String getEventTypeName() {
        return eventTypeName;
    }
    public void setEventTypeName(String eventTypeName) {
        this.eventTypeName = eventTypeName;
    }
    public Integer getSubSysType() {
        return subSysType;
    }
    public void setSubSysType(Integer subSysType) {
        this.subSysType = subSysType;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getSourceIdx() {
        return sourceIdx;
    }
    public void setSourceIdx(String sourceIdx) {
        this.sourceIdx = sourceIdx;
    }
    public Integer getSourceType() {
        return sourceType;
    }
    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }
    public String getSourceName() {
        return sourceName;
    }
    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }
    public String getLogTxt() {
        return logTxt;
    }
    public void setLogTxt(String logTxt) {
        this.logTxt = logTxt;
    }
    public String getRegionIdx() {
        return regionIdx;
    }
    public void setRegionIdx(String regionIdx) {
        this.regionIdx = regionIdx;
    }
    public String getExtInfo() {
        return extInfo;
    }
    public void setExtInfo(String extInfo) {
        this.extInfo = extInfo;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getTriggerrets() {
        return triggerrets;
    }
    public void setTriggerrets(String triggerrets) {
        this.triggerrets = triggerrets;
    }

    public String getRsltMsgTriggerinfo() {
        return rsltMsgTriggerinfo;
    }
    public void setRsltMsgTriggerinfo(String rsltMsgTriggerinfo) {
        this.rsltMsgTriggerinfo = rsltMsgTriggerinfo;
    }
    public String getErrmsg() {
        return errmsg;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public Integer getTrigInfoTriggertype() {
        return trigInfoTriggertype;
    }
    public void setTrigInfoTriggertype(Integer trigInfoTriggertype) {
        this.trigInfoTriggertype = trigInfoTriggertype;
    }
    public String getTrigInfoTriggerinfo() {
        return trigInfoTriggerinfo;
    }
    public void setTrigInfoTriggerinfo(String trigInfoTriggerinfo) {
        this.trigInfoTriggerinfo = trigInfoTriggerinfo;
    }

    public String getCusNumber() {
        return cusNumber;
    }

    public void setCusNumber(String cusNumber) {
        this.cusNumber = cusNumber;
    }

    public String getDataSource() {
        return dataSource;
    }

    public void setDataSource(String dataSource) {
        this.dataSource = dataSource;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getCoding() {
        return coding;
    }

    public void setCoding(String coding) {
        this.coding = coding;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Date getTriggertime() {
        return triggertime;
    }

    public void setTriggertime(Date triggertime) {
        this.triggertime = triggertime;
    }
}
