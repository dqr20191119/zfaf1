package com.cesgroup.prison.jobs.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.cesgroup.framework.utils.PropertiesUtil;

public class Synchro {

	public static List<Map<String, Object>> init() {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = null;
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100000");
		map.put("jyCode", "1100");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100001");
		map.put("jyCode", "1101");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100002");
		map.put("jyCode", "1102");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100003");
		map.put("jyCode", "1103");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100004");
		map.put("jyCode", "1104");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100006");
		map.put("jyCode", "1105");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100005");
		map.put("jyCode", "1120");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100007");
		map.put("jyCode", "1121");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100104");
		map.put("jyCode", "1142");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100101");
		map.put("jyCode", "1145");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100105");
		map.put("jyCode", "1146");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100103");
		map.put("jyCode", "1147");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100107");
		map.put("jyCode", "1149");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100008");
		map.put("jyCode", "1130");
		list.add(map);
		map = new HashMap<String, Object>();
		map.put("corps", "73878031185de329fa6876725c100100");
		map.put("jyCode", "1140");
		list.add(map);
		return list;
	}
	
	public static final String HOST = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.zfxx.url");
	
	public static final String HOST_ZFYW = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.zfxx.url") + "/zfxx/entity/";

	public static final String RETURN_CODE_SUCCESS = "200";
	
	public static final String HOST_PHOTO = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.zfphoto.url");
	
	public static final String HOST_DZWP = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.dzwp.url");
	
	/**
	 * 罪犯照片本系统（安防平台）存储根目录
	 */
	public static final String ZF_PHOTO_ROOT_PATH = PropertiesUtil.getValueByKeyUnchanged("application", "afpt.zfphoto.rootPath");
	
	public interface Org {
		
		/**
		 * 清河分局
		 */
		public static final String QHFJ = "1140";
		
	}
	
	public interface BathSize {
		
		public static final int BATCH_SIZE_2000 = 2000;
		
		public static final int BATCH_SIZE_1000 = 1000;
		
		public static final int BATCH_SIZE_500 = 500;
		
		public static final int BATCH_SIZE_100 = 100;
		
		public static final int BATCH_SIZE_50 = 50;
		
		public static final int BATCH_SIZE_30 = 30;
		
	}
	
	public interface Opt {
		
		/**
		 * 备份
		 */
		public static final String BACKUPS = "BACKUPS";
		
		/**
		 * 删除
		 */
		public static final String DELETE = "DELETE";
		
		/**
		 * 新增
		 */
		public static final String INSERT = "INSERT";
		
		/**
		 * 完成
		 */
		public static final String COMPLETE = "COMPLETE";
		
		/**
		 * 开始同步
		 */
		public static final String START = "START";
		
		/**
		 * 同步结束
		 */
		public static final String END = "END";
		
		/**
		 * 同步失败
		 */
		public static final String ERROR = "ERROR";
		
		/**
		 * 下载照片
		 */
		public static final String DOWNLOAD = "DOWNLOAD";
		
		/**
		 * 无操作
		 */
		public static final String NONE = "NONE";
		
	}
	
	public interface Zfyw {
		
		/**
		 * 数字冰雹
		 */
		public static final String SZBB = "数字冰雹";
		
		/**
		 * 数字冰雹日常管控
		 */
		public static final String SZBB_RCGK = "数字冰雹日常管控";
		
		/**
		 * 数字冰雹战时指挥
		 */
		public static final String SZBB_ZSZH = "数字冰雹战时指挥";
		
		/**
		 * 数字冰雹风险评估
		 */
		public static final String SZBB_FXPG = "数字冰雹风险评估";
		
		/**
		 * 数字冰雹领导管理
		 */
		public static final String SZBB_LDGL = "数字冰雹领导管理";
		
		/**
		 * 罪犯基本信息
		 * entity_zf_jbxx
		 */
		public static final String ENTITY_ZF_JBXX = "entity_zf_jbxx";
		
