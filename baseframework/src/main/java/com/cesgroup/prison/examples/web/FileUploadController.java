package com.cesgroup.prison.examples.web;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.framework.commons.CesDateUtils;
import com.cesgroup.framework.springmvc.web.BaseEntityController;
import com.cesgroup.prison.dagl.entity.Atth;
import com.cesgroup.prison.dagl.service.AtthService;

@Controller
@RequestMapping("/fileupload")
public class FileUploadController extends BaseEntityController<Atth> {
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
	@ResponseBody
	public void upload(@RequestParam(value = "uploadFile", required = false) MultipartFile[] file,
			HttpServletRequest request, @PathVariable("id") String id, Atth atth) {
		String path = request.getSession().getServletContext().getRealPath("static") + File.separator + "upload";
		if (file.length > 0) {
			for (int i = 0; i < file.length; i++) {
				String fileName = file[i].getOriginalFilename();
				System.out.println(path);
				File targetFile = new File(path, fileName);
				if (!targetFile.exists()) {
					targetFile.mkdirs();
				}

				// 保存
				try {
				
					/****** 保存全文信息 **************************/
					String createUser = "";//getUser().getUserName();
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
					service.insertAtth(atth);
				   	file[i].transferTo(targetFile);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
}
