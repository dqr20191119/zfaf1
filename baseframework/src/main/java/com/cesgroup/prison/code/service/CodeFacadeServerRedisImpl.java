package com.cesgroup.prison.code.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cesgroup.prison.code.entity.Code;
import com.cesgroup.prison.code.tool.RedisPoolClient2;

@Service("codeFacadeServerRedisImpl")
public class CodeFacadeServerRedisImpl implements CodeFacadeServer {
	
	@Resource(name="codeFacadeServerImpl")
	private CodeFacadeServer codeFacadeServer;
	
	private RedisPoolClient2 redisPoolClient = new RedisPoolClient2();

	/**
	 * @param groupKey
	 * @return
	 * @see com.ces.prison.interfaces.cache.server.justice.facade.code.CodeFacadeServer#loadCode(java.lang.String)
	 * @author hp(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2014-12-11 上午9:59:43
	 */
	@Override
	public Code loadCode(String groupKey, boolean showValid) {
		
		return this.codeFacadeServer.loadCode(groupKey, showValid);
	}

	/**
	 * @param groupKey
	 * @param currentCodeKey
	 * @return
	 * @see com.ces.prison.interfaces.cache.server.justice.facade.code.CodeFacadeServer#loadCode(java.lang.String, java.lang.String)
	 * @author hp(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2014-12-11 上午9:59:43
	 */
	@Override
	public Code loadCode(String groupKey, String currentCodeKey, boolean showValid) {
		
		return this.codeFacadeServer.loadCode(groupKey, currentCodeKey, showValid);
	}

	/**
	 * @param superCode
	 * @param currentCode
	 * @param level
	 * @return
	 * @see com.ces.prison.interfaces.cache.server.justice.facade.code.CodeFacadeServer#loadCode(java.lang.String, java.lang.String, int)
	 * @author hp(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2014-12-11 上午9:59:43
	 */
	public Code loadCode(String superCode, String currentCode, int level, boolean showValid) {
		
		return this.codeFacadeServer.loadCode(superCode, currentCode, level, showValid);
	}

	/**
	 * @param groupKey
	 * @return
	 * @see com.ces.prison.interfaces.cache.server.justice.facade.code.CodeFacadeServer#loadCode2Json(java.lang.String)
	 * @author hp(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2014-12-11 上午9:59:43
	 */
	@Override
	public String loadCode2Json(String groupKey, boolean showValid) {
		return this.loadCode2Json(groupKey, null, 1, showValid);
	}

	/**
	 * @param groupKey
	 * @param currentCodeKey
	 * @return
	 * @see com.ces.prison.interfaces.cache.server.justice.facade.code.CodeFacadeServer#loadCode2Json(java.lang.String, java.lang.String)
	 * @author hp(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2014-12-11 上午9:59:43
	 */
	@Override
	public String loadCode2Json(String groupKey, String currentCodeKey, boolean showValid) {
		
		return this.loadCode2Json(groupKey, currentCodeKey, 1, showValid);
	}

	/**
	 * @param groupKey
	 * @param currentCodeKey
	 * @param level
	 * @return
	 * @see com.ces.prison.interfaces.cache.server.justice.facade.code.CodeFacadeServer#loadCode2Json(java.lang.String, java.lang.String, int)
	 * @author hp(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2014-12-11 上午9:59:43
	 */
	@Override
	public String loadCode2Json(String groupKey, String currentCodeKey, int level, boolean showValid) {
		
		String value = redisPoolClient.hgetCode(groupKey, currentCodeKey+"_"+level+"_"+showValid);
		if(value != null){
			return value;
		}else{
			value = this.codeFacadeServer.loadCode2Json(groupKey, currentCodeKey, level, showValid);
			System.out.println("try again , now loading from db code:"+value);
			redisPoolClient.hsetCode(groupKey, currentCodeKey+"_"+level+"_"+showValid, value);
		}
		return value;
	}

