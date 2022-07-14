package com.cesgroup.prison.common.bean.user;
/**
 * cesgroup
 * 用户编码
 * lihh
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
@Component
public class UserCodeUtil {//extends  {
	private static final Logger log = LoggerFactory.getLogger(UserCodeUtil.class);
	private static final String cacheKey = "USER.RESP.CODE";	// 缓存标识

	/*
	 * 系统统一编码
	 */
	public static final String CODE_0000 = "0000";
	public static final String CODE_0001 = "0001";
	public static final String CODE_0002 = "0002";
	public static final String CODE_0003 = "0003";
	public static final String CODE_0004 = "0004";
	public static final String CODE_0005 = "0005";
	public static final String CODE_0006 = "0006";
	public static final String CODE_0007 = "0007";
	public static final String CODE_0008 = "0008";
	public static final String CODE_0009 = "0009";
	public static final String CODE_0010 = "0010";
	public static final String CODE_0011 = "0011";
	public static final String CODE_0012 = "0012";
	public static final String CODE_0013 = "0013";


	/*
	 * 1000类编码：为内部系统用户消息码
	 */
	public static final String CODE_1001 = "1001";
	public static final String CODE_1002 = "1002";
	public static final String CODE_1003 = "1003";
	public static final String CODE_1004 = "1004";
	public static final String CODE_1005 = "1005";
	public static final String CODE_1006 = "1006";


	/*
	 * 2000类编码：系统管理平台的消息码
	 */
	public static final String CODE_2001 = "2001";
	public static final String CODE_2002 = "2002";
	public static final String CODE_2003 = "2003";
	public static final String CODE_2004 = "2004";
	public static final String CODE_2005 = "2005";



	/**
	 * 私有构造函数
	 */
	private UserCodeUtil () {
		log.info("初始化用户模块响应码配置文件...");
		loadXml();
	}

	/**
	 * 加载配置文件
	 */
	private void loadXml () {
		//this.loadXml("code", "code-user.xml", "code");
	}


	/**
	 * 解析用户模块的响应码配置文件
	 */
	/*@Override
	protected void parseXml(String fileName, NodeList nodeList) {
		List<String> codes = new ArrayList<String>();	// 临时记录编码，用于检测是否重复
		Element item = null;
		String code = null;
		String desc = null;

		for (int i = 0, I = nodeList.getLength(); i < I; i++) {
			item = (Element) nodeList.item(i);
			code = item.getAttribute("code");
			desc = item.getAttribute("desc");

			if (!codes.contains(code)) {
				RedisCache.putHash(cacheKey, code, desc);
			} else {
				log.warn("在" + fileName + "配置文件中编码[" + code + "]重复");
			}
		}
	}*/

	/**
	 * 刷新配置文件
	 */
	/*@Override
	public Boolean refresh(String fileName) throws Exception {
		log.info("刷新用户模块响应码配置文件...");
		loadXml();
		return true;
	}*/


	/**
	 * 获取响应码对应描述
	 * @param code	响应码
	 * @param desc	默认响应码描述（未获取到时返回该值）
	 * @return 
	 */
	/*public static String getDesc (String code, String desc) {
		return Tools.toStr(RedisCache.getObject(cacheKey, code), desc);
	}*/
}
