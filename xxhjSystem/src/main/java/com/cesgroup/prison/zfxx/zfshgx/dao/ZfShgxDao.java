package com.cesgroup.prison.zfxx.zfshgx.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfshgx.entity.ZfShgx;

public interface ZfShgxDao extends BaseDao<ZfShgx, String> {
    
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
    int insertBatch(List<ZfShgx> list);
    
}
