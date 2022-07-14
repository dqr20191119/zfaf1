package com.cesgroup.prison.examples.web;

import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.framework.springmvc.web.BaseEntityController;
import com.cesgroup.prison.dagl.entity.Atth;
import com.cesgroup.prison.dagl.service.AtthService;

@Controller
@RequestMapping("/exp-down")
public class DownLoadController extends BaseEntityController<Atth> {
	@Autowired
	private AtthService service;

	@Override
	public BaseService<Atth, String> getService() {
		return service;
	}

	/**
	 * 下载
	 * @param fileName
	 * @param ids
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/download/{fileName}/{ids}")
	@ResponseBody
	public void download(@PathVariable("fileName") String fileName, @PathVariable("ids") String ids,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
			ServletOutputStream out = null;
		try {
			response.reset();
			String userAgent = request.getHeader("User-Agent");
			byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8"); // fileName.getBytes("UTF-")处理safari的乱码问题
			String fileNames = new String(bytes, "ISO8859-1");
			// 设置输出的格式
			response.setContentType("multipart/form-data");
			response.setHeader("Content-Disposition", "attachment;fileName=" + fileNames);
			Atth atth = service.selectOne(ids);
			byte[] contents = atth.getAtthFile();
			out = response.getOutputStream();
			// 写到输出流
			out.write(contents);
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
