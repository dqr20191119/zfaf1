package com.cesgroup.prison.broadcastPlay.web;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.prison.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.broadcastPlay.dao.BroadcastRecordDao;
import com.cesgroup.prison.broadcastPlay.dto.BroadcastRecordDto;
import com.cesgroup.prison.broadcastPlay.entity.BroadcastRecord;
import com.cesgroup.prison.broadcastPlay.service.BroadcastRecordService;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/broadcastRecord")
public class BroadcastRecordController extends BaseEntityDaoServiceCRUDController<BroadcastRecord, String, BroadcastRecordDao, BroadcastRecordService> {

	/**
	 * 开始播放
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "startPlay")
	@ResponseBody
	public AjaxResult startPlay(BroadcastRecordDto entity) {
		try {
			this.getService().startPlay(entity);
			return AjaxResult.success("开始播放广播指令发送成功");
		} catch (BusinessLayerException e) {
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			return AjaxResult.error("开始播放广播指令发送失败");
		}
	}

	/**
	 * 停止播放
	 * @param entity
	 * @return
	 */
	@RequestMapping(value = "stopPlay")
	@ResponseBody
	public AjaxResult stopPlay(BroadcastRecordDto entity) {
		try {
			this.getService().stopPlay(entity);
			return AjaxResult.success("停止播放广播指令发送成功");
		} catch (BusinessLayerException e) {
			return AjaxResult.error(e.getMessage());
		} catch (Exception e) {
			return AjaxResult.error("停止播放广播指令发送失败");
		}
	}

	/**
	 * @methodName: openDialog
	 * @Description: 打开窗口
	 * @return ModelAndView
	 * @throws
	 */
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("broadcastPlay/record_list");
		return mv;
	}

	@RequestMapping(value = "/listAll")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询广播播放记录")
	public Map<String, Object> listAll(@RequestParam(value="startTime", defaultValue="", required=false) String startTime,
									   @RequestParam(value="broadcastName", defaultValue="", required=false) String broadcastName,
									   @RequestParam(value="contentType", defaultValue="", required=false) String contentType) throws Exception {
		// 查询条件Map
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(startTime != null && !"".equals(startTime)) {
			paramMap.put("startTime", startTime);
		}
		if(broadcastName != null && !"".equals(broadcastName)) {
			paramMap.put("broadcastName", broadcastName);
		}
		if(contentType != null && !"".equals(contentType)) {
			paramMap.put("contentType", contentType);
		}
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> page = (Page<Map<String, Object>>) this.getService().listAll(paramMap, pageRequest);

		return DataUtils.pageToMap(page);
	}

}
