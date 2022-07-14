package com.cesgroup.prison.change.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.change.dao.ChangeApprovalMapper;
import com.cesgroup.prison.change.dao.ChangeDepartmentMapper;
import com.cesgroup.prison.change.dao.ChangeMapper;
import com.cesgroup.prison.change.dao.ChangePeopleMapper;
import com.cesgroup.prison.change.entity.Change;
import com.cesgroup.prison.change.entity.ChangeApproval;
import com.cesgroup.prison.change.entity.ChangeDepartment;
import com.cesgroup.prison.change.entity.ChangePeople;
import com.cesgroup.prison.change.service.ChangeService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.CommonUtil;

/**    
* @Description： 通报整改Service
* @author：zhengk  
* @date：2018-03-26 09:45   
* @version        
*/
@Service
public class ChangeServiceImpl extends BaseDaoService<Change,String,ChangeMapper> implements ChangeService{

	@Autowired
	private ChangeDepartmentMapper changeDepartmentMapper;
	@Autowired
	private ChangePeopleMapper changePeopleMapper;
	@Autowired
	private ChangeApprovalMapper changeApprovalMapper;
	
	@Override
	public Page<Map<String, String>> launchListPage(Map<String, Object> paramMap, PageRequest pageRequest) {
		return this.getDao().launchListPage(paramMap, pageRequest);
	}
	@Override
	public Page<Map<String, String>> changeListPage(Map<String, Object> paramMap, PageRequest pageRequest) {
		return this.getDao().changeListPage(paramMap, pageRequest);
	}
	@Override
	public Page<Map<String, String>> checkListPage(Map<String, Object> paramMap, PageRequest pageRequest) {
		return this.getDao().checkListPage(paramMap, pageRequest);
	}
	@Override
	public Page<Map<String, String>> recordListPage(Map<String, Object> paramMap, PageRequest pageRequest) {
		return this.getDao().recordListPage(paramMap, pageRequest);
	}

	@Transactional
	public void batchInsertChaDep(List<Map<String,Object>> list) throws Exception{
		UserBean userBean=AuthSystemFacade.getLoginUserInfo();
		for (Map<String, Object> map : list) {
			map.put("id", CommonUtil.createUUID());
			map.put("ccdCrteTime", new Date());
			map.put("ccdCrteUserId", userBean.getUserId());
			map.put("ccdUpdtTime", new Date());
			map.put("ccdUpdtUserId", userBean.getUserId());
			map.put("ccdStatus", "1");
		}
		changeDepartmentMapper.batchInsertChaDep(list);
	}
	@Transactional
	public void updateChangeDepartment(ChangeDepartment record) throws Exception{	
		changeDepartmentMapper.updateChangeDepartment(record);
	}
	@Transactional
	public void addChangePeople(ChangePeople model) throws Exception{	
		changePeopleMapper.insert(model);
	}
	
