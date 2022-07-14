package com.cesgroup.prison.jfsb.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.cache.ConfigExtend;
import com.cesgroup.prison.common.cache.DvcCameraBaseDtls;
import com.cesgroup.prison.common.cache.DvcVideoDeviceInfo;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.prison.jfsb.dao.CameraMapper;
import com.cesgroup.prison.jfsb.entity.Camera;
import com.cesgroup.prison.jfsb.service.ICameraService;
import com.cesgroup.prison.region.dao.RegionMapper;
import com.cesgroup.prison.region.entity.Region;

import javax.annotation.Resource;

/**   
*    
* @projectName：prison   
* @ClassName：CameraService   
* @Description： 摄像机Service
* @author：zhengke   
* @date：2017-12-01 09:45   
* @version        
*/
@Service
public class CameraServiceImpl extends BaseDaoService<Camera, String, CameraMapper> implements ICameraService {
	@Resource
	private RegionMapper regionMapper;
	@Resource
	private DvcCameraBaseDtls dvcCameraBaseDtls;
	/**
	* @methodName: searchCamera
	* @Description: 分页列表查询
	* @param camera_param
	* @param pageRequest
	* @return Page<Map<String,String>>    
	* @throws
	*/
	public Page<Map<String,String>> searchCamera(Camera camera_param,PageRequest pageRequest){
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("camera", camera_param);
		return getDao().searchCamera(map,pageRequest);
	}
	
	
	/**
	* @methodName: findByCbdAreaIdAndCbdCusNumber
	* @Description: 根据区域id和监所编码和是否为室外查询摄像机(用于区域摄像头树)
	* @param camera_param
	* @return List<Camera>    
	* @throws
	*/
	public List<Map<String,Object>> searchByCbdAreaIdAndCbdCusNumber(Camera camera_param){
		Map<String, Object> paramMap=new HashMap<String, Object>();
		paramMap.put("cbdAreaId", camera_param.getCbdAreaId());
		paramMap.put("cbdCusNumber", camera_param.getCbdCusNumber());
		paramMap.put("cbdOutSide", camera_param.getCbdOutSide());
		return getDao().searchByCbdAreaIdAndCbdCusNumber(paramMap);
	}
	/**
	* @methodName: findByCbdAreaIdAndCbdCusNumber
	* @Description: 根据区域id和监所编码查询摄像机
	* @param cbdAreaId
	* @param cbdCusNumber
	* @return List<Camera>    
	* @throws
	*/
	public List<Camera> findByCbdAreaIdAndCbdCusNumber(String cbdAreaId,String cbdCusNumber){
		return getDao().findByCbdAreaIdAndCbdCusNumber(cbdAreaId,cbdCusNumber);
	}
	/**
	* @methodName: findById
	* @Description: 根据摄像机id查询摄像机
	* @param id
	* @return Camera 
	* @throws
	*/
	public Camera findById(String id) {
		return getDao().findById(id);
	}

