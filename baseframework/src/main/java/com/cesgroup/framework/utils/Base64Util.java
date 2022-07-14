package com.cesgroup.framework.utils;

import java.io.UnsupportedEncodingException;

import org.apache.commons.codec.binary.Base64;

public class Base64Util {

    /**
     * 功能描述：单次解密
     * @param bytes
     * @return
     */
    public static byte[] decode(byte[] bytes) {
        return Base64Util.decode(bytes, 1);
    }
    
    /**
     * 功能描述：多次解密
     * @param bytes
     * @param times
     * @return
     */
    public static byte[] decode(byte[] bytes, int times) {
        byte[] bytesTmp = bytes;
        if(bytesTmp != null && bytesTmp.length > 0) {
            if(times > 0) {
                times --;
                bytesTmp = Base64.decodeBase64(bytesTmp);
                return Base64Util.decode(bytesTmp, times);
            }
        }
        return bytesTmp;
    }

    /**
     * 功能描述：单次解密
     * @param str
     * @return
     */
    public static byte[] decode(String str) {
        return Base64Util.decode(str, 1);
    }
    
    /**
     * 功能描述：多次解密
     * @param bytes
     * @param times
     * @return
     */
    public static byte[] decode(String str, int times) {
        try {
            return Base64Util.decode(str.getBytes("UTF-8"), times);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    
    /**
     * 功能描述：单次解密
     * @param str
     * @return
     */
    public static String decodeString(byte[] bytes) {
        return Base64Util.decodeString(bytes, 1);
    }
    
    /**
     * 功能描述：多次解密
     * @param str
     * @param times
     * @return
     */
    public static String decodeString(byte[] bytes, int times) {
        try {
            return new String(Base64Util.decode(bytes, times), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 功能描述：单次解密
     * @param str
     * @return
     */
    public static String decodeString(String str) {
        return Base64Util.decodeString(str, 1);
    }
    
    /**
     * 功能描述：多次解密
     * @param str
     * @param times
     * @return
     */
    public static String decodeString(String str, int times) {
        try {
            return Base64Util.decodeString(str.getBytes("UTF-8"), times);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    
    /**
     * 功能描述：单次加密
     * @param bytes
     * @return
     */
    public static byte[] encode(byte[] bytes) {
        return Base64Util.encode(bytes, 1);
    }
    
    /**
     * 功能描述：多次加密
     * @param bytes
     * @param times
     * @return
     */
    public static byte[] encode(byte[] bytes, int times) {
        byte[] bytesTmp = bytes;
        if(bytesTmp != null && bytesTmp.length > 0) {
            if(times > 0) {
                times--;
                bytesTmp = Base64.encodeBase64(bytesTmp);
                return Base64Util.encode(bytesTmp, times);
            }
        }
        return bytesTmp;
    }

    /**
     * 功能描述：单次加密
     * @param bytes
     * @return
     */
    public static byte[] encode(String str) {
        return Base64Util.encode(str, 1);
    }
    
    /**
     * 功能描述：多次加密
     * @param bytes
     * @param times
     * @return
     */
    public static byte[] encode(String str, int times) {
        try {
            return Base64Util.encode(str.getBytes("UTF-8"), times);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 功能描述：单次加密
     * @param str
     * @return
     */
    public static String encodeString(byte[] bytes) {
        return Base64Util.encodeString(bytes, 1);
    }
    
    /**
     * 功能描述：多次加密
     * @param str
     * @param times
     * @return
     */
    public static String encodeString(byte[] bytes, int times) {
        try {
            return new String(Base64Util.encode(bytes, times), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }
    
    /**
     * 功能描述：单次加密
     * @param str
     * @return
     */
    public static String encodeString(String str) {
        return Base64Util.encodeString(str, 1);
    }
    
    /**
     * 功能描述：多次加密
     * @param str
     * @param times
     * @return
     */
    public static String encodeString(String str, int times) {
        try {
            if(str != null && !"".equals(str)) {
                return Base64Util.encodeString(str.getBytes("UTF-8"), times);
            }
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return "";
    }
    
}
