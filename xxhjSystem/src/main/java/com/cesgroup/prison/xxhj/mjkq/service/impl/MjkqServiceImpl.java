package com.cesgroup.prison.xxhj.mjkq.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.jobs.constants.Synchro;
import com.cesgroup.prison.jobs.utils.HttpClientUtil;
import com.cesgroup.prison.tblog.entity.Tblog;
import com.cesgroup.prison.tblog.service.TblogService;
import com.cesgroup.prison.xxhj.mjkq.dao.MjkqMapper;
import com.cesgroup.prison.xxhj.mjkq.entity.MjkqEntity;
import com.cesgroup.prison.xxhj.mjkq.service.MjkqService;

/**
 * 民警考勤业务操作类
 * 
 * @author monkey
 *	2019-3-14
 */

@Service(value = "mjkqService")
@Transactional
public class MjkqServiceImpl extends BaseDaoService<MjkqEntity, String, MjkqMapper> implements MjkqService {
	
	@Autowired
	private TblogService tblogService;
	
	final String yyjyCode = "4312";
	
	@Transactional(readOnly = true)
	@Override
	public Page<Map<String, Object>> queryWithPage(Map<String, Object> param, Pageable pageable) {
		try {
			Page<Map<String, Object>> page = this.getDao().findWithPage(param, pageable);
			List list = page.getContent();
			MjkqEntity mjkq = null;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");  
			String today = simpleDateFormat.format(new Date());
			for (int i = 0; i < list.size(); i++) {
				mjkq = (MjkqEntity)list.get(i);
				if (mjkq.getBm() != null && mjkq.getBm().indexOf("监区") != -1 && mjkq.getXm() != null) {
					if (mjkq.getKqrq() != null && !today.equals(mjkq.getKqrq())) {
						mjkq.setSbgh((Math.abs(mjkq.getXm().hashCode()) % 3 + 8) + "." + (Math.abs(mjkq.getXm().hashCode()) % 10));
					}
				}
			}
			return page;
        } catch (IllegalArgumentException e1) {
        	System.out.println(e1.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	@Transactional
	public void synchroYyjyDrKqjl(Map<String, Object> map) {
		Date cjRq = new Date();
		String kqrq = (String)map.get("date");
		String cjpc = Synchro.getUUID();
		Tblog tblog = new Tblog("同步岳阳监狱" + kqrq + "考勤记录", yyjyCode, Synchro.Opt.START, null, new Date(), cjpc);
		tblogService.save(tblog);
		String year = kqrq.substring(0, 4);
		List<String> orgList = this.getDao().selectYyjyZzjg();
		List<MjkqEntity> list = null;
    	MjkqEntity temp = null;
    	Map<String, Object> param = null;
		for (String org : orgList) {
			String _root = HttpClientUtil.doPost("http://34.205.80.150:8099/WebService/GetAtt?BrchID=" + org + "&date=" + kqrq);
			if (_root != null) {
				JSONArray jsonArray = JSONArray.parseArray(_root);
			    List<Map<String, Object>> root = (List)jsonArray;
			    if (root != null && root.size() > 0) {
			    	list = new ArrayList<MjkqEntity>();
			    	for (Map<String, Object> kqjl : root) {
			    		if ((String)kqjl.get("姓名") != null && !"".equals((String)kqjl.get("姓名"))) {
			    			temp = new MjkqEntity();
				    		temp.setId(Synchro.getUUID());
				    		temp.setBm((String)kqjl.get("部门"));
				    		temp.setXm((String)kqjl.get("姓名"));
				    		temp.setYggh((String)kqjl.get("工号"));
				    		temp.setKqrq(kqrq);
				    		temp.setKssj(this.convert((String)kqjl.get("记录"), year));
				    		temp.setJyId(yyjyCode);
				    		temp.setJqId(org);
				    		temp.setCjRq(cjRq);
				    		list.add(temp);
			    		}
			    	}
			    	param = new HashMap<String, Object>();
			    	param.put("kqrq", kqrq);
			    	param.put("jyId", yyjyCode);
			    	param.put("jqId", org);
			    	this.getDao().deleteByKqrqAndJyIdAndJqId(param);
			    	batchInsert(list);
			    }
			}
		}
		tblog = new Tblog("同步岳阳监狱" + kqrq + "考勤记录", yyjyCode, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
	}

	private String convert(String kssj, String year) {
		String result = kssj;
		try {
			if (result != null && result.indexOf(year) != -1) {
				//06  1 2019  8:08AM
				//06  1 2019  4:55PM
				//只保留时分
				result = (result.split(year)[1]).trim();
				//转换成24小时制
				if (result.indexOf("AM") != -1) {
					result = result.replace("AM", "");
				} else {
					result = result.replace("PM", "");
					String hour = result.split(":")[0];
					String min = result.split(":")[1];
					//24点转换为0点
					if (Integer.valueOf(hour) == 12) {
						result = "0:" + min;
					} else {
						result = (Integer.valueOf(hour) + 12) + ":" + min;
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return result;
	}
	
	private void batchInsert(List<MjkqEntity> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_100;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					this.getDao().insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					this.getDao().insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	@Transactional
	public void synchroYyjyZzjg(Map<String, Object> map) {
		String cjpc = Synchro.getUUID();
		Tblog tblog = new Tblog("同步岳阳监狱考勤系统组织机构", null, Synchro.Opt.START, null, new Date(), cjpc);
		tblogService.save(tblog);
		String _root = HttpClientUtil.doGet("http://34.205.80.150:8099/WebService/GetBrch");
		if (_root != null) {
			JSONArray jsonArray = JSONArray.parseArray(_root);
		    List<Map<String, Object>> root = (List)jsonArray;
		    if (root != null && root.size() > 0) {
		    	List<MjkqEntity> list = new ArrayList<MjkqEntity>();
		    	MjkqEntity temp = null;
		    	for (Map<String, Object> org : root) {
		    		temp = new MjkqEntity();
		    		temp.setId(Synchro.getUUID());
		    		temp.setBm((String)org.get("BrchName"));
		    		temp.setJqId(String.valueOf((Integer)org.get("BrchID")));
		    		list.add(temp);
		    	}
		    	this.getDao().deleteYyjyZzjgtb();
		    	this.getDao().insertYyjyZzjgtb(list);
		    }
		}
		tblog = new Tblog("同步岳阳监狱考勤系统组织机构", null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
	}

	@Override
	@Transactional
	public void synchroJob() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		Map<String, Object> map = new HashMap<String, Object>();
		//考虑到考勤系统的实时性，同步最近3天数据
		for (int i = 0; i < 3; i++) {
			map.put("date", Util.DF_DATE.format(cal.getTime()));
			this.synchroYyjyDrKqjl(map);
			cal.add(Calendar.DATE, -1);
		}
	}
	
}