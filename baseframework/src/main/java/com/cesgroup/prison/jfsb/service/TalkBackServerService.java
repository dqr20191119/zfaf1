package com.cesgroup.prison.jfsb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.prison.jfsb.entity.TalkBackServerEntity;

/**      
* @projectName：prison   
* @ClassName：TalkBackServerService   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月20日 下午3:31:55   
* @version        
*/
public interface TalkBackServerService extends IBaseCRUDService<TalkBackServerEntity, String> {

	public Page<Map<String, Object>> listAll(TalkBackServerEntity entity, Pageable pageable);

	Map<String, Object> findTalkbackServerSum(TalkBackServerEntity entity);

	public AjaxMessage addInfo(TalkBackServerEntity entity);

	public AjaxMessage updateInfo(TalkBackServerEntity entity) throws Exception;

	public void deleteById(String id) throws Exception;

	/**
	 * @return 
	* @methodName: findById
	* @Description: 通过id查纪录
	* @param id void    
	* @throws
	*/
	public TalkBackServerEntity findById(String id);

	/**
	* @methodName: searchTreeData
	* @Description: 树数据
	* @param map
	* @return Map<String,Object>    
	* @throws
	*/
	public Map<String, Object> searchTreeData(String cusNumber);

	/**
	 * 通过监狱编号查询主机
	 * @param cusNumber
	 * @return
	 */
	public List<Map<String, Object>> findByCusNumber(String cusNumber);

	/**
	* @methodName: findInfoByCusNumberAndPcIpAndBaseIdnty
	* @Description: 查询主机信息
	* @param cusNumber 监狱号
	* @param ip 本机ip
	* @param idnty 分机编号
	* @return List<Map<String,Object>>
	* @throws  
	*/
	public List<Map<String, Object>> findInfoByCusNumberAndPcIpAndBaseIdnty(String cusNumber, String ip, String idnty);
}
