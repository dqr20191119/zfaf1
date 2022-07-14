package com.cesgroup.prison.change.dao;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.change.entity.Change;

public interface ChangeMapper extends BaseDao<Change, String>{

    int updateChange(Change record);

    public Page<Map<String, String>> launchListPage(Map<String, Object> paramMap, PageRequest pageRequest);
    public Page<Map<String, String>> changeListPage(Map<String, Object> paramMap, PageRequest pageRequest);
    public Page<Map<String, String>> checkListPage(Map<String, Object> paramMap, PageRequest pageRequest);
    public Page<Map<String, String>> recordListPage(Map<String, Object> paramMap, PageRequest pageRequest);
}