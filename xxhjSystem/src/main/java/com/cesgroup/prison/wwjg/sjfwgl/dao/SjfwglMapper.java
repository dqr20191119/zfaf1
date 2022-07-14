package com.cesgroup.prison.wwjg.sjfwgl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.wwjg.sjfwgl.entity.SjfwglEntity;

public interface SjfwglMapper  extends BaseDao<SjfwglEntity, String> {

	
	public SjfwglEntity getById(String id);

	public Page<SjfwglEntity> findList(SjfwglEntity SjfwglEntity, PageRequest pageRequest);

	public List<SjfwglEntity> findAllList(SjfwglEntity SjfwglEntity); 
	
	public void updateStatusByIds(List<String> idList);
	
	public SjfwglEntity getByCode(String code);
	
	/**
	 * Description: 根据五维架构ID、删除标志状态位，查询数据范围list
	 * @param wwjgId
	 * @param scflg
	 * @return
	 */
	public List<SjfwglEntity> findByWwjgIdAndScflg(@Param("wwjgId") String wwjgId, @Param("scflg") String scflg);
}
