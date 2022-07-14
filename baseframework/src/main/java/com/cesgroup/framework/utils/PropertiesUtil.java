package com.cesgroup.framework.utils;

import java.util.ResourceBundle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * read variable from *.properties file
 * @author ChengJie
 *
 */
public class PropertiesUtil {
	private static final Logger log = LoggerFactory.getLogger(PropertiesUtil.class);
	
	/**
	 * get properties value by fileName and variable key
	 * the key will be change to upper case character
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String getValueByKeyUpperCase(String fileName, String key) {
		log.info("getValueByKeyUpperCase from '{}' by key '{}'", fileName, key.toUpperCase());
		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		String value = rb.getString(key.toUpperCase()).trim();
		log.info("{} = {}", key.toUpperCase(), value);
		return value;
	}

	/**
	 * get properties value by fileName and variable key
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static String getValueByKeyUnchanged(String fileName, String key) {
		log.info("getValueByKeyUnchanged from '{}' by key '{}'", fileName, key);
		ResourceBundle rb = ResourceBundle.getBundle(fileName);
		String value = rb.getString(key).trim();
		log.info("{} = {}", key, value);
		return value;
	}

	/**
	 * get properties value by fileName and variable key
	 * @param fileName
	 * @param key
	 * @return
	 */
	public static boolean getBooleanByKeyUnchanged(String fileName, String key) {
		try {
			log.info("getBooleanByKeyUnchanged from '{}' by key '{}'", fileName, key);
			ResourceBundle rb = ResourceBundle.getBundle(fileName);
			String value = rb.getString(key);
			log.info("{} = {}", key, value);
			if(value != null && !value.trim().isEmpty()) {
				return Boolean.parseBoolean(value.trim());
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
	
}
