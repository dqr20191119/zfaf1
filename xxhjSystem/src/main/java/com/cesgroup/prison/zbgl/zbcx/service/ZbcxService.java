package com.cesgroup.prison.zbgl.zbcx.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zbgl.zbcx.entity.ZbcxEntity;

public interface ZbcxService extends IBaseCRUDService<ZbcxEntity, String> {

	public Page<Map<String, Object>> findList(HttpServletRequest request, PageRequest pageRequest);

	public List<Map<String, Object>> findAllList(HttpServletRequest request);

	public Map<String, Object> findTodayDutyPolice(Map<String, Object> map);

	public Map<String, Object> queryDutyCountByDeapmntAndDate(HttpServletRequest request)  ;

	public Page<Map<String, Object>> queryStaffByDeapmntAndDate(HttpServletRequest request, PageRequest pageRequest);

}
