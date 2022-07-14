package com.cesgroup.prison.alarm.record.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.prison.alarm.emerRecord.entity.EmerRecord;
import com.cesgroup.prison.alarm.record.dao.AlarmMapper;
import com.cesgroup.prison.alarm.record.entity.AlarmRecordEntity;
import com.cesgroup.prison.alarm.record.service.AlarmService;
import com.cesgroup.prison.aqfk.monitor.dao.EvidenceMapper;
import com.cesgroup.prison.aqfk.monitor.entity.Evidence;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.dao.AffixMapper;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.PageUtil;

@Service
@Transactional
public class AlarmServiceImpl extends BaseDaoService<AlarmRecordEntity, String, AlarmMapper> implements AlarmService {
	@Autowired
	private AlarmMapper mapper;

	@Resource
	private AffixMapper affixMapper;

	/**
	* @Fields evidenceMapper : 证据信息
	*/
	@Autowired
	private EvidenceMapper evidenceMapper;

	@Override
	public Page<Map<String, Object>> listAll(AlarmRecordEntity entity, String startTime, String endTime,
			String dprtmntId, String type,PageRequest pageRequest) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (entity != null) {
			map.put("alarmRecordEntity", entity);
		}
		if (StringUtils.isNotBlank(startTime)) {
			map.put("startTime", startTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			map.put("endTime", endTime);
		}
		if (StringUtils.isNotBlank(dprtmntId)) {
			map.put("dprtmntId", dprtmntId);
		}if (StringUtils.isNotBlank(type)) {
			map.put("type", type);
		}
		return mapper.listAll(map, pageRequest);
	}

	@Override
	public Map<String, Object> findAlarmRecordById(AlarmRecordEntity entity) {
		Map<String, Object> map = new HashMap<String, Object>();

		if (entity != null) {
			map.put("alarmRecordEntity", entity);
		}
		return mapper.findAlarmRecordById(map);
	}

