package com.cesgroup.prison.zfxx.dzwp.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.dzwp.entity.DzwpJfzc;

public interface DzwpJfzcDao extends BaseDao<DzwpJfzc, String> {
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
    int insertBatch(List<DzwpJfzc> list);
}