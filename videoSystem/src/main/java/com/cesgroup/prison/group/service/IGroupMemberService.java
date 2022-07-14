package com.cesgroup.prison.group.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.group.entity.GroupMember;

/**   
*    
* @projectName：prison   
* @ClassName：IGroupMemberService   
* @Description：群组成员   
* @author：zhengke   
* @date：2017-12-10 21:07   
* @version        
*/
public interface IGroupMemberService extends IBaseCRUDService<GroupMember, String> {

	/**
	 * 根据 单位编号、组ID、成员类型 查询群组成员信息
	 *
	 * @param grdCusNumber
	 * @param grdGrpId
	 * @param grdTypeIndc
	 * @return
	 * @throws ServiceException
	 */
	public List<GroupMember> queryByGrdCusNumberAndGrdGrpIdAndGrdTypeIndc(String grdCusNumber, String grdGrpId, String grdTypeIndc) throws ServiceException;

	/**
	 * 根据 单位编号、组ID、成员类型 查询成员（用于树展示）
	 * @param grdCusNumber
	 * @param grdGrpId
	 * @param grdTypeIndc
	 * @return
	 */
	public List<Map<String, Object>> queryTreeByGrdCusNumberAndGrdGrpIdAndGrdTypeIndc(String grdCusNumber, String grdGrpId, String grdTypeIndc);
	
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
	public void delByGrdGrpId(String grdGrpId);
}
