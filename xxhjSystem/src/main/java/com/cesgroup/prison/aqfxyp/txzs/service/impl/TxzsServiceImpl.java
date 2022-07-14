package com.cesgroup.prison.aqfxyp.txzs.service.impl;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.aqfxyp.fxcj.dao.FxcjMapper;
import com.cesgroup.prison.aqfxyp.txzs.dao.TxzsMapper;
import com.cesgroup.prison.aqfxyp.txzs.entity.Txzs;
import com.cesgroup.prison.aqfxyp.txzs.service.TxzsService;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.dao.CommonMapper;
import com.cesgroup.prison.criminal.dao.CriminalMapper;

@Service(value = "txzsService")
public class TxzsServiceImpl extends BaseDaoService<Txzs, String, TxzsMapper> implements TxzsService {
	@Autowired
	private FxcjMapper fxcjMapper;
	
	@Resource
	private CommonMapper  commonMapper;
	@Resource
	private CriminalMapper criminalMapper;

	@Override
	public List getfxgk(String jyId, String wgOne, String wgTwo, String wgThree) {
		Map map = new HashMap();
		List<Map> list = this.getDao().getWwjgZf(map);// 五维

		Map map2 = new HashMap();
		map2.put("jyId", jyId);
		List<Map> list2 = this.getDao().getWwjgKf(map2);// 各个维度扣分
		List listrh = new ArrayList();// 展示文字list
		List listrh2 = new ArrayList();// 展示总数list
		List listrh3 = new ArrayList();// 图像展示比例
		Double zf = 1000.0;
		Double mzxf = 0.0;
		for (int i = 0; i < list.size(); i++) {
			Map mapL = list.get(i);
			String name = mapL.get("NAME").toString();
			String id = mapL.get("ID").toString();
			Map mapLr = new HashMap();
			mapLr.put("name", name);
			Double kczf = 0.0;
			for (int i2 = 0; i2 < list2.size(); i2++) {
				Map mapL2 = list2.get(i2);
				String id2 = mapL2.get("WWJG_ID").toString();
				String kf = mapL2.get("KCZF").toString();
				Double kfd = Double.valueOf(kf);
				if (mzxf <= kfd) {
					mzxf = kfd;
				}
				if (id.equals(id2)) {
					kczf = kczf + kfd;
				}
			}
			zf = zf - kczf;
			zf = (double)Math.round(zf*10)/10;
			listrh2.add(kczf);
			mapLr.put("num", kczf);
			mapLr.put("max", mzxf);
			listrh.add(mapLr);
		}
		listrh3.add(listrh);
		listrh3.add(zf);
		listrh3.add(listrh2);
		return listrh3;

	}

