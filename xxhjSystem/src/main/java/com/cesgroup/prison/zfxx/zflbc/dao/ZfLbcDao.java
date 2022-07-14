package com.cesgroup.prison.zfxx.zflbc.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zflbc.entity.ZfLbc;
/**
 * Description: 罪犯老病残 dao层类
 * @author monkey
 *
 * 2019年2月28日
 */

public interface ZfLbcDao  extends BaseDao<ZfLbc,String> {
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
    int insertBatch(List<ZfLbc> list);
    
}
