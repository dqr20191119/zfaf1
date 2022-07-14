package com.cesgroup.prison.xxhj.jnmj.service;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.util.StringUtil;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.xxhj.jnmj.dao.JnmjMapper;
import com.cesgroup.prison.xxhj.jnmj.entity.Jnmj;

/**
 * 监内民警数据业务操作类
 * @author lincoln.cheng
 *
 */
@Service("jnmjService")
public class JnmjServiceImpl extends BaseDaoService<Jnmj, String, JnmjMapper> implements JnmjService {
	
	public static String SZ_ST="食堂"; 
	
	public static String SZ_CJ="车间";
	
	public static String SZ_JS="监舍";
	
	public static String SZ_GGQY="公共区域";
	
	public static String SZ_JQ="监区";
	public Page<Map<String, Object>> listPoliceInoutRecordInfo(HttpServletRequest request, PageRequest pageRequest) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("cusNumber", request.getParameter("cusNumber"));
		paramMap.put("drpmntId", request.getParameter("drpmntId"));
		paramMap.put("policeId", request.getParameter("policeId"));
		paramMap.put("startTime", request.getParameter("startTime"));
		paramMap.put("endTime", request.getParameter("endTime"));

		Page<Map<String, Object>> List = this.getDao().listPoliceInoutRecordInfo(paramMap, pageRequest);
		return List;
	}
	
	/*	查询首页民警数据* */
	public Map<String, Object> querySYInsidePoliceCount(Map<String, Object> map) {
		
		String config = (String) map.get("config");
		Map<String, Object> maps = new HashMap<String, Object>();
		Map<String, Object> list = new HashMap<String, Object>();
		if(config.equals("1")) {   
			//查询刷卡记录
			maps = this.getDao().querySYInsidePoliceCountByRecord(map);
		} else if(config.equals("2")) {   
			//查询值班情况
			maps = this.getDao().querySYInsidePoliceCountByDuty(map);
		} else {           
			//查询组织
			maps = this.getDao().querySYInsidePoliceCountByOrg(map);
		} 
		
		if( maps != null) {
			list = maps;
		} else {
			list.put("INSIDE_POLICE_COUNT", 0);
		}
		return list;
		
	}
	/*public List<Map<String, String>> queryInsidePoliceCount(String cusNumber, String drpmntId) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cusNumber", cusNumber);
		map.put("drpmntId", drpmntId);
		List<Map<String, String>> list = this.getDao().queryInsidePoliceCount(map);

		return list;
	}*/

	public List<Map<String, String>> queryInsidePoliceCountByPrison() throws Exception {

		List<Map<String, String>> list = new ArrayList<>();
		List<Map<String, String>> maps = new ArrayList<>();
		List<OrgEntity> orgEntityList = AuthSystemFacade.getAllJyInfo();

		maps = this.getDao().queryInsidePoliceCountByPrison();

		for (int i = 0; i < orgEntityList.size(); i++) {
			int k = 0;
			for (int j = 0; j < maps.size(); j++) {

				Map<String, String> m = new HashMap<>();
				if (orgEntityList.get(i).getOrgKey().equals(maps.get(j).get("CIP_CUS_NUMBER") + "")) {

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

	public List<OrgEntity> queryPrison() throws Exception {

		List<OrgEntity> orgEntityList = AuthSystemFacade.getAllJyInfo();
		return orgEntityList;
	}

	public List<OrgEntity> queryPrisonDepartment(String cusNumber) throws Exception {

		List<OrgEntity> orgEntityList = AuthSystemFacade.getAllJqInfoByJyKey(cusNumber);
		
		return orgEntityList;
	}

	public List<Map<String, String>> queryPrisonDrptmntPoliceCount(String cusNumber,String drpmntId, String config) {
		
		List<Map<String, String>> list = new ArrayList<>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cusNumber", cusNumber);
		map.put("drpmntId", drpmntId);
		
		if(config.equals("1")) {   
			//查询刷卡记录
			 list = this.getDao().queryPrisonDrptmntPoliceCount(map);
		} else if(config.equals("2")) {   
			//查询值班情况
			 list = this.getDao().queryPrisonDrptmntPoliceCountByDuty(map);
		} else {           
			//查询组织
			list = this.getDao().queryInsidePoliceCountByOrg(map);
		}  
		return list;
	}

	
	public List<Map<String, Object>> queryPrisonDrptmntPoliceInfo(HttpServletRequest request) throws Exception {
		
		String config =  String.valueOf(request.getParameter("config")); 
		String para = String.valueOf(request.getParameter("para"));             // para对应 sql的方法
		String cusNumber = String.valueOf(request.getParameter("cusNumber"));
		String drptmntId = String.valueOf(request.getParameter("drptmntId"));
		/*String policeIdnty = String.valueOf(request.getParameter("policeIdnty"));
		String policeName = String.valueOf(request.getParameter("policeName"));*/
		String policeIdntyOrName = String.valueOf(request.getParameter("policeIdntyOrName"));
		String policeNoList = String.valueOf(request.getParameter("policeNoList"));

		List<OrgEntity> orgEntityList = queryPrisonDepartment(cusNumber); // 监区接口 拿到监狱下所有监区
		List<String> dprmtList = new ArrayList<String>();
		List<Map<String, Object>> maps = new ArrayList<>();

		for (int i = 0; i < orgEntityList.size(); i++) {

			dprmtList.add(orgEntityList.get(i).getOrgKey());
		}
		String policeNoLists[] = policeNoList.split(",");
		String pliceLi=null;
		List policeNoListss = new ArrayList();
		for(int i = 0;i<policeNoLists.length;i++){
			if(!StringUtil.isNull(policeNoLists[i]) && !"null".equals(policeNoLists[i]) ){
				policeNoListss.add(policeNoLists[i]);
				pliceLi="1";
			}
		}
		List<OrgEntity> orgEntityLists = AuthSystemFacade.getAllJyInfo(); // 监狱接口
		if (config.equals("1")) {
			maps = this.getDao().queryPrisonDrptmntPoliceInfo(para, cusNumber, drptmntId, policeIdntyOrName, dprmtList,policeNoListss,pliceLi);
		} else if (config.equals("2")) {
			maps = this.getDao().queryPrisonDrptmntPoliceInfoByDuty(para, cusNumber, drptmntId, policeIdntyOrName, dprmtList,policeNoListss,pliceLi);
		} else {
			maps = this.getDao().queryPrisonDrptmntPoliceInfoByOrg(para, cusNumber, drptmntId, policeIdntyOrName, dprmtList,policeNoListss,pliceLi);
		} 
		
		
		/*Map mapQyjl = new HashMap();
		mapQyjl.put("jyId", cusNumber);
		List listQyjl = this.getDao().querySzqy(mapQyjl);
		
		
		for (int i = 0; i < maps.size(); i++) { // 把查到的警员匹配上监狱名称

			Map<String, Object> m = maps.get(i);
			for (int j = 0; j < orgEntityLists.size(); j++) {

				if (orgEntityLists.get(j).getOrgKey().equals(maps.get(i).get("PBD_CUS_NUMBER") + "")) {

					m.put("OBD_ORGA_NAME", orgEntityLists.get(j).getOrgName());
					break;
				}
			}
			
			String mjbh = String.valueOf( m.get("PBD_POLICE_IDNTY"));
			if(mjbh.length()>=5){
				String jqzfc = mjbh.substring( mjbh.length()-5, mjbh.length());
				String zzz = "6"+jqzfc;
				m.put("PBD_POLICE_CMMNCT", zzz);
			}
			
			
			String szqy = SZ_JQ;
			for(int q = 0;q<listQyjl.size();q++){
				Map mapSzqy = (Map) listQyjl.get(q);
				String inOut = String.valueOf(mapSzqy.get("INOUT"));
				String quName =  String.valueOf(mapSzqy.get("CHANNEL_NAME"));
				String carNo =  String.valueOf(mapSzqy.get("CERTIFICATE_NO"));
				if(mjbh.equals(carNo)){
					if(!StringUtil.isNull(inOut)){
						if("0".equals(inOut)){//出去
							szqy=SZ_GGQY;
						}else if("1".equals(inOut)&&(StringUtil.isNull(quName)||"null".equals(quName))){//进来
							szqy = SZ_JQ;
						}else if("1".equals(inOut)&&!StringUtil.isNull(quName)){
							//进到哪里
							if(quName.indexOf(SZ_ST)!=-1){
								szqy = SZ_ST;
							}else if(quName.indexOf(SZ_CJ)!=-1){
								szqy = SZ_CJ;
							}else if(quName.indexOf(SZ_JS)!=-1){
								szqy = SZ_JS;
							}else{
								szqy = SZ_JQ;
							}
						}
					}else{
						if(quName.indexOf(SZ_ST)!=-1){
							if(quName.indexOf("出口")!=-1){//出去
								szqy=SZ_GGQY;
							}else if(quName.indexOf("入口")!=-1){
								szqy = SZ_ST;
							}
							
						}else if(quName.indexOf(SZ_CJ)!=-1){
							if(quName.indexOf("出口")!=-1){//出去
								szqy=SZ_GGQY;
							}else if(quName.indexOf("入口")!=-1){
								szqy = SZ_CJ;
							}
						}else if(quName.indexOf(SZ_JS)!=-1){
							if(quName.indexOf("出口")!=-1){//出去
								szqy=SZ_GGQY;
							}else if(quName.indexOf("入口")!=-1){
								szqy = SZ_JS;
							}
						}else{
							szqy = SZ_JQ;
						}
					}
					
				}
			}
			m.put("PBD_SZQY", szqy);
			
			
		}
		*/
		
		
		
		return maps;
	}

	public Map<String, Object> queryDutyConfig(String cusNumber) {
		
		Map<String, Object> map =this.getDao().queryDutyConfig(cusNumber);
		String flag = "";
		if(map != null) {
			if(map.containsKey("FLAG")) {
				flag = ((String) map.get("FLAG")).trim();
			} 
		} 
		Map<String, Object> list = new HashMap<String, Object>();
		list.put("CUS_NUMBER", cusNumber);
		list.put("FLAG", flag);
		return list;
		
	}
	public Page<Map<String, Object>> queryPoliceByDid(HttpServletRequest request, PageRequest pageRequest) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String pbdcusNumber = request.getParameter("pbdcusNumber");
		String pbdDrptmntId = request.getParameter("pbdDrptmntId");

		if(StringUtils.isNotBlank(pbdcusNumber)) {
			paramMap.put("pbdcusNumber", pbdcusNumber);
		}
		
		if(StringUtils.isNotBlank(pbdDrptmntId)) {
			paramMap.put("pbdDrptmntId", pbdDrptmntId);
		}

		return this.getDao().queryPoliceByDid(paramMap, pageRequest);
	}

	@Override
	public Map<String, Object> findPoliceByUserId(HttpServletRequest request) {
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		String pbdcusNumber = request.getParameter("pbdcusNumber");
		String pbdUserId = request.getParameter("pbdUserId");
		
		if(StringUtils.isNotBlank(pbdcusNumber)) {
			paramMap.put("pbdcusNumber", pbdcusNumber);
		}
		
		if(StringUtils.isNotBlank(pbdUserId)) {
			paramMap.put("pbdUserId", pbdUserId);
		}
		return this.getDao().findPoliceByUserId(paramMap);
	}
	@Override
	public List<Map<String, Object>> queryRylb(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		String mjbh = request.getParameter("mjbh");
		String startTime = request.getParameter("startTime");
		String endTime = request.getParameter("endTime");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mjbh", mjbh);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return this.getDao().queryRygl(map);
	}

	@Override
	public List<Map<String, Object>> queryCaramer(HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				String mjbh =  request.getParameter("mjbh");
				String szqu =  URLDecoder.decode(request.getParameter("szqu"),"UTF-8");
				 
				Map<String, Object> mapZfxx = new HashMap<String, Object>();
				mapZfxx.put("mjbh", mjbh);
				//查询罪犯基本信息
				List<Map<String, Object>> listZfxx = this.getDao().querySzjq(mapZfxx);
				String szjy="";
				String szjq="";
				for(int i = 0;i<listZfxx.size();i++){
					Map<String, Object> zfxx = listZfxx.get(i);
					szjq = String.valueOf(zfxx.get("ORGANIZE_CODE"));
					szjy = szjq.substring(0,4);
					break;
				}
				//查询对应线路
				String lxName="";
				if(SZ_ST.equals(szqu)){
					lxName = SZ_ST;
				}else if(SZ_CJ.equals(szqu)){
					lxName = szjq+SZ_CJ;
				}
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("xlmc", lxName);
				List<Map<String, Object>> list = this.getDao().queryXl(map);
				List<Map<String, Object>> listCaramer = new ArrayList<Map<String, Object>>();
				for(int i = 0;i<list.size();i++){
					Map<String, Object> maplx = list.get(i);
					String lxid = (String) maplx.get("ID");
					Map<String, Object> mapSxj = new HashMap<String, Object>();
					mapSxj.put("lxId", lxid);
					listCaramer = this.getDao().openCamare(mapSxj);
					break;
				}
				return listCaramer;
	}
}
