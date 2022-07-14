package com.cesgroup.prison.zbgl.pbtbhz.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.pbtbhz.entity.JyzbDetailEntity;

/**
* @author lihong
* @date 创建时间：2020年5月25日 下午4:38:54
* 类说明:
*/
public interface JyzbDetailMapper extends BaseDao<JyzbDetailEntity, String> {
	public void updateById(JyzbDetailEntity jyzbDetailEntity);
	void deleteByYwId(@Param("ywId") String ywId);
	Page<JyzbDetailEntity> getByDutyDate(JyzbDetailEntity jyzbDetailEntity,PageRequest pageRequest);
	public List<JyzbDetailEntity> getByDutyDateOrZbyf(JyzbDetailEntity jyzbDetail);
}
