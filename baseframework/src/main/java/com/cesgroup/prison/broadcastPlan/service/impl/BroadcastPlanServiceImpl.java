package com.cesgroup.prison.broadcastPlan.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.broadcastPlan.dao.BroadcastPlanDao;
import com.cesgroup.prison.broadcastPlan.entity.BroadcastPlan;
import com.cesgroup.prison.broadcastPlan.service.BroadcastPlanService;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.dao.BroadcastFileDao;
import com.cesgroup.prison.jfsb.entity.BroadcastEntity;
import com.cesgroup.prison.jfsb.entity.BroadcastFile;
import com.cesgroup.prison.jfsb.service.BroadcastService;

@Service
@Transactional
public class BroadcastPlanServiceImpl extends BaseDaoService<BroadcastPlan, String, BroadcastPlanDao> implements BroadcastPlanService {
	@Resource
	private BroadcastPlanDao mapper;
	@Resource
	private BroadcastService broadcastService;
	@Resource
	private BroadcastFileDao broadcastFileDao;
	@Override
	public Page<BroadcastPlan> listAll(BroadcastPlan broadcastPlan,
			Pageable pageable) throws BusinessLayerException {
		Page<BroadcastPlan> list = null;
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String level = user.getUserLevel().toString();
			if(level.equals("2")) {
				broadcastPlan.setDbpCusNumber(user.getCusNumber());
			}else if("1".equals(level)) {
				broadcastPlan.setDbpCusNumber(null);
			}
			list  = mapper.listAll(broadcastPlan, pageable);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}



	@Override
	public AjaxMessage deleteByIds(List<String> id) throws BusinessLayerException {
		AjaxMessage ajaxMessage =null;
		try {
			mapper.deleteByIds(id);
			//联动删除关联了广播预案的报警预案
			mapper.deletByBroadcastPlanId(id);
			ajaxMessage=new AjaxMessage(true,null,"删除成功");
		} catch (Exception e) {
			ajaxMessage = new AjaxMessage(false);
			e.printStackTrace();
			throw new BusinessLayerException("删除广播预案异常") ;
		}
		return ajaxMessage;
	}

	@Override
	public void save(BroadcastPlan entity) {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		entity.setDbpCusNumber(user.getCusNumber());
		entity.setDbpCreateUserId(user.getUserId());
		entity.setDbpCreateTime(DateUtils.getCurrentDate(true));
		String  broadcastId =entity.getDbpBroadcastId();
		String[] broadcastIds = broadcastId.split(",");
		String bcName = "";
		//拼接广播名称
		for(int i=0;i<broadcastIds.length;i++) {
			BroadcastEntity broadcastEntity = broadcastService.selectOne(broadcastIds[i]);
			if(i!=broadcastIds.length-1) {
				bcName+=broadcastEntity.getBbdName()+",";
			}else {
				bcName+=broadcastEntity.getBbdName();
			}
		}
		entity.setDbpBroadcastName(bcName);
		if(entity.getDbpBroadcastFileDtlsId()!=null) {
			BroadcastFile broadcastFile = broadcastFileDao.selectOne(entity.getDbpBroadcastFileDtlsId());
			entity.setDbpBroadcastFileDtlsName(broadcastFile.getBfdName());
		}
		mapper.insert(entity);
	}

	@Override
	public void updateById(BroadcastPlan entity) {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		entity.setDbpUpdateUserId(user.getUserId());
		entity.setDbpUpdateTime(DateUtils.getCurrentDate(true));
		entity.setDbpCusNumber(user.getCusNumber());
		String  broadcastId =entity.getDbpBroadcastId();
		String[] broadcastIds = broadcastId.split(",");
		String bcName = "";
		//拼接广播名称
		for(int i=0;i<broadcastIds.length;i++) {
			BroadcastEntity broadcastEntity = broadcastService.selectOne(broadcastIds[i]);
			if(i!=broadcastIds.length-1) {
				bcName+=broadcastEntity.getBbdName()+",";
			}else {
				bcName+=broadcastEntity.getBbdName();
			}
		}
		entity.setDbpBroadcastName(bcName);
		if(entity.getDbpBroadcastFileDtlsId()!=null) {
			BroadcastFile broadcastFile = broadcastFileDao.selectOne(entity.getDbpBroadcastFileDtlsId());
			entity.setDbpBroadcastFileDtlsName(broadcastFile.getBfdName());
		}
		mapper.update(entity);
	}



	@Override
	public List<BroadcastPlan> queryByBfdCusNumber(String cusNumber) {
		BroadcastPlan broadcastPlan = new BroadcastPlan();
		broadcastPlan.setDbpCusNumber(cusNumber);
		return this.getDao().selectByEntity(broadcastPlan);
	}



	@Override
	public BroadcastPlan selectById(String id) {
		//查询广播预案
		BroadcastPlan broadcstPlan = mapper.selectOne(id);
		//通过广播id查询广播
		String broadcastIds ="";
		if(broadcstPlan!=null) {
			 broadcastIds = broadcstPlan.getDbpBroadcastId();
		}
		List<Map<String,Object>> broadcastList = new ArrayList<Map<String,Object>>();
		
		if(StringUtils.isNotBlank(broadcastIds)) {
			String[] splitBroadcastIds = broadcastIds.split(",");
			for (int i = 0; i < splitBroadcastIds.length; i++) {
				Map<String,Object> map = new HashMap<>();
				BroadcastEntity braodcast = broadcastService.selectOne(splitBroadcastIds[i]);
				map.put("ID", splitBroadcastIds[i]);
				map.put("BBD_NAME", braodcast.getBbdName());
				map.put("BBD_AREA", braodcast.getBbdArea());
				broadcastList.add(map);
			}
		}
		broadcstPlan.setBroadcasts(broadcastList);
		return broadcstPlan;
	}

}
