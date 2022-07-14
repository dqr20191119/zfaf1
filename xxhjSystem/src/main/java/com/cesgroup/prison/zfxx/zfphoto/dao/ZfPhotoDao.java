package com.cesgroup.prison.zfxx.zfphoto.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfphoto.entity.ZfPhoto;

/**
 * Description: 罪犯照片表数据访问类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
public interface ZfPhotoDao extends BaseDao<ZfPhoto, String> {
	/**
	 * 根据罪犯标识、照片类别，查询罪犯照片信息
	 * @param cId
	 * @param cZplb
	 * @return
	 */
	public List<ZfPhoto> findByCIdAndCStorageid(@Param("cId") String cId, @Param("cStorageid") String cStorageid);

	/**
	 * 根据罪犯基本信息同步日期，查询在此范围内的罪犯照片数据
	 * 
	 * @param dUrlTime
	 * @return
	 */
	public List<ZfPhoto> findExistsZfDqztByDUrlTime(@Param("dUrlTime") String dUrlTime);
	
	/**
	 * 根据罪犯所在监狱、所在监区、罪犯基本信息同步日期，查询在此范围内的罪犯照片数据
	 * @param cSzjy
	 * @param cSzjq
	 * @param dUrlTime
	 * @return
	 */
	public List<ZfPhoto> findExistsZfDqztByCSzjyAndCSzjqAndDUrlTime(@Param("cSzjy") String cSzjy, @Param("cSzjq") String cSzjq, @Param("dUrlTime") String dUrlTime);

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
    int insertBatch(List<ZfPhoto> list);
    
    public List<ZfPhoto> selectByJy();
    
}
