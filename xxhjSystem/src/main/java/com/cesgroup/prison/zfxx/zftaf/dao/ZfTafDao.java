package com.cesgroup.prison.zfxx.zftaf.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zftaf.entity.ZfTaf;

/**
 * Description: 罪犯同案犯表数据访问类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
public interface ZfTafDao extends BaseDao<ZfTaf, String> {
	/**
	 * 根据罪犯标识、同案犯标识，查询罪犯同案犯信息
	 * @param cId
	 * @param cZfId
	 * @return
	 */
	public List<ZfTaf> findByCIdAndCZfId(@Param("cId") String cId, @Param("cZfId") String cZfId);
	
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
    int insertBatch(List<ZfTaf> list);
    
}
