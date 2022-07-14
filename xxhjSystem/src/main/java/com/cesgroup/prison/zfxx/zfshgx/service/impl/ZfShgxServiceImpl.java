package com.cesgroup.prison.zfxx.zfshgx.service.impl;

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
import com.cesgroup.prison.zfxx.zfshgx.dao.ZfShgxDao;
import com.cesgroup.prison.zfxx.zfshgx.entity.ZfShgx;
import com.cesgroup.prison.zfxx.zfshgx.service.ZfShgxService;
@Service("zfShgxService")
@Transactional
public class ZfShgxServiceImpl extends BaseDaoService<ZfShgx,String,ZfShgxDao> implements ZfShgxService {
	@Autowired
	private ZfShgxDao zfShgxDao;
	
	@Override
	public void synchroZfShgx(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_SHGX, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfshgxList = null;
		ZfShgx zfshgx = null;
		List<ZfShgx> list = null;
		if (data != null) {
			list = new ArrayList<ZfShgx>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfshgxList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.DEFAULT_ZFSHGX);
				if (zfshgxList != null && zfshgxList.size() > 0) {
					for (Map<String, Object> map : zfshgxList) {
						zfshgx = new ZfShgx();
						this.convert(zfshgx, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						list.add(zfshgx);
					}
				}
			}
			this.batchInsert(list);
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfShgx zfshgx, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfshgx.setcId((String)zfjbxx.get("CId"));
		zfshgx.setcZfbh((String)zfjbxx.get("CZfbh"));
		//社会关系
		zfshgx.setcZjhm((String)map.get("CZjhm"));
		zfshgx.setcZjlb((String)map.get("CZjlb"));
		zfshgx.setcZy((String)map.get("CZy"));
		zfshgx.setcLxdh((String)map.get("CLxdh"));
		zfshgx.setcXm((String)map.get("CXm"));
		zfshgx.setcZzmm((String)map.get("CZzmm"));
		zfshgx.setcXb((String)map.get("CXb"));
		zfshgx.setdCsrq((String)map.get("DCsrq"));
		zfshgx.setnZlxr((String)map.get("NZlxr"));
		zfshgx.setcJtzz((String)map.get("CJtzz"));
		//公共字段
		zfshgx.setId(Synchro.getUUID());
		zfshgx.setdUrlTime(urlTime);
		zfshgx.setcCjpc(cjpc);
		zfshgx.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfShgx> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfShgxDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfShgxDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfShgxDao.insertHisBySource();
		zfShgxDao.deleteAll();
	}

}
