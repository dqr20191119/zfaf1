package com.cesgroup.prison.zfxx.zfjyzf.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfjyzf.entity.ZfJyzf;

/**
 * Description: 罪犯 狱外寄押 dao层类
 * @author monkey
 *
 * 2019年3月5日
 */
public interface ZfJyzfDao extends BaseDao<ZfJyzf, String> {

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
    int insertBatch(List<ZfJyzf> list);
    
}
