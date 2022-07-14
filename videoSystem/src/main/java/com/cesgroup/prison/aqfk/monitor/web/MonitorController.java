package com.cesgroup.prison.aqfk.monitor.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.framework.dto.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.commons.CesBeanUtils;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.support.NoModel;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.aqfk.monitor.dao.MonitorDocMapper;
import com.cesgroup.prison.aqfk.monitor.entity.MonitorDoc;
import com.cesgroup.prison.aqfk.monitor.service.MonitorService;
import com.cesgroup.prison.aqfk.monitor.service.impl.MonitorServiceImpl;
import com.cesgroup.prison.utils.DataUtils;

/**   
*    
* 项目名称：prison   
* 类名称：MonitorController   
* 类描述：   
* 创建人：zhengk   
* 创建时间：2017-11-24 09:16   
* @version        
*/
@Controller
@RequestMapping(value = "/monitor")
public class MonitorController extends BaseEntityDaoServiceCRUDController<MonitorDoc,String,MonitorDocMapper,MonitorServiceImpl> {
	@Autowired
	private MonitorService monitorService;

	/**
	* @methodName: jdjc
	* @Description: 监督检查dialog
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping("/jdjc")
	public ModelAndView jdjc(HttpServletRequest request, HttpServletResponse response) {
	    String type = request.getParameter("type");
		ModelAndView mv = new ModelAndView("aqfk/monitor/dialog_jdjc");
          if(!"".equals(type)&& type!=null){
              mv.addObject("type",type);//type为1表示从视频截图按钮进入此方法
          }else{
              mv.addObject("type","2"); //为普通打开
          }
		return mv;
	}
	/**
	* @methodName: jddlb
	* @Description: 监区监督单列表（已收监督单）
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping("/jddlb")
	public ModelAndView jddlb() {
		ModelAndView mv = new ModelAndView("aqfk/monitor/jq_monitorList");
		return mv;
	}

	@Logger(action = "添加", logger = "${id}")
    @RequestMapping(value = "/insert")
	@ResponseBody
    public AjaxMessage insert(@NoModel final MonitorDoc model) throws WebUIException {
		try {
			MonitorDoc monitorDoc=getService().insert(model);
			return new AjaxMessage(true,monitorDoc.getId());
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
		//mv.addObject("monitorDocId", monitorDoc.getId());
    }


    @RequestMapping("/newjdc")
    public ModelAndView newjdc(HttpServletRequest request){
       String lx = request.getParameter("lx");
       ModelAndView mv = new ModelAndView("aqfk/monitor/new");
        if(!"".equals(lx)&& lx!=null){
            mv.addObject("lx",lx);//type为1表示从视频截图按钮进入此方法
        }else{
            mv.addObject("lx","2"); //为普通打开
        }
        return mv;
    }


	 /**
     * 默认的进入修改方法
     *
     * @param id
     *            主键
     * @param model
     *            对象请求参数
     * @return ModelAndView
     */
    @Logger(action = "进入修改", logger = "${id}")
    @RequestMapping(value = "/edit")
    public ModelAndView edit(final String id, @NoModel final MonitorDoc model) throws WebUIException {
        CesBeanUtils.copy(getService().selectOne(id), model);
        return new ModelAndView("aqfk/monitor/edit", MODEL_PARAM_NAME, model);
    }
	
