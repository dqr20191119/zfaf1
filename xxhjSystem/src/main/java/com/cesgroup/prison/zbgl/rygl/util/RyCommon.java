package com.cesgroup.prison.zbgl.rygl.util;
/**
* @author lihong
* @date 创建时间：2020年5月13日 下午8:14:14
* 类说明:
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.commons.SpringContextUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.zbgl.bcgl.entity.BcglEntity;
import com.cesgroup.prison.zbgl.bcgl.service.BcglService;
import com.cesgroup.prison.zbgl.bcgl.service.impl.BcglServiceImpl;
import com.cesgroup.prison.zbgl.rygl.entity.RyglEntity;
import com.cesgroup.prison.zbgl.rygl.service.RyglService;
import com.cesgroup.prison.zbgl.rygl.service.impl.RyglServiceImpl;

public class RyCommon {
	private static RyglService ryglService = SpringContextUtils.getBean(RyglServiceImpl.class);
	
	private static BcglService bcglService = SpringContextUtils.getBean(BcglServiceImpl.class);
	
	/**
	 * 返回值为value label格式
	 * @param cusNumber
	 * @return
	 */
	public static String  getCommonbox(String cusNumber) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();	
		RyglEntity ry = new RyglEntity();
		ry.setCusNumber(cusNumber);
		List<RyglEntity> selectList = ryglService.selectList(ry);
		for (int i = 0; i < selectList.size(); i++) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("value", selectList.get(i).getId());
			map.put("label", selectList.get(i).getName());
			list.add(map);
		}
              return JSON.toJSONString(list);
	}
	
	
	/**
	 * 返回值为value label格式
	 * @param cusNumber
	 * @return
	 */
	public static String  getCommonbox3(String cusNumber) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();	
		RyglEntity ry = new RyglEntity();
		ry.setCusNumber(cusNumber);
		List<RyglEntity> selectList = ryglService.selectList(ry);
		for (int i = 0; i < selectList.size(); i++) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("value", selectList.get(i).getName());
			map.put("label", selectList.get(i).getName());
			list.add(map);
		}
              return JSON.toJSONString(list);
	}


    /**
     * 返回值为value label格式 根据监狱编号获取用户信息 查询系统管理平台的数据 auth 用于监狱用户
     * @param cusNumber
     * @return
     */
    public static String  getCommonbox4(String cusNumber) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
      List<Map<String,Object>> selectList = ryglService.getUserInfoByCusNumber(cusNumber);
        for (int i = 0; i < selectList.size(); i++) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("value", selectList.get(i).get("USER_ID").toString());
            map.put("label", selectList.get(i).get("USER_NAME"));
            list.add(map);
        }
        return JSON.toJSONString(list);
    }

    /**
     * 返回值为 value  text 格式 根据监狱编号获取用户信息 查询系统管理平台的数据 auth 用于监狱用户
     * @param cusNumber
     * @return
     */
    public static String  getCommonbox5(String cusNumber) {
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        RyglEntity ry = new RyglEntity();
        List<Map<String,Object>> selectList = ryglService.getUserInfoByCusNumber(cusNumber);
        for (int i = 0; i < selectList.size(); i++) {
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("value", selectList.get(i).get("USER_ID").toString());
            map.put("text", selectList.get(i).get("USER_NAME"));
            list.add(map);
        }
        return JSON.toJSONString(list);
    }


    /**
	 * 返回值为 value  text 格式
	 * @param cusNumber
	 * @return
	 */
	public static String  getCommonbox2(String cusNumber) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();	
		RyglEntity ry = new RyglEntity();
		ry.setCusNumber(cusNumber);
		List<RyglEntity> selectList = ryglService.selectList(ry);
		for (int i = 0; i < selectList.size(); i++) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("value", selectList.get(i).getId());
			map.put("text", selectList.get(i).getName());
			list.add(map);
		}
              return JSON.toJSONString(list);
	}
	
	
	
	/**
	 * 获取班次
	 * 返回值为 value  text 格式
	 * @param cusNumber
	 * @return
	 */
	public static String  getBcglCommonbox(String cusNumber) {
		//UserBean user = AuthSystemFacade.getLoginUserInfo();
		BcglEntity bcglEntity = new BcglEntity();
		bcglEntity.setDorCusNumber(cusNumber);
		bcglEntity.setDorStatus(CommonConstant.STATUS_VALID_CODE);
		List<BcglEntity> list = bcglService.findAllList(bcglEntity);
		
		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for(int i = 0; i < list.size(); i++) {
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("value", list.get(i).getId());
			map.put("text", list.get(i).getDorDutyOrderName());	
			maps.add(map);
		}
              return JSON.toJSONString(maps);
	}
	
}
