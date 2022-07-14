package com.cesgroup.prison.inspect.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.cesgroup.framework.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.aqfk.monitor.service.MonitorService;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.inspect.dao.InspectCheckPoliceMapper;
import com.cesgroup.prison.inspect.dao.InspectMapper;
import com.cesgroup.prison.inspect.dao.InspectPoliceMapper;
import com.cesgroup.prison.inspect.entity.Inspect;
import com.cesgroup.prison.inspect.entity.InspectCheckPolice;
import com.cesgroup.prison.inspect.entity.InspectPolice;
import com.cesgroup.prison.inspect.service.InspectService;

import dm.jdbc.driver.DmdbClob;
import dm.jdbc.driver.DmdbClobWriter;
/**
 * 网络督查通报
 * @author zhengk
 * @date 2018-03-21
 *
 */
@Service
public class InspectServiceImpl extends BaseService<Inspect, String> implements InspectService{

	@Autowired
	InspectMapper inspectMapper;
	@Autowired
	InspectPoliceMapper inspectPoliceMapper;
	@Autowired
	InspectCheckPoliceMapper inspectCheckPoliceMapper;
	@Autowired
	private MonitorService monitorService;

	@Override
	@Transactional
	public void addInspectInfo(Inspect ins){
		//添加督察通报记录
		ins.setInoCrteTime(new Date());
		ins.setInoUpdtTime(new Date());
		ins.setInoUpdtUserId(ins.getInoCrteUserId());
		ins.setInoUpdtUserName(ins.getInoCrteUserName());
		ins.setInoApprovalSttsIndc("0");
		
		Inspect ins_tmp=new Inspect();
		String id = ins.getId();	
		if(id != null && !"".equals(id)) {
			inspectPoliceMapper.deleteByIprInspectId(id);
			inspectCheckPoliceMapper.deleteByIcpInspectId(id);
			monitorService.deleteMonitorByInspectId(id);
			ins_tmp=this.update(ins);
		} else {			
			ins_tmp=this.insert(ins);			
		}
	
		//添加督察人员记录
		InspectPolice ip = new InspectPolice();
		ip.setIprInspectId(ins_tmp.getId());
		ip.setIprCrteTime(new Date());
		ip.setIprCrteUserId(ins.getInoCrteUserId());
		ip.setIprCrteUserName(ins.getInoCrteUserName());
		ip.setIprUpdtTime(new Date());
		ip.setIprUpdtUserId(ins.getInoCrteUserId());
		ip.setIprUpdtUserName(ins.getInoCrteUserName());
		ip.setIprTypeIndc("0");                     //表示网络督察上报
		String[] iprPoliceNames = ins.getIprPoliceNames().split(",");
		String[] iprPoliceIdntys = ins.getIprPoliceIdntys().split(",");
		
		for(int i=0 ; i<iprPoliceIdntys.length ; i++){
			String poName = iprPoliceNames[i];
			String poIdnty = iprPoliceIdntys[i];
			String cusNumber = ins.getInoCusNumber();
			ip.setIprCusNumber(cusNumber);
			ip.setIprPoliceIdnty(poIdnty);
			ip.setIprPoliceName(poName);
			ip.setId(null);
			inspectPoliceMapper.insert(ip);
		}
		
		//添加审批人员信息
		InspectCheckPolice cp = new InspectCheckPolice();
		cp.setIcpInspectId(ins_tmp.getId());
		cp.setIcpCrteTime(new Date());
		cp.setIcpCrteUserId(ins.getInoCrteUserId());
		cp.setIcpCrteUserName(ins.getInoCrteUserName());
		cp.setIcpUpdtTime(new Date());
		cp.setIcpUpdtUserId(ins.getInoCrteUserId());
		cp.setIcpUpdtUserName(ins.getInoCrteUserName());
		cp.setIcpInspectTypeIndc("0");                     //表示网络督察上报
		
		cp.setIcpPoliceIdnty(ins.getCheckPoliceIdnty());
		cp.setIcpPoliceName(ins.getCheckPoliceName());
		cp.setIcpCusNumber(ins.getInoCusNumber());
		inspectCheckPoliceMapper.insert(cp);

	}
	//巡查通报列表（本监狱发出的）
	@Override
	public Page<Inspect> inspectListPage(Map<String, Object> paramMap, PageRequest pageRequest){
		return inspectMapper.inspectListPage(paramMap,pageRequest);
	}
	@Override
	public Page<Inspect> inspectSpListPage(Map<String, Object> paramMap, PageRequest pageRequest) {
		return inspectMapper.inspectSpListPage(paramMap,pageRequest);
	}
	//汇总列表
	@Override
	public Page<Inspect> inspectHzListPage(Map<String, Object> paramMap, PageRequest pageRequest) {
		return inspectMapper.inspectHzListPage(paramMap,pageRequest);
	}

