package com.cesgroup.prison.szbb.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.httpclient.util.SzbbUtil;
import com.cesgroup.prison.szbb.dao.FxpgDao;
import com.cesgroup.prison.szbb.entity.Fxpg;
import com.cesgroup.prison.szbb.service.FxpgService;

/**
 * 数字冰雹数据对接 Controller 
 * 3.安全风险评估 
 * @author zhou.jian
 * @date 2019-03-01
 */
@Controller
@RequestMapping("/zfxx/szbb/fxpg")
public class FxpgController extends BaseEntityDaoServiceCRUDController<Fxpg,String,FxpgDao,FxpgService>{
	
	/**
	 * 数字冰雹：1.风险评估-监狱排名
	 * @return
	 */
	@RequestMapping(value = "/pushJypm")
    @ResponseBody
	public AjaxResult pushJypm() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getJypm(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【1.风险评估-监狱排名】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：2.风险评估-风险清单
	 * @return
	 */
	@RequestMapping(value = "/pushFxqd")
	@ResponseBody
	public AjaxResult pushFxqd() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getFxqd(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【2.风险评估风险清单】数据发生异常！！！");
		}
	}
	
	
	/**
	 * 数字冰雹：3.风险评估-频发风险
	 * @return
	 */
	@RequestMapping(value = "/pushPffx")
	@ResponseBody
	public AjaxResult pushPffx() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getPffx(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【3.风险评估-频发风险】数据发生异常！！！");
		}
	}
	
	
	/**
	 * 数字冰雹：4.风险评估-风险等级
	 * @return
	 */
	@RequestMapping(value = "/pushFxdj")
	@ResponseBody
	public AjaxResult pushFxdj() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getFxdj(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【4.风险评估-风险等级】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：5.风险评估-全部风险点
	 * @return
	 */
	@RequestMapping(value = "/pushQbfxd")
	@ResponseBody
	public AjaxResult pushQbfxd() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getQbfxd(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【5.风险评估-全部风险点】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：6.风险评估-当前发生风险点
	 * @return
	 */
	@RequestMapping(value = "/pushDqfxd")
	@ResponseBody
	public AjaxResult pushDqfxd() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getDqfxd(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【6.风险评估-当前发生风险点】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：7.风险评估-当前发生风险详情
	 * @return
	 */
	@RequestMapping(value = "/pushDqfxxq")
	@ResponseBody
	public AjaxResult pushDqfxxq() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getDqfxxq(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【7.风险评估-当前发生风险详情】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：8.风险评估-网格风险
	 * @return
	 */
	@RequestMapping(value = "/pushWgfx")
	@ResponseBody
	public AjaxResult pushWgfx() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getWgfx(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【8.风险评估-网格风险】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：9.风险评估-网格风险评估
	 * @return
	 */
	@RequestMapping(value = "/pushWgfxpg")
	@ResponseBody
	public AjaxResult pushWgfxpg() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getWgfxpg(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【9.风险评估-网格风险评估】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：10.风险评估-网格扣分
	 * @return
	 */
	@RequestMapping(value = "/pushWgkf")
	@ResponseBody
	public AjaxResult pushWgkf() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getWgkf(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【10.风险评估-网格扣分】数据发生异常！！！");
		}
	}
	
}
