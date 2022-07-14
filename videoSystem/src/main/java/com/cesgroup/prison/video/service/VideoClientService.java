package com.cesgroup.prison.video.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.cds.service.syslog.ISysLogService;
import com.cesgroup.cds.service.videoclient.IVideoClientService;
import com.cesgroup.framework.util.ConfigUtil;
import com.cesgroup.framework.util.Tools;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.cache.CdsFtpServerConfig;
import com.cesgroup.prison.common.cache.ConfigExtend;
import com.cesgroup.prison.common.cache.DvcCameraBaseDtls;
import com.cesgroup.prison.common.cache.DvcControlerServer;
import com.cesgroup.prison.common.cache.DvcStreamServerInfo;
import com.cesgroup.prison.common.cache.DvcVideoClientConfig;
import com.cesgroup.prison.common.cache.DvcVideoDeviceInfo;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.prison.webservice.service.IVideoClient;
import com.cesgroup.prison.webservice.service.impl.VideoClientImpl;
import com.cesgroup.scrap.db.QueryProcess;


@Service
@Scope("prototype")
public class VideoClientService implements IVideoClientService {
	/** 根据证据信息表的视频名称获取摄像机的设备类型(用于录像播放时获取录像的设备类型) */
	public static final String CDS_QUERY_DVCTYPE_BY_EIN_FILE_NAME = "cds_query_dvctype_by_ein_file_name_new";
	// 日志打印对象
	private static final Logger log = LoggerFactory.getLogger(VideoClientService.class);
	// 视频客户端IP
	private String clientIP = "";
	// 视频客户端配置
	private Map<String, Object> clientMap = null;
	// 登录用户的sessionBean
	private UserBean userBean = null;
	// 视频客户端对象
	private IVideoClient videoClient = null;
	//主机号码
	private static final  int[]  LOCAL_CODE = {154000,161000,162000,163000,164000,165000,
										166000,167000,168000,169000,171000,172000,
										173000,174000,175000,176000,177000,178000,
										181000,182000,183000,184000,185000,186000, 
										187000,188000,179000,189000};
										
	@Autowired
	private QueryProcess queryProcess;
	
	@Override
	public void setUserBean(UserBean userBean){
		this.userBean = userBean;
	}

	@Override
	public Map<String, Object> getClientMap() {
		return clientMap;
	}

	@Override
	public boolean connection(String reqIP){
		String ctrlServerAddr = null;	// 云台控制服务IP
		String orgCode = null;			// 机构号
		Object imageSavePath = null;		// 图片保存地址
		Object videoSavePath = null; 	// 视频保存地址

		try{
			orgCode = userBean.getOrgCode();
			clientMap = RedisCache.getHashMap(DvcVideoClientConfig.tableName, reqIP);
			ctrlServerAddr = Tools.toStr(RedisCache.getObject(DvcControlerServer.tableName, new String[]{orgCode, "1"}));

			// 默认参数
			if (clientMap == null){
				clientMap = new LinkedHashMap<String, Object>();
				clientMap.put(DvcVideoClientConfig.VCC_CLIENT_IP, reqIP);
				clientMap.put(DvcVideoClientConfig.VCC_WIDTH, 800);
				clientMap.put(DvcVideoClientConfig.VCC_HEIGHT, 600);
				clientMap.put(DvcVideoClientConfig.VCC_X_CRDNT, 0);
				clientMap.put(DvcVideoClientConfig.VCC_Y_CRDNT, 0);
				clientMap.put(DvcVideoClientConfig.VCC_IMG_PATH, "");
				clientMap.put(DvcVideoClientConfig.VCC_VIDEO_PATH, "");
			}

			imageSavePath = clientMap.get(DvcVideoClientConfig.VCC_IMG_PATH);
			videoSavePath = clientMap.get(DvcVideoClientConfig.VCC_VIDEO_PATH);

			if (imageSavePath == null || imageSavePath.equals("")){
				imageSavePath = ConfigUtil.get("SNAP_DEFAULT_SAVE_PATH");
				clientMap.put(DvcVideoClientConfig.VCC_IMG_PATH, imageSavePath);
			}

			if (videoSavePath == null || videoSavePath.equals("")){
				videoSavePath = ConfigUtil.get("RECORD_DEFAULT_SAVE_PATH");
				clientMap.put(DvcVideoClientConfig.VCC_VIDEO_PATH, videoSavePath);
			}

			clientIP = (String)clientMap.get(DvcVideoClientConfig.VCC_CLIENT_IP);
			videoClient = new VideoClientImpl(clientIP);

			if (Tools.notEmpty(ctrlServerAddr)){
				videoClient.setCtrlIp(ctrlServerAddr, true);
				log.info("设置[" + orgCode + "]云台控制服务地址：" + ctrlServerAddr);
			}
			return true;
		} catch (Exception ex){
			log.error("客户端[" + reqIP + "]请求连接[" + clientIP + "]视频客户端... 异常, " + ex.getMessage());
			return false;
		}
	}

	@Override
	public void setLayout(int layout, int last) throws Exception {
		int w = toInt( clientMap.get( DvcVideoClientConfig.VCC_WIDTH ) );// 视频客户端宽度
		int h = toInt( clientMap.get( DvcVideoClientConfig.VCC_HEIGHT ) );// 视频客户端高度
		int x = toInt( clientMap.get( DvcVideoClientConfig.VCC_X_CRDNT ) );// 视频客户端的X坐标
		int y = toInt( clientMap.get( DvcVideoClientConfig.VCC_Y_CRDNT ) );// 视频客户端的Y坐标

		for (int i = layout; i < last; i++) {
			//videoClient.closeMult(i);
		}
		videoClient.setLayout(layout, w, h, x, y);
	}

	public boolean setFormStyle(Boolean topMost, Boolean showFormTitle, Boolean showVideoTitle) throws Exception {
		try {
			videoClient.setForm(topMost, showFormTitle, showVideoTitle);
		} catch (Exception e) {
			log.error("设置窗口属性异常：" + e.getMessage());
			return false;
		}
		return true;
	}

	@Override
	public Integer getIndex() throws Exception {
		return videoClient.getIndex();
	}
	@Override
	public String getWinCameraID() throws Exception {
		return videoClient.getWinCameraID();
	}

	@Override
	public byte[] snap(String cameraId, String savePath, String fileName) throws Exception {
		String cameraName = null;
		String saveFile = null;
		byte[] imgBytes = null;

		if (savePath == null || "".equals(savePath)){
			savePath = String.valueOf(clientMap.get(DvcVideoClientConfig.VCC_IMG_PATH));
		}
		saveFile = savePath + "\\" + fileName;
		cameraName = (String)RedisCache.getObject(DvcCameraBaseDtls.tableName, cameraId, DvcCameraBaseDtls.CBD_NAME);

		// 截图后，休眠1000毫秒再操作
		videoClient.snap(this.getIndex(), saveFile);
		Thread.sleep(1000);

		// 记录操作日志
		this.saveUserHandleLog("监控截图，监控名称【" + cameraName + "】，截图名称{" + fileName + "}", ISysLogService.SubType.SNAP);
		imgBytes = videoClient.getImageBytes(saveFile);
		if (imgBytes == null){
			log.warn("截图后获取图片流失败，图片路径：" + saveFile);
		}
		return imgBytes;
	}

