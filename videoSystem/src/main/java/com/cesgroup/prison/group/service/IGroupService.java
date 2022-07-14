package com.cesgroup.prison.group.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.prison.group.entity.Group;

/**   
*    
* @projectName：prison   
* @ClassName：IGroupService   
* @Description：   群组
* @author：zhengke   
* @date：2017-12-10 21:49   
* @version        
*/
public interface IGroupService {
	/**
	* @methodName: getTree
	* @Description: 根据父ID和使用范围查询群组
	* @param id
	* @param gmaUseRange使用范围 0、公共，1、自定义
	* @param gmaCusNumber监所编号
	* @return List<Map<String,Object>>    
	* @throws
	*/
	public List<Map<String, Object>> getTree(Group group);
	
	/**
	* @methodName: getMemberTree
	* @Description: 查询群组成员树
	* @param id
	* @param gmaUseRange使用范围 0、公共，1、自定义
	* @param gmaCusNumber监所编号
	* @param gmaTypeIndc群组类别 1、摄像机，2、门禁，3、广播，4、大屏
	* @return List<Map<String,Object>>    
	* @throws
	*/
	//public List<Map<String,Object>> getGroupMemberTree(String id,String gmaUseRange,String gmaCusNumber,String gmaTypeIndc);
	
	public List<Map<String,Object>> simpleGroupMemberTree(Group group)  throws Exception;
	
	public List<Map<String,Object>> simpleGroupTree(Group group) throws Exception;
	
	public Group findById(String id);
	
	/**
	* @methodName: deleteGroup
	* @Description: 级联删除群组
	* @param id void    
	* @throws
	*/
	public void deleteGroup(String id);

	//局部修改
	public void updatePart(Group group_param);
	
	/**
	 * 根据监狱编号、工作组名称，查询工作组列表
	 * @param cusNumber
	 * @param grpName
	 * @return
	 */
	public List<Group> queryByGmaCusNumberAndGmaGrpName(String cusNumber, String grpName);
	
	
	
	public List queryCarame(String cusNumber, String grpName);
}
