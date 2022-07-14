package com.cesgroup.prison.inspect.service.impl;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.inspect.dao.InspectCheckPoliceMapper;
import com.cesgroup.prison.inspect.dao.InspectLocalMapper;
import com.cesgroup.prison.inspect.dao.InspectLocalRelationMapper;
import com.cesgroup.prison.inspect.dao.InspectPoliceMapper;
import com.cesgroup.prison.inspect.entity.InspectCheckPolice;
import com.cesgroup.prison.inspect.entity.InspectLocal;
import com.cesgroup.prison.inspect.entity.InspectLocalRelation;
import com.cesgroup.prison.inspect.entity.InspectPolice;
import com.cesgroup.prison.inspect.service.InspectLocalService;
/**
 * 
 * @author zhengk
 * @date 2018-03-23
 *
 */
@Service
public class InspectLocalServiceImpl extends BaseService<InspectLocal, String> implements InspectLocalService{

	@Autowired
	InspectLocalMapper inspectLocalMapper;
	@Autowired
	InspectPoliceMapper inspectPoliceMapper;
	@Autowired
	InspectCheckPoliceMapper inspectCheckPoliceMapper;
	@Autowired
	InspectLocalRelationMapper inspectLocalRelationMapper;

	@Override
	@Transactional
	public void addInspectInfo(InspectLocal ins) {
		//添加督察通报记录
		ins.setInlCrteTime(new Date());
		ins.setInlUpdtTime(new Date());
		ins.setInlUpdtUserId(ins.getInlCrteUserId());
		ins.setInlUpdtUserName(ins.getInlCrteUserName());
		ins.setInlApprovalSttsIndc("0");
		InspectLocal inspectLocal_tmp=this.insert(ins);
		if(ins.getGoodList()!=null) {
			for(InspectLocalRelation ir : ins.getGoodList()){
				ir.setInrInspectId(inspectLocal_tmp.getId());
				ir.setInrCusNumber(ins.getInlCusNumber());
				ir.setInrCrteTime(new Date());
				ir.setInrCrteUserId(ins.getInlCrteUserId());
				ir.setInrCrteUserName(ins.getInlCrteUserName());
				ir.setInrUpdtTime(new Date());
				ir.setInrUpdtUserId(ins.getInlCrteUserId());
				ir.setInrUpdtUserName(ins.getInlCrteUserName());
				inspectLocalRelationMapper.insert(ir);
			}
		}
		
		if(ins.getBadList()!=null) {
			for(InspectLocalRelation ir : ins.getBadList()){
				ir.setInrInspectId(inspectLocal_tmp.getId());
				ir.setInrCusNumber(ins.getInlCusNumber());
				ir.setInrCrteTime(new Date());
				ir.setInrCrteUserId(ins.getInlCrteUserId());
				ir.setInrCrteUserName(ins.getInlCrteUserName());
				ir.setInrUpdtTime(new Date());
				ir.setInrUpdtUserId(ins.getInlCrteUserId());
				ir.setInrUpdtUserName(ins.getInlCrteUserName());
				inspectLocalRelationMapper.insert(ir);
			}
		}
		
	
		//添加督察人员记录
		InspectPolice ip = new InspectPolice();
		ip.setIprInspectId(inspectLocal_tmp.getId());
		ip.setIprCrteTime(new Date());
		ip.setIprCrteUserId(ins.getInlCrteUserId());
		ip.setIprCrteUserName(ins.getInlCrteUserName());
		ip.setIprUpdtTime(new Date());
		ip.setIprUpdtUserId(ins.getInlCrteUserId());
		ip.setIprUpdtUserName(ins.getInlCrteUserName());
		ip.setIprTypeIndc("1");                     //表示现场督察上报
		String[] iprPoliceNames = ins.getIprPoliceNames().split(",");
		String[] iprPoliceIdntys = ins.getIprPoliceIdntys().split(",");
		for(int i=0 ; i<iprPoliceIdntys.length ; i++){
			String poName = iprPoliceNames[i];
			String poIdnty = iprPoliceIdntys[i];
			ip.setIprCusNumber(ins.getInlCusNumber());
			ip.setIprPoliceIdnty(poIdnty);
			ip.setIprPoliceName(poName);
			ip.setId(null);
			inspectPoliceMapper.insert(ip);
		}
		
		//添加审批人员信息
		InspectCheckPolice cp = new InspectCheckPolice();
		cp.setIcpInspectId(inspectLocal_tmp.getId());
		cp.setIcpCrteTime(new Date());
		cp.setIcpCrteUserId(ins.getInlCrteUserId());
		cp.setIcpCrteUserName(ins.getInlCrteUserName());
		cp.setIcpUpdtTime(new Date());
		cp.setIcpUpdtUserId(ins.getInlCrteUserId());
		cp.setIcpUpdtUserName(ins.getInlCrteUserName());
		cp.setIcpInspectTypeIndc("1");                     //表示网络督察上报
		cp.setIcpPoliceIdnty(ins.getCheckPoliceIdnty());
		cp.setIcpPoliceName(ins.getCheckPoliceName());
		cp.setIcpCusNumber(ins.getInlCusNumber());
		inspectCheckPoliceMapper.insert(cp);
	}

	@Override
	public Page<InspectLocal> inspectlocallistPage(Map<String, Object> paramMap, PageRequest pageRequest) {
		return inspectLocalMapper.inspectlocallistPage(paramMap, pageRequest);
	}

	@Override
	public Page<InspectLocal> inspectlocalHzListPage(Map<String, Object> paramMap, PageRequest pageRequest) {
		return inspectLocalMapper.inspectlocalHzListPage(paramMap, pageRequest);
	}

	@Override
	public List<InspectLocal> inspectlocallist(InspectLocal ins) {
		return inspectLocalMapper.inspectlocallist(ins);
	}

	@Override
	@Transactional
	public void updateInspectLocal(InspectLocal ins) {
		inspectLocalMapper.updateInspectLocal(ins);
		if(!"".equals(ins.getCheckPoliceIdnty()) && ins.getCheckPoliceIdnty()!=null){
			InspectCheckPolice cp = new InspectCheckPolice();
			cp.setIcpInspectId(ins.getId());
			cp.setIcpUpdtTime(new Date());
			cp.setIcpUpdtUserId(ins.getInlUpdtUserId());
			cp.setIcpUpdtUserName(ins.getInlUpdtUserName());
			cp.setIcpPoliceIdnty(ins.getCheckPoliceIdnty());
			cp.setIcpPoliceName(ins.getCheckPoliceName());
			cp.setIcpCusNumber(ins.getInlCusNumber());
			inspectCheckPoliceMapper.updateByIcpInspectId(cp);
		}
	}

	@Override
	public Page<InspectLocalRelation> inspectLocalRelationListPage(Map<String, Object> paramMap,
			PageRequest pageRequest) {
		return inspectLocalRelationMapper.inspectLocalRelationListPage(paramMap, pageRequest);
	}
}
