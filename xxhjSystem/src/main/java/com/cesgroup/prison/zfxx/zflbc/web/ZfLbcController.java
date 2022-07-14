package com.cesgroup.prison.zfxx.zflbc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zflbc.dao.ZfLbcDao;
import com.cesgroup.prison.zfxx.zflbc.entity.ZfLbc;
import com.cesgroup.prison.zfxx.zflbc.service.ZfLbcService;


/**
 * Description: 罪犯老病残 控制器
 * @author monkey
 *
 * 2019年2月28日
 */

@Controller
@RequestMapping("/zfxx/zfLbc")
public class ZfLbcController  extends BaseEntityDaoServiceCRUDController<ZfLbc,String,ZfLbcDao,ZfLbcService> {
	/**
	 * Description: 统计罪犯老病残信息数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfLbc")
    @ResponseBody
	public AjaxResult synchroZfLbc() {
		
		try {
			//this.getService().synchroZfLbc();
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error("罪犯老病残数据发生异常");
		}
	}
}
