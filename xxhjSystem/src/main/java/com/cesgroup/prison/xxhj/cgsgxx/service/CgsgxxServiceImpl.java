package com.cesgroup.prison.xxhj.cgsgxx.service;
 
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import com.cesgroup.prison.xxhj.cgsgxx.vo.CgsgxxVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.xxhj.cgsgxx.dao.CgsgxxMapper;
import com.cesgroup.prison.xxhj.cgsgxx.entity.CgsgxxEntity;

@Service("cgsgxxService")
public class CgsgxxServiceImpl extends BaseDaoService<CgsgxxEntity, String, CgsgxxMapper> implements CgsgxxService {
	
	@Resource
	private CgsgxxMapper cgsgxxMapper;

	@Override
	@Transactional
	public void saveOrUpdate(CgsgxxEntity cgsgxxEntity) throws Exception {
		
		String id = cgsgxxEntity.getId();
		
		if(id != null && !"".equals(id)){
			//修改或者填写返回登记
			cgsgxxMapper.update(cgsgxxEntity);
		} else {
			cgsgxxMapper.insert(cgsgxxEntity);
		}
	}

	@Override
	public CgsgxxEntity getById(String id) {
		
		return cgsgxxMapper.getById(id);
	}

	@Override
	public Page<CgsgxxEntity> findList(CgsgxxVo cgsgxxVo, PageRequest pageRequest) {
		
		return cgsgxxMapper.findList(cgsgxxVo, pageRequest);
	}

	@Override
	@Transactional
	public void deleteByIds(String ids) {
		String[] idArr = ids.split(",");
		cgsgxxMapper.deleteByIds(Arrays.asList(idArr));
		
	}

	@Override
	@Transactional
	public void completeByIds(String ids) {
		String[] idArr = ids.split(",");
		cgsgxxMapper.completeByIds(Arrays.asList(idArr));

	}
}