package com.cesgroup.prison.yjct.service.impl;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.yjct.dao.CzffczxMapper;
import com.cesgroup.prison.yjct.dao.FgglMapper;
import com.cesgroup.prison.yjct.dao.GzzcyMapper;
import com.cesgroup.prison.yjct.dao.WzglMapper;
import com.cesgroup.prison.yjct.dao.YabzMapper;
import com.cesgroup.prison.yjct.dao.YaczMapper;
import com.cesgroup.prison.yjct.dao.YapzMapper;
import com.cesgroup.prison.yjct.dao.ZjglMapper;
import com.cesgroup.prison.yjct.entity.CzffczxEntity;
import com.cesgroup.prison.yjct.entity.YabzEntity;
import com.cesgroup.prison.yjct.entity.YaczEntity;
import com.cesgroup.prison.yjct.entity.YapzEntity;
import com.cesgroup.prison.yjct.service.YabzService;

@Service("yabzService")
public class YabzServiceImpl extends BaseDaoService<YabzEntity, String, YabzMapper> implements YabzService {
	
	@Resource
	private YabzMapper yabzMapper;	
	@Resource
	private YapzMapper yapzMapper;
	@Resource
	private GzzcyMapper gzzcyMapper;	
	@Resource
	private YaczMapper yaczMapper;		 
	@Resource
	private WzglMapper wzglMapper;		 
	@Resource
	private FgglMapper fgglMapper;
	@Resource
	private CzffczxMapper czffczxMapper;
	@Resource
	private ZjglMapper zjglMapper;
	
	@Override
	public YabzEntity getById(String id) {
		
		return yabzMapper.getById(id);
	}

	@Override
	public Page<YabzEntity> findList(YabzEntity yabzEntity, PageRequest pageRequest) {
		
		return yabzMapper.findList(yabzEntity, pageRequest);
	}

	@Override
	public List<YabzEntity> findAllList(YabzEntity yabzEntity) {
		
		return yabzMapper.findAllList(yabzEntity);
	}

	@Override
	@Transactional
	public YabzEntity saveOrUpdate(YabzEntity yabzEntity) {
		
		String id = yabzEntity.getId();		
		if(id != null && !"".equals(id)) {
			yabzMapper.update(yabzEntity);
		} else {			
			yabzMapper.insert(yabzEntity);			
		}
		
		return yabzEntity;
	}	

	@Override
	@Transactional
	public void saveOrUpdateGzzInfo(YabzEntity yabzEntity) {

		List<YapzEntity> yapzList = yabzEntity.getYapzList();
		
		// ????????????????????????
		yabzEntity = yabzMapper.getById(yabzEntity.getId());
		
		// ????????????????????????
		YapzEntity yapzEntity = new YapzEntity();
		yapzEntity.setEpcPlanFid(yabzEntity.getId());
		yapzEntity.setEpcConfigType(CommonConstant.EPC_CONFIG_TYPE_GZZ_CODE);		
		yapzMapper.deleteByCondition(yapzEntity);
		
		// ????????????????????????
 		for(YapzEntity yapz : yapzList) {
			yapz.setEpcCusNumber(yabzEntity.getEpiCusNumber());
			yapz.setEpcCrteTime(new Date());
			yapz.setEpcCrteUserId(yabzEntity.getEpiCrteUserId());
			yapz.setEpcUpdtTime(new Date());
			yapz.setEpcUpdtUserId(yabzEntity.getEpiUpdtUserId());			 	
 		}		
		yapzMapper.insert(yapzList);
	}

