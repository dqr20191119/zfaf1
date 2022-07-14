package com.cesgroup.prison.zfxx.zfdm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.cesgroup.prison.db.service.RedisCache;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.xxhj.jnmj.service.JnmjService;
import com.cesgroup.prison.zfxx.zfdm.dao.ZfdmMapper;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfdmEntity;
import com.cesgroup.prison.zfxx.zfdm.service.ZfdmService;

/**
 * 湖南罪犯点名详情
 *
 */
@Service(value = "ZfdmService")
@Transactional
public class ZfdmServiceImpl extends BaseDaoService<ZfdmEntity, String, ZfdmMapper> implements ZfdmService {


	@Resource
	private JnmjService jnmjService;
	@Override
	/**
	* @methodName: searchZfdm
	* @Description: 分页列表查询
	* @param map
	* @param pageRequest
	* @return Page<Map<String,Object>>    
	* @throws
	*/
	public Page<Map<String, Object>> searchZfdm(Map<String, Object> map, PageRequest pageRequest){
		Page  page = null;
		try {
		int level = AuthSystemFacade.whatLevelForLoginUser();
         UserBean user = AuthSystemFacade.getLoginUserInfo();
 		
 		// 监狱局
 		if(level == 1) {
 			
 		}
 		// 监狱
 		else if(level == 2) {
 			
 			if(user != null){
 				map.put("cusNumber", user.getOrgCode());
 			}
 		}
 		// 监区
 		else {
 			if(user != null) {
 				// 监狱编号
 				map.put("cusNumber", user.getOrgCode());
 				
 				map.put("deptId", user.getDprtmntCode());
 				// 部门编号 
 			}
 		}
 		 
 		page = this.getDao().searchZfdm(map, pageRequest);
    	List<Map<String, Object>> listpage = page.getContent(); 
    	List<OrgEntity> orgEntityLists = null;
		orgEntityLists = AuthSystemFacade.getAllJyInfo();
		 for (int i = 0;i<listpage.size();i++) {
			 Map<String, Object> map2 = listpage.get(i);
			 String jyId = String.valueOf(map2.get("JY_ID"));
			 String jqId = String.valueOf(map2.get("JQ_ID"));
			 for (int j = 0; j < orgEntityLists.size(); j++) { 
				 if (orgEntityLists.get(j).getOrgKey().equals(jyId)) {
						map2.put("JY_NAME", orgEntityLists.get(j).getOrgName());
						List<OrgEntity> orgEntityList = jnmjService.queryPrisonDepartment(jyId);
						for(int k = 0; k< orgEntityList.size(); k++) {
							
							if (orgEntityList.get(k).getOrgKey().equals(jqId)) {
								map2.put("JQ_NAME", orgEntityList.get(k).getOrgName());
								break;
						    }
					    } 
					}	
		    	}
			
		}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
 		
		return page;
	}



}
