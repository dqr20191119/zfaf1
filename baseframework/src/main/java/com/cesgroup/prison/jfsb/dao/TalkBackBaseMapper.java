package com.cesgroup.prison.jfsb.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.jfsb.entity.TalkBackBaseEntity;

/**      
* @projectName：prison   
* @ClassName：TalkBackBaseMapper   
* @Description：  对讲信息
* @author：Tao.xu   
* @date：2017年12月20日 下午7:08:01   
* @version        
*/
public interface TalkBackBaseMapper extends BaseDao<TalkBackBaseEntity, String> {

	Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);

	void updateInfo(Map<String, Object> map);

	void deleteById(Map<String, Object> map);

	/**
	 * 通过监狱编号查询主机
	 * @param cusNumber
	 * @return
	 */
	List<Map<String, Object>> findBaseByMain(Map<String, Object> map);

	/**
	* @methodName: searchCombData
	* @Description: 下拉数据
	* @param map
	* @return Map<String,Object>    
	* @throws
	*/
	List<Map<String, Object>> searchCombData(Map<String, Object> map);

	/**
	 *
	 * 点位刷新时,对讲主机或者分机通过编号来判断是否存在
	 * @param map
	 *       cusNumber 监狱编码
	 *       idnty 对讲主机编号 或者 对讲分机编号
	 * @return
	 */
	List<String> findByTbdIdntyOrTseIdnty(Map<String, Object> map);

	Map<String, Object> findTalkbackBaseSum(Map<String, Object> paramMap);

}
