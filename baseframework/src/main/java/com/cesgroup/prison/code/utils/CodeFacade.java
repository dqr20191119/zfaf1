package com.cesgroup.prison.code.utils;

import com.cesgroup.framework.commons.SpringContextUtils;
import com.cesgroup.prison.code.entity.Code;
import com.cesgroup.prison.code.service.CodeFacadeServer;
import com.cesgroup.prison.code.service.CodeFacadeServerRedisImpl;

/**
 * 系统通用编码静态加载入口
 *
 */
public class CodeFacade {
	
	private static CodeFacadeServer codeFacadeServer = (CodeFacadeServer) SpringContextUtils.getBean(CodeFacadeServerRedisImpl.class);
	
	/**
	 * 
	 * 根据编码组编码获取 其孩子节点编码集合JSON，默认返回一层,深度=1(level = 1)  currentCode =null 
	 * @param superCode 编码组  编码KEY名称[ 键(key) ]
	 * @return
	 */
	public static Code loadCode(String groupKey) {
		 return loadCode(groupKey, false);
	}
	
	/**
	 * .根据编码组KEY，是否有效查询编码
	 * @param groupKey 编码组KEY
	 * @param showValid 是否只显示有效编码，true：只显示有效编码，false：显示全部编码
	 * @return Code
	 * @author admin(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2015-1-9  上午10:27:44
	 */
	public static Code loadCode(String groupKey, boolean showValid) {
		 return codeFacadeServer.loadCode(groupKey, showValid);
	}
	
	/**
	 * 根据编码组编码  +  当前编码KEY 获取 其孩子节点编码集合JSON，默认返回一层,深度=1(level = 1)
	 * @param groupKey 编码组  编码KEY名称[ 键(key) ]
	 * @param currentCodeKey 当前编码  编码KEY名称[ 键(key) ]
	 * @return
	 */
	public static Code loadCode(String groupKey, String currentCodeKey) {
		return loadCode(groupKey, currentCodeKey, false);
	}	
	
	/**
	 * .获取 其孩子节点编码集合JSON，默认返回一层,深度=1(level = 1)
	 * @param groupKey 编码组KEY
	 * @param currentCodeKey 当前编码KEY
	 * @param showValid 是否只显示有效编码，true：只显示有效编码，false：显示全部编码
	 * @return Code
	 * @author admin(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2015-1-9  上午10:29:21
	 */
	public static Code loadCode(String groupKey, String currentCodeKey, boolean showValid) {		
		return codeFacadeServer.loadCode(groupKey, currentCodeKey, showValid);
	}
	
	/**
	 * 根据编码组编码  +  当前编码KEY  + 编码获取深度    获取 其孩子节点编码集合JSON
	 * @param superCode 编码组  编码KEY名称[ 键(key) ]
	 * @param currentCode 当前编码  编码KEY名称[ 键(key) ]
	 * @param level  需要获取编码的 深度
	 * @return
	 */
	public static Code loadCode(String groupKey, String currentCodeKey, int level) {
		return loadCode(groupKey, currentCodeKey, level, false);
	}
	
	/**
	 * .获取 其孩子节点编码集合JSON
	 * @param groupKey 编码组KEY
	 * @param currentCodeKey 当前编码KEY
	 * @param level 返回深度
	 * @param showValid 是否只显示有效编码，true：只显示有效编码，false：显示全部编码
	 * @return Code
	 * @author admin(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2015-1-9  上午10:32:03
	 */
	public static Code loadCode(String groupKey, String currentCodeKey, int level, boolean showValid) {
		return codeFacadeServer.loadCode(groupKey, currentCodeKey, level, showValid);
	}
	
	/**
	 * 
	 * 根据编码组编码获取 其孩子节点编码集合JSON，默认返回一层,深度=1(level = 1)  currentCode =null 
	 * @param superCode 编码组  编码KEY名称[ 键(key) ]
	 * @return
	 */
	public static String loadCode2Json(String groupKey) {
		return loadCode2Json(groupKey, false);
	}
	
	public static String loadCode2Json(String groupKey, boolean showValid) {
		return codeFacadeServer.loadCode2Json(groupKey, showValid);
	}
	
	/**
	 * 根据编码组编码  +  当前编码KEY 获取 其孩子节点编码集合JSON，默认返回一层,深度=1(level = 1)
	 * @param groupKey 编码组  编码KEY名称[ 键(key) ]
	 * @param currentCodeKey 当前编码  编码KEY名称[ 键(key) ]
	 * @return
	 */
	public static String loadCode2Json(String groupKey, String currentCodeKey) {
		return loadCode2Json(groupKey, currentCodeKey, false);
	}
	
	public static String loadCode2Json(String groupKey, String currentCodeKey, boolean showValid) {
		return codeFacadeServer.loadCode2Json(groupKey, currentCodeKey, showValid);
	}
	