	@Override
	public void updateAlarmRecord(AlarmRecordEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setArdUpdtTime(date);
			entity.setArdUpdtUserId(userId);
			map.put("alarmRecordEntity", entity);
		}
		mapper.updateAlarmRecordInfo(map);
	}

	@Override
	public void addAlertFile(String id, String files) {
		// 关联附件
		List<String> idList = new ArrayList<String>();
		if (StringUtils.isNotBlank(files)) {
			idList.addAll(Arrays.asList(files.split(",")));
		}
		affixMapper.updateYwId(id, idList);
	}

	@Override
	public List<AffixEntity> findAlertFile(String id) {
		AffixEntity affixEntity = new AffixEntity();
		affixEntity.setYwId(id);
		List<AffixEntity> affixList = affixMapper.findAllList(affixEntity);
		return affixList;
	}

	@Override
	public String glZjByzjIds(List<String> ids, String ywId, String fjfl) {
		String msg = "";
		List<Map<String, Object>> zjList = evidenceMapper.findByIds(ids);

		if (!zjList.isEmpty() && StringUtils.isNotBlank(ywId)) {
			for (Map<String, Object> map : zjList) {
				String id = (String) map.get("ID");
				String title = (String) map.get("EIN_TITLE");// 摄像机+文件生成日期 + 类型（截图 or 视频）
				String fileName = (String) map.get("EIN_FILE_NAME");// 文件名，带文件后缀名
				String path = "/" + (String) map.get("EIN_FTP_PREFIX") + "/"
						+ ((String) map.get("EIN_FTP_PATH")).replace("\\", "/") + "/" + fileName;// 路径
				String crteUser = (String) map.get("EIN_CRTE_USER_ID");// 上传人id
				Date crteDate = (Date) map.get("EIN_CRTE_TIME");// 上传时间
				AffixEntity affixEntity = new AffixEntity();
				File file = new File(CommonConstant.systemRootAbsoultPath + path);
				if (file.exists() && file.isFile()) {
					System.out.println("文件" + fileName + "的大小是：" + file.length());
					affixEntity.setFileSize(Long.toString(file.length()));
				}
				String[] fileNames = fileName.split("[.]");
				affixEntity.setFileName(title + "." + fileNames[1]);
				affixEntity.setScrId(crteUser);
				affixEntity.setScrq(crteDate);
				affixEntity.setLinkUrl(path);
				affixEntity.setFileExts(fileNames[1]);// 文件后缀名
				affixEntity.setYwId(ywId);
				affixEntity.setFjfl(fjfl);// jsp 界面 上传控件id
				affixMapper.insert(affixEntity);
				Evidence record = new Evidence();// 更新证据信息状态为已使用
				record.setEinSttsIndc("1");// 使用状态，已使用
				record.setId(id);
				evidenceMapper.updatePart(record);
				msg = "关联证据信息成功";
			}
		} else {
			msg = "未查询到证据信息，关联失败";
		}
		return msg;
	}

	@Override
	public Map<String, Object> queryAlarmLevRecord(String cusNumber,String StartTime ,String Now,String DpName) throws Exception {
		AlarmRecordEntity alarmRecordEntity = new AlarmRecordEntity();
		Map<String, Object> map = new HashMap<String, Object>();

		if (!AuthSystemFacade.isProvLevel()) {
			alarmRecordEntity.setArdCusNumber(cusNumber);
		}

		Map<String, Object> map_ = new HashMap<String, Object>();
		alarmRecordEntity.setArdAlertLevelIndc("1");
		map_.put("alarmRecordEntity", alarmRecordEntity);
		map_.put("StartTime", StartTime);
		map_.put("Now", Now);
		map_.put("DpName", DpName);
		map.put("lev_1", mapper.queryAlarmLevRecord(map_).get("COUNT(ID)"));

		alarmRecordEntity.setArdAlertLevelIndc("2");
		map_.clear();
		map_.put("alarmRecordEntity", alarmRecordEntity);
		map_.put("StartTime", StartTime);
		map_.put("Now", Now);
		map_.put("DpName", DpName);
		map.put("lev_2", mapper.queryAlarmLevRecord(map_).get("COUNT(ID)"));

		alarmRecordEntity.setArdAlertLevelIndc("3");
		map_.clear();
		map_.put("alarmRecordEntity", alarmRecordEntity);
		map_.put("StartTime", StartTime);
		map_.put("Now", Now);
		map_.put("DpName", DpName);
		map.put("lev_3", mapper.queryAlarmLevRecord(map_).get("COUNT(ID)"));
		return map;
	}

	@Override
	public Page<Map<String, Object>> searchRecordList(AlarmRecordEntity entity,PageRequest pageRequest,String startTime,String endTime) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		if (entity != null) {
			map.put("alarmRecordEntity", entity);
		}	if (StringUtils.isNotBlank(startTime)) {
			map.put("startTime", startTime);
		}
		if (StringUtils.isNotBlank(endTime)) {
			map.put("endTime", endTime);
		}
		return mapper.searchRecordList(map,pageRequest);
	}

	@Override
	public Map<String, Object> queryJqAlarmLevRecord(String cusNumber, String startStr, String now, String dprtmntId, PageRequest pageRequest) {
		AlarmRecordEntity alarmRecordEntity = new AlarmRecordEntity();
		alarmRecordEntity.setArdCusNumber(cusNumber);
		alarmRecordEntity.setArdAlertLevelIndc("1");
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("alarmRecordEntity", alarmRecordEntity);
		param.put("startTime", startStr);
		param.put("endTime", now);
		param.put("dprtmntId", dprtmntId);
		map.put("lev_1", PageUtil.getTotal(mapper.countBjjl(param)));
		alarmRecordEntity.setArdAlertLevelIndc("2");
		map.put("lev_2", PageUtil.getTotal(mapper.countBjjl(param)));
		alarmRecordEntity.setArdAlertLevelIndc("3");
		map.put("lev_3", PageUtil.getTotal(mapper.countBjjl(param)));
		return map;
	}

	@Override
	public List<EmerRecord> queryQdyaByid(String id) {
		return this.getDao().queryQdyaByid(id);
	}

	@Override
	public Map<String, Object> queryAlertorAreaMappingDprtmntByCusNumberAndAlertorId(String cusNumber, String alertorId) throws ServiceException {
		try {
			return this.getDao().findAlertorAreaMappingDprtmntByCusNumberAndAlertorId(cusNumber, alertorId);
		} catch (Exception e) {
			throw new ServiceException("根据cusNumber = " + cusNumber + "，alertorId = " + alertorId + "查询报警器所属区域对应的，系统管理平台部门ID发生异常");
		}
	}

}
