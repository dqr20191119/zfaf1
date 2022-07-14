package com.cesgroup.prison.zfxx.zfjyzf.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zfjyzf.dao.ZfJyzfDao;
import com.cesgroup.prison.zfxx.zfjyzf.entity.ZfJyzf;
import com.cesgroup.prison.zfxx.zfjyzf.service.ZfJyzfService;

/**
 * Description: 罪犯狱外寄押 数据访问接口
 * @author monkey
 *
 * 2019年3月5日
 */

@Controller
@RequestMapping("/zfxx/zfJyzf")
public class ZfJyzfController  extends BaseEntityDaoServiceCRUDController<ZfJyzf,String,ZfJyzfDao,ZfJyzfService> {
	/**
	 * Description: 统计狱外就医数据信息数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfJyzf")
    @ResponseBody
	public AjaxResult synchroZfJyzf() {
		
		try {
			//this.getService().synchroZfJyzf();
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error("罪犯狱外寄押数据发生异常");
		}
	}
}
