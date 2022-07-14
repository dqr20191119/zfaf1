package com.cesgroup.prison.wwjg.qzdjwh.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.wwjg.qzdjwh.entity.QzdjwhEntity;

public interface QzdjwhMapper  extends BaseDao<QzdjwhEntity, String> {

	
	public QzdjwhEntity getById(String id);

	public Page<QzdjwhEntity> findList(QzdjwhEntity qzdjwhEntity, PageRequest pageRequest);

	public List<QzdjwhEntity> findAllList(QzdjwhEntity qzdjwhEntity); 
	
	public void updateStatusByIds(List<String> idList);
	
	public QzdjwhEntity getByCode(String code);
}
