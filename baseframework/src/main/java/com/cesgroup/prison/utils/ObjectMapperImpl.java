package com.cesgroup.prison.utils;

import com.cesgroup.framework.springmvc.utils.ObjectMapperFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * jsonobject 重写，实现对null值的转换
 * 
 * @author xiexiaqin
 * @date 2016-06-22
 *
 */
public class ObjectMapperImpl extends ObjectMapperFactory {

	@Override
	public ObjectMapper createJsonMapper() {
		CustomObjectMapper objectMapper = new CustomObjectMapper();
		return objectMapper;
	}
	
}
