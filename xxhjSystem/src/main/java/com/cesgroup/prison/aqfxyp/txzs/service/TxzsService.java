package com.cesgroup.prison.aqfxyp.txzs.service;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.aqfxyp.txzs.entity.Txzs;
 
public interface TxzsService extends  IBaseCRUDService<Txzs, String> {

 
	
	public List getfxgk(String jyId,String wgOne,String wgTwo,String wgThree);
	
	public List getPies(String jyId,String wgOne,String wgTwo,String wgThree);
	
	public List getBars(String jyId,String wgOne,String wgTwo,String wgThree);
	
	public List fxfbData(String jyId,String wgOne,String wgTwo,String wgThree);
	
	public List getfxqs(String year,String jyId,String wgOne,String wgTwo,String wgThree);
	

	public List wgfb(String type,String jyId,String wgOne,String wgTwo,String wgThree);

	public List getJqb();

	public Map<String,Object> getSssjl();
	
	
	
}
