package com.cesgroup.prison.code.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.code.dao.CodeMapper;
import com.cesgroup.prison.code.entity.Code;
import com.cesgroup.prison.code.tool.GroupKeyConst;
import com.cesgroup.prison.code.tool.JsonUtil;
import com.cesgroup.prison.code.tool.PinYin4jUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**   
*    
* @projectName：prison   
* @ClassName：CodeMaintainService   
* @Description： 编码Service  
* @author：zhengke   
* @date：2017-11-24 09:48   
* @version        
*/
@Service
public class CodeMaintainService extends BaseService<Code, String> {

	@Autowired
	private CodeMapper codeMapper;
	
	/**
	 * 根据编码组 KEY 获取编码组对象
	 * @param groupKey
	 * @return
	 */
	public Code loadGroupCode(String groupKey) {
		return codeMapper.loadGroupCode(groupKey);
	}
	
	/**
	 * 根据编码组 KEY 和当前编码KEY 获取当前编码组对象
	 * @param groupKey
	 * @return
	 */
	public Code loadCodeSelf(String groupKey, String currentCodeKey) {
		Map<String,String> map =new HashMap<String,String>();
		map.put("groupKey", groupKey);
		map.put("currentCodeKey", currentCodeKey);
		
		return codeMapper.loadCodeSelf(map);
	}
	
	/**
	 * 将当前的currentCode 添加到 groupCode对象中
	 * @param superCode	编码组 编码对象
	 * @param currentCode 当前需要处理的编码对象
	 */
	private void addCode(Code groupCode, Code currentCode) {
		if(currentCode.getParentId().equals(groupCode.getId())) {
			groupCode.setCodeList(currentCode);
		}
	}
	
	/**
	 * 根据编码组 和 该编码组下的所有编码Map ，组装Code 树
	 * @param superCode
	 * @param codeMap
	 */
	private void addCode(Code superCode, Map<String, List<Code>> codeMap) {
 		if(codeMap.containsKey(superCode.getId())) {
			List<Code> codeList = codeMap.get(superCode.getId());
			if(codeList != null) {
				superCode.setCodeList(codeList);
				for(Code code_ : codeList) {
					addCode(code_, codeMap);
				}
			}
		}
	}
	
	/**
	 * 根据当前编码对象 和 保留的层级 ，对对象进行过滤
	 * @param code 当前编码对象
	 * @param level 需要保留几层
	 */
	private void filterCodeBylevel(Code code, int level) {
		filterCodeBylevel(code, level, 0);
	}

	private void filterCodeBylevel(Code code, int level, int current) {
		if (code.getCodeList() != null) {
			for (Code code_ : code.getCodeList()) {
				if (current < level) {
					filterCodeBylevel(code_, level, current + 1);
				} else
					code.codeList = null;
			}
		}
	}
	
	public static List recursive(List list) {
		List recList = Lists.newArrayList();
		if(CollectionUtils.isNotEmpty(list)) {
			for(Object obj : list) {
				Code code = (Code)obj;
				Map map = Maps.newHashMap();
				map.put("id", code.getCodeKey());
				map.put("name", code.getName());
				map.put("hidden", code.getIsValid()==1?false:true);
				
				if(CollectionUtils.isEmpty(code.getCodeList())) {
					map.put("open", "false");
				}
				
				List childList = code.getCodeList();
				if (childList != null && !childList.isEmpty()) {
					List child = recursive(childList);
					if (child != null && !child.isEmpty()) {
						map.put("children", child);
					}
				}
				if(map != null && !map.isEmpty()) {
					recList.add(map);
				}
			}
		}
		return recList;
	}
	private static void recursive(List<Code> codeList,List list) {
		if(CollectionUtils.isNotEmpty(codeList)) {
			for(Object obj : codeList) {
				Code code = (Code)obj;
				Map map = new HashMap();
				map.put("pId", code.getParentId());
				map.put("id", code.getCodeKey());
				map.put("name", code.getName());
				map.put("hidden", code.getIsValid()==1?false:true);
				map.put("jianpinLower", PinYin4jUtil.getHanyuPinyinOfHanzi(code.getName().replaceAll(" ", ""), true, true));
				map.put("jianpinUpper", StringUtils.upperCase(String.valueOf(map.get("jianpinLower"))));
				list.add(map);
				
				List<Code> childList = code.getCodeList();
				if (childList != null && !childList.isEmpty()) {
					recursive(childList,list);
				}
			}
		}
	}
	