	@Override
	public List<Inspect> inspectlist(Inspect ins) {
		return inspectMapper.inspectlist(ins);
	}

	//根据id查询电子通报文档
	@Override
	public String findInoInspectDocumentById(String id) {
		Map<String, Object> map = inspectMapper.findInoInspectDocumentById(id);
		if(map==null || map.get("INO_INSPECT_DOCUMENT")==null) {
			return "";
		}
		String inoInspectDocument = (String)map.get("INO_INSPECT_DOCUMENT");
		String absoultPath = CommonConstant.systemRootAbsoultPath 
				+File.separator+CommonConstant.ftpInspectDocPath;
		String result=FileUtil.readFile(absoultPath+inoInspectDocument, null);
		return result;
	}
	
	@Override
	@Transactional
	public void updateInspect(Inspect ins) {
		inspectMapper.updateInspect(ins);
		if(!"".equals(ins.getCheckPoliceIdnty()) && ins.getCheckPoliceIdnty()!=null){
			InspectCheckPolice cp = new InspectCheckPolice();
			cp.setIcpInspectId(ins.getId());
			cp.setIcpUpdtTime(new Date());
			cp.setIcpUpdtUserId(ins.getInoUpdtUserId());
			cp.setIcpUpdtUserName(ins.getInoUpdtUserName());
			cp.setIcpPoliceIdnty(ins.getCheckPoliceIdnty());
			cp.setIcpPoliceName(ins.getCheckPoliceName());
			cp.setIcpCusNumber(ins.getInoCusNumber());
			inspectCheckPoliceMapper.updateByIcpInspectId(cp);
		}
	}
	
	//创建巡查通报文档
	@Override
	@Transactional
	public void createInspectFile(Map<String, Object> paramMap) {
		String cusNumber = (String)paramMap.get("cusNumber");
		String inspectId = (String)paramMap.get("inspectId");
		String inspectHTML = (String)paramMap.get("inspectHTML");
		//上传到ftp的路径，格式：年/月/日/监狱编号
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH)+1; 
		
		String absoultPath = CommonConstant.systemRootAbsoultPath 
				+File.separator+CommonConstant.ftpInspectDocPath;
		String filePath = File.separator+year+File.separator+month
				+File.separator+cusNumber;
		String fileName = inspectId+".txt";
		String inoInspectDocument=filePath+File.separator+fileName;
		
		FileUtil.writeToFile(absoultPath+filePath,fileName,inspectHTML,null);
		
		Inspect inspect = new Inspect();
		inspect.setId(inspectId);
		inspect.setInoInspectDocument(inoInspectDocument);
		inspectMapper.updateInspect(inspect);		
	}
	@Override
	@Transactional
	public void inspectDelete(String id) {
		this.getDao().delete(id);
		inspectPoliceMapper.deleteByIprInspectId(id);
		inspectCheckPoliceMapper.deleteByIcpInspectId(id);
		monitorService.deleteMonitorByInspectId(id);
	}
	@Override
	public Inspect findById(String id) {
		return this.getDao().selectOne(id);
	}
}
