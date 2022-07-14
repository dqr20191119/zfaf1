package com.cesgroup.prison.route.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.route.dao.RouteMapper;
import com.cesgroup.prison.route.entity.Route;
import com.cesgroup.prison.utils.CommonUtil;

@Service
public class RouteService extends BaseService<Route, String> {
	@Autowired
	private RouteMapper routeMapper;

	@Transactional(readOnly = false)
	public int deleteById(String id) {
		return routeMapper.deleteById(id);
	}

	@Transactional(readOnly = false)
	public void insertRoute(Route route) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		Date date = new Date();
		route.setId(CommonUtil.createUUID().replace("-", ""));
		route.setRpiCusNumber(userBean.getCusNumber());
		route.setRpiCreateTime(date);
		route.setRpiCreateUserId(userId);
		route.setRpiUpdateTime(date);
		route.setRpiUpdateUserId(userId);
		route.setRpiDepartCode(userBean.getDprtmntCode());
		routeMapper.insertRoute(route);
	}

	// 根据监狱编码查看巡视路线列表
	public List<Map<String, String>> findByPrisonCode(String cusNumber , String departCode)   {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		Map<String, Object> map_ = new HashMap<>();

		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			if(userBean.getUserLevel().equals(EUserLevel.PROV) || userBean.getUserLevel().equals(EUserLevel.PRIS)){
				if(StringUtils.isNotBlank(cusNumber)) {
					map_.put("cusNumber", cusNumber);
				}
			}else{
				if(StringUtils.isNotBlank(cusNumber)) {
					map_.put("cusNumber", cusNumber);
				}
				if(StringUtils.isNotBlank(departCode)) {
					map_.put("departCode", departCode);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		List<Map<String, Object>> routes = routeMapper.findByPrisonCode(map_);

		for (Map<String, Object> route : routes) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("text", (String) route.get("RPI_ROAM_NAME"));
			map.put("value", (String) route.get("ID"));
			list.add(map);
		}
		return list;
	}

	@Transactional(readOnly = false)
	public int updateRoute(Route route) throws Exception {
		route.setRpiUpdateUserId(AuthSystemFacade.getLoginUserInfo().getUserId());
		return routeMapper.updateRoute(route);
	}

	public Route findById(String id) {
		return routeMapper.findById(id);
	}

	// 条件分页查询
	public Page<Route> findByPage(Map<String, Object> map, Pageable pageable) {
		Page<Route> findByPage = routeMapper.findByPage(map, pageable);
		return findByPage;
	}
}
