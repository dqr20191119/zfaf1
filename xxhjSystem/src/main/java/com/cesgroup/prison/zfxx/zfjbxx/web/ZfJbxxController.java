package com.cesgroup.prison.zfxx.zfjbxx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zfjbxx.dao.ZfJbxxDao;
import com.cesgroup.prison.zfxx.zfjbxx.entity.ZfJbxx;
import com.cesgroup.prison.zfxx.zfjbxx.service.ZfJbxxService;

/**
 * Description: 罪犯基本信息控制器类
 * @author lincoln.cheng
 *
 * 2019年1月14日
 */
@Controller
@RequestMapping("/zfxx/zfJbxx")
public class ZfJbxxController extends BaseEntityDaoServiceCRUDController<ZfJbxx, String, ZfJbxxDao, ZfJbxxService> {
	
    /**
	 * Description: 同步罪犯基本信息数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfJbxx")
    @ResponseBody
	public AjaxResult synchroZfJbxx() {
    	try {
    		// this.getService().synchroZfJbxx();
    		// this.getService().downloadZfPhoto();
    		this.getService().synchroZfJbxxTask();
    		return AjaxResult.success();
    	} catch (Exception e) {
    		return AjaxResult.error("同步罪犯基本信息发生异常");
    	}
	}

    /**
	 * Description: 同步罪犯基本信息数据
	 * @return
	 */
    @RequestMapping(value = "/downloadZfPhoto")
    @ResponseBody
	public AjaxResult downloadZfPhoto(@RequestParam(value="cSzjy", required=false) String cSzjy, 
			@RequestParam(value="cSzjq", required=false) String cSzjq, 
			@RequestParam(value="dUrlTime", required=false) String dUrlTime) {
    	try {
    		// this.getService().synchroZfJbxx();
    		// this.getService().downloadZfPhoto();
    		this.getService().downloadZfPhoto(cSzjy, cSzjq, dUrlTime);
    		return AjaxResult.success();
    	} catch (Exception e) {
    		return AjaxResult.error("下载照片发生异常");
    	}
	}

	/**
	 * 融合通讯页面跳转
	 * @return
	 */
	@RequestMapping(value = "/openDialog")
	public ModelAndView openDialog() {
		ModelAndView mv = new ModelAndView("/zfxx/zfjbxx/zf_photo");
		return mv;
	}

}
