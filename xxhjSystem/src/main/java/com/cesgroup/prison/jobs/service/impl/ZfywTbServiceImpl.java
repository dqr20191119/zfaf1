package com.cesgroup.prison.jobs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ces.prison.common.constants.SystemConst;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.jobs.constants.Synchro;
import com.cesgroup.prison.jobs.service.ZfywTbService;
import com.cesgroup.prison.tblog.entity.Tblog;
import com.cesgroup.prison.tblog.service.TblogService;
import com.cesgroup.prison.zfxx.dzwp.service.DzwpService;
import com.cesgroup.prison.zfxx.jfkh.service.ZfJfkhRkhService;
import com.cesgroup.prison.zfxx.jfkh.service.ZfJfkhYhzService;
import com.cesgroup.prison.zfxx.jhzs.service.ZfJhzsService;
import com.cesgroup.prison.zfxx.jyqk.service.DhJyqkService;
import com.cesgroup.prison.zfxx.jyqk.service.DhJyqsService;
import com.cesgroup.prison.zfxx.jyqk.service.DhMbqkService;
import com.cesgroup.prison.zfxx.ynjcxx.service.ZfYnjcxxService;
import com.cesgroup.prison.zfxx.zfLjtq.service.ZfLjtqService;
import com.cesgroup.prison.zfxx.zfjbxx.service.ZfJbxxTbService;
import com.cesgroup.prison.zfxx.zfjyzf.service.ZfJyzfService;
import com.cesgroup.prison.zfxx.zfjzzy.service.ZfJzzyService;
import com.cesgroup.prison.zfxx.zfjzzysj.service.ZfJzzySjService;
import com.cesgroup.prison.zfxx.zflbc.service.ZfLbcService;
import com.cesgroup.prison.zfxx.zflj.service.ZfLjService;
import com.cesgroup.prison.zfxx.zfrzfp.service.ZfRzfpService;
import com.cesgroup.prison.zfxx.zfshgx.service.ZfShgxService;
import com.cesgroup.prison.zfxx.zfsy.service.ZfSyService;
import com.cesgroup.prison.zfxx.zftg.service.ZfTgService;
import com.cesgroup.prison.zfxx.zfxfbd.service.ZfXfbdJsService;
import com.cesgroup.prison.zfxx.zfxfbd.service.ZfXfbdJxService;
import com.cesgroup.prison.zfxx.zfzdzf.service.ZfZdzfService;
import com.cesgroup.prison.zfxx.zfzyjwzx.service.ZfZyjwzxService;

@Service("bfywTbService")
@Transactional
public class ZfywTbServiceImpl implements ZfywTbService {

	@Autowired
	private TblogService tblogService;
	@Autowired
	private ZfJbxxTbService zfJbxxTbService;
	@Autowired
	private ZfSyService zfSyService;
	@Autowired
	private ZfLjService zfLjService;
	@Autowired
	private ZfShgxService zfShgxService;
	@Autowired
	private ZfLbcService zfLbcService;
	@Autowired
	private ZfRzfpService zfRzfpService;
	@Autowired
	private ZfTgService zfTgService;
	@Autowired
	private ZfZdzfService zfZdzfService;
	@Autowired
	private ZfJzzyService zfJzzyService;
	@Autowired
	private ZfJhzsService zfJhzsService;
	@Autowired
	private ZfJyzfService zfJyzfService;
	@Autowired
	private ZfLjtqService zfLjtqService;
	@Autowired
	private ZfJzzySjService zfJzzySjService;
	@Autowired
	private ZfZyjwzxService zfZyjwzxService;
	@Autowired
	private ZfXfbdJxService zfXfbdJxService;
	@Autowired
	private ZfXfbdJsService zfXfbdJsService;
	@Autowired
	private ZfYnjcxxService zfYnjcxxService;
	@Autowired
	private ZfJfkhRkhService zfJfkhRkhService;
	@Autowired
	private ZfJfkhYhzService zfJfkhYhzService;
	
	
	/*就医情况*/
	@Autowired
	private DhJyqkService dhJyqkService;
	@Autowired
	private DhMbqkService dhMbqkService;
	@Autowired
	private DhJyqsService dhJyqsService;
	@Autowired
	private DzwpService dzwpService;
	
