package com.cesgroup.prison.zfxx.jfkh.service.impl;

import java.math.BigDecimal;
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
import com.cesgroup.prison.zfxx.jfkh.dao.ZfJfkhRkhDao;
import com.cesgroup.prison.zfxx.jfkh.entity.ZfJfkhRkh;
import com.cesgroup.prison.zfxx.jfkh.service.ZfJfkhRkhService;
@Service("zfJfkhRkhService")
@Transactional
public class ZfJfkhRkhServiceImpl extends BaseDaoService<ZfJfkhRkh, String, ZfJfkhRkhDao> implements ZfJfkhRkhService {
	@Autowired
	private ZfJfkhRkhDao zfJfkhRkhDao;
	
	@Override
	public void synchroZfJfkhRkh(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_RKH, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfJfkhRkhList = null;
		ZfJfkhRkh zfJfkhRkh = null;
		List<ZfJfkhRkh> list = null;
		if (data != null) {
			list = new ArrayList<ZfJfkhRkh>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfJfkhRkhList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.RSRJKFJZSPZT_ZFRJKFJZ);
				if (zfJfkhRkhList != null && zfJfkhRkhList.size() > 0) {
					for (Map<String, Object> map : zfJfkhRkhList) {
						zfJfkhRkh = new ZfJfkhRkh();
						this.convert(zfJfkhRkh, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						list.add(zfJfkhRkh);
					}
				}
			}
			this.batchInsert(list);
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfJfkhRkh ZfJfkhRkh, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		ZfJfkhRkh.setcId((String)zfjbxx.get("CId"));
		ZfJfkhRkh.setcZfbh((String)zfjbxx.get("CZfbh"));
		//日考核
		ZfJfkhRkh.setdKhrq((String)map.get("DKhrq"));
		if (map.get("NJkf") != null) {
			ZfJfkhRkh.setnJkf(new BigDecimal((String)map.get("NJkf")));
		}
		//公共字段
		ZfJfkhRkh.setId(Synchro.getUUID());
		ZfJfkhRkh.setdUrlTime(urlTime);
		ZfJfkhRkh.setcCjpc(cjpc);
		ZfJfkhRkh.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfJfkhRkh> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_100;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfJfkhRkhDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfJfkhRkhDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}

	@Override
	public void backups() {
		zfJfkhRkhDao.insertHisBySource();
		zfJfkhRkhDao.deleteAll();
	}

}
