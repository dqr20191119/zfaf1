package com.cesgroup.prison.location.service.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.location.dao.PoliceLocationDao;
import com.cesgroup.prison.location.entity.PoliceLocation;
import com.cesgroup.prison.location.service.PoliceLocationService;

@Service(value = "policeLocationService")
public class PoliceLocationServiceImpl extends BaseDaoService<PoliceLocation, String, PoliceLocationDao> implements PoliceLocationService {

	@Override
	public Page<Map<String, Object>> queryWithPage(Map<String, Object> param, Pageable pageable) {
		try {
            int level = AuthSystemFacade.whatLevelForLoginUser();
    		// 监狱局
    		if(level == 1) {
    			
    		}
    		// 监狱
    		else if(level == 2) {
    			UserBean user = AuthSystemFacade.getLoginUserInfo();
    			if(Util.notNull(user)){
    				param.put("cusNumber", user.getCusNumber());
    			}
    		}
    		// 监区
    		else {
    			UserBean user = AuthSystemFacade.getLoginUserInfo();
    			if(user != null) {
    				// 监狱编号
    				param.put("cusNumber", user.getCusNumber());
    				// 部门编号
    				param.put("deptCode", user.getDprtmntCode());
    			}
    		}
            return this.getDao().findWithPage(param, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	@Override
	public Page<Map<String, Object>> querySwsbWithPage(Map<String, Object> param, Pageable pageable) {
		try {
            int level = AuthSystemFacade.whatLevelForLoginUser();
    		// 监狱局
    		if(level == 1) {
    			
    		}
    		// 监狱
    		else if(level == 2) {
    			UserBean user = AuthSystemFacade.getLoginUserInfo();
    			if(Util.notNull(user)){
    				param.put("cusNumber", user.getCusNumber());
    			}
    		}
    		// 监区
    		else {
    			UserBean user = AuthSystemFacade.getLoginUserInfo();
    			if(user != null) {
    				// 监狱编号
    				param.put("cusNumber", user.getCusNumber());
    				// 部门编号
    				param.put("deptCode", user.getDprtmntCode());
    			}
    		}
            return this.getDao().findSwsbWithPage(param, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}
	
}
