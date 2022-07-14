package com.cesgroup.prison.xxhj.sy.web;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.xxhj.sy.dao.SyMapper;
import com.cesgroup.prison.xxhj.sy.entity.Sy;
import com.cesgroup.prison.xxhj.sy.service.SyService;

/**
 * Description: 首页控制器
 * @author lincoln.cheng
 *
 */
@Controller
@RequestMapping(value = "/xxhj/sy")
public class SyController extends BaseEntityDaoServiceCRUDController<Sy, String, SyMapper, SyService> {

	@RequestMapping("/index")
	public ModelAndView index() {
		
		ModelAndView mv = new ModelAndView("xxhj/sy/sy");
		return mv;
	}

    /**
     * 统计首页数据
     * @param cusNumber
     * @param cdjJobCode
     * @return
     */
    @RequestMapping(value ="/countSyCount")
	public Map<String, Object> countHomeMeunCount(String cusNumber,String cdjJobCode) {
    	
    	Map<String, Object> list = this.getService().countHomeMeunCount(cusNumber,cdjJobCode);
    	return  list;
	}
}