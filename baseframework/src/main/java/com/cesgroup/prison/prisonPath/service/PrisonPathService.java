package com.cesgroup.prison.prisonPath.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.prison.prisonPath.entity.PrisonPathCameraRltnEntity;
import com.cesgroup.prison.prisonPath.entity.PrisonPathEntity;

public interface PrisonPathService extends IBaseCRUDService<PrisonPathEntity, String> {

	public Page<Map<String, Object>> listAll(PrisonPathEntity entity, Pageable pageable);

	public List<Map<String, Object>> findPathByAreaIdForCombobox(Map<String, Object> paramMap);

	public AjaxMessage addInfo(Object pathData);

	public AjaxMessage updateInfo(Object pathData);

	public void deleteByIds(List<String> list);

	public void deleteCameraByIds(List<String> list);

	public PrisonPathEntity findById(String id);

	public List<Map<String, Object>> listAllForPrisonPathCamera(PrisonPathCameraRltnEntity entity);

	public List<Map<String, Object>> listAllForSx(String cusNumber, String areaId, String pathId, String cameraName);

}
