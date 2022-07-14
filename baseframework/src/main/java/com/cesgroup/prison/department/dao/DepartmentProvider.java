/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.framework.mybatis.base.dao</p>
 * <p>文件名:UserProvider.java</p>
 * <p>类更新历史信息</p>
 * @author huz 
 * @date 2016-07-07 15:25
 * @todo 
 */
package com.cesgroup.prison.department.dao;

import java.util.Map;

import com.cesgroup.framework.mybatis.mapper.handler.LikeTypeHandler;
/**
 * 部门provider
 * 
 * @author xiexiaqin
 * @date 2016-08-24
 *
 */
public class DepartmentProvider {
	
	/**
	 * 查询子部门
	 * 当接口类的方法无参数时，此处方法也必须无参数
	 * 当接口类的方法参数是单个时，此处的方法可以直接使用参数名
	 * 当接口类的方法参数是多个时，此处的方法参数必须是Map<String,Object>类型
	 * 当接口类的方法使用了@param 注解时，此处的方法map以@param的值为key
	 * 当接口类的方法没有使用@param 注解时，此处的方法以参数的顺序获取从0开始，也可以使用param+序号形式从1开始
	 * @see com.cesgroup.system.dao.DepartmentMapper#findSonByProvider(String)
	 */
	public String findSonByProvider(Map<String, Object> params){
		return "select * from t_fw_department where parent_Id = #{parentId,typeHandler="
				+ LikeTypeHandler.class.getCanonicalName()+"}";
	}
	
	
}
