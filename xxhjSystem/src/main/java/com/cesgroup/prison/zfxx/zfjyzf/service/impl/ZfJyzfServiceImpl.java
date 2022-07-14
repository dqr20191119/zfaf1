package com.cesgroup.prison.zfxx.zfjyzf.service.impl;

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
import com.cesgroup.prison.zfxx.zfjyzf.dao.ZfJyzfDao;
import com.cesgroup.prison.zfxx.zfjyzf.entity.ZfJyzf;
import com.cesgroup.prison.zfxx.zfjyzf.service.ZfJyzfService;

@Service("zfJyzfService")
@Transactional
public class ZfJyzfServiceImpl extends BaseDaoService<ZfJyzf, String, ZfJyzfDao> implements ZfJyzfService {
	
	@Autowired
	private ZfJyzfDao zfJyzfDao;
	
	@Override
	public void synchroZfJyzf(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_JYZF, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfJyzfList = null;
		ZfJyzf zfshgx = null;
		List<ZfJyzf> list = null;
		if (data != null) {
			list = new ArrayList<ZfJyzf>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfJyzfList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.DEFAULT_ZFJYXX);
				if (zfJyzfList != null && zfJyzfList.size() > 0) {
					for (Map<String, Object> map : zfJyzfList) {
						zfshgx = new ZfJyzf();
						this.convert(zfshgx, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						list.add(zfshgx);
					}
				}
			}
			this.batchInsert(list);
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfJyzf zfJyzf, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfJyzf.setcId((String)zfjbxx.get("CId"));
		zfJyzf.setcZfbh((String)zfjbxx.get("CZfbh"));
		//狱外寄押
		zfJyzf.setcJcdw((String)map.get("CJcdw"));
		zfJyzf.setcJcbm((String)map.get("CJcbm"));
		zfJyzf.setdJyqr((String)map.get("DJyqr"));
		zfJyzf.setdZzrq((String)map.get("DZzrq"));
		zfJyzf.setcJrdw((String)map.get("CJrdw"));
		zfJyzf.setcJrbm((String)map.get("CJrbm"));
		//公共字段
		zfJyzf.setId(Synchro.getUUID());
		zfJyzf.setdUrlTime(urlTime);
		zfJyzf.setcCjpc(cjpc);
		zfJyzf.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfJyzf> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfJyzfDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfJyzfDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfJyzfDao.insertHisBySource();
		zfJyzfDao.deleteAll();
	}
	
}
