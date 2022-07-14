package com.cesgroup.prison.zbgl.zbcx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.constant.AuthSystemConst;
import com.cesgroup.prison.zbgl.zbcx.dao.ZbcxMapper;
import com.cesgroup.prison.zbgl.zbcx.entity.ZbcxEntity;
import com.cesgroup.prison.zbgl.zbcx.service.ZbcxService;

@Service("zbcxService")
public class ZbcxServiceImpl extends BaseDaoService<ZbcxEntity, String, ZbcxMapper> implements ZbcxService {

	@Resource
	private ZbcxMapper zbcxMapper;

	public Page<Map<String, Object>> findList(HttpServletRequest request, PageRequest pageRequest) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("dbdCusNumber", request.getParameter("cusNumber"));
		paramMap.put("dmdDprtmntId", request.getParameter("dmdDprtmntId"));
		paramMap.put("mojOrderId", request.getParameter("mojOrderId"));
		paramMap.put("mojJobId", request.getParameter("mojJobId"));
		paramMap.put("dmdCategoryId", request.getParameter("categoryId"));
		paramMap.put("dbdStaffId", request.getParameter("dutyPolice"));
		paramMap.put("startDate", request.getParameter("startDate"));
		paramMap.put("endDate", request.getParameter("endDate"));

		Page<Map<String, Object>> List = zbcxMapper.findList(paramMap, pageRequest);
		return List;

	}

	public List<Map<String, Object>> findAllList(HttpServletRequest request) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("dbdCusNumber", request.getParameter("cusNumber"));
		paramMap.put("dmdDprtmntId", request.getParameter("dmdDprtmntId"));
		paramMap.put("dmdCategoryId", request.getParameter("categoryId"));
		paramMap.put("dbdStaffId", request.getParameter("dutyPolice"));
		paramMap.put("startDate", request.getParameter("startDate"));
		paramMap.put("endDate", request.getParameter("endDate"));

		List<Map<String, Object>> list = zbcxMapper.findAllList(paramMap);
		return list;

	}
        @Override
	public Map<String, Object> findTodayDutyPolice(Map<String, Object> map) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		String staffName = "";
		paramMap.put("cusNumber", map.get("cusNumber"));
		paramMap.put("cdjJobCode", map.get("cdjJobCode"));
		List<Map<String, Object>> list = zbcxMapper.findTodayDutyPolice(paramMap);

		for (int i = 0; i < list.size(); i++) {

			if (i == list.size() - 1) {
				staffName = staffName + list.get(i).get("DBD_STAFF_NAME");
			} else {
				staffName = staffName + list.get(i).get("DBD_STAFF_NAME") + "、";
			}
		}
		map.put("staffName", staffName);
		return map;
	}

	@Override
	public Map<String, Object> queryDutyCountByDeapmntAndDate(HttpServletRequest request) {
		
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("cusNumber", request.getParameter("cusNumber"));
			paramMap.put("dprtmntId", request.getParameter("dprtmntId"));
			paramMap.put("date", request.getParameter("date"));

			// 查询出的部门信息（总值班人数、白班数、夜班数）
			List<Map<String, String>> listMap = zbcxMapper.queryDutyCountByDeapmntAndDate(paramMap);

			// 存监区信息
			List<Map<String, String>> jqList = new ArrayList<Map<String, String>>();
			// 存科室信息
			List<Map<String, String>> ksList = new ArrayList<Map<String, String>>();
			// 存省局信息
			List<Map<String, String>> sjList = new ArrayList<Map<String, String>>();
			for (Map<String, String> map : listMap) {
				if (map.get("CUS_NUMBER").equals(AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG)) {
					// 省局
					sjList.add(map);
				} else if (map.get("CUS_NUMBER").length() == 4 && map.get("DPRTMNT_ID").length() == 6
						&& !map.get("CUS_NUMBER").equals(AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG)) {
					// 科室
					ksList.add(map);
				} else if (map.get("CUS_NUMBER").length() == 4 && map.get("DPRTMNT_ID").length() == 8
						&& !map.get("CUS_NUMBER").equals(AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG)) {
					// 监区
					jqList.add(map);
				} else if (map.get("CUS_NUMBER").length() == 4 && map.get("DPRTMNT_ID").length() == 10
						&& !map.get("CUS_NUMBER").equals(AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG)) {
					// 监区
					jqList.add(map);
				}
			}
			result.put("sjList", sjList);
			result.put("ksList", ksList);
			result.put("jqList", jqList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}

	@Override
	public Page<Map<String, Object>> queryStaffByDeapmntAndDate(HttpServletRequest request, PageRequest pageRequest) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber"));
		paramMap.put("dprtmntId", request.getParameter("dprtmntId"));
		paramMap.put("date", request.getParameter("date"));
		paramMap.put("type", request.getParameter("type"));
		return zbcxMapper.queryStaffByDeapmntAndDate(paramMap, pageRequest);
	}
}
