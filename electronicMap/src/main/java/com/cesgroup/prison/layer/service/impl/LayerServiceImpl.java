package com.cesgroup.prison.layer.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.layer.dao.LayerMapper;
import com.cesgroup.prison.layer.dao.LayerPointsMapper;
import com.cesgroup.prison.layer.entity.Layer;
import com.cesgroup.prison.layer.entity.LayerPoints;
import com.cesgroup.prison.layer.service.LayerService;

@Service
public class LayerServiceImpl extends BaseService<Layer, String> implements LayerService{

    @Autowired
    LayerMapper layerMapper;
    
    @Autowired
    LayerPointsMapper layerPointsMapper;
    
    @Override
    public LayerMapper getDao(){
        return layerMapper;
    }

	@Override
	@Transactional
	public Layer saveLayer(Layer layer) throws Exception {
		UserBean userBean= AuthSystemFacade.getLoginUserInfo();
		String userId = userBean.getUserId();
		layer.setLinUpdtUserId(userId);
		layer.setLinUpdtTime(new Date());
		layer.setLinCrteUserId(userId);
		layer.setLinCrteTime(new Date());
		String points  = layer.getLinPoints();
		String[] pointGroup = points.split(";");
		int order = 1;
		layer = super.insert(layer);
		//遍历图层点信息
		for(String pointDetail:pointGroup){
			if(!"".equals(pointDetail)){
				String[] pointDetail2 = pointDetail.split(",");
				String pointX = pointDetail2[0];
				String pointY = pointDetail2[1];
				String pointZ = pointDetail2[2];
				LayerPoints layerPoints = new LayerPoints();
				layerPoints.setLpoLayerId(layer.getId());
				layerPoints.setLpoPointX(pointX);
				layerPoints.setLpoPointY(pointY);
				layerPoints.setLpoPointZ(pointZ);
				layerPoints.setLpoOrder(order++);
				layerPoints.setLpoUpdtUserId(userId);
				layerPoints.setLpoUpdtTime(new Date());
				layerPoints.setLpoCrteUserId(userId);
				layerPoints.setLpoCrteTime(new Date());
				layerPointsMapper.insertSelective(layerPoints);
			}
		}
		return layer;
	}

	@Override
	@Transactional
	public void delete(String id) {
		layerPointsMapper.deleteByLayerId(id);
		super.delete(id);
	}

	@Override
	public Layer findByLayer(Layer layer) {
		List<Layer> listLayer = layerMapper.selectByEntity(layer);
		if(listLayer.isEmpty())
			return null;
		layer = listLayer.get(0);
		List<LayerPoints> list = layerPointsMapper.findByLayerId(layer.getId());
		layer.setLayerPoints(list);
		return layer;
	}
    
}
