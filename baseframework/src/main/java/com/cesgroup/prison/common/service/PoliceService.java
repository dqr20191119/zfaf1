package com.cesgroup.prison.common.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.common.dto.OrgInfoDto;
import com.cesgroup.prison.common.dto.UserInfoDto;
import com.cesgroup.prison.common.entity.PoliceEntity;

public interface PoliceService extends IBaseCRUDService<PoliceEntity, String> {

	public List<PoliceEntity> findAllList(PoliceEntity policeEntity);

	public List<Map<String, Object>> findDeptPoliceForCombotree(Map<String, Object> paramMap) throws Exception;
	
	public List<Map<String, Object>> findSyncDeptPoliceForCombotree(Map<String, Object> paramMap) throws Exception;

	public List<Map<String, Object>> findPoliceByAreaIdForCombobox(Map<String, Object> paramMap);
	
	public List<Map<String, Object>> findPoliceByDeptIdForCombobox(Map<String, Object> paramMap) throws Exception;

	List<Map<String, Object>> findAllPoliceForAutocomplete(Map<String, Object> paramMap);

	/**
	 * 根据用户编号查询用户信息
	 *
	 * @param userId
	 * @return
	 * @throws ServiceException
	 */
	UserInfoDto queryUserInfoDtoByUserId(String userId) throws ServiceException;

	/**
	 * 根据机构编码，查询机构信息
	 * @param orgCode
	 * @return
	 * @throws ServiceException
	 */
	OrgInfoDto queryOrgInfoDtoByOrgCode(String orgCode) throws ServiceException;
}