		/**
		 * 罪犯照片信息
		 * entity_zf_mtxx
		 */
		public static final String ENTITY_ZF_MTXX = "entity_zf_mtxx";
		
		/**
		 * 增量罪犯照片信息
		 * entity_zf_mtxx
		 */
		public static final String ENTITY_ZF_INCMTXX = "entity_zf_icnmtxx";
		
		/**
		 * 罪犯在本监狱收押信息
		 * entity_zf_sy
		 */
		public static final String ENTITY_ZF_SY = "entity_zf_sy";
		
		/**
		 * 罪犯在本监狱离监信息
		 * entity_zf_lj
		 */
		public static final String ENTITY_ZF_LJ = "entity_zf_lj";
		
		/**
		 * 社会关系
		 * entity_zf_shgx
		 */
		public static final String ENTITY_ZF_SHGX = "entity_zf_shgx";
		
		/**
		 * 老病残
		 * entity_zf_lbc
		 */
		public static final String ENTITY_ZF_LBC = "entity_zf_lbc";
		
		/**
		 * 认罪服判
		 * entity_zf_rzfp
		 */
		public static final String ENTITY_ZF_RZFP = "entity_zf_rzfp";
		
		/**
		 * 特管登记
		 * entity_zf_tg
		 */
		public static final String ENTITY_ZF_TG = "entity_zf_tg";
		
		/**
		 * 重点罪犯
		 * entity_zf_zdzf
		 */
		public static final String ENTITY_ZF_ZDZF = "entity_zf_zdzf";
		
		/**
		 * 罪犯外出情况-狱外就诊/住院（社会医院）	
		 * entity_zf_jzzy_wc
		 */
		public static final String ENTITY_ZF_JZZYWC = "entity_zf_jzzy_wc";
		
		/**
		 * 罪犯外出情况-解回再审
		 * entity_zf_jhzs
		 */
		public static final String ENTITY_ZF_JHZS = "entity_zf_jhzs";
		
		/**
		 * 罪犯外出情况-狱外寄押
		 * entity_zf_jyzf
		 */
		public static final String ENTITY_ZF_JYZF = "entity_zf_jyzf";
		
		/**
		 * 罪犯收监情况-离监探亲
		 * entity_zf_ljtq_sj
		 */
		public static final String ENTITY_ZF_LJTQSJ = "entity_zf_ljtq_sj";
		
		/**
		 * 罪犯收监情况-狱外就诊/住院（社会医院）
		 * entity_zf_jzzy_sj
		 */
		public static final String ENTITY_ZF_JZZYSJ = "entity_zf_jzzy_sj";
		
		/**
		 * 暂予监外执行
		 * entity_zf_zyjwzx
		 */
		public static final String ENTITY_ZF_ZYJWZX = "entity_zf_zyjwzx";
		
		/**
		 * 刑法变动减刑
		 * entity_zf_xfbd_jx
		 */
		public static final String ENTITY_ZF_XFBDJX = "entity_zf_xfbd_jx";
		
		/**
		 * 刑法变动假释
		 * entity_zf_xfbd_js
		 */
		public static final String ENTITY_ZF_XFBDJS = "entity_zf_xfbd_js";
		
		/**
		 * 狱内奖惩
		 * entity_zf_jcxx
		 */
		public static final String ENTITY_ZF_JCXX = "entity_zf_jcxx";
		
		/**
		 * 日考核
		 * entity_zf_rkh
		 */
		public static final String ENTITY_ZF_RKH = "entity_zf_rkh";
		
		/**
		 * 月考核
		 * entity_zf_yhz
		 */
		public static final String ENTITY_ZF_YHZ = "entity_zf_yhz";
	}
	
	public interface Member {
		
		/**
		 * Default_ZfJbxx
		 */
		public static final String DEFAULT_ZFJBXX = "Default_ZfJbxx";
		
		/**
		 * 媒体照片信息
		 * Default_ZfDmtxxPhoto
		 */
		public static final String DEFAULT_ZFDMTXXPHOTO = "Default_ZfDmtxxPhoto";
		
