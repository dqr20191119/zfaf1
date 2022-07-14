package com.cesgroup.prison.jfsb.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.jfsb.dao.CameraMapper;
import com.cesgroup.prison.jfsb.entity.Camera;
import com.cesgroup.prison.jfsb.service.ICameraService;
import com.cesgroup.prison.utils.DataUtils;

/**   
*    
* @projectName：prison   
* @ClassName：CameraController   
* @Description： 摄像机Controller  
* @author：zhengke   
* @date：2017-12-01 09:45   
* @version        
*/
@Controller
@RequestMapping(value = "/jfsb/camera")
public class CameraController extends BaseEntityDaoServiceCRUDController<Camera, String, CameraMapper, ICameraService> {

	@RequestMapping("/list")
	public ModelAndView list(String areaId) {
		ModelAndView mv = new ModelAndView("jfsb/camera/list");
		mv.addObject("areaId", areaId);
		return mv;
	}
	
	/**
	* @methodName: readList
	* @Description: add by WangXin 摄像机只读展示页面
	* @param areaId
	* @return
	*/
	@RequestMapping("/readList")
	public ModelAndView readList(String areaId) {
		ModelAndView mv = new ModelAndView("jfsb/camera/readList");
		mv.addObject("areaId", areaId);
		return mv;
	}
	
	@RequestMapping(value = "/searchCamera")
	@ResponseBody
	@Logger(action = "查询摄像机信息", logger = "摄像机列表")
	public Map<String, Object> searchCamera(Camera camera_param) {
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, String>> page = this.getService().searchCamera(camera_param, pageRequest);
		return DataUtils.pageToMap(page);
	}

	@RequestMapping(value = "/findByCbdAreaIdAndCbdCusNumber")
	@ResponseBody
	@Logger(action = "根据区域ID和监所编码查询摄像机信息", logger = "根据区域和监所编码查询摄像机列表")
	public List<Camera> findByCbdAreaIdAndCbdCusNumber(String areaId, String cbdCusNumber) {
		return this.getService().findByCbdAreaIdAndCbdCusNumber(areaId, cbdCusNumber);
	}

	@RequestMapping(value = "/findAreaCameraTree")
	@ResponseBody
	@Logger(action = "查询区域摄像机树", logger = "查询区域摄像机树")
	public List<Map<String, Object>> findAreaCameraTree(String id, String cusNumber) {
		return this.getService().findAreaCameraTree(cusNumber, id);
	}

