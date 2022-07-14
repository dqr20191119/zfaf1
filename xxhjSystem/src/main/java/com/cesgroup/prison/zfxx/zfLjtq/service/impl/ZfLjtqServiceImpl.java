package com.cesgroup.prison.zfxx.zfLjtq.service.impl;

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
import com.cesgroup.prison.zfxx.zfLjtq.dao.ZfLjtqDao;
import com.cesgroup.prison.zfxx.zfLjtq.entity.ZfLjtq;
import com.cesgroup.prison.zfxx.zfLjtq.service.ZfLjtqService;

@Service("zfLjtqService")
@Transactional
public class ZfLjtqServiceImpl extends BaseDaoService<ZfLjtq, String, ZfLjtqDao> implements ZfLjtqService {
	
	@Autowired
	private ZfLjtqDao zfLjtqDao;

	@Override
	public void synchroZfLjtq(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_LJTQSJ, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfLjtqList = null;
		ZfLjtq zfLjtq = null;
		List<ZfLjtq> list = null;
		if (data != null) {
			list = new ArrayList<ZfLjtq>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfLjtqList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.DEFAULT_ZFLJTQ);
				if (zfLjtqList != null && zfLjtqList.size() > 0) {
					for (Map<String, Object> map : zfLjtqList) {
						zfLjtq = new ZfLjtq();
						this.convert(zfLjtq, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						list.add(zfLjtq);
					}
				}
			}
			this.batchInsert(list);
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfLjtq zfLjtq, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfLjtq.setcId((String)zfjbxx.get("CId"));
		zfLjtq.setcZfbh((String)zfjbxx.get("CZfbh"));
		//罪犯收监情况-离监探亲
		zfLjtq.setcZjlb((String)map.get("CZjlb"));
		zfLjtq.setdJqqr((String)map.get("DJqqr"));
		zfLjtq.setdGjrq((String)map.get("DGjrq"));
		//公共字段
		zfLjtq.setId(Synchro.getUUID());
		zfLjtq.setdUrlTime(urlTime);
		zfLjtq.setcCjpc(cjpc);
		zfLjtq.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfLjtq> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfLjtqDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfLjtqDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfLjtqDao.insertHisBySource();
		zfLjtqDao.deleteAll();
	}
	
}
