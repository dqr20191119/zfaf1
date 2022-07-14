
package com.cesgroup.prison.view.service.impl;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.service.CommonService;
import com.cesgroup.prison.regionDepart.dao.RegionDepartMapper;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.view.dao.ViewMapper;
import com.cesgroup.prison.view.entity.View;
import com.cesgroup.prison.view.service.ViewService;
import com.cesgroup.prison.viewPeople.dao.ViewPeopleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 视角业务逻辑接口实现类
 * @author linhe
 * @date 2017-12-11
 *
 */
@Service
public class ViewServiceImpl extends BaseService<View, String> implements ViewService{
	@Autowired
	private ViewMapper viewMapper;
	@Autowired
	private RegionDepartMapper regionDepartMapper;
	@Autowired
    private ViewPeopleMapper viewPeopleMapper;
	@Resource
	private CommonService commonService;
	/**
	 * 删除
	 */
	@Override
	public void deleteView(String id) {
		// TODO Auto-generated method stub
	}
	/**
	 * 根据视觉类型查询
	 */
	@Override
	public List<Map<String, Object>> findByCusNumberAndType(String cusNumber,String type) {
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		List<View> viewList = this.viewMapper.findByCusNumberAndType(cusNumber,type);
		for(View view:viewList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", view.getId());
			map.put("text", view.getVfuViewName());
			map.put("areaId", view.getVfuAreaId());
			maps.add(map);
		}
		return maps;
	}
	/**
	 * 保存视角
	 * @throws Exception 
	 */
	@Override
	@Transactional
	public int saveView(View view) throws Exception {
		UserBean loginUser = AuthSystemFacade.getLoginUserInfo();
		int result = 0;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		//如果设置了默认是视角且该区域已有默认视角，则取消已有默认视角
		if(view.getVfuDefIndc()==1) {
			if(true) {
				List<View> views = this.viewMapper.findByAreaIdAndDef(view.getVfuCusNumber(), view.getVfuAreaId(), "1",view.getVfuConfine()+"");
				List<String> ids = new ArrayList<>();
				for(View defView: views) {
					ids.add(defView.getId());
				}
				//修改为非默认视角
				if(ids!=null && ids.size()>0) {
					this.viewMapper.updateToUndefByIds(ids);
				}
			}
		}
		if(view.getId()!=null && !view.getId().equals("")) {
			//修改
			view.setVfuUpdtTime(df.parse(df.format(new Date())));//修改日期
			view.setVfuUpdtUserId(loginUser.getUserId());//修改人
			this.viewMapper.updateById(view);
		}else {
			//添加
			view.setId(CommonUtil.createUUID().replace("-", ""));
			view.setVfuCrteTime(df.parse(df.format(new Date())));//创建日期
			view.setVfuCrteUserId(loginUser.getUserId());//创建人
			try {
				this.viewMapper.insertView(view);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	/**
	 * 分页查询
	 */
	@Override
	public Page<Map<String,String>> findByPage(Map<String,String> map,PageRequest request) {
		//Map<String, Object> map = (Map<String, Object>) DataUtils.objectToMap(view);
		//map.put("abdAreaName", "");
		return this.viewMapper.findByPage(map,request);
	}
	/**
	 * 批量删除
	 */
	@Override
	@Transactional
	public int batchDelete(String ids) {
		List<String> list = new ArrayList<>();
		for(String str: ids.replace("[", "").replace("]", "").replace("\"", "").split(",")) {
			list.add(str);
		}
		return this.viewMapper.batchDelete(list);
	}
	/**
	 * 根据监狱编号获取区域视角定位信息
	 * @throws Exception 
	 */
	@Override
	public List<Map<String, Object>> findRegionView(String cusNumber, String parentAreaId, String confine) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		UserBean loginUser = AuthSystemFacade.getLoginUserInfo();
		/*loginUser.setUserLevel(EUserLevel.AREA);
		loginUser.setDprtmntId("320304");*/
		if(cusNumber==null || cusNumber.equals("")) {
			cusNumber = loginUser.getCusNumber();
		}
		if(loginUser.getUserLevel()!=EUserLevel.AREA) {//省局或监狱
			list = this.viewMapper.findByCusNumberAndParentAreaIdAndViewType(cusNumber, parentAreaId, confine);
			List<Map<String, Object>> allChildList = this.viewMapper.findChildListByCusNumber(cusNumber);

			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = list.get(i);
				List<Map<String, Object>> childenList = new ArrayList<>();
				for (int x = 0; x < allChildList.size(); x++) {
					Map<String, Object> childMap = allChildList.get(x);
					if(!cusNumber.equals(String.valueOf(map.get("VFU_AREA_ID"))) && String.valueOf(childMap.get("VFU_AREA_ID")).contains(String.valueOf(map.get("VFU_AREA_ID")))){
						childenList.add(childMap);
//                        allChildList.remove(x);
					}
				}
                if (childenList.size() > 0) {
                    map.put("childens", childenList);
                    list.set(i, map);
                }
			}

/*			for(int i=0;i<list.size();i++) {
				Map<String, Object> map = list.get(i);
				if(String.valueOf(map.get("ABD_IS_LEAF")).equals("0") && !String.valueOf(map.get("ABD_PARENT_AREA_ID")).equals("") && !String.valueOf(map.get("ABD_PARENT_AREA_ID")).equals("null")) {//判断是否是父节点并且不是根节点
					String pAreaId = String.valueOf(map.get("VFU_AREA_ID"));
					List<Map<String, Object>> childenList = this.viewMapper.findByCusNumberAndParentAreaIdAndViewType(cusNumber, pAreaId, confine);
					map.put("childens", childenList);
					list.set(i, map);
				}
			}*/
		}else if(loginUser.getUserLevel()==EUserLevel.AREA) {//监区
			//根据当前登录用户监狱编号和用户组织编获取区域信息
//			List<RegionDepart> areaIdList = this.regionDepartMapper.findByCusNumberAndDprtmntId(cusNumber, loginUser.getDprtmntCode());
			List<Map<String, Object>> areaIdList = this.regionDepartMapper.findByCusNumberAndDprtmntId(cusNumber, loginUser.getDprtmntCode());
            List<Map<String,Object>> viewPeopleAreaIdList = this.viewPeopleMapper.findAreaIdByPoliceID(cusNumber, loginUser.getUserId());
			if(areaIdList.size()<1) {
				return new ArrayList<Map<String, Object>>();
			}else{
			    if(viewPeopleAreaIdList.size()>0){
                    for(Map<String, Object> map : viewPeopleAreaIdList){
                        areaIdList.add(map);
                    }
                }
            }
			//根据区域查询视角
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cusNumber", cusNumber);
			map.put("areaIdList", areaIdList);
			//获取当前用户部门所有区域视角信息
			List<Map<String, Object>> viewList = this.viewMapper.findByCusNumberAndAreaId(map);
			//重复的子区域视角集合
			List<Map<String, Object>> allChilden = new ArrayList<Map<String, Object>>();
			//区分视角级别
			for(Map<String, Object> viewMap1 :viewList) {
				//子区域集合
				List<Map<String, Object>> childenList = new ArrayList<Map<String, Object>>();
				for(Map<String, Object> viewMap2 :viewList) {
					if(viewMap1.get("VFU_AREA_ID").equals(viewMap2.get("ABD_PARENT_AREA_ID"))) {
						childenList.add(viewMap2);
						//添加到all childen中
						allChilden.add(viewMap2);
					}
				}
				if(childenList.size()>0) {
					viewMap1.put("childens", childenList);
				}
				list.add(viewMap1);
			}
			//遍历all childen 是否在list中，若在则删除
			for(Map<String, Object> childen: allChilden) {
				list.remove(childen);
			}
		}
		return list;
	}
	/**
	 * 根据监狱编号、区域编号、视角类型查询区域的视角信息
	 */
	@Override
	public View findByCusNumberAndAreaIdAndViewType(String cusNumbeer, String areaId, String confine) {
		List<View> viewList = this.viewMapper.findByCusNumberAndAreaIdAndViewType(cusNumbeer, areaId, confine);
		if(viewList!=null && viewList.size()>0) {
			return viewList.get(0);
		}else {
			return null;
		}
	}


	/**
	 * 根据监狱编号获取区域视角定位信息
	 * @throws Exception
	 */
	@Override
	public List<Map<String, Object>> findRegionView_2D(String cusNumber) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		UserBean loginUser = AuthSystemFacade.getLoginUserInfo();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		if(cusNumber==null || cusNumber.equals("")) {
			cusNumber = loginUser.getCusNumber();
		}
		paramMap.put("cusNumber", cusNumber);
		if(loginUser.getUserLevel()!=EUserLevel.AREA) {//省局或监狱
			list = commonService.findSyncAreaForCombotree(paramMap);

		}else if(loginUser.getUserLevel()==EUserLevel.AREA) {//监区
			//根据当前登录用户监狱编号和用户组织编获取区域信息
			List<Map<String, Object>> areaIdList = this.regionDepartMapper.findByCusNumberAndDprtmntId(cusNumber, loginUser.getDprtmntCode());
			List<Map<String,Object>> viewPeopleAreaIdList = this.viewPeopleMapper.findAreaIdByPoliceID(cusNumber, loginUser.getUserId());
			if(areaIdList.size()<1) {
				return new ArrayList<Map<String, Object>>();
			}else{
				if(viewPeopleAreaIdList.size()>0){
					for(Map<String, Object> map : viewPeopleAreaIdList){
						areaIdList.add(map);
					}
				}
			}
			//根据区域查询视角
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("cusNumber", cusNumber);
			map.put("areaIdList", areaIdList);
			//获取当前用户部门所有区域视角信息
			list = commonService.findSyncAreaForCombotree(map);
		}
		return list;
	}
	
	

}
