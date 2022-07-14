package com.cesgroup.prison.foreign.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.foreign.dao.ForeignPeopleDtlsMapper;
import com.cesgroup.prison.foreign.entity.ForeignPeopleDtls;
import com.cesgroup.prison.foreign.service.ForeignPeopleDtlsService;

@Controller
@RequestMapping("foreignPeopel")
public class ForeignPeopleDtlsController extends BaseEntityDaoServiceCRUDController<ForeignPeopleDtls, String, ForeignPeopleDtlsMapper, ForeignPeopleDtlsService> {

	@Autowired
	private ForeignPeopleDtlsService foreignPeopelService;
	
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv=new ModelAndView("foreign/foreignPeopelList");
		return mv;
	}
		
	@RequestMapping("/pageList")
	@ResponseBody
	@Logger(action = "查询", logger = "分页查询外来人员信息")
	public Page<Map<String, Object>> findPage(String cusNumber,String startTime,String endTime,String fpdIdCardCode,String fpdForeighCarId){
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("cusNumber", cusNumber);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("fpdIdCardCode", fpdIdCardCode);
		map.put("fpdForeighCarId",fpdForeighCarId);
		PageRequest pageRequest = buildPageRequest();
		Page<Map<String, Object>> pageFind = foreignPeopelService.pageFind(map, pageRequest);
		return pageFind;
	}
	
	@RequestMapping("/insertForeignPeopel")
	@ResponseBody
	@Logger(action = "新增", logger = "新增外来人员记录")
	public AjaxMessage insert(ForeignPeopleDtls entity,String foreignPeopelPicIds){

		try {
			foreignPeopelService.insertSelectiveB(entity,foreignPeopelPicIds);
			return new AjaxMessage(true, null, "操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			return new AjaxMessage(false, null, "操作失败");
		}
	}
	
	@RequestMapping("/updateForeignPeopel")
	@ResponseBody
	@Logger(action = "修改", logger = "修改外来人员记录")
	public AjaxMessage update(ForeignPeopleDtls entity,String foreignPeopelPicIds){
		try {
			foreignPeopelService.updateByPrimaryKeySelective(entity, foreignPeopelPicIds);
			return new AjaxMessage(true, null, "操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			return new AjaxMessage(false, null, "操作失败");
		}
	}
	
	@RequestMapping("/delForeignPeopel")
	@ResponseBody
	@Logger(action = "删除", logger = "删除外来人员记录")
	public AjaxMessage del(String  id){
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("id", id);
		try {
			foreignPeopelService.deleteByPrimaryKey(map);
			return new AjaxMessage(true, null, "操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			return new AjaxMessage(false, null, "操作失败");
		}
	}
	
	@RequestMapping("/findRecord")
	@ResponseBody
	@Logger(action = "查询", logger = "查询证件号对应的外来人员的最新基础信息")
	public AjaxMessage findRecord(String fpdIdCardCode,String cusNumber ){
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("fpdIdCardCode", fpdIdCardCode);
		map.put("cusNumber", cusNumber);
		try {
			Map<String, Object> selectBaseInfoByCardId = foreignPeopelService.selectBaseInfoByCardId(map);	
			if(selectBaseInfoByCardId==null) {
				return new AjaxMessage(false, null, "暂无记录");
			}
			selectBaseInfoByCardId.put("pics",foreignPeopelService.findPic((String)selectBaseInfoByCardId.get("ID")) );
			selectBaseInfoByCardId.put("ID", null);
			return new AjaxMessage(true, selectBaseInfoByCardId, "操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			return new AjaxMessage(false, null, "操作失败");
		}
	}
	
	@RequestMapping("/findPic")
	@ResponseBody
	@Logger(action = "删除", logger = "删除外来人员记录")
	public AjaxMessage findPic(String  id){
		try {
			List<AffixEntity> findPic = foreignPeopelService.findPic(id);
			return new AjaxMessage(true, findPic, "操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			return new AjaxMessage(false, null, "操作失败");
		}
	}
}
