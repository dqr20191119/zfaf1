package com.cesgroup.prison.zfjcj.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.zfjcj.entity.ZfjcjEntity;
import com.cesgroup.prison.zfjcj.vo.ZfjcjVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.HashMap;
import java.util.Map;

public interface IZfjcjService extends IBaseCRUDService<ZfjcjEntity, String> {

    /**
     * 分页查询所有
     * @param entity
     * @param pageable
     * @return
     */
    public Page<Map<String, Object>> listAll(ZfjcjVo entity, Pageable pageable);

    public void checkInfo(ZfjcjVo zfjcjVo);

}
