package com.cesgroup.prison.alarm.plan.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.cesgroup.framework.util.StringUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.alarm.plan.dao.AlarmEmerPlanRltnMapper;
import com.cesgroup.prison.alarm.plan.dao.AlarmPlanBroadcastPlanMapper;
import com.cesgroup.prison.alarm.plan.dao.AlarmPlanItemDtlsMapper;
import com.cesgroup.prison.alarm.plan.dao.AlarmPlanMasterMapper;
import com.cesgroup.prison.alarm.plan.dao.AlertPlanRltnMapper;
import com.cesgroup.prison.alarm.plan.dao.PlanDeviceRltnMapper;
import com.cesgroup.prison.alarm.plan.entity.AlarmEmerPlanRltn;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanBroadcastPlan;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanItemDtlsEntity;
import com.cesgroup.prison.alarm.plan.entity.AlarmPlanMasterEntity;
import com.cesgroup.prison.alarm.plan.entity.AlertPlanRltnEntity;
import com.cesgroup.prison.alarm.plan.entity.PlanDeviceRltnEntity;
import com.cesgroup.prison.alarm.plan.service.AlarmPlanService;
import com.cesgroup.prison.broadcastPlan.dao.BroadcastPlanDao;
import com.cesgroup.prison.broadcastPlan.entity.BroadcastPlan;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.CommonUtil;

/**      
* @projectName：prison   
* @ClassName：AlarmPlanMasterServiceImpl   
* @Description：   
* @author：Tao.xu   
* @date：2017年12月25日 下午12:16:47   
* @version        
*/
@Service
@Transactional
public class AlarmPlanServiceImpl extends BaseDaoService<AlarmPlanMasterEntity, String, AlarmPlanMasterMapper> implements AlarmPlanService {

	/**
	* @Fields planMapper : 预案
	*/
	@Autowired
	private AlarmPlanMasterMapper planMapper;

	/**
	* @Fields itemMapper : 关联项
	*/
	@Autowired
	private AlarmPlanItemDtlsMapper itemMapper;

	/**
	* @Fields deviceMapper :设备
	*/
	@Autowired
	private PlanDeviceRltnMapper deviceMapper;

	/**
	* @Fields alertMapper : 报警器
	*/
	@Autowired
	private AlertPlanRltnMapper alertMapper;
	@Resource
	private AlarmPlanBroadcastPlanMapper alarmBroadcastPlanMapper;
	@Resource
	private BroadcastPlanDao broadcastPlanMpper;
	/**
	 * 报警预案与应急预案关联关系DAO
	 */
	@Resource
	private AlarmEmerPlanRltnMapper alarmEmerPlanRltnMapper;
	
