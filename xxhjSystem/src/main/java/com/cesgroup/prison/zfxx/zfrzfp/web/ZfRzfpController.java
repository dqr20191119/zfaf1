package com.cesgroup.prison.zfxx.zfrzfp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zfrzfp.dao.ZfRzfpDao;
import com.cesgroup.prison.zfxx.zfrzfp.entity.ZfRzfp;
import com.cesgroup.prison.zfxx.zfrzfp.service.ZfRzfpService;

/**
 * Description: 罪犯认罪服判 控制器
 * @author monkey
 *
 * 2019年3月3日
 */

@Controller
@RequestMapping("/zfxx/zfRzfp")
public class ZfRzfpController   extends BaseEntityDaoServiceCRUDController<ZfRzfp,String,ZfRzfpDao,ZfRzfpService>{
	/**
	 * Description: 统计罪犯老病残信息数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfRzfp")
    @ResponseBody
	public AjaxResult synchroZfRzfp() {
		
		try {
			//this.getService().synchroZfRzfp();
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error("罪犯认罪服判类别数据发生异常");
		}
	}
}
