package com.cesgroup.prison.code.service;

import com.cesgroup.prison.code.entity.Code;

public interface CodeFacadeServer {

	/**
	 * 根据编码组编码获取 其孩子节点编码集合，默认返回一层,深度=1(level = 1)  currentCode =null 
	 * @param superCode 编码组  编码KEY名称[ 键(key) ]
	 * @return
	 */
	public Code loadCode(String groupKey, boolean showValid);
	
	/**
	 * 根据编码组编码  +  当前编码KEY 获取 其孩子节点编码集合，默认返回一层,深度=1(level = 1)
	 * @param groupKey 编码组  编码KEY名称[ 键(key) ]
	 * @param currentCodeKey 当前编码  编码KEY名称[ 键(key) ]
	 * @return
	 */
	public Code loadCode(String groupKey, String currentCodeKey, boolean showValid);
	
	/**
	 * 
	 * @param superCode 编码组  编码KEY名称[ 键(key) ]
	 * @param currentCode 当前编码  编码KEY名称[ 键(key) ]
	 * @param level  需要获取几层编码
	 * @return
	 */
	public Code loadCode(String superCode, String currentCode, int level, boolean showValid);
		
	/**
	 * 
	 * 根据编码组编码获取 其孩子节点编码集合JSON，默认返回一层,深度=1(level = 1)  currentCode =null 
	 * @param superCode 编码组  编码KEY名称[ 键(key) ]
	 * @return
	 */
	public String loadCode2Json(String groupKey, boolean showValid);
	
	/**
	 * 根据编码组编码  +  当前编码KEY 获取 其孩子节点编码集合JSON，默认返回一层,深度=1(level = 1)
	 * @param groupKey 编码组  编码KEY名称[ 键(key) ]
	 * @param currentCodeKey 当前编码  编码KEY名称[ 键(key) ]
	 * @return
	 */
	public String loadCode2Json(String groupKey, String currentCodeKey, boolean showValid);
	
	/**
	 * 根据编码组编码  +  当前编码KEY  + 编码获取深度    获取 其孩子节点编码集合JSON
	 * @param superCode 编码组  编码KEY名称[ 键(key) ]
	 * @param currentCode 当前编码  编码KEY名称[ 键(key) ]
	 * @param level  需要获取编码的 深度
	 * @return
	 */
	public String loadCode2Json(String groupKey, String currentCodeKey, int level, boolean showValid);
	
	public String loadCode2SimpleTreeJson(String groupKey, String currentCodeKey, int level, boolean showValid);
	
	/**
	 * .过滤下拉框编码
	 * @param groupKey 编码组KEY
	 * @param loadType 加载类型【1：匹配KEY后几位字符；2：匹配KEY前几位字符；3：剔除的KEY，如：1,2,3】
	 * @param regexString 匹配的KEY字符串
	 * @return
	 * @author hp(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2014-12-11  下午3:37:25
	 */
	public String loadCode4ComboJson(String groupKey, int loadType, String regex, boolean showValid);
	
	public String loadCode4ComboJson(String groupKey, int loadType, String regex, String codes);
	
	public String loadCode4CombotreeJson(String groupKey, String currentCodeKey, int level, boolean showValid);
	
	public String getCodeNameByCodeKey(String groupKey, String codeKeys);
}
