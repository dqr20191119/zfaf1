package com.cesgroup.prison.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.prison.model.entity.Model;


/**
 * 模型业务逻辑接口
 * @author hurenjie
 *
 */
@Service
public interface ModelService {

	/**
	 * 保存模型
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public int saveModel(Model model) throws Exception;
	

	/**
	 * 更新Model
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	public int updateModel(Model model) throws Exception;
	
	/**
	 * 根据ID删除
	 * @param id
	 * @return
	 */
	public int deleteModelByIds(List<String> ids);
	
	/**
	 * 根据模型编号获取到模型
	 * @param modelNo
	 * @return
	 */
	public Map<String, String> findByModel(Model model);

	/**
	 * 根据监狱编号=，区域编号like,模型名称like查询分页查询
	 * @param view
	 * @param request
	 * @return
	 */
    public Page<Map<String,String>> findByPage(Map<String,String> map,PageRequest request);


	/**
	 * 根据id查询
	 * @param id
	 * @return
	 */
	public Model findById(String id);

	/**
	 * 根据监狱编号=，区域编号like,模型名称like查询查询
	 * @param view
	 * @param request
	 * @return
	 */
	public List<Model> find(Map<String, String> map);
	
}