	/**
	* @methodName: searchMonitor
	* @Description: 监督单列表查询
	* @param pageable
	* @return Map<String,Object>    
	* @throws
	*/
	@RequestMapping(value = "/searchMonitor")
	@ResponseBody
	@Logger(action = "查询方法", logger = "查询列表")
	public Map<String, Object> searchMonitor(MonitorDoc monitorDoc_param,String startTime,String endTime,Pageable pageable){
		Page<Map<String, String>> page;
		try {
			page = monitorService.searchMonitor(monitorDoc_param,startTime,endTime,pageable);
			return DataUtils.pageToMap(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap<String, Object>();
	}
	/**
	* @methodName: searchYFMonitor
	* @Description: 已发监督单列表（包含已下发）查询
	* @param pageable
	* @return Map<String,Object>    
	* @throws
	*/
	@RequestMapping(value = "/searchYFMonitor")
	@ResponseBody
	@Logger(action = "查询方法", logger = "查询列表")
	public Map<String, Object> searchYFMonitor(MonitorDoc monitorDoc_param,String startTime,String endTime,Pageable pageable){
		Page<Map<String, String>> page;
		try {
			page = monitorService.searchYFMonitor(monitorDoc_param,startTime,endTime,pageable);
			return DataUtils.pageToMap(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap<String, Object>();
	}
	//根据网络督查通报id查询关联的监督单信息
	@RequestMapping(value = "/searchMonitorByInspectId")
	@ResponseBody
	public Map<String, Object> searchMonitorByInspectId(String inspectId,Pageable pageable){
		Page<Map<String, String>> page;
		try {
			if(inspectId==null||"".equals(inspectId)) {
				throw new Exception("inspectId为空");
			}
			page = monitorService.searchMonitorByInspectId(inspectId,pageable);
			return DataUtils.pageToMap(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap<String, Object>();
	}
	/*//查询部门已接收监督单信息
	@RequestMapping(value = "/searchDeptReceivedMonitor")
	@ResponseBody
	public Page<Map<String,String>> searchDeptReceivedMonitor(String params, Pageable pageable){
		Map<String,Object> map=new HashMap<String,Object>();
		
		if(params != null && (!"".equals(params))){
			JSONObject reqJSON = JSON.parseObject(params);
			if(reqJSON.size()>0){
				for(String key:reqJSON.keySet()){
					map.put(key,reqJSON.get(key));
				}
			}
		}
		Page<Map<String, String>> page;
		try {
			page = monitorService.searchDeptReceivedMonitor(map,pageable);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	//查询部门已接收监督单信息
	@RequestMapping(value = "/searchDeptReceivedMonitor")
	@ResponseBody
	public Map<String,Object> searchDeptReceivedMonitor(String params, Pageable pageable){
		Map<String,Object> map=new HashMap<String,Object>();
		
		if(params != null && (!"".equals(params))){
			JSONObject reqJSON = JSON.parseObject(params);
			if(reqJSON.size()>0){
				for(String key:reqJSON.keySet()){
					map.put(key,reqJSON.get(key));
				}
			}
		}
		Page<Map<String, String>> page;
		try {
			page = monitorService.searchDeptReceivedMonitor(map,pageable);
			return DataUtils.pageToMap(page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new HashMap<String, Object>();
	}
	//查询已接收监督单信息
	/*@RequestMapping(value = "/searchReceivedMonitor")
	@ResponseBody
	public Page<Map<String,String>> searchReceivedMonitor(String params, Pageable pageable){
		Map<String,Object> map=new HashMap<String,Object>();
		
		if(params != null){
			JSONObject reqJSON = JSON.parseObject(params);
			if(reqJSON.size()>0){
				for(String key:reqJSON.keySet()){
					map.put(key,reqJSON.get(key));
				}
			}
		}
		Page<Map<String, String>> page;
		try {
			page = monitorService.searchReceivedMonitor(map,pageable);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}*/
	//局部修改
	@RequestMapping(value = "/updatePart")
	@ResponseBody
	public AjaxMessage updatePart(MonitorDoc monitorDoc_param,HttpServletRequest request){
		try {
			monitorService.updatePart(monitorDoc_param,request);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}	
	}
	//根据监督单Id修改关联的证据使用状态
	@RequestMapping(value = "/updateEviStatusByMonId")
	@ResponseBody
	public AjaxMessage updateEviStatusByMonId(@RequestBody Map<String, Object> map){
		try {
			monitorService.updateEviStatusByMonId(map);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}	
	}
	//更改接收监督单的查看状态
	@RequestMapping(value = "/updateMrrStatus")
	@ResponseBody
	public AjaxMessage updateMrrStatus(@RequestBody Map<String, Object> map){
		try {
			monitorService.updateMrrStatus(map);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}	
	}
	@RequestMapping(value = "/batchInsertMonitorAndEvidence")
	@ResponseBody
	public AjaxMessage batchInsertMonitorAndEvidence(@RequestBody List<Map<String,Object>> list){
		try {
			monitorService.batchInsertMonitorAndEvidence(list);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}	
	}
	
	@RequestMapping(value = "/batchInsertMonitorAndRecipient")
	@ResponseBody
	public AjaxMessage batchInsertMonitorAndRecipient(@RequestBody List<Map<String,Object>> list,HttpServletRequest request){
		try {
			monitorService.batchInsertMonitorAndRecipient(list,request);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}	
	}
	//批量新增监督单和网络通报关联信息
	@RequestMapping(value = "/batchInsertMonitorAndInspect")
	@ResponseBody
	public AjaxMessage batchInsertMonitorAndInspect(@RequestBody List<Map<String,Object>> list){
		try {
			monitorService.batchInsertMonitorAndInspect(list);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}	
	}
	//查询监督单关联证据信息
	@RequestMapping(value = "/searchRelationMonEvi")
    public List<Map<String,Object>> searchRelationMonEvi(String monitorSqno){
    	return monitorService.searchRelationMonEvi(monitorSqno);
    }
	//查询监督单关联证据信息(用于grid列表展示)
	@RequestMapping(value = "/gridSearchRelationMonEvi")
    public Map<String,Object> gridSearchRelationMonEvi(String monitorSqno){
		Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", monitorService.searchRelationMonEvi(monitorSqno));
        return resultMap;
    }
    //查询监督单关联接收人信息
	@RequestMapping(value = "/searchRelationMonRec")
    public List<Map<String,Object>> searchRelationMonRec(String cusNumber,String monitorSqno){
    	return monitorService.searchRelationMonRec(cusNumber,monitorSqno);
    }
	//删除与监督关联的证据信息
	@ResponseBody
	@RequestMapping(value = "/delRelationMonEvi")
    public AjaxMessage delRelationMonEvi(String cusNumber,String monitorSqno){
		try {
			monitorService.delRelationMonEvi(cusNumber,monitorSqno);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}	
    }
    //删除与监督关联的接收人信息 
	@RequestMapping(value = "/delRelationMonRec")
	@ResponseBody
    public AjaxMessage delRelationMonRec(String cusNumber,String monitorSqno){
		try {
			monitorService.delRelationMonRec(cusNumber,monitorSqno);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}		
    }
	//删除已建监督单（现在把已建监督单作为监督单列表使用）
	@ResponseBody
	@RequestMapping(value = "/deleteYjjdd")
    public AjaxMessage deleteYjjdd(String cusNumber,String monitorSqno){
		return monitorService.deleteYjjdd(cusNumber,monitorSqno);
    }
	
	//判断监督单是否可编辑，如果监督单被巡查通报使用，并且巡查通报已被审批成功，则不可修改
	@ResponseBody
	@RequestMapping(value = "/monitorCanEdit")
    public AjaxMessage monitorCanEdit(String monitorSqno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("imrMonitorSqno", monitorSqno);
		map.put("inoApprovalSttsIndc", "3");	//0未提交 ，1已提交-待审核 ， 2不同意  ， 3 同意
    	int count = monitorService.searchMonitorInXctbCount(map);
    	
    	if(count>0) {
    		return new AjaxMessage(false,null,"监督单已被巡查通报使用，且巡查通报已被审批，不可修改！");
    	}else {
    		return new AjaxMessage(true);
    	}
    }
}
