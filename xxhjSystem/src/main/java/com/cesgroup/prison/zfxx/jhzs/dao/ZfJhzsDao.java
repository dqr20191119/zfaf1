package com.cesgroup.prison.zfxx.jhzs.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.jhzs.entity.ZfJhzs;

/**
 * Description: 罪犯 解回再审 dao层类
 * @author monkey
 *
 * 2019年3月5日
 */
public interface ZfJhzsDao extends BaseDao<ZfJhzs, String> {

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
    int insertBatch(List<ZfJhzs> list);
    
}
