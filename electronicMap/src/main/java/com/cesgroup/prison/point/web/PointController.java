package com.cesgroup.prison.point.web;

import com.cesgroup.framework.base.annotation.Logger;
import com.cesgroup.framework.bean.AjaxMessage;
import com.cesgroup.framework.exception.WebUIException;
import com.cesgroup.framework.springmvc.web.BaseEntityDaoServiceCRUDController;
import com.cesgroup.prison.point.dao.PointMapper;
import com.cesgroup.prison.point.entity.Point;
import com.cesgroup.prison.point.service.PointService;
import com.cesgroup.prison.point.vo.PointVo;
import com.cesgroup.prison.utils.DataUtils;
import com.cesgroup.prison.view.entity.View;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *
 * @ClassName：PointController
 * @Description：点位访问Controller
 * @author：mxz
 * @date：2017-12-20 09:49
 * @version
 */
@Controller
@RequestMapping(value = "/point")
public class PointController extends BaseEntityDaoServiceCRUDController<Point,String,PointMapper,PointService>{

    @Autowired
    private PointService PointService;

    @RequestMapping("/index")
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView("point/index");
        return mv;
    }

    @RequestMapping("/index2")
    public ModelAndView index2(){
        ModelAndView mv = new ModelAndView("pointDorm/index");
        return mv;
    }
    /**
     * 跳转信息弹框
     */
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView mv = new ModelAndView("point/list");
        return mv;
    }

    @RequestMapping("/list2")
    public ModelAndView list2() {
        ModelAndView mv = new ModelAndView("pointDorm/list");
        return mv;
    }
    /**
     * 根据区域id和设备类型获取设备信息
     * @param pointVo
     * @return
     */
    @RequestMapping(value = "/getAllDevice")
    @ResponseBody
    @Logger(action = "查询设备", logger = "根据区域id和设备类型获取设备信息")
    public List<PointVo> getAllDevice(PointVo pointVo) throws WebUIException, ParseException {
        List<PointVo> page = getService().getAllDevice(pointVo);
        return page;
    }
    /**
     * 获取所有设备的信息  单独维护
     * @param pointVo
     * @return
     */
    @RequestMapping(value = "/getAllTypeDevice")
    @ResponseBody
    @Logger(action = "查询设备", logger = "获取所有设备的信息  单独维护")
    public Map<String, Object>  getAllTypeDevice(PointVo pointVo,PageRequest pageRequest) throws WebUIException, ParseException {
        Page<PointVo> page = getService().getAllTypeDevice(pageRequest,pointVo);
        return  DataUtils.pageToMap(page);
    }
    /**
     * 点位保存
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/savePoint")
    @ResponseBody
    @Logger(action = "保存视角", logger = "保存视角信息")
    public Map<String,Object> savePoint(PointVo PointVo) throws WebUIException, ParseException {
        return PointService.savePoint(PointVo);
    }
    /**
     * 点位信息列表（分页）
     * @return
     */
    @RequestMapping(value = "/findByPage")
    @ResponseBody
    @Logger(action = "点位分页查询", logger = "点位分页查询")
    public Map<String, Object> findByPage(PointVo PointVo,PageRequest pageRequest) {
        Page<PointVo> page = PointService.findByPage(pageRequest,PointVo);
        return DataUtils.pageToMap(page);
    }

    /**
     * 删除方法
     * @param id
     * @return
     */
/*    @Logger(action="删除",logger="${id}")
    @RequestMapping(value="/destoryPoint/{id}")
    @ResponseBody
    public Map<String, Object> destoryPoint(@PathVariable("id") String id) {
        return  getService().destoryPoint(id);
    }*/
    @Logger(action = "删除", logger = "删除")
    @RequestMapping(value = "/destoryPoint")
    @ResponseBody
    public AjaxMessage destoryPoint(String id){
        AjaxMessage ajaxMessage = new AjaxMessage();
        boolean flag = false;
        Map<String ,String> map =new HashMap<String ,String>();
        try {
            getService().destoryPoint(id);
            flag = true;
            ajaxMessage.setSuccess(flag);
            ajaxMessage.setMsg("操作成功");
        }catch(Exception e) {
            ajaxMessage.setMsg("操作失败");
        }
        return ajaxMessage;
    }
    /**
     * 根据设备id和监狱编号 获取此设备的点位信息
     * @param pointVo
     * @return
     */
    @RequestMapping(value = "/getDevicePointById")
    @ResponseBody
    @Logger(action = "查询设备", logger = "根据设备id和监狱编号 获取此设备的点位信息")
    public List<PointVo> getDevicePointById(PointVo pointVo) throws WebUIException, ParseException {
        List<PointVo> data = getService().getDevicePointById(pointVo);
        return data;
    }
    /**
     * 根据区域id和监狱编号 获取视角信息
     * @param pointVo
     * @return
     */
    @RequestMapping(value = "/getViewByGrandId")
    @ResponseBody
    @Logger(action = "查询设备", logger = "根据设备id和监狱编号 获取此设备的点位信息")
    public List<View> getViewByGrandId(PointVo pointVo) throws WebUIException, ParseException {
        List<View> data = getService().getViewByGrandId(pointVo);
        return data;
    }
    /** 1、根据区域id，设备类型,监狱编号 获取此区域下的所有点位
     *  2、是否显示子区域下的所有点位 0、不显示1、显示
     *  3、返回所有点位模型对象
     *  @Param pointVo
     *  @return
     */
    @RequestMapping(value = "/getAllPointByObj")
    @ResponseBody
    @Logger(action = "查询点位", logger = "")
    public List<PointVo> getAllPointByObj(PointVo pointVo) throws WebUIException, ParseException {
        List<PointVo> data = getService().getAllPointByObj(pointVo);
        return data;
    }

    /**
     * 根据CusNumber分页查询所有监舍信息
     * @param pointVo
     * @param pageRequest
     * @return
     */
    @RequestMapping(value = "/findByDormPage")
    @ResponseBody
    @Logger(action = "分页查询监舍信息", logger = "监舍点位分页查询")
    public Map<String, Object> findByDormPage(PointVo pointVo, PageRequest pageRequest) {
        return DataUtils.pageToMap(PointService.findByDormPage(pageRequest, pointVo));
    }
}
