package com.cesgroup.prison.foreign.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.foreign.dao.ForeignCarDtlsMapper;
import com.cesgroup.prison.foreign.dao.ForeignRegisterMapper;
import com.cesgroup.prison.foreign.dto.ForeignCarDtlsDto;
import com.cesgroup.prison.foreign.entity.ForeignCarDtls;
import com.cesgroup.prison.foreign.entity.ForeignRegister;
import com.cesgroup.prison.foreign.service.ForeignCarDtlsService;

@Service
public class ForeignCarDtlsServiceImpl extends BaseDaoService<ForeignCarDtls, String, ForeignCarDtlsMapper> implements ForeignCarDtlsService {
	/**
	 * 外来人车登记数据库访问类
	 */
	@Autowired
	ForeignRegisterMapper foreignRegisterMapper;

	/**
	 * Description: 分页查询
	 */
	@Override
	public Page<ForeignCarDtlsDto> queryWithPageByCondition(Map<String, Object> map, PageRequest pageRequest) throws Exception {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		map.put("fcdCusNumber", user.getCusNumber());
		return this.getDao().findWithPageByCondition(map, pageRequest);
	}
	
	/**
	 * Description: 保存
	 */
	@Transactional
	@Override
	public ForeignCarDtls saveForeignCarDtls(ForeignCarDtls foreignCarDtls, ForeignRegister foreignRegister) throws Exception {
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		// 保存或更新外来人车登记信息
		String foreignRegisterId = "";
		if(foreignRegister != null) {
			ForeignRegister foreignRegisterOp = this.foreignRegisterMapper.selectOne(foreignRegister.getId());
			if(foreignRegisterOp == null) {
				// 创建新对象
				foreignRegisterOp = new ForeignRegister();
				foreignRegisterOp.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				
				// 外来人车登记信息属性拷贝
				BeanUtils.copyProperties(foreignRegister, foreignRegisterOp, "id");
				
				foreignRegisterOp.setFrCrteUserId(user.getUserId());
				foreignRegisterOp.setFrCrteTime(new Date());
				foreignRegisterOp.setFrUpdtUserId(user.getUserId());
				foreignRegisterOp.setFrUpdtTime(new Date());
				foreignRegisterOp.setFrCusNumber(user.getCusNumber());
				
				// 保存外来人车登记信息
				this.foreignRegisterMapper.insert(foreignRegisterOp);
			} else {
				// 外来人车登记信息属性拷贝
				BeanUtils.copyProperties(foreignRegister, foreignRegisterOp, "id", "frCrteUserId", "frCrteTime");

				// 更新外来人车登记信息
				this.foreignRegisterMapper.update(foreignRegisterOp);
			}
			foreignRegisterId = foreignRegisterOp.getId();
		}
		
		// 保存或更新外来车辆信息
		if(foreignCarDtls != null) {
			ForeignCarDtls foreignCarDtlsOp = this.getDao().selectOne(foreignCarDtls.getId());
			if(foreignCarDtlsOp == null) {
				// 创建新对象
				foreignCarDtlsOp = new ForeignCarDtls();
				foreignCarDtlsOp.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				
				// 外来车辆信息属性拷贝
				BeanUtils.copyProperties(foreignCarDtls, foreignCarDtlsOp, "id");
				
				foreignCarDtlsOp.setFcdRegisterId(foreignRegisterId);
				
				// 保存外来车辆信息
				this.getDao().insert(foreignCarDtlsOp);
			} else {
				// 外来车辆信息属性拷贝
				BeanUtils.copyProperties(foreignCarDtls, foreignCarDtlsOp, "id");

				// 更新外车辆信息
				this.getDao().update(foreignCarDtlsOp);
			}
			return foreignCarDtlsOp;
		}
		
		return null;
	}

	@Transactional
	@Override
	public int deleteByIds(List<String> ids) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<String, Object> findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findByCarLcnsIdnty(String carLcnsIdnty) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
