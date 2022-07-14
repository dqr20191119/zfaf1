package com.cesgroup.prison.zfxx.zfjzzysj.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zfjzzysj.dao.ZfJzzySjDao;
import com.cesgroup.prison.zfxx.zfjzzysj.entity.ZfJzzySj;
import com.cesgroup.prison.zfxx.zfjzzysj.service.ZfJzzySjService;
/**
 * Description: 罪犯收监情况-狱外就诊/住院 数据访问接口
 * @author monkey
 *
 * 2019年3月5日
 */

@Controller
@RequestMapping("/zfxx/zfJzzySj")
public class ZfJzzySjController  extends BaseEntityDaoServiceCRUDController<ZfJzzySj,String,ZfJzzySjDao,ZfJzzySjService> {
	/**
	 * Description: 统计狱外就医数据信息数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfJzzySj")
    @ResponseBody
	public AjaxResult synchroZfJzzySj() {
		
		try {
			//this.getService().synchroZfJzzySj();
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error("罪犯收监情况-狱外就诊/住院数据发生异常");
		}
	}
}
