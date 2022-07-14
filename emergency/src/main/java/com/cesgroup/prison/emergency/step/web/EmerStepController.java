package com.cesgroup.prison.emergency.step.web;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.dto.TreeDto;
import com.cesgroup.framework.exception.ServiceException;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceController;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.emergency.step.dao.EmerStepDao;
import com.cesgroup.prison.emergency.step.dto.EmerStepDto;
import com.cesgroup.prison.emergency.step.entity.EmerStep;
import com.cesgroup.prison.emergency.step.service.EmerStepService;
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
@RequestMapping(value = "/emergency/stepManage")
public class EmerStepController extends BaseEntityDaoServiceController<EmerStep, String, EmerStepDao, EmerStepService> {

    /**
     * 应急预案处置步骤管理窗口
     *
     * @return
     */
    @RequestMapping("/openDialog")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("emergency/step/index");
        return mv;
    }

    /**
     * 应急处置步骤树页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/tree")
    public ModelAndView preplanTreePage(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView("emergency/step/tree");
        return mv;
    }

    /**
     * Description: 初始化风应急处置步骤树数据
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
        ModelAndView mv = new ModelAndView("emergency/step/list");
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
        String stepName = request.getParameter("stepName");//处置步骤名称

        Map<String, Object> paramMap = new LinkedHashMap<String, Object>();
        paramMap.put("cusNumber", user.getCusNumber());
        if (preplanId != null && !"".equals(preplanId) && !"root".equals(preplanId)) {
            paramMap.put("preplanId", preplanId);
        }
        if (stepName != null && !"".equals(stepName)) {
            paramMap.put("stepName", stepName);
        }
        try {
            Page<EmerStepDto> pageInfo = this.getService().queryWithPage(paramMap, buildPageRequest());
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

        ModelAndView mv = new ModelAndView("emergency/step/edit");
        mv.addObject("preplanId", preplanId);
        mv.addObject("id", id);
        return mv;
    }

    /**
     * 根据主键ID，查询应急处置步骤信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryById")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 ID 查询应急处置步骤信息", model = "应急处置步骤管理")
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
     * 根据主键ID，查询应急处置步骤信息
     *
     * @param emerStep
     * @return
     */
    @RequestMapping(value = "/saveOrUpdate")
    @ResponseBody
    @Logger(action = "新增或更新", logger = "新增或更新应急处置步骤", model = "应急处置步骤管理")
    public AjaxMessage saveOrUpdate(EmerStep emerStep) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            this.getService().saveOrUpdate(emerStep);
            flag = true;
        } catch (ServiceException e) {
            flag = false;
            result = "应急处置步骤操作失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "应急处置步骤操作失败：" + e.getMessage();
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
     * 删除应急处置步骤信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/deleteByIds")
    @ResponseBody
    @Logger(action = "删除", logger = "删除应急预案处置步骤", model = "应急预案处置步骤管理")
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
            result = "删除应急预案处置步骤失败：" + e.getMessage();
        } catch (Exception e) {
            flag = false;
            result = "删除应急预案处置步骤失败：" + e.getMessage();
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
     * 根据主键ID，查询应急处置步骤信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryByPreplanId")
    @ResponseBody
    @Logger(action = "查询", logger = "根据 应急预案ID 查询应急处置步骤信息", model = "应急处置步骤管理")
    public AjaxMessage queryByPreplanId(HttpServletRequest request, HttpServletResponse response) {
        boolean flag = false;
        Object result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();

        try {
            String preplanId = request.getParameter("preplanId");
            if (preplanId != null && !"".equals(preplanId)) {
                result = this.getService().queryByPreplanId(preplanId);
                flag = true;
            } else {
                flag = false;
                result = "查询失败：应急预案编号为空，请确认后重试。";
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

}
