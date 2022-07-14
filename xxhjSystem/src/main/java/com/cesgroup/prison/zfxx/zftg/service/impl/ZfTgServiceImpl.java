package com.cesgroup.prison.zfxx.zftg.service.impl;

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
import com.cesgroup.prison.zfxx.zftg.dao.ZfTgDao;
import com.cesgroup.prison.zfxx.zftg.entity.ZfTg;
import com.cesgroup.prison.zfxx.zftg.service.ZfTgService;
@Service("zfTgService")
@Transactional
public class ZfTgServiceImpl extends BaseDaoService<ZfTg, String, ZfTgDao> implements ZfTgService {

	@Autowired
	private ZfTgDao zfTgDao;
	
	@Override
	public void synchroZfTg(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_TG, corp, time);
		Map<String, Object> zfjbxx = null;
		ZfTg zfTg = null;
		List<ZfTg> list = null;
		if (data != null) {
			list = new ArrayList<ZfTg>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfTg = new ZfTg();
				this.convert(zfTg, zfjbxx, corp, urlTime, cjpc, cjrq);
				list.add(zfTg);
			}
			this.batchInsert(list);
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfTg zfTg, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfTg.setcId((String)zfjbxx.get("CId"));
		zfTg.setcZfbh((String)zfjbxx.get("CZfbh"));
		//特管登记
		zfTg.setcFgdj((String)zfjbxx.get("CFgdj"));
		zfTg.setcQzfg((String)zfjbxx.get("CQzfg"));
		zfTg.setcSfww((String)zfjbxx.get("CSfww"));
		//公共字段
		zfTg.setId(Synchro.getUUID());
		zfTg.setdUrlTime(urlTime);
		zfTg.setcCjpc(cjpc);
		zfTg.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfTg> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfTgDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfTgDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfTgDao.insertHisBySource();
		zfTgDao.deleteAll();
	}

	
}
