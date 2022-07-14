package com.cesgroup.prison.zfxx.ynjcxx.service.impl;

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
import com.cesgroup.prison.zfxx.ynjcxx.dao.ZfYnjcxxDao;
import com.cesgroup.prison.zfxx.ynjcxx.entity.ZfYnjcxx;
import com.cesgroup.prison.zfxx.ynjcxx.service.ZfYnjcxxService;
@Service("zfYnjcxxService")
@Transactional
public class ZfYnjcxxServiceImpl extends BaseDaoService<ZfYnjcxx, String, ZfYnjcxxDao> implements ZfYnjcxxService {

	@Autowired
	private ZfYnjcxxDao zfYnjcxxDao;
	
	@Override
	public void synchroZfYnjcxx(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_JCXX, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfYnjcxxList = null;
		ZfYnjcxx zfYnjcxx = null;
		List<ZfYnjcxx> list = null;
		if (data != null) {
			list = new ArrayList<ZfYnjcxx>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfYnjcxxList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.RSJCXXSPZT_ZFJCXX);
				if (zfYnjcxxList != null && zfYnjcxxList.size() > 0) {
					for (Map<String, Object> map : zfYnjcxxList) {
						zfYnjcxx = new ZfYnjcxx();
						this.convert(zfYnjcxx, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						list.add(zfYnjcxx);
					}
				}
			}
			this.batchInsert(list);
		}
	}

	@Transactional(readOnly = true)
	private void convert(ZfYnjcxx zfYnjcxx, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfYnjcxx.setcId((String)zfjbxx.get("CId"));
		zfYnjcxx.setcZfbh((String)zfjbxx.get("CZfbh"));
		//狱内奖惩
		zfYnjcxx.setdSpsj((String)map.get("DSpsj"));
		zfYnjcxx.setcJllb((String)map.get("CJllb"));
		zfYnjcxx.setcCflb((String)map.get("CCflb"));
		zfYnjcxx.setcJcyj((String)map.get("CJcyj"));
		zfYnjcxx.setcBz((String)map.get("CBz"));
		//公共字段
		zfYnjcxx.setId(Synchro.getUUID());
		zfYnjcxx.setdUrlTime(urlTime);
		zfYnjcxx.setcCjpc(cjpc);
		zfYnjcxx.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfYnjcxx> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfYnjcxxDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfYnjcxxDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfYnjcxxDao.insertHisBySource();
		zfYnjcxxDao.deleteAll();
	}

}
