
package com.cesgroup.prison.region.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.AuthSystemConst;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.region.dao.RegionMapper;
import com.cesgroup.prison.region.entity.Region;
import com.cesgroup.prison.region.service.RegionService;

/**
 * 
 * @author linhe
 * @date 2017-11-27
 *
 */
@Service
public class RegionServiceImpl extends BaseService<Region, String> implements RegionService{
	@Autowired
	private RegionMapper regionMapper;

	/**
	 * 构建树信息
	 * @param id
	 * @return
	 */
	private List<Map<String, Object>> getTreeData(String abdCusNumber,String parentId){
		List<Region> regionList = null;
		if(parentId == null || parentId.equals("null") || parentId.equals("")|| parentId.equals("0")) //初始话加载tree
			regionList = this.regionMapper.findRegionByAbdCusNumber(abdCusNumber);
		else 
			regionList = this.regionMapper.findByParendIdAndCusNumerByXml(abdCusNumber,parentId);
		List<Map<String, Object>> mapChild =  new ArrayList<Map<String, Object>>();
		for (Region region : regionList) {
			Map<String, Object> map1 = new HashMap<String, Object>();
			map1.put("name", region.getAbdAreaName());
			map1.put("id", region.getId());
			map1.put("areaId", region.getAbdAreaId());
			map1.put("nodeCusNumber", region.getAbdCusNumber());
			map1.put("open", true);
			if (region.getAbdIsLeaf()==null || region.getAbdIsLeaf().equals("0")) {// 父节点
				map1.put("isParent", true);
			} else {
				map1.put("isParent", false);
			}
			mapChild.add(map1);
		}
		
		return mapChild;
	}
	public List<Map<String, Object>> getTree(String abdCusNumber,String parentId,String nodeCusNumber) throws Exception {
		//获取所有监狱信息集合
		List<OrgEntity> jyList = AuthSystemFacade.getAllJyInfo();
		// 动态查询区域信息
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		if(abdCusNumber!=null && !abdCusNumber.equals("")) {
			/*if(AuthSystemConst.AUTH_UNIT_KEY_JSSJYGLG.equals(abdCusNumber)) {	//监狱局登陆初始化区域
				if(nodeCusNumber==null || nodeCusNumber.equals("")) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("name", "新疆监狱管理局");
					map.put("id", "");
					map.put("areaId", "");
					map.put("open", true);
					//得到所有监狱信息
					List<Map<String, Object>> prisonList= new ArrayList<Map<String, Object>>();//getTreeData(abdCusNumber,parentId);
					for(OrgEntity org: jyList) {
						Map<String, Object> prisonMap = new HashMap<String, Object>();
						prisonMap.put("name", org.getOrgName());
						prisonMap.put("id", "");
						prisonMap.put("areaId", "");
						prisonMap.put("nodeCusNumber", org.getOrgKey());
						prisonMap.put("open", false);
						prisonMap.put("isParent", true);
						prisonList.add(prisonMap);
					}
					if(prisonList!=null&&prisonList.size()>0){
						map.put("children", prisonList);
					}
					maps.add(map);
				}else {
					List<Map<String, Object>> mapChild=getTreeData(nodeCusNumber,parentId);
					if(mapChild!=null&&mapChild.size()>0){
						maps = mapChild;
					}
				}
			}else */
			if(parentId==null) {
				/*Map<String, Object> prisonMap = new HashMap<String, Object>();
				for(OrgEntity org: jyList) {
					if(abdCusNumber.equals(org.getOrgKey())) {
						prisonMap.put("name", org.getOrgName());
						prisonMap.put("id", "");
						prisonMap.put("areaId", "");
						prisonMap.put("nodeCusNumber", org.getOrgKey());
						prisonMap.put("open", true);
						prisonMap.put("isParent", true);
						List<Map<String, Object>> mapChild=getTreeData(abdCusNumber,parentId);
						if(mapChild!=null&&mapChild.size()>0){
							prisonMap.put("children", mapChild);
							//maps = mapChild;
						}
						maps.add(prisonMap);
					}
				}*/
				List<Map<String, Object>> mapChild=getTreeData(abdCusNumber,parentId);
				if(mapChild!=null&&mapChild.size()>0){
					//prisonMap.put("children", mapChild);
					maps = mapChild;
				}
			}else{
				List<Map<String, Object>> mapChild=getTreeData(abdCusNumber,parentId);
				if(mapChild!=null&&mapChild.size()>0){
					maps = mapChild;
				}
			}
		}
		return maps;
	}
	
