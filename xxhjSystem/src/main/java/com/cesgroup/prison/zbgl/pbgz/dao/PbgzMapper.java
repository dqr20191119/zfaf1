package com.cesgroup.prison.zbgl.pbgz.dao;


import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.pbgz.entity.PbgzEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PbgzMapper extends BaseDao<PbgzEntity, String> {

    public PbgzEntity getById(String id);

    public Page<PbgzEntity> findList(PbgzEntity bcglEntity, PageRequest pageRequest);

    public List<PbgzEntity> findAllList(PbgzEntity bcglEntity);

    public void updateStatusByIds(List<String> idList);
}
