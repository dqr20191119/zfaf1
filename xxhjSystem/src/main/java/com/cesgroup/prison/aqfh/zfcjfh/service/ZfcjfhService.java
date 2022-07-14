package com.cesgroup.prison.aqfh.zfcjfh.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.aqfh.zfcjfh.entity.ZfcjfhEntity;

/**
* @author lihong
* @date 创建时间：2020年6月4日 上午10:47:05
* 类说明:
*/
public interface ZfcjfhService extends IBaseCRUDService<ZfcjfhEntity, String> {
	Page<ZfcjfhEntity> findList(ZfcjfhEntity zfcjfhEntity, PageRequest pageRequest);

	void updateById(ZfcjfhEntity zfcjfhEntity);

	AjaxResult inItDutyData();
}
