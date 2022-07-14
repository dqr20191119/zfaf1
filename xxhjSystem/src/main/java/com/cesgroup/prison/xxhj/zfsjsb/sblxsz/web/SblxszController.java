package com.cesgroup.prison.xxhj.zfsjsb.sblxsz.web;

import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.dao.SblxszMapper;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity.SblxszEntity;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.service.SblxszService;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.service.SblxszServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping(value = "/xxhj/zfsjsb/sblxsz")
public class SblxszController  extends BaseEntityDaoServiceCRUDController<SblxszEntity, String, SblxszMapper, SblxszServiceImpl> {

    @Resource
    private SblxszService sblxszService;

    @RequestMapping(value = "/toIndex")
    public ModelAndView toIndex(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView("/xxhj/zfsjsb/sblxsz/index");
        return mv;
    }


    /**
     * 根据prtdTypeCode查询
     * @param request
     * @return
     */
    @RequestMapping(value = "/listByTypeId")
    @ResponseBody
    public Map<String, Object> listByTypeId(HttpServletRequest request){
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("prtdTypeCode", request.getParameter("prtdTypeCode"));
        PageRequest pageRequest = buildPageRequest();
        Page<Map<String, Object>> page = service.findByTypeCode(paramMap);
        return DataUtils.pageToMap(page);
    }
}
