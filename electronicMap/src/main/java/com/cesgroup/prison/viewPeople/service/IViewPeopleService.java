package com.cesgroup.prison.viewPeople.service;

import com.cesgroup.prison.viewPeople.vo.ViewPeopleVo;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@Service
public interface IViewPeopleService {
    public List<ViewPeopleVo> findByCusNumberAndAreaId(String cusNumber, String areaId);

    public int insertByBatch(String objs) throws ParseException, Exception;

    public List<Map<String, Object>> findHavedAreatree(Map<String, Object> paramMap);
}
