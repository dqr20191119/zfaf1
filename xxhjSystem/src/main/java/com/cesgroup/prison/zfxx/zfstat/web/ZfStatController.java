package com.cesgroup.prison.zfxx.zfstat.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.jobs.service.BjJobService;
import com.cesgroup.prison.zfxx.zfstat.dao.ZfStatDao;
import com.cesgroup.prison.zfxx.zfstat.entity.ZfStat;
import com.cesgroup.prison.zfxx.zfstat.service.ZfStatService;


/**
 * Description: 统计数据访问控制器
 * @author zhou.jian
 *
 * 2019年2月20日
 */
@Controller
@RequestMapping("/zfxx/zfStat")
public class ZfStatController extends BaseEntityDaoServiceCRUDController<ZfStat,String,ZfStatDao,ZfStatService>{

	@Autowired
	private BjJobService bjJobService;
	
	/**
	 * Description: 统计罪犯信息数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfStat")
    @ResponseBody
	public AjaxResult synchroZfStat() {
		
		try {
			System.out.println("=====>start synchroZfStat");
			//this.getService().synchroZfStat();
			bjJobService.getHY();
			System.out.println("=====>end synchroZfStat");
			return AjaxResult.success();
		} catch (Exception e) {
			return AjaxResult.error("统计罪犯数据发生异常");
		}
	}
	
}
