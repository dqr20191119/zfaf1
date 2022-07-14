package com.cesgroup.prison.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import ces.sdk.system.bean.OrgInfo;
import com.ces.authsystem.entity.OrgEntity;
import com.ces.authsystem.entity.UserEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.common.constant.AuthSystemConst;
import com.cesgroup.prison.common.dao.PoliceMapper;
import com.cesgroup.prison.common.dto.OrgInfoDto;
import com.cesgroup.prison.common.dto.UserInfoDto;
import com.cesgroup.prison.common.entity.PoliceEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.service.PoliceService;

@Service("policeService")
public class PoliceServiceImpl extends BaseDaoService<PoliceEntity, String, PoliceMapper> implements PoliceService {

	@Resource
	private PoliceMapper policeMapper;

	@Override
	public List<PoliceEntity> findAllList(PoliceEntity policeEntity) {
		
		return policeMapper.findAllList(policeEntity);
	}

	/**
	 * 部门民警异步下拉树
	 */
	@Override
	public List<Map<String, Object>> findDeptPoliceForCombotree(Map<String, Object> paramMap) throws Exception {
		
		String id = String.valueOf(paramMap.get("id"));
		String cusNumber = String.valueOf(paramMap.get("cusNumber"));
		String mjxm = String.valueOf(paramMap.get("mjxm"));
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		if(cusNumber == null || "".equals(cusNumber) || "null".equals(cusNumber)) {
			cusNumber = AuthSystemFacade.getLoginUserInfo().getOrgCode();
		}
		
		if(id != null && !"".equals(id) && !"null".equals(id)) {
			// 加载警员信息
			PoliceEntity policeEntity = new PoliceEntity();
			policeEntity.setPbdCusNumber(cusNumber);
			policeEntity.setPbdDrptmntId(id);
			if(mjxm!="null"){policeEntity.setPbdPoliceName("%"+mjxm+"%");}
			List<PoliceEntity> policeList = policeMapper.findAllList(policeEntity);
			
			for(PoliceEntity police : policeList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("id", police.getPbdUserId());
				map.put("code", police.getPbdPoliceIdnty());
				map.put("name", police.getPbdPoliceName());
				map.put("type", "user");
				map.put("isParent", false);
				map.put("open", true);
				map.put("jwt", police.getPbdPoliceCmmnct());
				map.put("dh", police.getPbdFixedPhone());
				map.put("sj", police.getPbdPhone());
				map.put("jh", police.getPbdPoliceIdnty());
				resultList.add(map);
			}
		} else {
			// 加载部门信息
			List<OrgEntity> orgList = AuthSystemFacade.getAllChildrenOrgInfoByOrgKey(cusNumber);
			for(OrgEntity org : orgList) {
				Map<String, Object> map = new HashMap<String, Object>();
				String orgKey = org.getOrgKey();
				map.put("id", orgKey);
				map.put("code", org.getOrgKey());
				map.put("name", org.getOrgName());
				map.put("type", "org");
				map.put("isParent", true);
				map.put("open", false);	
				
				if(AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG.equals(cusNumber) && orgKey != null 
						&& orgKey.indexOf(AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG) == -1) {
					continue;
				}
				
				resultList.add(map);
			}
		}
		
		return resultList;
	}
	
	/**
	 * 部门民警同步下拉树
	 */
	@Override
	public List<Map<String, Object>> findSyncDeptPoliceForCombotree(Map<String, Object> paramMap) throws Exception {
		
		String cusNumber = String.valueOf(paramMap.get("cusNumber"));
		String id = String.valueOf(paramMap.get("id"));
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		if(cusNumber == null || "".equals(cusNumber) || "null".equals(cusNumber)) {
			cusNumber = AuthSystemFacade.getLoginUserInfo().getOrgCode();
		}
 
		// 加载警员信息
		PoliceEntity policeEntity = new PoliceEntity();
		policeEntity.setPbdCusNumber(cusNumber);
		List<PoliceEntity> policeList = policeMapper.findAllList(policeEntity);
		Map<String, List<Map<String, Object>>> policeMap = new HashMap<String, List<Map<String, Object>>>();
		
		for(PoliceEntity police : policeList) {			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", police.getPbdUserId());
			map.put("name", police.getPbdPoliceName());
			map.put("isParent", false);
			map.put("open", true);	
			
			if(policeMap.containsKey(police.getPbdDrptmntId())) {
				policeMap.get(police.getPbdDrptmntId()).add(map);
			} else {
				List<Map<String, Object>> policeMapList = new ArrayList<Map<String, Object>>();				
				policeMapList.add(map);
				policeMap.put(police.getPbdDrptmntId(), policeMapList);
			}			
		}
				
		// 加载部门信息
		List<OrgEntity> orgList = AuthSystemFacade.getAllChildrenOrgInfoByOrgKey(cusNumber);
		for(OrgEntity org : orgList) {
			Map<String, Object> map = new HashMap<String, Object>();
			String orgKey = org.getOrgKey();
			map.put("id", orgKey);
			map.put("name", org.getOrgName());
			
			if(policeMap.get(org.getOrgKey()) == null) {
				map.put("isParent", false);
				map.put("open", true);	
			} else {
				map.put("isParent", true);
				map.put("open", false);	
				map.put("children", policeMap.get(org.getOrgKey()));
			}
			
			if(AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG.equals(cusNumber) && orgKey != null 
					&& orgKey.indexOf(AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG) == -1) {
				continue;
			}
			
			resultList.add(map);
		}
	 		
		return resultList;
	}

