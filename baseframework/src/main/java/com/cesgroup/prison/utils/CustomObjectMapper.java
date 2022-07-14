package com.cesgroup.prison.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * 自定义全局jackson序列化
 * 
 * @author xiexiaqin
 * @date 2016-06-22
 *
 */
public class CustomObjectMapper extends ObjectMapper {  

  
    /**
	 * 序列化
	 */
	private static final long serialVersionUID = 1L;

	public CustomObjectMapper(){  
        super();  
        //设置null转换""  
        getSerializerProvider().setNullValueSerializer(new NullSerializer());  
        //设置日期转换yyyy-MM-dd HH:mm:ss  
       // setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));  
    }  
      
	/**
	 * null的json序列
	 * 
	 * @author xiexiaqin
	 * @date 2016-06-22
	 *
	 */
    private class NullSerializer extends JsonSerializer<Object> {  
        public void serialize(Object value, JsonGenerator jgen,  
                SerializerProvider provider) throws IOException,  
                JsonProcessingException {  
            	jgen.writeString("");  
        }  
    }  
}
