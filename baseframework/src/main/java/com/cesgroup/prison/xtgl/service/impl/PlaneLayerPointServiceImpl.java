package com.cesgroup.prison.xtgl.service.impl;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.jfsb.entity.AlertorEntity;
import com.cesgroup.prison.jfsb.entity.Camera;
import com.cesgroup.prison.jfsb.entity.DoorEntity;
import com.cesgroup.prison.jfsb.service.AlertorService;
import com.cesgroup.prison.jfsb.service.DoorService;
import com.cesgroup.prison.jfsb.service.ICameraService;
import com.cesgroup.prison.jfsb.service.TalkBackBaseService;
import com.cesgroup.prison.xtgl.dao.PlaneLayerPointMapper;
import com.cesgroup.prison.xtgl.entity.PlaneLayerPoint;
import com.cesgroup.prison.xtgl.service.PlaneLayerPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class PlaneLayerPointServiceImpl extends BaseService<PlaneLayerPoint,String>  implements PlaneLayerPointService{
	
	@Autowired
	private PlaneLayerPointMapper planeLayerPointMapper;
	
	@Autowired
	private ICameraService cameraService;
	
	@Autowired
	private AlertorService alertorService;
	
	@Autowired
	private TalkBackBaseService talkBackBaseService;
	
	@Autowired
	private DoorService doorService;

	@Override
	@Transactional
	public int updatePart(PlaneLayerPoint planeLayerPoint) {
		return planeLayerPointMapper.updatePart(planeLayerPoint);
	}

	@Override
	@Transactional
	public int deleteByPlpLayerIdnty(String plpLayerIdnty) {
		return planeLayerPointMapper.deleteByPlpLayerIdnty(plpLayerIdnty);
	}

	@Override
	public List<PlaneLayerPoint> findByPlpLayerIdnty(String plpLayerIdnty) {
		return planeLayerPointMapper.findByPlpLayerIdnty(plpLayerIdnty);
	}
	@Override
	public List<Map<String,Object>> searchPlaneLayerPoint(String plpLayerIdnty) {
		return planeLayerPointMapper.searchPlaneLayerPoint(plpLayerIdnty);
	}
	@Override
	@Transactional
	public void refreshPoint(String plpLayerIdnty,String cusNumber) {
		List<PlaneLayerPoint> pointList = new ArrayList<PlaneLayerPoint>();
		//如果没有传图层id，则刷新整个监狱的图层点位
		if(plpLayerIdnty==null || "".equals(plpLayerIdnty)) {
			PlaneLayerPoint entity =new PlaneLayerPoint();
			entity.setPlpCusNumber(cusNumber);
			pointList = planeLayerPointMapper.selectByEntity(entity);
		}else {
			pointList = planeLayerPointMapper.findByPlpLayerIdnty(plpLayerIdnty);
		}
		for (int i = 0; i < pointList.size(); i++) {
			String pointId = pointList.get(i).getId();
			String deviceId = pointList.get(i).getPlpDeviceIdnty();
			String deviceType = pointList.get(i).getPlpDeviceType();
			String pointName = pointList.get(i).getPlpPointName();
			String deviceModel = pointList.get(i).getPlpDeviceModel();
			switch (deviceType) {
			//摄像头
			case "1":
				Camera camera = cameraService.findById(deviceId);
				if(camera==null || "3".equals(camera.getCbdActionIndc())) {
					planeLayerPointMapper.delete(pointId);
				}else {
					if(!pointName.equals(camera.getCbdName()) || !deviceModel.equals(camera.getCbdTypeIndc())) {
						PlaneLayerPoint planeLayerPointTmp =new PlaneLayerPoint();
						planeLayerPointTmp.setId(pointId);
						planeLayerPointTmp.setPlpPointName(camera.getCbdName());
						planeLayerPointTmp.setPlpDeviceModel(camera.getCbdTypeIndc());
						planeLayerPointMapper.updatePart(planeLayerPointTmp);
					}
				}
				break;
			//对讲分机
			case "2":
				//TalkBackBaseEntity talkBackBaseEntity = talkBackBaseService.findById(deviceId);
				//点位刷新时,对讲机分主机和分机,所以查两张表
				List<String> talkNameList = talkBackBaseService.findByTbdIdntyOrTseIdnty(cusNumber,deviceId);
				if(talkNameList == null || talkNameList.size() == 0) {
					planeLayerPointMapper.delete(pointId);
				}else {
                    String talkName = talkNameList.get(0);
					if(!pointName.equals(talkName)) {
						PlaneLayerPoint planeLayerPointTmp =new PlaneLayerPoint();
						planeLayerPointTmp.setId(pointId);
						planeLayerPointTmp.setPlpPointName(talkName);
						planeLayerPointMapper.updatePart(planeLayerPointTmp);
					}
				}
				break;
			//报警器
			case "3":
				AlertorEntity alertorEntity = alertorService.findById(deviceId);
				if(alertorEntity==null || "3".equals(alertorEntity.getAbdActionIndc())) {
					planeLayerPointMapper.delete(pointId);
				}else {
					if(!pointName.equals(alertorEntity.getAbdName())) {
						PlaneLayerPoint planeLayerPointTmp =new PlaneLayerPoint();
						planeLayerPointTmp.setId(pointId);
						planeLayerPointTmp.setPlpPointName(alertorEntity.getAbdName());
						planeLayerPointMapper.updatePart(planeLayerPointTmp);
					}
				}
				break;
			//门禁
			case "4":
				DoorEntity doorEntity = doorService.findById(deviceId);
				if(doorEntity==null || "3".equals(doorEntity.getDcbActionIndc())) {
					planeLayerPointMapper.delete(pointId);
				}else {
					if(!pointName.equals(doorEntity.getDcbName())) {
						PlaneLayerPoint planeLayerPointTmp =new PlaneLayerPoint();
						planeLayerPointTmp.setId(pointId);
						planeLayerPointTmp.setPlpPointName(doorEntity.getDcbName());
						planeLayerPointMapper.updatePart(planeLayerPointTmp);
					}
				}
				break;
			//监舍标签
			case "5":
				
				break;
			default:
				break;
			}
		}
	}
}
