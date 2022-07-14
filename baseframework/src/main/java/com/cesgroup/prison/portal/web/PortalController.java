package com.cesgroup.prison.portal.web;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.prison.common.bean.user.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.springmvc.web.BaseController;
import com.cesgroup.framework.util.DateUtil;
import com.cesgroup.prison.common.facade.AuthSystemFacade;

/**
 * Description: 平台门户首页
 * @author lincoln.cheng
 *
 * 2019年1月13日
 */
@Controller
@RequestMapping(value = "/portal")
public class PortalController extends BaseController{
	
	/**
	 * Description: 综合首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/zhshouye")
	public ModelAndView zhshouye(HttpServletRequest request, HttpServletResponse response){
		
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w < 0) {
        	w = 0;
        }
            
		request.setAttribute("dqrq", DateUtil.getTodayString("yyyy年MM月dd日") + "<br />" + weekDays[w]);
		ModelAndView mv = new ModelAndView("portal/bj/zhshouye");
		return mv;
	}
	
	/**
	 * Description: 指挥安防首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/")
	public ModelAndView bjshouye(HttpServletRequest request, HttpServletResponse response){
		
		//int level = AuthSystemFacade.whatLevelForLoginUser();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		int level  =Integer.parseInt(user.getUserLevel().toString());
		ModelAndView mv = null;
		
		if(level == 1) {
			mv = new ModelAndView("portal/bj/sjshouye");
		} else if(level == 2) {
			mv = new ModelAndView("portal/bj/jyshouye");
		} else {
			mv = new ModelAndView("portal/bj/jqshouye");
		}
		
		return mv;
	}
	
	/**
	 * Description: 安全风险研判子系统
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/aqfxyp/shouye")
	public ModelAndView aqfxypshouye(HttpServletRequest request, HttpServletResponse response){
		
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w < 0) {
        	w = 0;
        }
		UserBean user=AuthSystemFacade.getLoginUserInfo();
		request.setAttribute("dqrq", DateUtil.getTodayString("yyyy年MM月dd日") + "<br />" + weekDays[w]);
		ModelAndView mv=null;
        if("1105".equals(user.getCusNumber())){
			 mv = new ModelAndView("portal/bj/aqfxypshouye");
		}else{
			 mv = new ModelAndView("portal/bj/aqfxypshouye_qtjy");
		}
		//mv = new ModelAndView("portal/bj/aqfxypshouye_qtjy");
		return mv;
	}
	
	/**
	 * Description: 指挥调度首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/shouye")
	public ModelAndView shouye(HttpServletRequest request, HttpServletResponse response){
		
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w < 0) {
        	w = 0;
        }
            
		request.setAttribute("dqrq", DateUtil.getTodayString("yyyy年MM月dd日") + "<br />" + weekDays[w]);
		ModelAndView mv = new ModelAndView("portal/shouye");
		return mv;
	}
	
	/**
	 * Description: 语音识别首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/yysb/shouye")
	public ModelAndView yysbshouye(HttpServletRequest request, HttpServletResponse response){
		
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w < 0) {
        	w = 0;
        }
            
		request.setAttribute("dqrq", DateUtil.getTodayString("yyyy年MM月dd日") + "<br />" + weekDays[w]);
		ModelAndView mv = new ModelAndView("portal/bj/yysbshouye");
		return mv;
	}
	
	/**
	 * Description: 语音识别-实时监控
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/yysb/ssjk")
	public ModelAndView yysbssjk(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = new ModelAndView("portal/bj/yysbssjk");
		return mv;
	}
	
	/**
	 * Description: 语音识别-历史记录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/yysb/lsjl")
	public ModelAndView yysblsjl(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = new ModelAndView("portal/bj/yysblsjl");
		return mv;
	}
	
	/**
	 * Description: 语音识别-敏感词分析
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/yysb/mgcfx")
	public ModelAndView yysbmgcfx(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = new ModelAndView("portal/bj/yysbmgcfx");
		return mv;
	}
	
	/**
	 * Description: 语音识别-敏感词分析查看详细
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/yysb/mgcfxxx")
	public ModelAndView yysbmgcfxxx(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = new ModelAndView("portal/bj/yysbmgcfxxx");
		return mv;
	}
	
	/**
	 * Description: 民警信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bj/mjxx")
	public ModelAndView mjxx(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = new ModelAndView("portal/bj/mjxx");
		return mv;
	}
	
	/**
	 * Description: 罪犯信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bj/zfxx")
	public ModelAndView zfxx(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = new ModelAndView("portal/bj/zfxx");
		return mv;
	}
	
	/**
	 * Description: 接报系统首页
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/jbxt/shouye")
	public ModelAndView jbxtshouye(HttpServletRequest request, HttpServletResponse response){
		
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w < 0) {
        	w = 0;
        }
            
		request.setAttribute("dqrq", DateUtil.getTodayString("yyyy年MM月dd日") + "<br />" + weekDays[w]);
		ModelAndView mv = new ModelAndView("portal/bj/jbxtshouye");
		return mv;
	}
	
	/**
	 * Description: 接报系统首页-任务管理
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/jbxt/rwgl")
	public ModelAndView jbxtrwgl(HttpServletRequest request, HttpServletResponse response){
		
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w < 0) {
        	w = 0;
        }
            
		request.setAttribute("dqrq", DateUtil.getTodayString("yyyy年MM月dd日") + "<br />" + weekDays[w]);
		ModelAndView mv = new ModelAndView("portal/bj/jbxtrwgl");
		return mv;
	}
	
	/**
	 * Description: 接报系统首页-日常值守
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/jbxt/rczs")
	public ModelAndView jbxtrczs(HttpServletRequest request, HttpServletResponse response){
		
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w < 0) {
        	w = 0;
        }
            
		request.setAttribute("dqrq", DateUtil.getTodayString("yyyy年MM月dd日") + "<br />" + weekDays[w]);
		ModelAndView mv = new ModelAndView("portal/bj/jbxtrczs");
		return mv;
	}
	
	//二维图层add by zk
	@RequestMapping("/planeMap")
	public ModelAndView planeMap(){
		ModelAndView mv = new ModelAndView("portal/map/planeMap");
		return mv;
	}
	
	@RequestMapping("/provMap")
	public ModelAndView provMap(){
		ModelAndView mv = new ModelAndView("portal/map/provMap");
		return mv;
	}
	@RequestMapping("/prisMap")
	public ModelAndView prisMap(){
		ModelAndView mv = new ModelAndView("portal/map/prisMap");
		return mv;
	}
	
	
	@RequestMapping("/index")
	public ModelAndView index(){
		ModelAndView mv = new ModelAndView("portal/index");
		return mv;
	}
	
	@RequestMapping("/prisonmap")
	public ModelAndView prisonmap(){
		ModelAndView mv = new ModelAndView("portal/prisonmap");
		return mv;
	}
	
	
	@RequestMapping("/map")
	public ModelAndView map(){
		ModelAndView mv = new ModelAndView("portal/map");
		return mv;
	}
	
	
	@RequestMapping("/socket")
	public ModelAndView socket(){
		ModelAndView mv = new ModelAndView("portal/socket");
		return mv;
	}
	
	
	
	@RequestMapping("/dialog")
	public ModelAndView dialog(){
		ModelAndView mv = new ModelAndView("portal/dialog");
		return mv;
	}
	
	//Begin add by linhe for 3d map manage 2017-11-30
	@RequestMapping("/editMapView")
	public ModelAndView editMapView(){
		ModelAndView mv = new ModelAndView("portal/editMapView");
		return mv;
	}
	@RequestMapping("/regionTree")
	public ModelAndView regionTree(){
		ModelAndView mv = new ModelAndView("portal/regionTree");
		return mv;
	}
	@RequestMapping("/swxs")
	public ModelAndView swxs(){
		ModelAndView mv = new ModelAndView("portal/swxs");
		return mv;
	}
	@RequestMapping("/prisonMapInfo")
	public ModelAndView prisonMapInfo(){
		ModelAndView mv = new ModelAndView("portal/prisonMapInfo");
		return mv;
	}
	@RequestMapping("/provinceMapInfo")
	public ModelAndView provinceMapInfo(){
		ModelAndView mv = new ModelAndView("portal/provinceMapInfo");
		return mv;
	}
	//End add by linhe for 3d map manage 2017-11-30
	/**
	 * Description: 罪犯质量考评
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/bj/zfzlkp")
	public ModelAndView zfzlkp(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = null;
		mv = new ModelAndView("portal/bj/zfzlkp");
		
		return mv;
	}
	
	@RequestMapping("/sjfxyp/ryglfxfx")
	public ModelAndView ryglfxfx(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = null;
		mv = new ModelAndView("portal/sjfxyp/ryglfxfx");
		
		return mv;
	}
	
	@RequestMapping("/sjfxyp/ryglyffx")
	public ModelAndView ryglyffx(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = null;
		mv = new ModelAndView("portal/sjfxyp/yffx");
		
		return mv;
	}
	@RequestMapping("/sjfxyp/sbyxqk")
	public ModelAndView sbyxqk(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = null;
		mv = new ModelAndView("portal/sjfxyp/sbyxqk");
		
		return mv;
	}
	@RequestMapping("/sjfxyp/yqyjpg")
	public ModelAndView yqyjpg(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = null;
		mv = new ModelAndView("portal/sjfxyp/yqyjpg");
		
		return mv;
	}
	
	
	@RequestMapping("/wghgl")
	public ModelAndView openwggl(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = null;
		mv = new ModelAndView("portal/bj/wghgl");
		
		return mv;
	}
	@RequestMapping("/djwg")
	public ModelAndView opendjwg(HttpServletRequest request, HttpServletResponse response){
		
		ModelAndView mv = null;
		mv = new ModelAndView("portal/bj/djwg");
		
		return mv;
	}
	
	/**
	 * Description: 值排班页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/zpbgl/shouye")
	public ModelAndView zpbglshouye(HttpServletRequest request, HttpServletResponse response){
		
		String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w < 0) {
        	w = 0;
        }
		UserBean user=AuthSystemFacade.getLoginUserInfo();
		request.setAttribute("dqrq", DateUtil.getTodayString("yyyy年MM月dd日") + "<br />" + weekDays[w]);
		ModelAndView mv=null;
		mv = new ModelAndView("portal/bj/zpbglshouye");
 		return mv;
	}
	
	
	/**
	 * Description: 通讯调度系统
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/txdd/shouye")
	public ModelAndView txddxt(HttpServletRequest request, HttpServletResponse response){
	    setDateWeek(request);
		ModelAndView mv=null;
		mv = new ModelAndView("portal/bj/txddshouye");
 		return mv;
	}

	/**
	 * Description: 视频监控
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/spjk/shouye")
	public ModelAndView spjk(HttpServletRequest request, HttpServletResponse response){
	    setDateWeek(request);
		ModelAndView mv=null;
		mv = new ModelAndView("portal/spjkshouye");
 		return mv;
	}
	
	/**
	 * Description: 警情处置
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/jqcz/shouye")
	public ModelAndView jqcz(HttpServletRequest request, HttpServletResponse response){
	    setDateWeek(request);
		ModelAndView mv=null;
		mv = new ModelAndView("portal/jqczshouye");
 		return mv;
	}

	/**
	 * Description: 警情处置
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/aqfh/shouye")
	public ModelAndView aqfh(HttpServletRequest request, HttpServletResponse response){
	    setDateWeek(request);
		ModelAndView mv=null;
		mv = new ModelAndView("portal/aqfhshouye");
 		return mv;
	}
	
	
    /**
     * Description: 系统集成首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/xtjc/shouye")
    public ModelAndView xtjc(HttpServletRequest request, HttpServletResponse response){
        setDateWeek(request);
        ModelAndView mv=null;
        mv = new ModelAndView("portal/bj/xtjcshouye");
        return mv;
    }
    
    
    /**
     * Description: 日志管理首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/rzgl/shouye")
    public ModelAndView rzgl(HttpServletRequest request, HttpServletResponse response){
        setDateWeek(request);
        ModelAndView mv=null;
        mv = new ModelAndView("portal/bj/rzglshouye");
        return mv;
    }

    /**
     * Description: 信息汇聚首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/xxhj/shouye")
    public ModelAndView openXxhj(HttpServletRequest request, HttpServletResponse response){
        setDateWeek(request);
        ModelAndView mv=null;
        mv = new ModelAndView("portal/bj/xxhjshouye");
        return mv;
    }

    /**
     * Description: 信息维护首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/xxwh/shouye")
    public ModelAndView openXxwh(HttpServletRequest request, HttpServletResponse response){
        setDateWeek(request);
        ModelAndView mv=null;
        mv = new ModelAndView("portal/xxwhshouye");
        return mv;
    }


    /**
     * Description: 信息研判首页
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/xxyp/shouye")
    public ModelAndView openXxyp(HttpServletRequest request, HttpServletResponse response){
        setDateWeek(request);
        ModelAndView mv=null;
        mv = new ModelAndView("portal/bj/xxypshouye");
        return mv;
    }


    private static void setDateWeek(HttpServletRequest request){
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if(w < 0) {
            w = 0;
        }
        UserBean user=AuthSystemFacade.getLoginUserInfo();
        request.setAttribute("dqrq", DateUtil.getTodayString("yyyy年MM月dd日") + "<br />" + weekDays[w]);
    }
}
