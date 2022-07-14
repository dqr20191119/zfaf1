package com.cesgroup.prison.xxhj.zfjbxx.service;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.util.StringUtil;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.xxhj.jnmj.service.JnmjService;
import com.cesgroup.prison.xxhj.zfjbxx.dao.ZfjbxxMapper;
import com.cesgroup.prison.xxhj.zfjbxx.entity.Zfjbxx;
import dm.jdbc.driver.DmdbType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.net.URLDecoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

;

@Service("zfjbxxService")
public class ZfjbxxServiceImpl extends BaseDaoService<Zfjbxx, String, ZfjbxxMapper> implements ZfjbxxService {

	@Resource
	private ZfjbxxMapper zfjbxxMapper;
	
	@Resource
	private JnmjService jnmjService;
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	public static String SZ_ST="食堂"; 
	
	public static String SZ_CJ="车间";
	
	public static String SZ_JS="监舍";
	
	public static String SZ_GGQY="公共区域";
	
	public static String SZ_JQ="监区";
	
	@Override
	public List<Map<String, Object>> queryXJPrisonerCount(HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cusNumber",request.getParameter("cusNumber") );
		map.put("drpmntId",request.getParameter("drpmntId") );
		return zfjbxxMapper.queryXJPrisonerCount(map);
	}

