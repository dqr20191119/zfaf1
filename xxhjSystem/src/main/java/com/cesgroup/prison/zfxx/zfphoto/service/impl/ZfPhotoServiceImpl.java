package com.cesgroup.prison.zfxx.zfphoto.service.impl;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.zfxx.zfphoto.dao.ZfPhotoDao;
import com.cesgroup.prison.zfxx.zfphoto.entity.ZfPhoto;
import com.cesgroup.prison.zfxx.zfphoto.service.ZfPhotoService;

/**
 * Description: 罪犯照片业务访问接口实现类
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
@Service("zfPhotoService")
public class ZfPhotoServiceImpl extends BaseDaoService<ZfPhoto, String, ZfPhotoDao> implements ZfPhotoService {
	
}
