package com.cesgroup.prison.zfxx.zfjbxx.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.jobs.constants.Synchro;
import com.cesgroup.prison.jobs.utils.ZfywHttpClientUtil;
import com.cesgroup.prison.zfxx.zfjbxx.dao.ZfJbxxDao;
import com.cesgroup.prison.zfxx.zfjbxx.entity.ZfJbxx;
import com.cesgroup.prison.zfxx.zfjbxx.service.ZfJbxxTbService;
import com.cesgroup.prison.zfxx.zfphoto.dao.ZfPhotoDao;
import com.cesgroup.prison.zfxx.zfphoto.entity.ZfPhoto;
import com.cesgroup.prison.zfxx.zfsy.dao.ZfSyDao;
import com.cesgroup.prison.zfxx.zfsy.entity.ZfSy;
import com.cesgroup.prison.zfxx.zftaf.dao.ZfTafDao;
import com.cesgroup.prison.zfxx.zftaf.entity.ZfTaf;
@Service("zfJbxxTbService")
@Transactional
public class ZfJbxxTbServiceImpl extends BaseDaoService<ZfJbxx, String, ZfJbxxDao> implements ZfJbxxTbService {

	@Autowired
	private ZfJbxxDao zfJbxxDao;
	@Autowired
	private ZfTafDao zfTafDao;
	@Autowired
	private ZfPhotoDao zfPhotoDao;
	@Autowired
	private ZfSyDao zfSyDao;
	
