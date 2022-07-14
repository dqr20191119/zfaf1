package com.cesgroup.prison.zfxx.zfdm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfZwdm;

/**
 * Description: 罪犯早晚点名表数据访问类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
public interface ZfZwdmDao extends BaseDao<ZfZwdm, String> {
	/**
	 * 分页查询早晚点名
	 * @param map
	 * @param pageable
	 * @return
	 */
	Page<Map<String, Object>> findWithPage(Map<String, Object> map, Pageable pageable);
	
	/**
	 * 根据点名类型、监狱名称、监区名称，查询罪犯早晚点名记录，并按照点名时间倒序排列
	 * 
	 * @param dmlx
	 * @param corpname
	 * @param deptname
	 * @return
	 */
	List<ZfZwdm> findByDmlxAndCorpnameAndDeptnameOrderByDtdmsjDesc(@Param("dmlx") String dmlx, @Param("corpname") String corpname, @Param("deptname") String deptname);

	String selectDmpc(Map<String, Object> map);
	
	List<ZfZwdm> selectDmqk(Map<String, Object> map);
	
}
