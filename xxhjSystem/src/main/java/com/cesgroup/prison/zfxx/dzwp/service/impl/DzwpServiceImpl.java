package com.cesgroup.prison.zfxx.dzwp.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.jobs.constants.Synchro;
import com.cesgroup.prison.jobs.utils.ZfywHttpClientUtil;
import com.cesgroup.prison.zfxx.dzwp.dao.DzwpJfzcDao;
import com.cesgroup.prison.zfxx.dzwp.dao.DzwpStzcDao;
import com.cesgroup.prison.zfxx.dzwp.dao.DzwpYsjgDao;
import com.cesgroup.prison.zfxx.dzwp.entity.DzwpJfzc;
import com.cesgroup.prison.zfxx.dzwp.entity.DzwpStzc;
import com.cesgroup.prison.zfxx.dzwp.entity.DzwpYsjg;
import com.cesgroup.prison.zfxx.dzwp.service.DzwpService;
import com.cesgroup.prison.zfxx.dzwp.util.DzwpUtil;

@Service("dzwpService")
@Transactional
public class DzwpServiceImpl extends BaseDaoService<DzwpStzc, String, DzwpStzcDao> implements DzwpService {

	@Autowired
	private DzwpJfzcDao dzwpJfzcDao;
	@Autowired
	private DzwpStzcDao dzwpStzcDao;
	@Autowired
	private DzwpYsjgDao dzwpYsjgDao;
	
	public static BigDecimal getValue(Object obj) {
		BigDecimal value = new BigDecimal(0);
		if (obj instanceof BigDecimal) {
			value = ((BigDecimal)obj);
		} else if (obj instanceof Double) {
			value = new BigDecimal((Double)obj);
		} else if (obj instanceof Float) {
			value = new BigDecimal((Float)obj);
		} else if (obj instanceof Long) {
			value = new BigDecimal((Long)obj);
		} else if (obj instanceof Integer) {
			value = new BigDecimal((Integer)obj);
		} else if (obj instanceof String) {
			value = new BigDecimal((String)obj);
		}
		return value;
	}
	
	@Override
	public void synchroDzwp(String jyId, String cjpc) {
		Map<String, Object> map = ZfywHttpClientUtil.getDzwp("2", DzwpUtil.getPrisonAreaId(jyId), "1m");
		Date cjrq = new Date();
		//罪犯食堂经费支出
		List<Map<String, Object>> expenseList = (List<Map<String, Object>>)map.get("expenseList");
		BigDecimal base = new BigDecimal(10000);
		if (expenseList != null) {
			List<DzwpJfzc> list = new ArrayList<DzwpJfzc>();
			DzwpJfzc dzwpJfzc = null;
			for (Map<String, Object> tmp : expenseList) {
				dzwpJfzc = new DzwpJfzc();
				dzwpJfzc.setId(Synchro.getUUID());
				dzwpJfzc.setCjpc(cjpc);
				dzwpJfzc.setCjrq(cjrq);
				dzwpJfzc.setTypes((String)tmp.get("type"));
				dzwpJfzc.setFunds(getValue(tmp.get("Funds")).divide(base, 2, BigDecimal.ROUND_HALF_UP));
				dzwpJfzc.setJyId(jyId);
				list.add(dzwpJfzc);
			}
			batchInsertDzwpJfzc(list);
		}
		
		//罪犯饮食结构
		List<Map<String, Object>> dietList = (List<Map<String, Object>>)map.get("dietList");
		if (dietList != null) {
			List<DzwpYsjg> list = new ArrayList<DzwpYsjg>();
			DzwpYsjg dzwpYsjg = null;
			for (Map<String, Object> tmp : dietList) {
				dzwpYsjg = new DzwpYsjg();
				dzwpYsjg.setId(Synchro.getUUID());
				dzwpYsjg.setCjpc(cjpc);
				dzwpYsjg.setCjrq(cjrq);
				dzwpYsjg.setTypes((String)tmp.get("Type"));
				dzwpYsjg.setAutualpro(getValue(tmp.get("ActualProportion")));
				dzwpYsjg.setStanpro(getValue(tmp.get("StandardProportion")));
				dzwpYsjg.setJyId(jyId);
				list.add(dzwpYsjg);
			}
			batchInsertDzwpYsjg(list);
		}
		
		//罪犯食堂采买种类
		List<Map<String, Object>> purchaseList = (List<Map<String, Object>>)map.get("purchaseList");
		if (purchaseList != null) {
			List<DzwpStzc> list = new ArrayList<DzwpStzc>();
			DzwpStzc dzwpStzc = null;
			for (Map<String, Object> tmp : purchaseList) {
				dzwpStzc = new DzwpStzc();
				dzwpStzc.setId(Synchro.getUUID());
				dzwpStzc.setCjpc(cjpc);
				dzwpStzc.setCjrq(cjrq);
				dzwpStzc.setTypes((String)tmp.get("Name"));
				dzwpStzc.setAmount(getValue(tmp.get("Amount")));
				dzwpStzc.setJyId(jyId);
				list.add(dzwpStzc);
			}
			batchInsertDzwpStzc(list);
		}
	}
	
	private void batchInsertDzwpJfzc(List<DzwpJfzc> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					dzwpJfzcDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					dzwpJfzcDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	private void batchInsertDzwpYsjg(List<DzwpYsjg> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					dzwpYsjgDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					dzwpYsjgDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	private void batchInsertDzwpStzc(List<DzwpStzc> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					dzwpStzcDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					dzwpStzcDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}

}
