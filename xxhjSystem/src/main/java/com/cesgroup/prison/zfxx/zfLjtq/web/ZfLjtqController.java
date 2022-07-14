package com.cesgroup.prison.zfxx.zfLjtq.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.zfxx.zfLjtq.dao.ZfLjtqDao;
import com.cesgroup.prison.zfxx.zfLjtq.entity.ZfLjtq;
import com.cesgroup.prison.zfxx.zfLjtq.service.ZfLjtqService;
/**
 * Description: 罪犯离监探亲 数据访问接口
 * @author monkey
 *
 * 2019年3月5日
 */

@Controller
@RequestMapping("/zfxx/zfLjtq")
public class ZfLjtqController  extends BaseEntityDaoServiceCRUDController<ZfLjtq,String,ZfLjtqDao,ZfLjtqService> {
	/**
	 * Description: 统计离监探亲数据信息数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfLjtq")
    @ResponseBody
	public AjaxResult synchroZfLjtq() {
		
		try {
			//this.getService().synchroZfLjtq();
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error("罪犯离监探亲数据发生异常");
		}
	}
}
