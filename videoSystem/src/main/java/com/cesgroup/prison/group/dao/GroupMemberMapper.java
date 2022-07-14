package com.cesgroup.prison.group.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.group.entity.GroupMember;

/**   
*    
* @projectName：prison   
* @ClassName：GroupMemberMapper   
* @Description：  群组成员 
* @author：zhengke   
* @date：2017-12-10 21:07   
* @version        
*/
public interface GroupMemberMapper extends BaseDao<GroupMember,String>{
	/**
	* @methodName: searchByGrdGrpIdAndGrdCusNumberAndGmaTypeIndc
	* @Description: 根据群组ID和监所编号和类型查询成员（用于树展示）
	* @param map
	* @return List<Map<String,Object>>    
	* @throws
	*/
	public List<Map<String,Object>> searchByGrdGrpIdAndGrdCusNumberAndGmaTypeIndc(Map<String,Object> map);
	
	public List<Map<String,Object>> simpleMemberTree(List<Map<String,Object>> list);
	
	/**
	* @methodName: batchInsert
	* @Description: 批量新增
	* @param list 
	* @return void    
	* @throws
	*/
	public void batchInsert(List<GroupMember> list);
	/**
	* @methodName: batchDelete
	* @Description: 批量删除
	* @param list
	* @return void    
	* @throws
	*/
	public void batchDelete(List<String> list);
	
	/**
	* @methodName: delByGrdGrpId
	* @Description: 通过群组删成员
	* @param grdGrpId
	* @return void    
	* @throws
	*/
	public void delByGrdGrpId(@Param("grdGrpId")String grdGrpId);

	/**
	 * 根据 单位编号、组ID、成员类型 查询群组成员信息
	 * @param grdCusNumber
	 * @param grdGrpId
	 * @param grdTypeIndc
	 * @return
	 */
	public List<GroupMember> findByGrdCusNumberAndGrdGrpIdAndGrdTypeIndc(@Param("grdCusNumber") String grdCusNumber, @Param("grdGrpId") String grdGrpId, @Param("grdTypeIndc") String grdTypeIndc);
}
