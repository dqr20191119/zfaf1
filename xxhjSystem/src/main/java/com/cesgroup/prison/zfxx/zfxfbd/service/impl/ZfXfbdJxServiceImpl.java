package com.cesgroup.prison.zfxx.zfxfbd.service.impl;

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
import com.cesgroup.prison.zfxx.zfxfbd.dao.ZfXfbdJxDao;
import com.cesgroup.prison.zfxx.zfxfbd.entity.ZfXfbdJx;
import com.cesgroup.prison.zfxx.zfxfbd.service.ZfXfbdJxService;

@Service("zfXfbdJxService")
@Transactional
public class ZfXfbdJxServiceImpl extends BaseDaoService<ZfXfbdJx, String, ZfXfbdJxDao> implements ZfXfbdJxService {

	@Autowired
	private ZfXfbdJxDao zfXfbdJxDao;
	
	@Override
	public void synchroZfXfbdJx(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_XFBDJX, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfXfbdJxList = null;
		ZfXfbdJx zfXfbdJx = null;
		List<ZfXfbdJx> list = null;
		if (data != null) {
			list = new ArrayList<ZfXfbdJx>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfXfbdJxList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.DEFAULT_ZFXFBD);
				if (zfXfbdJxList != null && zfXfbdJxList.size() > 0) {
					for (Map<String, Object> map : zfXfbdJxList) {
						zfXfbdJx = new ZfXfbdJx();
						this.convert(zfXfbdJx, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						list.add(zfXfbdJx);
					}
				}
			}
			this.batchInsert(list);
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfXfbdJx zfXfbdJx, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfXfbdJx.setcId((String)zfjbxx.get("CId"));
		zfXfbdJx.setcZfbh((String)zfjbxx.get("CZfbh"));
		//刑法变动
		zfXfbdJx.setdPcrq((String)map.get("DPcrq"));
		zfXfbdJx.setcBdlb((String)map.get("CBdlb"));
		zfXfbdJx.setcBdfd((String)map.get("CBdfd"));
		zfXfbdJx.setcBdyy((String)map.get("CBdyy"));
		//公共字段
		zfXfbdJx.setId(Synchro.getUUID());
		zfXfbdJx.setdUrlTime(urlTime);
		zfXfbdJx.setcCjpc(cjpc);
		zfXfbdJx.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfXfbdJx> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfXfbdJxDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfXfbdJxDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}

	@Override
	public void backups() {
		zfXfbdJxDao.insertHisBySource();
		zfXfbdJxDao.deleteAll();
	}

}
