package com.cesgroup.prison.code.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.code.dao.CodeMapper;
import com.cesgroup.prison.code.entity.Code;
import com.cesgroup.prison.code.service.CodeMaintainService;

/**   
*    
* @projectName：prison   
* @ClassName：CodeController   
* @Description：编码表访问Controller   
* @author：zhengke   
* @date：2017-11-24 09:49   
* @version        
*/
@Controller
@RequestMapping(value = "/code")
public class CodeController extends BaseEntityDaoServiceCRUDController<Code, String, CodeMapper, CodeMaintainService> {

	/**
	 * 根据编码组KEY获取编码
	 * @param loadType 加载类型，加载类型【1：匹配KEY后几位字符；2：匹配KEY前几位字符；3：剔除的KEY，如：1,2,3；4：包含哪些KEY】
	 * @param regex 匹配KEY字符串
	 * @param showValid 是否只显示有效编码，true：只显示有效编码，false：显示全部编码
	 * @return 
	 * 
	 */
	@RequestMapping(value = "/searchCode")
	@ResponseBody
	public List<Map<String, Object>> searchCode(String groupKey,
			@RequestParam(value="loadType", required = false, defaultValue = "0") int loadType,
			@RequestParam(value="regex", required = false, defaultValue = "") String regex,
			@RequestParam(value="showValid", required = false, defaultValue = "true") String showValid) {
		
		int level=1;
		Code code = service.loadCode(groupKey,null,level,Boolean.valueOf(showValid));
		
		List<Map<String, Object>> list=new ArrayList<Map<String, Object>>();
		if(code != null && CollectionUtils.isNotEmpty(code.getCodeList())) {
			Collections.sort(list, new Comparator() {
				public int compare(Object o1, Object o2) {
					Code c1 = (Code) o1;
					Code c2 = (Code) o2;
					return c1.getOrderNumber() - c2.getOrderNumber();
				}
			});
			
			if(loadType == 1) {
				for(Code child : code.getCodeList()) {
					if(child.getCodeKey().endsWith(regex)) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("value", child.getCodeKey());
						map.put("text", child.getName());
						map.put("hidden", child.getIsValid()==1?false:true);
						list.add(map);
					}
				}
			} else if(loadType == 2) {
				for(Code child : code.getCodeList()) {
					if(child.getCodeKey().startsWith(regex)) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("value", child.getCodeKey());
						map.put("text", child.getName());
						map.put("hidden", child.getIsValid()==1?false:true);
						list.add(map);
					}
				}
			} else if(loadType == 3) {
				for(Code child : code.getCodeList()) {
					if(!(","+regex+",").contains(","+child.getCodeKey()+",")) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("value", child.getCodeKey());
						map.put("text", child.getName());
						map.put("hidden", child.getIsValid()==1?false:true);
						list.add(map);
					}
				}
			} else if(loadType == 4) {
				for(Code child : code.getCodeList()) {
					if((","+regex+",").contains(","+child.getCodeKey()+",")) {
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("value", child.getCodeKey());
						map.put("text", child.getName());
						map.put("hidden", child.getIsValid()==1?false:true);
						list.add(map);
					}
				}
			} else {
				for(Code child : code.getCodeList()) {
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("value", child.getCodeKey());
					map.put("text", child.getName());
					map.put("hidden", child.getIsValid()==1?false:true);
					list.add(map);
				}
			}
			
		}
		
		return list;
	}
	
	/**
	 * 对于层级为树结构的，需要单独取出某一层使用此方法
	 * @param level 遍历层级
	 * @param showValid 是否只显示有效编码，true：只显示有效编码，false：显示全部编码
	 * @return 
	 */
	@RequestMapping(value = "/searchCodeTree")
	@ResponseBody
	public List<Map<String, Object>> getCode4ComboJsonByLevel(String groupKey,String codeKey,
			@RequestParam(value="level", required = false, defaultValue = "1") int level,
			@RequestParam(value="showValid", required = false, defaultValue = "true") String showValid) {
		
		Code groupCode = service.loadCode(groupKey, codeKey, level== 0 ? 1 : level, Boolean.valueOf(showValid));
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		if(groupCode != null) {
			Map map = new HashMap();
			map.put("id", groupCode.getCodeKey());
			map.put("name", groupCode.getName());
			map.put("hidden", groupCode.getIsValid()==1?false:true);
			List<Code> childList = groupCode.getCodeList();
			if(childList != null && !childList.isEmpty()) {
				List child = service.recursive(childList);
				if(child != null && !child.isEmpty()) {
					map.put("children", child);
				}
				list.add(map);
			}
		}
		
		return list;
	}
}
