package com.cesgroup.prison.group.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.group.dao.GroupMapper;
import com.cesgroup.prison.group.dao.GroupMemberMapper;
import com.cesgroup.prison.group.entity.Group;
import com.cesgroup.prison.group.entity.GroupMember;
import com.cesgroup.prison.group.service.IGroupService;
@Service
public class GroupServiceImpl extends BaseDaoService<Group,String,GroupMapper> implements IGroupService{
	@Autowired
	private GroupMemberMapper groupMemberMapper;
	
	/**
	* @methodName: getTree
	* @Description: 根据父ID和使用范围查询群组（异步树）
	* @param id
	* @param gmaUseRange使用范围 0、公共，1、自定义
	* @param gmaCusNumber监所编号
	* @return List<Map<String,Object>>    
	* @throws
	*/
	public List<Map<String, Object>> getTree(Group group) {
		String gmaUseRange=group.getGmaUseRange();
		String id=group.getId();
		String gmaCusNumber=group.getGmaCusNumber();
		
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		
		if("".equals(id)|| null==id){
			Map<String, Object> map = new HashMap<String, Object>();
			//取所有预案
			if("".equals(gmaUseRange)|| null==gmaUseRange){
				map.put("name", com.cesgroup.prison.constants.Group.TREE_ROOT_NAME);
				map.put("id",com.cesgroup.prison.constants.Group.TREE_ROOT_VALUE);
				map.put("isParent", true);
				List<Map<String, Object>> mapChild=new ArrayList<Map<String, Object>>();
				Map<String, Object> commonMap=new HashMap<String, Object>();
				Map<String, Object> diyMap=new HashMap<String, Object>();
				commonMap.put("name", com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_NAME);
				commonMap.put("id",com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_VALUE);
				commonMap.put("isParent", true);
				diyMap.put("name", com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_NAME);
				diyMap.put("id",com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_VALUE);
				diyMap.put("isParent", true);
				mapChild.add(commonMap);
				mapChild.add(diyMap);
				map.put("children", mapChild);
			}else{
			//公共预案
			if("0".equals(gmaUseRange)){
				map.put("name", com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_NAME);
				map.put("id",com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_VALUE);
				map.put("open", false);
				List<Map<String, Object>> mapChild=getTreeData(com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_VALUE,gmaUseRange,gmaCusNumber);
				if(mapChild!=null&&mapChild.size()>0){
					map.put("children", mapChild);
				}
			}else{//自定义预案
				map.put("name", com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_NAME);
				map.put("id",com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_VALUE);
				map.put("open", false);
				List<Map<String, Object>> mapChild=getTreeData(com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_VALUE,gmaUseRange,gmaCusNumber);
				if(mapChild!=null&&mapChild.size()>0){
					map.put("children", mapChild);
				}
			}
			}
			maps.add(map);
		}else{
			maps=getTreeData(id,gmaUseRange,gmaCusNumber);
			
		}
		return maps;
	}

	/**
	* @methodName: getTreeData
	* @Description: 根据父ID和使用范围查询群组
	* @param id
	* @param gmaUseRange使用范围 0、公共，1、自定义
	* @param gmaCusNumber监所编号
	* @return List<Map<String,Object>>    
	* @throws
	*/
	private List<Map<String, Object>> getTreeData(String id,String gmaUseRange,String gmaCusNumber){
		List<Group> groupList=this.getDao().findByGmaParentIdAndGmaUseRangeAndGmaCusNumber(id,gmaUseRange,gmaCusNumber);		
		List<Map<String, Object>> mapChild =  new ArrayList<Map<String, Object>>();
		for (Group group : groupList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", group.getGmaGrpName());
			map.put("id", group.getId());
			map.put("open", true);
			String isLeaf=group.getGmaIsLeaf();
			if ("0".equals(isLeaf)) {// 非叶子节点
				map.put("isParent", true);
			} else {
				map.put("isParent", false);
			}
			mapChild.add(map);
			
		}
		return mapChild;
	}
	
