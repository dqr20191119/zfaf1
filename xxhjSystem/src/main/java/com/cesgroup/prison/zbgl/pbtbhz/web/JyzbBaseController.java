package com.cesgroup.prison.zbgl.pbtbhz.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.zbgl.rygl.util.RyglConstant;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.zbgl.pbtbhz.dao.JyzbBaseMapper;
import com.cesgroup.prison.zbgl.pbtbhz.entity.JyzbBaseEntity;
import com.cesgroup.prison.zbgl.pbtbhz.entity.JyzbDetailEntity;
import com.cesgroup.prison.zbgl.pbtbhz.service.JyzbBaseService;

import ces.sdk.system.facade.SystemFacadeException;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;

/**
* @author lihong
* @date 创建时间：2020年5月25日 下午5:32:17
* 类说明:
*/
@Controller
@RequestMapping("/zbgl/jyzbhz")
public class JyzbBaseController extends BaseEntityDaoServiceCRUDController<JyzbBaseEntity, String, JyzbBaseMapper, JyzbBaseService> {
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/jyzbhz/index");
		return mv;
	}
	
	@RequestMapping(value = "/toHzView")
	public ModelAndView toHzView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/jyzbhz/hz_view");
		return mv;
	}

    @RequestMapping(value = "/toPrint")
    public ModelAndView toPrint(HttpServletRequest request, HttpServletResponse response) {
        String zbyf = request.getParameter("zbyf");
        ModelAndView mv = new ModelAndView("zbgl/jyzbhz/print");
        mv.addObject("zbyf",zbyf);
        return mv;
    }

	
	@RequestMapping(value = "/view")
	public ModelAndView toview(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("zbgl/jyzbhz/view");
		mv.addObject("id", request.getParameter("id"));
		mv.addObject("cusNumber", request.getParameter("cusNumber"));
		mv.addObject("zbYf", request.getParameter("zbYf"));
		mv.addObject("zbDh", request.getParameter("zbDh"));
		String cjrName = "";
		if(request.getParameter("cjrId")!=null && !"".equals(request.getParameter("cjrId"))) {
			try {
				cjrName = AuthSystemFacade.getUserInfoByUserId(request.getParameter("cjrId")).getUserName();
				mv.addObject("cjrName", cjrName);
			} catch (SystemFacadeException e) {
				e.printStackTrace();
			}
		}
		mv.addObject("cjrq", request.getParameter("cjrq"));
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("zbgl/jyzbhz/edit");
		mv.addObject("id", request.getParameter("id"));
		if(request.getParameter("id")!=null && !"".equals(request.getParameter("id"))) {
			//修改
			mv.addObject("cusNumber", request.getParameter("cusNumber"));
			mv.addObject("zbYf", request.getParameter("zbYf"));
			mv.addObject("zbDh", request.getParameter("zbDh"));
			//mv.addObject("cjrName", request.getParameter("cjrId"));
			String cjrName = "";
			if(request.getParameter("cjrId")!=null && !"".equals(request.getParameter("cjrId"))) {
				try {
					cjrName = AuthSystemFacade.getUserInfoByUserId(request.getParameter("cjrId")).getUserName();
					mv.addObject("cjrName", cjrName);
				} catch (SystemFacadeException e) {
					e.printStackTrace();
				}
			}
			mv.addObject("cjrq", request.getParameter("cjrq"));
		}else {
			//新增
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			mv.addObject("cjrId", user.getUserId());
			mv.addObject("cjrName", user.getUserName());
			mv.addObject("zbYf", DateUtils.getNyByMonth(new Date(), 0));
			mv.addObject("cjrq", DateUtils.getCurrentDate(false));
		}
		
		return mv;
	}
	
	@RequestMapping(value = "/getJyzbBaseById")
	@ResponseBody
	public JyzbBaseEntity getJyzbBaseById(JyzbBaseEntity jyzbBaseEntity, HttpServletRequest request, HttpServletResponse response)  {
			JyzbBaseEntity selectOne = this.service.selectOne(jyzbBaseEntity.getId());
		return selectOne;				
	}
	
	/**
	 * @param jyzbBaseEntity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getJyzbDetailByYwID")
	@ResponseBody
	public List<JyzbDetailEntity> getJyzbDetailByYwID(JyzbBaseEntity jyzbBaseEntity, HttpServletRequest request, HttpServletResponse response)  {
		List<JyzbDetailEntity> 	 jyzbDetailByYwID = this.service.getJyzbDetailByYwID(jyzbBaseEntity);
		return jyzbDetailByYwID;				
	}
	
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(JyzbBaseEntity jyzbBaseEntity, HttpServletRequest request, HttpServletResponse response) throws Exception  {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			PageRequest pageRequest = buildPageRequest();
			Page<JyzbBaseEntity> pageInfo = (Page<JyzbBaseEntity>) this.service.findList(jyzbBaseEntity, pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	@RequestMapping(value = "/deleteById")
	@ResponseBody
	public AjaxResult deleteById(JyzbBaseEntity jyzbBaseEntity, HttpServletRequest request, HttpServletResponse response)  {
		try {
			this.service.deleteById(jyzbBaseEntity.getId());
		} catch (Exception e) {
			e.printStackTrace();
			return	AjaxResult.error("发生异常");
		}
		return AjaxResult.success("删除成功");				
	}
	
	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public AjaxResult saveOrUpdate(JyzbBaseEntity jyzbBaseEntity, HttpServletRequest request, HttpServletResponse response)  {
		try {
			this.service.saveOrUpdate(jyzbBaseEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return	AjaxResult.error("发生异常");
		}
		return AjaxResult.success("保存成功");				
	}
	
	@RequestMapping(value = "/checkJyzbByzbYf")
	@ResponseBody
	public AjaxResult checkJyzbByzbYf(JyzbBaseEntity jyzbBaseEntity, HttpServletRequest request, HttpServletResponse response)  {
		try {
			AjaxResult checkJyzbByzbYf = this.service.checkJyzbByzbYf(jyzbBaseEntity);
			return checkJyzbByzbYf;	
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("发生异常");
		}
					
	}
	
	
	/**
	 * 上报或撤回
	 * @param jyzbBaseEntity
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateById")
	@ResponseBody
	public AjaxResult updateById(JyzbBaseEntity jyzbBaseEntity, HttpServletRequest request, HttpServletResponse response)  {
		try {
			this.service.updateById(jyzbBaseEntity);
		} catch (Exception e) {
			e.printStackTrace();
			return AjaxResult.error("更新发生异常");
		}
		return AjaxResult.success("更新成功");				
	}
	
	
	/**
	 * 按天汇总或按月各监狱值班明细
	 * @param
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/getZbDetailByDay")
	@ResponseBody
	public Map<String,Object> getZbDetailByDay(@RequestParam(value = "dutyDate",defaultValue = "",required = false) String dutyDate,@RequestParam(value = "zbYf",defaultValue = "",required = false) String zbYf, HttpServletRequest request, HttpServletResponse response)  {
			PageRequest pageRequest = buildPageRequest();
			Page<JyzbDetailEntity> 	pageInfo =(Page<JyzbDetailEntity>)this.service.getZbDetailByDay(dutyDate,zbYf,pageRequest);
		return DataUtils.pageToMap(pageInfo);				
	}
	
	/**
	 * 按天导出或者按月导出
	 * @param dutyDate
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/export")
    public void exportExcel(@RequestParam(value = "dutyDate",defaultValue = "",required = false) String dutyDate,@RequestParam(value = "zbYf",defaultValue = "",required = false) String zbYf,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	XSSFWorkbook wb=null;
        InputStream inputStream=null;
        AjaxResult ajaxResult =null;
        try {
	        	//查询数据写出
	        	 ajaxResult = this.service.writerByDutyDate(dutyDate,zbYf);
	        	if(ajaxResult.getCode()==200) {
	        		inputStream = new FileInputStream(new File(ajaxResult.getMessage()));
	                wb = new XSSFWorkbook(inputStream);
	                response.reset();
	                response.setContentType("ultipart/form-data");
	                response.setHeader("Content-Disposition",
	                        "attachment; filename=" + IdUtil.simpleUUID() + ".xlsx");
	                response.flushBuffer();
	                wb.write(response.getOutputStream());
	        	}
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
            	wb.close();
            	inputStream.close();
            	FileUtil.del(ajaxResult.getMessage());
            }
	}
	
	
	//toImport
    /**
     * 跳转导入页面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/toImport")
    public ModelAndView toImport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("zbgl/jyzbhz/import");
        mv.addObject("cusNumber", request.getParameter("cusNumber"));
        mv.addObject("zbyf", request.getParameter("zbyf"));
        return mv;
    }



    /**
     * 导入文件
     * @param file
     * @param request
     * @throws Exception
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult upload(@RequestParam(value = "uploadFile", required = false) MultipartFile file,
                         HttpServletRequest request, HttpServletResponse response) throws Exception {

        //	List<AffixEntity> affixList = affixService.upload(file, affixEntity, request);
        AjaxResult ajaxResult = this.service.importFile(file, request);
        //return JSON.toJSONString(affixList);
        return ajaxResult;
    }



    /**
     * 下载excel模板
     */
    @RequestMapping("/downloadexcel")
    public void downloadPermMatrix(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置响应和请求编码utf-8
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        XSSFWorkbook wb=null;
        InputStream inputStream=null;
        try {
            //String path = "D:\\jggz\\zhddUploads\\zbrymb\\zbrymb.xlsx";
            String path = CommonConstant.jggzUploadsRootPath+File.separator+CommonConstant.uploadGlobalPath+File.separator+ RyglConstant.zbrymb+File.separator+"zbhzmb.xlsx";
            inputStream = new FileInputStream(new File(path));
            wb = new XSSFWorkbook(inputStream);
            response.reset();
            response.setContentType("ultipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode("监狱值班人员模板", "UTF-8") + ".xlsx");
            response.flushBuffer();
            wb.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            wb.close();
            inputStream.close();
        }
    }
	
}
