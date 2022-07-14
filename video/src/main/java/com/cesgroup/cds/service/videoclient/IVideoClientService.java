package com.cesgroup.cds.service.videoclient;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.prison.common.bean.user.UserBean;


public interface IVideoClientService {

	/**
	 * 设置登录Session的用户实体对象
	 * @param userSessionBean
	 */
	public void setUserBean(UserBean userBean);

	/**
	 * 获取客户端配置信息
	 * @return
	 */
	public Map<String, Object> getClientMap();
	
	/**
	 * 连接视频客户端
	 * @param reqIP 请求方IP
	 * @throws Exception
	 */
	public boolean connection(String reqIP);

	/**
     * 设备多画面窗口布局
     * @param layoutName 布局名称1-16
     */
    public void setLayout(int layout, int last) throws Exception;

    /**
     * 设置窗口属性
     * @param topMost 是否置顶
     * @param showFormTitle 显示对话框
     * @param showVideoTitle 显示视频工具条
     * @return
     */
    public boolean setFormStyle(Boolean topMost, Boolean showFormTitle, Boolean showVideoTitle) throws Exception;
    
    /**
     * 获取当前选中窗口的索引
     * @return 索引(0-n)
     * @throws Exception
     */
    public Integer getIndex() throws Exception;
    /**
     * 获取当前选中窗口的摄像头ID
     * @return 选中窗口的摄像头ID
     * @throws Exception
     */
    public String getWinCameraID() throws Exception;
    
    /**
     * 视频截图
     * @param cameraId 视频ID
     * @param savePath 截图保存路径
     * @param fileName 保存的文件名
     * @return 图片二进制流
     * @throws Exception
     */
    public byte[] snap(String cameraId, String savePath, String fileName) throws Exception;
    
    public JSONObject snap(JSONObject jsonObj) throws Exception;

    public String joinPath(String filePath, String fileName);

    /**
     * 多画面实时视频（直连设备）
     * @param map Map<窗口索引, 摄像机ID>
     * @throws Exception
     */
    public void playVideoMult(JSONArray cameraList) throws Exception;
    
    /**
     * 多画面实时视频（流媒体）
     * @param map Map<窗口索引, 摄像机ID>
     * @throws Exception
     */
    public JSONArray playVideoStreamMult(JSONArray cameraList) throws Exception;
    
    /**
     * 视频回放
     * @param cameraId 摄像机ID
     * @param startTime 开始时间yyyy-MM-dd HH:mm:ss
     * @param endTime 结束时间yyyy-MM-dd HH:mm:ss
     * @return 
     * @throws Exception
     */
    public JSONObject playback_Mult(String cameraId, String startTime, String endTime) throws Exception;
    
    /**
     * 视频回放
     * @param index 画面索引
     * @param cameraId 摄像机ID
     * @param startTime 开始时间yyyy-MM-dd HH:mm:ss
     * @param endTime 结束时间yyyy-MM-dd HH:mm:ss
     * @return 
     * @throws Exception
     */
    public JSONObject playback_Mult(Integer index, String cameraId, String startTime, String endTime) throws Exception;

    /**
     * 停止视频回放
     * @param location 窗口索引
     * @return
     * @throws Exception
     */
    public Boolean stopPlayback_Mult(Integer location) throws Exception;

    /**
     * 开始录像
     * @param cameraId 视频ID
     * @param file 文件名
     * @param path 存放路径(末尾不带路径符号)
     * @throws Exception
     */
    public void startRecord(String cameraId, String file, String path) throws Exception;

    public JSONObject recordVideo(JSONObject jsonObj) throws Exception;

    /**
     * 停止录像
     * @param index 窗口索引
     * @throws Exception
     */
    public void stopRecord(Integer index) throws Exception;
    
    /**
     * 关闭所有的单画面窗口
     * @throws Exception
     */
    public void closeAll() throws Exception;

    /**
     * 关闭所有的多画面窗口
     * @throws Exception
     */
    public void closeAll_mult() throws Exception;
    
