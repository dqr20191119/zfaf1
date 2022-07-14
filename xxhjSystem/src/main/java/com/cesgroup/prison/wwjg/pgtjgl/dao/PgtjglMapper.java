package com.cesgroup.prison.wwjg.pgtjgl.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.wwjg.pgtjgl.entity.PgtjglEntity;

public interface PgtjglMapper extends BaseDao<PgtjglEntity, String> {

	
	public PgtjglEntity getById(String id);

	public Page<PgtjglEntity> findList(PgtjglEntity PgtjglEntity, PageRequest pageRequest);

	public List<PgtjglEntity> findAllList(PgtjglEntity PgtjglEntity); 
	
	public void updateStatusByIds(List<String> idList);
	
	public PgtjglEntity getByCode(String code);
	/**
	 * Description: 根据风险点ID、数据删除状态位，查询风险评估条件
	 * @param fxdId
	 * @param scflg
	 * @return
	 */
	public List<PgtjglEntity> findByFxdIdAndScflg(@Param("fxdId") String fxdId, @Param("scflg") String scflg);
}
