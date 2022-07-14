package com.cesgroup.prison.dagl.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.dagl.dao.AtthMapper;
import com.cesgroup.prison.dagl.entity.Atth;

@Service
public class AtthService extends BaseDaoService<Atth, String, AtthMapper> {

	/**
	 * 根据记录ID查询整条记录
	 * 
	 * @param id
	 * @return
	 */
	public Atth getAtthlpById(String id) {
		return super.selectOne(id);
	}

	/**
	 * 新增
	 * 
	 * @param atth
	 * @return
	 */
	public void insertAtth(Atth atth) {
		super.insert(atth);
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
	 * 获取条目对应的全文名称
	 * 
	 * @param ownerId
	 *            全文对应的条目id
	 * @return
	 */
	public List<String> getFileNames(String ownerId) {
		return getDao().getFileNames(ownerId);
	}

	/**
	 * 使用mybatis xml方式保存全文信息
	 * 
	 * @param atth
	 */
	public void saveAtthByXml(Atth atth) {
		getDao().saveAtthByXml(atth);
	}

}
