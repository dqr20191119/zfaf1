package com.cesgroup.prison.zfxx.zfzdzf.service.impl;

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
import com.cesgroup.prison.zfxx.zfzdzf.dao.ZfZdzfDao;
import com.cesgroup.prison.zfxx.zfzdzf.entity.ZfZdzf;
import com.cesgroup.prison.zfxx.zfzdzf.service.ZfZdzfService;

@Service("zfZdzfService")
@Transactional
public class ZfZdzfServiceImpl extends BaseDaoService<ZfZdzf, String, ZfZdzfDao> implements ZfZdzfService {
	
	@Autowired
	private ZfZdzfDao zfZdzfDao;
	
	@Override
	public void synchroZfZdzf(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_ZDZF, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfZdzfList = null;
		ZfZdzf zfZdzf = null;
		List<ZfZdzf> list = null;
		if (data != null) {
			list = new ArrayList<ZfZdzf>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfZdzfList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.RSWWZKZDZF_ZFWWZK);
				if (zfZdzfList != null && zfZdzfList.size() > 0) {
					for (Map<String, Object> map : zfZdzfList) {
						zfZdzf = new ZfZdzf();
						this.convert(zfZdzf, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						list.add(zfZdzf);
					}
				}
			}
			this.batchInsert(list);
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfZdzf zfZdzf, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfZdzf.setcId((String)zfjbxx.get("CId"));
		zfZdzf.setcZfbh((String)zfjbxx.get("CZfbh"));
		//重点罪犯
		zfZdzf.setdPzrq((String)map.get("DPzrq"));
		zfZdzf.setcZkjb((String)map.get("CZkjb"));
		zfZdzf.setdCbrq((String)map.get("DCbrq"));
		zfZdzf.setcZkyy((String)map.get("CZkyy"));
		zfZdzf.setdCxpzrq((String)map.get("DCxpzrq"));
		//公共字段
		zfZdzf.setId(Synchro.getUUID());
		zfZdzf.setdUrlTime(urlTime);
		zfZdzf.setcCjpc(cjpc);
		zfZdzf.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfZdzf> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfZdzfDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfZdzfDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfZdzfDao.insertHisBySource();
		zfZdzfDao.deleteAll();
	}
	
}
