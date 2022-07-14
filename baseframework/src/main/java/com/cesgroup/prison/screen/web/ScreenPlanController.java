package com.cesgroup.prison.screen.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.screen.dao.ScreenPlanWindowCameraMapper;
import com.cesgroup.prison.screen.dao.ScreenPlanWindowNewMapper;
import com.cesgroup.prison.screen.entity.ScreenPlanNewEntity;
import com.cesgroup.prison.screen.entity.ScreenPlanWindowCameraEntity;
import com.cesgroup.prison.screen.entity.ScreenPlanWindowNewEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.screen.dao.ScreenPlanMapper;
import com.cesgroup.prison.screen.entity.ScreenPlanEntity;
import com.cesgroup.prison.screen.service.ScreenPlanService;
import com.cesgroup.prison.utils.DataUtils;

/**      
* @projectName：prison   
* @ClassName：ScreenPlanController   
* @Description：   
* @author：Tao.xu   
* @date：2018年1月3日 下午7:44:38   
* @version        
*/
@Controller
@RequestMapping(value = "/screenPlan")
public class ScreenPlanController
		extends BaseEntityDaoServiceCRUDController<ScreenPlanEntity, String, ScreenPlanMapper, ScreenPlanService> {

	@Resource
	private ScreenPlanService service;


	/**
	* @methodName: openDialog
	* @Description: 打开窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("screen/list_new");
		return mv;
	}

	/**
	* @methodName: openDialog
	* @Description: 打开增加窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog/save")
	public ModelAndView openAddDialog() {
		ModelAndView mv = new ModelAndView("screen/save");
		return mv;
	}

	/**
	* @methodName: openDialog
	* @Description: 打开修改窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog/update")
	public ModelAndView openUpdateDialog(String id) {
		ModelAndView mv = new ModelAndView("screen/update");
		mv.addObject("ID", id);
		return mv;
	}

	/*@RequestMapping(value = "/openDialog/setting")
	public ModelAndView openDialog(String id, String name) throws UnsupportedEncodingException {
		ModelAndView mv = new ModelAndView("screen/setting");
		mv.addObject("ID", id);
		mv.addObject("NAME", URLDecoder.decode(name, "utf8") + "-区域-");
		return mv;
	}*/
      @RequestMapping(value = "/openDialog/setting")
      public ModelAndView openDialog(String id, String name) throws UnsupportedEncodingException {
          ModelAndView mv = new ModelAndView("screen/setting_new");
          mv.addObject("id", id);
          mv.addObject("NAME", URLDecoder.decode(name, "utf8"));
          return mv;
      }
	@RequestMapping(value = "/listAll")
	@ResponseBody
	public Map<String, Object> listAll(ScreenPlanEntity entity) throws Exception {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, pageRequest);

		return DataUtils.pageToMap(page);
	}

    @RequestMapping(value = "/pageSelectAll")
    @ResponseBody
    public Map<String, Object> pageSelectAll(ScreenPlanNewEntity entity) throws Exception {
        PageRequest pageRequest = buildPageRequest();
        Page<ScreenPlanNewEntity> page = (Page<ScreenPlanNewEntity>) service.pageSelectAll(entity, pageRequest);

        return DataUtils.pageToMap(page);
    }

    /**
     * 查询待关联的摄像头 新
     * @param cusNumber
     * @param areaId
     * @param screenPlanId
     * @param windowId
     * @return
     */
    @RequestMapping(value = "/listAllForSxNew")
    @ResponseBody
    public Map<String, Object> listAllForSxNew(String cusNumber, String areaId, String screenPlanId, String windowId) {
        List<Map<String, Object>> list = (List<Map<String, Object>>) service.listAllForSxNew(cusNumber, areaId,
                screenPlanId, windowId);
        Map<String, Object> map = new HashMap<>();
        map.put("data", list);
        return map;
    }

    /**
     * 查询已经关联的摄像头
     * @param cusNumber
     * @param screenPlanId
     * @param windowId
     * @return
     */
    @RequestMapping(value = "/getByScreenPlanWindowCameraEntity")
    @ResponseBody
    public AjaxResult selectListByScreenPlanWindowCameraEntity(String cusNumber,String screenPlanId,String windowId) {
        try {
            ScreenPlanWindowCameraEntity sc = new ScreenPlanWindowCameraEntity();
            sc.setCusNumber(cusNumber);
            sc.setScreenPlanId(screenPlanId);
            sc.setWindowId(windowId);
            List<ScreenPlanWindowCameraEntity> screenPlanWindowCameraEntities = service.selectListByScreenPlanWindowCameraEntity(sc);
            return AjaxResult.success(screenPlanWindowCameraEntities);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("查询已经关联的摄像头失败");
        }
    }

    /**
     * 查询电视墙预案关联的所有窗口
     * @param ScreenPlanId
     * @param cusNumber
     * @return
     */
    @RequestMapping(value = "/getWindowByScreenPlanId")
    @ResponseBody
    public AjaxResult selectWindowByScreenPlanId(@RequestParam("screenPlanId") String screenPlanId,@RequestParam("cusNumber") String cusNumber) {
        try {
            List<ScreenPlanWindowNewEntity> screenPlanWindowNewEntity = service.selectWindowByScreenPlanId(screenPlanId, cusNumber);
            return AjaxResult.success(screenPlanWindowNewEntity);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("查询预案关联窗口失败");
        }
    }

	@RequestMapping(value = "/seachComboData")
	@ResponseBody
	public List<Map<String, Object>> seachComboData(String cusNumber, String isDynamic) throws Exception {
		List<Map<String, Object>> maps = new ArrayList<>();
		maps = service.searchCombDataForPlan(cusNumber, isDynamic);
		return maps;
	}

	@RequestMapping(value = "/findById")
	@ResponseBody
	public AjaxMessage findById(String id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			obj = service.findById(id);
			flag = true;
		} catch (Exception e) {
			flag = false;
			obj = "查询发生异常";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/update/info")
	@ResponseBody
	public AjaxMessage update(ScreenPlanEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.updateInfo(entity);
			flag = true;
			obj = "修改成功";
		} catch (Exception e) {
			flag = false;
			obj = "修改发生异常";
		}

		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@RequestMapping(value = "/delete")
	@ResponseBody
	public AjaxMessage delete(String id) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			service.deleById(id);
			flag = true;
			obj = "删除成功";
		} catch (Exception e) {
			flag = false;
			obj = "删除发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public AjaxMessage save(ScreenPlanEntity entity) {
		return service.addInfo(entity);
	}

	/**
	* @methodName: listAll
	* @Description: 信号分组查询
	* @param cusNumber 监狱号
	* @param screenPlanId 大屏预案id
	* @param screenAreaId 大屏区域id
	* @param type 1 、查询大屏窗口  2、查询信号源
	* @throws  
	*/
	@RequestMapping(value = "/listAll/xhfz")
	@ResponseBody
	public Map<String, Object> listAll(String cusNumber, String screenPlanId, String screenAreaId, String type)
			throws Exception {
		return service.listAll(cusNumber, screenPlanId, screenAreaId, type);
	}

	/**
	* @methodName: listAllForSx
	* @Description: 查询待关联信号源
	* @param cusNumber
	* @param areaId
	* @param screenAreaId
	* @param screenPlanId
	* @return
	* @throws Exception Map<String,Object>
	* @throws  
	*/
	@RequestMapping(value = "/listAllForSx")
	@ResponseBody
	public Map<String, Object> listAllForSx(String cusNumber, String areaId, String screenPlanId, String cameraName)
			throws Exception {
		List<Map<String, Object>> list = (List<Map<String, Object>>) service.listAllForSx(cusNumber, areaId,
				screenPlanId, cameraName);
		Map<String, Object> map = new HashMap<>();
		map.put("data", list);
		return map;
	}

	@ResponseBody
    @RequestMapping(value = "/save/xhfz")
    public AjaxMessage saveXhfz(@RequestBody Object xhfzMap) {
        return service.saveXhfz(xhfzMap);
    }

    @ResponseBody
    @RequestMapping(value = "/saveNew")
    public AjaxResult saveNew(@RequestBody Object xhfzMap) {
        return service.saveNew(xhfzMap);
    }

	@RequestMapping(value = "/seachComboData/xhfz")
	@ResponseBody
	public List<Map<String, Object>> seachComboDataXhfz(String cusNumber, String planId) throws Exception {
		return service.searchCombData(cusNumber, planId);
	}

	@RequestMapping(value = "/findById/xhfz")
	@ResponseBody
	public AjaxMessage findScreenPlanAreaRltnById(String id) throws Exception {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {

			flag = true;
			obj = service.findScreenPlanAreaRltnById(id);
		} catch (Exception e) {
			flag = false;
			obj = "查询发生异常";
		}
		if (flag) {
			ajaxMessage.setObj(obj);
		} else {
			ajaxMessage.setMsg((String) obj);
		}
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@RequestMapping(value = "/deleteXhfz", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage deleteXhfzByIds(@RequestBody List<String> ids, HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			String type = request.getParameter("type");// 1、批量删除大屏窗口 2、批量删除大屏信号源
			service.deleteXhfzByIds(ids, type);
			flag = true;
			obj = "删除成功";
		} catch (Exception e) {
			flag = false;
			obj = "删除发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	/**
	* @methodName: deleteXhfzById
	* @Description: 删除大屏预案及关联窗口和信号源
	* @param request
	* @return AjaxMessage
	* @throws  
	*/
	@RequestMapping(value = "/deleteXhfz/dpqy", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage deleteXhfzById(HttpServletRequest request) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			String screenAreaId = request.getParameter("screenAreaId");
			service.deleteXhfzById(screenAreaId);
			flag = true;
			obj = "删除成功";
		} catch (Exception e) {
			flag = false;
			obj = "删除发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

    /**
     * 电视墙预案切换
     * @param id 预案id
     * @param tywallId 电视墙id
     * @return
     */
    @RequestMapping(value = "/screenPlanQh", method = RequestMethod.POST)
    @ResponseBody
	public AjaxResult screenPlanQh(@RequestParam("id")String id,@RequestParam("tywallId") String tywallId,@RequestParam("cusNumber")String cusNumber){
	    try {
	        service.screenPlanQh(id,tywallId,cusNumber);
	        return AjaxResult.success("操作成功");
          }catch (Exception e){
	        e.printStackTrace();
              return AjaxResult.error("操作失败");
          }
      }

    /**
     * 上墙
     * @param id
     * @param tywallId
     * @param cusNumber
     * @return
     */
    @RequestMapping(value = "/screenPlanSq", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult screenPlanSq(@RequestParam("id")String id,@RequestParam("tywallId") String tywallId,@RequestParam("cusNumber")String cusNumber){
        try {
            service.screenPlanSq(id,tywallId,cusNumber);
            return AjaxResult.success("操作成功");
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("操作失败");
        }
    }

}
