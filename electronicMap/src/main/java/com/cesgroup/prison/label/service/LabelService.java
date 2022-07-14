package com.cesgroup.prison.label.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cesgroup.framework.biz.service.BaseService;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.label.dao.LabelMapper;
import com.cesgroup.prison.label.entity.Label;
import com.cesgroup.prison.utils.CommonUtil;

@Service
@Transactional(readOnly = false)
public class LabelService extends BaseService<Label, String> {

    @Autowired
    private LabelMapper labelMapper;


    public int insertLabel(Label label) throws Exception {
        Label ifHave = labelMapper.findByLabelCode(label);
        if (ifHave == null) {
            label.setId(CommonUtil.createUUID().replace("-", ""));
            label.setMliCrteUserId(AuthSystemFacade.getLoginUserInfo().getUserId());
            label.setMliCrteTime(new Date());
            label.setMliCusNumber(AuthSystemFacade.getLoginUserInfo().getCusNumber());
            return labelMapper.insertSelectiveLabel(label);
        }else {
            throw new RuntimeException();
        }
/*        label.setId(CommonUtil.createUUID().replace("-", ""));
        label.setMliCrteUserId(AuthSystemFacade.getLoginUserInfo().getUserId());
        label.setMliCrteTime(new Date());
        label.setMliCusNumber(AuthSystemFacade.getLoginUserInfo().getCusNumber());
        return labelMapper.insertSelectiveLabel(label);*/

    }

    public int updateLabel(Label label) throws Exception {
        if (label.getId().isEmpty()) {
            throw new RuntimeException("缺少id");
        }
        label.setMliUpdtUserId(AuthSystemFacade.getLoginUserInfo().getUserId());
        label.setMliUpdtTime(new Date());
        return labelMapper.updateByPrimaryKeySelective(label);
    }

    public int delLabel(String id) {
        return labelMapper.deleteByPrimaryKey(id);
    }

    public Label selectById(String id) {
        return labelMapper.selectByPrimaryKey(id);
    }

    public Page<Label> selectPageLabel(Label label, Pageable pageable) {
        return labelMapper.selectPageLabel(label, pageable);
    }

    public List<Label> labelList(String mliCusNumber, String mliAreaId) {
        List<Label> list = null;
        try {
            list = labelMapper.findByCusNumberAndAreaId(mliCusNumber, mliAreaId);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }
}