	/**
	 * @throws Exception 
	* @methodName: simpleGroupTree
	* @Description: 查询群组树（同步树）  
	* @throws
	*/
	/*public List<Map<String,Object>> simpleGroupTree(Group group) throws Exception{
		//用户登陆信息从后台获取
		UserBean userBean=AuthSystemFacade.getLoginUserInfo();
		String userId=userBean.getUserId();
		String gmaCrteDepartment=userBean.getDprtmntCode();
				
		List<Map<String, Object>> groupList =  new ArrayList<Map<String, Object>>();
		
		String gmaUseRange=group.getGmaUseRange();
		String groupId=group.getId();
		String gmaCusNumber=group.getGmaCusNumber();
		String gmaTypeIndc=group.getGmaTypeIndc();
		String gmaSubTypeIndc=group.getGmaSubTypeIndc();
		//String userId=group.getGmaCrteUserId();
		//String gmaCrteDepartment=group.getGmaCrteDepartment();
		
		Map<String, Object> commonMap=new HashMap<String, Object>();
		commonMap.put("id", com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_VALUE);
		commonMap.put("pId", com.cesgroup.prison.constants.Group.TREE_ROOT_VALUE);
		commonMap.put("name",com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_NAME);
		commonMap.put("open", true);
		commonMap.put("node_type", "group");
		commonMap.put("cusNumber", gmaCusNumber);
		Map<String, Object> diyMap=new HashMap<String, Object>();
		diyMap.put("id",com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_VALUE);
		diyMap.put("pId",com.cesgroup.prison.constants.Group.TREE_ROOT_VALUE);
		diyMap.put("name",com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_NAME);
		diyMap.put("open", true);
		diyMap.put("node_type", "group");
		diyMap.put("cusNumber", gmaCusNumber);
		
		Group group_param=new Group();
		group_param.setGmaCusNumber(gmaCusNumber);
		group_param.setGmaTypeIndc(gmaTypeIndc);
		group_param.setGmaSubTypeIndc(gmaSubTypeIndc);
		group_param.setId(groupId);
		
		//取所有预案
		if("".equals(gmaUseRange)|| null==gmaUseRange){
			group.setGmaUseRange("0");
			groupList.addAll(simpleGroupTree(group));
			group.setGmaUseRange("1");
			groupList.addAll(simpleGroupTree(group));
			return groupList;
		//公共预案
		}else if("0".equals(gmaUseRange)){
			groupList.add(commonMap);
			group_param.setGmaUseRange(gmaUseRange);
			group_param.setGmaCrteDepartment(gmaCrteDepartment);
		//自定义预案
		}else if("1".equals(gmaUseRange)) {
			groupList.add(diyMap);
			group_param.setGmaUseRange(gmaUseRange);
			group_param.setGmaCrteUserId(userId);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");  
		List<Group> tmpList=this.getDao().searchByXML(group_param);
		for (Group grp : tmpList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", grp.getId());
			map.put("pId", grp.getGmaParentId());
			map.put("name", grp.getGmaGrpName());
			map.put("node_type", "group");
			map.put("cusNumber", grp.getGmaCusNumber());
			map.put("startTime", grp.getGmaStartTime()==null ? null : sdf.format(grp.getGmaStartTime()));
			map.put("endTime", grp.getGmaEndTime()==null ? null : sdf.format(grp.getGmaEndTime()));
			groupList.add(map);
		}
		
		return groupList;
	}*/
	public List<Map<String,Object>> simpleGroupTree(Group group) throws Exception{
		//用户登陆信息从后台获取
		UserBean userBean=AuthSystemFacade.getLoginUserInfo();
		String userId=userBean.getUserId();
		String gmaCrteDepartment=userBean.getDprtmntCode();
				
		List<Map<String, Object>> groupList =  new ArrayList<Map<String, Object>>();
		
		String gmaUseRange=group.getGmaUseRange();
		String groupId=group.getId();
		String gmaCusNumber=group.getGmaCusNumber();
		String gmaTypeIndc=group.getGmaTypeIndc();
		String gmaSubTypeIndc=group.getGmaSubTypeIndc();
		//String userId=group.getGmaCrteUserId();
		//String gmaCrteDepartment=group.getGmaCrteDepartment();
		
		Map<String, Object> commonMap=new HashMap<String, Object>();
		commonMap.put("id", com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_VALUE);
		commonMap.put("pId", com.cesgroup.prison.constants.Group.TREE_ROOT_VALUE);
		commonMap.put("name",com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_NAME);
		commonMap.put("open", true);
		commonMap.put("node_type", "group");
		commonMap.put("cusNumber", gmaCusNumber);
		Map<String, Object> diyMap=new HashMap<String, Object>();
		diyMap.put("id",com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_VALUE);
		diyMap.put("pId",com.cesgroup.prison.constants.Group.TREE_ROOT_VALUE);
		diyMap.put("name",com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_NAME);
		diyMap.put("open", true);
		diyMap.put("node_type", "group");
		diyMap.put("cusNumber", gmaCusNumber);
		
		Group group_param=new Group();
		group_param.setGmaCusNumber(gmaCusNumber);
		group_param.setGmaTypeIndc(gmaTypeIndc);
		group_param.setGmaSubTypeIndc(gmaSubTypeIndc);
		group_param.setId(groupId);
		
		group_param.setGmaUseRange(gmaUseRange);
		group_param.setGmaCrteDepartment(gmaCrteDepartment);
		group_param.setGmaCrteUserId(userId);
		
		//取所有预案
		if("".equals(gmaUseRange)|| null==gmaUseRange){
			groupList.add(commonMap);
			groupList.add(diyMap);
		//公共预案
		}else if("0".equals(gmaUseRange)){
			groupList.add(commonMap);
		//自定义预案
		}else if("1".equals(gmaUseRange)) {
			groupList.add(diyMap);
		}
		
		List<Map<String,Object>> tmpList=this.getDao().simpleGroupTree(group_param);
		groupList.addAll(tmpList);
		return groupList;
	}

