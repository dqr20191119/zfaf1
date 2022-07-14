package com.cesgroup.prison.common.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.common.entity.MessageEntity;

public interface MessageMapper extends BaseDao<MessageEntity, String> {
	/**
	 * 根据主键ID，查询消息记录
	 * @param id
	 * @return
	 */
	public MessageEntity findById(@Param("id") String id);

	/**
	 * 根据主键ID列表，查询消息记录列表
	 * @param idList
	 * @return
	 */
	List<MessageEntity> findByIdList(@Param("idList") List<String> idList);

	/**
	 * 分页查询
	 * @param paramMap
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> findWithPage(Map<String, Object> paramMap, Pageable pageable);

    public void updateReadById(@Param("isRead") String isRead, @Param("readDate") Date readDate, @Param("idList") List<String> idList);

    public void updateReadByYwId(MessageEntity messageEntity);
    
	public Page<MessageEntity> findList(MessageEntity messageEntity, PageRequest pageRequest);

	public MessageEntity findFirstMessage(@Param("noticeUserId") String noticeUserId, @Param("idList") List<String> idList);

	public List<Map<String, Object>> findByMsgType(MessageEntity messageEntity);
}
