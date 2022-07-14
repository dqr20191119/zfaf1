package com.cesgroup.prison.zfxx.jyqk.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.jobs.constants.Synchro;
import com.cesgroup.prison.zfxx.jyqk.dao.DhJyqkDao;
import com.cesgroup.prison.zfxx.jyqk.entity.DhJyqk;
import com.cesgroup.prison.zfxx.jyqk.service.DhJyqkService;
import com.cesgroup.prison.zfxx.jyqk.util.DhJyqkHttpclient;
import com.cesgroup.prison.zfxx.jyqk.util.DhJyqkUtil;

@Service("dhJyqkService")
@Transactional
public class DhJyqkServiceImpl extends BaseDaoService<DhJyqk, String, DhJyqkDao> implements DhJyqkService {

	@Autowired
	private DhJyqkDao dhJyqkDao;

	@Override
	public void synchroDhJyqk(String jyId, String cjpc) {
		try {
			Document doc = null;
			Date cjrq = new Date();
        	List<DhJyqk> list = new ArrayList<DhJyqk>();
        	DhJyqk temp = null;
            // 将字符串转为XML
        	String xml = DhJyqkHttpclient.pub("S0089", Util.getCurrentYear() + "-01-01", Util.getYesterdayDate(false), DhJyqkUtil.getPrisonAreaId(jyId));
        	if (xml != null) {
        		doc = (Document) DocumentHelper.parseText(xml);
                Element rootElt = doc.getRootElement();
                Iterator iter = rootElt.elementIterator("List");
                // 遍历head节点
                while (iter.hasNext()) {
                	 Element itemEle = (Element) iter.next();
                     String prisonAreaName = itemEle.elementTextTrim("PrisonAreaName");
                     String count = itemEle.elementTextTrim("Count");
                     if (prisonAreaName != null && count != null) {
                    	 temp = new DhJyqk();
                    	 temp.setId(Synchro.getUUID());
                    	 temp.setCjpc(cjpc);
                    	 temp.setCjrq(cjrq);
                    	 temp.setJqMc(prisonAreaName);
                    	 temp.setJyrc(new BigDecimal(count));
                    	 temp.setJyId(jyId);
                    	 list.add(temp);
                     }
                }
                if (list.size() > 0) {
                	dhJyqkDao.deleteByJyId(jyId);
                	this.batchInsert(list);
                }
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	private void batchInsert(List<DhJyqk> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					dhJyqkDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					dhJyqkDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}
	
}