	@Override
	public JSONObject snap(JSONObject jsonObj) throws Exception {
		Integer index = null;
		//Integer cameraId = null;
		String cameraId = null; 
		String 	orgCode  = null;
		String 	savePath = null;
		String 	fileName = null;
		String  deviceType = null;
		Map<String, Object> cameraBase = null;

		index	 = Tools.toInt(jsonObj.get("index"), videoClient.getIndex());
		cameraId = Tools.toStr(jsonObj.get("cameraId"));
		orgCode  = Tools.toStr(jsonObj.get("orgCode"));
		savePath = Tools.toStr(jsonObj.get("savePath"), Tools.toStr(clientMap.get(DvcVideoClientConfig.VCC_IMG_PATH)));
		fileName = Tools.toStr(jsonObj.get("fileName"));

		if( cameraId != null ){
			// 查询摄像机基础信息
			cameraBase = RedisCache.getHashMap(DvcCameraBaseDtls.tableName, cameraId);

			if (fileName == null) {
				deviceType = Tools.toStr(getDvcType(cameraId));					// 查询摄像机对应设备信息
				fileName = getFileName(orgCode, cameraId, deviceType, "jpg");	// 命名规则：机构号_摄像机编号_设备类型_年月日_时分秒.jpg
			}

			videoClient.snap(index, joinPath(savePath, fileName));

			jsonObj.put("index", index);
			jsonObj.put("cameraName", cameraBase.get(DvcCameraBaseDtls.CBD_NAME));
			jsonObj.put("savePath", savePath);
			jsonObj.put("fileName", fileName);
		}
		else {
			throw new Exception("摄像机编号为空!");
		}
		return jsonObj;
	}
	/*//update by zk
	@Override
	public void playVideoMult(JSONArray cameraList)throws Exception {
		Map<String, Object> cameraBase = null;
		Map<String, Object> deviceBase = null;
		Integer index = null;
		//Integer cameraId = null;
		String cameraId = null;
		String cameraName = null;
		String[] fileds = null;
		
		JSONObject json = null;
			
		for(int i = 0; i < cameraList.size(); i++){
			json = cameraList.getJSONObject(i);
			index = Tools.toInt( json.get("index") );
			
			//如果index超过了最大布局则不执行 add by zk 
			String maxLayout = ConfigUtil.get(ConfigUtil.VIDEOCLIENT_MAX_LAYOUT);
			int maxIndex = Tools.toInt( maxLayout );
			if(index>=maxIndex) {
				return;
			}
			
			
			//cameraId = Tools.toInt( json.get("cameraId") );
			cameraId =(String)json.get("cameraId");
			fileds = new String[]{
					DvcCameraBaseDtls.CBD_NAME, 
					DvcCameraBaseDtls.CBD_DVR_IDNTY,
					DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY, 
					DvcCameraBaseDtls.CBD_IP_ADDRS
			};
			// 查询摄像机基础信息
			cameraBase = RedisCache.getHashMap(DvcCameraBaseDtls.tableName, cameraId, fileds);
			cameraName = (String)cameraBase.get(DvcCameraBaseDtls.CBD_NAME);
			// 查询摄像机对应设备信息
			deviceBase = RedisCache.getHashMap(DvcVideoDeviceInfo.tableName, cameraBase.get(DvcCameraBaseDtls.CBD_DVR_IDNTY));
			// 打开视频图像
			videoClient.playVideoMult(cameraName,
					toInt(cameraBase.get(DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY)), 
					(String)cameraBase.get(DvcCameraBaseDtls.CBD_IP_ADDRS), 
					(String)deviceBase.get(DvcVideoDeviceInfo.VDI_IP_ADDRS), 
					toInt(deviceBase.get(DvcVideoDeviceInfo.VDI_PORT)),
					(String)deviceBase.get(DvcVideoDeviceInfo.VDI_USER_NAME), 
					(String)deviceBase.get(DvcVideoDeviceInfo.VDI_USER_PASSWORD), 
					toInt(deviceBase.get(DvcVideoDeviceInfo.VDI_BRAND)), 
					index);
			// 记录操作日志
			this.saveUserHandleLog("查看【"+cameraName+"】的视频监控，视频编号["+cameraId+"]", ISysLogService.SubType.OPEN_VIDEO);
		}
	}*/
	
	//区分直连、dvr播放方式，之前只是dvr update by zk 20180326
	@Override
	public void playVideoMult(JSONArray cameraList)throws Exception {
		Map<String, Object> cameraBase = null;
		Map<String, Object> deviceBase = null;
		Integer index = null;
		//Integer cameraId = null;
		String cameraId = null;
		String cameraName = null;
		Object mode = null;					// 播放方式
		String[] fileds = null;
		Object modeDevice = ConfigExtend.getValue("video.play.mode.default");	//设备播放
		JSONObject json = null;
		for(int i = 0; i < cameraList.size(); i++){
			json = cameraList.getJSONObject(i);
			index = Tools.toInt( json.get("index") );
			
			//如果index超过了最大布局则不执行 add by zk 
			String maxLayout = ConfigUtil.get(ConfigUtil.VIDEOCLIENT_MAX_LAYOUT);
			int maxIndex = Tools.toInt( maxLayout );
			if(index>=maxIndex) {
				return;
			}
			cameraId =(String)json.get("cameraId");
			fileds = new String[]{
					DvcCameraBaseDtls.CBD_NAME, 
					DvcCameraBaseDtls.CBD_DVR_IDNTY,
					DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY, 
					DvcCameraBaseDtls.CBD_VIDEO_PLAY_INDC,
					DvcCameraBaseDtls.CBD_IP_ADDRS,
					DvcCameraBaseDtls.CBD_PORT,
					DvcCameraBaseDtls.CBD_USER_NAME,
					DvcCameraBaseDtls.CBD_USER_PASSWORD,
					DvcCameraBaseDtls.CBD_CHNNL_IDNTY,
					DvcCameraBaseDtls.CBD_BRAND_NAME,
					DvcCameraBaseDtls.CBD_PLATFORM_IDNTY,
					DvcCameraBaseDtls.ID
			};
			// 查询摄像机基础信息
			cameraBase = RedisCache.getHashMap(DvcCameraBaseDtls.tableName, cameraId, fileds);
			cameraName = (String)cameraBase.get(DvcCameraBaseDtls.CBD_NAME);
			mode = (String)cameraBase.get(DvcCameraBaseDtls.CBD_VIDEO_PLAY_INDC);
			log.info("mode:"+mode);	
			// 设置默认播放方式
			if (mode == null) mode = (String)modeDevice;
			log.info("mode:"+mode);	
			//设备
			if(mode.equals("2")) {
				// 查询摄像机对应设备信息
				deviceBase = RedisCache.getHashMap(DvcVideoDeviceInfo.tableName, cameraBase.get(DvcCameraBaseDtls.CBD_DVR_IDNTY));
				// 打开视频图像
				videoClient.playVideoMult(cameraName,
						toInt(cameraBase.get(DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY)), 
						(String)cameraBase.get(DvcCameraBaseDtls.CBD_IP_ADDRS), 
						(String)deviceBase.get(DvcVideoDeviceInfo.VDI_IP_ADDRS), 
						toInt(deviceBase.get(DvcVideoDeviceInfo.VDI_PORT)),
						(String)deviceBase.get(DvcVideoDeviceInfo.VDI_USER_NAME), 
						(String)deviceBase.get(DvcVideoDeviceInfo.VDI_USER_PASSWORD), 
						toInt(deviceBase.get(DvcVideoDeviceInfo.VDI_BRAND)), 
						index,
						(String)cameraBase.get(DvcCameraBaseDtls.CBD_PLATFORM_IDNTY));
			}
			//直连
			else if(mode.equals("0")) {
				// 打开视频图像
				videoClient.playVideoMult(cameraName,
						toInt(cameraBase.get(DvcCameraBaseDtls.CBD_CHNNL_IDNTY)), 
						(String)cameraBase.get(DvcCameraBaseDtls.CBD_IP_ADDRS), 
						(String)cameraBase.get(DvcCameraBaseDtls.CBD_IP_ADDRS), 
						toInt(cameraBase.get(DvcCameraBaseDtls.CBD_PORT)),
						(String)cameraBase.get(DvcCameraBaseDtls.CBD_USER_NAME), 
						(String)cameraBase.get(DvcCameraBaseDtls.CBD_USER_PASSWORD), 
						toInt(cameraBase.get(DvcCameraBaseDtls.CBD_BRAND_NAME)), 
						index,
						(String)cameraBase.get(DvcCameraBaseDtls.CBD_PLATFORM_IDNTY));
			}
			// 记录操作日志
			this.saveUserHandleLog("查看【"+cameraName+"】的视频监控，视频编号["+cameraId+"]", ISysLogService.SubType.OPEN_VIDEO);
		}
	}

