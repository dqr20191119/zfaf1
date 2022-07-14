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
import com.cesgroup.prison.zfxx.zfxfbd.dao.ZfXfbdJsDao;
import com.cesgroup.prison.zfxx.zfxfbd.entity.ZfXfbdJs;
import com.cesgroup.prison.zfxx.zfxfbd.service.ZfXfbdJsService;

@Service("zfXfbdJsService")
@Transactional
public class ZfXfbdJsServiceImpl extends BaseDaoService<ZfXfbdJs, String, ZfXfbdJsDao> implements ZfXfbdJsService {

	@Autowired
	private ZfXfbdJsDao zfXfbdJsDao;
	
	@Override
	public void synchroZfXfbdJs(String corp, String jyCode, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_XFBDJS, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfXfbdJsList = null;
		List<Map<String, Object>> zfZyztbdList = null;
		ZfXfbdJs zfXfbdJs = null;
		List<ZfXfbdJs> list = null;
		if (data != null) {
			list = new ArrayList<ZfXfbdJs>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfXfbdJsList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.DEFAULT_ZFXFBD);
				zfZyztbdList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.RSZYZTBDTIMELINE_ZFZYZTBD);
				if (zfXfbdJsList != null && zfXfbdJsList.size() > 0 && zfZyztbdList != null && zfZyztbdList.size() > 0) {
					zfXfbdJs = new ZfXfbdJs();
					this.convert(zfXfbdJs, zfXfbdJsList.get(0), zfZyztbdList.get(0), zfjbxx, corp, jyCode, urlTime, cjpc, cjrq);
					list.add(zfXfbdJs);
				}
			}
			this.batchInsert(list);
		}
	}

	@Transactional(readOnly = true)
	private void convert(ZfXfbdJs zfXfbdJs, Map<String, Object> xfbd, Map<String, Object> zyzt, Map<String, Object> zfjbxx, String corp, String jyCode, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfXfbdJs.setcId((String)zfjbxx.get("CId"));
		zfXfbdJs.setcZfbh((String)zfjbxx.get("CZfbh"));
		//假释
		zfXfbdJs.setdLjrq((String)zyzt.get("DLjrq"));
		zfXfbdJs.setdCbrq((String)xfbd.get("DCbrq"));
		zfXfbdJs.setdZxrq((String)xfbd.get("DZxrq"));
		zfXfbdJs.setcJyId(jyCode);
		//公共字段
		zfXfbdJs.setId(Synchro.getUUID());
		zfXfbdJs.setdUrlTime(urlTime);
		zfXfbdJs.setcCjpc(cjpc);
		zfXfbdJs.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfXfbdJs> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfXfbdJsDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfXfbdJsDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfXfbdJsDao.insertHisBySource();
		zfXfbdJsDao.deleteAll();
	}

}
