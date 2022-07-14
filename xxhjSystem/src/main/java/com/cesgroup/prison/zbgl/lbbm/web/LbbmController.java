package com.cesgroup.prison.zbgl.lbbm.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.zbgl.lbbm.dao.LbbmMapper;
import com.cesgroup.prison.zbgl.lbbm.entity.LbbmEntity;
import com.cesgroup.prison.zbgl.lbbm.service.LbbmService;

/**
 * 类别部门管理
 * 
 */
@Controller
@RequestMapping(value = "/zbgl/lbbm")
public class LbbmController extends BaseEntityDaoServiceCRUDController<LbbmEntity, String, LbbmMapper, LbbmService> {
	
	@Resource
	private LbbmService lbbmService;
	 
	@RequestMapping(value = "/searchAllData")
	@ResponseBody
	public List<Map<String, Object>> searchAllData(LbbmEntity lbbmEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String param = lbbmEntity.getParam();			
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		lbbmEntity.setDcdCusNumber(user.getOrgCode());
		//lbbmEntity.setDcdStatus("1");
		List<Map<String, Object>> list = lbbmService.findAllList(lbbmEntity);
		if(param != null) {
			if((param.toString()).equals("1")) {                //有参数是返回下拉框值
				List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
				
				for(int i = 0; i<list.size(); i++){
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("value", list.get(i).get("DCD_DPRTMNT_ID"));
					map.put("text", "");	
					maps.add(map);
				}
				list = maps;
			} else if( param.equals("2")) {                //有参数是返回下拉框值
				List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
				
				for(int i = 0; i<list.size(); i++){
					
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("value", list.get(i).get("DCD_CATEGORY_ID"));
					map.put("text", list.get(i).get("DCA_CATEGORY_NAME"));	
					maps.add(map);
				}
				list = maps;
			}
		}
		return list;
	}
	
}
