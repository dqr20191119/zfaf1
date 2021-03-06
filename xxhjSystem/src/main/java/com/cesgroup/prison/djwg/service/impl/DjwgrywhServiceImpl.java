package com.cesgroup.prison.djwg.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.djwg.dao.DjwgrywhMapper;
import com.cesgroup.prison.djwg.dao.DjwgzzwhMapper;
import com.cesgroup.prison.djwg.entity.DjwgrywhEntity;
import com.cesgroup.prison.djwg.entity.DjwgzzwhEntity;
import com.cesgroup.prison.djwg.service.DjwgrywhService;

import ces.sdk.util.StringUtil;

@Service("djwgrywhService")
public class DjwgrywhServiceImpl extends BaseDaoService<DjwgrywhEntity, String, DjwgrywhMapper> implements DjwgrywhService {

	@Resource
	private DjwgrywhMapper djwgrywhMapper;
	
	@Resource
	private DjwgzzwhMapper djwgzzwhMapper;
	
	@Override
	public DjwgrywhEntity getById(String id) {
		// TODO Auto-generated method stub
		DjwgrywhEntity djwgrywhEntity = djwgrywhMapper.getById(id);
		
		return djwgrywhEntity;
	}

	@Override
	public Page<DjwgrywhEntity> findList(DjwgrywhEntity djwgrywhEntity, PageRequest pageRequest) {
		// TODO Auto-generated method stub
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String jyId = user.getOrgCode();
		djwgrywhEntity.setJyId(jyId);
		return djwgrywhMapper.findList(djwgrywhEntity, pageRequest);
	}

	@Override
	public List<DjwgrywhEntity> findAllList(DjwgrywhEntity djwgrywhEntity) {
		// TODO Auto-generated method stub
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		String jyId = user.getOrgCode();
		djwgrywhEntity.setJyId(jyId);
		return djwgrywhMapper.findAllList(djwgrywhEntity);
	}

	@Override
	@Transactional
	public void saveOrUpdate(DjwgrywhEntity djwgrywhEntity) throws Exception {
		// TODO Auto-generated method stub
		String id =djwgrywhEntity.getId();	
		
		if(id != null && !"".equals(id)) {
			djwgrywhMapper.update(djwgrywhEntity);
		} else {	
			if(!StringUtil.isBlank(djwgrywhEntity.getCode())){
			djwgrywhEntity.setCode(djwgrywhEntity.getJyId()+"_"+djwgrywhEntity.getCode());
			}
			djwgrywhMapper.insert(djwgrywhEntity);			
		}
	}

	@Override
	@Transactional
	public void deleteByIds(String idList) {
		// TODO Auto-generated method stub
		String[] idArr = idList.split(",");
		djwgrywhMapper.updateStatusByIds(Arrays.asList(idArr));
	}

