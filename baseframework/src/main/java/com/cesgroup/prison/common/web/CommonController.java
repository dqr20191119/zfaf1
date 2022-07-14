package com.cesgroup.prison.common.web;

import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.dao.CommonMapper;
import com.cesgroup.prison.common.entity.AffixEntity;
import com.cesgroup.prison.common.entity.CommonEntity;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.common.service.CommonService;

/**
 * 公共管理
 * 
 */
@Controller
@RequestMapping(value = "/common/all")
public class CommonController extends BaseEntityDaoServiceCRUDController<CommonEntity, String, CommonMapper, CommonService> {

	@Resource
	private CommonService commonService;
	
	/**
	 * 查询证据下拉框
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findEvidenceForCombobox")
	@ResponseBody
	public List<Map<String, Object>> findEvidenceForCombobox(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("einContentTypeIndc", request.getParameter("einContentTypeIndc"));
		paramMap.put("einFileTypeIndc", request.getParameter("einFileTypeIndc"));
		paramMap.put("einCusNumber", AuthSystemFacade.getLoginUserInfo().getOrgCode());
		
		return commonService.findEvidenceForCombobox(paramMap);
	}
	
	/**
	 * 区域视角下拉树
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findAreaViewFuncForCombotree")
	@ResponseBody
	public List<Map<String, Object>> findAreaViewFuncForCombotree(HttpServletRequest request, HttpServletResponse response) {
		
		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber"));				// 监狱id
		paramMap.put("id", request.getParameter("id"));								// 节点id
		paramMap.put("viewType", request.getParameter("viewType"));					// 视角类型		
		
		return commonService.findAreaViewFuncForCombotree(paramMap);
	}
	
	/**
	 * 获取区域-一次性获取
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/findSyncAreaForCombotree")
	@ResponseBody
	public List<Map<String, Object>> findSyncAreaForCombotree(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String cusNumber = request.getParameter("cusNumber");
		if(cusNumber == null || "".equals(cusNumber)) {
			cusNumber = AuthSystemFacade.getLoginUserInfo().getCusNumber();
		}
		
		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", cusNumber);				// 监狱id
		
		return commonService.findSyncAreaForCombotree(paramMap);
	}
	
	/**
	 * 获取此区域同级的模型
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findAreaEqualLevelModel")
	@ResponseBody
	public Map<String, Object> findNotAreaModel(HttpServletRequest request, HttpServletResponse response) {
		
		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber"));				// 监狱id
		paramMap.put("areaId", request.getParameter("areaId"));						// 区域id	
		
		return commonService.findAreaEqualLevelModel(paramMap);
	}
	
	/**
	 * 获取报警预案下拉框
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/findMasterPlanForCombobox")
	@ResponseBody
	public List<Map<String, Object>> findMasterPlanForCombobox(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber"));				// 监狱id
		
		return commonService.findMasterPlanForCombobox(paramMap);
	}
	
	/**
	 * 获取监舍信息（待修改）
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findAllJsxz")
	@ResponseBody
	public List<Map<String, Object>> findAllJsxz(HttpServletRequest request, HttpServletResponse response) {
		
		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber"));				// 监狱id
		paramMap.put("areaId", request.getParameter("areaId"));						// 区域id
		
		return commonService.findAllJsxz(paramMap);
	}
	
	/**
	 * 获取楼层监舍信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findLcjsh")
	@ResponseBody
	public List<Map<String, Object>> findLcjsh(HttpServletRequest request, HttpServletResponse response) {
		
		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber"));				// 监狱id
		paramMap.put("areaId", request.getParameter("areaId"));						// 区域id
		
		return commonService.findLcjsh(paramMap);
	}
	
	/**
	 * 获取罪犯照片
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 * @throws   
	 */
	@RequestMapping(value = "/findZfPicInfo")
	@ResponseBody
	public List<Map<String, Object>> findZfPicInfo(HttpServletRequest request, HttpServletResponse response) throws Exception {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("zfBh", request.getParameter("zfBh"));				// 罪犯编号
		paramMap.put("mtNrFl", request.getParameter("mtNrFl"));			// 媒体信息分类	3-正面照, 4-左侧照, 5-右侧照
		
		List<AffixEntity> list = commonService.findZfPicInfo(paramMap);
		if(list != null && list.size() > 0) {
			AffixEntity file = list.get(0);
			response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(file.getFileName(), "utf-8"));
	        response.addHeader("Content-Length", file.getFileSize());
	        response.setContentType("application/octet-stream");
	        		
			if(file.getLinkUrl() != null && !"".equals(file.getLinkUrl())) {
				String realPath = "D:\\jggz";	//系统文件全局上传根路径
				realPath = realPath + file.getLinkUrl().replaceAll("/", "\\\\");
				IOUtils.copy(new FileInputStream(new File(realPath)), response.getOutputStream());
			} else {
				IOUtils.write(file.getImage(), response.getOutputStream());
			}				
		}
		