	@Override
	public JSONArray playVideoStreamMult(JSONArray cameraList) throws Exception {
		//无论流媒体、设备、平台、直连统一走playVideoMult update by zk 20180326 start
		//return playVideo(cameraList);
		playVideoMult(cameraList);
		return null;
		//update by zk 20180326 end
		
//		Map<String, Object> streamInfo = null;// 流媒体信息
//		Map<String, Object> cameraBase = null;
//		Map<String, Object> deviceBase = null;
//		Object streamMediaId = null;// 流媒体设备ID
//		String streamIp = null;		// 流媒体IP
//		Integer streamPort = null;	// 流媒体端口
//		Integer streamType = null;	// 码流类型
//		Integer deviceType = null;	// 设备类型
//		Integer index = null;		// 索引
//		Integer cameraId = null;	// 摄像机ID
//		String cameraName = null;	// 摄像机名称
//		String errorLog = "";		// 错误日志
//
//		JSONObject json = null;
//
//		String[] fileds = new String[]{
//				DvcCameraBaseDtls.CBD_NAME, 
//				DvcCameraBaseDtls.CBD_DVR_IDNTY,
//				DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY, 
//				DvcCameraBaseDtls.CBD_IP_ADDRS
//		};
//
//		for(int i = 0; i < cameraList.size(); i++){
//			json = cameraList.getJSONObject(i);
//			index = Tools.toInt( json.get("index") );
//			cameraId = Tools.toInt( json.get("cameraId") );
//			cameraName = (String)RedisCache.getObject(DvcCameraBaseDtls.tableName, cameraId, DvcCameraBaseDtls.CBD_NAME);
//			deviceType = this.getDvcType(cameraId);
//
//			if (deviceType != null) {
//				streamMediaId = RedisCache.getObject(DvcCameraBaseDtls.tableName, cameraId, DvcCameraBaseDtls.CBD_STREAM_MEDIA_IDNTY);
//				streamType = Tools.toInt(RedisCache.getObject(DvcCameraBaseDtls.tableName, cameraId, DvcCameraBaseDtls.CBD_STREAM_TYPE), 1);
//
//				if (streamMediaId != null) {
//					// 查询流媒体服务IP端口
//					streamInfo = RedisCache.getHashMap(DvcStreamServerInfo.tableName, streamMediaId, new String[]{
//							DvcStreamServerInfo.SSI_IP_ADDRS, 
//							DvcStreamServerInfo.SSI_PORT
//					});
//
//					if (streamInfo != null){
//						streamIp = (String)streamInfo.get(DvcStreamServerInfo.SSI_IP_ADDRS);
//						streamPort = toInt(streamInfo.get(DvcStreamServerInfo.SSI_PORT));
//
//						// 调用视频客户端先关闭该窗口的当前视频画面并休眠100毫秒缓冲
//						videoClient.closeMult(index);
//						Thread.sleep(100);
//
//						// 调用视频客户端打开视频:streamType 0主码流、1辅码流
//						videoClient.playVideoStreamMult(cameraId, deviceType, streamIp, streamPort, index, streamType);
//					} else {
//						errorLog = "数据异常的摄像机ID：" + cameraId + ", 获取流媒体设备信息错误";
//					}
//				} else {
//					// 没有配置流媒体的走直连
//					cameraBase = RedisCache.getHashMap(DvcCameraBaseDtls.tableName, cameraId, fileds);
//					deviceBase = RedisCache.getHashMap(DvcVideoDeviceInfo.tableName, cameraBase.get(DvcCameraBaseDtls.CBD_DVR_IDNTY));
//
//					// 打开视频图像
//					videoClient.playVideoMult(cameraName,
//							toInt(cameraBase.get(DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY)), 
//							(String)cameraBase.get(DvcCameraBaseDtls.CBD_IP_ADDRS), 
//							(String)deviceBase.get(DvcVideoDeviceInfo.VDI_IP_ADDRS), 
//							toInt(deviceBase.get(DvcVideoDeviceInfo.VDI_PORT)),
//							(String)deviceBase.get(DvcVideoDeviceInfo.VDI_USER_NAME), 
//							(String)deviceBase.get(DvcVideoDeviceInfo.VDI_USER_PASSWORD), 
//							toInt(deviceBase.get(DvcVideoDeviceInfo.VDI_BRAND)), 
//							index);
//					// errorLog = "数据异常的摄像机ID：" + cameraId + ", 未关联流媒体设备";
//
//					/* 新接口的参数JSON格式
//					 	{
//						 	index: "窗口索引",
//						 	cameraId: "摄像机编号",
//						 	cameraName: "摄像机名称",
//						 	brand: "设备品牌",
//							dvr:{
//								userName: "用户名",
//								password: "密码",
//								ip: "设备IP",
//								port: "设备端口",
//								channel: "设备通道"
//							},
//							stream:{
//								ip: "流媒体IP",
//								port: "流媒体端口",
//								type: "流类型（0主码流、1辅码流）"
//							},
//							platform:{
//								userName: "用户名",
//								password: "密码",
//								ip: "平台IP",
//								port: "平台端口",
//								id: "平台设备的唯一编号"
//							}
//						}
//					*/
//				}
//			} else {
//				errorLog = "数据异常的摄像机ID：" + cameraId + ", 获取设备类型错误";
//			}
//
//			// 记录操作日志
//			this.saveUserHandleLog("查看【"+cameraName+"】的视频监控，视频编号["+cameraId+"]", ISysLogService.SubType.OPEN_VIDEO);
//
//			if( errorLog.length() > 0)
//				log.error(errorLog);
//
//			json.put("cameraName", cameraName);
//			json.put("msg", errorLog);
//		}
//		return cameraList;
	}


