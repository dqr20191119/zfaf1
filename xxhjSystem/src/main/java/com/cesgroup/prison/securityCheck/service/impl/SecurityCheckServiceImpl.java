package com.cesgroup.prison.securityCheck.service.impl;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.securityCheck.dao.SecurityCheckDao;
import com.cesgroup.prison.securityCheck.entity.SecurityCheck;
import com.cesgroup.prison.securityCheck.service.SecurityCheckService;

@Service("securityCheckService")
public class SecurityCheckServiceImpl extends BaseDaoService<SecurityCheck, String, SecurityCheckDao> implements SecurityCheckService {

	@Override
	public Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable) {
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			if(userBean != null) {
				map.put("cusNumber", userBean.getCusNumber());
			}
			map.put("checkTime", Util.toStr(Util.DF_DATE));
			return this.getDao().listAll(map, pageable);
		} catch (Exception e) {
			return null;
		}
	}

}
