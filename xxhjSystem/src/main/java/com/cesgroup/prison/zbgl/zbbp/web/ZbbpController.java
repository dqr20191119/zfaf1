package com.cesgroup.prison.zbgl.zbbp.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.codec.Base62;
import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.zbgl.mbsz.dto.DutyPoliceDto;
import com.cesgroup.prison.zbgl.rygl.util.RyglConstant;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.zbgl.mbbm.dao.MbbmMapper;
import com.cesgroup.prison.zbgl.mbbm.entity.MbbmEntity;
import com.cesgroup.prison.zbgl.mbbm.service.MbbmService;
import com.cesgroup.prison.zbgl.mbsz.service.MbszService;
import com.cesgroup.prison.zbgl.mbsz.web.MbszController;
import com.cesgroup.prison.zbgl.zbbp.dao.ZbbpMapper;
import com.cesgroup.prison.zbgl.zbbp.dto.ZbbpDto;
import com.cesgroup.prison.zbgl.zbbp.entity.ZbbpEntity;
import com.cesgroup.prison.zbgl.zbbp.service.ZbbpService;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;

/**
 * 值班编排
 * 
 */
@Controller
@RequestMapping(value = "/zbgl/zbbp")
public class ZbbpController extends BaseEntityDaoServiceCRUDController<ZbbpEntity, String, ZbbpMapper, ZbbpService> {
	
	private final Logger logger = LoggerFactory.getLogger(MbszController.class);  
	
