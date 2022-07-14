package com.cesgroup.prison.common.bean.user.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Date;

/*import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.security.crypto.codec.Utf8;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
*/import org.springframework.util.StringUtils;

/**
 * 加密数据类（由原来的TokenUtils改名而成）
 * 
 * @author ghn
 * 
 */
public class TokenEUtils {
	// private static final String key = "cesXarch";
	// private static final String DELIMITER = ":";
	private static final String charset = "gbk";

	/*public static String makeTokenSignature(long tokenExpiryTime,
			String username, String password)
			throws UnsupportedEncodingException {
		String data = username + ":" + tokenExpiryTime + ":" + password + ":"
				+ "cesXarch";
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("No MD5 algorithm available!");
		}
		return new String(Hex.encode(digest.digest(data.getBytes(charset))));
	}*/

	public static String encodeToken(String[] cookieTokens) throws Exception {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cookieTokens.length; i++) {
			sb.append(cookieTokens[i]);

			if (i < cookieTokens.length - 1) {
				sb.append(":");
			}
		}

		String value = sb.toString();

		sb = new StringBuilder(new String(
				Base64.encode(value.getBytes(charset))));

		while (sb.charAt(sb.length() - 1) == '=') {
			sb.deleteCharAt(sb.length() - 1);
		}

		return sb.toString();
	}

	public static String[] decodeToken(String cookieValue) throws IOException {
		for (int j = 0; j < cookieValue.length() % 4; j++) {
			cookieValue = cookieValue + "=";
		}

		String cookieAsPlainText = new String(Base64.decode(cookieValue
				.getBytes(charset)));
		String[] tokens = StringUtils.delimitedListToStringArray(
				cookieAsPlainText, ":");

		if (((tokens[0].equalsIgnoreCase("http")) || (tokens[0]
				.equalsIgnoreCase("https"))) && (tokens[1].startsWith("//"))) {
			String[] newTokens = new String[tokens.length - 1];
			newTokens[0] = (tokens[0] + ":" + tokens[1]);
			System.arraycopy(tokens, 2, newTokens, 1, newTokens.length - 1);
			tokens = newTokens;
		}

		return tokens;
	}

	/*public static String getToken(String loginname, String password,
			long expiryTime) throws Exception {
		assert ((loginname != null) && (!"".equals(loginname.trim()))) : "loginname 不能为空！";
		assert ((password != null) && (!"".equals(password.trim()))) : "password 不能为空！";

		long time = System.currentTimeMillis() + expiryTime * 60 * 1000L;
		return encodeToken(new String[] { loginname, Long.toString(time),
				makeTokenSignature(time, loginname, password) });
	}*/

	/*public static String getToken(String loginname, String password)
			throws Exception {
		return getToken(loginname, password, 30);
	}

	public static void main(String[] args) throws Exception {
		String token = getToken("3236005|南京女子监狱", "123", 1);
		System.out.println(token);
		String[] tokenArray = decodeToken(token);
		if ((tokenArray != null) && (tokenArray.length > 0))
			for (String tok : tokenArray)
				System.out.println(tok);
	}
	*/

	/*public static String[] decodeTokenFromUrl(String token) throws Exception {
		String[] tokenArray = (String[]) null;
		try {
			tokenArray = decodeToken(token);
			long tokenExpiryTime;
			if (tokenArray.length != 3) {
				throw new InvalidCookieException(
						"Cookie token did not contain 3 tokens, but contained '"
								+ Arrays.asList(tokenArray) + "'");
			}
			try {
				tokenExpiryTime = new Long(tokenArray[1]).longValue();
			} catch (NumberFormatException nfe) {

				throw new InvalidCookieException(
						"Cookie token[1] did not contain a valid number (contained '"
								+ tokenArray[1] + "')");
			}
			if (isTokenExpired(tokenExpiryTime)) {
				throw new InvalidCookieException(
						"Cookie token[1] has expired (expired on '"
								+ new Date(tokenExpiryTime)
								+ "'; current time is '" + new Date() + "')");
			}
			String expectedTokenSignature = makeTokenSignature(tokenExpiryTime,
					tokenArray[0], "");

			if (!equals(expectedTokenSignature, tokenArray[2]))
				throw new InvalidCookieException(
						"Cookie token[2] contained signature '" + tokenArray[2]
								+ "' but expected '" + expectedTokenSignature
								+ "'");
		} catch (IOException e) {
			throw new InvalidCookieException("the token from url is error'");
		}
		return tokenArray;
	}*/

	/*private static boolean isTokenExpired(long tokenExpiryTime) {
		return tokenExpiryTime < System.currentTimeMillis();
	}

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
	}*/

	/*private static byte[] bytesUtf8(String s) {
		if (s == null) {
			return null;
		}
		return Utf8.encode(s);
	}*/
}