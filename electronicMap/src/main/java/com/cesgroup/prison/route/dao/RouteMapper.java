package com.cesgroup.prison.route.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.route.entity.Route;

public interface RouteMapper extends BaseDao<Route, String> {

	public int deleteById(String id);

	public int insertRoute(Route route);

	// 根据监狱编码查看巡视路线列表
	public List<Map<String, Object>> findByPrisonCode(Map<String, Object> map);

	public int updateRoute(Route route);

	public Route findById(String id);

	// 条件分页查询
	public Page<Route> findByPage(Map<String, Object> map, Pageable pageable);
}
