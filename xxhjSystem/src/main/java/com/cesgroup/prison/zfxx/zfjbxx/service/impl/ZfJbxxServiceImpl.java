package com.cesgroup.prison.zfxx.zfjbxx.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.FutureTask;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.exception.RESTHttpClientException;
import com.cesgroup.framework.utils.PropertiesUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.httpclient.zfxx.ZfJbxxHttpClient;
import com.cesgroup.prison.httpclient.zfxx.ZfLjHttpClient;
import com.cesgroup.prison.httpclient.zfxx.ZfPhotoHttpClient;
import com.cesgroup.prison.httpclient.zfxx.ZfSyHttpClient;
import com.cesgroup.prison.httpclient.zfxx.dto.ZfJbxxDto;
import com.cesgroup.prison.httpclient.zfxx.dto.ZfLjDto;
import com.cesgroup.prison.httpclient.zfxx.dto.ZfPhotoDto;
import com.cesgroup.prison.httpclient.zfxx.dto.ZfSyDto;
import com.cesgroup.prison.httpclient.zfxx.dto.ZfTafDto;
import com.cesgroup.prison.zfxx.zfdqzt.dao.ZfDqztDao;
import com.cesgroup.prison.zfxx.zfdqzt.entity.ZfDqzt;
import com.cesgroup.prison.zfxx.zfjbxx.dao.ZfJbxxDao;
import com.cesgroup.prison.zfxx.zfjbxx.entity.ZfJbxx;
import com.cesgroup.prison.zfxx.zfjbxx.service.ZfJbxxService;
import com.cesgroup.prison.zfxx.zfjbxx.task.SynchroZfJbxxTask;
import com.cesgroup.prison.zfxx.zflj.dao.ZfLjDao;
import com.cesgroup.prison.zfxx.zflj.entity.ZfLj;
import com.cesgroup.prison.zfxx.zfphoto.dao.ZfPhotoDao;
import com.cesgroup.prison.zfxx.zfphoto.entity.ZfPhoto;
import com.cesgroup.prison.zfxx.zfsy.dao.ZfSyDao;
import com.cesgroup.prison.zfxx.zfsy.entity.ZfSy;
import com.cesgroup.prison.zfxx.zftaf.dao.ZfTafDao;
import com.cesgroup.prison.zfxx.zftaf.entity.ZfTaf;

