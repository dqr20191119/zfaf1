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
import com.cesgroup.prison.zfxx.jfkh.dao.ZfJfkhYhzDao;
import com.cesgroup.prison.zfxx.jfkh.entity.ZfJfkhYhz;
import com.cesgroup.prison.zfxx.jfkh.service.ZfJfkhYhzService;
@Service("zfJfkhYhzService")
@Transactional
public class ZfJfkhYhzServiceImpl extends BaseDaoService<ZfJfkhYhz, String, ZfJfkhYhzDao> implements ZfJfkhYhzService {
	@Autowired
	private ZfJfkhYhzDao zfJfkhYhzDao;
	
	@Override
	public void synchroZfJfkhYhz(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_YHZ, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfJfkhYhzList = null;
		ZfJfkhYhz zfJfkhYhz = null;
		List<ZfJfkhYhz> list = null;
		if (data != null) {
			list = new ArrayList<ZfJfkhYhz>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfJfkhYhzList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.RSYHZMXSPZT_ZFYHZMX);
				if (zfJfkhYhzList != null && zfJfkhYhzList.size() > 0) {
					for (Map<String, Object> map : zfJfkhYhzList) {
						zfJfkhYhz = new ZfJfkhYhz();
						this.convert(zfJfkhYhz, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						list.add(zfJfkhYhz);
					}
				}
			}
			this.batchInsert(list);
		}

	}

	@Transactional(readOnly = true)
	private void convert(ZfJfkhYhz ZfJfkhYhz, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		ZfJfkhYhz.setcId((String)zfjbxx.get("CId"));
		ZfJfkhYhz.setcZfbh((String)zfjbxx.get("CZfbh"));
		//日考核
		ZfJfkhYhz.setnKhnf((String)map.get("NKhnf"));
		ZfJfkhYhz.setnKhyf((String)map.get("NKhyf"));
		if (map.get("NYkhdf") != null) {
			ZfJfkhYhz.setnYkhdf(new BigDecimal((String)map.get("NYkhdf")));
		}
		if (map.get("NLjjf") != null) {
			ZfJfkhYhz.setnLjjf(new BigDecimal((String)map.get("NLjjf")));
		}
		//公共字段
		ZfJfkhYhz.setId(Synchro.getUUID());
		ZfJfkhYhz.setdUrlTime(urlTime);
		ZfJfkhYhz.setcCjpc(cjpc);
		ZfJfkhYhz.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfJfkhYhz> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_100;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfJfkhYhzDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfJfkhYhzDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}

	@Override
	public void backups() {
		zfJfkhYhzDao.insertHisBySource();
		zfJfkhYhzDao.deleteAll();
	}

}
