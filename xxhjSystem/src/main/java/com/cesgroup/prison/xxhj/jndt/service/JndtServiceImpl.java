package com.cesgroup.prison.xxhj.jndt.service;
 
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.xxhj.jndt.dao.JndtMapper;
import com.cesgroup.prison.xxhj.jndt.entity.JndtEntity;

@Service("jndtService")
public class JndtServiceImpl extends BaseDaoService<JndtEntity, String, JndtMapper> implements JndtService {
	
	@Resource
	private JndtMapper jndtMapper;
	
	@Override
	public JndtEntity getById(String id) {
		
		return jndtMapper.getById(id);
	}
	
	@Override
	public JndtEntity getHistoryById(String id) {
		
		return jndtMapper.getHistoryById(id);
	}
	
	@Override
	public Page<JndtEntity> findList(JndtEntity jndtEntity, PageRequest pageRequest) {
		
		return jndtMapper.findList(jndtEntity, pageRequest);
	}
	
	@Override
	public List<JndtEntity> findAllList(JndtEntity jndtEntity) {
		
		return jndtMapper.findAllList(jndtEntity);
	}
	
	@Override
	public Page<JndtEntity> findHistoryList(JndtEntity jndtEntity, PageRequest pageRequest) {
		
		return jndtMapper.findHistoryList(jndtEntity, pageRequest);
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(JndtEntity jndtEntity) throws Exception {
		
		String id = jndtEntity.getId();
		
		if(id != null && !"".equals(id)){
			//修改或者填写返回登记
			if(StringUtils.isNotBlank(jndtEntity.getParBackTime().toString()) && StringUtils.isNotBlank(jndtEntity.getParBackReporterId())){
				jndtEntity.setParStatus("2");
			}
			jndtMapper.update(jndtEntity);
			jndtMapper.insertHistory(jndtEntity);
		} else {
			jndtMapper.insert(jndtEntity);
			jndtMapper.insertHistory(jndtEntity);
		}
		
	}
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		jndtMapper.deleteByIds(Arrays.asList(idArr));
		jndtMapper.deleteByRelationIds(Arrays.asList(idArr));
	}

	@Override
	public  List<Map<String, String>> countPeople(String cusNumber,String parOutCategory) {
		return 	jndtMapper.countPeople(cusNumber,parOutCategory);		

	}

	@Override
	@Transactional
	public void completeJndtByIds(String ids) {
		String[] idArr = ids.split(",");
		jndtMapper.completeJndtByIds(Arrays.asList(idArr));
		jndtMapper.completeJndtByRelationIds(Arrays.asList(idArr));

	}
}