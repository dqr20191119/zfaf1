package com.cesgroup.prison.zbgl.pbgz.service.impl;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.zbgl.pbgz.dao.PbgzMapper;
import com.cesgroup.prison.zbgl.pbgz.entity.PbgzEntity;
import com.cesgroup.prison.zbgl.pbgz.service.PbgzService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("pbgzService")
public class PbgzServiceImpl extends BaseDaoService<PbgzEntity, String, PbgzMapper> implements PbgzService {


    @Override
    public PbgzEntity getById(String id) {
        return null;
    }

    @Override
    public Page<PbgzEntity> findList(PbgzEntity pbgzEntity, PageRequest pageRequest) {
        return null;
    }

    @Override
    public List<PbgzEntity> findAllList(PbgzEntity pbgzEntity) {
        return null;
    }

    @Override
    public void saveOrUpdate(PbgzEntity pbgzEntity) {

    }

    @Override
    public void deleteById(String id) {

    }
}
