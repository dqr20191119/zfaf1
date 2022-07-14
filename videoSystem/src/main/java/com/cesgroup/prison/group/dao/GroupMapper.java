package com.cesgroup.prison.group.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.group.entity.Group;

/**   
*    
* @projectName：prison   
* @ClassName：GroupMapper   
* @Description：   群组
* @author：zhengke   
* @date：2017-12-10 21:50   
* @version        
*/
public interface GroupMapper  extends BaseDao<Group,String>{

	public Group findById(String id);
	public List<Group> findByGmaParentId(String gmaParentId);
	/**
	* @methodName: findByGmaParentIdAndGmaUseRangeAndGmaCusNumber
	* @Description: 根据父ID和使用范围查询群组
	* @param parentId
	* @param gmaUseRange使用范围 0、公共，1、自定义
	* @param gmaCusNumber监所编号
	* @return List<Group>    
	* @throws
	*/
	public List<Group> findByGmaParentIdAndGmaUseRangeAndGmaCusNumber(String parentId,String gmaUseRange,String gmaCusNumber);
	
	@Update("update CDS_GRP_MASTER set GMA_IS_LEAF=#{isLeaf} where id=#{id}")
	public int updateIsLeaf(@Param("id")String id,@Param("isLeaf")String isLeaf);
	
	public int updatePart(Group record);
	
	public List<Group> searchByXML(Group record);
	
	public List<Map<String,Object>> simpleGroupTree(Group record);
	
	/**
	 * 根据监狱编号、工作组名称，查询工作组列表
	 * @param gmaCusNumber
	 * @param gmaGrpName
	 * @return
	 */
	public List<Group> findByGmaCusNumberAndGmaGrpName(@Param("gmaCusNumber") String gmaCusNumber, @Param("gmaGrpName") String gmaGrpName);

	
	/**
	 * 根据id查询对应的摄像机设备列表
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> queryCarame(Map map);

}
