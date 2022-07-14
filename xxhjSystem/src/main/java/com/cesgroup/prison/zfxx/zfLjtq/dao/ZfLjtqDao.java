package com.cesgroup.prison.zfxx.zfLjtq.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfLjtq.entity.ZfLjtq;
/**
 * Description: 罪犯 离监探亲 dao层类
 * @author monkey
 *
 * 2019年3月5日
 */
public interface ZfLjtqDao extends BaseDao<ZfLjtq, String> {

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
    int insertBatch(List<ZfLjtq> list);
    
}
