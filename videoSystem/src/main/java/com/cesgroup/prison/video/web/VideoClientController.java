package com.cesgroup.prison.video.web;

import java.io.File;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.cds.service.videoclient.IVideoClientService;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.framework.util.IpUtil;
import com.cesgroup.framework.util.Tools;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.utils.UserLoginManager;
import com.cesgroup.prison.common.constant.CommonConstant;
@Controller
@Scope("prototype")
@RequestMapping("/videoClient")
public class VideoClientController extends BaseController{
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(VideoClientController.class);

	@Resource
	private IVideoClientService videoClientService;

	private JSONObject getParams(HttpServletRequest request) throws Exception{
		String reqArgs = request.getParameter("args");
		// String reqAddr = request.getRemoteAddr();
		String reqAddr = IpUtil.getIpAddress();
		log.info("客户端视频调用ip：" + reqAddr);
		
		UserBean userBean = UserLoginManager.getUserByLoginIp(reqAddr);
		if( userBean != null ){
			videoClientService.setUserBean(userBean);
			videoClientService.connection(reqAddr);

			log.debug("接收到客户端["+reqAddr+"]的请求参数:<" + reqArgs + ">");
			return JSON.parseObject(reqArgs);
		} else {
			throw new Exception("未获取到用户登录信息");
		}
	}

    /**
     * 设备多画面窗口布局
     * @param arg={"layout":"布局名称(值范围在1-16)"}
     * @throws java.rmi.RemoteException
     */
	@RequestMapping("/setLayout")
	@ResponseBody
    public AjaxMessage setLayout(HttpServletRequest request){
		JSONObject reqJSON = null;
    	try {
    		reqJSON = getParams(request);
        	videoClientService.setLayout(
        			Tools.toInt(reqJSON.get("layout")),
        			Tools.toInt(reqJSON.get("last"))
        	);
        	return new AjaxMessage(true);
    	} catch(Exception ex) {
    		log.error("设备多画面窗口布局异常：", ex);
    		return doError(ex.getMessage());
    	}
    }

	@RequestMapping("/setFormStyle")
	@ResponseBody
	public AjaxMessage setFormStyle(HttpServletRequest request){
		try {
			JSONObject reqJSON = getParams(request);
			boolean result = videoClientService.setFormStyle(
					Boolean.parseBoolean( Tools.toStr( reqJSON.get("topMost"), "true" ) ), 
					Boolean.parseBoolean( Tools.toStr( reqJSON.get("showFormTitle"), "true" ) ), 
					Boolean.parseBoolean( Tools.toStr( reqJSON.get("showVideoTitle"), "false" ) )
			);
			return new AjaxMessage(result);
		} catch (Exception ex) {
			log.error("设置窗口属性异常：", ex);
			return new AjaxMessage(false);
		}
	}
	
    /**
     * 获取当前选中窗口的索引
     * @return 选中的窗口索引
     */
	@RequestMapping("getIndex")
	@ResponseBody
    public AjaxMessage getIndex(HttpServletRequest request){
    	try{
    		getParams(request);
        	return new AjaxMessage(true, videoClientService.getIndex());
    	} catch(Exception ex){
    		log.error("获取当前选中窗口的索引异常：", ex);
    		return doError(ex.getMessage());
    	}
    }
	
	 /**
     * 获取当前选中窗口的摄像头ID
     * @return 选中的窗口的摄像头ID
     */
	@RequestMapping("getWinCameraID")
	@ResponseBody
    public AjaxMessage getWinCameraID(HttpServletRequest request){
    	try{
    		getParams(request);
        	return new AjaxMessage(true, videoClientService.getWinCameraID());
    	} catch(Exception ex){
    		log.error("获取当前选中窗口的摄像头ID异常：", ex);
    		return doError(ex.getMessage());
    	}
    }

    /**
     * 抓图
     * @param arg={"file":"文件名称", "path":"文件路径(可选参数)"};
     */
	@RequestMapping("snap")
	@ResponseBody
    public AjaxMessage snap(HttpServletRequest request){
    	try{
    		JSONObject reqJSON = getParams(request);
    		String cameraId = (String)reqJSON.get("cameraId");
    		String fileName = (String)reqJSON.get("file");
			String filePath = (String)reqJSON.get("path");
    		byte[] result = videoClientService.snap(cameraId, filePath, fileName);
    		return new AjaxMessage((result != null && result.length > 0));	
    	} catch(Exception ex){
    		log.error("抓图异常：", ex);
    		return doError(ex.getMessage());
    	}
    }
 
