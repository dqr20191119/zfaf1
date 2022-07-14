package com.cesgroup.prison.aqfh.zfcjfh.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.prison.aqfh.zfcjfh.dao.ZfcjfhMapper;
import com.cesgroup.prison.aqfh.zfcjfh.entity.ZfcjfhEntity;
import com.cesgroup.prison.aqfh.zfcjfh.service.ZfcjfhService;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;

/**
* @author lihong
* @date 创建时间：2020年6月4日 上午10:47:52
* 类说明:
*/
@Service
public class ZfcjfhServiceImpl  extends BaseDaoService<ZfcjfhEntity, String, ZfcjfhMapper> implements ZfcjfhService {

	@Override
	public Page<ZfcjfhEntity> findList(ZfcjfhEntity zfcjfhEntity, PageRequest pageRequest) {
		
		return this.getDao().findList(zfcjfhEntity, pageRequest);
	}

	@Override
	@Transactional
	public void updateById(ZfcjfhEntity zfcjfhEntity) {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		zfcjfhEntity.setShrId(user.getUserId());
		zfcjfhEntity.setShrName(user.getUserName());
		zfcjfhEntity.setShRq(DateUtils.getCurrentDate(true));
		this.getDao().updateById(zfcjfhEntity);
	}

	@Override
	@Transactional
	public AjaxResult inItDutyData() {
		//先查询当天有不有释放数据  没有同步当天的数据  有就不同步
		ZfcjfhEntity zfcj = new ZfcjfhEntity();
		//SimpleDateFormat sdf = new SimpleDateFormat();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		zfcj.setSfRq(new Date());
		zfcj.setCusNumber(user.getCusNumber());
	  List<ZfcjfhEntity> zfcjList = this.getDao().selectByZfCj(zfcj);
	  if(zfcjList.size() <= 0) {//开始同步数据
		  List<ZfcjfhEntity> todaySfzfDatas = this.getDao().getTodaySfzfData(zfcj);
		  if(todaySfzfDatas.size() >0) {
			  for (ZfcjfhEntity todaySfzfData:todaySfzfDatas) {
					/*
					 * ZfcjfhEntity todatZfcj = new ZfcjfhEntity();
					 * todatZfcj.setCusNumber(todaySfzfData.getCusNumber());
					 * todatZfcj.setJqId(todaySfzfData.getJqId());
					 * todatZfcj.setCsRq(todaySfzfData.getCsRq());
					 * todatZfcj.setJg(todaySfzfData.getJg());
					 * todatZfcj.setMz(todaySfzfData.getMz());
					 * todatZfcj.setName(todaySfzfData.getName());
					 * todatZfcj.setSflb(todaySfzfData.getSflb());
					 * todatZfcj.setSfRq(todaySfzfData.getSfRq());
					 * todatZfcj.setXq(todaySfzfData.getXq());
					 * todatZfcj.setXqQr(todaySfzfData.getXqQr());
					 * todatZfcj.setXqZr(todaySfzfData.getXqZr()); if(todaySfzfData.getXz() != null
					 * && !"".equals(todaySfzfData.getXz()) ) {
					 * todatZfcj.setXz(todaySfzfData.getXz()); }
					 * todatZfcj.setZfBh(todaySfzfData.getZfBh());
					 * todatZfcj.setZm(todaySfzfData.getZm());
					 * todatZfcj.setZt(todaySfzfData.getZt());
					 * todatZfcj.setZxXq(todaySfzfData.getZxXq());
					 * todatZfcj.setZxXqZr(todaySfzfData.getZxXqZr()); todatZfcj.setZt("0");
					 */
				  todaySfzfData.setZt("0");
				  this.getDao().insert(todaySfzfData);
			  }
		  }
	  }
		return AjaxResult.success("同步成功");
	}

}
