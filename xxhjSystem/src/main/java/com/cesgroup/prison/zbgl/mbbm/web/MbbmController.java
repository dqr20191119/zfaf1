package com.cesgroup.prison.zbgl.mbbm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.zbgl.mbbm.dao.MbbmMapper;
import com.cesgroup.prison.zbgl.mbbm.entity.MbbmEntity;
import com.cesgroup.prison.zbgl.mbbm.service.MbbmService;

/**
 * 模板部门
 * 
 */
@Controller
@RequestMapping(value = "/zbgl/mbbm")
public class MbbmController extends BaseEntityDaoServiceCRUDController<MbbmEntity, String, MbbmMapper, MbbmService> {

	@Resource
	private MbbmService mbbmService;
	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public MbbmEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return mbbmService.getById(id); 
	}
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(MbbmEntity mbbmEntity, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PageRequest pageRequest = buildPageRequest();
		//mbbmEntity.setDmdStatus(CommonConstant.STATUS_VALID_CODE);
		mbbmEntity.setDmdCusNumber(user.getOrgCode());
		Page<MbbmEntity> pageInfo = (Page<MbbmEntity>) mbbmService.findList(mbbmEntity, pageRequest);
		
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(MbbmEntity mbbmEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String param = mbbmEntity.getParam();			
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		mbbmEntity.setDmdCusNumber(user.getOrgCode());
		//mbbmEntity.setDmdStatus(CommonConstant.STATUS_VALID_CODE);
		mbbmEntity.setDmdModeId("");                   //该条件为查询是否重复编排特殊情况，若有其他情况则需分类讨论
		List<Map<String, Object>> list = mbbmService.findAllList(mbbmEntity);
		
		if( param != null && !param.equals("")) {                //有参数是返回下拉框值
			List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
			
			for(int i = 0; i<list.size(); i++){
				
				String startDate = ((String) list.get(i).get("DMD_START_DATE")).substring(0,10);
				String endDate = ((String) list.get(i).get("DMD_END_DATE")).substring(0,10);

				String dutyDate = startDate+" ~ "+endDate;
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("value", list.get(i).get("ID"));
				map.put("text", dutyDate);	
				maps.add(map);
			}
			list = maps;
		} 
		return list;
	}
	@RequestMapping("/updateDmdZtById")
	@ResponseBody
	public AjaxResult updateDmdZtById(MbbmEntity mbbmEntity) {
		try {
			this.service.updateDmdZtById(mbbmEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("更新失败");
		}
		return AjaxResult.success("更新成功");
	}
	
	
}
