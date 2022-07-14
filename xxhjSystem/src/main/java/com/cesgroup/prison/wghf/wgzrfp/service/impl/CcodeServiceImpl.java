package com.cesgroup.prison.wghf.wgzrfp.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.dto.TreeDto;
import com.cesgroup.framework.exception.BusinessLayerException;

import com.cesgroup.prison.wghf.wgzrfp.dao.CcodeDao;
import com.cesgroup.prison.wghf.wgzrfp.entiy.Ccode;
import com.cesgroup.prison.wghf.wgzrfp.service.CcodeService;

import ces.sdk.util.Util;



@Service(value = "ccodeService")
public class CcodeServiceImpl extends BaseDaoService<Ccode, String, CcodeDao> implements CcodeService{

	@Autowired
	private CcodeDao ccodeDao;
	
	@Override
	public List<TreeDto> initCcodeTree() throws BusinessLayerException {
		try {
			List<TreeDto> treeList = this.packageCcodeTreeData();
			return treeList;
		} catch (Exception e) {
			throw new BusinessLayerException("初始化北京市监狱局树形结构发生异常，Exception info is: " + e.getMessage());
		}
		
	}

	private List<TreeDto> packageCcodeTreeData() {
		List<TreeDto> treeList = new ArrayList<TreeDto>();
		
		// 根节点设置
		TreeDto root = new TreeDto();
        root.setId(TreeDto.ROOT_ID);
        root.setCode(TreeDto.ROOT_CODE);
        root.setName("北京市监狱局");
        root.setOpen(true);
        treeList.add(root);
		
		
		List<Ccode> ccodeList = this.queryValidCcode();
		if(ccodeList != null && ccodeList.size() > 0) {
			for(Ccode ccode : ccodeList) {
				TreeDto treeNode = new TreeDto();
				treeNode.setId(ccode.getId());
				treeNode.setpId(Util.notNull(ccode.getParentId()) ? ccode.getParentId() : TreeDto.ROOT_ID);
				treeNode.setName(ccode.getName());
				treeNode.setCode(ccode.getCodeKey());
				treeNode.setOpen(true);
				treeList.add(treeNode);
			}
		}
		return treeList;
	}

	private List<Ccode> queryValidCcode() {
		// TODO Auto-generated method stub
		return this.ccodeDao.findByCodeKey();
	}

	

	



}