	@Resource
	private ZbbpService zbbpService;
	@Resource
	private MbszService mbszService;
	@Resource
	private MbbmService mbbmService;
	@Resource
	private MbbmMapper mbbmMapper;
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/zbbp/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/zbbp/edit");
		mv.addObject("id", request.getParameter("id"));                  //模板部门表主键
		mv.addObject("categoryId", request.getParameter("categoryId"));
		mv.addObject("dmdDprtmntId", request.getParameter("dmdDprtmntId"));
		mv.addObject("dmdModeId", request.getParameter("dmdModeId"));
		mv.addObject("dmdStartDate", request.getParameter("dmdStartDate"));
		mv.addObject("dmdEndDate", request.getParameter("dmdEndDate"));
		mv.addObject("dmdName",request.getParameter("dmdName"));
		return mv;
	}
	
	@RequestMapping(value = "/toView")
	public ModelAndView toView(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/zbbp/view");
		mv.addObject("id", request.getParameter("id"));                  //模板部门表主键
		mv.addObject("categoryId", request.getParameter("categoryId"));
		mv.addObject("dmdDprtmntId", request.getParameter("dmdDprtmntId"));
		mv.addObject("dmdModeId", request.getParameter("dmdModeId"));
		mv.addObject("dmdStartDate", request.getParameter("dmdStartDate"));
		mv.addObject("dmdEndDate", request.getParameter("dmdEndDate"));
		return mv;
	}


    @RequestMapping(value = "/toPrint")
    public ModelAndView toPrint(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView("zbgl/zbbp/print");
        mv.addObject("id", request.getParameter("id"));                  //模板部门表主键
        mv.addObject("categoryId", request.getParameter("categoryId"));
        mv.addObject("dmdDprtmntId", request.getParameter("dmdDprtmntId"));
        mv.addObject("dmdModeId", request.getParameter("dmdModeId"));
        mv.addObject("dmdStartDate", request.getParameter("dmdStartDate"));
        mv.addObject("dmdEndDate", request.getParameter("dmdEndDate"));
        mv.addObject("print", request.getParameter("print"));
        return mv;
    }


	@RequestMapping(value = "/saveDutyData")
	@ResponseBody
	public Map<String, Object> saveDutyData(MbbmEntity mbbmEntity, HttpServletRequest request,
			HttpServletResponse response) {
		
		Map<String, Object> resultMap = new HashMap<>();
		ZbbpEntity zbbpEntity = new ZbbpEntity();
		try {	
			Date startDate = mbbmEntity.getDmdStartDate();
			Date endDate = mbbmEntity.getDmdEndDate();
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = mbbmEntity.getId();
			
			if(id != null && !id.equals("")) {
				//修改
				ZbbpEntity zbbp = new ZbbpEntity();
				zbbp.setDbdDutyModeDepartmentId(id);
				zbbpService.updateRyPbCount(id);//回退之前排班的次数
				zbbpService.deleteByConditions(zbbp);//真删 删除过去排班人员
				zbbpEntity.setDbdDutyModeDepartmentId(id);
				
			} else {
			//新增
				mbbmEntity.setDmdCusNumber(user.getOrgCode());
				mbbmEntity.setDmdCategoryId(mbbmEntity.getCdmCategoryId());
				mbbmEntity.setDmdCrteTime(new Date());
				mbbmEntity.setDmdCrteUserId(user.getUserId());
				mbbmEntity.setDmdUpdtTime(new Date());
				mbbmEntity.setDmdUpdtUserId(user.getUserId());
				mbbmEntity.setDmdZt("0");//默认未发布
				mbbmMapper.insert(mbbmEntity);                                                        //新增一条模板部门表记录
				
				String dbdDutyModeDepartmentId = mbbmEntity.getId();                                  //刚生成的部门模板表主键 
				zbbpEntity.setDbdDutyModeDepartmentId(dbdDutyModeDepartmentId);
			}
			
			
			
			
			  String allPoliceList[] = mbbmEntity.getAllPoliceList().trim().split(";");  ///按天数数组
			 for(int i = 0; i < allPoliceList.length; i++) {
			  
			  zbbpEntity.setDutyPoliceData(allPoliceList[i]);
			  zbbpEntity.setDbdCusNumber(user.getOrgCode());
			  zbbpEntity.setDbdCrteTime(new Date());
			  zbbpEntity.setDbdCrteUserId(user.getUserId());
			  zbbpEntity.setDbdUpdtTime(new Date());
			  zbbpEntity.setDbdUpdtUserId(user.getUserId());
			  Date date = startDate;
			  zbbpEntity.setDbdDutyDate(CommonUtil.addDate(date, i));
			  zbbpService.saveDutyData(zbbpEntity); 
			  }
			 
			//String allPoliceList[] = mbbmEntity.getAllPoliceList().trim().split(";");//按天数数组
			//zbbpService.saveDutyData(zbbpEntity,startDate,endDate,allPoliceList);
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
			
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		return resultMap;
	}

    @RequestMapping(value = "/getPrint")
    @ResponseBody
    public List<Map<String, Object>> getPrint(MbbmEntity mbbmEntity, HttpServletRequest request,
                                              HttpServletResponse response) {
            ZbbpEntity zbbpEntity = new ZbbpEntity();
            String allPoliceList[] = mbbmEntity.getAllPoliceList().trim().split(";");  ///按天数数组
            SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
            List<Map<String, Object>> list = new ArrayList<>();
        try {
            for(int i = 0; i < allPoliceList.length; i++) {

                /*zbbpEntity.setDutyPoliceData(allPoliceList[i]);
                zbbpEntity.setDbdCusNumber(user.getOrgCode()); zbbpEntity.setDbdCrteTime(new Date());
                zbbpEntity.setDbdCrteUserId(user.getUserId());
                zbbpEntity.setDbdUpdtTime(new Date());
                zbbpEntity.setDbdUpdtUserId(user.getUserId());
                Date date = startDate;
                zbbpEntity.setDbdDutyDate(CommonUtil.addDate(date, i));
                zbbpService.saveDutyData(zbbpEntity);*/
                zbbpEntity.setDutyPoliceData(allPoliceList[i]);
                String[] allPoliceDataAndJobId = zbbpEntity.getDutyPoliceData().split(",");
                Date startDate = mbbmEntity.getDmdStartDate();
                Date date = startDate;
                String date1 = sdf.format(CommonUtil.addDate(date, i));
                String[]  polices = new String[allPoliceDataAndJobId.length];
                for (int j = 0; j < allPoliceDataAndJobId.length; j++) {
                    String[] allPoliceData = (allPoliceDataAndJobId[j].split("&")[1]).split("%");
                    String[] policeINames = allPoliceData[1].split("@");
                    polices[j] = policeINames[0];
                }
                Map<String, Object> map = ArrayToMap(polices, date1);
                list.add(map);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


            //String allPoliceList[] = mbbmEntity.getAllPoliceList().trim().split(";");//按天数数组
            //zbbpService.saveDutyData(zbbpEntity,startDate,endDate,allPoliceList);
           // resultMap.put("code", CommonConstant.SUCCESS_CODE);
        return list;
    }

    private Map<String,Object> ArrayToMap(String[] policeINames,String date)throws Exception{
        Map<String,Object> map=new HashMap<>();
            map.put("dutyDate",date);
            map.put("week", Util.getWeekDay(date));
            map.put(RyglConstant.ZHZ,formatString(policeINames[0]));
            map.put(RyglConstant.ZZBZ, formatString(policeINames[1]));
            map.put(RyglConstant.ZZBY, formatString(policeINames[2]));
            map.put(RyglConstant.ZHZBZ, formatString(policeINames[3]));
            map.put(RyglConstant.ZHZBY, formatString(policeINames[4]));
            map.put(RyglConstant.WZBZ, formatString(policeINames[5]));
            map.put(RyglConstant.WZBY, formatString(policeINames[6]));
    return map;
    }


    /**
     * 给只有两个汉字的姓名中间加个空格
     * @param str
     * @return
     */
    private String formatString(String str){
        if(str.length()==2){
            String s2 =str.substring(0,str.length()-1)+" "+str.substring(str.length()-1);
            return s2;
        }
        return str;
    }

	
	@RequestMapping(value = "/deleteByConditions")
	@ResponseBody
	public Map<String, Object> deleteByConditions(ZbbpEntity zbbpEntity) {
		
		Map<String, Object> resultMap = new HashMap<>();
		
		MbbmEntity mbbmEntity = new MbbmEntity();
		mbbmEntity.setId(zbbpEntity.getDbdDutyModeDepartmentId());
		try {
			
			mbbmService.deleteByConditions(mbbmEntity);      //真删 删除模板部门表
			zbbpService.deleteByConditions(zbbpEntity);      //真删
			
			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		return resultMap;
	}	
	
	
	
	@RequestMapping("/exportExcel")
    public void exportExcel(ZbbpDto zbbpDto,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	XSSFWorkbook wb=null;
        InputStream inputStream=null;
        AjaxResult ajaxResult =null;
        try {
	        	//查询数据写出
	        	 ajaxResult = this.service.writerZbpById(zbbpDto);
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


    @RequestMapping("/exportZbmbExcel")
    public void exportZbmbExcel(ZbbpDto zbbpDto,HttpServletRequest request, HttpServletResponse response) throws Exception {
        XSSFWorkbook wb=null;
        InputStream inputStream=null;
        AjaxResult ajaxResult =null;
        try {
            //查询数据写出
            ajaxResult = this.service.exportZbmbExcel(zbbpDto);
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

    @RequestMapping(value = "/toImport")
    public ModelAndView toImport(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = new ModelAndView("zbgl/zbbp/import");
       /* mv.addObject("categoryId", request.getParameter("categoryId"));
        mv.addObject("dmdDprtmntId", request.getParameter("dmdDprtmntId"));
        mv.addObject("dmdModeId", request.getParameter("dmdModeId"));
        mv.addObject("dmdStartDate", request.getParameter("dmdStartDate"));
        mv.addObject("dmdEndDate", request.getParameter("dmdEndDate"));
        mv.addObject("dmdName", URLDecoder.decode(request.getParameter("dmdName"), "utf8"));*/
        // mv.addObject("planName", URLDecoder.decode(planName, "utf8"));

        //String url ="dmdName="+URLEncoder.encode(request.getParameter("dmdName"),"utf-8")+"&categoryId="+request.getParameter("categoryId")+"&dmdDprtmntId="+request.getParameter("dmdDprtmntId")+"&dmdModeId="+request.getParameter("dmdModeId")+"&dmdStartDate="+request.getParameter("dmdStartDate")+"&dmdEndDate="+request.getParameter("dmdEndDate");
        String dmdName = request.getParameter("dmdName");
        logger.error("/toImport--------URLDecoder解码前 dmdName="+dmdName);
        dmdName =URLDecoder.decode(request.getParameter("dmdName"), "utf8");
        logger.error("/toImport--------Base62编码前 dmdName="+dmdName);
        String url ="dmdName="+ Base62.encode(dmdName)+"&categoryId="+request.getParameter("categoryId")+"&dmdDprtmntId="+request.getParameter("dmdDprtmntId")+"&dmdModeId="+request.getParameter("dmdModeId")+"&dmdStartDate="+request.getParameter("dmdStartDate")+"&dmdEndDate="+request.getParameter("dmdEndDate");
       // String url ="dmdName="+URLEncoder.encode(request.getParameter("dmdName"),"utf8")+"&categoryId="+request.getParameter("categoryId")+"&dmdDprtmntId="+request.getParameter("dmdDprtmntId")+"&dmdModeId="+request.getParameter("dmdModeId")+"&dmdStartDate="+request.getParameter("dmdStartDate")+"&dmdEndDate="+request.getParameter("dmdEndDate");
        mv.addObject("url",url);
        return mv;
    }

    /**
     * 导入文件
     * @param file
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/upload")
    @ResponseBody
    public String upload(@RequestParam(value = "uploadFile", required = false) MultipartFile file,
                             ZbbpDto zbbpDto, HttpServletResponse response,HttpServletRequest request) throws Exception {

        //	List<AffixEntity> affixList = affixService.upload(file, affixEntity, request);
        logger.error("解码前:dmdName="+request.getParameter("dmdName"));
       // String dmdName = URLDecoder.decode(request.getParameter("dmdName"), "utf8");
        String dmdName = Base62.decodeStr(request.getParameter("dmdName"));
        logger.error("Base62解码后:dmdName="+dmdName);
        AjaxResult ajaxResult = this.service.importZbData(file, zbbpDto,dmdName);
       // return JSON.toJSONString(affixList);
        return JSON.toJSONString(ajaxResult);
    }


}