		return null;
	}
	
	/**
	 * 获取标签数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findShowLabelData")
	@ResponseBody
	public List<Map<String, Object>> findShowLabelData(HttpServletRequest request, HttpServletResponse response) {
		
		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber"));				// 监狱id
		
		return commonService.findShowLabelData(paramMap);
	}
	
	/**
	 * 实时民警分布(根据区域或部门)
	 * @author zhengk
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mjfbList")
	@ResponseBody
	public List<Map<String, Object>> mjfbList(HttpServletRequest request, HttpServletResponse response) {
		
		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber"));							// 监狱id
		paramMap.put("buildingNum", request.getParameter("buildingNum"));						// 楼id
		paramMap.put("floorNum", request.getParameter("floorNum"));								// 楼层id
		paramMap.put("departmentId", request.getParameter("departmentId"));						// 部门id
		paramMap.put("drdPeopleTypeIndc", request.getParameter("drdPeopleTypeIndc"));			// 人员类型(1-民警，2-犯人，3-外来人员，4-职工)
		
		return commonService.mjfbList(paramMap);
	}
	/**
	 * 实时民警数量(根据区域或部门)
	 * @author zhengk
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/mjfbCount")
	@ResponseBody
	public Map<String, Object> mjfbCount(HttpServletRequest request, HttpServletResponse response) {
		
		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber"));							// 监狱id
		paramMap.put("buildingNum", request.getParameter("buildingNum"));						// 楼id
		paramMap.put("floorNum", request.getParameter("floorNum"));								// 楼层id
		paramMap.put("departmentId", request.getParameter("departmentId"));						// 部门id
		paramMap.put("drdPeopleTypeIndc", request.getParameter("drdPeopleTypeIndc"));			// 人员类型1-民警，2-犯人，3-外来人员，4-职工)
		
		return commonService.mjfbCount(paramMap);
	}

	@RequestMapping(value="/findDepartmentByAreaId")
	@ResponseBody
	public List<Map<String,Object>> findDepartmentByAreaId(HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber"));
		paramMap.put("areaId", request.getParameter("areaId"));

		return commonService.findDepartmentByAreaId(paramMap);
	}

	/**
	 * 定时生成区域统计信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/countAreaInfoHz")
	@ResponseBody
	public void countAreaInfoHz(HttpServletRequest request, HttpServletResponse response) throws Exception {

		commonService.countAreaInfoHz();
	}
	
	/**
	 * 根据区域获取区域统计信息
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/findAreaInfoHzCount")
	@ResponseBody
	public Map<String, Object> findAreaInfoHzCount(HttpServletRequest request, HttpServletResponse response) {
		
		// 构造参数
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("cusNumber", request.getParameter("cusNumber"));				// 监狱id
		paramMap.put("areaId", request.getParameter("areaId"));						// 区域id
		
		return commonService.findAreaInfoHzCount(paramMap);
	}

	/**
	 * 根据监狱ID及时间获取进入人员及车辆数量
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getPeopleAndCarCount")
	@ResponseBody
	public Map<String, Object> getPeopleAndCarCount(HttpServletRequest request, HttpServletResponse response) {
		String cusNumber = request.getParameter("cusNumber");

		Map<String, Object> paramMap = new HashMap<>();
		if(Util.notNull(cusNumber)){
			paramMap.put("cusNumber", cusNumber);
		}
		paramMap.put("frTime", Util.toStr(Util.DF_DATE));

		return commonService.getPeopleAndCarCount(paramMap);
	}
	
	/**
	 * 定时生成监狱统计信息
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping(value="/countPrisonInfoHz")
	@ResponseBody
	public void countPrisonInfoHz(HttpServletRequest request, HttpServletResponse response) throws Exception {

		commonService.countPrisonInfoHz();
	}

	/**
	 * 获取在监民警数量
	 * 
	 * @param deptName
	 * @return
	 */
	@RequestMapping(value="/getPoliceCountInPrison")
	@ResponseBody
	public Map<String, Object> getPoliceCountInPrison(@RequestParam(value="deptCode", defaultValue="", required=false) String deptCode,
			@RequestParam(value="zjmj", defaultValue="", required=false) String zjmj) {
		Map<String, Object> paramMap = new HashMap<>();
		//int level = AuthSystemFacade.whatLevelForLoginUser();
		String level = AuthSystemFacade.getLoginUserInfo().getUserLevel().toString();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		Date mytime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		paramMap.put("mytime", sdf.format(mytime));
		// 监狱局
		if(level.equals("1")) {
			
		}
		// 监狱
		else if(level.equals("2")) {
			
			if(Util.notNull(user)){
				paramMap.put("cusNumber", user.getCusNumber());
			}
			if(Util.notNull(deptCode)) {
				paramMap.put("deptCode", deptCode);
			}
		}
		// 监区
		else {
			if(user != null) {
				// 监狱编号
				paramMap.put("cusNumber", user.getCusNumber());
				
				// 部门编号
				paramMap.put("deptCode", user.getDprtmntCode());
			}
		}
		
		//非空时查询监狱所有否则查询监区
		/*if (zjmj != null && !"".equals(zjmj)) {
			paramMap.put("jyId", user.getOrgId());
		}*/
		return commonService.getPoliceCountInPrison(paramMap);
	}
