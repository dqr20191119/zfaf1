package com.cesgroup.prison.zfxx.zfstat.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.httpclient.zfxx.ZfStatHttpClient;
import com.cesgroup.prison.httpclient.zfxx.dto.ZfStatDto;
import com.cesgroup.prison.zfxx.zfstat.dao.ZfStatDao;
import com.cesgroup.prison.zfxx.zfstat.entity.ZfStat;
import com.cesgroup.prison.zfxx.zfstat.service.ZfStatService;

@Service("zfStatService")
public class ZfStatServiceImpl extends BaseDaoService<ZfStat,String,ZfStatDao> implements ZfStatService {

	@Autowired
	private ZfStatDao zfStatDao;
	 
	/**
	 * 定时任务使用，统计所有监狱的外出罪犯
	 */
	@Override
	public void synchroZfStat(String jyId, String jqId,String type,String jyCorps,String jqCorps) throws BusinessLayerException {
		// TODO Auto-generated method stub
		try{
			Date startDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
			
		//	List<ZfStatDto> zfStatDtoList = ZfStatHttpClient.entityZfStat(Util.toStr(startDate, Util.DF_DATE), jyCorps, "corp", "");
			
		//	List<ZfStatDto> zfStatDtoList2 = ZfStatHttpClient.entityZfStat(Util.toStr(startDate, Util.DF_DATE), jyCorps, "dept", jqCorps);
			
			List<ZfStatDto> zfStatDtoList = ZfStatHttpClient.entityZfStat(Util.toStr(startDate, Util.DF_DATE), jyCorps, type, jqCorps);
			
			if(zfStatDtoList != null && zfStatDtoList.size()>0) {
				System.out.println("开始同步" + Util.toStr(startDate, Util.DF_DATE) + "罪犯统计信息" + zfStatDtoList.size() + "条");
				logger.info("开始同步" + Util.toStr(startDate, Util.DF_DATE) + "罪犯统计信息" + zfStatDtoList.size() + "条");
				this.synchoZfStats(zfStatDtoList,jyId,jqId);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	

	private void synchoZfStats(List<ZfStatDto>  zfStatDtoList,String jyId,String jqId) {
		
		if(zfStatDtoList != null && zfStatDtoList.size()>0) {
			
			for(ZfStatDto obt : zfStatDtoList) {
				
				ZfStat newObj = this.convertZfStatDtoToZfStat(obt,jyId,jqId);
				
				if(newObj != null) {
					zfStatDao.insert(newObj);
				}
			}
		}
	}
	
	private ZfStat convertZfStatDtoToZfStat(ZfStatDto dto,String jyId,String jqId) {
		ZfStat zf = null;
		
		if(dto != null) {
			zf = new ZfStat();
			zf.setCount(dto.getCount());
			zf.setItemsId(dto.getId());
			zf.setItemsValue(dto.getValue());
			zf.setItemsPercent(dto.getPercent());
			zf.setItemsName(dto.getName());
			zf.setDUrlTime(Util.toDate(dto.getDUrlTime(), Util.DF_DATE));
			zf.setJqId(jqId);
			zf.setJyId(jyId);
			zf.setType("STAT_ZFXX");
		}
		return zf;
	}

	

}
