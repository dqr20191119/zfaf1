package com.cesgroup.prison.zfxx.zfdqzt.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfdqzt.entity.ZfDqzt;

/**
 * Description: 罪犯当前状态表数据访问类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
public interface ZfDqztDao extends BaseDao<ZfDqzt, String> {

	/**
	 * 根据罪犯标识，查询罪犯当前状态
	 * @param cId
	 * @return
	 */
	public List<ZfDqzt> findByCId(@Param("cId") String cId);
}
