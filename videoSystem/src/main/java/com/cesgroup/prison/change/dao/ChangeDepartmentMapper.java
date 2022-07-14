package com.cesgroup.prison.change.dao;

import java.util.List;
import java.util.Map;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.change.entity.ChangeDepartment;

public interface ChangeDepartmentMapper extends BaseDao<ChangeDepartment, String> {
 
    int updateChangeDepartment(ChangeDepartment record);
    //批量新增整改部门信息
    public void batchInsertChaDep(List<Map<String,Object>> list);
    public List<ChangeDepartment> searchChangeDepartment(ChangeDepartment record);
    
    public void updateCcdStatus(ChangeDepartment record);
}