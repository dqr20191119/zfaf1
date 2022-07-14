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
import com.cesgroup.prison.common.cache.DvcVideoDeviceInfo;
import com.cesgroup.prison.sppz.dao.VideoDeviceMapper;
import com.cesgroup.prison.sppz.entity.VideoDevice;
import com.cesgroup.prison.sppz.service.IVideoDeviceService;

/**   
*    
* @projectName：prison   
* @ClassName：VideoDeviceService   
* @Description：   
* @author：zhengke   
* @date：2017-12-10 21:06   
* @version        
*/
@Service
public class VideoDeviceServiceImpl extends BaseDaoService<VideoDevice,String,VideoDeviceMapper> implements IVideoDeviceService{
	@Autowired
	private DvcVideoDeviceInfo DvcVideoDeviceInfo;
	
	public Page<Map<String,String>> searchVideoDevice(VideoDevice videoDevice_param,PageRequest pageRequest){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("videoDevice", videoDevice_param);
		return getDao().searchVideoDevice(map,pageRequest);
	}
	
	
	/**
	 * 供前置机统一拉取单位下的所有设备信息，业务平台慎用
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> searchVideoDevice(VideoDevice videoDevice_param){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("videoDevice", videoDevice_param);
		return getDao().searchVideoDevice(map);
	}
	
	/**
	* @methodName: simpleVideoDeviceList
	* @Description: 简单视频设备列表（供combobox使用）
	* @param vdiCusNumber
	* @return List<Map<String,Object>>    
	* @throws
	*/
	public List<Map<String,Object>> simpleVideoDeviceList(String vdiCusNumber){
		return getDao().simpleVideoDeviceList(vdiCusNumber);
	}
	@Override
	@Transactional
	public VideoDevice insert(final VideoDevice entity) {
        getDao().insert(entity);
        try {
        	DvcVideoDeviceInfo.refresh();
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
        	DvcVideoDeviceInfo.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	@Override
	@Transactional
    public VideoDevice update(final VideoDevice entity) {
        getDao().update(entity);
        try {
        	DvcVideoDeviceInfo.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return entity;
    }
}