	@Override
	public List<Map<String, Object>> findAllPoliceForAutocomplete(Map<String, Object> paramMap) {

		return policeMapper.findAllPoliceForAutocomplete(paramMap);
	}

	@Override
	public List<Map<String, Object>> findPoliceByAreaIdForCombobox(Map<String, Object> paramMap) {
		
		return policeMapper.findPoliceByAreaIdForCombobox(paramMap);
	}
	
	@Override
	public List<Map<String, Object>> findPoliceByDeptIdForCombobox(Map<String, Object> paramMap) throws Exception {
		
		String deptId = String.valueOf(paramMap.get("deptId"));
		String cusNumber = String.valueOf(paramMap.get("cusNumber"));
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		if(cusNumber == null || "".equals(cusNumber) || "null".equals(cusNumber)) {
			cusNumber = AuthSystemFacade.getLoginUserInfo().getOrgCode();
		}
		
		// 加载警员信息
		PoliceEntity policeEntity = new PoliceEntity();
		policeEntity.setPbdCusNumber(cusNumber);
		policeEntity.setPbdDrptmntId(deptId);
		List<PoliceEntity> policeList = policeMapper.findAllList(policeEntity);
		
		for(PoliceEntity police : policeList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", police.getPbdUserId());
			map.put("text", police.getPbdPoliceName());
			resultList.add(map);
		}
			
		return resultList;
	}

	@Override
	public UserInfoDto queryUserInfoDtoByUserId(String userId) throws ServiceException {
		try {
			UserEntity userEntity = AuthSystemFacade.getUserInfoByUserId(userId);
			if(userEntity == null) {
				return null;
			}
			UserInfoDto userInfoDto = new UserInfoDto();
			userInfoDto.setUserId(String.valueOf(userEntity.getUserId()));
			userInfoDto.setUserName(userEntity.getUserName());
			userInfoDto.setUserOrgCode(userEntity.getOrgKey());
			userInfoDto.setUserOrgName(userEntity.getOrgName());
			userInfoDto.setUserUnitCode(userEntity.getOrgUnitKey());
			userInfoDto.setUserUnitName(userEntity.getOrgUnit());
			userInfoDto.setPoliceAffair(userEntity.getPoliceaffair());
			return userInfoDto;
		} catch (Exception e) {
			throw new ServiceException("根据用户编号查询用户信息发生异常->" + e);
		}
	}

	@Override
	public OrgInfoDto queryOrgInfoDtoByOrgCode(String orgCode) throws ServiceException {
		try {
			// 根据机构编码获取机构信息
			OrgInfo orgInfo = AuthSystemFacade.getOrgByOrgCode(orgCode);
			if(orgInfo == null) {
				return null;
			}

			// 根据机构编码获取机构所属单位信息
			OrgInfo unitInfo = AuthSystemFacade.getUnitOrgInfoByOrgCode(orgCode);
			if(unitInfo == null) {
				return null;
			}

			// 声明并初始化返回值
			OrgInfoDto orgInfoDto = new OrgInfoDto();
			orgInfoDto.setOrgId(String.valueOf(orgInfo.getOrganizeID()));
			orgInfoDto.setOrgCode(orgInfo.getOrganizeCode());
			orgInfoDto.setOrgName(orgInfo.getOrganizeName());
			orgInfoDto.setUnitId(String.valueOf(unitInfo.getOrganizeID()));
			orgInfoDto.setUnitCode(unitInfo.getOrganizeCode());
			orgInfoDto.setUnitName(unitInfo.getOrganizeName());
			return orgInfoDto;
		} catch (Exception e) {
			throw new ServiceException("根据机构编号查询机构信息发生异常->" + e);
		}
	}

}
