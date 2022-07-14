package com.cesgroup.prison.zfxx.zftg.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zftg.dao.ZfTgDao;
import com.cesgroup.prison.zfxx.zftg.entity.ZfTg;
import com.cesgroup.prison.zfxx.zftg.service.ZfTgService;

/**
 * Description: 罪犯  特管等级 控制器
 * @author monkey
 *
 * 2019年3月3日
 */

@Controller
@RequestMapping("/zfxx/zfTg")
public class ZfTgController  extends BaseEntityDaoServiceCRUDController<ZfTg,String,ZfTgDao,ZfTgService>{
	/**
	 * Description: 统计罪犯老病残信息数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfTg")
    @ResponseBody
	public AjaxResult synchroZfRzfp() {
		
		try {
			//this.getService().synchroZfTg();
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error("罪犯特管等级数据发生异常");
		}
	}
}
