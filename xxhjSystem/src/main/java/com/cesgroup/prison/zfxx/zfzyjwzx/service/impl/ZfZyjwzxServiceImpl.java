package com.cesgroup.prison.zfxx.zfzyjwzx.service.impl;

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
import com.cesgroup.prison.zfxx.zfzyjwzx.dao.ZfZyjwzxDao;
import com.cesgroup.prison.zfxx.zfzyjwzx.entity.ZfZyjwzx;
import com.cesgroup.prison.zfxx.zfzyjwzx.service.ZfZyjwzxService;
@Service("zfZyjwzxService")
@Transactional
public class ZfZyjwzxServiceImpl extends BaseDaoService<ZfZyjwzx, String, ZfZyjwzxDao> implements ZfZyjwzxService {
	
	@Autowired
	private ZfZyjwzxDao zfZyjwzxDao;
	
	@Override
	public void synchroZfZyjwzx(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_ZYJWZX, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfZyjwzxList = null;
		ZfZyjwzx zfZyjwzx = null;
		List<ZfZyjwzx> list = null;
		if (data != null) {
			list = new ArrayList<ZfZyjwzx>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfZyjwzxList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.DEFAULT_ZFJWZX);
				if (zfZyjwzxList != null && zfZyjwzxList.size() > 0) {
					for (Map<String, Object> map : zfZyjwzxList) {
						zfZyjwzx = new ZfZyjwzx();
						this.convert(zfZyjwzx, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						list.add(zfZyjwzx);
					}
				}
			}
			this.batchInsert(list);
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfZyjwzx zfZyjwzx, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfZyjwzx.setcId((String)zfjbxx.get("CId"));
		zfZyjwzx.setcZfbh((String)zfjbxx.get("CZfbh"));
		//暂予监外执行
		zfZyjwzx.setdSjrq((String)map.get("DSjrq"));
		zfZyjwzx.setdZzrq((String)map.get("DZzrq"));
		List<Map<String, Object>> brxxList = (List<Map<String, Object>>)map.get(Synchro.Member.DEFAULT_ZFJWZXBRXX);
		if (brxxList != null && brxxList.size() > 0) {
			Map<String, Object> brxx = brxxList.get(0);
			zfZyjwzx.setcBrxm((String)brxx.get("CBrxm"));
			zfZyjwzx.setcDw((String)brxx.get("CDw"));
			zfZyjwzx.setcBrdh((String)brxx.get("CBrdh"));
			zfZyjwzx.setcZjlx((String)brxx.get("CZjlx"));
			zfZyjwzx.setcZjhm((String)brxx.get("CZjhm"));
		}
		List<Map<String, Object>> bjxxList = (List<Map<String, Object>>)map.get(Synchro.Member.DEFAULT_ZFJWZXBJXX);
		if (bjxxList != null && bjxxList.size() > 0) {
			Map<String, Object> bjxx = bjxxList.get(0);
			zfZyjwzx.setcPcsQh((String)bjxx.get("CPcsQh"));
			zfZyjwzx.setcPcs((String)bjxx.get("CPcs"));
			zfZyjwzx.setcSfsQh((String)bjxx.get("CSfsQh"));
			zfZyjwzx.setcSfs((String)bjxx.get("CSfs"));
		}
		//公共字段
		zfZyjwzx.setId(Synchro.getUUID());
		zfZyjwzx.setdUrlTime(urlTime);
		zfZyjwzx.setcCjpc(cjpc);
		zfZyjwzx.setdCjrq(cjrq);
	}

	private void batchInsert(List<ZfZyjwzx> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfZyjwzxDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfZyjwzxDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfZyjwzxDao.insertHisBySource();
		zfZyjwzxDao.deleteAll();
	}
	
}