    /**
     * 多画面实时视频
     * @param request args={"connectType":"连接类型：0-直连设备、1-流媒体", "cameraList":{"窗口索引": "摄像机ID"}}
     */
	@RequestMapping("playVideoMult")
	@ResponseBody
	public AjaxMessage playVideoMult(HttpServletRequest request){
    	try {

    		JSONObject reqJSON = getParams(request);
    		JSONArray cameraList = reqJSON.getJSONArray("list");

    		switch(Integer.valueOf(reqJSON.getString("connectType"))){
    			case 0: videoClientService.playVideoMult(cameraList); break;
    			case 1: videoClientService.playVideoStreamMult(cameraList); break;
    		}

    		return new AjaxMessage(true);

    	} catch(Exception ex){
    		log.error("多画面播放视频异常：", ex);
    		return doError(ex.getMessage());
    		
    	}
    }

    /**
     * 视频回放
     * @param args={"cameraID":"摄像机ID", "startTime":"开始时间yyyy-MM-dd HH:mm:ss", "endTime":"结束时间yyyy-MM-dd HH:mm:ss"}
     * @return 
     */
	@RequestMapping("playbackMult")
	@ResponseBody
    public AjaxMessage playbackMult(HttpServletRequest request){
    	try{
    		JSONObject reqJSON = getParams(request);
    		JSONObject retJSON = videoClientService.playback_Mult(
    				reqJSON.getString("cameraID"), 
    				reqJSON.getString("startTime"), 
    				reqJSON.getString("endTime")
    		);
    		Boolean result = retJSON.getBoolean("result");
    		AjaxMessage retMsg = new AjaxMessage(result);
    		retMsg.setObj(retJSON);
    		retMsg.setMsg(result ? "回放成功!" : "回放失败");
    		return retMsg;
    	} catch(Exception ex){
    		log.error("视频回放异常：", ex);
    		return doError(ex.getMessage());
    	}
    }

	/**
     * 视频回放
     * @param args=[{"index":"画面索引", "cameraID":"摄像机ID", "startTime":"开始时间yyyy-MM-dd HH:mm:ss", "endTime":"结束时间yyyy-MM-dd HH:mm:ss"},...]
     * @return 
     */
	@RequestMapping("playbackVideoMult")
	@ResponseBody
    public AjaxMessage playbackVideoMult(HttpServletRequest request){
    	try{
    		JSONObject reqJSON = getParams(request);
    		JSONObject layout = reqJSON.getJSONObject("layout");
    		JSONArray videoList = reqJSON.getJSONArray("videoList");
    		JSONObject jsonObj = null;
    		JSONObject result = new JSONObject();

    		//1、设置窗口布局
    		try {
    			if( layout != null ){
    				videoClientService.setLayout(
    						layout.getIntValue("layout"), 
    						layout.getIntValue("last")
    				);
    			}
			} catch (Exception ex) {
				log.error("视频回放-设置窗口布局异常", ex);
				return doError(ex.getMessage());
			}

    		int index = 0;
    		String cameraId = null;

    		//2、打开视频回放
    		for (int i = 0; i < videoList.size(); i++) {
    			jsonObj = videoList.getJSONObject(i);
    			index = jsonObj.getInteger("index");
    			cameraId = jsonObj.getString("cameraId");
    			result.put( String.valueOf( index ), cameraId );
    			videoClientService.playback_Mult(
    					index, 
    					cameraId, 
    					jsonObj.getString("startTime"), 
    					jsonObj.getString("endTime")
        		);
			}
    		return new AjaxMessage(true, result);
    	} catch(Exception ex){
    		log.error("视频回放异常：", ex);
    		return doError(ex.getMessage());
    	}
    }

    /**
     * 停止视频回放
     * @param args={"index":"窗口索引"}
     * @return
     * @throws java.rmi.RemoteException
     */
	@RequestMapping("stopPlaybackMult")
	@ResponseBody
    public AjaxMessage stopPlaybackMult(HttpServletRequest request){
    	try{
    		JSONObject reqJSON = getParams(request);
    		String index =  reqJSON.getString("index");
    		Integer location = null;

    		// 判断取值-没有则自动获取当前选中的窗口的索引
    		if (index == null || index.equals("")){
    			location = videoClientService.getIndex();
    		} else {
    			location = Integer.valueOf(index);
    		}

    		// 接口调用
    		boolean result = videoClientService.stopPlayback_Mult(location);

    		// 返回结果
    		return new AjaxMessage(result);

    	} catch(Exception ex){
    		log.error("停止视频回放异常：", ex);
    		return doError(ex.getMessage());
    	}
    }