	@Override
	public List<Map<String, Object>> queryXJPrsnrCountPrisonList(HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<>();
		map.put("type",request.getParameter("type") );
		
		List<Map<String, Object>> maps = new ArrayList<>();
		maps=zfjbxxMapper.queryXJPrsnrCountPrisonList(map);
		
		List<OrgEntity> orgEntityList = AuthSystemFacade.getAllJyInfo(); 
		
		for(int i = 0; i < orgEntityList.size(); i++) {
			int k=0;
			for (int j = 0; j < maps.size(); j++) {
				
				Map<String, Object> m = new HashMap<>();
				if (orgEntityList.get(i).getOrgKey().equals(maps.get(j).get("JY_ID") + "")) {
					
					m.put("JY_ID", orgEntityList.get(i).getOrgKey());
					m.put("prisonName", orgEntityList.get(i).getOrgName());
					m.put("RS", maps.get(j).get("RS"));
					list.add(m);
					break;
				} else {
					
					k++;
					if (k == maps.size()) {
						m.put("JY_ID", orgEntityList.get(i).getOrgKey());
						m.put("prisonName", orgEntityList.get(i).getOrgName());
						m.put("RS", "0");
						list.add(m);
						break;
					}
				}
			}
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryXJPrsnrCountDrptmntList(HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cusNumber",request.getParameter("cusNumber") );
		map.put("type",request.getParameter("type") );
		
		List<Map<String, Object>> maps = new ArrayList<>();
		maps = zfjbxxMapper.queryXJPrsnrCountDrptmntList(map);
		
		return maps;
	}
	
	public List<Map<String, String>> countPrisoner(String cusNumber) { 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("para", cusNumber);
		return zfjbxxMapper.countPrisoner(map);
	}
	
	public List<Map<String, Object>> hospitalCount(String cusNumber) { 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("para", cusNumber);
		return zfjbxxMapper.hospitalCount(map);
	}
	
	public List<Map<String, Object>> queryPrisonCount(HttpServletRequest request) throws Exception {
		
		List<Map<String, Object>> list = new ArrayList<>();
		List<Map<String, Object>> maps = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<OrgEntity> orgEntityList = AuthSystemFacade.getAllJyInfo(); 
		String type = String.valueOf(request.getParameter("type"));
		
		if(!"".equals(type) && type.equals("0")) {       

			maps = zfjbxxMapper.insideByPrisonCount(map);
		} else if (!"".equals(type) && type.equals("1")) {
			
			maps = zfjbxxMapper.freePrisonerCount(map);
		} else if (!"".equals(type) && type.equals("2")) {
			
			maps = zfjbxxMapper.todayinPrisonerCount(map);
		} else if (!"".equals(type) && type.equals("3")) {
			
			maps = zfjbxxMapper.repeatCheckPrisonerCount(map);
		} else if (!"".equals(type) && type.equals("4")) {
			
			maps = zfjbxxMapper.leavePrisonerCount(map);
		} else if (!"".equals(type) && (type.equals("8")||type.equals("5")||type.equals("7"))) {
			
			maps = zfjbxxMapper.outHospitalPrisonerCount(map);
		}
		
		for (int i = 0; i < orgEntityList.size(); i++) {
			int k = 0;
			
			for (int j = 0; j < maps.size(); j++) {				
				Map<String, Object> m = new HashMap<>();
				if(orgEntityList.get(i).getOrgKey().equals(maps.get(j).get("PBD_CUS_NUMBER") + "")) {
					
					m.put("OBD_ORGA_IDNTY", orgEntityList.get(i).getOrgKey());
					m.put("OBD_ORGA_NAME", orgEntityList.get(i).getOrgName());
					m.put("COUNT", maps.get(j).get("COUNT"));
					list.add(m);
					break;
				} else {
					k++;
					if (k == maps.size()) {
						
						m.put("OBD_ORGA_IDNTY", orgEntityList.get(i).getOrgKey());
						m.put("OBD_ORGA_NAME", orgEntityList.get(i).getOrgName());
						m.put("COUNT", "0");
						list.add(m);
						break;
					}
				}
			}
		}
		
		return list;
	}

	public List<Map<String, Object>> queryDprtCount(HttpServletRequest request) throws Exception { 
		
		List<Map<String, Object>> list = new ArrayList<>();		
		String type = String.valueOf(request.getParameter("type"));
		String cusNumber = String.valueOf(request.getParameter("cusNumber"));
		
		List<OrgEntity> orgEntityList = AuthSystemFacade.getAllJqInfoByJyKey(cusNumber);
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> maps = new ArrayList<>();
		
		if(!"".equals(type) && type.equals("0")) {
			
			map.put("pbdCusNumber", cusNumber);
			maps = zfjbxxMapper.insidePrisonerDprtCount(map);
		} else if (!"".equals(type) && type.equals("1")) {

			map.put("cprCusNumber", cusNumber);
			maps = zfjbxxMapper.freePrisonerDprtCount(map);
		} else if (!"".equals(type) && type.equals("2")) {
			
			map.put("pbdCusNumber", cusNumber);
			maps = zfjbxxMapper.todayInPrisonerDprtCount(map);
		} else if (!"".equals(type) && type.equals("3")) {
			
			map.put("pbdCusNumber", cusNumber);
			maps = zfjbxxMapper.repeatcheckInPrisonerDprtCount(map);
		} else if (!"".equals(type) && type.equals("4")) {
			
			map.put("pbdCusNumber", cusNumber);
			maps = zfjbxxMapper.leavePrisonerDprtCount(map);
		} else if (!"".equals(type) && type.equals("8")) {

			map.put("pbdCusNumber", cusNumber);
			maps = zfjbxxMapper.outHospitalPrisonerDprtCount(map);
		}
		
		for(int i = 0; i < orgEntityList.size(); i++) {
			int k = 0;
			Map<String, Object> m = new HashMap<>();
			
			if(maps.size() != 0) {				
				for(int j = 0; j < maps.size(); j++) {
					
					if (orgEntityList.get(i).getOrgKey().equals(maps.get(j).get("PBD_PRSN_AREA_IDNTY") + "")) {
						
						m.put("PBD_PRSN_AREA_IDNTY", orgEntityList.get(i).getOrgKey());
						m.put("PBD_PRSN_AREA", orgEntityList.get(i).getOrgName());
						m.put("COUNT", maps.get(j).get("COUNT"));
						list.add(m);
						break;
					} else {

						k++;
						if (k == maps.size()) {							
							m.put("PBD_PRSN_AREA_IDNTY", orgEntityList.get(i).getOrgKey());
							m.put("PBD_PRSN_AREA", orgEntityList.get(i).getOrgName());
							m.put("COUNT", "0");
							list.add(m);
							break;
						}
					}
				}
			} else { 
				
				m.put("PBD_PRSN_AREA_IDNTY", orgEntityList.get(i).getOrgKey());
				m.put("PBD_PRSN_AREA", orgEntityList.get(i).getOrgName());
				m.put("COUNT", "0");
				list.add(m);
			}
		}
		
		return list;
	}

	@Override
	public List<Map<String, Object>> queryInfoPrisonerArchives(HttpServletRequest request) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();		
		String cusNumber = String.valueOf(request.getParameter("cusNumber"));
		map.put("pbdPrsnrIdnty", request.getParameter("prisonerId"));
		map.put("pbdCusNumber",cusNumber );
	
		List<Map<String, Object>> maps = new ArrayList<>();		
		maps=zfjbxxMapper.queryInfoPrisonerArchives(map);
		
		List<OrgEntity> orgEntityList = jnmjService.queryPrisonDepartment(cusNumber);
		
		for(int i=0;i<maps.size();i++) {
			Map<String, Object> m = maps.get(i);
		
			for(int j = 0; j < orgEntityList.size(); j++) {	
				
				if(orgEntityList.get(j).getOrgKey().equals(maps.get(i).get("PBD_PRSN_AREA_IDNTY") + "")) {			
					m.put("PBD_PRSN_AREA", orgEntityList.get(j).getOrgName());
				}
			}
		}
		return maps;
	}
	
	//罪犯财务状况
	@Override
	public Page<Map<String, Object>>listPrisonerFinanical(HttpServletRequest request,Pageable page) throws Exception {
		
		String type = String.valueOf(request.getParameter("type"));
		Page<Map<String, Object>> maps = null;
		Map<String, Object> map = new HashMap<String, Object>();
	
		if(type.equals("1")) {                                         //收入信息
			
			map.put("pinCusNumber", request.getParameter("pinCusNumber"));
			map.put("pinPrsnrIdnty", request.getParameter("pinPrsnrIdnty"));
			
			maps= zfjbxxMapper.listPrisonerIncome(map, page);
			
		} else if (type.equals("2")) {                                  //支出信息
			
			map.put("ppaCusNumber", request.getParameter("ppaCusNumber"));
			map.put("ppaPrsnrIdnty", request.getParameter("ppaPrsnrIdnty"));
			
			maps= zfjbxxMapper.listPrisonerPay(map, page);
			
		} else if (type.equals("3")) {                                   //账户信息
	     
			map.put("pacCusNumber", request.getParameter("pacCusNumber"));
			map.put("pacPrsnrIdnty", request.getParameter("pacPrsnrIdnty"));
			
			maps= zfjbxxMapper.listPrisonerAccount(map, page);
			
		} else if (type.equals("4")) {                                    //消费信息
	
			map.put("pcoCusNumber", request.getParameter("pcoCusNumber"));
			map.put("pcoPrsnrIdnty", request.getParameter("pcoPrsnrIdnty"));
			
			maps= zfjbxxMapper.listPrisonerConsume(map, page);
		}
		
		return maps;
	}
	
	/*罪犯考核奖惩*/
	@Override
	public Page<Map<String, Object>>listPrisonerRewardPunish(HttpServletRequest request,Pageable page) throws Exception {
		
		String type = String.valueOf(request.getParameter("type"));
		Page<Map<String, Object>> maps = null;
		Map<String, Object> map = new HashMap<String, Object>();
	
		if(type.equals("1")) {
		
			map.put("pprCusNumber", request.getParameter("pprCusNumber"));
			map.put("pprPrsnrIdnty", request.getParameter("pprPrsnrIdnty"));
			
			maps= zfjbxxMapper.listPoliticsReward(map, page);
		} else if (type.equals("2")) {
			
			map.put("pppaCusNumber", request.getParameter("pppCusNumber"));
			map.put("pppPrsnrIdnty", request.getParameter("pppPrsnrIdnty"));
			
			maps= zfjbxxMapper.listPoliticsPunish(map, page);
		} else if (type.equals("3")) {
	
			map.put("pjrCusNumber", request.getParameter("pjrCusNumber"));
			map.put("pjrPrsnrIdnty", request.getParameter("pjrPrsnrIdnty"));
			
			maps= zfjbxxMapper.listJudicialReward(map, page);
		}
		
		return maps;
	}

	public Page<Map<String, String>> listPrisonerHealthy(String pheCusNumber, String phePrsnrIdnty, Pageable page) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pheCusNumber", pheCusNumber);
		map.put("phePrsnrIdnty", phePrsnrIdnty);
		
		return zfjbxxMapper.listPrisonerHealthy(map, page);
	}

