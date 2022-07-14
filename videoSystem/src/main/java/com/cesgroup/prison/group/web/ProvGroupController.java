package com.cesgroup.prison.group.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.xtgl.service.DvcRoleService;

/**   
*    
* @projectName：prison   
* @ClassName：ProvGroupController   
* @Description：省局群组管理Controller 
* @author：zhengke   
* @date：2017-12-01 15:25   
* @version        
*/
@Controller
@RequestMapping(value = "/provGroupManage")
public class ProvGroupController extends BaseController {
	
	@Autowired
	private DvcRoleService service;
	
	@RequestMapping("/allPrisonAreaCameraTree")
	@ResponseBody
	public List<Map<String,Object>> allPrisonAreaCameraTree(HttpServletRequest request){
		String id=request.getParameter("id");
		String cbdSttsIndc_except=request.getParameter("cbdSttsIndc_except");
		List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();	
		try {
			if("".equals(id)|| null==id){
				List<OrgEntity> orgList  = AuthSystemFacade.getAllJyInfo();
			
				for (OrgEntity orgEntity : orgList) {
					Map<String,Object> map =new HashMap<String,Object>();
					map.put("org_id", orgEntity.getOrgKey());
					map.put("pId", null);
					map.put("id", orgEntity.getOrgKey());
					map.put("name", orgEntity.getOrgName());
					map.put("node_type", "prison");
					map.put("isParent", true);
					list.add(map);
				}
				return list;
			}else {
				return service.simpleAreaCameraTreeByXML("2",id,null,cbdSttsIndc_except);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
