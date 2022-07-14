package com.cesgroup.prison.dagl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.dagl.dao.DaglMapper;
import com.cesgroup.prison.dagl.entity.Dagl;
import com.cesgroup.prison.dagl.vo.DaglInfo;

@Service
public class DaglService extends BaseDaoService<Dagl, String, DaglMapper> {

	/**
	 * 根据记录ID查询整条记录
	 */
	public Dagl getDaglpById(String id) {
		return super.selectOne(id);
	}

	/**
	 * 新增
	 * 
	 * @param dagl
	 * @return
	 */
	public void insertDagl(Dagl dagl) {
		super.insert(dagl);
	}

	/**
	 * 修改
	 * 
	 * @param dagl
	 * @return
	 */
	public void updateDagl(Dagl dagl) {
		super.update(dagl);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	public void deleteDaglById(String id) {
		super.delete(id);
	}

	/**
	 * 获取条目对应的条目及全文信息（一对多）
	 * 
	 * @param id
	 * @return
	 */
	public Dagl getDaAnAtth(String id) {
		return getDao().getDaAnAtth(id);
	}

	/**
	 * 获取条目及全文信息（关联查询）
	 * 
	 * @param daglInfo
	 * @return
	 */
	public List<DaglInfo> getAllDaglInfo(DaglInfo daglInfo) {
//		PageHelper.startPage(pageNumber, pageSize);
		return getDao().getAllDaglInfo(daglInfo);
	}

	/**
	 * 获取条目及全文信息总记录数（关联查询）
	 * 
	 * @param daglInfo
	 * @return
	 */
	public int getDaglInfoByconditionCnt(DaglInfo daglInfo) {
		return getDao().getDaglInfoByconditionCnt(daglInfo);
	}
}
