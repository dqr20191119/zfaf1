package com.cesgroup.prison.facemark.service;

import javax.annotation.Resource;

import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.rlsb.dao.RlsbNowMapper;
import com.cesgroup.prison.rlsb.entity.RlsbNowEntity;
import com.cesgroup.prison.utils.DataUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.cesgroup.prison.fm.service.IMessageProcess;
import com.cesgroup.prison.rlsb.dao.RlsbMapper;
import com.cesgroup.prison.rlsb.entity.RlsbEntity;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FacemarkProcess  implements IMessageProcess {
	
	@Resource
	private RlsbMapper rlsbMapper;
      @Resource
	private RlsbNowMapper rlsbNowMapper;
	@Transactional
	@Override
	public void processMessage(String cusNumber, JSONObject jsonObj) {
          RlsbEntity rlsbEntity = new RlsbEntity();
          JSONObject data = jsonObj.getJSONObject("body").getJSONObject("data");
          JSONObject capInf = data.getJSONObject("jsonData").getJSONObject("capInf");
          JSONObject blkInf = data.getJSONObject("jsonData").getJSONObject("blkInf");
          JSONObject rect = capInf.getJSONObject("rect");
          JSONObject humanDetail = blkInf.getJSONObject("humanDetail");
          rlsbEntity = entity(cusNumber,data,capInf,blkInf,rect,humanDetail);
          rlsbMapper.insert(rlsbEntity);
		//删除前一周的数据 再插入数据
          RlsbNowEntity rlsbNowEntity = new RlsbNowEntity();
          //获取一周前的时间
          Date daytTime = Util.getBeforeOrAfterDay(-7);
          SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
          String st = Util.toStr(daytTime, simpleDateFormat);
          rlsbNowEntity.setSt(st);
          rlsbNowEntity.setCusNumber(cusNumber);
          rlsbNowMapper.deleteBeforNowTimeOfDay(rlsbNowEntity);
          RlsbNowEntity rlsbNowEntity1 = new RlsbNowEntity();
          BeanUtils.copyProperties(rlsbEntity,rlsbNowEntity1);
          rlsbNowMapper.insert(rlsbNowEntity1);
	}
	
	public RlsbEntity entity(String cusNumber, JSONObject data, JSONObject capInf, JSONObject blkInf, JSONObject rect, JSONObject humanDetail) {
		RlsbEntity entity = new RlsbEntity();
		entity.setCusNumber(cusNumber);
		entity.setEventUuid(data.getString("eventUUID"));
		entity.setSt(data.getString("st"));
		entity.setSrcIndexCode(data.getString("srcIndexCode"));
		entity.setSrcType(data.getString("srcType"));
		entity.setEventType(data.getString("eventType"));
		entity.setChannelIndexCode(capInf.getString("channelIndexCode"));
		entity.setChannelName(capInf.getString("channelName"));
		entity.setChannelNo(capInf.getString("channelNo"));
		entity.setLocationIndexCode(capInf.getString("locationIndexCode"));
		entity.setLocationName(capInf.getString("locationName"));
		entity.setCapSmallpicUrl(capInf.getString("capSmallPicUrl"));
		entity.setCapBkgpicUrl(capInf.getString("capBkgPicUrl"));
		entity.setCapSmallpicUrlId(capInf.getString("capSmallPicUrlId"));
		entity.setCapBkgpicUrlId(capInf.getString("capSmallPicUrlId"));
		entity.setRelationId(capInf.getString("relationId"));
		entity.setCapTime(capInf.getString("capTime"));
		entity.setRectHeight(rect.getString("height"));
		entity.setRectWidth(rect.getString("width"));
		entity.setRectX(rect.getString("x"));
		entity.setRectY(rect.getString("y"));
		entity.setBlacklistId(blkInf.getString("blacklistId"));
		entity.setNamelistId(blkInf.getString("namelistId"));
		entity.setFacepicUrl(blkInf.getString("facePicurl"));
		entity.setFacepicUrlId(blkInf.getString("facePicurlId"));
		entity.setSimilarity(blkInf.getString("similarity"));
		entity.setBlacklistName(humanDetail.getString("blacklistName"));
		entity.setNamelistName(humanDetail.getString("namelistName"));
		entity.setCertificateType(humanDetail.getString("certificateType"));
		entity.setCertificateNo(humanDetail.getString("certificateNo"));
		entity.setCardNo(humanDetail.getString("cardNo"));
		entity.setGender(humanDetail.getString("gender"));
		entity.setNation(humanDetail.getString("nation"));
		entity.setCreateTime(humanDetail.getString("createTime"));
		return entity;
	}
	

}
