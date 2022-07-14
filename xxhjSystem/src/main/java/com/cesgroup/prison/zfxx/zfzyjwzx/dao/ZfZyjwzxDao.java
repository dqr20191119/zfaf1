package com.cesgroup.prison.zfxx.zfzyjwzx.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfzyjwzx.entity.ZfZyjwzx;

public interface ZfZyjwzxDao extends BaseDao<ZfZyjwzx, String> {

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
    int insertBatch(List<ZfZyjwzx> list);
    
}
