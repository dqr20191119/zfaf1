package com.cesgroup.prison.szbb.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.httpclient.util.SzbbUtil;
import com.cesgroup.prison.szbb.dao.RcgkDao;
import com.cesgroup.prison.szbb.entity.Rcgk;
import com.cesgroup.prison.szbb.service.RcgkService;

/**
 * 数字冰雹数据对接Controller
 * 1.日常管控 
 * @author zhou.jian
 * @date 2019-2-28
 */
@Controller
@RequestMapping("/zfxx/szbb/rcgk")
public class RcgkController extends BaseEntityDaoServiceCRUDController<Rcgk,String,RcgkDao,RcgkService>{

	
	/**
	 * 数字冰雹：1.日常管控-今日值班 
	 * @return
	 */
	@RequestMapping(value = "/pushJrzb")
    @ResponseBody
	public AjaxResult pushJrzb() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getJrzbList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【1.日常管控-今日值班】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：2.日常管控-今日概况 
	 * @return
	 */
	@RequestMapping(value = "/pushJrgk")
    @ResponseBody
	public AjaxResult pushJrgk() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getJrgk(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【2.日常管控-今日管控】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：3.日常管控-区域管控
	 * @return
	 */
	@RequestMapping(value = "/pushQygk")
    @ResponseBody
	public AjaxResult pushQygk() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getQygkList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【3.日常管控-区域管控】数据发生异常！！！");
		}
	}
	
	
	/**
	 * 数字冰雹：4.日常管控-重点罪犯
	 * @return
	 */
	@RequestMapping(value = "/pushZdzf")
    @ResponseBody
	public AjaxResult pushZdzf() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getZdzfList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【4.日常管控-重点罪犯】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：5.日常管控-大门出入
	 * @return
	 */
	@RequestMapping(value = "/pushDmcr")
	@ResponseBody
	public AjaxResult pushDmcr() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getDmcrList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【5.日常管控-大门出入】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：6.日常管控-人脸识别
	 * @return
	 */
	@RequestMapping(value = "/pushRlsb")
	@ResponseBody
	public AjaxResult pushRlsb() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getRlsbList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【6.日常管控-人脸识别】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：7.日常管控-周界安防
	 * @return
	 */
	@RequestMapping(value = "/pushZjaf")
	@ResponseBody
	public AjaxResult pushZjaf() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getZjafList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【7.日常管控-周界安防】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：8.日常管控-手机管控
	 * @return
	 */
	@RequestMapping(value = "/pushSjgk")
	@ResponseBody
	public AjaxResult pushSjgk() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getSjgkList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【8.日常管控-手机管控】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：9.日常管控-押解任务 
	 * @return
	 */
	@RequestMapping(value = "/pushYwyjrw")
	@ResponseBody
	public AjaxResult pushYwyjrw() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getYwyjrwList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【9.日常管控-押解任务】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：10.日常管控-押解车辆 
	 * @return
	 */
	@RequestMapping(value = "/pushYwyjclwz")
	@ResponseBody
	public AjaxResult pushYwyjclwz() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getYwyjclwzList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【10.日常管控-押解车辆】数据发生异常！！！");
		}
	}
	
	
}
