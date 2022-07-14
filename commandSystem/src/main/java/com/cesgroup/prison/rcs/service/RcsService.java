package com.cesgroup.prison.rcs.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.rcs.entity.RcsEntity;
import com.cesgroup.prison.rcs.entity.YjyaEntity;

/**
 * Description: 融合通讯业务操作接口
 * @author lincoln
 *
 */
public interface RcsService extends IBaseCRUDService<RcsEntity, String>, IMessageProcess {
	/**
	 * 开始呼叫
	 * @param pcIp
	 * @param cusNumber
	 * @param idnty
	 * @return
	 */
	AjaxResult startCall(String pcIp, RcsEntity rcsEntity);
	/**
	 * 挂断
	 */
	AjaxResult hangUp(String pcIp, RcsEntity rcsEntity);
	
	/**
	 * 启动应急预案
	 * @param pcIp
	 * @param yjyaEntity
	 * @return
	 */
	AjaxResult startYjya(String pcIp, YjyaEntity yjyaEntity);
	
	public Page<Map<String,String>> searchRcs(RcsEntity rcs,PageRequest pageRequest);
	
	public Page<Map<String,String>> searchJl(RcsEntity rcs,PageRequest pageRequest);
	
	public void startCallDsq();
}
