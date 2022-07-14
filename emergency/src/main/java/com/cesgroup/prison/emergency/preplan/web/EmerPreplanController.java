package com.cesgroup.prison.emergency.preplan.web;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.constant.CommonConstant;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.preplan.dao.EmerPreplanDao;
import com.cesgroup.prison.emergency.preplan.entity.EmerPreplan;
import com.cesgroup.prison.emergency.preplan.service.EmerPreplanService;
import com.cesgroup.prison.utils.DataUtils;

import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/emergency/preplanManage")
public class EmerPreplanController extends BaseEntityDaoServiceCRUDController<EmerPreplan, String, EmerPreplanDao, EmerPreplanService> {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(EmerPreplanController.class);

    /**
     * 窗口
     *
     * @return
     */
    @RequestMapping("/openDialog")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("emergency/preplan/list");
        return mv;
    }

    @RequestMapping(value = "/queryWithPage")
    @ResponseBody
    public Map<String, Object> queryWithPage(HttpServletRequest request, HttpServletResponse response) throws WebUIException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new WebUIException("用户未登录");
        }
        String preplanSource = request.getParameter("preplanSource");//预案来源
        String preplanName = request.getParameter("preplanName");//预案名称

        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("cusNumber", user.getCusNumber());
        if (preplanSource != null && !"".equals(preplanSource)) {
            paramMap.put("preplanSource", preplanSource);
        }
        if (preplanName != null && !"".equals(preplanName)) {
            paramMap.put("preplanName", preplanName);
        }
        try {
            Page<Map<String, Object>> pageInfo = (Page<Map<String, Object>>) this.getService().queryWithPage(paramMap, buildPageRequest());
            return DataUtils.pageToMap(pageInfo);
        } catch (ServiceException e) {
            throw new WebUIException(e.getMessage());
        } catch (Exception e) {
            throw new WebUIException(e.getMessage());
        }
    }

    /**
     * Description: 打开更新页面
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/toEdit")
    public ModelAndView toEdit(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");

        ModelAndView mv = new ModelAndView("emergency/preplan/edit");
        mv.addObject("id", id);
        return mv;
    }

    /**
     * 根据主键ID，查询应急预案信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryById")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 ID 查询应急预案信息", model = "应急预案")
    public AjaxMessage queryById(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();

        try {
            String id = request.getParameter("id");
            if (id != null && !"".equals(id)) {
                result = this.getService().queryById(id);
                flag = true;
            } else {
                flag = false;
                result = "查询失败 ";
            }
        } catch (ServiceException e) {
            flag = false;
            result = "查询失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "查询失败：" + e.getMessage();
        }
        if (flag) {
            ajaxMsg.setObj(result);
        } else {
            ajaxMsg.setMsg((String) result);
        }
        ajaxMsg.setSuccess(flag);

        return ajaxMsg;
    }

    /**
     * 根据主键ID，查询应急预案信息
     *
     * @param emerPreplan
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    @Logger(action = "新增或更新", logger = "新增或更新应急预案", model = "应急预案管理")
    public AjaxMessage saveOrUpdate(EmerPreplan emerPreplan) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            this.getService().saveOrUpdate(emerPreplan);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "应急预案操作失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "应急预案操作失败：" + e.getMessage();
        }
        if (flag) {
            ajaxMsg.setObj(result);
        } else {
            ajaxMsg.setMsg((String) result);
        }
        ajaxMsg.setSuccess(flag);

        return ajaxMsg;
    }

    /**
     * 删除应急预案信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteByIds")
    @ResponseBody
    @Logger(action = "删除", logger = "删除应急预案", model = "应急预案管理")
    public AjaxMessage deleteByIds(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            String ids = request.getParameter("ids");
            this.getService().deleteByIds(ids);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "删除应急预案失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "删除应急预案失败：" + e.getMessage();
        }
        if (flag) {
            ajaxMsg.setObj(result);
        } else {
            ajaxMsg.setMsg((String) result);
        }
        ajaxMsg.setSuccess(flag);

        return ajaxMsg;
    }

    /**
     * 根据主键ID，查询应急预案信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/judgeById")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 ID 判断应急预案是否存在", model = "应急预案")
    public AjaxMessage judgeById(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();

        try {
            String id = request.getParameter("id");
            if (id != null && !"".equals(id)) {
                EmerPreplan emerPreplan = this.getService().queryById(id);
                if(emerPreplan == null || emerPreplan.getStatus() == null || !"0".equals(emerPreplan.getStatus())) {
                    flag = false;
                    result = "应急预案不存在，请刷新后重试";
                } else {
                    flag = true;
                    result = emerPreplan;
                }
            } else {
                flag = false;
                result = "查询失败：应急预案ID为空";
            }
        } catch (ServiceException e) {
            flag = false;
            result = "查询失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "查询失败：" + e.getMessage();
        }
        if (flag) {
            ajaxMsg.setObj(result);
        } else {
            ajaxMsg.setMsg((String) result);
        }
        ajaxMsg.setSuccess(flag);

        return ajaxMsg;
    }

    /**
     * 查询本单位全部的应急预案
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryAllForCombobox")
    @ResponseBody
    @Logger(action = "查询", logger = "查询本单位全部的应急预案", model = "应急预案")
    public List<Map<String, Object>> queryAllForCombobox(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            logger.error("查询失败，用户未登录");
            return resultList;
        }
        try {
            List<EmerPreplan> emerPreplanList = this.getService().queryByCusNumberAndStatus(user.getCusNumber(), "0");
            if(emerPreplanList != null && emerPreplanList.size() > 0) {
                for(EmerPreplan emerPreplan : emerPreplanList) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("value", emerPreplan.getId());
                    map.put("text", emerPreplan.getName());
                    resultList.add(map);
                }
            }
        } catch (ServiceException e) {
            logger.error("查询失败：" + e.getMessage());
        } catch (Exception e) {
            logger.error("查询失败：" + e.getMessage());
        }

        return resultList;
    }

}
