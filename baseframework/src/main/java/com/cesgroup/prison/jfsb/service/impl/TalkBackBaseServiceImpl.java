package com.cesgroup.prison.jfsb.service.impl;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.dao.TalkBackBaseMapper;
import com.cesgroup.prison.jfsb.entity.TalkBackBaseEntity;
import com.cesgroup.prison.jfsb.service.TalkBackBaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @projectName：prison
 * @ClassName：TalkBackBaseServiceImpl
 * @Description：
 * @author：Tao.xu
 * @date：2017年12月20日 下午7:12:57
 */
@Service
@Transactional
public class TalkBackBaseServiceImpl extends BaseDaoService<TalkBackBaseEntity, String, TalkBackBaseMapper>
		implements TalkBackBaseService {

	@Autowired
	private TalkBackBaseMapper mapper;

	@Override
	public void deleteById(String id) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(id)) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			TalkBackBaseEntity entity = new TalkBackBaseEntity();
			entity.setTbdActionIndc("3");
			entity.setTbdUpdtTime(date);
			entity.setId(id);
			entity.setTbdUpdtUserId(userId);
			map.put("talkBackBaseEntity", entity);
		}
		mapper.deleteById(map);
	}

	@Override
	public AjaxMessage addInfo(TalkBackBaseEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();

			TalkBackBaseEntity backBaseEntity = new TalkBackBaseEntity();
			backBaseEntity.setTbdCusNumber(userBean.getCusNumber());
			backBaseEntity.setTbdIdnty(entity.getTbdIdnty());
			Map<String, Object> map = this.findTalkbackBaseSum(backBaseEntity);
			if (Integer.parseInt((String) map.get("SUM")) > 0) {
				flag = false;
				obj = "编号【" + entity.getTbdIdnty() + "】重复，保存失败";
			} else {
				entity.setTbdCusNumber(userBean.getCusNumber());
				entity.setTbdCrteTime(date);
				entity.setTbdCrteUserId(userId);
				entity.setTbdUpdtTime(date);
				entity.setTbdUpdtUserId(userId);
				entity.setTbdActionIndc("1");
				mapper.insert(entity);
				flag = true;
				obj = "保存成功";
			}
		} catch (Exception e) {
			flag = false;
			obj = "保存发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@Override
	public void updateInfo(TalkBackBaseEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setTbdActionIndc("2");
			entity.setTbdUpdtTime(date);
			entity.setTbdUpdtUserId(userId);
			map.put("talkBackBaseEntity", entity);
		}
		mapper.updateInfo(map);
	}

	@Override
	public TalkBackBaseEntity findById(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public List<String> findByTbdIdntyOrTseIdnty(String cusNumber, String idnty) {
		Map<String, Object> map = new HashMap<>();
		map.put("cusNumber", cusNumber);
		map.put("idnty", idnty);
		return mapper.findByTbdIdntyOrTseIdnty(map);
	}

	@Override
	public Page<Map<String, Object>> listAll(TalkBackBaseEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("talkBackBaseEntity", entity);
		}
		return mapper.listAll(map, pageable);
	}

	@Override
	public List<Map<String, Object>> findBaseByMain(String tbdMainIdnty) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(tbdMainIdnty)) {
			map.put("tbdMainIdnty", tbdMainIdnty.trim());
		}
		return mapper.findBaseByMain(map);
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
		List<Map<String, Object>> list = mapper.searchCombData(map);
		if (!list.isEmpty()) {
			for (Map<String, Object> talkMap : list) {
				talkMap.put("value", talkMap.get("id"));
				talkMap.put("text", talkMap.get("name"));
			}
		}
		return list;
	}

	@Override
	public Map<String, Object> findTalkbackBaseSum(TalkBackBaseEntity entity) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("talkBackBaseEntity", entity);
		}
		return mapper.findTalkbackBaseSum(map);
	}
}
