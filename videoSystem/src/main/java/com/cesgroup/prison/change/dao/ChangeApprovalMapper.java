package com.cesgroup.prison.change.dao;

import java.util.List;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.change.entity.ChangeApproval;

public interface ChangeApprovalMapper extends BaseDao<ChangeApproval, String>{

    int updateChangeApproval(ChangeApproval record);
    public List<ChangeApproval> searchChangeApproval(ChangeApproval record);
}