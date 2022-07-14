package com.cesgroup.prison.dagl.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.base.entity.DownloadEntity;
import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.framework.commons.CesDateUtils;
import com.cesgroup.framework.springmvc.converter.DownloadEntityBuilder;
import com.cesgroup.framework.springmvc.web.BaseEntityController;
import com.cesgroup.prison.dagl.entity.Atth;
import com.cesgroup.prison.dagl.service.AtthService;
import com.cesgroup.prison.utils.DataUtils;

/**
 * 全文管理controller
 * 
 * @author xiexiaqin
 * @date 2016-04-19
 *
 */
@Controller
@RequestMapping(value = "/atth")
public class AtthController extends BaseEntityController<Atth> {

	@Autowired
	private AtthService service;

	@Override
	public BaseService<Atth, String> getService() {
		return service;
	}

	/**
	 * 上传文件
	 * 
	 * @param file
	 * @param request
	 * @param id
	 */
	@RequestMapping(value = "/upload.do/{id}")
	public void upload(@RequestParam(value = "uploadFile", required = false) MultipartFile[] file,
			HttpServletRequest request, @PathVariable("id") String id, Atth atth) {
		String path = request.getSession().getServletContext().getRealPath("static") + File.separator + "upload";
		if (file.length > 0) {
			for (int i = 0; i < file.length; i++) {
				String fileName = file[i].getOriginalFilename();
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}

				// 保存
				try {
					file[i].transferTo(targetFile);
					/****** 保存全文信息 **************************/
					String createUser = getUser().getUserName();
					String createDate = CesDateUtils.toString(new Date(), "yyyy-MM-dd");
					String fileType = file[i].getContentType();
					String fileSize = String.valueOf(file[i].getSize());
					atth.setOwnerId(id);
					atth.setCreateUser(createUser);
					atth.setCreateDate(createDate);
					atth.setFileType(fileType);
					atth.setFileSize(fileSize);
					atth.setFileName(fileName);
					InputStream is = null;
			       //读取文件流
			       is = file[i].getInputStream();
			       byte[] bytes = FileCopyUtils.copyToByteArray(is);
			     //保存blob字段   
			       atth.setAtthFile(bytes);
			       //service.saveAtthByXml(atth);// 使用xml方式
			       service.insertAtth(atth);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * 多文件上传
	 * 
	 * @param request
	 * @param response
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping("/upload")
	public String upload2(HttpServletRequest request, HttpServletResponse response)
			throws IllegalStateException, IOException {
		// 创建一个通用的多部分解析器
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 判断 request 是否有文件上传,即多部分请求
		if (multipartResolver.isMultipart(request)) {
			// 转换成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 取得request中的所有文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// 记录上传过程起始时的时间，用来计算上传时间
				int pre = (int) System.currentTimeMillis();
				// 取得上传文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null) {
					// 取得当前上传文件的文件名称
					String myFileName = file.getOriginalFilename();
					// 如果名称不为“”,说明该文件存在，否则说明该文件不存在
					if (myFileName.trim() != "") {
						System.out.println(myFileName);
						// 重命名上传后的文件名
						String fileName = new Date().getTime() + file.getOriginalFilename();
						// 定义上传路径
						String path = request.getSession().getServletContext().getRealPath("upload") + File.separator
								+ fileName;
						File localFile = new File(path);
						file.transferTo(localFile);
						/*
						 * Dagl
						 * dagl=service.getDaglpById(request.getParameter("id")=
						 * =null?"":request.getParameter("id").toString());
						 * dagl.setFilename(fileName); service.updateDagl(dagl);
						 */
					}
				}
				// 记录上传该文件后的时间
				int finaltime = (int) System.currentTimeMillis();
				System.out.println(finaltime - pre);
			}

		}
		return "/success";
	}

	/**
	 * 全文下载
	 * 
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
//	@RequestMapping("/download")
//	@ResponseBody
//	public void download(String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
//		request.setCharacterEncoding("UTF-8");
//		response.setContentType("text/html;charset=utf-8");
//
//		java.io.BufferedInputStream bis = null;
//		java.io.BufferedOutputStream bos = null;
//		response.setContentType("application/x-msdownload;");
//		response.setHeader("Content-disposition",
//				"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
//		String ctxPath = request.getSession().getServletContext().getRealPath("static") + File.separator + "upload";
//
//		String downLoadPath = ctxPath + File.separator + fileName;
//		downLoadPath = URLDecoder.decode(downLoadPath, "UTF-8");
//		try {
//			long fileLength = new File(downLoadPath).length();
//
//			response.setHeader("Content-Length", String.valueOf(fileLength));
//			bis = new BufferedInputStream(new FileInputStream(downLoadPath));
//			bos = new BufferedOutputStream(response.getOutputStream());
//			byte[] buff = new byte[2048];
//			int bytesRead;
//			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
//				bos.write(buff, 0, bytesRead);
//			}
//			bos.flush();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			if (bis != null)
//				bis.close();
//			if (bos != null)
//				bos.close();
//		}
//
//	}
	
	/**
	 * 全文下载
	 * 
	 * @param fileName
	 * @param request
	 * @param response
	 * @throws Exception
	 */
//	@RequestMapping(value="/download", produces="application/octet-stream")
//	@ResponseBody
//	public FileSystemResource download(String fileName, HttpServletRequest request) throws Exception {
//		String ctxPath = request.getSession().getServletContext().getRealPath("static") + File.separator + "upload";
//		
//		String downLoadPath = ctxPath + File.separator + fileName;
//		downLoadPath = URLDecoder.decode(downLoadPath, "UTF-8");
//		
//		FileSystemResource resource = new FileSystemResource(downLoadPath);
//		return resource;
//	}
	
	@RequestMapping(value="/download")
	@ResponseBody
	public DownloadEntity download(String fileName, HttpServletRequest request) throws Exception {
		String ctxPath = request.getSession().getServletContext().getRealPath("static") + File.separator + "upload";
		
		String downLoadPath = ctxPath + File.separator + fileName;
		downLoadPath = URLDecoder.decode(downLoadPath, "UTF-8");
		
		File file = new File(downLoadPath);
		return DownloadEntityBuilder.createFileDownloadEntity(file, "测试中文.png");
	}

	/**
	 * 档案管理全文页面
	 *
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/index/{tmId}")
	public ModelAndView atth(@PathVariable("tmId") String tmId) {
		ModelAndView mv = new ModelAndView("dagl/index2");
		mv.addObject("tmId", tmId);
		return mv;
	}

	/**
	 * 列表展示
	 * 
	 * @param entp
	 * @return
	 */
	@RequestMapping(value = "/search/{tmId}")
	@ResponseBody
	@Logger(action = "查询", logger = "")
	public Map<String, Object> searchDate(@PathVariable("tmId") String ownerId) {
		Page<Atth> page = processSearch("EQ_ownerId", ownerId);
		return DataUtils.pageToMap(page);
	}

	/**
	 * 物理删除
	 * 
	 * @param ids
	 */
	@RequestMapping(value = "/destroy")
	@Logger(action = "彻底删除", logger = "${id}")
	public void destroy(String ids, HttpServletResponse response) {
		if (ids != null && !ids.equals("")) {
			for (String id : ids.split(",")) {
				service.deleteDaglById(id);
			}
		}
		try {
			response.getWriter().print("success");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 上传页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/openUpload")
	@Logger(action = "进入上传页面", logger = "${上传页面}")
	public ModelAndView openUpload(String id) {
		ModelAndView mav = new ModelAndView("dagl/fileuploader");
		mav.addObject("id", id);
		return mav;
	}

	/**
	 * 图片查看页面(多张图片)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/showPhotos/{ownerId}")
	@Logger(action = "图片查看页面", logger = "${上传页面}")
	public ModelAndView showPhotos(@PathVariable("ownerId") String ownerId) {
		ModelAndView mav = new ModelAndView("dagl/showPhotos");
		mav.addObject("ownerId", ownerId);
		return mav;
	}

	/**
	 * 图片查看页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/showPhoto")
	@Logger(action = "图片查看页面", logger = "${上传页面}")
	public ModelAndView showPhoto(String filename) {
		ModelAndView mav = new ModelAndView("dagl/showPhoto");

		mav.addObject("filename", filename);

		return mav;
	}

	/**
	 * 查看图片
	 * 
	 * @param ownerId
	 * @return
	 */
	@RequestMapping(value = "/showPhotogallery/{ownerId}")
	@Logger(action = "图片查看页面", logger = "${上传页面}")
	public ModelAndView gallery(@PathVariable("ownerId") String ownerId) {
		List<String> list = service.getFileNames(ownerId);
		ModelAndView mav = new ModelAndView("dagl/gallery");
		mav.addObject("fileList", list);
		return mav;
	}

}
