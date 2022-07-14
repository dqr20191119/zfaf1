package com.cesgroup.prison.zfxx.zfstat.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zfstat.dao.ZfStatZyDao;
import com.cesgroup.prison.zfxx.zfstat.entity.ZfStatZy;
import com.cesgroup.prison.zfxx.zfstat.service.ZfStatZyService;

/**
 * Description: 统计暂押访问控制器
 * @author zhou.jian
 *
 * 2019年2月21日
 */
@Controller
@RequestMapping("/zfxx/zfStatZy")
public class ZfStatZyController extends BaseEntityDaoServiceCRUDController<ZfStatZy,String,ZfStatZyDao,ZfStatZyService>{

	
	/**
	 * Description: 2.统计【暂押罪犯】信息数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfStatZy")
    @ResponseBody
	public AjaxResult synchroZfStatZy() {
		
		try {
			//this.getService().synchroZfStatZy();
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error("统计【暂押罪犯】数据发生异常");
		}
	}
    
    /**
     * Description: 3.统计【罪犯调动】信息数据
     * @return
     */
    @RequestMapping(value = "/synchroZfStatZfdd")
    @ResponseBody
    public AjaxResult synchroZfStatZfdd() {
    	
    	try {
    		//this.getService().synchroZfStatZfdd();
    		return AjaxResult.success();
    	} catch (Exception e) {
    		return AjaxResult.error("统计【罪犯调动】数据发生异常");
    	}
    }
	
    
    /**
	 * Description: 4.统计【在押罪犯】信息数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfStatZyqk")
    @ResponseBody
	public AjaxResult synchroZfStatZyqk() {
		
		try {
			//this.getService().synchroZfStatZyqk();
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error("统计【在押罪犯】数据发生异常");
		}
	}
    
    /**
     * Description: 5.统计【性别罪犯】信息数据
     * @return
     */
    @RequestMapping(value = "/synchroZfStatZyXb")
    @ResponseBody
    public AjaxResult synchroZfStatZyXb() {
    	
    	try {
    	//	this.getService().synchroZfStatZyXb();
    		return AjaxResult.success();
    	} catch (Exception e) {
    		return AjaxResult.error("统计【性别罪犯】数据发生异常");
    	}
    }
    
    /**
     * Description: 6.统计【罪犯年龄】信息数据
     * @return
     */
    @RequestMapping(value = "/synchroZfStatZyNl")
    @ResponseBody
    public AjaxResult synchroZfStatZyNl() {
    	
    	try {
    		//this.getService().synchroZfStatZyNl();
    		return AjaxResult.success();
    	} catch (Exception e) {
    		return AjaxResult.error("统计【罪犯年龄】数据发生异常");
    	}
    }
    
    /**
     * Description: 7.统计【罪犯罪名】信息数据
     * @return
     */
    @RequestMapping(value = "/synchroZfStatZyZm")
    @ResponseBody
    public AjaxResult synchroZfStatZyZm() {
    	
    	try {
    		//this.getService().synchroZfStatZyZm();
    		return AjaxResult.success();
    	} catch (Exception e) {
    		return AjaxResult.error("统计【罪犯罪名】数据发生异常");
    	}
    }
    
    /**
     * Description: 8.统计【罪犯原判刑期】信息数据
     * @return
     */
    @RequestMapping(value = "/synchroZfStatZyYpxq")
    @ResponseBody
    public AjaxResult synchroZfStatZyYpxq() {
    	
    	try {
    		//this.getService().synchroZfStatZyYpxq();
    		return AjaxResult.success();
    	} catch (Exception e) {
    		return AjaxResult.error("统计【原判刑期】数据发生异常");
    	}
    }
    
    /**
     * Description: 9.统计【罪犯剩余刑期】信息数据
     * @return
     */
    @RequestMapping(value = "/synchroZfStatZySyxq")
    @ResponseBody
    public AjaxResult synchroZfStatZySyxq() {
    	
    	try {
    		//this.getService().synchroZfStatZySyxq();
    		return AjaxResult.success();
    	} catch (Exception e) {
    		return AjaxResult.error("统计【剩余刑期】数据发生异常");
    	}
    }
    
    /**
     * Description: 10.统计【监狱关押能力】信息数据
     * @return
     */
    @RequestMapping(value = "/synchroZfStatZyJyGynl")
    @ResponseBody
    public AjaxResult synchroZfStatZyJyGynl() {
    	
    	try {
    		//this.getService().synchroZfStatZyJyGynl();
    		return AjaxResult.success();
    	} catch (Exception e) {
    		return AjaxResult.error("统计【监狱关押能力】数据发生异常");
    	}
    }
    
    /**
     * Description: 11.统计【各监狱关押人数】信息数据
     * @return
     */
    @RequestMapping(value = "/synchroZfStatZyJygyqk")
    @ResponseBody
    public AjaxResult synchroZfStatZyJygyqk() {
    	
    	try {
    		//this.getService().synchroZfStatZyJygyqk();
    		return AjaxResult.success();
    	} catch (Exception e) {
    		return AjaxResult.error("统计【各监狱关押人数】数据发生异常");
    	}
    }
    
    /**
     * Description: 12.统计【各监狱改造情况】信息数据
     * @return
     */
    @RequestMapping(value = "/synchroZfStatZyJygzqk")
    @ResponseBody
    public AjaxResult synchroZfStatZyJygzqk() {
    	
    	try {
    		//this.getService().synchroZfStatZyJygzqk();
    		return AjaxResult.success();
    	} catch (Exception e) {
    		return AjaxResult.error("统计【各监狱改造情况】数据发生异常");
    	}
    }
    
}
