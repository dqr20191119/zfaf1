package com.cesgroup.prison.jobs.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.base.entity.StringIDEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.PropertiesUtil;
import com.cesgroup.prison.httpclient.util.SzbbUtil;
import com.cesgroup.prison.jobs.constants.Synchro;
import com.cesgroup.prison.jobs.dao.BjJobDao;
import com.cesgroup.prison.jobs.service.BjJobService;
import com.cesgroup.prison.jobs.service.ZfywTbService;
import com.cesgroup.prison.jobs.utils.HttpClientUtil;
import com.cesgroup.prison.szbb.service.FxpgService;
import com.cesgroup.prison.szbb.service.LdglService;
import com.cesgroup.prison.szbb.service.RcgkService;
import com.cesgroup.prison.szbb.service.ZszhService;
import com.cesgroup.prison.tblog.entity.Tblog;
import com.cesgroup.prison.tblog.service.TblogService;
import com.cesgroup.prison.ysjg.service.YsjgService;
import com.cesgroup.prison.zfxx.zfstat.service.ZfStatService;
import com.cesgroup.prison.zfxx.zfstat.service.ZfStatZyService;

import ces.sdk.util.StringUtil;
import net.sf.json.JSONObject;

@Service("bjJobService")
public class BjJobServiceImpl  extends BaseDaoService<StringIDEntity, String, BjJobDao> implements BjJobService {

	@Autowired
	private ZfStatService zfStatService;
	@Autowired
	private ZfStatZyService zfStatZyService;
	@Autowired
	private YsjgService ysjgService;
	@Autowired
	private ZszhService zszhService;
	@Autowired
	private FxpgService fxpgService;
	@Autowired
	private LdglService ldglService;
	@Autowired
	private RcgkService rcgkService;
	@Autowired
	private ZfywTbService zfywTbService;
	@Autowired
	private TblogService tblogService;
	
	/**
	 * 获取需要corps的监狱编号（作废）
	 */
	//private static String jyCode = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.corps.jycode");
	/**
	 * 获取全部监狱的corps
	 */
	private static String corpsUrl = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.all.corps.url"); //http://192.168.8.39:6789/uim-api/api/corps/
	
	/**
	 * 获取凯辉情况下对应的监狱和监狱编码
	 */
	private static String khzzbm = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.kh.ysjg.zzbm");
	
