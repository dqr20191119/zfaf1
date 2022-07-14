/**
 * <p>Copyright:Copyright(c) 2013</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.ces.xarch.sso.bean</p>
 * <p>文件名:XarchTokenUtils.java</p>
 * <p>类更新历史信息</p>
 * @todo <a href="mailto:510276046@qq.com">Erxn(滕树宝)</a> 创建于 2013-11-11 上午10:26:59
 */
package com.ces.utils;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
//import org.springframework.security.web.authentication.rememberme.InvalidCookieException;


/**
 * 单点登录令牌工具类.
 * <p>Company:上海中信信息发展股份有限公司</p>
 * @date 2013-11-11 上午10:26:59
 * @version 1.0.2013-11-11
 */
public class TokenUtils {
	
	private static final String key = "cesXarch";
	private static final String DELIMITER = ":";
	
	
	public static String makeTokenSignature(long tokenExpiryTime, String username,
			String password) {
		String data = username + ":" + tokenExpiryTime + ":" + password + ":" + key;
		
		
		//old
		//return EncodeUtils.MD5(data);
		//new
		MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("No MD5 algorithm available!");
        }
        return new String(Hex.encode(digest.digest(data.getBytes())));
		
	}
	
	public static String encodeToken(String[] cookieTokens) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cookieTokens.length; i++) {
			sb.append(cookieTokens[i]);

			if (i < cookieTokens.length - 1) {
				sb.append(DELIMITER);
			}
		}

		String value = sb.toString();

		//old该版本生成规则和springframework中解析规则存在差异，导致解析失败
		//sb = new StringBuilder(new String(new BASE64Encoder().encode(value.getBytes())));
		//new
		sb = new StringBuilder(new String(Base64.encode(value.getBytes())));

		while (sb.charAt(sb.length() - 1) == '=') {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}
	
	public static String[] decodeToken(String cookieValue) throws IOException{
        for (int j = 0; j < cookieValue.length() % 4; j++) {
            cookieValue = cookieValue + "=";
        }
        //old
        //String cookieAsPlainText = new String(new BASE64Decoder().decodeBuffer(cookieValue));
        //String[] tokens = cookieAsPlainText.split(DELIMITER);
        //new
        String cookieAsPlainText = new String(Base64.decode(cookieValue.getBytes()));
        String[] tokens = org.springframework.util.StringUtils.delimitedListToStringArray(cookieAsPlainText, DELIMITER);

        if ((tokens[0].equalsIgnoreCase("http") || tokens[0].equalsIgnoreCase("https")) && tokens[1].startsWith("//")) {
            String[] newTokens = new String[tokens.length - 1];
            newTokens[0] = tokens[0] + ":" + tokens[1];
            System.arraycopy(tokens, 2, newTokens, 1, newTokens.length - 1);
            tokens = newTokens;
        }

        return tokens;
    }
	
	/**
	 * <p>根据用户名，密码，和过期时间生成Token，密码password为加密后的密码，expiryTime过期时间，单位为分钟</p>.
	 * @param loginname 用户名
	 * @param password  密码
	 * @param expiryTime 过期时间
	 * @return 令牌字符串
	 * @author <a href="mailto:510276046@qq.com">Erxn(滕树宝)</a>
	 * @date 2013-11-11  下午2:14:47
	 */
	public static String getToken(String loginname,String password,int expiryTime){
		assert loginname!=null&&!"".equals(loginname.trim()):"loginname 不能为空！";
		assert password!=null&&!"".equals(password.trim()):"password 不能为空！";
		
		long time = System.currentTimeMillis() + expiryTime*60*1000L;
		return encodeToken(new String[]{loginname,Long.toString(time),makeTokenSignature(time,loginname,password)});
		
	}
	/**
	 * <p>根据用户名，密码生成Token，密码password为加密后的密码，Token过期时间为30分钟</p>.
	 * @param loginname 用户名
	 * @param password 密码
	 * @return 令牌字符串
	 * @author <a href="mailto:510276046@qq.com">Erxn(滕树宝)</a>
	 * @date 2013-11-11  下午2:14:47
	 */
	public static String getToken(String loginname,String password){
		return getToken(loginname,password,30);
	}
	
	public static void main(String[] args) throws IOException{
		String token = TokenUtils.getToken("小王|南京监狱", "", 1);
		System.out.println(token);
		String[] tokenArray = TokenUtils.decodeToken(token);
		if(tokenArray != null && tokenArray.length>0)
		for(String tok : tokenArray)
			System.out.println(tok);
	}
	
	
	/**
	 * 检查来自网络URL的令牌（令牌）
	 * 令牌解析代码来源于spring security   
	 * org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices.processAutoLoginCookie()
	 * @param token
	 * @return
	 * @throws Exception
	 */
	public static String[] decodeTokenFromUrl(String token) throws Exception{
		String[] tokenArray = null;
		try {
			tokenArray = TokenUtils.decodeToken(token);
			if (tokenArray.length != 3) {
	            throw new InvalidCookieException("Cookie token did not contain 3" +
	                    " tokens, but contained '" + Arrays.asList(tokenArray) + "'");
	        }
			long tokenExpiryTime;
	        try {
	            tokenExpiryTime = new Long(tokenArray[1]).longValue();
	        }
	        catch (NumberFormatException nfe) {
	            throw new InvalidCookieException("Cookie token[1] did not contain a valid number (contained '" +
	            		tokenArray[1] + "')");
	        }
	        if (isTokenExpired(tokenExpiryTime)) {
	            throw new InvalidCookieException("Cookie token[1] has expired (expired on '"
	                    + new Date(tokenExpiryTime) + "'; current time is '" + new Date() + "')");
	        }
	        String expectedTokenSignature = makeTokenSignature(tokenExpiryTime, tokenArray[0],"");

	        if (!equals(expectedTokenSignature,tokenArray[2])) {
	            throw new InvalidCookieException("Cookie token[2] contained signature '" + tokenArray[2]
	                                                                                                    + "' but expected '" + expectedTokenSignature + "'");
	        }
			
		} catch (IOException e) {
			throw new InvalidCookieException("the token from url is error'");
		}
		return tokenArray;
	}
	private static boolean isTokenExpired(long tokenExpiryTime) {
        return tokenExpiryTime < System.currentTimeMillis();
    }
	/**
     * Constant time comparison to prevent against timing attacks.
     */
    private static boolean equals(String expected, String actual) {
        byte[] expectedBytes = bytesUtf8(expected);
        byte[] actualBytes = bytesUtf8(actual);
        if (expectedBytes.length != actualBytes.length) {
            return false;
        }

        int result = 0;
        for (int i = 0; i < expectedBytes.length; i++) {
            result |= expectedBytes[i] ^ actualBytes[i];
        }
        return result == 0;
    }
    private static byte[] bytesUtf8(String s) {
        if (s == null) {
            return null;
        }
        return Utf8.encode(s);
    }
    
   
}

class InvalidCookieException extends Exception {
	public InvalidCookieException(String message) {
        super(message);
    }
}


