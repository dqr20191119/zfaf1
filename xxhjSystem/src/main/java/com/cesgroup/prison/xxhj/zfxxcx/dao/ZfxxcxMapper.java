package com.cesgroup.prison.xxhj.zfxxcx.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.zfxxcx.entity.ZfxxcxEntity;

public interface ZfxxcxMapper  extends BaseDao<ZfxxcxEntity, String>{

	Page<Map<String, Object>> queryZfxxInfo(Map<String, Object> map, PageRequest pageRequest);

}