	@Override
	public Page<Map<String, Object>> listAllForMaster(AlarmPlanMasterEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("alarmPlanMasterEntity", entity);
		}
		return planMapper.listAll(map, pageable);
	}

	@Override
	public AlarmPlanMasterEntity findMasterById(String id) {
		AlarmPlanMasterEntity alarmPlanMasterEntity = planMapper.selectOne(id);
		if (alarmPlanMasterEntity != null && StringUtils.isNotBlank(alarmPlanMasterEntity.getId())) {
			AlarmPlanItemDtlsEntity alarmPlanItemDtlsEntity = new AlarmPlanItemDtlsEntity();
			alarmPlanItemDtlsEntity.setPidPlanId(alarmPlanMasterEntity.getId());
			// 关联项list
			List<AlarmPlanItemDtlsEntity> items = itemMapper.selectByEntity(alarmPlanItemDtlsEntity);
			AlertPlanRltnEntity alertPlanRltnEntity = new AlertPlanRltnEntity();
			alertPlanRltnEntity.setAprPlanId(alarmPlanMasterEntity.getId());

			Map<String, String> itemName = new HashMap<>();// 临时获取itemName办法
			itemName.put("1", "门禁");
			itemName.put("2", "摄像");
			itemName.put("3", "广播");
			itemName.put("4", "大屏");
			itemName.put("5", "对讲");

			// 报警器list
			List<AlertPlanRltnEntity> alerts = alertMapper.selectByEntity(alertPlanRltnEntity);
			for (AlarmPlanItemDtlsEntity itemDtlsEntity : items) {
				PlanDeviceRltnEntity deviceRltnEntity = new PlanDeviceRltnEntity();
				deviceRltnEntity.setPdrCusNumber(itemDtlsEntity.getPidCusNumber());
				deviceRltnEntity.setPdrItemId(itemDtlsEntity.getPidItemId());
				deviceRltnEntity.setPdrPlanId(itemDtlsEntity.getPidPlanId());
				itemDtlsEntity.setDevices(deviceMapper.selectByEntity(deviceRltnEntity));
				itemDtlsEntity.setItemName(itemName.get(itemDtlsEntity.getPidItemId()));
			}
			alarmPlanMasterEntity.setItems(items);
			alarmPlanMasterEntity.setAlerts(alerts);
			AlarmPlanBroadcastPlan alarmPlanBroadcastPlan = new AlarmPlanBroadcastPlan();
			alarmPlanBroadcastPlan.setBprPlanId(id);
			List<AlarmPlanBroadcastPlan> abcps = alarmBroadcastPlanMapper.selectByEntity(alarmPlanBroadcastPlan);
                  List<Map<String,Object>>  emerPlanNameList =  alarmEmerPlanRltnMapper.getemerPlanNameByAlarmPlanId(id);
                  List<String> emerPlanNames = new ArrayList<String>();
                  if(emerPlanNameList !=null && emerPlanNameList.size()>0){
                      for (Map<String, Object> emerPlanName : emerPlanNameList) {
                          emerPlanNames.add(emerPlanName.get("emerPlanName").toString());
                      }
                  }

			alarmPlanMasterEntity.setBroadcastPlan(abcps);
                alarmPlanMasterEntity.setEmerPlanNames(emerPlanNames);
		}
		return alarmPlanMasterEntity;
	}

	@Override
	public void delAlarmPlanMaster(AlarmPlanMasterEntity entity) {
		planMapper.deleteByEntity(entity);
		if (StringUtils.isNotBlank(entity.getId()) && StringUtils.isNotBlank(entity.getPmaCusNumber())) {
			String id = entity.getId();
			String cusNumber = entity.getPmaCusNumber();
			// 删除关联项
			AlarmPlanItemDtlsEntity alarmPlanItemDtlsEntity = new AlarmPlanItemDtlsEntity();
			alarmPlanItemDtlsEntity.setPidPlanId(id);
			alarmPlanItemDtlsEntity.setPidCusNumber(cusNumber);
			delAlarmPlanItem(alarmPlanItemDtlsEntity);

			// 删除设备
			PlanDeviceRltnEntity delPlanDeviceRltn = new PlanDeviceRltnEntity();
			delPlanDeviceRltn.setPdrPlanId(id);
			delPlanDeviceRltn.setPdrCusNumber(cusNumber);
			delPlanDeviceRltn(delPlanDeviceRltn);

			// 删除报警器
			AlertPlanRltnEntity alertPlanRltnEntity = new AlertPlanRltnEntity();
			alertPlanRltnEntity.setAprPlanId(id);
			alertPlanRltnEntity.setAprCusNumber(cusNumber);
			delAlertPlanRltn(alertPlanRltnEntity);

		}
	}

	@Override
	public void delAlarmPlanItem(AlarmPlanItemDtlsEntity entity) {
		itemMapper.deleteByEntity(entity);
		if (StringUtils.isNotBlank(entity.getPidPlanId()) && StringUtils.isNotBlank(entity.getPidCusNumber())
				&& StringUtils.isNotBlank(entity.getPidItemId())) {
			String id = entity.getPidPlanId();
			String cusNumber = entity.getPidCusNumber();
			String itemId = entity.getPidItemId();
			// 删除设备
			PlanDeviceRltnEntity delPlanDeviceRltn = new PlanDeviceRltnEntity();
			delPlanDeviceRltn.setPdrPlanId(id);
			delPlanDeviceRltn.setPdrCusNumber(cusNumber);
			delPlanDeviceRltn.setPdrItemId(itemId);
			delPlanDeviceRltn(delPlanDeviceRltn);
		}
	}

	@Override
	public void delAlertPlanRltn(AlertPlanRltnEntity entity) {
		alertMapper.deleteByEntity(entity);
	}

	@Override
	public void delPlanDeviceRltn(PlanDeviceRltnEntity entity) {
		deviceMapper.deleteByEntity(entity);
	}

	@Override
	public void addMasterInfo(AlarmPlanMasterEntity entity) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		Date date = new Date();
		String id = UUID.randomUUID().toString().replace("-", "");// 因为id是后台uuid生成的，前端添加关联项的时候需要，这里就添加预案成功返回一个id给前端
		entity.setId(id);
		entity.setPmaCusNumber(userBean.getCusNumber());
		entity.setPmaCrteTime(date);
		entity.setPmaCrteUserId(userId);
		entity.setPmaUpdtTime(date);
		entity.setPmaUpdtUserId(userId);
		planMapper.insert(entity);
		List<AlarmPlanItemDtlsEntity> items = entity.getItems();
		if (!items.isEmpty()) {
			for (AlarmPlanItemDtlsEntity alarmPlanItemDtlsEntity : items) {
				alarmPlanItemDtlsEntity.setPidPlanId(id);
				alarmPlanItemDtlsEntity.setPidCusNumber(userBean.getCusNumber());
				alarmPlanItemDtlsEntity.setPidCrteTime(date);
				alarmPlanItemDtlsEntity.setPidCrteUserId(userId);
				alarmPlanItemDtlsEntity.setPidUpdtTime(date);
				alarmPlanItemDtlsEntity.setPidUpdtUserId(userId);
			}
			itemMapper.insert(items);
		}
	}

	@Override
	public void updateMasterInfo(AlarmPlanMasterEntity entity) throws Exception {
		UserBean userBean = AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		Date date = new Date();
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			entity.setPmaUpdtTime(date);
			entity.setPmaUpdtUserId(userId);
			map.put("alarmPlanMasterEntity", entity);
			planMapper.updateAlarmPlanMasterInfo(map);
			List<AlarmPlanItemDtlsEntity> items = entity.getItems();
			for (AlarmPlanItemDtlsEntity alarmPlanItemDtlsEntity : items) {
				if (StringUtils.isNotBlank(alarmPlanItemDtlsEntity.getId())) {
					if (StringUtils.isNotBlank(alarmPlanItemDtlsEntity.getPidSttsIndc())) {
						Map<String, Object> map_item = new HashMap<>();
						alarmPlanItemDtlsEntity.setPidUpdtUserId(userId);
						alarmPlanItemDtlsEntity.setPidUpdtTime(date);
						map_item.put("alarmPlanItemDtlsEntity", alarmPlanItemDtlsEntity);
						itemMapper.updateItem(map_item);
					} else {
						alarmPlanItemDtlsEntity.setPidCusNumber(userBean.getCusNumber());
						delAlarmPlanItem(alarmPlanItemDtlsEntity);
					}
				} else {
					if (StringUtils.isNotBlank(alarmPlanItemDtlsEntity.getPidSttsIndc())) {
						alarmPlanItemDtlsEntity.setPidCusNumber(userBean.getCusNumber());
						alarmPlanItemDtlsEntity.setPidCrteTime(date);
						alarmPlanItemDtlsEntity.setPidCrteUserId(userId);
						alarmPlanItemDtlsEntity.setPidUpdtTime(date);
						alarmPlanItemDtlsEntity.setPidUpdtUserId(userId);
						itemMapper.insert(alarmPlanItemDtlsEntity);
					}
				}
			}
		}

	}

	@Override
	public void updateDeviceInfo(PlanDeviceRltnEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setPdrUpdtTime(date);
			entity.setPdrUpdtUserId(userId);
			map.put("planDeviceRltnEntity", entity);
		}
		deviceMapper.updatePlanDeviceRltnInfo(map);
	}

	@Override
	public void updateAlertInfo(AlertPlanRltnEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setAprUpdtTime(date);
			entity.setAprUpdtUserId(userId);
			map.put("alertPlanRltnEntity", entity);
		}
		alertMapper.updateAlert(map);
	}

	

	@Override
	public List<Map<String, Object>> listAllForDevice(String cusNumber, String areaId, String itemId, String planId) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber);
		}
		if (StringUtils.isNotBlank(areaId)) {
			map.put("areaId", areaId);
		}
		if (StringUtils.isNotBlank(planId)) {
			map.put("planId", planId);
		}
		if (StringUtils.isNotBlank(itemId)) {// 1 "门禁"; 2 "摄像机"; 3 "广播"; 4 "大屏"; 5 "对讲";
			map.put("itemId", itemId);
			switch (itemId) {
			case "1":
				return planMapper.listAllForMj(map);
			case "2":
				return planMapper.listAllForSx(map);
			case "3":
				return planMapper.listAllForGb(map);
			case "4":
				return planMapper.listAllForDp(map);
			case "5":
				return planMapper.listAllForDj(map);
			default:
				return null;
			}
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> listAllForDeviceByItem(PlanDeviceRltnEntity entity) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("planDeviceRltnEntity", entity);
		}
		return deviceMapper.listAll(map);
	}

	@Override
	public void addDeviceInfo(List<Map<String, Object>> devices) throws Exception {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		PlanDeviceRltnEntity entity = null;
		for (Map<String, Object> device : devices) {
			if (StringUtils.isNotBlank((String) device.get("ID"))) {
				entity = new PlanDeviceRltnEntity();
				entity.setPdrOutoIndc((String) device.get("PDR_OUTO_INDC"));
				entity.setPdrPlanId((String) device.get("PDR_PLAN_ID"));
				entity.setId((String) device.get("ID"));
				entity.setPdrCusNumber(user.getCusNumber());
				entity.setPdrExecOrder((String) device.get("PDR_EXEC_ORDER"));
				updateDeviceInfo(entity);
			} else {
				entity = new PlanDeviceRltnEntity();
				entity.setPdrOutoIndc((String) device.get("PDR_OUTO_INDC"));
				entity.setPdrCusNumber(user.getCusNumber());
				entity.setPdrExecOrder((String) device.get("PDR_EXEC_ORDER"));
				entity.setPdrItemId((String) device.get("PDR_ITEM_ID"));
				entity.setPdrCrteTime(new Date());
				entity.setPdrCrteUserId(user.getUserId());
				entity.setPdrUpdtTime(new Date());
				entity.setPdrUpdtUserId(user.getUserId());
				entity.setPdrOutoIndc((String) device.get("PDR_OUTO_INDC"));
				entity.setPdrDvcIdnty((String) device.get("PDR_DVC_IDNTY"));
				entity.setPdrDvcName((String) device.get("PDR_DVC_NAME"));
				entity.setPdrPlanId((String) device.get("PDR_PLAN_ID"));
				deviceMapper.insert(entity);
			}
		}
	}

	@Override
	public List<Map<String, Object>> listAllForAlertor(String cusNumber, String areaId) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber);
		}
		if (StringUtils.isNotBlank(areaId)) {
			map.put("areaId", areaId);
		}
		return planMapper.listAllForBjq(map);
	}

	@Override
	public List<Map<String, Object>> listAllForAlertorByPlan(AlertPlanRltnEntity entity) {
		Map<String, Object> map = new HashMap<>();
		if (entity != null) {
			map.put("alertPlanRltnEntity", entity);
		}
		return alertMapper.listAllForAlertor(map);
	}

	@Override
	public void addAlertInfo(List<Map<String, Object>> alertors) throws Exception {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		AlertPlanRltnEntity entity = null;
		for (Map<String, Object> alertor : alertors) {
			entity = new AlertPlanRltnEntity();
			if (StringUtils.isNotBlank((String) alertor.get("ID"))) {
				entity.setAprCusNumber((String) alertor.get("APR_CUS_NUMBER"));
				entity.setAprDvcIdnty((String) alertor.get("APR_DVC_IDNTY"));
				entity.setAprPlanId((String) alertor.get("APR_PLAN_ID"));
				entity.setAprRemark((String) alertor.get("APR_REMARK"));
				entity.setAprBrandIndc((String) alertor.get("APR_BRAND_INDC"));
				entity.setId((String) alertor.get("ID"));
				updateAlertInfo(entity);
			} else {
				entity.setAprCusNumber(user.getCusNumber());
				entity.setAprDvcIdnty((String) alertor.get("APR_DVC_IDNTY"));
				entity.setAprDvcName((String) alertor.get("APR_DVC_NAME"));
				entity.setAprDvcTypeIndc((String) alertor.get("APR_DVC_TYPE_INDC"));
				entity.setAprPlanId((String) alertor.get("APR_PLAN_ID"));
				entity.setAprRemark((String) alertor.get("APR_REMARK"));
				entity.setAprBrandIndc((String) alertor.get("APR_BRAND_INDC"));
				entity.setAprCrteTime(new Date());
				entity.setAprCrteUserId(user.getUserId());
				entity.setAprUpdtTime(new Date());
				entity.setAprUpdtUserId(user.getUserId());
				alertMapper.insert(entity);
			}
		}

	}
	/**
	 * 保存或修改关联的报警广播预案
	 */
	@Override
	public void saveBroadcastPlan(AlarmPlanBroadcastPlan entity) {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		//若报警预案id在数据库存在则修改 
		AlarmPlanBroadcastPlan abp  = new AlarmPlanBroadcastPlan();
		abp.setBprPlanId(entity.getBprPlanId());
		 //long countByEntity = alarmBroadcastPlanMapper.selectCountByEntity(abp);
          List<AlarmPlanBroadcastPlan> alarmPlanBroadcastPlans = alarmBroadcastPlanMapper.selectByEntity(abp);
          if(alarmPlanBroadcastPlans !=null && alarmPlanBroadcastPlans.size()>0) {
              entity.setBprUpdtTime(DateUtils.getCurrentDate(true));
              entity.setBprUpdtUserId(user.getUserId());
			//根据报警预案修改数据
                if(!StringUtil.isNull(entity.getBprBroadcastPlanId())){
                    //若广播预案id不为空修改关联的广播报警预案
                    BroadcastPlan broadcastPlan = broadcastPlanMpper.selectOne(entity.getBprBroadcastPlanId());
                    if(broadcastPlan.getDbpBroadcastPlanName()!=null) {
                        entity.setBprBroadcastPlanName(broadcastPlan.getDbpBroadcastPlanName());
                    }
                    alarmBroadcastPlanMapper.updateByPlanId(entity);
                }else{
                    //若广播预案id为空表示不关联广播报警预案,删除
                    alarmBroadcastPlanMapper.delete(alarmPlanBroadcastPlans.get(0).getId());
                }
		}else {//插入
			//查询广播预案名称
                if(!StringUtil.isNull(entity.getBprBroadcastPlanId())){
                    BroadcastPlan broadcastPlan = broadcastPlanMpper.selectOne(entity.getBprBroadcastPlanId());
                    if(broadcastPlan.getDbpBroadcastPlanName()!=null) {
                        entity.setBprBroadcastPlanName(broadcastPlan.getDbpBroadcastPlanName());
                    }
                    entity.setBprCusNumber(user.getCusNumber());
                    entity.setBprCrteUserId(user.getUserId());
                    entity.setBprCrteTime(DateUtils.getCurrentDate(true));
                    alarmBroadcastPlanMapper.insert(entity);
                }
		}
	}
	/**
	 * 根据报警预案查询 关联的广播预案
	 */
	@Override
	public AlarmPlanBroadcastPlan findByPlanId(String bprPlanId) {
		AlarmPlanBroadcastPlan apbp = new AlarmPlanBroadcastPlan();
		apbp.setBprPlanId(bprPlanId);
		 List<AlarmPlanBroadcastPlan> selectByEntity = alarmBroadcastPlanMapper.selectByEntity(apbp);
		 if(selectByEntity.size()>0) {
			  apbp = selectByEntity.get(0);
		 }
		return apbp;
	}

	@Override
	public AlarmEmerPlanRltn queryAlarmEmerPlanRltnByAlarmPlanId(String alarmPlanId) throws ServiceException {
		List<AlarmEmerPlanRltn> alarmEmerPlanRltnList = null;
		try {
			alarmEmerPlanRltnList = this.alarmEmerPlanRltnMapper.findByAlarmPlanId(alarmPlanId);
		} catch (Exception e) {
			throw new ServiceException("根据报警预案ID，查询报警预案与应急预案关联关系数据发生异常，原因：" + e);
		}
		if(alarmEmerPlanRltnList == null || alarmEmerPlanRltnList.size() <=0) {
			return null;
		}
		return alarmEmerPlanRltnList.get(0);
	}

	@Override
	public void saveOrUpdateAlarmEmerPlanRltn(AlarmEmerPlanRltn entity) throws ServiceException {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		if (user == null) {
			throw new ServiceException("用户未登录");
		}
		if(entity == null) {
			throw new ServiceException("报警预案与应急预案关联关系实体类为空");
		}
		// 单位编号赋值
		entity.setCusNumber(user.getCusNumber());

		// 判断报警预案ID是否为空
		if(entity.getAlarmPlanId() == null || "".equals(entity.getAlarmPlanId())) {
			throw new ServiceException("报警预案ID为空");
		}

		// 声明报警预案与应急预案关联关系操作实类体
		AlarmEmerPlanRltn alarmEmerPlanRltnOperate = null;

		// 根据单位编号、报警预案编号，查询报警预案与应急预案关联关系数据
		try {
			List<AlarmEmerPlanRltn> alarmEmerPlanRltnList = this.alarmEmerPlanRltnMapper.findByAlarmPlanId(entity.getAlarmPlanId());
			if(alarmEmerPlanRltnList != null && alarmEmerPlanRltnList.size() > 0) {
				alarmEmerPlanRltnOperate = alarmEmerPlanRltnList.get(0);
			}
		} catch (Exception e) {
			throw new ServiceException("根据单位编号、报警预案编号，查询报警预案与应急预案关联关系数据失败");
		}

		if(alarmEmerPlanRltnOperate == null) {
			alarmEmerPlanRltnOperate = new AlarmEmerPlanRltn();
			try {
				alarmEmerPlanRltnOperate.setId(CommonUtil.createUUID());
				alarmEmerPlanRltnOperate.setCusNumber(entity.getCusNumber());
				alarmEmerPlanRltnOperate.setAlarmPlanId(entity.getAlarmPlanId());
				alarmEmerPlanRltnOperate.setEmerPlanId(entity.getEmerPlanId());
				alarmEmerPlanRltnOperate.setStatus("0");
				Integer showOrder = this.alarmEmerPlanRltnMapper.findMaxShowOrderByCusNumberAndAlarmPlanId(entity.getCusNumber(), entity.getAlarmPlanId());
				alarmEmerPlanRltnOperate.setShowOrder(Long.valueOf(showOrder == null ? 1 : showOrder + 1));
				alarmEmerPlanRltnOperate.setUpdateUserId(user.getUserId());
				alarmEmerPlanRltnOperate.setUpdateTime(new Date());
				this.alarmEmerPlanRltnMapper.insertSelective(alarmEmerPlanRltnOperate);
			} catch (Exception e) {
				throw new ServiceException("新增报警预案与应急预案关联关系数据失败，原因：" + e);
			}
		} else {
			try {
				alarmEmerPlanRltnOperate.setEmerPlanId(entity.getEmerPlanId());
				alarmEmerPlanRltnOperate.setStatus("0");
				alarmEmerPlanRltnOperate.setUpdateUserId(user.getUserId());
				alarmEmerPlanRltnOperate.setUpdateTime(new Date());
				this.alarmEmerPlanRltnMapper.updateSelective(alarmEmerPlanRltnOperate);
			} catch (Exception e) {
				throw new ServiceException("更新报警预案与应急预案关联关系数据失败，原因：" + e);
			}
		}
	}
}
