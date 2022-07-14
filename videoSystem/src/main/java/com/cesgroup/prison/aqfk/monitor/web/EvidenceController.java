package com.cesgroup.prison.aqfk.monitor.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.framework.dto.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.aqfk.monitor.dao.EvidenceMapper;
import com.cesgroup.prison.aqfk.monitor.entity.Evidence;
import com.cesgroup.prison.aqfk.monitor.service.IEvidenceService;
import com.cesgroup.prison.aqfk.monitor.service.impl.EvidenceServiceImpl;
import com.cesgroup.prison.utils.DataUtils;

@Controller
@RequestMapping(value = "/evidence")
public class EvidenceController extends BaseEntityDaoServiceCRUDController<Evidence,String,EvidenceMapper,EvidenceServiceImpl>{
	@Autowired
	private IEvidenceService evidenceService;
	
	/**
	* @methodName: searchEvidence
	* @Description: 证据信息列表查询
	* @param pageable
	* @return Map<String,Object>    
	* @throws
	*/
	@RequestMapping(value = "/searchEvidence")
	@ResponseBody
	@Logger(action = "查询方法", logger = "查询列表")
	public Map<String, Object> searchEvidence(Evidence evidence_param,String startTime,String endTime,Pageable pageable) {
		Page<Map<String, String>> page = evidenceService.listEvidence(evidence_param,startTime,endTime,pageable);
		return DataUtils.pageToMap(page);
	}
	
	@RequestMapping(value = "/add")
	@ResponseBody
	public Map<String, Object> addEvidence(HttpServletRequest request) {
		return evidenceService.addEvidence(request);
	}
	//局部修改
	@RequestMapping(value = "/updatePart")
	@ResponseBody
	public AjaxMessage updatePart(Evidence evidence_param){
		try {
			evidenceService.updatePart(evidence_param);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
	}
	
	@RequestMapping(value = "/searchVideo")
	@ResponseBody
	public Map<String, Object> searchVideo(String id) {
		return evidenceService.searchVideo(id);
	}
	
	@RequestMapping(value = "/searchImage")
	@ResponseBody
	public Map<String, Object> searchImage(String id) {
		return evidenceService.searchImage(id);
	}
	
	@RequestMapping("/batchDelete")
	@ResponseBody
	public AjaxMessage batchDelete(@RequestBody List<String> ids){
		try {
			this.evidenceService.batchDelete(ids);
			return new AjaxMessage(true);
		} catch (Exception e) {
			return new AjaxMessage(false,null,e.getMessage());
		}
		
	}
	/*
	 * 更新FTP上的图片，把原始图片重命名并上传修改后的图片
	 * @param fileName 包含路径
	*/
	@RequestMapping(value = "/updateFtpImg")
	@ResponseBody
	public AjaxMessage updateFtpImg(String cusNumber,String fileName,String imgBase64){
		return evidenceService.updateFtpImg(cusNumber,fileName,imgBase64);
	}
	
	@RequestMapping("/editImgEvidenceDialog")
	public ModelAndView editImgEvidenceDialog(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mv = new ModelAndView("common/editImgEvidence");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}

    /**
     * @methodName: getNowEvidence
     * @Description: 获取最新的一条事件类型记录
     * @return AjaxResult
     * @throws
     */
    @RequestMapping("/getNowEvidence")
    @ResponseBody
    public AjaxResult getNowEvidence(){
        try {
            Evidence evidence = this.service.getNowEvidence();
            return AjaxResult.success(evidence);
        }catch (Exception e){
            e.printStackTrace();
           return AjaxResult.error(e.getMessage());
        }
    }


    /**
     * @methodName: getNowEvidence
     * @Description: 获取最新的一条事件类型记录
     * @return AjaxResult
     * @throws
     */
    @RequestMapping("/getEvidenceById")
    @ResponseBody
    public AjaxResult getEvidenceById(@RequestParam("id") String id){
        try {
            Evidence evidence = this.service.selectOne(id);
            return AjaxResult.success(evidence);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error(e.getMessage());
        }
    }
}
