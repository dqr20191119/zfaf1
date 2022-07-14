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
import com.cesgroup.prison.zfxx.jyqk.dao.DhMbqkDao;
import com.cesgroup.prison.zfxx.jyqk.entity.DhMbqk;
import com.cesgroup.prison.zfxx.jyqk.service.DhMbqkService;
import com.cesgroup.prison.zfxx.jyqk.util.DhJyqkHttpclient;
import com.cesgroup.prison.zfxx.jyqk.util.DhJyqkUtil;

@Service("dhMbqkService")
@Transactional
public class DhMbqkServiceImpl extends BaseDaoService<DhMbqk, String, DhMbqkDao> implements DhMbqkService {

	@Autowired
	private DhMbqkDao dhMbqkDao;
	
	@Override
	public void synchroDhMbqk(String jyId, String cjpc) {
		try {
			Document doc = null;
			Date cjrq = new Date();
        	List<DhMbqk> list = new ArrayList<DhMbqk>();
        	DhMbqk temp = null;
            // 将字符串转为XML
        	String xml = DhJyqkHttpclient.pub("S0091", Util.getCurrentYear() + "-01-01", Util.getYesterdayDate(false), DhJyqkUtil.getPrisonAreaId(jyId));
        	if (xml != null) {
        		doc = (Document) DocumentHelper.parseText(xml);
                Element rootElt = doc.getRootElement(); 
                Iterator iter = rootElt.elementIterator("List"); 
                // 遍历head节点
                while (iter.hasNext()) {
                	 Element itemEle = (Element) iter.next();
                     String chronicType = itemEle.elementTextTrim("ChronicType"); 
                     String count = itemEle.elementTextTrim("Count");
                     if (chronicType != null && count != null) {
                    	 temp = new DhMbqk();
                    	 temp.setId(Synchro.getUUID());
                    	 temp.setCjpc(cjpc);
                    	 temp.setCjrq(cjrq);
                    	 temp.setMblx(chronicType);
                    	 temp.setMbrc(new BigDecimal(count));
                    	 temp.setJyId(jyId);
                    	 list.add(temp);
                     }
                }
                if (list.size() > 0) {
                	dhMbqkDao.deleteByJyId(jyId);
                	this.batchInsert(list);
                }
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void batchInsert(List<DhMbqk> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					dhMbqkDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					dhMbqkDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}

}
