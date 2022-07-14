package com.cesgroup.prison.inspect.service;


import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.inspect.entity.InspectLocal;
import com.cesgroup.prison.inspect.entity.InspectLocalRelation;
/**
 * 
 * @author zhengk
 * @date 2018-03-23
 *
 */
@Service
public interface InspectLocalService extends IBaseCRUDService<InspectLocal,String>{

	//待审批
	Page<InspectLocal> inspectlocallistPage(Map<String, Object> paramMap, PageRequest pageRequest);
	//汇总列表
	Page<InspectLocal> inspectlocalHzListPage(Map<String, Object> paramMap, PageRequest pageRequest);
	
	List<InspectLocal> inspectlocallist(InspectLocal ins);
	
	void addInspectInfo(InspectLocal ins);
	
	void updateInspectLocal(InspectLocal ins);
	
	//好的方面、存在问题
	Page<InspectLocalRelation> inspectLocalRelationListPage(Map<String, Object> paramMap, PageRequest pageRequest);

}
