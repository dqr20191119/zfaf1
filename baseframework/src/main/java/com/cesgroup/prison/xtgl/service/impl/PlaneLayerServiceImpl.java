package com.cesgroup.prison.xtgl.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.common.dao.AffixMapper;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.xtgl.dao.PlaneLayerMapper;
import com.cesgroup.prison.xtgl.entity.PlaneLayer;
import com.cesgroup.prison.xtgl.service.PlaneLayerService;


@Service
public class PlaneLayerServiceImpl extends BaseService<PlaneLayer,String>  implements PlaneLayerService{
	
	@Autowired
	private PlaneLayerMapper planeLayerMapper;

	@Resource
	private AffixMapper affixMapper;
	
	@Override
	@Transactional
	public int updatePart(PlaneLayer planeLayer) {
		return planeLayerMapper.updatePart(planeLayer);
	}

	@Override
	public List<PlaneLayer> findByPliAreaId(String pliAreaId) {
		return planeLayerMapper.findByPliAreaId(pliAreaId);
	}

	@Override
	@Transactional
	public void saveFile(String id, String files) {
		// 关联附件
		List<String> idList = new ArrayList<String>();

		if (StringUtils.isNotBlank(files)) {
			//一个区域关联一个图层
			idList.add(files.split(",")[0]);
		}
		affixMapper.updateYwId(id, idList);
	}

	@Override
	public List<AffixEntity> findFile(String id) {
		AffixEntity affixEntity = new AffixEntity();
		affixEntity.setYwId(id);
		List<AffixEntity> affixList = affixMapper.findAllList(affixEntity);
		return affixList;
	}

	@Override
	@Transactional
	public void deleteFile(String ywId) {
		affixMapper.deleteByYwId(ywId);
	}

}
