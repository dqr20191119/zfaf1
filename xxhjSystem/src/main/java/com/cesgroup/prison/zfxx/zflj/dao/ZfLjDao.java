package com.cesgroup.prison.zfxx.zflj.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zflj.entity.ZfLj;

/**
 * Description: 离监表数据访问类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
public interface ZfLjDao extends BaseDao<ZfLj, String> {

	/**
	 * 根据罪犯标识、离监类别、离监日期，查询罪犯离监信息
	 * @param cId
	 * @param cLjlb
	 * @param dLjrq
	 * @return
	 */
	public List<ZfLj> findByCIdAndCLjlbAndDLjrq(@Param("cId") String cId, @Param("cLjlb") String cLjlb, @Param("dLjrq") String dLjrq);
}
