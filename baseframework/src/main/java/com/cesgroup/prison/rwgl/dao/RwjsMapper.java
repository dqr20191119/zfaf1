package com.cesgroup.prison.rwgl.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cesgroup.prison.common.bean.user.UserBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.rwgl.entity.RwjsEntity;

public interface RwjsMapper extends BaseDao<RwjsEntity, String> {

	public RwjsEntity getById(String id);
	
	public Page<RwjsEntity> findList(RwjsEntity rwjsEntity, PageRequest pageRequest);
	
	public Page<RwjsEntity> findDbList(RwjsEntity rwjsEntity, PageRequest pageRequest);

	public List<RwjsEntity> findAllList(RwjsEntity rwjsEntity);
	
	public List<Map<String, Object>> searchSwdb(UserBean user);
	//监外就诊和住院情况
	public List<Map<String, Object>> searchJwqk(HashMap<String, String> map);

}
