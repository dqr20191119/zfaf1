package com.cesgroup.prison.wwjg.fxgkgl.service.impl;
 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.wwjg.fxgkgl.entity.FxgkglEntity;
import com.cesgroup.prison.wwjg.fxgkgl.service.FxgkglService;
import com.cesgroup.prison.wwjg.fxgkgl.dao.FxgkglMapper;
@Service("FxgkglService")
public class FxgkglServiceImpl extends BaseDaoService<FxgkglEntity, String, FxgkglMapper> implements FxgkglService {
	
	@Resource
	private FxgkglMapper FxgkglMapper;
	
	@Override
	public FxgkglEntity getById(String id) { 
		
		FxgkglEntity FxgkglEntity = FxgkglMapper.getById(id);
		
		return FxgkglEntity;
	}

	@Override
	public Page<FxgkglEntity> findList(FxgkglEntity FxgkglEntity, PageRequest pageRequest) {
		return FxgkglMapper.findList(FxgkglEntity, pageRequest);
	}

	@Override
	public List<FxgkglEntity> findAllList(FxgkglEntity FxgkglEntity) {
		
		return FxgkglMapper.findAllList(FxgkglEntity);
	}
	
	@Override
	@Transactional
	public void saveOrUpdate(FxgkglEntity FxgkglEntity) throws Exception {

		String id =FxgkglEntity.getId();		
		if(id != null && !"".equals(id)) {
			FxgkglMapper.update(FxgkglEntity);
		} else {			
			FxgkglMapper.insert(FxgkglEntity);			
		}
		
	}
	
	@Override
	@Transactional
	public void deleteByIds(String ids) {

		String[] idArr = ids.split(",");
		FxgkglMapper.updateStatusByIds(Arrays.asList(idArr));
 		
	} 
	
	@Override
	public FxgkglEntity getByCode(String code) { 
		
		FxgkglEntity FxgkglEntity = FxgkglMapper.getByCode(code);
		
		return FxgkglEntity;
	}

	@Override
	public FxgkglEntity getByFxcjId(String id) {
		// TODO Auto-generated method stub
		List<FxgkglEntity> list = FxgkglMapper.getByFxcjId(id);
		FxgkglEntity fxgkglEntity = new FxgkglEntity();
		for(int i = 0;i<list.size();i++){
			fxgkglEntity = list.get(i);
			break;
		}
		return fxgkglEntity;
	}