	private JSONArray playVideo (JSONArray cameraArray) throws Exception {
		Map<String, Object> cameraMap = null;		// 摄像机信息
		Map<String, Object> dvrMap = null;			// 设备信息
		Map<String, Object> streamMap = null;		// 流媒体信息
		Map<String, Object> platformMap = null;		// 平台信息

		Integer index = null;				// 窗口索引
		Object cameraId = null;				// 摄像机编号
		Object cameraName = null;			// 摄像机名称
		Object mode = null;					// 播放方式
		Object dvrId = null;				// 设备DVR编号
		Object dvrChannel = null;			// 设备通道
		Object streamId = null;				// 流媒体编号
		Object platformId = null;			// 平台编号
		Object platformCameraId = null;		// 平台设备的唯一编号
		Object modeDefault = ConfigExtend.getValue("video.play.mode.default");
		
		JSONObject cameraJSON = null;		// 请求打开的摄像机信息
		String remark = "";				// 打开结果

		for(int i = 0, I = cameraArray.size(); i < I; i++) {
			cameraJSON = cameraArray.getJSONObject(i);
			cameraId = cameraJSON.get("cameraId");
			index = Tools.toInt(cameraJSON.get("index"));

			// 获取缓存的摄像机信息
			cameraMap = RedisCache.getHashMap(DvcCameraBaseDtls.tableName, cameraId);
			if(cameraMap != null){
				cameraName = cameraMap.get(DvcCameraBaseDtls.CBD_NAME);
				mode = cameraMap.get(DvcCameraBaseDtls.CBD_VIDEO_PLAY_INDC);
				dvrId = cameraMap.get(DvcCameraBaseDtls.CBD_DVR_IDNTY);
				dvrChannel = cameraMap.get(DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY);
				streamId = cameraMap.get(DvcCameraBaseDtls.CBD_STREAM_MEDIA_IDNTY);
				platformId = cameraMap.get(DvcCameraBaseDtls.CBD_PLATFORM);
				platformCameraId = cameraMap.get(DvcCameraBaseDtls.CBD_PLATFORM_IDNTY);
			}

			JSONObject paramsJSON = new JSONObject();		// 录像回放参数信息
			JSONObject dvrJSON = new JSONObject();			// 设备信息
			JSONObject streamJSON = new JSONObject();		// 流媒体信息
			JSONObject platformJSON = new JSONObject();		// 平台信息

			// 设置默认播放方式
			if (mode == null) mode = modeDefault;


			// 获取设备信息
			if (dvrId != null) {
				dvrMap = RedisCache.getHashMap(DvcVideoDeviceInfo.tableName, dvrId);
				if (dvrMap != null) {
					dvrJSON.put("userName", dvrMap.get(DvcVideoDeviceInfo.VDI_USER_NAME));			// 用户名
					dvrJSON.put("password", dvrMap.get(DvcVideoDeviceInfo.VDI_USER_PASSWORD));		// 密码
					dvrJSON.put("ip", dvrMap.get(DvcVideoDeviceInfo.VDI_IP_ADDRS));					// 设备IP
					dvrJSON.put("port", dvrMap.get(DvcVideoDeviceInfo.VDI_PORT));					// 设备端口
					dvrJSON.put("channel", dvrChannel);												// 设备通道
					dvrJSON.put("brand", dvrMap.get(DvcVideoDeviceInfo.VDI_BRAND));					// 设备品牌
				} else {
					remark += "设备所在DVR信息为空; ";
				}
			} else {
				remark += "未配置设备所在DVR; ";
			}

			// 流媒体信息
			if (streamId != null) {
				streamMap = RedisCache.getHashMap(DvcStreamServerInfo.tableName, streamId);
				if (streamMap != null) {
					streamJSON.put("ip", streamMap.get(DvcStreamServerInfo.SSI_IP_ADDRS));			// 流媒体IP
					streamJSON.put("port", streamMap.get(DvcStreamServerInfo.SSI_PORT));			// 流媒体端口
					if(cameraMap != null)
						streamJSON.put("type", cameraMap.get(DvcCameraBaseDtls.CBD_STREAM_TYPE));		// 流类型
				} else {
					remark += "设备所在流媒体信息为空; ";
				}
			} else {
				remark += "未配置设备所在流媒体; ";
			}

			// 获取设备平台信息
			if (platformId != null) {
				platformMap = RedisCache.getHashMap(DvcVideoDeviceInfo.tableName, platformId);
				if (platformMap != null) {
					platformJSON.put("userName", platformMap.get(DvcVideoDeviceInfo.VDI_USER_NAME));		// 用户名
					platformJSON.put("password", platformMap.get(DvcVideoDeviceInfo.VDI_USER_PASSWORD));	// 密码
					platformJSON.put("ip", platformMap.get(DvcVideoDeviceInfo.VDI_IP_ADDRS));				// 平台IP
					platformJSON.put("port", platformMap.get(DvcVideoDeviceInfo.VDI_PORT));					// 平台端口
					platformJSON.put("id", platformCameraId);												// 平台设备的唯一编号
					platformJSON.put("brand", platformMap.get(DvcVideoDeviceInfo.VDI_BRAND));				// 设备品牌
				} else {
					remark += "设备所在平台信息为空; ";
				}
			} else {
				remark += "未配置设备所在平台; ";
			}

			/* 新接口的参数JSON格式
			 	{
				 	index: "窗口索引",
				 	cameraId: "摄像机编号",
				 	cameraName: "摄像机名称",
				 	mode: "选择播放方式：1.平台、2.设备",
					dvr:{
						userName: "用户名",
						password: "密码",
						ip: "设备IP",
						port: "设备端口",
						channel: "设备通道",
						brand: "设备品牌"
					},
					stream:{
						ip: "流媒体IP",
						port: "流媒体端口",
						type: "流类型（0主码流、1辅码流）"
					},
					platform:{
						userName: "用户名",
						password: "密码",
						ip: "平台IP",
						port: "平台端口",
						id: "平台设备的唯一编号"
						brand: "平台品牌"
					}
				}
			*/
			paramsJSON.put("index", index);					// 窗口索引
			paramsJSON.put("cameraId", cameraId);			// 摄像机编号
			paramsJSON.put("cameraName", cameraName);		// 摄像机名称
			paramsJSON.put("mode", mode);					// 播放方式
			paramsJSON.put("dvr", dvrJSON);					// 设备信息
			paramsJSON.put("stream", streamJSON);			// 流媒体信息
			paramsJSON.put("platform", platformJSON);		// 平台信息

			// 调用视频客户端打开视频
			videoClient.videoCtrl("PlayVideo_All_Mult", paramsJSON.toJSONString());

			cameraJSON.put("cameraName", cameraName);
			cameraJSON.put("result", "播放发送成功");
			cameraJSON.put("remark", remark);
//			cameraJSON.put("msg", respMsg);
			remark = "";
		}
		return cameraArray;
	}

	@Override
	public JSONObject playback_Mult(String cameraId, String startTime, String endTime) throws Exception {
		return this.playback_Mult(this.getIndex(), cameraId, startTime, endTime);
	}

	@Override
	public JSONObject playback_Mult(Integer index, String cameraId, String startTime, String endTime) throws Exception {
		return playbackMult(index, cameraId, startTime, endTime);
//		Map<String, Object> cameraBase = null;
//		Map<String, Object> deviceBase = null;
//		Integer channel = 0;// 通道号
//		Integer brand = null;// 设备类型：8-海康、20-EDNNS
//		Boolean result = false;// 回放结果
//		String cameraName = null;// 摄像机名称
//		JSONObject retJSON = new JSONObject();
//
//		// 查询摄像机基础信息
//		cameraBase = RedisCache.getHashMap(DvcCameraBaseDtls.tableName, cameraId, new String[]{
//				DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY, 
//				DvcCameraBaseDtls.CBD_DVR_IDNTY,
//				DvcCameraBaseDtls.CBD_IP_ADDRS,
//				DvcCameraBaseDtls.CBD_NAME
//		});
//		cameraName = (String)cameraBase.get(DvcCameraBaseDtls.CBD_NAME);
//		deviceBase = RedisCache.getHashMap(DvcVideoDeviceInfo.tableName, cameraBase.get(DvcCameraBaseDtls.CBD_DVR_IDNTY));
//		channel = toInt(cameraBase.get(DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY));
//
//		// 先关闭窗口画面
//		videoClient.closeMult(index);
//		Thread.sleep(100);
//
//		// 获取设备类型（品牌）
//		brand = toInt( deviceBase.get(DvcVideoDeviceInfo.VDI_BRAND) );
//
//		// 录像回放
//		result = videoClient.playBackMult(index, startTime, endTime, 
//				(channel == null ? 0 : channel), 
//				(String)cameraBase.get(DvcCameraBaseDtls.CBD_IP_ADDRS),
//				(String)deviceBase.get(DvcVideoDeviceInfo.VDI_IP_ADDRS), 
//				toInt(deviceBase.get(DvcVideoDeviceInfo.VDI_PORT)), 
//				(String)deviceBase.get(DvcVideoDeviceInfo.VDI_USER_NAME), 
//				(String)deviceBase.get(DvcVideoDeviceInfo.VDI_USER_PASSWORD), 
//				brand);
//
//		retJSON.put("result", result);
//		retJSON.put("devType", brand);
//		retJSON.put("cameraName", cameraName);
//
//		playbackMult(index, cameraId, startTime, endTime);
//
//		// 操作日志
//		this.saveUserHandleLog("回放【" + cameraName + "】视频监控，视频编号["+cameraId+"], 回放" + (result ? "成功!":"失败!"),
//				ISysLogService.SubType.PLAY_BACK);
//		return retJSON;
	}


