package com.cesgroup.prison.zfxx.zfjbxx.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfjbxx.entity.ZfJbxx;

/**
 * Description: 罪犯基本信息表数据访问类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
public interface ZfJbxxDao extends BaseDao<ZfJbxx, String> {
	/**
	 * Description: 已知罪犯标识，查询罪犯信息
	 * @param cId
	 * @return
	 */
	public List<ZfJbxx> findByCId(@Param("cId") String cId);
	
	/**
     * 备份所有数据
     * @return
     */
    int insertHisBySource();
    
    /**
     * 删除所有数据
     * @return
     */
    int deleteAll();
    
    /**
     * 批量新增
     * @param list
     * @return
     */
    int insertBatch(List<ZfJbxx> list);
    
}
