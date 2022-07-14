package com.cesgroup.prison.roamRecord.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.roamRecord.entity.RoamRecord;

/**
 * 三维巡视记录业务服务类
 * 
 * @author lincoln.cheng
 *
 */
@Service
public interface RoamRecordService extends IBaseCRUDService<RoamRecord, String> {
	/**
	 * 保存三维巡视记录
	 * @return
	 * @throws BusinessLayerException
	 */
	public void save(RoamRecord roamRecord) throws BusinessLayerException;
	/**
	 * 分页查询在监民警信息类表
	 * @param param
	 * @param pageable
	 * @return
	 */
    public Page<Map<String, Object>> queryWithPage(Map<String, Object> param, Pageable pageable);
}
