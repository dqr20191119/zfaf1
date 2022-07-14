package com.cesgroup.prison.zfxx.zflbc.service.impl;

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
import com.cesgroup.prison.zfxx.zflbc.dao.ZfLbcDao;
import com.cesgroup.prison.zfxx.zflbc.entity.ZfLbc;
import com.cesgroup.prison.zfxx.zflbc.service.ZfLbcService;

@Service("zfLbcService")
@Transactional
public class ZfLbcServiceImpl extends BaseDaoService<ZfLbc,String,ZfLbcDao> implements ZfLbcService {
	
	@Autowired
	private ZfLbcDao zfLbcDao;
	
	@Override
	public void synchroZfLbc(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_LBC, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zflbcList = null;
		ZfLbc zfLbc = null;
		List<ZfLbc> list = null;
		if (data != null) {
			list = new ArrayList<ZfLbc>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zflbcList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.RSLBC_DEFAULT_ZFLBC);
				if (zflbcList != null && zflbcList.size() > 0) {
					for (Map<String, Object> map : zflbcList) {
						zfLbc = new ZfLbc();
						this.convert(zfLbc, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						list.add(zfLbc);
					}
				}
			}
			this.batchInsert(list);
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfLbc zflbc, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zflbc.setcId((String)zfjbxx.get("CId"));
		zflbc.setcZfbh((String)zfjbxx.get("CZfbh"));
		//老病残
		zflbc.setdJdrq((String)map.get("DJdrq"));
		zflbc.setcLb((String)map.get("CLb"));
		zflbc.setcQk((String)map.get("CQk"));
		zflbc.setcBclb((String)map.get("CBclb"));
		zflbc.setdDjrq((String)map.get("DDjrq"));
		//公共字段
		zflbc.setId(Synchro.getUUID());
		zflbc.setdUrlTime(urlTime);
		zflbc.setcCjpc(cjpc);
		zflbc.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfLbc> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfLbcDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfLbcDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfLbcDao.insertHisBySource();
		zfLbcDao.deleteAll();
	}
	
}
