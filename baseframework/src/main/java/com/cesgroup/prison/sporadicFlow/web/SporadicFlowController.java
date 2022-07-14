package com.cesgroup.prison.sporadicFlow.web;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.sporadicFlow.dao.SporadicFlowMapper;
import com.cesgroup.prison.sporadicFlow.entity.SporadicFlowRegisterEntity;
import com.cesgroup.prison.sporadicFlow.service.SporadicFlowService;
import com.cesgroup.prison.utils.DataUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**      
* @projectName：prison   
* @ClassName：SporadicFlowController   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月7日 下午8:16:32   
* @version        
*/
@Controller
@RequestMapping(value = "/sporadicFlow")
public class SporadicFlowController extends
		BaseEntityDaoServiceCRUDController<SporadicFlowRegisterEntity, String, SporadicFlowMapper, SporadicFlowService> {

	@Resource
	private SporadicFlowService service;

	/**
	* @methodName: openDialog
	* @Description: 打开窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("sporadicFlow/index");
		return mv;
	}

	/**
	* @methodName: openDialog
	* @Description: 打开窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog/register")
	public ModelAndView openRegisterDialog() {
		ModelAndView mv = new ModelAndView("sporadicFlow/register");
		return mv;
	}
	/**
	 * @methodName: openDialog
	 * @Description: 打开窗口
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "/openDialog/edit")
	public ModelAndView openEditDialog(String id) {
		ModelAndView mv = new ModelAndView("sporadicFlow/register");
		mv.addObject("ID", id);
		return mv;
	}
	/**
	* @methodName: openDialog
	* @Description: 打开登记窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog/check")
	public ModelAndView openCheckDialog(String id) {
		ModelAndView mv = new ModelAndView("sporadicFlow/check");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/openDialog/back")
	public ModelAndView openbackDialog(String id) {
		ModelAndView mv = new ModelAndView("sporadicFlow/back");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/openDialog/show")
	public ModelAndView openShowDialog(String id) {
		ModelAndView mv = new ModelAndView("sporadicFlow/show");
		mv.addObject("ID", id);
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询罪犯零星活动信息")
	public Map<String, Object> listAll(SporadicFlowRegisterEntity entity) throws Exception{
		PageRequest pageRequest = buildPageRequest();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, pageRequest,user);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/findDeptPoliceForGridByItem")
	@ResponseBody
	public Map<String, Object> findDeptPoliceForGridByItem(HttpServletRequest request, HttpServletResponse response) {
		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", request.getParameter("id"));
		paramMap.put("flag", 1);
		Map<String, Object> gridMap = new HashMap<String, Object>();
		gridMap.put("data",service.findDeptPoliceForGridByItem(paramMap));
		return gridMap;
	}

	@RequestMapping(value = "/findDeptOffenderForGridByItem")
	@ResponseBody
	public Map<String, Object> findDeptOffenderForGridByItem(HttpServletRequest request, HttpServletResponse response) {
		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", request.getParameter("id"));
		paramMap.put("flag", "2");
		Map<String, Object> gridMap = new HashMap<String, Object>();
		gridMap.put("data",service.findDeptOffenderForGridByItem(paramMap));
		return gridMap;
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	@Logger(action = "查询", logger = "根据ID查询罪犯零星活动信息")
	public AjaxMessage findById(String id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			Map<String, Object> paramMap =new HashMap<String, Object>();
			paramMap.put("id",id);
			Map<String, Object> tempObj = service.findById(paramMap);
			SporadicFlowRegisterEntity entity=new SporadicFlowRegisterEntity();
			entity.setId(tempObj.get("ID").toString());
			entity.setSflDprtmntId(Integer.parseInt(tempObj.get("SFL_DPRTMNT_ID").toString()));
			entity.setSflStartAddrs(tempObj.get("SFL_START_ADDRS").toString());

			if (tempObj.get("SFL_REASON") != null) {
				entity.setSflReason(tempObj.get("SFL_REASON").toString());
			}
			entity.setSflFlowType(tempObj.get("SFL_FLOW_TYPE").toString());
			entity.setSflEndAddrs(tempObj.get("SFL_END_ADDRS").toString());

			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			entity.setSflEndTime(sdf.parse(tempObj.get("SFL_END_TIME").toString()));
			entity.setSflStartTime(sdf.parse(tempObj.get("SFL_START_TIME").toString()));
			entity.setSflPrisonPathId(tempObj.get("SFL_PRISON_PATH_ID").toString());
			entity.setSflDprtmnt(tempObj.get("SFL_DPRTMNT").toString());
			entity.setSflOffenderNum(Integer.parseInt(tempObj.get("SFL_OFFENDER_NUM").toString()));
			obj = entity;
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "查询罪犯异常： " + e.getMessage();
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/register")
	@ResponseBody
	@Logger(action = "登记", logger = "增加登记罪犯零星活动信息")
	public Map<String, Object> add(@Param("formDataJson")String formDataJson,@Param("gridDataPoliceJson")String gridDataPoliceJson,@Param("gridDataOffenderJson")String gridDataOffenderJson,HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			JSONObject formData = JSONObject.fromObject(formDataJson);
			JSONArray gridDataPolice = JSONArray.fromObject(gridDataPoliceJson);
			JSONArray gridDataOffender = JSONArray.fromObject(gridDataOffenderJson);
			SporadicFlowRegisterEntity entity= (SporadicFlowRegisterEntity)JSONObject.toBean(formData, SporadicFlowRegisterEntity.class);
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			service.addRegisterInfo(entity,gridDataPolice,gridDataOffender,user,request);
			map.put("code", 1);
		} catch (Exception e) {
			map.put("code", 0);
			map.put("message", e.getMessage());
		}
		return map;
	}

	@RequestMapping(value = "/editInfo")
	@ResponseBody
	@Logger(action = "修改", logger = "修改登记罪犯零星活动信息")
	public Map<String, Object> update(@Param("formDataJson")String formDataJson,@Param("gridDataPoliceJson")String gridDataPoliceJson,@Param("gridDataOffenderJson")String gridDataOffenderJson,HttpServletRequest request){
		JSONObject formData = JSONObject.fromObject(formDataJson);
		JSONArray gridDataPolice = JSONArray.fromObject(gridDataPoliceJson);
		JSONArray gridDataOffender = JSONArray.fromObject(gridDataOffenderJson);
		SporadicFlowRegisterEntity entity = (SporadicFlowRegisterEntity) JSONObject.toBean(formData, SporadicFlowRegisterEntity.class);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			service.updateRegisterInfoAll(entity, gridDataPolice, gridDataOffender, user,request);
			map.put("code", 1);
		} catch (Exception e) {
			map.put("code", 0);
			map.put("message", e.getMessage());
		}

		return map;
	}

	@RequestMapping(value = "/updateStts")
	@ResponseBody
	@Logger(action = "更新", logger = "更新罪犯零星活动登记单状态或审核状态")
	public Map<String, Object> updateStts(SporadicFlowRegisterEntity entity) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			service.updateRegisterInfo(entity,user);
			map.put("code", 1);
		} catch (Exception e) {
			map.put("code", 0);
			map.put("message", e.getMessage());
		}

		return map;

	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> delete(@RequestBody List<String> id, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			service.deleteByIds(id);

			map.put("code", 1);

		} catch (Exception e) {
			map.put("code", 0);
			map.put("message", e.getMessage());
		}
		return map;
	}

}