	@Override
	@Transactional
	public String saveOrUpdateCzffInfo(YabzEntity yabzEntity) {

		List<YapzEntity> yapzList = yabzEntity.getYapzList();
		String id = yabzEntity.getId();
		String msg = "";
		
		List<String> idList = new ArrayList<String>();
		for(YapzEntity yapz : yapzList) {
			idList.add(yapz.getEpcConfigItemFid());
		}
		
		if(id != null && !"".equals(id)) {
			// ??????-????????????????????????????????????????????????
			yaczMapper.deleteByPlanidAndMethodid(id, idList);
		}
		
 		
		// ?????????????????????????????????????????????		start	--heqh ???????????????????????????????????????????????????	?????? ???????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
 		List<CzffczxEntity> czffczxList = czffczxMapper.findCountByPlanid(idList);
		
		YaczEntity yaczEntity = new YaczEntity();
		yaczEntity.setEpaPlanFid(yabzEntity.getId());
		//????????????????????? ?????? ?????? ????????????????????????????????????
		List<YaczEntity> yaczList = yaczMapper.findCountByPlanid(yaczEntity);
		
		if(yaczList != null) {
			if(czffczxList.size() <= yaczList.size()) {	
				for(int i = 0; i < czffczxList.size(); i++) {
					CzffczxEntity czffczx = czffczxList.get(i);
					
					for(int j = 0; j < yaczList.size(); j++) {
						if(czffczx.getMraMethodFid().equals(yaczList.get(j).getEpaMethodFid())
								&& Integer.parseInt(czffczx.getActionCount()) != Integer.parseInt(yaczList.get(j).getActionCount())) {							
							msg = "??????????????????????????????????????????";
							return msg;
						}
					}
				}
			} else {
				msg = "??????????????????????????????????????????";
				return msg;
			}
		} else {
			msg = "??????????????????????????????????????????";
			return msg;
		}
		
		
		
		// end
				
		// ????????????????????????
		yabzEntity = yabzMapper.getById(yabzEntity.getId());
		
		// ????????????????????????
		YapzEntity yapzEntity = new YapzEntity();
		yapzEntity.setEpcPlanFid(yabzEntity.getId());
		yapzEntity.setEpcConfigType(CommonConstant.EPC_CONFIG_TYPE_CZFF_CODE);		
		yapzMapper.deleteByCondition(yapzEntity);
				 
		// ????????????????????????
 		for(YapzEntity yapz : yapzList) {
			yapz.setEpcCusNumber(yabzEntity.getEpiCusNumber());
			yapz.setEpcCrteTime(new Date());
			yapz.setEpcCrteUserId(yabzEntity.getEpiCrteUserId());
			yapz.setEpcUpdtTime(new Date());
			yapz.setEpcUpdtUserId(yabzEntity.getEpiUpdtUserId());	
 		}		
		yapzMapper.insert(yapzList);
		
		return msg;
	}

	@Override
	@Transactional
	public String saveOrUpdatePlanAction(YabzEntity yabzEntity) throws Exception {
		
		String msg = "";
		List<YaczEntity> yaczList = yabzEntity.getYaczList();
		
		// ????????????????????????????????????
		/*YapzEntity yapzEntity = new YapzEntity();
		yapzEntity.setEpcPlanFid(yabzEntity.getId());
		yapzEntity.setEpcConfigType(CommonConstant.EPC_CONFIG_TYPE_CZFF_CODE);
		yapzEntity.setEpcConfigItemFid(yaczList.get(0).getEpaMethodFid());
		List<YapzEntity> list = yapzMapper.findAllList(yapzEntity);
		
		if(list == null || list.size() == 0) {
			msg = "?????????????????????";
			return msg;
		}*/
		
		// ????????????????????????
		yabzEntity = yabzMapper.getById(yabzEntity.getId());
				
		if(yaczList != null && yaczList.size() > 0) {
			// ????????????????????????
			YaczEntity paramYacz = new YaczEntity();
			paramYacz.setEpaPlanFid(yabzEntity.getId());
			paramYacz.setEpaMethodFid(yaczList.get(0).getEpaMethodFid());
			paramYacz.setEpaRelActionType(yaczList.get(0).getEpaRelActionType());
			yaczMapper.deleteByCondition(paramYacz);
						 			
 			for(YaczEntity yaczEntity : yaczList) {				
				yaczEntity.setEpaCusNumber(yabzEntity.getEpiCusNumber());
				yaczEntity.setEpaCrteTime(new Date());
				yaczEntity.setEpaCrteUserId(yabzEntity.getEpiCrteUserId());
				yaczEntity.setEpaUpdtTime(new Date());
				yaczEntity.setEpaUpdtUserId(yabzEntity.getEpiUpdtUserId());				 
			}
			
			// ????????????????????????
			yaczMapper.insert(yaczList);			 
		}
		
		return msg;
	}
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		for(String id : idArr) {
			yabzMapper.delete(id);
		}
		
		// ????????????????????????		
		yapzMapper.batchDeleteByPlanid(Arrays.asList(idArr));
		
