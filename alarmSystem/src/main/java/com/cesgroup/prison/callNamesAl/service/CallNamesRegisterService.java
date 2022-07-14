package com.cesgroup.prison.callNamesAl.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.callNamesAl.entity.CallNamesRegisterEntity;
import com.cesgroup.prison.common.entity.AffixEntity;

public interface CallNamesRegisterService extends IBaseCRUDService<CallNamesRegisterEntity, String> {

	public Page<Map<String, Object>> listAll(CallNamesRegisterEntity entity, Pageable pageable);

	/**
	* @methodName: addInfo
	* @Description: 新增入库，调用接口注册人脸
	* @param prisoner 罪犯编号
	* @throws Exception void
	* @throws  
	*/
	public String addInfo(List<String> prisoner) throws Exception;

	public void updateInfo(CallNamesRegisterEntity entity) throws Exception;

	public void deleteByIds(List<String> list);
	
	/**
	 * @throws Exception 
	* @methodName: logout
	* @Description: 注销操作
	* @param ids void
	* @throws  
	*/
	public String logout(List<String> ids) throws Exception;

	public CallNamesRegisterEntity findById(String id);

	/**
	* @methodName: addPrisonerPicFile
	* @Description: 上传本地罪犯照片
	* @param id 业务id
	* @param file void
	* @throws Exception 
	*/
	public String addPrisonerPicFile(String id, String file) throws Exception;

	/**
	* @methodName: findPrisonerPicFile
	* @Description: 查询本地上传照片
	* @param id  业务id
	* @return List<AffixEntity>
	* @throws  
	*/
	public List<AffixEntity> findPrisonerPicFile(String id);

	/**
	* @methodName: findForTree
	* @Description: 新增界面区域监舍树
	* @param paramMap
	* @return List<Map<String,Object>>
	* @throws  
	*/
	List<Map<String, Object>> findForTree(Map<String, Object> paramMap);

	List<Map<String, Object>> findPrisonerByJs(Map<String, Object> paramMap);

	List<Map<String, Object>> findRegisterPrisonerByJs(Map<String, Object> paramMap);

}