    /**
     * 关闭窗口画面
     * @param index 窗口索引
     * @throws Exception
     */
    public void closeVideo_Mult(Integer index) throws Exception;
    
    /**
     * 获取图片
     * @param imagepath 图片路径(包含名称)
     * @return 字节流
     * @throws Exception
     */
    public byte[] getPictureData(String imagepath) throws Exception;
    
    /**
     * 播放(回放)视频文件
     * @param index 窗口索引
     * @param file 视频文件
     * @param type 视频的类型：1大华、8海康、20Ednns
     * @return
     * @throws Exception
     */
    public JSONObject playbackFileName(Integer index, String file, Integer type) throws Exception;
    
    /**
     * 上传文件
     * @param ftpServerIP FTP服务器IP
     * @param ftpUserID FTP用户名
     * @param ftpPassword FTP密码
     * @param filename 本地文件(路径+名称)
     * @return {Boolean} true成功/false失败
     * @throws Exception
     */
    public Boolean uploadFile(String ftpServerIP, String ftpUserID, String ftpPassword, String filename) throws Exception;
    
    /**
     * 上传文件
     * @param ftpServerIP FTP服务器IP
     * @param ftpUserID FTP用户名
     * @param ftpPassword FTP密码
     * @param filename 本地文件(路径+名称)
     * @param path 上传到ftp的路径 栗子：aa、aa/bb
     * @return {Boolean} true成功/false失败
     * @throws Exception
     */
    public Boolean uploadFileAndPath(String ftpServerIP, String ftpUserID, String ftpPassword, String filename,String path) throws Exception;
    /**
     * 下载文件
     * @param ftpServerIP FTP服务器IP
     * @param ftpUserID FTP用户名
     * @param ftpPassword FTP密码
     * @param filename FTP文件（路径+名称）
     * @param filePath 本地存放路径（路径）
     * @return {Boolean} true成功/false失败
     * @throws Exception
     */
    public Boolean downloadFile(String ftpServerIP, String ftpUserID, String ftpPassword, String filename, String filePath) throws Exception;
    
    /**
     * 打开群组摄像机
     * @param orgId 机构号
     * @param groupId 群组ID
     * @param groupName 群组名称
     * @return
     * @throws Exception
     */
    public Object playGroupVideo(String orgId, String groupId, String groupName, JSONObject extend) throws Exception;
    
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
    public JSONArray playVideoHandle (JSONObject options) throws Exception;

    /**
     * 打开摄像机群组并记录日志
     * @param options 参数对象
	 * options = {
	 * 		"cusNumber": "机构号",
	 * 		"userId": "操作人编号",
	 * 		"userName": "操作人名称",
	 * 		"type": "操作类型：1实时",
	 * 		"subType": "操作子类型：1手动、2轮循、3群组",
	 * 		"groupId": "群组编号",
	 * 		"groupName": "群组名称",
	 * 		"extend": 群组扩张参数（type=3时有效），格式={"isAutoLayout":"是否自动布局", "curLayout":"当前布局"}
	 * }
     * @return
     */
    public JSONArray playGroupHandle (JSONObject options) throws Exception;

    /**
     * 回放摄像机并记录日志
     * @param options 参数对象
	 * options = {
	 * 		"cusNumber": "机构号",
	 * 		"userId": "操作人编号",
	 * 		"userName": "操作人名称",
	 * 		"type": "操作类型：2回放",
	 * 		"subType": "操作子类型：1手动、2轮循、3群组",
	 * 		"layout": {"layout":"布局", "last":"上一次布局"}
	 * 		"cameraList": [
	 * 			{"index":"", "cameraId":"", "cameraName":"", "startTime":"", "endTime":""}
	 * 		]
	 * }
     * @return
     */
    public JSONArray playbackHandle(JSONObject options) throws Exception;
    
	public Boolean iniTalkMult(JSONObject reqJSON)throws Exception;

	public Boolean startTalkMult(JSONObject reqJSON)throws Exception ;

	public Boolean stopTalk(JSONObject reqJSON)throws Exception;
}
