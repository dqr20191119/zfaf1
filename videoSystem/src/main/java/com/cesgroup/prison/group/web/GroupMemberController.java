package com.cesgroup.prison.group.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.group.dao.GroupMemberMapper;
import com.cesgroup.prison.group.entity.GroupMember;
import com.cesgroup.prison.group.service.IGroupMemberService;
import com.cesgroup.prison.group.service.impl.GroupMemberServiceImpl;
/**   
*    
* @projectName：prison   
* @ClassName：GroupMemberController   
* @Description：   群组成员
* @author：zhengke   
* @date：2017-12-10 21:07   
* @version        
*/
@Controller
@RequestMapping(value = "/groupMember")
public class GroupMemberController extends BaseEntityDaoServiceCRUDController<GroupMember, String, GroupMemberMapper, IGroupMemberService> {

	@RequestMapping("/queryTree")
	@ResponseBody
	public Map<String,Object> queryTreeByGrdCusNumberAndGrdGrpIdAndGrdTypeIndc(HttpServletRequest request, HttpServletResponse response) {
		String grdCusNumber = request.getParameter("grdCusNumber");
		String grdGrpId = request.getParameter("grdGrpId");
		String grdTypeIndc = request.getParameter("grdTypeIndc");

		List<Map<String, Object>> treeList = null;
		try {
			treeList = this.getService().queryTreeByGrdCusNumberAndGrdGrpIdAndGrdTypeIndc(grdCusNumber, grdGrpId, grdTypeIndc);
		} catch (Exception e) {

		}
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("data", treeList);
		return resultMap;
	}

	@RequestMapping("/queryByGrdGrpIdAndGrdCusNumberAndGrdTypeIndc")
	@ResponseBody
	@Logger(action = "查询", logger = "根据 单位编号、组ID、成员类型 查询群组成员信息", model = "群组成员管理")
	public AjaxMessage queryByGrdGrpIdAndGrdCusNumberAndGrdTypeIndc(HttpServletRequest request, HttpServletResponse response) {
		String grdCusNumber = request.getParameter("grdCusNumber");
		String grdGrpId = request.getParameter("grdGrpId");
		String grdTypeIndc = request.getParameter("grdTypeIndc");

		try {
			List<GroupMember> list = this.getService().queryByGrdCusNumberAndGrdGrpIdAndGrdTypeIndc(grdCusNumber, grdGrpId, grdTypeIndc);
			return new AjaxMessage(true, list);
		} catch (Exception e) {
			return new AjaxMessage(false, null, e.getMessage());
		}
	}

	/**
	* @methodName: batchInsert
	* @Description: 批量新增
	* @param list
	* @return void    
	* @throws
	*/
	@RequestMapping("/batchInsert")
	@ResponseBody
	public AjaxMessage batchInsert(@RequestBody List<GroupMember> list,@RequestParam("grdGrpId")String grdGrpId){
		try {
			delByGrdGrpId(grdGrpId);
			if(list.size()>0){
				this.getService().batchInsert(list);
			}
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}

	}
	/**
	* @methodName: batchDelete
	* @Description: 批量删除
	*
	 * @param id
	 * @return
	 */
	@RequestMapping("/batchDelete")
	@ResponseBody
	public AjaxMessage batchDelete(@RequestBody List<String> id){
		try {
			this.getService().batchDelete(id);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
		
	}
	/**
	* @methodName: delByGrdGrpId
	* @Description: 通过群组删成员
	* @param grdGrpId
	* @return void    
	* @throws
	*/
	@RequestMapping("/delByGrdGrpId")
	@ResponseBody
	public AjaxMessage delByGrdGrpId(@RequestParam("grdGrpId")String grdGrpId){
		try {
			this.getService().delByGrdGrpId(grdGrpId);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}	
	}
}
