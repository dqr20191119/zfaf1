package com.cesgroup.prison.jswh.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jswh.dao.JswhMapper;
import com.cesgroup.prison.jswh.entity.JswhEntity;
import com.cesgroup.prison.jswh.service.JswhService;

@Service("jswhService")
public class JswhServiceImpl extends BaseDaoService<JswhEntity, String, JswhMapper> implements JswhService {
	
	@Resource
	private JswhMapper jswhMapper;
	
	@Override
	public JswhEntity getById(String id) {
		
		return jswhMapper.getById(id);
	}
	
	@Override
	public Page<JswhEntity> findList(JswhEntity JswhEntity, PageRequest pageRequest) {
		Page<JswhEntity> map = jswhMapper.findList(JswhEntity, pageRequest);
		return map;
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(List<Map<String, Object>> jswhs) throws Exception {

		JswhEntity jswhEntity =new JswhEntity();
		jswhEntity.setCpjLch(jswhs.get(0).get("key").toString()); //区域ID
		jswhEntity.setCpjLc(jswhs.get(0).get("value").toString()); //区域名称
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		jswhEntity.setCpjCusNumber(user.getOrgCode());
		jswhEntity.setCpjCrteUserId(user.getUserId());
		jswhEntity.setCpjCrteTime(new Date());
		jswhEntity.setCpjUpdtUserId(user.getUserId());
		jswhEntity.setCpjUpdtTime(new Date());
		String id = (String) jswhs.get(1).get("value");   //id
		
			for( int i = 2; i< jswhs.size(); i++) {
				
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> maps =  (List<Map<String, Object>>) jswhs.get(i).get("value");
				jswhEntity.setCpjJs(maps.get(0).get("value").toString());   //监舍名称
				jswhEntity.setCpjCameraId(maps.get(1).get("value").toString());   //摄像头id
				jswhEntity.setCpjCameraName(maps.get(1).get("key").toString());   //摄像头名称
				jswhEntity.setCpjFCameraName(String.valueOf(maps.get(2).get("key")));          //副摄像头名称
				
				@SuppressWarnings("unchecked")
				List<Map<String, Object>> values =  (List<Map<String, Object>>) maps.get(2).get("value");
				String value = "";
				for(int j =0 ;j < values.size() ;j++) {
					if( j == 0) {
						value =  String.valueOf(values.get(j));
					} else {
						value = values.get(j)+","+ value;
					}
				}
				jswhEntity.setCpjFCameraId(value);          //副摄像头ID
				
				if(id != null && !"".equals(id)) {
					jswhEntity.setId(id);
					jswhMapper.update(jswhEntity);
				} else {
					jswhEntity.setId(null);
					jswhMapper.insert(jswhEntity);
			}
		}
	}	
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {
		String[] idArr = ids.split(",");
		jswhMapper.deleteByIds(Arrays.asList(idArr));			 
	}

	@Override
	public List<Map<String, Object>> findAllList(JswhEntity jswhEntity) {
		
		return jswhMapper.findAllList(jswhEntity);
	}
}
