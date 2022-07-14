package com.cesgroup.prison.yjct.service.impl;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.dao.AffixMapper;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.yjct.dao.GzzglMapper;
import com.cesgroup.prison.yjct.dao.YjjlMapper;
import com.cesgroup.prison.yjct.dao.YjjlgcMapper;
import com.cesgroup.prison.yjct.entity.GzzglEntity;
import com.cesgroup.prison.yjct.entity.YjjlEntity;
import com.cesgroup.prison.yjct.entity.YjjlgcEntity;
import com.cesgroup.prison.yjct.service.YjjlService;

@Service("yjjlService")
public class YjjlServiceImpl extends BaseDaoService<YjjlEntity, String, YjjlMapper> implements YjjlService {
	
	@Resource
	private YjjlMapper yjjlMapper;
	@Resource
	private YjjlgcMapper yjjlgcMapper;
	@Resource
	private AffixMapper affixMapper;
	/**
	 * 工作组Dao
	 */
	@Resource
	private GzzglMapper gzzglMapper;

	@Override
	public YjjlEntity getById(String id) {
		
		YjjlEntity yjjlEntity = yjjlMapper.getById(id);
		
		AffixEntity affixEntity = new AffixEntity();
		affixEntity.setYwId(id);
		List<AffixEntity> affixList = affixMapper.findAllList(affixEntity);
		
		for(AffixEntity affix : affixList) {
			if("1".equals(affix.getFileType())) {
				yjjlEntity.getJlbstAffixIdList().add(affix);
			} else if("2".equals(affix.getFileType())) {
				yjjlEntity.getZzjtAffixIdList().add(affix);
			} else if("3".equals(affix.getFileType())) {
				yjjlEntity.getRydjbAffixIdList().add(affix);
			}
		}
		
		return yjjlEntity;
	}

	@Override
	public Page<YjjlEntity> findList(YjjlEntity yjjlEntity, PageRequest pageRequest) {
		
		return yjjlMapper.findList(yjjlEntity, pageRequest);
	}

	@Override
	@Transactional
	public YjjlEntity saveOrUpdate(YjjlEntity yjjlEntity) {

		String id = yjjlEntity.getId();		
		if(id != null && !"".equals(id)) {
			yjjlMapper.update(yjjlEntity);
		} else {			
			yjjlMapper.insert(yjjlEntity);			
		}
		
		return yjjlEntity;
	}

	@Override
	@Transactional
	public void saveOrUpdateEventReport(YjjlEntity yjjlEntity) {
		
		// 更新记录已归档
		YjjlEntity yjjl = yjjlMapper.getById(yjjlEntity.getId());
		yjjl.setEhrSaveTime(yjjlEntity.getEhrUpdtTime());
		yjjl.setEhrExperience(yjjlEntity.getEhrExperience());
		yjjl.setEhrStatus("3");
		yjjl.setEhrUpdtTime(yjjlEntity.getEhrUpdtTime());
		yjjl.setEhrUpdtUserId(yjjlEntity.getEhrUpdtUserId());
		yjjlMapper.update(yjjl);
		
		// 关联附件
		List<String> idList = new ArrayList<String>();
		String rydjbAffixIds = yjjlEntity.getRydjbAffixIds();
		if(rydjbAffixIds != null && !"".equals(rydjbAffixIds)) {
			idList.addAll(Arrays.asList(rydjbAffixIds.split(",")));
		}	
		
		if(idList.size() > 0) {
			affixMapper.updateYwId(yjjlEntity.getId(), idList);
		}
	}

	@Override
	public List<YjjlEntity> tj(YjjlEntity yjjlEntity) {
		
		List<YjjlEntity> yjjlList = new ArrayList<YjjlEntity>();
		String epiPlanType = yjjlEntity.getEpiPlanType();
		
		if(epiPlanType != null && !"".equals(epiPlanType)) {
			// 按类型统计
			
			yjjlList = yjjlMapper.tjByPlanType(yjjlEntity);
		} else {
			
			// 按全部统计
			yjjlList = yjjlMapper.tjByAll(yjjlEntity);
		}
		
		return yjjlList;
	}

	@Override
	@Transactional
	public String updateRecordStatus(YjjlEntity yjjlEntity) {
		
		String msg = "";
		
		// 校验处置流程是否全部填写
		int czlcNum = yjjlEntity.getCzlcNum();
		
		YjjlgcEntity yjjlgcEntity = new YjjlgcEntity();
		yjjlgcEntity.setEhpHandleFid(yjjlEntity.getId());
		List<YjjlgcEntity> yjjlgcList = yjjlgcMapper.findAllList(yjjlgcEntity);
		
		if(yjjlgcList != null && yjjlgcList.size() == czlcNum) {
			
			YjjlEntity yjjl = yjjlMapper.getById(yjjlEntity.getId());
			yjjl.setEhrStatus(yjjlEntity.getEhrStatus());
			yjjl.setEhrUpdtTime(yjjlEntity.getEhrUpdtTime());
			yjjl.setEhrUpdtUserId(yjjlEntity.getEhrUpdtUserId());
			yjjlMapper.update(yjjl);
		} else {
			msg = "请记录所有流程情况！";
		}
		
		return msg;
	}	
	
	@Override
	public Map<String, Object> findEventRecord(String id) {
		
		return yjjlMapper.findEventRecord(id);
	}

	@Override
	public Map<String, String> queryYjjlStatusAndYjyaNameAndGzzName(String yjjlId, String gzzId) {
		Map<String, String> resultMap = new LinkedHashMap<String, String>();
		String cusNumber = ""; // 应急预案所属监狱编号
		String yjyaName = "";// 应急预案名称
		String yjjlStatus = "";// 应急记录状态
		String gzzName = "";// 工作组名称
		if(Util.notNull(yjjlId)) {
			YjjlEntity yjjlEntity = this.getDao().getById(yjjlId);
			if(yjjlEntity != null) {
				cusNumber = yjjlEntity.getEhrCusNumber();
				yjyaName = yjjlEntity.getEpiPlanName();
				yjjlStatus = yjjlEntity.getEhrStatus();
			}
		}
		if(Util.notNull(gzzId)) {
			GzzglEntity ggzglEntity = this.gzzglMapper.getById(gzzId);
			if(ggzglEntity != null) {
				gzzName = ggzglEntity.getWgiWorkgroupName();
			}
		}
		resultMap.put("cusNumber", cusNumber);
		resultMap.put("yjyaName", yjyaName);
		resultMap.put("yjjlStatus", yjjlStatus);
		resultMap.put("gzzName", gzzName);
		return resultMap;
	}
	
	
}
