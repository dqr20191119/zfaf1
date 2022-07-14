package com.cesgroup.prison.xxhj.sy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.xxhj.jnmj.service.JnmjService;
import com.cesgroup.prison.xxhj.jryf.dao.JryfMapper;
import com.cesgroup.prison.xxhj.sy.dao.SyMapper;
import com.cesgroup.prison.xxhj.sy.entity.Sy;
import com.cesgroup.prison.xxhj.zfjbxx.dao.ZfjbxxMapper;
import com.cesgroup.prison.zbgl.zbcx.service.ZbcxService;

/**
 * Description: 首页业务处理类
 * @author lincoln.cheng
 *
 */
@Service("syService")
public class SyServiceImpl extends BaseDaoService<Sy, String, SyMapper> implements SyService {
	
	@Resource
	private SyMapper syMapper;
	
	@Resource
	private JryfMapper jryfMapper;
	
	@Resource
	private ZfjbxxMapper zfjbxxMapper;
	
	@Resource
	private ZbcxService zbcxService;
	
	@Resource
	private JnmjService jnmjService;
	
	public Map<String, Object> countHomeMeunCount(String cusNumber,String cdjJobCode) {
		
		Map<String, Object> con = jnmjService.queryDutyConfig(cusNumber);
		String config = "";
		if(con != null) {
			if(con.containsKey("FLAG")) {
				config =(String) con.get("FLAG");
			} 
		}
	    Map<String, Object> resultMap = new HashMap<String, Object>();
	    String[] jobCodeList = cdjJobCode.split("/");
	    //查询今日值班民警
	    for(int i = 0; i< jobCodeList.length;i++) {
	    	Map<String, Object> map = new HashMap<String, Object>();		
	  		map.put("cusNumber", cusNumber);
	  		map.put("cdjJobCode", jobCodeList[i]);
	  		
	  		Map<String, Object> list = zbcxService.findTodayDutyPolice(map);
	  		resultMap.put(jobCodeList[i], list);
	    }
	    //查询监内民警人数
		Map<String, Object> map1 = new HashMap<String, Object>();		
		map1.put("cusNumber", cusNumber);
		map1.put("config", config);
		Map<String, Object> list1 = jnmjService.querySYInsidePoliceCount(map1);
		list1.put("config", config);
		
		resultMap.put("current_insidePoliceCount", list1);
		
		//查询实时罪犯人数
		Map<String, Object> map2 = new HashMap<String, Object>();		
		if(cusNumber != "" && !"".equals(cusNumber)) {
			map2.put("cusNumber", cusNumber);
			map2.put("drpmntId",'0');
		} else {
			map2.put("cusNumber", '0');
		}
		
		List<Map<String, Object>> list2= zfjbxxMapper.queryXJPrisonerCount(map2);
		resultMap.put("current_PrisonerCount", list2);
		
		/*Map<String, Object> map5 = new HashMap<String, Object>();		

		if(cusNumber != "" && !"".equals(cusNumber)) {
			map5.put("cusNumber", cusNumber);
			Date beginTime = new Date();
			beginTime.setHours(0);
	        beginTime.setMinutes(0);
			map5.put("beginTime", beginTime);
		}
		Map<String, Object> list5 = CommonController.getPeopleAndCarCount(map5);*/

		/*	
		//查询外出住院人数
		Map<String, Object> map3 = new HashMap<String, Object>();		
		map3.put("para", cusNumber);
		List<Map<String, Object>> list3= jryfMapper.hospitalCount(map3);
		resultMap.put("current_hospitalCount", list3);
		
		//查询今日外来车辆
		Map<String, Object> map4 = new HashMap<String, Object>();		
		map4.put("para", cusNumber);
		List<Map<String, Object>> list4= homeMeunMapper.queryForeignCarCount(map4);
		resultMap.put("foreignCarCount", list4);
		
		//查询今日外来人员
		Map<String, Object> map5 = new HashMap<String, Object>();		
		map5.put("para", cusNumber);
		List<Map<String, Object>> list5= homeMeunMapper.queryForeignPeopleCount(map4);
		resultMap.put("foreignPeopleCount", list5);
		
		//查询重点罪犯分布
		//邪教犯、法轮功犯、全能神犯
		Map<String, Object> maps = new HashMap<String, Object>();	
		Map<String, Object> maps1 = new HashMap<String, Object>();		
		maps1.put("para", cusNumber);
		List<Map<String, Object>> list6= syMapper.queryKeynotePrisoner1(maps1);
		maps.put("keynotePrisoner1", list6);
		
		//查询当前其它邪教犯、维族犯数、外籍犯
		Map<String, Object> maps2 = new HashMap<String, Object>();		
		maps2.put("para", cusNumber);
		List<Map<String, Object>> list7= syMapper.queryKeynotePrisoner2(maps2);
		maps.put("keynotePrisoner2", list7);
		
		//查询当前其它邪教犯、维族犯数、外籍犯
		Map<String, Object> maps3 = new HashMap<String, Object>();		
		maps3.put("para", cusNumber);
		List<Map<String, Object>> list8= syMapper.queryKeynotePrisoner3(maps3);
		maps.put("keynotePrisoner3", list8);
		resultMap.put("keynotePrisoner", maps);
		*/
		return resultMap;
	}
}