	public Page<Map<String, String>> listPrisonerMeeting(String pmeCusNumber, String pmePrsnrIdnty, Pageable page) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pmeCusNumber", pmeCusNumber);
		map.put("pmePrsnrIdnty", pmePrsnrIdnty);
		
		return zfjbxxMapper.listPrisonerMeeting(map, page);
	}

	public Page<Map<String, String>> listPrisonerPhone(String pphCusNumber, String pphPrsnrIdnty, Pageable page) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pphCusNumber", pphCusNumber);
		map.put("pphPrsnrIdnty", pphPrsnrIdnty);
		
		return zfjbxxMapper.listPrisonerPhone(map, page);
	}

	public Page<Map<String, Object>> queryPrisonerBriefInfo(HttpServletRequest request, PageRequest pageRequest) throws Exception {
		String para = String.valueOf(request.getParameter("para"));          
		String type = String.valueOf(request.getParameter("type"));           
		String cusNumber = String.valueOf(request.getParameter("cusNumber"));
		String prsnrIdnty = String.valueOf(request.getParameter("prsnrIdnty"));
		String prsnrName = String.valueOf(request.getParameter("prsnrName"));
		String prsnAreaIdnty = String.valueOf(request.getParameter("prsnAreaIdnty"));
		String prsnrSttsIndc = String.valueOf(request.getParameter("prsnrSttsIndc"));
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("para", para);
		map.put("type", type);
		map.put("cusNumber", cusNumber);
		map.put("prsnrIdnty", prsnrIdnty);
		map.put("prsnrName", prsnrName);
		map.put("prsnAreaIdnty", prsnAreaIdnty);
		map.put("prsnrSttsIndc", prsnrSttsIndc);
		
		List<OrgEntity> orgEntityLists = AuthSystemFacade.getAllJyInfo();   

		List<OrgEntity> orgEntityList = jnmjService.queryPrisonDepartment(cusNumber);  
		
		List<String>  dprmtList=new ArrayList<String>(); 
		Page<Map<String, Object>> pageList = null;
	
		for (int i = 0; i < orgEntityList.size(); i++) {
			
			dprmtList.add(orgEntityList.get(i).getOrgKey());
		}   
		
		//监外就医
		if(type.equals("8")) {
			
			pageList = zfjbxxMapper.queryHospitalPrisonerInfo(map, pageRequest);
		} else if(type.equals("21")) { 
			
			//邪教犯
			pageList = zfjbxxMapper.queryPrisonerXJF(map, pageRequest);
		} else if(type.equals("22")) {     
			
			//维族犯
			pageList = zfjbxxMapper.queryPrisonerWZF(map, pageRequest);
		} else {
			
			pageList = zfjbxxMapper.queryPrisonerBriefInfo(para, type,cusNumber, prsnrIdnty,
					prsnrName, prsnAreaIdnty, prsnrSttsIndc,dprmtList, pageRequest);
		}
		
		for (int i = 0; i < pageList.getContent().size(); i++) {
			
			Map<String, Object> m =  pageList.getContent().get(i);   //把查到的警员匹配上监狱名称
			
			for (int j = 0; j < orgEntityLists.size(); j++) { 
				if (orgEntityLists.get(j).getOrgKey().equals(pageList.getContent().get(i).get("PBD_CUS_NUMBER") + "")) {
					m.put("PBD_CUS_NAME", orgEntityLists.get(j).getOrgName());
					for(int k = 0; k < orgEntityList.size(); k++) {
						if (orgEntityList.get(k).getOrgKey().equals(pageList.getContent().get(i).get("PBD_PRSN_AREA_IDNTY") + "")) {
							m.put("PBD_PRSN_AREA", orgEntityList.get(k).getOrgName());
							break;
					    }
				    } 
	            }
	        }
		}
		
		return pageList;
	}

	public Page<Map<String, Object>> queryPrisonerXJF(HttpServletRequest request, PageRequest pageRequest) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String para = String.valueOf(request.getParameter("para"));
		String cusNumber = String.valueOf(request.getParameter("cusNumber"));
		map.put("cusNumber",cusNumber );
		map.put("para",para );
		
		return zfjbxxMapper.queryPrisonerXJF(map, pageRequest);
	}


	@Override
	public Page<Map<String, Object>> queryPrisonerInfo (HttpServletRequest request, PageRequest pageRequest) throws Exception {
		 
		String type = String.valueOf(request.getParameter("type"));
		String zyzf = String.valueOf(request.getParameter("zyzf"));
		String cusNumber = String.valueOf(request.getParameter("cusNumber"));
		String prsnAreaIdnty = request.getParameter("prsnAreaIdnty");
		String prsnrIdnty = request.getParameter("prsnrIdnty");
		String prsnrName = request.getParameter("prsnrName");
		String prsnrIdntyOrName = request.getParameter("prsnrIdntyOrName");
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("type", type);
		map.put("zyzf", zyzf);
		map.put("cusNumber", cusNumber);
		map.put("prsnrIdnty", prsnrIdnty);
		map.put("prsnrName", prsnrName);
		map.put("prsnAreaIdnty", prsnAreaIdnty);
		map.put("prsnrIdntyOrName", prsnrIdntyOrName);
		Page<Map<String, Object>> pageList = null;
		//pageList= zfjbxxMapper.queryPrisonerInfo(map, pageRequest);
		if("1".equals(type)){
			pageList= zfjbxxMapper.queryzfxx(map, pageRequest);
		}else{
			pageList= zfjbxxMapper.queryPrisonerInfo(map, pageRequest);
		}
		
		for (int i = 0; i < pageList.getContent().size(); i++) { 
			
			Map<String, Object> m =  pageList.getContent().get(i);
			String xq = String.valueOf(m.get("PBD_SNTN_TERM"));
			if(xq.length()==6){
				String nian = xq.substring(0,2);
				String yue = xq.substring(2,4);
				String ri = xq.substring(4,6);
				 String fhj = "";
				 if(!"00".equals(nian)){
					 fhj=fhj+nian+"年";
				 }
				 if(!"00".equals(yue)){
					 fhj=fhj+yue+"月";		 
				 }
				 if(!"00".equals(ri)){
					 fhj=fhj+ri+"日";
				 }
				m.put("PBD_SNTN_TERM", fhj);
			}else if(xq.length()==4){
				if("9995".equals(xq)){
					m.put("PBD_SNTN_TERM","无期徒刑");
				}
				if("9996".equals(xq)){
					m.put("PBD_SNTN_TERM","死刑缓期二年执行");
				}
			} 
		}
 
		return pageList;	
	}
	
	@Override
	public Page<Map<String, Object>> queryPrisonerBedInfo(HttpServletRequest request,Pageable page) throws Exception {
		
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("jsId", request.getParameter("jsId"));
		map.put("qyId", request.getParameter("qyId"));
		
		Page<Map<String, Object>> pageList = zfjbxxMapper.queryPrisonerBedInfo(map,page);
		return pageList;
		
	}
	
	@Override
	public Page<Map<String, Object>> queryJSPrisonerInfo(HttpServletRequest request,Pageable page) throws Exception {
		/*String jsId=request.getParameter("jsId");
		if(!StringUtil.isNull(jsId)) {
			String[] tmpString=jsId.split("_");
			Map<String, Object> map=new HashMap<String, Object>();
			map.put("cusNumber",tmpString[0]);
			map.put("lch", tmpString[1]);
			map.put("jsh", tmpString[2]);
			Page<Map<String, Object>> pageList = null;
			pageList = zfjbxxMapper.queryJSPrisonerInfo(map,page);
			return pageList;
		}
		return null;*/
		
		Map<String, Object> map=new HashMap<String, Object>();
		Page<Map<String, Object>> pageList = null;
		
		String cusNumber=request.getParameter("cusNumber");
		String lch=request.getParameter("lch");
		String jsh=request.getParameter("jsh");
		
		if(!StringUtil.isNull(lch)) {
			map.put("lch", lch);
		}
		if(!StringUtil.isNull(jsh)) {
			map.put("jsh", jsh);
		}
		if(!StringUtil.isNull(cusNumber)) {
			map.put("cusNumber",cusNumber);
			pageList = zfjbxxMapper.queryJSPrisonerInfo(map,page);
			return pageList;
		}
		return null;
	}

	@Override
	public Page<Map<String, Object>> findList(Map<String, Object> map, Pageable pageable) {
		Page<Map<String, Object>> pageList = null;
		try{
			pageList = zfjbxxMapper.queryJcjl(map, pageable);
		}catch(Exception e){
			e.printStackTrace();
		}
		return pageList;
	}

	@Override
	public List<Map<String, Object>> queryCaramer(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String zfbh =  request.getParameter("zfbh");
		String szqu =  URLDecoder.decode(request.getParameter("szqu"),"UTF-8");
		 
		Map<String, Object> mapZfxx = new HashMap<String, Object>();
		mapZfxx.put("zfbh", zfbh);
		//查询罪犯基本信息
		List<Map<String, Object>> listZfxx = zfjbxxMapper.querySzjq(mapZfxx);
		String szjq="";
		String jsh="";
		for(int i = 0;i<listZfxx.size();i++){
			Map<String, Object> zfxx = listZfxx.get(i);
			szjq = String.valueOf(zfxx.get("C_SZJQ"));
			jsh =  String.valueOf(zfxx.get("C_JSH"));
			break;
		}
		
			
	
		//查询对应线路
		String lxName="";
		if(SZ_ST.equals(szqu)){
			lxName = SZ_ST;
		}else if(SZ_CJ.equals(szqu)){
			lxName = szjq+SZ_CJ;
		}else if(SZ_JS.equals(szqu)){
			if(!StringUtil.isNull(jsh)&&!"null".equals(jsh)){
				String[] lhs = jsh.split("#");
				String lh = "";//楼号
				String lc = "";//楼层
				String bs = "";//几班（几号监舍）
				if(lhs.length>=1){
					lh = lhs[0];
					String[] lcs = lhs[1].split("_");
					if(lcs.length>=1){
						lc = lcs[0];
						bs = lcs[1];
					}
				}
				if(!StringUtil.isNull(lh)&&!StringUtil.isNull(lc)&&!StringUtil.isNull(bs)){
					if("1".equals(lh)){
						if("1".equals(lc)){//一监区
							lxName="一监区"+Integer.valueOf(bs)+"班A";
						}else if("2".equals(lc)){//二监区
							lxName="二监区"+Integer.valueOf(bs)+"班A";
						}else if("3".equals(lc)){//三监区
							lxName="三监区"+Integer.valueOf(bs)+"班A";
						}
					}else if("2".equals(lh)){
						if("1".equals(lc)){
							lxName="四监区"+Integer.valueOf(bs)+"班A";
						}else if("2".equals(lc)){
							lxName="五监区"+Integer.valueOf(bs)+"班A";
						}else if("3".equals(lc)){
							lxName="六监区"+Integer.valueOf(bs)+"班A";
						}
					}else if("3".equals(lh)){
						if("3".equals(lc)){
							lxName="七监区"+Integer.valueOf(bs)+"班A";
						}else if("4".equals(lc)){
							lxName="八监区"+Integer.valueOf(bs)+"班A";
						}else if("5".equals(lc)){
							lxName="九监区"+Integer.valueOf(bs)+"班A";
						}
					}else if("4".equals(lh)){
						if("2".equals(lc)){
							lxName="十监区"+Integer.valueOf(bs)+"班A";
						}else if("3".equals(lc)){
							lxName="十一监区"+Integer.valueOf(bs)+"班A";
						} 
					}
				}
			}
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("xlmc", lxName);
		List<Map<String, Object>> list = zfjbxxMapper.queryXl(map);
		List<Map<String, Object>> listCaramer = new ArrayList<Map<String, Object>>();
		for(int i = 0;i<list.size();i++){
			Map<String, Object> maplx = list.get(i);
			String lxid = (String) maplx.get("ID");
			Map<String, Object> mapSxj = new HashMap<String, Object>();
			mapSxj.put("lxId", lxid);
			listCaramer = zfjbxxMapper.openCamare(mapSxj);
			break;
		}
		return listCaramer;
	}

	@Override
	public List<Map<String, Object>> queryRylb(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String zfbh = request.getParameter("zfbh");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("zfbh", zfbh);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return zfjbxxMapper.queryRygl(map);
	}

	@Override
	public Map<String, String> jrdt(HttpServletRequest request) throws Exception {
		String counts="";
		
		UserBean userben= AuthSystemFacade.getLoginUserInfo();
		String temp = userben.getUserLevel().toString();
		//查询保外就医
		Map<String,Object> bwjyMapParma =  new HashMap<String, Object>();
		String bwjyCount="";
		if("2".equals(temp)) {
			bwjyMapParma.put("jyId", userben.getCusNumber());
			bwjyMapParma.put("jqId", userben.getDprtmntCode());
			Map<String, Object> queryjrBwjyCount = zfjbxxMapper.queryjrBwjyCount(bwjyMapParma);
			 bwjyCount = queryjrBwjyCount.get("count").toString();
		}
		//查询今日收押新犯 男 或 nv
		String sfycount ="";
		
		Map<String, Object> sfynfCountMap = zfjbxxMapper.queryjrxsfBySexCount("1");
		String sfynf = sfynfCountMap.get("count").toString();
		
		Map<String, Object> sfynvfCountMap = zfjbxxMapper.queryjrxsfBySexCount("2");
		String sfynvf = sfynvfCountMap.get("count").toString();
		//拼接男犯 和女犯 数量
		sfycount =sfynf+","+sfynvf;
		
		
		
		Map map2 = new HashMap();
		//20190925 用户需要实时显示省局数据，而不是定时任务执行，所以修改成和监狱获取数据来源一样 heqh
		/*//省局用户改为从数据表中读取
		if(temp.equals("1") ){
			String sql="select * from PORTAL.T_PORTAL_JRDT t where jy_id = '4300' and jq_id is null " +
					" and trunc(c_cj_rq) = to_date('" + DateUtils.getCurrentDate(false)+"','yyyy-MM-dd')" +
					" order by c_cj_rq desc";
			Connection con = null;
			Statement statement = null;
			ResultSet rs = null;
			try {
				con = dataSource.getConnection();
				statement = con.createStatement();
				rs = statement.executeQuery(sql);
				while (rs.next()) {
					counts = rs.getString("COUNTS");
					map2.put("counts", counts);
					break;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (statement != null)
						statement.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}else{//其他用户继续使用查询的即时数据
*/			Connection con = null;
			Statement statement = null;
			ResultSet rs = null;
			try {
				con = dataSource.getConnection();
				CallableStatement cs = con.prepareCall("{call YZGL.PRO_JRDT(?,?,?,?)}") ;
				cs.setString(1, String.valueOf(temp));// 设置输入参数的值
				if(temp.equals("1") ){//省局
					cs.setString(2, "");
					cs.setString(3, "");
				} else {//监狱
					cs.setString(2, userben.getCusNumber());
					cs.setString(3, userben.getDprtmntCode());
				}
				cs.registerOutParameter(4,   DmdbType.DATA_VARCHAR);// 注册输出参数的类
				cs.execute();
                counts = cs.getString(4);
                if("1".equals(temp)) {
                	 counts=counts+sfycount;
                 	map2.put("counts", counts);
                }else {
                	counts=counts+bwjyCount;
                	map2.put("counts", counts);
                }
                
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (statement != null)
						statement.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
		//}
		return map2;
	}
	
	
	 
}
