package com.cesgroup.prison.jfsb.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.broadcastPlay.dao.BroadcastRecordDao;
import com.cesgroup.prison.broadcastPlay.dto.BroadcastRecordDto;
import com.cesgroup.prison.broadcastPlay.entity.BroadcastRecord;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.dao.AreadeviceMapper;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.dao.BroadcastFileDao;
import com.cesgroup.prison.jfsb.dao.BroadcastMapper;
import com.cesgroup.prison.jfsb.dto.BroadcastEntityDto;
import com.cesgroup.prison.jfsb.entity.BroadcastEntity;
import com.cesgroup.prison.jfsb.entity.BroadcastFile;
import com.cesgroup.prison.jfsb.service.BroadcastService;

/**      
* @projectName：prison   
* @ClassName：BroadcastServiceImpl   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月20日 下午3:32:51   
* @version        
*/
@Service
@Transactional
public class BroadcastServiceImpl extends BaseDaoService<BroadcastEntity, String, BroadcastMapper> implements BroadcastService {
	private static final Logger logger = LoggerFactory.getLogger(BroadcastServiceImpl.class);

	@Resource
	private BroadcastMapper mapper;

	@Resource
	private AreadeviceMapper areadeviceMapper;
	/**
	 * 广播记录数据库访问接口
	 */
	@Resource
	private BroadcastRecordDao broadcastRecordDao;
	/**
	 * 广播媒体库文件数据访问接口
	 */
	@Resource
	private BroadcastFileDao broadcastFileDao;

	@Override
	public void deleteByIds(List<String> ids) {
		mapper.deleteByIds(ids);
	}

	@Override
	public AjaxMessage addInfo(BroadcastEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();

			BroadcastEntity broadcastEntity = new BroadcastEntity();
			broadcastEntity.setBbdCusNumber(userBean.getCusNumber());
			broadcastEntity.setBbdIdnty(entity.getBbdIdnty());
			if (getDao().selectCountByEntity(broadcastEntity) > 0) {
				flag = false;
				obj = "编号【" + entity.getBbdIdnty() + "】重复，保存失败";
			} else {
				entity.setBbdCusNumber(userBean.getCusNumber());
				entity.setBbdCrteTime(date);
				entity.setBbdCrteUserId(userId);
				entity.setBbdUpdtTime(date);
				entity.setBbdUpdtUserId(userId);
				mapper.insert(entity);
				flag = true;
				obj = "保存成功";
			}
		} catch (Exception e) {
			flag = false;
			obj = "保存广播发生异常！";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;

	}

