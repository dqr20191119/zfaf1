package com.cesgroup.prison.broadcastPlay.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.cesgroup.prison.broadcastPlay.dto.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.util.MsgIdUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.frm.net.netty.bean.MsgBody;
import com.cesgroup.frm.net.netty.bean.MsgHeader;
import com.cesgroup.prison.broadcastPlay.dao.BroadcastRecordDao;
import com.cesgroup.prison.broadcastPlay.entity.BroadcastRecord;
import com.cesgroup.prison.broadcastPlay.service.BroadcastRecordService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.dao.BroadcastFileDao;
import com.cesgroup.prison.jfsb.dao.BroadcastMapper;
import com.cesgroup.prison.jfsb.entity.BroadcastEntity;
import com.cesgroup.prison.jfsb.entity.BroadcastFile;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@Service("broadcastRecordService")
@Transactional
public class BroadcastRecordServiceImpl extends BaseDaoService<BroadcastRecord, String, BroadcastRecordDao> implements BroadcastRecordService {
	private static final Logger logger = LoggerFactory.getLogger(BroadcastRecordServiceImpl.class);

	/**
	 * gson工具类
	 */
	private static final Gson gson = new GsonBuilder().create();
	
	/**
	 * 广播设备数据库访问接口
	 */
	@Resource
	private BroadcastMapper broadcastMapper;
	/**
	 * 广播曲目数据库访问接口
	 */
	@Resource
	private BroadcastFileDao broadcastFileDao;
	/**
	 * 消息发送接口
	 */
	@Resource
	private MqMessageSender mqMessageSender;

