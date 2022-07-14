package com.cesgroup.prison.common.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.common.entity.AffixEntity;

public interface AffixMapper extends BaseDao<AffixEntity, String> {

	public AffixEntity getById(String id);
	
	public List<AffixEntity> findAllList(AffixEntity affixEntity);

	public void updateYwId(@Param("ywId") String ywId, @Param("idList") List<String> idList);

	public void deleteByYwIdAndFileType(@Param("ywId") String ywId, @Param("fileTypeList") List<String> fileTypeList);
	
	public void deleteByYwId(@Param("ywId") String ywId);
}
