package com.cesgroup.prison.jfsb.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.jfsb.entity.Camera;

/**   
*    
* @projectName：prison   
* @ClassName：CameraMapper   
* @Description：摄像机Mapper   
* @author：zhengke   
* @date：2017-12-01 09:45   
* @version        
*/
public interface CameraMapper extends BaseDao<Camera, String> {
	/**
	* @methodName: searchCamera
	* @Description: 分页列表查询
	* @param map
	* @param pageRequest
	* @return Page<Map<String,String>>    
	* @throws
	*/
	public Page<Map<String, String>> searchCamera(Map<String, Object> map, PageRequest pageRequest);

	/**
	* @methodName: findByCbdAreaIdAndCbdCusNumber
	* @Description: 根据区域id和监所编码和是否为室外查询摄像机(用于区域摄像头树)
	* @param cbdAreaId
	* @param cbdCusNumber
	* @return List<Camera>    
	* @throws
	*/
	public List<Map<String, Object>> searchByCbdAreaIdAndCbdCusNumber(Map<String, Object> map);

	/**
	* @methodName: findByCbdAreaIdAndCbdCusNumber
	* @Description: 根据区域id和监所编码查询摄像机
	* @param cbdAreaId
	* @param cbdCusNumber
	* @return List<Camera>    
	* @throws
	*/
	public List<Camera> findByCbdAreaIdAndCbdCusNumber(String areaId, String cbdCusNumber);
	/**
	* @methodName: findById
	* @Description: 根据摄像机id查询摄像机
	* @param id
	* @return Camera 
	* @throws
	*/
	public Camera findById(String id);

	// 局部修改
	public int updatePart(Camera camera);

	// 区域删除(逻辑删除)
	public int deleteByAreaId(String cbdAreaId);

	// 根据id删除(逻辑删除)
	public int deleteById(String id);
	//根据id批量删除
	void deleteByIds(List<String> ids);
	//根据监狱编号查询正常的摄像头数量
	public int selectGoodCount(String cusNumber);
	
	//根据监狱编号查询故障的摄像头数量
	public int selectBadCount(String cusNumber);

	//根据对讲ID查询摄像头ID 用于呼叫对讲关联摄像头  临时添加 2018-09-28 wq
	public String getCameraIdByTalkbackId(Map<String, String> param);
	/**
	 * 根据查询条件，查询摄像机信息
	 *
	 * @param queryMap
	 * @return
	 */
	public List<Camera> findByQueryMap(Map<String, Object> queryMap);
	/**
	 * 根据摄像机所在平台索引编号，查询摄像机信息
	 * 
	 * @param cbdPlatformIdnty
	 * @return
	 */
	public List<Camera> findByCbdPlatformIdnty(@Param("cbdPlatformIdnty") String cbdPlatformIdnty);
}