	@Override
	public List getPies(String jyId, String wgOne, String wgTwo, String wgThree) {
		// TODO Auto-generated method stub
		/*
		 * Map map = new HashMap(); map.put("jyId", jyId); List<Map> list =
		 * this.getDao().getSjfwKf(map);//数据范围扣分 Map map2 = new HashMap(); List
		 * list2 = this.getDao().getSjfw(map2); Map mapr = new HashMap(); Double
		 * wwjgkfzs = 0.0; for(int i=0;i<list2.size();i++){ Map mapz = (Map)
		 * list2.get(i); String sjfwId = mapz.get("SJFWID").toString(); String
		 * sjfwName = mapz.get("SJFWNAME").toString(); String wwjgId =
		 * mapz.get("WWJGID").toString(); String wwjgName =
		 * mapz.get("WWJGNAME").toString(); Double sjfwkfzs = 0.0; for(int y
		 * =0;y<list.size();y++){ Map mapy = list.get(y); String sjfwId2 =
		 * mapy.get("SJFW_ID").toString(); String wwjgId2 =
		 * mapy.get("WWJG_ID").toString(); String KCZF =
		 * mapy.get("KCZF").toString();
		 * if(sjfwId2.equals(sjfwId)&&wwjgId2.equals(wwjgId)){ sjfwkfzs =
		 * sjfwkfzs+ Double.valueOf(KCZF); } } Map mapr1 = new HashMap();
		 * mapr1.put("sjfwName", sjfwName); mapr1.put("value", sjfwkfzs);
		 * mapr1.put("score", null);
		 * 
		 * mapr.put("fxpgList", mapr1); }
		 */
		Map map = new HashMap();
		List<Map> listww = this.getDao().getWwjgZf(map);// 五维
		Map map2 = new HashMap();
		List listsjfw = this.getDao().getSjfw(map2);// 数据范围
		Map map3 = new HashMap();
		map3.put("jyId", jyId);
		List<Map> listsjfwkf = this.getDao().getSjfwKf(map3);// 数据范围扣分

		List listr = new ArrayList();
		for (int i = 0; i < listww.size(); i++) {// 五唯循环
			Map mapr = new HashMap();
			Map mapL = listww.get(i);
			String name = mapL.get("NAME").toString();
			String id = mapL.get("ID").toString();
			Map mapsjfw = new HashMap();
			List listsjfww = new ArrayList();
			for (int j = 0; j < listsjfw.size(); j++) {
				Map mapkfx = new HashMap();
				Map mapz = (Map) listsjfw.get(j);
				String sjfwId = mapz.get("SJFWID").toString();
				String sjfwName = mapz.get("SJFWNAME").toString();
				String wwjgId = mapz.get("WWJGID").toString();
				String wwjgName = mapz.get("WWJGNAME").toString();
				Double sjfwkfzf = 0.0;
				for (int y = 0; y < listsjfwkf.size(); y++) {
					Map mapy = listsjfwkf.get(y);
					String sjfwId2 = mapy.get("SJFW_ID").toString();
					String wwjgId2 = mapy.get("WWJG_ID").toString();
					String KCZF = mapy.get("KCZF").toString();
					if (sjfwId2.equals(sjfwId) && wwjgId2.equals(wwjgId)) {
						sjfwkfzf = sjfwkfzf + Double.valueOf(KCZF);
					}
				}
				if (id.equals(wwjgId)) {
					mapkfx.put("name", sjfwName);
					mapkfx.put("value", sjfwkfzf);
					mapkfx.put("score", null);
					listsjfww.add(mapkfx);
					mapsjfw.put("fxpgList", listsjfww);
				}
			}
			mapsjfw.put("score", 200);

			mapr.put("wwjgname", name);
			mapr.put("wwjgjg", mapsjfw);
			listr.add(mapr);
		}

		return listr;
	}

	@Override
	public List getBars(String jyId, String wgOne, String wgTwo, String wgThree) {
		// TODO Auto-generated method stub
		Map map22 = new HashMap();
		List<Map> listww = this.getDao().getWwjgZf(map22);// 五维

		Map map = new HashMap();
		List listfxd = this.getDao().getfxd(map);

		Map map2 = new HashMap();
		map2.put("jyId",jyId);
		List listfxdkf = this.getDao().getfxdKf(map2);
		List listfxdjgfh = new ArrayList();
		for (int j = 0; j < listww.size(); j++) {// 五唯循环
			Map mapWw = listww.get(j);
			String wwname = mapWw.get("NAME").toString();
			String wwid = mapWw.get("ID").toString();
			Map mapwwrh = new HashMap();
			List listfxdjg = new ArrayList();
			for (int i = 0; i < listfxd.size(); i++) {
				Map mapfxd = new HashMap();
				Map mapL = (Map) listfxd.get(i);
				String name = mapL.get("NAME").toString();
				String id = mapL.get("ID").toString();
				Double fxdkfzh = 0.0;
				Double score = 0.0;
				for (int y = 0; y < listfxdkf.size(); y++) {
					Map mapy = (Map) listfxdkf.get(y);
					String fxdjId = mapy.get("FXDJ_ID").toString();
					String wwjgId = mapy.get("WWJG_ID").toString();
					String kczf = mapy.get("KCZF").toString();
					if (id.equals(fxdjId) && wwjgId.equals(wwid)) {
						fxdkfzh = fxdkfzh + Double.valueOf(kczf);
					}
					if (wwjgId.equals(wwid)) {
						score = score + Double.valueOf(kczf);
					}
				}
				name = name.substring(0, 2);
				mapfxd.put("name", name);
				mapfxd.put("value", fxdkfzh);
				mapfxd.put("score", Math.round(score));
				listfxdjg.add(mapfxd);
			}
			mapwwrh.put("wwjgname", wwname);
			mapwwrh.put("wwjgjg", listfxdjg);
			listfxdjgfh.add(mapwwrh);

		}
		return listfxdjgfh;
	}

