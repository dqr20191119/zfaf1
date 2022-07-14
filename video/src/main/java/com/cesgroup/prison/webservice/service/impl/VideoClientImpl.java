package com.cesgroup.prison.webservice.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONArray;
import com.cesgroup.prison.webservice.AbsWebService;
import com.cesgroup.prison.webservice.service.IVideoClient;

public class VideoClientImpl extends AbsWebService implements IVideoClient {
	// 日志对象
	private static final Logger log = LoggerFactory.getLogger(VideoClientImpl.class);
	
	// 视频客户端WS地址
	private String address = "http://?:4503/VideoPlayerService";

	// 静态对象定义
	private static final String SOAPACTION = "http://127.0.0.1/CalculatorService/";
	private static final String NAMESPACE = "http://127.0.0.1";

	// 输入参数类型集合
	/* ******************** 视频调用接口 BEGIN ******************* */
	private static final JSONArray getImageBytes = new JSONArray();			// 获取图片字节码
	private static final JSONArray setLayout = new JSONArray();				// 设置窗口布局的参数
	private static final JSONArray setCtrlIp = new JSONArray();				// 设置云控制服务地址
	private static final JSONArray setForm = new JSONArray();				// 设置窗口
	private static final JSONArray videoCtrl = new JSONArray();				// 控制通用方法
	private static final JSONArray playVideoMult = new JSONArray();			// 视频播放（直连）
	private static final JSONArray playVideoStreamMult = new JSONArray();	// 视频播放（流媒体）
	private static final JSONArray playBackMult = new JSONArray();			// 视频回放
	private static final JSONArray stopPlayBackMult = new JSONArray();		// 停止视频回放
	private static final JSONArray playBackFileMult = new JSONArray();		// 回放视频文件
	private static final JSONArray stopPlayBackFileMult = new JSONArray();	// 停止回放视频文件
	private static final JSONArray startRecord = new JSONArray();			// 开始录像
	private static final JSONArray startRecordByBrand = new JSONArray();	// 开始录像（新接口，根据品牌视频客户端去加文件后缀）
	private static final JSONArray stopRecord = new JSONArray();			// 停止录像
	private static final JSONArray snap = new JSONArray();					// 视频截图
	private static final JSONArray closeMult = new JSONArray();				// 关闭视频
	private static final JSONArray uploadFile = new JSONArray();			// 上传文件
	private static final JSONArray uploadFileAndPath = new JSONArray();		// 上传文件携带路径参数（上传到ftp的路径）
	private static final JSONArray downloadFile = new JSONArray();			// 下载文件
	private static final JSONArray getRecordFileByTime = new JSONArray();	// 根据时间下载录像
	/* ******************** 视频调用接口 END ********************* */


	/* ******************** 对讲调用接口 BEGIN ******************* */
	private static final JSONArray initTalk = new JSONArray();				// 初始化对讲
	private static final JSONArray startTalk = new JSONArray();				// 开始对讲
	private static final JSONArray stopTalk = new JSONArray();				// 结束对讲
	private static final JSONArray uninitTalk = new JSONArray();			// 注销对讲
	/* ******************** 对讲调用接口 END ********************* */



