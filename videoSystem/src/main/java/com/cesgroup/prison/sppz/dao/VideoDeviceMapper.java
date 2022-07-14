package com.cesgroup.prison.sppz.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.sppz.entity.VideoDevice;

public interface VideoDeviceMapper extends BaseDao<VideoDevice,String>{
	public Page<Map<String,String>> searchVideoDevice(Map<String,Object> map,PageRequest pageRequest);
	
	/**
	 * 供前置机统一拉取单位下的所有设备信息，业务平台慎用
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> searchVideoDevice(Map<String,Object> map);
	
	
	/**
	* @methodName: simpleVideoDeviceList
	* @Description: 简单视频设备列表（供combobox使用）
	* @param vdiCusNumber
	* @return List<Map<String,Object>>    
	* @throws
	*/
	public List<Map<String,Object>> simpleVideoDeviceList(@Param(value="vdiCusNumber")String vdiCusNumber);
}
