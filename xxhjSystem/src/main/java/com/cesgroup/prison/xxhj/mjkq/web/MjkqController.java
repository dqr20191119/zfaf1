package com.cesgroup.prison.xxhj.mjkq.web;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xxhj.mjkq.dao.MjkqMapper;
import com.cesgroup.prison.xxhj.mjkq.entity.MjkqEntity;
import com.cesgroup.prison.xxhj.mjkq.service.MjkqService;
/**
 * Description: 民警考勤查询 控制器
 * 
 * @author monkey
 * 
 * 2019年03月14日
 */
@Controller
@RequestMapping("/xxhj/mjkq")
public class MjkqController extends BaseEntityDaoServiceCRUDController<MjkqEntity, String, MjkqMapper, MjkqService>{
	/**
	 * 跳转至 民警考勤查询 记录
	 * @return
	 */
    @RequestMapping(value = "/toIndex")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("/xxhj/mjkq/index");
        return mv;
    }
    
    
    /**
     * 分页查询罪犯巡视点名记录
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    @Logger(action = "查询", logger = "分页 查询民警考勤 记录")
    public Map<String, Object> search(
    		@RequestParam(value="xm", defaultValue="", required=false) String xm,
    		@RequestParam(value="kqrq", defaultValue="", required=false) String kqrq) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("xm", xm);
        param.put("kqrq", kqrq);
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		param.put("jyId", user.getOrgCode());	
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = this.getService().queryWithPage(param, pageRequest);
        return DataUtils.pageToMap(page);
    }
    
    @RequestMapping(value = "/synchroYyjyZzjg")
    @ResponseBody
	public AjaxResult synchroYyjyZzjg() {
		try {
			System.out.println("===========synchroYyjyZzjg===========");
			this.getService().synchroYyjyZzjg(null);
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("synchroYyjyZzjg error");
		}
	}
    
    @RequestMapping(value = "/synchroYyjyDrKqjl")
    @ResponseBody
	public AjaxResult synchroYyjyDrKqjl() {
		try {
			System.out.println("===========synchroYyjyDrKqjl===========");
			Calendar cal = Calendar.getInstance();
			cal.setTime(new Date());
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < 3; i++) {
				map.put("date", Util.DF_DATE.format(cal.getTime()));
				this.getService().synchroYyjyDrKqjl(map);
				cal.add(Calendar.DATE, -1);
			}
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("synchroYyjyDrKqjl error");
		}
	}
    
}
