package com.cesgroup.prison.code.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.code.entity.Code;

/**   
*    
* @projectName：prison   
* @ClassName：CodeMapper   
* @Description：编码Mapper   
* @author：zhengke   
* @date：2017-11-24 09:48   
* @version        
*/
public interface CodeMapper extends BaseDao<Code,String> {
	
	/**
	 * 根据编码组 KEY 获取编码组对象
	 * 
	 * @param groupKey
	 * @return
	 */
	public Code loadGroupCode(String groupKey);
	
	/**
	 * 根据编码组编码获取 其孩子节点编码集合，默认返回一层,深度=1(level = 1)  currentCode =null 
	 * @param superCode 编码组  编码KEY名称[ 键(key) ]
	 * @return
	 */
	public List<Code> loadCode(Map<String,Object> map);
	
	/**
	 * 根据编码组 KEY 和当前编码KEY 获取当前编码组对象
	 * 
	 * @param groupKey
	 * @return
	 */
	public Code loadCodeSelf(Map<String,String> map);
}
