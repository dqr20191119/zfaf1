package com.cesgroup.prison.tool.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.tool.entity.TKhLdgj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface TKhLdgjMapper extends BaseDao<TKhLdgj, String> {

    //劳具数量
    int getToolNum(String prisonCode);

    //列表
    Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);
}