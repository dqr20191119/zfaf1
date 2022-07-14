package com.cesgroup.prison.xxhj.zfxxcx.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.xxhj.zfxxcx.dao.ZfxxcxMapper;
import com.cesgroup.prison.xxhj.zfxxcx.entity.ZfxxcxEntity;
import com.cesgroup.prison.xxhj.zfxxcx.service.ZfxxcxService;

/**
 * 罪犯信息查询 业务操作类
 * 
 * @author monkey
 *	2019-3-19
 */

@Service(value = "zfxxcxService")
@Transactional
public class ZfxxcxServiceImpl  extends BaseDaoService<ZfxxcxEntity, String, ZfxxcxMapper> implements ZfxxcxService{
//	@Resource
//	private ZfxxcxDao zfxxcxDao;
	@Override
	public Page<Map<String, Object>> queryZfxxInfo(HttpServletRequest request, PageRequest pageRequest)
			throws Exception {
		String cZfbh = request.getParameter("cZfbh");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("cZfbh", cZfbh);
		return this.getDao().queryZfxxInfo(map, pageRequest);
	}

}
