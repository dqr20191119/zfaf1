package com.cesgroup.prison.roamRecord.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.dto.AjaxResult;
import com.cesgroup.framework.exception.BusinessLayerException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.roamRecord.dao.RoamRecordDao;
import com.cesgroup.prison.roamRecord.entity.RoamRecord;
import com.cesgroup.prison.roamRecord.service.RoamRecordService;
import com.cesgroup.prison.utils.DataUtils;

/**
 * Description: 三维巡视记录控制器
 * 
 * @author lincoln.cheng
 * 
 * 2019年03月10日
 */
@Controller
@RequestMapping("/roamRecord")
public class RoamRecordController extends BaseEntityDaoServiceCRUDController<RoamRecord, String, RoamRecordDao, RoamRecordService> {
	/**
	 * 跳转至三维巡视记录
	 * @return
	 */
    @RequestMapping(value = "/openDialog")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("/roamRecord/index");
        return mv;
    }

    /**
     * 分页查询三维巡视记录
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询三维巡视记录")
    public Map<String, Object> search(
    		@RequestParam(value="roamUserName", defaultValue="", required=false) String roamUserName) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roamUserName", roamUserName);

        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = this.getService().queryWithPage(param, pageRequest);
        return DataUtils.pageToMap(page);
    }

    /**
     * 新增三维巡视记录
     * @param roamRecord
     * @return
     */
    @Logger(action = "新增", logger = "三维巡视记录")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult save(RoamRecord roamRecord) {
    	try {
            this.getService().save(roamRecord);
        } catch (BusinessLayerException e) {
            return AjaxResult.error(e.getMessage());
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success();
    }
}
