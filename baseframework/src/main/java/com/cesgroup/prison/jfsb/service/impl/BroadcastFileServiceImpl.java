package com.cesgroup.prison.jfsb.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.jfsb.dao.BroadcastFileDao;
import com.cesgroup.prison.jfsb.entity.BroadcastFile;
import com.cesgroup.prison.jfsb.service.BroadcastFileService;

import ces.sdk.util.Util;

@Service
@Transactional
public class BroadcastFileServiceImpl extends BaseDaoService<BroadcastFile, String, BroadcastFileDao> implements BroadcastFileService {

	@Override
	public Page<BroadcastFile> listAll(Map<String, Object> paramMap, Pageable pageable) throws BusinessLayerException {
		try {
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			if(userBean != null) {
				paramMap.put("bfdCusNumber", userBean.getCusNumber());
			}
			return this.getDao().listAll(paramMap, pageable);
		} catch (Exception e) {
			throw new BusinessLayerException("分页查询BroadcastFile发生异常，异常信息：" + e);
		}
	}

	@Override
	public BroadcastFile queryById(String id) throws BusinessLayerException {
		try {
			return this.getDao().findById(id);
		} catch (Exception e) {
			throw new BusinessLayerException("根据主键ID=" + id + "查询BroadcastFile发生异常，异常信息：" + e);
		}
	}

	@Override
	public AjaxMessage saveOrUpdate(BroadcastFile entity) throws BusinessLayerException {
		try {
			AjaxMessage am = new AjaxMessage();
			if(entity == null) {
				am.setSuccess(false);
				am.setMsg("数据异常");
				return am;
			}
			
			UserBean userBean = AuthSystemFacade.getLoginUserInfo();
			if(userBean == null || Util.isNull(userBean.getCusNumber())) {
				am.setSuccess(false);
				am.setMsg("获取操作用户监狱编号失败，新增或修改操作中断");
				return am;
			}
			
			BroadcastFile broadcastFileOp = Util.notNull(entity.getId()) ? this.getDao().findById(entity.getId()) : null;
			
			// 如果broadcastFileOp为空，则新增
			if(broadcastFileOp == null) {
				// 判断曲目ID是否为空
				if(Util.isNull(entity.getBfdIdnty())) {
					am.setSuccess(false);
					am.setMsg("曲目ID为空，添加失败");
					return am;
				}
				
				// 判断曲目编号是否重复
				Integer count = this.getDao().findCountByBfdCusNumberAndBfdIdnty(userBean.getCusNumber(), entity.getBfdIdnty());
				if(count > 0) {
					am.setSuccess(false);
					am.setMsg("曲目ID【" + entity.getBfdIdnty() + "】重复，添加失败");
					return am;
				}
				
		        // 查询最大排序号
		        Integer showOrder = this.getDao().findMaxOrderByBfdCusNumber(userBean.getCusNumber());
		        if(Util.notNull(showOrder)) {
		            showOrder += 1;
		        } else {
		        	showOrder = 1;
		        }
		        
		        // 赋值
				broadcastFileOp = new BroadcastFile();
				broadcastFileOp.setId(UUID.randomUUID().toString().replaceAll("-", ""));
				broadcastFileOp.setBfdCusNumber(userBean.getCusNumber());
				broadcastFileOp.setBfdIdnty(entity.getBfdIdnty());
				broadcastFileOp.setBfdName(entity.getBfdName());
				broadcastFileOp.setBfdRemark(entity.getBfdRemark());
				broadcastFileOp.setBfdSttsIndc("0");
				broadcastFileOp.setBfdOrder(Long.valueOf(showOrder));
				broadcastFileOp.setBfdCrteTime(new Date());
				broadcastFileOp.setBfdCrteUserId(userBean.getUserId());
				broadcastFileOp.setBfdUpdtTime(new Date());
				broadcastFileOp.setBfdUpdtUserId(userBean.getUserId());
				
				// 保存
				this.getDao().insert(broadcastFileOp);
				
				am.setSuccess(true);
				am.setMsg("新增成功");
			}
			// 如果broadcastFileOp不为空，则修改
			else {
				// 赋值
				broadcastFileOp.setBfdName(entity.getBfdName());
				broadcastFileOp.setBfdRemark(entity.getBfdRemark());
				broadcastFileOp.setBfdSttsIndc("0");
				broadcastFileOp.setBfdUpdtTime(new Date());
				broadcastFileOp.setBfdUpdtUserId(userBean.getUserId());
				
				// 修改
				this.getDao().update(broadcastFileOp);

				am.setSuccess(true);
				am.setMsg("修改成功");
			}
			
			return am;
		} catch (Exception e) {
			throw new BusinessLayerException("新增或修改BroadcastFile发生异常，异常信息：" + e);
		}
	}

	@Override
	public AjaxMessage deleteByIds(List<String> list) throws BusinessLayerException {
		try {
			AjaxMessage am = new AjaxMessage();
			if(list != null && list.size() > 0) {
				UserBean userBean = AuthSystemFacade.getLoginUserInfo();
				if(userBean == null || Util.isNull(userBean.getCusNumber())) {
					am.setSuccess(false);
					am.setMsg("获取操作用户监狱编号失败，删除操作中断");
					return am;
				}
				
				for(String id : list) {
					BroadcastFile broadcastFileOp = this.getDao().findById(id);
					if(broadcastFileOp != null) {
						broadcastFileOp.setBfdSttsIndc("1");
						broadcastFileOp.setBfdOrder(Long.valueOf(0));
						broadcastFileOp.setBfdUpdtTime(new Date());
						broadcastFileOp.setBfdUpdtUserId(userBean.getUserId());
						
						this.getDao().update(broadcastFileOp);
					}
				}
			}
			am.setSuccess(true);
			am.setMsg("删除成功");
			return am;
		} catch (Exception e) {
			throw new BusinessLayerException("删除BroadcastFile发生异常，异常信息：" + e);
		}
	}

	@Override
	public List<BroadcastFile> queryByBfdCusNumber(String cusNumber) throws BusinessLayerException {
		try {
			List<BroadcastFile> broadcastFileList = this.getDao().findByBfdCusNumber(cusNumber);
			return broadcastFileList;
		} catch (Exception e) {
			throw new BusinessLayerException("根据监狱编号查询广播曲目列表发生异常，异常原因：" + e);
		}
	}

}
