package com.cesgroup.prison.aqfk.monitor.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import com.cesgroup.prison.common.facade.AuthSystemFacade;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cesgroup.cds.service.videoclient.IVideoClientService;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.framework.util.Tools;
import com.cesgroup.prison.aqfk.monitor.dao.EvidenceMapper;
import com.cesgroup.prison.aqfk.monitor.entity.Evidence;
import com.cesgroup.prison.aqfk.monitor.service.IEvidenceService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.utils.UserLoginManager;
import com.cesgroup.prison.common.cache.CdsFtpServerConfig;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.db.service.RedisCache;
import com.cesgroup.prison.framework.utils.FtpUtil;
import com.cesgroup.scrap.db.QueryProcess;

import sun.misc.BASE64Decoder;


@Service
public class EvidenceServiceImpl extends BaseService<Evidence, String> implements IEvidenceService{
	@Autowired
	private EvidenceMapper evidenceMapper;
	
	@Resource
	IVideoClientService videoClientService;
	
	@Resource
	private FtpUtil  ftpUtil;
	
	//批量删除
	@Transactional
	public int batchDelete(List<String> ids) {
		return evidenceMapper.batchDelete(ids);
	}

	public Page<Map<String, String>> listEvidence(Evidence evidence_param,String startTime,String endTime,Pageable pageable) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("evidence", evidence_param);
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		return evidenceMapper.listEvidence(map, pageable);
	}
	//局部修改
	@Transactional
	public void updatePart(Evidence evidence_param){
		evidenceMapper.updatePart(evidence_param);
	}
	
	@Transactional
	public Map<String, Object> addEvidence(HttpServletRequest request) {
		Map<String, Object> map=new HashMap<String, Object>();
		String args = request.getParameter("args");	// 请求参数
		String ip = request.getRemoteAddr();		// 请求IP
		String fileType	= null;		// 文件类型
		String snapFile = null;		// 截图文件
		String typeDesc = "截图";	// 操作说明
		byte[] pictureBytes = null;	// 图片字节流

		JSONObject reqJSON = null;	// 请求对象
		JSONObject retJSON = null;	// 相应对象
		UserBean userBean = null;	// 用户对象
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("_yyyy-MM-dd_");// 日期格式对象

		try {
			
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR); 
			int month = c.get(Calendar.MONTH)+1; 
			int date = c.get(Calendar.DATE); 
			//上传到ftp的路径，格式：年/月/日/监狱编号
			
			/*if (CommonConstant.systemRootAbsoultPath == null || CommonConstant.systemRootAbsoultPath.equals("")){
				CommonConstant.systemRootAbsoultPath =  ConfigUtil.get("SYSTEM.ROOT.ABSOULTPATH");
			}*/
			
			//uploads的地址
			String  relationPath1 =CommonConstant.uploadZhddGlobalPath +File.separator+CommonConstant.ftpVideoPlanPath;
			//ftp根目录为起始的目录，即uploads之后的路径
			String  relationPath2="";
			
			String absoultPath = CommonConstant.systemRootAbsoultPath +File.separator+relationPath1;
			File file = new File(absoultPath);
			if(!file.exists())
				file.mkdirs();
			
			
			
			boolean isUpload=false;
			userBean = UserLoginManager.getUserByLoginIp(ip);
			reqJSON = JSON.parseObject( args );
			fileType = reqJSON.getString("fileType");

			// 创建客户端连接
			videoClientService.setUserBean(userBean);
			videoClientService.connection(ip);

			// 截图并获取图片的字节流
			if( "1".equals(fileType) ){
				retJSON = videoClientService.snap( reqJSON );
				snapFile = videoClientService.joinPath( retJSON.getString("savePath"), retJSON.getString("fileName") );
				//截图完成后才能继续走下面的代码，因为videoClient.snap没有返回值，此处只能多延迟一会update by zk 2018-03-29
				Thread.sleep(1000);// 稍微延迟再获取图片流（防止有获取不到图片流）
				
				
				//pictureBytes = videoClientService.getPictureData( snapFile );		
				//isUpload = videoClientService.uploadFile(null, null, null, snapFile);
				
				relationPath2=CommonConstant.ftpVideoImgPath+File.separator+year+File.separator+month+File.separator+date+File.separator+reqJSON.getString("orgCode");
						
				isUpload = videoClientService.uploadFileAndPath(null, null, null, snapFile,relationPath2.replaceAll("\\\\", "/"));
				
    			map.put("msg",isUpload ? "文件上传成功!" : "文件上传失败!");
    			
    			if(!isUpload) {
    				map.put("success", false);
    				return map;
    			}

			}
			

			// 如果是录像则开始录像
			if( "2".equals(fileType) ){
				typeDesc = "录像";
				retJSON = videoClientService.recordVideo( JSON.parseObject( args ) );
				
				relationPath2=CommonConstant.ftpVideoRecordPath+File.separator+year+File.separator+month+File.separator+date+File.separator+reqJSON.getString("orgCode");
			}
			
			String title = retJSON.getString("title");
			String number = retJSON.getString("number");
			String address = retJSON.getString("address");
			String cameraName = retJSON.getString("cameraName");

			if( title == null ){ 
				title = cameraName 
						+ dateFormat.format(new Date())
						+ typeDesc
						+ number; 
			}
			if( address == null ){ address = cameraName; }
			
			Evidence evidence=new Evidence();
			evidence.setEinCusNumber((String)retJSON.get("orgCode"));				// 机构号
			evidence.setEinTitle(title);											// 证据标题
			evidence.setEinContentTypeIndc((String)retJSON.get("contentType"));		// 证据内容类型
			evidence.setEinContentDesc((String)retJSON.get("contentDesc"));			// 证据内容说明
			evidence.setEinCameraId((String)retJSON.get("cameraId"));				// 摄像机编号
			evidence.setEinCameraName(cameraName);									// 摄像机名称
			evidence.setEinAddrs(address);											// 证据发生地点(因为现在摄像机名称基本是按地点来命名的)
			evidence.setEinFileTypeIndc((String)retJSON.get("fileType"));			// 文件类型
			evidence.setEinFileName((String)retJSON.get("fileName"));				// 文件名称
			evidence.setEinFilePath((String)retJSON.get("savePath"));				// 文件地址（客户端文件保存的地址）
			evidence.setEinCrteUserId((String)retJSON.get("userId"));				// 记录人员
			evidence.setEinSttsIndc("0");  											// 未使用
			evidence.setEinCrteTime(new Date());
			evidence.setEinFtpPath(relationPath2); 									//上传到ftp的路径(相对于ftp根目录)
			evidence.setEinFtpPrefix(relationPath1);                				//前缀
			Evidence evi_tmp=this.insert(evidence);
			//返回添加证据的id --add by zk
			retJSON.put("sqno", evi_tmp.getId());
			map.put("obj", retJSON);
			map.put("success", true);
		} catch(Exception ex) {
			map.put("obj", ex.getMessage());
			map.put("success", false);
		}
		return map;
	}
	public Map<String, Object> searchVideo( String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return evidenceMapper.searchVideo(map);
	}
	public Map<String, Object> searchImage(String id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", id);
		return evidenceMapper.searchImage(map);
	}
	
	/*
	 * 更新FTP上的图片，把原始图片重命名并上传修改后的图片
	 * @param fileName 包含路径
	*/
	public AjaxMessage updateFtpImg(String cusNumber,String fileName,String imgBase64) {
		try {
			
			//获取FTP服务器信息
			String ftpServerIP = ""; //IP
			String ftpUserID = ""; //用户名
			String ftpPassword = ""; //密码
			
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
			}else{
				return new AjaxMessage(false, null, "未查询到FTP服务器信息,无法上传");
			}
			
			//连接FTP服务器
			FTPClient ftpClient = ftpUtil.connectFTPServer(ftpServerIP,ftpUserID,ftpPassword);
			
			//原始文件名
			String remoteFileName = fileName;
			//把原始文件重命名
			String exts = remoteFileName.substring(remoteFileName.lastIndexOf("."));
			String newFileName = remoteFileName.substring(0,remoteFileName.lastIndexOf(".")) + "_org" + exts;
			
			if(ftpUtil.isHaveFile(newFileName, ftpClient)) {
				ftpUtil.delete(remoteFileName, ftpClient);
			}else {
				//重命名
				ftpUtil.rename(newFileName, remoteFileName, ftpClient);
			}
			imgBase64 = imgBase64.replaceAll("data:image/png;base64,", "");
			imgBase64 = imgBase64.replaceAll("data:image/jpeg;base64,", ""); 
            BASE64Decoder decoder = new BASE64Decoder();
            // Base64解码      
            byte[] imageByte = null;
            try {
                imageByte = decoder.decodeBuffer(imgBase64);      
                for (int i = 0; i < imageByte.length; ++i) {      
                    if (imageByte[i] < 0) {// 调整异常数据      
                        imageByte[i] += 256;      
                    }      
                }      
            } catch (Exception e) {
                 e.printStackTrace(); 
            }   
			
            //上传修改后的图片，使用原来的名字
            InputStream imageStream = new ByteArrayInputStream(imageByte);
			
            if(ftpUtil.uploadForIn(imageStream,fileName,ftpClient)){
				//上传成功关闭FTP服务器连接
				ftpUtil.closeFTPConnect(ftpClient);
				
				return new AjaxMessage(true, null, "更新成功");
			}else{
				return new AjaxMessage(false, null, "更新失败");
			}
		} catch (Exception e) {
			logger.error("上传文件异常",e);
			return new AjaxMessage(false, null, "更新文件异常");
		}
	}

    @Override
    public Evidence getNowEvidence() {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        String cusNumber = user.getCusNumber();
        return evidenceMapper.getNowEvidence(cusNumber);
    }
}
 