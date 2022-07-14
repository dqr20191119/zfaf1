package com.cesgroup.framework.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 业务层异常定义
 * @author lincoln.cheng
 *
 * 2019年1月15日
 */
public class RESTHttpClientException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(RESTHttpClientException.class);  
	
	public RESTHttpClientException() {}
	
	public RESTHttpClientException(String message) throws RESTHttpClientException {
		super(message);
		logger.error(message);
	}
	
	public RESTHttpClientException(String message, Throwable cause) throws RESTHttpClientException {
		super(message, cause);
		logger.error(message, cause);
	}
}
