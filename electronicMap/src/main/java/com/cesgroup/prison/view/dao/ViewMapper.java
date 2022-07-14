package com.cesgroup.prison.view.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.regionDepart.entity.RegionDepart;
import com.cesgroup.prison.view.entity.View;
/**
 * 视角管理mapper
 * @author linhe 2017-12-11
 *
 */
public interface ViewMapper  extends BaseDao<View,String> {
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int deleteById(String id);
	/**
	 * 添加
	 * @param record
	 * @return
	 */
    public void insertView(View view);
    /**
     * 根据id查询
     * @param id
     * @return
     */
    public View findtById(String id);
    /**
     * 根据id修改
     * @param record
     * @return
     */
    public void updateById(View view);

    /**
     * 根据监狱编号和子节点状态查询查询视角
     * @param cusNumber	监狱编号
     * @param isLeaf	子节点状态（1：子节点，0：父节点）
     * @return
     */
    public List<View> findByVfuCusNumberAndVfuIsLeaf(String cusNumber,String isLeaf);
    /**
     * 根据监狱编号和父节点编号查询视角信息
     * @param cusNumber 监狱编号
     * @param parentId	父节点编号（一级节点父节点编号为-1）
     * @return
     */
    public List<View> findByCusNumberParentId(String cusNumber,String parentId);
    /**
     * 根据视觉类型排序查询
     * @param type
     * @return
     */
    public List<View> findByCusNumberAndType(String cusNumber,String type);
    /**
     * 根据父级视角id查询子视角信息集合
     * @param id
     * @return
     */
    public List<View> findByParentId(String id);
    /**
     * 根据监狱编号、区域编号、默认视角查询（均为可选）
     * @param cusNumber
     * @param areaId
     * @param def
     * @return
     */
    public List<View> findByAreaIdAndDef(String cusNumber, String areaId, String def,String confine);
    /**
     * 根据id批量修改为非默认视角
     * @param ids
     * @return
     */
    public int updateToUndefByIds(List<String> ids);
    /**
     * 根据监狱编号、区域编号、区域名称查询分页查询
     * @param map
     * @return
     */
    public Page<Map<String, String>> findByPage(Map<String, String> map, Pageable pageable);
    /**
     * 批量删除
     * @param ids
     * @return
     */
    public int batchDelete(List<String> ids);
    /**
     * 根据监狱编号、父级区域编号、视角类型查询默认视角信息
     * @param cusNumber
     * @param parentAreaId
     * @param confine
     * @return
     */
    public List<Map<String, Object>> findByCusNumberAndParentAreaIdAndViewType(String cusNumber, String parentAreaId, String confine);
    /**
     * 根据监狱编号、区域编号、视角权限查询视角信息
     * @param cusNumbeer
     * @param areaId
     * @param confine
     * @return
     */
    public List<View> findByCusNumberAndAreaIdAndViewType(String cusNumbeer, String areaId, String confine);
    /**
     * 根据监狱编号，区域编号集合和视角权限查询区域视角信息
     * @param map
     * @return
     */
    public List<Map<String, Object>> findByCusNumberAndAreaId(Map<String, Object> map);

    public List<Map<String, Object>> findChildListByCusNumber(String cusNumber);

}