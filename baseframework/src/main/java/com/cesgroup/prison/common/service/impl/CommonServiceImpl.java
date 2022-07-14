package com.cesgroup.prison.common.service.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import com.ces.authsystem.entity.OrgEntity;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.dao.CommonMapper;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.common.entity.CommonEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.service.CommonService;

@Service("commonService")
public class CommonServiceImpl extends BaseDaoService<CommonEntity, String, CommonMapper> implements CommonService {

	@Resource
	private CommonMapper commonMapper;
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	@Override
	public List<Map<String, Object>> findEvidenceForCombobox(Map<String, Object> paramMap) {
		
		return commonMapper.findEvidenceForCombobox(paramMap);
	}

	@Override
	public List<Map<String, Object>> findAreaViewFuncForCombotree(Map<String, Object> paramMap) {
				
		List<Map<String, Object>> areaList = commonMapper.findAreaViewFuncForCombotree(paramMap);
		
		for(Map<String, Object> areaMap : areaList) {
			int childrenNum = Integer.parseInt(areaMap.get("childrenNum") + "");
			if(childrenNum == 0) {					 
				areaMap.put("isParent", false);
				areaMap.put("open", true);
			} else {
				areaMap.put("isParent", true);
				areaMap.put("open", false);
			}
		}
		
		return areaList;
	}

	@Override
	public List<Map<String, Object>> findSyncAreaForCombotree(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> areaList = commonMapper.findSyncAreaForCombotree(paramMap);
		
		for(Map<String, Object> areaMap : areaList) {
			int childrenNum = Integer.parseInt(areaMap.get("childrenNum") + "");
			if(childrenNum == 0) {					 
				areaMap.put("isParent", false);
				areaMap.put("open", true);
			} else {
				areaMap.put("isParent", true);
				areaMap.put("open", false);
			}
		}
		
		return areaList;
	}

	@Override
	public Map<String, Object> findAreaEqualLevelModel(Map<String, Object> paramMap) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> areaList = commonMapper.findArea(paramMap);
		if(areaList != null && areaList.size() > 0) {
			
			paramMap.put("parentAreaId", areaList.get(0).get("ABD_PARENT_AREA_ID"));
			List<Map<String, Object>> modelList = commonMapper.findAreaEqualLevelModel(paramMap);
			
			int abdOrder = Integer.parseInt(areaList.get(0).get("ABD_ORDER") + "");
			List<Map<String, Object>> showModelList = new ArrayList<Map<String, Object>>();
			List<Map<String, Object>> hideModelList = new ArrayList<Map<String, Object>>();
			for(Map<String, Object> modelMap : modelList) {
				if("1".equals(String.valueOf(modelMap.get("MIN_IS_TOP")))) {
					// 楼顶、天花板
					hideModelList.add(modelMap);
				} else {
					int modelOrder = Integer.parseInt(modelMap.get("ABD_ORDER") + "");
					
					if(abdOrder >= modelOrder) {
						showModelList.add(modelMap);
					} else {
						hideModelList.add(modelMap);
					}
				}				
			}
			
			resultMap.put("showModel", showModelList);
			resultMap.put("hideModel", hideModelList);
 		}
		
