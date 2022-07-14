package com.cesgroup.prison.dagl.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.dagl.entity.Dagl;
import com.cesgroup.prison.dagl.vo.DaglInfo;

public interface DaglMapper extends BaseDao<Dagl, String> {
	/**
	 * 根据条目Id获取条目信息及对应的全文信息
	 * 
	 * @param id
	 * @return
	 */
	public Dagl getDaAnAtth(String id);

	/**
	 * 获取档案条目及全文信息（关联查询）
	 * 
	 * @param daglInfo
	 * @return
	 */
	public List<DaglInfo> getAllDaglInfo(DaglInfo daglInfo);

	/**
	 * 获取档案条目和全文信息关联查询的总记录数
	 * 
	 * @param daglInfo
	 * @return
	 */
	public int getDaglInfoByconditionCnt(DaglInfo daglInfo);
}
