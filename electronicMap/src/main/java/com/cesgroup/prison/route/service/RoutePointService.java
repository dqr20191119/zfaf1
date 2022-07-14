package com.cesgroup.prison.route.service;
import com.cesgroup.prison.common.facade.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.route.dao.CdsRoamPointRltnMapper;
import com.cesgroup.prison.route.dao.RouteMapper;
import com.cesgroup.prison.route.entity.CdsRoamPointRltn;
import com.cesgroup.prison.route.entity.Route;
import com.cesgroup.prison.utils.CommonUtil;

@Service
public class RoutePointService extends BaseService<CdsRoamPointRltn, String>{
	
	@Autowired
	private CdsRoamPointRltnMapper routePointMapper;
	@Autowired
	private RouteMapper routeMapper;

	@Transactional(readOnly=false)
	public int deleteByPrimaryKey(String id) {
		return routePointMapper.deleteByPrimaryKey(id);
	}
	
	@Transactional(readOnly=false)
	public int insertPoint(CdsRoamPointRltn record) throws Exception {

		record.setRprCrteUserId(AuthSystemFacade.getLoginUserInfo().getUserId());

    	return routePointMapper.insertPoint(record);
    }
	
	@Transactional(readOnly=false)
	public int insertSelectivePoint(CdsRoamPointRltn record) throws Exception {
		record.setRprCusNumber(AuthSystemFacade.getLoginUserInfo().getCusNumber());
		record.setId(CommonUtil.createUUID().replace("-", ""));
		record.setRprCrteUserId(AuthSystemFacade.getLoginUserInfo().getUserId());
		CdsRoamPointRltn point= new CdsRoamPointRltn();
		point.setRprRoamIdnty(record.getRprRoamIdnty());
		List<CdsRoamPointRltn> selectByEntity = routePointMapper.selectByRouteId(point);
		for(CdsRoamPointRltn cds:selectByEntity) {
			if(cds.getRprOrder()==record.getRprOrder()) {
				throw new RuntimeException("巡视点次序重复");
			}
		}
    	return routePointMapper.insertSelectivePoint(record);
    }
  
   public CdsRoamPointRltn selectByPrimaryKey(String id) {
    	return routePointMapper.selectByPrimaryKey(id);
    }
   
    @Transactional(readOnly=false)
   public  int updateByPrimaryKeySelective(CdsRoamPointRltn record) throws Exception {
		record.setRprUpdtUserId(AuthSystemFacade.getLoginUserInfo().getUserId());
		CdsRoamPointRltn point= new CdsRoamPointRltn();
		point.setRprRoamIdnty(record.getRprRoamIdnty());
		List<CdsRoamPointRltn> selectByEntity = routePointMapper.selectByRouteId(point);
		for(CdsRoamPointRltn cds:selectByEntity) {
			if(cds.getRprOrder()==record.getRprOrder()&&!cds.getId().equals(record.getId())) {
				throw new RuntimeException("巡视点次序重复");
			}
		}
    	return routePointMapper.updateByPrimaryKeySelective(record);
    }
    
    @Transactional(readOnly=false)
   public  int updateByPrimaryKey(CdsRoamPointRltn record) throws Exception {
		record.setRprUpdtUserId(AuthSystemFacade.getLoginUserInfo().getUserId());
    	return routePointMapper.updateByPrimaryKey(record);
    }
  
    public Page<CdsRoamPointRltn> findByPage(Map<String, String> map,Pageable page){
    	Page<CdsRoamPointRltn> findByPage = routePointMapper.findByPage(map, page);
    	for(CdsRoamPointRltn point:findByPage.getContent()) {
    		point.setRprRoamName(routeMapper.findById(point.getRprRoamIdnty()).getRpiName());
    	}
    	return findByPage;
    }
    
    public List<CdsRoamPointRltn> selectPointsByRouteID(CdsRoamPointRltn cdsRoamPointRltn){
    	return routePointMapper.selectByRouteId(cdsRoamPointRltn);
    }
}