    /**
     * 开始录像
     * @param args={"file":"文件名", "path":"路径(可选参数)"}
     */
	@RequestMapping("startRecord")
	@ResponseBody
    public AjaxMessage startRecord(HttpServletRequest request){
    	try{
    		JSONObject reqJSON = getParams(request);
    		String cameraId = (String)reqJSON.get("cameraId");
    		String file = (String)reqJSON.get("file");
    		String path = (String)reqJSON.get("path");
    		videoClientService.startRecord(cameraId, file, path);
    		return new AjaxMessage(true);
    	} catch(Exception ex){
    		log.error("开始录像异常：", ex);
    		return doError(ex.getMessage());
    	}
    }

    /**
     * 停止录像
     * @param args={"index":"窗口索引", "fileName":"上传的录像文件（不传则不上传）"}
     */
	@RequestMapping("stopRecord")
	@ResponseBody
    public AjaxMessage stopRecord (HttpServletRequest request) {
    	try{
    		AjaxMessage retData = new AjaxMessage(true, null, "操作成功!");
    		JSONObject reqJSON = getParams(request);
    		String index =  reqJSON.getString("index");
    		String uploadFile = reqJSON.getString("uploadFile");
    		String orgCode = reqJSON.getString("orgCode");		//监狱编号
    		
    		boolean isUpload = false;
    		Integer location = null;

    		// 判断取值-没有则自动获取当前选中的窗口的索引
    		if (index == null || index.equals("")){
    			location = videoClientService.getIndex();
    		} else {
    			location = Integer.valueOf(index);
    		}

    		videoClientService.stopRecord(location);
    		if( !Tools.isEmpty(uploadFile) ){
    			//isUpload = videoClientService.uploadFile(null, null, null, uploadFile);
    			//add by zk

    			Calendar c = Calendar.getInstance();
    			int year = c.get(Calendar.YEAR); 
    			int month = c.get(Calendar.MONTH)+1; 
    			int date = c.get(Calendar.DATE); 
    			//上传到ftp的路径，格式：年/月/日/监狱编号
    			
    			/*if (CommonConstant.systemRootAbsoultPath == null || CommonConstant.systemRootAbsoultPath.equals("")){
    				CommonConstant.systemRootAbsoultPath =  ConfigUtil.get("SYSTEM.ROOT.ABSOULTPATH");
    			}*/
    			
    			String  relationPath1 =CommonConstant.uploadZhddGlobalPath +File.separator+CommonConstant.ftpVideoPlanPath;
    			
    			String absoultPath = CommonConstant.systemRootAbsoultPath +File.separator+relationPath1;
    			File file = new File(absoultPath);
    			if(!file.exists())
    				file.mkdirs();
    			
    			String  relationPath2=CommonConstant.ftpVideoRecordPath+File.separator+year+File.separator+month+File.separator+date+File.separator+reqJSON.getString("orgCode");

    			String webPath = relationPath1 +File.separator+ relationPath2 ;//数据库存储地址，无文件名

    			isUpload = videoClientService.uploadFileAndPath(null, null, null, uploadFile,relationPath2.replaceAll("\\\\", "/"));
    			
    			reqJSON.put("isUpload", isUpload);
    			retData.setMsg(isUpload ? "文件上传成功!" : "文件上传失败!");
    			
    			//add by zk
    			if(!isUpload) {
    				return doError("文件上传失败!");
    			}
    		}
    		retData.setObj(reqJSON);
    		
    		return retData;
    	} catch(Exception ex){
    		log.error("停止录像异常：", ex);
    		return doError(ex.getMessage());
    	}
    }

    /**
     * 关闭所有的单画面窗口
     * @throws java.rmi.RemoteException
     */
	@RequestMapping("closeAll")
	@ResponseBody
    public AjaxMessage closeAll (HttpServletRequest request) {
    	try{
    		getParams(request);
    		// videoClientService.connection(request.getRemoteAddr());
    		videoClientService.connection(IpUtil.getIpAddress());
    		videoClientService.closeAll();
    		return new AjaxMessage(true);
    	} catch(Exception ex){
    		log.error("关闭单画面窗口异常：", ex);
    		return doError(ex.getMessage());
    	}
    }

