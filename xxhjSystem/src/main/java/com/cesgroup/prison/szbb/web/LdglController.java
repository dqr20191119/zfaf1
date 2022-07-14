package com.cesgroup.prison.szbb.web;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.httpclient.util.SzbbUtil;
import com.cesgroup.prison.szbb.dao.LdglDao;
import com.cesgroup.prison.szbb.entity.Ldgl;
import com.cesgroup.prison.szbb.service.LdglService;

/**
 * 数字冰雹数据对接 Controller
 * 4.领导管理驾驶舱
 * @author zhou.jian
 * @date 2019-03-01
 */
@Controller
@RequestMapping("/zfxx/szbb/ldgl")
public class LdglController extends BaseEntityDaoServiceCRUDController<Ldgl,String,LdglDao,LdglService>{

	
	/**
	 * 数字冰雹：4.1.领导管理驾驶舱 - 罪犯变化
	 * @return
	 */
	@RequestMapping(value = "/pushZfbh")
    @ResponseBody
	public AjaxResult pushZfbh() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getZfbh(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【4.1.领导管理驾驶舱 - 罪犯变化】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：4.2.领导管理驾驶舱 - 罪犯年龄对比
	 * @return
	 */
	@RequestMapping(value = "/pushZfnldb")
	@ResponseBody
	public AjaxResult pushZfnldb() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getZfnldb(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【4.2.领导管理驾驶舱 - 罪犯年龄对比】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：4.3.领导管理驾驶舱 - 罪犯类型
	 * @return
	 */
	@RequestMapping(value = "/pushZflx")
	@ResponseBody
	public AjaxResult pushZflx() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getZflx(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【4.3.领导管理驾驶舱 - 罪犯类型】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：4.4.领导管理驾驶舱 - 队伍建设
	 * @return
	 */
	@RequestMapping(value = "/pushDwjs")
	@ResponseBody
	public AjaxResult pushDwjs() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getDwjs(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【4.4.领导管理驾驶舱 - 队伍建设】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：4.5.领导管理驾驶舱 - 个别谈话
	 * @return
	 */
	@RequestMapping(value = "/pushGbth")
	@ResponseBody
	public AjaxResult pushGbth() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getGbth(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【4.5.领导管理驾驶舱 - 个别谈话】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：4.6.领导管理驾驶舱 - 亲情电话
	 * @return
	 */
	@RequestMapping(value = "/pushQqdh")
	@ResponseBody
	public AjaxResult pushQqdh() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getQqdh(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【4.6.领导管理驾驶舱 - 亲情电话】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：4.7.领导管理驾驶舱 - 刑罚执行
	 * @return
	 */
	@RequestMapping(value = "/pushXfzx")
	@ResponseBody
	public AjaxResult pushXfzx() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getXfzx(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【4.7.领导管理驾驶舱 - 刑罚执行】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：4.8.领导管理驾驶舱 - 劳动工具
	 * @return
	 */
	@RequestMapping(value = "/pushLdgj")
	@ResponseBody
	public AjaxResult pushLdgj() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getLdgj(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【4.8.领导管理驾驶舱 - 劳动工具】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：4.9.领导管理驾驶舱 - 大宗物品
	 * @return
	 */
	@RequestMapping(value = "/pushDzwp")
	@ResponseBody
	public AjaxResult pushDzwp() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getDzwp(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【4.9.领导管理驾驶舱 - 大宗物品】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：4.10.领导管理驾驶舱 - 就医统计
	 * @return
	 */
	@RequestMapping(value = "/pushJytj")
	@ResponseBody
	public AjaxResult pushJytj() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getJytj(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【4.10.领导管理驾驶舱 - 就医统计】数据发生异常！！！");
		}
	}
	
	/**
	 * 数字冰雹：4.11.领导管理驾驶舱 - 组织结构
	 * @return
	 */
	@RequestMapping(value = "/pushZzjg")
	@ResponseBody
	public AjaxResult pushZzjg() {
		
		try {
			List<String> jyList = SzbbUtil.initSzbb();
			for (String jyCode : jyList) {
				this.getService().getZzjg(jyCode);
			}
			return AjaxResult.success();
			
		} catch (Exception e) {
			return AjaxResult.error("推送【4.11.领导管理驾驶舱 - 组织结构】数据发生异常！！！");
		}
	}
	
}
