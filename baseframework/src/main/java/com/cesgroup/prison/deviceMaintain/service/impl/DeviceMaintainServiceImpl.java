/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.demo.service.code</p>
 * <p>文件名:CodeKeyService.java</p>
 * <p>类更新历史信息</p>
 * @author xiexiaqin 
 * @date 2016-04-22 10:22
 * @todo 
 */
package com.cesgroup.prison.deviceMaintain.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constants.socket.IMsgTypeConst;
import com.cesgroup.prison.common.entity.MessageEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.facade.MessageSendFacade;
import com.cesgroup.prison.common.service.MessageService;
import com.cesgroup.prison.deviceMaintain.dao.DeviceMaintainMapper;
import com.cesgroup.prison.deviceMaintain.entity.DeviceMaintainEntity;
import com.cesgroup.prison.deviceMaintain.service.DeviceMaintainService;

/**      
* @projectName：prison   
* @ClassName：DeviceMaintainServiceImpl   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月9日 下午5:58:57   
* @version        
*/
@Service
@Transactional
public class DeviceMaintainServiceImpl extends BaseDaoService<DeviceMaintainEntity, String, DeviceMaintainMapper>
		implements DeviceMaintainService {

	@Autowired
	private DeviceMaintainMapper mapper;

	@Resource
	private MessageService messageService;

	@Override
	public Page<Map<String, Object>> listAll(DeviceMaintainEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (entity != null) {
			map.put("deviceMaintainEntity", entity);
		}
		return mapper.listAll(map, pageable);
	}

	@Override
	public void addInfo(DeviceMaintainEntity entity, HttpServletRequest request) throws Exception {
		Date date = new Date();
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		entity.setDmaCusNumber(userBean.getCusNumber());
		entity.setDmaCrteTime(date);
		entity.setDmaCrteUserId(userId);
		entity.setDmaUpdtTime(date);
		entity.setDmaUpdtUserId(userId);
		entity.setDmaFaultSubmitterId(userId);// 提交人
		entity.setDmaFaultSubmitter(userBean.getRealName());
		entity.setDmaDprmntIdnty(userBean.getDprtmntId());// 填报部门
		entity.setDmaDprmntName(userBean.getDprtmntName());
		entity.setDmaSubmitType("0");// 上报类型
		entity.setDmaSttsIndc("1");// 待签收状态
		entity.setDmaRemindStts("0");// 提醒状态
		mapper.insert(entity);
		// 发消息通知维修订阅者
		this.sendMsg(IMsgTypeConst.MSG_FAULT_MAINTAIN, "1", userBean.getCusNumber(), "1",
				request.getContextPath() + "/deviceMaintain/openDialog/handle", entity);

	}

	@Override
	public void updateInfo(DeviceMaintainEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (entity != null) {
			Date date = new Date();
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			entity.setDmaUpdtTime(date);
			entity.setDmaUpdtUserId(userId);
			map.put("deviceMaintainEntity", entity);
		}
		mapper.updateInfo(map);
	}

	@Override
	public void deleteByIds(List<String> ids) {
		mapper.deleteByIds(ids);
	}

	@Override
	public Map<String, Object> findById(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(id)) {
			map.put("id", id.trim());
		}
		return mapper.findById(map);
	}

	/**
	* @methodName: sendMsg
	* @Description: 发送消息
	* @param msgType 消息类型: 6002 故障维修
	* @param sendType 发送类型：1-按监狱订阅、2-按用户、3-按组织部门
	* @param sendTo 发送目标对象: 对应上面的具体值-多个值逗号分隔
	* @param isSendToGzt 是否发送到工作台消息	0-否，1-是(默认传1)
	* @param url 业务模块处理url---带上下文路径列表页面
	* @param entity
	* @throws Exception void
	* @throws  
	*/
	private void sendMsg(String msgType, String sendType, String sendTo, String isSendToGzt, String url,
			DeviceMaintainEntity entity) throws Exception {
		Map<String, String> msgMap = new HashMap<String, String>();
		msgMap.put("msgType", msgType);
		msgMap.put("sendType", sendType);
		msgMap.put("sendTo", sendTo);
		msgMap.put("content", JSON.toJSONString(entity));
		msgMap.put("url", url);
		msgMap.put("isSendToGzt", isSendToGzt);
		msgMap.put("ywId", entity.getId());
		msgMap.put("cusNumber", entity.getDmaCusNumber());
		msgMap.put("noticeContent", "设备故障维修");
		MessageSendFacade.send(msgMap);
	}

	/**
	* @methodName: updateReadByYwId
	* @Description: 控制台消息更新
	* @param ywId 业务id
	* @param jyId 监狱id-按业务需求定，如果不传直接更新业务id对应所有的消息为已读，否则更新指定监狱的消息为已读
	* @param noticeUserId 用户id-按业务需求定，如果不传直接更新业务id对应所有的消息为已读，否则更新指定用户的消息为已读
	* @param msgType 消息类型
	* @throws  
	*/
	private void updateReadByYwId(String ywId, String jyId, String noticeUserId, String msgType) {
		MessageEntity messageEntity = new MessageEntity();
		messageEntity.setJyId(jyId);
		messageEntity.setYwId(ywId);
		messageEntity.setNoticeUserId(noticeUserId);
		messageEntity.setMsgType(msgType);
		messageService.updateReadByYwId(messageEntity);
	}

	@Override
	public void signIn(DeviceMaintainEntity entity, HttpServletRequest request) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		entity.setDmaSttsIndc("2");// 已签收
		entity.setDmaMaintainStartTime(new Date());
		entity.setDmaMaintainPersonId(userBean.getUserId());// 维修人，签收人
		entity.setDmaMaintainPerson(userBean.getRealName());
		this.updateInfo(entity);
		this.updateReadByYwId(entity.getId(), userBean.getCusNumber(), null, IMsgTypeConst.MSG_FAULT_MAINTAIN);
	}

	@Override
	public void affairsDone(DeviceMaintainEntity entity, HttpServletRequest request) throws Exception {
		entity.setDmaSttsIndc("3");// 已完成
		entity.setDmaMaintainEndTime(new Date()); // 事务完成时间
		this.updateInfo(entity);
		DeviceMaintainEntity deviceMaintainEntity = mapper.selectOne(entity.getId());
		if ("1".equals(deviceMaintainEntity.getDmaRemindStts())) {// 提醒状态为1 已提醒
			this.updateReadByYwId(entity.getId(), deviceMaintainEntity.getDmaCusNumber(), null,
					IMsgTypeConst.MSG_FAULT_MAINTAIN);
		}
		// 处置完成后发送消息给事务报备提交人 ，提交人反馈意见
		this.sendMsg(IMsgTypeConst.MSG_FAULT_MAINTAIN, "2", deviceMaintainEntity.getDmaFaultSubmitterId(), "1",
				request.getContextPath() + "/deviceMaintain/openDialog/feedback", deviceMaintainEntity);
	}

	@Override
	public void feedBack(DeviceMaintainEntity entity, HttpServletRequest request) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		entity.setDmaSttsIndc("4");// 已反馈
		entity.setDmaSureTime(new Date());
		entity.setDmaDprtmntLeader(userBean.getRealName());
		entity.setDmaDprtmntLeaderId(userBean.getUserId());
		this.updateInfo(entity);
		this.updateReadByYwId(entity.getId(), userBean.getCusNumber(), null, IMsgTypeConst.MSG_FAULT_MAINTAIN);
	}

	@Override
	public void remind(DeviceMaintainEntity entity, HttpServletRequest request) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		entity.setDmaRemindStts("1");
		entity.setDmaRemindTime(new Date());
		entity.setDmaRemindPeopleId(userBean.getUserId());
		entity.setDmaRemindPeople(userBean.getRealName());
		this.updateInfo(entity);
		// 发消息通知维修订阅者
		this.sendMsg(IMsgTypeConst.MSG_FAULT_MAINTAIN, "1", userBean.getCusNumber(), "1",
				request.getContextPath() + "/deviceMaintain/openDialog/handle", entity);
	}

}