	// 静态初始化
	static {
		/* ******************** 视频调用接口 BEGIN ******************* */
		// 获取图片字节码
		getImageBytes.add(getParameter("imagepath", "string"));

		// 设置窗口布局
		setLayout.add(getParameter("LayoutName", "string"));
		setLayout.add(getParameter("width", "int"));
		setLayout.add(getParameter("height", "int"));
		setLayout.add(getParameter("x", "int"));
		setLayout.add(getParameter("y", "int"));

		// 设置云控制服务地址
		setCtrlIp.add(getParameter("ControlServerIP", "string"));
		setCtrlIp.add(getParameter("BooControl", "boolean"));

		// 设置窗口
		setForm.add(getParameter("topMost", "boolean"));
		setForm.add(getParameter("showFormTitle", "boolean"));
		setForm.add(getParameter("showVideoControlTitle", "boolean"));

		// 控制通用方法
		videoCtrl.add(getParameter("StrJson", "string"));
		videoCtrl.add(getParameter("methodName", "string"));

		// 视频播放（直连）
		playVideoMult.add(getParameter("title", "string"));
		playVideoMult.add(getParameter("channel", "int"));
		playVideoMult.add(getParameter("cameraIP", "string"));
		playVideoMult.add(getParameter("dvrIP", "string"));
		playVideoMult.add(getParameter("dvrPort", "int"));
		playVideoMult.add(getParameter("dvrUserName", "string"));
		playVideoMult.add(getParameter("dvrPassword", "string"));
		playVideoMult.add(getParameter("type", "int"));
		playVideoMult.add(getParameter("index", "int"));
		playVideoMult.add(getParameter("deviceID", "string"));		//摄像头id add by zk 2018-04-25

		// 视频播放（流媒体）
		playVideoStreamMult.add(getParameter("cameraID", "int"));
		playVideoStreamMult.add(getParameter("deviceType", "int"));
		playVideoStreamMult.add(getParameter("streamIP", "string"));
		playVideoStreamMult.add(getParameter("streamPort", "int"));
		playVideoStreamMult.add(getParameter("index", "int"));
		playVideoStreamMult.add(getParameter("streamType", "int"));

		// 视频回放
		playBackMult.add(getParameter("location", "int"));
		playBackMult.add(getParameter("startTime", "string"));
		playBackMult.add(getParameter("endTime", "string"));
		playBackMult.add(getParameter("channel", "int"));
		playBackMult.add(getParameter("cameraIP", "string"));
		playBackMult.add(getParameter("dvrIP", "string"));
		playBackMult.add(getParameter("dvrPort", "int"));
		playBackMult.add(getParameter("dvrUserName", "string"));
		playBackMult.add(getParameter("dvrPassword", "string"));
		playBackMult.add(getParameter("type", "int"));

		// 停止视频回放
		stopPlayBackMult.add(getParameter("location", "int"));

		// 回放视频文件
		playBackFileMult.add(getParameter("location", "int"));
		playBackFileMult.add(getParameter("FileName", "string"));
		playBackFileMult.add(getParameter("type", "int"));

		// 停止回放视频文件
		stopPlayBackFileMult.add(getParameter("location", "int"));

		// 开始录像
		startRecord.add(getParameter("index", "int"));
		startRecord.add(getParameter("file", "string"));

		// 开始录像（新接口，根据品牌视频客户端去加文件后缀）
		startRecordByBrand.add(getParameter("index", "int"));
		startRecordByBrand.add(getParameter("file", "string"));
		startRecordByBrand.add(getParameter("brand", "int"));

		// 停止录像
		stopRecord.add(getParameter("index", "int"));

		// 视频截图
		snap.add(getParameter("index", "int"));
		snap.add(getParameter("file", "string"));

		// 关闭视频
		closeMult.add(getParameter("index", "int"));

		// 上传文件
		uploadFile.add(getParameter("ftpServerIP", "string"));
		uploadFile.add(getParameter("ftpUserID", "string"));
		uploadFile.add(getParameter("ftpPassword", "string"));
		uploadFile.add(getParameter("filename", "string"));
		// 上传文件携带路径参数（上传到ftp的路径）
		uploadFileAndPath.add(getParameter("ftpServerIP", "string"));
		uploadFileAndPath.add(getParameter("ftpUserID", "string"));
		uploadFileAndPath.add(getParameter("ftpPassword", "string"));
		uploadFileAndPath.add(getParameter("filename", "string"));
		uploadFileAndPath.add(getParameter("newPath", "string"));

		// 下载文件
		downloadFile.add(getParameter("ftpServerIP", "string"));
		downloadFile.add(getParameter("ftpUserID", "string"));
		downloadFile.add(getParameter("ftpPassword", "string"));
		downloadFile.add(getParameter("filename", "string"));
		downloadFile.add(getParameter("filePath", "string"));
		
		// 根据时间下载录像
		getRecordFileByTime.add(getParameter("index", "int"));
		getRecordFileByTime.add(getParameter("file", "string"));
		getRecordFileByTime.add(getParameter("startTime", "string"));
		getRecordFileByTime.add(getParameter("endTime", "string"));
		getRecordFileByTime.add(getParameter("brand", "int"));
		/* ******************** 视频调用接口 END ********************* */



		/* ******************** 对讲调用接口 BEGIN ******************* */
		// 初始化对讲
		initTalk.add(getParameter("json", "string"));
		initTalk.add(getParameter("deviceType", "int"));

		// 开始对讲
		startTalk.add(getParameter("dstCode", "int"));
		startTalk.add(getParameter("deviceType", "int"));

		// 结束对讲
		stopTalk.add(getParameter("dstCode", "int"));
		stopTalk.add(getParameter("deviceType", "int"));

		// 注销对讲
		uninitTalk.add(getParameter("deviceType", "int"));
		/* ******************** 对讲调用接口 END ********************* */
	}