	/**
	 * @param groupKey
	 * @param currentCodeKey
	 * @param level
	 * @param showValid
	 * @return
	 * @see com.ces.prison.interfaces.cache.server.CodeFacadeServer#loadCode2SimpleTreeJson(java.lang.String, java.lang.String, int, boolean)
	 * @author admin(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2015-6-26 下午12:41:17
	 */
	@Override
	public String loadCode2SimpleTreeJson(String groupKey, String currentCodeKey, int level, boolean showValid) {
		
		String value = redisPoolClient.hgetCode(groupKey, "simple_"+currentCodeKey+"_"+level+"_"+showValid);
		if(value != null){
			return value;
		}else{
			value = this.codeFacadeServer.loadCode2SimpleTreeJson(groupKey, currentCodeKey, level, showValid);
			System.out.println("try again , now loading from db code:"+value);
			redisPoolClient.hsetCode(groupKey, "simple_"+currentCodeKey+"_"+level+"_"+showValid, value);
		}
		return value;
	}

	/**
	 * @param groupKey
	 * @param loadType
	 * @param regex
	 * @return
	 * @see com.ces.prison.interfaces.cache.server.justice.facade.code.CodeFacadeServer#loadCode4ComboJson(java.lang.String, int, java.lang.String)
	 * @author hp(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2014-12-11 上午9:59:43
	 */
	@Override
	public String loadCode4ComboJson(String groupKey, int loadType, String regex, boolean showValid) {
		
		String value = redisPoolClient.hgetCode(groupKey, "loadType"+"_"+loadType+"_"+regex+"_"+showValid);
		if(value != null){
			return value;
		}else{
			value = this.codeFacadeServer.loadCode4ComboJson(groupKey, loadType, regex, showValid);
			System.out.println("try again , now loading from db code:"+value);
			redisPoolClient.hsetCode(groupKey, "loadType"+"_"+loadType+"_"+regex+"_"+showValid, value);
		}
		return value;
	}

	/**
	 * @param groupKey
	 * @param loadType
	 * @param regex
	 * @param codes
	 * @return
	 * @see com.ces.prison.interfaces.cache.server.CodeFacadeServer#loadCode4ComboJson(java.lang.String, int, java.lang.String, java.lang.String)
	 * @author admin(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2015-7-1 下午4:52:15
	 */
	@Override
	public String loadCode4ComboJson(String groupKey, int loadType, String regex, String codes) {
		
		String value = this.codeFacadeServer.loadCode4ComboJson(groupKey, loadType, regex, codes);
//		String value = redisPoolClient.hgetCode(groupKey, "loadType"+"_"+loadType+"_"+regex+"_"+codes);
//		if(value != null){
//			return value;
//		}else{
//			redisPoolClient.hsetCode(groupKey, "loadType"+"_"+loadType+"_"+regex+"_"+codes, value);
//		}
		return value;
	}

	/**
	 * @param groupKey
	 * @param currentCodeKey
	 * @param level
	 * @param loadType
	 * @param regex
	 * @return
	 * @see com.ces.prison.interfaces.cache.server.CodeFacadeServer#loadCode4CombotreeJson(java.lang.String, java.lang.String, int, int, java.lang.String)
	 * @author admin(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2014-12-25 下午7:42:57
	 */
	public String loadCode4CombotreeJson(String groupKey, String currentCodeKey, int level, boolean showValid) {
		
		String value = redisPoolClient.hgetCode(groupKey, "combotree_"+currentCodeKey+"_"+level+"_"+showValid);
		if(value != null){
			return value;
		}else{
			value = this.codeFacadeServer.loadCode4CombotreeJson(groupKey, currentCodeKey, level, showValid);
			System.out.println("try again , now loading from db code:"+value);
			redisPoolClient.hsetCode(groupKey, "combotree_"+currentCodeKey+"_"+level+"_"+showValid, value);
		}
		return value;
	}

	/**
	 * @param groupKey
	 * @param codeKeys
	 * @return
	 * @see com.ces.prison.interfaces.cache.server.CodeFacadeServer#getCodeNameByCodeKey(java.lang.String, java.lang.String)
	 * @author hp(冯有双 E-mail:feng.youshuang@cesgroup.com.cn)
	 * @date 2014-12-11 下午3:53:37
	 */
	@Override
	public String getCodeNameByCodeKey(String groupKey, String codeKeys) {
		
		String value = redisPoolClient.hgetCode(groupKey, codeKeys);
		if(value != null){
			return value;
		}else{
			value = this.codeFacadeServer.getCodeNameByCodeKey(groupKey, codeKeys);
			redisPoolClient.hsetCode(groupKey, codeKeys, value);
		}
		return value;
	}
}
