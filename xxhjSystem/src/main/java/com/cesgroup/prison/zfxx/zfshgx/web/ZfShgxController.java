package com.cesgroup.prison.zfxx.zfshgx.web;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.jobs.constants.Synchro;
import com.cesgroup.prison.jobs.service.BjJobService;
import com.cesgroup.prison.jobs.service.ZfywTbService;
import com.cesgroup.prison.tblog.entity.Tblog;
import com.cesgroup.prison.tblog.service.TblogService;
import com.cesgroup.prison.zfxx.dzwp.util.DzwpUtil;
import com.cesgroup.prison.zfxx.jyqk.util.DhJyqkUtil;
import com.cesgroup.prison.zfxx.zfshgx.dao.ZfShgxDao;
import com.cesgroup.prison.zfxx.zfshgx.entity.ZfShgx;
import com.cesgroup.prison.zfxx.zfshgx.service.ZfShgxService;
/**
 * Description: 罪犯社会关系 控制器
 * @author monkey
 *
 * 2019年3月11日
 */

@Controller
@RequestMapping("/zfxx/zfShgx")
public class ZfShgxController  extends BaseEntityDaoServiceCRUDController<ZfShgx,String,ZfShgxDao,ZfShgxService> {
	
	@Autowired
	private BjJobService bjJobService;
	@Autowired
	private TblogService tblogService;
	@Autowired
	private ZfywTbService zfywTbService;
	
	private String getTime() {
		//同步当天数据
		//return Util.toStr(new Date(), Util.DF_DATE);
		//同步昨天数据
		return Util.getYesterdayDate(false);
	}
	