	/**
	 * 根据编码组编码获取 其孩子节点编码集合，默认返回一层,深度=1(level = 1) currentCode =null
	 * @param superCode 编码组 编码KEY名称[ 键(key) ]
	 * @return
	 */
	public Code loadCode(String groupKey,boolean showValid) {
		return loadCode(groupKey, null, 1,showValid);
	}
	
	/**
	 * 根据编码组编码 + 当前编码KEY 获取 其孩子节点编码集合，默认返回一层,深度=1(level = 1)
	 * @param groupKey 编码组 编码KEY名称[ 键(key) ]
	 * @param currentCodeKey 当前编码 编码KEY名称[ 键(key) ]
	 * @return
	 */
	public Code loadCode(String groupKey, String currentCodeKey,boolean showValid) {
		return loadCode(groupKey, currentCodeKey, 1,showValid);
	}
	
	/**
	 * .根据编码组KEY，是否有效查询编码
	 * @param groupKey 编码组KEY
	 * @param level 遍历层级
	 * @param showValid 是否只显示有效编码，true：只显示有效编码，false：显示全部编码
	 * @return Code
	 */
	public Code loadCode(String groupKey, String codeKey, Integer level, boolean showValid) {
		// 编码组下的所有编码值
		Map<String, List<Code>> codeMap = new HashMap<String, List<Code>>();
		
		if(groupKey==null) {
			return null;
		}
		
		//遍历层级默认为1
		if(level==0) {
			level=1;
		}
		
		Code code;
		if(codeKey==null || codeKey.equals("")) {
			code=loadGroupCode(groupKey);
		} else {
			code = loadCodeSelf(groupKey, codeKey);
		}
		
		Map<String,Object> map =new HashMap<String,Object>();
		map.put("groupKey", groupKey);
		map.put("codeKey", codeKey);
		map.put("level", level);
		map.put("showValid", showValid);
		List<Code> codeList=codeMapper.loadCode(map);
		for (Code code_tmp : codeList) {
			String parentId_ =code_tmp.getParentId();
			
			if(level==1){// 如果只获取一层，则直接返回code对象
				addCode(code,code_tmp);
			}else{// 如果多层，则需要进行计算得出
				if (codeMap.get(parentId_) == null) {
					codeMap.put(parentId_, new ArrayList<Code>());
				}
				codeMap.get(parentId_).add(code_tmp);
			}
		}
		
		if (level != 1) {
			addCode(code, codeMap);// 组装全部编码，得到一颗编码树
			filterCodeBylevel(code, level);// 根据给定的深度 ，过滤编码树的层级
		}
		
		return code;
	}
	
	/**
	 * 根据编码组编码获取 其孩子节点编码集合JSON，默认返回一层,深度=1(level = 1) currentCode =null
	 * @param superCode 编码组 编码KEY名称[ 键(key) ]
	 * @return
	 */
	public String loadCode2Json(String groupKey, boolean showValid) {
		return loadCode2Json(groupKey, null, 1, showValid);
	}
	
	/**
	 * 根据编码组编码 + 当前编码KEY 获取 其孩子节点编码集合JSON，默认返回一层,深度=1(level = 1)
	 * @param groupKey 编码组 编码KEY名称[ 键(key) ]
	 * @param currentCodeKey 当前编码 编码KEY名称[ 键(key) ]
	 * @return
	 */
	public String loadCode2Json(String groupKey, String currentCodeKey, boolean showValid) {
		return loadCode2Json(groupKey, currentCodeKey, 1, showValid);
	}
	
	/**
	 * 根据编码组编码 + 当前编码KEY + 编码获取深度 获取 其孩子节点编码集合JSON
	 * @param superCode 编码组 编码KEY名称[ 键(key) ]
	 * @param currentCode 当前编码 编码KEY名称[ 键(key) ]
	 * @param level 需要获取编码的 深度
	 * @return
	 */	
	public String loadCode2Json(String groupKey, String currentCodeKey,int level,boolean showValid) {
		// -------------需要根据组件库的要求进行递归遍历，后期需要完善
		Code code = null;
		if(showValid) {
			code = loadCode(groupKey, currentCodeKey, level,true);
		} else {
			code = loadCode(groupKey, currentCodeKey, level,false);
		}
		
		List list = new ArrayList();
		if (code.getCodeList() != null) {
			List<Code> codeList = code.getCodeList();
			for (int i = 0; i < codeList.size(); i++) {
				Code childCode = codeList.get(i);
				Map map = Maps.newHashMap();
				//当编码组为行政区划的时候,显示所有项,包括已失效的.
				if(GroupKeyConst.GROUP_CODE_KEY_XZQH.equals(groupKey)) {
					map.put("hidden",false);
				} else {
					map.put("hidden", childCode.getIsValid()==1?false:true);
				}
				map.put("text", childCode.getName());
				map.put("value", childCode.getCodeKey());
				//map.put("jianpinLower", PinYin4jUtil.getHanyuPinyinOfHanzi(childCode.getName().replaceAll(" ", ""), true, true));
				//map.put("jianpinUpper", StringUtils.upperCase(String.valueOf(map.get("jianpinLower"))));
				//提供是否有效状态位
				map.put("isValid",childCode.getIsValid());
				
				list.add(map);
			}
		}
		
		return JsonUtil.objectToJsonStr(list);
	}
	
