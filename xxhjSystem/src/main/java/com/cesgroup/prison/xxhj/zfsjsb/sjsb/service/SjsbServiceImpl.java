package com.cesgroup.prison.xxhj.zfsjsb.sjsb.service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.common.bean.user.UserBean;
import com.cesgroup.prison.common.facade.AuthSystemFacade;
import com.cesgroup.prison.utils.CommonUtil;
import com.cesgroup.prison.xxhj.zfsjsb.sjsb.dao.SjsbMapper;
import com.cesgroup.prison.xxhj.zfsjsb.sjsb.entity.SjsbEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("sjsbService")
public class SjsbServiceImpl extends BaseDaoService<SjsbEntity, String, SjsbMapper> implements SjsbService {

    @Resource
    private SjsbMapper sjsbMapper;

    @Override
    public Page<Map<String, Object>> listAll(SjsbEntity entity, Pageable pageable) {
        Page<Map<String, Object>> page = sjsbMapper.listAll(entity, pageable);
        return page;
    }

    @Override
    public Page<Map<String, Object>> findByTimeAndDprtmntId(Map<String, Object> paramMap) {
        return sjsbMapper.findByTimeAndDprtmntId(paramMap);
    }

    @Override
    public Page<Map<String, Object>> getCntByTime(Map<String, Object> paramMap) {
        return sjsbMapper.getReportSjhz(paramMap);
    }

    @Override
    public Map<String, Object> getReportDprt(Map<String, Object> paramMap, HttpServletRequest request) {
        Map<String, Object> m = new HashMap<String, Object>();
        List<String> allReportDprt = sjsbMapper.getAllReportDprt(paramMap);
        List<String> reportedDprt = sjsbMapper.getAllReportDprtByCprTime(paramMap);
        allReportDprt.removeAll(reportedDprt);
        m.put("reportedDprt", reportedDprt);
        m.put("unreportedDprt", allReportDprt);
        return m;
    }

    @Override
    @Transactional
    public void deleteInfo(List<SjsbEntity> list) {
        for(SjsbEntity sjsb : list) {
            sjsbMapper.deleteReportDataByEntity(sjsb);
        }
    }

    @Override
    @Transactional
    public void addInfo(Map<String, Object> paramMap, HttpServletRequest request) throws Exception {
        SjsbEntity deleteEntity = new SjsbEntity();
        UserBean userBean = AuthSystemFacade.getLoginUserInfo();
        String cprRemark = "";
        if(paramMap.get("cprRemark") != null) {
            cprRemark = (String)paramMap.get("cprRemark");
        }
        deleteEntity.setCprDprtmntId((String)paramMap.get("cprDprtmntId"));
        deleteEntity.setCprTime((String)paramMap.get("cprTime"));
        deleteEntity.setCprCusNumber(userBean.getCusNumber());
        sjsbMapper.deleteReportDataByEntity(deleteEntity);
        if(paramMap.get("sbsjList") != null ) {
            for(Map<String, Object> m : (List<Map<String, Object>>)paramMap.get("sbsjList")) {
                String cprTypeCode = (String)m.get("cprTypeCode");
//                long cprNumber = Long.parseLong((String)m.get("cprNumber"));
                String cprNumber = (String) m.get("cprNumber");
                SjsbEntity entity = new SjsbEntity();
                entity.setId(CommonUtil.createUUID());
                entity.setCprCusNumber(userBean.getCusNumber());
                entity.setCprDprtmntId((String)paramMap.get("cprDprtmntId"));
                entity.setCprTypeCode(cprTypeCode);
                entity.setCprNumber(cprNumber);
                entity.setCprTime((String)paramMap.get("cprTime"));
                entity.setCprRemark(cprRemark);
                entity.setCprCrteTime(new Date());
                entity.setCprCrteUserId(userBean.getUserId());
                entity.setCprCrteUserName(userBean.getUserName());
                entity.setCprStatus("1");
                sjsbMapper.insertInfo(entity);
            }
        }
    }
}
