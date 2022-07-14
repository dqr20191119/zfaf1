package com.cesgroup.prison.xxhj.zfsjsb.sblxsz.service;

import com.cesgroup.framework.biz.service.BaseDaoService;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.dao.SblxszMapper;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity.SblxszEntity;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service("sblxszService")
public class SblxszServiceImpl extends BaseDaoService<SblxszEntity, String, SblxszMapper> implements SblxszService {

    @Resource
    private SblxszMapper sblxszMapper;

    @Override
    public Page<Map<String, Object>> findByTypeCode(Map<String, Object> paramMap) {
        return sblxszMapper.findByTypeCode(paramMap);
    }


}