	/**
	 * 根据编码组编码  +  当前编码KEY  + 编码获取深度    获取 其孩子节点编码集合JSON
	 * @param superCode 编码组  编码KEY名称[ 键(key) ]
	 * @param currentCode 当前编码  编码KEY名称[ 键(key) ]
	 * @param level  需要获取编码的 深度
	 * @return
	 */
	public static String loadCode2Json(String groupKey, String currentCodeKey, int level) {
		return loadCode2Json(groupKey, currentCodeKey, level, false);
	}
	
	public static String loadCode2Json(String groupKey, String currentCodeKey, int level, boolean showValid) {
		return codeFacadeServer.loadCode2Json(groupKey, currentCodeKey, level, showValid);
	}
	
	public static String loadCode2SimpleTreeJson(String groupKey, String currentCodeKey, int level) {
		return loadCode2SimpleTreeJson(groupKey, currentCodeKey, level, false);
	}
	
	public static String loadCode2SimpleTreeJson(String groupKey, String currentCodeKey, int level, boolean showValid) {
		return codeFacadeServer.loadCode2SimpleTreeJson(groupKey, currentCodeKey, level, showValid);
	}
	
	/**
	 * .过滤下拉框编码
	 * @param groupKey 编码组KEY
	 * @param loadType 加载类型【1：匹配KEY后几位字符；2：匹配KEY前几位字符；3：剔除的KEY，如：1,2,3；4：包含哪些KEY；5：剔除的type；6：包含的type】
	 * @param regexString 匹配的KEY字符串
	 * @return
	 * @author hp(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2014-12-11  上午10:07:59
	 */
	public static String loadCode4ComboJson(String groupKey, int loadType, String regexString) {
		return loadCode4ComboJson(groupKey, loadType, regexString, false);
	}
	
	/**
	 * .过滤下拉框编码
	 * @param groupKey 编码组KEY
	 * @param loadType 加载类型【1：匹配KEY后几位字符；2：匹配KEY前几位字符；3：剔除的KEY，如：1,2,3；4：包含哪些KEY；5：剔除的type；6：包含的type】
	 * @param regexString 匹配的KEY字符串
	 * @param showValid 是否只显示有效编码，true：只显示有效编码，false：显示全部编码
	 * @return
	 * @author hp(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2014-12-11  上午10:07:59
	 */
	public static String loadCode4ComboJson(String groupKey, int loadType, String regexString, boolean showValid) {
		return codeFacadeServer.loadCode4ComboJson(groupKey, loadType, regexString, showValid);
	}
	
	/**
	 * .过滤下拉框编码（在过滤的结果集中再剔除包含codes的编码）
	 * @param groupKey 编码组KEY
	 * @param loadType 加载类型【1：匹配KEY后几位字符；2：匹配KEY前几位字符；3：剔除的KEY，如：1,2,3；4：包含哪些KEY】
	 * @param regexString 匹配的KEY字符串
	 * @param codes 要剔除的编码KEY，多个用逗号分隔
	 * @return String
	 * @author admin(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2015-7-1  下午4:55:02
	 */
	public static String loadCode4ComboJson(String groupKey, int loadType, String regexString, String codes) {
		return codeFacadeServer.loadCode4ComboJson(groupKey, loadType, regexString, codes);
	}
	
	/**
	 * .构造有效下拉树编码
	 * @param groupKey 编码组KEY
	 * @param currentCodeKey 当前编码KEY
	 * @param level 返回深度
	 * @return String
	 * @author admin(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2015-1-9  上午10:34:17
	 */
	public static String loadCode4CombotreeJson(String groupKey, String currentCodeKey, int level) {
		return loadCode4CombotreeJson(groupKey, currentCodeKey, level, false);
	}
	
	/**
	 * .构造下拉树编码
	 * @param groupKey 编码组KEY
	 * @param currentCodeKey 当前编码KEY
	 * @param level 返回深度
	 * @param showValid 是否只显示有效编码，true：只显示有效编码，false：显示全部编码
	 * @return String
	 * @author admin(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2015-1-9  上午10:35:01
	 */
	public static String loadCode4CombotreeJson(String groupKey, String currentCodeKey, int level, boolean showValid) {
		return codeFacadeServer.loadCode4CombotreeJson(groupKey, currentCodeKey, level, showValid);
	}
	
	/**
	 *.根据编码KEY获取编码名称
	 * @param groupKey 编码组KEY
	 * @param codeKeys 编码KEY，多个用“,”分开
	 * @return String
	 * @author admin(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2014-12-15  下午8:05:07
	 */
	public static String getCodeNameByCodeKey(String groupKey, String codeKeys) {
		return codeFacadeServer.getCodeNameByCodeKey(groupKey, codeKeys);
	}
}
