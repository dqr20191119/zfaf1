package com.cesgroup.prison.zfxx.zfdm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.xxhj.jnmj.service.JnmjService;
import com.cesgroup.prison.zfxx.zfdm.dao.ZfXsdmDao;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfXsdm;
import com.cesgroup.prison.zfxx.zfdm.service.ZfXsdmService;

/**
 * 罪犯巡视点名业务操作类
 * 
 * @author lincoln.cheng
 *
 */
@Service(value = "zfXsdmService")
@Transactional
public class ZfXsdmServiceImpl extends BaseDaoService<ZfXsdm, String, ZfXsdmDao> implements ZfXsdmService {
	
	
	@Resource
	private JnmjService jnmjService;
	
	
	@Transactional(readOnly = true)
	@Override
	public Page<Map<String, Object>> queryWithPage(Map<String, Object> param, Pageable pageable) {
		 Page page = null;
		try {
            int level = AuthSystemFacade.whatLevelForLoginUser();
            UserBean user = AuthSystemFacade.getLoginUserInfo();
            Map<String, Object> map = new HashMap<String, Object>();
    		
    		// 监狱局
    		if(level == 1) {
    			
    		}
    		// 监狱
    		else if(level == 2) {
    			
    			if(user != null){
    				map.put("jyId", user.getOrgCode());
    			}
    		}
    		// 监区
    		else {
    			if(user != null) {
    				// 监狱编号
    				map.put("jyId", user.getOrgCode());
    				// 部门编号
    				//param.put("deptname", user.getDprtmntName());
    			}
    		}
    		List<Map<String, Object>> listsy = this.getDao().findqbjqxydmr(map);
    		List<Map<String, Object>> listyd = this.getDao().findydmr(map);
    		//拿到监狱
    		List<OrgEntity> orgEntityLists = AuthSystemFacade.getAllJyInfo();
    		//展示监狱监区
		 
    		for (int i = 0;i<listsy.size();i++) {
    			Map<String, Object> map2= listsy.get(i);
				String zs = String.valueOf(map2.get("ZS"));
				String jyId = String.valueOf(map2.get("JY_ID"));
				String jqId = String.valueOf(map2.get("JQ_ID"));
				if(level != 2&&level != 1) {
					if(!user.getDprtmntCode().equals(jqId)){
						listsy.remove(map2);
						i--;
						continue;
					} 
				}
				int qqrs = Integer.valueOf(zs);
				for (int i1 = 0;i1<listyd.size();i1++) {
					Map<String, Object> map3= listyd.get(i1);
					String zs1 = String.valueOf(map3.get("ZS"));
					String jqId1 = String.valueOf(map3.get("JQ_ID"));
					if(jqId.equals(jqId1)){
						qqrs = qqrs - Integer.valueOf(zs1);
					}
				}
				
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
				
				map2.put("QQRS", qqrs);
			}
    		Map mapppp = new HashMap();
   		    page = this.getDao().finndListpd(mapppp,pageable);
   		 List<Map<String, Object>> listpage = page.getContent();
   		 listpage.clear();
   		 listpage.addAll(listsy);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return page;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ZfXsdm> queryLatestXsdmRecordOfJianqu() throws BusinessLayerException {
		List<ZfXsdm> zfXsdmList = new ArrayList<ZfXsdm>();
		try {
            int level = AuthSystemFacade.whatLevelForLoginUser();
    		// 监狱局
    		if(level == 1) {
    			
    		}
    		// 监狱
    		else if(level == 2) {
    			
    		}
    		// 监区
    		else {
    			UserBean user = AuthSystemFacade.getLoginUserInfo();
    			if(user != null) {
		    		// 查询巡视点名
		    		List<ZfXsdm> xsdmList = this.getDao().findByCorpnameAndDeptnameOrderByDmsjDesc(user.getOrgName(), user.getDprtmntName());
		    		if(xsdmList != null && xsdmList.size() > 0) {
		    			// 获取最近的有巡视点名记录的日期
		    			String dmsj = Util.toStr(xsdmList.get(0).getDmsj(), Util.DF_DATE);
		    			
		    			// 根据点名日期、监狱名称、监区名称查询巡视点名记录
			    		xsdmList = this.getDao().findByDmsjAndCorpnameAndDeptnameOrderByDmsjDesc(dmsj, user.getOrgName(), user.getDprtmntName());
		    			
		    			zfXsdmList.addAll(xsdmList);
		    		}
    			}
    		}
        } catch (Exception e) {
            throw new BusinessLayerException("查询监区最近的巡视点名信息发生异常");
        }
        return zfXsdmList;
	}
}
