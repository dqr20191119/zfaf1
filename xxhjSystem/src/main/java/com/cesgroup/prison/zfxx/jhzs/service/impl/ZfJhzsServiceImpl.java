package com.cesgroup.prison.zfxx.jhzs.service.impl;

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
import com.cesgroup.prison.zfxx.jhzs.dao.ZfJhzsDao;
import com.cesgroup.prison.zfxx.jhzs.entity.ZfJhzs;
import com.cesgroup.prison.zfxx.jhzs.service.ZfJhzsService;


@Service("zfJhzsService")
@Transactional
public class ZfJhzsServiceImpl extends BaseDaoService<ZfJhzs, String, ZfJhzsDao> implements ZfJhzsService {
	
	@Autowired
	private ZfJhzsDao zfJhzsDao;
	
	@Override
	public void synchroZfJhzs(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_JHZS, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfJhzsList = null;
		ZfJhzs zfJhzs = null;
		List<ZfJhzs> list = null;
		if (data != null) {
			list = new ArrayList<ZfJhzs>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfJhzsList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.DEFAULT_ZFJHZS);
				if (zfJhzsList != null && zfJhzsList.size() > 0) {
					for (Map<String, Object> map : zfJhzsList) {
						zfJhzs = new ZfJhzs();
						this.convert(zfJhzs, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						list.add(zfJhzs);
					}
				}
			}
			this.batchInsert(list);
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfJhzs zfJhzs, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfJhzs.setcId((String)zfjbxx.get("CId"));
		zfJhzs.setcZfbh((String)zfjbxx.get("CZfbh"));
		//解回再审
		zfJhzs.setcGhzh((String)map.get("CGhzh"));
		zfJhzs.setcThdw((String)map.get("CThdw"));
		zfJhzs.setcThqx((String)map.get("CThqx"));
		zfJhzs.setdThrq((String)map.get("DThrq"));
		zfJhzs.setdZzrq((String)map.get("DZzrq"));
		zfJhzs.setcJbgjMc((String)map.get("CJbgjMc"));
		zfJhzs.setcLxdh((String)map.get("CLxdh"));
		zfJhzs.setcThyy((String)map.get("CThyy"));
		//公共字段
		zfJhzs.setId(Synchro.getUUID());
		zfJhzs.setdUrlTime(urlTime);
		zfJhzs.setcCjpc(cjpc);
		zfJhzs.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfJhzs> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfJhzsDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfJhzsDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfJhzsDao.insertHisBySource();
		zfJhzsDao.deleteAll();
	}
	
}
