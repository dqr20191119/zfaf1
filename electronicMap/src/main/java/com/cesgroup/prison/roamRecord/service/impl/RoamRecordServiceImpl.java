package com.cesgroup.prison.roamRecord.service.impl;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.roamRecord.dao.RoamRecordDao;
import com.cesgroup.prison.roamRecord.entity.RoamRecord;
import com.cesgroup.prison.roamRecord.service.RoamRecordService;

/**
 * 三维巡视记录业务操作类
 * 
 * @author lincoln.cheng
 *
 */
@Service(value = "roamRecordService")
@Transactional
public class RoamRecordServiceImpl extends BaseDaoService<RoamRecord, String, RoamRecordDao> implements RoamRecordService {
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
    				param.put("roamDeptId", user.getDprtmntCode());
    			}
    		}
            return this.getDao().findWithPage(param, pageable);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

	@Transactional
	@Override
	public void save(RoamRecord roamRecord) throws BusinessLayerException {
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			if(userBean != null && roamRecord != null) {
				RoamRecord roamRecord_op = new RoamRecord();
				roamRecord_op.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				roamRecord_op.setCusNumber(userBean.getCusNumber());
				roamRecord_op.setRoamPathId(roamRecord.getRoamPathId());
				roamRecord_op.setRoamPathName(roamRecord.getRoamPathName());
				roamRecord_op.setScreenPlanId(roamRecord.getScreenPlanId());
				roamRecord_op.setScreenPlanName(roamRecord.getScreenPlanName());
				roamRecord_op.setRoamUserId(userBean.getUserId());
				roamRecord_op.setRoamUserName(userBean.getUserName());
				roamRecord_op.setRoamTime(new Date());
				roamRecord_op.setRoamDeptId(userBean.getDprtmntCode());
				roamRecord_op.setRoamDeptName(userBean.getDprtmntName());
				this.getDao().insert(roamRecord_op);
			}
		} catch (Exception e) {
			throw new BusinessLayerException("保存三维巡视记录发生异常", e);
		}
	}
}