	@Override
	public List fxfbData(String jyId, String wgOne, String wgTwo, String wgThree) {
		// TODO Auto-generated method stub
		Map mapc = new HashMap();
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		mapc.put("jyId", jyId);
		List<Map> listcode = fxcjMapper.getAllwGkkkk(mapc);
		Double a = 1000.0;
		for (Map map : listcode) {
			Map<String, Object> mapr = new HashMap<String, Object>();
			mapr.put("name", String.valueOf(map.get("NAME")));
			mapr.put("value", a - Double.valueOf(String.valueOf(map.get("KF"))));
			mapr.put("score", null);
			list.add(mapr);
		}
		
		
		
		
		/*mapc.put("groupkey", "4.13.6");
		mapc.put("jyId",jyId);
		List<Map> listcode = fxcjMapper.getFxfbwg(mapc);
		if(listcode.size()<12){
			mapc.put("bj",13-listcode.size());
			List<Map> listcodebj=fxcjMapper.getFxfbwgBj(mapc);
			listcode.addAll(listcodebj);
		}
		Map map = new HashMap();
		map.put("jyId",jyId);
		List listfxd = this.getDao().getwgOne(map);// 获取网格二级
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		for (int y = 0; y < listcode.size(); y++) {
			Map mapjg = listcode.get(y);
			String codeKey = (String) mapjg.get("CODE_KEY");
			String name = (String) mapjg.get("NAME");
			int codelength = codeKey.length();
			Double a = 1000.0;
			for (int i = 0; i < listfxd.size(); i++) {
				Map mapj = (Map) listfxd.get(i);
				String kczf = mapj.get("KCZF").toString();
				String wgOne1 = mapj.get("WG_TWO").toString();
				String wgOneName = mapj.get("WGTWONAME").toString();

				if (wgOne1.equals(codeKey)) {
					a = a - Double.valueOf(kczf);
				}

			}
			Map<String, Object> mapr = new HashMap<String, Object>();
			mapr.put("name", name);
			mapr.put("value", a);
			mapr.put("score", null);
			list.add(mapr);

		}*/

		// 排序
		if(list != null && list.size() > 0) { 
			list.sort((Map<String, Object> h1, Map<String, Object> h2) -> ((Double) h2.get("value")).compareTo((Double) h1.get("value")));
		}

		return list;
	}

