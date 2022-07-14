package com.cesgroup.prison.zfxx.zfdm.web;

import java.util.HashMap;
import java.util.List;
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
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.zfxx.zfdm.dao.ZfZwdmDao;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfZwdm;
import com.cesgroup.prison.zfxx.zfdm.service.ZfZwdmService;

/**
 * Description: 早晚点名记录控制器
 * 
 * @author lincoln.cheng
 * 
 * 2019年03月10日
 */
@Controller
@RequestMapping("/zfxx/zfZwdm")
public class ZfZwdmController extends BaseEntityDaoServiceCRUDController<ZfZwdm, String, ZfZwdmDao, ZfZwdmService> {
	/**
	 * 跳转至罪犯早晚点名记录
	 * @return
	 */
    @RequestMapping(value = "/toIndex")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("/zfxx/zfzwdm/index");
        return mv;
    }

    /**
     * 分页查询罪犯早晚点名记录
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    @Logger(action = "查询", logger = "分页查询罪犯巡视点名记录")
    public Map<String, Object> search(
    		@RequestParam(value="deptname", defaultValue="", required=false) String deptname,
    		@RequestParam(value="dtdmsj", defaultValue="", required=false) String dtdmsj) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("deptname", deptname);
        param.put("dtdmsj", dtdmsj);

        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = this.getService().queryWithPage(param, pageRequest);
        return DataUtils.pageToMap(page);
    }
    
    /**
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/searchZwdm")
	@ResponseBody
	public AjaxResult searchZwdm() {
		try{
			List<ZfZwdm> list = this.getService().queryLatestZwdmRecordOfJianqu();
			return AjaxResult.success(list);
		} catch(BusinessLayerException e) {
			return AjaxResult.error("查询罪犯早晚点名发生异常");
		} catch(Exception e) {
			return AjaxResult.error("查询罪犯早晚点名发生异常");
		}
	}
}