	@Override
	public Page<Map<String, Object>> findListWdgz( Pageable pageable) {
		Page<Map<String, Object>> page = null;
		try{
		
		// TODO Auto-generated method stub
		Map map1 = new HashMap();
		map1.put("GZLX", "政治改造");
		map1.put("FZLX", "基础分");
		map1.put("GZNR", "罪犯能够坚持五个认同，真诚认罪悔罪，提交政治改造决心书的，得3分；不达标的该项不得分。");
		map1.put("FZ", "3分");
		Map map2 = new HashMap();
		map2.put("GZLX", "政治改造");
		map2.put("FZLX", "基础分");
		map2.put("GZNR", "罪犯能够拥护党的路线方针政策、维护国家宪法权威，在党的知识或宪法知识测试时考核合格的，得1分；不合格的该项不得分。");
		map2.put("FZ", "1分");
		Map map3 = new HashMap();
		map3.put("GZLX", "政治改造");
		map3.put("FZLX", "基础分");
		map3.put("GZNR", "罪犯月度内能够按照要求积极参加监区（分监区）、监狱或省局组织的政治思想教育课程的，得1分；无故不参加的，该项不得分。");
		map3.put("FZ", "1分");
		Map map4 = new HashMap();
		map4.put("GZLX", "政治改造");
		map4.put("FZLX", "基础分");
		map4.put("GZNR", "罪犯每日能够按照要求收看中央电视台新闻联播活动的，得1分；无故不参加的，该项不得分。");
		map4.put("FZ", "1分");
		Map map5 = new HashMap();
		map5.put("GZLX", "政治改造");
		map5.put("FZLX", "奖励分");
		map5.put("GZNR", "罪犯能够积极参加监区（分监区）、监狱或省局组织的以政治改造为主题的各类活动的，加1分，积极参加并在活动中获得奖项或名次的，加2分；未参加的，该项不加分。");
		map5.put("FZ", "1分");
		Map map6 = new HashMap();
		map6.put("GZLX", "监管改造");
		map6.put("FZLX", "基础分");
		map6.put("GZNR", "罪犯月度内能够遵守法律法规及监规队纪，无违规违纪行为受到扣分以上处理的，得2.5分；罪犯月度内发生违规违纪行为计分考核扣分分值少于4分的扣减0.5分，扣分分值达到4分以上的扣减1分；罪犯月度内发生违规违纪行为受到警告处罚的扣减1.5分，受到记过处罚的扣减2分，受到禁闭以上处罚的扣减2.5分。");
		map6.put("FZ", "2.5分");
		Map map7 = new HashMap();
		map7.put("GZLX", "监管改造");
		map7.put("FZLX", "奖励分");
		map7.put("GZNR", "罪犯月度内在监管改造方面因表现突出，在全额获取计分考核基本分100分的前提下，受到加分奖励累计达到5分以上的，加1分，加分奖励累计少于5分的，加0.5分；未受到额外加分的，该项不加分。");
		map7.put("FZ", "5分");
		Map map8 = new HashMap();
		map8.put("GZLX", "监管改造");
		map8.put("FZLX", "奖励分");
		map8.put("GZNR", "3个月内罪犯家庭会见、亲情电话和家庭汇款有一项有记录的，加0.5分；3个月内无任何记录的，该项不加分。");
		map8.put("FZ", "0.5分");
		Map map9 = new HashMap();
		map9.put("GZLX", "教育改造");
		map9.put("FZLX", "基础分");
		map9.put("GZNR", "罪犯能够按照要求参加各类教育评估的，得1分；未按照要求参加各类教育评估的，扣减1分。");
		map9.put("FZ", "1分");
		
		Map map10 = new HashMap();
		map10.put("GZLX", "教育改造");
		map10.put("FZLX", "基础分");
		map10.put("GZNR", "罪犯参加心理互助组活动，协助民警对同犯开展心理互助的，得1分；经民警评定未参加活动、发挥互助作用的，扣减1分。");
		map10.put("FZ", "1分");
		
		Map map20 = new HashMap();
		map20.put("GZLX", "教育改造");
		map20.put("FZLX", "奖励分");
		map20.put("GZNR", "罪犯参加教育改造方面相关考试时成绩合格的，加1分；成绩不合格的，该项不加分。");
		map20.put("FZ", "1分");
		
		Map map11 = new HashMap();
		map11.put("GZLX", "教育改造");
		map11.put("FZLX", "奖励分");
		map11.put("GZNR", "罪犯半年内参加过亲情帮教或社会帮教活动的，加0.5分；未参加的，该项不加分。");
		map11.put("FZ", "0.5分");
		Map map12 = new HashMap();
		map12.put("GZLX", "教育改造");
		map12.put("FZLX", "奖励分");
		map12.put("GZNR", "罪犯被评为优秀心理互助组组长的，加0.5分；未被评为优秀心理互助组组长的，该项不加分。");
		map12.put("FZ", "0.5分");
		
		Map map13 = new HashMap();
		map13.put("GZLX", "文化改造");
		map13.put("FZLX", "基础分");
		map13.put("GZNR", "罪犯月度内能够常态参加监区（分监区）或监狱组织开展的文化教育或文体活动的，得2分；参加次数少于应参加次数或未按照要求参加的，扣减1分，无故不参加的，扣减2分。");
		map13.put("FZ", "2分");
		
		Map map14 = new HashMap();
		map14.put("GZLX", "文化改造");
		map14.put("FZLX", "基础分");
		map14.put("GZNR", "罪犯能够常态参与监区文化建设的，得1分；经民警评定罪犯参加后表现不积极的扣减0.5分，无故不参加的扣减1分。");
		map14.put("FZ", "1分");
		
		Map map19 = new HashMap();
		map19.put("GZLX", "文化改造");
		map19.put("FZLX", "奖励分");
		map19.put("GZNR", "罪犯能够积极参加弘扬社会主义核心价值观主题教育活动，并能做到熟练掌握社会主义核心价值观基本内容的，加0.5分；未参加或不能熟练掌握社会主义核心价值观基本内容的，该项不加分");
		map19.put("FZ", "0.5分");
		
		Map map15 = new HashMap();
		map15.put("GZLX", "文化改造");
		map15.put("FZLX", "奖励分");
		map15.put("GZNR", "罪犯能够积极参加优秀传统文化教育活动的，加0.5分；未参加的，该项不加分。");
		map15.put("FZ", "0.5分");
		
		Map map16 = new HashMap();
		map16.put("GZLX", "劳动改造");
		map16.put("FZLX", "基础分");
		map16.put("GZNR", "罪犯月度内参加生产劳动任务，劳动考核分总分值大于35分的，得3.5分；劳动考核分分值达到35分的，得3分；劳动考核分分值达到21分小于35分的，得2分；劳动考核分分值小于21分的，得1分；劳动考核分值为零分或负分的，该项直接扣减3.5分。");
		map16.put("FZ", "3.5分");
		
		Map map17 = new HashMap();
		map17.put("GZLX", "劳动改造");
		map17.put("FZLX", "奖励分");
		map17.put("GZNR", "罪犯能够积极参加职业技能培训的，加0.5分；参加职业技能培训并获得相应职业技能等级证书的，加1分；未参加的，该项不加分。");
		map17.put("FZ", "1分");
		
		Map map18 = new HashMap();
		map18.put("GZLX", "劳动改造");
		map18.put("FZLX", "奖励分");
		map18.put("GZNR", "罪犯能够积极参加就业推介、就业指导活动的，加0.5分；未参加的，该项不加分。");
		map18.put("FZ", "0.5分");
		
		List list = new ArrayList();
		list.add(map1);
		list.add(map2);
		list.add(map3);
		list.add(map4);
		list.add(map5);
		list.add(map6);
		list.add(map7);
		list.add(map8);
		list.add(map9);
		list.add(map10);
		list.add(map11);
		list.add(map12);
		list.add(map13);
		list.add(map14);
		list.add(map15);
		list.add(map16);
		list.add(map17);
		list.add(map18);
		list.add(map19);
		list.add(map20);
		Map mapppp = new HashMap();
		 page = FxgkglMapper.finndListWDGZ(mapppp,pageable);
		List<Map<String, Object>> listpage = page.getContent();
		listpage.clear();
		listpage.addAll(list);
		
		}catch(Exception e){
			e.printStackTrace();
		}
		return page;
	}
}
