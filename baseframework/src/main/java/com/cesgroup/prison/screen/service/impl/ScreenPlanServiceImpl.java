package com.cesgroup.prison.screen.service.impl;

import java.util.*;

import cn.hutool.core.util.IdUtil;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.util.MsgIdUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.frm.net.netty.bean.MsgBody;
import com.cesgroup.frm.net.netty.bean.MsgHeader;
import com.cesgroup.prison.bfcf.common.BfCfConstants;
import com.cesgroup.prison.netamq.service.MqMessageSender;
import com.cesgroup.prison.screen.dao.*;
import com.cesgroup.prison.screen.dto.ScreenConstants;
import com.cesgroup.prison.screen.dto.StartScreenAudioMsgBody;
import com.cesgroup.prison.screen.dto.StartScreenSqMsgBody;
import com.cesgroup.prison.screen.dto.WindowMsg;
import com.cesgroup.prison.screen.entity.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.screen.service.ScreenPlanService;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Service
@Transactional
public class ScreenPlanServiceImpl extends BaseDaoService<ScreenPlanEntity, String, ScreenPlanMapper>
		implements ScreenPlanService {
    /**
     * gson工具类
     */
    private static final Gson gson = new GsonBuilder().create();
    /**
     * 消息发送接口
     */
    @Resource
    private MqMessageSender mqMessageSender;


	/**
	* @Fields sparMapper : 区域
	*/
	@Autowired
	private ScreenPlanAreaRltnMapper sparMapper;

	/**
	* @Fields sacMapper : 信号源
	*/
	@Autowired
	private ScreenAreaCameraMapper sacMapper;

	/**
	* @Fields sawMapper : 窗口
	*/
	@Autowired
	private ScreenAreaWindowMapper sawMapper;
    /**
     * 现用预案
     */
      @Resource
	private ScreenPlanNewMapper screenPlanNewMapper;

     @Resource
	private ScreenPlanWindowCameraMapper screenPlanWindowCameraMapper;

     @Resource
     private ScreenPlanWindowNewMapper screenPlanWindowNewMapper;

	@Override
	public Page<Map<String, Object>> listAll(ScreenPlanEntity entity, Pageable pageable) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (entity != null) {
			map.put("screenPlanEntity", entity);
		}
		return getDao().listAll(map, pageable);
	}

	@Override
	public AjaxMessage addInfo(ScreenPlanEntity entity) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			if (entity != null) {
				UserBean userBean = AuthSystemFacade.getLoginUserInfo();
				String userId = userBean.getUserId();
				Date date = new Date();
				ScreenPlanEntity screenPlanEntity = new ScreenPlanEntity();
				screenPlanEntity.setSplCusNumber(userBean.getCusNumber());
				screenPlanEntity.setSplPlanIndc(entity.getSplPlanIndc());
				if (getDao().selectCountByEntity(screenPlanEntity) == 0) {
					entity.setSplCusNumber(userBean.getCusNumber());
					entity.setSplCrteTime(date);
					entity.setSplCrteUserId(userId);
					entity.setSplUpdtTime(date);
					entity.setSplUpdtUserId(userId);
					getDao().insert(entity);
					flag = true;
					obj = "保存成功";
				} else {
					flag = false;
					obj = "【" + entity.getSplPlanIndc() + "】已存在,保存失败";
				}
			} else {
				flag = false;
				obj = "保存失败，参数错误";
			}
		} catch (Exception e) {
			flag = false;
			obj = "保存发生异常";
		}
		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@Override
	public void updateInfo(ScreenPlanEntity entity) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		if (entity != null) {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			String userId = userBean.getUserId();
			Date date = new Date();
			entity.setSplUpdtTime(date);
			entity.setSplUpdtUserId(userId);
			map.put("screenPlanEntity", entity);
		}
		getDao().updateInfo(map);

	}

	@Override
	public void deleById(String id) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(id)) {
			map.put("id", id);
			getDao().deleteById(map);
		}
	}

	@Override
	public ScreenPlanEntity findById(String id) {
		return getDao().selectOne(id);
	}

	@Override
	public List<Map<String, Object>> searchCombDataForPlan(String cusNumber, String isDynamic) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber);
		}
		if (StringUtils.isNotBlank(isDynamic)) {
			map.put("isDynamic", isDynamic);
		}

		return getDao().searchCombData(map);
	}

	@Override
	public List<Map<String, Object>> searchCombData(String cusNumber, String planId) {
		Map<String, Object> map = new HashMap<String, Object>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber);
		}
		if (StringUtils.isNotBlank(planId)) {
			map.put("planId", planId);
		}
		return sparMapper.searchCombData(map);
	}

	@Override
	public Map<String, Object> listAll(String cusNumber, String screenPlanId, String screenAreaId, String type) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber);
		}
		if (StringUtils.isNotBlank(screenPlanId)) {
			map.put("screenPlanId", screenPlanId);
		}
		if (StringUtils.isNotBlank(screenAreaId)) {
			map.put("screenAreaId", screenAreaId);
		}
		List<Map<String, Object>> list = null;
		switch (type) {
		case "1":
			list = sawMapper.listAll(map);
			break;
		case "2":
			list = sacMapper.listAll(map);
			break;
		default:
			break;
		}
		Map<String, Object> data = new HashMap<>();
		data.put("data", list);
		return data;
	}

	@SuppressWarnings("unchecked")
	@Override
	public AjaxMessage saveXhfz(Object xhfzMap) {
		AjaxMessage ajaxMessage = new AjaxMessage();
		boolean flag = false;
		Object obj = null;
		try {
			if (xhfzMap != null) {
				Map<String, String> map = (Map<String, String>) xhfzMap;
				if (!map.isEmpty()) {
					Map<String, String> screenArea = (Map<String, String>) JSON.parse(map.get("screenArea"));// 大屏区域集合
					if (screenArea != null && !screenArea.isEmpty()) {
						UserBean userBean = AuthSystemFacade.getLoginUserInfo();
						String userId = userBean.getUserId();
						String cusNumber = userBean.getCusNumber();
						Date date = new Date();
						String screenPlanId = screenArea.get("screenPlanId");// 大屏预案id
						String screenAreaId = screenArea.get("id");// 大屏区域id
						if (StringUtils.isBlank(screenAreaId)) {// 如果 大屏区域id screenAreaId 为空新增， 否则更新
							ScreenPlanAreaRltnEntity screenPlanAreaRltnEntity = new ScreenPlanAreaRltnEntity();
							screenPlanAreaRltnEntity.setSprCusNumber(cusNumber);
							screenPlanAreaRltnEntity.setSprCrteTime(date);
							screenPlanAreaRltnEntity.setSprCrteUserId(userId);
							screenPlanAreaRltnEntity.setSprUpdtTime(date);
							screenPlanAreaRltnEntity.setSprUpdtUserId(userId);
							screenPlanAreaRltnEntity.setSprScreenPlanId(screenPlanId);
							screenPlanAreaRltnEntity.setSprTimeLag(screenArea.get("sprTimeLag"));
							screenPlanAreaRltnEntity.setSprScreenAreaName(screenArea.get("sprScreenAreaName"));
							screenPlanAreaRltnEntity.setSprIsRound(screenArea.get("sprIsRound"));
							sparMapper.insert(screenPlanAreaRltnEntity);
							screenAreaId = screenPlanAreaRltnEntity.getId();
						} else {
							Map<String, Object> upMap = new HashMap<String, Object>();
							ScreenPlanAreaRltnEntity screenPlanAreaRltnEntity = new ScreenPlanAreaRltnEntity();
							screenPlanAreaRltnEntity.setSprUpdtTime(date);
							screenPlanAreaRltnEntity.setSprUpdtUserId(userId);
							screenPlanAreaRltnEntity.setSprScreenPlanId(screenPlanId);
							screenPlanAreaRltnEntity.setId(screenAreaId);
							screenPlanAreaRltnEntity.setSprTimeLag(screenArea.get("sprTimeLag"));
							screenPlanAreaRltnEntity.setSprScreenAreaName(screenArea.get("sprScreenAreaName"));
							screenPlanAreaRltnEntity.setSprIsRound(screenArea.get("sprIsRound"));
							upMap.put("screenPlanAreaRltnEntity", screenPlanAreaRltnEntity);
							sparMapper.updateInfo(upMap);
						}

						List<Map<String, String>> screenWindow = (List<Map<String, String>>) JSON
								.parse(map.get("screenWindow"));
						if (screenWindow != null && !screenWindow.isEmpty()) {
							for (Map<String, String> windowMap : screenWindow) {
								ScreenAreaWindowEntity windowEntity = new ScreenAreaWindowEntity();
								windowEntity.setSwrScreenAreaId(screenAreaId);
								windowEntity.setSwrScreenPlanId(screenPlanId);
								windowEntity.setSwrScreenWindowNum(windowMap.get("SWR_SCREEN_WINDOW_NUM"));
								windowEntity.setSwrSeqNum(windowMap.get("SWR_SEQ_NUM"));// 新增排序
								windowEntity.setSwrCrteTime(date);
								windowEntity.setSwrCrteUserId(userId);
								windowEntity.setSwrUpdtTime(date);
								windowEntity.setSwrUpdtUserId(userId);
								windowEntity.setSwrCusNumber(cusNumber);
								if (StringUtils.isNotBlank(windowMap.get("ID"))) {
									Map<String, Object> upMap = new HashMap<String, Object>();
									windowEntity.setId(windowMap.get("ID"));
									upMap.put("screenAreaWindowEntity", windowEntity);
									sawMapper.updateInfo(upMap);
								} else {
									sawMapper.insert(windowEntity);
								}
							}
						}
						List<Map<String, String>> screenCamera = (List<Map<String, String>>) JSON
								.parse(map.get("screenCamera"));
						if (screenCamera != null && !screenCamera.isEmpty()) {
							for (Map<String, String> cameramap : screenCamera) {
								ScreenAreaCameraEntity areaCameraEntity = new ScreenAreaCameraEntity();
								areaCameraEntity.setScrCameraId(cameramap.get("SCR_CAMERA_ID"));
								areaCameraEntity.setScrCameraName(cameramap.get("SCR_CAMERA_NAME"));
								areaCameraEntity.setScrCrteTime(date);
								areaCameraEntity.setScrCrteUserId(userId);
								areaCameraEntity.setScrUpdtTime(date);
								areaCameraEntity.setScrUpdtUserId(userId);
								areaCameraEntity.setScrCusNumber(cusNumber);
								areaCameraEntity.setScrSeqNum(cameramap.get("SCR_SEQ_NUM"));
								areaCameraEntity.setScrScreenAreaId(screenAreaId);
								areaCameraEntity.setScrScreenPlanId(screenPlanId);
								if (StringUtils.isNotBlank(cameramap.get("ID"))) {
									Map<String, Object> upMap = new HashMap<String, Object>();
									areaCameraEntity.setId(cameramap.get("ID"));
									upMap.put("screenAreaCameraEntity", areaCameraEntity);
									sacMapper.updateInfo(upMap);
								} else {
									sacMapper.insert(areaCameraEntity);
								}

							}
						}

						flag = true;
						obj = "保存成功";
					}
				}
			}

		} catch (Exception e) {
			flag = false;
			obj = "发生异常，保存失败";
		}

		ajaxMessage.setMsg((String) obj);
		ajaxMessage.setSuccess(flag);
		return ajaxMessage;
	}

	@Override
	public ScreenPlanAreaRltnEntity findScreenPlanAreaRltnById(String id) {
		return sparMapper.selectOne(id);
	}

	@Override
	public List<Map<String, Object>> listAllForSx(String cusNumber, String areaId, String screenPlanId,
			String cameraName) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(cusNumber)) {
			map.put("cusNumber", cusNumber);
		}
		if (StringUtils.isNotBlank(areaId)) {
			map.put("areaId", areaId);
		}
		if (StringUtils.isNotBlank(screenPlanId)) {
			map.put("screenPlanId", screenPlanId);
		}
		if (StringUtils.isNotBlank(cameraName)) {
			map.put("cameraName", cameraName);
		}
		return sacMapper.listAllForSx(map);
	}

	@Override
	public void deleteXhfzByIds(List<String> ids, String type) {
		switch (type) {
		case "1":
			sawMapper.deleteByIds(ids);
			break;
		case "2":
			sacMapper.deleteByIds(ids);
			break;
		default:
			break;
		}
	}

	@Override
	public void deleteXhfzById(String screenAreaId) {
		Map<String, Object> map = new HashMap<>();
		if (StringUtils.isNotBlank(screenAreaId)) {
			map.put("screenAreaId", screenAreaId);
			sparMapper.deleteById(map);
		}

	}

	@Override
	public int isRound(String screenPlanId) {
		int flag = 0;
		ScreenPlanEntity planEntity = getDao().selectOne(screenPlanId);
		if (planEntity != null) {
			ScreenPlanAreaRltnEntity screenPlanAreaRltnEntity = new ScreenPlanAreaRltnEntity();
			screenPlanAreaRltnEntity.setSprScreenPlanId(screenPlanId);
			if (sparMapper.selectCountByEntity(screenPlanAreaRltnEntity) > 0) {

				ScreenAreaCameraEntity cameraEntity = new ScreenAreaCameraEntity();
				cameraEntity.setScrScreenPlanId(screenPlanId);

				ScreenAreaWindowEntity screenAreaWindowEntity = new ScreenAreaWindowEntity();
				screenAreaWindowEntity.setSwrScreenPlanId(screenPlanId);

				if (sacMapper.selectCountByEntity(cameraEntity) > 0
						&& sawMapper.selectCountByEntity(screenAreaWindowEntity) > 0) {
					flag = 1;
				}
			}
		}
		return flag;
	}

    @Override
    public Page<ScreenPlanNewEntity> pageSelectAll(ScreenPlanNewEntity screenPlanNewEntity, Pageable pageable) {
        return screenPlanNewMapper.pageSelectAll(screenPlanNewEntity, pageable);
    }

    @Override
    public List<ScreenPlanWindowNewEntity> selectWindowByScreenPlanId(String screenPlanId,String cusNumber) {
        ScreenPlanWindowNewEntity screenPlanWindowNewEntity = new ScreenPlanWindowNewEntity();
        screenPlanWindowNewEntity.setScreenPlanId(screenPlanId);
        screenPlanWindowNewEntity.setCusNumber(cusNumber);
        return screenPlanWindowNewMapper.selectByScreenPlanWindowNewEntity(screenPlanWindowNewEntity);
    }

    @Override
    public List<Map<String, Object>> listAllForSxNew(String cusNumber, String areaId, String screenPlanId, String windowId) {
        String level = AuthSystemFacade.getLoginUserInfo().getUserLevel().toString();
        if("1".equals(level)){//省局
            cusNumber = "";
        }
        return screenPlanWindowCameraMapper.listAllForSxNew(cusNumber,areaId,screenPlanId,windowId);
    }

    @Override
    public List<ScreenPlanWindowCameraEntity> selectListByScreenPlanWindowCameraEntity(ScreenPlanWindowCameraEntity screenPlanWindowCameraEntity) {
        return screenPlanWindowCameraMapper.selectByEntity(screenPlanWindowCameraEntity);
    }

    @Override
    public AjaxResult saveNew(Object xhfzMap) {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
	    try {
              if (xhfzMap != null) {

                  Map<String, String> map = (Map<String, String>) xhfzMap;
                  if(!map.isEmpty()){
                      List<ScreenPlanWindowCameraEntity>  list = new ArrayList<>();
                      ScreenPlanWindowCameraEntity sc = new ScreenPlanWindowCameraEntity();
                      sc.setCusNumber(user.getCusNumber());
                      sc.setCameraId(user.getUserId());
                      sc.setCreatName(user.getUserName());
                      sc.setUpdateId(user.getUserId());
                      sc.setUpdateName(user.getUserName());
                      sc.setCreatTime(Util.getCurrentDateTime());
                      sc.setUpdateTime(Util.getCurrentDateTime());
                      if(map.get("screenPlanId")!=null){
                          sc.setScreenPlanId(map.get("screenPlanId"));
                          ScreenPlanNewEntity screenPlanNewEntity = screenPlanNewMapper.selectOne(map.get("screenPlanId"));
                          if (StringUtils.isNotBlank(screenPlanNewEntity.getTywallId())) {
                              sc.setTvwallId(screenPlanNewEntity.getTywallId());
                          }
                      }
                      if (map.get("windowId")!= null) {
                          sc.setWindowId(map.get("windowId"));
                      }
                      if (StringUtils.isNotBlank(sc.getScreenPlanId()) && StringUtils.isNotBlank(sc.getTvwallId()) && StringUtils.isNotBlank(sc.getWindowId())) {
                          screenPlanWindowCameraMapper.deleteByscreenPlanIdAndWindowId(sc.getTvwallId(),sc.getScreenPlanId(),sc.getWindowId());
                      }
                      List<Map<String, String>> screenCameras = (List<Map<String, String>>) JSON.parse(map.get("screenCamera"));
                      if(screenCameras.size() >0){
                          for(Map<String, String> screenCamera:screenCameras){
                              ScreenPlanWindowCameraEntity spwc = new ScreenPlanWindowCameraEntity();
                              BeanUtils.copyProperties(sc,spwc);
                              spwc.setCameraId(screenCamera.get("cameraId"));
                              spwc.setCameraName(screenCamera.get("cameraName"));
                              spwc.setCameraPlatformIdnty(screenCamera.get("cameraPlatformIdnty"));
                              screenPlanWindowCameraMapper.insert(spwc);
                          }
                      }else{
                          screenPlanWindowCameraMapper.insert(sc);
                      }
                  }
              }
          }catch (Exception e){
	        e.printStackTrace();
              return AjaxResult.error("保存失败");
          }
        return AjaxResult.success();
    }

    @Override
    public void screenPlanQh(String id, String tywallId,String cusNumber) {
        // 声明消息体
        MsgBody msgBody = this.convertToStartScreenAudioMsgBody(id,tywallId);
        // 发送切换指令
        String msgType = ScreenConstants.SCREEN_SWITCH;
        //生产消息序列
        String uuId = IdUtil.simpleUUID();
        String msgId = MsgIdUtil.getMsgSeq(uuId);
        //封装消息头
        String sendMsg = this.createMessage(cusNumber, msgType, msgBody,msgId);
        mqMessageSender.sendScreenMessage(sendMsg,cusNumber,msgId);
        //更新状态
        ScreenPlanNewEntity screenPlanNewEntity = new ScreenPlanNewEntity();
        screenPlanNewEntity.setStatus("1");
        screenPlanNewEntity.setId(id);
        screenPlanNewMapper.updateById(screenPlanNewEntity);
        //把其他预案状态更新为0
        ScreenPlanNewEntity sp= new ScreenPlanNewEntity();
        sp.setCusNumber(cusNumber);
        sp.setStatus("0");
        sp.setId(id);
        screenPlanNewMapper.updateInfo(sp);
    }

    /**
     *上墙
     * @param id 电视墙预案id
     * @param tywallId 电视墙id
     * @param cusNumber
     */
    @Override
    public void screenPlanSq(String id, String tywallId, String cusNumber) {
        // 声明消息体
        MsgBody msgBody = this.convertToStartScreenSqMsgBody(id,tywallId,cusNumber);
        // 发送上墙指令
        String msgType = ScreenConstants.SCREEN_SQ;
        //生产消息序列
        String uuId = IdUtil.simpleUUID();
        String msgId = MsgIdUtil.getMsgSeq(uuId);
        //封装消息头
        String sendMsg = this.createMessage(cusNumber, msgType, msgBody,msgId);
        mqMessageSender.sendScreenMessage(sendMsg,cusNumber,msgId);


    }

    private StartScreenSqMsgBody convertToStartScreenSqMsgBody(String id, String tywallId, String cusNumber){
        StartScreenSqMsgBody startScreenSqMsgBody = new StartScreenSqMsgBody();
        startScreenSqMsgBody.setnTvWallId(tywallId);
        List<WindowMsg> windowMsgs = new ArrayList<>();
        ScreenPlanWindowNewEntity screenPlanWindowNewEntity = new ScreenPlanWindowNewEntity();
        screenPlanWindowNewEntity.setCusNumber(cusNumber);
        screenPlanWindowNewEntity.setScreenPlanId(id);
        screenPlanWindowNewEntity.setTvwallId(tywallId);
        //查询窗口id
        List<ScreenPlanWindowNewEntity> screenPlanWindowNewEntities = screenPlanWindowNewMapper.selectByEntity(screenPlanWindowNewEntity);
        if (screenPlanWindowNewEntities.size() > 0) {
            for(ScreenPlanWindowNewEntity sc:screenPlanWindowNewEntities){
                WindowMsg windowMsg = new WindowMsg();
                windowMsg.setnWndId(sc.getWindowId());
                ScreenPlanWindowCameraEntity spwc = new ScreenPlanWindowCameraEntity();
                spwc.setCusNumber(cusNumber);
                spwc.setTvwallId(tywallId);
                spwc.setScreenPlanId(id);
                spwc.setWindowId(sc.getWindowId());
                //查询每个窗口关联的摄像头id
                List<String> cameraList = new ArrayList<>();
                List<ScreenPlanWindowCameraEntity> screenPlanWindowCameraEntities = screenPlanWindowCameraMapper.selectByEntity(spwc);
                if (screenPlanWindowCameraEntities.size() > 0) {
                    for(ScreenPlanWindowCameraEntity scpc:screenPlanWindowCameraEntities){
                        cameraList.add(scpc.getCameraPlatformIdnty());//添加摄像头的平台索引id
                    }
                    windowMsg.setCameraList(cameraList);
                }else{
                    windowMsg.setCameraList(new ArrayList<String>());
                }
                windowMsgs.add(windowMsg);
            }
        }
        startScreenSqMsgBody.setWindowMsgs(windowMsgs);

        return startScreenSqMsgBody;
    }



    private StartScreenAudioMsgBody convertToStartScreenAudioMsgBody(String id,String tywallId) {
        String[]  planIds= id.split("_");
        StartScreenAudioMsgBody startScreenAudioMsgBody = new StartScreenAudioMsgBody();
        startScreenAudioMsgBody.setScreenIndc(planIds[1]);
        startScreenAudioMsgBody.setTVWallId(tywallId);
        startScreenAudioMsgBody.setTaskId(IdUtil.simpleUUID());
        return startScreenAudioMsgBody;
    }


    private String createMessage(String cusNumber,String msgType,MsgBody msgBody, String msgId){
        // 消息头
       MsgHeader msgHeader = new MsgHeader();
        msgHeader.setCusNumber(cusNumber);// 新增的消息头
        msgHeader.setMsgID(msgId);
        msgHeader.setMsgType(msgType);
        msgHeader.setLength(0);
        msgHeader.setSender("SERVER");
        msgHeader.setRecevier("screenPlan");
        msgHeader.setSendTime(Util.toStr(Util.DF_TIME));

        JsonObject out = new JsonObject();
        out.add("header", gson.fromJson(gson.toJson(msgHeader), JsonObject.class));
        out.add("body", gson.fromJson(gson.toJson(msgBody), JsonObject.class));

        return gson.toJson(out);
    }

}
