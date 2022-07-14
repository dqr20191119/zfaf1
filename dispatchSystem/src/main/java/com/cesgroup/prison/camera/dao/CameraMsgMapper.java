package com.cesgroup.prison.camera.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.camera.entity.CameraMsgEntity;

public interface CameraMsgMapper extends BaseDao<CameraMsgEntity, String> {
			
	public void updateCameraStatus(CameraMsgEntity cameraMsgEntity);
}
