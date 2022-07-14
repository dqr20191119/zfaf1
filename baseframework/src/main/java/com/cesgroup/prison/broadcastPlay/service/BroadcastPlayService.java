package com.cesgroup.prison.broadcastPlay.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.broadcastPlay.entity.BroadcastPlay;

public interface BroadcastPlayService extends IBaseCRUDService<BroadcastPlay, String> {
	public BroadcastPlay findById(String id);
	
	public Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	public AjaxMessage addInfo(BroadcastPlay entity);

	public void updateInfo(BroadcastPlay entity) throws Exception;

	public void deleteByIds(List<String> list);
	
	public AjaxResult startPlay(String playId);
	
	public void startPlayDsq();
}
