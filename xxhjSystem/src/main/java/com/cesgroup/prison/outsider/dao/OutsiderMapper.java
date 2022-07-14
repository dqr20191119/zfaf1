package com.cesgroup.prison.outsider.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.outsider.entity.Outsider;

public interface OutsiderMapper  extends BaseDao<Outsider, String> {
	
	/**
	 * Description: 查询外来人员历史数据，避免插入重复数据
	 * @param 监狱编号
	 * @param 身份证号
	 * @param 登记时间
	 */
	public List<Outsider> findHisRecord(@Param("cusNumber") String cusNumber,@Param("sfzh") String sfzh,@Param("djsj") String djsj);
}
