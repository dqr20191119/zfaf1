package com.cesgroup.prison.zfjcj.dao;

import com.cesgroup.framework.mybatis.dao.BaseDao;
import com.cesgroup.prison.foreign.vo.ForeignRegisterVo;
import com.cesgroup.prison.zfjcj.entity.ZfjcjEntity;
import com.cesgroup.prison.zfjcj.vo.ZfjcjVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ZfjcjMapper extends BaseDao<ZfjcjEntity, String> {
    Page<Map<String, Object>> listAll(ZfjcjVo entity, Pageable pageable);
}