	@Override
	public void synchroZfJbxx(String corp, String jyId, String time, String cjpc) {
		List<Map<String, Object>> data = ZfywHttpClientUtil.getData(Synchro.Zfyw.ENTITY_ZF_JBXX, corp, time);
		if (data != null) {
			//罪犯
			Map<String, Object> zfjbxx = null;
			ZfJbxx jbxx = null;
			List<ZfJbxx> list = new ArrayList<ZfJbxx>();
			//同案犯
			List<Map<String, Object>> zfTafList = null;
			ZfTaf zfTaf = null;
			List<ZfTaf> tafList = new ArrayList<ZfTaf>();
			//照片
			List<Map<String, Object>> zfPhotoList = null;
			ZfPhoto zfPhoto = null;
			List<ZfPhoto> photoList = new ArrayList<ZfPhoto>();
			Date cjrq = new Date();
			Date urlTime = Util.toDate(time, Util.DF_DATE);
			for (Map<String, Object> temp : data) {
				zfjbxx = (Map<String, Object>)temp.get(Synchro.Member.DEFAULT_ZFJBXX);
				jbxx = new ZfJbxx();
				this.convertZfJbxx(jbxx, zfjbxx, jyId, urlTime, cjpc, cjrq);
				list.add(jbxx);
				
				//同案犯信息
				zfTafList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.DEFAULT_ZFTAF);
				if (zfTafList != null && zfTafList.size() > 0) {
					for (Map<String, Object> map : zfTafList) {
						zfTaf = new ZfTaf();
						this.convertZfTaf(zfTaf, map, zfjbxx, urlTime, cjpc, cjrq);
						tafList.add(zfTaf);
					}
				}
				
				//照片信息
				zfPhotoList = (List<Map<String, Object>>)zfjbxx.get(Synchro.Member.DEFAULT_ZFDMTXXPHOTO);
				if (zfPhotoList != null && zfPhotoList.size() > 0) {
					for (Map<String, Object> map : zfPhotoList) {
						zfPhoto = new ZfPhoto();
						this.convertZfPhoto(zfPhoto, map, zfjbxx, urlTime, cjpc, cjrq);
						photoList.add(zfPhoto);
					}
				}
			}
			this.batchInsertZfJbxx(list);
			this.batchInsertZfTaf(tafList);
			this.batchInsertZfPhoto(photoList);
		}
	}

	@Transactional(readOnly = true)
	private void convertZfJbxx(ZfJbxx jbxx, Map<String, Object> zfjbxx, String jyId, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		jbxx.setcZfbh((String)zfjbxx.get("CZfbh"));
		jbxx.setcId((String)zfjbxx.get("CId"));
		jbxx.setcXm((String)zfjbxx.get("CXm"));
		jbxx.setcZsym((String)zfjbxx.get("CZsym"));
		jbxx.setcXb((String)zfjbxx.get("CXb"));
		jbxx.setdCsrq(Util.toDate((String)zfjbxx.get("DCsrq"), Util.DF_DOT_DATE));
		jbxx.setcMz((String)zfjbxx.get("CMz"));
		jbxx.setcBqzzmm((String)zfjbxx.get("CBqzzmm"));
		jbxx.setcBqwhcd((String)zfjbxx.get("CBqwhcd"));
		jbxx.setcBqhyzk((String)zfjbxx.get("CBqhyzk"));
		jbxx.setcSxzy((String)zfjbxx.get("CSxzy"));
		jbxx.setcBqzy((String)zfjbxx.get("CBqzy"));
		jbxx.setcBqzylb((String)zfjbxx.get("CBqzylb"));
		jbxx.setcBqzj((String)zfjbxx.get("CBqzj"));		
		jbxx.setcMsccLxqk((String)zfjbxx.get("CMsccLxqk"));
		jbxx.setnFjjnZe((String)zfjbxx.get("NFjjnZe"));
		jbxx.setnMspcjnZe((String)zfjbxx.get("NMspcjnZe"));
		jbxx.setnZjjnZe((String)zfjbxx.get("NZjjnZe"));
		jbxx.setnZltpjnZe((String)zfjbxx.get("NZltpjnZe"));
		jbxx.setcZydah((String)zfjbxx.get("CZydah"));
		jbxx.setcFdah((String)zfjbxx.get("CFdah"));
		jbxx.setcSzjy((String)zfjbxx.get("CSzjy"));
		jbxx.setcSzjq((String)zfjbxx.get("CSzjq"));
		jbxx.setcFylb((String)zfjbxx.get("CFylb"));
		jbxx.setcGz((String)zfjbxx.get("CGz"));
		jbxx.setcMqwhcd((String)zfjbxx.get("CMqwhcd"));
		jbxx.setcHjzh((String)zfjbxx.get("CHjzh"));
		jbxx.setcJsh((String)zfjbxx.get("CJsh"));
		jbxx.setcCwh((String)zfjbxx.get("CCwh"));
		jbxx.setcJggjMc((String)zfjbxx.get("CJggjMc"));
		jbxx.setcFgdj((String)zfjbxx.get("CFgdj"));
		jbxx.setcSyjg((String)zfjbxx.get("CSyjg"));
		jbxx.setcRjbz((String)zfjbxx.get("CRjbz"));
		jbxx.setdRjrq(Util.toDate((String)zfjbxx.get("DRjrq"), Util.DF_DOT_DATE));
		jbxx.setcZmzSlt((String)zfjbxx.get("CZmzSlt"));
		jbxx.setcJg((String)zfjbxx.get("CJg"));
		jbxx.setcJtzz((String)zfjbxx.get("CJtzz"));
		jbxx.setcHjdz((String)zfjbxx.get("CHjdz"));
		jbxx.setcYpXz((String)zfjbxx.get("CYpXz"));
		jbxx.setcYpXq((String)zfjbxx.get("CYpXq"));
		jbxx.setdYpXqqr(Util.toDate((String)zfjbxx.get("DYpXqqr"), Util.DF_DOT_DATE));
		jbxx.setdYpXqzr(Util.toDate((String)zfjbxx.get("DYpXqzr"), Util.DF_DOT_DATE));
		jbxx.setcYpBzlb((String)zfjbxx.get("CYpBzlb"));
		jbxx.setcXxqZxxz((String)zfjbxx.get("CXxqZxxz"));
		jbxx.setcXxq((String)zfjbxx.get("CXxq"));
		jbxx.setdXxqQr(Util.toDate((String)zfjbxx.get("DXxqQr"), Util.DF_DOT_DATE));
		jbxx.setdXxqZr(Util.toDate((String)zfjbxx.get("DXxqZr"), Util.DF_DOT_DATE));
		jbxx.setcZjhm((String)zfjbxx.get("CZjhm"));
		jbxx.setcZjlb((String)zfjbxx.get("CZjlb"));
		jbxx.setcSzb((String)zfjbxx.get("CSzb"));
		//公共字段
		jbxx.setId(Synchro.getUUID());
		jbxx.setdUrlTime(urlTime);
		jbxx.setcJyId(jyId);
		jbxx.setcCjpc(cjpc);
		jbxx.setdCjrq(cjrq);
	}

	@Transactional(readOnly = true)
	private void convertZfTaf(ZfTaf zfTaf, Map<String, Object> map, Map<String, Object> zfjbxx, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfTaf.setcId((String)zfjbxx.get("CId"));
		zfTaf.setcZfbh((String)zfjbxx.get("CZfbh"));
		//同案犯信息
		zfTaf.setcZfId((String)map.get("CZfId"));
		//公共字段
		zfTaf.setId(Synchro.getUUID());
		zfTaf.setdUrlTime(urlTime);
		zfTaf.setcCjpc(cjpc);
		zfTaf.setdCjrq(cjrq);
	}
	
	@Transactional(readOnly = true)
	private void convertZfPhoto(ZfPhoto zfPhoto, Map<String, Object> map, Map<String, Object> zfjbxx, Date urlTime, String cjpc, Date cjrq) {
		//罪犯信息
		zfPhoto.setcId((String)zfjbxx.get("CId"));
		zfPhoto.setcZfbh((String)zfjbxx.get("CZfbh"));
		zfPhoto.setcXm((String)zfjbxx.get("CXm"));
		//同案犯信息
		zfPhoto.setcZplb((String)map.get("CZplb"));
		zfPhoto.setcStorageid((String)map.get("CStorageid"));
		zfPhoto.setdCjsj(Util.toDate((String)map.get("DCjsj"), Util.DF_DOT_DATE));
		String photoCategory = CommonConstant.PhotoCategoryValue.get(zfPhoto.getcZplb());
		photoCategory = Util.isNull(photoCategory) ? "Unknow" : photoCategory;
		// 设置照片在本系统存储的名称
		zfPhoto.setcFileName(zfPhoto.getcZfbh() + "_" + photoCategory + ".jpg");
		// 设置照片在本系统存储的相对路径
		zfPhoto.setcFilePath("/" + photoCategory + "/" + zfPhoto.getcFileName());
		//公共字段
		zfPhoto.setId(Synchro.getUUID());
		zfPhoto.setdUrlTime(urlTime);
		zfPhoto.setcCjpc(cjpc);
		zfPhoto.setdCjrq(cjrq);
	}
	
	private void batchInsertZfJbxx(List<ZfJbxx> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_30;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfJbxxDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfJbxxDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	private void batchInsertZfTaf(List<ZfTaf> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfTafDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfTafDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	private void batchInsertZfPhoto(List<ZfPhoto> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					zfPhotoDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					zfPhotoDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
	@Override
	public void backups() {
		zfJbxxDao.insertHisBySource();
		zfJbxxDao.deleteAll();
		zfTafDao.insertHisBySource();
		zfTafDao.deleteAll();
		zfPhotoDao.insertHisBySource();
		zfPhotoDao.deleteAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public void synchroZfPhoto() {
		List<ZfPhoto> list = zfPhotoDao.selectAll();
		int size = Synchro.BathSize.BATCH_SIZE_2000;
		final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
		final CountDownLatch latch = new CountDownLatch(num);
		for (int i = 0; i < num; i++) {
			final int j = i;
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						List<ZfPhoto> photoList = null;
						if (j < num - 1) {
							photoList = list.subList(j * size, (j + 1) * size);
						} else {
							photoList = list.subList(j * size, list.size());
						}
						for (ZfPhoto temp : photoList) {
							downloadPhoto(temp);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
	                	latch.countDown();
	                }
				}
			}).start();
		}
		try {
	    	latch.await();
		} catch (InterruptedException e) {
	    	 e.printStackTrace();
		}
	}
	
	@Override
	@Transactional(readOnly = true)
	public void synchroIncZfPhoto(String time) {
		ZfSy query = new ZfSy();
		query.setdUrlTime(Util.toDate(time, Util.DF_DATE));
		List<ZfSy> list = zfSyDao.selectByEntity(query);
		ZfPhoto zfPhoto = null;
		List<ZfPhoto> zfPhotoList = null;
		for (ZfSy zfSy : list) {
			try {
				zfPhoto = new ZfPhoto();
				zfPhoto.setcZfbh(zfSy.getcZfbh());
				zfPhotoList = zfPhotoDao.selectByEntity(zfPhoto);
				for (ZfPhoto photo : zfPhotoList) {
					this.downloadPhoto(photo);
				}
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
		}
	}
	
	@Transactional(readOnly = true)
	private void downloadPhoto(ZfPhoto photo) {
		HttpEntity httpEntity = null;
		InputStream inputStream = null;
		try {
			httpEntity = ZfywHttpClientUtil.httpGet(Synchro.HOST_PHOTO + "/" + photo.getcStorageid());
			inputStream = httpEntity.getContent();
			FileUtils.copyToFile(inputStream, new File(Synchro.ZF_PHOTO_ROOT_PATH + photo.getcFilePath()));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
