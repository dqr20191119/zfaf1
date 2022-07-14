package com.cesgroup.prison.xxhj.cgsgxx.service;

import java.util.List;

import com.cesgroup.prison.xxhj.cgsgxx.vo.CgsgxxVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.cgsgxx.entity.CgsgxxEntity;

public interface CgsgxxService extends IBaseCRUDService<CgsgxxEntity, String> {
	
	public void saveOrUpdate(CgsgxxEntity cgsgxxEntity) throws Exception;
	
	public CgsgxxEntity getById(String id);
	
	public Page<CgsgxxEntity> findList(CgsgxxVo cgsgxxVo, PageRequest pageRequest);
	
	public void deleteByIds(String ids);

	public void completeByIds(String ids);
}