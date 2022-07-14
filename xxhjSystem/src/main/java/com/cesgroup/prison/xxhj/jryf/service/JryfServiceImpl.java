package com.cesgroup.prison.xxhj.jryf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.xxhj.jnmj.service.JnmjService;
import com.cesgroup.prison.xxhj.jryf.dao.JryfMapper;
import com.cesgroup.prison.xxhj.jryf.entity.Jryf;

@Service("jryfService")
public class JryfServiceImpl extends BaseDaoService<Jryf, String, JryfMapper> implements JryfService {

	@Resource
	private JryfMapper jryfMapper;
	
	@Resource
	private JnmjService jnmjService;
	
	public List<Map<String, String>> countPrisoner(String cusNumber) { 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("para", cusNumber);
		return jryfMapper.countPrisoner(map);
	}
	
	public List<Map<String, Object>> hospitalCount(String cusNumber) { 
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("para", cusNumber);
		return jryfMapper.hospitalCount(map);
	}
	
	public List<Map<String, Object>> queryPrisonCount(HttpServletRequest request) throws Exception {
		
		List<Map<String, Object>> list = new ArrayList<>();
		List<Map<String, Object>> maps = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		
		List<OrgEntity> orgEntityList = AuthSystemFacade.getAllJyInfo(); 
		String type = String.valueOf(request.getParameter("type"));
		
		if(!"".equals(type) && type.equals("0")) {       

			maps = jryfMapper.insideByPrisonCount(map);
		} else if (!"".equals(type) && type.equals("1")) {
			
			maps = jryfMapper.freePrisonerCount(map);
		} else if (!"".equals(type) && type.equals("2")) {
			
			maps = jryfMapper.todayinPrisonerCount(map);
		} else if (!"".equals(type) && type.equals("3")) {
			
			maps = jryfMapper.repeatCheckPrisonerCount(map);
		} else if (!"".equals(type) && type.equals("4")) {
			
			maps = jryfMapper.leavePrisonerCount(map);
		} else if (!"".equals(type) && (type.equals("8")||type.equals("5")||type.equals("7"))) {
			
			maps = jryfMapper.outHospitalPrisonerCount(map);
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
			maps = jryfMapper.insidePrisonerDprtCount(map);
		} else if (!"".equals(type) && type.equals("1")) {

			map.put("cprCusNumber", cusNumber);
			maps = jryfMapper.freePrisonerDprtCount(map);
		} else if (!"".equals(type) && type.equals("2")) {
			
			map.put("pbdCusNumber", cusNumber);
			maps = jryfMapper.todayInPrisonerDprtCount(map);
		} else if (!"".equals(type) && type.equals("3")) {
			
			map.put("pbdCusNumber", cusNumber);
			maps = jryfMapper.repeatcheckInPrisonerDprtCount(map);
		} else if (!"".equals(type) && type.equals("4")) {
			
			map.put("pbdCusNumber", cusNumber);
			maps = jryfMapper.leavePrisonerDprtCount(map);
		} else if (!"".equals(type) && type.equals("8")) {

			map.put("pbdCusNumber", cusNumber);
			maps = jryfMapper.outHospitalPrisonerDprtCount(map);
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
	public List<Map<String, Object>> queryInfoPrisonerarchives(HttpServletRequest request) throws Exception {

		Map<String, Object> map = new HashMap<String, Object>();		
		String cusNumber = String.valueOf(request.getParameter("pbdCusNumber"));
		map.put("pbdPrsnrIdnty", request.getParameter("pbdPrsnrIdnty"));
		map.put("pbdCusNumber",cusNumber );
	
		List<Map<String, Object>> maps = new ArrayList<>();		
		maps=jryfMapper.queryInfoPrisonerarchives(map);
		
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
	
		if(type.equals("1")) {
			
			map.put("pinCusNumber", request.getParameter("pinCusNumber"));
			map.put("pinPrsnrIdnty", request.getParameter("pinPrsnrIdnty"));
			
			maps= jryfMapper.listPrisonerIncome(map, page);
			
		} else if (type.equals("2")) {
			
			map.put("ppaCusNumber", request.getParameter("ppaCusNumber"));
			map.put("ppaPrsnrIdnty", request.getParameter("ppaPrsnrIdnty"));
			
			maps= jryfMapper.listPrisonerPay(map, page);
			
		} else if (type.equals("3")) {
	
			map.put("pacCusNumber", request.getParameter("pacCusNumber"));
			map.put("pacPrsnrIdnty", request.getParameter("pacPrsnrIdnty"));
			
			maps= jryfMapper.listPrisonerAccount(map, page);
			
		} else if (type.equals("4")) {
	
			map.put("pcoCusNumber", request.getParameter("pcoCusNumber"));
			map.put("pcoPrsnrIdnty", request.getParameter("pcoPrsnrIdnty"));
			
			maps= jryfMapper.listPrisonerConsume(map, page);
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
			
			maps= jryfMapper.listPoliticsReward(map, page);
		} else if (type.equals("2")) {
			
			map.put("pppaCusNumber", request.getParameter("pppCusNumber"));
			map.put("pppPrsnrIdnty", request.getParameter("pppPrsnrIdnty"));
			
			maps= jryfMapper.listPoliticsPunish(map, page);
		} else if (type.equals("3")) {
	
			map.put("pjrCusNumber", request.getParameter("pjrCusNumber"));
			map.put("pjrPrsnrIdnty", request.getParameter("pjrPrsnrIdnty"));
			
			maps= jryfMapper.listJudicialReward(map, page);
		}
		
		return maps;
	}

	public Page<Map<String, String>> listPrisonerHealthy(String pheCusNumber, String phePrsnrIdnty, Pageable page) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pheCusNumber", pheCusNumber);
		map.put("phePrsnrIdnty", phePrsnrIdnty);
		
		return jryfMapper.listPrisonerHealthy(map, page);
	}

	public Page<Map<String, String>> listPrisonerMeeting(String pmeCusNumber, String pmePrsnrIdnty, Pageable page) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pmeCusNumber", pmeCusNumber);
		map.put("pmePrsnrIdnty", pmePrsnrIdnty);
		
		return jryfMapper.listPrisonerMeeting(map, page);
	}

