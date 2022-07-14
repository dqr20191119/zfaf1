package com.cesgroup.prison.change.web;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.support.NoModel;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.change.dao.ChangeMapper;
import com.cesgroup.prison.change.entity.Change;
import com.cesgroup.prison.change.entity.ChangeApproval;
import com.cesgroup.prison.change.entity.ChangeDepartment;
import com.cesgroup.prison.change.entity.ChangePeople;
import com.cesgroup.prison.change.service.ChangeService;
import com.cesgroup.prison.change.service.impl.ChangeServiceImpl;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.inspect.entity.Inspect;
import com.cesgroup.prison.inspect.service.InspectService;
import com.cesgroup.prison.utils.DataUtils;

/**    
* @Description： 通报整改Controller  
* @author：zhengk   
* @date：2018-03-26 09:45   
*/
@Controller
@RequestMapping(value="/xxyp/change")
public class ChangeController extends BaseEntityDaoServiceCRUDController<Change,String,ChangeMapper,ChangeServiceImpl>{

	@Autowired
	private ChangeService changeService;
	
	@Autowired
	private InspectService inspectService;
	
	@RequestMapping("/launchDialog")
	public ModelAndView launchDialog(){
		ModelAndView mv = new ModelAndView("xxyp/change/launch");
		return mv;
	}
	@RequestMapping("/launchDetailDialog")
	public ModelAndView launchDetailDialog(Inspect ins){
		ModelAndView mv = new ModelAndView("xxyp/change/launchdetail");
		List<Inspect> isList = inspectService.inspectlist(ins);
		if(isList!=null && isList.size()>0){
			mv.addObject("inspect", isList.get(0));
		}
		return mv;
	}
	@RequestMapping("/changeDialog")
	public ModelAndView changeDialog(){
		ModelAndView mv = new ModelAndView("xxyp/change/change");
		return mv;
	}

	@RequestMapping("/checkDialog")
	public ModelAndView checkDialog(){
		ModelAndView mv = new ModelAndView("xxyp/change/check");
		return mv;
	}

	@RequestMapping("/recordDialog")
	public ModelAndView recordDialog(String cusNumber){
		ModelAndView mv = new ModelAndView("xxyp/change/record");
		mv.addObject("cusNumber",cusNumber);
		return mv;
	}
	
	@RequestMapping(value="/launchListPage")
	@ResponseBody
	@Logger(action = "查询发起菜单所需列表信息", logger = "查询发起菜单所需列表信息")
	public Map<String, Object> launchListPage(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("inoNoticeCusNumber", (String)request.getParameter("inoNoticeCusNumber"));	
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String,String>> page=service.launchListPage(paramMap,pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	@RequestMapping(value="/changeListPage")
	@ResponseBody
	@Logger(action = "查询整改菜单所需列表信息", logger = "查询整改菜单所需列表信息")
	public Map<String, Object> changeListPage(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cchCusNumber", (String)request.getParameter("cchCusNumber"));	
		paramMap.put("ccdDprtmntIdnty", (String)request.getParameter("ccdDprtmntIdnty"));	
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String,String>> page=service.changeListPage(paramMap,pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	@RequestMapping(value="/checkListPage")
	@ResponseBody
	@Logger(action = "查询整改审批菜单所需列表信息", logger = "查询整改审批菜单所需列表信息")
	public Map<String, Object> checkListPage(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cchCusNumber", (String)request.getParameter("cchCusNumber"));	
		paramMap.put("ccdBranchLeaders", (String)request.getParameter("ccdBranchLeaders"));	
		paramMap.put("ccdManagerLeaders", (String)request.getParameter("ccdManagerLeaders"));
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String,String>> page=service.checkListPage(paramMap,pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	@RequestMapping(value="/recordListPage")
	@ResponseBody
	@Logger(action = "查询整改汇总菜单所需列表信息", logger = "查询整改汇总菜单所需列表信息")
	public Map<String, Object> recordListPage(HttpServletRequest request){
		Map<String, Object> paramMap = new HashMap<String, Object>();
		PageRequest pageRequest = buildPageRequest();
		paramMap.put("startTime", (String)request.getParameter("startTime"));	
		paramMap.put("endTime", (String)request.getParameter("endTime"));	
		paramMap.put("ccdStatus", (String)request.getParameter("ccdStatus"));
		paramMap.put("inoNoticeCusNumber", (String)request.getParameter("inoNoticeCusNumber"));
		paramMap.put("ccdDprtmntIdnty", (String)request.getParameter("ccdDprtmntIdnty"));
		Page<Map<String,String>> page=service.recordListPage(paramMap,pageRequest);
		return DataUtils.pageToMap(page);
	}
	
	@Logger(action = "添加", logger = "${id}")
    @RequestMapping(value = "/add")
	@ResponseBody
    public AjaxMessage add(@NoModel final Change model) throws WebUIException {
		try {
			UserBean userBean=AuthSystemFacade.getLoginUserInfo();
			model.setCchCrteUserId(userBean.getUserId());
			model.setCchCrteTime(new Date());
			model.setCchUpdtTime(new Date());
			model.setCchUpdtUserId(userBean.getUserId());
			
			Change change=getService().insert(model);
			return new AjaxMessage(true,change.getId());
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
    }
	
	@RequestMapping(value = "/batchInsertChaDep")
	@ResponseBody
	public AjaxMessage batchInsertChaDep(@RequestBody List<Map<String,Object>> list){
		try {
			service.batchInsertChaDep(list);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}	
	}
	
	@RequestMapping(value = "/updateChangeDepartment")
	@ResponseBody
	public AjaxMessage updateChangeDepartment(ChangeDepartment record){
		try {
			service.updateChangeDepartment(record);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}	
	}
	
	@Logger(action = "添加整改人", logger = "${id}")
    @RequestMapping(value = "/addChangePeople")
	@ResponseBody
    public AjaxMessage addChangePeople(ChangePeople model){
		try {
			service.addChangePeople(model);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
    }
	//整改页面的提交业务
    @RequestMapping(value = "/changeSubmit")
	@ResponseBody
    public AjaxMessage changeSubmit(HttpServletRequest request){
		try {
			service.changeSubmit(request);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
    }
    //审批页面的提交业务
    @RequestMapping(value = "/checkSubmit")
	@ResponseBody
    public AjaxMessage checkSubmit(HttpServletRequest request){
		try {
			service.checkSubmit(request);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
    }
    
    @RequestMapping(value = "/searchChangeApproval")
	@ResponseBody
    public AjaxMessage searchChangeApproval(ChangeApproval record){
		try {
			List<ChangeApproval> list=service.searchChangeApproval(record);
			return new AjaxMessage(true,list);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
    }
    @RequestMapping(value = "/searchChangePeople")
	@ResponseBody
    public AjaxMessage searchChangePeople(ChangePeople record){
		try {
			List<ChangePeople> list=service.searchChangePeople(record);
			return new AjaxMessage(true,list);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
    }
    @RequestMapping(value = "/searchChangeDepartment")
	@ResponseBody
    public AjaxMessage searchChangeDepartment(ChangeDepartment record){
		try {
			List<ChangeDepartment> list=service.searchChangeDepartment(record);
			return new AjaxMessage(true,list);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
    }
}
