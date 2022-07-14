
package com.cesgroup.prison.region.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.region.dao.RegionMapper;
import com.cesgroup.prison.region.entity.Region;

/**
 * 
 * @author linhe
 * @date 2017-11-27
 *
 */
@Service
public interface RegionService {

	
	/**
	 * 构建树信息
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	public List<Map<String, Object>> getTree(String abdCusNumber,String parentId,String nodeCusNumber) throws Exception;
	
	/**
	 * 查看区域下是否有子区域
	 * 
	 * @param regionId
	 * @return
	 */
	public List<Region> findByCusNumberAndAreaId(String cusNumber,String id);
	/**
	 * 删除区域
	 * @param id
	 */
	public void deleteRegion(String cusNumber,String id);
	/**
	 * 添加区域
	 * @param region
	 * @throws ParseException 
	 * @throws Exception 
	 */
	public void insertRegion(Region region) throws ParseException, Exception;
	/**
	 * 修改区域
	 * @param region
	 * @return
	 * @throws ParseException
	 * @throws Exception 
	 */
	public String updateRegion(Region region) throws ParseException, Exception;
	
}
