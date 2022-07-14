package com.cesgroup.prison.aqfxyp.wxpg.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.aqfxyp.wxpg.entity.WxpgEntity;

public interface WxpgService  extends IBaseCRUDService<WxpgEntity, String> {
	public List<Map<String,Object>> getWxpg();
	
	public Page<WxpgEntity> findListPage(String zt, PageRequest pageRequest);
}