	/**
	 * 视频回放
	 * @param index			回放的窗口索引
	 * @param cameraId		回放的摄像机编号
	 * @param startTime		回放的开始时间
	 * @param endTime		回放的结束时间
	 * @throws Exception
	 */
	private JSONObject playbackMult (Integer index, String cameraId, String startTime, String endTime) throws Exception {
		JSONObject retJSON = new JSONObject();		// 返回结果
		Map<String, Object> cameraMap = null;		// 摄像机信息
		Map<String, Object> dvrMap = null;			// 设备信息
		Map<String, Object> platformMap = null;		// 平台信息

		Object cameraName = null;			// 摄像机名称
		Object mode = null;					// 回放选择方式
		Object brand = null;				// 品牌
		Object dvrId = null;				// 设备信息编号
		Object dvrChannel = null;			// 设备通道
		Object platformId = null;			// 平台编号
		Object platformCameraId = null;		// 平台设备的唯一编号
		Object modeDefault = ConfigExtend.getValue("video.playback.mode.default");	// 默认选择回放方式

		JSONObject paramsJSON = new JSONObject();		// 录像回放参数信息
		JSONObject dvrJSON = new JSONObject();			// 设备信息
		JSONObject platformJSON = new JSONObject();		// 平台信息
		String remark = "";

		// 获取缓存的摄像机信息
		cameraMap = RedisCache.getHashMap(DvcCameraBaseDtls.tableName, cameraId);
		cameraName = cameraMap.get(DvcCameraBaseDtls.CBD_NAME);
		mode = cameraMap.get(DvcCameraBaseDtls.CBD_VIDEO_PLAYBACK_INDC);
		dvrId = cameraMap.get(DvcCameraBaseDtls.CBD_DVR_IDNTY);
		dvrChannel = cameraMap.get(DvcCameraBaseDtls.CBD_DVR_CHNNL_IDNTY);
		platformId = cameraMap.get(DvcCameraBaseDtls.CBD_PLATFORM);
		platformCameraId = cameraMap.get(DvcCameraBaseDtls.CBD_PLATFORM_IDNTY);
		// 缺省参数，回放选择方式默认：平台
		if (mode == null) mode = modeDefault;

		// 获取设备信息
		if (dvrId != null) {
			dvrMap = RedisCache.getHashMap(DvcVideoDeviceInfo.tableName, dvrId);
			if (dvrMap != null) {
				dvrJSON.put("userName", dvrMap.get(DvcVideoDeviceInfo.VDI_USER_NAME));			// 用户名
				dvrJSON.put("password", dvrMap.get(DvcVideoDeviceInfo.VDI_USER_PASSWORD));		// 密码
				dvrJSON.put("ip", dvrMap.get(DvcVideoDeviceInfo.VDI_IP_ADDRS));					// 设备IP
				dvrJSON.put("port", dvrMap.get(DvcVideoDeviceInfo.VDI_PORT));					// 设备端口
				dvrJSON.put("channel", dvrChannel);												// 设备通道
				dvrJSON.put("brand", dvrMap.get(DvcVideoDeviceInfo.VDI_BRAND));					// 设备品牌
			} else {
				remark += "设备所在DVR信息为空; ";
			}
		} else {
			remark += "未配置设备所在DVR; ";
		}

		// 获取设备平台信息
		if (platformId != null) {
			platformMap = RedisCache.getHashMap(DvcVideoDeviceInfo.tableName, platformId);
			if (platformMap != null) {
				platformJSON.put("userName", platformMap.get(DvcVideoDeviceInfo.VDI_USER_NAME));		// 用户名
				platformJSON.put("password", platformMap.get(DvcVideoDeviceInfo.VDI_USER_PASSWORD));	// 密码
				platformJSON.put("ip", platformMap.get(DvcVideoDeviceInfo.VDI_IP_ADDRS));				// 平台IP
				platformJSON.put("port", platformMap.get(DvcVideoDeviceInfo.VDI_PORT));					// 平台端口
				platformJSON.put("id", platformCameraId);												// 平台设备的唯一编号
				platformJSON.put("brand", platformMap.get(DvcVideoDeviceInfo.VDI_BRAND));				// 设备品牌
			} else {
				remark += "设备所在平台信息为空; ";
			}
		} else {
			remark += "未配置设备所在平台; ";
		}


		/* 录像回放参数JSON格式
		 	{
			 	index: "窗口索引",
			 	startTime: "开始时间",
			 	endTime: "结束时间",
			 	mode: "选择回放方式：1.平台、2.设备",
				dvr:{
					userName: "用户名",
					password: "密码",
					ip: "设备IP",
					port: "设备端口",
					channel: "设备通道",
					brand: "设备品牌"
				},
				platform:{
					userName: "用户名",
					password: "密码",
					ip: "平台IP",
					port: "平台端口",
					id: "平台设备的唯一编号",
					brand: "平台品牌"
				},
				deviceID : "摄像头id"  //add by zk 2018-06-19
			}
		 */
		paramsJSON.put("title", cameraName); //摄像机名称
		paramsJSON.put("index", index);	// 窗口索引
		paramsJSON.put("startTime", startTime);			// 开始时间
		paramsJSON.put("endTime", endTime);				// 结束时间
		paramsJSON.put("mode", mode);					// 回放选择方式：1.平台、2.设备
		paramsJSON.put("dvr", dvrJSON);					// 设备信息
		paramsJSON.put("platform", platformJSON);		// 平台信息
		paramsJSON.put("deviceID", platformCameraId);	// 摄像头在平台的索引IDid add by zk 2018-06-19
		//log.debug(paramsJSON.toJSONString());
		// 请求视频客户端回放视频
		videoClient.videoCtrl("PlayBack_All_Mutl", paramsJSON.toJSONString());

		// 操作日志
		this.saveUserHandleLog("回放【" + cameraName + "】视频监控，视频编号["+cameraId+"], 回放发送成功!", ISysLogService.SubType.PLAY_BACK);

		// 返回结果
		retJSON.put("devType", brand);
		retJSON.put("cameraName", cameraName);
		retJSON.put("result", true);
		retJSON.put("remark", remark);
		remark = "";
		return retJSON;
	}





	@Override
	public Boolean stopPlayback_Mult(Integer location) throws Exception {
		return videoClient.stopPlayBackMult(location);
	}

	@Override
	public void startRecord(String cameraId, String file, String path) throws Exception {
		
		Map<String, Object> cameraBase = null;
		String cameraName = null;// 摄像机名称
		String deviceType = null;
		
		if (path == null || "".equals(path)) {
			path = String.valueOf(clientMap.get(DvcVideoClientConfig.VCC_VIDEO_PATH));
		}
		cameraBase = RedisCache.getHashMap(DvcCameraBaseDtls.tableName, cameraId, new String[]{
				DvcCameraBaseDtls.CBD_NAME,
				DvcCameraBaseDtls.CBD_DVR_IDNTY
		});
		// 获取摄像机名称
		cameraName = Tools.toStr(cameraBase.get(DvcCameraBaseDtls.CBD_NAME), "");

		// 查询摄像机对应设备信息
		deviceType = Tools.toStr( RedisCache.getObject(
				DvcVideoDeviceInfo.tableName, 
				cameraBase.get( DvcCameraBaseDtls.CBD_DVR_IDNTY ), 
				DvcVideoDeviceInfo.VDI_BRAND) );

		// 8-海康:mp4后缀、20-EDNNS：edh后缀
		if (deviceType.equals("20")) {
			file = file.replace(".mp4", ".edh");
		}

		// 开始录像
		videoClient.startRecord(this.getIndex(), path + "\\" + file);

		// 记录操作日志
		this.saveUserHandleLog("监控录像，监控名称【" + cameraName + "】，录像名称{" + file + "}",
				ISysLogService.SubType.RECORD);
	}

