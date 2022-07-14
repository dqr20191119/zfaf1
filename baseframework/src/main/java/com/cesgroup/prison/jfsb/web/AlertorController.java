package com.cesgroup.prison.jfsb.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.jfsb.dao.AlertorMapper;
import com.cesgroup.prison.jfsb.entity.AlertorEntity;
import com.cesgroup.prison.jfsb.service.AlertorService;
import com.cesgroup.prison.utils.DataUtils;

/**
 * @projectName：prison
 * @ClassName：AlertorController
 * @Description：
 * @author：Tao.xu
 * @date：2017年12月12日 下午4:05:30
 * @version
 */
@Controller
@RequestMapping(value = "/alertor")
public class AlertorController
        extends BaseEntityDaoServiceCRUDController<AlertorEntity, String, AlertorMapper, AlertorService> {

    @Resource
    private AlertorService service;

    /**
     * @methodName: openDialog
     * @Description: 打开窗口
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/openDialog")
    public ModelAndView openDialog(String areaId) {
        ModelAndView mv = new ModelAndView("jfsb/alertor/list");
        mv.addObject("areaId", areaId);
        return mv;
    }

    /**
     * @methodName: readList
     * @Description: add by WangXin
     * @param areaId
     * @return
     */
    @RequestMapping("/readList")
    public ModelAndView readList(String areaId) {
        ModelAndView mv = new ModelAndView("jfsb/alertor/readList");
        mv.addObject("areaId", areaId);
        return mv;
    }

    /**
     * @methodName: openDialog
     * @Description: 打开增加窗口
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/openDialog/save")
    public ModelAndView openAddDialog() {
        ModelAndView mv = new ModelAndView("jfsb/alertor/save");
        return mv;
    }

    /**
     * @methodName: openDialog
     * @Description: 打开修改窗口
     * @return ModelAndView
     * @throws
     */
    @RequestMapping(value = "/openDialog/update")
    public ModelAndView openUpdateDialog(String id) {
        ModelAndView mv = new ModelAndView("jfsb/alertor/update");
        mv.addObject("ID", id);
        return mv;
    }

    @RequestMapping(value = "/listAll")
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询报警器信息")
    public Map<String, Object> listAll(AlertorEntity entity) throws Exception {
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = (Page<Map<String, Object>>) service.listAll(entity, pageRequest);
        return DataUtils.pageToMap(page);
    }

    @RequestMapping(value = "/findById")
    @ResponseBody
    @Logger(action = "查询", logger = "根据ID查询报警器信息")
    public AjaxMessage findById(String id) {
        boolean flag = false;
        String result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            AlertorEntity entity = service.findById(id);
            flag = true;
            ajaxMsg.setObj(entity);
        } catch (Exception e) {
            flag = false;
            result = "报警器查询失败：" + e.getMessage();
        }
        if (!flag) {
            ajaxMsg.setMsg(result);
        }
        ajaxMsg.setSuccess(flag);
        return ajaxMsg;
    }

    @RequestMapping(value = "/update/info")
    @ResponseBody
    @Logger(action = "修改", logger = "修改报警器信息")
    public AjaxMessage update(AlertorEntity entity) {
        boolean flag = false;
        String result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            service.updateInfo(entity);
            flag = true;
            result = "修改成功";
        } catch (Exception e) {
            flag = false;
            result = "修改失败：" + e.getMessage();
        }
        ajaxMsg.setMsg(result);
        ajaxMsg.setSuccess(flag);
        return ajaxMsg;

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public AjaxMessage delete(@RequestBody List<String> id, HttpServletRequest request) {
        boolean flag = false;
        String result = null;
        AjaxMessage ajaxMsg = new AjaxMessage();
        try {
            service.deleteByIds(id);
            flag = true;
            result = "删除成功";
        } catch (Exception e) {
            flag = false;
            result = "删除失败：" + e.getMessage();
        }
        ajaxMsg.setMsg(result);
        ajaxMsg.setSuccess(flag);
        return ajaxMsg;
    }

    @ResponseBody
    @RequestMapping(value = "/save")
    public AjaxMessage inster(AlertorEntity entity) {
        return service.addInfo(entity);
    }

    @RequestMapping(value = "/seachComboData")
    @ResponseBody
    @Logger(action = "控件下拉查询", logger = "查询报警器信息")
    public List<Map<String, Object>> seachComboData(String cusNumber, String areaId) throws Exception {
        List<Map<String, Object>> maps = new ArrayList<>();
        maps = service.searchCombData(cusNumber, areaId);
        return maps;
    }
}
