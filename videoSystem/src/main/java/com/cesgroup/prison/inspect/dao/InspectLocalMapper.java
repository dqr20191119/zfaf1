package com.cesgroup.prison.inspect.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.inspect.entity.InspectLocal;
/**
 * 
 * @author zhengk
 * @date 2018-01-09
 *
 */
public interface InspectLocalMapper extends BaseDao<InspectLocal, String>{
	//审批列表
	Page<InspectLocal> inspectlocallistPage(Map<String, Object> paramMap, PageRequest pageRequest);
	//汇总列表
	Page<InspectLocal> inspectlocalHzListPage(Map<String, Object> paramMap, PageRequest pageRequest);
	
	List<InspectLocal> inspectlocallist(InspectLocal ins);
	
	void updateInspectLocal(InspectLocal ins);
}