	/**
	 * 查看区域下是否有子区域
	 * 
	 * @param regionId
	 * @return
	 */
	public List<Region> findByCusNumberAndAreaId(String cusNumber,String id) {
		return regionMapper.findByCusNumberAndAreaId(cusNumber,id);
	}
	
	/**
	 * 修改区域
	 * @param id
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public String updateRegion(Region region) throws Exception {
		Region model = selectOne(region.getId());
		//获取子区域列表
		List<Region> leafList = this.regionMapper.findByParendIdAndCusNumerByXml(String.valueOf(region.getAbdCusNumber()),model.getAbdAreaId());
		if(leafList.size()>0 && !region.getAbdAreaId().equals(model.getAbdAreaId())) {
			List<String> ids = new ArrayList<>();
			for(Region r: leafList) {
				ids.add(r.getId());
			}
			Map<String,Object> map = new HashMap<>();
			map.put("parentId", region.getAbdAreaId());
			map.put("ids", ids);
			this.regionMapper.updateParentIdByIds(map);
		}
		//当前登陆用户
		UserBean loginUser = AuthSystemFacade.getLoginUserInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		model.setAbdAreaId(region.getAbdAreaId());
		model.setAbdAreaName(region.getAbdAreaName());
		model.setAbdLevelIndc(region.getAbdLevelIndc());
		model.setAbdTypeIndc(region.getAbdTypeIndc());
		model.setAbdParentAreaId(region.getAbdParentAreaId());
		model.setAbdShortName(region.getAbdShortName());
		model.setAbdOrder(region.getAbdOrder());
		model.setAbdUpdtTime(df.parse(df.format(new Date())));
		model.setAbdUpdtUserId(loginUser.getUserId());
		model.setAbdJqId(region.getAbdJqId());
		model.setAbdJqName(region.getAbdJqName());
		regionMapper.update(model);
		return "success";
	}
	
	/**
	 * 添加区域
	 * @param id
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public void insertRegion(Region region) throws Exception {
		//当前登陆用户
		UserBean loginUser = AuthSystemFacade.getLoginUserInfo();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		region.setAbdCrteTime(df.parse(df.format(new Date())));
		region.setAbdUpdtTime(df.parse(df.format(new Date())));
		region.setAbdCrteUserId(loginUser.getUserId());
		region.setAbdUpdtUserId(loginUser.getUserId());
		region.setAbdCusNumber(region.getAbdCusNumber());
		//判断是否有父节点，如果有父节点编号则判断父节点的isLeaf是否为1，若为1则修改为0
		if(region.getAbdParentAreaId()!=null && !region.getAbdParentAreaId().equals("null") && !region.getAbdParentAreaId().equals("")) {
			List<Region> list = regionMapper.findByCusNumberAndAreaId(region.getAbdCusNumber(),region.getAbdParentAreaId());
			Region updRegion = list.get(0);
			if(updRegion.getAbdIsLeaf() != null && updRegion.getAbdIsLeaf().equals("1")) {
				updRegion.setAbdIsLeaf("0");
				try {
					regionMapper.updateByXml(updRegion);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		region.setAbdIsLeaf("1");
		this.regionMapper.insert(region);
	}
	/**
	 * 删除区域
	 */
	@Override
	@Transactional
	public void deleteRegion(String cusNumber,String areaId) {
		//判断父级区域下是否还有当前区域以外的子区域，若无则修改父级区域isLeaf为1
		Region region = regionMapper.findByCusNumberAndAreaId(cusNumber, areaId).get(0);
		List<Region> bortherRegions = this.regionMapper.findByParendIdAndCusNumerByXml(cusNumber, region.getAbdParentAreaId());
		if(bortherRegions.size()<2 && region.getAbdParentAreaId()!=null && !region.getAbdParentAreaId().equals("")) {
			Region parentRegion = regionMapper.findByCusNumberAndAreaId(region.getAbdCusNumber(),region.getAbdParentAreaId()).get(0);
			parentRegion.setAbdIsLeaf("1");
			regionMapper.updateByXml(parentRegion);
		}
		List<Region> list = this.regionMapper.findByCusNumberAndAreaId(cusNumber,areaId);
		List<Region> chidList = this.regionMapper.findByParendIdAndCusNumerByXml(cusNumber, areaId);
		List<String> ids = new ArrayList<>();
		for(Region reg :list) {
			ids.add(reg.getId());
		}
		for(Region reg:chidList) {
			ids.add(reg.getId());
		}
		regionMapper.batchDelete(ids);
	}
	


	

}