/**
 * Description: 罪犯基本信息业务访问接口实现类
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
@Service("zfJbxxService")
@Transactional
public class ZfJbxxServiceImpl extends BaseDaoService<ZfJbxx, String, ZfJbxxDao> implements ZfJbxxService {
	/**
	 * 罪犯照片本系统（安防平台）存储根目录
	 */
	private String zfPhotoRootPath = PropertiesUtil.getValueByKeyUnchanged("application", "afpt.zfphoto.rootPath"); //D:/zfPhoto
	/**
	 * 同步罪犯信息的监狱编号
	 */
	private String synchroZfxxCorp = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.zfxx.corp"); // 73878031185de329fa6876725c100006
	/**
	 * 同步罪犯信息开始日期
	 */
	// private String synchroZfxxStartDate = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.zfxx.startDate"); //
	/**
	 * 同步罪犯基本信息开始日期
	 */
	private String synchroZfxxZfJbxxStartDate = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.zfxx.zfJbxxStartDate"); //
	/**
	 * 同步罪犯收押信息开始日期
	 */
	private String synchroZfxxZfSyStartDate = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.zfxx.zfSyStartDate"); //
	/**
	 * 同步罪犯离监信息开始日期
	 */
	private String synchroZfxxZfLjStartDate = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.zfxx.zfLjStartDate"); //
	/**
	 * 是否从startDate开始同步罪犯信息开始日期（若是，则开始时间为配置文件中的synchro.zfxx.startDate，若否，则只同步当天的数据）
	 */
	private Boolean synchroZfxxFromStartDate = PropertiesUtil.getBooleanByKeyUnchanged("application", "synchro.zfxx.fromStartDate"); //
	/**
	 * 下载罪犯照片开始日期
	 */
	private String downloadZfPhotoStartDate = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.zfphoto.downloadStartDate"); //
	/**
	 * 是否下载照片
	 */
	private Boolean downloadZfPhoto = PropertiesUtil.getBooleanByKeyUnchanged("application", "synchro.zfphoto.download"); //
	/**
	 * 罪犯当前状态Dao
	 */
	@Autowired
	private ZfDqztDao zfDqztDao;
	/**
	 * 罪犯同案犯信息Dao
	 */
	@Autowired
	private ZfTafDao zfTafDao;
	/**
	 * 罪犯照片信息Dao
	 */
	@Autowired
	private ZfPhotoDao zfPhotoDao;
	/**
	 * 罪犯收押信息Dao
	 */
	@Autowired
	private ZfSyDao zfSyDao;
	/**
	 * 罪犯离监信息Dao
	 */
	@Autowired
	private ZfLjDao zfLjDao;
	
	@Override
	public void synchroZfJbxxTask() throws BusinessLayerException {
		try {
			Thread thread = new Thread(new FutureTask<>(new SynchroZfJbxxTask(this)));
			thread.start();
		} catch (Exception e) {
			throw new BusinessLayerException("执行多线程同步罪犯信息发生异常, Exception info is " + e);
		}
	}
	
	/**
	 * 同步罪犯基本信息、同案犯信息、照片信息
	 */
	@Override
	public void synchoZfJbxxAndZfTafAndZfPhoto() throws BusinessLayerException {
		// System.out.println("synchoZfJbxxAndZfTafAndZfPhoto 同步罪犯基本信息、同案犯信息、照片信息");
		String startDateStr = synchroZfxxZfJbxxStartDate;
		// 是否以配置文件中的startDate为同步开始日期
		if(!synchroZfxxFromStartDate) {
			startDateStr = Util.getCurrentDate();
		}
		Date startDate = Util.toDate(startDateStr, Util.DF_DATE);
		Date endDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		
		while(startDate.compareTo(endDate) <= 0) {
			try {
				// 罪犯基本信息、罪犯同犯信息、罪犯照片信息
				List<ZfJbxxDto> zfJbxxDtoList = ZfJbxxHttpClient.entityZfJbxx(Util.toStr(startDate, Util.DF_DATE), synchroZfxxCorp);
				
				if(zfJbxxDtoList != null && zfJbxxDtoList.size() > 0) {
					System.out.println("开始同步" + Util.toStr(startDate, Util.DF_DATE) + "罪犯基本信息" + zfJbxxDtoList.size() + "条");
					logger.info("开始同步" + Util.toStr(startDate, Util.DF_DATE) + "罪犯基本信息" + zfJbxxDtoList.size() + "条");
					this.synchoZfJbxxAndZfTafAndZfPhoto(zfJbxxDtoList);
				}
				
				startDate = Util.getAddDayDate(startDate, 1);
			} catch (RESTHttpClientException e) {
				throw new BusinessLayerException("同步罪犯信息发生异常, Exception info is " + e);
			}
		}
	}
	

	@Override
	public void synchoZfSy() throws BusinessLayerException {
		// System.out.println("synchoZfSy 同步罪犯收押");
		String startDateStr = synchroZfxxZfSyStartDate;
		// 是否以配置文件中的startDate为同步开始日期
		if(!synchroZfxxFromStartDate) {
			startDateStr = Util.getCurrentDate();
		}
		Date startDate = Util.toDate(startDateStr, Util.DF_DATE);
		Date endDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		
		while(startDate.compareTo(endDate) <= 0) {
			try {
				// 基本信息、罪犯收押信息
				List<ZfJbxxDto> syZfJbxxDtoList = ZfSyHttpClient.entityZfSy(Util.toStr(startDate, Util.DF_DATE), synchroZfxxCorp);
				if(syZfJbxxDtoList != null && syZfJbxxDtoList.size() > 0) {
					System.out.println("开始同步" + Util.toStr(startDate, Util.DF_DATE) + "罪犯收押信息" + syZfJbxxDtoList.size() + "条");
					logger.info("开始同步" + Util.toStr(startDate, Util.DF_DATE) + "罪犯收押信息" + syZfJbxxDtoList.size() + "条");
					this.synchoZfSy(syZfJbxxDtoList);
				}
				startDate = Util.getAddDayDate(startDate, 1);
			} catch (RESTHttpClientException e) {
				throw new BusinessLayerException("同步罪犯信息发生异常, Exception info is " + e);
			}
		}
	}

	@Override
	public void synchoZfLj() throws BusinessLayerException {
		// System.out.println("synchoZfSy 同步罪犯离监");
		String startDateStr = synchroZfxxZfLjStartDate;
		// 是否以配置文件中的startDate为同步开始日期
		if(!synchroZfxxFromStartDate) {
			startDateStr = Util.getCurrentDate();
		}
		Date startDate = Util.toDate(startDateStr, Util.DF_DATE);
		Date endDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		
		while(startDate.compareTo(endDate) <= 0) {
			try {
				// 基本信息、罪犯离监信息
				List<ZfJbxxDto> ljZfJbxxDtoList = ZfLjHttpClient.entityZfLj(Util.toStr(startDate, Util.DF_DATE), synchroZfxxCorp);
				if(ljZfJbxxDtoList != null && ljZfJbxxDtoList.size() > 0) {
					System.out.println("开始同步" + Util.toStr(startDate, Util.DF_DATE) + "罪犯离监信息" + ljZfJbxxDtoList.size() + "条");
					logger.info("开始同步" + Util.toStr(startDate, Util.DF_DATE) + "罪犯离监信息" + ljZfJbxxDtoList.size() + "条");
					this.synchoZfLj(ljZfJbxxDtoList);
				}
				startDate = Util.getAddDayDate(startDate, 1);
			} catch (RESTHttpClientException e) {
				throw new BusinessLayerException("同步罪犯信息发生异常, Exception info is " + e);
			}
		}
	}

	/**
	 * 根据罪犯所在监狱、所在监区、罪犯基本信息同步日期，下载罪犯照片
	 */
	@Override
	public void autoDownloadZfPhoto() throws BusinessLayerException {
		String startDateStr = downloadZfPhotoStartDate;
		// 是否以配置文件中的startDate为同步开始日期
		if(!synchroZfxxFromStartDate) {
			startDateStr = Util.getCurrentDate();
		}
		Date startDate = Util.toDate(startDateStr, Util.DF_DATE);
		Date endDate = Util.toDate(Util.getCurrentDate(), Util.DF_DATE);
		
		while(startDate.compareTo(endDate) <= 0) {
			try {
				// 根据在监日期查询在此范围内的罪犯的照片数据
				List<ZfPhoto> zfPhotoList = this.zfPhotoDao.findExistsZfDqztByDUrlTime(Util.toStr(startDate, Util.DF_DATE));
				
				if(zfPhotoList != null && zfPhotoList.size() > 0) {
					for(ZfPhoto zfPhoto : zfPhotoList) {
						this.downloadZfPhoto(zfPhoto);
					}
				}
				startDate = Util.getAddDayDate(startDate, 1);
			} catch (Exception e) {
				throw new BusinessLayerException("下载照片失败");
			}
		}
		logger.info("autoDownloadZfPhoto执行结束");
	}

	/**
	 * 根据罪犯所在监狱、所在监区、罪犯基本信息同步日期，下载罪犯照片
	 */
	@Override
	public void downloadZfPhoto(String cSzjy, String cSzjq, String dUrlTime) throws BusinessLayerException {
		try {
			// 根据所在监狱、所在监区、在监日期查询在此范围内的罪犯的照片数据
			List<ZfPhoto> zfPhotoList = this.zfPhotoDao.findExistsZfDqztByCSzjyAndCSzjqAndDUrlTime(cSzjy, cSzjq, dUrlTime);
			
			if(zfPhotoList != null && zfPhotoList.size() > 0) {
				for(ZfPhoto zfPhoto : zfPhotoList) {
					this.downloadZfPhoto(zfPhoto);
				}
			}
		} catch (Exception e) {
			throw new BusinessLayerException("下载照片失败");
		}
	}

	/**
	 * 下载罪犯张照片，将照片存储在本地
	 * @throws BusinessLayerException
	 */
	private void downloadZfPhoto(ZfPhoto zfPhoto) throws BusinessLayerException {
        try {
        	if(zfPhoto != null) {
				// 照片类别转换
				String photoCategory = CommonConstant.PhotoCategoryValue.get(zfPhoto.getcZplb());
				photoCategory = Util.isNull(photoCategory) ? "Unknow" : photoCategory;
				logger.info("照片类别【" + zfPhoto.getcZplb() + "】，转化为" + photoCategory);
				
        		String fileDirectoryPath = this.zfPhotoRootPath + "/" + photoCategory;
        		
        		ZfPhotoHttpClient.downloadZfPhoto(zfPhoto.getcStorageid(), fileDirectoryPath, zfPhoto.getcFileName());
        	}
        } catch (Exception e) {
            throw new BusinessLayerException("下载罪犯照片发生异常, Exception info is " + e);
        }
	}

	/**
	 * 同步罪犯基本信息、罪犯同犯信息、罪犯照片信息
	 * 
	 * @param zfJbxxDtoList
	 */
	private void synchoZfJbxxAndZfTafAndZfPhoto(List<ZfJbxxDto> zfJbxxDtoList) {
		if(zfJbxxDtoList != null && zfJbxxDtoList.size() > 0) {
			for(ZfJbxxDto zfJbxxDto : zfJbxxDtoList) {
				// 罪犯基本信息DTO转罪犯基本信息实体类
				ZfJbxx zfJbxx = this.convertZfJbxxDtoToZfJbxx(zfJbxxDto);
				
				// 罪犯基本信息DTO转罪犯当前状态实体类
				ZfDqzt zfDqzt = this.convertZfJbxxDtoToZfDqzt(zfJbxxDto);
				
				// 从罪犯基本信息DTO中获取罪犯同犯信息，并转化为罪犯同犯信息实体类
				List<ZfTaf> zfTafList = this.convertZfTafDtoToZfTaf(zfJbxxDto.getZfTafDtoList());
				
				// 从罪犯基本信息DTO中获取罪犯照片信息，并转化为罪犯照片信息实体类
				List<ZfPhoto> zfPhotoList = this.convertZfPhotoDtoToZfPhoto(zfJbxxDto.getZfPhotoDtoList());
				
				// 保存或更新罪犯基本信息
				if(zfJbxx != null) {
					this.synchroZfJbxx(zfJbxx);
				}
				
				// 保存或更新罪犯当前状态
				if(zfDqzt != null) {
					this.synchroZfDqzt(zfDqzt);
				}
				
				// 保存或更新罪犯同案犯信息
				if(zfTafList != null && zfTafList.size() > 0) {
					this.synchroZfTafList(zfTafList);
				}
				
				// 保存或更新罪犯照片信息
				if(zfPhotoList != null && zfPhotoList.size() > 0) {
					this.synchroZfPhoto(zfPhotoList);
				}
			}
		}
	}

	/**
	 * 同步罪犯收押信息
	 * @param zfJbxxDtoList
	 */
	private void synchoZfSy(List<ZfJbxxDto> zfJbxxDtoList) {
		if(zfJbxxDtoList != null && zfJbxxDtoList.size() > 0) {
			for(ZfJbxxDto zfJbxxDto : zfJbxxDtoList) {
				// 从罪犯基本信息DTO中获取罪犯收押信息，并转化为罪犯收押信息实体类
				List<ZfSy> zfSyList = this.convertZfSyDtoToZfSy(zfJbxxDto.getZfSyDtoList());
				
				// 保存或更新罪犯收押信息
				if(zfSyList != null && zfSyList.size() > 0) {
					this.synchroZfSy(zfSyList);
				}
			}
		}
	}

	/**
	 * 同步罪犯离监信息
	 * @param zfJbxxDtoList
	 */
	private void synchoZfLj(List<ZfJbxxDto> zfJbxxDtoList) {
		if(zfJbxxDtoList != null && zfJbxxDtoList.size() > 0) {
			for(ZfJbxxDto zfJbxxDto : zfJbxxDtoList) {
				// 从罪犯基本信息DTO中获取罪犯收押信息，并转化为罪犯收押信息实体类
				List<ZfLj> zfLjList = this.convertZfLjDtoToZfLj(zfJbxxDto.getZfLjDtoList());
				
				// 保存或更新罪犯收押信息
				if(zfLjList != null && zfLjList.size() > 0) {
					this.synchroZfLj(zfLjList);
				}
			}
		}
	}
	
	/**
	 * Description: 罪犯基本信息Dto转罪犯基本信息实体类
	 * @param zfJbxxDto
	 * @return
	 */
	private ZfJbxx convertZfJbxxDtoToZfJbxx(ZfJbxxDto zfJbxxDto) {
		ZfJbxx zfJbxx = null;
		if(zfJbxxDto != null) {
			zfJbxx = new ZfJbxx();
			zfJbxx.setcZfbh(zfJbxxDto.getCZfbh());
			zfJbxx.setcId(zfJbxxDto.getCId());
			zfJbxx.setcXm(zfJbxxDto.getCXm());
			zfJbxx.setcZsym(zfJbxxDto.getCZsym());
			zfJbxx.setcXb(zfJbxxDto.getCXb());
			zfJbxx.setdCsrq(Util.toDate(zfJbxxDto.getDCsrq(), Util.DF_DOT_DATE));
			zfJbxx.setcMz(zfJbxxDto.getCMz());
			zfJbxx.setcBqzzmm(zfJbxxDto.getCBqzzmm());
			zfJbxx.setcBqwhcd(zfJbxxDto.getCBqwhcd());
			zfJbxx.setcBqhyzk(zfJbxxDto.getCBqhyzk());
			zfJbxx.setcSxzy(zfJbxxDto.getCSxzy());
			zfJbxx.setcBqzy(zfJbxxDto.getCBqzy());
			zfJbxx.setcBqzylb(zfJbxxDto.getCBqzylb());
			zfJbxx.setcBqzj(zfJbxxDto.getCBqzj());
			zfJbxx.setcRjbz(zfJbxxDto.getCRjbz());
			
			zfJbxx.setcMsccLxqk(zfJbxxDto.getCMsccLxqk());
			zfJbxx.setnFjjnZe(zfJbxxDto.getNFjjnZe());
			zfJbxx.setnMspcjnZe(zfJbxxDto.getNMspcjnZe());
			zfJbxx.setnZjjnZe(zfJbxxDto.getNZjjnZe());
			zfJbxx.setnZltpjnZe(zfJbxxDto.getNZltpjnZe());
			zfJbxx.setcZydah(zfJbxxDto.getCZydah());
			zfJbxx.setcFdah(zfJbxxDto.getCFdah());
			zfJbxx.setcSzjy(zfJbxxDto.getCSzjy());
			zfJbxx.setcSzjq(zfJbxxDto.getCSzjq());
			zfJbxx.setcFylb(zfJbxxDto.getCFylb());
			zfJbxx.setcGz(zfJbxxDto.getCGz());
			zfJbxx.setcMqwhcd(zfJbxxDto.getCMqwhcd());
			zfJbxx.setcHjzh(zfJbxxDto.getCHjzh());
			zfJbxx.setcJsh(zfJbxxDto.getCJsh());
			zfJbxx.setcCwh(zfJbxxDto.getCCwh());
			zfJbxx.setcJggjMc(zfJbxxDto.getCJggjMc());
			zfJbxx.setcFgdj(zfJbxxDto.getCFgdj());
			zfJbxx.setcSyjg(zfJbxxDto.getCSyjg());
			zfJbxx.setdRjrq(Util.toDate(zfJbxxDto.getDRjrq(), Util.DF_DOT_DATE));
			zfJbxx.setcZmzSlt(zfJbxxDto.getCZmzSlt());
			zfJbxx.setcJg(zfJbxxDto.getCJg());
			zfJbxx.setcJtzz(zfJbxxDto.getCJtzz());
			zfJbxx.setcHjdz(zfJbxxDto.getCHjdz());
			zfJbxx.setcYpXz(zfJbxxDto.getCYpXz());
			zfJbxx.setcYpXq(zfJbxxDto.getCYpXq());
			zfJbxx.setdYpXqqr(Util.toDate(zfJbxxDto.getDYpXqqr(), Util.DF_DOT_DATE));
			zfJbxx.setdYpXqzr(Util.toDate(zfJbxxDto.getDYpXqzr(), Util.DF_DOT_DATE));
			zfJbxx.setcYpBzlb(zfJbxxDto.getCYpBzlb());
			zfJbxx.setcXxqZxxz(zfJbxxDto.getCXxqZxxz());
			zfJbxx.setcXxq(zfJbxxDto.getCXxq());
			zfJbxx.setdXxqQr(Util.toDate(zfJbxxDto.getDXxqQr(), Util.DF_DOT_DATE));
			zfJbxx.setdXxqZr(Util.toDate(zfJbxxDto.getDXxqZr(), Util.DF_DOT_DATE));
			zfJbxx.setdUrlTime(Util.toDate(zfJbxxDto.getDUrlTime(), Util.DF_DATE));
		}
		
		return zfJbxx;
	}

	/**
	 * Description: 罪犯基本信息Dto转罪犯当前状态实体类
	 * @param zfJbxxDto
	 * @return
	 */
	private ZfDqzt convertZfJbxxDtoToZfDqzt(ZfJbxxDto zfJbxxDto) {
		ZfDqzt zfDqzt = null;
		if(zfJbxxDto != null) {
			zfDqzt = new ZfDqzt();
			zfDqzt.setcZfbh(zfJbxxDto.getCZfbh());
			zfDqzt.setcId(zfJbxxDto.getCId());
			zfDqzt.setcZydah(zfJbxxDto.getCZydah());
			zfDqzt.setcFdah(zfJbxxDto.getCFdah());
			zfDqzt.setcSzjy(zfJbxxDto.getCSzjy());
			zfDqzt.setcSzjq(zfJbxxDto.getCSzjq());
			zfDqzt.setcFylb(zfJbxxDto.getCFylb());
			zfDqzt.setcMqwhcd(zfJbxxDto.getCMqwhcd());
			zfDqzt.setcGz(zfJbxxDto.getCGz());
			zfDqzt.setcMsccLxqk(zfJbxxDto.getCMsccLxqk());
			zfDqzt.setnFjjnZe(zfJbxxDto.getNFjjnZe());
			zfDqzt.setnMspcjnZe(zfJbxxDto.getNMspcjnZe());
			zfDqzt.setnZjjnZe(zfJbxxDto.getNZjjnZe());
			zfDqzt.setnZltpjnZe(zfJbxxDto.getNZltpjnZe());
			zfDqzt.setcJsh(zfJbxxDto.getCJsh());
			zfDqzt.setcCwh(zfJbxxDto.getCCwh());
			zfDqzt.setcHjzh(zfJbxxDto.getCHjzh());
			// zfDqzt.setcJggjJh(zfJbxxDto.getCJggjJh());
			zfDqzt.setcJggjMc(zfJbxxDto.getCJggjMc());
			zfDqzt.setcFgdj(zfJbxxDto.getCFgdj());
			zfDqzt.setdUrlTime(Util.toDate(zfJbxxDto.getDUrlTime(), Util.DF_DATE));
		}
		
		return zfDqzt;
	}
	
	/**
	 * Description: 罪犯同案犯DTO转化为罪犯同案犯实体类
	 * @param zfTafDto
	 * @return
	 */
	private ZfTaf convertZfTafDtoToZfTaf(ZfTafDto zfTafDto) {
		ZfTaf zfTaf = null;
		if(zfTafDto != null) {
			zfTaf = new ZfTaf();
			zfTaf.setcId(zfTafDto.getCId());
			zfTaf.setcZfId(zfTafDto.getCZfId());
			zfTaf.setdUrlTime(Util.toDate(zfTafDto.getDUrlTime(), Util.DF_DATE));
		}
		return zfTaf;
	}

	/**
	 * Description: 罪犯照片DTO转化为罪犯照片实体类
	 * @param zfJbxx
	 * @param zfPhotoDto
	 * @return
	 */
	private ZfPhoto convertZfPhotoDtoToZfPhoto(ZfPhotoDto zfPhotoDto) {
		ZfPhoto zfPhoto = null;
		if(zfPhotoDto != null) {
			zfPhoto = new ZfPhoto();
			zfPhoto.setcZfbh(zfPhotoDto.getCZfbh());
			zfPhoto.setcId(zfPhotoDto.getCId());
			zfPhoto.setcXm(zfPhotoDto.getCXm());
			zfPhoto.setcZplb(zfPhotoDto.getCZplb());
			zfPhoto.setcStorageid(zfPhotoDto.getCStorageid());
			zfPhoto.setdCjsj(Util.toDate(zfPhotoDto.getDCjsj(), Util.DF_DOT_DATE));
			zfPhoto.setdUrlTime(Util.toDate(zfPhotoDto.getDUrlTime(), Util.DF_DATE));
		}
		return zfPhoto;
	}

	/**
	 * Description: 罪犯离监信息DTO转化为罪犯离监信息实体类
	 * @param zfLjDto
	 * @return
	 */
	private ZfLj convertZfLjDtoToZfLj(ZfLjDto zfLjDto) {
		ZfLj zfLj = null;
		if(zfLjDto != null) {
			zfLj = new ZfLj();
			zfLj.setcZfbh(zfLjDto.getCZfbh());
			zfLj.setcId(zfLjDto.getCId());
			zfLj.setdLjrq(Util.toDate(zfLjDto.getDLjrq(), Util.DF_DOT_DATE));
			zfLj.setcLjlb(zfLjDto.getCLjlb());
			zfLj.setcQx(zfLjDto.getCQx());
			zfLj.setcQxQh(zfLjDto.getCQxQh());
			zfLj.setcQxMx(zfLjDto.getCQxMx());
			zfLj.setdUrlTime(Util.toDate(zfLjDto.getDUrlTime(), Util.DF_DATE));
		}
		return zfLj;
	}

	/**
	 * Description: 罪犯同案犯DTO转化为罪犯同案犯实体类
	 * @param zfSyDto
	 * @return
	 */
	private ZfSy convertZfSyDtoToZfSy(ZfSyDto zfSyDto) {
		ZfSy zfSy = null;
		if(zfSyDto != null) {
			zfSy = new ZfSy();
			zfSy.setcZfbh(zfSyDto.getCZfbh());
			zfSy.setcId(zfSyDto.getCId());
			zfSy.setcSylb(zfSyDto.getCSylb());
			zfSy.setdSyrq(Util.toDate(zfSyDto.getDSyrq(), Util.DF_DOT_DATE));
			zfSy.setcSyjg(zfSyDto.getCSyjg());
			zfSy.setcSyjgQh(zfSyDto.getCSyjgQh());
			zfSy.setcSyjgMx(zfSyDto.getCSyjgMx());
			zfSy.setdUrlTime(Util.toDate(zfSyDto.getDUrlTime(), Util.DF_DATE));
		}
		return zfSy;
	}

	/**
	 * Description: 罪犯同案犯DTO list转化为罪犯同案犯实体类list
	 * @param zfTafDtoList
	 * @return
	 */
	private List<ZfTaf> convertZfTafDtoToZfTaf(List<ZfTafDto> zfTafDtoList) {
		List<ZfTaf> zfTafList = null;
		if(zfTafDtoList != null && zfTafDtoList.size() > 0) {
			zfTafList = new ArrayList<ZfTaf>();
			for(ZfTafDto zfTafDto : zfTafDtoList) {
				ZfTaf zfTaf = this.convertZfTafDtoToZfTaf(zfTafDto);
				zfTafList.add(zfTaf);
			}
		}
		return zfTafList;
	}

	/**
	 * Description: 罪犯照片DTO list转化为罪犯照片实体类list
	 * @param zfPhotoDtoList
	 * @return
	 */
	private List<ZfPhoto> convertZfPhotoDtoToZfPhoto(List<ZfPhotoDto> zfPhotoDtoList) {
		List<ZfPhoto> zfPhotoList = null;
		if(zfPhotoDtoList != null && zfPhotoDtoList.size() > 0) {
			zfPhotoList = new ArrayList<ZfPhoto>();
			for(ZfPhotoDto zfPhotoDto : zfPhotoDtoList) {
				ZfPhoto zfPhoto = this.convertZfPhotoDtoToZfPhoto(zfPhotoDto);
				zfPhotoList.add(zfPhoto);
			}
		}
		return zfPhotoList;
	}

	/**
	 * Description: 罪犯离监信息DTO list转化为罪犯离监信息实体类list
	 * @param zfLjDtoList
	 * @return
	 */
	private List<ZfLj> convertZfLjDtoToZfLj(List<ZfLjDto> zfLjDtoList) {
		List<ZfLj> zfLjList = null;
		if(zfLjDtoList != null && zfLjDtoList.size() > 0) {
			zfLjList = new ArrayList<ZfLj>();
			for(ZfLjDto zfLjDto : zfLjDtoList) {
				ZfLj zfTaf = this.convertZfLjDtoToZfLj(zfLjDto);
				zfLjList.add(zfTaf);
			}
		}
		return zfLjList;
	}

	/**
	 * Description: 罪犯收押DTO list转化为罪犯收押实体类list
	 * @param zfSyDtoList
	 * @return
	 */
	private List<ZfSy> convertZfSyDtoToZfSy(List<ZfSyDto> zfSyDtoList) {
		List<ZfSy> zfSyList = null;
		if(zfSyDtoList != null && zfSyDtoList.size() > 0) {
			zfSyList = new ArrayList<ZfSy>();
			for(ZfSyDto zfTafDto : zfSyDtoList) {
				ZfSy zfSy = this.convertZfSyDtoToZfSy(zfTafDto);
				zfSyList.add(zfSy);
			}
		}
		return zfSyList;
	}

	/**
	 * 保存罪犯基本信息数据
	 * @param zfJbxx
	 */
	@Transactional
	private synchronized void synchroZfJbxx(ZfJbxx zfJbxx) {
		if(zfJbxx != null) {
			ZfJbxx zfJbxxOP = null;
			List<ZfJbxx> oldZfJbxxList = this.getDao().findByCId(zfJbxx.getcId());
			if(oldZfJbxxList != null && oldZfJbxxList.size() > 0) {
				zfJbxxOP = oldZfJbxxList.get(0);
				// 罪犯基本信息属性拷贝
				BeanUtils.copyProperties(zfJbxx, zfJbxxOP, "id");

				System.out.println("更新罪犯基本信息，罪犯编号=" + zfJbxx.getcZfbh());
				logger.info("更新罪犯基本信息，罪犯编号=" + zfJbxx.getcZfbh());
				
				// 修改
				this.getDao().update(zfJbxxOP);
			} else {
				// 创建新对象
				zfJbxxOP = new ZfJbxx();
				zfJbxxOP.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				
				// 罪犯基本信息属性拷贝
				BeanUtils.copyProperties(zfJbxx, zfJbxxOP, "id");

				System.out.println("新增罪犯基本信息，罪犯编号=" + zfJbxx.getcZfbh());
				logger.info("新增罪犯基本信息，罪犯编号=" + zfJbxx.getcZfbh());
				
				// 新增
				this.getDao().insert(zfJbxxOP);
			}
		}
	}

	/**
	 * 保存罪犯当前状态数据
	 * @param zfDqzt
	 */
	@Transactional
	private synchronized void synchroZfDqzt(ZfDqzt zfDqzt) {
		if(zfDqzt != null) {
			ZfDqzt zfDqztOP = null;
			List<ZfDqzt> oldZfDqztList = this.zfDqztDao.findByCId(zfDqzt.getcId());
			if(oldZfDqztList != null && oldZfDqztList.size() > 0) {
				zfDqztOP = oldZfDqztList.get(0);
				// 罪犯当前状态属性拷贝
				BeanUtils.copyProperties(zfDqzt, zfDqztOP, "id");

				System.out.println("更新罪犯当前状态信息，罪犯编号=" + zfDqzt.getcZfbh());
				logger.info("更新罪犯当前状态信息，罪犯编号=" + zfDqzt.getcZfbh());
				
				// 修改
				this.zfDqztDao.update(zfDqztOP);
			} else {
				// 创建新对象
				zfDqztOP = new ZfDqzt();
				zfDqztOP.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				
				// 罪犯当前状态属性拷贝
				BeanUtils.copyProperties(zfDqzt, zfDqztOP, "id");

				System.out.println("新增罪犯当前状态信息，罪犯编号=" + zfDqzt.getcZfbh());
				logger.info("新增罪犯当前状态信息，罪犯编号=" + zfDqzt.getcZfbh());
				
				// 新增
				this.zfDqztDao.insert(zfDqztOP);
			}
		}
	}

	/**
	 * 保存罪犯同案犯数据
	 * @param zfTaf
	 */
	@Transactional
	private synchronized void synchroZfTaf(ZfTaf zfTaf) {
		if(zfTaf != null) {
			ZfTaf zfTafOP = null;
			List<ZfTaf> oldZfTafList = this.zfTafDao.findByCIdAndCZfId(zfTaf.getcId(), zfTaf.getcZfId());
			if(oldZfTafList != null && oldZfTafList.size() > 0) {
				// zfTafOP = oldZfTafList.get(0);
				// 罪犯同案犯属性拷贝
				// BeanUtils.copyProperties(zfTaf, zfTafOP, "id");
				
				// 修改
				// TODO 因表中无额外信息，无需更新
				// this.zfTafDao.update(zfTafOP);
			} else {
				// 创建新对象
				zfTafOP = new ZfTaf();
				zfTafOP.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				
				// 罪犯同案犯属性拷贝
				BeanUtils.copyProperties(zfTaf, zfTafOP, "id");

				System.out.println("新增罪犯同案犯信息，罪犯标识=" + zfTafOP.getcId() + "，同案犯标识=" + zfTafOP.getcZfId());
				logger.info("新增罪犯同案犯信息，罪犯标识=" + zfTafOP.getcId() + "，同案犯标识=" + zfTafOP.getcZfId());
				// 新增
				this.zfTafDao.insert(zfTafOP);
			}
		}
	}
	
	/**
	 * 保存罪犯照片数据
	 * @param zfPhoto
	 * @throws BusinessLayerException 
	 */
	@Transactional
	private synchronized void synchroZfPhoto(ZfPhoto zfPhoto) {
		if(zfPhoto != null) {
			ZfPhoto zfPhotoOP = null;
			
			List<ZfPhoto> oldZfPhotoList = this.zfPhotoDao.findByCIdAndCStorageid(zfPhoto.getcId(), zfPhoto.getcStorageid());
			
			if(oldZfPhotoList != null && oldZfPhotoList.size() > 0) {
				zfPhotoOP = oldZfPhotoList.get(0);
				// 罪犯照片属性拷贝
				BeanUtils.copyProperties(zfPhoto, zfPhotoOP, "id");
				
				// 照片类别转换
				String photoCategory = CommonConstant.PhotoCategoryValue.get(zfPhotoOP.getcZplb());
				photoCategory = Util.isNull(photoCategory) ? "Unknow" : photoCategory;
				logger.info("照片类别【" + zfPhotoOP.getcZplb() + "】，转化为" + photoCategory);
				
				// 设置照片在本系统存储的名称
				zfPhotoOP.setcFileName(zfPhotoOP.getcZfbh() + "_" + photoCategory + ".jpg");
				
				// 设置照片在本系统存储的相对路径
				zfPhotoOP.setcFilePath("/" + photoCategory + "/" + zfPhotoOP.getcFileName());

				System.out.println("更新罪犯照片信息，罪犯编号=" + zfPhotoOP.getcZfbh() + "，照片类别=" + zfPhotoOP.getcZplb() + "，照片预览ID=" + zfPhotoOP.getcStorageid());
				logger.info("更新罪犯照片信息，罪犯编号=" + zfPhotoOP.getcZfbh() + "，照片类别=" + zfPhotoOP.getcZplb() + "，照片预览ID=" + zfPhotoOP.getcStorageid());
				
				// 修改
				this.zfPhotoDao.update(zfPhotoOP);
			} else {
				// 创建新对象
				zfPhotoOP = new ZfPhoto();
				zfPhotoOP.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				
				// 罪犯照片属性拷贝
				BeanUtils.copyProperties(zfPhoto, zfPhotoOP, "id");

				// 照片类别转换
				String photoCategory = CommonConstant.PhotoCategoryValue.get(zfPhotoOP.getcZplb());
				photoCategory = Util.isNull(photoCategory) ? "Unknow" : photoCategory;
				logger.info("照片类别【" + zfPhotoOP.getcZplb() + "】，转化为" + photoCategory);
				
				// 设置照片在本系统存储的名称
				zfPhotoOP.setcFileName(zfPhotoOP.getcZfbh() + "_" + photoCategory + ".jpg");
				
				// 设置照片在本系统存储的相对路径
				zfPhotoOP.setcFilePath("/" + photoCategory + "/" + zfPhotoOP.getcFileName());

				System.out.println("新增罪犯照片信息，罪犯编号=" + zfPhotoOP.getcZfbh() + "，照片类别=" + zfPhotoOP.getcZplb() + "，照片预览ID=" + zfPhotoOP.getcStorageid());
				logger.info("新增罪犯照片信息，罪犯编号=" + zfPhotoOP.getcZfbh() + "，照片类别=" + zfPhotoOP.getcZplb() + "，照片预览ID=" + zfPhotoOP.getcStorageid());
				
				// 新增
				this.zfPhotoDao.insert(zfPhotoOP);
			}
			
			if(zfPhotoOP != null) {
				// 将照片下载本系统（安防平台）
				try {
					if(downloadZfPhoto) {
						this.downloadZfPhoto(zfPhotoOP);
					}
				} catch (BusinessLayerException e) {
					System.err.println(e.getMessage());
					// return;
				}
			}
		}
	}

	/**
	 * 保存罪犯收押数据
	 * @param zfSy
	 */
	@Transactional
	private synchronized void synchroZfSy(ZfSy zfSy) {
		if(zfSy != null) {
			ZfSy zfSyOP = null;
			List<ZfSy> oldZfSyList = this.zfSyDao.findByCIdAndCSylbAndDSyrq(zfSy.getcId(), zfSy.getcSylb(), Util.toStr(zfSy.getdSyrq(), Util.DF_DATE));
			if(oldZfSyList != null && oldZfSyList.size() > 0) {
				zfSyOP = oldZfSyList.get(0);
				// 罪犯照片属性拷贝
				BeanUtils.copyProperties(zfSy, zfSyOP, "id");

				System.out.println("更新罪犯收押信息，罪犯编号=" + zfSyOP.getcZfbh());
				logger.info("更新罪犯收押信息，罪犯编号=" + zfSyOP.getcZfbh());
				
				// 修改
				this.zfSyDao.update(zfSyOP);
			} else {
				// 创建新对象
				zfSyOP = new ZfSy();
				zfSyOP.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				
				// 罪犯照片属性拷贝
				BeanUtils.copyProperties(zfSy, zfSyOP, "id");

				System.out.println("新增罪犯收押信息，罪犯编号=" + zfSyOP.getcZfbh());
				logger.info("新增罪犯收押信息，罪犯编号=" + zfSyOP.getcZfbh());
				
				// 新增
				this.zfSyDao.insert(zfSyOP);
			}
		}
	}

	/**
	 * 保存罪犯离监数据
	 * @param zfLj
	 */
	@Transactional
	private synchronized void synchroZfLj(ZfLj zfLj) {
		if(zfLj != null) {
			ZfLj zfLjOP = null;
			List<ZfLj> oldZfLjList = this.zfLjDao.findByCIdAndCLjlbAndDLjrq(zfLj.getcId(), zfLj.getcLjlb(), Util.toStr(zfLj.getdLjrq(), Util.DF_DATE));
			if(oldZfLjList != null && oldZfLjList.size() > 0) {
				zfLjOP = oldZfLjList.get(0);
				// 罪犯离监属性拷贝
				BeanUtils.copyProperties(zfLj, zfLjOP, "id");
				
				System.out.println("更新罪犯离监信息，罪犯编号=" + zfLjOP.getcZfbh());
				logger.info("更新罪犯离监信息，罪犯编号=" + zfLjOP.getcZfbh());
				
				// 修改
				this.zfLjDao.update(zfLjOP);
			} else {
				// 创建新对象
				zfLjOP = new ZfLj();
				zfLjOP.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				
				// 罪犯离监属性拷贝
				BeanUtils.copyProperties(zfLj, zfLjOP, "id");

				System.out.println("新增罪犯离监信息，罪犯编号=" + zfLjOP.getcZfbh());
				logger.info("新增罪犯离监信息，罪犯编号=" + zfLjOP.getcZfbh());
				
				// 新增
				this.zfLjDao.insert(zfLjOP);
			}
		}
	}

	/**
	 * 同步罪犯同案犯信息
	 * @param zfTafList
	 */
	private void synchroZfTafList(List<ZfTaf> zfTafList) {
		if(zfTafList != null && zfTafList.size() > 0) {
			for(ZfTaf zfTaf : zfTafList) {
				this.synchroZfTaf(zfTaf);
			}
		}
	}
	
	/**
	 * 同步罪犯照片信息
	 * @param zfTafList
	 */
	private void synchroZfPhoto(List<ZfPhoto> zfPhotoList) {
		if(zfPhotoList != null && zfPhotoList.size() > 0) {
			for(ZfPhoto zfPhoto : zfPhotoList) {
				this.synchroZfPhoto(zfPhoto);
			}
		}
	}

	/**
	 * 同步罪犯收押信息
	 * @param zfSyList
	 */
	private void synchroZfSy(List<ZfSy> zfSyList) {
		if(zfSyList != null && zfSyList.size() > 0) {
			for(ZfSy zfSy : zfSyList) {
				this.synchroZfSy(zfSy);
			}
		}
	}
	

	/**
	 * 同步罪犯离监信息
	 * @param zfLjList
	 */
	private void synchroZfLj(List<ZfLj> zfLjList) {
		if(zfLjList != null && zfLjList.size() > 0) {
			for(ZfLj zfTaf : zfLjList) {
				this.synchroZfLj(zfTaf);
			}
		}
	}

}
