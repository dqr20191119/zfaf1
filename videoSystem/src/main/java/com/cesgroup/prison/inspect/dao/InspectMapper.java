package com.cesgroup.prison.inspect.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.inspect.entity.Inspect;
/**
 * 网络督查通报
 * @author wq
 * @date 2018-01-04
 *
 */
public interface InspectMapper extends BaseDao<Inspect, String>{
	//巡查通报列表（本监狱发出的）
	Page<Inspect> inspectListPage(Map<String, Object> paramMap, PageRequest pageRequest);
	//审批列表
	Page<Inspect> inspectSpListPage(Map<String, Object> paramMap, PageRequest pageRequest);
	//汇总列表
	Page<Inspect> inspectHzListPage(Map<String, Object> paramMap, PageRequest pageRequest);

	List<Inspect> inspectlist(Inspect ins);

	void updateInspect(Inspect ins);
	//根据id查询电子通报文档
	public Map<String, Object> findInoInspectDocumentById(String id);
}