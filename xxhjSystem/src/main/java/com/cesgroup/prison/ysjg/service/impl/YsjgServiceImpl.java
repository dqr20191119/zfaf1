package com.cesgroup.prison.ysjg.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.utils.PropertiesUtil;
import com.cesgroup.prison.jobs.utils.HttpClientUtil;
import com.cesgroup.prison.ysjg.dao.DieDao;
import com.cesgroup.prison.ysjg.dao.ExpenseDao;
import com.cesgroup.prison.ysjg.dao.PurchaseDao;
import com.cesgroup.prison.ysjg.dao.YsjgDao;
import com.cesgroup.prison.ysjg.entity.DieEntity;
import com.cesgroup.prison.ysjg.entity.ExpenseEntity;
import com.cesgroup.prison.ysjg.entity.PurchaseEntity;
import com.cesgroup.prison.ysjg.entity.YsjgEntity;
import com.cesgroup.prison.ysjg.service.YsjgService;


@Service("ysjgService")
public class YsjgServiceImpl extends BaseDaoService<YsjgEntity,String,YsjgDao> implements YsjgService {

	@Autowired
	private YsjgDao ysjgDao;
	
	@Autowired
	private PurchaseDao purchaseDao;
	
	@Autowired
	private ExpenseDao expenseDao;
	
	@Autowired
	private DieDao dieDao;

	private static String ysjgurl = PropertiesUtil.getValueByKeyUnchanged("application", "synchro.kh.ysjg.url"); //http://192.168.8.188:8080/PMS/inventoryDataList

	@Override
	public void getYsjg(String type, String secCode, String period,String jyId) {

		String url = ysjgurl+"?type="+type+"&secCode="+secCode+"&period="+period;
		String fhjg = HttpClientUtil.doGet(url);
		List<Map> goodsList = new ArrayList<Map>();
		goodsList= JSONArray.parseArray(fhjg,Map.class);
		String hqsj="";
		String zbId = UUID.randomUUID().toString();
		
		for(int i = 0 ;i<goodsList.size();i++){
			Map map = goodsList.get(i);
			List purchaseList = (List) map.get("purchaseList");
			for(int y = 0;y<purchaseList.size();y++){
				Map mapPur = (Map) purchaseList.get(y);
				String Amount = String.valueOf(mapPur.get("Amount"));
				String Name =  String.valueOf( mapPur.get("Name"));
				PurchaseEntity purchase = new PurchaseEntity();
				purchase.setYsjgId(zbId);
				purchase.setAmount(Amount);
				purchase.setName(Name);
				purchaseDao.insert(purchase);
			}
			
			hqsj = (String) map.get("Time");
			List expenseList = (List) map.get("expenseList");
			for(int y = 0;y<expenseList.size();y++){
				Map mapExpense = (Map) expenseList.get(y);
				String Funds =  String.valueOf(mapExpense.get("Funds"));
				String etype =  String.valueOf( mapExpense.get("type"));
				ExpenseEntity expenseEntity = new ExpenseEntity();
				expenseEntity.setFunds(Funds);
				expenseEntity.setYsjgId(zbId);
				expenseEntity.setType(etype);
				expenseDao.insert(expenseEntity);
				
			}
			List dietList = (List) map.get("dietList");
			for(int y = 0;y<dietList.size();y++){
				Map mapDie= (Map) dietList.get(y);
				String ActualProportion =  String.valueOf( mapDie.get("ActualProportion"));
				String StandardProportion =  String.valueOf( mapDie.get("StandardProportion"));
				String DType =  String.valueOf( mapDie.get("Type"));
				DieEntity die = new DieEntity();
				die.setYsjgId(zbId);
				die.setActualProportion(ActualProportion);
				die.setStandardProportion(StandardProportion);
				die.setType(DType);
				dieDao.insert(die);
			}
		}
		
		YsjgEntity ysjgEntity = new YsjgEntity();
		ysjgEntity.setJyId(jyId);
		ysjgEntity.setJqId("");
		ysjgEntity.setHqTime(hqsj);
		ysjgEntity.setHqType(period);
		ysjgEntity.setType(type);
		ysjgEntity.setId(zbId);
		ysjgDao.insert(ysjgEntity);
		
	}
	
	public static void main(String[] args) {
		YsjgServiceImpl YsjgServiceImpl = new YsjgServiceImpl();
		YsjgServiceImpl.getYsjg("2", "0", "1y","1100");
	}
	
	
}