    /**
     * 关闭所有的多画面窗口
     */
	@RequestMapping("closeAllMult")
	@ResponseBody
    public AjaxMessage closeAllMult(HttpServletRequest request){
    	try{
    		getParams(request);
    		// videoClientService.connection(request.getRemoteAddr());
    		videoClientService.connection(IpUtil.getIpAddress());
    		videoClientService.closeAll_mult();
    		return new AjaxMessage(true);
    	} catch(Exception ex){
    		log.error("关闭多画面窗口异常：", ex);
    		return doError(ex.getMessage());
    	}
    }

    /**
     * 回放视频文件
     */
	@RequestMapping("playVideoFile")
	@ResponseBody
	public AjaxMessage playbackFileName(HttpServletRequest request){
		AjaxMessage ajaxMessage = new AjaxMessage(false);
		try{
			JSONObject reqJSON = getParams(request);
			JSONObject retJSON = null;
    		String index =  reqJSON.getString("index");
    		String fileName = reqJSON.getString("file");
    		String type = reqJSON.getString("type");
    		String ftpPath = reqJSON.getString("ftpPath");
    		Integer dvcType = null;
    		Integer location = null;
    		boolean playResult = false;
    		boolean isDownload = true;// 默认已下载
    		// 判断取值-没有则自动获取当前选中的窗口的索引
    		if (index != null && !index.isEmpty()){
    			location = Integer.valueOf(index);
    		}

    		if (type != null && !type.isEmpty()){
    			dvcType = Integer.valueOf(type);
    		}

    		/*
    		 * 无论本地文件是否存在先去执行downloadFile,如果本地文件存在，则downloadFile会直接返回结果
    		 * 如果本地文件不存在，则downloadFile会在下载完成后返回结果
    		 * */			
    		int lastIndex = fileName.lastIndexOf("\\");
			String name = ftpPath+"\\"+fileName.substring(lastIndex+1);
			String path = fileName.substring(0, lastIndex);
			name =  name.replaceAll("\\\\", "/");
			isDownload = videoClientService.downloadFile(null, null, null, name, path);
			

    		if( isDownload ) {
    			retJSON = videoClientService.playbackFileName(location, fileName, dvcType);
    			playResult = retJSON.getBoolean("result");
    			ajaxMessage.setObj(retJSON);
    			ajaxMessage.setSuccess(playResult);
    			ajaxMessage.setMsg(playResult ? "文件播放成功!" : "文件播放失败!");
    		} else {
    			ajaxMessage.setMsg("录像文件下载失败，无法播放!");
    		}
		} catch(Exception ex){
			log.error("回放视频文件异常：", ex);
			return doError(ex.getMessage());
		}
		return ajaxMessage;
	}

	@RequestMapping("upload")
	@ResponseBody
	public AjaxMessage uploadFile(HttpServletRequest request){
		try {
			JSONObject reqJSON = getParams(request);
			String ftpServerIP = reqJSON.getString("ftpIp");
			String ftpUserID = reqJSON.getString("userId");
			String ftpPassword = reqJSON.getString("password");
			String fileName = reqJSON.getString("fileName");

			boolean result = videoClientService.uploadFile(ftpServerIP, ftpUserID, ftpPassword, fileName);
			return new AjaxMessage(result);
		} catch(Exception ex){
			log.error("上传文件异常：", ex);
			return doError(ex.getMessage());
		}
	}

	@RequestMapping("download")
	@ResponseBody
	public AjaxMessage downloadFile(HttpServletRequest request){
		try {
			JSONObject reqJSON = getParams(request);
			String ftpServerIP = reqJSON.getString("ftpIp");
			String ftpUserID = reqJSON.getString("userId");
			String ftpPassword = reqJSON.getString("password");
			String fileName = reqJSON.getString("fileName");
			String filePath = reqJSON.getString("downPath");
			boolean result = videoClientService.downloadFile(ftpServerIP, ftpUserID, ftpPassword, fileName, filePath);
			return new AjaxMessage(result);
		} catch(Exception ex){
			log.error("下载文件异常：", ex);
			return doError(ex.getMessage());
		}
	}

