
package com.cesgroup.prison.view.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.prison.view.entity.View;

/**
 * 视角业务逻辑接口
 * @author linhe
 * @date 2017-12-11
 *
 */
@Service
public interface ViewService {
	
	/**
	 * 删除视角
	 * @param id
	 */
	public void deleteView(String id);
	/**
	 * 根据视觉类型排序查询
	 * @param type
	 * @return
	 */
	public List<Map<String, Object>> findByCusNumberAndType(String cusNumber,String type);
	/**
	 * 保存视角
	 * @param view
	 * @return
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public int saveView(View view) throws ParseException, Exception;
	
	/**
     * 根据监狱编号、区域编号、区域名称查询分页查询
     * @param map
     * @return
     */
	/**
	 * 根据监狱编号、区域编号、区域名称查询分页查询
	 * @param view
	 * @param request
	 * @return
	 */
    public Page<Map<String,String>> findByPage(Map<String,String> map,PageRequest request);
    /**
     * 批量删除
     * @param ids	多个id以逗号分隔
     * @return
     */
    public int batchDelete(String ids);
    /**
     * 根据监狱编号查询区域视角定位
     * @param cusNumber
     * @return
     * @throws Exception 
     */
    public List<Map<String, Object>> findRegionView(String cusNumber, String parentAreaId, String confine) throws Exception; 
    /**
     * 根据监狱编号、区域编号、视角权限查询视角信息
     * 默认返回默认视角，若无默认视角则返回非默认视角
     * @param cusNumbeer
     * @param areaId
     * @param confine
     * @return
     */
    public View findByCusNumberAndAreaIdAndViewType(String cusNumber, String areaId, String confine);

	public List<Map<String, Object>> findRegionView_2D(String cusNumber) throws Exception;

}
