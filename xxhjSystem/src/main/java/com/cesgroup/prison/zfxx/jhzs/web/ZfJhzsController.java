package com.cesgroup.prison.zfxx.jhzs.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.jhzs.dao.ZfJhzsDao;
import com.cesgroup.prison.zfxx.jhzs.entity.ZfJhzs;
import com.cesgroup.prison.zfxx.jhzs.service.ZfJhzsService;

/**
 * Description: 罪犯 解回再审 数据访问接口
 * @author monkey
 *
 * 2019年3月5日
 */

@Controller
@RequestMapping("/zfxx/zfJhzs")
public class ZfJhzsController  extends BaseEntityDaoServiceCRUDController<ZfJhzs,String,ZfJhzsDao,ZfJhzsService>{
	/**
	 * Description: 统计 解回再审 数据信息数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfJhzs")
    @ResponseBody
	public AjaxResult synchroZfJhzs() {
		
		try {
			//this.getService().synchroZfJhzs();
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error("罪犯狱外就医数据发生异常");
		}
	}
}
