package com.cesgroup.prison.common.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ces.authsystem.entity.UserEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.common.dao.MessageMapper;
import com.cesgroup.prison.common.entity.MessageEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.service.MessageService;

@Service("messageService")
public class MessageServiceImpl extends BaseDaoService<MessageEntity, String, MessageMapper> implements MessageService {
	
	@Resource
	private MessageMapper messageMapper;
	
	@Resource(name = "dataSource")
	private DataSource dataSource;

	@Override
	public Page<Map<String, Object>> queryWithPage(Map<String, Object> paramMap, Pageable pageable) throws ServiceException {
		try {
			Page<Map<String, Object>> page = this.getDao().findWithPage(paramMap, pageable);
			return page;
		} catch (Exception e) {
			throw new ServiceException("分页查询消息内容发生异常，原因：" + e);
		}
	}

	@Transactional
	@Override
	public void updateReadById(String isRead, Date readDate, String ids) {
		// 消息编号转为数组
		String[] idArray = ids != null ? ids.split(",") : null;
		if(idArray == null || idArray.length <= 0) {
			throw new ServiceException("消息编号为空");
		}

		// 查询消息记录
		List<MessageEntity> messageEntityList = null;
		try {
			messageEntityList = this.getDao().findByIdList(Arrays.asList(idArray));
		} catch (Exception e) {
			throw new ServiceException("查询消息记录列表发生异常，原因：" + e);
		}

		// 更新消息记录
		if(messageEntityList == null || messageEntityList.size() <= 0) {
			return;
		}
		for (MessageEntity messageEntity : messageEntityList) {
			messageEntity.setIsRead(isRead);
			messageEntity.setReadDate(readDate);
			messageEntity.setEndDate(readDate);
			try {
				this.getDao().updateSelective(messageEntity);
			} catch (Exception e) {
				throw new ServiceException("更新消息记录读取状态发生异常，原因：" + e);
			}
		}
	}
	
	@Override
	@Transactional
	public void updateReadByYwId(MessageEntity messageEntity) {
		
		messageMapper.updateReadByYwId(messageEntity);
	}

	@Override
	public Page<MessageEntity> findList(MessageEntity messageEntity, PageRequest pageRequest) {
		
		return messageMapper.findList(messageEntity, pageRequest);
	}

	@Override
	public MessageEntity findFirstMessage(MessageEntity messageEntity) {
		
		List<String> idList =  Arrays.asList(messageEntity.getId().split(","));
		return messageMapper.findFirstMessage(messageEntity.getNoticeUserId(), idList);
	}

	@Override
	public List<Map<String, Object>> findByMsgType(MessageEntity messageEntity) {
		
		return messageMapper.findByMsgType(messageEntity);
	}

	/**
	 * 将消息通知入库
	 *
	 * @param userIds
	 * @param url
	 * @param msgType
	 * @param ywId
	 * @param noticeContent
	 */
	public void generalMessageByUser(String[] userIds, String url, String msgType, String ywId, String noticeContent) {
		
		Connection conn = null;
		PreparedStatement pstat = null;
		
		try {
			if(!ArrayUtils.isEmpty(userIds)) {
				
				conn = dataSource.getConnection();
				conn.setAutoCommit(false);
				
				String insertSql = "insert into t_c_message(id, notice_user_id, notice_user_name, jy_id, msg_type, is_read, url, create_date, yw_id, content, start_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				pstat = conn.prepareStatement(insertSql);
				
				for (int i = 0; i < userIds.length; i++) {					
					UserEntity user = AuthSystemFacade.getUserInfoByUserId(userIds[i]);				
					pstat.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
					pstat.setString(2, user.getUserId() + "");
					pstat.setString(3, user.getUserName());
					pstat.setString(4, user.getOrgUnitKey());
					pstat.setString(5, msgType);
					pstat.setString(6, "0");
					pstat.setString(7, url);
					pstat.setTimestamp(8, new Timestamp(new Date().getTime()));
					pstat.setString(9, ywId);
					pstat.setString(10, noticeContent);
					pstat.setTimestamp(11, new Timestamp(new Date().getTime()));
					pstat.addBatch();
				}
				
				pstat.executeBatch();
				conn.commit();
			}
		} catch (Exception e) {
			
			logger.error("工作台消息错误：" + e.toString(), e.fillInStackTrace());
		} finally {
			
			if(pstat != null) {
				try {
					pstat.close();
				} catch (SQLException e) {
					logger.error("工作台消息错误：" + e.toString(), e.fillInStackTrace());
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("工作台消息错误：" + e.toString(), e.fillInStackTrace());
				}
			}
		}
	}

	/**
	 * .提醒某些组织下的所有用户
	 *
	 * @param orgKeys
	 * @param url
	 * @param msgType
	 * @param ywId
	 * @param noticeContent
	 */
	public void generalMessageByOrg(String[] orgKeys, String url, String msgType, String ywId, String noticeContent) {
		
		Connection conn = null;
		PreparedStatement pstat = null;
		
		try {
			if(orgKeys != null && orgKeys.length > 0) {
				
				List<UserEntity> allUser = new ArrayList<UserEntity>();
				for (int i = 0; i < orgKeys.length; i++) {
					List<UserEntity> listUser = AuthSystemFacade.getAllUserInfoByOrgKey(orgKeys[i]);
					if(CollectionUtils.isNotEmpty(listUser)){
						for(UserEntity user : listUser) {
							if(!allUser.contains(user)) {
								allUser.add(user);
							}
						}
					}
				}
				
				if(CollectionUtils.isNotEmpty(allUser)) {
					
					conn = dataSource.getConnection();
					conn.setAutoCommit(false);

					String insertSql = "insert into t_c_message(id, notice_user_id, notice_user_name, jy_id, msg_type, is_read, url, create_date, yw_id, content, start_date) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					pstat = conn.prepareStatement(insertSql);
					
					for (UserEntity user : allUser) {
						pstat.setString(1, UUID.randomUUID().toString().replaceAll("-", ""));
						pstat.setString(2, user.getUserId() + "");
						pstat.setString(3, user.getUserName());
						pstat.setString(4, user.getOrgUnitKey());
						pstat.setString(5, msgType);
						pstat.setString(6, "0");
						pstat.setString(7, url);
						pstat.setTimestamp(8, new Timestamp(new Date().getTime()));
						pstat.setString(9, ywId);
						pstat.setString(10, noticeContent);
						pstat.setTimestamp(11, new Timestamp(new Date().getTime()));
						pstat.addBatch();
					}
					
					pstat.executeBatch();
					conn.commit();
				}
			}
		} catch (Exception e) {
			
			logger.error("工作台消息错误：" + e.toString(), e.fillInStackTrace());
		} finally {
			
			if(pstat != null) {
				try {
					pstat.close();
				} catch (SQLException e) {
					logger.error("工作台消息错误：" + e.toString(), e.fillInStackTrace());
				}
			}
			
			if(conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					logger.error("工作台消息错误：" + e.toString(), e.fillInStackTrace());
				}
			}
		}
	}
}
