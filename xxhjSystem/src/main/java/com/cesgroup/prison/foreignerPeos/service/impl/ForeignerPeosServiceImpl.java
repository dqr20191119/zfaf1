package com.cesgroup.prison.foreignerPeos.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.foreignerPeos.dao.ForeignerPeosMapper;
import com.cesgroup.prison.foreignerPeos.entity.ForeignerPeosEntity;
import com.cesgroup.prison.foreignerPeos.service.ForeignerPeosService;

@Service("foreignerPeosService")
public class ForeignerPeosServiceImpl extends BaseDaoService<ForeignerPeosEntity, String, ForeignerPeosMapper> implements ForeignerPeosService {
	@Resource
	private ForeignerPeosMapper foreignerPeosMapper;

	@Override
	public Integer searchCounts() {
	    Map<String, Object> paramMap = new HashMap<String, Object>();
	    paramMap.put("dDjrq", Util.toStr(Util.DF_DATE));
	    paramMap.put("cusNumber", AuthSystemFacade.getLoginUserInfo().getOrgCode());
		return foreignerPeosMapper.searchCounts(paramMap);
	}
	
	@Override
	public Page<ForeignerPeosEntity> findList(Map<String, Object> paramMap, PageRequest pageRequest) {
		return foreignerPeosMapper.findList(paramMap, pageRequest);
	}
}
