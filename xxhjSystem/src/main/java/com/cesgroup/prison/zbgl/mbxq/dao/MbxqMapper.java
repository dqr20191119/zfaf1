package com.cesgroup.prison.zbgl.mbxq.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.mbxq.entity.MbxqEntity;

public interface MbxqMapper extends BaseDao<MbxqEntity, String> {

	public List<MbxqEntity> findAllList(MbxqEntity MbxqEntity);
	public void deleteByConditions(@Param("mojModeId")String mojModeId);

}
