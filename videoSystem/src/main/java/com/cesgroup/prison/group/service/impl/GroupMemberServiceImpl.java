package com.cesgroup.prison.group.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.group.dao.GroupMemberMapper;
import com.cesgroup.prison.group.entity.GroupMember;
import com.cesgroup.prison.group.service.IGroupMemberService;
import com.cesgroup.prison.utils.CommonUtil;
import com.google.common.collect.Lists;
/**   
*    
* @projectName：prison   
* @ClassName：GroupMemberServiceImpl   
* @Description：   群组成员
* @author：zhengke   
* @date：2017-12-10 21:07   
* @version        
*/
@Service
public class GroupMemberServiceImpl extends BaseDaoService<GroupMember, String, GroupMemberMapper> implements IGroupMemberService {

	@Override
	public List<GroupMember> queryByGrdCusNumberAndGrdGrpIdAndGrdTypeIndc(String grdCusNumber, String grdGrpId, String grdTypeIndc) throws ServiceException {
		try {
			return this.getDao().findByGrdCusNumberAndGrdGrpIdAndGrdTypeIndc(grdCusNumber, grdGrpId, grdTypeIndc);
		} catch (Exception e) {
			throw new ServiceException("根据 单位编号、组ID、成员类型 查询群组成员信息发生异常，原因：" + e);
		}
	}

	@Override
	public List<Map<String, Object>> queryTreeByGrdCusNumberAndGrdGrpIdAndGrdTypeIndc(String grdCusNumber, String grdGrpId, String grdTypeIndc) {
		List<GroupMember> groupMemberList = null;
		try {
			groupMemberList = this.getDao().findByGrdCusNumberAndGrdGrpIdAndGrdTypeIndc(grdCusNumber, grdGrpId, grdTypeIndc);
		} catch (Exception e) {
			throw new ServiceException("根据 单位编号、组ID、成员类型 查询群组成员信息发生异常，原因：" + e);
		}
		if(groupMemberList == null || groupMemberList.size() <= 0) {
			return null;
		}

		List<Map<String, Object>> treeList = new ArrayList<Map<String, Object>>();
		Map<String, Object> tree = null;
		for(GroupMember groupMember : groupMemberList) {
			tree = new LinkedHashMap<String, Object>();
			tree.put("id", groupMember.getGrdMmbrIdnty());
			tree.put("name", groupMember.getGrdMmbrName());
			tree.put("isParent", 0);
			treeList.add(tree);
		}
		return treeList;
	}

	/**
	* @methodName: batchInsert
	* @Description: 批量新增
	* @param list void    
	* @throws
	*/
	@Transactional
	public void batchInsert(List<GroupMember> list){
		for (GroupMember groupMember : list) {
			groupMember.setId(CommonUtil.createUUID());
		}
		if(list.size()>0) {
			List<List<GroupMember>> assignList =Lists.partition(list, 99);
			for (int i = 0; i < assignList.size(); i++) {
				this.getDao().batchInsert(assignList.get(i));
			}
		}	
	}
	
	/**
	* @methodName: batchDelete
	* @Description: 批量删除
	* @param list
	* @return void    
	* @throws
	*/
	public void batchDelete(List<String> list){
		this.getDao().batchDelete(list);
	}
	/**
	* @methodName: delByGrdGrpId
	* @Description: 通过群组删成员
	* @param grdGrpId
	* @return void    
	* @throws
	*/
	@Transactional
	public void delByGrdGrpId(String grdGrpId){
		this.getDao().delByGrdGrpId(grdGrpId);
	}
}
