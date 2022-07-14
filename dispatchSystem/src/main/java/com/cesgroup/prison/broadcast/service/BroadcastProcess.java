package com.cesgroup.prison.broadcast.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import com.cesgroup.prison.broadcast.bean.BroadcastBean;
import com.cesgroup.prison.broadcastPlay.entity.BroadcastRecord;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.constants.socket.IMsgTypeConst;
import com.cesgroup.prison.common.facade.MessageSendFacade;
import com.cesgroup.prison.jfsb.entity.BroadcastEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.fm.bean.MsgHeader;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.broadcastPlay.dao.BroadcastRecordDao;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.fm.util.MsgTypeConst;
import com.cesgroup.prison.jfsb.dao.BroadcastMapper;
import com.google.gson.JsonObject;

/**
 * 广播返回信息处理
 *
 * @author
 */
@Service
@Transactional
public class BroadcastProcess implements IMessageProcess {
    /**
     * 日志处理类
     */
    private final Logger logger = LoggerFactory.getLogger(BroadcastProcess.class);

    /**
     * 广播设备DAO
     */
    @Resource
    private BroadcastMapper broadcastMapper;

    /**
     * 广播播放记录DAO
     */
    @Resource
    private BroadcastRecordDao broadcastRecordDao;

    /**
     * 电网登记
     */
    @Transactional
    @Override
    public void processMessage(String cusNumber, JSONObject jsonObject) {
        System.out.println("BroadcastProcess processMessage 收到消息" + jsonObject.toJSONString());
        // 将普通的JSONObject对象转化为JsonObject
        JsonObject gsonObject = Util.fromJson(JSONObject.toJSONString(jsonObject), JsonObject.class);

        // 从gsonObject中取出header，并转化为MsgHeader兑现
        MsgHeader msgHead = gsonObject != null ? Util.fromJson(gsonObject.get("header"), MsgHeader.class) : null;

        // 从msgHead中获取msgType消息类型
        String msgType = msgHead != null ? msgHead.getMsgType() : "";

        if (MsgTypeConst.BROADCAST_RETURN.equals(msgType)) {
            logger.info("收到广播返回信息：" + jsonObject.toJSONString());
            try {
                // 从gsonObject中获取body
                JsonObject body = gsonObject.getAsJsonObject("body");
                if (body == null) {
                    logger.info("广播返回消息body为null");
                    return;
                }

                // 将body中的消息转化为BroadcastBean对象
                BroadcastBean broadcastBean = Util.fromJson(body, BroadcastBean.class);
                if (broadcastBean == null || Util.isNull(broadcastBean.getRand())) {
                    logger.info("广播返回消息body转BroadcastBean，BroadcastBean为null，或broadcastBean.getRand()为null");
                    return;
                }

                // 根据广播记录编号rand，查询广播记录信息
                BroadcastRecord broadcastRecord = this.broadcastRecordDao.findById(broadcastBean.getRand());
                if (broadcastRecord == null) {
                    logger.info("根据广播播放记录ID=" + broadcastBean.getRand() + "查询广播播放记录，查询结果为null");
                    return;
                }

                // 广播指令执行结果
                String resultCoce = broadcastBean.getRelust() != null ? broadcastBean.getRelust() : "";
                logger.info("广播指令执行结果=" + resultCoce);
                // 指令执行成功
                if("1".equals(resultCoce)) {
                    logger.info("广播指令执行成功");
                }
                // 指令执行失败
                else {
                    logger.info("广播指令执行失败");
                }
                // 广播设备
                logger.info("根据广播设备ID=" + broadcastRecord.getBroadcastId() + "查询广播设备信息");
                BroadcastEntity broadcastEntity = this.broadcastMapper.findById(broadcastRecord.getBroadcastId());
                logger.info("根据广播设备ID=" + broadcastRecord.getBroadcastId() + "查询广播设备信息，返回结果为" + broadcastEntity);

                // 更新广播播放记录
                logger.info("更新广播播放记录");
                this.updateBroadcastRecord(broadcastRecord, broadcastBean);

                // 广播设备不存在
                if(broadcastEntity == null) {
                    return;
                }

                // 向前台发送消息
                Map<String, String> msgMap = new HashMap<String, String>();
                msgMap.put("msgType", IMsgTypeConst.MSG_BROADCAST_RESULT_STATUS);
                msgMap.put("sendType", "2");
                msgMap.put("sendTo", broadcastRecord.getUpdateUserId());
                msgMap.put("content", JSONObject.toJSONString(body));
                msgMap.put("isSendToGzt", "0");
                msgMap.put("url", null);
                msgMap.put("ywId", null);
                msgMap.put("cusNumber", cusNumber);
                MessageSendFacade.send(msgMap);
                // 更新广播设备状态
                logger.info("更新广播设备状态");
                this.updateBroadcastEntity(broadcastEntity, broadcastBean);

            } catch (Exception e) {
                logger.error("更新广播播放记录发生异常，异常原因：" + e);
            }
        }
    }

