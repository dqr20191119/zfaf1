package com.cesgroup.prison.sppz.service.impl;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.cache.DvcStreamServerInfo;
import com.cesgroup.prison.sppz.dao.StreamServerMapper;
import com.cesgroup.prison.sppz.entity.StreamServer;
import com.cesgroup.prison.sppz.service.IStreamServerService;

/**   
*    
* @projectName：prison   
* @ClassName：StreamServerService   
* @Description：   流媒体服务Service
* @author：zhengke   
* @date：2017-12-01 09:51   
* @version        
*/
@Service
public class StreamServerServiceImpl extends BaseDaoService<StreamServer,String,StreamServerMapper> implements IStreamServerService{
	@Autowired
	private DvcStreamServerInfo dvcStreamServerInfo;
	@Autowired
	private StreamServerMapper mapper;
	
	public Page<Map<String,String>> searchStreamServer(StreamServer streamServer_param,PageRequest pageRequest){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("streamServer", streamServer_param);
		return getDao().searchStreamServer(map,pageRequest);
	}
	
	/**
	* @methodName: simpleStreamServerList
	* @Description: 简单流媒体服务列表（供combobox使用）
	* @param ssiCusNumber
	* @return List<Map<String,Object>>    
	* @throws
	*/
	public List<Map<String,Object>> simpleStreamServerList(String ssiCusNumber){
		 return this.getDao().simpleStreamServerList(ssiCusNumber);
	}
	@Override
	@Transactional
	public StreamServer insert(final StreamServer entity) {
        getDao().insert(entity);
        try {
        	dvcStreamServerInfo.refresh();
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
        	dvcStreamServerInfo.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	@Override
	@Transactional
    public StreamServer update(final StreamServer entity) {
        getDao().update(entity);
        try {
        	dvcStreamServerInfo.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return entity;
    }

	@Override
	@Transactional
	public void deleteByIds(List<String> id) {
		mapper.deleteByIds(id);
	}
}