	@Override
	public List getfxqs(String year, String jyId, String wgOne, String wgTwo, String wgThree) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);// 获取当前年份
		int month = cal.get(Calendar.MONTH) + 1;// 获取当前月份
		List listr = new ArrayList();
		Map map = new HashMap();
		map.put("year", yearNow);
		map.put("jyId",jyId);
		Map map2 = new HashMap();
		map2.put("year", yearNow - 1);
		map2.put("jyId",jyId);
		List list = this.getDao().getfxqs(map);// 获取今年所有月份的扣分值
		List list2 = this.getDao().getfxqs(map2);// 获取去年所有月份的扣分值
		if (month - 11 == 0) {
			for (int i = 1; i <= 12; i++) {
				String yyy = String.valueOf(i);
				if (i < 10) {
					yyy = "0" + i;
				}
				Double a = 0.0;
				for (int y = 0; y < list.size(); y++) {
					Map mapr = (Map) list.get(y);
					String yue1 = mapr.get("YUE").toString();
					String kczf = mapr.get("KCZF").toString();
					if (yyy.equals(yue1)) {
						a = a + Double.valueOf(kczf);
					}
				}
				Map mapl = new HashMap();
				mapl.put("name",  i + "月");
				mapl.put("value", a);
				mapl.put("score", String.valueOf(yearNow)+"-"+yyy);
				listr.add(mapl);
			}
		} else if (month - 11 > 0) {
			
			for (int i = 2; i <= 12; i++) {
				String yyy = String.valueOf(i);
				if (i < 10) {
					yyy = "0" + i;
				};
				Double a = 0.0;
				for (int y = 0; y < list.size(); y++) {
					Map mapr = (Map) list.get(y);
					String yue1 = mapr.get("YUE").toString();
					String kczf = mapr.get("KCZF").toString();
					if (yyy.equals(yue1)) {
						a = a + Double.valueOf(kczf);
					}
				}
				Map mapl = new HashMap();
				mapl.put("name", i + "月");
				mapl.put("value", a);
				mapl.put("score", String.valueOf(yearNow)+"-"+yyy);
				listr.add(mapl);
			}
			Map mapl = new HashMap();
			mapl.put("name", 1 + "月");
			mapl.put("value", 0);
			mapl.put("score", String.valueOf(yearNow+1)+"-01" );
			listr.add(mapl);
		} else if (month - 11 < 0) {
			for (int i = month+2; i <= 12; i++) {
				String yyy = String.valueOf(i);
				if (i < 10) {
					yyy = "0" + i;
				}
				Double a = 0.0;
				for (int y = month+2; y < list2.size(); y++) {
					Map mapr = (Map) list2.get(y);
					String yue1 = mapr.get("YUE").toString();
					String kczf = mapr.get("KCZF").toString();
					if (yyy.equals(yue1)) {
						a = a + Double.valueOf(kczf);
					}
				}
				Map mapl = new HashMap();
				mapl.put("name", i + "月");
				mapl.put("value", a);
				mapl.put("score", String.valueOf(yearNow-1)+"-"+yyy);
				listr.add(mapl);
			}
			for (int i = 1; i <= month+1; i++) {
				String yyy = String.valueOf(i);
				if (i < 10) {
					yyy = "0" + i;
				}
				Double a = 0.0;
				for (int y = 0; y < list.size(); y++) {
					Map mapr = (Map) list.get(y);
					String yue1 = mapr.get("YUE").toString();
					String kczf = mapr.get("KCZF").toString();
					if (yyy.equals(yue1)) {
						a = a + Double.valueOf(kczf);
					}
				}
				Map mapl = new HashMap();
				mapl.put("name", i + "月");
				mapl.put("value", a);
				mapl.put("score", String.valueOf(yearNow)+"-"+yyy);
				listr.add(mapl);
			}
			;
		}
		return listr;
	}

	@Override
	public List wgfb(String type, String jyId, String wgOne, String wgTwo, String wgThree) {
		// TODO Auto-generated method stub
		Map mapc = new HashMap();
		mapc.put("groupkey", "4.13.6");
		/* mapc.put("parentId", "4.13.6.1105"); */
		List<Map> listcode = fxcjMapper.getAllwG(mapc);
		Map map = new HashMap();
		List listfxd = this.getDao().getwgOne(map);// 获取网格二级
		List list = new ArrayList();
		for (int y = 0; y < listcode.size(); y++) {
			Map mapjg = listcode.get(y);
			String codeKey = (String) mapjg.get("CODE_KEY");
			String name = (String) mapjg.get("NAME");
			int codelength = codeKey.length();
			String qyggz = "";
			String aqgly = "";
			String dwcy = "";
			String jcgbzcy = "";// 基础格表成员
			String zbry = "";// 值班人员
			if ("28".equals(type)) {
				if (!codeKey.equals("11050301") && !codeKey.equals("11050302") && !codeKey.equals("11050303")) {
					continue;
				}
				if (codeKey.equals("11050301")) {
					qyggz = "刘蕊";
					aqgly = "单晓玲";
					dwcy = "宋薇";
				}
				if (codeKey.equals("11050302")) {
					qyggz = "吴建华";
					aqgly = "单晓玲";
					dwcy = "孟培";
				}
				if (codeKey.equals("11050303")) {
					qyggz = "杨梅青";
					aqgly = "马莉芳";
					dwcy = "李倩";
				}
			} else if ("42".equals(type)) {
				if (!codeKey.equals("11050304") && !codeKey.equals("11050305") && !codeKey.equals("11050306")) {
					continue;
				}
				if (codeKey.equals("11050304")) {
					qyggz = "李红新";
					aqgly = "马莉芳";
					dwcy = "李红颖";
				}
				if (codeKey.equals("11050305")) {
					qyggz = "邢玫/郑芳芳";
					aqgly = "高云起";
					dwcy = "闫春玲";
				}
				if (codeKey.equals("11050306")) {
					qyggz = "";
					aqgly = "";
					dwcy = "";
				}
			} else if ("27".equals(type)) {
				if (!codeKey.equals("11050308") && !codeKey.equals("11050309") && !codeKey.equals("11050310")) {
					continue;
				}
				if (codeKey.equals("11050308")) {
					qyggz = "刘蕊";
					aqgly = "高云起";
					dwcy = "窦芳芳";
				}
				if (codeKey.equals("11050309")) {
					qyggz = "刘蕊";
					aqgly = "高云起";
					dwcy = "乔建平";
				}
				if (codeKey.equals("11050310")) {
					qyggz = "杨梅青";
					aqgly = "单晓玲";
					dwcy = "";
				}
			} else if ("31".equals(type)) {
				if (!codeKey.equals("11050311") && !codeKey.equals("11050312") && !codeKey.equals("11050314")
						&& !codeKey.equals("11050315") && !codeKey.equals("11050316")) {
					continue;
				}
				if (codeKey.equals("11050311")) {
					qyggz = "方兴亚";
					aqgly = "郑哲磊";
					dwcy = "丁晔";
				}
				if (codeKey.equals("11050312")) {
					qyggz = "方兴亚";
					aqgly = "郑哲磊";
					dwcy = "徐宏";
				}
				if (codeKey.equals("11050314")) {
					qyggz = "";
					aqgly = "";
					dwcy = "";
				}
				if (codeKey.equals("11050315")) {
					qyggz = "";
					aqgly = "";
					dwcy = "";
				}
				if (codeKey.equals("11050316")) {
					qyggz = "";
					aqgly = "";
					dwcy = "";
				}
			} else if ("32".equals(type)) {
				if (!codeKey.equals("11050505")) {
					continue;
				}
				if (codeKey.equals("11050505")) {
					qyggz = "方兴亚";
					aqgly = "郑哲磊";
					dwcy = "丁晔";
				}

			} else if ("svg2-6".equals(type)) {// 第二层一监区
				if (!codeKey.equals("1105030101") && !codeKey.equals("1105030102") && !codeKey.equals("1105030103")) {
					continue;
				}
				if (codeKey.equals("1105030101")) {
					jcgbzcy = "左丹阳 张海燕";
					zbry = "温宏波";
				}
				if (codeKey.equals("1105030102")) {
					jcgbzcy = "蔺君静 郑培";
					zbry = "温宏波";
				}
				if (codeKey.equals("1105030103")) {
					jcgbzcy = "赵志涛 孙高洁";
					zbry = "温宏波";
				}

			} else if ("svg2-5".equals(type)) {// 第二层二监区
				if (!codeKey.equals("1105030201") && !codeKey.equals("1105030202") && !codeKey.equals("1105030203")) {
					continue;
				}
				if (codeKey.equals("1105030201")) {
					jcgbzcy = "齐艳宏 于东洋 赵素巧";
					zbry = "李艳霞";
				}
				if (codeKey.equals("1105030202")) {
					jcgbzcy = "单晓玲 王美懿 周洋";
					zbry = "李艳霞";
				}
				if (codeKey.equals("1105030203")) {
					jcgbzcy = "张景洁 李晨曦 赵强";
					zbry = "李艳霞";
				}

			} else if ("svg2-9".equals(type)) {// 第二层三监区
				if (!codeKey.equals("1105030301") && !codeKey.equals("1105030302") && !codeKey.equals("1105030303")) {
					continue;
				}
				if (codeKey.equals("1105030301")) {
					jcgbzcy = "彭兴亮 李春燕 阮彩霞";
					zbry = "李苑";
				}
				if (codeKey.equals("1105030302")) {
					jcgbzcy = "孟丹 闫鹏 黎芳";
					zbry = "李苑";
				}
				if (codeKey.equals("1105030303")) {
					jcgbzcy = "黄俊亭 薛芳";
					zbry = "李苑";
				}

			} else if ("svg2-7".equals(type)) {// 第二层四监区
				if (!codeKey.equals("1105030401") && !codeKey.equals("1105030402") && !codeKey.equals("1105030403")) {
					continue;
				}
				if (codeKey.equals("1105030401")) {
					jcgbzcy = "田佳 邢晨思 宋颖";
					zbry = "杜莉微";
				}
				if (codeKey.equals("1105030402")) {
					jcgbzcy = "马莉芳 肖春征 吉晓波";
					zbry = "杜莉微";
				}
				if (codeKey.equals("1105030403")) {
					jcgbzcy = "尉迟肖博 成海静 马连梁";
					zbry = "杜莉微";
				}

			} else if ("svg2-8".equals(type)) {// 第二层五监区
				if (!codeKey.equals("1105030501") && !codeKey.equals("1105030502") && !codeKey.equals("1105030503")) {
					continue;
				}
				if (codeKey.equals("1105030501")) {
					jcgbzcy = "唐凤君 姜慧芳 李彦杰";
					zbry = "陈熹微";
				}
				if (codeKey.equals("1105030502")) {
					jcgbzcy = "李润波 周琼洁 马静";
					zbry = "陈熹微";
				}
				if (codeKey.equals("1105030503")) {
					jcgbzcy = "张晓 赵琳 段继祥";
					zbry = "陈熹微";
				}

			} else if ("svg2-10".equals(type)) {// 第二层六监区
				if (!codeKey.equals("1105030601") && !codeKey.equals("1105030602") && !codeKey.equals("1105030603")) {
					continue;
				}
				if (codeKey.equals("1105030601")) {
					jcgbzcy = "";
					zbry = "";
				}
				if (codeKey.equals("1105030602")) {
					jcgbzcy = "";
					zbry = "";
				}
				if (codeKey.equals("1105030603")) {
					jcgbzcy = "";
					zbry = "";
				}
			} else if ("svg2-11".equals(type)) {// 第二层七监区
				if (!codeKey.equals("1105030801") && !codeKey.equals("1105030802") && !codeKey.equals("1105030803")) {
					continue;
				}
				if (codeKey.equals("1105030801")) {
					jcgbzcy = "高云起 申艳 马元";
					zbry = "赵晔";
				}
				if (codeKey.equals("1105030802")) {
					jcgbzcy = "袁小龙 赵媛 高素平";
					zbry = "赵晔";
				}
				if (codeKey.equals("1105030803")) {
					jcgbzcy = "窦万山 张思明 李霁";
					zbry = "赵晔";
				}
			} else if ("svg2-12".equals(type)) {// 第二层八监区
				if (!codeKey.equals("1105030901") && !codeKey.equals("1105030902") && !codeKey.equals("1105030903")) {
					continue;
				}
				if (codeKey.equals("1105030901")) {
					jcgbzcy = "彭旗 杨东方 郑凤红";
					zbry = "魏贺春";
				}
				if (codeKey.equals("1105030902")) {
					jcgbzcy = "王艳 张伟平";
					zbry = "魏贺春";
				}
				if (codeKey.equals("1105030903")) {
					jcgbzcy = "杨玉玲 孔立萍 王帅";
					zbry = "魏贺春";
				}
			} else if ("svg2-14".equals(type)) {// 第二层九监区
				if (!codeKey.equals("1105031001") && !codeKey.equals("1105031002") && !codeKey.equals("1105031003")) {
					continue;
				}
				if (codeKey.equals("1105031001")) {
					jcgbzcy = "张希武 冯蕾";
					zbry = "郝媛";
				}
				if (codeKey.equals("1105031002")) {
					jcgbzcy = "曹晓颖 李洋";
					zbry = "郝媛";
				}
				if (codeKey.equals("1105031003")) {
					jcgbzcy = "";
					zbry = "";
				}
			} else if ("svg2-13".equals(type)) {// 第二层十监区
				if (!codeKey.equals("1105031101") && !codeKey.equals("1105031102") && !codeKey.equals("1105031103")) {
					continue;
				}
				if (codeKey.equals("1105031101")) {
					jcgbzcy = "郑哲磊 白俊维 丁庆玲";
					zbry = "李晓娜";
				}
				if (codeKey.equals("1105031102")) {
					jcgbzcy = "王雨 王晓华";
					zbry = "李晓娜";
				}
				if (codeKey.equals("1105031103")) {
					jcgbzcy = "李艳 单巧丽 戴春芳";
					zbry = "李晓娜";
				}
			} else if ("svg2-15".equals(type)) {// 第二层十一监区
				if (!codeKey.equals("1105031201") && !codeKey.equals("1105031202") && !codeKey.equals("1105031203")) {
					continue;
				}
				if (codeKey.equals("1105031201")) {
					jcgbzcy = "邢洪军 王晓义 王柏静";
					zbry = "刘景胜";
				}
				if (codeKey.equals("1105031202")) {
					jcgbzcy = "戴卫平 王海艳";
					zbry = "刘景胜";
				}
				if (codeKey.equals("1105031203")) {
					jcgbzcy = "胡焕美 孟佳 贾鸿君";
					zbry = "刘景胜";
				}
			} else {
				break;
			}
			String zsjg = "0";
			for (int i = 0; i < listfxd.size(); i++) {
				Map mapj = (Map) listfxd.get(i);
				String kczf = mapj.get("KCZF").toString();
				String wgOne1 = mapj.get("WG_TWO").toString();
				String wgOneName = mapj.get("WGTWONAME").toString();
				if (codeKey.equals(wgOne1)) {
					zsjg = kczf;
				}
			}
			Map mapr = new HashMap();
			mapr.put("name", name);
			mapr.put("code", codeKey);
			mapr.put("fxz", zsjg);
			mapr.put("qyggz", qyggz);
			mapr.put("aqgly", aqgly);
			mapr.put("dwcy", dwcy);
			mapr.put("jcgbzcy", jcgbzcy);
			mapr.put("zbry", zbry);
			list.add(mapr);

		}

		return list;
	}

	@Override
	public List getJqb() {
		//暂时只获取五家监狱的  怀化监狱"4336, "永州监狱"4304, "雁南监狱"4303, "女子监狱"4307, "岳阳监狱4312"的   数据  后续有 再加  
		List<Double>  resList = new ArrayList<Double>();
		String[] jyIds = {"4336","4304","4303","4307","4312"};
		String mytime = DateUtils.getCurrentDate(false);
		for (int i = 0; i < jyIds.length; i++) {
			Map<String,Object> mapZjMj = new HashMap<String, Object>();
			mapZjMj.put("cusNumber", jyIds[i]);
			//mapZjMj.put("mytime", mytime);
			//1.查询五家监狱当日警察的在监数量
			//Integer policeCountInPrison = commonMapper.getPoliceCountInPrison(mapZjMj);
			Integer policeCountZaiCePrison = commonMapper.getPoliceCountZaiCePrison(mapZjMj);
			//2.查询罪犯在押数量
			Map<String,Object> mapZyZf = new HashMap<String, Object>();
			mapZyZf.put("jyId", jyIds[i]);
			Integer criminalCount = criminalMapper.getCriminalCount(mapZyZf);
			 DecimalFormat df = new DecimalFormat("#.00");//保留两位小数
			 Double jqb=0.00;
			if(criminalCount!=0) {
				Double format = (double)policeCountZaiCePrison/criminalCount*100;
				 jqb =Double.parseDouble(df.format(format));
			}
			resList.add(jqb);
		}
		
		
		return resList;
	}

	@Override
	public Map<String,Object> getSssjl() {
		//同样先获取五家监狱的
		Map<String,Object> resMap = new HashMap<String, Object>();
		List<Integer> zfCountList = new ArrayList<Integer>();
		List<Integer> mjCountList = new ArrayList<Integer>();
		String[] jyIds = {"4336","4304","4303","4307","4312"};
		for (int i = 0; i < jyIds.length; i++) {
			Map<String,Object> mapZjMj = new HashMap<String, Object>();
			String mytime = DateUtils.getCurrentDate(false);
			mapZjMj.put("cusNumber", jyIds[i]);
			//mapZjMj.put("mytime", mytime);
			//1.查询五家监狱当日警察的在监数量
			//Integer policeCountInPrison = commonMapper.getPoliceCountInPrison(mapZjMj);
			Integer policeCountZaiCePrison = commonMapper.getPoliceCountZaiCePrison(mapZjMj);
			mjCountList.add(policeCountZaiCePrison);
			//2.查询罪犯在押数量
			Map<String,Object> mapZyZf = new HashMap<String, Object>();
			mapZyZf.put("jyId", jyIds[i]);
			Integer criminalCount = criminalMapper.getCriminalCount(mapZyZf);
			zfCountList.add(criminalCount);
		}
		resMap.put("zfCountList", zfCountList);
		resMap.put("mjCountList", mjCountList);
		return resMap;
	}

}
