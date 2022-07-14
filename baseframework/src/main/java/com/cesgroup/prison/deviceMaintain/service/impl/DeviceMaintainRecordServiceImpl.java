/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.demo.service.code</p>
 * <p>文件名:CodeKeyService.java</p>
 * <p>类更新历史信息</p>
 * @author xiexiaqin 
 * @date 2016-04-22 10:22
 * @todo 
 */
package com.cesgroup.prison.deviceMaintain.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.deviceMaintain.dao.DeviceMaintainRecordMapper;
import com.cesgroup.prison.deviceMaintain.entity.DeviceMaintainRecordEntity;
import com.cesgroup.prison.deviceMaintain.service.DeviceMaintainRecordService;

/**      
* @projectName：prison   
* @ClassName：DeviceMaintainServiceImpl   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月9日 下午5:58:57   
* @version        
*/
@Service
@Transactional
public class DeviceMaintainRecordServiceImpl
		extends BaseDaoService<DeviceMaintainRecordEntity, String, DeviceMaintainRecordMapper>
		implements DeviceMaintainRecordService {

	@Autowired
	private DeviceMaintainRecordMapper mapper;

	@Override
	public Page<Map<String, Object>> listAll(DeviceMaintainRecordEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (entity != null) {
			map.put("deviceMaintainRecordEntity", entity);
		}
		return mapper.listAll(map, pageable);
	}

	@Override
	public void addRecordInfo(List<Map<String, Object>> devices) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		Date date = new Date();
		for (Map<String, Object> map : devices) {
			DeviceMaintainRecordEntity entity = new DeviceMaintainRecordEntity();
			entity.setDmrCrteTime(date);
			entity.setDmrCrteUserId(userId);
			entity.setDmrUpdtTime(date);
			entity.setDmrUpdtUserId(userId);
			entity.setDmrCusNumber(userBean.getCusNumber());
			entity.setDmrDeviceIdnty((String) map.get("DMR_DEVICE_IDNTY"));
			entity.setDmrFaultMaintainer((String) map.get("DMR_FAULT_MAINTAINER"));
			entity.setDmrFaultMaintainTime((String) map.get("DMR_FAULT_MAINTAIN_TIME"));
			entity.setDmrFaultContent((String) map.get("DMR_FAULT_CONTENT"));
			entity.setDmrDeviceType((String) map.get("DMR_DEVICE_TYPE"));
			entity.setDmrDeviceName((String) map.get("DMR_DEVICE_NAME"));
			mapper.insert(entity);

		}

	}

	@Override
	public void updateRecordInfo(DeviceMaintainRecordEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setDmrUpdtTime(date);
			entity.setDmrUpdtUserId(userId);
			map.put("deviceMaintainRecordEntity", entity);
		}
		mapper.updateRecordInfo(map);
	}

	@Override
	public void deleteByIds(List<String> ids) {
		mapper.deleteByIds(ids);
	}

	@Override
	public DeviceMaintainRecordEntity findById(String id) {
		return mapper.selectOne(id);
	}

	/**
	 * 根据区域查设备 1、摄像头  2、对讲分机  3、 报警器  4、门禁   5、广播   6、对讲主机   7、高压电网
	 */
	@Override
	public List<Map<String, Object>> findDeviceList(String type, String cusNumber, String areaId) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber.trim());
		}
		if (StringUtils.isNotBlank(type)) {
			map.put("type", type.trim());
		}
		if (StringUtils.isNotBlank(areaId)) {
			map.put("areaId", areaId.trim());
		}
		switch (type) {
		case "1":
			return mapper.findDeviceList_sxt(map);
		case "2":
			return mapper.findDeviceList_djfj(map);
		case "3":
			return mapper.findDeviceList_bjq(map);
		case "4":
			return mapper.findDeviceList_mj(map);
		case "5":
			return mapper.findDeviceList_gb(map);
		case "6":
			return mapper.findDeviceList_djzj(map);
		case "7":
			return mapper.findDeviceList_gydw(map);
		default:
			break;
		}
		return null;
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Map<String, Object>> findYnSbyc(PageRequest pageRequest) {
		return mapper.selectYnSbyc(pageRequest);
	}

}
