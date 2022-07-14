package com.cesgroup.prison.zfxx.zfzdzf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zflbc.dao.ZfLbcDao;
import com.cesgroup.prison.zfxx.zflbc.entity.ZfLbc;
import com.cesgroup.prison.zfxx.zflbc.service.ZfLbcService;
import com.cesgroup.prison.zfxx.zfzdzf.dao.ZfZdzfDao;
import com.cesgroup.prison.zfxx.zfzdzf.entity.ZfZdzf;
import com.cesgroup.prison.zfxx.zfzdzf.service.ZfZdzfService;

/**
 * Description: 罪犯老病残 控制器
 * @author monkey
 *
 * 2019年3月4日
 */

@Controller
@RequestMapping("/zfxx/zfZdzf")
public class ZfZdzfController   extends BaseEntityDaoServiceCRUDController<ZfZdzf,String,ZfZdzfDao,ZfZdzfService>{
	/**
	 * Description: 统计罪犯重点罪犯数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfZdzf")
    @ResponseBody
	public AjaxResult synchroZfLbc() {
		
		try {
			//this.getService().synchroZfZdzf();
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error("统计重点罪犯数据发生异常");
		}
	}
}
