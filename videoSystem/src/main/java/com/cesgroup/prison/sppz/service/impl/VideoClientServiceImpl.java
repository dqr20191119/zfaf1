package com.cesgroup.prison.sppz.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.cache.DvcVideoClientConfig;
import com.cesgroup.prison.sppz.dao.VideoClientMapper;
import com.cesgroup.prison.sppz.entity.VideoClient;
import com.cesgroup.prison.sppz.service.VideoClientSettingService;

@Service
public class VideoClientServiceImpl extends BaseDaoService<VideoClient,String,VideoClientMapper> implements VideoClientSettingService{
	
	@Autowired
	private DvcVideoClientConfig dvcVideoClientConfig;
	
	public Page<Map<String,String>> searchVideoClient(VideoClient videoClient_param,PageRequest pageRequest){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("videoClient", videoClient_param);
		return getDao().searchVideoClient(map,pageRequest);
	}
	@Transactional
	public void updatePart(VideoClient videoClient_param) throws Exception {
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("videoClient", videoClient_param);
		getDao().updatePart(map);
		dvcVideoClientConfig.refresh();
	}
	@Override
	@Transactional
	public VideoClient insert(final VideoClient entity) {
        getDao().insert(entity);
        try {
			dvcVideoClientConfig.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return entity;
    }
	@Override
	@Transactional
    public void delete(final String id) {
        getDao().delete(id);
        try {
			dvcVideoClientConfig.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
