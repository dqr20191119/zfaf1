package com.cesgroup.prison.aqfxyp.fxcj.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.aqfxyp.fxcj.entity.Fxcj;

/**
 * Description: 风险采集数据访问类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
public interface FxcjMapper extends BaseDao<Fxcj, String> {

	/**
	 * Description: 分页查询风险采集信息
	 * @param riskCollect
	 * @param pageRequest
	 * @return
	 */
	public Page<Fxcj> findList(Fxcj riskCollect, PageRequest pageRequest);
	
	
	
	List<Map> getAllwG(Map<String, Object> map);
	public Fxcj getById(String id);
	
	public void insertDb(Map map);



	public Page<Fxcj> findListNew(@Param("jQName")String jQName,@Param("wwName") String wwName,@Param("bz") String bz,@Param("date") String date, @Param("jyId") String jyId,PageRequest pageRequest);

	public List<Map> getFxfbwg(Map<String, Object> map);

	public List<Map> getFxfbwgBj(Map<String, Object> map);
	
	public List<Map> getAllwGkkkk(Map<String, Object> map);
}
