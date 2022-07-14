package com.cesgroup.prison.zbgl.rygl.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.framework.util.StringUtil;
import com.cesgroup.framework.utils.Util;
import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.zbgl.rygl.dto.DutyCountDto;
import com.cesgroup.prison.zbgl.rygl.entity.DutyFlagEntity;
import com.cesgroup.prison.zbgl.rygl.entity.RyztHistoryEntity;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.code.tool.DateUtils;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.zbgl.rygl.dao.RyglMapper;
import com.cesgroup.prison.zbgl.rygl.dto.JyzzxxDto;
import com.cesgroup.prison.zbgl.rygl.entity.RyglEntity;
import com.cesgroup.prison.zbgl.rygl.service.RyglService;
import com.cesgroup.prison.zbgl.rygl.util.RyglConstant;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;


/**
 * 值班人员管理
 * 
 */
@Controller
@RequestMapping(value = "/zbgl/rygl")
public class RyglController extends BaseEntityDaoServiceCRUDController<RyglEntity, String, RyglMapper, RyglService> {
	
	private final Logger logger = LoggerFactory.getLogger(RyglController.class);
	
	
	
	
	@RequestMapping(value = "/toIndex")
	public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/rygl/index");
		return mv;
	}
	
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
		
		ModelAndView mv = new ModelAndView("zbgl/rygl/edit");
		mv.addObject("id", request.getParameter("id"));
		return mv;
	}

    @RequestMapping(value = "/todutyFlagIndex")
    public ModelAndView toDutyFlag(HttpServletRequest request, HttpServletResponse response) {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        ModelAndView mv = new ModelAndView("zbgl/rygl/dutyFlagIndex");
        mv.addObject("cusNumber", user.getCusNumber());
        return mv;
    }

    @RequestMapping(value = "/seachDutyFlagData")
    @ResponseBody
    public AjaxResult seachDutyFlagData(String cusNumber) throws Exception {
        return AjaxResult.success(this.service.seachDutyFlagData(cusNumber));
    }


    @RequestMapping(value = "/todutyFlagEdit")
    public ModelAndView todutyFlagEdit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("zbgl/rygl/dutyFlagEdit");
        mv.addObject("id", request.getParameter("id"));
        return mv;
    }


    @RequestMapping(value = "/getdutyFlagByCusNumber")
    @ResponseBody
    public DutyFlagEntity getdutyFlagByCusNumber(String cusNumber) {
        return this.service.seachDutyFlagData(cusNumber).get(0);
    }


    @RequestMapping(value = "/updateDutyFlagById")
    @ResponseBody
    public AjaxResult updateDutyFlagById(DutyFlagEntity dutyFlagEntity)  {
        try {
            this.service.updateDutyFlagById(dutyFlagEntity);
        }catch (Exception e){
            AjaxResult.error();
            e.printStackTrace();
        }
        return AjaxResult.success();
    }



    @RequestMapping(value = "/view")
    public ModelAndView toview(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView("zbgl/rygl/view");
        mv.addObject("id", request.getParameter("id"));
        return mv;
    }

    @RequestMapping(value = "/toCount")
    public ModelAndView toCount(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView("zbgl/rygl/count");
        return mv;
    }

	
	@RequestMapping(value = "/getById")
	@ResponseBody
	public RyglEntity getById(String id, HttpServletRequest request, HttpServletResponse response) {
	 		
		return this.service.getById(id); 
	}
	
	@RequestMapping(value = "/searchData")
	@ResponseBody
	public Map<String, Object> searchData(RyglEntity ryglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
              PageRequest pageRequest = buildPageRequest();
              Page<RyglEntity> pageInfo = (Page<RyglEntity>)this.service.findList(ryglEntity, pageRequest);
              return DataUtils.pageToMap(pageInfo);
	}
	
	
	@RequestMapping(value = "/selectList")
	@ResponseBody
	public List<RyglEntity> selectList(RyglEntity ryglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
              return this.service.selectList(ryglEntity);
	}
	/**
	 * 
	 * 检查当前用户值班岗位是否为指挥长
	 * @param request
	 * @param response
	 * @return
	 * 
	 */
	@RequestMapping(value = "/checkIsZhz")
	@ResponseBody
	public AjaxResult checkIsZhz(HttpServletRequest request, HttpServletResponse response)  {
		//List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();	
		UserBean user = AuthSystemFacade.getLoginUserInfo();
          String msg = "";
         if(user.getUserLevel().equals(EUserLevel.PROV)){//省局用户
             RyglEntity ry = new RyglEntity();
             ry.setCusNumber(user.getCusNumber());
             ry.setName(user.getRealName());
             ry.setJob("指挥长");
             List<RyglEntity> checkIsZhz = this.service.checkIsZhz(ry);
              msg = "false";//不是指挥长
             if(checkIsZhz!=null && checkIsZhz.size()>0) {
                 msg = "true";//是指挥长
             }
         }else{

         }

              return AjaxResult.success(msg);
	}




    /**
     *
     * 对培训,病假,其他提前一月进行消息提醒
     * @param request
     * @param response
     * @return
     *
     */
    @RequestMapping(value = "/remindMessageByRyzy")
    @ResponseBody
    public AjaxResult remindMessageByRyzy(HttpServletRequest request, HttpServletResponse response)  {
        try {
            return AjaxResult.success(this.service.remindMessageByRyzy());
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error();
        }
    }



	@RequestMapping(value = "/getCommonbox")
	@ResponseBody
	public List<Map<String,Object>> getCommonbox(RyglEntity ryglEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();	
		List<RyglEntity> selectList = this.service.selectList(ryglEntity);
		for (int i = 0; i < selectList.size(); i++) {
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("value", selectList.get(i).getId());
			map.put("label", selectList.get(i).getName());
			list.add(map);
		}
              return list;
	}

    @RequestMapping(value = "/dutyCount")
    @ResponseBody
    public List<DutyCountDto> dutyCount(@RequestParam(value = "year",defaultValue = "") String year,@RequestParam(value = "cusNumber",defaultValue = "")String cusNumber) {
	    try {
              UserBean user = AuthSystemFacade.getLoginUserInfo();
              Map<String, Object> map = new HashMap<>();
              if(StringUtil.isNull(year)){
                  year = Util.getCurrentYear();
              }
              map.put("year",year);
              if(StringUtil.isNull(cusNumber)){
                  cusNumber = user.getCusNumber();
              }
              map.put("cusNumber",cusNumber);
              List<DutyCountDto> dutyCountDtos = this.service.dutyCount(map);
              return dutyCountDtos;
          }catch (Exception e){
	        e.printStackTrace();
              return null;
          }
    }

	
	@RequestMapping(value = "/saveOrUpdate")
	@ResponseBody
	public Map<String, Object> saveOrUpdate(RyglEntity ryglEntity, HttpServletRequest request, HttpServletResponse response) {

		Map<String, Object> resultMap = new HashMap<>();
		
		try {
			UserBean user = AuthSystemFacade.getLoginUserInfo();
			String id = ryglEntity.getId();
			if(id != null && !"".equals(id)) {
                      ryglEntity.setUpdateName(user.getUserId());
                      ryglEntity.setUpdateTime(DateUtils.getCurrentDate());
                      //若值班岗位job 进行了修改 这里需要改变值班顺
                      RyglEntity byId = this.service.getById(id);
                      if(!byId.getJob().equals(ryglEntity.getJob())){//进行了岗位调整
                          insetPbOrder(ryglEntity);
                      }else{
                          //不调整岗位 是否调整本值班岗位的排班顺序
                          if(ryglEntity.getInsertType()!=null && "1".equals(ryglEntity.getInsertType())){
                                  //值班顺序插入到这个人之后
                               if(ryglEntity.getAfterInsertId()!=null){
                                   RyglEntity ryglEntity1 = this.service.getById(ryglEntity.getAfterInsertId());
                                   //后移
                                    this.service.afterMove(ryglEntity1);
                                   ryglEntity.setPbOrder(ryglEntity1.getPbOrder()+1);
                               }
                          }
                      }

                      this.service.updateById(ryglEntity);
			} else {
                      ryglEntity.setCreateTime(DateUtils.getCurrentDate());
                      ryglEntity.setCreateName(user.getUserId());
                      ryglEntity.setPbCount(0);
                     // RyglEntity maxRygl  = this.service.getMaxPbOrder();
                      insetPbOrder(ryglEntity);
                      this.service.insert(ryglEntity);
			}
                    //这里只保存4 5 6 状态的历史记录
			if("4".equals(ryglEntity.getRyzt()) ||"5".equals(ryglEntity.getRyzt()) ||"6".equals(ryglEntity.getRyzt())){
                      RyztHistoryEntity ryztHistoryEntity = new RyztHistoryEntity();
                      ryztHistoryEntity.setZbryId(ryglEntity.getId());
                      if(!StringUtil.isNull(ryglEntity.getRemark())){
                          ryztHistoryEntity.setRemark(ryglEntity.getRemark());
                      }
                      if(!StringUtil.isNull(ryglEntity.getRyzt())){
                          ryztHistoryEntity.setRyzt(ryglEntity.getRyzt());
                      }
                      if(!StringUtil.isNull(ryglEntity.getStartDate())){
                          ryztHistoryEntity.setStartDate(ryglEntity.getStartDate());
                      }
                      if(!StringUtil.isNull(ryglEntity.getEndDate())){
                          ryztHistoryEntity.setEndDate(ryglEntity.getEndDate());
                      }
                      ryztHistoryEntity.setUpdateId(user.getUserId());
                      ryztHistoryEntity.setUpdateName(user.getUserName());
                      ryztHistoryEntity.setUpdateTime(Util.getCurrentDateTime());
                      this.service.insertRyztHistory(ryztHistoryEntity);
                  }


			resultMap.put("code", CommonConstant.SUCCESS_CODE);
		} catch(Exception e) {
			
			logger.error(e.toString(), e.fillInStackTrace());
			resultMap.put("code", CommonConstant.FAILURE_CODE);
		}
		
		return resultMap;
	}


	private void  insetPbOrder(RyglEntity ryglEntity){
          if(ryglEntity.getInsertType()!=null && "1".equals(ryglEntity.getInsertType())){
              //值班顺序插入到这个人之后
              if(ryglEntity.getAfterInsertId()!=null){
                  RyglEntity ryglEntity1 = this.service.getById(ryglEntity.getAfterInsertId());
                  //后移
                  this.service.afterMove(ryglEntity1);
                  ryglEntity.setPbOrder(ryglEntity1.getPbOrder()+1);
              }
          }else{
              //返回调整岗位的最大排序号 插入到最后
              Integer maxPbOrderByjob = this.service.tZJob(ryglEntity);
              ryglEntity.setPbOrder(maxPbOrderByjob+1);
          }
      }


	@RequestMapping(value = "/deleteByIds")
	@ResponseBody
	public Map<String, Object> deleteByIds(@RequestParam("ids") String ids, HttpServletRequest request, HttpServletResponse response) {
          Map<String, Object> resultMap = new HashMap<>();
		try {

                this.service.deleteByIds(ids);
                resultMap.put("code",CommonConstant.SUCCESS_CODE);
            }catch (Exception e){
                logger.error("值班人员管理删除异常", e.fillInStackTrace());
                resultMap.put("code", CommonConstant.FAILURE_CODE);
            }


		return resultMap;
	}
	
	@RequestMapping("/exportExcel")
    public void exportExcel(@RequestParam("ids") String ids,HttpServletRequest request, HttpServletResponse response) throws Exception {
    	XSSFWorkbook wb=null;
        InputStream inputStream=null;
        AjaxResult ajaxResult =null;
        try {
	        	//查询数据写出
	        	 ajaxResult = this.service.writerByIds(ids);
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
	

    /**
     * 获取省局的组织代码和名称
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/listSjzzxx")
    @ResponseBody
    public List<Map<String, Object>> listSjzzxx() throws Exception {

        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        List<JyzzxxDto> jyzzxxDtos = this.service.listSjzzxx();
        for (JyzzxxDto jyzzxxDto : jyzzxxDtos) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("value", jyzzxxDto.getCusNumber());
            map.put("text", jyzzxxDto.getCusName());
            resultList.add(map);
        }

        return resultList;
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
        	String path = CommonConstant.jggzUploadsRootPath+File.separator+CommonConstant.uploadGlobalPath+File.separator+RyglConstant.zbrymb+File.separator+"zbrymb.xlsx";
        	 inputStream = new FileInputStream(new File(path));
            wb = new XSSFWorkbook(inputStream);
            response.reset();
            response.setContentType("ultipart/form-data");
            response.setHeader("Content-Disposition",
                    "attachment; filename=" + URLEncoder.encode("值班人员模板", "UTF-8") + ".xlsx");
            response.flushBuffer();
            wb.write(response.getOutputStream());
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
            	wb.close();
            	inputStream.close();
            }
	}
    /**
     * 跳转导入页面
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/toImport")
    public ModelAndView toImport(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	ModelAndView mv = new ModelAndView("zbgl/rygl/import");
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
	public String upload(@RequestParam(value = "uploadFile", required = false) MultipartFile file, 
			HttpServletRequest request, HttpServletResponse response) throws Exception {
				 							
	//	List<AffixEntity> affixList = affixService.upload(file, affixEntity, request); 	
		AjaxResult ajaxResult = this.service.importFile(file, request);
		//return JSON.toJSONString(affixList);
		return JSON.toJSONString(ajaxResult);
	}
	
	
    
    
}
