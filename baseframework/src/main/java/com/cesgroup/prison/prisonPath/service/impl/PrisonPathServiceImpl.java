package com.cesgroup.prison.prisonPath.service.impl;

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

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.prisonPath.dao.PrisonPathCameraRltnMapper;
import com.cesgroup.prison.prisonPath.dao.PrisonPathMapper;
import com.cesgroup.prison.prisonPath.entity.PrisonPathCameraRltnEntity;
import com.cesgroup.prison.prisonPath.entity.PrisonPathEntity;
import com.cesgroup.prison.prisonPath.service.PrisonPathService;

@Service
@Transactional
public class PrisonPathServiceImpl extends BaseDaoService<PrisonPathEntity, String, PrisonPathMapper>
		implements PrisonPathService {

	@Autowired
	private PrisonPathMapper mapper;

	@Autowired
	private PrisonPathCameraRltnMapper cameraMapper;

	@Override
	public void deleteByIds(List<String> ids) {
		mapper.deleteByIds(ids);
	}

	public void updatePrisonPathInfo(PrisonPathEntity entity) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("prisonPathEntity", entity);
		}
		mapper.updateInfo(map);
	}

	public void updatePrisonPathCameraInfo(PrisonPathCameraRltnEntity entity) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("prisonPathCameraRltnEntity", entity);
		}
		cameraMapper.updateInfo(map);
	}

	@Override
	public Page<Map<String, Object>> listAll(PrisonPathEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("prisonPathEntity", entity);
		}
		return mapper.listAll(map, pageable);
	}

	/**
	 * 监区路线列表
	 */
	@Override
	public List<Map<String, Object>> findPathByAreaIdForCombobox(Map<String, Object> paramMap) {

		return mapper.findPathByAreaIdForCombobox(paramMap);
	}

	@Override
	public PrisonPathEntity findById(String id) {
		return mapper.selectOne(id);
	}

	@Override
	public List<Map<String, Object>> listAllForSx(String cusNumber, String areaId, String pathId, String cameraName) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber);
		}
		if (StringUtils.isNotBlank(areaId)) {
			map.put("areaId", areaId);
		}
		if (StringUtils.isNotBlank(pathId)) {
			map.put("pathId", pathId);
		}
		if (StringUtils.isNotBlank(cameraName)) {
			map.put("cameraName", cameraName);
		}
		return cameraMapper.listAllForSx(map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public AjaxMessage addInfo(Object pathData) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			Map<String, String> pathMap = (Map<String, String>) pathData;
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			String cusNumber = userBean.getCusNumber();
			Date date = new Date();
			Map<String, String> path = (Map<String, String>) JSON.parse(pathMap.get("path"));
			if (path != null) {
				PrisonPathEntity entity = new PrisonPathEntity();
				entity.setPpiCusNumber(cusNumber);
				entity.setPpiCrteTime(date);
				entity.setPpiCrteUserId(userId);
				entity.setPpiUpdtTime(date);
				entity.setPpiUpdtUserId(userId);
				entity.setPpiActionIndc("1");
				entity.setPpiDprtmnt(path.get("ppiDprtmnt"));
				entity.setPpiDprtmntId(path.get("ppiDprtmntId"));
				entity.setPpiEndArea(path.get("ppiEndArea"));
				entity.setPpiEndAreaId(path.get("ppiEndAreaId"));
				entity.setPpiPathName(path.get("ppiPathName"));
				entity.setPpiStartArea(path.get("ppiStartArea"));
				entity.setPpiStartAreaId(path.get("ppiStartAreaId"));
				entity.setPpiRemark(path.get("ppiRemark"));
				entity.setPpiPathType(path.get("ppiPathType"));
				mapper.insert(entity);

				String pathId = entity.getId();

				if (StringUtils.isNotBlank(pathId)) {
					List<Map<String, String>> camera = (List<Map<String, String>>) JSON.parse(pathMap.get("camera"));
					if (camera != null && !camera.isEmpty()) {
						for (Map<String, String> map : camera) {
							PrisonPathCameraRltnEntity cameraRltnEntity = new PrisonPathCameraRltnEntity();
							cameraRltnEntity.setPcrCamera(map.get("PCR_CAMERA"));
							cameraRltnEntity.setPcrCameraId(map.get("PCR_CAMERA_ID"));
							cameraRltnEntity.setPcrCusNumber(cusNumber);
							cameraRltnEntity.setPcrRemark(map.get("PCR_REMARK"));
							cameraRltnEntity.setPcrPathId(pathId);
							cameraRltnEntity.setPcrUpdtTime(date);
							cameraRltnEntity.setPcrUpdtUserId(userId);
							cameraRltnEntity.setPcrCrteTime(date);
							cameraRltnEntity.setPcrCrteUserId(userId);
							cameraMapper.insert(cameraRltnEntity);
						}
					}
				}
				flag = true;
				obj = "保存成功";
			}

		} catch (Exception e) {
			flag = false;
			obj = "发生异常，保存失败";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AjaxMessage updateInfo(Object pathData) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			Map<String, String> pathMap = (Map<String, String>) pathData;
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			Map<String, String> path = (Map<String, String>) JSON.parse(pathMap.get("path"));
			if (path != null) {
				PrisonPathEntity entity = new PrisonPathEntity();

				entity.setPpiUpdtTime(date);
				entity.setPpiUpdtUserId(userId);
				entity.setPpiActionIndc("2");
				entity.setId(path.get("id"));
				entity.setPpiDprtmnt(path.get("ppiDprtmnt"));
				entity.setPpiDprtmntId(path.get("ppiDprtmntId"));
				entity.setPpiEndArea(path.get("ppiEndArea"));
				entity.setPpiEndAreaId(path.get("ppiEndAreaId"));
				entity.setPpiPathName(path.get("ppiPathName"));
				entity.setPpiStartArea(path.get("ppiStartArea"));
				entity.setPpiStartAreaId(path.get("ppiStartAreaId"));
				entity.setPpiRemark(path.get("ppiRemark"));
				entity.setPpiPathType(path.get("ppiPathType"));
				this.updatePrisonPathInfo(entity);

				List<Map<String, String>> camera = (List<Map<String, String>>) JSON.parse(pathMap.get("camera"));
				if (camera != null && !camera.isEmpty()) {
					for (Map<String, String> map : camera) {
						PrisonPathCameraRltnEntity cameraRltnEntity = new PrisonPathCameraRltnEntity();
						String cameraRltnId = map.get("ID");

						cameraRltnEntity.setPcrCamera(map.get("PCR_CAMERA"));
						cameraRltnEntity.setPcrCameraId(map.get("PCR_CAMERA_ID"));
						cameraRltnEntity.setPcrRemark(map.get("PCR_REMARK"));
						cameraRltnEntity.setPcrPathId(map.get("PCR_PATH_ID"));
						cameraRltnEntity.setPcrUpdtTime(date);
						cameraRltnEntity.setPcrUpdtUserId(userId);

						if (StringUtils.isNotBlank(cameraRltnId)) {
							cameraRltnEntity.setId(cameraRltnId);
							updatePrisonPathCameraInfo(cameraRltnEntity);
						} else {
							cameraRltnEntity.setPcrCrteTime(date);
							cameraRltnEntity.setPcrCrteUserId(userId);
							cameraMapper.insert(cameraRltnEntity);
						}
					}
				}

				flag = true;
				obj = "修改成功";
			}

		} catch (Exception e) {
			flag = false;
			obj = "发生异常，修改失败";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@Override
	public List<Map<String, Object>> listAllForPrisonPathCamera(PrisonPathCameraRltnEntity entity) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("prisonPathCameraRltnEntity", entity);
		}
		return cameraMapper.listAll(map);
	}

	@Override
	public void deleteCameraByIds(List<String> list) {
		cameraMapper.deleteByIds(list);
	}

}
