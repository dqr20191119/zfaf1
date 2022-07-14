package com.cesgroup.prison.aqfxyp.wxpg.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.aqfxyp.wxpg.entity.WxpgEntity;

public interface WxpgMapper extends BaseDao<WxpgEntity, String> {
	public List<Map<String,Object>> getWxpg(Map<String, Object> map);
	
	public Page<WxpgEntity> findJd(Map<String, Object> map, PageRequest pageRequest);
	
	public Page<WxpgEntity> findGd(Map<String, Object> map, PageRequest pageRequest);
	
	public Page<WxpgEntity> findZd(Map<String, Object> map, PageRequest pageRequest);
	
	public Page<WxpgEntity> findDd(Map<String, Object> map, PageRequest pageRequest);

	public Page<WxpgEntity> findAllFx(Map<String, Object> paramMap, PageRequest pageRequest);
	
}
