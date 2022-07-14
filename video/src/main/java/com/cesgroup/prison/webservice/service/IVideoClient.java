package com.cesgroup.prison.webservice.service;

public interface IVideoClient {
	/**
	 * 统一调用接口
	 * @param methodName	方法名称
	 * @param params		方法对应的JSON格式字符串
	 * @return
	 * @throws Exception
	 */
	public String videoCtrl (String methodName, String params) throws Exception;



	/* ********** 视频调用相关接口 BEGIN ********** */
	public Integer getIndex () throws Exception;
	//获取当前选中窗口的摄像头ID
	public String getWinCameraID () throws Exception;
	public byte[] getImageBytes (String imagePath) throws Exception;

	public Boolean setCtrlIp(String serverIp, Boolean isCtrl) throws Exception;
	public void setLayout (Integer layout, Integer width, Integer height, Integer left, Integer top) throws Exception;
	public void setForm (Boolean mostTop, Boolean showTitle, Boolean showCtrlTitle) throws Exception;
	public void reminForm () throws Exception;
	public void restoreForm () throws Exception;

	public void playVideoMult (String title, Integer channel, String cameraIP, String dvrIP, Integer dvrPort, String dvrUserName, String dvrPassword, Integer type, Integer index,String deviceID) throws Exception;
	public void playVideoStreamMult (Integer cameraId, Integer dvcType, String streamIP, Integer streamPort, Integer index, Integer streamType) throws Exception;

	public Boolean playBackMult (String title,Integer index, String startTime, String endTime, Integer channel, String cameraIP, String dvrIP, Integer dvrPort, String dvrUserName, String dvrPassword, Integer type) throws Exception;
	public Boolean stopPlayBackMult (Integer index) throws Exception;

	public Boolean playBackFileMult (Integer index, String fileName, Integer type) throws Exception;
	public Boolean stopPlayBackFileMult (Integer index) throws Exception;

	public void startRecord (Integer index, String file) throws Exception;
	public String startRecordByBrand(Integer index, String file, Integer brand) throws Exception;
	public void stopRecord (Integer index) throws Exception;
	public void snap (Integer index, String file) throws Exception;

	public void closeMult () throws Exception;
	public void closeMult (Integer index) throws Exception;

	public Boolean uploadFile (String serverIp, String userId, String password, String fileName) throws Exception;
	/* ********** 上传文件携带上传路径参数 add by zk ********** */
	public Boolean uploadFileAndPath (String serverIp, String userId, String password, String fileName,String path) throws Exception;
	public Boolean downloadFile (String serverIp, String userId, String password, String fileName, String filePath) throws Exception;
	/* ********** 根据时间下载录像 add by zk 2018-03-31********** */
	public Boolean GetRecordFileByTime(Integer index, String file, String startTime, String endTime, Integer brand) throws Exception;
	/* ********** 视频调用相关接口 END ********** */



	/* ********** 对讲调用相关接口 BEGIN ************* */
	/**
	 * 初始化对讲
	 * @param dvcType	设备类型：莱邦-801
	 * @param jsonStr	初始化参数，JSON格式字符串 ：{
	 * 		"LocalCode":"主机编号：1000-60000，值必须是1000的倍数", 
	 * 		"Sn":"主机SN号：四位号码，必须唯一", 
	 * 		"SipAcc":"SIP账户", 
	 * 		"SipPwd":"SIP密码", 
	 * 		"BoxAddress":"地址盒IP"
	 * }
	 */
	public Boolean initTalk (Integer dvcType, String jsonStr) throws Exception;

	/**
	 * 开始对讲
	 * @param dvcType	设备类型
	 * @param slaveCode	分机编码
	 */
	public Boolean startTalk (Integer dvcType, Integer slaveCode) throws Exception;

	/**
	 * 结束对讲
	 * @param dvcType	设备类型
	 * @param slaveCode	分机编码
	 */
	public Boolean stopTalk (Integer dvcType, Integer slaveCode) throws Exception;

	/**
	 * 注销对讲
	 * @param dvcType	设备类型
	 */
	public Boolean uninitTalk (Integer dvcType) throws Exception;
	/* ********** 对讲调用相关接口 END ************* */
}