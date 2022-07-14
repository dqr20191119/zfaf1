package com.cesgroup.prison.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * 自定义转换类
 * 实现全局参数绑定转换date
 * @author xiexiaqin
 * @date 2016-06-22
 *
 */
public class CustomConverter  implements Converter<String, Date> {
	private String dateFormatPattern;  //转换的格式  
	 
    public CustomConverter(String dateFormatPattern) {  
	           this.dateFormatPattern = dateFormatPattern;  
	   }

	@Override
	public Date convert(String source) {
		 
        if(!StringUtils.hasLength(source)) {  
             return null;  
         }  
         DateFormat df = new SimpleDateFormat(dateFormatPattern);  
         try {  
             return df.parse(source);  
         } catch (ParseException e) {  
             throw new IllegalArgumentException(String.format("类型转换失败，需要格式%s，但格式是[%s]", dateFormatPattern, source));   
         }  
	}

}
