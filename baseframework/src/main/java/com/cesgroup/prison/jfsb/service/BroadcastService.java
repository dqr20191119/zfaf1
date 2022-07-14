package com.cesgroup.prison.jfsb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.jfsb.dto.BroadcastEntityDto;
import com.cesgroup.prison.jfsb.entity.BroadcastEntity;

/**      
* @projectName：prison   
* @ClassName：BroadcastService   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月20日 下午3:31:55   
* @version        
*/
public interface BroadcastService extends IBaseCRUDService<BroadcastEntity, String> {
	/**
	 * 分页查询广播设备数据
	 * @param entity
	 * @param pageable
	 * @return
	 */
	public Page<Map<String, Object>> listAll(BroadcastEntity entity, Pageable pageable);
	/**
	 * 新增广播设备
	 * @param entity
	 * @return
	 */
	public AjaxMessage addInfo(BroadcastEntity entity);
	/**
	 * 修改广播设备
	 * @param entity
	 * @throws Exception
	 */
	public void updateInfo(BroadcastEntity entity) throws Exception;
	/**
	 * 删除广播设备
	 * @param list
	 */
	public void deleteByIds(List<String> list);
	/**
	 * 根据主键ID查询广播设备
	 * @param id
	 * @return
	 */
	public BroadcastEntity findById(String id);
	/**
	 * 根据主键ID查询广播设备DTO信息
	 * @return
	 * @throws BusinessLayerException
	 */
	public BroadcastEntityDto queryBroadcastEntityDtoById(String id) throws BusinessLayerException;
	
	public Object findBroadcastList(String ids);
	public List<BroadcastEntity> queryByBfdCusNumber(String cusNumber);
	
}
