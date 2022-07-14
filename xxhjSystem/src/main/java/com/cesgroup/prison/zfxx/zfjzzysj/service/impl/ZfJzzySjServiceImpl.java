package com.cesgroup.prison.zfxx.zfjzzysj.service.impl;

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
import com.cesgroup.prison.zfxx.zfjzzysj.dao.ZfJzzySjDao;
import com.cesgroup.prison.zfxx.zfjzzysj.entity.ZfJzzySj;
import com.cesgroup.prison.zfxx.zfjzzysj.service.ZfJzzySjService;

@Service("zfJzzySjService")
@Transactional
public class ZfJzzySjServiceImpl extends BaseDaoService<ZfJzzySj, String, ZfJzzySjDao> implements ZfJzzySjService {
	
	@Autowired
	private ZfJzzySjDao zfJzzySjDao;
	@Override
	public void synchroZfJzzySj(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_JZZYSJ, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfJzzySjList = null;
		ZfJzzySj zfJzzySj = null;
		List<ZfJzzySj> list = null;
		if (data != null) {
			list = new ArrayList<ZfJzzySj>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfJzzySjList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.DEFAULT_ZFJZZY);
				if (zfJzzySjList != null && zfJzzySjList.size() > 0) {
					for (Map<String, Object> map : zfJzzySjList) {
						zfJzzySj = new ZfJzzySj();
						this.convert(zfJzzySj, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						list.add(zfJzzySj);
					}
				}
			}
			this.batchInsert(list);
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfJzzySj zfJzzySj, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfJzzySj.setcId((String)zfjbxx.get("CId"));
		zfJzzySj.setcZfbh((String)zfjbxx.get("CZfbh"));
		//罪犯收监情况-狱外就诊/住院（社会医院）
		zfJzzySj.setcYymc((String)map.get("CYymc"));
		zfJzzySj.setcJzlb((String)map.get("CJzlb"));
		zfJzzySj.setcJzzysj((String)map.get("CJzzysj"));
		zfJzzySj.setdJsrq((String)map.get("DJsrq"));
		//公共字段
		zfJzzySj.setId(Synchro.getUUID());
		zfJzzySj.setdUrlTime(urlTime);
		zfJzzySj.setcCjpc(cjpc);
		zfJzzySj.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfJzzySj> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfJzzySjDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfJzzySjDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfJzzySjDao.insertHisBySource();
		zfJzzySjDao.deleteAll();
	}
	
}
