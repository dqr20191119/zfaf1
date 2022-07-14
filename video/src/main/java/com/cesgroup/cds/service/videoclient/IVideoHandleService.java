package com.cesgroup.cds.service.videoclient;

/**
 * 视频操作服务-接口
 * @author xie.yh
 */
public interface IVideoHandleService {
//	/**
//	 * 连接视频客户端
//	 * @param reqIp 请求IP
//	 */
//	public void connection(String reqIp) throws Exception;
//
//
//	/**
//	 * 获取当前选中画面的索引
//	 * @return	画面索引：最小值0，最大值15
//	 */
//	public Integer getIndex() throws Exception;
//
//
//	/**
//	 * 设置画面布局
//	 * @param oldLayout	旧布局：最小值1，最大值16
//	 * @param newLayout 新布局：最小值1，最大值16
//	 */
//	public void setLayout(int oldLayout, int newLayout) throws Exception;
//
//	/**
//	 * 设置画面布局
//	 * @param oldLayout	旧布局：最小值1，最大值16
//	 * @param newLayout 新布局：最小值1，最大值16
//	 */
//	public void setLayout(LayoutReq req) throws Exception;
//
//	/**
//	 * 播放实时视频（流媒体）
//	 */
//	public PlayVideoResp playStreamVideo(PlayVideoReq req) throws Exception;
//
//
//	/**
//	 * 播放实时视频（流媒体）
//	 */
//	public List<PlayVideoResp> playStreamVideo(List<PlayVideoReq> reqList) throws Exception;
//	
//
//	/**
//	 * 播放实时视频（直连）
//	 */
//	public PlayVideoResp playDirectVideo(PlayVideoReq req) throws Exception;
//
//
//	/**
//	 * 播放实时视频（直连）
//	 */
//	public List<PlayVideoResp> playDirectVideo(List<PlayVideoReq> reqList) throws Exception;
//
//
//	/**
//	 * 回放视频
//	 */
//	public PlayBackResp playbackVideo(PlayBackReq req) throws Exception;
//
//
//	/**
//	 * 回放视频
//	 */
//	public List<PlayBackResp> playbackVideo(List<PlayBackReq> reqList) throws Exception;
//
//
//	/**
//	 * 停止回放
//	 * @param index
//	 */
//	public boolean stopPlayback(Integer index) throws Exception;
//
//	/**
//	 * 停止回放
//	 * @param list 回放的画面索引集合
//	 * @return 成功停止回放的画面索引集合
//	 */
//	public List<Integer> stopPlayback(List<Integer> list) throws Exception;
//
//
//	/**
//	 * 播放视频文件
//	 */
//	public PlayFileResp playVideoFile(PlayFileReq req) throws Exception;
//
//
//	/**
//	 * 播放视频文件
//	 */
//	public List<PlayFileResp> playVideoFile(List<PlayFileReq> reqList) throws Exception;
//
//
//	/**
//	 * 停止播放视频文件
//	 * @param index 画面索引
//	 */
//	public boolean stopPlayFile(Integer index) throws Exception;
//
//
//	/**
//	 * 停止播放视频文件
//	 * @param list 播放文件的画面索引集合
//	 * @return 成功停止播放的画面索引集合
//	 */
//	public List<Integer> stopPlayFile(List<Integer> list) throws Exception;
//
//
//	/**
//	 * 视频截图
//	 */
//	public VideoSnapResp videoSnap(VideoSnapReq req) throws Exception;
//
//
//	/**
//	 * 视频录像
//	 */
//	public VideoRecordResp videoRecord(VideoRecordReq req) throws Exception;
//
//
//	/**
//	 * 停止录像
//	 * @param index 画面索引
//	 */
//	public void stopRecord(Integer index) throws Exception;
//	
//	/**
//	 * 上传文件
//	 * @param orgId 机构ID
//	 * @param fileName 文件名（带全路径）
//	 */
//	public boolean uploadFile(String orgId, String fileName) throws Exception;
//	
//	/**
//	 * 下载文件
//	 * @param orgId			机构号
//	 * @param fileName		下载文件名称
//	 * @param savePath		下载保存路径
//	 */
//	public boolean downloadFile(String orgId, String fileName, String savePath) throws Exception;
//
//
//	/**
//	 * 关闭客户端
//	 */
//	public void closeClient() throws Exception;
//
//
//	/**
//	 * 关闭指定画面
//	 * @param index 画面索引
//	 * @throws Exception
//	 */
//	public void closeVideo(Integer index) throws Exception;
//
//
//	/**
//	 * 关闭画面
//	 * @param list 画面索引集合
//	 * @return 结果true/false
//	 */
//	public void closeVideo(List<Integer> list) throws Exception;
}