	public VideoClientImpl (String ip) {
		//Lihh
		if(ip.equals("0:0:0:0:0:0:0:1"))
			ip="127.0.0.1";
		
		this.address = this.address.replace("?", ip);//"10.58.223.24"
	}

	@Override
	protected String getAddress() {
		return address;
	}

	@Override
	protected String getNameSpace() {
		return NAMESPACE;
	}

	@Override
	protected String getSoapAction() {
		return SOAPACTION;
	}



	@Override
	public Integer getIndex () throws Exception {
		return (Integer) call("GetIndex", "int");
	}
	@Override
	public String getWinCameraID () throws Exception {
		return (String) call("GetWinCameraID", "string");
	}

	@Override
	public byte[] getImageBytes (String imagePath) throws Exception {
		return (byte[]) call("GetPictureData", getImageBytes, "base64Binary", imagePath);
	}

	@Override
	public Boolean setCtrlIp (String serverIp, Boolean isCtrl) throws Exception {
		return (Boolean) call("InitControlIP", setCtrlIp, "boolean", serverIp, isCtrl);
	}

	@Override
	public void setLayout (Integer layout, Integer width, Integer height, Integer left, Integer top) throws Exception {
		call("setLayout", setLayout, "string", new Object[]{layout, width, height, left, top});
	}

	@Override
	public void setForm (Boolean mostTop, Boolean showTitle, Boolean showCtrlTitle) throws Exception {
		call("setFormStyle", setForm, "string", mostTop, showTitle, showCtrlTitle);
	}

	@Override
	public void reminForm () throws Exception {
		call("MinimizeVoideoControl");
	}

	@Override
	public void restoreForm () throws Exception {
		call("RestoreVideoControl");
	}

	@Override
	public String videoCtrl (String methodName, String params) throws Exception {
		return (String) call("VideoController", videoCtrl, "string", params, methodName);
	}

	/**
	 * 新疆的deviceID传递的是cameraID，目的是为了getWinCameraID获取到当前选中窗口的cameraID 
	 * 北京把这deviceID当做摄像机编码传递，为了保证功能的正常使用，在后面新增carmerID字段
	 * **/
	@Override
	public void playVideoMult (String title, Integer channel, String cameraIP, String dvrIP, Integer dvrPort, String dvrUserName, String dvrPassword, Integer type, Integer index,String deviceID) throws Exception {
		log.info("playVideo_Mult参数==>[" + title + ","+channel+ ","+cameraIP+ ","+dvrIP+ ","+dvrPort+ ","+dvrUserName+ ","+dvrPassword+ ","+type+ ","+index+ ","+deviceID+"]");
		call("playVideo_Mult", playVideoMult, "string", title, channel, cameraIP, dvrIP, dvrPort, dvrUserName, dvrPassword, type, index,deviceID);
	}

	@Override
	public void playVideoStreamMult (Integer cameraId, Integer dvcType, String streamIP, Integer streamPort, Integer index, Integer streamType) throws Exception {
		call("playVideo_Stream_Mult", playVideoStreamMult, "string", cameraId, dvcType, streamIP, streamPort, index, streamType);
	}

