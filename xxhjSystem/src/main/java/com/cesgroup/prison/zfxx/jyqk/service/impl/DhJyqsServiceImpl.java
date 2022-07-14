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
import com.cesgroup.prison.jobs.constants.Synchro;
import com.cesgroup.prison.zfxx.jyqk.dao.DhJyqsDao;
import com.cesgroup.prison.zfxx.jyqk.entity.DhJyqs;
import com.cesgroup.prison.zfxx.jyqk.service.DhJyqsService;
import com.cesgroup.prison.zfxx.jyqk.util.DhJyqkHttpclient;
import com.cesgroup.prison.zfxx.jyqk.util.DhJyqkUtil;

@Service("dhJyqsService")
@Transactional
public class DhJyqsServiceImpl extends BaseDaoService<DhJyqs, String, DhJyqsDao> implements DhJyqsService {

	@Autowired
	private DhJyqsDao dhJyqsDao;
	
	@Override
	public void synchroDhJyqs(String jyId, String cjpc) {
		try {
			Document doc = null;
			Date cjrq = new Date();
        	List<DhJyqs> list = new ArrayList<DhJyqs>();
        	DhJyqs temp = null;
            // 将字符串转为XML
        	String xml = DhJyqkHttpclient.pub("S0090", "2019-01-01", "2019-05-31", DhJyqkUtil.getPrisonAreaId(jyId));
        	if (xml != null) {
        		doc = (Document) DocumentHelper.parseText(xml);
                Element rootElt = doc.getRootElement(); 
                Iterator iter = rootElt.elementIterator("List"); 
                // 遍历head节点
                while (iter.hasNext()) {
                	 Element itemEle = (Element) iter.next();
                     String year = itemEle.elementTextTrim("Year"); 
                     String count = itemEle.elementTextTrim("Count");
                     if (year != null && count != null) {
                    	 temp = new DhJyqs();
                    	 temp.setId(Synchro.getUUID());
                    	 temp.setCjpc(cjpc);
                    	 temp.setCjrq(cjrq);
                    	 temp.setNf(year);
                    	 temp.setJyrc(new BigDecimal(count));
                    	 temp.setJyId(jyId);
                    	 list.add(temp);
                     }
                }
                if (list.size() > 0) {
                	this.batchInsert(list);
                }
        	}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void batchInsert(List<DhJyqs> list) {
		if (list != null && list.size() > 0) {
			int size = Synchro.BathSize.BATCH_SIZE_50;
			final int num = (list.size() % size == 0) ? (list.size() / size) : (list.size() / size + 1);
			for (int i = 0; i < num; i++) {
				if (i < num - 1) {
					dhJyqsDao.insertBatch(list.subList(i * size, (i + 1) * size));
				} else {
					dhJyqsDao.insertBatch(list.subList(i * size, list.size()));
				}
			}
		}
	}

}