	@Override
	public void startPlay(BroadcastRecordDto broadcastRecordDto) throws BusinessLayerException {
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			/* 1.校验用户信息 */
			if(userBean == null) {
				throw new BusinessLayerException("获取用户信息失败，播放中断！");
			}
			/* 2.校验页面传来的实体对象 */
			if(broadcastRecordDto == null) {
				throw new BusinessLayerException("广播数据错误，对象为null，播放中断！");
			}
			/* 3.校验页面传来的广播ID */
			if(Util.isNull(broadcastRecordDto.getBroadcastId())) {
				throw new BusinessLayerException("广播数据错误，广播设备，播放中断！");
			}

			/* 4.校验广播状态 */
			// 待播放的广播设备列表
			List<BroadcastEntity> broadcastEntityList = null;
			// 设备索引编号
			String dialNos = "";
			// 离线的广播设备设备
			String offlineBroadcast = "";
			// 正在使用的广播设备
			String inServiceBroadcast = "";
			if(Util.notNull(broadcastRecordDto.getBroadcastId())) {
				String[] broadcastIds = broadcastRecordDto.getBroadcastId().split(",");

				if(broadcastIds != null && broadcastIds.length > 0) {
					broadcastEntityList = this.broadcastMapper.findByIdList(Arrays.asList(broadcastIds));
				}
			}
			if(broadcastEntityList == null || broadcastEntityList.size() <= 0) {
				throw new BusinessLayerException("广播设备不存在，请联系管理员");
			}
			// 根据广播设备状态，将状态为使用中的广播、离线的广播统计出来，用以给前台提醒
			for(BroadcastEntity broadcast : broadcastEntityList) {
				if(CommonConstant.BroadcastStatus.OFFLINE.equals(broadcast.getBbdSttsIndc())) {
					offlineBroadcast += broadcast.getBbdName() + ",";
				}
				else if(CommonConstant.BroadcastStatus.IN_SERVICE.equals(broadcast.getBbdSttsIndc())) {
					inServiceBroadcast += broadcast.getBbdName() + ",";
				}
				if(broadcast != null && Util.notNull(broadcast.getBbdIdnty())) {
					dialNos += broadcast.getBbdIdnty() + ":";
				}
			}
			if(offlineBroadcast != null && !"".equals(offlineBroadcast)) {
				offlineBroadcast = offlineBroadcast.substring(0, offlineBroadcast.lastIndexOf(","));
				throw new BusinessLayerException("广播设备[" + offlineBroadcast + "]处于离线状态，请联系管理员");
			}
			if(inServiceBroadcast != null && !"".equals(inServiceBroadcast)) {
				inServiceBroadcast = inServiceBroadcast.substring(0, inServiceBroadcast.lastIndexOf(","));
				throw new BusinessLayerException("广播设备[" + inServiceBroadcast + "]正在使用中，请先将广播停止，刷新后重试");
			}

			/* 5.根据播放类型、播放内容，获取播放内容的值，用于页面展示 */
			String contentType = broadcastRecordDto.getContentType();
			String content = broadcastRecordDto.getContent();
			String contentValue = "";
			// 判断广播类型是否媒体库
			if(CommonConstant.BroadcastType.AUDIO.equals(contentType)) {
				// 将音频文件ID拆分成数组
				String[] broadcastFileIdArr = content != null ? content.split(",") : null;
				// 根据ID查询名称
				if(broadcastFileIdArr != null && broadcastFileIdArr.length > 0) {
					for(String broadcastFileId : broadcastFileIdArr) {
						BroadcastFile broadcastFile = this.broadcastFileDao.findById(broadcastFileId);
						if(broadcastFile != null) {
							contentValue += broadcastFile.getBfdName() + ",";
						}
					}
					if(contentValue != null && !"".equals(contentValue)) {
						contentValue = contentValue.substring(0, contentValue.lastIndexOf(","));
					}
				}
			} else {
				contentValue = content;
			}

			/* 6.开始播放广播并保存记录 */
			for(BroadcastEntity broadcast : broadcastEntityList) {

				BroadcastRecord broadcastRecord = new BroadcastRecord();
				String recordId = UUID.randomUUID().toString().replaceAll("-", "");
				broadcastRecord.setId(recordId);
				broadcastRecord.setCusNumber(broadcast.getBbdCusNumber());
				broadcastRecord.setBroadcastId(broadcast.getId());
				broadcastRecord.setBroadcastName(broadcast.getBbdName());
				broadcastRecord.setContentType(contentType);
				broadcastRecord.setContent(content);
				broadcastRecord.setStartTime(new Date());
				broadcastRecord.setStartResult("99");
				broadcastRecord.setStartResultDesc("已发送开始指令，等待执行结果");
				broadcastRecord.setCreateUserId(userBean.getUserId());
				broadcastRecord.setCreateTime(new Date());
				broadcastRecord.setUpdateUserId(userBean.getUserId());
				broadcastRecord.setUpdateTime(new Date());
				broadcastRecord.setContentValue(contentValue);

				// 保存播放记录
				this.getDao().insert(broadcastRecord);

				// 更新广播设备状态
				//broadcast.setBbdSttsIndc(CommonConstant.BroadcastStatus.IN_SERVICE);
				broadcast.setBbdLatestRecordId(recordId);
				broadcast.setBbdUpdtTime(new Date());
				broadcast.setBbdUpdtUserId(userBean.getUserId());
				this.broadcastMapper.update(broadcast);

				// 发送播放广播指令
				this.sendStartPlayCommand(broadcastRecord, broadcast);
			}
		} catch (BusinessLayerException e) {
			throw new BusinessLayerException(e.getMessage());
		} catch (Exception e) {
			throw new BusinessLayerException("播放广播发生异常");
		}
	}

	@Override
	public void stopPlay(BroadcastRecordDto broadcastRecordDto) throws BusinessLayerException {
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			/* 1.校验用户信息 */
			if(userBean == null) {
				throw new BusinessLayerException("获取用户信息失败，停止广播中断！");
			}
			/* 3.校验页面传来的广播ID */
			if(Util.isNull(broadcastRecordDto.getBroadcastId())) {
				throw new BusinessLayerException("广播数据错误，广播设备表编号，停止广播中断！");
			}

			/* 4.校验广播状态 */
			// 待播放的广播设备列表
			List<BroadcastEntity> broadcastEntityList = null;
			// 设备索引编号
			String dialNos = "";
			// 离线的广播设备设备
			String offlineBroadcast = "";
			// 正在使用的广播设备
			String inServiceBroadcast = "";
			if(Util.notNull(broadcastRecordDto.getBroadcastId())) {
				String[] broadcastIds = broadcastRecordDto.getBroadcastId().split(",");

				if(broadcastIds != null && broadcastIds.length > 0) {
					broadcastEntityList = this.broadcastMapper.findByIdList(Arrays.asList(broadcastIds));
				}
			}
			if(broadcastEntityList == null || broadcastEntityList.size() <= 0) {
				throw new BusinessLayerException("广播设备不存在，请联系管理员");
			}

			/* 5.开始停止广播并保存记录 */
			for(BroadcastEntity broadcast : broadcastEntityList) {
				 
				// 最近一次播放记录ID
				String latestRecordId = broadcast.getBbdLatestRecordId();

				//  根据广播播放记录ID，查询广播播放记录
				BroadcastRecord latestRecord = Util.notNull(latestRecordId) ? this.getDao().findById(latestRecordId) : null;

				// 如果广播播放记录存在，更新广播播放记录，发送停止指令
				if(latestRecord != null) {
					// 更新广播记录
					latestRecord.setEndTime(new Date());
					latestRecord.setEndResult("99");
					latestRecord.setEndResultDesc("已发送停止指令，等待执行结果");
					this.getDao().update(latestRecord);

					// 发送停止指令
					this.sendStopPlayCommand(latestRecord);
				}
			}
			/*for(BroadcastEntity broadcast : broadcastEntityList) {
				// 判断广播设备状态，如果为空闲则执行下一次循环
				if(CommonConstant.BroadcastStatus.IDLE.equals(broadcast.getBbdSttsIndc())) {
					continue;
				}
				// 最近一次播放记录ID
				String latestRecordId = broadcast.getBbdLatestRecordId();

				//  根据广播播放记录ID，查询广播播放记录
				BroadcastRecord latestRecord = Util.notNull(latestRecordId) ? this.getDao().findById(latestRecordId) : null;

				// 如果广播播放记录不存在，只更新设备状态
				if(latestRecord == null) {
					// 将设备状态改为空闲
					broadcast.setBbdSttsIndc(CommonConstant.BroadcastPlayStatus.PLAY);
					broadcast.setBbdUpdtUserId(userBean.getUserId());
					broadcast.setBbdUpdtTime(new Date());
					broadcast.setBbdLatestRecordId(null);
					this.broadcastMapper.update(broadcast);

					// 继续执行下一循环
					continue;
				}

				// 如果广播播放记录存在，更新广播播放记录，发送停止指令
				if(latestRecord != null) {
					// 更新广播记录
					latestRecord.setEndTime(new Date());
					latestRecord.setEndResult("99");
					latestRecord.setEndResultDesc("已发送停止指令，等待执行结果");
					this.getDao().update(latestRecord);

					// 发送停止指令
					this.sendStopPlayCommand(latestRecord);
				}
			}*/
		} catch (BusinessLayerException e) {
			throw new BusinessLayerException(e.getMessage());
		} catch (Exception e) {
			throw new BusinessLayerException("播放广播发生异常");
		}
	}

	@Override
	public Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable) throws BusinessLayerException {
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			if(userBean != null) {
				map.put("cusNumber", userBean.getCusNumber());
			}
			Page<Map<String, Object>> pageList = this.getDao().findWithPageByCondition(map, pageable);
			return pageList;
		} catch (Exception e) {
			throw new BusinessLayerException("根据查询条件，分页查询广播播放记录。");
		}
	}

	/**
	 * 开始播放
	 * 
	 * @param broadcastRecord
	 * @param broadcast
	 * @throws BusinessLayerException
	 */
	private void sendStartPlayCommand(BroadcastRecord broadcastRecord, BroadcastEntity broadcast) throws BusinessLayerException {
		try {

			// 声明消息体
			MsgBody msgBody = null;
			
			// 消息体初始化，如果播放类型为文本转语音
			if(CommonConstant.BroadcastType.TEXT.equals(broadcastRecord.getContentType())) {
				msgBody = this.convertToStartPlayTextMsgBody(broadcast.getBbdLatestRecordId(), broadcastRecord.getContent(), broadcast.getBbdIdnty());
			}
			// 如果播放类型为媒体库文件
			else {
				msgBody = this.convertToStartPlayAudioMsgBody(broadcast.getBbdLatestRecordId(), broadcastRecord.getContent(), broadcast.getBbdIdnty());
			}
			
			// 发送播放广播的指令
			String msgType = CommonConstant.StartBroadcastMsgEnum.get(broadcastRecord.getContentType());
			
			String sendMsg = this.createMessage(broadcastRecord.getCusNumber(), msgType, msgBody);
			mqMessageSender.sendBroadcastMessage(sendMsg, broadcastRecord.getCusNumber(), "5");
			System.out.println("广播操作指令发送成功");
		} catch (Exception e) {
			throw new BusinessLayerException("广播操作指令发送失败，发生异常：" + e.getMessage());
		}
	}

	/**
	 * 停止播放
	 *
	 * @param broadcastRecord
	 * @throws BusinessLayerException
	 */
	private void sendStopPlayCommand(BroadcastRecord broadcastRecord) throws BusinessLayerException {
		try {

			// 声明消息体
			MsgBody msgBody = null;

			// 消息体初始化，如果播放类型为文本转语音
			if(CommonConstant.BroadcastType.TEXT.equals(broadcastRecord.getContentType())) {
				msgBody = this.convertToStopPlayTextMsgBody(broadcastRecord.getId(), broadcastRecord.getSessionId());
			}
			// 如果播放类型为媒体库文件
			else {
				msgBody = this.convertToStopPlayAudioMsgBody(broadcastRecord.getId(), broadcastRecord.getSessionId());
			}

			// 发送停止广播的指令
			String msgType = CommonConstant.StopBroadcastMsgEnum.get(broadcastRecord.getContentType());

			String sendMsg = this.createMessage(broadcastRecord.getCusNumber(), msgType, msgBody);
			mqMessageSender.sendBroadcastMessage(sendMsg, broadcastRecord.getCusNumber(), "5");
			System.out.println("广播停止指令发送成功");
		} catch (Exception e) {
			throw new BusinessLayerException("广播停止指令发送失败，发生异常：" + e.getMessage());
		}
	}

	/**
	 * 封装消息头与消息内容
	 * @param cusNumber
	 * @param msgBody
	 * @return
	 */
	private String createMessage(String cusNumber, String msgType, MsgBody msgBody) {
		// 消息头
		MsgHeader msgHeader = new MsgHeader();
		msgHeader.setCusNumber(cusNumber);// 新增的消息头
		msgHeader.setMsgID(MsgIdUtil.getMsgSeq(""));
		// msgType: Broadcast001: 播放文本声音；Broadcast002: 停止播放文本声音；Broadcast003: 播放音乐；Broadcast004：停止播放音乐；
		msgHeader.setMsgType(msgType);
		msgHeader.setLength(0);
		msgHeader.setSender("SERVER");
		msgHeader.setRecevier("Broadcast");
		msgHeader.setSendTime(Util.toStr(Util.DF_TIME));
		
		JsonObject out = new JsonObject();
		out.add("header", gson.fromJson(gson.toJson(msgHeader), JsonObject.class));
		out.add("body", gson.fromJson(gson.toJson(msgBody), JsonObject.class));
		
		return gson.toJson(out);
	}

	/**
	 * 类型转换
	 *
	 * @param recordId
	 * @param content
	 * @param dialNos
	 * @return
	 * @throws BusinessLayerException
	 */
	private StartPlayTextMsgBody convertToStartPlayTextMsgBody(String recordId, String content, String dialNos) throws BusinessLayerException {
		if(Util.isNull(recordId)) {
			logger.error("广播记录ID为空");
			throw new BusinessLayerException("广播数据异常，请联系管理员");
		}
		if(Util.isNull(content)) {
			logger.error("广播播放内容为空");
			throw new BusinessLayerException("广播播放内容为空，请检查数据");
		}
		if(Util.isNull(dialNos)) {
			logger.error("广播设备索引ID为空");
			throw new BusinessLayerException("广播设备索引ID为空，请检查数据");
		}
		StartPlayTextMsgBody messageBody = new StartPlayTextMsgBody();
		messageBody.setTts_id(recordId);
		messageBody.setVoice("Mandarin");
		messageBody.setVoice_name("woman");
		messageBody.setText(content);
		messageBody.setPlay_mode("1");
		messageBody.setVol("80");
		messageBody.setDial_nos(dialNos);
		messageBody.setSpeed("38");
		messageBody.setVolume("100");
		return messageBody;
	}

	/**
	 * 类型转换
	 *
	 * @param recordId
	 * @param content
	 * @param dialNos
	 * @return
	 * @throws BusinessLayerException 
	 */
	private StartPlayAudioMsgBody convertToStartPlayAudioMsgBody(String recordId, String content, String dialNos) throws BusinessLayerException {
		if(Util.isNull(recordId)) {
			logger.error("广播记录ID为空");
			throw new BusinessLayerException("广播数据异常，请联系管理员");
		}
		if(Util.isNull(content)) {
			logger.error("广播播放内容为空");
			throw new BusinessLayerException("广播播放内容为空，请检查数据");
		}
		if(Util.isNull(dialNos)) {
			logger.error("广播设备索引ID为空");
			throw new BusinessLayerException("广播设备索引ID为空，请检查数据");
		}
		
		StartPlayAudioMsgBody messageBody = new StartPlayAudioMsgBody();
		messageBody.setRand(recordId);
		// 将播放内容中的英文逗号替换为英文冒号
		String mediumIds = "";
		// 根据广播设备主键查询广播设备信息
		if(Util.notNull(content)) {
			String[] broadcastFileIds = content.split(",");
			if(broadcastFileIds != null && broadcastFileIds.length > 0) {
				for(String broadcastFileId : broadcastFileIds) {
					BroadcastFile broadcastFile = this.broadcastFileDao.findById(broadcastFileId);
					if(broadcastFile != null && Util.notNull(broadcastFile.getBfdIdnty())) {
						mediumIds += broadcastFile.getBfdIdnty() + ":";
					}
				}
			}
		}
		if(mediumIds != null && !"".equals(mediumIds)) {
			mediumIds = mediumIds.substring(0, mediumIds.lastIndexOf(":"));
		} else {
			throw new BusinessLayerException("媒体库文件基础数据不存在或已被删除，请联系管理员");
		}
		messageBody.setMedium_ids(mediumIds);
		messageBody.setPlay_mode("1");
		messageBody.setVol("80");
		messageBody.setDuration("0");
		messageBody.setDial_nos(dialNos);
		messageBody.setCyc_count("1");
		
		return messageBody;
	}

	/**
	 * 类型转换
	 *
	 * @param recordId
	 * @param sessionId
	 * @return
	 * @throws BusinessLayerException
	 */
	private StopPlayTextMsgBody convertToStopPlayTextMsgBody(String recordId, String sessionId) throws BusinessLayerException {
		if(Util.isNull(recordId)) {
			logger.error("广播记录ID为空");
			throw new BusinessLayerException("广播数据异常，请联系管理员");
		}
		StopPlayTextMsgBody messageBody = new StopPlayTextMsgBody();
		messageBody.setRand(recordId);
		messageBody.setTts_id(sessionId);
		return messageBody;
	}

	/**
	 * 类型转换
	 *
	 * @param recordId
	 * @param sessionId
	 * @return
	 * @throws BusinessLayerException
	 */
	private StopPlayAudioMsgBody convertToStopPlayAudioMsgBody(String recordId, String sessionId) throws BusinessLayerException {
		if(Util.isNull(recordId)) {
			logger.error("广播记录ID为空");
			throw new BusinessLayerException("广播数据异常，请联系管理员");
		}
		StopPlayAudioMsgBody messageBody = new StopPlayAudioMsgBody();
		messageBody.setRand(recordId);
		messageBody.setTaskId(sessionId);

		return messageBody;
	}
}
