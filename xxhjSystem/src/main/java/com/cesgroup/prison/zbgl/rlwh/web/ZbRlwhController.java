package com.cesgroup.prison.zbgl.rlwh.web;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.zbgl.rlwh.dao.ZbRlwhMapper;
import com.cesgroup.prison.zbgl.rlwh.entity.ZbRlwhEntity;
import com.cesgroup.prison.zbgl.rlwh.service.ZbRlwhService;
import com.cesgroup.prison.zbgl.rygl.entity.RyglEntity;
import com.cesgroup.prison.zbgl.rygl.web.RyglController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 *@ClassName ZbRlwhController
 *@Description TODO
 *@Author lh
 *@Date 2020/8/18 14:52
 *
 **/
@Controller
@RequestMapping(value = "/zbgl/zbrlwh")
public class ZbRlwhController extends BaseEntityDaoServiceCRUDController<ZbRlwhEntity, String, ZbRlwhMapper, ZbRlwhService> {
    private final Logger logger = LoggerFactory.getLogger(ZbRlwhController.class);




    @RequestMapping(value = "/toIndex")
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView("zbgl/zbrlwh/index");
        return mv;
    }

    @RequestMapping(value = "/toEdit")
    public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView("zbgl/zbrlwh/edit");
       // mv.addObject("id", request.getParameter("id"));
        return mv;
    }

    @RequestMapping(value = "/toUpdate")
    public ModelAndView toUpdate(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mv = new ModelAndView("zbgl/zbrlwh/update");
         mv.addObject("id", request.getParameter("id"));
        return mv;
    }

    @RequestMapping(value = "/saveData")
    @ResponseBody
    public AjaxResult saveData(String rlwhDatas,HttpServletRequest request, HttpServletResponse response) {

        try {
            this.service.saveDate(rlwhDatas);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("保存失败");
        }
        return AjaxResult.success("保存成功");
    }

    @RequestMapping(value = "/getById")
    @ResponseBody
    public ZbRlwhEntity getById(String id,HttpServletRequest request, HttpServletResponse response) {
            return  this.service.selectOne(id);
    }

    @RequestMapping(value = "/updateById")
    @ResponseBody
    public AjaxResult updateById(ZbRlwhEntity zbRlwhEntity) {
        try {
            this.service.updateById(zbRlwhEntity);
        }catch (Exception e){
            e.printStackTrace();
           return AjaxResult.error("更新失败");
        }

        return AjaxResult.success("更新成功");
    }

    @RequestMapping(value = "/deleteByIds")
    @ResponseBody
    public AjaxResult deleteByIds(String  ids) {
        try {
            this.service.deleteByIds(ids);
        }catch (Exception e){
            e.printStackTrace();
            return AjaxResult.error("删除失败");
        }
        return AjaxResult.success("删除成功");
    }


    @RequestMapping(value = "/searchData")
    @ResponseBody
    public Map<String, Object> searchData(ZbRlwhEntity zbRlwhEntity, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageRequest pageRequest = buildPageRequest();
        Page<ZbRlwhEntity> pageInfo = (Page<ZbRlwhEntity>)this.service.findList(zbRlwhEntity, pageRequest);
        return DataUtils.pageToMap(pageInfo);
    }

    /**
     * 根据开始和结束日期初始化日历数据
     * @param startDate
     * @param endDate
     * @return
     */
    @RequestMapping(value = "/getInitRlwhData")
    @ResponseBody
    public List<ZbRlwhEntity> getInitRlwhData(String startDate, String endDate)   {
        return this.getService().getInitRlwhData(startDate,endDate);
    }

    /**
     * 检查在日期内是否有数据
     * @return
     */
    @RequestMapping(value = "/checkIsBetweenStartDateAndEndDate")
    @ResponseBody
    public AjaxResult checkIsBetweenStartDateAndEndDate(String startDate, String endDate){
        return this.getService().checkIsBetweenStartDateAndEndDate(startDate, endDate);
    }

}
