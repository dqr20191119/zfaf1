package com.cesgroup.prison.zfxx.zfjzzy.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfjzzy.entity.ZfJzzy;

/**
 * Description: 罪犯 狱外就医 dao层类
 * @author monkey
 *
 * 2019年3月4日
 */
public interface ZfJzzyDao extends BaseDao<ZfJzzy, String> {

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
    int insertBatch(List<ZfJzzy> list);
    
}
