package com.cesgroup.prison.model.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.model.dao.ModelMapper;
import com.cesgroup.prison.model.entity.Model;
import com.cesgroup.prison.model.service.ModelService;
import com.cesgroup.prison.utils.CommonUtil;

@Service
public class ModelServiceImpl extends BaseService<Model, String> implements ModelService{

	@Autowired
	ModelMapper modelMapper;
	
	@Override
	@Transactional
	public int saveModel(Model model) throws Exception {
		UserBean userBean= AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		model.setMinUpdtUserId(userId);
		model.setMinUpdtTime(new Date());
		Integer result;
		if(model.getId() == null || "".equals(model.getId())){
			String id = modelMapper.selectIdByModel(model);
			if(id == null || "".equals(id)){
				//重复判断
				model.setId(CommonUtil.createUUID().replace("-", ""));
				model.setMinCrteUserId(userId);
				model.setMinCrteTime(new Date());
				result = modelMapper.saveModel(model);
			}else{
				model.setId(id);
				result = modelMapper.updateModel(model);
			}
		}else{
			result = modelMapper.updateModel(model);
		}
		return result;
	}

	@Override
	@Transactional
	public int updateModel(Model model) throws Exception {
		UserBean userBean= AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		model.setMinUpdtUserId(userId);
		model.setMinUpdtTime(new Date());
		return modelMapper.updateModel(model);
	}

	@Override
	@Transactional
	public int deleteModelByIds(List<String> ids) {
		return modelMapper.deleteByIds(ids);
	}

	@Override
	public Map<String, String> findByModel(Model model) {
		return modelMapper.findByModel(model);
	}

	@Override
	public Page<Map<String, String>> findByPage(Map<String, String> map, PageRequest request) {
		return modelMapper.findByPage(map,request);
	}

	@Override
	public Model findById(String id) {
		return modelMapper.findById(id);
	}

	@Override
	public List<Model> find(Map<String, String> map) {
		return modelMapper.find(map);
	}
	

}