	@Transactional
	public void changeSubmit(HttpServletRequest request) throws Exception{	
		UserBean userBean=AuthSystemFacade.getLoginUserInfo();
		//监狱id
		String cusNumber=userBean.getOrgCode();
		//用户id
		String userId=userBean.getUserId();
		//用户真实姓名
		String realName=userBean.getRealName();
		//部门编号
		String dprtmntCode=userBean.getDprtmntCode();
		String changeId=request.getParameter("changeId");
		String changeDepartmentId=request.getParameter("changeDepartmentId");
		String ccpChangedMatters=request.getParameter("ccpChangedMatters");
		String ccdBranchLeaders=request.getParameter("ccdBranchLeaders");
		String ccdBranchLeadersName=request.getParameter("ccdBranchLeadersName");
		String ccdManagerLeaders=request.getParameter("ccdManagerLeaders");
		String ccdManagerLeadersName=request.getParameter("ccdManagerLeadersName");
		
		ChangeDepartment changeDepartment = new ChangeDepartment();
		changeDepartment.setId(changeDepartmentId);
		changeDepartment.setCcdBranchLeaders(ccdBranchLeaders);
		changeDepartment.setCcdBranchLeadersName(ccdBranchLeadersName);
		changeDepartment.setCcdManagerLeaders(ccdManagerLeaders);
		changeDepartment.setCcdManagerLeadersName(ccdManagerLeadersName);
		changeDepartment.setCcdStatus("2");
		changeDepartment.setCcdUpdtTime(new Date());
		changeDepartment.setCcdUpdtUserId(userId);
		updateChangeDepartment(changeDepartment);
		
		ChangePeople changePeople = new ChangePeople();
		changePeople.setCcpChangedIdnty(changeId);
		changePeople.setCcpChangedMatters(ccpChangedMatters);
		changePeople.setCcpChangedTime(new Date());
		changePeople.setCcpCusNumber(cusNumber);
		changePeople.setCcpDprtmntIdnty(dprtmntCode);
		changePeople.setCcpPoliceIdnty(userId);
		changePeople.setCcpPoliceName(realName);
		changePeople.setCcpCrteTime(new Date());
		changePeople.setCcpCrteUserId(userId);
		changePeople.setCcpUpdtTime(new Date());
		changePeople.setCcpUpdtUserId(userId);
		addChangePeople(changePeople);
	}
	@Transactional
	public void checkSubmit(HttpServletRequest request) throws Exception{	
		UserBean userBean=AuthSystemFacade.getLoginUserInfo();
		//监狱id
		String cusNumber=userBean.getOrgCode();
		//用户id
		String userId=userBean.getUserId();
		

		String changeId=request.getParameter("changeId");
		String ccdDprtmntIdnty=request.getParameter("ccdDprtmntIdnty");
		String ccdStatus=(String)request.getParameter("ccdStatus");
		String ccaApprovalType=(String)request.getParameter("ccaApprovalType");
		String ccaPoliceIdnty=request.getParameter("ccaPoliceIdnty");
		String ccaPoliceName=request.getParameter("ccaPoliceName");
		String ccaSuggestion=(String)request.getParameter("ccaSuggestion");
		String ccaContent=request.getParameter("ccaContent");
		
		ChangeDepartment changeDepartment = new ChangeDepartment();
		changeDepartment.setCcdDprtmntIdnty(ccdDprtmntIdnty);
		changeDepartment.setCcdChangedId(changeId);
		changeDepartment.setCcdCusNumber(cusNumber);
		changeDepartment.setCcdStatus(ccdStatus);
		changeDepartment.setCcdUpdtTime(new Date());
		changeDepartment.setCcdUpdtUserId(userId);
		changeDepartmentMapper.updateCcdStatus(changeDepartment);
		
		ChangeApproval changeApproval=new ChangeApproval();
		changeApproval.setCcaCusNumber(cusNumber);
		changeApproval.setCcaChangedIdnty(changeId);
		changeApproval.setCcaDprtmntIdnty(ccdDprtmntIdnty);
		changeApproval.setCcaApprovalType(ccaApprovalType);
		changeApproval.setCcaPoliceIdnty(ccaPoliceIdnty);
		changeApproval.setCcaPoliceName(ccaPoliceName);
		changeApproval.setCcaSuggestion(ccaSuggestion);
		changeApproval.setCcaContent(ccaContent);
		changeApproval.setCcaCrteUserId(userId);
		changeApproval.setCcaCrteTime(new Date());
		changeApproval.setCcaUpdtUserId(userId);
		changeApproval.setCcaUpdtTime(new Date());
		changeApprovalMapper.insert(changeApproval);
	}
	@Override
	public List<ChangeApproval> searchChangeApproval(ChangeApproval record) {
		return changeApprovalMapper.searchChangeApproval(record);
	}
	@Override
	public List<ChangeDepartment> searchChangeDepartment(ChangeDepartment record) {
		return changeDepartmentMapper.searchChangeDepartment(record);
	}
	@Override
	public List<ChangePeople> searchChangePeople(ChangePeople record) {
		return changePeopleMapper.searchChangePeople(record);
	}
}