	/**
	 * 获取所有监狱编码和监狱对应的corps
	 * @param isJq true需要监区数据，false 不需要
	 * @return
	 */
	public List<Map<String, Object>> getOrgList(boolean isJq) {
		List<Map<String ,Object>> listJycodeCoprs = new ArrayList<Map<String ,Object>>();
		//最新获取监狱、监区coprs方法
		List<Map<String ,Object>> list= Synchro.init();
		for(int i = 0;i<list.size();i++){
			Map<String ,Object> map = list.get(i);
			String jyCode = (String) map.get("jyCode");
			String corps = (String) map.get("corps");
			List listjqCo = new ArrayList();
			//获取jqjson
			if(isJq){//是否需要获取对应监区数据
				String jqUrl = corpsUrl+corps+"/depts";
				String jqJson = HttpClientUtil.doGet(jqUrl);
				JSONObject  jasonObject2 = JSONObject.fromObject(jqJson);
				Map<String ,Object> map2 = (Map<String ,Object>)jasonObject2;
				List<Map<String ,Object>> list2 = (List<Map<String ,Object>>) map2.get("data");
				for(int j=0;j<list2.size();j++){
					Map<String ,Object> mapList2 = (Map<String ,Object>) list2.get(j);
					Map<String ,Object> mapjy2= (Map<String ,Object>) mapList2.get("ext");
					String jqName = (String) mapjy2.get("virtualCorpid");
					String jqCorps = (String) mapjy2.get("CGuid");
					Map<String ,Object> mapjg = new HashMap<String ,Object>();
					if(StringUtil.isNotBlank(jqName)){
						if(jqName.contains("监区")){
							mapjg.put("jqName", jqName);
							mapjg.put("jqCorps", jqCorps);
							listjqCo.add(mapjg);
						}
					}
				}
			}
			Map<String ,Object> mapjg = new HashMap<String ,Object>();
			mapjg.put("jyCode", jyCode);
			mapjg.put("corps", corps);
			mapjg.put("jqList", listjqCo);
			listJycodeCoprs.add(mapjg);
			
		}
		return listJycodeCoprs;
	/*	
		
		//获取所有监狱code,进入list集合
		List<String> listJy = new ArrayList<String>();
		if(jyCode.length()>0){
			String jyCodes[] = jyCode.split(",");
			for(int i =0;i<jyCodes.length;i++){
				listJy.add(jyCodes[i]);
			}
		}
		List<Map<String ,Object>> listJycodeCoprs = new ArrayList<Map<String ,Object>>();
		String a = HttpClientUtil.doGet(corpsUrl);
		JSONObject  jasonObject = JSONObject.fromObject(a);
		Map<String ,Object> map = (Map<String ,Object>)jasonObject;
		List<Map<String ,Object>> list = (List<Map<String ,Object>>) map.get("data");
		for(int i=0;i<list.size();i++){
			Map<String ,Object> mapList = (Map<String ,Object>) list.get(i);
			Map<String ,Object> mapjy= (Map<String ,Object>) mapList.get("ext");
			String jyCode = (String) mapjy.get("CGbm");
			String corps = (String) mapjy.get("CGuid");
			List listjqCo = new ArrayList();
			//获取jqjson
			if(isJq){//是否需要获取对应监区数据
				String jqUrl = corpsUrl+corps+"/depts";
				String jqJson = HttpClientUtil.doGet(jqUrl);
				JSONObject  jasonObject2 = JSONObject.fromObject(jqJson);
				Map<String ,Object> map2 = (Map<String ,Object>)jasonObject2;
				List<Map<String ,Object>> list2 = (List<Map<String ,Object>>) map2.get("data");
				for(int j=0;j<list2.size();j++){
					Map<String ,Object> mapList2 = (Map<String ,Object>) list2.get(j);
					Map<String ,Object> mapjy2= (Map<String ,Object>) mapList2.get("ext");
					String jqName = (String) mapjy2.get("virtualCorpid");
					String jqCorps = (String) mapjy2.get("CGuid");
					Map<String ,Object> mapjg = new HashMap<String ,Object>();
					if(StringUtil.isNotBlank(jqName)){
						if(jqName.contains("监区")){
							mapjg.put("jqName", jqName);
							mapjg.put("jqCorps", jqCorps);
							listjqCo.add(mapjg);
						}
					}
				}
			}
			
			if(listJy.contains(jyCode)){
				Map<String ,Object> mapjg = new HashMap<String ,Object>();
				mapjg.put("jyCode", jyCode);
				mapjg.put("corps", corps);
				mapjg.put("jqList", listjqCo);
				listJycodeCoprs.add(mapjg);
			}
		}
		*/
		/*return listJycodeCoprs;*/
	}
	
	
	@Override
	public void findById() {

		System.out.println("我就是我，不一样的定时任务");
	}
	/**
	 * 定时任务获取华宇数据 预计1天一次
	 */
	@Override
	@Transactional
	public void getHY() {
		try{
			//罪犯统计数据
			List<Map<String ,Object>>  listJyCorsp = getOrgList(true);
			for(int i = 0;i<listJyCorsp.size();i++){
				Map<String,Object> mapJyCorps = listJyCorsp.get(i);
				String jyId = (String) mapJyCorps.get("jyCode");
				String corps = (String) mapJyCorps.get("corps");
				/**
				 * 监狱级
				 * */
				
				//统计外出罪犯 
				zfStatService.synchroZfStat(jyId, "", "corp", corps, "");
				//统计暂押罪犯
				zfStatZyService.synchroZfStatZy(jyId, "", "corp", corps, "");
				//统计罪犯调动
				zfStatZyService.synchroZfStatZfdd(jyId, "", "corp", corps, "");
				//在押罪犯
				zfStatZyService.synchroZfStatZyqk(jyId, "", "corp", corps, "");
				//罪犯性别
				zfStatZyService.synchroZfStatZyXb(jyId, "", "corp", corps, "");
				//罪犯年龄
				zfStatZyService.synchroZfStatZyNl(jyId, "", "corp", corps, "");
				//统计罪犯罪名
				zfStatZyService.synchroZfStatZyZm(jyId, "", "corp", corps, "");
				//统计罪犯原判刑期
				zfStatZyService.synchroZfStatZyYpxq(jyId, "", "corp", corps, "");
				//统计罪犯剩余刑期
				zfStatZyService.synchroZfStatZySyxq(jyId, "", "corp", corps, "");
				//关押
				zfStatZyService.synchroZfStatZyJygyqk(jyId, "", "corp", corps, "");
				//改造情况
				zfStatZyService.synchroZfStatZyJygzqk(jyId, "", "corp", corps, "");
				
				
				List listJq = (List) mapJyCorps.get("jqList");
				for(int y = 0;y<listJq.size();y++){
					Map map = (Map) listJq.get(y);
					String jqName = (String) map.get("jqName");
					String jqCorps = (String) map.get("jqCorps");
					/**
					 * 监区级
					 * */
					//统计外出罪犯 
					zfStatService.synchroZfStat(jyId, jqName, "dept", corps, jqCorps);
					//统计暂押罪犯
					zfStatZyService.synchroZfStatZy(jyId, jqName, "dept", corps, jqCorps);
					//统计罪犯调动
					zfStatZyService.synchroZfStatZfdd(jyId, jqName, "dept", corps, jqCorps);
					//在押罪犯
					zfStatZyService.synchroZfStatZyqk(jyId, jqName, "dept", corps, jqCorps);
					//罪犯性别
					zfStatZyService.synchroZfStatZyXb(jyId, jqName, "dept", corps, jqCorps);
					//罪犯年龄
					zfStatZyService.synchroZfStatZyNl(jyId, jqName, "dept", corps, jqCorps);
					//统计罪犯罪名
					zfStatZyService.synchroZfStatZyZm(jyId, jqName, "dept", corps, jqCorps);
					//统计罪犯原判刑期
					zfStatZyService.synchroZfStatZyYpxq(jyId, jqName, "dept", corps, jqCorps);
					//统计罪犯剩余刑期
					zfStatZyService.synchroZfStatZySyxq(jyId, jqName, "dept", corps, jqCorps);
					//关押
					zfStatZyService.synchroZfStatZyJygyqk(jyId, jqName, "dept", corps, jqCorps);
					//改造情况
					zfStatZyService.synchroZfStatZyJygzqk(jyId, jqName, "dept", corps, jqCorps);
					
				}
			}
			//监狱关押能力
			zfStatZyService.synchroZfStatZyJyGynl(listJyCorsp);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * 定时任务获取华宇罪犯业务数据 预计1天一次
	 */
	@Override
	@Transactional
	public void getHyZfyw() {
		System.out.println("========zfyw start========");
		try {
			List<Map<String, Object>> jyList = getOrgList(false);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.synchroZfyw(jyList);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("========zfyw end========");
	}
	
	@Override
	@Transactional
	public void getHyZfzp() {
		System.out.println("========zfzp start========");
		try {
			zfywTbService.synchroZfzp();
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("========zfzp end========");
	}
	
	/**
	 * 获取凯辉数据  预计一个月1次，分布取3个月 6个月1年的数据
	 */
	@Override
	@Transactional
	public void getKH() {
		// TODO Auto-generated method stub
		String khbm[]= khzzbm.split("@");
		System.out.println(khbm);
		String type = "2";//1民警，2：服刑人员
		for(int i = 0;i<khbm.length;i++){
			String jyId = khbm[i].split(",")[0];
			String secCode = khbm[i].split(",")[1];
			ysjgService.getYsjg(type, secCode, "3m", jyId);//3个月
			ysjgService.getYsjg(type, secCode, "6m", jyId);//6个月
			ysjgService.getYsjg(type, secCode, "1y", jyId);//1年
		}
	}


	/**
	 * 推送数字冰雹
	 */
	@Override
	@Transactional
	public void tsSzbb() {
		System.out.println("========>开始推送数字冰雹");
		String cjpc = Synchro.getUUID();
		Tblog tblog = new Tblog(Synchro.Zfyw.SZBB, null, Synchro.Opt.START, null, new Date(), cjpc);
		tblogService.save(tblog);
		
		List<String> jyList = SzbbUtil.initSzbb();
		for (String jyCode : jyList) {
			System.out.println("==========>" + jyCode);
			//数字冰雹：1.1.日常管控-今日值班
			try {
				rcgkService.getJrzbList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：1.2.日常管控-今日概况
			try {
				rcgkService.getJrgk(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：1.3.日常管控-区域管控
			try {
				rcgkService.getQygkList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：1.4.日常管控-重点罪犯
			try {
				rcgkService.getZdzfList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：1.5.日常管控-大门出入
			try {
				rcgkService.getDmcrList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：1.6.日常管控-人脸识别
			try {
				rcgkService.getRlsbList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：1.7.日常管控-周界安防
			try {
				rcgkService.getZjafList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：1.8.日常管控-手机管控
			try {
				rcgkService.getSjgkList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：1.9.日常管控-押解任务
			try {
				rcgkService.getYwyjrwList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：1.10.日常管控-押解车辆
			try {
				rcgkService.getYwyjclwzList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}		
			tblog = new Tblog(Synchro.Zfyw.SZBB_RCGK + "#" + jyCode, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
			tblogService.save(tblog);
			
			//数字冰雹：2.1.战时指挥 - 现场警员
			try {
				zszhService.getXcjyList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：2.2.战时指挥 - 待援警员
			try {
				zszhService.getDyjyList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：2.3.战时指挥 - 待命车辆
			try {
				zszhService.getDmclList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：2.4.战时指挥 - 警务装备
			try {
				zszhService.getJwzbList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：2.5.战时指挥 - 事件信息
			try {
				zszhService.getSjxx(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：2.6.战时指挥 - 事件流程
			try {
				zszhService.getSjlcList(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：2.7.战时指挥 - 当前流程
			try {
				zszhService.getDqlc(jyCode);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			//数字冰雹：2.8.战时指挥 - 涉事部位
			try {
				zszhService.getSsbm(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tblog = new Tblog(Synchro.Zfyw.SZBB_ZSZH + "#" + jyCode, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
			tblogService.save(tblog);
			
			//数字冰雹：3.1.风险评估-监狱排名
			try {
				fxpgService.getJypm(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：3.2.风险评估-风险清单
			try {
				fxpgService.getFxqd(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：3.3.风险评估-频发风险
			try {
				fxpgService.getPffx(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：3.4.风险评估-风险等级
			try {
				fxpgService.getFxdj(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：3.5.风险评估-全部风险点
			try {
				fxpgService.getQbfxd(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：3.6.风险评估-当前发生风险点
			try {
				fxpgService.getDqfxd(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：3.7.风险评估-当前发生风险详情
			try {
				fxpgService.getDqfxxq(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：3.8.风险评估-网格风险
			try {
				fxpgService.getWgfx(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：3.9.风险评估-网格风险评估
			try {
				fxpgService.getWgfxpg(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tblog = new Tblog(Synchro.Zfyw.SZBB_FXPG + "#" + jyCode, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
			tblogService.save(tblog);
			
			//数字冰雹：3.10.风险评估-网格扣分
			try {
				fxpgService.getWgkf(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//数字冰雹：4.1.领导管理驾驶舱 - 罪犯变化
			try {
				ldglService.getZfbh(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：4.2.领导管理驾驶舱 - 罪犯年龄对比
			try {
				ldglService.getZfnldb(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：4.3.领导管理驾驶舱 - 罪犯类型
			try {
				ldglService.getZflx(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：4.4.领导管理驾驶舱 - 队伍建设
			try {
				ldglService.getDwjs(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：4.5.领导管理驾驶舱 - 个别谈话
			try {
				ldglService.getGbth(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：4.6.领导管理驾驶舱 - 亲情电话
			try {
				ldglService.getQqdh(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：4.7.领导管理驾驶舱 - 刑罚执行
			try {
				ldglService.getXfzx(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：4.8.领导管理驾驶舱 - 劳动工具
			try {
				ldglService.getLdgj(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：4.9.领导管理驾驶舱 - 大宗物品
			try {
				ldglService.getDzwp(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：4.10.领导管理驾驶舱 - 就医统计
			try {
				ldglService.getJytj(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			//数字冰雹：4.11.领导管理驾驶舱 - 组织结构
			try {
				ldglService.getZzjg(jyCode);
			} catch (Exception e) {
				e.printStackTrace();
			}
			tblog = new Tblog(Synchro.Zfyw.SZBB_LDGL + "#" + jyCode, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println(jyCode + "========>推送完成");
		}
		tblog = new Tblog(Synchro.Zfyw.SZBB, null, Synchro.Opt.END, null, new Date(), cjpc);
		tblogService.save(tblog);
		System.out.println("========>结束推送数字冰雹");
	}


	@Override
	@Transactional
	public void getHyJfkh() {
		System.out.println("========jfkh start========");
		try {
			List<Map<String, Object>> jyList = getOrgList(false);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.synchroJfkh(jyList);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("========jfkh end========");
	}
	
}
