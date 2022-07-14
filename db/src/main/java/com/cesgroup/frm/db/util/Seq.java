package com.cesgroup.frm.db.util;

import java.util.Date;
import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import com.cesgroup.framework.commons.SpringContextUtils;
/**
 * cesgroup
 * 序列号
 */
public class Seq {
	
	private static Seq msgSeq=new Seq();
	
	private static String oldTime = "";

	private static int oldNum = 1;
	private static String lock="1";

	private static java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(
			"yyyyMMddHH24mmss");
	
	@SuppressWarnings("unchecked")
	private RedisTemplate<String, String> redisTemplate = (RedisTemplate<String, String>)SpringContextUtils.getBean("redisTemplate");
	private String redisKeyName = IRedisConst.COMMON_CUR_TIME_SEQ;
	
	private Seq(){
	}
	
	public static Seq getInstance(String format){
		sdf = new java.text.SimpleDateFormat(format);
		return msgSeq;
	}
	
	public static Seq getInstance(){
		return msgSeq;
	}

	private String fi0(int i) {
		String b = "0000" + i;
		return b.substring(b.length() - 4);
	}

	private String getT() {
		Date d = new Date();
		d.setTime(System.currentTimeMillis());
		return sdf.format(d);
	}

	public String get() throws Exception {
		String s = getT();
		synchronized (lock) {
			int cn = 1;
			while (oldTime.equals(s) && cn <= oldNum) {
				cn = oldNum + 1;
				if (cn >= 10000) {
					Thread.sleep(1000);
					s = getT();
					cn = 1;
				}
			}
			oldTime = s;
			s = s + fi0(cn);
			oldNum = cn;
		}
		
		List<String> list = redisTemplate.opsForList().range(redisKeyName, 0, redisTemplate.opsForList().size(redisKeyName)-1);
		if(list.contains(s)){
			get();
		}else{
			redisTemplate.opsForList().rightPush(redisKeyName, s);
		}
		
		return s;
	}
}
