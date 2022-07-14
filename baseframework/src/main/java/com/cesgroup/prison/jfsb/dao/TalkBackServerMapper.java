package com.cesgroup.prison.jfsb.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.jfsb.entity.TalkBackServerEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**      
* @projectName：prison   
* @ClassName：TalkBackServerMapper   
* @Description：  对讲主机 
* @author：Tao.xu   
* @date：2017年12月20日 下午7:08:01   
* @version        
*/
public interface TalkBackServerMapper extends BaseDao<TalkBackServerEntity, String> {

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteById(Map<String, Object> map);

	// 根据主机id tbd_main_idnty 逻辑删除对讲分机数据
	void deleteByIdForBase(Map<String, Object> map);

	/**
	* @methodName: searchTreeData
	* @Description: 树数据
	* @param map
	* @return Map<String,Object>    
	* @throws
	*/
	List<Map<String, Object>> searchTreeData(Map<String, Object> map);

	/**
	 * 通过监狱编号查询主机
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> findByCusNumber(Map<String, Object> map);

	/**
	* @methodName: findInfoByCusNumberAndPcIpAndBaseIdnty
	* @Description: 根据监狱号、本机ip、分机编号查询主机信息
	* @param map
	* @return List<Map<String,Object>>
	* @throws  
	*/
	List<Map<String, Object>> findInfoByCusNumberAndPcIpAndBaseIdnty(Map<String, Object> map);

	Map<String, Object> findTalkbackServerSum(Map<String, Object> paramMap);

	/**
	 * 新增时,查找是否存在重复IP
	* */
	List<String> findRepeatIpsWhenAdd(String tsePcIp);

	/**
	 * 修改时,查找是否存在重复IP
	 */
	List<String> findRepeatIpsWhenUpdate(Map<String, Object> map);

}
