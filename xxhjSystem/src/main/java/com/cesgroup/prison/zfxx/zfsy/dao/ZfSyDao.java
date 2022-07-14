package com.cesgroup.prison.zfxx.zfsy.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfsy.entity.ZfSy;

/**
 * Description: 收押表数据访问类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
public interface ZfSyDao extends BaseDao<ZfSy, String> {
	/**
	 * 根据罪犯标识、收押类别、收押日期，查询罪犯收押信息
	 * @param cId
	 * @param cSylb
	 * @param dSyrq
	 * @return
	 */
	public List<ZfSy> findByCIdAndCSylbAndDSyrq(@Param("cId") String cId, @Param("cSylb") String cSylb, @Param("dSyrq") String dSyrq);
}
