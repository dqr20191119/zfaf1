package com.cesgroup.prison.zfxx.jyqk.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.jyqk.entity.DhJyqs;

public interface DhJyqsDao extends BaseDao<DhJyqs, String> {
    /**
     * 删除所有数据
     * @return
     */
    int deleteByJyId(String jyId);
    
    /**
     * 批量新增
     * @param list
     * @return
     */
    int insertBatch(List<DhJyqs> list);
}