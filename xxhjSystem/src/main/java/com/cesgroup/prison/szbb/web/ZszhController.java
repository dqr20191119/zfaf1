package com.cesgroup.prison.szbb.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.httpclient.util.SzbbUtil;
import com.cesgroup.prison.szbb.dao.ZszhDao;
import com.cesgroup.prison.szbb.entity.Zszh;
import com.cesgroup.prison.szbb.service.ZszhService;

/**
 * 数字冰雹数据对接 Controller
 * 2.战时指挥
 * @author zhou.jian
 * @date 2019-03-01
 */
@Controller
@RequestMapping("/zfxx/szbb/zszh")
public class ZszhController extends BaseEntityDaoServiceCRUDController<Zszh,String,ZszhDao,ZszhService>{

	
	/**
	 * 数字冰雹：2.1.战时指挥 - 现场警员
	 * @return
	 */
	@RequestMapping(value = "/pushXcjy")
    @ResponseBody
	public AjaxResult pushXcjy() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getXcjyList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【2.1.战时指挥 - 现场警员】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：2.2.战时指挥 - 待援警员
	 * @return
	 */
	@RequestMapping(value = "/pushDyjy")
	@ResponseBody
	public AjaxResult pushDyjy() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getDyjyList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【2.2.战时指挥 - 待援警员】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：2.3.战时指挥 - 待命车辆
	 * @return
	 */
	@RequestMapping(value = "/pushDmcl")
	@ResponseBody
	public AjaxResult pushDmcl() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getDmclList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【2.3.战时指挥 - 待命车辆】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：2.4.战时指挥 - 警务装备
	 * @return
	 */
	@RequestMapping(value = "/pushJwzb")
	@ResponseBody
	public AjaxResult pushJwzb() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getJwzbList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【2.4.战时指挥 - 警务装备】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：2.5.战时指挥 - 事件信息
	 * @return
	 */
	@RequestMapping(value = "/pushSjxx")
	@ResponseBody
	public AjaxResult pushSjxx() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getSjxx(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【2.5.战时指挥 - 事件信息】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：2.6.战时指挥 - 事件流程
	 * @return
	 */
	@RequestMapping(value = "/pushSjlc")
	@ResponseBody
	public AjaxResult pushSjlc() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getSjlcList(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【2.6.战时指挥 - 事件流程】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：2.7.战时指挥 - 当前流程
	 * @return
	 */
	@RequestMapping(value = "/pushDqlc")
	@ResponseBody
	public AjaxResult pushDqlc() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getDqlc(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【2.7.战时指挥 - 当前流程】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：2.8.战时指挥 - 涉事部位
	 * @return
	 */
	@RequestMapping(value = "/pushSsbm")
	@ResponseBody
	public AjaxResult pushSsbm() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getSsbm(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【2.8.战时指挥 - 涉事部位】数据发生异常！！！");
		}
	}
	
}
