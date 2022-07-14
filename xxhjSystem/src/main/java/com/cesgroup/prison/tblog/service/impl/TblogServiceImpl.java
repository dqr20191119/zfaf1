package com.cesgroup.prison.tblog.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.tblog.dao.TblogDao;
import com.cesgroup.prison.tblog.entity.Tblog;
import com.cesgroup.prison.tblog.service.TblogService;

@Service("tblogService")
@Transactional
public class TblogServiceImpl extends BaseDaoService<Tblog, String, TblogDao> implements TblogService {

	@Override
	public void save(Tblog tblog) {
		this.getDao().insert(tblog);
	}

}
