package com.cesgroup.prison.jfsb.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.prison.jfsb.entity.TalkBackBaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**      
* @projectName：prison   
* @ClassName：TalkBackBaseService   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月20日 下午3:31:55   
* @version        
*/
public interface TalkBackBaseService extends IBaseCRUDService<TalkBackBaseEntity, String> {

	public Page<Map<String, Object>> listAll(TalkBackBaseEntity entity, Pageable pageable);

	Map<String, Object> findTalkbackBaseSum(TalkBackBaseEntity entity);

	public AjaxMessage addInfo(TalkBackBaseEntity entity);

	public void updateInfo(TalkBackBaseEntity entity) throws Exception;

	public void deleteById(String id) throws Exception;

	public TalkBackBaseEntity findById(String id);

	public List<String> findByTbdIdntyOrTseIdnty(String cusNumber, String idnty);

	public List<Map<String, Object>> findBaseByMain(String tbdMainIdnty);

	/**
	* @methodName: searchCombData
	* @Description: 下拉数据
	* @param cusNumber 监狱号
	* @param areaId 区域编号
	* @return List<Map<String,Object>>
	* @throws  
	*/
	public List<Map<String, Object>> searchCombData(String cusNumber, String areaId);

}