	@Override
	public JSONObject recordVideo(JSONObject jsonObj) throws Exception {
		return recordVideoByBrand2(jsonObj);
//		Integer index = null;
//		Integer cameraId = null; 
//		String 	orgCode  = null;
//		String 	savePath = null;
//		String 	fileName = null;
//		String  file = null;
//		String  deviceType = null;
//		Map<String, Object> cameraBase = null;
//
//		index	 = jsonObj.getInteger("index");
//		orgCode  = jsonObj.getString("orgCode");
//		cameraId = jsonObj.getInteger("cameraId");
//		savePath = jsonObj.getString("savePath");
//		fileName = jsonObj.getString("fileName");
//
//		if( cameraId != null ){
//			// 查询摄像机基础信息
//			cameraBase = RedisCache.getHashMap(DvcCameraBaseDtls.tableName, cameraId, new String[]{
//					DvcCameraBaseDtls.CBD_NAME,
//					DvcCameraBaseDtls.CBD_DVR_IDNTY
//			});
//
//			if( savePath == null ){
//				savePath = Tools.toStr( clientMap.get(DvcVideoClientConfig.VCC_VIDEO_PATH) );
//			}
//
//			if( fileName == null ){
//				// 查询摄像机对应设备信息
//				deviceType = Tools.toStr( RedisCache.getObject(
//						DvcVideoDeviceInfo.tableName, 
//						cameraBase.get( DvcCameraBaseDtls.CBD_DVR_IDNTY ), 
//						DvcVideoDeviceInfo.VDI_BRAND) );
//				// 命名规则：机构号_摄像机编号_设备类型_年月日_时分秒.mp4
//				fileName = getFileName(orgCode, cameraId, deviceType, deviceType.equals("8") ? "mp4" : "edh");
//			}
//
//			if( index == null ){
//				index = videoClient.getIndex();
//			}
//
//			file = joinPath(savePath, fileName);
//
//			videoClient.startRecord(index, file);
//
//			jsonObj.put("cameraName", cameraBase.get(DvcCameraBaseDtls.CBD_NAME));
//			jsonObj.put("savePath", savePath);
//			jsonObj.put("fileName", fileName);
//		}
//		else {
//			throw new Exception("摄像机编号为空!");
//		}
//		return jsonObj;
	}

	public JSONObject recordVideoByBrand (JSONObject jsonObj) throws Exception {
		Integer index = Tools.toInt(jsonObj.get("index"), videoClient.getIndex());					// 窗口索引
		//Integer cameraId = Tools.toInt(jsonObj.get("cameraId"));									// 摄像机编号
		String cameraId = Tools.toStr(jsonObj.get("cameraId"));									// 摄像机编号
		String orgCode = Tools.toStr(jsonObj.get("orgCode"));										// 机构号
		String savePathDefault = Tools.toStr(clientMap.get(DvcVideoClientConfig.VCC_VIDEO_PATH));	// 默认保存路径
		String savePath = Tools.toStr(jsonObj.get("savePath"), savePathDefault);					// 保存路径
		String fileName = Tools.toStr(jsonObj.get("fileName"));										// 保存文件名

		Map<String, Object> cameraMap = null;	// 缓存的摄像机基础信息
		Object brand = null;	// 摄像机品牌
		Object dvrId = null;	// 摄像机设备编号

		if (cameraId != null) {
			// 查询摄像机基础信息
			cameraMap = RedisCache.getHashMap(DvcCameraBaseDtls.tableName, cameraId);
			dvrId = cameraMap.get(DvcCameraBaseDtls.CBD_DVR_IDNTY);
			brand = RedisCache.getObject(DvcVideoDeviceInfo.tableName, dvrId, DvcVideoDeviceInfo.VDI_BRAND);

			if (savePath == null) {
				savePath = Tools.toStr(clientMap.get(DvcVideoClientConfig.VCC_VIDEO_PATH));
			}

			if (fileName == null) {
				// 命名规则：机构号_摄像机编号_设备类型_年月日_时分秒
				fileName = getFileName(orgCode, cameraId, Tools.toStr(brand), "");
				fileName = fileName.substring(0, fileName.length() - 1);// 去掉最后的.
			}

			videoClient.startRecordByBrand(index, joinPath(savePath, fileName), Tools.toInt(brand));

			jsonObj.put("cameraName", cameraMap.get(DvcCameraBaseDtls.CBD_NAME));
			jsonObj.put("savePath", savePath);
			jsonObj.put("fileName", fileName);
		}
		else {
			throw new Exception("摄像机编号为空!");
		}
		return jsonObj;
	}
	//add by zk
	public JSONObject recordVideoByBrand2 (JSONObject jsonObj) throws Exception {
		Integer index = Tools.toInt(jsonObj.get("index"), videoClient.getIndex());					// 窗口索引
		//Integer cameraId = Tools.toInt(jsonObj.get("cameraId"));									// 摄像机编号
		String cameraId = Tools.toStr(jsonObj.get("cameraId"));									// 摄像机编号
		String orgCode = Tools.toStr(jsonObj.get("orgCode"));										// 机构号
		String savePathDefault = Tools.toStr(clientMap.get(DvcVideoClientConfig.VCC_VIDEO_PATH));	// 默认保存路径
		String savePath = Tools.toStr(jsonObj.get("savePath"), savePathDefault);					// 保存路径
		String fileName = Tools.toStr(jsonObj.get("fileName"));										// 保存文件名

		Map<String, Object> cameraMap = null;	// 缓存的摄像机基础信息
		Object brand = null;	// 摄像机品牌
		Object dvrId = null;	// 摄像机设备编号

		if (cameraId != null) {
			// 查询摄像机基础信息
			cameraMap = RedisCache.getHashMap(DvcCameraBaseDtls.tableName, cameraId);
			dvrId = cameraMap.get(DvcCameraBaseDtls.CBD_DVR_IDNTY);
			brand = RedisCache.getObject(DvcVideoDeviceInfo.tableName, dvrId, DvcVideoDeviceInfo.VDI_BRAND);

			if (savePath == null) {
				savePath = Tools.toStr(clientMap.get(DvcVideoClientConfig.VCC_VIDEO_PATH));
			}

			if (fileName == null) {
				// 命名规则：机构号_摄像机编号_设备类型_年月日_时分秒
				fileName = getFileName(orgCode, cameraId, Tools.toStr(brand), "");
				fileName = fileName.substring(0, fileName.length() - 1);// 去掉最后的.
			}

			fileName=videoClient.startRecordByBrand(index, joinPath(savePath, fileName), Tools.toInt(brand));
			//现在startRecordByBrand加了个返回类型，返回录像文件的名称包含后缀 update by zk 2018-03-31
			if(fileName==null) {
				throw new Exception("录像失败!");
			}
						
			jsonObj.put("cameraName", cameraMap.get(DvcCameraBaseDtls.CBD_NAME));
			jsonObj.put("savePath", savePath);
			jsonObj.put("fileName", fileName);
		}
		else {
			throw new Exception("摄像机编号为空!");
		}
		return jsonObj;
	}


/**
 * 注释了QUERY
 */
	@Override
	public JSONObject playbackFileName(Integer index, String file, Integer devType) throws Exception {
		Boolean result = false;// 播放结果
		String fileName = null;// 播放的文件
		List<Object> params = null;// 获取设备类型的参数集
		List<Map<String, Object>> retListMap = null;// 设备类型结果集
		JSONObject jsonObj = new JSONObject();
		
		if (index == null) {
			index = videoClient.getIndex();
			log.debug("未传入视频文件播放的窗口位置，自动获取窗口索引:" + index);
		}

		if (devType == null) {
			log.debug("未传入视频文件的设备类型，从数据库获取...");
			// 获取文件名称
			fileName = file.substring(file.lastIndexOf("\\") + 1);
			// 设置查询参数
			params = new ArrayList<Object>();
			params.add(fileName);
			// 处理查询结果
			retListMap = queryProcess.process(VideoClientService.CDS_QUERY_DVCTYPE_BY_EIN_FILE_NAME, "0", params);
			if (retListMap != null && retListMap.size() > 0){
				devType = Integer.valueOf(retListMap.get(0).get("VDI_BRAND").toString());
				log.debug("获取播放的视频文件[" + fileName + "]的设备类型:" + devType);
			}
		}
		// 关闭当前画面并休眠100毫秒缓冲
		videoClient.closeMult(index);
		Thread.sleep(100);

		// 播放录像并获取返回结果
		result = videoClient.playBackFileMult(index, file, devType);

		jsonObj.put("result", result);
		jsonObj.put("index", index);
		jsonObj.put("devType", devType);

		// 记录操作日志
		this.saveUserHandleLog("播放视频录像【" + fileName + "】，播放" + (result ? "成功!" : "失败!"),
				ISysLogService.SubType.PLAY_FILE);
		return jsonObj;
	}

	
	@Override
	public String joinPath(String filePath, String fileName){
		int lastIndex = filePath.lastIndexOf("\\");
		if( lastIndex == filePath.length() - 1){
			filePath = filePath.substring(0, filePath.length() - 1);
		}
		return filePath + "\\" + fileName;
	}
	
