package com.cesgroup.prison.zfxx.zfzyjwzx.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zfzyjwzx.dao.ZfZyjwzxDao;
import com.cesgroup.prison.zfxx.zfzyjwzx.entity.ZfZyjwzx;
import com.cesgroup.prison.zfxx.zfzyjwzx.service.ZfZyjwzxService;

/**
 * Description: 暂予监外执行 数据访问接口
 * @author monkey
 *
 * 2019年3月5日
 */

@Controller
@RequestMapping("/zfxx/zfZyjwzx")
public class ZfZyjwzxController extends BaseEntityDaoServiceCRUDController<ZfZyjwzx,String,ZfZyjwzxDao,ZfZyjwzxService> {
	/**
	 * Description: 统计狱外就医数据信息数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfZyjwzx")
    @ResponseBody
	public AjaxResult synchroZfZyjwzx() {
		
		try {
			//this.getService().synchroZfZyjwzx();
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error("暂予监外执行数据发生异常");
		}
	}
}