	public Page<Map<String, String>> listPrisonerPhone(String pphCusNumber, String pphPrsnrIdnty, Pageable page) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pphCusNumber", pphCusNumber);
		map.put("pphPrsnrIdnty", pphPrsnrIdnty);
		
		return jryfMapper.listPrisonerPhone(map, page);
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
			
			pageList = jryfMapper.queryHospitalPrisonerInfo(map, pageRequest);
		} else if(type.equals("21")) { 
			
			//邪教犯
			pageList = jryfMapper.queryPrisonerXJF(map, pageRequest);
		} else if(type.equals("22")) {     
			
			//维族犯
			pageList = jryfMapper.queryPrisonerWZF(map, pageRequest);
		} else {
			
			pageList = jryfMapper.queryPrisonerBriefInfo(para, type,cusNumber, prsnrIdnty,
					prsnrName, prsnAreaIdnty, prsnrSttsIndc,dprmtList, pageRequest);
		}
		
		for (int i = 0; i < pageList.getContent().size(); i++) { 
			Map<String, Object> m =  pageList.getContent().get(i);   //把查到的警员匹配上监狱名称
			for (int j = 0; j < orgEntityLists.size(); j++) { 
				if (orgEntityLists.get(j).getOrgKey().equals(pageList.getContent().get(i).get("PBD_CUS_NUMBER") + "")) {
					m.put("PBD_CUS_NAME", orgEntityLists.get(j).getOrgName());
					for(int k = 0;k<orgEntityList.size();k++) {
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
		
		return jryfMapper.queryPrisonerXJF(map, pageRequest);
	}
	
	@Override
	public List<Map<String, Object>> queryXJPrisonerCount(HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cusNumber",request.getParameter("cusNumber") );
		
		return jryfMapper.queryXJPrisonerCount(map);
		
	}

	@Override
	public List<Map<String, Object>> queryXJPrsnrCountPrisonList(HttpServletRequest request) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Map<String, Object>> list = new ArrayList<>();
		map.put("type",request.getParameter("type") );
		
		List<Map<String, Object>> maps = new ArrayList<>();
		maps=jryfMapper.queryXJPrsnrCountPrisonList(map);
		
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
		maps = jryfMapper.queryXJPrsnrCountDrptmntList(map);
		
		return maps;
	}
	
	@Override
	public Page<Map<String, Object>> queryPrisonerInfo (HttpServletRequest request, PageRequest pageRequest) throws Exception {
		
		String type = String.valueOf(request.getParameter("type"));
		String cusNumber = String.valueOf(request.getParameter("cusNumber"));
		String prsnrIdnty = String.valueOf(request.getParameter("prsnrIdnty"));
		String prsnrName = String.valueOf(request.getParameter("prsnrName"));
		String prsnAreaIdnty = String.valueOf(request.getParameter("prsnAreaIdnty"));
	
		Map<String, Object> map=new HashMap<String, Object>();
		map.put("type", type);
		map.put("cusNumber", cusNumber);
		map.put("prsnrIdnty", prsnrIdnty);
		map.put("prsnrName", prsnrName);
		map.put("prsnAreaIdnty", prsnAreaIdnty);
		
		Page<Map<String, Object>> pageList = null;
		pageList= jryfMapper.queryPrisonerInfo(map, pageRequest);
		
		//拿到监狱
		List<OrgEntity> orgEntityLists = AuthSystemFacade.getAllJyInfo();   
		//根据拿到监区
		List<OrgEntity> orgEntityList = jnmjService.queryPrisonDepartment(cusNumber);  

		for (int i = 0; i < pageList.getContent().size(); i++) { 
			Map<String, Object> m =  pageList.getContent().get(i);
			for (int j = 0; j < orgEntityLists.size(); j++) {  
				if (orgEntityLists.get(j).getOrgKey().equals(pageList.getContent().get(i).get("PBD_CUS_NUMBER") + "")) {
					m.put("PBD_CUS_NAME", orgEntityLists.get(j).getOrgName());
					for(int k = 0;k<orgEntityList.size();k++) {
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
}