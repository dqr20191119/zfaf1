package com.cesgroup.prison.model.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.model.entity.Model;
import com.cesgroup.prison.view.entity.View;

public interface ModelMapper extends BaseDao<Model,String>{
    int deleteByIds(List<String> ids);
    
    int saveModel(Model record);

    Model selectByPage(String id);

    int updateModel(Model record);

    Map<String, String> findByModel(Model model);

	Page<Map<String, String>> findByPage(Map<String, String> map, Pageable pageable);

	Model findById(String id);

	List<Model> find(Map<String, String> map);

	String selectIdByModel(Model model);
    
}