	/**
	* @methodName: findAreaCameraTree
	* @Description: 查询区域摄像机树
	* @param cusNumber 监所名称
	* @param parentId
	* @return List<Map<String,Object>>    
	* @throws
	*/
	public List<Map<String, Object>> findAreaCameraTree(String cusNumber,String parentId){
		List<Region> regionList = null;
		if(parentId == null || parentId.equals("null") || parentId.equals("")|| parentId.equals("0")){ //初始化加载tree
			regionList = this.regionMapper.findRegionByAbdCusNumber(cusNumber);
		}else{ 
			regionList = this.regionMapper.findByAbdCusNumberAndAbdParentAreaId(cusNumber,parentId);
			
			int count = regionList.size();
			if (count>0 || parentId==null || parentId.equals("null") || parentId.equals("")) {// 父节点
				
			} else {//加载摄像头信息
				Camera camera=new Camera();
				camera.setCbdAreaId(parentId);
				camera.setCbdCusNumber(cusNumber);
				List<Map<String, Object>> cameraList=searchByCbdAreaIdAndCbdCusNumber(camera);
				
				return cameraList;
			}
		}
		List<Map<String, Object>> mapChild =  new ArrayList<Map<String, Object>>();
		for (Region region : regionList) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("name", region.getAbdAreaName());
			map1.put("id", region.getAbdAreaId());
			map1.put("open", true);
			//区域下面加载摄像头，所以这里区域的叶子节点也设置isParent为true
			map1.put("isParent", true);
			mapChild.add(map1);
		}
		return mapChild;
	}
	//局部修改
	@Transactional
	public int updatePart(Camera camera) throws Exception{
		int count = this.getDao().updatePart(camera);
		dvcCameraBaseDtls.refresh();
		return count;
	}
	//区域删除
	@Transactional
	public int deleteByAreaId(String cbdAreaId) throws Exception {
		int count = this.getDao().deleteByAreaId(cbdAreaId);
		dvcCameraBaseDtls.refresh();
		return count;
	}
	
	
	
	
	@Transactional
	public int deleteById(String id) throws Exception {
		int count = this.getDao().deleteById(id);
		dvcCameraBaseDtls.refresh();
		return count;
	}
	@Override
	@Transactional
	public Camera insert(final Camera entity) {
        try {
        	UserBean userBean = AuthSystemFacade.getLoginUserInfo();
        	String userId = userBean.getUserId();
			Date date = new Date();
			entity.setCbdCrteUserId(userId);
			entity.setCbdCrteTime(date);
			entity.setCbdUpdtUserId(userId);
			entity.setCbdUpdtTime(date);
			entity.setCbdActionIndc("1");
			entity.setCbdCusNumber(userBean.getCusNumber());
            getDao().insert(entity);
        	dvcCameraBaseDtls.refresh();
		} catch (Exception e) {
			e.printStackTrace();
		}
        return entity;
    }
	
	//根据监狱编号查询正常的摄像头数量
	public int selectGoodCount(String cusNumber) {
		return getDao().selectGoodCount(cusNumber);
	}
	
	//根据监狱编号查询故障的摄像头数量
	public int selectBadCount(String cusNumber) {
		return getDao().selectBadCount(cusNumber);
	}

	@Override
	public List<String> getCameraIdByTalkbackId(String cusNumber, List<String> talkIdntys) {
		List<String> cameraIds = new ArrayList<>();
		Iterator iterator = talkIdntys.iterator();
		while(iterator.hasNext()){
			String talkBackId = (String) iterator.next();
			Map<String, String> params = new HashMap<>();
			params.put("cusNumber", cusNumber);
			params.put("talkBackId", talkBackId);
			String cameraId = this.getDao().getCameraIdByTalkbackId(params);
			cameraIds.add(cameraId);
		}
		return cameraIds;
	}
	
	@Override
	public List<Camera> queryByCbdPlatformIdnty(String cbdPlatformIdnty) throws BusinessLayerException {
		try {
			if(cbdPlatformIdnty != null && !cbdPlatformIdnty.isEmpty()) {
				List<Camera> cameraList = this.getDao().findByCbdPlatformIdnty(cbdPlatformIdnty);
				return cameraList;
			}
			return null;
		} catch (Exception e) {
			throw new BusinessLayerException("根据摄像机在平台的索引编号[" + cbdPlatformIdnty + "]查询摄像机设备信息发生异常", e);
		}
	}

	@Override
	public List<Camera> queryByCbdCusNumberAndCbdPlatformIdnty(String cbdCusNumber, String cbdPlatformIdnty) throws BusinessLayerException {
		try {
			Map<String, Object> queryMap = new HashMap<String, Object>();
			if(cbdCusNumber != null && !cbdCusNumber.isEmpty()) {
				queryMap.put("cbdCusNumber", cbdCusNumber);
			}
			if(cbdPlatformIdnty != null && !cbdPlatformIdnty.isEmpty()) {
				queryMap.put("cbdPlatformIdnty", cbdPlatformIdnty);
			}
			List<Camera> cameraList = this.getDao().findByQueryMap(queryMap);
			// 如果摄像机设备信息为空
			if(cameraList == null || cameraList.size() <= 0) {
				// 判断摄像机索引编号中是否包含英文$符号，如果包含$符号需将$替换成英文下划线_再查询一次
				if(cbdPlatformIdnty != null && cbdPlatformIdnty.contains("$")) {
					queryMap.put("cbdPlatformIdnty", cbdPlatformIdnty.replaceAll("$", "_"));
					cameraList = this.getDao().findByQueryMap(queryMap);
				}
			}
			return cameraList;
		} catch (Exception e) {
			throw new BusinessLayerException("根据摄像机所属监狱编号[" + cbdCusNumber + "]及在平台的索引编号[" + cbdPlatformIdnty + "]查询摄像机设备信息发生异常", e);
		}
	}

	@Override
	public Map<String, Object> getPlayInfoByCameraId(String cameraId) throws ServiceException {
		// 摄像机设备默认的播放方式
		String defaultMode = ConfigExtend.getValue("video.play.mode.default") != null ? ConfigExtend.getValue("video.play.mode.default").toString() : null;

		// 摄像机设备查询字段
		String[] fileds = new String[]{
				DvcCameraBaseDtls.CBD_NAME,
				DvcCameraBaseDtls.CBD_DVR_IDNTY,
				DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY,
				DvcCameraBaseDtls.CBD_VIDEO_PLAY_INDC,
				DvcCameraBaseDtls.CBD_IP_ADDRS,
				DvcCameraBaseDtls.CBD_PORT,
				DvcCameraBaseDtls.CBD_USER_NAME,
				DvcCameraBaseDtls.CBD_USER_PASSWORD,
				DvcCameraBaseDtls.CBD_CHNNL_IDNTY,
				DvcCameraBaseDtls.CBD_BRAND_NAME,
				DvcCameraBaseDtls.CBD_PLATFORM_IDNTY,
				DvcCameraBaseDtls.ID
		};
		// 查询摄像机基础信息
		Map<String, Object> cameraBase = RedisCache.getHashMap(DvcCameraBaseDtls.tableName, cameraId, fileds);
		if(cameraBase == null) {
			throw new ServiceException("摄像机编号[" + cameraId + "]的数据在缓存中不存在！");
		}

		// 摄像机名称
		String cameraName = cameraBase.get(DvcCameraBaseDtls.CBD_NAME) != null ? cameraBase.get(DvcCameraBaseDtls.CBD_NAME).toString() : null;
		// 品牌
		String brand = null;
		// IP地址
		String ipAddress = null;
		// 端口号
		String port = null;
		// 用户名
		String userName = null;
		// 密码
		String password = null;
		// 通道号
		String channel = null;
		// 第三方平台ID（通过NVR或直连摄像头时不使用，通过第三方平台是用作摄像机的编号，由第三方平台定义）
		String platformDeviceId = cameraBase != null && cameraBase.get(DvcCameraBaseDtls.CBD_PLATFORM_IDNTY) != null ? cameraBase.get(DvcCameraBaseDtls.CBD_PLATFORM_IDNTY).toString() : null;

		// 设置播放方式
		String mode = cameraBase.get(DvcCameraBaseDtls.CBD_VIDEO_PLAY_INDC) != null ? cameraBase.get(DvcCameraBaseDtls.CBD_VIDEO_PLAY_INDC).toString() : defaultMode;

		if(mode == null || "".equals(mode)) {
			throw new ServiceException("摄像机的播放模式为空！");
		}

		//设备
		if(mode.equals("2")) {
			// 查询摄像机对应设备信息
			Map<String, Object> deviceBase = RedisCache.getHashMap(DvcVideoDeviceInfo.tableName, cameraBase.get(DvcCameraBaseDtls.CBD_DVR_IDNTY));

			brand = deviceBase != null && deviceBase.get(DvcVideoDeviceInfo.VDI_BRAND) != null ? deviceBase.get(DvcVideoDeviceInfo.VDI_BRAND).toString() : null;
			ipAddress = deviceBase != null && deviceBase.get(DvcVideoDeviceInfo.VDI_IP_ADDRS) != null ? deviceBase.get(DvcVideoDeviceInfo.VDI_IP_ADDRS).toString() : null;
			port = deviceBase != null && deviceBase.get(DvcVideoDeviceInfo.VDI_PORT) != null ? deviceBase.get(DvcVideoDeviceInfo.VDI_PORT).toString() : null;
			userName = deviceBase != null && deviceBase.get(DvcVideoDeviceInfo.VDI_USER_NAME) != null ? deviceBase.get(DvcVideoDeviceInfo.VDI_USER_NAME).toString() : null;
			password = deviceBase != null && deviceBase.get(DvcVideoDeviceInfo.VDI_USER_PASSWORD) != null ? deviceBase.get(DvcVideoDeviceInfo.VDI_USER_PASSWORD).toString() : null;
			channel = cameraBase != null && cameraBase.get(DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY) != null ? cameraBase.get(DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY).toString() : null;
		}
		//直连
		else if(mode.equals("0")) {
			brand = cameraBase != null && cameraBase.get(DvcCameraBaseDtls.CBD_BRAND_NAME) != null ? cameraBase.get(DvcCameraBaseDtls.CBD_BRAND_NAME).toString() : null;
			ipAddress = cameraBase != null && cameraBase.get(DvcCameraBaseDtls.CBD_IP_ADDRS) != null ? cameraBase.get(DvcCameraBaseDtls.CBD_IP_ADDRS).toString() : null;
			port = cameraBase != null && cameraBase.get(DvcCameraBaseDtls.CBD_PORT) != null ? cameraBase.get(DvcCameraBaseDtls.CBD_PORT).toString() : null;
			userName = cameraBase != null && cameraBase.get(DvcCameraBaseDtls.CBD_USER_NAME) != null ? cameraBase.get(DvcCameraBaseDtls.CBD_USER_NAME).toString() : null;
			password = cameraBase != null && cameraBase.get(DvcCameraBaseDtls.CBD_USER_PASSWORD) != null ? cameraBase.get(DvcCameraBaseDtls.CBD_USER_PASSWORD).toString() : null;
			channel = cameraBase != null && cameraBase.get(DvcCameraBaseDtls.CBD_CHNNL_IDNTY) != null ? cameraBase.get(DvcCameraBaseDtls.CBD_CHNNL_IDNTY).toString() : null;
		}

		Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
		resultMap.put("cameraName", cameraName);
		resultMap.put("brand", brand);
		resultMap.put("ipAddress", ipAddress);
		resultMap.put("port", port);
		resultMap.put("userName", userName);
		resultMap.put("password", password);
		resultMap.put("channel", channel);
		resultMap.put("platformDeviceId", platformDeviceId);
		return resultMap;
	}

	@Transactional
	@Override
	public void deleteByIds(List<String> ids) throws Exception {
		this.getDao().deleteByIds(ids);
		dvcCameraBaseDtls.refresh();
	}
}
