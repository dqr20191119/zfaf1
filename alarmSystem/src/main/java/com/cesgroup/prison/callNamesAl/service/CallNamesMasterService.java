package com.cesgroup.prison.callNamesAl.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.callNamesAl.bean.CallNameJsonRootBean;
import com.cesgroup.prison.callNamesAl.bean.CallNamesJsDataBean;
import com.cesgroup.prison.callNamesAl.entity.CallNamesAreaDtlsEntity;
import com.cesgroup.prison.callNamesAl.entity.CallNamesMasterEntity;
import com.cesgroup.prison.callNamesAl.entity.CallNamesResultEntity;

public interface CallNamesMasterService extends IBaseCRUDService<CallNamesMasterEntity, String> {

	public Page<Map<String, Object>> listAll(CallNamesMasterEntity entity, Pageable pageable);

	public Page<Map<String, Object>> listAllForJsInfo(CallNamesAreaDtlsEntity entity, Pageable pageable);

	public Page<Map<String, Object>> listAllForZfInfo(CallNamesResultEntity entity, Pageable pageable);

	public void addInfo(CallNamesMasterEntity entity) throws Exception;

	public void updateInfo(CallNamesMasterEntity entity) throws Exception;

	public void deleteByIds(List<String> list);

	public CallNamesMasterEntity findById(String id);

	public List<Map<String, Object>> findForTree(HttpServletRequest request, HttpServletResponse response) throws Exception;

	public List<Map<String, Object>> findJsAndZfsByLc(HttpServletRequest request, HttpServletResponse response);

	/**
	 * @methodName: beginCallNames
	 * @Description: 开始点名
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 *             String
	 */
	public String beginCallNames(HttpServletRequest request, HttpServletResponse response) throws Exception;

	/**
	 * @methodName: EndCallNames
	 * @Description: 结束点名
	 * @param jyh
	 * @param lch
	 * @param jshs
	 * @return
	 * @throws Exception String
	 * @throws
	 */
	public String EndCallNames(String jyh, String lch, List<String> jshs) throws Exception;

	/**
	 * @methodName: getPrisonerIndcByJs
	 * @Description: 根据监狱号、楼层号和监舍号获取监舍罪犯编号list
	 * @param jyh 监狱号
	 * @param lch 楼层号
	 * @param jsh 监舍号
	 * @return List<String>
	 * @throws
	 */
	public List<String> getPrisonerIndcByJs(String jyh, String lch, String jsh);

	/**
	 * @methodName: isCallNamesIng
	 * @Description: 根据监狱号、楼层号 判断当前楼层是否在点名
	 * @param entity
	 * @return int 0 否 1 是
	 * @throws
	 */
	public int isCallNamesIng(CallNamesMasterEntity entity);

	/**
	 * @throws Exception
	 * @methodName: getCallNamesDtlsByJs
	 * @Description: 根据js获取点名详情 ，前端调用
	 * @param jyh 监狱号
	 * @param lch 楼层号
	 * @param jshs 监舍号list 如果前面界面显示监舍都已经点名完成，list就为空
	 * @throws
	 */
	public List<CallNamesJsDataBean> getCallNamesDtlsByJs(String jyh, String lch, List<String> jshs) throws Exception;

	/**
	 * @methodName: getCallNamesPrisonerDtlsByJs
	 * @Description: 根据监舍获取罪犯点名状况， 前端调用
	 * @param jyh
	 * @param lch
	 * @param jsh
	 * @return Map<String,Object>
	 * @throws
	 */
	public CallNamesJsDataBean getCallNamesPrisonerDtlsByJs(String jyh, String lch, String jsh);

	/**
	 * @methodName: getCallNamesDtlsByLc
	 * @Description: 根据楼层判断是否正在点名，前端调用 （界面轮询调用判断是不是进入点名状态）
	 * @param jyh
	 * @param lch
	 * @return CallNameJsonRootBean
	 * @throws
	 */
	public CallNameJsonRootBean getCallNamesDtlsByLc(String jyh, String lch);

	/**
	 * @methodName: getPrisonerInfoByJsFromCache
	 * @Description: 从缓存取罪犯信息
	 * @param jyh
	 * @param lch
	 * @param jsh
	 * @return
	 * 		List<Map<String,Object>>
	 */
	public List<Map<String, Object>> getPrisonerInfoByJsFromCache(String jyh, String lch, String jsh);

	void saveResult(String jyh, String lch, String jsh, String userId, CallNamesJsDataBean callNamesJsDataBean) throws Exception;

}
