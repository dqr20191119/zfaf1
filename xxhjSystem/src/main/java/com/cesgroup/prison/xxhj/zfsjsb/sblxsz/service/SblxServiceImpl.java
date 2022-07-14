package com.cesgroup.prison.xxhj.zfsjsb.sblxsz.service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.dao.SblxMapper;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.dao.SblxszMapper;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity.SblxEntity;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity.SblxVo;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity.SblxszEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service("sblxService")
public class SblxServiceImpl extends BaseDaoService<SblxEntity, String, SblxMapper> implements SblxService {

    @Resource
    private SblxMapper sblxMapper;

    @Autowired
    private SblxszMapper sblxszMapper;

    @Override
    public Page<Map<String, Object>> listAll(SblxEntity entity, Pageable pageable) {
        Page<Map<String, Object>> page = sblxMapper.listAll(entity, pageable);
        return page;
    }

    @Override
    @Transactional
    public void addInfo(SblxVo sblxVo, HttpServletRequest request) throws Exception {
        List<SblxszEntity> sblxszList = sblxVo.getSblxszList();
        String id = "";
        UserBean userBean = AuthSystemFacade.getLoginUserInfo();
        if(StringUtils.isEmpty(sblxVo.getId())) {
            id = CommonUtil.createUUID();
            SblxEntity sblxEntity = new SblxEntity();
            sblxEntity.setId(id);
            sblxEntity.setPrtCusNumber(userBean.getCusNumber());
            sblxEntity.setPrtTypeCode(id);
            sblxEntity.setPrtTypeName(sblxVo.getPrtTypeName());
            sblxEntity.setPrtRemark(sblxVo.getPrtRemark());
            sblxEntity.setPrtStatus("1");
            sblxEntity.setPrtCrteTime(new Date());
            sblxEntity.setPrtCrteUserId(userBean.getUserId());
            sblxEntity.setPrtCrteUserName(userBean.getUserName());
            sblxMapper.insertInfo(sblxEntity);
        } else {
            id = sblxVo.getId();
            SblxEntity sblxEntity = new SblxEntity();
            sblxEntity.setId(id);
            sblxEntity.setPrtTypeName(sblxVo.getPrtTypeName());
            sblxEntity.setPrtRemark(sblxVo.getPrtRemark());
            sblxEntity.setPrtUpdtTime(new Date());
            sblxEntity.setPrtUpdtUserId(userBean.getUserId());
            sblxEntity.setPrtUpdtUserName(userBean.getUserName());
            sblxMapper.updateInfo(sblxEntity);

            //删除历史记录
            sblxszMapper.deleteByTypeId(id);
        }
        if (sblxszList != null && sblxszList.size() > 0) {
            for (SblxszEntity sblxszEntity : sblxszList) {
                sblxszEntity.setId(CommonUtil.createUUID());
                sblxszEntity.setPrtdTypeCode(id);
                sblxszEntity.setPrtdRemark(sblxVo.getPrtRemark());
                sblxszEntity.setPrtdCrteTime(new Date());
                sblxszEntity.setPrtdCrteUserId(userBean.getUserId());
                sblxszEntity.setPrtdCrteUserName(userBean.getUserName());
                sblxszEntity.setPrtdStatus("1");
                sblxszMapper.insertInfo(sblxszEntity);
            }
        }
    }

    @Override
    public SblxEntity findById(Map<String, Object> paramMap) {
        return sblxMapper.findById(paramMap);
    }

    @Override
    @Transactional
    public void deleteByIds(List<String> list) {
        sblxMapper.deleteByIds(list);
        sblxszMapper.deleteByTypeCodes(list);
    }
}