	/**
	 * Description: 同步罪犯业务数据
	 * @return
	 */
    @RequestMapping(value = "/synchroZfyw")
    @ResponseBody
	public AjaxResult synchroZfyw() {
		try {
			System.out.println("=====>start synchroZfyw");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.synchroZfyw(jyList);
			}
			System.out.println("=====>end synchroZfyw");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步罪犯业务数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfjbxx")
    @ResponseBody
	public AjaxResult synchroZfjbxx() {
		try {
			System.out.println("=====>start synchroZfjbxx");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfJbxx(time, cjpc, jyList);
				zfywTbService.incZfPhoto(time, cjpc);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfjbxx");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步罪犯基本信息数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfsy")
    @ResponseBody
	public AjaxResult synchroZfsy() {
		try {
			System.out.println("=====>start synchroZfsy");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfSy(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfjbxx");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步罪犯收押数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfLj")
    @ResponseBody
	public AjaxResult synchroZfLj() {
		try {
			System.out.println("=====>start synchroZfLj");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfLj(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfLj");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步罪犯离监数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfShgx")
    @ResponseBody
	public AjaxResult synchroZfShgx() {
		try {
			System.out.println("=====>start synchroZfShgx");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfShgx(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfShgx");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步罪犯社会关系数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfLbc")
    @ResponseBody
	public AjaxResult synchroZfLbc() {
		try {
			System.out.println("=====>start synchroZfLbc");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfLbc(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfLbc");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步罪犯老病残数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfRzfp")
    @ResponseBody
	public AjaxResult synchroZfRzfp() {
		try {
			System.out.println("=====>start synchroZfRzfp");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfRzfp(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfRzfp");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步罪犯认罪服判数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfTg")
    @ResponseBody
	public AjaxResult synchroZfTg() {
		try {
			System.out.println("=====>start synchroZfTg");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfTg(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfTg");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步特管数据发生异常");
		}
	}
 
    @RequestMapping(value = "/synchroZfZdzf")
    @ResponseBody
	public AjaxResult synchroZfZdzf() {
		try {
			System.out.println("=====>start synchroZfZdzf");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfZdzf(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfZdzf");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步重点罪犯数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfJzzy")
    @ResponseBody
	public AjaxResult synchroZfJzzy() {
		try {
			System.out.println("=====>start synchroZfJzzy");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfJzzy(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfJzzy");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步就诊住院数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfJhzs")
    @ResponseBody
	public AjaxResult synchroZfJhzs() {
		try {
			System.out.println("=====>start synchroZfJhzs");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfJhzs(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfJhzs");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步解回再审数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfJyzf")
    @ResponseBody
	public AjaxResult synchroZfJyzf() {
		try {
			System.out.println("=====>start synchroZfJyzf");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfJyzf(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfJyzf");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步狱外寄押数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfLjtq")
    @ResponseBody
	public AjaxResult synchroZfLjtq() {
		try {
			System.out.println("=====>start synchroZfLjtq");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfLjtq(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfLjtq");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步离监探亲数据发生异常");
		}
	}

    @RequestMapping(value = "/synchroZfJzzySj")
    @ResponseBody
	public AjaxResult synchroZfJzzySj() {
		try {
			System.out.println("=====>start synchroZfJzzySj");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfJzzySj(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfJzzySj");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步就诊收监数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfZyjwzx")
    @ResponseBody
	public AjaxResult synchroZfZyjwzx() {
		try {
			System.out.println("=====>start synchroZfZyjwzx");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfZyjwzx(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfZyjwzx");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步暂予监外执行数据发生异常");
		}
	}

    @RequestMapping(value = "/synchroZfPhoto")
    @ResponseBody
	public AjaxResult synchroZfPhoto() {
		try {
			System.out.println("=====>start synchroZfPhoto");
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			zfywTbService.zfPhoto(cjpc);
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfPhoto");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("全量同步罪犯照片数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfXfbdJx")
    @ResponseBody
	public AjaxResult synchroZfXfbdJx() {
		try {
			System.out.println("=====>start synchroZfXfbdJx");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfXfbdJx(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfXfbdJx");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步刑法变动减刑数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfXfbdJs")
    @ResponseBody
	public AjaxResult synchroZfXfbdJs() {
		try {
			System.out.println("=====>start synchroZfJzzySj");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfXfbdJs(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfXfbdJs");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步刑法变动假释数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfYnjcxx")
    @ResponseBody
	public AjaxResult synchroZfYnjcxx() {
		try {
			System.out.println("=====>start synchroZfYnjcxx");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfYnjcxx(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfYnjcxx");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步狱内奖惩数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroZfJfkhRkh")
    @ResponseBody
	public AjaxResult synchroZfJfkhRkh() {
		try {
			System.out.println("=====>start synchroZfJfkhRkh");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfJfkhRkh(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfJfkhRkh");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步日考核数据发生异常");
		}
	}

    @RequestMapping(value = "/synchroZfJfkhYhz")
    @ResponseBody
	public AjaxResult synchroZfJfkhYhz() {
		try {
			System.out.println("=====>start synchroZfJfkhYhz");
			List<Map<String, Object>> jyList = bjJobService.getOrgList(false);
			String time = getTime();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			if (jyList != null && jyList.size() > 0) {
				zfywTbService.zfJfkhYhz(time, cjpc, jyList);
			}
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroZfJfkhYhz");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步月考核数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroDhJyqk")
    @ResponseBody
	public AjaxResult synchroDhJyqk() {
		try {
			System.out.println("=====>start synchroDhJyqk");
			List<String> jyList = DhJyqkUtil.getJyList();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			zfywTbService.synchroDhJyqk(cjpc, jyList);
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroDhJyqk");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步各监区就医情况数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroDhMbqk")
    @ResponseBody
	public AjaxResult synchroDhMbqk() {
		try {
			System.out.println("=====>start synchroDhMbqk");
			List<String> jyList = DhJyqkUtil.getJyList();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			zfywTbService.synchroDhMbqk(cjpc, jyList);
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroDhMbqk");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步慢病分析数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroDhJyqs")
    @ResponseBody
	public AjaxResult synchroDhJyqs() {
		try {
			System.out.println("=====>start synchroDhJyqs");
			List<String> jyList = DhJyqkUtil.getJyList();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			zfywTbService.synchroDhJyqs(cjpc, jyList);
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroDhJyqs");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步就医趋势数据发生异常");
		}
	}
    
    @RequestMapping(value = "/synchroDzwp")
    @ResponseBody
	public AjaxResult synchroDzwp() {
		try {
			System.out.println("=====>start synchroDzwp");
			List<String> jyList = DzwpUtil.getJyList();
			String cjpc = Synchro.getUUID();
			Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
			tblogService.save(tblog);
			zfywTbService.synchroDzwp(cjpc, jyList);
			tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
			tblogService.save(tblog);
			System.out.println("=====>end synchroDzwp");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("同步大宗物品数据发生异常");
		}
	}
    
    /**
	 * Description: 推送数字冰雹
	 * @return
	 */
    @RequestMapping(value = "/tsSzbb")
    @ResponseBody
	public AjaxResult tsSzbb() {
		try {
			System.out.println("=====>start tsSzbb");
			bjJobService.tsSzbb();
			System.out.println("=====>end tsSzbb");
			return AjaxResult.success();
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("推送数字冰雹发生异常");
		}
	}
    
}