	/**
	* @methodName: simpleGroupMemberTree
	* @Description: 查询群组成员树(同步树)  
	* @throws
	*/
	public List<Map<String,Object>> simpleGroupMemberTree(Group group)  throws Exception{
	
		String gmaTypeIndc=group.getGmaTypeIndc();
	
		List<Map<String,Object>> groupList=simpleGroupTree(group);
		
		List<Map<String,Object>> groupMemberList=new ArrayList<Map<String,Object>>();
		
		//摄像机
		if("1".equals(gmaTypeIndc)){
			//子节点加载成员if("1".equals(group.getGmaIsLeaf()))
			//此处遍历所有群组加载成员
			groupMemberList=groupMemberMapper.simpleMemberTree(groupList);
			
		}
		groupList.addAll(groupMemberList);
		return groupList;
	}
	
	/**
	* @methodName: getGroupMemberTree
	* @Description: 查询群组成员树(异步树)
	* @param id
	* @param gmaUseRange使用范围 0、公共，1、自定义
	* @param gmaCusNumber监所编号
	* @param gmaTypeIndc群组类别 1、摄像机，2、门禁，3、广播，4、大屏
	* @return List<Map<String,Object>>    
	* @throws
	*/
	/*public List<Map<String,Object>> getGroupMemberTree(String id,String gmaUseRange,String gmaCusNumber,String gmaTypeIndc){
		
				List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
				Map<String, Object> map = new HashMap<String, Object>();
				if("".equals(id)|| null==id){
					//取所有预案
					if("".equals(gmaUseRange)|| null==gmaUseRange){
						map.put("name", com.cesgroup.prison.constants.Group.TREE_ROOT_NAME);
						map.put("id",com.cesgroup.prison.constants.Group.TREE_ROOT_VALUE);
						map.put("isParent", true);
						List<Map<String, Object>> mapChild=new ArrayList<Map<String, Object>>();
						Map<String, Object> commonMap=new HashMap<String, Object>();
						Map<String, Object> diyMap=new HashMap<String, Object>();
						commonMap.put("name", com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_NAME);
						commonMap.put("id",com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_VALUE);
						commonMap.put("isParent", true);
						diyMap.put("name", com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_NAME);
						diyMap.put("id",com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_VALUE);
						diyMap.put("isParent", true);
						mapChild.add(commonMap);
						mapChild.add(diyMap);
						map.put("children", mapChild);
					}else{
						//公共预案
						if("0".equals(gmaUseRange)){
							map.put("name", com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_NAME);
							map.put("id",com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_VALUE);
							map.put("open", true);
							List<Map<String, Object>> mapChild=getGroupMemberTreeData(com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_VALUE,gmaUseRange,gmaCusNumber,gmaTypeIndc);
							if(mapChild!=null&&mapChild.size()>0){
								map.put("children", mapChild);
							}
						}else{//自定义预案
							map.put("name", com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_NAME);
							map.put("id",com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_VALUE);
							map.put("open", true);
							List<Map<String, Object>> mapChild=getGroupMemberTreeData(com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_VALUE,gmaUseRange,gmaCusNumber,gmaTypeIndc);
							if(mapChild!=null&&mapChild.size()>0){
								map.put("children", mapChild);
							}
						}
					}
					
					maps.add(map);
				}else{
					Group group=this.getDao().findById(id);
					//判断是否是子节点，如果是加载成员信息，否则继续执行
					if("1".equals(group.getGmaIsLeaf())){
						
						//加载成员信息
						Map<String, Object> paramMap=new HashMap<String, Object>();
						paramMap.put("grdGrpId", group.getId());
						paramMap.put("grdCusNumber", gmaCusNumber);
						paramMap.put("grdTypeIndc", gmaTypeIndc);		
						List<Map<String, Object>> groupMemberList=groupMemberMapper.searchByGrdGrpIdAndGrdCusNumberAndGmaTypeIndc(paramMap);
						
						if(groupMemberList!=null&&groupMemberList.size()>0){
							maps=groupMemberList;
						}
						
					}else{
						maps=getGroupMemberTreeData(id,gmaUseRange,gmaCusNumber,gmaTypeIndc);
					}
					
					
				}
				return maps;
	}
	@Transactional
	private List<Map<String, Object>> getGroupMemberTreeData(String id,String gmaUseRange,String gmaCusNumber,String gmaTypeIndc){
		List<Group> groupList=this.getDao().findByGmaParentIdAndGmaUseRangeAndGmaCusNumber(id,gmaUseRange,gmaCusNumber);		
		List<Map<String, Object>> mapChild =  new ArrayList<Map<String, Object>>();
		for (Group group : groupList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", group.getGmaGrpName());
			map.put("id", group.getId());
			map.put("open", true);
			String isLeaf=group.getGmaIsLeaf();
			if ("0".equals(isLeaf)) {// 非叶子节点
				map.put("isParent", true);
			} else {//叶子节点,下面需要列出成员所以设置isParent为true
				map.put("isParent", true);
			}
			mapChild.add(map);
			
		}
		return mapChild;
	}
	*/
	
