package com.cesgroup.prison.xwzc.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.xwzc.entity.XwzcEntity;
import com.cesgroup.prison.xwzc.entity.XwzcNowEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Map;

/**
 *@ClassName XwzcNowMapper
 *@Description TODO
 *@Author lh
 *@Date 2020/8/12 10:09
 *
 **/
public interface XwzcNowMapper extends BaseDao<XwzcNowEntity, String> {
    public Page<XwzcEntity> searchXwzcList(XwzcEntity xwzcNowEntity, PageRequest pageRequest);

    public Map<String, Object> searchXwzcCount(Map<String, Object> map);

    int searchXwzcBeforeStartTimeCount(XwzcNowEntity xwzcNowEntity);

    void deleteBeforeStartTime(XwzcNowEntity xwzcNowEntity);
}
