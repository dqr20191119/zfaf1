package com.cesgroup.prison.zfxx.zfjzzy.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.jobs.constants.Synchro;
import com.cesgroup.prison.jobs.utils.ZfywHttpClientUtil;
import com.cesgroup.prison.zfxx.zfjzzy.dao.ZfJzzyDao;
import com.cesgroup.prison.zfxx.zfjzzy.entity.ZfJzzy;
import com.cesgroup.prison.zfxx.zfjzzy.service.ZfJzzyService;

@Service("zfJzzyService")
@Transactional
public class ZfJzzyServiceImpl extends BaseDaoService<ZfJzzy, String, ZfJzzyDao> implements ZfJzzyService {
	
	@Autowired
	private ZfJzzyDao zfJzzyDao;

	@Override
	public void synchroZfJzzy(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_JZZYWC, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfJzzyList = null;
		ZfJzzy zfJzzy = null;
		List<ZfJzzy> list = null;
		if (data != null) {
			list = new ArrayList<ZfJzzy>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfJzzyList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.DEFAULT_ZFJZZY);
				if (zfJzzyList != null && zfJzzyList.size() > 0) {
					for (Map<String, Object> map : zfJzzyList) {
						zfJzzy = new ZfJzzy();
						this.convert(zfJzzy, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						list.add(zfJzzy);
					}
				}
			}
			this.batchInsert(list);
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfJzzy zfJzzy, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfJzzy.setcId((String)zfjbxx.get("CId"));
		zfJzzy.setcZfbh((String)zfjbxx.get("CZfbh"));
		//罪犯外出情况-狱外就诊/住院（社会医院）	
		zfJzzy.setcYymc((String)map.get("CYymc"));
		zfJzzy.setcJzlb((String)map.get("CJzlb"));
		zfJzzy.setcJzzysj((String)map.get("CJzzysj"));
		zfJzzy.setdJsrq((String)map.get("DJsrq"));
		zfJzzy.setcZrgjXm((String)map.get("CZrgjXm"));
		//公共字段
		zfJzzy.setId(Synchro.getUUID());
		zfJzzy.setdUrlTime(urlTime);
		zfJzzy.setcCjpc(cjpc);
		zfJzzy.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfJzzy> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfJzzyDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfJzzyDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfJzzyDao.insertHisBySource();
		zfJzzyDao.deleteAll();
	}
	
}
