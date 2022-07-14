package com.cesgroup.prison.jctj.web;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.jctj.dao.JcjlDao;
import com.cesgroup.prison.jctj.entity.JcjlEntity;
import com.cesgroup.prison.jctj.service.JcjlService;
import com.cesgroup.prison.utils.PagerModel;
 
@Controller
@RequestMapping("/jctj/jcjl")
public class JcjlController  extends BaseEntityDaoServiceCRUDController<JcjlEntity,String,JcjlDao,JcjlService>{
	
	@Autowired
	private JcjlService jcjlService;
	
	/**
	 * 查询进出记录
	 * @return
	 */
	@RequestMapping(value = "/getJctj")
	@ResponseBody
	public List<Map<String, Object>> query() {
		List<Map<String, Object>> list = null;
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			//查询当天出入记录
			param.put("time", "today");
			PageRequest pageRequest = buildPageRequest();
			PagerModel pm = jcjlService.getJctj(param, 1, pageRequest.getPageSize());
			list = (List<Map<String, Object>>)pm.getRows();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;				
	}
	
    @RequestMapping(value = "/findByPage")
    @ResponseBody
    public Map<String, Object> findByPage(HttpServletRequest request, HttpServletResponse response) {
    	Map<String, Object> map = new HashMap<String, Object>();
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    		Map<String, Object> param = new HashMap<String, Object>();
        	param.put("zfxm", request.getParameter("zfxm"));
        	String startTime = request.getParameter("startTime");
        	if (startTime != null && !"".equals(startTime)) {
        		param.put("startTime", sdf.parse(startTime));
        	}
        	String endTime = request.getParameter("endTime");
        	if (endTime != null && !"".equals(endTime)) {
        		param.put("endTime", sdf.parse(endTime));
        	}
			PageRequest pageRequest = buildPageRequest();
			PagerModel pm = jcjlService.getJctj(param, pageRequest.getPageNumber() + 1, pageRequest.getPageSize());
			map.put("pageNumber", pageRequest.getPageNumber() + 1);
			map.put("totalPages", pm.getTotalPages());
			map.put("total", pm.getTotal());
			map.put("data", pm.getRows());
		} catch (Exception e) {
			e.printStackTrace();
		}
        return map;
    }
	
	/**
	 * 统计监区和劳动场所罪犯数量
	 * @return
	 */
	@RequestMapping(value = "/getZfszqytj")
	@ResponseBody
	public Map<String, Object> queryJsLdcs() {
		Map<String, Object> map = null;
		try {
			map = jcjlService.getZfszqytj();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;				
	}
}
