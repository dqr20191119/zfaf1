package com.cesgroup.prison.zfjcj.service.impl;

import ces.sdk.system.bean.UserInfo;
import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.zfjcj.dao.ZfjcjMapper;
import com.cesgroup.prison.zfjcj.entity.ZfjcjEntity;
import com.cesgroup.prison.zfjcj.service.IZfjcjService;
import com.cesgroup.prison.zfjcj.vo.ZfjcjVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
public class ZfjcjService extends BaseDaoService<ZfjcjEntity, String, ZfjcjMapper> implements IZfjcjService {

    @Autowired
    private ZfjcjMapper zfjcjMapper;

    @Override
    @Transactional(readOnly = true)
    public Page<Map<String, Object>> listAll(ZfjcjVo entity, Pageable pageable) {
        Page<Map<String, Object>> page = zfjcjMapper.listAll(entity, pageable);

        return page;

    }

    @Override
    @Transactional
    public void checkInfo(ZfjcjVo zfjcjVo) {
        try {
            ZfjcjEntity zfjcjEntity = new ZfjcjEntity();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            UserBean user = AuthSystemFacade.getLoginUserInfo();
            zfjcjEntity.setPirCusNumber(zfjcjVo.getJyid());
            zfjcjEntity.setPirApprovalPoliceName(user.getUserName());
            zfjcjEntity.setPirApprovalPolice(user.getUserId());
            zfjcjEntity.setPirApprovalTime(simpleDateFormat.format(new Date()));
            zfjcjEntity.setPirApprovalStts("1");
            zfjcjEntity.setPirLjRq(zfjcjVo.getLjrq());
            zfjcjEntity.setPirZfBh(zfjcjVo.getZfbh());
            zfjcjMapper.insert(zfjcjEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
