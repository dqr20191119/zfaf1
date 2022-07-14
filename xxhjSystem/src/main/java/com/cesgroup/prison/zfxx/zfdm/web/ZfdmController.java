package com.cesgroup.prison.zfxx.zfdm.web;

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
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.zfxx.zfdm.dao.ZfdmMapper;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfdmEntity;
import com.cesgroup.prison.zfxx.zfdm.service.impl.ZfdmServiceImpl;

/**
 * Description: 湖南罪犯点名详情
 * 
 */
@Controller
@RequestMapping("/zfxx/zfdm")
public class ZfdmController extends BaseEntityDaoServiceCRUDController<ZfdmEntity, String, ZfdmMapper, ZfdmServiceImpl> {
	/**
	 * 跳转至罪犯点名详情记录
	 * @return
	 */
    @RequestMapping(value = "/detail")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("/zfxx/zfdm/detail");
        return mv;
    }

    /**
     * 分页查询罪犯巡视点名记录
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询罪犯点名详情记录")
    public Map<String, Object> search(
    		@RequestParam(value="deptId", defaultValue="", required=false) String deptId,
    		@RequestParam(value="cusNumber", defaultValue="", required=false) String cusNumber) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("deptId", deptId);
        param.put("cusNumber", cusNumber);
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = this.getService().searchZfdm(param, pageRequest);
        return DataUtils.pageToMap(page);
    }
    
}