	/**
	 * @param groupKey
	 * @param currentCodeKey
	 * @param level
	 * @param showValid
	 * @return
	 */
	public String loadCode2SimpleTreeJson(String groupKey, String currentCodeKey, int level, boolean showValid) {
		List<?> list = Lists.newArrayList();
		Code groupCode = null;
		if(showValid) {
			groupCode = this.loadCode(groupKey, currentCodeKey, level,true);
		} else {
			groupCode = this.loadCode(groupKey, currentCodeKey, level, false);
		}
		
		List<Code> childList=groupCode.getCodeList();
		recursive(childList,list);
		return JsonUtil.objectToJsonStr(list);
	}
	
	private List<Map> loadCode4Regex(Code code,int loadType,String regex) {
		List list = Lists.newArrayList();
		if (code != null && CollectionUtils.isNotEmpty(code.getCodeList())) {
			if(loadType == 1) {
				for (Code child : code.getCodeList()) {
					if(child.getCodeKey().endsWith(regex)) {
						Map map = Maps.newHashMap();
						map.put("value", child.getCodeKey());
						map.put("text", child.getName());
						map.put("hidden", child.getIsValid()==1?false:true);
						map.put("jianpinLower", PinYin4jUtil.getHanyuPinyinOfHanzi(child.getName().replaceAll(" ", ""), true, true));
						map.put("jianpinUpper", StringUtils.upperCase(String.valueOf(map.get("jianpinLower"))));
						list.add(map);
					}
				}
			} else if(loadType == 2) {
				for (Code child : code.getCodeList()) {
					if(child.getCodeKey().startsWith(regex)) {
						Map map = Maps.newHashMap();
						map.put("value", child.getCodeKey());
						map.put("text", child.getName());
						map.put("hidden", child.getIsValid()==1?false:true);
						map.put("jianpinLower", PinYin4jUtil.getHanyuPinyinOfHanzi(child.getName().replaceAll(" ", ""), true, true));
						map.put("jianpinUpper", StringUtils.upperCase(String.valueOf(map.get("jianpinLower"))));
						list.add(map);
					}
				}
			} else if(loadType == 3) {
				for(Code child : code.getCodeList()) {
					if(!(","+regex+",").contains(","+child.getCodeKey()+",")) {
						Map map = Maps.newHashMap();
						map.put("value", child.getCodeKey());
						map.put("text", child.getName());
						map.put("hidden", child.getIsValid()==1?false:true);
						map.put("jianpinLower", PinYin4jUtil.getHanyuPinyinOfHanzi(child.getName().replaceAll(" ", ""), true, true));
						map.put("jianpinUpper", StringUtils.upperCase(String.valueOf(map.get("jianpinLower"))));
						list.add(map);
					}
				}
			} else if(loadType == 4) {
				for(Code child : code.getCodeList()) {
					if((","+regex+",").contains(","+child.getCodeKey()+",")) {
						Map map = Maps.newHashMap();
						map.put("value", child.getCodeKey());
						map.put("text", child.getName());
						map.put("hidden", child.getIsValid()==1?false:true);
						map.put("jianpinLower", PinYin4jUtil.getHanyuPinyinOfHanzi(child.getName().replaceAll(" ", ""), true, true));
						map.put("jianpinUpper", StringUtils.upperCase(String.valueOf(map.get("jianpinLower"))));
						list.add(map);
					}
				}
			} else if(loadType == 5) {
				for(Code child : code.getCodeList()) {
					if(!(","+regex+",").contains(","+child.getType()+",")) {
						Map map = Maps.newHashMap();
						map.put("value", child.getCodeKey());
						map.put("text", child.getName());
						map.put("hidden", child.getIsValid()==1?false:true);
						map.put("jianpinLower", PinYin4jUtil.getHanyuPinyinOfHanzi(child.getName().replaceAll(" ", ""), true, true));
						map.put("jianpinUpper", StringUtils.upperCase(String.valueOf(map.get("jianpinLower"))));
						list.add(map);
					}
				}
			} else if(loadType == 6) {
				for(Code child : code.getCodeList()) {
					if((","+regex+",").contains(","+child.getType()+",")) {
						Map map = Maps.newHashMap();
						map.put("value", child.getCodeKey());
						map.put("text", child.getName());
						map.put("hidden", child.getIsValid()==1?false:true);
						map.put("jianpinLower", PinYin4jUtil.getHanyuPinyinOfHanzi(child.getName().replaceAll(" ", ""), true, true));
						map.put("jianpinUpper", StringUtils.upperCase(String.valueOf(map.get("jianpinLower"))));
						list.add(map);
					}
				}
			} else {
				for(Code child : code.getCodeList()) {
					Map map = Maps.newHashMap();
					map.put("value", child.getCodeKey());
					map.put("text", child.getName());
					map.put("hidden", child.getIsValid()==1?false:true);
					map.put("jianpinLower", PinYin4jUtil.getHanyuPinyinOfHanzi(child.getName().replaceAll(" ", ""), true, true));
					map.put("jianpinUpper", StringUtils.upperCase(String.valueOf(map.get("jianpinLower"))));
					list.add(map);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * @param groupKey
	 * @param loadType
	 * @param regex
	 * @param codes
	 * @return
	 */
	public String loadCode4ComboJson(String groupKey, int loadType, String regex, String codes) {
		Code code = this.loadCode(groupKey, false);
		List<Map> list = this.loadCode4Regex(code, loadType, regex);
		if(CollectionUtils.isNotEmpty(list)&&StringUtils.isNotBlank(codes)) {
			List _codes = Arrays.asList(codes.split(","));
			for (int i=list.size()-1;i>=0;i--) {
				Map map = list.get(i);
				String codeKey = String.valueOf(map.get("value"));
				if(_codes.contains(codeKey)){
					list.remove(map);
				}
			}
		}
		
		return JsonUtil.objectToJsonStr(list);
	}
	
	/**
	 * @param groupKey
	 * @param loadType
	 * @param regex
	 * @return
	 */
	public String loadCode4ComboJson(String groupKey, int loadType, String regex, boolean showValid) {
		Code code = null;
		if(showValid) {
			code = this.loadCode(groupKey,true);
		} else {
			code = this.loadCode(groupKey, false);
		}
		
		List list = this.loadCode4Regex(code, loadType, regex);
		return JsonUtil.objectToJsonStr(list);
	}
	
	public String loadCode4CombotreeJson(String groupKey, String currentCodeKey, int level, boolean showValid) {
		Code groupCode = null;
		if(showValid) {
			groupCode = this.loadCode(groupKey, currentCodeKey, level,true);
		} else {
			groupCode = this.loadCode(groupKey, currentCodeKey, level, false);
		}
		
		Map map = new HashMap();
		if(groupCode!=null) {
			map.put("id", groupCode.getCodeKey());
			map.put("name", groupCode.getName());
			map.put("hidden", groupCode.getIsValid()==1?false:true);
			List<Code> childList = groupCode.getCodeList();
			if (childList != null && !childList.isEmpty()) {
				List child = recursive(childList);
				if (child != null && !child.isEmpty()) {
					map.put("children", child);
				}
			}
		}
		
		return JsonUtil.objectToJsonStr(map);
	}
	
	/**
	 *.根据编码KEY获取编码名称
	 * @param groupKey 编码组KEY
	 * @param codeKeys 编码KEY，多个用“,”分开
	 * @return
	 */	
	public String getCodeNameByCodeKey(String groupKey, String codeKeys) {
		String codeName = "";
		if(StringUtils.isNotBlank(codeKeys)) {
			String[] codes = StringUtils.split(codeKeys, ",");
			for (int i = 0; i < codes.length; i++) {
				Code code = this.loadCode(groupKey, codes[i].trim(),false);
				if(code!=null)
				codeName += "，"+code.getName();
			}
			
			if(!"".equals(codeName))
				codeName = codeName.substring(1);
		}
		
		return codeName;
	}
}
