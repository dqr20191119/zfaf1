package com.cesgroup.prison.zfxx.zfstat.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.httpclient.zfxx.ZfStatZyHttpClient;
import com.cesgroup.prison.httpclient.zfxx.dto.ZfStatZyDto;
import com.cesgroup.prison.zfxx.zfstat.dao.ZfStatZyDao;
import com.cesgroup.prison.zfxx.zfstat.entity.ZfStatZy;
import com.cesgroup.prison.zfxx.zfstat.service.ZfStatZyService;

@Service("zfStatZyService")
public class ZfStatZyServiceImpl extends BaseDaoService<ZfStatZy,String,ZfStatZyDao> implements ZfStatZyService{

 
	
	@Autowired
	private ZfStatZyDao zfStatZyDao;
	
	
	/**
	 * 2.统计【暂押罪犯】信息数据
	 */
	@Override
	public void synchroZfStatZy(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException {
		
		Date startDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		String type = "STAT_ZFDD_ZY";
		try {
			
			List<ZfStatZyDto> dtoList = ZfStatZyHttpClient.entityZfStatZy(Util.toStr(startDate, Util.DF_DATE), jyCorps, lx, jqCorps);
			if(dtoList != null && dtoList.size()>0) {
				System.out.println("2.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【暂押罪犯】统计信息" + dtoList.size() + "条");
				logger.info("2.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【暂押罪犯】统计信息" + dtoList.size() + "条");
				this.synchoZfStats(dtoList,type,jyId,jqId);
			}
		} catch (Exception e) {
			throw new BusinessLayerException("2.同步【暂押罪犯】统计发生异常, Exception info is " + e);
		}
	}
	
	/**
	 * 3.统计【罪犯调动】信息数据
	 */
	@Override
	public void synchroZfStatZfdd(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException {
		
		Date startDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		String type = "STAT_ZFDD";
		try {
			
			List<ZfStatZyDto> dtoList = ZfStatZyHttpClient.entityZfStatZfdd(Util.toStr(startDate, Util.DF_DATE), jyCorps, lx, jqCorps);
			if(dtoList != null && dtoList.size()>0) {
				System.out.println("3.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【罪犯调动】统计信息" + dtoList.size() + "条");
				logger.info("3.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【罪犯调动】统计信息" + dtoList.size() + "条");
				this.synchoZfStats(dtoList,type,jyId,jqId);
			}
		} catch (Exception e) {
			throw new BusinessLayerException("3.同步【罪犯调动】统计发生异常, Exception info is " + e);
		}
	}
	
	
	private void synchoZfStats(List<ZfStatZyDto>  dtoList,String type,String jyId,String jqId) {
		
		if(dtoList != null && dtoList.size()>0) {
			
			for(ZfStatZyDto obt : dtoList) {
				
				ZfStatZy newObj = this.convertZfStatDtoToZfStat(obt,type,jyId,jqId);
				
				if(newObj != null) {
					
					zfStatZyDao.insert(newObj);
				}
			}
		}
	}
	private void synchoZfStats(List<ZfStatZyDto>  dtoList,String type,List<Map<String ,Object>> list) {
		
		if(dtoList != null && dtoList.size()>0) {
			
			for(ZfStatZyDto obt : dtoList) {
				
				ZfStatZy newObj = this.convertZfStatDtoToZfStat(obt,type,list);
				
				if(newObj != null) {
					
					zfStatZyDao.insert(newObj);
				}
			}
		}
	}
	private ZfStatZy convertZfStatDtoToZfStat(ZfStatZyDto dto,String type,String jyId,String jqId) {
		
		ZfStatZy zf = null;
		
		if(dto != null) {
			zf = new ZfStatZy();
//			zf.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			zf.setCount(dto.getCount());
			zf.setItemsId(dto.getId());
			zf.setItemsValue(dto.getValue());
			zf.setItemsPercent(dto.getPercent());
			zf.setItemsName(dto.getName());
			zf.setDUrlTime(Util.toDate(dto.getDUrlTime(), Util.DF_DATE));
			zf.setType(type);
			zf.setJqId(jqId);;
			zf.setJyId(jyId);
		}
		return zf;
	}

	private ZfStatZy convertZfStatDtoToZfStat(ZfStatZyDto dto,String type,List<Map<String ,Object>> list) {
		
		ZfStatZy zf = null;
		
		if(dto != null) {
			zf = new ZfStatZy();
//			zf.setId(UUID.randomUUID().toString().replaceAll("-", ""));
			zf.setCount(dto.getCount());
			zf.setItemsId(dto.getId());
			zf.setItemsValue(dto.getValue());
			zf.setItemsPercent(dto.getPercent());
			zf.setItemsName(dto.getName());
			zf.setDUrlTime(Util.toDate(dto.getDUrlTime(), Util.DF_DATE));
			zf.setType(type);
			for(int i = 0;i<list.size();i++){
				Map<String,Object> mapJyCorps = list.get(i);
				String jyId = (String) mapJyCorps.get("jyCode");
				String corps = (String) mapJyCorps.get("corps");
				if(dto.getId().equals(corps)){
					zf.setJyId(jyId);
					break;
				}
			}
			 
		}
		return zf;
	}
	/**
	 * 4.统计【在押罪犯】信息数据
	 */
	@Override
	public void synchroZfStatZyqk(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException {
		Date startDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		String type = "STAT_ZYQK_V1";
		
		try {
			
			List<ZfStatZyDto> dtoList = ZfStatZyHttpClient.entityZfStatZyqk(Util.toStr(startDate, Util.DF_DATE), jyCorps, lx, jqCorps);
			
			if(dtoList != null && dtoList.size()>0) {
				
				System.out.println("4.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【在押罪犯】统计信息" + dtoList.size() + "条");
				logger.info("4.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【在押罪犯】统计信息" + dtoList.size() + "条");
				
				this.synchoZfStats(dtoList,type,jyId,jqId);
			}
		} catch (Exception e) {
			throw new BusinessLayerException("4.同步【在押罪犯】统计发生异常, Exception info is " + e);
		}
		
	}

	
	/**
	 * 5.统计【性别】罪犯信息数据
	 */
	@Override
	public void synchroZfStatZyXb(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException {
		Date startDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		String type = "STAT_ZF_XB";
		
		try {
			
			List<ZfStatZyDto> dtoList = ZfStatZyHttpClient.entityZfStatZyXb(Util.toStr(startDate, Util.DF_DATE), jyCorps, lx, jqCorps);
			
			if(dtoList != null && dtoList.size()>0) {
				
				System.out.println("5.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【性别罪犯】统计信息" + dtoList.size() + "条");
				logger.info("5.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【性别罪犯】统计信息" + dtoList.size() + "条");
				
				this.synchoZfStats(dtoList,type,jyId,jqId);
			}
		} catch (Exception e) {
			throw new BusinessLayerException("5.同步【性别罪犯】统计发生异常, Exception info is " + e);
		}
		
	}
	
	/**
	 * 6.统计【年龄】罪犯信息数据
	 */
	@Override
	public void synchroZfStatZyNl(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException {
		Date startDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		String type = "STAT_ZF_NL";
		
		try {
			
			List<ZfStatZyDto> dtoList = ZfStatZyHttpClient.entityZfStatZyNl(Util.toStr(startDate, Util.DF_DATE), jyCorps, lx, jqCorps);
			
			if(dtoList != null && dtoList.size()>0) {
				
				System.out.println("6.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【年龄】统计信息" + dtoList.size() + "条");
				logger.info("6.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【年龄】统计信息" + dtoList.size() + "条");
				
				this.synchoZfStats(dtoList,type,jyId,jqId);
			}
		} catch (Exception e) {
			throw new BusinessLayerException("6.同步【年龄】统计发生异常, Exception info is " + e);
		}
	}
	
	/**
	 * 7.统计【罪名】罪犯信息数据
	 */
	@Override
	public void synchroZfStatZyZm(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException {
		Date startDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		String type = "STAT_ZF_ZM";
		
		try {
			
			List<ZfStatZyDto> dtoList = ZfStatZyHttpClient.entityZfStatZyZm(Util.toStr(startDate, Util.DF_DATE), jyCorps, lx, jqCorps);
			
			if(dtoList != null && dtoList.size()>0) {
				
				System.out.println("7.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【罪名】统计信息" + dtoList.size() + "条");
				logger.info("7.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【罪名】统计信息" + dtoList.size() + "条");
				
				this.synchoZfStats(dtoList,type,jyId,jqId);
			}
		} catch (Exception e) {
			throw new BusinessLayerException("7.同步【罪名】统计发生异常, Exception info is " + e);
		}
	}
	
	/**
	 * 8.统计【原判刑期】信息数据
	 */
	@Override
	public void synchroZfStatZyYpxq(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException {
		
		Date startDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		
		String type = "STAT_ZF_YPXQ";
		
		try {
			
			List<ZfStatZyDto> dtoList = ZfStatZyHttpClient.entityZfStatZyYpxq(Util.toStr(startDate, Util.DF_DATE), jyCorps, lx, jqCorps);
			
			if(dtoList != null && dtoList.size()>0) {
				
				System.out.println("8.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【原判刑期】统计信息" + dtoList.size() + "条");
				logger.info("8.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【原判刑期】统计信息" + dtoList.size() + "条");
				
				this.synchoZfStats(dtoList,type,jyId,jqId);
			}
		} catch (Exception e) {
			throw new BusinessLayerException("8.同步【原判刑期】统计发生异常, Exception info is " + e);
		}
	}
	
	/**
	 * 9.统计【剩余刑期】信息数据
	 */
	@Override
	public void synchroZfStatZySyxq(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException {
		
		Date startDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		
		String type = "STAT_ZF_SYXQ";
		
		try {
			
			List<ZfStatZyDto> dtoList = ZfStatZyHttpClient.entityZfStatZySyxq(Util.toStr(startDate, Util.DF_DATE), jyCorps, lx, jqCorps);
			
			if(dtoList != null && dtoList.size()>0) {
				
				System.out.println("9.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【剩余刑期】统计信息" + dtoList.size() + "条");
				logger.info("9.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【剩余刑期】统计信息" + dtoList.size() + "条");
				
				this.synchoZfStats(dtoList,type,jyId,jqId);
			}
		} catch (Exception e) {
			throw new BusinessLayerException("9.同步【剩余刑期】统计发生异常, Exception info is " + e);
		}
	}
	/**
	 * 10.统计【监狱关押能力】信息数据
	 */
	@Override
	public void synchroZfStatZyJyGynl(List<Map<String ,Object>> list) throws BusinessLayerException {
		
		Date startDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		
		String type = "STAT_ZF_GYNL";
		
		try {
			
			List<ZfStatZyDto> dtoList = ZfStatZyHttpClient.entityZfStatZyJyGynl();
			
			if(dtoList != null && dtoList.size()>0) {
				
				System.out.println("10.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【监狱关押能力】统计信息" + dtoList.size() + "条");
				logger.info("10.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【监狱关押能力】统计信息" + dtoList.size() + "条");
				
				this.synchoZfStats(dtoList,type,list);
			}
		} catch (Exception e) {
			throw new BusinessLayerException("10.同步【监狱关押能力】统计发生异常, Exception info is " + e);
		}
	}
	
	/**
	 * 11.统计【各监狱关押人数】信息数据
	 */
	@Override
	public void synchroZfStatZyJygyqk(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException {
		
		Date startDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		
		String type = "STAT_ZF_ZYQK_V2";
		
		try {
			
			List<ZfStatZyDto> dtoList = ZfStatZyHttpClient.entityZfStatZyJygyqk(Util.toStr(startDate, Util.DF_DATE), jyCorps, lx, jqCorps);
			
			if(dtoList != null && dtoList.size()>0) {
				
				System.out.println("11.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【各监狱关押人数】统计信息" + dtoList.size() + "条");
				logger.info("11.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【各监狱关押人数】统计信息" + dtoList.size() + "条");
				
				this.synchoZfStats(dtoList,type,jyId,jqId);
			}
		} catch (Exception e) {
			throw new BusinessLayerException("11.同步【各监狱关押人数】统计发生异常, Exception info is " + e);
		}
	}
	
	/**
	 * 12.统计【改造情况】信息数据
	 */
	@Override
	public void synchroZfStatZyJygzqk(String jyId,String jqId,String lx,String jyCorps,String jqCorps) throws BusinessLayerException {
		
		Date startDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		
		String type = "STAT_ZF_GZQK";
		
		try {
			
			List<ZfStatZyDto> dtoList = ZfStatZyHttpClient.entityZfStatZyJygzqk(Util.toStr(startDate, Util.DF_DATE), jyCorps, lx, jqCorps);
			
			if(dtoList != null && dtoList.size()>0) {
				
				System.out.println("12.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【各监狱改造情况】统计信息" + dtoList.size() + "条");
				logger.info("12.开始同步" + Util.toStr(startDate, Util.DF_DATE) + "【各监狱改造情况】统计信息" + dtoList.size() + "条");
				
				this.synchoZfStats(dtoList,type,jyId,jqId);
			}
		} catch (Exception e) {
			throw new BusinessLayerException("11.同步【各监狱改造情况】统计发生异常, Exception info is " + e);
		}
	}

}
