package com.cesgroup.prison.djwg.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.djwg.entity.DjwgzzwhEntity;

public interface DjwgzzwhMapper  extends BaseDao<DjwgzzwhEntity, String> {

	
	public DjwgzzwhEntity getById(String id);

	public Page<DjwgzzwhEntity> findList(DjwgzzwhEntity djwgzzwhEntity, PageRequest pageRequest);

	public List<DjwgzzwhEntity> findAllList(DjwgzzwhEntity djwgzzwhEntity); 
	
	public void updateStatusByIds(List<String> idList);
	
	public DjwgzzwhEntity getByCode(String jyId ,String zzCode);
	
	public List<DjwgzzwhEntity> findZzwh(DjwgzzwhEntity djwgzzwhEntity); 
}
