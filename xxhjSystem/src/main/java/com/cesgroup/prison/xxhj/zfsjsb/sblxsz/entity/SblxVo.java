package com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity;

import com.cesgroup.framework.base.entity.StringIDEntity;

import java.util.List;

public class SblxVo extends StringIDEntity {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -5319092071869191100L;

    //类型名称
    private String  prtTypeName ;

    //备注
    private String  prtRemark ;

    //部门列表数据
    private List<SblxszEntity> sblxszList;

    public String getPrtTypeName() {
        return prtTypeName;
    }

    public void setPrtTypeName(String prtTypeName) {
        this.prtTypeName = prtTypeName;
    }

    public String getPrtRemark() {
        return prtRemark;
    }

    public void setPrtRemark(String prtRemark) {
        this.prtRemark = prtRemark;
    }

    public List<SblxszEntity> getSblxszList() {
        return sblxszList;
    }

    public void setSblxszList(List<SblxszEntity> sblxszList) {
        this.sblxszList = sblxszList;
    }
}
