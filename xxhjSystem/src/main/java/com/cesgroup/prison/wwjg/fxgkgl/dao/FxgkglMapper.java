package com.cesgroup.prison.wwjg.fxgkgl.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.wwjg.fxgkgl.entity.FxgkglEntity;

public interface FxgkglMapper extends BaseDao<FxgkglEntity, String> {

	
	public FxgkglEntity getById(String id);

	public Page<FxgkglEntity> findList(FxgkglEntity FxgkglEntity, PageRequest pageRequest);

	public List<FxgkglEntity> findAllList(FxgkglEntity FxgkglEntity); 
	
	public void updateStatusByIds(List<String> idList);
	
	public FxgkglEntity getByCode(String code);
	/**
	 * Description: 根据风险点ID、数据删除状态位，查询风险评估条件
	 * @param fxdId
	 * @param scflg
	 * @return
	 */
	public List<FxgkglEntity> findByFxdIdAndScflg(@Param("fxdId") String fxdId, @Param("scflg") String scflg);
	
	public List<FxgkglEntity> getByFxcjId(String id);
	
	public Page<Map<String ,Object>> finndListWDGZ(Map<String ,Object> map, Pageable pageable);

	
	
}
