package com.cesgroup.prison.aqfxyp.wxpg.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.aqfxyp.wxpg.dao.WxpgMapper;
import com.cesgroup.prison.aqfxyp.wxpg.entity.WxpgEntity;
import com.cesgroup.prison.aqfxyp.wxpg.service.WxpgService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;

@Service("wxpgService")
public class WxpgServiceImpl extends BaseDaoService<WxpgEntity, String, WxpgMapper> implements WxpgService {
	
	@Autowired
	private WxpgMapper wxpgMapper;
	
	@Override
	public List<Map<String,Object>> getWxpg() {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		int level = AuthSystemFacade.whatLevelForLoginUser();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		// 监狱局
		if(level == 1) {
			
		}
		// 监狱
		else if(level == 2) {
			paramMap.put("cusNumber", user.getCusNumber());
		}
		// 监区
		else {
			// 监狱编号
			paramMap.put("cusNumber", user.getCusNumber());
			// 部门编号
			paramMap.put("deptCode", user.getDprtmntCode());
		}
		List<Map<String,Object>> list = wxpgMapper.getWxpg(paramMap);
		List<Map<String,Object>> wxpg = new ArrayList<Map<String,Object>>();
		Map<String,Object> m = new HashMap<String,Object>();
		m.put("gd", 0);
		m.put("zd", 0);
		m.put("dd", 0);
		for (Map<String,Object> temp : list) {
			m.put((String)temp.get("WXDJ"), temp.get("RS"));
		}
		wxpg.add(m);
		return wxpg;
	}

	@Override
	public Page<WxpgEntity> findListPage(String zt, PageRequest pageRequest) {
		Map<String, Object> paramMap=new HashMap<String, Object>();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		// 监狱编号
		paramMap.put("cusNumber", user.getCusNumber());
		// 部门编号
		paramMap.put("deptCode", user.getDprtmntCode());
		if (!"0".equals(zt)) {
			paramMap.put("wxdj", zt);
		}
		return wxpgMapper.findAllFx(paramMap,pageRequest);
	}

}
