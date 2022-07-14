package com.cesgroup.prison.zbgl.pbtbhz.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.pbtbhz.entity.JyzbBaseEntity;

/**
* @author lihong
* @date 创建时间：2020年5月25日 下午4:38:19
* 类说明:
*/
public interface JyzbBaseMapper extends BaseDao<JyzbBaseEntity, String> {
	public Page<JyzbBaseEntity> findList(JyzbBaseEntity jyzbBaseEntity, PageRequest pageRequest);

	public void updateById(JyzbBaseEntity jyzbBaseEntity);

	public void updateZtByZbYf(JyzbBaseEntity jyzb);
}
