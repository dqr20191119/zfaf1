package com.cesgroup.prison.doorstate.web;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.doorstate.dao.DoorStateMapper;
import com.cesgroup.prison.doorstate.entity.DoorStateEntity;
import com.cesgroup.prison.doorstate.service.DoorStateService;
import com.cesgroup.prison.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ Author     ：zhouzc.
 * @ Date       ：Created in 18:15 2019/5/23
 * @ Description：门禁状态数量及于详情页面展示
 */

@Controller
@RequestMapping(value = "/doorstate/all")
public class DoorStateController extends BaseEntityDaoServiceCRUDController<DoorStateEntity, String, DoorStateMapper, DoorStateService> {

    @Resource
    private DoorStateService doorStateService;

    /**
     * 通过门禁状态查询门禁的数量
     */
    @RequestMapping(value = "getDoorNumByStatus")
    @ResponseBody
    public Map<String, Object> getDoorNumByStatus(String cusNumber,String status) {
        Map<String, Object> map = new HashMap<>();
        Integer openNum = doorStateService.getDoorNumByStatus(cusNumber,status);
        map.put("openNum", openNum);
        return map;
    }

    /**
     * 打开窗口 跳转到list页面
     * @return
     */
    @RequestMapping(value = "/openDialog")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("doorState/list");
        return mv;
    }

    /**
     * 门禁状态列表页
     * @param dsdDoorName
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/listAll")
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询门禁状态信息")
    public Map<String, Object> listAll(
            @RequestParam(value="dsdDoorName", defaultValue="", required=false) String dsdDoorName
            ,@RequestParam(value="dsdDoorState", required = false) String dsdDoorState
    ) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("dsdDoorName", dsdDoorName);
        paramMap.put("dsdDoorState", dsdDoorState);
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = (Page<Map<String, Object>>) this.getService().listAll(paramMap, pageRequest);

        return DataUtils.pageToMap(page);
    }



    @RequestMapping(value = "/listAllByDoorStateEntity")
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询门禁状态信息")
    public AjaxResult listAllByDoorStateEntity(DoorStateEntity doorStateEntity)  {
        try {
            List<DoorStateEntity> doorStateEntityList = doorStateService.listAllByDoorStateEntity(doorStateEntity);
            return AjaxResult.success(doorStateEntityList);
        }catch (Exception e){
            e.printStackTrace();
           return  AjaxResult.error("查询发生异常");
        }
    }
}
