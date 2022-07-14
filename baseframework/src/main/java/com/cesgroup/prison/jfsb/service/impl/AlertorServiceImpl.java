package com.cesgroup.prison.jfsb.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.dao.AlertorMapper;
import com.cesgroup.prison.jfsb.entity.AlertorEntity;
import com.cesgroup.prison.jfsb.service.AlertorService;

/**      
* @projectName：prison   
* @ClassName：AlertorServiceImpl   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月12日 下午3:51:06   
* @version        
*/
@Service
@Transactional
public class AlertorServiceImpl extends BaseDaoService<AlertorEntity, String, AlertorMapper> implements AlertorService {

	@Autowired
	private AlertorMapper mapper;

	@Override
	public void deleteByIds(List<String> ids) {
		mapper.deleteByIds(ids);
	}

	@Override
	public AjaxMessage addInfo(AlertorEntity entity) {
		AjaxMessage ajaxMsg = new AjaxMessage();
		boolean flag = false;
		String result = null;
		try {
			AlertorEntity alertorEntity = new AlertorEntity();
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			alertorEntity.setAbdIdnty(entity.getAbdIdnty());
			alertorEntity.setAbdCusNumber(userBean.getCusNumber());
			Map<String, Object> map = this.findAlertorSum(alertorEntity);
			if (Integer.parseInt((String) map.get("SUM")) > 0) {
				flag = false;
				result = "编号【" + entity.getAbdIdnty() + "】重复，保存失败";
			} else {
				String userId = userBean.getUserId();
				Date date = new Date();
				entity.setAbdActionIndc("1");
				entity.setAbdCusNumber(userBean.getCusNumber());
				entity.setAbdCrteTime(date);
				entity.setAbdCrteUserId(userId);
				entity.setAbdUpdtTime(date);
				entity.setAbdUpdtUserId(userId);
				mapper.insert(entity);
				flag = true;
				result = "保存成功";
			}
		} catch (Exception e) {
			flag = false;
			result = "保存失败：" + e.getMessage();
		}
		ajaxMsg.setMsg(result);
		ajaxMsg.setSuccess(flag);
		return ajaxMsg;
	}

	@Override
	public void updateInfo(AlertorEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setAbdUpdtTime(date);
			entity.setAbdUpdtUserId(userId);
			entity.setAbdActionIndc("2");
			map.put("alertorEntity", entity);
		}
		mapper.updateInfo(map);
	}

	@Override
	public Page<Map<String, Object>> listAll(AlertorEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("alertorEntity", entity);
		}
		return mapper.listAll(map, pageable);
	}

	@Override
	public AlertorEntity findById(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public List<Map<String, Object>> searchCombData(String cusNumber, String areaId) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber.trim());
		}
		if (StringUtils.isNotBlank(areaId)) {
			map.put("areaId", areaId.trim());
		}
		return mapper.searchCombData(map);
	}

	@Override
	public Map<String, Object> findAlertorSum(AlertorEntity entity) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("alertorEntity", entity);
		}
		return mapper.findAlertorSum(map);
	}
}
