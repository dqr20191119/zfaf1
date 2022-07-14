package com.cesgroup.prison.region.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.region.entity.Region;

/**
 * 区域mapper
 * @author linhe
 * @date 2017-11-27
 *
 */
public interface RegionMapper extends BaseDao<Region,String> {

	/**
	 * 根据监狱编号查询区域信息
	 * @param cusNumber
	 * @return
	 */
	public List<Region> findByAbdCusNumber(String cusNumber);
	/**
	 * 根据监狱编号和区域id查询区域信息
	 * @param abdAreaId
	 * @return
	 */
	public List<Region> findByCusNumberAndAreaId(String cusNumber,String abdAreaId);
	/**
	 * 查看区域下是否有子区域
	 * 基于sql方式
	 * @param parentId,abdCusNumber
	 * @return
	 */
	@Select("select count(ABD_AREA_ID) from cds_area_base_dtls where ABD_PARENT_AREA_ID =#{abdParentAreaId} and ABD_CUS_NUMBER=#{abdCusNumber}")
	public int findRegionCountByAbdParentAreaIdAndAbdCusNumber(@Param("abdParentAreaId")String abdParentAreaId,@Param("abdCusNumber")String abdCusNumber);
	/**
	 * 查询子区域信息
	 * 根据监狱编号和父区域id查询
	 * @param abdCusNumber,abdParentAreaId
	 * @return
	 */
	public List<Region> findByAbdCusNumberAndAbdParentAreaId(String abdCusNumber,String abdParentAreaId);
	/**
	 * 查询子区域信息
	 * 根据监狱编号查询所有
	 * @param abdCusNumber,abdParentAreaId
	 * @return
	 */
	@Select("select * from cds_area_base_dtls where (ABD_PARENT_AREA_ID is null or ABD_PARENT_AREA_ID='') and ABD_CUS_NUMBER=#{abdCusNumber} order by ABD_ORDER asc")
	public List<Region> findRegionByAbdCusNumber(String abdCusNumber);
	
	/**
	 * 查询子区域信息
	 * 基于xml方式
	 * @param parentId
	 * @return
	 */
	public List<Region>findSonByXml(String parentId);
	
	/**
	 * 查询子区域信息
	 * 基于sql方式
	 * @param cusNumber
	 * @return
	 */
	@Select("select * from cds_area_base_dtls where 1=1 order by abd_order")
	public List<Region> findSonBySql(String cusNumber);
	
	/**
	 * 根据监狱编号和
	 * @param cusNumber
	 * @param type
	 * @return
	 */
	public List<Region> findByAbdCusNumberAndAbdTypeIndc(String cusNumber,String type);
	/**
	 * 根据id批量修改父级区域编号
	 * @param map
	 * @return
	 */
	public int updateParentIdByIds(Map<String, Object> map);
	/**
	 * 修改
	 */
	public void updateByXml(Region region);
	/**
	 * 根据监狱编号和父级区域编号 排序查询
	 * @param cusNumber
	 * @param parentId
	 * @return
	 */
	public List<Region> findByParendIdAndCusNumerByXml(String cusNumber, String parentId);
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public int batchDelete(List<String> ids);
	/**
	 * 根据监狱编号和区域属性排序查询
	 * @param cusNumber
	 * @param level
	 * @return
	 */
	public List<Region> findByCusNumberAndLevel(String cusNumber,String level);
	
	
}


