package com.cesgroup.prison.zfxx.zfrzfp.service.impl;

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
import com.cesgroup.prison.zfxx.zfrzfp.dao.ZfRzfpDao;
import com.cesgroup.prison.zfxx.zfrzfp.entity.ZfRzfp;
import com.cesgroup.prison.zfxx.zfrzfp.service.ZfRzfpService;

@Service("zfRzfpService")
@Transactional
public class ZfRzfpServiceImpl extends BaseDaoService<ZfRzfp, String, ZfRzfpDao> implements ZfRzfpService {
	@Autowired
	private ZfRzfpDao zfRzfpDao;

	@Override
	public void synchroZfRzfp(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_RZFP, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfRzfpList = null;
		ZfRzfp zfRzfp = null;
		String rzfplb = "";
		List<ZfRzfp> list = null;
		List<Map<String, Object>> zfRzfplbList = null;
		if (data != null) {
			list = new ArrayList<ZfRzfp>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfRzfpList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.DEFAULT_ZFRZFP);
				for (int i = 0; zfRzfpList != null && i < zfRzfpList.size(); i++) {
					zfRzfplbList = (List<Map<String, Object>>)zfRzfpList.get(i).get(Synchro.Member.DEFAULT_ZFRZFPLB);
					if (zfRzfplbList != null && zfRzfplbList.size() > 0) {
						if (i == 0) {
							rzfplb = (String)zfRzfplbList.get(0).get("CRzfplb");
						} else {
							rzfplb = rzfplb + "/" + (String)zfRzfplbList.get(0).get("CRzfplb");
						}
					}
				}
				if (zfRzfpList != null && zfRzfpList.size() > 0) {
					zfRzfp = new ZfRzfp();
					this.convert(zfRzfp, zfRzfpList.get(0), zfjbxx, rzfplb, corp, urlTime, cjpc, cjrq);
					list.add(zfRzfp);
				}
			}
			this.batchInsert(list);
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfRzfp zfRzfp, Map<String, Object> map, Map<String, Object> zfjbxx, String rzfplb, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfRzfp.setcId((String)zfjbxx.get("CId"));
		zfRzfp.setcZfbh((String)zfjbxx.get("CZfbh"));
		//认罪服判
		zfRzfp.setcRzfplb(rzfplb);
		zfRzfp.setdDjrq((String)map.get("DDjrq"));
		//公共字段
		zfRzfp.setId(Synchro.getUUID());
		zfRzfp.setdUrlTime(urlTime);
		zfRzfp.setcCjpc(cjpc);
		zfRzfp.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfRzfp> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfRzfpDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfRzfpDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfRzfpDao.insertHisBySource();
		zfRzfpDao.deleteAll();
	}

}