		/**
		 * 同案犯信息
		 * Default_ZfTaf
		 */
		public static final String DEFAULT_ZFTAF = "Default_ZfTaf";
		
		/**
		 * 罪犯在本监狱收押信息
		 * 罪犯在本监狱离监信息
		 * RsZyztbdTimeline_ZfZyztbd
		 */
		public static final String RSZYZBDTIMELINE_ZFZYZTBD = "RsZyztbdTimeline_ZfZyztbd";
		
		/**
		 * 社会关系
		 * Default_ZfShgx
		 */
		public static final String DEFAULT_ZFSHGX = "Default_ZfShgx";
		
		/**
		 * 老病残
		 * RsLbcDefault_ZfLbc
		 */
		public static final String RSLBC_DEFAULT_ZFLBC = "RsLbcDefault_ZfLbc";
		
		/**
		 * 认罪服判
		 * Default_ZfRzfp
		 */
		public static final String DEFAULT_ZFRZFP = "Default_ZfRzfp";
		
		/**
		 * 认罪服判类别
		 * Default_ZfRzfplb
		 */
		public static final String DEFAULT_ZFRZFPLB = "Default_ZfRzfplb";
		
		/**
		 * 重点罪犯
		 * RsWwzkZdzf_ZfWwzk
		 */
		public static final String RSWWZKZDZF_ZFWWZK = "RsWwzkZdzf_ZfWwzk";
		
		/**
		 * 罪犯外出情况-狱外就诊/住院（社会医院）
		 * 罪犯收监情况-狱外就诊/住院（社会医院）
		 * Default_ZfJzzy
		 */
		public static final String DEFAULT_ZFJZZY = "Default_ZfJzzy";
		
		/**
		 * 罪犯外出情况-解回再审
		 * Default_ZfJhzs
		 */
		public static final String DEFAULT_ZFJHZS = "Default_ZfJhzs";
		
		/**
		 * 罪犯外出情况-狱外寄押
		 * Default_ZfJyxx
		 */
		public static final String DEFAULT_ZFJYXX = "Default_ZfJyxx";
		
		/**
		 * 罪犯收监情况-离监探亲
		 * Default_ZfLjtq
		 */
		public static final String DEFAULT_ZFLJTQ = "Default_ZfLjtq";
		
		/**
		 * 暂予监外执行
		 * Default_ZfJwzx
		 */
		public static final String DEFAULT_ZFJWZX = "Default_ZfJwzx";
		
		/**
		 * 暂予监外执行保人信息
		 * Default_ZfJwzxBrxx
		 */
		public static final String DEFAULT_ZFJWZXBRXX = "Default_ZfJwzxBrxx";
		
		/**
		 * 暂予监外执行司法所信息
		 * Default_ZfJwzxBjxx
		 */
		public static final String DEFAULT_ZFJWZXBJXX = "Default_ZfJwzxBjxx";
		
		/**
		 * 刑法变动
		 * Default_ZfXfbd
		 */
		public static final String DEFAULT_ZFXFBD = "Default_ZfXfbd";
		
		/**
		 * 罪犯假释
		 * RsZyztbdTimeline_ZfZyztbd
		 */
		public static final String RSZYZTBDTIMELINE_ZFZYZTBD = "RsZyztbdTimeline_ZfZyztbd";
		
		/**
		 * 狱内奖惩信息
		 * RsJcxxSpzt_ZfJcxx
		 */
		public static final String RSJCXXSPZT_ZFJCXX = "RsJcxxSpzt_ZfJcxx";
		
		/**
		 * 日考核
		 * RsRjkfjzSpzt_ZfRjkfjz
		 */
		public static final String RSRJKFJZSPZT_ZFRJKFJZ = "RsRjkfjzSpzt_ZfRjkfjz";
		
		/**
		 * 月考核
		 * RsYhzmxSpzt_ZfYhzmx
		 */
		public static final String RSYHZMXSPZT_ZFYHZMX = "RsYhzmxSpzt_ZfYhzmx";
	}
	
	public static String getUUID() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
}
