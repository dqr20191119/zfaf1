package com.cesgroup.prison.alarm.flow.web;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.alarm.flow.entity.FlowDtlsAlarmRecordEntity;
import com.cesgroup.prison.alarm.flow.service.FlowDtlsAlarmRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.alarm.flow.dao.FlowMasterMapper;
import com.cesgroup.prison.alarm.flow.entity.FlowMasterEntity;
import com.cesgroup.prison.alarm.flow.service.FlowMasterService;
import com.cesgroup.prison.utils.DataUtils;

import net.sf.json.JSONArray;

import javax.annotation.Resource;

@Controller
@RequestMapping(value = "/flow")
public class FlowMasterController
		extends BaseEntityDaoServiceCRUDController<FlowMasterEntity, String, FlowMasterMapper, FlowMasterService> {

	@Autowired
	private FlowMasterService flowMasterService;


	@Resource
      private FlowDtlsAlarmRecordService flowDtlsAlarmRecordService;


	@RequestMapping("/save")
	public ModelAndView save(String id) {
		ModelAndView mv = new ModelAndView("alarm/flow/save");
		if (id != null && !"".equals(id)) {
			mv.addObject("id", id);
		}
		return mv;
	}

	@RequestMapping("/detail")
	public ModelAndView detail(String id) {
		ModelAndView mv = new ModelAndView("alarm/flow/detail");
		mv.addObject("id", id);
		return mv;
	}

	@RequestMapping("/list")
	public ModelAndView list() {
		ModelAndView mv = new ModelAndView("alarm/flow/list");
		return mv;
	}

	/**
	 * 保存、更新流程
	 * 
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/saveFlow")
	@ResponseBody
	@Logger(action = "保存、更新流程", logger = "保存、更新流程信息")
	public AjaxMessage saveFlow(FlowMasterEntity flowMaster) throws Exception {
		try {
			FlowMasterEntity entity = flowMasterService.saveFlow(flowMaster);
			return new AjaxMessage(true, entity, "success");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxMessage(false, null, "系统出错");
		}
	}

	/**
	 * 删除流程
	 * 
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	@RequestMapping(value = "/deleteByIds")
	@ResponseBody
	@Logger(action = "删除流程", logger = "删除流程")
	public AjaxMessage deleteByIds(String obj) {
		try {
			JSONArray jsonArray = JSONArray.fromObject(obj);
			List<String> ids = JSONArray.toList(jsonArray);
			int num = flowMasterService.deleteByIds(ids);
			if (num > 0) {
				return new AjaxMessage(true, null, "删除成功");
			} else {
				return new AjaxMessage(false, null, "删除失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxMessage(false, null, "系统出错");
		}
	}

	/**
	 * 查找流程
	 * 
	 * @return
	 */
	@RequestMapping(value = "/findById")
	@ResponseBody
	@Logger(action = "查找流程", logger = "查找流程")
	public AjaxMessage findById(String id) {
		try {
			FlowMasterEntity entity = flowMasterService.findById(id);
			return new AjaxMessage(true, entity, "success");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxMessage(false, null, "系统出错");
		}
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/findByPage")
	@ResponseBody
	@Logger(action = "流程分页查询", logger = "流程分页查询")
	public Map<String, Object> findByPage(FlowMasterEntity flowMaster) {
		try {
			if (flowMaster.getHfmFlowName() != null)
				flowMaster.setHfmFlowName(URLDecoder.decode(flowMaster.getHfmFlowName()));
			PageRequest pageRequest = buildPageRequest();
			Page<Map<String, String>> page = flowMasterService.findByPage(flowMaster, pageRequest);
			return DataUtils.pageToMap(page);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	* @methodName: findMaster
	* @Description: 流程主表信息
	* @param cusNumber 当前用户所在监狱号
	* @param cusNum 省局监狱号
	* @param id 主表id号
	* @return Map<String,Object>
	* @throws  
	*/
	@RequestMapping(value = "/findMaster")
	@ResponseBody
	@Logger(action = "查询", logger = "流程主表信息")
	public AjaxMessage findMaster(String cusNumber, String cusNum, String id) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			List<Map<String, Object>> list = service.findMaster(cusNumber, cusNum, id);
			flag = true;
			result = list;
		} catch (Exception e) {
			flag = false;
			result = "查询流程失败： " + e.getMessage();
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
	* @methodName: findFlowDtls
	* @Description: 流程详情信息
	* @param cusNumber 当前用户所在监狱号
	* @param cusNum 省局监狱号
	* @param flowId 主表id号
	* @return Map<String,Object>
	* @throws  
	*/
	@RequestMapping(value = "/findFlowDtls")
	@ResponseBody
	@Logger(action = "查询", logger = "流程详情")
	public AjaxMessage findFlowDtls(String cusNumber, String cusNum, String flowId) {
		boolean flag = false;
		Object result = null;
		AjaxMessage ajaxMsg = new AjaxMessage();
		try {
			List<Map<String, Object>> list = service.findFlowDtls(cusNumber, cusNum, flowId);
			flag = true;
			result = list;
		} catch (Exception e) {
			flag = false;
			result = "查询流程失败： " + e.getMessage();
		}
		if (flag) {
			ajaxMsg.setObj(result);
		} else {
			ajaxMsg.setMsg((String) result);
		}
		ajaxMsg.setSuccess(flag);

		return ajaxMsg;
	}

	@RequestMapping(value = "/seachComboData")
	@ResponseBody
	public List<Map<String, Object>> seachComboData(String cusNumber) throws Exception {
		List<Map<String, Object>> maps = new ArrayList<>();
		maps = service.searchCombData(cusNumber);
		return maps;
	}

    /**
     * 保存或更新报警流程详细记录信息
     * @param flowDtlsAlarmRecordEntity
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    public AjaxResult saveOrUpdate(FlowDtlsAlarmRecordEntity flowDtlsAlarmRecordEntity)  {
        try {
            return flowDtlsAlarmRecordService.saveOrUpdate(flowDtlsAlarmRecordEntity);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("保存或修改失败");
        }

    }

    /**
     * 通过id查询抱紧流程记录表数据
     * @param flowDtlsAlarmRecordEntity
     * @return
     */
    @RequestMapping(value = "/getFlowDtlsAlarmRecordById")
    @ResponseBody
    public AjaxResult getById(FlowDtlsAlarmRecordEntity flowDtlsAlarmRecordEntity)  {
        try {
            FlowDtlsAlarmRecordEntity flowDtlsAlarmRecordEntity1 = flowDtlsAlarmRecordService.selectOne(flowDtlsAlarmRecordEntity.getId());
            return AjaxResult.success(flowDtlsAlarmRecordEntity1);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("查询失败");
        }
    }


    /**
     * 通过 报警记录id , 报警流程id  ,报警详情id 查询报警详情记录表数据
     * @param flowDtlsAlarmRecordEntity
     * @return
     */
    @RequestMapping(value = "/getByFlowDtlsAlarmRecord")
    @ResponseBody
    public AjaxResult getByFlowDtlsAlarmRecord(FlowDtlsAlarmRecordEntity flowDtlsAlarmRecordEntity)  {
        try {
            FlowDtlsAlarmRecordEntity flowDtlsAlarmRecordEntity1 =  flowDtlsAlarmRecordService.ListBylowDtlsAlarmRecord(flowDtlsAlarmRecordEntity);
            return AjaxResult.success(flowDtlsAlarmRecordEntity1);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("查询失败");
        }
    }

    /**
     * 通过 报警记录id ,查询报警流程详情表数据
     * @param flowDtlsAlarmRecordEntity
     * @return
     */
    @RequestMapping(value = "/ListByAlarmRecordId")
    @ResponseBody
    public AjaxResult ListByAlarmRecordId(@RequestParam("recordId")String recordId)  {
        try {
            AjaxResult ajaxResult =  flowDtlsAlarmRecordService.ListByAlarmRecordId(recordId);
            return ajaxResult;
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("查询失败");
        }
    }
}