/**
 * 省局查询在监民警数量
 */
	@RequestMapping(value="/getPoliceCountInPrisonSj")
	@ResponseBody
	public Map<String, Object> getPoliceCountInPrisonSj(@RequestParam(value="deptCode", defaultValue="", required=false) String deptCode){
		Map<String, Object> paramMap = new HashMap<>();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		Date mytime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(Util.notNull(user)){
			paramMap.put("cusNumber",deptCode);
			paramMap.put("mytime",sdf.format(mytime));
		}
		return commonService.getPoliceCountInPrisonsj(paramMap);
	}
	
	/**
	 * 获取在监民警数量
	 * 
	 * @param deptName
	 * @return
	 */
	@RequestMapping(value="/getSecurityCheckCount")
	@ResponseBody
	public Map<String, Object> getSecurityCheckCount() {
		Map<String, Object> paramMap = new HashMap<>();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		if(user != null) {
			// 监狱编号
			paramMap.put("cusNumber", user.getCusNumber());
		}
		paramMap.put("checkTime", Util.toStr(Util.DF_DATE));

		return commonService.getSecurityCheckCount(paramMap);
	}

	@RequestMapping("/jq")
	@ResponseBody
	public List<Map<String, Object>> findjqByjy(){
		Map<String, Object> paramMap = new HashMap<>();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		if(user != null) {
			// 监狱编号
			paramMap.put("jyId", user.getOrgId());
		}
		return commonService.findjqByjy(paramMap);

	}


	@RequestMapping(value="/getPoliceCountZaiCe")
	@ResponseBody
	public Map<String, Object> getPoliceCountZaiCe(@RequestParam(value="zjmj", defaultValue="", required=false) String zjmj) {
		Map<String, Object> paramMap = new HashMap<>();
		//int level = AuthSystemFacade.whatLevelForLoginUser();
		String level = AuthSystemFacade.getLoginUserInfo().getUserLevel().toString();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		// 监狱局
		String sj="1";
		if(level.equals("1")) {
			paramMap.put("sj",sj);
		}
		// 监狱
		else if(level.equals("2")) {
			if(Util.notNull(user)){
				paramMap.put("cusNumber", user.getCusNumber());
			}
		}
		// 监区
		else {
			if(user != null) {
				// 监狱编号
				paramMap.put("cusNumber", user.getCusNumber());
				//监区编号
				paramMap.put("jqId", user.getDprtmntCode());
			}
		}
		return commonService.getPoliceCountZaiCe(paramMap);
	}

    /**
     *
     * @param deptCode
     * @return
     */
    @RequestMapping(value="/getPoliceCountZaiCeByCusNumberOrDeptCode")
    @ResponseBody
    public Map<String, Object> getPoliceCountZaiCeByCusNumberOrDeptCode(@RequestParam(value="deptCode", defaultValue="", required=false) String deptCode,@RequestParam(value="cusNumber", defaultValue="", required=false) String cusNumber) {
        Map<String, Object> paramMap = new HashMap<>();
        if(!"".equals(deptCode)){
            paramMap.put("deptCode", deptCode);
        }
        if(!"".equals(cusNumber)){
            paramMap.put("cusNumber", cusNumber);
        }
        return commonService.getPoliceCountZaiCeByCusNumberOrDeptCode(paramMap);
    }


	/**
	 * 查询备勤警察
	 * @param zjmj
	 * @return
	 */
	@RequestMapping(value="/getPoliceCountBeiQin")
	@ResponseBody
	public Map<String, Object> getPoliceCountBeiQin(@RequestParam(value="zjmj", defaultValue="", required=false) String zjmj) {
		Map<String, Object> paramMap = new HashMap<>();
		//int level = AuthSystemFacade.whatLevelForLoginUser();
		String level = AuthSystemFacade.getLoginUserInfo().getUserLevel().toString();
		UserBean user = AuthSystemFacade.getLoginUserInfo();
		Date mytime = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		paramMap.put("mytime", sdf.format(mytime));
		// 监狱局
		if(level.equals("1")) {
		}
		// 监狱
		else if(level.equals("2")) {
			
			if(Util.notNull(user)){
				paramMap.put("cusNumber", user.getCusNumber());
			}
		}
		// 监区
		else {
			if(user != null) {
				// 监狱编号
				paramMap.put("cusNumber", user.getCusNumber());
				
				// 监区编号
				paramMap.put("JqId", user.getDprtmntCode());
			}
		}
		
		return commonService.getPoliceCountBeiQin(paramMap);
	}
}
