package com.cesgroup.prison.wdgz.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.plugins.authsystem.utils.FacadeUtil;
import com.cesgroup.prison.wdgz.dao.WdgzKhpjMapper;
import com.cesgroup.prison.wdgz.entity.WdgzKhpjEntity;
import com.cesgroup.prison.wdgz.service.WdgzKhpjService;

import ces.sdk.system.bean.OrgInfo;

@Service(value = "wdgzKhpjService")
public class WdgzKhpjServiceImpl extends BaseDaoService<WdgzKhpjEntity, String, WdgzKhpjMapper> implements WdgzKhpjService {
	@Resource
	private WdgzKhpjMapper wdgzKhpjMapper;
	@Override
	public Page<Map<String, Object>> findList(HttpServletRequest request, PageRequest pageRequest) {
		Page<Map<String, Object>> pageList =null;
		try{
			int level = AuthSystemFacade.whatLevelForLoginUser();
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if (level == 2) {
				paramMap.put("cusNumber", user.getCusNumber());
			} else if (level == 3) {
				// 监狱编号
				paramMap.put("cusNumber", user.getCusNumber());
				// 部门编号
				paramMap.put("deptCode", user.getDprtmntCode());
			}
			pageList = wdgzKhpjMapper.findList(paramMap, pageRequest);
			List<Map<String, Object>> list = pageList.getContent();
			if (list != null) {
				for (Map<String, Object> map : list) {
					map.put("JQ_ID", getOrgByCode((String)map.get("JQ_ID")));
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return pageList;
	}

	private String getOrgByCode(String code) {
		try {
			OrgInfo org = FacadeUtil.getOrgInfoFacade().getOrgInfoByKey(code);
			return org.getOrganizeName();
		} catch (Exception e) {
			return null;
		}
	}
	
	@Override
	public Map<String, Object> getWg(String zfjbxxId) {
		// TODO Auto-generated method stub
		Map mapzjg = new HashMap();
		try{
			
		
		Map map = new HashMap();
		map.put("zfjbxxId", zfjbxxId);
		List list = wdgzKhpjMapper.getAll(map);
		String zbId  = "";
		String zfXm = "";
		String jyName = "";
		String jqName = "";
		List listpm = new ArrayList();
		List listdf = new ArrayList();//得分
		List listkf = new ArrayList();//扣分
		List list2p = new ArrayList();
		List listTWz = new ArrayList();
		
		for(int i=0;i<list.size();i++){
			Map mapzb = (Map)list.get(i);
			zbId = mapzb.get("ID").toString();
			zfXm = mapzb.get("ZF_XM").toString();
			jyName = mapzb.get("JY_NAME").toString();
			jqName = mapzb.get("JQ_NAME").toString();
			break;
		}
		
		Map map2 = new HashMap();
		map2.put("khpjId", zbId);
		List list2 = wdgzKhpjMapper.getAllMX(map2);
		Double zzgzJcf = 0.0;
		Double zzgzJlf = 0.0;
		Double jggzJcf = 0.0;
		Double jggzJlf = 0.0;
		Double jygzJcf = 0.0;
		Double jygzJlf = 0.0;
		Double whgzJcf = 0.0;
		Double whgzJlf = 0.0;
		Double ldgzJcf = 0.0;
		Double ldgzJlf = 0.0;
		for(int i = 0;i<list2.size();i++){
			Map mapMx = (Map)list2.get(i);
			String WDGZ_TYPE_CODE = mapMx.get("WDGZ_TYPE_CODE").toString();
			String WDGZ_TYPE_NAME = mapMx.get("WDGZ_TYPE_NAME").toString();
			String WDGZ_MX_TYPE = mapMx.get("WDGZ_MX_TYPE").toString();
			String WDGZ_MX_NAME = mapMx.get("WDGZ_MX_NAME").toString();
			String WDGZ_MX = mapMx.get("WDGZ_MX").toString();
			String WDGZ_MX_FZ = mapMx.get("WDGZ_MX_FZ").toString();
			Double fz = Double.valueOf(WDGZ_MX_FZ);
			if("ZZGZ".equals(WDGZ_TYPE_CODE)){
				if("JCF".equals(WDGZ_MX_TYPE)){
					zzgzJcf = zzgzJcf+fz;
				}else if("JLF".equals(WDGZ_MX_TYPE)){
					zzgzJlf = zzgzJlf+fz;
				}
			}else if("JGGZ".equals(WDGZ_TYPE_CODE)){
				if("JCF".equals(WDGZ_MX_TYPE)){
					jggzJcf = jggzJcf+fz;
				}else if("JLF".equals(WDGZ_MX_TYPE)){
					jggzJlf = jggzJlf+fz;
				}
			}else if("JYGZ".equals(WDGZ_TYPE_CODE)){
				if("JCF".equals(WDGZ_MX_TYPE)){
					jygzJcf = jygzJcf+fz;
				}else if("JLF".equals(WDGZ_MX_TYPE)){
					jygzJlf = jygzJlf+fz;
				}
			}else if("WHGZ".equals(WDGZ_TYPE_CODE)){
				if("JCF".equals(WDGZ_MX_TYPE)){
					whgzJcf = whgzJcf+fz;
				}else if("JLF".equals(WDGZ_MX_TYPE)){
					whgzJlf = whgzJlf+fz;
				}
			}else if("LDGZ".equals(WDGZ_TYPE_CODE)){
				if("JCF".equals(WDGZ_MX_TYPE)){
					ldgzJcf = ldgzJcf+fz;
				}else if("JLF".equals(WDGZ_MX_TYPE)){
					ldgzJlf = ldgzJlf+fz;
				}
				
			} 
		}
		Double zf =   zzgzJcf + zzgzJlf + jggzJcf + jggzJlf + jygzJcf + jygzJlf +  whgzJcf +
		  whgzJlf +  ldgzJcf +  ldgzJlf ;
		
		Double zzgzZf = zzgzJcf + zzgzJlf;
		Double jggzZf = jggzJcf + jggzJlf;
		Double jygzZf = jygzJcf + jygzJlf;
		Double whgzZf = whgzJcf + whgzJlf;
		Double ldgzZf = ldgzJcf + ldgzJlf;
		
		Double zzgzYsf = 10.0;
		Double jggzYsf = 5.0;
		Double jygzYsf = 5.0;
		Double whgzYsf = 5.0;
		Double ldgzYsf = 5.0;
		
		Map map3 = new HashMap();
		map3.put("jqName", jqName);
		/*
		List list3 = wdgzKhpjMapper.getAllpm(map3);
		int g = 1;
		int u = 1;
		for(int i = 0;i<list3.size();i++){
			Map mappm = (Map) list3.get(i);
			String KHPJ_ID = (String) mappm.get("KHPJ_ID");
			BigDecimal b = (BigDecimal) mappm.get("ZFHZ");
			String zfhz = b.toString();
			if(i>0){
				Map mapzx = (Map) list3.get(i-1);
				BigDecimal b1 = (BigDecimal) mapzx.get("ZFHZ");
				String zfhz2 = b1.toString();
				if(Double.valueOf(zfhz2).compareTo(Double.valueOf(zfhz))==0){
					
				}else{
					g=u;
				}
			}
			u++;
			String pm = "第"+g+"名";
			if(KHPJ_ID.equals(zbId)){
				 Map mappm1 = new HashMap();
				 mappm1.put("pm", pm);
				 listpm.add(mappm1);
			}
		}*/
		
		 Map mappm1 = new HashMap();
		 mappm1.put("pm", "第1名");
		 listpm.add(mappm1);
		Map mapp2 = new HashMap();
		 mapp2.put("value", zzgzZf);
		 mapp2.put("name", "政治改造得分");
		 list2p.add(mapp2);
		 Map map21 = new HashMap();
		 map21.put("value", zzgzYsf-zzgzZf);
		 map21.put("name", "政治改造扣分");
		 list2p.add(map21);
		 listdf.add(zzgzZf);
		 listkf.add(zzgzYsf-zzgzZf);
		 
		 
		 Map mapp3p = new HashMap();
		 mapp3p.put("value", jggzZf);
		 mapp3p.put("name", "监管改造得分");
		 list2p.add(mapp3p);
		 Map map31 = new HashMap();
		 map31.put("value", jggzYsf-jggzZf);
		 map31.put("name", "监管改造扣分");
		 list2.add(map31);
		 listdf.add(jggzZf);
		 listkf.add(jggzYsf-jggzZf);
		 
		 Map map4 = new HashMap();
		 map4.put("value", jygzZf);
		 map4.put("name", "教育改造得分");
		 list2p.add(map4);
		 Map map41 = new HashMap();
		 map41.put("value", jygzYsf-jygzZf);
		 map41.put("name", "教育改造扣分");
		 list2p.add(map41);
		 listdf.add(jygzZf);
		 listkf.add(jygzYsf-jygzZf);
		 
		 Map map5 = new HashMap();
		 map5.put("value", whgzZf);
		 map5.put("name", "文化改造得分");
		 list2.add(map5);
		 Map map51 = new HashMap();
		 map51.put("value", whgzYsf-whgzZf);
		 map51.put("name", "文化改造扣分");
		 list2.add(map51);
		 listdf.add(whgzZf);
		 listkf.add(whgzYsf-whgzZf);
		 
		 
		 Map map6 = new HashMap();
		 map6.put("value", ldgzZf);
		 map6.put("name", "劳动改造得分");
		 list2.add(map6);
		 Map map61 = new HashMap();
		 map61.put("value", ldgzYsf-ldgzZf);
		 map61.put("name", "劳动改造扣分");
		 list2.add(map61);
		 listdf.add(ldgzZf);
		 listkf.add(ldgzYsf-ldgzZf);
		
		
		
		mapzjg.put("id", zbId);//罪犯姓名
		mapzjg.put("zfXm", zfXm);//罪犯姓名
		mapzjg.put("jyName", jyName);//监狱名称
		mapzjg.put("jqName", jqName);//监区名称
		mapzjg.put("qbzf", zf);//总分
		
		//政治改造分数
		mapzjg.put("zzgzJcf", zzgzJcf);
		mapzjg.put("zzgzJlf", zzgzJlf);
		mapzjg.put("zzgzZf", zzgzZf);
		mapzjg.put("zzgzYsf", zzgzYsf);
		mapzjg.put("zzgzKf", zzgzYsf-zzgzZf);
		//监管改造分数
		mapzjg.put("jggzJcf", jggzJcf);
		mapzjg.put("jggzJlf", jggzJlf);
		mapzjg.put("jggzZf", jggzZf);
		mapzjg.put("jggzYsf", jggzYsf);
		mapzjg.put("jggzKf", jggzYsf-jggzZf);
		//教育改造分数
		mapzjg.put("jygzJcf", jygzJcf);
		mapzjg.put("jygzJlf", jygzJlf);
		mapzjg.put("jygzZf", jygzZf);
		mapzjg.put("jygzYsf", jygzYsf);
		mapzjg.put("jygzKf", jygzYsf-jygzZf);
		//文化改造分数
		mapzjg.put("whgzJcf", whgzJcf);
		mapzjg.put("whgzJlf", whgzJlf);
		mapzjg.put("whgzZf", whgzZf);
		mapzjg.put("whgzYsf", whgzYsf);
		mapzjg.put("whgzKf", whgzYsf-whgzZf);
		//劳动改造分数
		mapzjg.put("ldgzJcf", ldgzJcf);
		mapzjg.put("ldgzJlf", ldgzJlf);
		mapzjg.put("ldgzZf", ldgzZf);
		mapzjg.put("ldgzYsf", ldgzYsf);
		mapzjg.put("ldgzKf", ldgzYsf-ldgzZf);
		
		

		 List listname = new ArrayList();
		 listname.add("政治改造得分");
		 listTWz.add("政治改造");
		 listname.add("政治改造扣分");
		 listname.add("监管改造得分");
		 listTWz.add("监管改造");
		 listname.add("监管改造扣分");
		 listname.add("教育改造得分");
		 listTWz.add("教育改造");
		 listname.add("教育改造扣分");
		 listname.add("文化改造得分");
		 listTWz.add("文化改造");
		 listname.add("文化改造扣分");
		 listname.add("劳动改造得分");
		 listTWz.add("劳动改造");
		 listname.add("劳动改造扣分");
		 mapzjg.put("listname", listname);
		 mapzjg.put("listTWz", listTWz);
		 mapzjg.put("listmx", list2p);
		 mapzjg.put("listPm",listpm);
		 mapzjg.put("listzf",listdf);
		 mapzjg.put("listkf",listkf);
		}catch(Exception e){
			e.printStackTrace();
		}
		return mapzjg;
	}

	@Override
	public Map<String, Object> getMx(String zbId, String lx) {
		// TODO Auto-generated method stub
		List listjg = new ArrayList();
		Map map = new HashMap();
		map.put("id", zbId);
		List list = wdgzKhpjMapper.getAllById(map);
		 
		String zfXm = "";
		String jyName = "";
		String jqName = "";
		List listpm = new ArrayList();
		List listdf = new ArrayList();//得分
		List listkf = new ArrayList();//扣分
		List list2p = new ArrayList();
		List listTWz = new ArrayList();
		
		for(int i=0;i<list.size();i++){
			Map mapzb = (Map)list.get(i);
			zbId = mapzb.get("ID").toString();
			zfXm = mapzb.get("ZF_XM").toString();
			jyName = mapzb.get("JY_NAME").toString();
			jqName = mapzb.get("JQ_NAME").toString();
			break;
		}
		
		Map map2 = new HashMap();
		map2.put("khpjId", zbId);
		List list2 = wdgzKhpjMapper.getAllMX(map2);
		Double zzgzJcf = 0.0;
		Double zzgzJlf = 0.0;
		Double jggzJcf = 0.0;
		Double jggzJlf = 0.0;
		Double jygzJcf = 0.0;
		Double jygzJlf = 0.0;
		Double whgzJcf = 0.0;
		Double whgzJlf = 0.0;
		Double ldgzJcf = 0.0;
		Double ldgzJlf = 0.0;
		for(int i = 0;i<list2.size();i++){
			Map mapMx = (Map)list2.get(i);
			String WDGZ_TYPE_CODE = mapMx.get("WDGZ_TYPE_CODE").toString();
			String WDGZ_TYPE_NAME = mapMx.get("WDGZ_TYPE_NAME").toString();
			String WDGZ_MX_TYPE = mapMx.get("WDGZ_MX_TYPE").toString();
			String WDGZ_MX_NAME = mapMx.get("WDGZ_MX_NAME").toString();
			String WDGZ_MX = mapMx.get("WDGZ_MX").toString();
			String WDGZ_MX_FZ = mapMx.get("WDGZ_MX_FZ").toString();
			Double fz = Double.valueOf(WDGZ_MX_FZ);
			if("ZZGZ".equals(WDGZ_TYPE_CODE)){
				if("JCF".equals(WDGZ_MX_TYPE)){
					zzgzJcf = zzgzJcf+fz;
				}else if("JLF".equals(WDGZ_MX_TYPE)){
					zzgzJlf = zzgzJlf+fz;
				}
			}else if("JGGZ".equals(WDGZ_TYPE_CODE)){
				if("JCF".equals(WDGZ_MX_TYPE)){
					jggzJcf = jggzJcf+fz;
				}else if("JLF".equals(WDGZ_MX_TYPE)){
					jggzJlf = jggzJlf+fz;
				}
			}else if("JYGZ".equals(WDGZ_TYPE_CODE)){
				if("JCF".equals(WDGZ_MX_TYPE)){
					jygzJcf = jygzJcf+fz;
				}else if("JLF".equals(WDGZ_MX_TYPE)){
					jygzJlf = jygzJlf+fz;
				}
			}else if("WHGZ".equals(WDGZ_TYPE_CODE)){
				if("JCF".equals(WDGZ_MX_TYPE)){
					whgzJcf = whgzJcf+fz;
				}else if("JLF".equals(WDGZ_MX_TYPE)){
					whgzJlf = whgzJlf+fz;
				}
			}else if("LDGZ".equals(WDGZ_TYPE_CODE)){
				if("JCF".equals(WDGZ_MX_TYPE)){
					ldgzJcf = ldgzJcf+fz;
				}else if("JLF".equals(WDGZ_MX_TYPE)){
					ldgzJlf = ldgzJlf+fz;
				}
			} 
			if(lx.equals(WDGZ_TYPE_CODE)){
				listjg.add(mapMx);
			}
		}
		Double zf =   zzgzJcf + zzgzJlf + jggzJcf + jggzJlf + jygzJcf + jygzJlf +  whgzJcf +
				  whgzJlf +  ldgzJcf +  ldgzJlf ;
				
				Double zzgzZf = zzgzJcf + zzgzJlf;
				Double jggzZf = jggzJcf + jggzJlf;
				Double jygzZf = jygzJcf + jygzJlf;
				Double whgzZf = whgzJcf + whgzJlf;
				Double ldgzZf = ldgzJcf + ldgzJlf;
				
				Double zzgzYsf = 10.0;
				Double jggzYsf = 5.0;
				Double jygzYsf = 5.0;
				Double whgzYsf = 5.0;
				Double ldgzYsf = 5.0;
		

				Map mapzjg = new HashMap();
				mapzjg.put("id", zbId);//罪犯姓名
				mapzjg.put("zfXm", zfXm);//罪犯姓名
				mapzjg.put("jyName", jyName);//监狱名称
				mapzjg.put("jqName", jqName);//监区名称
				mapzjg.put("qbzf", zf);//总分
				
				//政治改造分数
				mapzjg.put("zzgzJcf", zzgzJcf);
				mapzjg.put("zzgzJlf", zzgzJlf);
				mapzjg.put("zzgzZf", zzgzZf);
				mapzjg.put("zzgzYsf", zzgzYsf);
				mapzjg.put("zzgzKf", zzgzYsf-zzgzZf);
				//监管改造分数
				mapzjg.put("jggzJcf", jggzJcf);
				mapzjg.put("jggzJlf", jggzJlf);
				mapzjg.put("jggzZf", jggzZf);
				mapzjg.put("jggzYsf", jggzYsf);
				mapzjg.put("jggzKf", jggzYsf-jggzZf);
				//教育改造分数
				mapzjg.put("jygzJcf", jygzJcf);
				mapzjg.put("jygzJlf", jygzJlf);
				mapzjg.put("jygzZf", jygzZf);
				mapzjg.put("jygzYsf", jygzYsf);
				mapzjg.put("jygzKf", jygzYsf-jygzZf);
				//文化改造分数
				mapzjg.put("whgzJcf", whgzJcf);
				mapzjg.put("whgzJlf", whgzJlf);
				mapzjg.put("whgzZf", whgzZf);
				mapzjg.put("whgzYsf", whgzYsf);
				mapzjg.put("whgzKf", whgzYsf-whgzZf);
				//劳动改造分数
				mapzjg.put("ldgzJcf", ldgzJcf);
				mapzjg.put("ldgzJlf", ldgzJlf);
				mapzjg.put("ldgzZf", ldgzZf);
				mapzjg.put("ldgzYsf", ldgzYsf);
				mapzjg.put("ldgzKf", ldgzYsf-ldgzZf);
				
				mapzjg.put("listjg", listjg);
		
		return mapzjg;
	}

}