	@Override
	public void stopRecord(Integer index) throws Exception {
		videoClient.stopRecord(index);
	}

	@Override
	public byte[] getPictureData(String imagepath) throws Exception {
		return videoClient.getImageBytes(imagepath);
	}

	@Override
	public void closeAll() throws Exception {
		videoClient.closeMult();
	}

	@Override
	public void closeAll_mult() throws Exception {
		videoClient.closeMult();
	}

	@Override
	public void closeVideo_Mult(Integer index) throws Exception {
		videoClient.closeMult(index);
	}

	@Override
	public Boolean uploadFile(String ftpServerIP, String ftpUserID, String ftpPassword, String filename) throws Exception {
		String cusNumber = userBean.getOrgCode();
		Map<String, Object > ftpServerConfig = RedisCache.getHashMap(CdsFtpServerConfig.tableName, cusNumber);
		if (ftpServerConfig != null) {
			if( Tools.isEmpty( ftpServerIP ) ) {
				ftpServerIP = Tools.toStr( ftpServerConfig.get(CdsFtpServerConfig.FSC_SERVER_IP) ); 
			}
			if( Tools.isEmpty( ftpUserID ) ) {
				ftpUserID = Tools.toStr( ftpServerConfig.get(CdsFtpServerConfig.FSC_USER_NAME) );
			}
			if( Tools.isEmpty( ftpPassword ) ) {
				ftpPassword = Tools.toStr( ftpServerConfig.get(CdsFtpServerConfig.FSC_PASSWORD) );
			}
		}
		return videoClient.uploadFile(ftpServerIP, ftpUserID, ftpPassword, filename);
	}
	
	@Override
	public Boolean uploadFileAndPath(String ftpServerIP, String ftpUserID, String ftpPassword, String filename,String path) throws Exception {
		String cusNumber = userBean.getOrgCode();
		Map<String, Object > ftpServerConfig = RedisCache.getHashMap(CdsFtpServerConfig.tableName, cusNumber);
		if (ftpServerConfig != null) {
			if( Tools.isEmpty( ftpServerIP ) ) {
				ftpServerIP = Tools.toStr( ftpServerConfig.get(CdsFtpServerConfig.FSC_SERVER_IP) ); 
			}
			if( Tools.isEmpty( ftpUserID ) ) {
				ftpUserID = Tools.toStr( ftpServerConfig.get(CdsFtpServerConfig.FSC_USER_NAME) );
			}
			if( Tools.isEmpty( ftpPassword ) ) {
				ftpPassword = Tools.toStr( ftpServerConfig.get(CdsFtpServerConfig.FSC_PASSWORD) );
			}
		}
		return videoClient.uploadFileAndPath(ftpServerIP, ftpUserID, ftpPassword, filename,path);
	}

	@Override
	public Boolean downloadFile(String ftpServerIP, String ftpUserID, String ftpPassword, String filename, String filePath) throws Exception {
		String cusNumber = userBean.getOrgCode();
		String ftpPath = "";
		Map<String, Object > ftpServerConfig = RedisCache.getHashMap(CdsFtpServerConfig.tableName, cusNumber);

		if (ftpServerConfig != null) {
			if( Tools.isEmpty( ftpServerIP ) ) {
				ftpServerIP = Tools.toStr( ftpServerConfig.get(CdsFtpServerConfig.FSC_SERVER_IP) ); 
			}
			if( Tools.isEmpty( ftpUserID ) ) {
				ftpUserID = Tools.toStr( ftpServerConfig.get(CdsFtpServerConfig.FSC_USER_NAME) );
			}
			if( Tools.isEmpty( ftpPassword ) ) {
				ftpPassword = Tools.toStr( ftpServerConfig.get(CdsFtpServerConfig.FSC_PASSWORD) );
			}
			ftpPath = Tools.toStr( ftpServerConfig.get(CdsFtpServerConfig.FSC_DOWNLOAD_ADDRS) );
		}
		// 下载文件
		return videoClient.downloadFile(ftpServerIP, ftpUserID, ftpPassword, filename, filePath);
	}

	/**
	 * 获取设备类型
	 * @param cameraId
	 * @return
	 */
	private Integer getDvcType(String cameraId){
		try {
			Object key = RedisCache.getObject(DvcCameraBaseDtls.tableName, cameraId, DvcCameraBaseDtls.CBD_DVR_IDNTY);
			Object dvrType = RedisCache.getObject(DvcVideoDeviceInfo.tableName, key, DvcVideoDeviceInfo.VDI_BRAND);
			return Integer.valueOf(dvrType.toString());
		} catch (Exception ex) {
			log.error("获取摄像机["+cameraId+"]设备类型异常：" + ex.getMessage());
			return null;
		}
	}

	/**
	 * INT类型转换
	 * @param obj
	 * @return
	 */
	private Integer toInt(Object obj){
		if (obj != null)
			return Integer.valueOf(obj.toString());
		return null;
	}

	/**
	 * 获取文件名(组合规则：机构号_摄像机编号_设备类型_年月日_时分秒.文件类型)
	 * @param orgCode 机构号
	 * @param cameraId 摄像机ID
	 * @param deviceType 摄像机对应设备类型
	 * @param fileType 文件类型：jgp、mp4等
	 * @return
	 */
	private String getFileName(Object orgCode, Object cameraId, Object deviceType, Object fileType){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		StringBuffer strBuf = new StringBuffer();
		strBuf.append( orgCode 		).append("_")
			  .append( cameraId 	).append("_")
			  .append( deviceType 	).append("_")
			  .append( dateFormat.format( new Date() ) ).append(".")
			  .append( fileType );
		return strBuf.toString();
	}

	/**
	 * 插入系统操作日志
	 * @param handleLog
	 */
	private void saveUserHandleLog(final String handleLog, final ISysLogService.SubType subType){
		List<Object> logParams = null;// 系统操作日志参数
		// 设置日志参数
		logParams = new ArrayList<Object>();
		logParams.add(userBean.getOrgCode());// 机构号
		logParams.add(userBean.getUserId());// 用户ID
		logParams.add(userBean.getRealName());// 用户姓名
		logParams.add(3);// 用户操作类型
		logParams.add(subType.toString());// 用户操作子类型
		logParams.add(handleLog);// 用户操作类型
		//HandleLogManager.insert( logParams );
	}
	
	/**
	 * 注释了cameraList = queryProcess.process("sys_query_group_cameras", "0", "0", params);
	 */

