package com.cesgroup.prison.zfxx.zftg.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zftg.entity.ZfTg;

/**
 * Description: 罪犯 特管等级dao层类
 * @author monkey
 *
 * 2019年3月3日
 */
public interface ZfTgDao extends BaseDao<ZfTg, String> {

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
    int insertBatch(List<ZfTg> list);
}
