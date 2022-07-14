package com.cesgroup.prison.inspect.service;

import java.sql.Clob;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.inspect.entity.Inspect;

import dm.jdbc.driver.DmdbClob;
/**
 * 
 * @author zhengk
 * @date 2018-01-04
 *
 */
@Service
public interface InspectService extends IBaseCRUDService<Inspect,String>{

	void addInspectInfo(Inspect ins);
	//巡查通报列表（本监狱发出的）
	Page<Inspect> inspectListPage(Map<String, Object> paramMap, PageRequest pageRequest);
	//待审批
	Page<Inspect> inspectSpListPage(Map<String, Object> paramMap, PageRequest pageRequest);
	//汇总列表
	Page<Inspect> inspectHzListPage(Map<String, Object> paramMap, PageRequest pageRequest);

	List<Inspect> inspectlist(Inspect ins);

	void updateInspect(Inspect ins);
	
	//根据id查询电子通报文档
	public String findInoInspectDocumentById(String id);
	//创建巡查通报文档
	public void createInspectFile(Map<String, Object> paramMap);
	
	public void inspectDelete(String id);
	
	public Inspect findById(String id);
}
