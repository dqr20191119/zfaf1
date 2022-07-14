package com.cesgroup.prison.viewPeople.service.impl;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.regionDepart.dao.RegionDepartMapper;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.viewPeople.dao.ViewPeopleMapper;
import com.cesgroup.prison.viewPeople.entity.ViewPeople;
import com.cesgroup.prison.viewPeople.service.IViewPeopleService;
import com.cesgroup.prison.viewPeople.vo.ViewPeopleVo;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ViewPeopleServiceImpl extends BaseService<ViewPeople, String> implements IViewPeopleService {
    @Autowired
    private ViewPeopleMapper viewPeopleMapper;
    @Autowired
    private RegionDepartMapper regionDepartMapper;

    @Override
    public List<ViewPeopleVo> findByCusNumberAndAreaId(String cusNumber, String areaId) {
        return viewPeopleMapper.findByCusNumberAndAreaId(cusNumber, areaId);
    }

    @Override
    @Transactional
    public int insertByBatch(String objs) throws Exception {
        List<ViewPeople> list = new ArrayList<>();
        //当前登陆用户
        UserBean loginUser = AuthSystemFacade.getLoginUserInfo();
        //当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        JSONArray array = JSONArray.fromObject(objs);
        List<?> list2 = JSONArray.toList(array, new ViewPeople(), new JsonConfig());
        for(int i=0; i<list2.size();i++) {
            ViewPeople viewPeople = (ViewPeople)list2.get(i);
            viewPeople.setId(CommonUtil.createUUID().replace("-", ""));
            viewPeople.setVprCrteUserId(loginUser.getUserId());
            viewPeople.setVprUpdtUserId(loginUser.getUserId());
            viewPeople.setVprCrteTime(df.parse(df.format(new Date())));
            viewPeople.setVprUpdtTime(df.parse(df.format(new Date())));
            if(viewPeople.getVprPoliceId()!=null && !"".equals(viewPeople.getVprPoliceId())){
                int flag = viewPeopleMapper.findDepAndAreaRelation(viewPeople.getVprPoliceId(), viewPeople.getVprAreaId());
                if (flag == 0) {
                    list.add(viewPeople);
                }
            }
        }
        //批量删除
        ViewPeople vp = (ViewPeople)list2.get(0);
        this.viewPeopleMapper.deleteByCusNumberAndAreaId(vp.getVprCusNumber(), vp.getVprAreaId());
        //批量添加
        int result = 0;
        if(list.size()>0){
            result = this.viewPeopleMapper.insertByBatch(list);
        }
        return result;
    }


    public List<Map<String, Object>> findHavedAreatree(Map<String, Object> paramMap) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();

        List<Map<String, Object>> parentAreaList = viewPeopleMapper.findParenAreaIdAndName(paramMap);
        List<Map<String, Object>> childAreaList = viewPeopleMapper.findChildAreaIdAndName(paramMap);

        for (Map<String, Object> parentMap : parentAreaList) {
            Map<String, Object> map = new HashMap<String, Object>();
            List<Map<String, Object>> childlist = new ArrayList<>();
            map.put("id", parentMap.get("id"));
            map.put("name", parentMap.get("name"));
            map.put("isParent", true);
            map.put("open", true);
            for (Map<String, Object> childMap : childAreaList) {
                Map<String, Object> map1 = new HashMap<String, Object>();
                map1.put("id", childMap.get("id"));
                map1.put("name", childMap.get("name"));
                map1.put("isParent", false);
                map1.put("open", true);
                if(childMap.get("id").toString().contains(parentMap.get("id").toString())){
                    childlist.add(map1);
                }
            }
            map.put("children", childlist);
            resultList.add(map);
        }
        return resultList;
    }
}
