package com.cesgroup.prison.common.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.bean.user.utils.EUserLevel;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.dao.AreadeviceMapper;
import com.cesgroup.prison.common.entity.AreadeviceEntity;
import com.cesgroup.prison.common.service.AreadeviceService;

/**
 * 区域设备公共管理
 */
@Controller
@RequestMapping(value = "/common/areadevice")
public class AreadeviceController extends BaseEntityDaoServiceCRUDController<AreadeviceEntity, String, AreadeviceMapper, AreadeviceService> {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AreadeviceController.class);
    @Resource
    private AreadeviceService areadeviceService;

    /**
     * 区域设备下拉树
     * 设备类型deviceType: 1-摄像头，2-对讲分机，3-报警器，4-门禁，5-广播，6-对讲主机，7-高压电网，8-监舍标签，20-地图标签，0-只查询区域
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/findForCombotree")
    @ResponseBody
    public List<Map<String, Object>> findForCombotree(HttpServletRequest request, HttpServletResponse response) {

        // 构造参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cusNumber", request.getParameter("cusNumber"));                // 监狱id
        paramMap.put("id", request.getParameter("id"));                                // 节点id
        paramMap.put("deviceType", request.getParameter("deviceType"));                // 设备类型

        return areadeviceService.findForCombotree(paramMap);
    }

    /**
     * 根据区域和设备类型获取设备列表
     * 设备类型deviceType:	1-摄像头，2-对讲分机，3-报警器，4-门禁，5-广播，6-对讲主机，7-高压电网，8-监舍标签，20-地图标签
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/findDeviceList")
    @ResponseBody
    public List<Map<String, Object>> findDeviceList(HttpServletRequest request, HttpServletResponse response) {

        // 构造参数
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cusNumber", request.getParameter("cusNumber"));                // 监狱id
        paramMap.put("areaId", request.getParameter("areaId"));                        // 区域id
        paramMap.put("deviceType", request.getParameter("deviceType"));                // 设备类型
        paramMap.put("outSide", request.getParameter("outSide"));                    // 是否室外 0-室内，1-室外

        return areadeviceService.findDeviceList(paramMap);
    }

    @RequestMapping(value = "/nullJson")
    @ResponseBody
    public List<Map<String, Object>> nullJson() {
        return new ArrayList<Map<String, Object>>();
    }


    @RequestMapping(value = "/findForDepCombotree")
    @ResponseBody
    public List<Map<String, Object>> findForDepCombotree(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("cusNumber", request.getParameter("cusNumber"));

        return areadeviceService.findForDepCombotree(paramMap);
    }

    /**
     * 查询区域广播设备树
     *
     * @return
     */
    @RequestMapping("/queryAreaBroadcastTree")
    @ResponseBody
    public List<Map<String, Object>> queryAreaBroadcastTree() {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        try {
            UserBean userBean = AuthSystemFacade.getLoginUserInfo();

            Map<String, Object> paramMap = new HashMap<String, Object>();
            if (EUserLevel.PROV.equals(userBean.getUserLevel()) || EUserLevel.PRIS.equals(userBean.getUserLevel())) {//省局、监狱用户
                paramMap.put("cusNumber", userBean.getCusNumber());
            } else if (EUserLevel.AREA.equals(userBean.getUserLevel())) {//监区
                paramMap.put("cusNumber", userBean.getCusNumber());
                paramMap.put("departmentId", userBean.getDprtmntCode());
            }
            resultList = this.getService().queryAreaBoradcastTree(paramMap);
        } catch (BusinessLayerException e) {
            logger.error("查询区域广播设备树发生异常" + e);
        }
        return resultList;
    }
}
