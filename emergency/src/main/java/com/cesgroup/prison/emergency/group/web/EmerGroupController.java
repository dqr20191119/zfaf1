package com.cesgroup.prison.emergency.group.web;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.dto.TreeDto;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.group.dao.EmerGroupDao;
import com.cesgroup.prison.emergency.group.entity.EmerGroup;
import com.cesgroup.prison.emergency.group.service.EmerGroupService;
import com.cesgroup.prison.utils.DataUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/emergency/groupManage")
public class EmerGroupController extends BaseEntityDaoServiceCRUDController<EmerGroup, String, EmerGroupDao, EmerGroupService> {

    /**
     * 应急预案工作组管理窗口
     *
     * @return
     */
    @RequestMapping("/openDialog")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("emergency/group/index");
        return mv;
    }

    /**
     * 指令树页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/tree")
    public ModelAndView preplanTreePage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("emergency/group/tree");
        return mv;
    }

    /**
     * Description: 初始化风指令树数据
     *
     * @return
     */
    @RequestMapping("/initTree")
    @ResponseBody
    public List<TreeDto> initTree() throws WebUIException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new WebUIException("用户未登录");
        }
        try {
            return this.getService().initTree(user.getCusNumber());
        } catch (ServiceException e) {
            throw new WebUIException(e.getMessage());
        } catch (Exception e) {
            throw new WebUIException(e.getMessage());
        }
    }

    /**
     * Description: 右侧详细列表页面
     *
     * @param preplanId
     * @return
     */
    @RequestMapping(value = "/toList")
    public ModelAndView toList(@RequestParam(value = "preplanId", defaultValue = "", required = false) String preplanId) throws WebUIException {
        ModelAndView mv = new ModelAndView("emergency/group/list");
        mv.addObject("preplanId", preplanId);
        return mv;
    }

    @RequestMapping(value = "/queryWithPage")
    @ResponseBody
    public Map<String, Object> queryWithPage(HttpServletRequest request, HttpServletResponse response) throws WebUIException {
        UserBean user = AuthSystemFacade.getLoginUserInfo();
        if (user == null) {
            throw new WebUIException("用户未登录");
        }
        String preplanId = request.getParameter("preplanId");//应急预案编号
        String groupName = request.getParameter("groupName");//工作组名称

        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("cusNumber", user.getCusNumber());
        if (preplanId != null && !"".equals(preplanId) && !"root".equals(preplanId)) {
            paramMap.put("preplanId", preplanId);
        }
        if (groupName != null && !"".equals(groupName)) {
            paramMap.put("groupName", groupName);
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
        String preplanId = request.getParameter("preplanId");
        String id = request.getParameter("id");

        ModelAndView mv = new ModelAndView("emergency/group/edit");
        mv.addObject("preplanId", preplanId);
        mv.addObject("id", id);
        return mv;
    }

    /**
     * 根据主键ID，查询指令信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryById")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 ID 查询指令信息", model = "指令管理")
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
     * 根据主键ID，查询指令信息
     *
     * @param emerGroup
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    @Logger(action = "新增或更新", logger = "新增或更新应急梯队", model = "应急梯队管理")
    public AjaxMessage saveOrUpdate(EmerGroup emerGroup) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            this.getService().saveOrUpdate(emerGroup);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "应急梯队操作失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "应急梯队操作失败：" + e.getMessage();
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
     * 删除指令信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteByIds")
    @ResponseBody
    @Logger(action = "删除", logger = "删除应急预案工作组", model = "应急预案工作组管理")
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
            result = "删除应急预案工作组失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "删除应急预案工作组失败：" + e.getMessage();
        }
        if (flag) {
            ajaxMsg.setObj(result);
        } else {
            ajaxMsg.setMsg((String) result);
        }
        ajaxMsg.setSuccess(flag);

        return ajaxMsg;
    }

}
