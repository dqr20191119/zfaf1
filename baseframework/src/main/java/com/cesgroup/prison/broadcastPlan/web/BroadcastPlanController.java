package com.cesgroup.prison.broadcastPlan.web;

import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.broadcastPlan.dao.BroadcastPlanDao;
import com.cesgroup.prison.broadcastPlan.entity.BroadcastPlan;
import com.cesgroup.prison.broadcastPlan.service.BroadcastPlanService;
import com.cesgroup.prison.utils.DataUtils;

/**
 * 广播曲目维护控制器
 * 
 * @author lincoln.cheng
 *
 */
@Controller
@RequestMapping(value = "broadcastPlan")
public class BroadcastPlanController extends BaseEntityDaoServiceCRUDController<BroadcastPlan, String, BroadcastPlanDao, BroadcastPlanService> {
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(BroadcastPlanController.class);

	/**
	 * 广播预案列表页面
	 * @return
	 */
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("jfsb/broadcast/plan/list");
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
		ModelAndView mv = new ModelAndView("jfsb/broadcast/plan/save");
		return mv;
	}

	/**
	* @methodName: openDialog
	* @Description: 打开修改窗口
	* @return ModelAndView    
	* @throws
	*/
	@RequestMapping(value = "/openDialog/update")
	public ModelAndView openUpdateDialog(@RequestParam(value = "id", defaultValue = "", required = false) String id) {
		ModelAndView mv = new ModelAndView("jfsb/broadcast/plan/update");
		mv.addObject("id", id);
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询广播预案曲目信息")
	public Map<String, Object> listAll(BroadcastPlan broadcastPlan) {
		try {
			PageRequest pageRequest = buildPageRequest();
			Page<BroadcastPlan> page = this.getService().listAll(broadcastPlan, pageRequest);
			return DataUtils.pageToMap(page);
		} catch (BusinessLayerException e) {
			logger.error("分页查询广播曲目信息发生异常");
			return null;
		}
	}

	@RequestMapping(value = "/queryById")
	@ResponseBody
	public AjaxMessage queryById(@RequestParam(value = "id", defaultValue = "", required = false)String id) {
		AjaxMessage ajaxMessage =new AjaxMessage();	
		try {
			BroadcastPlan broadcastPlan = this.service.selectById(id);
			ajaxMessage.setSuccess(true);
			ajaxMessage.setObj(broadcastPlan);
			ajaxMessage.setMsg("查询广播预案成功!");
		} catch (Exception e) {
			ajaxMessage.setSuccess(false);
			ajaxMessage.setMsg("查询广播预案失败");
			e.printStackTrace();
		}
		return ajaxMessage;
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	public AjaxMessage save(BroadcastPlan entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		try {
			this.getService().save(entity);
			ajaxMessage.setSuccess(true);
			ajaxMessage.setMsg("插入成功");
			return ajaxMessage;
		} catch (Exception e) {
			e.printStackTrace();
			ajaxMessage.setSuccess(false);
			ajaxMessage.setMsg("新增广播预案发生异常");
			return ajaxMessage;
		}
	}

	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage update(BroadcastPlan enyity) {
		AjaxMessage ajaxMessage = null;
		try {
			this.service.updateById(enyity);
			ajaxMessage = new AjaxMessage(true,null,"修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxMessage =new AjaxMessage(false,null,"更新广播预案发生异常");
		}
		return ajaxMessage;
	}
	
	
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public AjaxMessage delete(@RequestBody List<String> id) {
		try {
			return this.service.deleteByIds(id);
		} catch (BusinessLayerException e) {
			AjaxMessage ajaxMessage = new AjaxMessage();
			ajaxMessage.setSuccess(false);
			ajaxMessage.setMsg("删除广播预案发生异常");
			return ajaxMessage;
		}
	}
}