	@RequestMapping(value = "/updatePart")
	@ResponseBody
	@Logger(action = "局部修改", logger = "局部修改")
	public AjaxMessage updatePart(Camera camera_param) {
		try {
			this.getService().updatePart(camera_param);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false, null, e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteByAreaId")
	@ResponseBody
	@Logger(action = "区域删除", logger = "区域删除")
	public AjaxMessage deleteByAreaId(String cbdAreaId) {
		try {
			this.getService().deleteByAreaId(cbdAreaId);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false, null, e.getMessage());
		}
	}

	@RequestMapping(value = "/deleteById")
	@ResponseBody
	@Logger(action = "根据id删除", logger = "区域删除")
	public AjaxMessage deleteById(String id) {
		try {
			this.getService().deleteById(id);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false, null, e.getMessage());
		}
	}
	/**
	 * 批量逻辑删除
	 * @param id
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/deleteByIds")
	@ResponseBody
	@Logger(action = "根据ids删除", logger = "区域删除")
	public AjaxMessage deleteByIds(@RequestBody List<String> id, HttpServletRequest request) {
		try {
			this.getService().deleteByIds(id);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false, null, e.getMessage());
		}
	}
	
	//根据区域id和监所编码和是否为室外查询摄像机(用于区域摄像头树)
	@RequestMapping(value = "/searchByCbdAreaIdAndCbdCusNumber")
	@ResponseBody
	public List<Map<String,Object>> searchByCbdAreaIdAndCbdCusNumber(Camera camera_param) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		try {
			list=this.getService().searchByCbdAreaIdAndCbdCusNumber(camera_param);
			return list;
		} catch (Exception e) {
			return null;
		}
	}
	//查询摄像头正常和故障的摄像头数量
	@RequestMapping(value = "/searchCameraCount")
	@ResponseBody
	public List<Map<String, Object>> searcsearchCameraCounthCamera(String cusNumber) {
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		Map<String, Object> mapGood = new HashMap<String, Object>();
		mapGood.put("name", "正常");
		mapGood.put("value", this.getService().selectGoodCount(cusNumber));
		list.add(mapGood);
		Map<String, Object> mapBad = new HashMap<String, Object>();
		mapBad.put("name", "故障");
		mapBad.put("value", this.getService().selectBadCount(cusNumber));
		list.add(mapBad);
		return list;
	}

	//根据对讲ID查询摄像头ID 用于呼叫对讲关联摄像头  临时添加 2018-09-28 wq
	@RequestMapping(value= "/getCameraIdByTalkbackId")
	@ResponseBody
	public List<String> getCameraIdByTalkbackId(HttpServletRequest request){
		String cusNumber = request.getParameter("cusNumber");
		List<String> talkIdntys = (List<String>) (JSON.parse(request.getParameter("talkIdntys")));
		return this.getService().getCameraIdByTalkbackId(cusNumber,talkIdntys);
	}

    /**
     * 根据摄像机在平台的索引编号查询摄像机设备信息
     * @param cbdPlatformIdnty
     * @return
     */
    @Logger(action = "查询", logger = "根据摄像机在平台的索引编号查询摄像机设备信息")
    @RequestMapping(value = "/getCameraByCbdCusNumberAndCbdPlatformIdnty", method = RequestMethod.POST)
    @ResponseBody
	public AjaxResult getCameraByCbdCusNumberAndCbdPlatformIdnty(@RequestParam(defaultValue = "", value = "cbdCusNumber", required = false) String cbdCusNumber,
																 @RequestParam(defaultValue = "", value = "cbdPlatformIdnty", required = false) String cbdPlatformIdnty) {
    	try {
    		if(cbdCusNumber == null || cbdCusNumber.isEmpty()) {
				return AjaxResult.error("数据缺失，摄像机所属监狱编号不能为空");
			}
    		if(cbdPlatformIdnty == null || cbdPlatformIdnty.isEmpty()) {
    			return AjaxResult.error("数据缺失，摄像机在平台的索引编号不能为空");
    		}
    		
            List<Camera> cameraList = this.getService().queryByCbdCusNumberAndCbdPlatformIdnty(cbdCusNumber, cbdPlatformIdnty);
            if(cameraList != null && cameraList.size() > 0) {
            	return AjaxResult.success(cameraList.get(0));
            } else {
            	return AjaxResult.error("未查询到摄像机在平台的索引编号[" + cbdPlatformIdnty + "]所对应的摄像机设备信息");
            }
        } catch (BusinessLayerException e) {
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
	}

	/**
	 *  根据摄像头ID，查询摄像头播放视频的必要信息
	 * @param request
	 * @return
	 */
	@RequestMapping("/getPlayInfoByCameraId")
	@ResponseBody
	@Logger(action = "查询", logger = "根据摄像机 ID 查询摄像机用于播放视频的必要信息", model = "摄像机管理")
	public AjaxMessage getPlayInfoByCameraId(HttpServletRequest request) {
		String cameraId = request.getParameter("cameraId");// 摄像机ID
		try {
			Map<String, Object> playInfo = this.getService().getPlayInfoByCameraId(cameraId);
			return new AjaxMessage(true, playInfo);
		} catch (ServiceException e) {
			return new AjaxMessage(false, null, e.getMessage());
		} catch (Exception e) {
			return new AjaxMessage(false, null, e.getMessage());
		}
	}
}
