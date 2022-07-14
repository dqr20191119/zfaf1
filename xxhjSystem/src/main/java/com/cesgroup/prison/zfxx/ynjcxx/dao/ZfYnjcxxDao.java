package com.cesgroup.prison.zfxx.ynjcxx.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.ynjcxx.entity.ZfYnjcxx;

public interface ZfYnjcxxDao extends BaseDao<ZfYnjcxx, String> {
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
    int insertBatch(List<ZfYnjcxx> list);
    
}