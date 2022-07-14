package com.cesgroup.prison.xxhj.zfsjsb.sblxsz.service;

import com.cesgroup.framework.base.service.IBaseCRUDService;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity.SblxEntity;
import com.cesgroup.prison.xxhj.zfsjsb.sblxsz.entity.SblxVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface SblxService extends IBaseCRUDService<SblxEntity, String> {

    /**
     * 分页查询所有
     * @param entity
     * @param pageable
     * @return
     */
    public Page<Map<String, Object>> listAll(SblxEntity entity, Pageable pageable);

    /**
     * 上报数据类型录入
     * @param sblxVo
     * @throws Exception
     */
    public void addInfo(SblxVo sblxVo, HttpServletRequest request) throws Exception;


    /**
     * 通过id查询
     * @param paramMap
     * @return
     */
    public SblxEntity findById(Map<String, Object> paramMap);

    /**
     * 根据id删记录
     * @param list
     */
    public void deleteByIds(List<String> list);
}
