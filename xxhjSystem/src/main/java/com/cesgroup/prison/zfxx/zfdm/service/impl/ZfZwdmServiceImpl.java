package com.cesgroup.prison.zfxx.zfdm.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.zfxx.zfdm.dao.ZfZwdmDao;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfZwdm;
import com.cesgroup.prison.zfxx.zfdm.service.ZfZwdmService;

/**
 * 罪犯巡视点名业务操作类
 * 
 * @author lincoln.cheng
 *
 */
@Service(value = "zfZwdmService")
@Transactional
public class ZfZwdmServiceImpl extends BaseDaoService<ZfZwdm, String, ZfZwdmDao> implements ZfZwdmService {
	@Transactional(readOnly = true)
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
    			if(user != null){
    				param.put("corpname", user.getOrgName());
    			}
    		}
    		// 监区
    		else {
    			UserBean user = AuthSystemFacade.getLoginUserInfo();
    			if(user != null) {
    				// 监狱编号
    				param.put("corpname", user.getOrgName());
    				// 部门编号
    				param.put("deptname", user.getDprtmntName());
    			}
    		}
            return this.getDao().findWithPage(param, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	@Transactional(readOnly = true)
	@Override
	public List<ZfZwdm> queryLatestZwdmRecordOfJianqu() throws BusinessLayerException {
		List<ZfZwdm> zfZwdmList = new ArrayList<ZfZwdm>();
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
    			Map<String, Object> param = new HashMap<String, Object>();
    			param.put("cusNumber", user.getCusNumber());
    			param.put("dprtmntCode", user.getDprtmntCode());
    			String dmpc = this.getDao().selectDmpc(param);
    			if (dmpc == null) {
    				zfZwdmList = this.getDao().findByDmlxAndCorpnameAndDeptnameOrderByDtdmsjDesc("早晚点名", user.getCusNumber(), user.getDprtmntCode());
        			if (zfZwdmList != null && zfZwdmList.size() > 0) {
            			for (ZfZwdm temp : zfZwdmList) {
            				temp.setSdrs(0);
        					temp.setCorpid("orange");
        					temp.setDmlx("未完成");
            			}
        			}
    			} else {
    				param.put("dmpc", dmpc);
    				zfZwdmList = this.getDao().selectDmqk(param);
    				if (zfZwdmList != null && zfZwdmList.size() > 0) {
            			for (ZfZwdm temp : zfZwdmList) {
            				if (temp.getZrs() == temp.getSdrs()) {
            					temp.setCorpid("blue");
            					temp.setDmlx("完成");
            				} else {
            					temp.setCorpid("orange");
            					temp.setDmlx("未完成");
            				}
            			}
        			}
    			}
    		}
        } catch (Exception e) {
        	e.printStackTrace();
            throw new BusinessLayerException("查询监区最近的早晚点名信息发生异常");
        }
        return zfZwdmList;
	}
}