	@Override
	public Boolean playBackMult (String title,Integer index, String startTime, String endTime, Integer channel, String cameraIP, String dvrIP, Integer dvrPort, String dvrUserName, String dvrPassword, Integer type) throws Exception {
		return (Boolean) call("Playback_Mult", playBackMult, "boolean", index, startTime, endTime, channel, cameraIP, dvrIP, dvrPort, dvrUserName, dvrPassword, type);
	}

	@Override
	public Boolean stopPlayBackMult (Integer index) throws Exception {
		return (Boolean) call("StopPlayback_Mult", stopPlayBackMult, "boolean", index);
	}

	@Override
	public Boolean playBackFileMult (Integer index, String fileName, Integer type) throws Exception {
		return (Boolean) call("PlayBack_File_Mult", playBackFileMult, "boolean", index, fileName, type);
	}

	@Override
	public Boolean stopPlayBackFileMult (Integer index) throws Exception {
		return (Boolean) call("StopPlayBack_File_Mult", stopPlayBackFileMult, "boolean", index);
	}

	@Override
	public void startRecord (Integer index, String file) throws Exception {
		call("StartRecord", startRecord, "string", index, file);
	}

	@Override
	public String startRecordByBrand(Integer index, String file, Integer brand) throws Exception {
		return (String)call("StartRecordByBrand", startRecordByBrand, "string", index, file, brand);
	}

	@Override
	public void stopRecord (Integer index) throws Exception {
		call("StopRecord", stopRecord, "string", index);
	}

	@Override
	public void snap (Integer index, String file) throws Exception {
		call("Snap", snap, "string", index, file);
	}

	@Override
	public void closeMult () throws Exception {
		call("closeAll_mult");
	}

	@Override
	public void closeMult (Integer index) throws Exception {
		call("closeVideo_Mult", closeMult, "string", index);
	}

	@Override
	public Boolean uploadFile (String serverIp, String userId, String password, String fileName) throws Exception {
		return (Boolean) call("UploadFile", uploadFile, "boolean", serverIp, userId, password, fileName);
	}

	/* ********** 上传文件携带上传路径参数(path格式，相对于ftp的相对路径举个栗子：aa、aa/bb) add by zk ********** */
	@Override
	public Boolean uploadFileAndPath (String serverIp, String userId, String password, String fileName,String path) throws Exception{
		return (Boolean) call("UploadFileAndPath", uploadFileAndPath, "boolean", serverIp, userId, password, fileName,path);
	}
	@Override
	public Boolean downloadFile (String serverIp, String userId, String password, String fileName, String filePath) throws Exception {
		return (Boolean) call("DownloadFile", downloadFile, "boolean", serverIp, userId, password, fileName, filePath);
	}

	/* ********** 根据时间下载录像 add by zk 2018-03-31********** */
	@Override
	public Boolean GetRecordFileByTime(Integer index, String file, String startTime, String endTime, Integer brand) throws Exception{
		return (Boolean) call("GetRecordFileByTime", getRecordFileByTime, "boolean", index, file, startTime, endTime,brand);
	}




	@Override
	public Boolean initTalk(Integer dvcType, String jsonStr) throws Exception {
		// Init(String param1, ,int deviceType)
		return (Boolean) call("InitTalk_Mult", initTalk, "boolean", jsonStr, dvcType);
	}

	@Override
	public Boolean startTalk(Integer dvcType, Integer slaveCode) throws Exception {
		// StartTalk(int dstCode,int deviceType)
		return (Boolean) call("StartTalk", startTalk, "boolean", slaveCode, dvcType);
	}

	@Override
	public Boolean stopTalk(Integer dvcType, Integer slaveCode) throws Exception {
		// StopTalk(int dstCode, ,int deviceType)
		return (Boolean) call("StopTalk", stopTalk, "boolean", slaveCode, dvcType);
	}

	@Override
	public Boolean uninitTalk(Integer dvcType) throws Exception {
		// Uninit(int deviceType)
		return (Boolean) call("UinitTalk_Mult", uninitTalk, "boolean", dvcType);
	}
}
