package com.cesgroup.prison.aqfk.monitor.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.prison.aqfk.monitor.entity.Evidence;

public interface IEvidenceService {
	public Page<Map<String, String>> listEvidence(Evidence evidence_param,String startTime,String endTime,Pageable pageable);
	//局部修改
	public void updatePart(Evidence evidence_param);
	public Map<String, Object> addEvidence(HttpServletRequest request);
	public Map<String, Object> searchVideo(String id);
	public Map<String, Object> searchImage(String id);
	//批量删除
	public int batchDelete(List<String> ids);
	/*
	 * 更新FTP上的图片，把原始图片重命名并上传修改后的图片
	 * @param fileName 包含路径
	*/
	public AjaxMessage updateFtpImg(String cusNumber,String fileName,String imgBase64);

    Evidence getNowEvidence();
}
