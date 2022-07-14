package com.cesgroup.prison.broadcastPlay.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.broadcastPlay.dto.BroadcastRecordDto;
import com.cesgroup.prison.broadcastPlay.entity.BroadcastRecord;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface BroadcastRecordService extends IBaseCRUDService<BroadcastRecord, String> {
	
	/**
	 * 开始播放广播，并保存播放记录
	 * 
	 * @param broadcastRecordDto
	 * @throws BusinessLayerException
	 */
	public void startPlay(BroadcastRecordDto broadcastRecordDto) throws BusinessLayerException;

	/**
	 * 停止播放广播，并更新播放记录
	 *
	 * @param broadcastRecordDto
	 * @throws BusinessLayerException
	 */
	public void stopPlay(BroadcastRecordDto broadcastRecordDto) throws BusinessLayerException;

	/**
	 * 分页查询广播播放记录
	 *
	 * @param map
	 * @param pageable
	 * @return
	 * @throws BusinessLayerException
	 */
	public Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable) throws BusinessLayerException;
}
