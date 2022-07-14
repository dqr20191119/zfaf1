package com.cesgroup.prison.zfxx.dzwp.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.dzwp.entity.DzwpYsjg;

public interface DzwpYsjgDao extends BaseDao<DzwpYsjg, String> {
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
    int insertBatch(List<DzwpYsjg> list);
}