	@RequestMapping("playGroup")
	@ResponseBody
	public AjaxMessage playGroup(HttpServletRequest request){
		try {
			JSONObject reqJSON = getParams(request);
			String orgId = reqJSON.getString("orgId");
			String groupId = reqJSON.getString("groupId");
			String groupName = reqJSON.getString("groupName");
			JSONObject extend = reqJSON.getJSONObject("extend");
			JSONArray result = (JSONArray)videoClientService.playGroupVideo(orgId, groupId, groupName, extend);
			return new AjaxMessage(true, result);
		} catch(Exception ex){
			log.error("播放视频预案异常：", ex);
			return doError(ex.getMessage());
		}
	}

	/**
	 * 初始化对讲客户端
	 * @param request
	 * @return
	 */
	@RequestMapping("iniTalkMult")
	@ResponseBody
	public AjaxMessage iniTalkMult(HttpServletRequest request){
		try {
			JSONObject reqJSON = getParams(request);
			log.info("对讲视频初始化操作请求参数==>[" + reqJSON.toJSONString() + "]");
			Boolean result = videoClientService.iniTalkMult(reqJSON);
			return new AjaxMessage(result);
		} catch(Exception ex){
			log.error("播放对讲视屏异常：", ex);
			return doError(ex.getMessage());
		}
	}

	
	/**
	 * 开始对讲
	 * @param request
	 * @return
	 * 需要参数
	 * Integer dvcType = 801;
	 *Integer slaveCode=9000;
	 */
	@RequestMapping("/startTalkMult")
	@ResponseBody
	public AjaxMessage startTalkMult(HttpServletRequest request){
		try {
			JSONObject reqJSON = getParams(request);
			log.info("对讲视频开始操作请求参数==>[" + reqJSON.toJSONString() + "]");
			Boolean result = videoClientService.startTalkMult(reqJSON);
			String msg ="";
			if(result) {
				msg = "开始对讲指令成功";
			}else {
				msg = "开始对讲指令失败";
			}
			return new AjaxMessage(result,null,msg);
		} catch(Exception ex){
			log.error("播放对讲视屏异常：", ex);
			return doError(ex.getMessage());
		}
	}

	/**
	 * 停止对讲
	 * @param request
	 *  Integer dvcType = 801;
	 *	Integer slaveCode=9000;
	 * @return
	 */
	@RequestMapping("/stopTalk")
	@ResponseBody
	public AjaxMessage stopTalk(HttpServletRequest request){
		try {
			JSONObject reqJSON = getParams(request);
			log.info("对讲视频停止操作请求参数==>[" + reqJSON.toJSONString() + "]");
			Boolean result = videoClientService.stopTalk(reqJSON);
			return new AjaxMessage(result);
		} catch(Exception ex){
			log.error("停止对讲视屏异常：", ex);
			return doError(ex.getMessage());
		}
	}

	
	

	
	
	
	@RequestMapping("videoHandle")
	@ResponseBody
	public AjaxMessage videoHandleAndRecordLog (HttpServletRequest request) {
		try {
			JSONObject reqJSON = getParams(request);
			JSONArray retJSON = null;
			int handleType = reqJSON.getIntValue("handleType");
			log.info("视频操作请求参数==>[" + reqJSON.toJSONString() + "]");

			// 分类处理不同操作
			switch (handleType) {
				case 1: retJSON = videoClientService.playVideoHandle( reqJSON ); break;
				case 2: retJSON = videoClientService.playGroupHandle( reqJSON ); break;
				case 3: retJSON = videoClientService.playbackHandle( reqJSON ); break;
			}
			return new AjaxMessage(true, retJSON);
		} 
		catch(Exception ex) {
			log.error("视频操作异常：", ex);
			return doError(ex.getMessage());
		}
	}
	protected AjaxMessage doError(String msg){
		if (msg != null){
			if (msg.indexOf("java.net.ConnectException") > -1){
				msg = "无法连接到视频客户端!";
			} else if (msg.indexOf("由于内部错误，服务器无法处理该请求") > -1){
				msg = "视频客户端内部错误，请重新启动!";
			} else if (msg.indexOf("The server was unable to process the request due to an internal error.") > -1){
				msg = "视频客户端异常，请重新启动!";
			}
		} else {
			msg = "系统数据错误(null)!";
		}
		return new AjaxMessage(false, null, msg);
	}
}
