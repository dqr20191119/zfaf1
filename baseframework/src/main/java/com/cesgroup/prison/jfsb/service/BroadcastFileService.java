package com.cesgroup.prison.jfsb.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.jfsb.entity.BroadcastFile;

/**
 * 广播曲目维护业务操作接口
 * @author lincoln.cheng
 *
 */
public interface BroadcastFileService extends IBaseCRUDService<BroadcastFile, String> {
	/**
	 * 分页查询
	 * @param entity
	 * @param pageable
	 * @return
	 * @throws BusinessLayerException
	 */
	public Page<BroadcastFile> listAll(Map<String, Object> paramMap, Pageable pageable) throws BusinessLayerException;
	
	/**
	 * 根据主键ID查询广播曲目数据
	 * 
	 * @param id
	 * @return
	 * @throws BusinessLayerException
	 */
	public BroadcastFile queryById(String id) throws BusinessLayerException;
	
	/**
	 * 保存或更新
	 * @param entity
	 * @return
	 * @throws BusinessLayerException
	 */
	public AjaxMessage saveOrUpdate(BroadcastFile entity) throws BusinessLayerException;
	
	/**
	 * 删除
	 * @param list
	 * @throws BusinessLayerException
	 */
	public AjaxMessage deleteByIds(List<String> list) throws BusinessLayerException;
	
	/**
	 * 根据监狱编号，查询广播曲目列表
	 * 
	 * @param cusNumber
	 * @return
	 * @throws BusinessLayerException
	 */
	public List<BroadcastFile> queryByBfdCusNumber(String cusNumber) throws BusinessLayerException;
}
