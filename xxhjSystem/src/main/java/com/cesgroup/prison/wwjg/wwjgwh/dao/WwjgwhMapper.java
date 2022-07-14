package com.cesgroup.prison.wwjg.wwjgwh.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.wwjg.wwjgwh.entity.WwjgwhEntity;

/**
 * Description: 五维架构数据访问类
 * @author lincoln.cheng
 *
 */
public interface WwjgwhMapper extends BaseDao<WwjgwhEntity, String> {

	
	public WwjgwhEntity getById(String id);

	public Page<WwjgwhEntity> findList(WwjgwhEntity wwjgwhEntity, PageRequest pageRequest);

	public List<WwjgwhEntity> findAllList(WwjgwhEntity wwjgwhEntity); 
	
	public void updateStatusByIds(List<String> idList);
	
	public WwjgwhEntity getByCode(String code);

	/**
	 * Description: 根据删除标志状态位，查询风险五维架构数据list
	 * @param scflag
	 * @return
	 */
	public List<WwjgwhEntity> findByScflg(@Param("scflg") String scflg);
}
