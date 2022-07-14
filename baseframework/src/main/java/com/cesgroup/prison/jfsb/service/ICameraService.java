package com.cesgroup.prison.jfsb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.jfsb.entity.Camera;

public interface ICameraService extends IBaseCRUDService<Camera, String> {

	public Page<Map<String,String>> searchCamera(Camera camera_param,PageRequest pageRequest);
	//根据区域id和监所编码和是否为室外查询摄像机(用于区域摄像头树)
	public List<Map<String,Object>> searchByCbdAreaIdAndCbdCusNumber(Camera camera_param);
	public List<Camera> findByCbdAreaIdAndCbdCusNumber(String areaId,String cbdCusNumber);
	public List<Map<String, Object>> findAreaCameraTree(String id,String cusNumber);
	//局部修改
	public int updatePart(Camera camera) throws Exception;
	public int deleteByAreaId(String cbdAreaId) throws Exception;
	public int deleteById(String id) throws Exception;
	
	/**
	* @methodName: findById
	* @Description: 根据摄像机id查询摄像机
	* @param id
	* @return Camera 
	* @throws
	*/
	public Camera findById(String id);
	
	//根据监狱编号查询正常的摄像头数量
	public int selectGoodCount(String cusNumber);
	
	//根据监狱编号查询故障的摄像头数量
	public int selectBadCount(String cusNumber);

	
	//根据id批量删除摄像头
	public void deleteByIds(List<String> ids) throws  Exception;
	//根据对讲ID查询摄像头ID 用于呼叫对讲关联摄像头  临时添加 2018-09-28 wq
	public List<String> getCameraIdByTalkbackId(String cusNumber, List<String> talkIdntys);

	/**
	 * Add by lincoln.cheng 2019/04/18
	 * 
	* @methodName: queryByCbdPlatformIdnty
	* @Description: 根据摄像机在平台的索引编号查询摄像机
	* @param cbdPlatformIdnty
	* @return List<Camera> 
	* @throws
	*/
	public List<Camera> queryByCbdPlatformIdnty(String cbdPlatformIdnty) throws BusinessLayerException;
	/**
	 * Add by lincoln.cheng 2019/04/18
	 *
	 * @methodName: queryByCbdCusNumberAndCbdPlatformIdnty
	 * @Description: 根据摄像机所属监狱编号及在平台的索引编号查询摄像机
	 * @param cbdCusNumber
	 * @param cbdPlatformIdnty
	 * @return List<Camera>
	 * @throws
	 */
	public List<Camera> queryByCbdCusNumberAndCbdPlatformIdnty(String cbdCusNumber, String cbdPlatformIdnty) throws BusinessLayerException;

	/**
	 * 根据摄像机ID，获取视频播放需要的必备信息
	 * @param cameraId
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, Object> getPlayInfoByCameraId(String cameraId) throws ServiceException;
}