	@Override
	public void updateInfo(BroadcastEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setBbdUpdtTime(date);
			entity.setBbdUpdtUserId(userId);
			map.put("broadcastEntity", entity);
		}
		mapper.updateInfo(map);
	}

	@Override
	public Page<Map<String, Object>> listAll(BroadcastEntity entity, Pageable pageable) {
		try {
			Map<String, Object> map = new HashMap<>();
			if (entity != null) {
				map.put("broadcastEntity", entity);
			}
			return mapper.listAll(map, pageable);
		} catch (Exception e) {
			logger.error("分页查询广播设备列表信息发生异常" + e);
		}
		return null;
	}

	@Override
	public BroadcastEntity findById(String id) {
		return this.getDao().findById(id);
	}

	@Override
	public BroadcastEntityDto queryBroadcastEntityDtoById(String id) throws BusinessLayerException {
		try {
			BroadcastEntityDto dto = new BroadcastEntityDto();
			if (Util.notNull(id)) {
				BroadcastEntity entity = this.getDao().findById(id);

				if (entity != null) {
					dto.setId(entity.getId());
					dto.setBbdCusNumber(entity.getBbdCusNumber());
					dto.setBbdIdnty(entity.getBbdIdnty());
					dto.setBbdName(entity.getBbdName());
					dto.setBbdBrand(entity.getBbdBrand());
					dto.setBbdSttsIndc(entity.getBbdSttsIndc());
					dto.setBbdCameraId(entity.getBbdCameraId());
					dto.setBbdCameraName(entity.getBbdCameraName());
					// 根据广播设备最近的广播播放记录ID，查询广播播放记录DTO
					BroadcastRecordDto broadcastRecordDto = this.queryBroadcastRecordDtoByBroadcastRecordId(entity.getBbdLatestRecordId());
					dto.setBbdLatestRecord(broadcastRecordDto);
				}
			}
			return dto;
		} catch (Exception e) {
			throw new BusinessLayerException("根据广播设备主键ID，查询广播设备及其最近的播放记录发生异常" + e);
		}
	}

	/**
	 * 根据广播播放记录ID，查询广播播放记录，并封装成BroadcastRecordDto类型数据
	 * @param broadcastRecordId
	 * @return
	 */
	private BroadcastRecordDto queryBroadcastRecordDtoByBroadcastRecordId(String broadcastRecordId) {
		if(broadcastRecordId == null || "".equals(broadcastRecordId)) {
			return null;
		}
		BroadcastRecord broadcastRecord = this.broadcastRecordDao.findById(broadcastRecordId);
		if (broadcastRecord == null) {
			return null;
		}
		BroadcastRecordDto broadcastRecordDto = new BroadcastRecordDto();
		broadcastRecordDto.setId(broadcastRecord.getId());
		broadcastRecordDto.setBroadcastId(broadcastRecord.getBroadcastId());
		broadcastRecordDto.setCusNumber(broadcastRecord.getCusNumber());
		broadcastRecordDto.setContentType(broadcastRecord.getContentType());
		broadcastRecordDto.setContent(broadcastRecord.getContent());
		String contentMappingName = "";
		if(CommonConstant.BroadcastType.AUDIO.equals(broadcastRecord.getContentType())) {
			String broadcastFileIdStr = broadcastRecord.getContent();
			String[] broadcastFileIdArr = broadcastFileIdStr != null ? broadcastFileIdStr.split(",") : null;
			if(broadcastFileIdArr != null && broadcastFileIdArr.length > 0) {
				List<BroadcastFile> broadcastFileList = this.broadcastFileDao.findByIdList(Arrays.asList(broadcastFileIdArr));
				if(broadcastFileList != null && broadcastFileList.size() > 0) {
					for(BroadcastFile broadcastFile : broadcastFileList) {
						contentMappingName += broadcastFile.getBfdName() + ",";
					}
				}
			}
		}
		if(contentMappingName != null && !"".equals(contentMappingName)) {
			contentMappingName = contentMappingName.substring(0, contentMappingName.lastIndexOf(","));
		}
		broadcastRecordDto.setContentMappingName(contentMappingName);
		return broadcastRecordDto;
	}



	/**
	 * 将cameraList中符合条件的device放入areaList
	 *
	 * @param areaList
	 * @param deviceList
	 * @return
	 */
	private List<Map<String, Object>> packageAreaListAndDvcList(List<Map<String, Object>> areaList, List<Map<String, Object>> deviceList) {
		if(areaList == null || areaList.size() <= 0) {
			return areaList;
		}
		if(deviceList == null || deviceList.size() <= 0) {
			return areaList;
		}
		// 循环areaList获取areaIdSet
		Set<String> areaIdSet = new HashSet<String>();
		for(int i=0; i<areaList.size(); i++) {
			Map<String, Object> currentArea = areaList.get(i);
			String currentAreaId = currentArea.get("id") != null ? currentArea.get("id").toString() : null;
			if(currentAreaId != null && !"".equals(currentAreaId)) {
				areaIdSet.add(currentAreaId);
			}
		}

		// 循环deviceList，将符合条件的device放入areaList
		for(int i=0; i<deviceList.size(); i++) {
			Map<String, Object> currentDevice = deviceList.get(i);
			String currentDevicePId = currentDevice.get("pId") != null ? currentDevice.get("pId").toString() : null;
			if(currentDevicePId != null && !"".equals(currentDevicePId) && areaIdSet.contains(currentDevicePId)) {
				areaList.add(currentDevice);
			}
		}
		return areaList;
	}

	@Override
	public Object findBroadcastList(String ids) {
		List<String> idList = new ArrayList<String>();
		String[] str = ids.split(",");
		for (String id : str) {
			idList.add(id);
		}
		List<BroadcastEntity> list = this.getDao().findByIdList(idList);
		return list;
	}

	@Override
	public List<BroadcastEntity> queryByBfdCusNumber(String cusNumber) {
		BroadcastEntity breBroadcastEntity = new BroadcastEntity();
		breBroadcastEntity.setBbdCusNumber(cusNumber);
		return this.getDao().selectByEntity(breBroadcastEntity);
	}
}
