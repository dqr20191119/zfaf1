package com.cesgroup.prison.rcs.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.jfsb.entity.Camera;
import com.cesgroup.prison.rcs.entity.RcsEntity;
import com.cesgroup.prison.yrzq.entity.YrzqEntity;

/**
 * 通讯融合DAO
 * @author lincoln
 *
 */
public interface RcsDao extends BaseDao<RcsEntity, String> {
	
	public Page<Map<String, String>> searchRcs(Map<String, Object> map, PageRequest pageRequest);
	public Page<Map<String, String>> searchJl(Map<String, Object> map, PageRequest pageRequest);
	public int updateType(RcsEntity rcsEntity);
	public List<Map<String, Object>> getXgnr(String sxsj);
	public void updateXgtzId(Map<String, Object> map);
	public void updateYrzq(String id);
	
}