    /**
     * 更新广播播放记录
     * @param broadcastRecord
     * @param broadcastBean
     */
    @Transactional
    protected void updateBroadcastRecord(BroadcastRecord broadcastRecord, BroadcastBean broadcastBean) {
        if(broadcastRecord == null || broadcastBean == null) {
            return;
        }
        // 指令类型
        String playType = broadcastBean.getPlaytype() != null ? broadcastBean.getPlaytype() : "";
        logger.info("更新广播播放记录，指令类型=" + playType);

        // 开始广播
        if("开始广播".equals(playType)) {
            broadcastRecord.setSessionId(broadcastBean.getTaskId());
            logger.info("设置广播播放记录SessionId=" + broadcastRecord.getSessionId());
            broadcastRecord.setStartTime(new Date());
            logger.info("设置广播播放记录播放指令执行结果" + broadcastRecord.getStartResult());
            broadcastRecord.setStartResult(broadcastBean.getRelust());
            String resultDesc = "1".equals(broadcastBean.getRelust()) ? "执行开始广播指令成功" : "执行开始广播指令失败";
            broadcastRecord.setStartResultDesc(resultDesc);
            logger.info("设置广播播放记录播放指令执行结果描述" +  broadcastRecord.getStartResultDesc());
        }
        // 停止广播
        else {
            broadcastRecord.setEndTime(new Date());
            broadcastRecord.setEndResult(broadcastBean.getRelust());
            logger.info("设置广播播放记录停止指令执行结果" + broadcastRecord.getStartResult());
            String resultDesc = "1".equals(broadcastBean.getRelust()) ? "执行停止广播指令成功" : "执行停止广播指令失败";
            broadcastRecord.setEndResultDesc(resultDesc);
            logger.info("设置广播播放记录停止指令执行结果描述" +  broadcastRecord.getStartResultDesc());
        }
        broadcastRecord.setUpdateTime(new Date());
        this.broadcastRecordDao.update(broadcastRecord);

    }

    /**
     * 更新广播设备状态(0:空闲；1：离线；2：使用中)
     * @param broadcastEntity
     * @param broadcastBean
     */
    @Transactional
    protected void updateBroadcastEntity(BroadcastEntity broadcastEntity, BroadcastBean broadcastBean) {
        if(broadcastEntity == null || broadcastBean == null) {
            return;
        }
        // 指令类型
        String playType = broadcastBean.getPlaytype() != null ? broadcastBean.getPlaytype() : "";
        logger.info("更新广播设备状态，指令类型=" + playType);

        // 开始广播
        if("开始广播".equals(playType)) {
            String newStatus = CommonConstant.BroadcastStatus.IDLE;
            String latestRecordId = null;
            // 根据执行结果，来决定广播设备的状态
            logger.info("广播播放记录播放指令执行结果" + broadcastBean.getRelust());
            if("1".equals(broadcastBean.getRelust())) {
                //newStatus = CommonConstant.BroadcastStatus.IN_SERVICE;
                latestRecordId = broadcastBean.getRand();
            } else {
                newStatus = CommonConstant.BroadcastStatus.IDLE;
            }
            logger.info("更新广播设备状态为[" + newStatus + "]");
            broadcastEntity.setBbdSttsIndc(newStatus);
            logger.info("更新广播设备最后播放记录ID[" + latestRecordId + "]");
            broadcastEntity.setBbdLatestRecordId(latestRecordId);
            broadcastEntity.setBbdUpdtTime(new Date());
            this.broadcastMapper.update(broadcastEntity);
        }
        // 停止广播
        else {
            // 根据执行结果，来决定广播设备的状态
            logger.info("广播播放记录播放指令执行结果" + broadcastBean.getRelust());
            if("1".equals(broadcastBean.getRelust())) {
                String newStatus = CommonConstant.BroadcastStatus.IDLE;
                logger.info("更新广播设备状态为[" + newStatus + "]");
                broadcastEntity.setBbdSttsIndc(newStatus);
                broadcastEntity.setBbdLatestRecordId(null);
                logger.info("更新广播设备最近广播记录ID为null");
                broadcastEntity.setBbdUpdtTime(new Date());
                this.broadcastMapper.update(broadcastEntity);
            }
        }
    }
}
