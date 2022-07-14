package com.cesgroup.prison.zbgl.hbsq.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zbgl.hbsq.dto.ZbrxxDto;
import com.cesgroup.prison.zbgl.hbsq.entity.HbsqEntity;
import com.cesgroup.prison.zbgl.zbbp.entity.ZbbpEntity;

import java.util.List;
import java.util.Map;

public interface HbsqMapper extends BaseDao<HbsqEntity, String> {

	public Page<HbsqEntity> findList(HbsqEntity hbsqEntity, PageRequest pageRequest);

	public void updateById(HbsqEntity hbsqEntity);
	/**
	 * 根据id更新值班人信息
	 * @param zbbpEntity
	 */
	void updateZbrById(ZbbpEntity zbbpEntity);

	public Integer checkZbrIsZbbp(ZbrxxDto zbrxxDto);

    List<Map<String,Object>> checkIsZhz(@Param("dutyDate") String dutyDate, @Param("cusNumber") String cusNumber,@Param("zbrName") String  zbrName);
}