	@Override
	public Map<String, Object> getDjwg() {
		// TODO Auto-generated method stub
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		Map<String, Object> mapr = new HashMap<String, Object> ();
		DjwgzzwhEntity djwgzzwhEntity = new DjwgzzwhEntity();
		djwgzzwhEntity.setJyId(user.getOrgCode());
		DjwgrywhEntity djwgrywhEntity = new DjwgrywhEntity();
		djwgrywhEntity.setJyId(user.getOrgCode());
		List<DjwgzzwhEntity> listZzwh = djwgzzwhMapper.findZzwh(djwgzzwhEntity);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("label", "id");
		map.put("name", "id");
		List<Map<String,Object>> listGrid = new ArrayList<Map<String,Object>>();
		listGrid.add(map);
		for(int i = 0;i<listZzwh.size();i++){
			String code = listZzwh.get(i).getZzCode();
			String name  = listZzwh.get(i).getZzName();
			String css = "<div class=\"header-item\">";
			String jqname = name;
			if(name.length()>6){
				css = "<div class=\"header-item two-lines\">";
				String qname = name.substring(0,6);
				String hname = name.substring(6,name.length());
				jqname = qname+"<br>"+hname;
			}
			
			Map<String, Object> mapnew = new HashMap<String, Object>();
			mapnew.put("label", css+jqname+"</div>");
			mapnew.put("name", code);
			listGrid.add(mapnew);
		}
		mapr.put("gridColModel", listGrid);
	
		
		//??????????????????
		List<DjwgrywhEntity> listRywh2 = djwgrywhMapper.findAllList(djwgrywhEntity);
		
		List<DjwgrywhEntity> findfej = djwgrywhMapper.findfej(djwgrywhEntity);
		//<li>????????????????????????????????????<span class="department-amount"></span></li>
		StringBuffer sb = new StringBuffer();
		List listz = new ArrayList();
		for(int j = 0;j<listZzwh.size();j++){
			String zzCode = listZzwh.get(j).getZzCode();
			if(zzCode.equals(user.getOrgCode()+"_DWBZCY")){
				continue;
			}
			String zzName = listZzwh.get(j).getZzName();
			sb.append("<li>"+zzName+"???<span>");
			listz.add(zzCode);
			int rs = 0;
			
			for(int y =0;y<findfej.size();y++){
				String zCode = findfej.get(y).getZzCode();
				String userName =  findfej.get(y).getUserName();
				if(zCode.equals(zzCode)){
					if(!StringUtil.isBlank(userName)){
						String userNames[] = userName.split(",");
						rs= rs+userNames.length;
					}
				}
				
			}
			sb.append(rs+"</span></li>");	
		}
		List listzh = new ArrayList();
		int jo1=1;
		for(int i = 0;i<listRywh2.size();i++){
			String code = listRywh2.get(i).getCode();
			String zyuser = listRywh2.get(i).getUserName()+"<br>"+listRywh2.get(i).getZw();
			String  zzzcode = listRywh2.get(i).getZzCode();
			Map mapMax = new HashMap();
			mapMax.put("parentCode", code);
			mapMax.put("jyId", user.getOrgCode());
			List listMax = djwgrywhMapper.findMax(mapMax);//????????????
			for(int m = 0;m<listMax.size();m++){
				Map pxMap = (Map)listMax.get(m);
				DjwgrywhEntity djwgrywhEntity2 = new DjwgrywhEntity();
				djwgrywhEntity2.setPx(String.valueOf(pxMap.get("PX")));
				djwgrywhEntity2.setParentCode(code);
				djwgrywhEntity2.setJyId(user.getOrgCode());
				List<DjwgrywhEntity> listRywh = djwgrywhMapper.findRywh(djwgrywhEntity2);
				
				Map mapP = new HashMap();
				for(int y = 0 ;y<listRywh.size();y++){
					String userName = listRywh.get(y).getUserName();
					List listuser = new ArrayList();
					mapP.put("id", "local_"+jo1);
					jo1++;
					String zzcode =  listRywh.get(y).getZzCode();
					if(userName.indexOf(",")!=-1){
						String userNames[] = userName.split(",");
						for(int o = 0;o<userNames.length;o++){
							listuser.add(userNames[o]);
						}
						mapP.put(zzcode, listuser);
					}else{
						mapP.put(zzcode, userName);
					}
					
					//mapP.put("PX", listRywh.get(y).getPx());
					mapP.put(zzzcode, zyuser);
				}
				
				
				listzh.add(mapP);
				
				
			}
			
			
			
		}
		
		 System.out.println( JSON.toJSONString(listzh));
		 mapr.put("zsData", listzh);
		 mapr.put("zhrs", sb.toString());
		 
		/*{
    		"id": "local_1",
    		"name": "??????<br>???????????? | ?????????",
    		"department": "?????????????????????",
    		"shuji": ["?????????", "?????????", "?????????", "?????????", "??????"],
    		"dangyuan": ["?????????", "?????????", "?????????", "?????????", "?????????", "??????", "?????????", "?????????", "?????????", "?????????", "?????????", "?????????", "??????", "?????????",
    			"?????????", "??????", "?????????", "?????????", "??????", "?????????", "??????", "?????????", "??????", "??????", "?????????", "?????????"
    		],
    		"jijifenzi": ["?????????"],
    		"shenqing": [],
    		"qunzhong": ["??????","?????????"]
    	}
    	, 
    	{
    		"id": "local_2",
    		"name": "??????<br>???????????? | ?????????",
    		"department": "??????????????????",
    		"shuji": ["?????????", "??????", "??????", "?????????", "??????"],
    		"dangyuan": ["?????????", "??????", "??????", "?????????", "??????", "?????????", "??????", "?????????", "?????????", "?????????", "?????????", "??????", "?????????", "??????",
    			"??????", "?????????","?????????"
    		],
    		"jijifenzi": ["??????", "?????????", "??????"],
    		"shenqing": ["?????????", "??????", "?????????"],
    		"qunzhong": []
    	}*/
		
		
		
	 
		
		
		return mapr;
	}

}
