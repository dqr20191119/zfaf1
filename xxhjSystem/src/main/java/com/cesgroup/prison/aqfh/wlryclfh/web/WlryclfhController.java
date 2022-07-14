package com.cesgroup.prison.aqfh.wlryclfh.web;

import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.aqfh.wlryclfh.dao.WlryclfhMapper;
import com.cesgroup.prison.aqfh.wlryclfh.entity.WlclEntity;
import com.cesgroup.prison.aqfh.wlryclfh.entity.WlryEntity;
import com.cesgroup.prison.aqfh.wlryclfh.entity.WlryclfhEntity;
import com.cesgroup.prison.aqfh.wlryclfh.service.WlryclfhService;
import com.cesgroup.prison.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @ClassName WlryclfhController
 * @Description TODO
 * @Author lh
 * @Date 2020/6/10 14:03
 **/
@Controller
@RequestMapping(value = "/aqfh/wlryclfh")
public class WlryclfhController extends BaseEntityDaoServiceCRUDController<WlryclfhEntity, String, WlryclfhMapper, WlryclfhService> {

    @RequestMapping(value = "/toIndex")
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("aqfh/wlryclfh/index");
        return mv;
    }

    @RequestMapping(value = "/toView")
    public ModelAndView toIndex(@RequestParam("id")String id,@RequestParam("lx")String lx, HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("aqfh/wlryclfh/view");
        mv.addObject("id",id);
        mv.addObject("lx",lx);
        return mv;
    }


    @RequestMapping(value = "/getById")
    @ResponseBody
    public WlryclfhEntity getById(@RequestParam("id")String id, HttpServletRequest request, HttpServletResponse response) {
      WlryclfhEntity wlryclfhEntity =  this.service.selectOne(id);
        return wlryclfhEntity;
    }

    @RequestMapping(value = "/getWlryOrWlclById")
    public Map<String,Object> getWlryOrWlclById(@RequestParam("id")String id,@RequestParam("lx")String lx, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PageRequest pageRequest = buildPageRequest();
        if("1".equals(lx)){//外来人员
            WlryEntity wlryEntity = new WlryEntity();
            wlryEntity.setYwId(id);
            Page<WlryEntity>  pageInfo = (Page<WlryEntity>) this.service.selectWlryByYwId(wlryEntity,pageRequest);
            return DataUtils.pageToMap(pageInfo);
        }else{//外来车辆
            WlclEntity wlclEntity = new WlclEntity();
            wlclEntity.setYwId(id);
            Page<WlclEntity>  pageInfo = (Page<WlclEntity>) this.service.selectWlclByYwId(wlclEntity,pageRequest);
            return DataUtils.pageToMap(pageInfo);
        }
    }


    @RequestMapping(value = "/searchData")
    @ResponseBody
    public Map<String,Object> seachData(WlryclfhEntity wlryclfhEntity, HttpServletRequest request, HttpServletResponse response)throws Exception {
        PageRequest pageRequest = buildPageRequest();
        Page<WlryclfhEntity> pageInfo = (Page<WlryclfhEntity>) this.service.findList(wlryclfhEntity, pageRequest);
        return DataUtils.pageToMap(pageInfo);
    }


    @RequestMapping(value = "/inItDutyData")
    @ResponseBody
    public AjaxResult inItDutyData(HttpServletRequest request, HttpServletResponse response)throws Exception {
        AjaxResult ajaxResult = null;
        try {
            ajaxResult = this.service.inItDutyData();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("发生异常");
        }
        return ajaxResult;
    }

    @RequestMapping(value = "/updateById")
    @ResponseBody
    public AjaxResult updateById(WlryclfhEntity wlryclfhEntity, HttpServletRequest request, HttpServletResponse response)throws Exception {
        try {
            this.service.updateById(wlryclfhEntity);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("更新发生异常");
        }
        return AjaxResult.success();
    }
}
