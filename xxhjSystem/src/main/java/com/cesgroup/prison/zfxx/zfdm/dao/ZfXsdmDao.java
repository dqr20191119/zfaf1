package com.cesgroup.prison.zfxx.zfdm.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfXsdm;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfZwdm;

/**
 * Description: 罪犯巡视点名表数据访问类
 * @author lincoln.cheng
 *
 * 2019年1月12日
 */
public interface ZfXsdmDao extends BaseDao<ZfXsdm, String> {
	/**
     * 分页查询巡视点名
     * @param map
     * @param pageable
     * @return
     */
	Page<Map<String, Object>> findWithPage(Map<String, Object> map, Pageable pageable);
	
	/**
	 * 根据监狱名称、监区名称，查询罪犯巡视点名记录，并按照点名时间倒序排列
	 * 
	 * @param dmlx
	 * @param corpname
	 * @param deptname
	 * @return
	 */
	List<ZfXsdm> findByCorpnameAndDeptnameOrderByDmsjDesc(@Param("corpname") String corpname, @Param("deptname") String deptname);
	
	/**
	 * 根据点名日期、监狱名称、监区名称，查询罪犯巡视点名记录，并按照点名时间倒序排列
	 * 
	 * @param dmsj
	 * @param corpname
	 * @param deptname
	 * @return
	 */
	List<ZfXsdm> findByDmsjAndCorpnameAndDeptnameOrderByDmsjDesc(@Param("dmsj") String dmsj, @Param("corpname") String corpname, @Param("deptname") String deptname);



	/**
	 * 获取各个监区所有需要进行点名的罪犯
	 * @param map
	 * @return
	 */
	List<Map <String, Object>> findqbjqxydmr(Map<String, Object> map);


	/**
	 * 获取各个监区所有已经点名的罪犯
	 * @param map
	 * @return
	 */
	List<Map <String, Object>> findydmr(Map<String, Object> map);
	
	public Page<Map<String ,Object>> finndListpd(Map<String ,Object> map, Pageable pageable);
	
}
