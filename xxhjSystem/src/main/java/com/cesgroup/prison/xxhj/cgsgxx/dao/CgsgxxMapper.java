package com.cesgroup.prison.xxhj.cgsgxx.dao;

import java.util.List;

import com.cesgroup.prison.xxhj.cgsgxx.vo.CgsgxxVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xxhj.cgsgxx.entity.CgsgxxEntity;

public interface CgsgxxMapper extends BaseDao<CgsgxxEntity, String> {

	public CgsgxxEntity getById(String id);

	public Page<CgsgxxEntity> findList(CgsgxxVo CgsgxxVo, PageRequest pageRequest);
	
	public List<CgsgxxEntity> findAllList(CgsgxxEntity CgsgxxEntity); 

	public void deleteByIds(List<String> idList);

	public void completeByIds(List<String> idList);

}