		// ????????????????????????
		yaczMapper.batchDeleteByPlanid(Arrays.asList(idArr));		
	}

	@Override
	@Transactional
	public void updatePlanStatus(YabzEntity yabzEntity) {
		
		YabzEntity yabz = yabzMapper.getById(yabzEntity.getId());
		yabz.setEpiPlanStatus(yabzEntity.getEpiPlanStatus());
		yabz.setEpiUpdtTime(yabzEntity.getEpiUpdtTime());
		yabz.setEpiUpdtUserId(yabzEntity.getEpiUpdtUserId());
		yabzMapper.update(yabz);	
	}
	
	@Override
	@Transactional
	public void copyYaxx(YabzEntity yabzEntity) throws Exception {
		
		// ???????????????
		YabzEntity yabz = yabzMapper.getById(yabzEntity.getId());
		yabz.setEpiPlanStatus("5");
		yabz.setEpiUpdtTime(yabzEntity.getEpiCrteTime());
		yabz.setEpiUpdtUserId(yabzEntity.getEpiCrteUserId());
		yabzMapper.update(yabz);
		
		// ??????????????????
		/*YabzEntity yabzCopy = new YabzEntity();
		BeanUtils.copyProperties(yabzCopy, yabz);
		yabzCopy.setId(null);
		yabzCopy.setEpiPlanStatus("0");
		yabzCopy.setEpiAppFlow(null);
		yabzCopy.setEpiCrteTime(yabzEntity.getEpiCrteTime());
		yabzCopy.setEpiCrteUserId(yabzEntity.getEpiCrteUserId());
		yabzCopy.setEpiUpdtTime(yabzEntity.getEpiCrteTime());
		yabzCopy.setEpiUpdtUserId(yabzEntity.getEpiCrteUserId());*/
		yabz.setId(null);
		yabz.setEpiPlanStatus("0");
		yabz.setEpiAppFlow(null);
		yabz.setEpiCrteTime(yabzEntity.getEpiCrteTime());
		yabz.setEpiCrteUserId(yabzEntity.getEpiCrteUserId());
		yabz.setEpiUpdtTime(yabzEntity.getEpiCrteTime());
		yabz.setEpiUpdtUserId(yabzEntity.getEpiCrteUserId());
		yabzMapper.insert(yabz);
		
		// ??????????????????
		YapzEntity yapzEntity = new YapzEntity();
		yapzEntity.setEpcPlanFid(yabzEntity.getId());
		List<YapzEntity> yapzList = yapzMapper.findAllList1(yapzEntity);
		for(YapzEntity yapz : yapzList) {
			yapz.setId(null);
			yapz.setEpcPlanFid(yabz.getId());
			yapz.setEpcCrteTime(yabzEntity.getEpiCrteTime());
			yapz.setEpcCrteUserId(yabzEntity.getEpiCrteUserId());
			yapz.setEpcUpdtTime(yabzEntity.getEpiCrteTime());
			yapz.setEpcUpdtUserId(yabzEntity.getEpiCrteUserId());
			yapzMapper.insert(yapz);
		}
		
		// ??????????????????
		YaczEntity yaczEntity = new YaczEntity();
		yaczEntity.setEpaPlanFid(yabzEntity.getId());
		List<YaczEntity> yaczList = yaczMapper.findAllList1(yaczEntity);
		for(YaczEntity yacz : yaczList) {
			yacz.setId(null);
			yacz.setEpaPlanFid(yabz.getId());
			yacz.setEpaCrteTime(yabzEntity.getEpiCrteTime());
			yacz.setEpaCrteUserId(yabzEntity.getEpiCrteUserId());
			yacz.setEpaUpdtTime(yabzEntity.getEpiCrteTime());
			yacz.setEpaUpdtUserId(yabzEntity.getEpiCrteUserId());
			yaczMapper.insert(yacz);
		}
	}
	
	/**
	 * 
	 * ????????????-????????????????????????-?????????????????????????????????????????????
	 * 
	 * */
	
	@Override
	@Transactional
	public String saveOrUpdatePlanActionRecord(YabzEntity yabzEntity) throws Exception {
		
		String msg = "";
		List<YaczEntity> yaczList = yabzEntity.getYaczList();
		
		
		// ????????????????????????
		yabzEntity = yabzMapper.getById(yabzEntity.getId());
				
		if(yaczList != null && yaczList.size() > 0) {
			// ????????????????????????
			YaczEntity paramYacz = new YaczEntity();
			paramYacz.setEpaPlanFid(yabzEntity.getId());
			paramYacz.setEpaRelActionType(yaczList.get(0).getEpaRelActionType());
			yaczMapper.deleteByCondition(paramYacz);
						 			
 			for(YaczEntity yaczEntity : yaczList) {				
				yaczEntity.setEpaCusNumber(yabzEntity.getEpiCusNumber());
				yaczEntity.setEpaCrteTime(new Date());
				yaczEntity.setEpaCrteUserId(yabzEntity.getEpiCrteUserId());
				yaczEntity.setEpaUpdtTime(new Date());
				yaczEntity.setEpaUpdtUserId(yabzEntity.getEpiUpdtUserId());				 
			}
			
			// ????????????????????????
			yaczMapper.insert(yaczList);			 
		}
		
		return msg;
	}
}
