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
import com.cesgroup.prison.zfxx.zfdm.dao.ZfXsdmDao;
import com.cesgroup.prison.zfxx.zfdm.entity.ZfXsdm;
import com.cesgroup.prison.zfxx.zfdm.service.ZfXsdmService;

/**
 * Description: 巡视点名记录控制器
 * 
 * @author lincoln.cheng
 * 
 * 2019年03月10日
 */
@Controller
@RequestMapping("/zfxx/zfXsdm")
public class ZfXsdmController extends BaseEntityDaoServiceCRUDController<ZfXsdm, String, ZfXsdmDao, ZfXsdmService> {
	/**
	 * 跳转至罪犯巡视点名记录
	 * @return
	 */
    @RequestMapping(value = "/toIndex")
    public ModelAndView openDialog() {
        ModelAndView mv = new ModelAndView("/zfxx/zfxsdm/index");
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
    @Logger(action = "查询", logger = "分页查询罪犯巡视点名记录")
    public Map<String, Object> search(
    		@RequestParam(value="deptname", defaultValue="", required=false) String deptname,
    		@RequestParam(value="dmsj", defaultValue="", required=false) String dmsj) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("deptname", deptname);
        param.put("dmsj", dmsj);
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page   = this.getService().queryWithPage(param, pageRequest);
        return DataUtils.pageToMap(page);
    }
    
    /**
     * 
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
	@RequestMapping(value = "/searchXsdm")
	@ResponseBody
	public AjaxResult searchXsdm() {
		try{
			List<ZfXsdm> list = this.getService().queryLatestXsdmRecordOfJianqu();
			return AjaxResult.success(list);
		} catch(BusinessLayerException e) {
			return AjaxResult.error("查询罪犯巡视点名发生异常");
		} catch(Exception e) {
			return AjaxResult.error("查询罪犯巡视点名发生异常");
		}
	}
}