	public Group findById(String id){
		return this.getDao().findById(id);
	}
	/**
	* @methodName: deleteGroup
	* @Description: 级联删除群组
	* @param id void    
	* @throws
	*/
	@Transactional
	public void deleteGroup(String id) {
		//id是公共预案或自定义预案
		if(id.equals(com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_VALUE)||id.equals(com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_VALUE)) {
			return;
		}
		List<Group> list=this.getDao().findByGmaParentId(id);
		//如果请先删除子级节点
		if(list.size()>0){
			return;
		}else {
			groupMemberMapper.delByGrdGrpId(id);
			delete(id);
		}
		
	}
	
	@Override
	@Transactional
    public Group insert(final Group entity) {
		String parentId=entity.getGmaParentId();
		//父id是公共预案或自定义预案
		if(parentId.equals(com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_VALUE)||parentId.equals(com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_VALUE)) {
			
		}
		else {
			Group group=this.getDao().findById(parentId);
			String isLeaf=group.getGmaIsLeaf();
			if("1".equals(isLeaf)){//如果是叶子节点，更改为父节点
				this.getDao().updateIsLeaf(parentId,"0");
			}
			//新增节点为叶子节点
			entity.setGmaIsLeaf("1");
		}
		
		this.getDao().insert(entity);
        return entity;
    }
	@Override
    @Transactional
    public void delete(final String id) {
		//id是公共预案或自定义预案
		if(id.equals(com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_VALUE)||id.equals(com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_VALUE)) {
			return;
		}
		Group group=this.getDao().findById(id);
		List<Group> childs=this.getDao().findByGmaParentId(group.getGmaParentId());
		//如果删除节点的父节点只有一个子节点，则把该父节点设置为叶子节点
		if(childs.size()<=1){
			this.getDao().updateIsLeaf(group.getGmaParentId(), "1");
		}
        getDao().delete(id);
    }
	@Override
    @Transactional
    public Group update(final Group entity) {
		//操作修改前的父节点
		String id=entity.getId();
		//id是公共预案或自定义预案
		if(id.equals(com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_VALUE)||id.equals(com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_VALUE)) {
			return entity;
		}
		Group p_group=this.getDao().findById(id);
		List<Group> childs=this.getDao().findByGmaParentId(p_group.getGmaParentId());
		//如果删除节点的父节点只有一个子节点，则把该父节点设置为叶子节点
		if(childs.size()<=1){
			this.getDao().updateIsLeaf(p_group.getGmaParentId(), "1");
		}
		//操作修改后的父节点
		
		String parentId=entity.getGmaParentId();
		//父id是公共预案或自定义预案
		if(parentId.equals(com.cesgroup.prison.constants.Group.DIY_TREE_ROOT_VALUE)||parentId.equals(com.cesgroup.prison.constants.Group.COMMON_TREE_ROOT_VALUE)) {
			
		}
		else {
			Group group=this.getDao().findById(entity.getGmaParentId());
			String isLeaf=group.getGmaIsLeaf();
			if("1".equals(isLeaf)){//如果是叶子节点，更改为父节点
				this.getDao().updateIsLeaf(entity.getGmaParentId(),"0");
			}
		}
        getDao().update(entity);
        return entity;
    }
	
	//局部修改
	@Transactional
	public void updatePart(Group group_param){
		this.getDao().updatePart(group_param);
	}
	
	//局部修改
	@Override
	public List<Group> queryByGmaCusNumberAndGmaGrpName(String cusNumber, String grpName) {
		List<Group> groupList = this.getDao().findByGmaCusNumberAndGmaGrpName(cusNumber, grpName);
		return groupList;
	}

	@Override
	public List queryCarame(String cusNumber, String grpName) {
		// TODO Auto-generated method stub
		Map map = new HashMap();
		map.put("gmaCusNumber", cusNumber);
		map.put("gmaGrpName",grpName );
		List list = this.getDao().queryCarame(map);
		return list;
	}
}
