package com.cesgroup.prison.common.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.common.entity.MessageEntity;

public interface MessageService extends IBaseCRUDService<MessageEntity, String> {
	/**
	 * 分页查询消息通知
	 * @param paramMap
	 * @param pageable
	 * @return
	 * @throws ServiceException
	 */
	public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException;

	/**
	 * 根据消息编号（多个消息编号之间以英文逗号分隔），更新消息的阅读状态及阅读时间
	 * @param isRead
	 * @param readDate
	 * @param ids
	 * @throws ServiceException
	 */
	public void updateReadById(String isRead, Date readDate, String ids) throws ServiceException;
	
	public void updateReadByYwId(MessageEntity messageEntity);
	
	public Page<MessageEntity> findList(MessageEntity messageEntity, PageRequest pageRequest);
	
	public MessageEntity findFirstMessage(MessageEntity messageEntity);
		
	public List<Map<String, Object>> findByMsgType(MessageEntity messageEntity);
	/**
	 * 发送消息提醒到工作台
	 * @param userIds
	 * @param url
	 * @param openType
	 * @param ywId
	 * @param noticeContent
	 */
	public void generalMessageByUser(String[] userIds, String url, String openType, String ywId, String noticeContent);

	/**
	 * 发送消息提醒到工作台
	 * @param orgKeys
	 * @param url
	 * @param openType
	 * @param ywId
	 * @param noticeContent
	 */
	public void generalMessageByOrg(String[] orgKeys, String url, String openType, String ywId, String noticeContent);
}
