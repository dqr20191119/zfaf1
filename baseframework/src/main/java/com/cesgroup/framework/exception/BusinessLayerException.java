package com.cesgroup.framework.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Description: 业务层异常定义
 * @author lincoln.cheng
 *
 * 2019年1月15日
 */
public class BusinessLayerException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Logger logger = LoggerFactory.getLogger(BusinessLayerException.class);  
	
	public BusinessLayerException() {}
	
	public BusinessLayerException(String message) throws BusinessLayerException {
		super(message);
		logger.error(message);
	}
	
	public BusinessLayerException(String message, Throwable cause) throws BusinessLayerException {
		super(message, cause);
		logger.error(message, cause);
	}
}
