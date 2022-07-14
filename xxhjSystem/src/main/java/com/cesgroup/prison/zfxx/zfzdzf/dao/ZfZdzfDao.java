package com.cesgroup.prison.zfxx.zfzdzf.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfzdzf.entity.ZfZdzf;

/**
 * Description: 罪犯重点罪犯 dao层类
 * @author monkey
 *
 * 2019年3月4日
 */
public interface ZfZdzfDao extends BaseDao<ZfZdzf,String> {

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
    int insertBatch(List<ZfZdzf> list);
    
}