	@Override
	public Object playGroupVideo(String orgId, String groupId, String groupName, JSONObject extend) throws Exception {
		JSONArray retArray = new JSONArray();
		JSONObject retJSON = null;
		JSONArray cameraMap = null;// 播放的摄像MAP
		List<Map<String, Object>> cameraList = null;// 摄像机列表
		List<Object> params = new ArrayList<Object>();// 查询参数

		boolean isAutoLayout = extend.getBooleanValue("isAutoLayout");
		int curLayout = extend.getIntValue("curLayout");

		params.add(orgId);
		params.add(groupId);

		// 1.查询群组
		cameraList = queryProcess.process("sys_query_group_cameras", "0", "0", params);
		if( cameraList != null && cameraList.size() > 0 ){
			cameraMap = new JSONArray();

			String maxLayout = ConfigUtil.get(ConfigUtil.VIDEOCLIENT_MAX_LAYOUT);
			int index = 0;
			int maxIndex = isAutoLayout ? Tools.toInt( maxLayout ) : curLayout;

			for(Map<String, Object> map : cameraList ){
				retJSON = new JSONObject();
				retJSON.put("index", index);
				retJSON.put("cameraId", map.get("ID"));
				retJSON.put("cameraName", map.get("Name"));
				retArray.add(retJSON);
				
				if( index < maxIndex ){
					cameraMap.add(retJSON);
				}
				index++;
			}
			int layout = index > maxIndex ? maxIndex : index;
			// 2.设置布局
			this.setLayout(layout, maxIndex);
			// 3.打开摄像机
			//update by zk
			//this.playVideoStreamMult( cameraMap );
			this.playVideoMult( cameraMap );
		} else {
			retJSON = new JSONObject();
		}
		this.saveUserHandleLog("打开摄像机群组【" + groupName + "】", ISysLogService.SubType.NONE);
		// 返回打开的摄像机数据
		return retArray;
	}
	/**
     * 打开摄像机并记录日志
     * @param options 参数对象
	 * options = {
	 * 		"cusNumber": "机构号",
	 * 		"userId": "操作人编号",
	 * 		"userName": "操作人名称",
	 * 		"type": "操作类型：1实时",
	 * 		"subType": "操作子类型：1手动、2轮循(自动 add by zk)、3群组",
	 * 		"cameraList": [
	 * 			{"index": "窗口索引", "cameraId": "摄像机编号"}
	 * 		]
	 * }
     * @return
     */
	@Override
	public JSONArray playVideoHandle(JSONObject options) throws Exception {
		JSONArray cameraList = options.getJSONArray("cameraList");

		// 临时处理解决办法
//		String clientIp = Tools.toStr(clientMap.get(DvcVideoClientConfig.VCC_CLIENT_IP));
//		IVideoClient videoClient = new VideoClientImpl(clientIp);
		videoClient.restoreForm();
		
		//bak
		/*String maxLayout = ConfigUtil.get(ConfigUtil.VIDEOCLIENT_MAX_LAYOUT);
		int layout = cameraList.size();
		int maxIndex = Tools.toInt( maxLayout );
		// 2.设置布局
		this.setLayout(layout > maxIndex ? maxIndex : layout, maxIndex);*/
		
		/*//判断subtype类型，如果是手动（1）则不设置布局使用当前布局，如果是自动（2）则根据传入摄像数量自动设置布局 add by zk 2018-03-19
		String subType = Tools.toStr(options.get("subType"));	
		if(subType!=null) {
			//自动
			if("2".equals(subType)) {
				String maxLayout = ConfigUtil.get(ConfigUtil.VIDEOCLIENT_MAX_LAYOUT);
				int layout = cameraList.size();
				int maxIndex = Tools.toInt( maxLayout );
				// 2.设置布局
				this.setLayout(layout > maxIndex ? maxIndex : layout, maxIndex);
			}
		}*/
		
		
		// 打开视频
		//this.playVideoStreamMult(cameraList);
		this.playVideoMult(cameraList);
		// 2. 记录日志
		this.recordVideoHandleLog(options, cameraList);

		return cameraList;
	}

	/**
	 * 打开对讲视屏
	 */
	public Boolean  iniTalkMult(JSONObject inParams) throws Exception{
		Integer dvcType = 801;
		//随机生成主机号码索引
		Random random = new Random();
		int index = random.nextInt(LOCAL_CODE.length);
		inParams.put("LocalCode",Integer.valueOf(LOCAL_CODE[index]));
		String jsonString = inParams.toJSONString();
		Boolean initTalk = videoClient.initTalk(dvcType, jsonString);
		return initTalk;
	}
	
	@Override
	public Boolean startTalkMult(JSONObject reqJSON) throws Exception{
		Integer dvcType = 801;
		Integer slaveCode=reqJSON.getInteger("slaveCode");
		Boolean startTalkMult=	videoClient.startTalk(dvcType, slaveCode);
		return startTalkMult;
	}

	@Override
	public Boolean stopTalk(JSONObject reqJSON) throws Exception{
		Integer dvcType = 801;
		Integer slaveCode=reqJSON.getInteger("slaveCode");
		return videoClient.stopTalk(dvcType, slaveCode);
	}
	
	@Override
	public JSONArray playGroupHandle(JSONObject options) throws Exception {
		String cusNumber = Tools.toStr( options.get("cusNumber") );
		String groupId = Tools.toStr( options.get("groupId") );
		String groupName = Tools.toStr( options.get("groupName") );
		JSONObject extend = options.getJSONObject("extend");

		// 1. 打开视频群组
		JSONArray cameraList = (JSONArray)this.playGroupVideo(cusNumber, groupId, groupName, extend);
		JSONArray openedList = new JSONArray();
		int maxIndex = Tools.toInt(ConfigUtil.get(ConfigUtil.VIDEOCLIENT_MAX_LAYOUT));

		// 2. 如果视频群组数量大于最大播放数量则需要过滤下数据
		if( maxIndex < cameraList.size() ){
			for(int i = 0; i < maxIndex; i++){
				openedList.add( cameraList.get(i) );
			}
		}

		// 3. 记录日志
		this.recordVideoHandleLog(options, openedList);
		return cameraList;
	}
	
	
	
	
	
	@Override
	public JSONArray playbackHandle(JSONObject options) throws Exception {
		JSONArray cameraList = options.getJSONArray("cameraList");
		JSONObject layout = options.getJSONObject("layout");
		JSONObject cameraInfo = null;
		JSONObject retJSON = null;

		// 1.设置窗口布局
		if( layout != null ){
			this.setLayout(
					layout.getIntValue("layout"), 
					layout.getIntValue("last")
			);
		}

		// 2.回放视频
		for(int i = 0; i < cameraList.size(); i++){
			cameraInfo = cameraList.getJSONObject( i );
			retJSON = this.playback_Mult(
					Tools.toInt( cameraInfo.get("index") ), 
					Tools.toStr( cameraInfo.get("cameraId") ),
					Tools.toStr( cameraInfo.get("startTime") ),
					Tools.toStr( cameraInfo.get("endTime") )
			);
			cameraInfo.put("devType", retJSON.get("devType"));
		}

		// 3.记录日志
		this.recordVideoHandleLog(options, cameraList);

		return cameraList;
	}

	/**
	 * 记录日志
	 * @param options 参数对象
	 * options = {
	 * 		"cusNumber": "机构号",
	 * 		"userId": "操作人编号",
	 * 		"userName": "操作人名称",
	 * 		"type": "操作类型：1实时、2回放、3群组",
	 * 		"subType": "操作子类型：1手动、2轮循"
	 * }
	 * @param cameraList 摄像机列表
	 * cameraList = [{
	 * 		"cameraId": "摄像机编号",
	 * 		"cameraName": "摄像机名称"
	 * }]
	 */
	private void recordVideoHandleLog (JSONObject options, JSONArray cameraList) {
		List<Object> logParams = null;
		JSONObject cameraInfo = null;
		for(int i = 0; i < cameraList.size(); i++){
			cameraInfo = cameraList.getJSONObject( i );
			logParams = new ArrayList<Object>();
			logParams.add( options.get("cusNumber") );	// 机构号
//			logParams.add( "" );						// 日志编号
			logParams.add( options.get("userId") );		// 操作人编号
			logParams.add( options.get("userName") );	// 操作人名称
			logParams.add( options.get("type") );		// 操作类型
			logParams.add( options.get("subType") );	// 操作子类型
			logParams.add( cameraInfo.get("cameraId") );// 摄像机编号
			logParams.add( cameraInfo.get("cameraName") );// 摄像机名称
			logParams.add( cameraList.size() );			// 打开数量

			// 保存日志
			//VideoHandleLogManager.insert( logParams );
		}
	}

	
}