	@Override
	public void synchroZfyw(List<Map<String, Object>> jyList) {
		//String time = Util.toStr(new Date(), Util.DF_DATE);
		//同步昨天数据
		String time = Util.getYesterdayDate(false);
		String cjpc = Synchro.getUUID();
		Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
		tblogService.save(tblog);
		try {
			zfJbxx(time, cjpc, jyList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			zfSy(time, cjpc, jyList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			zfLj(time, cjpc, jyList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			zfShgx(time, cjpc, jyList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			zfLbc(time, cjpc, jyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			zfRzfp(time, cjpc, jyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			zfTg(time, cjpc, jyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			zfZdzf(time, cjpc, jyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			zfJzzy(time, cjpc, jyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			zfJhzs(time, cjpc, jyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			zfJyzf(time, cjpc, jyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			zfLjtq(time, cjpc, jyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			zfJzzySj(time, cjpc, jyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			zfZyjwzx(time, cjpc, jyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			zfXfbdJx(time, cjpc, jyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			zfXfbdJs(time, cjpc, jyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			zfYnjcxx(time, cjpc, jyList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			incZfPhoto(time, cjpc);
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*try {
			zfPhoto(cjpc);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
		tblogService.save(tblog);
	}
	
	public void synchroZfzp() {
		try {
			String cjpc = Synchro.getUUID();
			zfPhoto(cjpc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
     * 罪犯基本信息
     * @param time
     * @param cjpc
     * @param list
     */
	@Override
    public void zfJbxx(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String jyId = null;
    	String opt = null;
    	zfJbxxTbService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JBXX, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			jyId = (String)map.get("jyCode");
			try {
				//不同步北京市监狱管理局和清河分局罪犯数据，否侧出现重复
				if (!SystemConst.UNIT_CODE_KEY_CENTER.equals(jyId) && !Synchro.Org.QHFJ.equals(jyId)) {
					zfJbxxTbService.synchroZfJbxx(corp, jyId, time, cjpc);
					opt = Synchro.Opt.INSERT;
				} else {
					opt = Synchro.Opt.NONE;
				}
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JBXX, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JBXX, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 新收押
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfSy(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfSyService.synchroZfSy(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_SY, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_SY, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 离监
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfLj(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfLjService.synchroZfLj(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_LJ, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_LJ, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 社会关系
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfShgx(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfShgxService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_SHGX, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfShgxService.synchroZfShgx(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_SHGX, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_SHGX, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 老病残
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfLbc(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfLbcService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_LBC, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfLbcService.synchroZfLbc(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_LBC, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_LBC, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 认罪服判
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfRzfp(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfRzfpService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_RZFP, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfRzfpService.synchroZfRzfp(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_RZFP, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_RZFP, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 特管登记
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfTg(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfTgService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_TG, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfTgService.synchroZfTg(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_TG, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_TG, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 重点罪犯
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfZdzf(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfZdzfService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_ZDZF, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfZdzfService.synchroZfZdzf(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_ZDZF, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_ZDZF, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 罪犯外出情况-狱外就诊/住院（社会医院）
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfJzzy(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfJzzyService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JZZYWC, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfJzzyService.synchroZfJzzy(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JZZYWC, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JZZYWC, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 解回再审
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfJhzs(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfJhzsService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JHZS, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfJhzsService.synchroZfJhzs(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JHZS, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JHZS, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 狱外寄押
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfJyzf(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfJyzfService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JYZF, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfJyzfService.synchroZfJyzf(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JYZF, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JYZF, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 罪犯收监情况-离监探亲
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfLjtq(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfLjtqService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_LJTQSJ, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfLjtqService.synchroZfLjtq(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_LJTQSJ, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_LJTQSJ, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 罪犯收监情况-狱外就诊/住院信息任务
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfJzzySj(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfJzzySjService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JZZYSJ, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfJzzySjService.synchroZfJzzySj(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JZZYSJ, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JZZYSJ, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 暂予监外执行
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfZyjwzx(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfZyjwzxService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_ZYJWZX, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfZyjwzxService.synchroZfZyjwzx(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_ZYJWZX, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_ZYJWZX, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 全量罪犯照片
     * @param time
     * @param cjpc
     * @param list
     */
	@Override
    public void zfPhoto(String cjpc) {
    	Tblog tblog = null;
    	String opt = null;
		try {
			zfJbxxTbService.synchroZfPhoto();
			opt = Synchro.Opt.DOWNLOAD;
		} catch (Exception e) {
			e.printStackTrace();
			opt = Synchro.Opt.ERROR;
		}
		tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_MTXX, null, opt, null, new Date(), cjpc);
		tblogService.save(tblog);
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_MTXX, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
    /**
     * 增量罪犯照片
     * @param time
     * @param cjpc
     * @param list
     */
	@Override
    public void incZfPhoto(String time, String cjpc) {
    	Tblog tblog = null;
    	String opt = null;
		try {
			zfJbxxTbService.synchroIncZfPhoto(time);
			opt = Synchro.Opt.DOWNLOAD;
		} catch (Exception e) {
			e.printStackTrace();
			opt = Synchro.Opt.ERROR;
		}
		tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_INCMTXX, null, opt, null, new Date(), cjpc);
		tblogService.save(tblog);
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_INCMTXX, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
	
	/**
     * 刑法变动减刑
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfXfbdJx(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfXfbdJxService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_XFBDJX, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfXfbdJxService.synchroZfXfbdJx(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_XFBDJX, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_XFBDJX, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
    
	/**
     * 刑法变动假释
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfXfbdJs(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	String jyCode = null;
    	zfXfbdJsService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_XFBDJS, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			jyCode = (String)map.get("jyCode");
			try {
				zfXfbdJsService.synchroZfXfbdJs(corp, jyCode, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_XFBDJS, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_XFBDJS, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
	
	/**
     * 狱内奖惩信息
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfYnjcxx(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfYnjcxxService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JCXX, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfYnjcxxService.synchroZfYnjcxx(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JCXX, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_JCXX, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
	
	/**
     * 日考核
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfJfkhRkh(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfJfkhRkhService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_RKH, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfJfkhRkhService.synchroZfJfkhRkh(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_RKH, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_RKH, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }
	
	/**
     * 月考核
     * @param time
     * @param cjpc
     * @param jyList
     */
	@Override
    public void zfJfkhYhz(String time, String cjpc, List<Map<String, Object>> jyList) {
    	Tblog tblog = null;
    	String corp = null;
    	String opt = null;
    	zfJfkhYhzService.backups();
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_YHZ, null, Synchro.Opt.BACKUPS, null, new Date(), cjpc);
    	tblogService.save(tblog);
    	for (Map<String, Object> map : jyList) {
			corp = (String)map.get("corps");
			try {
				zfJfkhYhzService.synchroZfJfkhYhz(corp, time, cjpc);
				opt = Synchro.Opt.INSERT;
			} catch (Exception e) {
				e.printStackTrace();
				opt = Synchro.Opt.ERROR;
			}
			tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_YHZ, (String)map.get("jyCode"), opt, null, new Date(), cjpc);
			tblogService.save(tblog);
		}
    	tblog = new Tblog(Synchro.Zfyw.ENTITY_ZF_YHZ, null, Synchro.Opt.COMPLETE, null, new Date(), cjpc);
		tblogService.save(tblog);
    }

	@Override
	public void synchroJfkh(List<Map<String, Object>> jyList) {
		//String time = Util.toStr(new Date(), Util.DF_DATE);
		//同步昨天数据
		String time = Util.getYesterdayDate(false);
		String cjpc = Synchro.getUUID();
		Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
		tblogService.save(tblog);
		try {
			zfJfkhRkh(time, cjpc, jyList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		try {
			zfJfkhYhz(time, cjpc, jyList);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
		tblogService.save(tblog);
	}
	
	@Override
	public void synchroDhJyqk(String cjpc, List<String> jyList) {
		Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
		tblogService.save(tblog);
		for (String jyId : jyList) {
			dhJyqkService.synchroDhJyqk(jyId, cjpc);
		}
		tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
		tblogService.save(tblog);
	}
	
	@Override
	public void synchroDhMbqk(String cjpc, List<String> jyList) {
		Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
		tblogService.save(tblog);
		for (String jyId : jyList) {
			dhMbqkService.synchroDhMbqk(jyId, cjpc);
		}
		tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
		tblogService.save(tblog);
	}
	
	@Override
	public void synchroDhJyqs(String cjpc, List<String> jyList) {
		Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
		tblogService.save(tblog);
		for (String jyId : jyList) {
			dhJyqsService.synchroDhJyqs(jyId, cjpc);
		}
		tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
		tblogService.save(tblog);
	}
	
	
	@Override
	public void synchroDzwp(String cjpc, List<String> jyList) {
		Tblog tblog = new Tblog(null, null, Synchro.Opt.START, null, new Date(), cjpc);
		tblogService.save(tblog);
		for (String jyId : jyList) {
			dzwpService.synchroDzwp(jyId, cjpc);
		}
		tblog = new Tblog(null, null, Synchro.Opt.END, null, new Date(), cjpc);
		tblogService.save(tblog);
	}
	
}
