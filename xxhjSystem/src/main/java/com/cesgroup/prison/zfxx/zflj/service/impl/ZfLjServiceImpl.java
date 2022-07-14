package com.cesgroup.prison.zfxx.zflj.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.jobs.constants.Synchro;
import com.cesgroup.prison.jobs.utils.ZfywHttpClientUtil;
import com.cesgroup.prison.zfxx.zflj.dao.ZfLjDao;
import com.cesgroup.prison.zfxx.zflj.entity.ZfLj;
import com.cesgroup.prison.zfxx.zflj.service.ZfLjService;

/**
 * Description: 离监业务访问接口实现类
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
@Service("zfLjService")
@Transactional
public class ZfLjServiceImpl extends BaseDaoService<ZfLj, String, ZfLjDao> implements ZfLjService {
	
	@Override
	public void synchroZfLj(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_LJ, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfLjList = null;
		ZfLj zfLj = null;
		ZfLj query = null;
		long count = 0;
		if (data != null) {
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfLjList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.RSZYZBDTIMELINE_ZFZYZTBD);
				if (zfLjList != null && zfLjList.size() > 0) {
					for (Map<String, Object> map : zfLjList) {
						zfLj = new ZfLj();
						this.convert(zfLj, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						query = new ZfLj();
						query.setcZfbh(zfLj.getcZfbh());
						//根据罪犯编号判断离监数据不能重复同步
						count = this.getDao().selectCountByEntity(query);
						if (count == 0) {
							this.getDao().insert(zfLj);
						}
					}
				}
			}
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfLj zfLj, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfLj.setcId((String)zfjbxx.get("CId"));
		zfLj.setcZfbh((String)zfjbxx.get("CZfbh"));
		//离监业务
		zfLj.setdLjrq(Util.toDate((String)map.get("DLjrq"), Util.DF_DOT_DATE));
		zfLj.setcLjlb((String)map.get("CLjlb"));
		zfLj.setcQx((String)map.get("CQx"));
		zfLj.setcQxQh((String)map.get("CQxQh"));
		zfLj.setcQxMx((String)map.get("CQxMx"));
		//公共字段
		zfLj.setId(Synchro.getUUID());
		zfLj.setdUrlTime(urlTime);
		zfLj.setcCjpc(cjpc);
		zfLj.setdCjrq(cjrq);
	}
	
}
