package com.cesgroup.prison.framework.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

/**
 * cesgroup
 * FTP工具类
 * @author lihh
 */
@Repository
public class FtpUtil {
	private static final Logger logger = LoggerFactory.getLogger(FtpUtil.class);
	/**
	 * 连接FTP服务器
	 */
	public  FTPClient connectFTPServer(String ip,String userName,String pwd){
		FTPClient ftpClient = null;
		try {
			    ftpClient = new FTPClient();
			    ftpClient.connect(ip,21);
				//连接FTP服务器
			    ftpClient.login(userName, pwd);
				//设置为二进制传输模式
			    ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			    ftpClient.setBufferSize(1024*1024*10);
			    ftpClient.setControlEncoding("UTF-8");
//				ftpClient.sendServer("PASV");
				logger.debug("连接FTP服务器成功");
				return ftpClient;
		} catch (IOException e) {
			ftpClient = null;
			logger.error("连接FTP服务器失败",e);
		}
		return ftpClient;
	}
	
	/**
	 * 关闭FTP服务器连接
	 * @return true 关闭连接成功, false 关闭连接异常
	 */
	public  boolean closeFTPConnect(FTPClient ftpClient){
		try {
			if(ftpClient!=null && ftpClient.isConnected()){
				ftpClient.logout();
				ftpClient.disconnect();
				logger.debug("关闭FTP服务器连接成功");
			}else{
				logger.debug("FTP服务器连接不存在或已关闭");
			}
			return true;
		} catch (IOException e) {
			logger.error("关闭FTP服务器连接失败",e);
			return false;
		}
	}
	/**
	 * 文件上传
	 * @param in 存放文件对象的输入流
	 * @param localFileName 文件名
	 * @return
	 */
	public boolean uploadForIn(InputStream in,String remoteFileName,FTPClient ftpClient){
				try {
					//文件上传
					if(ftpClient.storeFile(remoteFileName, in)){
						logger.debug("上传文件成功");
						return true;
					}else{
						logger.debug("上传文件失败");
						return false;
					}
				} catch (IOException e) {
					logger.error("上传文件失败",e);
					return false;
				}finally{
						try {
							if(in!=null){
								in.close();
							}
						} catch (IOException e) {
							logger.error("关闭流失败",e);
						}
				}
	}
	/**
	 * FTP文件下载
	 * @param fileName 下载的文件名
	 * @param ftpClient FTP客户端
	 * @param localDir  下载到本地的目录 
	 * @return 下载完成后本地的文件名
	 */
	public boolean download(String fileName,FTPClient ftpClient,String localDir){
		FileOutputStream out = null;
		try {
			File file = new File(localDir+fileName);
			
			if(file.isFile()){
				logger.debug("文件已存在,无需下载");
				return true;
			}
			out = new FileOutputStream(file);
			if(ftpClient.retrieveFile(fileName, out)){
				logger.debug("下载文件成功");
				return true;
			}else{
				return false;
			}
		} catch (IOException e) {
			logger.error("下载文件失败",e);
			return false;
		}finally{
			try {
				if(out!=null){
					out.close();
				}
			} catch (IOException e) {
				logger.error("关闭流失败",e);
			}
		}
	}
	/**
	 * 获取FTP服务器上的文件列表,并返回所有的文件名
	 * @param ftpClient
	 * @return
	 */
	public List<String> ScanFiles(FTPClient ftpClient,String suffix){
		List<String>  fileNameList = new ArrayList<String>(); 
		try {
			String[] name  =  ftpClient.listNames();
			for(String fileName:name){
				if(fileName.endsWith(suffix)){
					fileNameList.add(fileName);
				}
					
			}
		} catch (IOException e) {
			logger.error("获取FTP服务器文件列表失败",e);
		}
		if(fileNameList.size()>0) logger.debug("获取FTP服务器文件列表成功");
		return fileNameList;
	}
	
	/**
	 * 重命名远程ftp文件
	 * @author zk
	 * @param newFileName 新远程文件名称(路径-必须保证在同一路径下) 
	 * @param remoteFileName 文件名
	 * @return
	 */
	public boolean rename(String newFileName,String remoteFileName,FTPClient ftpClient){
		try {
			ftpClient.enterLocalPassiveMode(); 
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE); 
			FTPFile[] files = ftpClient.listFiles(remoteFileName); 
			if (files.length == 1) 
			{ 
				boolean status = ftpClient.rename(remoteFileName, newFileName); 
				return status;
			} 
			else
			{ 
				logger.debug("文件不存在");
				return false;
			} 
		} catch (IOException e) {
			logger.error("重命名文件失败",e);
			return false;
		}finally{
				
		}
	}
	 /** 
	   * 删除远程FTP文件 
	   * @author zk
	   * @param remoteFileName 
	   *      远程文件路径 
	   * @return 
	   */
	  public boolean delete(String remoteFileName,FTPClient ftpClient)
	  { 
		  try {
				ftpClient.enterLocalPassiveMode(); 
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE); 
				FTPFile[] files = ftpClient.listFiles(remoteFileName); 
				if (files.length == 1) 
				{ 
					boolean status = ftpClient.deleteFile(remoteFileName); 
					return status;
				} 
				else
				{ 
					logger.debug("文件不存在");
					return true;
				} 
			} catch (IOException e) {
				logger.error("删除文件失败",e);
				return false;
			}finally{
					
			} 
	  } 
	  
	  /** 
	   * 判断远程FTP文件是否存在 
	   * @author zk
	   * @param remoteFileName 
	   *      远程文件路径 
	   * @return 
	   */
	  public boolean isHaveFile(String remoteFileName,FTPClient ftpClient)
	  { 
		  try {
				ftpClient.enterLocalPassiveMode(); 
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE); 
				FTPFile[] files = ftpClient.listFiles(remoteFileName); 
				if (files.length > 0) 
				{ 
					return true;
				} 
				else
				{ 
					logger.debug("文件不存在");
					return false;
				} 
			} catch (IOException e) {
				logger.error("查询ftp文件是否存在失败",e);
				return false;
			}finally{
					
			} 
	  } 
}
