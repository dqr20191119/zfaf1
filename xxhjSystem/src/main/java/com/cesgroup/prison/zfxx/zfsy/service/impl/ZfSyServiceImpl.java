package com.cesgroup.prison.zfxx.zfsy.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.jobs.constants.Synchro;
import com.cesgroup.prison.jobs.utils.ZfywHttpClientUtil;
import com.cesgroup.prison.zfxx.zfsy.dao.ZfSyDao;
import com.cesgroup.prison.zfxx.zfsy.entity.ZfSy;
import com.cesgroup.prison.zfxx.zfsy.service.ZfSyService;

/**
 * Description: 收押业务访问接口实现类
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
@Service("zfSyService")
@Transactional
public class ZfSyServiceImpl extends BaseDaoService<ZfSy, String, ZfSyDao> implements ZfSyService {

	@Override
	public void synchroZfSy(String corp, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_SY, corp, time);
		Map<String, Object> zfjbxx = null;
		List<Map<String, Object>> zfSyList = null;
		ZfSy zfSy = null;
		ZfSy query = null;
		long count = 0;
		if (data != null) {
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				zfSyList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.RSZYZBDTIMELINE_ZFZYZTBD);
				if (zfSyList != null && zfSyList.size() > 0) {
					for (Map<String, Object> map : zfSyList) {
						zfSy = new ZfSy();
						this.convert(zfSy, map, zfjbxx, corp, urlTime, cjpc, cjrq);
						query = new ZfSy();
						query.setcZfbh(zfSy.getcZfbh());
						query.setdSyrq(zfSy.getdSyrq());
						//根据罪犯编号和收押日期判断新收押数据不能重复同步
						count = this.getDao().selectCountByEntity(query);
						if (count == 0) {
							this.getDao().insert(zfSy);
						}
					}
				}
			}
		}
	}
	
	@Transactional(readOnly = true)
	private void convert(ZfSy zfSy, Map<String, Object> map, Map<String, Object> zfjbxx, String corp, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfSy.setcId((String)zfjbxx.get("CId"));
		zfSy.setcZfbh((String)zfjbxx.get("CZfbh"));
		//收押业务
		zfSy.setcSylb((String)map.get("CSylb"));
		zfSy.setdSyrq(Util.toDate((String)map.get("DSyrq"), Util.DF_DOT_DATE));
		zfSy.setcSyjg((String)map.get("CSyjg"));
		zfSy.setcSyjgQh((String)map.get("CSyjgQh"));
		zfSy.setcSyjgMx((String)map.get("CSyjgMx"));
		//公共字段
		zfSy.setId(Synchro.getUUID());
		zfSy.setdUrlTime(urlTime);
		zfSy.setcCjpc(cjpc);
		zfSy.setdCjrq(cjrq);
	}
	
}
