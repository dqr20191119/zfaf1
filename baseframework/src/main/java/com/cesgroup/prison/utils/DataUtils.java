/**
 * <p>Copyright:Copyright(c) 2016</p>
 * <p>Company:上海中信信息发展股份有限公司</p>
 * <p>包名:com.cesgroup.rfs.mgr.common.utils</p>
 * <p>文件名:DataUtils.java</p>
 * <p>类更新历史信息</p>
 * @author huz 
 * @date 2016-03-16 16:19
 * @todo 
 */
package com.cesgroup.prison.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;

import com.github.pagehelper.PageInfo;

/**
 * 数据转换工具
 * @author huz
 * @date 2016-03-16
 * 
 */
public class DataUtils {
	
	/**
	 * 将分页信息转换为Map
	 * @param page
	 * @return
	 */
	public static Map<String, Object> pageInfoToMap(PageInfo<?> page){
		Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("pageNumber", page.getPageNum());
        resultMap.put("totalPages", page.getPages());
        resultMap.put("total", page.getTotal());
        resultMap.put("data", page.getList());
        return resultMap;
	}
	
	/**
	 * 将分页信息转换为Map
	 * @param page
	 * @return
	 */
	public static Map<String, Object> pageToMap(Page<?> page){
		Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("pageNumber", page.getNumber() + 1);
        resultMap.put("totalPages", page.getTotalPages());
        resultMap.put("total", page.getTotalElements());
        resultMap.put("data", page.getContent());
        return resultMap;
	}
	public static Map<String, Object> success(String message){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "success");
		map.put("message", message);
		map.put("code", 200);
		return map;
	}
	
}
