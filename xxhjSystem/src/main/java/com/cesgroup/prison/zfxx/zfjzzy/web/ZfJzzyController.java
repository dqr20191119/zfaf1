package com.cesgroup.prison.zfxx.zfjzzy.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zfjzzy.dao.ZfJzzyDao;
import com.cesgroup.prison.zfxx.zfjzzy.entity.ZfJzzy;
import com.cesgroup.prison.zfxx.zfjzzy.service.ZfJzzyService;
/**
 * Description: 罪犯狱外就医 数据访问接口
 * @author monkey
 *
 * 2019年3月4日
 */

@Controller
@RequestMapping("/zfxx/zfJzzy")
public class ZfJzzyController  extends BaseEntityDaoServiceCRUDController<ZfJzzy,String,ZfJzzyDao,ZfJzzyService> {
	/**
	 * Description: 统计狱外就医数据信息数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfJzzy")
    @ResponseBody
	public AjaxResult synchroZfJzzy() {
		
		try {
			//this.getService().synchroZfJzzy();
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error("罪犯狱外就医数据发生异常");
		}
	}
}
