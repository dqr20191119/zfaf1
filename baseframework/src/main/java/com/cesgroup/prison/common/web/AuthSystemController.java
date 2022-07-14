package com.cesgroup.prison.common.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.dao.PoliceMapper;
import com.cesgroup.prison.common.entity.PoliceEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.service.PoliceService;
import com.cesgroup.prison.plugins.authsystem.utils.CookieUtil;

import ces.sdk.system.dbbean.DbUserEntity;

/**
 * 系统管理
 * 
 */
@Controller
@RequestMapping(value = "/common/authsystem")
public class AuthSystemController
		extends BaseEntityDaoServiceCRUDController<PoliceEntity, String, PoliceMapper, PoliceService> {

	@Resource
	private PoliceService policeService;

	/**
	 * 获取所有监狱下拉框
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findAllJyForCombobox")
	@ResponseBody
	public List<Map<String, Object>> findAllJyForCombobox(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<OrgEntity> orgList = AuthSystemFacade.getAllJyInfo();

		for (OrgEntity org : orgList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", org.getOrgKey());
			map.put("text", org.getOrgName());
			resultList.add(map);
		}

		return resultList;
	}

	/**
	 * 获取所有监狱下拉框+市局+分局
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findSjdwAllJyForCombobox")
	@ResponseBody
	public List<Map<String, Object>> findSjdwAllJyForCombobox(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<OrgEntity> orgList = AuthSystemFacade.getAllJyInfo();
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		//获得当前登录用户的所属监狱编号
		String unitId=userBean.getOrgCode();
		if(unitId.equals("1100")){
			OrgEntity orgEntity1=new OrgEntity();//清河分局
			OrgEntity orgEntity2=new OrgEntity();//市局
			orgEntity1.setOrgKey("1140");
			orgEntity1.setOrgName("清河监狱管理分局");
			orgEntity2.setOrgKey("1100");
			orgEntity2.setOrgName("北京市监狱（戒毒）管理局");
			orgList.add(orgEntity2);
			orgList.add(orgEntity1);
			for (OrgEntity org : orgList) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("value", org.getOrgKey());
				map.put("text", org.getOrgName());
				resultList.add(map);
			}
			return resultList;
		}else if(unitId.equals("1140")){
			List<OrgEntity> orgEntityList =new ArrayList<>();
			orgEntityList.add(0,orgList.get(1));
			orgEntityList.add(1,orgList.get(2));
			orgEntityList.add(2,orgList.get(3));
			orgEntityList.add(3,orgList.get(4));
			orgEntityList.add(4,orgList.get(5));
			OrgEntity orgEntity3=new OrgEntity();//清河分局
			orgEntity3.setOrgKey("1140");
			orgEntity3.setOrgName("清河监狱管理分局");
			orgEntityList.add(orgEntity3);
			for (OrgEntity orgEntity : orgEntityList) {
				Map<String,Object> map =new HashMap<String,Object>();
				map.put("value", orgEntity.getOrgKey());
				map.put("text", orgEntity.getOrgName());
				resultList.add(map);
			}
			return resultList;
		}


		return null;
	}

	/**
	 * 根据监狱获取所有监区下拉框
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findAllJqByJyKeyForCombobox")
	@ResponseBody
	public List<Map<String, Object>> findAllJqByJyKeyForCombobox(String cusNumber, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<OrgEntity> orgList = AuthSystemFacade.getAllJqInfoByJyKey(cusNumber);

		for (OrgEntity org : orgList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", org.getOrgKey());
			map.put("text", org.getOrgName());
			resultList.add(map);
		}

		return resultList;
	}

	/**
	 * 根据监狱获取所有部门下拉框
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findAllChildrenOrgByJyKeyForCombobox")
	@ResponseBody
	public List<Map<String, Object>> findAllChildrenOrgByJyKeyForCombobox(String cusNumber, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		List<OrgEntity> orgList = AuthSystemFacade.getAllChildrenOrgInfoByOrgKey(cusNumber);

		for (OrgEntity org : orgList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", org.getOrgKey());
			map.put("text", org.getOrgName());
			resultList.add(map);
		}

		return resultList;
	}

	/**
	 * 根据监狱获取所有部门下拉树
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findAllDeptForCombotree")
	@ResponseBody
	public List<Map<String, Object>> findAllDeptForCombotree(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		String cusNumber = request.getParameter("cusNumber");
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

		if (cusNumber == null || "".equals(cusNumber)) {
			cusNumber = AuthSystemFacade.getLoginUserInfo().getOrgCode();
		}

		// 加载部门信息
		List<OrgEntity> orgList = AuthSystemFacade.getAllChildrenOrgInfoByOrgKey(cusNumber);
		for (OrgEntity org : orgList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", org.getOrgKey());
			map.put("name", org.getOrgName());
			map.put("isParent", false);
			map.put("open", true);
			resultList.add(map);
		}

		return resultList;
	}

	/**
	 * 部门民警下拉树(异步加载)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findDeptPoliceForCombotree")
	@ResponseBody
	public List<Map<String, Object>> findDeptPoliceForCombotree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber")); // 监狱id
		paramMap.put("id", request.getParameter("id")); // 节点id
		paramMap.put("mjxm", request.getParameter("mjxm")); // 节点id

		return policeService.findDeptPoliceForCombotree(paramMap);
	}

	/**
	 * 部门民警下拉树(异步加载)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findDeptPoliceForGrid")
	@ResponseBody
	public Map<String, Object> findDeptPoliceForGrid(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber")); // 监狱id
		paramMap.put("id", request.getParameter("id")); // 节点id
		Map<String, Object> gridMap = new HashMap<String, Object>();
		gridMap.put("data", policeService.findDeptPoliceForCombotree(paramMap));
		return gridMap;
	}

	/**
	 * 部门民警下拉树(一次性获取)
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findSyncDeptPoliceForCombotree")
	@ResponseBody
	public List<Map<String, Object>> findSyncDeptPoliceForCombotree(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber")); // 监狱id
		paramMap.put("id", request.getParameter("id")); // 节点id

		return policeService.findSyncDeptPoliceForCombotree(paramMap);
	}


	@RequestMapping(value = "/findAllPoliceForAutocomplete")
	@ResponseBody
	public List<Map<String, Object>> findAllPoliceForAutoComplate(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber")); // 监狱id

		return policeService.findAllPoliceForAutocomplete(paramMap);

	}

	/**
	 * 根据区域id获取民警下拉框
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/findPoliceByAreaIdForCombobox")
	@ResponseBody
	public List<Map<String, Object>> findPoliceByAreaIdForCombobox(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber")); // 监狱id
		paramMap.put("areaId", request.getParameter("areaId")); // 区域id

		return policeService.findPoliceByAreaIdForCombobox(paramMap);
	}

	/**
	 * 根据部门id获取民警下拉框
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPoliceByDeptIdForCombobox")
	@ResponseBody
	public List<Map<String, Object>> findPoliceByDeptIdForCombobox(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber")); // 监狱id
		paramMap.put("deptId", request.getParameter("deptId")); // 部门id

		return policeService.findPoliceByDeptIdForCombobox(paramMap);
	}

	/**
	 * 获取当前登录民警照片
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPolicePicByLoginName")
	@ResponseBody
	public Object findPolicePicByLoginName(HttpServletRequest request, HttpServletResponse response) throws Exception {

		UserBean userBean = AuthSystemFacade.getLoginUserInfo();

		String loginName = userBean.getLoginName();
		String orgKey = userBean.getOrgCode();
		DbUserEntity dbUserEntity = AuthSystemFacade.getLoginUserInfoDatabase(loginName, orgKey);

		if (dbUserEntity != null && dbUserEntity.getHeadIconOrignal() != null) {
			ServletOutputStream os = CookieUtil.getResponse().getOutputStream();
			ImageIO.write(dbUserEntity.getHeadIconOrignal(), "jpg", os);
			os.close();
		}

		return null;
	}

	/**
	 * 获取当前登录民警信息
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPoliceInfoByLoginName")
	@ResponseBody
	public PoliceEntity findPoliceInfoByLoginName(HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		UserBean userBean = AuthSystemFacade.getLoginUserInfo();

		String loginName = userBean.getLoginName();
		String orgKey = userBean.getOrgCode();

		PoliceEntity policeEntity = new PoliceEntity();
		policeEntity.setPbdCusNumber(orgKey);
		policeEntity.setPbdLoginName(loginName);

		List<PoliceEntity> policeList = policeService.findAllList(policeEntity);
		if (policeList != null && policeList.size() > 0) {
			return policeList.get(0);
		}

		return null;
	}

	/**
	* @methodName: findMjPicInfo
	* @Description: 获取民警照片
	* @param request
	* @param response
	* @return
	* @throws Exception Object
	* @throws  
	*/
	@RequestMapping(value = "/findMjPicInfo")
	@ResponseBody
	public Object findMjPicInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String loginName = request.getParameter("loginName");
		String demptId = request.getParameter("demptId");

		DbUserEntity dbUserEntity = AuthSystemFacade.getLoginUserInfoDatabase(loginName, demptId);

		if (dbUserEntity != null && dbUserEntity.getHeadIconOrignal() != null) {
			ServletOutputStream os = CookieUtil.getResponse().getOutputStream();
			ImageIO.write(dbUserEntity.getHeadIconOrignal(), "jpg", os);
			os.close();
		}
		return null;
	}

	/**
	 * 根据用户ID，查询用户信息
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryUserById")
	@ResponseBody
	@Logger(action = "查询", logger = "根据用户ID查询用户信息", model = "系统管理")
	public AjaxMessage queryUserById(HttpServletRequest request, HttpServletResponse response) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();

		try {
			String id = request.getParameter("id");
			if (id != null && !"".equals(id)) {
				result = this.getService().queryUserInfoDtoByUserId(id);
				flag = true;
			} else {
				flag = false;
				result = "查询失败 ";
			}
		} catch (ServiceException e) {
			flag = false;
			result = "查询失败：" + e.getMessage();
		} catch (Exception e) {
			flag = false;
			result = "查询失败：" + e.getMessage();
		}
		if (flag) {
			ajaxMsg.setObj(result);
		} else {
			ajaxMsg.setMsg((String) result);
		}
		ajaxMsg.setSuccess(flag);

		return ajaxMsg;
	}

	/**
	 * 根据机构编码，查询机构信息
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryOrgByCode")
	@ResponseBody
	@Logger(action = "查询", logger = "根据机构编码查询机构信息", model = "系统管理")
	public AjaxMessage queryOrgInfoDtoByOrgCode(HttpServletRequest request, HttpServletResponse response) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();

		try {
			String code = request.getParameter("code");
			if (code != null && !"".equals(code)) {
				result = this.getService().queryOrgInfoDtoByOrgCode(code);
				flag = true;
			} else {
				flag = false;
				result = "查询失败 ";
			}
		} catch (ServiceException e) {
			flag = false;
			result = "查询失败：" + e.getMessage();
		} catch (Exception e) {
			flag = false;
			result = "查询失败：" + e.getMessage();
		}
		if (flag) {
			ajaxMsg.setObj(result);
		} else {
			ajaxMsg.setMsg((String) result);
		}
		ajaxMsg.setSuccess(flag);

		return ajaxMsg;
	}
}
