package com.cesgroup.prison.wwjg.fxdgl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.wwjg.fxdgl.entity.FxdglEntity;

public interface FxdglMapper  extends BaseDao<FxdglEntity, String> {

	
	public FxdglEntity getById(String id);

	public Page<FxdglEntity> findList(FxdglEntity FxdglEntity, PageRequest pageRequest);

	public List<FxdglEntity> findAllList(FxdglEntity FxdglEntity); 
	
	public void updateStatusByIds(List<String> idList);
	
	public FxdglEntity getByCode(String code);

	/**
	 * Description: 根据删除标志状态位，查询风险点数据list
	 * @param scflag
	 * @return
	 */
	public List<FxdglEntity> findByScflg(@Param("scflg") String scflg);
	
	/**
	 * Description: 根据数据范围ID、删除标志状态位，查询风险点数据list
	 * @param scflag
	 * @return
	 */
	public List<FxdglEntity> findBySjfwIdAndScflg(@Param("sjfwId") String sjfwId, @Param("scflg") String scflg);
}
