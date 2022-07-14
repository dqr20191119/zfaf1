package com.cesgroup.prison.tool.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.doorstate.entity.DoorStateEntity;
import com.cesgroup.prison.tool.entity.TKhLdgj;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

/**
 * Created byjing on2019/5/26
 **/
public interface ToolService   extends IBaseCRUDService<TKhLdgj, String> {
    //查询数量
    public Integer getToolOpenNum(String cusNumber);
    //列表
    public Page<Map<String, Object>> listAll(Map<String, Object> map, Pageable pageable);
}