		return resultMap;
	}

	@Override
	public List<Map<String, Object>> findMasterPlanForCombobox(Map<String, Object> paramMap) throws Exception {
		paramMap.put("cusNumber", AuthSystemFacade.getLoginUserInfo().getCusNumber());
		return commonMapper.findMasterPlanForCombobox(paramMap);
	}

	@Override
	public List<Map<String, Object>> findAllJsxz(Map<String, Object> paramMap) {
		
		return commonMapper.findAllJsxz(paramMap);
	} 
	
	@Override
	public List<Map<String, Object>> findLcjsh(Map<String, Object> paramMap) {
		
		return commonMapper.findLcjsh(paramMap);
	} 
	
	@Override
	public List<AffixEntity> findZfPicInfo(Map<String, Object> paramMap) {
		
		return commonMapper.findZfPicInfo(paramMap);
	}

	@Override
	public List<Map<String, Object>> findShowLabelData(Map<String, Object> paramMap) {
		
		List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
		
		// 获取所有生活场所楼信息-监舍
		List<Map<String, Object>> areaList = commonMapper.findAreaOfJs(paramMap);
		
		// 遍历监舍楼
		for(Map<String, Object> areaMap : areaList) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			paramMap.put("areaId", areaMap.get("ABD_AREA_ID"));
			Map<String, Object> mjslMap = commonMapper.countMjslByAreaId(paramMap);
			resultMap.put("mjsl", mjslMap.get("mjsl"));
			
			List<Map<String, Object>> areaInfoHzCountList = commonMapper.findAreaInfoHzCount(paramMap); 
			long zfsl = 0;
			long sxjsl = 0;
			long bjqsl = 0;
			long djfjsl = 0;
			long mjsl = 0;
			long gbsl = 0;
			long djzjsl = 0;
			for(Map<String, Object> areaInfoHzCount : areaInfoHzCountList) {
			
				zfsl += Long.parseLong(areaInfoHzCount.get("AIC_ZF_COUNT") + "");
				sxjsl += Long.parseLong(areaInfoHzCount.get("AIC_CAMERA_COUNT") + "");
				bjqsl += Long.parseLong(areaInfoHzCount.get("AIC_ALERTOR_COUNT") + "");
				djfjsl += Long.parseLong(areaInfoHzCount.get("AIC_TALK_COUNT") + "");
				mjsl += Long.parseLong(areaInfoHzCount.get("AIC_DOOR_COUNT") + "");
				gbsl += Long.parseLong(areaInfoHzCount.get("AIC_BROADCAST_COUNT") + "");
				djzjsl += Long.parseLong(areaInfoHzCount.get("AIC_TALK_SERVER_COUNT") + "");
			}	
			
			resultMap.put("zfsl", zfsl);
			resultMap.put("sbsl", sxjsl+bjqsl+djfjsl+mjsl+gbsl+djzjsl);
			
			resultMap.put("areaId", areaMap.get("ABD_AREA_ID"));
			resultMap.put("areaName", areaMap.get("ABD_AREA_NAME"));
			resultList.add(resultMap);
		}
		
		return resultList;
	}
	
	@Override
	public List<Map<String, Object>> mjfbList(Map<String, Object> paramMap){
		return commonMapper.mjfbList(paramMap);
	}
	@Override
	public Map<String, Object> mjfbCount(Map<String, Object> paramMap){
		return commonMapper.mjfbCount(paramMap);
	}

	@Override
	public List<Map<String, Object>> findDepartmentByAreaId(Map<String, Object> paramMap) {
		return commonMapper.findDepartmentByAreaId(paramMap);
	}

	@Override
	public void countAreaInfoHz() throws Exception {
		
		// 获取所有监狱
		List<OrgEntity> orgList = AuthSystemFacade.getAllJyInfo();
		Map<String, Map<String, Map<String, Object>>> jyhzMap = new HashMap<String, Map<String, Map<String, Object>>>();
		
		for(OrgEntity orgEntity : orgList) {
			Map<String, Map<String, Object>> hzMap = new HashMap<String, Map<String, Object>>();
			String orgKey = orgEntity.getOrgKey();	// 监狱id
			
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("cusNumber", orgKey);
			
			// 民警数量
			List<Map<String, Object>> mjslList = commonMapper.countMjsl(paramMap);	
			for(Map<String, Object> mjsl : mjslList) {
				Map<String, Object> mjslMap = new HashMap<String, Object>();
				mjslMap.put("mjsl", mjsl.get("mjsl"));
				hzMap.put(mjsl.get("areaId") + "", mjslMap);
			}
			
			// 罪犯数量
			List<Map<String, Object>> zfslList = commonMapper.countZfsl(paramMap);
			for(Map<String, Object> zfsl : zfslList) {
				hzMap.get(zfsl.get("areaId") + "").put("zfsl", zfsl.get("zfsl"));
			}
			
			// 摄像机数量
			List<Map<String, Object>> cameraList = commonMapper.countCamera(paramMap);
			for(Map<String, Object> camera : cameraList) {
				hzMap.get(camera.get("areaId") + "").put("sxjsl", camera.get("sxjsl"));
			}
			
			// 报警器数量
			List<Map<String, Object>> alertorList = commonMapper.countAlertor(paramMap);
			for(Map<String, Object> alertor : alertorList) {
				hzMap.get(alertor.get("areaId") + "").put("bjqsl", alertor.get("bjqsl"));
			}
			
			// 对讲分机数量
			List<Map<String, Object>> talkList = commonMapper.countTalk(paramMap);
			for(Map<String, Object> talk : talkList) {
				hzMap.get(talk.get("areaId") + "").put("djfjsl", talk.get("djfjsl"));
			}
			
			// 对讲主机数量
			List<Map<String, Object>> talkServerList = commonMapper.countTalkServer(paramMap);
			for(Map<String, Object> talkServer : talkServerList) {
				hzMap.get(talkServer.get("areaId") + "").put("djzjsl", talkServer.get("djzjsl"));
			}
			
			// 门禁数量
			List<Map<String, Object>> doorList = commonMapper.countDoor(paramMap);
			for(Map<String, Object> door : doorList) {
				hzMap.get(door.get("areaId") + "").put("doorsl", door.get("doorsl"));
			}
			
			// 广播数量
			List<Map<String, Object>> broadcastList = commonMapper.countBroadcast(paramMap);
			for(Map<String, Object> broadcast : broadcastList) {
				hzMap.get(broadcast.get("areaId") + "").put("gbsl", broadcast.get("gbsl"));
			}
			
			jyhzMap.put(orgKey, hzMap);
		}
		
		Iterator<String> ite = jyhzMap.keySet().iterator();
		List<Object[]> list = new ArrayList<Object[]>();
		while(ite.hasNext()) {
			String cusNumber = ite.next();
			
			Map<String, Map<String, Object>> map = jyhzMap.get(cusNumber);
			Iterator<String> i = map.keySet().iterator();
			while(i.hasNext()) {
				Object[] obj = new Object[11];
				String areaId = i.next();
				Map<String, Object> m = map.get(areaId);
				obj[0] = UUID.randomUUID().toString().replace("-", "");
				obj[1] = cusNumber;
				obj[2] = areaId;
				obj[3] = m.get("mjsl") == null ? 0 : m.get("mjsl");
				obj[4] = m.get("zfsl") == null ? 0 : m.get("zfsl");
				obj[5] = m.get("sxjsl") == null ? 0 : m.get("sxjsl");
				obj[6] = m.get("bjqsl") == null ? 0 : m.get("bjqsl");
				obj[7] = m.get("djfjsl") == null ? 0 : m.get("djfjsl");
				obj[8] = m.get("doorsl") == null ? 0 : m.get("doorsl");
				obj[9] = m.get("gbsl") == null ? 0 : m.get("gbsl");
				obj[10] = m.get("djzjsl") == null ? 0 : m.get("djzjsl");
				
				list.add(obj);
			}
		}
		
		Connection con = null;
		Statement stat = null;
		try {
			con = dataSource.getConnection();
			stat = con.createStatement();
			
			// 删除全部
			stat.addBatch("delete from cds_area_info_count");
			stat.executeBatch();
			con.commit();
			
			stat.clearBatch();
			
			// 批量插入
			for(Object[] o : list) {
				String sql = "insert into cds_area_info_count(id, aic_cus_number, aic_area_id, aic_police_count, aic_zf_count, aic_camera_count, " 
						+ "aic_alertor_count, aic_talk_count, aic_door_count, aic_broadcast_count, aic_talk_server_count) " 
						+ "values('" + o[0] + "', '" + o[1] + "', '" + o[2] + "', " + o[3] + ", " + o[4] + ", " + o[5] + ", " 
						+ "" + o[6] + ", " + o[7] + ", " + o[8] + ", " + o[9] + ", " + o[10] + ")";
						
				stat.addBatch(sql);
			}
			
			stat.executeBatch();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stat != null) {
					stat.close();
				}
					
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Map<String, Object> findAreaInfoHzCount(Map<String, Object> paramMap) {
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		try{
		
		Map<String, Object> mjslMap = commonMapper.countMjslByAreaId(paramMap);
		resultMap.put("mjsl", mjslMap.get("mjsl"));
		
		List<Map<String, Object>> areaInfoHzCountList = commonMapper.findAreaInfoHzCount(paramMap); 
		long zfsl = 0;
		long sxjsl = 0;
		long bjqsl = 0;
		long djfjsl = 0;
		long doorsl = 0;
		long gbsl = 0;
		long djzjsl = 0;
		for(Map<String, Object> areaInfoHzCount : areaInfoHzCountList) {
			zfsl += Long.parseLong(areaInfoHzCount.get("AIC_ZF_COUNT") + "");
			sxjsl += Long.parseLong(areaInfoHzCount.get("AIC_CAMERA_COUNT") + "");
			bjqsl += Long.parseLong(areaInfoHzCount.get("AIC_ALERTOR_COUNT") + "");
			djfjsl += Long.parseLong(areaInfoHzCount.get("AIC_TALK_COUNT") + "");
			doorsl += Long.parseLong(areaInfoHzCount.get("AIC_DOOR_COUNT") + "");
			gbsl += Long.parseLong(areaInfoHzCount.get("AIC_BROADCAST_COUNT") + "");
			djzjsl += Long.parseLong(areaInfoHzCount.get("AIC_TALK_SERVER_COUNT") + "");
		}	
		
		resultMap.put("zfsl", zfsl);
		resultMap.put("sxjsl", sxjsl);
		resultMap.put("bjqsl", bjqsl);
		resultMap.put("djfjsl", djfjsl);
		resultMap.put("doorsl", doorsl);
		resultMap.put("gbsl", gbsl);
		resultMap.put("djzjsl", djzjsl);
		
		}catch(Exception e){
			resultMap.put("mjsl", "0");
			resultMap.put("zfsl", "0");
			resultMap.put("sxjsl", "0");
			resultMap.put("bjqsl", "0");
			resultMap.put("djfjsl", "0");
			resultMap.put("doorsl", "0");
			resultMap.put("gbsl", "0");
			resultMap.put("djzjsl", "0");
		}
		return resultMap;
	}
	
	@Override
	public Map<String, Object> getPeopleAndCarCount(Map<String, Object> paramMap) {
		Map<String, Object> countMap = new HashMap<>();
		// 查询当天实时外来车辆数量
		Integer carCount = commonMapper.getCarCount(paramMap);
		
		// 查询当天实时外来人员数量
		Integer peopleCount = commonMapper.getPeopleCount(paramMap);
		
		countMap.put("carCount", carCount);
		countMap.put("peopleCount", peopleCount);
		return countMap;
	}

	@Override
	public void countPrisonInfoHz() {
		
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> list = commonMapper.countPrisonInfoHz();
		
		for(int i = 0; i < list.size(); i++) {
			Map<String, Object> map = list.get(i);
			Map<String, Object> dataMap = new HashMap<String, Object>();
			
			dataMap.put("id", UUID.randomUUID().toString().replace("-", ""));
			dataMap.put("jy_id", map.get("JY_ID"));
			dataMap.put("jq_id", map.get("JQ_ID"));
			dataMap.put("lb", map.get("LB"));
			dataMap.put("rs", map.get("RS"));
			dataList.add(dataMap);
		}
		
		Connection con = null;
		Statement stat = null;
		try {
			con = dataSource.getConnection();
			stat = con.createStatement();
			
			// 删除全部
			stat.addBatch("delete from cds_prison_info_count");
			stat.executeBatch();
			con.commit();
			
			stat.clearBatch();
			
			// 批量插入
			for(Map<String, Object> data : dataList) {
				String sql = "insert into cds_prison_info_count(id, jy_id, jq_id, lb, rs) " 
						+ "values('" + data.get("id") + "', '" + data.get("jy_id") + "', '" + data.get("jq_id") + "', "
						+ "'" + data.get("lb") + "', " + data.get("rs") + ")";
						
				stat.addBatch(sql);
			}
			
			stat.executeBatch();
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stat != null) {
					stat.close();
				}
					
				if(con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Map<String, Object> getPoliceCountInPrison(Map<String, Object> paramMap) {
		Map<String, Object> countMap = new HashMap<>();
		// 查询当天实时在监民警数量
		Integer policeCountInPrison = commonMapper.getPoliceCountInPrison(paramMap);
		//List<Map<String, Object>> list = commonMapper.getPoliceNo(paramMap);
		countMap.put("policeCountInPrison", policeCountInPrison);
		//countMap.put("policeNoList", list);
		return countMap;
	}
	
	@Override
	public Map<String, Object> getSecurityCheckCount(Map<String, Object> paramMap) {
		Map<String, Object> countMap = new HashMap<>();
		// 查询当天实时在监民警数量
		Integer securityCheckCount = commonMapper.getSecurityCheckCount(paramMap);
		
		countMap.put("securityCheckCount", securityCheckCount);
		return countMap;
	}

	@Override
	public List<Map<String, Object>> findjqByjy(Map<String, Object> paramMap) {

		return commonMapper.findjqByjy(paramMap);
	}

	@Override
	public Map<String, Object> getPoliceCountZaiCe(Map<String, Object> paramMap) {
		Map<String, Object> countMap = new HashMap<>();
		Integer policeCountZaiCe = commonMapper.getPoliceCountZaiCePrison(paramMap);
		//System.out.println("在册民警======================"+policeCountZaiCe);
		countMap.put("policeCountZaiCe",policeCountZaiCe);
		return countMap;
	}

	@Override
	public Map<String, Object> getPoliceCountBeiQin(Map<String, Object> paramMap) {
		Map<String, Object> countMap = new HashMap<>();
		Integer policeCountBeiQin = commonMapper.getPoliceCountBeiQinPrison(paramMap);
		countMap.put("policeCountBeiQin",policeCountBeiQin);
		return countMap;
	}

	@Override
	public Map<String, Object> getPoliceCountInPrisonsj(Map<String, Object> paramMap) {
		Map<String, Object> countMap = new HashMap<>();
		Integer policeCountInPrison = commonMapper.getPoliceCountInPrisonsj(paramMap);
		countMap.put("policeCountInPrison", policeCountInPrison);
		return countMap;
	}

    @Override
    public Map<String, Object> getPoliceCountZaiCeByCusNumberOrDeptCode(Map<String, Object> paramMap) {
        Map<String, Object> countMap = new HashMap<>();
        Integer policeCountZaice = commonMapper.getPoliceCountZaiCeByCusNumberOrDeptCode(paramMap);
        countMap.put("policeCountZaice", policeCountZaice);
        return countMap;
    }
}
