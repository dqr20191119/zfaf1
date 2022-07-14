package com.cesgroup.prison.xxhj.zfsjsb.sblxsz.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity.SblxszEntity;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface SblxszService extends IBaseCRUDService<SblxszEntity, String> {

    Page<Map<String, Object>> findByTypeCode(Map<String, Object> paramMap);

}
