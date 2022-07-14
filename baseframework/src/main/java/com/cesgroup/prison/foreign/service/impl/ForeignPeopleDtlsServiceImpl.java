package com.cesgroup.prison.foreign.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.foreign.dao.ForeignPeopleDtlsMapper;
import com.cesgroup.prison.foreign.entity.ForeignPeopleDtls;
import com.cesgroup.prison.foreign.service.ForeignPeopleDtlsService;

@Service
@Transactional(readOnly=false)
public class ForeignPeopleDtlsServiceImpl extends BaseDaoService<ForeignPeopleDtls, String, ForeignPeopleDtlsMapper> implements ForeignPeopleDtlsService {

	@Override
	public int deleteByPrimaryKey(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertSelectiveB(ForeignPeopleDtls record, String foreignPeopelPicIds) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ForeignPeopleDtls selectByPrimaryKey(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateByPrimaryKeySelective(ForeignPeopleDtls record, String foreignPeopelPicIds) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Page<Map<String, Object>> pageFind(Map<String, Object> map, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> selectBaseInfoByCardId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AffixEntity> findPic(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
